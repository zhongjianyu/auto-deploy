package auto.deploy.websocket.util;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import auto.deploy.websocket.object.ServerConfig;
import auto.deploy.websocket.object.SshUserInfo;

/**
 * 
 * @描述：ssh工具类
 *
 * @作者：zhongjy
 *
 * @时间：2017年6月13日 下午7:49:56
 */
public class SshUtil {

	/**
	 * 
	 * @描述：获取ssh连接session
	 *
	 * @返回：Session
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年6月13日 下午7:58:12
	 */
	public static Session getSession(ServerConfig config) {
		Session session = null;
		try {
			JSch jsch = new JSch();
			session = jsch.getSession(config.getUserName(), config.getHost(), config.getPort());
			session.setPassword(config.getUserPwd());
			session.setConfig("StrictHostKeyChecking", "no");
			session.setUserInfo(new SshUserInfo());
			session.connect(config.getTimeOut());
		} catch (JSchException e) {
			e.printStackTrace();
		}
		return session;
	}
}
