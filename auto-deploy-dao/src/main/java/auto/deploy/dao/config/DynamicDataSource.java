package auto.deploy.dao.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 
 * @描述：动态数据源类
 *
 * @作者：zhongjy
 * 
 * @时间：2017年6月15日 下午3:02:19
 */

public class DynamicDataSource extends AbstractRoutingDataSource {
	/**
	 * 取得当前使用哪个数据源
	 */
	@Override
	protected Object determineCurrentLookupKey() {
		return DbContextHolder.getDbType();
	}

}
