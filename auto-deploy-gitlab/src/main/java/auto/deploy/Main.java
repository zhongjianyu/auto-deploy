package auto.deploy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import auto.deploy.util.SpringContextUtil;

/**
 * 
 * @描述：项目启动类
 *
 * @作者：zhongjy
 *
 * @时间：2017年4月21日 上午8:40:52
 */
@SpringBootApplication
public class Main {
	/**
	 * 
	 * @描述：项目启动类入口方法
	 *
	 * @返回：void
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年4月24日 下午12:31:32
	 */
	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(Main.class);
		// 启动
		ApplicationContext context = application.run(args);
		// 设置上下文到SpringContextUtil
		SpringContextUtil.setApplicationContext(context);
	}

}
