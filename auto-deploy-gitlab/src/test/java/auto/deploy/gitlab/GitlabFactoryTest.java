package auto.deploy.gitlab;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.gitlab.api.GitlabAPI;
import org.gitlab.api.models.GitlabBranch;
import org.gitlab.api.models.GitlabGroup;
import org.gitlab.api.models.GitlabMergeRequest;
import org.gitlab.api.models.GitlabProject;
import org.gitlab.api.models.GitlabUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 
 * @描述：用户service测试类
 *
 * @作者：zhongjy
 *
 * @时间：2017年4月24日 下午12:28:57
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class GitlabFactoryTest {

	/**
	 * 读取配置文件
	 */
	@Resource
	private Environment environment;

	/**
	 * 
	 * @描述：创建用户
	 *
	 * @返回：void
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年6月9日 下午11:18:10
	 */
	@Test
	public void createUserTest() throws IOException {
		GitlabAPI api = GitlabFactory.getInstance(environment).getApi();
		String email = "949434438@qq.com";// 邮箱地址
		String password = "develop001";// 密码
		String username = "develop001";// 账号
		String fullName = "开发001";// 全名
		String skypeId = null;
		String linkedIn = null;
		String twitter = null;
		String website_url = null;
		Integer projects_limit = 13;// 允许创建项目数;
		String extern_uid = "121131328341575713";// 扩展用户ID
		String extern_provider_name = "develop001";// 扩展用户名
		String bio = null;
		Boolean isAdmin = false;// 是否管理员，管理员可以访问所有的项目和分组
		Boolean can_create_group = false;// 是否可创建分组
		Boolean skip_confirmation = true;// 是否跳过配置
		api.createUser(email, password, username, fullName, skypeId, linkedIn, twitter, website_url, projects_limit, extern_uid, extern_provider_name,
				bio, isAdmin, can_create_group, skip_confirmation);
	}

	/**
	 * 
	 * @描述：获取用户
	 *
	 * @返回：void
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年6月9日 下午11:18:10
	 */
	@Test
	public void getUserTest() throws IOException {
		GitlabAPI api = GitlabFactory.getInstance(environment).getApi();
		List<GitlabUser> list = api.getUsers();
		for (GitlabUser gitlabUser : list) {
			System.out.println(gitlabUser.getId());
			System.out.println(gitlabUser.getUsername());
			System.out.println(gitlabUser.getName());
			System.out.println(gitlabUser.getExternProviderName());
			System.out.println(gitlabUser.getExternUid());
			System.out.println(gitlabUser.getEmail());
			System.out.println(gitlabUser.getProjectsLimit());
			System.out.println("-------------------");
		}
	}

	/**
	 * 
	 * @描述：创建项目分组
	 *
	 * @返回：void
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年6月9日 下午11:18:10
	 */
	@Test
	public void createGroupTest() throws IOException {
		GitlabAPI api = GitlabFactory.getInstance(environment).getApi();
		api.createGroup("group001");
		api.createGroup("group002");
		api.createGroup("group003");
	}

	/**
	 * 
	 * @描述：查询项目分组
	 *
	 * @返回：void
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年6月9日 下午11:18:25
	 */
	@Test
	public void getGroupTest() throws IOException {
		GitlabAPI api = GitlabFactory.getInstance(environment).getApi();
		List<GitlabGroup> list = api.getGroups();
		for (GitlabGroup gitlabGroup : list) {
			System.out.println(gitlabGroup.getId());
			System.out.println(gitlabGroup.getName());
			System.out.println(gitlabGroup.getWebUrl());
		}
	}

	/**
	 * 
	 * @描述：创建项目
	 *
	 * @返回：void
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年6月9日 下午11:18:25
	 */
	@Test
	public void createProjectTest() throws IOException {
		GitlabAPI api = GitlabFactory.getInstance(environment).getApi();
		GitlabGroup group = api.getGroup(2);
		api.createProjectForGroup("project-a02", group, "项目-a02");
		api.createProjectForGroup("project-a03", group, "项目-a03");
		api.createProjectForGroup("project-a04", group, "项目-a04");
	}

	/**
	 * 
	 * @描述：查询项目
	 *
	 * @返回：void
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年6月9日 下午11:18:25
	 */
	@Test
	public void getProjectTest() throws IOException {
		GitlabAPI api = GitlabFactory.getInstance(environment).getApi();
		// 项目列表
		List<GitlabProject> projectList = api.getAllProjects();
		for (GitlabProject gitlabProject : projectList) {
			System.out.println("项目ID：" + gitlabProject.getId());
			System.out.println("创建者：" + api.getUser(gitlabProject.getCreatorId()).getUsername());
			System.out.println("项目名称：" + gitlabProject.getName() + "，SSH地址：" + gitlabProject.getSshUrl() + "，描述：" + gitlabProject.getDescription());
		}
		System.out.println("---------------------------");
		List<GitlabProject> projectList2 = api.getOwnedProjects();
		for (GitlabProject gitlabProject : projectList2) {
			System.out.println(gitlabProject.getName());
		}
	}

	/**
	 * 
	 * @描述：创建分支
	 *
	 * @返回：void
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年6月9日 下午11:18:25
	 */
	@Test
	public void createBranchTest() throws IOException {
		GitlabAPI api = GitlabFactory.getInstance(environment).getApi();
		// 最后一个参数是指定在项目的那个分支的基础上创建分支
		api.createBranch(2, "test-project_1.0.2", "test-project_1.0.1");
	}

	/**
	 * 
	 * @描述：查询分支
	 *
	 * @返回：void
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年6月9日 下午11:18:25
	 */
	@Test
	public void getBranchTest() throws IOException {
		GitlabAPI api = GitlabFactory.getInstance(environment).getApi();
		// 最后一个参数是指定在项目的那个分支的基础上创建分支
		// api.createBranch(2, "test-project_1.0.2", "test-project_1.0.1");
		List<GitlabBranch> list = api.getBranches(2);
		for (GitlabBranch gitlabBranch : list) {
			System.out.println(gitlabBranch.getName());
			// System.out.println(gitlabBranch.);
			System.out.println("--------------");
		}
	}

	/**
	 * 
	 * @描述：分支合并
	 *
	 * @返回：void
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年6月9日 下午11:18:25
	 */
	@Test
	public void mergeBranchTest() throws IOException {
		GitlabAPI api = GitlabFactory.getInstance(environment).getApi();
		
		// 受理人ID
		Integer assigneeId = 1;
		Integer projectId = 2;
		String sourceBranch = "test-project_1.0.1";
		String targetBranch = "master";
		GitlabMergeRequest request = api.createMergeRequest(projectId, sourceBranch, targetBranch, assigneeId,
				"项目test-project分支test-project_1.0.1合并到master");
		api.acceptMergeRequest(api.getProject(2), request.getId(), "项目test-project分支test-project_1.0.1合并到master");
	}

	

	@Test
	public void testGitlabApi() throws Exception {
		GitlabAPI api = GitlabFactory.getInstance(environment).getApi();
		// 创建项目组
		api.createGroup("group001");
		api.createGroup("group002");
		api.createGroup("group003");
		// 创建项目
		api.createProject("test-project-002");

		api.createGroup("");
		List<GitlabProject> projectList = api.getAllProjects();
		for (GitlabProject gitlabProject : projectList) {
			System.out.println("项目名称：" + gitlabProject.getName() + "，SSH地址：" + gitlabProject.getSshUrl() + "，创建者："
					+ gitlabProject.getOwner().getName() + "，" + gitlabProject.getDescription());
		}
		System.out.println(123);
		System.out.println(environment.getProperty("gitlab.url"));
		GitlabAPI api2 = GitlabFactory.getInstance(environment).getApi();
		System.out.println(api == api2);
		// System.out.println(SpringContextUtil.getBean(Environment.class));
	}

}
