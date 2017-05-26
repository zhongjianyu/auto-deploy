package auto.deploy.web.controller.aut;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;

import auto.deploy.dao.entity.aut.AutUser;
import auto.deploy.object.PageBean;
import auto.deploy.object.RetMsg;
import auto.deploy.service.aut.AutUserService;

/**
 * 
 * @描述：用户表(控制器)
 * 
 * 				@作者：zhongjy
 * 
 * @时间: 2017-05-21
 */
@Controller
@RequestMapping("/autUser")
public class AutUserController {
	@Resource
	private AutUserService autUserService;

	@RequestMapping("/autUserPage")
	public String index(HttpServletRequest request) {
		return "aut/autUserPage";
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
		return page;
	}
	
	@RequestMapping("/addUser")
	@ResponseBody
	public RetMsg addUser(HttpServletRequest request, HttpServletResponse response, AutUser autUser) {
		RetMsg retMsg = new RetMsg();
		autUserService.insert(autUser);
		return retMsg;
	}
}
