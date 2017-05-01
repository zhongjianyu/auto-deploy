package auto.deploy.web.controller.entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @描述：登录控制器
 *
 * @作者：zhongjy
 *
 * @时间：2017年4月24日 下午12:31:49
 */
@Controller
public class LoginController {

	@RequestMapping("login.html")
	public String index(HttpServletRequest request) {
		return "entry/login";
	}
}
