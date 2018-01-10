package auto.deploy.service.dev.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.IdentityService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.gitlab.api.models.GitlabBranch;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import auto.deploy.activiti.service.ActivitiService;
import auto.deploy.dao.config.Where;
import auto.deploy.dao.entity.dev.DevBranch;
import auto.deploy.dao.entity.dev.DevProject;
import auto.deploy.dao.entity.dev.DevProjectActor;
import auto.deploy.dao.mapper.dev.DevBranchMapper;
import auto.deploy.gitlab.service.GitlabService;
import auto.deploy.object.PageBean;
import auto.deploy.service.dev.DevBranchService;
import auto.deploy.service.dev.DevProjectActorService;
import auto.deploy.service.dev.DevProjectService;

/**
 * 
 * @描述：项目分支表(服务实现类).
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-06-17
 */
@Service
public class DevBranchServiceImpl extends ServiceImpl<DevBranchMapper, DevBranch> implements DevBranchService {

	@Resource
	private DevProjectService devProjectService;
	@Resource
	private GitlabService gitlabService;
	@Resource
	private ActivitiService activitiService;
	@Resource
	private IdentityService identityService;
	@Resource
	private TaskService taskService;
	@Resource
	private DevProjectActorService devProjectActorService;

	@Override
	public Page<DevBranch> list(PageBean pageBean, DevBranch obj) throws Exception {
		Where<DevBranch> where = new Where<DevBranch>();
		where.orderBy("create_time", false);
		Page<DevBranch> page = selectPage(new Page<DevBranch>(pageBean.getPageNum(), pageBean.getPageSize()), where);
		return page;
	}

	@Override
	@Transactional
	public void add(DevBranch obj, Long userId) throws Exception {
		// 插入分支表
		DevProject project = devProjectService.selectById(obj.getProjectId());
		DevBranch branch = selectById(obj.getParentBranchId());
		if (branch == null) {
			// 如果没有选择分支，则默认master分支，检查厦门是否已经有master分支，如果没有没提示
			List<GitlabBranch> list = gitlabService.getProjectBranchList(project.getGitlabProjectId());
			int flag = 0;
			for (GitlabBranch gitlabBranch : list) {
				if (gitlabBranch.getName().equals("master")) {
					// 插入master分支
					branch = new DevBranch();
					branch.setBranchName("master");
					branch.setProjectId(obj.getProjectId());
					branch.setProjectName(project.getProjectName());
					branch.setIsDeploySuccess(0);
					branch.setProjectStatus(0);
					branch.setParentBranchId(0L);
					branch.setParentBranchName("初始化master分支");
					branch.setIsActive(1);
					insert(branch);
					flag++;
					break;
				}
			}
			if (flag == 0) {
				throw new Exception("该项目没有master分支，请手工创建master分支");
			}
		}
		obj.setProjectName(project.getProjectName());
		obj.setIsDeploySuccess(0);
		obj.setProjectStatus(0);
		obj.setIsActive(1);
		obj.setParentBranchName(branch.getBranchName());
		obj.setParentBranchId(branch.getId());
		insert(obj);
		// 启动流程
		identityService.setAuthenticatedUserId(userId.toString());
		Map<String, String> param = new HashMap<String, String>();
		param.put("projectId", String.valueOf(obj.getProjectId()));
		param.put("branchId", String.valueOf(obj.getId()));
		ProcessInstance processInstance = activitiService.startProcess("it_project_develop_cycle", param);
		setCandidateUser(processInstance, obj.getProjectId());
		// 新建远程分支
		gitlabService.addBranch(project.getGitlabProjectId(), obj.getBranchName(), branch.getBranchName());
	}

	@Override
	@Transactional
	public void setCandidateUser(ProcessInstance processInstance, Long projectId) throws Exception {
		Task task = taskService.createTaskQuery().processInstanceId(processInstance.getProcessInstanceId()).singleResult();
		String taskKey = task.getTaskDefinitionKey();
		Integer projectStage = null;
		if ("task_branch".equals(taskKey)) {
			// 1-项目分支
			projectStage = 1;
		} else if ("task_dev".equals(taskKey)) {
			// 2-日常开发
			projectStage = 2;
		} else if ("task_test_approval".equals(taskKey)) {
			// 3-日常测试审批
			projectStage = 3;
		} else if ("task_test".equals(taskKey)) {
			// 4-日常测试
			projectStage = 4;
		} else if ("task_check_approval".equals(taskKey)) {
			// 5-验收测试审批
			projectStage = 5;
		} else if ("task_check".equals(taskKey)) {
			// 6-验收测试
			projectStage = 6;
		} else if ("task_prepare_approval".equals(taskKey)) {
			// 7-预发环境审批
			projectStage = 7;
		} else if ("task_prepare".equals(taskKey)) {
			// 8-预发环境
			projectStage = 8;
		} else if ("task_produce_approval".equals(taskKey)) {
			// 9-生产环境审批
			projectStage = 9;
		} else if ("task_produce".equals(taskKey)) {
			// 10-生产环境
			projectStage = 10;
		}
		Where<DevProjectActor> where = new Where<DevProjectActor>();
		where.eq("project_id", projectId);
		where.eq("project_stage", projectStage);
		List<DevProjectActor> list = devProjectActorService.selectList(where);
		for (DevProjectActor devProjectActor : list) {
			taskService.addUserIdentityLink(task.getId(), devProjectActor.getUserId().toString(), "candidate");
		}
	}

}
