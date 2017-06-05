package auto.deploy.web.pool;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 
 * @描述：创建线程池
 *
 * @作者：zhongjy
 *
 * @时间：2017年6月5日 下午6:46:49
 */
@Configuration
@EnableAsync
public class TaskExecutePool {

	@Resource
	private TaskThreadPoolConfig config;

	/**
	 * 
	 * @描述：创建线程池bean
	 *
	 * @返回：Executor
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年6月5日 下午6:54:02
	 */
	@Bean
	public Executor taskAsyncPool() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(config.getCorePoolSize());
		executor.setMaxPoolSize(config.getMaxPoolSize());
		executor.setQueueCapacity(config.getQueueCapacity());
		executor.setKeepAliveSeconds(config.getKeepAliveSeconds());
		executor.setThreadNamePrefix("Auto-Deploy_Executor-");
		// 拒绝策略：当pllo达到最大值时,不在新线程中执行任务,由调用者所在的线程来执行。
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		executor.initialize();
		return executor;
	}

}
