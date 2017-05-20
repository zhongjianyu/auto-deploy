package auto.deploy.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import auto.deploy.object.aut.vo.AutRoleVO;
import auto.deploy.object.aut.vo.AutUserVO;

/**
 * 
 * @描述：自定义security的用户服务
 *
 * @作者：zhongjy
 *
 * @时间：2017年5月3日 下午5:15:49
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserService userService;

	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		if (StringUtils.isEmpty(userName)) {
			throw new UsernameNotFoundException("账号不能为空");
		}
		AutUserVO user = userService.getUserByUserName(userName);
		if(user == null) {
			throw new UsernameNotFoundException("用户不存在");
		}
		org.springframework.security.core.userdetails.User userDetail = new org.springframework.security.core.userdetails.User(
				user.getUserName(), user.getUserPwd(), user.getIsActive() == 1, user.getIsAccountExpired() == 0,
				user.getIsCredentialsExpired() == 0, user.getIsAccountLocked() == 0, getGrantedAuthorities(user));
		return userDetail;
	}

	private List<GrantedAuthority> getGrantedAuthorities(AutUserVO user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (AutRoleVO role : user.getRoleList()) {
			System.out.println("Role : " + role);
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleCode()));
		}
		System.out.print("authorities :" + authorities);
		return authorities;
	}
}
