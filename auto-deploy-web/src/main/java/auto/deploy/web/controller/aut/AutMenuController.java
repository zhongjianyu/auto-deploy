package auto.deploy.web.controller.aut;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;

import auto.deploy.dao.config.Where;
import auto.deploy.dao.entity.aut.AutMenu;
import auto.deploy.object.PageBean;
import auto.deploy.object.RetMsg;
import auto.deploy.object.aut.dto.AutMenuDO;
import auto.deploy.service.aut.AutMenuService;
import auto.deploy.web.controller.BaseController;

/**
 * 
 * @描述：菜单表(控制器).
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-05-27
 */
@Controller
@RequestMapping("/aut/autMenu")
public class AutMenuController extends BaseController {
	@Resource
	private AutMenuService autMenuService;

	/**
	 * 
	 * @描述：菜单表(页面).
	 *
	 * @返回：String
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-05-27
	 */
	@RequestMapping("/autMenuPage")
	public String autMenuPage(HttpServletRequest request, HttpServletResponse response) {

		return "aut/autMenuPage";
	}

	/**
	 * 
	 * @描述：菜单表(分页列表).
	 *
	 * @返回：Page<AutMenu>
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-05-27
	 */
	@RequestMapping("/list")
	@ResponseBody
	public Page<AutMenu> list(HttpServletRequest request, HttpServletResponse response, PageBean pageBean, AutMenu obj) {
		Page<AutMenu> page = null;
		try {
			Thread.sleep(3000);
			page = autMenuService.list(pageBean, obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}

	/**
	 * 
	 * @描述：菜单表(新增).
	 *
	 * @返回：RetMsg
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-05-27
	 */
	@RequestMapping("/add")
	@ResponseBody
	public RetMsg add(HttpServletRequest request, HttpServletResponse response, AutMenu obj) {
		RetMsg retMsg = new RetMsg();
		// 判断菜单编码是否存在
		try {
			obj.setMenuCode(autMenuService.getNextMenuCode(obj.getMenuLevel(), obj.getParentCode()));
			autMenuService.insert(obj);
			retMsg.setCode(0);
			retMsg.setMessage("操作成功");
		} catch (Exception e) {
			retMsg.setCode(1);
			e.printStackTrace();
		}
		return retMsg;
	}

	/**
	 * 
	 * @描述：菜单表(根据ID删除对象).
	 *
	 * @返回：RetMsg
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-05-27
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public RetMsg delete(HttpServletRequest request, HttpServletResponse response, AutMenu obj) {
		RetMsg retMsg = new RetMsg();

		// 如果是父节点，则子节则其下面的所有子节点也会被删除
		AutMenu autMenu = autMenuService.selectById(obj.getId());
		if (autMenu.getMenuLevel().intValue() == 1) {
			Where<AutMenu> where = new Where<AutMenu>();
			where.eq("parent_code", autMenu.getMenuCode());
			autMenuService.delete(where);
		}
		autMenuService.deleteById(obj.getId());
		retMsg.setCode(0);
		retMsg.setMessage("操作成功");
		return retMsg;
	}

	/**
	 * 
	 * @描述：菜单表(根据ID修改对象).
	 *
	 * @返回：RetMsg
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-05-27
	 */
	@RequestMapping("/update")
	@ResponseBody
	public RetMsg update(HttpServletRequest request, HttpServletResponse response, AutMenu obj) {
		RetMsg retMsg = new RetMsg();

		AutMenu orgnlObj = autMenuService.selectById(obj.getId());
		orgnlObj.setMenuName(obj.getMenuName());
		orgnlObj.setMenuHref(obj.getMenuHref());
		orgnlObj.setMenuIcon(obj.getMenuIcon());
		orgnlObj.setMenuRank(obj.getMenuRank());
		orgnlObj.setIsActive(obj.getIsActive());

		autMenuService.updateById(orgnlObj);
		retMsg.setCode(0);
		retMsg.setMessage("操作成功");
		return retMsg;
	}

	/**
	 * 
	 * @描述：菜单表(根据ID获取对象).
	 *
	 * @返回：AutUser
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-05-27
	 */
	@RequestMapping("/getById")
	@ResponseBody
	public AutMenuDO getById(HttpServletRequest request, HttpServletResponse response, AutMenu obj) {
		AutMenuDO autMenuDO = new AutMenuDO();
		AutMenu autMenu = autMenuService.selectById(obj.getId());
		autMenuDO.setAutMenu(autMenu);
		if (autMenu.getMenuLevel().intValue() == 2) {
			Where<AutMenu> where = new Where<AutMenu>();
			where.eq("menu_code", autMenu.getParentCode());
			autMenuDO.setParentMenu(autMenuService.selectOne(where));
		}
		return autMenuDO;
	}

}
