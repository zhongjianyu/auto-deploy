package auto.deploy.gitlab;

import java.util.List;

import javax.annotation.Resource;

import org.gitlab.api.GitlabAPI;
import org.gitlab.api.models.GitlabProject;
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

	@Test
	public void testGitlabApi() throws Exception {
		GitlabAPI api = GitlabFactory.getInstance(environment).getApi();

		// api.createProject("test-project");

		List<GitlabProject> projectList = api.getAllProjects();
		for (GitlabProject gitlabProject : projectList) {
			System.out.println(gitlabProject.getName());
		}
		System.out.println(123);
		System.out.println(environment.getProperty("gitlab.url"));
		GitlabAPI api2 = GitlabFactory.getInstance(environment).getApi();
		System.out.println(api == api2);
		// System.out.println(SpringContextUtil.getBean(Environment.class));
	}

}
