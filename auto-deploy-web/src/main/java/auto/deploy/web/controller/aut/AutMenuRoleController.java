package auto.deploy.web.controller.aut;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;

import auto.deploy.dao.entity.aut.AutMenuRole;
import auto.deploy.object.PageBean;
import auto.deploy.object.RetMsg;
import auto.deploy.service.aut.AutMenuRoleService;
import auto.deploy.web.annotation.FuncObj;
import auto.deploy.web.controller.BaseController;

/**
 * 
 * @描述：菜单-角色表(控制器).
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-05-27
 */
@Controller
@RequestMapping("/aut/autMenuRole")
public class AutMenuRoleController extends BaseController {
	@Resource
	private AutMenuRoleService autMenuRoleService;

	/**
	 * 
	 * @描述：菜单-角色表(页面).
	 *
	 * @返回：String
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-05-27
	 */
	@RequestMapping("/autMenuRolePage")
	public String autMenuRolePage(HttpServletRequest request, HttpServletResponse response) {

		return "aut/autMenuRolePage";
	}

	/**
	 * 
	 * @描述：菜单-角色表(分页列表).
	 *
	 * @返回：Page<AutMenuRole>
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-05-27
	 */
	@RequestMapping("/list")
	@ResponseBody
	@FuncObj(desc = "[权限管理]-[菜单角色管理]-[搜索]")
	public Page<AutMenuRole> list(HttpServletRequest request, HttpServletResponse response, PageBean pageBean, AutMenuRole obj) {
		Page<AutMenuRole> page = null;
		try {
			page = autMenuRoleService.list(pageBean, obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}

	/**
	 * 
	 * @描述：菜单-角色表(新增).
	 *
	 * @返回：RetMsg
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-05-27
	 */
	@RequestMapping("/add")
	@ResponseBody
	@FuncObj(desc = "[权限管理]-[菜单角色管理]-[新增]")
	public RetMsg add(HttpServletRequest request, HttpServletResponse response, AutMenuRole obj) {
		RetMsg retMsg = new RetMsg();

		// obj.set...

		autMenuRoleService.insert(obj);
		retMsg.setCode(0);
		retMsg.setMessage("操作成功");
		return retMsg;
	}

	/**
	 * 
	 * @描述：菜单-角色表(根据ID删除对象).
	 *
	 * @返回：RetMsg
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-05-27
	 */
	@RequestMapping("/delete")
	@ResponseBody
	@FuncObj(desc = "[权限管理]-[菜单角色管理]-[删除]")
	public RetMsg delete(HttpServletRequest request, HttpServletResponse response, AutMenuRole obj) {
		RetMsg retMsg = new RetMsg();

		autMenuRoleService.deleteById(obj.getId());

		retMsg.setCode(0);
		retMsg.setMessage("操作成功");
		return retMsg;
	}

	/**
	 * 
	 * @描述：菜单-角色表(根据ID修改对象).
	 *
	 * @返回：RetMsg
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-05-27
	 */
	@RequestMapping("/update")
	@ResponseBody
	@FuncObj(desc = "[权限管理]-[菜单角色管理]-[修改]")
	public RetMsg update(HttpServletRequest request, HttpServletResponse response, AutMenuRole obj) {
		RetMsg retMsg = new RetMsg();

		AutMenuRole orgnlObj = autMenuRoleService.selectById(obj.getId());
		// orgnlObj.set...

		autMenuRoleService.updateById(orgnlObj);
		retMsg.setCode(0);
		retMsg.setMessage("操作成功");
		return retMsg;
	}

	/**
	 * 
	 * @描述：菜单-角色表(根据ID获取对象).
	 *
	 * @返回：AutUser
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-05-27
	 */
	@RequestMapping("/getById")
	@ResponseBody
	@FuncObj(desc = "[权限管理]-[菜单角色管理]-[详情]")
	public AutMenuRole getById(HttpServletRequest request, HttpServletResponse response, AutMenuRole obj) {
		return autMenuRoleService.selectById(obj.getId());
	}

}
