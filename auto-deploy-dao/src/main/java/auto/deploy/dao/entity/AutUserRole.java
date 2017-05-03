package auto.deploy.dao.entity;

import auto.deploy.dao.entity.Entity;

/**
 * 
 * @描述：用户-角色关联表(实体类)
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-05-03
 */
public class AutUserRole extends Entity<AutUserRole> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
	private Long userId;
    /**
     * 角色ID
     */
	private Long roleId;


	public Long getUserId() {
		return userId;
	}

	public AutUserRole setUserId(Long userId) {
		this.userId = userId;
		return this;
	}

	public Long getRoleId() {
		return roleId;
	}

	public AutUserRole setRoleId(Long roleId) {
		this.roleId = roleId;
		return this;
	}

}
