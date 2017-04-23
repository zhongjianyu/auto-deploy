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
		// user.setCreateTime(new Date());
		// user.setName("赵敏");
		// userService.myInsert(user);
		user.setId(856003755209052160L);
		user.setName("赵敏2");
		user.updateById();
		// user.update(whereClause, args)
		// System.out.println(environment.getProperty("spring.datasource.url"));
		// System.out.println(user);
		return "index";
	}
}
