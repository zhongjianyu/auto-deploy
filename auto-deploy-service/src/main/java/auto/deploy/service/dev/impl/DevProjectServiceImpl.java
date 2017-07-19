package auto.deploy.service.dev.impl;

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
	public RetMsg setActor(DevProject obj, String devUserIds, String testUserIds, String checkUserIds, String prepareUserIds, String produceUserIds) {
		RetMsg retMsg = new RetMsg();

		String[] devUserIdsArr = devUserIds.split(",");
		String[] testUserIdsArr = testUserIds.split(",");
		String[] checkUserIdsArr = checkUserIds.split(",");
		String[] prepareUserIdsArr = prepareUserIds.split(",");
		String[] produceUserIdsArr = produceUserIds.split(",");
		DevProject project = selectById(obj.getId());
		// 先删除在增加
		Where<DevProjectActor> where0 = new Where<DevProjectActor>();
		where0.eq("project_id", obj.getId());
		devProjectActorService.delete(where0);
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
				devProjectActorService.insert(devProjectActor);
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
				devProjectActorService.insert(devProjectActor);
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
				devProjectActorService.insert(devProjectActor);
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
				devProjectActorService.insert(devProjectActor);
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
				devProjectActorService.insert(devProjectActor);
			}
		}

		retMsg.setCode(0);
		retMsg.setMessage("操作成功");
		return retMsg;
	}

}
