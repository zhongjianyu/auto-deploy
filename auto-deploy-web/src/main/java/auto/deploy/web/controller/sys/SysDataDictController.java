package auto.deploy.web.controller.sys;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;

import auto.deploy.dao.entity.sys.SysDataDict;
import auto.deploy.object.PageBean;
import auto.deploy.object.RetMsg;
import auto.deploy.service.sys.SysDataDictService;
import auto.deploy.web.controller.BaseController;

/**
 * 
 * @描述：数据字典表(控制器).
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-05-27
 */
@Controller
@RequestMapping("/sys/sysDataDict")
public class SysDataDictController extends BaseController {
	@Resource
	private SysDataDictService sysDataDictService;

	/**
	 * 
	 * @描述：数据字典表(页面).
	 *
	 * @返回：String
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-05-27
	 */
	@RequestMapping("/sysDataDictPage")
	public String sysDataDictPage(HttpServletRequest request, HttpServletResponse response) {

		return "sys/sysDataDictPage";
	}

	/**
	 * 
	 * @描述：数据字典表(分页列表).
	 *
	 * @返回：Page<SysDataDict>
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-05-27
	 */
	@RequestMapping("/list")
	@ResponseBody
	public Page<SysDataDict> list(HttpServletRequest request, HttpServletResponse response, PageBean pageBean, SysDataDict obj) {
		Page<SysDataDict> page = null;
		try {
			page = sysDataDictService.list(pageBean, obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}

	/**
	 * 
	 * @描述：数据字典表(新增).
	 *
	 * @返回：RetMsg
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-05-27
	 */
	@RequestMapping("/add")
	@ResponseBody
	public RetMsg add(HttpServletRequest request, HttpServletResponse response, SysDataDict obj) {
		RetMsg retMsg = new RetMsg();

		// obj.set...

		sysDataDictService.insert(obj);
		retMsg.setCode(0);
		retMsg.setMessage("操作成功");
		return retMsg;
	}

	/**
	 * 
	 * @描述：数据字典表(根据ID删除对象).
	 *
	 * @返回：RetMsg
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-05-27
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public RetMsg delete(HttpServletRequest request, HttpServletResponse response, SysDataDict obj) {
		RetMsg retMsg = new RetMsg();

		sysDataDictService.deleteById(obj.getId());

		retMsg.setCode(0);
		retMsg.setMessage("操作成功");
		return retMsg;
	}

	/**
	 * 
	 * @描述：数据字典表(根据ID修改对象).
	 *
	 * @返回：RetMsg
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-05-27
	 */
	@RequestMapping("/update")
	@ResponseBody
	public RetMsg update(HttpServletRequest request, HttpServletResponse response, SysDataDict obj) {
		RetMsg retMsg = new RetMsg();

		SysDataDict orgnlObj = sysDataDictService.selectById(obj.getId());
		// orgnlObj.set...

		sysDataDictService.updateById(orgnlObj);
		retMsg.setCode(0);
		retMsg.setMessage("操作成功");
		return retMsg;
	}

	/**
	 * 
	 * @描述：数据字典表(根据ID获取对象).
	 *
	 * @返回：AutUser
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-05-27
	 */
	@RequestMapping("/getById")
	@ResponseBody
	public SysDataDict getById(HttpServletRequest request, HttpServletResponse response, SysDataDict obj) {
		return sysDataDictService.selectById(obj.getId());
	}

}
