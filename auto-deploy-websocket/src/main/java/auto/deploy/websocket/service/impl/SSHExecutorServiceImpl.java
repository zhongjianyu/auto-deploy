package auto.deploy.websocket.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.Session;

import auto.deploy.websocket.WebSocketMsg;
import auto.deploy.websocket.object.ServerConfig;
import auto.deploy.websocket.service.SSHExecutorService;
import auto.deploy.websocket.service.WebSocketService;
import auto.deploy.websocket.util.SshUtil;

/**
 * 
 * @描述：ssh执行器(实现).
 *
 * @作者：zhongjy
 * 
 * @时间：2017年6月14日 下午4:41:36
 */
@Service
public class SSHExecutorServiceImpl implements SSHExecutorService {

	@Resource
	private WebSocketService webSocketService;

	@Override
	public void tailLog(String cmd, String userName, ServerConfig config) {
		Session session = null;
		ChannelExec channelExec = null;
		InputStream in = null;
		try {
			session = SshUtil.getSession(config);
			channelExec = (ChannelExec) session.openChannel("exec");
			channelExec.setCommand(cmd);
			channelExec.setInputStream(null);
			channelExec.setErrStream(System.err);
			in = channelExec.getInputStream();
			channelExec.connect();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String line;
			WebSocketMsg msg = new WebSocketMsg();
			msg.setCode(0);
			while ((line = reader.readLine()) != null) {
				// 将实时日志通过WebSocket发送给客户端，给每一行添加一个HTML换行
				msg.setMessage(line);
				webSocketService.pushMessageToUser(msg, userName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (channelExec != null) {
				channelExec.disconnect();
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (session != null) {
				session.disconnect();
			}
		}

	}

}
