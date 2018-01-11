package auto.deploy.service.dev.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;
import org.gitlab.api.models.GitlabProject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.StringUtils;

import auto.deploy.dao.config.Where;
import auto.deploy.dao.entity.aut.AutUser;
import auto.deploy.dao.entity.dev.DevProject;
import auto.deploy.dao.entity.dev.DevProjectActor;
import auto.deploy.dao.entity.dev.DevProjectGroup;
import auto.deploy.dao.mapper.dev.DevProjectMapper;
import auto.deploy.gitlab.service.GitlabService;
import auto.deploy.object.PageBean;
import auto.deploy.object.RetMsg;
import auto.deploy.service.aut.AutUserService;
import auto.deploy.service.dev.DevProjectActorService;
import auto.deploy.service.dev.DevProjectGroupService;
import auto.deploy.service.dev.DevProjectService;

/**
 * 
 * @描述：项目表(服务实现类).
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-06-17
 */
@Service
public class DevProjectServiceImpl extends ServiceImpl<DevProjectMapper, DevProject> implements DevProjectService {

	@Resource
	private DevProjectGroupService devProjectGroupService;
	@Resource
	private GitlabService gitlabService;
	@Resource
	private DevProjectActorService devProjectActorService;
	@Resource
	private AutUserService autUserService;
	@Resource
	private TaskService taskService;
	@Resource
	private RuntimeService runtimeService;

	@Override
	public Page<DevProject> list(PageBean pageBean, DevProject obj) throws Exception {
		Where<DevProject> where = new Where<DevProject>();
		if (StringUtils.isNotEmpty(obj.getProjectName())) {
			where.like("project_name", obj.getProjectName());
			where.or("project_desc LIKE {0}", "%" + obj.getProjectName() + "%");
		}
		where.orderBy("create_time", false);
		Page<DevProject> page = selectPage(new Page<DevProject>(pageBean.getPageNum(), pageBean.getPageSize()), where);
		return page;
	}

	@Override
	@Transactional
	public void add(DevProject obj) throws Exception {
		// 获取分组信息
		DevProjectGroup group = devProjectGroupService.selectById(obj.getGroupId());
		// 新增git项目
		obj.setGitlabGroupId(group.getGitlabGroupId());
		GitlabProject gitlabProject = gitlabService.createProject(obj);
		// 回填gitlab项目信息
		obj.setSshLink(gitlabProject.getSshUrl());

		obj.setGroupName(group.getGroupName());
		obj.setGitlabProjectId(gitlabProject.getId());
		insert(obj);
	}

	@Override
	@Transactional
	public void del(DevProject obj) throws Exception {
		// 删除gitlab项目
		obj = selectById(obj.getId());
		gitlabService.delProject(obj);
		deleteById(obj.getId());
	}

	private DevProjectActor makeDevProjectActor(String id, DevProject project, Integer projectStage) throws Exception {
		AutUser user = autUserService.selectById(Long.parseLong(id));
		DevProjectActor devProjectActor = new DevProjectActor();
		devProjectActor.setUserId(user.getId());
		devProjectActor.setUserName(user.getUserName());
		devProjectActor.setProjectId(project.getId());
		devProjectActor.setProjectName(project.getProjectName());
		devProjectActor.setIsActive(1);
		devProjectActor.setProjectStage(projectStage);
		return devProjectActor;
	}

