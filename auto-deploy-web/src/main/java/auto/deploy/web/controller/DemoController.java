package auto.deploy.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;

import auto.deploy.dao.entity.aut.AutUser;
import auto.deploy.object.PageBean;
import auto.deploy.service.aut.AutUserService;

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

	@Resource
	private AutUserService autUserService;

	@RequestMapping("/demo")
	public String index(HttpServletRequest request) {
		return "demo/demo";
	}

	@RequestMapping("/list")
	@ResponseBody
	public Page<AutUser> list(HttpServletRequest request, HttpServletResponse response, PageBean pageBean) {
		Page<AutUser> page = null;
		try {
			page = autUserService.list(pageBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(page);
		System.out.println(page.getRecords());
		
		//测试修改数据
		AutUser user = autUserService.selectById(866162534596730880L);
		user.setUserName("d1");
		autUserService.updateById(user);
		return page;
	}
}
