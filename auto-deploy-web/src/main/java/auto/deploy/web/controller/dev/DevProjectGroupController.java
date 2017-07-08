package auto.deploy.web.controller.dev;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;

import auto.deploy.dao.entity.dev.DevProjectGroup;
import auto.deploy.object.PageBean;
import auto.deploy.object.RetMsg;
import auto.deploy.service.dev.DevProjectGroupService;
import auto.deploy.web.controller.BaseController;

/**
 * 
 * @描述：项目分组表(控制器).
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-06-17
 */
@Controller
@RequestMapping("/dev/devProjectGroup")
public class DevProjectGroupController extends BaseController {
	@Resource
	private DevProjectGroupService devProjectGroupService;

	/**
	 * 
	 * @描述：项目分组表(页面).
	 *
	 * @返回：String
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-06-17
	 */
	@RequestMapping("/devProjectGroupPage")
	public String devProjectGroupPage(HttpServletRequest request, HttpServletResponse response) {

		return "dev/devProjectGroupPage";
	}

	/**
	 * 
	 * @描述：项目分组表(分页列表).
	 *
	 * @返回：Page<DevProjectGroup>
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-06-17
	 */
	@RequestMapping("/list")
	@ResponseBody
	public Page<DevProjectGroup> list(HttpServletRequest request, HttpServletResponse response, PageBean pageBean, DevProjectGroup obj) {
		Page<DevProjectGroup> page = null;
		try {
			page = devProjectGroupService.list(pageBean, obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}

	/**
	 * 
	 * @描述：项目分组表(新增).
	 *
	 * @返回：RetMsg
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-06-17
	 */
	@RequestMapping("/add")
	@ResponseBody
	public RetMsg add(HttpServletRequest request, HttpServletResponse response, DevProjectGroup obj) {
		RetMsg retMsg = new RetMsg();
		try {
			retMsg = devProjectGroupService.add(obj);
		} catch (Exception e) {
			retMsg.setCode(1);
			retMsg.setMessage(e.getMessage());
			e.printStackTrace();
		}
		return retMsg;
	}

	/**
	 * 
	 * @描述：项目分组表(根据ID删除对象).
	 *
	 * @返回：RetMsg
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-06-17
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public RetMsg delete(HttpServletRequest request, HttpServletResponse response, DevProjectGroup obj) {
		RetMsg retMsg = new RetMsg();
		try {
			retMsg = devProjectGroupService.del(obj);
		} catch (Exception e) {
			retMsg.setCode(1);
			retMsg.setMessage(e.getMessage());
			e.printStackTrace();
		}
		return retMsg;
	}

	/**
	 * 
	 * @描述：项目分组表(根据ID修改对象).
	 *
	 * @返回：RetMsg
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-06-17
	 */
	@RequestMapping("/update")
	@ResponseBody
	public RetMsg update(HttpServletRequest request, HttpServletResponse response, DevProjectGroup obj) {
		RetMsg retMsg = new RetMsg();

		DevProjectGroup orgnlObj = devProjectGroupService.selectById(obj.getId());
		orgnlObj.setGroupName(obj.getGroupName());
		orgnlObj.setGroupDesc(obj.getGroupDesc());
		orgnlObj.setIsActive(obj.getIsActive());
		devProjectGroupService.updateById(orgnlObj);
		retMsg.setCode(0);
		retMsg.setMessage("操作成功");
		return retMsg;
	}

	/**
	 * 
	 * @描述：项目分组表(根据ID获取对象).
	 *
	 * @返回：AutUser
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-06-17
	 */
	@RequestMapping("/getById")
	@ResponseBody
	public DevProjectGroup getById(HttpServletRequest request, HttpServletResponse response, DevProjectGroup obj) {
		return devProjectGroupService.selectById(obj.getId());
	}

}
