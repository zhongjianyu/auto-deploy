package auto.deploy.gitlab;

import java.io.IOException;

import org.gitlab.api.GitlabAPI;
import org.gitlab.api.models.GitlabSession;
import org.springframework.core.env.Environment;

/**
 * 
 * @描述：gitlabApi工厂类(单例)
 *
 * @作者：zhongjy
 *
 * @时间：2017年5月6日 下午11:58:13
 */
public class GitlabFactory {

	public volatile static GitlabFactory instance = null;

	private Object api;

	public static GitlabFactory getInstance(Environment env) {

		if (instance == null) {
			synchronized (GitlabFactory.class) {
				if (instance == null) {
					instance = new GitlabFactory(env);
				}
			}
		}
		return instance;
	}

	private GitlabFactory(Environment env) {
		final GitlabSession session;
		try {
			session = GitlabAPI.connect(env.getProperty("gitlab.url"), env.getProperty("gitlab.user"),
					env.getProperty("gitlab.password"));
			String privateToken = session.getPrivateToken();
			api = GitlabAPI.connect(env.getProperty("gitlab.url"), privateToken);
		} catch (IOException e) {
			api = e;
			e.printStackTrace();
		}
	}

	public GitlabAPI getApi() {
		return (GitlabAPI) api;
	}
}
