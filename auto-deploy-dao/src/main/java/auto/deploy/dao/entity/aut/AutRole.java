package auto.deploy.dao.entity.aut;

/**
 * 
 * @描述：角色表(实体类)
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-05-14
 */
public class AutRole extends Entity<AutRole> {

    private static final long serialVersionUID = 1L;

    /**
     * 角色名称(唯一)
     */
	private String roleName;
    /**
     * 角色代码(唯一)
     */
	private String roleCode;
    /**
     * 是否激活
     */
	private Integer isActive;


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

	public Integer getIsActive() {
		return isActive;
	}

	public AutRole setIsActive(Integer isActive) {
		this.isActive = isActive;
		return this;
	}

}
