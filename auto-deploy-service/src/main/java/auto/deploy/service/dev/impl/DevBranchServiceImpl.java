package auto.deploy.service.dev.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
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
import auto.deploy.object.aut.vo.DevTaskVO;
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
	@Resource
	private RuntimeService runtimeService;
	@Resource
	private DevBranchService devBranchService;

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
		param.put("projectName", project.getProjectName());
		param.put("branchName", obj.getBranchName());
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

	@Override
	public Page<DevTaskVO> getProcessBranch(PageBean pageBean, Map<String, String> param) throws Exception {
		Page<DevTaskVO> devTaskVOListPage = new Page<DevTaskVO>();
		List<DevTaskVO> devTaskVOList = new ArrayList<DevTaskVO>();
		// taskKey实际上是标志不同的开发阶段
		String taskKey = param.get("taskKey");
		// 用户ID，过来候选权限
		String userId = param.get("userId");
		// 查询关键字(匹配项目名，分支名以及节点名称)
		String searchKey = param.get("searchKey");

		TaskQuery query = taskService.createTaskQuery().taskCandidateUser(userId).taskDefinitionKey(taskKey).taskDescriptionLikeIgnoreCase(searchKey);

		// 获取任务数量
		Long count = query.count();
		// 获取任务结果集
		// 分页开始索引
		int firstResult = (pageBean.getPageNum() - 1) * pageBean.getPageSize();
		// 分页查询记录数
		int maxResults = pageBean.getPageSize();
		List<Task> taskList = query.orderByTaskCreateTime().desc().listPage(firstResult, maxResults);
		// 获取所有任务的流程实例
		Set<String> processInstanceIdSet = new HashSet<String>();
		for (Task task : taskList) {
			processInstanceIdSet.add(task.getProcessInstanceId());
		}

		// 流程实例集合，其中key是流程实例ID的，value是对应的流程实例
		Map<String, ProcessInstance> processInstanceMap = new HashMap<String, ProcessInstance>();

		// 流程实例id-项目
		Map<String, DevProject> instanceProjectMap = new HashMap<String, DevProject>();
		Map<String, String> instanceProjectIdMap = new HashMap<String, String>();
		Map<String, DevProject> projectMap = new HashMap<String, DevProject>();
		// 所有项目id的set
		Set<String> projectIdSet = new HashSet<String>();

		// 流程实例id-分支
		Map<String, DevBranch> instanceBranchMap = new HashMap<String, DevBranch>();
		Map<String, String> instanceBranchIdMap = new HashMap<String, String>();
		Map<String, DevBranch> branchMap = new HashMap<String, DevBranch>();
		// 所有分支id的set
		Set<String> branchIdSet = new HashSet<String>();

		// 构造processInstanceMap
		if (processInstanceIdSet.size() > 0) {
			List<ProcessInstance> processInstanceList = runtimeService.createProcessInstanceQuery().processInstanceIds(processInstanceIdSet).list();
			for (ProcessInstance processInstance : processInstanceList) {
				processInstanceMap.put(processInstance.getProcessInstanceId(), processInstance);
				String[] projectIdAndBranchIdArr = processInstance.getBusinessKey().split(",");
				String projectId = projectIdAndBranchIdArr[0];
				String branchId = projectIdAndBranchIdArr[1];
				instanceProjectIdMap.put(processInstance.getProcessInstanceId(), projectId);
				instanceBranchIdMap.put(processInstance.getProcessInstanceId(), branchId);
				projectIdSet.add(projectId);
				branchIdSet.add(branchId);
			}
		}
		// 构造instanceProjectMap
		if (projectIdSet.size() > 0) {
			Where<DevProject> projectWhere = new Where<DevProject>();
			projectWhere.in("id", projectIdSet);
			List<DevProject> projectList = devProjectService.selectList(projectWhere);
			for (DevProject devProject : projectList) {
				projectMap.put(devProject.getId().toString(), devProject);
			}
		}
		for (Map.Entry<String, String> entry : instanceProjectIdMap.entrySet()) {
			String instanceId = entry.getKey();
			String projectId = entry.getValue();
			instanceProjectMap.put(instanceId, projectMap.get(projectId));
		}

		// 构造instanceDevBranchMap
		if (branchIdSet.size() > 0) {
			Where<DevBranch> branchWhere = new Where<DevBranch>();
			branchWhere.in("id", branchIdSet);
			List<DevBranch> branchList = devBranchService.selectList(branchWhere);
			for (DevBranch devBranch : branchList) {
				branchMap.put(devBranch.getId().toString(), devBranch);
			}
		}
		for (Map.Entry<String, String> entry : instanceBranchIdMap.entrySet()) {
			String instanceId = entry.getKey();
			String branchId = entry.getValue();
			instanceBranchMap.put(instanceId, branchMap.get(branchId));
		}
		// 构造返回对象
		for (Task task : taskList) {
			DevTaskVO vo = new DevTaskVO();
			vo.setAssignee(task.getAssignee());
			vo.setCategory(task.getCategory());
			vo.setCreateTime(task.getCreateTime());
			vo.setDescription(task.getDescription());
			vo.setDevBranch(instanceBranchMap.get(task.getProcessInstanceId()));
			vo.setDevProject(instanceProjectMap.get(task.getProcessInstanceId()));
			vo.setDueDate(task.getDueDate());
			vo.setExecutionId(task.getExecutionId());
			vo.setFormKey(task.getFormKey());
			vo.setId(task.getId());
			vo.setName(task.getName());
			vo.setOwner(task.getOwner());
			vo.setParentTaskId(task.getParentTaskId());
			vo.setPriority(task.getPriority());
			vo.setProcessDefinitionId(task.getProcessDefinitionId());
			vo.setProcessInstanceId(task.getProcessInstanceId());
			vo.setProcessVariables(task.getProcessVariables());
			vo.setTaskDefinitionKey(task.getTaskDefinitionKey());
			vo.setTaskLocalVariables(task.getTaskLocalVariables());
			vo.setTenantId(task.getTenantId());
			devTaskVOList.add(vo);
		}

		devTaskVOListPage.setRecords(devTaskVOList);
		devTaskVOListPage.setTotal(count.intValue());
		devTaskVOListPage.setSize(pageBean.getPageSize());
		devTaskVOListPage.setCurrent(pageBean.getPageNum());
		return devTaskVOListPage;
	}

}
