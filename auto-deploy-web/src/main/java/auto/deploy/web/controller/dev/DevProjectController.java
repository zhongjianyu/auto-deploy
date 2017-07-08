package auto.deploy.web.controller.dev;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;

import auto.deploy.dao.entity.dev.DevProject;
import auto.deploy.object.PageBean;
import auto.deploy.object.RetMsg;
import auto.deploy.service.dev.DevProjectGroupService;
import auto.deploy.service.dev.DevProjectService;
import auto.deploy.web.controller.BaseController;

/**
 * 
 * @描述：项目表(控制器).
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-06-17
 */
@Controller
@RequestMapping("/dev/devProject")
public class DevProjectController extends BaseController {
	@Resource
	private DevProjectService devProjectService;
	@Resource
	private DevProjectGroupService devProjectGroupService;

	/**
	 * 
	 * @描述：项目表(页面).
	 *
	 * @返回：String
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-06-17
	 */
	@RequestMapping("/devProjectPage")
	public String devProjectPage(HttpServletRequest request, HttpServletResponse response) {

		return "dev/devProjectPage";
	}

	/**
	 * 
	 * @描述：项目表(分页列表).
	 *
	 * @返回：Page<DevProject>
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-06-17
	 */
	@RequestMapping("/list")
	@ResponseBody
	public Page<DevProject> list(HttpServletRequest request, HttpServletResponse response, PageBean pageBean, DevProject obj) {
		Page<DevProject> page = null;
		try {
			page = devProjectService.list(pageBean, obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}

	/**
	 * 
	 * @描述：项目表(新增).
	 *
	 * @返回：RetMsg
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-06-17
	 */
	@RequestMapping("/add")
	@ResponseBody
	public RetMsg add(HttpServletRequest request, HttpServletResponse response, DevProject obj) {
		RetMsg retMsg = new RetMsg();
		try {
			devProjectService.add(obj);
			retMsg.setCode(0);
			retMsg.setMessage("操作成功");
		} catch (Exception e) {
			retMsg.setCode(1);
			retMsg.setMessage(e.getMessage());
			e.printStackTrace();
		}
		return retMsg;
	}

	/**
	 * 
	 * @描述：项目表(根据ID删除对象).
	 *
	 * @返回：RetMsg
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-06-17
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public RetMsg delete(HttpServletRequest request, HttpServletResponse response, DevProject obj) {
		RetMsg retMsg = new RetMsg();

		try {
			devProjectService.del(obj);
			retMsg.setCode(0);
			retMsg.setMessage("操作成功");
		} catch (Exception e) {
			retMsg.setCode(1);
			retMsg.setMessage(e.getMessage());
			e.printStackTrace();
		}
		return retMsg;
	}

	/**
	 * 
	 * @描述：项目表(根据ID修改对象).
	 *
	 * @返回：RetMsg
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-06-17
	 */
	@RequestMapping("/update")
	@ResponseBody
	public RetMsg update(HttpServletRequest request, HttpServletResponse response, DevProject obj) {
		RetMsg retMsg = new RetMsg();

		DevProject orgnlObj = devProjectService.selectById(obj.getId());
		orgnlObj.setProjectDesc(obj.getProjectDesc());
		orgnlObj.setIsActive(obj.getIsActive());
		devProjectService.updateById(orgnlObj);
		retMsg.setCode(0);
		retMsg.setMessage("操作成功");
		return retMsg;
	}

	/**
	 * 
	 * @描述：项目表(根据ID获取对象).
	 *
	 * @返回：AutUser
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-06-17
	 */
	@RequestMapping("/getById")
	@ResponseBody
	public DevProject getById(HttpServletRequest request, HttpServletResponse response, DevProject obj) {
		return devProjectService.selectById(obj.getId());
	}

}
