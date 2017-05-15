package auto.deploy.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @描述：demo控制器
 *
 * @作者：zhongjy
 *
 * @时间：2017年4月24日 下午12:31:49
 */
@Controller
@RequestMapping("/demo")
public class DemoController {

	@RequestMapping("/demo")
	public String index(HttpServletRequest request) {
		return "demo/demo";
	}
}
