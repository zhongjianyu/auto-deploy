

import java.util.List;

import auto.deploy.dao.entity.AutUser;

/**
 * 
 * @描述：用户�?�对�?
 *
 * @作�?�：zhongjy
 *
 * @时间�?2017�?5�?3�? 下午5:08:24
 */
public final class AutUserVO extends AutUser {

	/**
	 * @描述：TODO
	 */
	private static final long serialVersionUID = 1L;

	private List<AutRoleVO> roleList;

	public List<AutRoleVO> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<AutRoleVO> roleList) {
		this.roleList = roleList;
	}

}
