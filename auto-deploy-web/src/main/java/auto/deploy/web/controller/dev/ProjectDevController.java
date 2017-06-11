package auto.deploy.web.controller.dev;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import auto.deploy.web.controller.BaseController;

/**
 * 
 * @描述：项目开发(控制器).
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-05-27
 */
@Controller
@RequestMapping("/dev/projectDev")
public class ProjectDevController extends BaseController {

	/**
	 * 
	 * @描述：项目开发(页面).
	 *
	 * @返回：String
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-05-27
	 */
	@RequestMapping("/projectDevPage")
	public String projectDevPage(HttpServletRequest request, HttpServletResponse response) {
		return "dev/projectDevPage";
	}

}
