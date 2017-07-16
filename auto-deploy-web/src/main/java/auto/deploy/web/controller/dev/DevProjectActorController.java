package auto.deploy.web.controller.dev;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;

import auto.deploy.dao.entity.dev.DevProjectActor;
import auto.deploy.object.PageBean;
import auto.deploy.object.RetMsg;
import auto.deploy.object.aut.vo.ProjectUserVO;
import auto.deploy.service.dev.DevProjectActorService;
import auto.deploy.web.controller.BaseController;

/**
 * 
 * @描述：项目参与者表(控制器).
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-07-16
 */
@Controller
@RequestMapping("/dev/devProjectActor")
public class DevProjectActorController extends BaseController {
	@Resource
	private DevProjectActorService devProjectActorService;

	/**
	 * 
	 * @描述：项目参与者表(页面).
	 *
	 * @返回：String
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-07-16
	 */
	@RequestMapping("/devProjectActorPage")
	public String devProjectActorPage(HttpServletRequest request, HttpServletResponse response) {

		return "dev/devProjectActorPage";
	}

	/**
	 * 
	 * @描述：项目参与者表(分页列表).
	 *
	 * @返回：Page<DevProjectActor>
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-07-16
	 */
	@RequestMapping("/list")
	@ResponseBody
	public Page<DevProjectActor> list(HttpServletRequest request, HttpServletResponse response, PageBean pageBean, DevProjectActor obj) {
		Page<DevProjectActor> page = null;
		try {
			page = devProjectActorService.list(pageBean, obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}

	/**
	 * 
	 * @描述：项目参与者表(新增).
	 *
	 * @返回：RetMsg
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-07-16
	 */
	@RequestMapping("/add")
	@ResponseBody
	public RetMsg add(HttpServletRequest request, HttpServletResponse response, DevProjectActor obj) {
		RetMsg retMsg = new RetMsg();

		// obj.set...

		devProjectActorService.insert(obj);
		retMsg.setCode(0);
		retMsg.setMessage("操作成功");
		return retMsg;
	}

	/**
	 * 
	 * @描述：项目参与者表(根据ID删除对象).
	 *
	 * @返回：RetMsg
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-07-16
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public RetMsg delete(HttpServletRequest request, HttpServletResponse response, DevProjectActor obj) {
		RetMsg retMsg = new RetMsg();

		devProjectActorService.deleteById(obj.getId());

		retMsg.setCode(0);
		retMsg.setMessage("操作成功");
		return retMsg;
	}

	/**
	 * 
	 * @描述：项目参与者表(根据ID修改对象).
	 *
	 * @返回：RetMsg
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-07-16
	 */
	@RequestMapping("/update")
	@ResponseBody
	public RetMsg update(HttpServletRequest request, HttpServletResponse response, DevProjectActor obj) {
		RetMsg retMsg = new RetMsg();

		DevProjectActor orgnlObj = devProjectActorService.selectById(obj.getId());
		// orgnlObj.set...

		devProjectActorService.updateById(orgnlObj);
		retMsg.setCode(0);
		retMsg.setMessage("操作成功");
		return retMsg;
	}

	/**
	 * 
	 * @描述：项目参与者表(根据ID获取对象).
	 *
	 * @返回：AutUser
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-07-16
	 */
	@RequestMapping("/getById")
	@ResponseBody
	public DevProjectActor getById(HttpServletRequest request, HttpServletResponse response, DevProjectActor obj) {
		return devProjectActorService.selectById(obj.getId());
	}

	/**
	 * 
	 * @描述：根据项目ID获取项目参与者列表.
	 *
	 * 						@返回：AutUser
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-07-16
	 */
	@RequestMapping("/getUserListByProjectId")
	@ResponseBody
	public ProjectUserVO getUserListByProjectId(HttpServletRequest request, HttpServletResponse response, DevProjectActor obj) {

		ProjectUserVO vo = devProjectActorService.getUserListByProjectId(obj.getProjectId());

		return vo;
	}

}
