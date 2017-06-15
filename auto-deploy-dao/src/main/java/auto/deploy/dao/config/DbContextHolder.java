package auto.deploy.dao.config;

/**
 * 
 * @描述：数据库上下文(线程安全).
 *
 * @作者：zhongjy
 * 
 * @时间：2017年6月15日 下午3:18:32
 */
public class DbContextHolder {

	private static final ThreadLocal contextHolder = new ThreadLocal<>();

	/**
	 * 
	 * @描述：设置数据源
	 *
	 * @返回：void
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年6月15日 下午3:15:04
	 */
	public static void setDbType(DBTypeEnum dbTypeEnum) {
		contextHolder.set(dbTypeEnum.getValue());
	}

	/**
	 * 
	 * @描述：取得当前数据源
	 *
	 * @返回：String
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年6月15日 下午3:15:10
	 */
	public static Object getDbType() {
		return contextHolder.get();
	}

	/**
	 * 
	 * @描述：清除上下文数据
	 *
	 * @返回：void
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年6月15日 下午3:15:17
	 */
	public static void clearDbType() {
		contextHolder.remove();
	}
}
