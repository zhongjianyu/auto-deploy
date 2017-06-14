package auto.deploy.websocket.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.Session;

import auto.deploy.websocket.object.ServerConfig;
import auto.deploy.websocket.util.SshUtil;

public class SshTest {

	private static final String USER = "dev";
	private static final String PASSWORD = "1qaz@WSX";
	private static final String HOST = "192.168.0.123";
	private static final int DEFAULT_SSH_PORT = 22;

	public static void main(String[] arg) {

		try {
			ServerConfig config = new ServerConfig();
			config.setHost(HOST);
			config.setPort(DEFAULT_SSH_PORT);
			config.setTimeOut(10000);
			config.setUserName(USER);
			config.setUserPwd(PASSWORD);

			Session session = SshUtil.getSession(config);

			String cmd = "ls";
			ChannelExec channelExec = (ChannelExec) session.openChannel("exec");
			channelExec.setCommand(cmd);
			channelExec.setInputStream(null);
			channelExec.setErrStream(System.err);
			InputStream in = channelExec.getInputStream();
			channelExec.connect();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String line;
			try {
				while ((line = reader.readLine()) != null) {
					// 将实时日志通过WebSocket发送给客户端，给每一行添加一个HTML换行
					System.out.println(line);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				channelExec.disconnect();
				in.close();
				session.disconnect();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
