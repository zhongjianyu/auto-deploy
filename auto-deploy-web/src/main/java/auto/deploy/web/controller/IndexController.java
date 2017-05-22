package auto.deploy.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import auto.deploy.object.aut.vo.AutMenuVO;
import auto.deploy.object.aut.vo.NavigationVO;
import auto.deploy.security.CustomUser;
import auto.deploy.service.aut.AutMenuRoleService;

/**
 * 
 * @描述：首页控制器
 *
 * @作者：zhongjy
 *
 * @时间：2017年4月24日 下午12:31:49
 */
@Controller
public class IndexController {

	/**
	 * 读取配置文件
	 */
	@Resource
	private Environment environment;
	@Resource
	private AutMenuRoleService autMenuRoleService;

	/**
	 * 
	 * @描述：首页
	 *
	 * @返回：String
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年5月22日 下午11:05:27
	 */
	@RequestMapping("/")
	public String index(HttpServletRequest request) {
		CustomUser userDetails = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		request.setAttribute("CURRENT_NICK_NAME", userDetails.getNickName());
		return "index/index";
	}

	/**
	 * 
	 * @描述：返回左侧菜单数据
	 *
	 * @返回：List<NavigationVO>
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年5月22日 下午11:05:39
	 */
	@RequestMapping("/index/menuList")
	@ResponseBody
	public List<NavigationVO> menuList(HttpServletRequest request) {
		CustomUser userDetails = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<AutMenuVO> autMenuVOList = userDetails.getMenuList();
		List<NavigationVO> navigatioList = new ArrayList<NavigationVO>();
		for (AutMenuVO autMenuVO : autMenuVOList) {
			NavigationVO nav = new NavigationVO();
			nav.setTitle(autMenuVO.getMenuName());
			nav.setSpread(false);
			nav.setIcon(autMenuVO.getMenuIcon());
			nav.setHref(autMenuVO.getMenuHref());
			if (autMenuVO.getChildren() != null && autMenuVO.getChildren().size() > 0) {
				List<NavigationVO> children = new ArrayList<NavigationVO>();
				for (AutMenuVO autMenuChildVO : autMenuVO.getChildren()) {
					NavigationVO child = new NavigationVO();
					child.setTitle(autMenuChildVO.getMenuName());
					child.setSpread(false);
					child.setIcon(autMenuChildVO.getMenuIcon());
					child.setHref(autMenuChildVO.getMenuHref());
					children.add(child);
				}
				nav.setChildren(children);
			}
			navigatioList.add(nav);
		}
		return navigatioList;
	}
}
