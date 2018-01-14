package auto.deploy.web.controller.dev;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;

import auto.deploy.object.PageBean;
import auto.deploy.object.RetMsg;
import auto.deploy.object.aut.vo.DevTaskVO;
import auto.deploy.security.CustomUser;
import auto.deploy.service.dev.DevBranchService;
import auto.deploy.web.controller.BaseController;
import auto.deploy.web.task.WebTask;
import auto.deploy.websocket.object.ServerConfig;
import auto.deploy.websocket.service.SSHExecutorService;

/**
 * 
 * @描述：项目开发(控制器).
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-05-27
 */
@Controller
@RequestMapping("/dev/devProjectDev")
public class DevProjectDevController extends BaseController {

	@Resource
	private SSHExecutorService sSHExecutorService;
	@Resource
	private WebTask webTask;
	@Resource
	private ThreadPoolTaskExecutor taskAsyncPool;
	@Resource
	private DevBranchService devBranchService;

	/**
	 * 
	 * @描述：项目开发(页面).
	 *
	 * @返回：String
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-05-27
	 */
	@RequestMapping("/devProjectDevPage")
	public String projectDevPage(HttpServletRequest request, HttpServletResponse response) {
		return "dev/devProjectDevPage";
	}

	@RequestMapping("/doDeploy")
	@ResponseBody
	public RetMsg doDeploy() {
		RetMsg msg = new RetMsg();
		CustomUser user = getCustomDetail();
		ServerConfig config = new ServerConfig();
		config.setHost("120.25.81.213");
		config.setPort(22);
		config.setTimeOut(10000);
		config.setUserName("root");
		config.setUserPwd("***A1987");
		String cmd = "tail -f /root/projects/16tbk/logs/log";
		try {
			webTask.tailLogTask(cmd, user.getUsername(), config);
			msg.setCode(0);
			msg.setMessage("触发成功");
		} catch (InterruptedException e) {
			e.printStackTrace();
			msg.setCode(1);
			msg.setMessage("触发失败");
		}

		return msg;
	}

	@RequestMapping("/unDeploy")
	@ResponseBody
	public RetMsg unDeploy() {
		RetMsg msg = new RetMsg();
		System.out.println(taskAsyncPool.getActiveCount());
		msg.setCode(0);
		msg.setMessage("触发成功");
		return msg;
	}

	/**
	 * 
	 * @描述：项目各个节点数据结果集
	 *
	 * @返回：Page<DevTaskVO>
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2018年1月14日 下午10:26:06
	 */
	@RequestMapping("/getProcessBranch")
	@ResponseBody
	public Page<DevTaskVO> getProcessBranch(HttpServletRequest request, HttpServletResponse response, PageBean pageBean) {
		Map<String, String> param = new HashMap<String, String>();

		param.put("taskKey", request.getParameter("taskKey"));
		param.put("userId", getCustomDetail().getUserId().toString());
		param.put("searchKey", request.getParameter("searchKey"));

		Page<DevTaskVO> result = null;
		try {
			result = devBranchService.getProcessBranch(pageBean, param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
