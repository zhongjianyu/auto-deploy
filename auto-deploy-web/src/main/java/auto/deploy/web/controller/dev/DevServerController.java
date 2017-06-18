package auto.deploy.web.controller.dev;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;

import auto.deploy.dao.entity.dev.DevServer;
import auto.deploy.object.PageBean;
import auto.deploy.object.RetMsg;
import auto.deploy.service.dev.DevServerService;
import auto.deploy.web.controller.BaseController;

/**
 * 
 * @描述：服务器表(控制器).
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-06-17
 */
@Controller
@RequestMapping("/dev/devServer")
public class DevServerController extends BaseController {
	@Resource
	private DevServerService devServerService;
	
	/**
	 * 
	 * @描述：服务器表(页面).
	 *
	 * @返回：String
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-06-17
	 */
	@RequestMapping("/devServerPage")
	public String devServerPage(HttpServletRequest request,HttpServletResponse response) {
		
		return "dev/devServerPage";
	}
	
	/**
	 * 
	 * @描述：服务器表(分页列表).
	 *
	 * @返回：Page<DevServer>
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-06-17
	 */
	@RequestMapping("/list")
	@ResponseBody
	public Page<DevServer> list(HttpServletRequest request, HttpServletResponse response, PageBean pageBean, DevServer obj) {
		Page<DevServer> page = null;
		try {
			page = devServerService.list(pageBean, obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}
	

	/**
	 * 
	 * @描述：服务器表(新增).
	 *
	 * @返回：RetMsg
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-06-17
	 */
	@RequestMapping("/add")
	@ResponseBody
	public RetMsg add(HttpServletRequest request, HttpServletResponse response, DevServer obj) {
		RetMsg retMsg = new RetMsg();

		obj.setServerStatus(2);

		devServerService.insert(obj);
		retMsg.setCode(0);
		retMsg.setMessage("操作成功");
		return retMsg;
	}
	
	/**
	 * 
	 * @描述：服务器表(根据ID删除对象).
	 *
	 * @返回：RetMsg
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-06-17
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public RetMsg delete(HttpServletRequest request, HttpServletResponse response, DevServer obj) {
		RetMsg retMsg = new RetMsg();

		devServerService.deleteById(obj.getId());

		retMsg.setCode(0);
		retMsg.setMessage("操作成功");
		return retMsg;
	}
	
	/**
	 * 
	 * @描述：服务器表(根据ID修改对象).
	 *
	 * @返回：RetMsg
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-06-17
	 */
	@RequestMapping("/update")
	@ResponseBody
	public RetMsg update(HttpServletRequest request, HttpServletResponse response, DevServer obj) {
		RetMsg retMsg = new RetMsg();

		DevServer orgnlObj = devServerService.selectById(obj.getId());
		orgnlObj.setServerIp(obj.getServerIp());
		orgnlObj.setUserName(obj.getUserName());
		orgnlObj.setUserPwd(obj.getUserPwd());
		orgnlObj.setServerPort(obj.getServerPort());
		orgnlObj.setTimeOut(obj.getTimeOut());
		orgnlObj.setServerDesc(obj.getServerDesc());
		orgnlObj.setIsActive(obj.getIsActive());
		devServerService.updateById(orgnlObj);
		retMsg.setCode(0);
		retMsg.setMessage("操作成功");
		return retMsg;
	}
    
	/**
	 * 
	 * @描述：服务器表(根据ID获取对象).
	 *
	 * @返回：AutUser
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-06-17
	 */
	@RequestMapping("/getById")
	@ResponseBody
	public DevServer getById(HttpServletRequest request, HttpServletResponse response, DevServer obj) {
		return devServerService.selectById(obj.getId());
	}

}
