package auto.deploy.object.aut.dto;

import auto.deploy.dao.entity.aut.AutRole;

/**
 * 
 * @描述：角色对象
 *
 * @作者：zhongjy
 *
 * @时间：2017年5月3日 下午5:09:27
 */
public class AutRoleDO {

	private AutRole autRole;
	private Integer isCheck;

	public AutRole getAutRole() {
		return autRole;
	}

	public void setAutRole(AutRole autRole) {
		this.autRole = autRole;
	}

	public Integer getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(Integer isCheck) {
		this.isCheck = isCheck;
	}

}
