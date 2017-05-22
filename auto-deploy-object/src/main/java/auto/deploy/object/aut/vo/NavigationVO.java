package auto.deploy.object.aut.vo;

import java.util.List;

/**
 * 
 * @描述：导航菜单对象
 *
 * @作者：zhongjy
 *
 * @时间：2017年5月22日 下午10:23:48
 */
public class NavigationVO {
	/**
	 * 菜单显示内容
	 */
	private String title;
	/**
	 * 菜单图标
	 */
	private String icon;
	/**
	 * 是否展开
	 */
	private boolean spread;
	/**
	 * 菜单链接
	 */
	private String href;
	/**
	 * 子菜单列表
	 */
	private List<NavigationVO> children;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public boolean isSpread() {
		return spread;
	}

	public void setSpread(boolean spread) {
		this.spread = spread;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public List<NavigationVO> getChildren() {
		return children;
	}

	public void setChildren(List<NavigationVO> children) {
		this.children = children;
	}

}
