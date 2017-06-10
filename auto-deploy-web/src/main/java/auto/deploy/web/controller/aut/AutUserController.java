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
import auto.deploy.dao.entity.aut.AutRole;
import auto.deploy.dao.entity.aut.AutUser;
import auto.deploy.dao.entity.aut.AutUserRole;
import auto.deploy.object.PageBean;
import auto.deploy.object.RetMsg;
import auto.deploy.object.aut.dto.AutRoleDO;
import auto.deploy.security.CustomPasswordEncoder;
import auto.deploy.service.aut.AutRoleService;
import auto.deploy.service.aut.AutUserRoleService;
import auto.deploy.service.aut.AutUserService;
import auto.deploy.util.EncryptUtil;
import auto.deploy.web.annotation.FuncObj;

/**
 * 
 * @描述：用户表(控制器).
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-05-21
 */
@Controller
@RequestMapping("/aut/autUser")
public class AutUserController {
	@Resource
	private AutUserService autUserService;
	@Resource
	private CustomPasswordEncoder customPasswordEncoder;
	@Resource
	private AutUserRoleService autUserRoleService;
	@Resource
	private AutRoleService autRoleService;
	@Resource
	private ActivitiService activitiService;

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
	@FuncObj(desc = "[权限管理]-[用户管理]-[搜索]")
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
	@FuncObj(desc = "[权限管理]-[用户管理]-[新增]")
	public RetMsg add(HttpServletRequest request, HttpServletResponse response, AutUser obj) {
		RetMsg retMsg = new RetMsg();

		// 检查用户名是否存在
		Where<AutUser> where = new Where<AutUser>();
		where.eq("user_name", obj.getUserName());
		where.or("user_email = {0}", obj.getUserEmail());
		if (autUserService.selectCount(where) > 0) {
			retMsg.setCode(1);
			retMsg.setMessage("账号或邮箱已被注册");
		} else {
			obj.setUserPwd(customPasswordEncoder.encode(obj.getUserPwd()));
			autUserService.insert(obj);
			retMsg.setCode(0);
			retMsg.setMessage("操作成功");
			// 同步activiti用户
			activitiService.addUser(obj.getId());
		}

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
	@FuncObj(desc = "[权限管理]-[用户管理]-[删除]")
	public RetMsg delete(HttpServletRequest request, HttpServletResponse response, AutUser obj) {
		RetMsg retMsg = new RetMsg();

		autUserService.deleteById(obj.getId());

		retMsg.setCode(0);
		retMsg.setMessage("操作成功");

		// 同步activiti用户
		activitiService.delUser(obj.getId());
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
	@FuncObj(desc = "[权限管理]-[用户管理]-[修改]")
	public RetMsg update(HttpServletRequest request, HttpServletResponse response, AutUser obj) {
		RetMsg retMsg = new RetMsg();

		// 检查邮箱是否在其他用户中存在
		Where<AutUser> where = new Where<AutUser>();
		where.ne("id", obj.getId());
		where.eq("user_email", obj.getUserEmail());
		if (autUserService.selectCount(where) > 0) {
			retMsg.setCode(1);
			retMsg.setMessage("邮箱已被占用");
		} else {
			AutUser orgnlObj = autUserService.selectById(obj.getId());
			orgnlObj.setNickName(obj.getNickName());
			// 如果MD5值相等，则没有修改密码,否则修改了
			if (!obj.getUserPwd().equals(EncryptUtil.encryptMD5(orgnlObj.getUserPwd()))) {
				orgnlObj.setUserPwd(customPasswordEncoder.encode(obj.getUserPwd()));
			}
			orgnlObj.setIsActive(obj.getIsActive());
			orgnlObj.setIsAccountExpired(obj.getIsAccountExpired());
			orgnlObj.setIsAccountLocked(obj.getIsAccountLocked());
			orgnlObj.setIsCredentialsExpired(obj.getIsCredentialsExpired());

			autUserService.updateById(orgnlObj);
			retMsg.setCode(0);
			retMsg.setMessage("操作成功");
		}
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
	@FuncObj(desc = "[权限管理]-[用户管理]-[详情]")
	public AutUser getById(HttpServletRequest request, HttpServletResponse response, AutUser obj) {
		AutUser autUser = autUserService.selectById(obj.getId());
		// 密码MD5后传递到前台
		autUser.setUserPwd(EncryptUtil.encryptMD5(autUser.getUserPwd()));
		return autUser;
	}

	/**
	 * 
	 * @描述：角色表.
	 *
	 * 			@返回：Page<AutMenu>
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-05-27
	 */
	@RequestMapping("/roleList")
	@ResponseBody
	@FuncObj(desc = "[权限管理]-[用户管理]-[分配角色(查看)]")
	public Page<AutRoleDO> roleList(HttpServletRequest request, HttpServletResponse response, PageBean pageBean) {
		Page<AutRoleDO> page = new Page<AutRoleDO>();
		long userId = Long.parseLong(request.getParameter("userId"));
		try {
			Where<AutUserRole> w1 = new Where<AutUserRole>();
			w1.eq("user_id", userId).eq("is_active", 1);
			w1.setSqlSelect("role_id");
			List<AutUserRole> autUserRoleList = autUserRoleService.selectList(w1);
			Set<Long> roleIdSet = new HashSet<Long>();
			for (AutUserRole autUserRole : autUserRoleList) {
				roleIdSet.add(autUserRole.getRoleId());
			}

			Page<AutRole> rolePage = autRoleService.list(pageBean, new AutRole());
			List<AutRole> roleList = rolePage.getRecords();

			page.setCurrent(rolePage.getCurrent());
			page.setSize(rolePage.getSize());
			page.setTotal(rolePage.getTotal());
			List<AutRoleDO> voList = new ArrayList<AutRoleDO>();
			for (AutRole autRole : roleList) {
				AutRoleDO roleDO = new AutRoleDO();
				if (roleIdSet.contains(autRole.getId())) {
					roleDO.setIsCheck(1);
				} else {
					roleDO.setIsCheck(0);
				}
				roleDO.setAutRole(autRole);
				voList.add(roleDO);
			}
			page.setRecords(voList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}

	/**
	 * 
	 * @描述：用户分配角色
	 *
	 * @返回：RetMsg
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-05-27
	 */
	@RequestMapping("/setAuth")
	@ResponseBody
	@FuncObj(desc = "[权限管理]-[用户管理]-[分配角色(操作)]")
	public RetMsg setAuth(HttpServletRequest request, HttpServletResponse response) {
		RetMsg retMsg = new RetMsg();

		int isActive = Integer.parseInt(request.getParameter("isCheck"));
		long id = Long.parseLong(request.getParameter("id"));
		long userId = Long.parseLong(request.getParameter("userId"));
		// 菜单权限
		Where<AutUserRole> where = new Where<AutUserRole>();
		where.eq("user_id", userId);
		where.eq("role_id", id);
		AutUserRole autUserRole = autUserRoleService.selectOne(where);
		if (autUserRole == null) {
			AutUser autUser = autUserService.selectById(userId);
			AutRole autRole = autRoleService.selectById(id);
			autUserRole = new AutUserRole();
			autUserRole.setUserId(userId);
			autUserRole.setRoleId(autRole.getId());
			autUserRole.setRoleCode(autRole.getRoleCode());
			autUserRole.setUserName(autUser.getUserName());
			autUserRole.setRoleName(autRole.getRoleName());
			autUserRole.setIsActive(isActive);
			autUserRoleService.insert(autUserRole);
			// 同步activiti用户
			if (isActive == 1) {
				activitiService.addUserGroup(userId, id);
			} else {
				activitiService.delUserGroup(userId, id);
			}
		} else {
			autUserRole.setIsActive(isActive);
			autUserRoleService.updateById(autUserRole);
			// 同步activiti用户
			if (isActive == 1) {
				activitiService.addUserGroup(userId, id);
			} else {
				activitiService.delUserGroup(userId, id);
			}
		}
		retMsg.setCode(0);
		retMsg.setMessage("操作成功");
		return retMsg;
	}
}
