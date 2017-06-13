package auto.deploy.websocket.object;

/**
 * 
 * @描述：ssh连接服务器配置类
 *
 * @作者：zhongjy
 *
 * @时间：2017年6月13日 下午7:51:30
 */
public class ServerConfig {
	/**
	 * 登录账号
	 */
	private String userName;
	/**
	 * 登录密码
	 */
	private String userPwd;
	/**
	 * 登录主机IP
	 */
	private String host;
	/**
	 * 端口号
	 */
	private Integer port;
	/**
	 * 超时毫秒数
	 */
	private Integer timeOut;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public Integer getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(Integer timeOut) {
		this.timeOut = timeOut;
	}

}
