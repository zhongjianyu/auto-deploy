package auto.deploy.web.controller.aut;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;

import auto.deploy.dao.entity.aut.AutUserRole;
import auto.deploy.object.PageBean;
import auto.deploy.object.RetMsg;
import auto.deploy.service.aut.AutUserRoleService;
import auto.deploy.web.annotation.FuncObj;
import auto.deploy.web.controller.BaseController;

/**
 * 
 * @描述：用户-角色关联表(控制器).
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-05-27
 */
@Controller
@RequestMapping("/aut/autUserRole")
public class AutUserRoleController extends BaseController {
	@Resource
	private AutUserRoleService autUserRoleService;
	
	/**
	 * 
	 * @描述：用户-角色关联表(页面).
	 *
	 * @返回：String
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-05-27
	 */
	@RequestMapping("/autUserRolePage")
	public String autUserRolePage(HttpServletRequest request,HttpServletResponse response) {
		
		return "aut/autUserRolePage";
	}
	
	/**
	 * 
	 * @描述：用户-角色关联表(分页列表).
	 *
	 * @返回：Page<AutUserRole>
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-05-27
	 */
	@RequestMapping("/list")
	@ResponseBody
	@FuncObj(desc = "[权限管理]-[用户角色管理]-[搜索]")
	public Page<AutUserRole> list(HttpServletRequest request, HttpServletResponse response, PageBean pageBean, AutUserRole obj) {
		Page<AutUserRole> page = null;
		try {
			page = autUserRoleService.list(pageBean, obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}
	

	/**
	 * 
	 * @描述：用户-角色关联表(新增).
	 *
	 * @返回：RetMsg
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-05-27
	 */
	@RequestMapping("/add")
	@ResponseBody
	@FuncObj(desc = "[权限管理]-[用户角色管理]-[新增]")
	public RetMsg add(HttpServletRequest request, HttpServletResponse response, AutUserRole obj) {
		RetMsg retMsg = new RetMsg();

		// obj.set...

		autUserRoleService.insert(obj);
		retMsg.setCode(0);
		retMsg.setMessage("操作成功");
		return retMsg;
	}
	
	/**
	 * 
	 * @描述：用户-角色关联表(根据ID删除对象).
	 *
	 * @返回：RetMsg
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-05-27
	 */
	@RequestMapping("/delete")
	@ResponseBody
	@FuncObj(desc = "[权限管理]-[用户角色管理]-[删除]")
	public RetMsg delete(HttpServletRequest request, HttpServletResponse response, AutUserRole obj) {
		RetMsg retMsg = new RetMsg();

		autUserRoleService.deleteById(obj.getId());

		retMsg.setCode(0);
		retMsg.setMessage("操作成功");
		return retMsg;
	}
	
	/**
	 * 
	 * @描述：用户-角色关联表(根据ID修改对象).
	 *
	 * @返回：RetMsg
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-05-27
	 */
	@RequestMapping("/update")
	@ResponseBody
	@FuncObj(desc = "[权限管理]-[用户角色管理]-[修改]")
	public RetMsg update(HttpServletRequest request, HttpServletResponse response, AutUserRole obj) {
		RetMsg retMsg = new RetMsg();

		AutUserRole orgnlObj = autUserRoleService.selectById(obj.getId());
		// orgnlObj.set...

		autUserRoleService.updateById(orgnlObj);
		retMsg.setCode(0);
		retMsg.setMessage("操作成功");
		return retMsg;
	}
    
	/**
	 * 
	 * @描述：用户-角色关联表(根据ID获取对象).
	 *
	 * @返回：AutUser
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-05-27
	 */
	@RequestMapping("/getById")
	@ResponseBody
	@FuncObj(desc = "[权限管理]-[用户角色管理]-[详情]")
	public AutUserRole getById(HttpServletRequest request, HttpServletResponse response, AutUserRole obj) {
		return autUserRoleService.selectById(obj.getId());
	}

}
