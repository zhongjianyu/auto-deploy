package auto.deploy.service.dev.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import auto.deploy.dao.config.Where;
import auto.deploy.dao.entity.aut.AutUser;
import auto.deploy.dao.entity.dev.DevProjectActor;
import auto.deploy.dao.mapper.dev.DevProjectActorMapper;
import auto.deploy.object.PageBean;
import auto.deploy.object.aut.vo.ProjectUserVO;
import auto.deploy.service.aut.AutUserService;
import auto.deploy.service.dev.DevProjectActorService;

/**
 * 
 * @描述：项目参与者表(服务实现类).
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-07-16
 */
@Service
public class DevProjectActorServiceImpl extends ServiceImpl<DevProjectActorMapper, DevProjectActor> implements DevProjectActorService {

	@Resource
	private AutUserService autUserService;

	@Override
	public Page<DevProjectActor> list(PageBean pageBean, DevProjectActor obj) throws Exception {
		Where<DevProjectActor> where = new Where<DevProjectActor>();
		where.orderBy("create_time", false);
		Page<DevProjectActor> page = selectPage(new Page<DevProjectActor>(pageBean.getPageNum(), pageBean.getPageSize()), where);
		return page;
	}

	@Override
	public ProjectUserVO getUserListByProjectId(Long projectId) {
		Where<DevProjectActor> where = new Where<DevProjectActor>();
		where.eq("project_id", projectId);
		where.eq("is_active", 1);
		where.setSqlSelect("user_id");
		List<DevProjectActor> list = selectList(where);

		ProjectUserVO vo = new ProjectUserVO();

		List<AutUser> devUserList = new ArrayList<AutUser>();
		List<AutUser> testUserList = new ArrayList<AutUser>();
		List<AutUser> checkUserList = new ArrayList<AutUser>();
		List<AutUser> prepareUserList = new ArrayList<AutUser>();
		List<AutUser> produceUserList = new ArrayList<AutUser>();
		for (DevProjectActor devProjectActor : list) {
			if (devProjectActor.getProjectStage().intValue() == 1) {
				devUserList.add(autUserService.selectById(devProjectActor.getUserId()));
			} else if (devProjectActor.getProjectStage().intValue() == 2) {
				testUserList.add(autUserService.selectById(devProjectActor.getUserId()));
			} else if (devProjectActor.getProjectStage().intValue() == 3) {
				checkUserList.add(autUserService.selectById(devProjectActor.getUserId()));
			} else if (devProjectActor.getProjectStage().intValue() == 4) {
				prepareUserList.add(autUserService.selectById(devProjectActor.getUserId()));
			} else if (devProjectActor.getProjectStage().intValue() == 5) {
				produceUserList.add(autUserService.selectById(devProjectActor.getUserId()));
			}
		}
		vo.setDevUserList(devUserList);
		vo.setTestUserList(testUserList);
		vo.setCheckUserList(checkUserList);
		vo.setPrepareUserList(prepareUserList);
		vo.setProduceUserList(produceUserList);
		return vo;
	}
}
