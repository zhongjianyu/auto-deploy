package auto.deploy.gitlab.service.impl;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.gitlab.api.GitlabAPI;
import org.gitlab.api.models.GitlabBranch;
import org.gitlab.api.models.GitlabGroup;
import org.gitlab.api.models.GitlabProject;
import org.gitlab.api.models.GitlabUser;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import auto.deploy.dao.entity.aut.AutUser;
import auto.deploy.dao.entity.dev.DevProject;
import auto.deploy.dao.entity.dev.DevProjectGroup;
import auto.deploy.gitlab.GitlabFactory;
import auto.deploy.gitlab.service.GitlabService;

/**
 * 
 * @描述：gitlab服务实现
 *
 * @作者：zhongjy
 *
 * @时间：2017年6月10日 下午5:22:12
 */
@Service
public class GitlabServiceImpl implements GitlabService {

	/**
	 * 读取配置文件
	 */
	@Resource
	private Environment environment;

	@Override
	public void addUser(AutUser autUser) {
		GitlabAPI api = GitlabFactory.getInstance(environment).getApi();
		// 邮箱地址
		String email = autUser.getUserEmail();
		// 密码
		String password = autUser.getUserPwd();
		// 账号
		String username = autUser.getUserName();
		// 全名
		String fullName = autUser.getNickName();
		String skypeId = null;
		String linkedIn = null;
		String twitter = null;
		String website_url = null;
		// 允许创建项目数;
		Integer projects_limit = 100;
		// 扩展用户ID
		String extern_uid = autUser.getId().toString();
		// 扩展用户名
		String extern_provider_name = autUser.getUserName();
		String bio = null;
		// 是否管理员，管理员可以访问所有的项目和分组
		Boolean isAdmin = false;
		// 是否可创建分组
		Boolean can_create_group = false;
		// 是否跳过配置
		Boolean skip_confirmation = true;
		try {
			api.createUser(email, password, username, fullName, skypeId, linkedIn, twitter, website_url, projects_limit, extern_uid,
					extern_provider_name, bio, isAdmin, can_create_group, skip_confirmation);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delUser(AutUser autUser) {
		GitlabAPI api = GitlabFactory.getInstance(environment).getApi();
		try {
			List<GitlabUser> list = api.findUsers(autUser.getUserName());
			for (GitlabUser gitlabUser : list) {
				if (gitlabUser.getEmail().equals(autUser.getUserEmail()) && gitlabUser.getUsername().equals(autUser.getUserName())) {
					api.deleteUser(gitlabUser.getId());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public GitlabProject createProject(DevProject devProject) throws Exception {
		GitlabAPI api = GitlabFactory.getInstance(environment).getApi();
		GitlabGroup group = api.getGroup(devProject.getGitlabGroupId());
		GitlabProject project = api.createProjectForGroup(devProject.getProjectName(), group, devProject.getProjectDesc());
		return project;
	}

	@Override
	public void delProject(DevProject devProject) throws Exception {
		GitlabAPI api = GitlabFactory.getInstance(environment).getApi();
		api.deleteProject(devProject.getGitlabProjectId());
	}

	@Override
	public GitlabGroup createGroup(DevProjectGroup devProjectGroup) throws Exception {
		GitlabAPI api = GitlabFactory.getInstance(environment).getApi();
		GitlabGroup group = api.createGroup(devProjectGroup.getGroupName());
		return group;
	}

	@Override
	public void delGroup(DevProjectGroup devProjectGroup) throws Exception {
		GitlabAPI api = GitlabFactory.getInstance(environment).getApi();
		api.deleteGroup(devProjectGroup.getGitlabGroupId());
	}

	@Override
	public void addBranch(Integer projectId, String targetBranch, String sourceBranch) throws Exception {
		GitlabAPI api = GitlabFactory.getInstance(environment).getApi();
		api.createBranch(projectId, targetBranch, sourceBranch);
	}
	
	@Override
	public List<GitlabBranch> getProjectBranchList(Integer projectId) throws Exception {
		GitlabAPI api = GitlabFactory.getInstance(environment).getApi();
		return api.getBranches(projectId);
	}

}
