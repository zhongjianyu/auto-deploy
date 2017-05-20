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
