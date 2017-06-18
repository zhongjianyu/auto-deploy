package auto.deploy.dao.entity.dev;

import auto.deploy.dao.entity.Entity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
 * 
 * @描述：项目表(实体类).
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-06-18
 */
public class DevProject extends Entity<DevProject> {

    private static final long serialVersionUID = 1L;

    /**
     * 项目名称
     */
	private String projectName;
    /**
     * 项目描述
     */
	private String projectDesc;
    /**
     * ssh地址
     */
	private String sshLink;
    /**
     * 项目分组ID
     */
	@JsonSerialize(using = ToStringSerializer.class)
	private Long groupId;
    /**
     * 项目分组名称
     */
	private String groupName;
    /**
     * gitlab分组ID
     */
	private Integer gitlabGroupId;
    /**
     * gitlab项目ID
     */
	private Integer gitlabProjectId;
    /**
     * 是否激活
     */
	private Integer isActive;


	public String getProjectName() {
		return projectName;
	}

	public DevProject setProjectName(String projectName) {
		this.projectName = projectName;
		return this;
	}

	public String getProjectDesc() {
		return projectDesc;
	}

	public DevProject setProjectDesc(String projectDesc) {
		this.projectDesc = projectDesc;
		return this;
	}

	public String getSshLink() {
		return sshLink;
	}

	public DevProject setSshLink(String sshLink) {
		this.sshLink = sshLink;
		return this;
	}

	public Long getGroupId() {
		return groupId;
	}

	public DevProject setGroupId(Long groupId) {
		this.groupId = groupId;
		return this;
	}

	public String getGroupName() {
		return groupName;
	}

	public DevProject setGroupName(String groupName) {
		this.groupName = groupName;
		return this;
	}

	public Integer getGitlabGroupId() {
		return gitlabGroupId;
	}

	public DevProject setGitlabGroupId(Integer gitlabGroupId) {
		this.gitlabGroupId = gitlabGroupId;
		return this;
	}

	public Integer getGitlabProjectId() {
		return gitlabProjectId;
	}

	public DevProject setGitlabProjectId(Integer gitlabProjectId) {
		this.gitlabProjectId = gitlabProjectId;
		return this;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public DevProject setIsActive(Integer isActive) {
		this.isActive = isActive;
		return this;
	}

}
