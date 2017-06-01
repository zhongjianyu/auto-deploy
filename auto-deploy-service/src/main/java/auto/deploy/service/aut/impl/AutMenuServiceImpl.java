package auto.deploy.service.aut.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.StringUtils;

import auto.deploy.dao.config.Where;
import auto.deploy.dao.entity.aut.AutMenu;
import auto.deploy.dao.mapper.aut.AutMenuMapper;
import auto.deploy.object.PageBean;
import auto.deploy.service.aut.AutMenuService;

/**
 * 
 * @描述：菜单表(服务实现类).
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-05-27
 */
@Service
public class AutMenuServiceImpl extends ServiceImpl<AutMenuMapper, AutMenu>implements AutMenuService {

	@Override
	public Page<AutMenu> list(PageBean pageBean, AutMenu obj) throws Exception {
		Where<AutMenu> where = new Where<AutMenu>();
		if (StringUtils.isNotEmpty(obj.getMenuName())) {
			where.like("menu_name", obj.getMenuName());
		}
		if (obj.getMenuLevel() != null) {
			where.eq("menu_level", obj.getMenuLevel());
		}
		if (obj.getParentCode() != null) {
			where.eq("parent_code", obj.getParentCode());
		}
		where.orderBy("menu_code", true);
		Page<AutMenu> page = selectPage(new Page<AutMenu>(pageBean.getPageNum(), pageBean.getPageSize()), where);
		return page;
	}

	@Override
	public String getNextMenuCode(int menuLevel, String parentCode) throws Exception {
		String nextMenuCode = "";
		if (menuLevel == 1) {
			Where<AutMenu> where = new Where<AutMenu>();
			where.eq("menu_level", 1);
			where.orderBy("menu_code", false);
			where.setSqlSelect("menu_code");
			where.last("LIMIT 0,1");
			List<AutMenu> autMenuList = selectList(where);
			if (autMenuList.size() > 0) {
				AutMenu autMenu = autMenuList.get(0);
				nextMenuCode = autMenu.getMenuCode();
				String codeStr = nextMenuCode.substring(0, 2);
				Integer codeNum = Integer.parseInt(codeStr);
				codeNum++;
				if (codeNum > 99) {
					throw new Exception("菜单数已经超过99");
				} else {
					if (codeNum > 9) {
						codeStr = codeNum + "0000";
					} else {
						codeStr = "0" + codeNum + "0000";
					}
					nextMenuCode = codeStr;
				}
			} else {
				nextMenuCode = "000000";
			}
		} else {
			Where<AutMenu> where = new Where<AutMenu>();
			where.eq("parent_code", parentCode);
			where.orderBy("menu_code", false);
			where.last("LIMIT 0,1");
			where.setSqlSelect("menu_code");
			List<AutMenu> autMenuList = selectList(where);
			if (autMenuList.size() > 0) {
				AutMenu autMenu = autMenuList.get(0);
				nextMenuCode = autMenu.getMenuCode();

				String codeStr = nextMenuCode.substring(2, 4);
				Integer codeNum = Integer.parseInt(codeStr);
				codeNum++;
				if (codeNum > 99) {
					throw new Exception("菜单数已经超过99");
				} else {
					if (codeNum > 9) {
						codeStr = parentCode.substring(0, 2) + codeNum + "00";
					} else {
						codeStr = parentCode.substring(0, 2) + "0" + codeNum + "00";
					}
					nextMenuCode = codeStr;
				}
			} else {
				nextMenuCode = parentCode.substring(0, 2) + "0100";
			}
		}
		return nextMenuCode;
	}
}
