package auto.deploy.security;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;

import auto.deploy.dao.config.Where;
import auto.deploy.dao.entity.aut.AutMenu;
import auto.deploy.dao.entity.aut.AutMenuRole;
import auto.deploy.dao.entity.aut.AutUser;
import auto.deploy.dao.entity.aut.AutUserRole;
import auto.deploy.object.aut.vo.AutMenuVO;
import auto.deploy.object.aut.vo.AutRoleVO;
import auto.deploy.object.aut.vo.AutUserVO;
import auto.deploy.service.aut.AutMenuRoleService;
import auto.deploy.service.aut.AutMenuService;
import auto.deploy.service.aut.AutUserRoleService;
import auto.deploy.service.aut.AutUserService;

/**
 * 
 * @描述：用户服务
 *
 * @作者：zhongjy
 *
 * @时间：2017年5月3日 下午5:15:01
 */
@Service
public class UserService {

	@Resource
	private AutUserService autUserService;
	@Resource
	private AutUserRoleService autUserRoleService;
	@Resource
	private AutMenuRoleService autMenuRoleService;
	@Resource
	private AutMenuService autMenuService;

	/**
	 * 
	 * @描述：根据登录名获取用户
	 *
	 * @返回：AutUserVO
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年5月7日 下午3:51:32
	 */
	public AutUserVO getUserByUserName(String userName) {
		AutUserVO user = null;
		AutUser autUser = autUserService.selectOne(new EntityWrapper<AutUser>().isWhere(false).eq("user_name", userName));
		if (autUser != null) {
			user = new AutUserVO();
			BeanUtils.copyProperties(autUser, user);
			List<AutRoleVO> roleList = new ArrayList<AutRoleVO>();
			Where<AutUserRole> where = new Where<AutUserRole>();
			where.eq("is_active", 1);
			where.eq("user_id", autUser.getId());
			List<AutUserRole> autUserRoleList = autUserRoleService.selectList(where);
			List<String> roleCodeList = new ArrayList<String>();
			for (AutUserRole autUserRole : autUserRoleList) {
				// 获取角色列表
				AutRoleVO autRoleVO = new AutRoleVO();
				autRoleVO.setRoleCode(autUserRole.getRoleCode());
				autRoleVO.setRoleName(autUserRole.getRoleName());
				roleList.add(autRoleVO);
				roleCodeList.add(autUserRole.getRoleCode());
			}
			user.setRoleList(roleList);
			// 菜单列表
			List<AutMenuVO> autMenuVOList = new ArrayList<AutMenuVO>();
			if (roleCodeList.size() > 0) {
				// 根据角色列表获取菜单数据:角色->菜单ID->菜单
				Where<AutMenuRole> where2 = new Where<AutMenuRole>();
				where2.eq("is_active", 1);
				where2.in("role_code", roleCodeList);
				List<AutMenuRole> autMenuRoleList = autMenuRoleService.selectList(where2);
				List<Long> menuIdList = new ArrayList<Long>();
				for (AutMenuRole autMenuRole : autMenuRoleList) {
					menuIdList.add(autMenuRole.getMenuId());
				}
				Where<AutMenu> where3 = new Where<AutMenu>();
				where3.eq("is_active", 1);
				where3.in("id", menuIdList);
				where3.orderBy("menu_rank", true);
				List<AutMenu> autMenuList = autMenuService.selectList(where3);
				List<AutMenu> autMenuChildList = new ArrayList<AutMenu>();

				for (AutMenu autMenu : autMenuList) {
					if (autMenu.getMenuLevel().intValue() == 1) {
						// 一级菜单
						AutMenuVO autMenuVO = new AutMenuVO();
						BeanUtils.copyProperties(autMenu, autMenuVO);
						autMenuVOList.add(autMenuVO);
					} else {
						// 二级菜单
						autMenuChildList.add(autMenu);
					}
				}
				// 把二级菜单放到以及菜单中
				for (AutMenuVO autMenuVO : autMenuVOList) {
					List<AutMenuVO> children = new ArrayList<AutMenuVO>();
					for (AutMenu autMenu : autMenuChildList) {
						if (autMenuVO.getMenuCode().equals(autMenu.getParentCode())) {
							AutMenuVO child = new AutMenuVO();
							BeanUtils.copyProperties(autMenu, child);
							children.add(child);
						}
					}
					autMenuVO.setChildren(children);
				}
			}
			user.setMenuList(autMenuVOList);
		}
		return user;
	}

	public void ser1() {
		System.out.println("这是ser1...");
	}

	public void ser2() {
		System.out.println("这是ser2...");
	}
}
