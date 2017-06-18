package auto.deploy.dao.entity.dev;

import auto.deploy.dao.entity.Entity;

/**
 * 
 * @描述：服务器表(实体类).
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-06-17
 */
public class DevServer extends Entity<DevServer> {

    private static final long serialVersionUID = 1L;

    /**
     * 主机IP/域名
     */
	private String serverIp;
    /**
     * 登录账号
     */
	private String userName;
    /**
     * 登录密码
     */
	private String userPwd;
    /**
     * 端口号
     */
	private Integer serverPort;
    /**
     * 超时毫秒数
     */
	private Integer timeOut;
    /**
     * 主机描述
     */
	private String serverDesc;
    /**
     * 服务器状态:1-正常，2-异常
     */
	private Integer serverStatus;
    /**
     * 是否激活
     */
	private Integer isActive;


	public String getServerIp() {
		return serverIp;
	}

	public DevServer setServerIp(String serverIp) {
		this.serverIp = serverIp;
		return this;
	}

	public String getUserName() {
		return userName;
	}

	public DevServer setUserName(String userName) {
		this.userName = userName;
		return this;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public DevServer setUserPwd(String userPwd) {
		this.userPwd = userPwd;
		return this;
	}

	public Integer getServerPort() {
		return serverPort;
	}

	public DevServer setServerPort(Integer serverPort) {
		this.serverPort = serverPort;
		return this;
	}

	public Integer getTimeOut() {
		return timeOut;
	}

	public DevServer setTimeOut(Integer timeOut) {
		this.timeOut = timeOut;
		return this;
	}

	public String getServerDesc() {
		return serverDesc;
	}

	public DevServer setServerDesc(String serverDesc) {
		this.serverDesc = serverDesc;
		return this;
	}

	public Integer getServerStatus() {
		return serverStatus;
	}

	public DevServer setServerStatus(Integer serverStatus) {
		this.serverStatus = serverStatus;
		return this;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public DevServer setIsActive(Integer isActive) {
		this.isActive = isActive;
		return this;
	}

}
