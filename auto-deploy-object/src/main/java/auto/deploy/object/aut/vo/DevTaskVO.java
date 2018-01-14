package auto.deploy.object.aut.vo;

import auto.deploy.dao.entity.dev.DevBranch;
import auto.deploy.dao.entity.dev.DevProject;

/**
 * 
 * @描述：任务对象对象VO
 *
 * @作者：zhongjy
 *
 * @时间：2017年5月3日 下午5:08:24
 */
public final class DevTaskVO {

	private DevProject devProject;
	private DevBranch devBranch;

	public DevProject getDevProject() {
		return devProject;
	}

	public void setDevProject(DevProject devProject) {
		this.devProject = devProject;
	}

	public DevBranch getDevBranch() {
		return devBranch;
	}

	public void setDevBranch(DevBranch devBranch) {
		this.devBranch = devBranch;
	}

}
