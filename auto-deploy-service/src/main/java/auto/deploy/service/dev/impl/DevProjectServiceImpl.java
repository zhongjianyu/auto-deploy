package auto.deploy.service.dev.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

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

	@Override
	@Transactional
	public RetMsg setActor(DevProject obj, String devUserIds, String testUserIds, String checkUserIds, String prepareUserIds, String produceUserIds,
			String testApprovalUserIds, String checkApprovalUserIds, String prepareApprovalUserIds, String produceApprovalUserIds) {
		RetMsg retMsg = new RetMsg();

		String[] devUserIdsArr = devUserIds.split(",");
		String[] testUserIdsArr = testUserIds.split(",");
		String[] checkUserIdsArr = checkUserIds.split(",");
		String[] prepareUserIdsArr = prepareUserIds.split(",");
		String[] produceUserIdsArr = produceUserIds.split(",");

		String[] testApprovalUserIdsArr = testApprovalUserIds.split(",");
		String[] checkApprovalUserIdsArr = checkApprovalUserIds.split(",");
		String[] prepareApprovalUserIdsArr = prepareApprovalUserIds.split(",");
		String[] produceApprovalUserIdsArr = produceApprovalUserIds.split(",");

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
				AutUser user = autUserService.selectById(Long.parseLong(id));
				DevProjectActor devProjectActor = new DevProjectActor();
				devProjectActor.setUserId(user.getId());
				devProjectActor.setUserName(user.getUserName());
				devProjectActor.setProjectId(project.getId());
				devProjectActor.setProjectName(project.getProjectName());
				devProjectActor.setIsActive(1);
				devProjectActor.setProjectStage(1);
				list.add(devProjectActor);
				// devProjectActorService.insert(devProjectActor);
			}
		}
		// 测试
		for (String id : testUserIdsArr) {
			if (StringUtils.isNotEmpty(id)) {
				AutUser user = autUserService.selectById(Long.parseLong(id));
				DevProjectActor devProjectActor = new DevProjectActor();
				devProjectActor.setUserId(user.getId());
				devProjectActor.setUserName(user.getUserName());
				devProjectActor.setProjectId(project.getId());
				devProjectActor.setProjectName(project.getProjectName());
				devProjectActor.setIsActive(1);
				devProjectActor.setProjectStage(2);
				list.add(devProjectActor);
				// devProjectActorService.insert(devProjectActor);
			}
		}
		// 验收
		for (String id : checkUserIdsArr) {
			if (StringUtils.isNotEmpty(id)) {
				AutUser user = autUserService.selectById(Long.parseLong(id));
				DevProjectActor devProjectActor = new DevProjectActor();
				devProjectActor.setUserId(user.getId());
				devProjectActor.setUserName(user.getUserName());
				devProjectActor.setProjectId(project.getId());
				devProjectActor.setProjectName(project.getProjectName());
				devProjectActor.setIsActive(1);
				devProjectActor.setProjectStage(3);
				list.add(devProjectActor);
				// devProjectActorService.insert(devProjectActor);
			}
		}
		// 预发
		for (String id : prepareUserIdsArr) {
			if (StringUtils.isNotEmpty(id)) {
				AutUser user = autUserService.selectById(Long.parseLong(id));
				DevProjectActor devProjectActor = new DevProjectActor();
				devProjectActor.setUserId(user.getId());
				devProjectActor.setUserName(user.getUserName());
				devProjectActor.setProjectId(project.getId());
				devProjectActor.setProjectName(project.getProjectName());
				devProjectActor.setIsActive(1);
				devProjectActor.setProjectStage(4);
				list.add(devProjectActor);
				// devProjectActorService.insert(devProjectActor);
			}
		}
		// 生产
		for (String id : produceUserIdsArr) {
			if (StringUtils.isNotEmpty(id)) {
				AutUser user = autUserService.selectById(Long.parseLong(id));
				DevProjectActor devProjectActor = new DevProjectActor();
				devProjectActor.setUserId(user.getId());
				devProjectActor.setUserName(user.getUserName());
				devProjectActor.setProjectId(project.getId());
				devProjectActor.setProjectName(project.getProjectName());
				devProjectActor.setIsActive(1);
				devProjectActor.setProjectStage(5);
				list.add(devProjectActor);
				// devProjectActorService.insert(devProjectActor);
			}
		}

		// 测试审批
		for (String id : testApprovalUserIdsArr) {
			if (StringUtils.isNotEmpty(id)) {
				AutUser user = autUserService.selectById(Long.parseLong(id));
				DevProjectActor devProjectActor = new DevProjectActor();
				devProjectActor.setUserId(user.getId());
				devProjectActor.setUserName(user.getUserName());
				devProjectActor.setProjectId(project.getId());
				devProjectActor.setProjectName(project.getProjectName());
				devProjectActor.setIsActive(1);
				devProjectActor.setProjectStage(6);
				list.add(devProjectActor);
				// devProjectActorService.insert(devProjectActor);
			}
		}
		// 验收审批
		for (String id : checkApprovalUserIdsArr) {
			if (StringUtils.isNotEmpty(id)) {
				AutUser user = autUserService.selectById(Long.parseLong(id));
				DevProjectActor devProjectActor = new DevProjectActor();
				devProjectActor.setUserId(user.getId());
				devProjectActor.setUserName(user.getUserName());
				devProjectActor.setProjectId(project.getId());
				devProjectActor.setProjectName(project.getProjectName());
				devProjectActor.setIsActive(1);
				devProjectActor.setProjectStage(7);
				list.add(devProjectActor);
				// devProjectActorService.insert(devProjectActor);
			}
		}
		// 预发审批
		for (String id : prepareApprovalUserIdsArr) {
			if (StringUtils.isNotEmpty(id)) {
				AutUser user = autUserService.selectById(Long.parseLong(id));
				DevProjectActor devProjectActor = new DevProjectActor();
				devProjectActor.setUserId(user.getId());
				devProjectActor.setUserName(user.getUserName());
				devProjectActor.setProjectId(project.getId());
				devProjectActor.setProjectName(project.getProjectName());
				devProjectActor.setIsActive(1);
				devProjectActor.setProjectStage(8);
				list.add(devProjectActor);
				// devProjectActorService.insert(devProjectActor);
			}
		}
		// 生产审批
		for (String id : produceApprovalUserIdsArr) {
			if (StringUtils.isNotEmpty(id)) {
				AutUser user = autUserService.selectById(Long.parseLong(id));
				DevProjectActor devProjectActor = new DevProjectActor();
				devProjectActor.setUserId(user.getId());
				devProjectActor.setUserName(user.getUserName());
				devProjectActor.setProjectId(project.getId());
				devProjectActor.setProjectName(project.getProjectName());
				devProjectActor.setIsActive(1);
				devProjectActor.setProjectStage(9);
				list.add(devProjectActor);
				// devProjectActorService.insert(devProjectActor);
			}
		}
		if (list.size() > 0) {
			devProjectActorService.insertBatch(list);
		}

		retMsg.setCode(0);
		retMsg.setMessage("操作成功");
		return retMsg;
	}

}
