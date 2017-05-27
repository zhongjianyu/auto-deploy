package auto.deploy.dao.entity.aut;

import auto.deploy.dao.entity.Entity;

/**
 * 
 * @描述：控件表(实体类).
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-05-27
 */
public class AutWidget extends Entity<AutWidget> {

    private static final long serialVersionUID = 1L;

    /**
     * 控件编码
     */
	private String widgetCode;
    /**
     * 控件名称
     */
	private String widgetName;
    /**
     * 控件归属菜单ID
     */
	private Long menuId;
    /**
     * 控件归属菜单编码
     */
	private String menuCode;
    /**
     * 是否激活
     */
	private Integer isActive;


	public String getWidgetCode() {
		return widgetCode;
	}

	public AutWidget setWidgetCode(String widgetCode) {
		this.widgetCode = widgetCode;
		return this;
	}

	public String getWidgetName() {
		return widgetName;
	}

	public AutWidget setWidgetName(String widgetName) {
		this.widgetName = widgetName;
		return this;
	}

	public Long getMenuId() {
		return menuId;
	}

	public AutWidget setMenuId(Long menuId) {
		this.menuId = menuId;
		return this;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public AutWidget setMenuCode(String menuCode) {
		this.menuCode = menuCode;
		return this;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public AutWidget setIsActive(Integer isActive) {
		this.isActive = isActive;
		return this;
	}

}
