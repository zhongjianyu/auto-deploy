package auto.deploy.web.controller.aut;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;

import auto.deploy.dao.entity.aut.AutMenu;
import auto.deploy.object.PageBean;
import auto.deploy.object.RetMsg;
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
	public String autMenuPage(HttpServletRequest request,HttpServletResponse response) {
		
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

		// obj.set...

		autMenuService.insert(obj);
		retMsg.setCode(0);
		retMsg.setMessage("操作成功");
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
		// orgnlObj.set...

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
	public AutMenu getById(HttpServletRequest request, HttpServletResponse response, AutMenu obj) {
		return autMenuService.selectById(obj.getId());
	}

}
