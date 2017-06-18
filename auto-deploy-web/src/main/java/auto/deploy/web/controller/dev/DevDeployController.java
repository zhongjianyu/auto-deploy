package auto.deploy.web.controller.dev;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;

import auto.deploy.dao.entity.dev.DevDeploy;
import auto.deploy.object.PageBean;
import auto.deploy.object.RetMsg;
import auto.deploy.service.dev.DevDeployService;
import auto.deploy.web.controller.BaseController;

/**
 * 
 * @描述：项目部署表(控制器).
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-06-17
 */
@Controller
@RequestMapping("/dev/devDeploy")
public class DevDeployController extends BaseController {
	@Resource
	private DevDeployService devDeployService;
	
	/**
	 * 
	 * @描述：项目部署表(页面).
	 *
	 * @返回：String
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-06-17
	 */
	@RequestMapping("/devDeployPage")
	public String devDeployPage(HttpServletRequest request,HttpServletResponse response) {
		
		return "dev/devDeployPage";
	}
	
	/**
	 * 
	 * @描述：项目部署表(分页列表).
	 *
	 * @返回：Page<DevDeploy>
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-06-17
	 */
	@RequestMapping("/list")
	@ResponseBody
	public Page<DevDeploy> list(HttpServletRequest request, HttpServletResponse response, PageBean pageBean, DevDeploy obj) {
		Page<DevDeploy> page = null;
		try {
			page = devDeployService.list(pageBean, obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}
	

	/**
	 * 
	 * @描述：项目部署表(新增).
	 *
	 * @返回：RetMsg
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-06-17
	 */
	@RequestMapping("/add")
	@ResponseBody
	public RetMsg add(HttpServletRequest request, HttpServletResponse response, DevDeploy obj) {
		RetMsg retMsg = new RetMsg();

		// obj.set...

		devDeployService.insert(obj);
		retMsg.setCode(0);
		retMsg.setMessage("操作成功");
		return retMsg;
	}
	
	/**
	 * 
	 * @描述：项目部署表(根据ID删除对象).
	 *
	 * @返回：RetMsg
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-06-17
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public RetMsg delete(HttpServletRequest request, HttpServletResponse response, DevDeploy obj) {
		RetMsg retMsg = new RetMsg();

		devDeployService.deleteById(obj.getId());

		retMsg.setCode(0);
		retMsg.setMessage("操作成功");
		return retMsg;
	}
	
	/**
	 * 
	 * @描述：项目部署表(根据ID修改对象).
	 *
	 * @返回：RetMsg
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-06-17
	 */
	@RequestMapping("/update")
	@ResponseBody
	public RetMsg update(HttpServletRequest request, HttpServletResponse response, DevDeploy obj) {
		RetMsg retMsg = new RetMsg();

		DevDeploy orgnlObj = devDeployService.selectById(obj.getId());
		// orgnlObj.set...

		devDeployService.updateById(orgnlObj);
		retMsg.setCode(0);
		retMsg.setMessage("操作成功");
		return retMsg;
	}
    
	/**
	 * 
	 * @描述：项目部署表(根据ID获取对象).
	 *
	 * @返回：AutUser
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-06-17
	 */
	@RequestMapping("/getById")
	@ResponseBody
	public DevDeploy getById(HttpServletRequest request, HttpServletResponse response, DevDeploy obj) {
		return devDeployService.selectById(obj.getId());
	}

}
