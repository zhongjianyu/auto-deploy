package auto.deploy.activiti.service.impl;

import javax.annotation.Resource;

import org.activiti.engine.DynamicBpmnService;
import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.springframework.stereotype.Service;

import auto.deploy.activiti.service.ActivitiService;

/**
 * 
 * @描述：流程服务类(实现类).
 *
 * @作者：zhongjy
 *
 * @时间：2017年6月10日 下午4:32:44
 */
@Service
public class ActivitiServiceImpl implements ActivitiService {

	@Resource
	private DynamicBpmnService dynamicBpmnService;
	@Resource
	private FormService formService;
	@Resource
	private HistoryService historyService;
	@Resource
	private IdentityService identityService;
	@Resource
	private ManagementService managementService;
	@Resource
	private RepositoryService repositoryService;
	@Resource
	private RuntimeService runtimeService;
	@Resource
	private TaskService taskService;

	@Override
	public void addUser(Long userId) {
		User user = identityService.newUser(userId.toString());
		identityService.saveUser(user);
	}

	@Override
	public void delUser(Long userId) {
		identityService.deleteUser(userId.toString());
	}

	@Override
	public void addGroup(Long groupId) {
		Group group = identityService.newGroup(groupId.toString());
		identityService.saveGroup(group);
	}

	@Override
	public void delGroup(Long groupId) {
		identityService.deleteGroup(groupId.toString());
	}

	@Override
	public void addUserGroup(Long userId, Long groupId) {
		identityService.createMembership(userId.toString(), groupId.toString());
	}

	@Override
	public void delUserGroup(Long userId, Long groupId) {
		identityService.deleteMembership(userId.toString(), groupId.toString());
	}

}
