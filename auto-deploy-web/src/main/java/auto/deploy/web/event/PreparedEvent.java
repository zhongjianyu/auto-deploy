package auto.deploy.web.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationListener;

/**
 * 
 * @描述：上下文创建完成[事件]
 *
 * @作者：zhongjy
 *
 * @时间：2017年4月29日 上午7:55:06
 */
public class PreparedEvent implements ApplicationListener<ApplicationPreparedEvent> {

	private final static Logger logger = LoggerFactory.getLogger(PreparedEvent.class);

	@Override
	public void onApplicationEvent(ApplicationPreparedEvent e) {
		logger.info("\n上下文创建完成[事件]：PreparedEvent");
	}

}
