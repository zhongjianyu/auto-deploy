package auto.deploy.dao.entity.dev;

import auto.deploy.dao.entity.Entity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
 * 
 * @描述：项目分支表(实体类).
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-06-17
 */
public class DevBranch extends Entity<DevBranch> {

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
    /**
     * 是否部署成功：1-成功,1-失败
     */
	private Integer isDeploySuccess;
    /**
     * 项目状态：0-停止,1-运行,2-部署中
     */
	private Integer projectStatus;


	public String getBranchName() {
		return branchName;
	}

	public DevBranch setBranchName(String branchName) {
		this.branchName = branchName;
		return this;
	}

	public Long getProjectId() {
		return projectId;
	}

	public DevBranch setProjectId(Long projectId) {
		this.projectId = projectId;
		return this;
	}

	public String getProjectName() {
		return projectName;
	}

	public DevBranch setProjectName(String projectName) {
		this.projectName = projectName;
		return this;
	}

	public Integer getIsDeploySuccess() {
		return isDeploySuccess;
	}

	public DevBranch setIsDeploySuccess(Integer isDeploySuccess) {
		this.isDeploySuccess = isDeploySuccess;
		return this;
	}

	public Integer getProjectStatus() {
		return projectStatus;
	}

	public DevBranch setProjectStatus(Integer projectStatus) {
		this.projectStatus = projectStatus;
		return this;
	}

}
