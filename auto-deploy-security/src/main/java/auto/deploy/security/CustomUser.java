package auto.deploy.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import auto.deploy.object.aut.vo.AutMenuVO;

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
	private String nickName;
	private List<AutMenuVO> menuList;

	/**
	 * 
	 */
	private static final long serialVersionUID = -2117593108771034827L;

	public CustomUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public List<AutMenuVO> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<AutMenuVO> menuList) {
		this.menuList = menuList;
	}

}
