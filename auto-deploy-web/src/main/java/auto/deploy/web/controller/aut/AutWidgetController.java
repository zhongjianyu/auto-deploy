package auto.deploy.web.controller.aut;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;

import auto.deploy.dao.entity.aut.AutWidget;
import auto.deploy.object.PageBean;
import auto.deploy.object.RetMsg;
import auto.deploy.object.aut.dto.AutWidgetDO;
import auto.deploy.service.aut.AutWidgetService;
import auto.deploy.web.controller.BaseController;

/**
 * 
 * @描述：控件表(控制器).
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-05-27
 */
@Controller
@RequestMapping("/aut/autWidget")
public class AutWidgetController extends BaseController {
	@Resource
	private AutWidgetService autWidgetService;

	/**
	 * 
	 * @描述：控件表(页面).
	 *
	 * @返回：String
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-05-27
	 */
	@RequestMapping("/autWidgetPage")
	public String autWidgetPage(HttpServletRequest request, HttpServletResponse response) {

		return "aut/autWidgetPage";
	}

	/**
	 * 
	 * @描述：控件表(分页列表).
	 *
	 * @返回：Page<AutWidget>
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-05-27
	 */
	@RequestMapping("/list")
	@ResponseBody
	public Page<AutWidgetDO> list(HttpServletRequest request, HttpServletResponse response, PageBean pageBean, AutWidgetDO obj) {
		Page<AutWidgetDO> page = null;
		try {
			page = autWidgetService.list(pageBean, obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}

	/**
	 * 
	 * @描述：控件表(新增).
	 *
	 * @返回：RetMsg
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-05-27
	 */
	@RequestMapping("/add")
	@ResponseBody
	public RetMsg add(HttpServletRequest request, HttpServletResponse response, AutWidget obj) {
		RetMsg retMsg = new RetMsg();

		// obj.set...

		autWidgetService.insert(obj);
		retMsg.setCode(0);
		retMsg.setMessage("操作成功");
		return retMsg;
	}

	/**
	 * 
	 * @描述：控件表(根据ID删除对象).
	 *
	 * @返回：RetMsg
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-05-27
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public RetMsg delete(HttpServletRequest request, HttpServletResponse response, AutWidget obj) {
		RetMsg retMsg = new RetMsg();

		autWidgetService.deleteById(obj.getId());

		retMsg.setCode(0);
		retMsg.setMessage("操作成功");
		return retMsg;
	}

	/**
	 * 
	 * @描述：控件表(根据ID修改对象).
	 *
	 * @返回：RetMsg
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-05-27
	 */
	@RequestMapping("/update")
	@ResponseBody
	public RetMsg update(HttpServletRequest request, HttpServletResponse response, AutWidget obj) {
		RetMsg retMsg = new RetMsg();

		AutWidget orgnlObj = autWidgetService.selectById(obj.getId());
		// orgnlObj.set...

		autWidgetService.updateById(orgnlObj);
		retMsg.setCode(0);
		retMsg.setMessage("操作成功");
		return retMsg;
	}

	/**
	 * 
	 * @描述：控件表(根据ID获取对象).
	 *
	 * @返回：AutUser
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-05-27
	 */
	@RequestMapping("/getById")
	@ResponseBody
	public AutWidget getById(HttpServletRequest request, HttpServletResponse response, AutWidget obj) {
		return autWidgetService.selectById(obj.getId());
	}

}
