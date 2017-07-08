package auto.deploy.service.aut.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.StringUtils;

import auto.deploy.activiti.service.ActivitiService;
import auto.deploy.dao.config.Where;
import auto.deploy.dao.entity.aut.AutUser;
import auto.deploy.dao.mapper.aut.AutUserMapper;
import auto.deploy.gitlab.service.GitlabService;
import auto.deploy.object.PageBean;
import auto.deploy.object.RetMsg;
import auto.deploy.service.aut.AutUserService;

/**
 * 
 * @描述：用户表(服务实现类).
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-05-21
 */
@Service
public class AutUserServiceImpl extends ServiceImpl<AutUserMapper, AutUser> implements AutUserService {

	@Resource
	private AutUserService autUserService;
	@Resource
	private ActivitiService activitiService;
	@Resource
	private GitlabService gitlabService;

	@Override
	public Page<AutUser> list(PageBean pageBean, AutUser obj) throws Exception {
		Where<AutUser> where = new Where<AutUser>();
		if (StringUtils.isNotEmpty(obj.getUserName())) {
			where.like("user_name", obj.getUserName());
			where.or("nick_name LIKE {0}", "%" + obj.getUserName() + "%");
			where.or("user_email LIKE {0}", "%" + obj.getUserName() + "%");
		}
		where.orderBy("create_time", false);
		Page<AutUser> page = selectPage(new Page<AutUser>(pageBean.getPageNum(), pageBean.getPageSize()), where);
		return page;
	}

	@Override
	public void delete(AutUser obj) throws Exception {
		obj = autUserService.selectById(obj.getId());
		autUserService.deleteById(obj.getId());
		// 同步activiti用户
		activitiService.delUser(obj.getId());
		// 同步gitlab用户
		gitlabService.delUser(obj);
	}

	@Override
	public RetMsg add(AutUser obj) throws Exception {
		RetMsg retMsg = new RetMsg();
		// 检查用户名是否存在
		Where<AutUser> where = new Where<AutUser>();
		where.eq("user_name", obj.getUserName());
		where.or("user_email = {0}", obj.getUserEmail());
		if (autUserService.selectCount(where) > 0) {
			retMsg.setCode(1);
			retMsg.setMessage("账号或邮箱已被注册");
		} else {
			String charPassword = obj.getUserPwd();
			// obj.setUserPwd(customPasswordEncoder.encode(obj.getUserPwd()));
			autUserService.insert(obj);
			retMsg.setCode(0);
			retMsg.setMessage("操作成功");
			// 同步activiti用户
			activitiService.addUser(obj.getId());
			// 同步gitlab用户
			obj.setUserPwd(charPassword);
			gitlabService.addUser(obj);
		}
		return retMsg;
	}
}
