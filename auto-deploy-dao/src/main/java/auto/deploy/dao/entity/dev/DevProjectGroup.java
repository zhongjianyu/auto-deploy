package auto.deploy.dao.entity.dev;

import auto.deploy.dao.entity.Entity;

/**
 * 
 * @描述：项目分组表(实体类).
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-06-17
 */
public class DevProjectGroup extends Entity<DevProjectGroup> {

    private static final long serialVersionUID = 1L;

    /**
     * 分组名称
     */
	private String groupName;
    /**
     * 分组描述
     */
	private String groupDesc;
    /**
     * 是否激活
     */
	private Integer isActive;
    /**
     * gitlab分组ID
     */
	private Integer gitlabGroupId;


	public String getGroupName() {
		return groupName;
	}

	public DevProjectGroup setGroupName(String groupName) {
		this.groupName = groupName;
		return this;
	}

	public String getGroupDesc() {
		return groupDesc;
	}

	public DevProjectGroup setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
		return this;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public DevProjectGroup setIsActive(Integer isActive) {
		this.isActive = isActive;
		return this;
	}

	public Integer getGitlabGroupId() {
		return gitlabGroupId;
	}

	public DevProjectGroup setGitlabGroupId(Integer gitlabGroupId) {
		this.gitlabGroupId = gitlabGroupId;
		return this;
	}

}