	@Override
	@Transactional
	public RetMsg setActor(DevProject obj, String devUserIds, String testUserIds, String checkUserIds, String prepareUserIds, String produceUserIds,
			String testApprovalUserIds, String checkApprovalUserIds, String prepareApprovalUserIds, String produceApprovalUserIds) throws Exception {
		RetMsg retMsg = new RetMsg();

		List<String> devUserIdsArr = Arrays.asList(devUserIds.split(","));
		List<String> testUserIdsArr = Arrays.asList(testUserIds.split(","));
		List<String> checkUserIdsArr = Arrays.asList(checkUserIds.split(","));
		List<String> prepareUserIdsArr = Arrays.asList(prepareUserIds.split(","));
		List<String> produceUserIdsArr = Arrays.asList(produceUserIds.split(","));

		List<String> testApprovalUserIdsArr = Arrays.asList(testApprovalUserIds.split(","));
		List<String> checkApprovalUserIdsArr = Arrays.asList(checkApprovalUserIds.split(","));
		List<String> prepareApprovalUserIdsArr = Arrays.asList(prepareApprovalUserIds.split(","));
		List<String> produceApprovalUserIdsArr = Arrays.asList(produceApprovalUserIds.split(","));

		DevProject project = selectById(obj.getId());
		// 先删除在增加
		Where<DevProjectActor> where0 = new Where<DevProjectActor>();
		where0.eq("project_id", obj.getId());
		devProjectActorService.delete(where0);

		// 一次插入
		List<DevProjectActor> list = new ArrayList<DevProjectActor>();
		// 开发
		for (String id : devUserIdsArr) {
			if (StringUtils.isNotEmpty(id)) {
				list.add(makeDevProjectActor(id, project, 2));
			}
		}
		// 测试
		for (String id : testUserIdsArr) {
			if (StringUtils.isNotEmpty(id)) {
				list.add(makeDevProjectActor(id, project, 4));
			}
		}
		// 验收
		for (String id : checkUserIdsArr) {
			if (StringUtils.isNotEmpty(id)) {
				list.add(makeDevProjectActor(id, project, 6));
			}
		}
		// 预发
		for (String id : prepareUserIdsArr) {
			if (StringUtils.isNotEmpty(id)) {
				list.add(makeDevProjectActor(id, project, 8));
			}
		}
		// 生产
		for (String id : produceUserIdsArr) {
			if (StringUtils.isNotEmpty(id)) {
				list.add(makeDevProjectActor(id, project, 10));
			}
		}

		// 测试审批
		for (String id : testApprovalUserIdsArr) {
			if (StringUtils.isNotEmpty(id)) {
				list.add(makeDevProjectActor(id, project, 3));
			}
		}
		// 验收审批
		for (String id : checkApprovalUserIdsArr) {
			if (StringUtils.isNotEmpty(id)) {
				list.add(makeDevProjectActor(id, project, 5));
			}
		}
		// 预发审批
		for (String id : prepareApprovalUserIdsArr) {
			if (StringUtils.isNotEmpty(id)) {
				list.add(makeDevProjectActor(id, project, 7));
			}
		}
		// 生产审批
		for (String id : produceApprovalUserIdsArr) {
			if (StringUtils.isNotEmpty(id)) {
				list.add(makeDevProjectActor(id, project, 9));
			}
		}
		if (list.size() > 0) {
			devProjectActorService.insertBatch(list);
		}

		// 给项目分支项目分支设置流程候选人
		List<Task> taskList = taskService.createTaskQuery().processInstanceBusinessKeyLike(project.getId().toString() + "%")
				.processDefinitionKey("it_project_develop_cycle").list();
		for (Task task : taskList) {
			// 获取已有的任务-用户关系
			List<IdentityLink> identityLinkList = taskService.getIdentityLinksForTask(task.getId());
			// 原来的候选用户
			Set<String> orgnlCandidateUserIdSet = new HashSet<String>();
			// 将要增加的候选用户
			Set<String> addCandidateUserIdSet = new HashSet<String>();
			// 将要删除的候选用户
			Set<String> delCandidateUserIdSet = new HashSet<String>();
			for (IdentityLink identityLink : identityLinkList) {
				if ("candidate".equals(identityLink.getType()) && StringUtils.isNotEmpty(identityLink.getUserId())) {
					orgnlCandidateUserIdSet.add(identityLink.getUserId());
				}
			}

			String taskKey = task.getTaskDefinitionKey();
			if ("task_branch".equals(taskKey) || "task_dev".equals(taskKey)) {
				// 计算新增
				for (String id : devUserIdsArr) {
					if (!orgnlCandidateUserIdSet.contains(id) && StringUtils.isNotEmpty(id)) {
						addCandidateUserIdSet.add(id);
					}
				}
				// 计算删除
				for (String id : orgnlCandidateUserIdSet) {
					if (!devUserIdsArr.contains(id) && StringUtils.isNotEmpty(id)) {
						delCandidateUserIdSet.add(id);
					}
				}
			} else if ("task_test_approval".equals(taskKey)) {
				// 计算新增
				for (String id : testApprovalUserIdsArr) {
					if (!orgnlCandidateUserIdSet.contains(id) && StringUtils.isNotEmpty(id)) {
						addCandidateUserIdSet.add(id);
					}
				}
				// 计算删除
				for (String id : orgnlCandidateUserIdSet) {
					if (!testApprovalUserIdsArr.contains(id) && StringUtils.isNotEmpty(id)) {
						delCandidateUserIdSet.add(id);
					}
				}
			} else if ("task_test".equals(taskKey)) {
				// 计算新增
				for (String id : testUserIdsArr) {
					if (!orgnlCandidateUserIdSet.contains(id) && StringUtils.isNotEmpty(id)) {
						addCandidateUserIdSet.add(id);
					}
				}
				// 计算删除
				for (String id : orgnlCandidateUserIdSet) {
					if (!testUserIdsArr.contains(id) && StringUtils.isNotEmpty(id)) {
						delCandidateUserIdSet.add(id);
					}
				}
			} else if ("task_check_approval".equals(taskKey)) {
				// 计算新增
				for (String id : checkApprovalUserIdsArr) {
					if (!orgnlCandidateUserIdSet.contains(id) && StringUtils.isNotEmpty(id)) {
						addCandidateUserIdSet.add(id);
					}
				}
				// 计算删除
				for (String id : orgnlCandidateUserIdSet) {
					if (!checkApprovalUserIdsArr.contains(id) && StringUtils.isNotEmpty(id)) {
						delCandidateUserIdSet.add(id);
					}
				}
			} else if ("task_check".equals(taskKey)) {
				// 计算新增
				for (String id : checkUserIdsArr) {
					if (!orgnlCandidateUserIdSet.contains(id) && StringUtils.isNotEmpty(id)) {
						addCandidateUserIdSet.add(id);
					}
				}
				// 计算删除
				for (String id : orgnlCandidateUserIdSet) {
					if (!checkUserIdsArr.contains(id) && StringUtils.isNotEmpty(id)) {
						delCandidateUserIdSet.add(id);
					}
				}
			} else if ("task_prepare_approval".equals(taskKey)) {
				// 计算新增
				for (String id : prepareApprovalUserIdsArr) {
					if (!orgnlCandidateUserIdSet.contains(id) && StringUtils.isNotEmpty(id)) {
						addCandidateUserIdSet.add(id);
					}
				}
				// 计算删除
				for (String id : orgnlCandidateUserIdSet) {
					if (!prepareApprovalUserIdsArr.contains(id) && StringUtils.isNotEmpty(id)) {
						delCandidateUserIdSet.add(id);
					}
				}
			} else if ("task_prepare".equals(taskKey)) {
				// 计算新增
				for (String id : prepareUserIdsArr) {
					if (!orgnlCandidateUserIdSet.contains(id) && StringUtils.isNotEmpty(id)) {
						addCandidateUserIdSet.add(id);
					}
				}
				// 计算删除
				for (String id : orgnlCandidateUserIdSet) {
					if (!prepareUserIdsArr.contains(id) && StringUtils.isNotEmpty(id)) {
						delCandidateUserIdSet.add(id);
					}
				}
			} else if ("task_produce_approval".equals(taskKey)) {
				// 计算新增
				for (String id : produceApprovalUserIdsArr) {
					if (!orgnlCandidateUserIdSet.contains(id) && StringUtils.isNotEmpty(id)) {
						addCandidateUserIdSet.add(id);
					}
				}
				// 计算删除
				for (String id : orgnlCandidateUserIdSet) {
					if (!produceApprovalUserIdsArr.contains(id) && StringUtils.isNotEmpty(id)) {
						delCandidateUserIdSet.add(id);
					}
				}
			} else if ("task_produce".equals(taskKey)) {
				// 计算新增
				for (String id : produceUserIdsArr) {
					if (!orgnlCandidateUserIdSet.contains(id) && StringUtils.isNotEmpty(id)) {
						addCandidateUserIdSet.add(id);
					}
				}
				// 计算删除
				for (String id : orgnlCandidateUserIdSet) {
					if (!produceUserIdsArr.contains(id) && StringUtils.isNotEmpty(id)) {
						delCandidateUserIdSet.add(id);
					}
				}
			}
			// 删除和设置候选用户
			for (String uid : delCandidateUserIdSet) {
				taskService.deleteUserIdentityLink(task.getId(), uid, "candidate");
			}
			for (String uid : addCandidateUserIdSet) {
				taskService.addUserIdentityLink(task.getId(), uid, "candidate");
			}

		}
		retMsg.setCode(0);
		retMsg.setMessage("操作成功");
		return retMsg;
	}

}
