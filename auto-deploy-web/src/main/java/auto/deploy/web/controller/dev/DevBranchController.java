package auto.deploy.web.controller.dev;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;

import auto.deploy.dao.config.Where;
import auto.deploy.dao.entity.dev.DevBranch;
import auto.deploy.object.PageBean;
import auto.deploy.object.RetMsg;
import auto.deploy.service.dev.DevBranchService;
import auto.deploy.web.controller.BaseController;

/**
 * 
 * @描述：项目分支表(控制器).
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-06-17
 */
@Controller
@RequestMapping("/dev/devBranch")
public class DevBranchController extends BaseController {
	@Resource
	private DevBranchService devBranchService;

	/**
	 * 
	 * @描述：项目分支表(页面).
	 *
	 * @返回：String
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-06-17
	 */
	@RequestMapping("/devBranchPage")
	public String devBranchPage(HttpServletRequest request, HttpServletResponse response) {

		return "dev/devBranchPage";
	}

	/**
	 * 
	 * @描述：项目分支表(分页列表).
	 *
	 * @返回：Page<DevBranch>
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-06-17
	 */
	@RequestMapping("/list")
	@ResponseBody
	public Page<DevBranch> list(HttpServletRequest request, HttpServletResponse response, PageBean pageBean, DevBranch obj) {
		Page<DevBranch> page = null;
		try {
			page = devBranchService.list(pageBean, obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}

	/**
	 * 
	 * @描述：项目分支表(新增).
	 *
	 * @返回：RetMsg
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-06-17
	 */
	@RequestMapping("/add")
	@ResponseBody
	public RetMsg add(HttpServletRequest request, HttpServletResponse response, DevBranch obj) {
		RetMsg retMsg = new RetMsg();
		try {
			//检查分支名称是否存在
			Where<DevBranch> branchWhere = new Where<DevBranch>();
			branchWhere.eq("branch_name", obj.getBranchName());
			if(devBranchService.selectCount(branchWhere) > 0){
				retMsg.setCode(1);
				retMsg.setMessage("分支名称已存在");
			}else{
				devBranchService.add(obj);
				retMsg.setCode(0);
				retMsg.setMessage("操作成功");
			}
		} catch (Exception e) {
			retMsg.setCode(1);
			retMsg.setMessage(e.getMessage());
			e.printStackTrace();
		}
		return retMsg;
	}

	/**
	 * 
	 * @描述：项目分支表(根据ID删除对象).
	 *
	 * @返回：RetMsg
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-06-17
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public RetMsg delete(HttpServletRequest request, HttpServletResponse response, DevBranch obj) {
		RetMsg retMsg = new RetMsg();

		devBranchService.deleteById(obj.getId());

		retMsg.setCode(0);
		retMsg.setMessage("操作成功");
		return retMsg;
	}

	/**
	 * 
	 * @描述：项目分支表(根据ID修改对象).
	 *
	 * @返回：RetMsg
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-06-17
	 */
	@RequestMapping("/update")
	@ResponseBody
	public RetMsg update(HttpServletRequest request, HttpServletResponse response, DevBranch obj) {
		RetMsg retMsg = new RetMsg();

		DevBranch orgnlObj = devBranchService.selectById(obj.getId());
		// orgnlObj.set...

		devBranchService.updateById(orgnlObj);
		retMsg.setCode(0);
		retMsg.setMessage("操作成功");
		return retMsg;
	}

	/**
	 * 
	 * @描述：项目分支表(根据ID获取对象).
	 *
	 * @返回：AutUser
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-06-17
	 */
	@RequestMapping("/getById")
	@ResponseBody
	public DevBranch getById(HttpServletRequest request, HttpServletResponse response, DevBranch obj) {
		return devBranchService.selectById(obj.getId());
	}

	/**
	 * 
	 * @描述：获取项目分支
	 *
	 * @返回：List<DevBranch>
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年7月25日 下午9:24:18
	 */
	@RequestMapping("/getListByProjectId")
	@ResponseBody
	public List<DevBranch> getListByProjectId(HttpServletRequest request, HttpServletResponse response, DevBranch obj) {
		List<DevBranch> list = new ArrayList<DevBranch>();
		try {
			Where<DevBranch> where = new Where<DevBranch>();
			where.eq("is_active", 1);
			where.eq("project_id", obj.getProjectId());
			list = devBranchService.selectList(where);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
