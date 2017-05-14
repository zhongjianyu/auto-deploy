package auto.deploy.security;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;

import auto.deploy.dao.entity.AutUser;
import auto.deploy.object.vo.AutRoleVO;
import auto.deploy.object.vo.AutUserVO;
import auto.deploy.service.authority.AutUserService;

/**
 * 
 * @描述：用户服务
 *
 * @作者：zhongjy
 *
 * @时间：2017年5月3日 下午5:15:01
 */
@Service
public class UserService {

	@Resource
	private AutUserService autUserService;

	/**
	 * 
	 * @描述：根据登录名获取用户
	 *
	 * @返回：AutUserVO
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年5月7日 下午3:51:32
	 */
	public AutUserVO getUserByUserName(String userName) {
		AutUserVO user = null;
		AutUser autUser = autUserService.selectOne(new EntityWrapper<AutUser>().eq("user_name", userName).eq("is_delete", 0));
		if (autUser != null) {
			user = new AutUserVO();
			BeanUtils.copyProperties(autUser, user);
			List<AutRoleVO> roleList = new ArrayList<AutRoleVO>();
			AutRoleVO role1 = new AutRoleVO();
			role1.setRoleCode("sadmin");
			role1.setRoleName("超级管理员");
			AutRoleVO role2 = new AutRoleVO();
			role2.setRoleCode("admin");
			role2.setRoleName("管理员");
			roleList.add(role1);
			roleList.add(role2);
			user.setRoleList(roleList);
		}
		return user;
	}

	public void ser1() {
		System.out.println("这是ser1...");
	}

	public void ser2() {
		System.out.println("这是ser2...");
	}
}
