package auto.deploy.web.controller.sys;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;

import auto.deploy.dao.entity.sys.SysOperateLog;
import auto.deploy.object.PageBean;
import auto.deploy.object.RetMsg;
import auto.deploy.service.sys.SysOperateLogService;
import auto.deploy.web.annotation.FuncObj;
import auto.deploy.web.controller.BaseController;

/**
 * 
 * @描述：操作日志表(控制器).
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-06-05
 */
@Controller
@RequestMapping("/sys/sysOperateLog")
public class SysOperateLogController extends BaseController {
	@Resource
	private SysOperateLogService sysOperateLogService;
	
	/**
	 * 
	 * @描述：操作日志表(页面).
	 *
	 * @返回：String
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-06-05
	 */
	@RequestMapping("/sysOperateLogPage")
	public String sysOperateLogPage(HttpServletRequest request,HttpServletResponse response) {
		
		return "sys/sysOperateLogPage";
	}
	
	/**
	 * 
	 * @描述：操作日志表(分页列表).
	 *
	 * @返回：Page<SysOperateLog>
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-06-05
	 */
	@RequestMapping("/list")
	@ResponseBody
	public Page<SysOperateLog> list(HttpServletRequest request, HttpServletResponse response, PageBean pageBean, SysOperateLog obj) {
		Page<SysOperateLog> page = null;
		try {
			page = sysOperateLogService.list(pageBean, obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}
	

	/**
	 * 
	 * @描述：操作日志表(新增).
	 *
	 * @返回：RetMsg
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-06-05
	 */
	@RequestMapping("/add")
	@ResponseBody
	public RetMsg add(HttpServletRequest request, HttpServletResponse response, SysOperateLog obj) {
		RetMsg retMsg = new RetMsg();

		// obj.set...

		sysOperateLogService.insert(obj);
		retMsg.setCode(0);
		retMsg.setMessage("操作成功");
		return retMsg;
	}
	
	/**
	 * 
	 * @描述：操作日志表(根据ID删除对象).
	 *
	 * @返回：RetMsg
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-06-05
	 */
	@RequestMapping("/delete")
	@ResponseBody
	@FuncObj(desc = "[系统管理]-[日志管理]-[删除]")
	public RetMsg delete(HttpServletRequest request, HttpServletResponse response, SysOperateLog obj) {
		RetMsg retMsg = new RetMsg();

		sysOperateLogService.deleteById(obj.getId());

		retMsg.setCode(0);
		retMsg.setMessage("操作成功");
		return retMsg;
	}
	
	/**
	 * 
	 * @描述：操作日志表(根据ID修改对象).
	 *
	 * @返回：RetMsg
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-06-05
	 */
	@RequestMapping("/update")
	@ResponseBody
	public RetMsg update(HttpServletRequest request, HttpServletResponse response, SysOperateLog obj) {
		RetMsg retMsg = new RetMsg();

		SysOperateLog orgnlObj = sysOperateLogService.selectById(obj.getId());
		// orgnlObj.set...

		sysOperateLogService.updateById(orgnlObj);
		retMsg.setCode(0);
		retMsg.setMessage("操作成功");
		return retMsg;
	}
    
	/**
	 * 
	 * @描述：操作日志表(根据ID获取对象).
	 *
	 * @返回：AutUser
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-06-05
	 */
	@RequestMapping("/getById")
	@ResponseBody
	public SysOperateLog getById(HttpServletRequest request, HttpServletResponse response, SysOperateLog obj) {
		return sysOperateLogService.selectById(obj.getId());
	}

}
