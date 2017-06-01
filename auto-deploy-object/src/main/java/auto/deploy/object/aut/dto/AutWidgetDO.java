package auto.deploy.object.aut.dto;

import auto.deploy.dao.entity.aut.AutMenu;
import auto.deploy.dao.entity.aut.AutWidget;

/**
 * 
 * @描述：控件数据对象
 *
 * @作者：zhongjy
 *
 * @时间：2017年5月3日 下午5:09:27
 */
public class AutWidgetDO {

	/**
	 * 控件对象
	 */
	private AutWidget autWidget;
	/**
	 * 控件归属菜单
	 */
	private AutMenu menu;
	/**
	 * 控件归属菜单的父菜单
	 */
	private AutMenu parentMenu;
	/**
	 * 完整归属菜单名：一级+二级菜单名称
	 */
	private String fullMenuName;

	public AutWidget getAutWidget() {
		return autWidget;
	}

	public void setAutWidget(AutWidget autWidget) {
		this.autWidget = autWidget;
	}

	public AutMenu getMenu() {
		return menu;
	}

	public void setMenu(AutMenu menu) {
		this.menu = menu;
	}

	public AutMenu getParentMenu() {
		return parentMenu;
	}

	public void setParentMenu(AutMenu parentMenu) {
		this.parentMenu = parentMenu;
	}

	public String getFullMenuName() {
		return fullMenuName;
	}

	public void setFullMenuName(String fullMenuName) {
		this.fullMenuName = fullMenuName;
	}

}
