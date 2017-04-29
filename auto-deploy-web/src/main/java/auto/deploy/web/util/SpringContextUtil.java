package auto.deploy.web.util;

import org.springframework.context.ApplicationContext;

/**
 * 
 * @描述：spring上下文工具类
 *
 * @作者：zhongjy
 *
 * @时间：2017年4月29日 上午8:45:02
 */
public class SpringContextUtil {
	private static ApplicationContext applicationContext;

	/**
	 * 
	 * @描述：获取上下文
	 *
	 * @返回：ApplicationContext
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年4月29日 上午8:44:54
	 */
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * 
	 * @描述：设置上下文
	 *
	 * @返回：void
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年4月29日 上午8:45:37
	 */
	public static void setApplicationContext(ApplicationContext applicationContext) {
		SpringContextUtil.applicationContext = applicationContext;
	}

	/**
	 * 
	 * @描述：通过名字获取上下文中的bean
	 *
	 * @返回：Object
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年4月29日 上午8:45:53
	 */
	public static Object getBean(String name) {
		return applicationContext.getBean(name);
	}

	/**
	 * 
	 * @描述：通过类型获取上下文中的bean
	 *
	 * @返回：Object
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年4月29日 上午8:46:05
	 */
	public static Object getBean(Class<?> requiredType) {
		return applicationContext.getBean(requiredType);
	}
}
