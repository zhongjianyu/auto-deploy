package auto.deploy.dao.entity.sys;

import java.util.Date;
import auto.deploy.dao.entity.Entity;

/**
 * 
 * @描述：操作日志表(实体类).
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-05-24
 */
public class SysOperateLog extends Entity<SysOperateLog> {

    private static final long serialVersionUID = 1L;

    /**
     * 操作用户ID
     */
	private Long operateUserId;
    /**
     * 操作用户账号
     */
	private String operateUserName;
    /**
     * 操作时间
     */
	private Date operateTime;
    /**
     * 操作日志名称
     */
	private String operateLogName;
    /**
     * 操作类名
     */
	private String className;
    /**
     * 方法名
     */
	private String methodName;
    /**
     * 操作明细说明
     */
	private String operateDetailDesc;


	public Long getOperateUserId() {
		return operateUserId;
	}

	public SysOperateLog setOperateUserId(Long operateUserId) {
		this.operateUserId = operateUserId;
		return this;
	}

	public String getOperateUserName() {
		return operateUserName;
	}

	public SysOperateLog setOperateUserName(String operateUserName) {
		this.operateUserName = operateUserName;
		return this;
	}

	public Date getOperateTime() {
		return operateTime;
	}

	public SysOperateLog setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
		return this;
	}

	public String getOperateLogName() {
		return operateLogName;
	}

	public SysOperateLog setOperateLogName(String operateLogName) {
		this.operateLogName = operateLogName;
		return this;
	}

	public String getClassName() {
		return className;
	}

	public SysOperateLog setClassName(String className) {
		this.className = className;
		return this;
	}

	public String getMethodName() {
		return methodName;
	}

	public SysOperateLog setMethodName(String methodName) {
		this.methodName = methodName;
		return this;
	}

	public String getOperateDetailDesc() {
		return operateDetailDesc;
	}

	public SysOperateLog setOperateDetailDesc(String operateDetailDesc) {
		this.operateDetailDesc = operateDetailDesc;
		return this;
	}

}
