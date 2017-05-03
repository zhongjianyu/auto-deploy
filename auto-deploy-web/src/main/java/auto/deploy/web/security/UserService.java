package auto.deploy.web.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import auto.deploy.web.vo.AutRoleVO;
import auto.deploy.web.vo.AutUserVO;

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

	public AutUserVO getUserByUserName(String userName) {
		AutUserVO user = new AutUserVO();
		user.setUserName("zjy");
		user.setUserPwd("1234");
		user.setIsActive(1);
		user.setIsAccountExpired(0);
		user.setIsAccountLocked(0);
		user.setIsCredentialsExpired(0);
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
		return user;
	}

	public void ser1() {
		System.out.println("这是ser1...");
	}

	public void ser2() {
		System.out.println("这是ser2...");
	}
}
