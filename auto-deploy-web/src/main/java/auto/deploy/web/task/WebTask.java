package auto.deploy.web.task;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import auto.deploy.dao.entity.sys.SysOperateLog;
import auto.deploy.service.sys.SysOperateLogService;

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

	@Resource
	private SysOperateLogService sysOperateLogService;

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
	public void webLogTask(SysOperateLog sysOperateLog) throws InterruptedException {
		sysOperateLogService.insert(sysOperateLog);
		logger.info("操作日志记录成功...");
	}

}
