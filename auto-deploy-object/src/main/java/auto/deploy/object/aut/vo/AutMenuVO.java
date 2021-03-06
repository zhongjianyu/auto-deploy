package auto.deploy.object.aut.vo;

import java.util.List;

import auto.deploy.dao.entity.aut.AutMenu;
import auto.deploy.dao.entity.aut.AutWidget;

/**
 * 
 * @描述：菜单对象(tree).
 *
 * @作者：zhongjy
 *
 * @时间：2017年5月3日 下午5:09:27
 */
public class AutMenuVO extends AutMenu {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 二级子菜单
	 */
	private List<AutMenuVO> children;
	/**
	 * 菜单控件
	 */
	private List<AutWidget> widgetList;

	public List<AutMenuVO> getChildren() {
		return children;
	}

	public void setChildren(List<AutMenuVO> children) {
		this.children = children;
	}

	public List<AutWidget> getWidgetList() {
		return widgetList;
	}

	public void setWidgetList(List<AutWidget> widgetList) {
		this.widgetList = widgetList;
	}

}
