package auto.deploy.dao.entity.dev;

import auto.deploy.dao.entity.Entity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
 * 
 * @描述：项目参与者表(实体类).
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-07-16
 */
public class DevProjectActor extends Entity<DevProjectActor> {

    private static final long serialVersionUID = 1L;

    /**
     * 参与者ID
     */
	@JsonSerialize(using = ToStringSerializer.class)
	private Long userId;
    /**
     * 参与者账号
     */
	private String userName;
    /**
     * 项目ID
     */
	@JsonSerialize(using = ToStringSerializer.class)
	private Long projectId;
    /**
     * 项目名称
     */
	private String projectName;
    /**
     * 是否激活
     */
	private Integer isActive;
    /**
     * 项目阶段：1-开发人员，2-开发测试，3-验收测试，4-预发审批，5-生产审批
     */
	private Integer projectStage;


	public Long getUserId() {
		return userId;
	}

	public DevProjectActor setUserId(Long userId) {
		this.userId = userId;
		return this;
	}

	public String getUserName() {
		return userName;
	}

	public DevProjectActor setUserName(String userName) {
		this.userName = userName;
		return this;
	}

	public Long getProjectId() {
		return projectId;
	}

	public DevProjectActor setProjectId(Long projectId) {
		this.projectId = projectId;
		return this;
	}

	public String getProjectName() {
		return projectName;
	}

	public DevProjectActor setProjectName(String projectName) {
		this.projectName = projectName;
		return this;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public DevProjectActor setIsActive(Integer isActive) {
		this.isActive = isActive;
		return this;
	}

	public Integer getProjectStage() {
		return projectStage;
	}

	public DevProjectActor setProjectStage(Integer projectStage) {
		this.projectStage = projectStage;
		return this;
	}

}
