package auto.deploy.web.controller;

import javax.annotation.Resource;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import auto.deploy.dao.entity.User;
import auto.deploy.service.authority.UserService;

@Controller
public class IndexController {

	/**
	 * 读取配置文件
	 */
	@Resource
	private Environment environment;

	@Resource
	private UserService userService;

	@RequestMapping("index")
	public String index() {
		User user = new User();
		user.setName("赵敏");
		userService.myInsert(user);
		//System.out.println(environment.getProperty("spring.datasource.url"));
		User user2 = user.selectById("855802012663709696");
		System.out.println(user2);
		return "index";
	}
}
