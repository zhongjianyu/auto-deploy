package auto.deploy.web.controller.aut;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;

import auto.deploy.dao.entity.aut.AutWidgetRole;
import auto.deploy.object.PageBean;
import auto.deploy.object.RetMsg;
import auto.deploy.service.aut.AutWidgetRoleService;
import auto.deploy.web.controller.BaseController;

/**
 * 
 * @描述：控件-角色表(控制器).
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-05-27
 */
@Controller
@RequestMapping("/aut/autWidgetRole")
public class AutWidgetRoleController extends BaseController {
	@Resource
	private AutWidgetRoleService autWidgetRoleService;
	
	/**
	 * 
	 * @描述：控件-角色表(页面).
	 *
	 * @返回：String
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-05-27
	 */
	@RequestMapping("/autWidgetRolePage")
	public String autWidgetRolePage(HttpServletRequest request,HttpServletResponse response) {
		
		return "aut/autWidgetRolePage";
	}
	
	/**
	 * 
	 * @描述：控件-角色表(分页列表).
	 *
	 * @返回：Page<AutWidgetRole>
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-05-27
	 */
	@RequestMapping("/list")
	@ResponseBody
	public Page<AutWidgetRole> list(HttpServletRequest request, HttpServletResponse response, PageBean pageBean, AutWidgetRole obj) {
		Page<AutWidgetRole> page = null;
		try {
			page = autWidgetRoleService.list(pageBean, obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}
	

	/**
	 * 
	 * @描述：控件-角色表(新增).
	 *
	 * @返回：RetMsg
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-05-27
	 */
	@RequestMapping("/add")
	@ResponseBody
	public RetMsg add(HttpServletRequest request, HttpServletResponse response, AutWidgetRole obj) {
		RetMsg retMsg = new RetMsg();

		// obj.set...

		autWidgetRoleService.insert(obj);
		retMsg.setCode(0);
		retMsg.setMessage("操作成功");
		return retMsg;
	}
	
	/**
	 * 
	 * @描述：控件-角色表(根据ID删除对象).
	 *
	 * @返回：RetMsg
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-05-27
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public RetMsg delete(HttpServletRequest request, HttpServletResponse response, AutWidgetRole obj) {
		RetMsg retMsg = new RetMsg();

		autWidgetRoleService.deleteById(obj.getId());

		retMsg.setCode(0);
		retMsg.setMessage("操作成功");
		return retMsg;
	}
	
	/**
	 * 
	 * @描述：控件-角色表(根据ID修改对象).
	 *
	 * @返回：RetMsg
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-05-27
	 */
	@RequestMapping("/update")
	@ResponseBody
	public RetMsg update(HttpServletRequest request, HttpServletResponse response, AutWidgetRole obj) {
		RetMsg retMsg = new RetMsg();

		AutWidgetRole orgnlObj = autWidgetRoleService.selectById(obj.getId());
		// orgnlObj.set...

		autWidgetRoleService.updateById(orgnlObj);
		retMsg.setCode(0);
		retMsg.setMessage("操作成功");
		return retMsg;
	}
    
	/**
	 * 
	 * @描述：控件-角色表(根据ID获取对象).
	 *
	 * @返回：AutUser
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-05-27
	 */
	@RequestMapping("/getById")
	@ResponseBody
	public AutWidgetRole getById(HttpServletRequest request, HttpServletResponse response, AutWidgetRole obj) {
		return autWidgetRoleService.selectById(obj.getId());
	}

}
