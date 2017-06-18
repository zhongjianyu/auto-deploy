package auto.deploy.dao.entity.dev;

import auto.deploy.dao.entity.Entity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
 * 
 * @描述：项目部署表(实体类).
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-06-17
 */
public class DevDeploy extends Entity<DevDeploy> {

    private static final long serialVersionUID = 1L;

    /**
     * 分支名称
     */
	private String branchName;
    /**
     * 项目ID
     */
	@JsonSerialize(using = ToStringSerializer.class)
	private Long projectId;
    /**
     * 项目名称
     */
	private String projectName;


	public String getBranchName() {
		return branchName;
	}

	public DevDeploy setBranchName(String branchName) {
		this.branchName = branchName;
		return this;
	}

	public Long getProjectId() {
		return projectId;
	}

	public DevDeploy setProjectId(Long projectId) {
		this.projectId = projectId;
		return this;
	}

	public String getProjectName() {
		return projectName;
	}

	public DevDeploy setProjectName(String projectName) {
		this.projectName = projectName;
		return this;
	}

}
