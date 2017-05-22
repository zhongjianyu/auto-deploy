package auto.deploy.object.aut.vo;

import java.util.List;

import auto.deploy.dao.entity.aut.AutUser;

/**
 * 
 * @描述：用户值对象
 *
 * @作者：zhongjy
 *
 * @时间：2017年5月3日 下午5:08:24
 */
public final class AutUserVO extends AutUser {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 角色列表
	 */
	private List<AutRoleVO> roleList;
	/**
	 * 菜单列表
	 */
	private List<AutMenuVO> menuList;

	public List<AutRoleVO> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<AutRoleVO> roleList) {
		this.roleList = roleList;
	}

	public List<AutMenuVO> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<AutMenuVO> menuList) {
		this.menuList = menuList;
	}

}
