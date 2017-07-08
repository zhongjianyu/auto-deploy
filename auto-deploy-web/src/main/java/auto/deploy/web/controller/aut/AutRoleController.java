package auto.deploy.web.controller.aut;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;

import auto.deploy.activiti.service.ActivitiService;
import auto.deploy.dao.config.Where;
import auto.deploy.dao.entity.aut.AutMenu;
import auto.deploy.dao.entity.aut.AutMenuRole;
import auto.deploy.dao.entity.aut.AutRole;
import auto.deploy.dao.entity.aut.AutWidget;
import auto.deploy.dao.entity.aut.AutWidgetRole;
import auto.deploy.object.PageBean;
import auto.deploy.object.RetMsg;
import auto.deploy.object.aut.dto.AutMenuDO;
import auto.deploy.object.aut.dto.AutWidgetDO;
import auto.deploy.service.aut.AutMenuRoleService;
import auto.deploy.service.aut.AutMenuService;
import auto.deploy.service.aut.AutRoleService;
import auto.deploy.service.aut.AutWidgetRoleService;
import auto.deploy.service.aut.AutWidgetService;
import auto.deploy.web.annotation.FuncObj;
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
	@Resource
	private AutMenuService autMenuService;
	@Resource
	private AutWidgetService autWidgetService;
	@Resource
	private AutMenuRoleService autMenuRoleService;
	@Resource
	private AutWidgetRoleService autWidgetRoleService;
	@Resource
	private ActivitiService activitiService;

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
	@FuncObj(desc = "[权限管理]-[角色管理]-[搜索]")
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
	@FuncObj(desc = "[权限管理]-[角色管理]-[新增]")
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
			// 同步activiti分组
			activitiService.addGroup(obj.getId());
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
	@FuncObj(desc = "[权限管理]-[角色管理]-[删除]")
	public RetMsg delete(HttpServletRequest request, HttpServletResponse response, AutRole obj) {
		RetMsg retMsg = new RetMsg();

		autRoleService.deleteById(obj.getId());

		retMsg.setCode(0);
		retMsg.setMessage("操作成功");
		// 同步activiti分组
		activitiService.delGroup(obj.getId());
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
	@FuncObj(desc = "[权限管理]-[角色管理]-[修改]")
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
	@FuncObj(desc = "[权限管理]-[角色管理]-[详情]")
	public AutRole getById(HttpServletRequest request, HttpServletResponse response, AutRole obj) {
		return autRoleService.selectById(obj.getId());
	}

	/**
	 * 
	 * @描述：菜单表,含菜单下的控件(分页列表).
	 *
	 * @返回：Page<AutMenu>
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-05-27
	 */
	@RequestMapping("/menuWidgetList")
	@ResponseBody
	@FuncObj(desc = "[权限管理]-[角色管理]-[授权(查看)]")
	public Page<AutMenuDO> menuWidgetList(HttpServletRequest request, HttpServletResponse response, PageBean pageBean, AutMenu obj) {
		Page<AutMenuDO> page = new Page<AutMenuDO>();
		long roleId = Long.parseLong(request.getParameter("roleId"));
		try {
			Where<AutMenuRole> w1 = new Where<AutMenuRole>();
			w1.eq("role_id", roleId).eq("is_active", 1);
			w1.setSqlSelect("menu_id");
			List<AutMenuRole> autMenuRoleList = autMenuRoleService.selectList(w1);
			Set<Long> menuIdSet = new HashSet<Long>();
			for (AutMenuRole autMenuRole : autMenuRoleList) {
				menuIdSet.add(autMenuRole.getMenuId());
			}

			Where<AutWidgetRole> w2 = new Where<AutWidgetRole>();
			w2.eq("role_id", roleId).eq("is_active", 1);
			w2.setSqlSelect("widget_id");
			List<AutWidgetRole> autWidgetRoleList = autWidgetRoleService.selectList(w2);
			Set<Long> widgetIdSet = new HashSet<Long>();
			for (AutWidgetRole autWidgetRole : autWidgetRoleList) {
				widgetIdSet.add(autWidgetRole.getWidgetId());
			}

			Page<AutMenu> menuPage = autMenuService.list(pageBean, obj);
			List<AutMenu> menuList = menuPage.getRecords();

			page.setCurrent(menuPage.getCurrent());
			page.setSize(menuPage.getSize());
			page.setTotal(menuPage.getTotal());
			List<AutMenuDO> voList = new ArrayList<AutMenuDO>();
			for (AutMenu autMenu : menuList) {
				AutMenuDO menuDO = new AutMenuDO();
				if (menuIdSet.contains(autMenu.getId())) {
					menuDO.setIsCheck(1);
				} else {
					menuDO.setIsCheck(0);
				}
				menuDO.setAutMenu(autMenu);
				// 根据菜单查询其控件
				Where<AutWidget> where = new Where<AutWidget>();
				where.eq("menu_id", autMenu.getId());
				List<AutWidget> widgetList = autWidgetService.selectList(where);
				List<AutWidgetDO> widgetDOList = new ArrayList<AutWidgetDO>();
				for (AutWidget autWidget : widgetList) {
					AutWidgetDO autWidgetDO = new AutWidgetDO();
					autWidgetDO.setAutWidget(autWidget);
					if (widgetIdSet.contains(autWidget.getId())) {
						autWidgetDO.setIsCheck(1);
					} else {
						autWidgetDO.setIsCheck(0);
					}
					widgetDOList.add(autWidgetDO);
				}
				menuDO.setWidgetList(widgetDOList);
				voList.add(menuDO);
			}
			page.setRecords(voList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}

	/**
	 * 
	 * @描述：菜单及控件授权
	 *
	 * @返回：RetMsg
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-05-27
	 */
	@RequestMapping("/setAuth")
	@ResponseBody
	@FuncObj(desc = "[权限管理]-[角色管理]-[授权(操作)]")
	public RetMsg setAuth(HttpServletRequest request, HttpServletResponse response) {
		RetMsg retMsg = new RetMsg();

		int isActive = Integer.parseInt(request.getParameter("isCheck"));
		long id = Long.parseLong(request.getParameter("id"));
		String type = request.getParameter("type");
		long roleId = Long.parseLong(request.getParameter("roleId"));
		if ("1".equals(type)) {
			// 菜单权限
			Where<AutMenuRole> where = new Where<AutMenuRole>();
			where.eq("menu_id", id);
			where.eq("role_id", roleId);
			AutMenuRole autMenuRole = autMenuRoleService.selectOne(where);
			if (autMenuRole == null) {
				AutMenu autMenu = autMenuService.selectById(id);
				AutRole autRole = autRoleService.selectById(roleId);
				autMenuRole = new AutMenuRole();
				autMenuRole.setMenuCode(autMenu.getMenuCode());
				autMenuRole.setMenuId(autMenu.getId());
				autMenuRole.setIsActive(isActive);
				autMenuRole.setRoleName(autRole.getRoleName());
				autMenuRole.setRoleCode(autRole.getRoleCode());
				autMenuRole.setRoleId(autRole.getId());
				autMenuRoleService.insert(autMenuRole);
			} else {
				autMenuRole.setIsActive(isActive);
				autMenuRoleService.updateById(autMenuRole);
			}
		} else {
			// 控件权限
			Where<AutWidgetRole> where = new Where<AutWidgetRole>();
			where.eq("widget_id", id);
			where.eq("role_id", roleId);
			AutWidgetRole autWidgetRole = autWidgetRoleService.selectOne(where);
			if (autWidgetRole == null) {
				AutWidget autWidget = autWidgetService.selectById(id);
				AutRole autRole = autRoleService.selectById(roleId);
				autWidgetRole = new AutWidgetRole();
				autWidgetRole.setWidgetCode(autWidget.getWidgetCode());
				autWidgetRole.setWidgetId(autWidget.getId());
				autWidgetRole.setRoleId(autRole.getId());
				autWidgetRole.setRoleCode(autRole.getRoleCode());
				autWidgetRole.setRoleName(autRole.getRoleName());
				autWidgetRole.setIsActive(isActive);
				autWidgetRoleService.insert(autWidgetRole);
			} else {
				autWidgetRole.setIsActive(isActive);
				autWidgetRoleService.updateById(autWidgetRole);
			}
		}
		retMsg.setCode(0);
		retMsg.setMessage("操作成功");
		return retMsg;
	}

}
