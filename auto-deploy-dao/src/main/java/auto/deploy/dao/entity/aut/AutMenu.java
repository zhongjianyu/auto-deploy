package auto.deploy.dao.entity.aut;

import auto.deploy.dao.entity.Entity;

/**
 * 
 * @描述：菜单表(实体类)
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-05-21
 */
public class AutMenu extends Entity<AutMenu> {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单编码(唯一)
     */
	private String menuCode;
    /**
     * 菜单等级
     */
	private Integer menuLevel;
    /**
     * 菜单名称
     */
	private String menuName;
    /**
     * 父级菜单编码
     */
	private String parentCode;
    /**
     * 图标样式
     */
	private String menuIcon;
    /**
     * 菜单链接
     */
	private String menuHref;
    /**
     * 同级菜单排序
     */
	private Integer menuRank;
    /**
     * 是否激活
     */
	private Integer isActive;


	public String getMenuCode() {
		return menuCode;
	}

	public AutMenu setMenuCode(String menuCode) {
		this.menuCode = menuCode;
		return this;
	}

	public Integer getMenuLevel() {
		return menuLevel;
	}

	public AutMenu setMenuLevel(Integer menuLevel) {
		this.menuLevel = menuLevel;
		return this;
	}

	public String getMenuName() {
		return menuName;
	}

	public AutMenu setMenuName(String menuName) {
		this.menuName = menuName;
		return this;
	}

	public String getParentCode() {
		return parentCode;
	}

	public AutMenu setParentCode(String parentCode) {
		this.parentCode = parentCode;
		return this;
	}

	public String getMenuIcon() {
		return menuIcon;
	}

	public AutMenu setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
		return this;
	}

	public String getMenuHref() {
		return menuHref;
	}

	public AutMenu setMenuHref(String menuHref) {
		this.menuHref = menuHref;
		return this;
	}

	public Integer getMenuRank() {
		return menuRank;
	}

	public AutMenu setMenuRank(Integer menuRank) {
		this.menuRank = menuRank;
		return this;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public AutMenu setIsActive(Integer isActive) {
		this.isActive = isActive;
		return this;
	}

}
