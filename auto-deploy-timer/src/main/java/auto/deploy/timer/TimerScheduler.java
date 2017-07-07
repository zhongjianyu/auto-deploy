package auto.deploy.timer;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.jcraft.jsch.Session;

import auto.deploy.dao.config.Where;
import auto.deploy.dao.entity.dev.DevServer;
import auto.deploy.service.dev.DevServerService;
import auto.deploy.websocket.object.ServerConfig;
import auto.deploy.websocket.util.SshUtil;

/**
 * 
 * @描述：定时器调度
 *
 * @作者：zhongjy
 *
 * @时间：2017年7月6日 下午9:36:08
 */
@Component
public class TimerScheduler {

	private final static Logger logger = LoggerFactory.getLogger(TimerScheduler.class);

	@Resource
	private DevServerService devServerService;

	/**
	 * 
	 * @描述：定时检查服务器主机状态(每10分钟执行一次检查).
	 *
	 * @返回：void
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年7月6日 下午9:19:14
	 */
	@Scheduled(cron = "0 0/10 * * * ?")
	public void checkServerStatus() {
		Where<DevServer> where = new Where<DevServer>();
		where.eq("is_active", 1);
		List<DevServer> serverList = devServerService.selectList(where);
		for (DevServer devServer : serverList) {
			ServerConfig config = new ServerConfig();
			config.setHost(devServer.getServerIp());
			config.setPort(devServer.getServerPort());
			config.setTimeOut(devServer.getTimeOut());
			config.setUserName(devServer.getUserName());
			config.setUserPwd(devServer.getUserPwd());
			Session session = null;
			session = SshUtil.getSession(config);
			if (session != null) {
				session.disconnect();
				devServer.setServerStatus(1);
				devServerService.updateById(devServer);
				logger.info("主机" + devServer.getServerIp() + ":" + devServer.getServerPort() + "正常");
			} else {
				devServer.setServerStatus(2);
				devServerService.updateById(devServer);
				logger.info("主机" + devServer.getServerIp() + ":" + devServer.getServerPort() + "异常");
			}
		}

	}
}
