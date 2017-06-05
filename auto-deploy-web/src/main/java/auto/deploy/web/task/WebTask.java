package auto.deploy.web.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 
 * @描述：异步线程类
 *
 * @作者：zhongjy
 *
 * @时间：2017年6月5日 下午7:01:17
 */
@Component
public class WebTask {

	private final static Logger logger = LoggerFactory.getLogger(WebTask.class);

	/**
	 * 
	 * @描述：日志处理线程
	 *
	 * @返回：void
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年6月5日 下午7:08:59
	 */
	@Async("taskAsyncPool")
	public void webLogTask(int i) throws InterruptedException {
		logger.info("Task000001----" + i + " started.");
	}

	@Async("taskAsyncPool")
	public void webLogTask2(int i) throws InterruptedException {
		logger.info("Task000002----" + i + " started.");
	}

}
