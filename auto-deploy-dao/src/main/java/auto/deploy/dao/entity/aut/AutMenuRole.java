package auto.deploy.dao.entity.aut;

/**
 * 
 * @描述：菜单-角色表(实体类)
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-05-14
 */
public class AutMenuRole extends Entity<AutMenuRole> {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单编码
     */
	private String menuCode;
    /**
     * 菜单ID
     */
	private Long menuId;
    /**
     * 是否激活
     */
	private Integer isActive;
    /**
     * 角色名称(唯一)
     */
	private String roleName;
    /**
     * 角色代码(唯一)
     */
	private String roleCode;


	public String getMenuCode() {
		return menuCode;
	}

	public AutMenuRole setMenuCode(String menuCode) {
		this.menuCode = menuCode;
		return this;
	}

	public Long getMenuId() {
		return menuId;
	}

	public AutMenuRole setMenuId(Long menuId) {
		this.menuId = menuId;
		return this;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public AutMenuRole setIsActive(Integer isActive) {
		this.isActive = isActive;
		return this;
	}

	public String getRoleName() {
		return roleName;
	}

	public AutMenuRole setRoleName(String roleName) {
		this.roleName = roleName;
		return this;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public AutMenuRole setRoleCode(String roleCode) {
		this.roleCode = roleCode;
		return this;
	}

}
