package auto.deploy.web.controller.aut;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;

import auto.deploy.dao.config.Where;
import auto.deploy.dao.entity.aut.AutRole;
import auto.deploy.object.PageBean;
import auto.deploy.object.RetMsg;
import auto.deploy.service.aut.AutRoleService;
import auto.deploy.web.controller.BaseController;

/**
 * 
 * @描述：角色表(控制器).
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-05-27
 */
@Controller
@RequestMapping("/aut/autRole")
public class AutRoleController extends BaseController {
	@Resource
	private AutRoleService autRoleService;

	/**
	 * 
	 * @描述：角色表(页面).
	 *
	 * @返回：String
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-05-27
	 */
	@RequestMapping("/autRolePage")
	public String autRolePage(HttpServletRequest request, HttpServletResponse response) {

		return "aut/autRolePage";
	}

	/**
	 * 
	 * @描述：角色表(分页列表).
	 *
	 * @返回：Page<AutRole>
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-05-27
	 */
	@RequestMapping("/list")
	@ResponseBody
	public Page<AutRole> list(HttpServletRequest request, HttpServletResponse response, PageBean pageBean, AutRole obj) {
		Page<AutRole> page = null;
		try {
			page = autRoleService.list(pageBean, obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}

	/**
	 * 
	 * @描述：角色表(新增).
	 *
	 * @返回：RetMsg
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-05-27
	 */
	@RequestMapping("/add")
	@ResponseBody
	public RetMsg add(HttpServletRequest request, HttpServletResponse response, AutRole obj) {
		RetMsg retMsg = new RetMsg();
		// 检查角色名称和角色代码是否已经存在
		Where<AutRole> where = new Where<AutRole>();
		where.eq("role_name", obj.getRoleName());
		where.or("role_code = {0}", obj.getRoleCode());
		if (autRoleService.selectCount(where) > 0) {
			retMsg.setCode(1);
			retMsg.setMessage("角色名称或代码已存在");
		} else {
			autRoleService.insert(obj);
			retMsg.setCode(0);
			retMsg.setMessage("操作成功");
		}
		return retMsg;
	}

	/**
	 * 
	 * @描述：角色表(根据ID删除对象).
	 *
	 * @返回：RetMsg
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-05-27
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public RetMsg delete(HttpServletRequest request, HttpServletResponse response, AutRole obj) {
		RetMsg retMsg = new RetMsg();

		autRoleService.deleteById(obj.getId());

		retMsg.setCode(0);
		retMsg.setMessage("操作成功");
		return retMsg;
	}

	/**
	 * 
	 * @描述：角色表(根据ID修改对象).
	 *
	 * @返回：RetMsg
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-05-27
	 */
	@RequestMapping("/update")
	@ResponseBody
	public RetMsg update(HttpServletRequest request, HttpServletResponse response, AutRole obj) {
		RetMsg retMsg = new RetMsg();

		// 查询是否有其他相同的角色名称和代码
		Where<AutRole> where = new Where<AutRole>();
		where.eq("role_name", obj.getRoleName());
		where.or("role_code = {0}", obj.getRoleCode());
		where.and().ne("id", obj.getId());
		if (autRoleService.selectCount(where) > 0) {
			retMsg.setCode(1);
			retMsg.setMessage("角色名称或代码已存在");
		} else {
			AutRole orgnlObj = autRoleService.selectById(obj.getId());
			orgnlObj.setRoleName(obj.getRoleName());
			orgnlObj.setRoleCode(obj.getRoleCode());
			orgnlObj.setIsActive(obj.getIsActive());
			autRoleService.updateById(orgnlObj);
			retMsg.setCode(0);
			retMsg.setMessage("操作成功");
		}
		return retMsg;
	}

	/**
	 * 
	 * @描述：角色表(根据ID获取对象).
	 *
	 * @返回：AutUser
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-05-27
	 */
	@RequestMapping("/getById")
	@ResponseBody
	public AutRole getById(HttpServletRequest request, HttpServletResponse response, AutRole obj) {
		return autRoleService.selectById(obj.getId());
	}

}
