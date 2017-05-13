package auto.deploy.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

	@RequestMapping("/")
	public String index(HttpServletRequest request) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		request.setAttribute("CURRENT_USER_NAME", userDetails.getUsername());
		return "index/index";
	}
}
