package auto.deploy.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * 
 * @描述：自定义security用户对象
 *
 * @作者：zhongjy
 *
 * @时间：2017年5月21日 下午12:06:32
 */
public class CustomUser extends User {

	private Long userId;

	/**
	 * 
	 */
	private static final long serialVersionUID = -2117593108771034827L;

	public CustomUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		setUserId(null);
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
