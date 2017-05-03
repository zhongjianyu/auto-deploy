package auto.deploy.dao.entity;

import auto.deploy.dao.entity.Entity;

/**
 * 
 * @描述：角色表(实体类)
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-05-03
 */
public class AutRole extends Entity<AutRole> {

    private static final long serialVersionUID = 1L;

    /**
     * 角色名称
     */
	private String roleName;
    /**
     * 角色代码
     */
	private String roleCode;


	public String getRoleName() {
		return roleName;
	}

	public AutRole setRoleName(String roleName) {
		this.roleName = roleName;
		return this;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public AutRole setRoleCode(String roleCode) {
		this.roleCode = roleCode;
		return this;
	}

}
