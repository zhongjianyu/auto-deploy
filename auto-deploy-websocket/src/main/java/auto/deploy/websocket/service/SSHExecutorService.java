package auto.deploy.websocket.service;

import auto.deploy.websocket.object.ServerConfig;

/**
 * 
 * @描述：ssh执行器(接口).
 *
 * @作者：zhongjy
 *
 * @时间：2017年6月13日 下午7:31:41
 */
public interface SSHExecutorService {

	/**
	 * 
	 * @描述：tail实时日志
	 *
	 * @返回：void
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年6月13日 下午9:57:16
	 */
	public void tailDeployLog(String cmd, String userName, ServerConfig config);
}
