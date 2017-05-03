package auto.deploy.web.controller;

import javax.annotation.Resource;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import auto.deploy.service.authority.AutUserService;
import auto.deploy.web.vo.AutUserVO;

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
	private AutUserService userService;

	@RequestMapping("index")
	public String index() {
		AutUserVO user = new AutUserVO();
		// user.setCreateTime(new Date());
		// user.setName("赵敏");
		// userService.myInsert(user);
		user.setId(856003755209052160L);
		user.setUserName("赵敏2");
		user.updateById();
		// user.update(whereClause, args)
		// System.out.println(environment.getProperty("spring.datasource.url"));
		// System.out.println(user);
		return "index";
	}
}
