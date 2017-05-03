package auto.deploy.dao.entity;

import auto.deploy.dao.entity.Entity;

/**
 * 
 * @描述：用户表(实体类)
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-05-03
 */
public class AutUser extends Entity<AutUser> {

    private static final long serialVersionUID = 1L;

    /**
     * 登录密码
     */
	private String userPwd;
    /**
     * 用户名
     */
	private String userName;


	public String getUserPwd() {
		return userPwd;
	}

	public AutUser setUserPwd(String userPwd) {
		this.userPwd = userPwd;
		return this;
	}

	public String getUserName() {
		return userName;
	}

	public AutUser setUserName(String userName) {
		this.userName = userName;
		return this;
	}

}
