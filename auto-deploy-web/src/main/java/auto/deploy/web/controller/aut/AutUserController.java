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
 * @描述：用户表(控制器).
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-05-21
 */
@Controller
@RequestMapping("/autUser")
public class AutUserController {
	@Resource
	private AutUserService autUserService;

	/**
	 * 
	 * @描述：用户表(页面).
	 *
	 * @返回：String
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年5月27日 下午4:29:41
	 */
	@RequestMapping("/autUserPage")
	public String index(HttpServletRequest request) {
		return "aut/autUserPage";
	}

	/**
	 * 
	 * @描述：用户表(分页列表).
	 *
	 * @返回：Page<AutUser>
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年5月27日 下午4:30:09
	 */
	@RequestMapping("/list")
	@ResponseBody
	public Page<AutUser> list(HttpServletRequest request, HttpServletResponse response, PageBean pageBean, AutUser obj) {
		Page<AutUser> page = null;
		try {
			page = autUserService.list(pageBean, obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}

	/**
	 * 
	 * @描述：用户表(新增).
	 *
	 * @返回：RetMsg
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年5月27日 下午4:30:49
	 */
	@RequestMapping("/add")
	@ResponseBody
	public RetMsg add(HttpServletRequest request, HttpServletResponse response, AutUser obj) {
		RetMsg retMsg = new RetMsg();

		// obj.set...

		autUserService.insert(obj);
		retMsg.setCode(0);
		retMsg.setMessage("操作成功");
		return retMsg;
	}

	/**
	 * 
	 * @描述：用户表(根据ID删除对象).
	 *
	 * @返回：RetMsg
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年5月27日 下午4:44:27
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public RetMsg delete(HttpServletRequest request, HttpServletResponse response, AutUser obj) {
		RetMsg retMsg = new RetMsg();

		autUserService.deleteById(obj.getId());

		retMsg.setCode(0);
		retMsg.setMessage("操作成功");
		return retMsg;
	}

	/**
	 * 
	 * @描述：用户表(根据ID修改对象).
	 *
	 * @返回：RetMsg
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年5月27日 下午4:42:36
	 */
	@RequestMapping("/update")
	@ResponseBody
	public RetMsg update(HttpServletRequest request, HttpServletResponse response, AutUser obj) {
		RetMsg retMsg = new RetMsg();

		AutUser orgnlObj = autUserService.selectById(obj.getId());
		// orgnlObj.set...

		autUserService.updateById(orgnlObj);
		retMsg.setCode(0);
		retMsg.setMessage("操作成功");
		return retMsg;
	}

	/**
	 * 
	 * @描述：用户表(根据ID获取对象).
	 *
	 * @返回：AutUser
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年5月27日 下午4:34:54
	 */
	@RequestMapping("/getById")
	@ResponseBody
	public AutUser getById(HttpServletRequest request, HttpServletResponse response, AutUser obj) {
		return autUserService.selectById(obj.getId());
	}
}
