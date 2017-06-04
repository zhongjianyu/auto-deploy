package auto.deploy.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import auto.deploy.dao.entity.aut.AutUser;
import auto.deploy.object.RetMsg;
import auto.deploy.object.aut.vo.AutMenuVO;
import auto.deploy.object.aut.vo.NavigationVO;
import auto.deploy.security.CustomPasswordEncoder;
import auto.deploy.service.aut.AutMenuRoleService;
import auto.deploy.service.aut.AutUserService;

/**
 * 
 * @描述：首页控制器
 *
 * @作者：zhongjy
 *
 * @时间：2017年4月24日 下午12:31:49
 */
@Controller
public class IndexController extends BaseController {

	@Resource
	private AutMenuRoleService autMenuRoleService;
	@Resource
	private AutUserService autUserService;
	@Resource
	private CustomPasswordEncoder customPasswordEncoder;

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
		request.setAttribute("CURRENT_NICK_NAME", getCustomDetail().getNickName());
		return "index/index";
	}
	/**
	 * 
	 * @描述：主页
	 *
	 * @返回：String
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年5月22日 下午11:05:27
	 */
	@RequestMapping("/index/main")
	public String main(HttpServletRequest request) {
		request.setAttribute("CURRENT_NICK_NAME", getCustomDetail().getNickName());
		return "index/main";
	}

	/**
	 * 
	 * @描述：个人信息
	 *
	 * @返回：String
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年5月22日 下午11:05:27
	 */
	@RequestMapping("/index/userInfoPage")
	public String userInfoPage(HttpServletRequest request) {
		AutUser autUser = autUserService.selectById(getCustomDetail().getUserId());
		request.setAttribute("nickName", autUser.getNickName());
		return "index/userInfoPage";
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
		List<AutMenuVO> autMenuVOList = getCustomDetail().getMenuList();
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

	@RequestMapping("/index/doChangePwd")
	@ResponseBody
	public RetMsg doChangePwd(HttpServletRequest request, HttpServletResponse response) {
		String oldPwd = request.getParameter("oldPwd");
		String newPwd = request.getParameter("newPwd");
		AutUser autUser = autUserService.selectById(getCustomDetail().getUserId());
		RetMsg retMsg = new RetMsg();
		if (customPasswordEncoder.matches(oldPwd, autUser.getUserPwd())) {
			autUser.setUserPwd(customPasswordEncoder.encode(newPwd));
			autUserService.updateById(autUser);
			retMsg.setCode(0);
			retMsg.setMessage("操作成功");
		} else {
			retMsg.setCode(1);
			retMsg.setMessage("原密码错误");
		}
		return retMsg;
	}

	@RequestMapping("/index/saveUserInfo")
	@ResponseBody
	public RetMsg saveUserInfo(HttpServletRequest request, HttpServletResponse response) {
		String nickName = request.getParameter("nickName");
		AutUser autUser = autUserService.selectById(getCustomDetail().getUserId());
		autUser.setNickName(nickName);
		autUserService.updateById(autUser);
		RetMsg retMsg = new RetMsg();
		retMsg.setCode(0);
		retMsg.setMessage("操作成功");
		return retMsg;
	}
}
