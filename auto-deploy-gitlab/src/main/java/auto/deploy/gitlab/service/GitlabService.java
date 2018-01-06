package auto.deploy.gitlab.service;

import java.util.List;

import org.gitlab.api.models.GitlabBranch;
import org.gitlab.api.models.GitlabGroup;
import org.gitlab.api.models.GitlabProject;

import auto.deploy.dao.entity.aut.AutUser;
import auto.deploy.dao.entity.dev.DevProject;
import auto.deploy.dao.entity.dev.DevProjectGroup;

/**
 * 
 * @描述：gitlab服务接口
 *
 * @作者：zhongjy
 *
 * @时间：2017年6月10日 下午5:21:07
 */
public interface GitlabService {

	/**
	 * 
	 * @描述：添加用户
	 *
	 * @返回：void
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年6月10日 下午8:44:27
	 */
	public void addUser(AutUser autUser);

	/**
	 * 
	 * @描述：删除用户
	 *
	 * @返回：void
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年6月10日 下午8:07:03
	 */
	public void delUser(AutUser autUser);

	/**
	 * 
	 * @描述：创建项目
	 *
	 * @返回：GitlabProject
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年7月8日 下午3:22:33
	 */
	public GitlabProject createProject(DevProject devProject) throws Exception;

	/**
	 * 
	 * @描述：删除项目
	 *
	 * @返回：GitlabProject
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年7月8日 下午3:22:33
	 */
	public void delProject(DevProject devProject) throws Exception;

	/**
	 * 
	 * @描述：创建分组
	 *
	 * @返回：GitlabProject
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年7月8日 下午3:22:33
	 */
	public GitlabGroup createGroup(DevProjectGroup devProjectGroup) throws Exception;

	/**
	 * 
	 * @描述：删除分组
	 *
	 * @返回：GitlabProject
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年7月8日 下午3:22:33
	 */
	public void delGroup(DevProjectGroup devProjectGroup) throws Exception;

	/**
	 * 
	 * @描述：新建分支
	 *
	 * @返回：GitlabProject
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年7月8日 下午3:22:33
	 */
	public void addBranch(Integer projectId, String targetBranch, String sourceBranch) throws Exception;

	/**
	 * 
	 * @描述：获取项目的分支列表
	 *
	 * @返回：List<GitlabBranch>
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2018年1月6日 下午2:47:03
	 */
	List<GitlabBranch> getProjectBranchList(Integer projectId) throws Exception;

}
