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
    /**
     * 是否激活
     */
	private Integer isActive;
    /**
     * 账号是否过期
     */
	private Integer isAccountExpired;
    /**
     * 账号是否被锁
     */
	private Integer isAccountLocked;
    /**
     * 证书是否过期
     */
	private Integer isCredentialsExpired;


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

	public Integer getIsActive() {
		return isActive;
	}

	public AutUser setIsActive(Integer isActive) {
		this.isActive = isActive;
		return this;
	}

	public Integer getIsAccountExpired() {
		return isAccountExpired;
	}

	public AutUser setIsAccountExpired(Integer isAccountExpired) {
		this.isAccountExpired = isAccountExpired;
		return this;
	}

	public Integer getIsAccountLocked() {
		return isAccountLocked;
	}

	public AutUser setIsAccountLocked(Integer isAccountLocked) {
		this.isAccountLocked = isAccountLocked;
		return this;
	}

	public Integer getIsCredentialsExpired() {
		return isCredentialsExpired;
	}

	public AutUser setIsCredentialsExpired(Integer isCredentialsExpired) {
		this.isCredentialsExpired = isCredentialsExpired;
		return this;
	}

}
