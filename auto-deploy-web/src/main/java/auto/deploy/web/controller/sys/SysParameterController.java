package auto.deploy.web.controller.sys;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;

import auto.deploy.dao.config.Where;
import auto.deploy.dao.entity.sys.SysParameter;
import auto.deploy.object.PageBean;
import auto.deploy.object.RetMsg;
import auto.deploy.service.sys.SysParameterService;
import auto.deploy.web.controller.BaseController;

/**
 * 
 * @描述：系统参数表(控制器).
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-06-04
 */
@Controller
@RequestMapping("/sys/sysParameter")
public class SysParameterController extends BaseController {
	@Resource
	private SysParameterService sysParameterService;

	/**
	 * 
	 * @描述：系统参数表(页面).
	 *
	 * @返回：String
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-06-04
	 */
	@RequestMapping("/sysParameterPage")
	public String sysParameterPage(HttpServletRequest request, HttpServletResponse response) {

		return "sys/sysParameterPage";
	}

	/**
	 * 
	 * @描述：系统参数表(分页列表).
	 *
	 * @返回：Page<SysParameter>
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-06-04
	 */
	@RequestMapping("/list")
	@ResponseBody
	public Page<SysParameter> list(HttpServletRequest request, HttpServletResponse response, PageBean pageBean, SysParameter obj) {
		Page<SysParameter> page = null;
		try {
			page = sysParameterService.list(pageBean, obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}

	/**
	 * 
	 * @描述：系统参数表(新增).
	 *
	 * @返回：RetMsg
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-06-04
	 */
	@RequestMapping("/add")
	@ResponseBody
	public RetMsg add(HttpServletRequest request, HttpServletResponse response, SysParameter obj) {
		RetMsg retMsg = new RetMsg();

		// 判断key是否已被使用
		Where<SysParameter> where = new Where<SysParameter>();
		where.eq("param_key", obj.getParamKey());
		if (sysParameterService.selectCount(where) > 0) {
			retMsg.setCode(1);
			retMsg.setMessage("参数key已经存在");
		} else {
			sysParameterService.insert(obj);
			retMsg.setCode(0);
			retMsg.setMessage("操作成功");
		}
		return retMsg;
	}

	/**
	 * 
	 * @描述：系统参数表(根据ID删除对象).
	 *
	 * @返回：RetMsg
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-06-04
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public RetMsg delete(HttpServletRequest request, HttpServletResponse response, SysParameter obj) {
		RetMsg retMsg = new RetMsg();

		sysParameterService.deleteById(obj.getId());

		retMsg.setCode(0);
		retMsg.setMessage("操作成功");
		return retMsg;
	}

	/**
	 * 
	 * @描述：系统参数表(根据ID修改对象).
	 *
	 * @返回：RetMsg
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-06-04
	 */
	@RequestMapping("/update")
	@ResponseBody
	public RetMsg update(HttpServletRequest request, HttpServletResponse response, SysParameter obj) {
		RetMsg retMsg = new RetMsg();
		// 判断key是否已经被占用
		Where<SysParameter> where = new Where<SysParameter>();
		where.eq("param_key", obj.getParamKey());
		where.ne("id", obj.getId());
		if (sysParameterService.selectCount(where) > 0) {
			retMsg.setCode(1);
			retMsg.setMessage("参数key已经存在");
		} else {
			SysParameter orgnlObj = sysParameterService.selectById(obj.getId());
			orgnlObj.setParamDesc(obj.getParamDesc());
			orgnlObj.setParamKey(obj.getParamKey());
			orgnlObj.setParamValue(obj.getParamValue());
			orgnlObj.setIsActive(obj.getIsActive());
			sysParameterService.updateById(orgnlObj);
			retMsg.setCode(0);
			retMsg.setMessage("操作成功");
		}
		return retMsg;
	}

	/**
	 * 
	 * @描述：系统参数表(根据ID获取对象).
	 *
	 * @返回：AutUser
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-06-04
	 */
	@RequestMapping("/getById")
	@ResponseBody
	public SysParameter getById(HttpServletRequest request, HttpServletResponse response, SysParameter obj) {
		return sysParameterService.selectById(obj.getId());
	}

}
