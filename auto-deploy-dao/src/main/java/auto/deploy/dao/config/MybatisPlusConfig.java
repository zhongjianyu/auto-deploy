package auto.deploy.dao.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.tomcat.jdbc.pool.PoolConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.MybatisConfiguration;
import com.baomidou.mybatisplus.MybatisXMLLanguageDriver;
import com.baomidou.mybatisplus.entity.GlobalConfiguration;
import com.baomidou.mybatisplus.enums.DBType;
import com.baomidou.mybatisplus.mapper.LogicSqlInjector;
import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import com.baomidou.mybatisplus.toolkit.DruidUtils;

/**
 * 
 * @描述：mybatisplus配置
 *
 * @作者：zhongjy
 *
 * @时间：2017年4月24日 下午12:26:23
 */
@Configuration
@MapperScan("auto.deploy.dao.mapper*")
@EnableConfigurationProperties(MybatisProperties.class)
public class MybatisPlusConfig {
	@Autowired
	private DataSource dataSource;

	@Autowired
	private MybatisProperties properties;

	@Autowired
	private ResourceLoader resourceLoader = new DefaultResourceLoader();

	@Autowired(required = false)
	private Interceptor[] interceptors;

	@Autowired(required = false)
	private DatabaseIdProvider databaseIdProvider;

	/**
	 * 
	 * @描述：mybatis-plus分页插件
	 *
	 * @返回：PaginationInterceptor
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年4月24日 下午12:26:37
	 */
	@Bean
	public PaginationInterceptor paginationInterceptor() {
		PaginationInterceptor page = new PaginationInterceptor();
		page.setDialectType("mysql");
		page.setOverflowCurrent(true);
		return page;
	}

	/**
	 * 
	 * @描述：性能分析插件(生产环境要去掉本插件，直接注释掉即可).
	 *
	 * @返回：PerformanceInterceptor
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年4月24日 下午12:26:37
	 */
	@Bean
	public PerformanceInterceptor performanceInterceptor() {
		PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
		// SQL 执行最大时长(毫秒)，超过自动停止运行
		performanceInterceptor.setMaxTime(500);
		// SQL是否格式化，默认false
		performanceInterceptor.setFormat(true);
		return performanceInterceptor;
	}

	/**
	 * 
	 * @描述：这里全部使用mybatis-autoconfigure 已经自动加载的资源。不手动指定 配置文件和mybatis-boot的配置文件同步
	 *
	 * @返回：MybatisSqlSessionFactoryBean
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年4月24日 下午12:26:53
	 */
	@Bean
	public MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean() {
		MybatisSqlSessionFactoryBean mybatisPlus = new MybatisSqlSessionFactoryBean();
		mybatisPlus.setDataSource(dataSource);
		mybatisPlus.setVfs(SpringBootVFS.class);
		if (StringUtils.hasText(this.properties.getConfigLocation())) {
			mybatisPlus.setConfigLocation(this.resourceLoader.getResource(this.properties.getConfigLocation()));
		}
		mybatisPlus.setConfiguration(properties.getConfiguration());
		if (!ObjectUtils.isEmpty(this.interceptors)) {
			mybatisPlus.setPlugins(this.interceptors);
		}
		// MP 全局配置，更多内容进入类看注释
		GlobalConfiguration globalConfig = new GlobalConfiguration();
		globalConfig.setDbType(DBType.MYSQL.name());// 数据库类型
		// ID 策略 AUTO->`0`("数据库ID自增") INPUT->`1`(用户输入ID")
		// ID_WORKER->`2`("全局唯一ID") UUID->`3`("全局唯一ID")
		globalConfig.setIdType(2);
		// MP 属性下划线 转 驼峰 , 如果原生配置 mc.setMapUnderscoreToCamelCase(true)
		// 开启，该配置可以无。
		globalConfig.setDbColumnUnderline(true);
		// 自定义填充字段
		MetaObjectHandler metaObjectHandler = new MetaObjectHandlerImpl();
		globalConfig.setMetaObjectHandler(metaObjectHandler);

		// 逻辑删除配置
		LogicSqlInjector logicSqlInjector = new LogicSqlInjector();
		globalConfig.setSqlInjector(logicSqlInjector);
		// 逻辑删除全局值
		globalConfig.setLogicDeleteValue("1");
		// 逻辑未删除全局值
		globalConfig.setLogicNotDeleteValue("0");

		mybatisPlus.setGlobalConfig(globalConfig);
		MybatisConfiguration mc = new MybatisConfiguration();
		// 对于完全自定义的mapper需要加此项配置，才能实现下划线转驼峰
		mc.setMapUnderscoreToCamelCase(true);
		mc.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
		mybatisPlus.setConfiguration(mc);
		if (this.databaseIdProvider != null) {
			mybatisPlus.setDatabaseIdProvider(this.databaseIdProvider);
		}
		if (StringUtils.hasLength(this.properties.getTypeAliasesPackage())) {
			mybatisPlus.setTypeAliasesPackage(this.properties.getTypeAliasesPackage());
		}
		if (StringUtils.hasLength(this.properties.getTypeHandlersPackage())) {
			mybatisPlus.setTypeHandlersPackage(this.properties.getTypeHandlersPackage());
		}
		if (!ObjectUtils.isEmpty(this.properties.resolveMapperLocations())) {
			mybatisPlus.setMapperLocations(this.properties.resolveMapperLocations());
		}
		return mybatisPlus;
	}

	/**
	 * 
	 * @描述：动态数据源
	 *
	 * @返回：DynamicDataSource
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年6月15日 下午3:21:43
	 */
	/*
	 * @Bean public DynamicDataSource dynamicDataSource() { DynamicDataSource
	 * dynamicDataSource = new DynamicDataSource(); // 目标数据源 Map<Object, Object>
	 * map = new HashMap<Object, Object>();
	 * dynamicDataSource.setTargetDataSources(map); // 默认数据源
	 * dynamicDataSource.setDefaultTargetDataSource(dataSource); return null; }
	 */
	// https://github.com/alibaba/druid/wiki/%E9%85%8D%E7%BD%AE_DruidDataSource%E5%8F%82%E8%80%83%E9%85%8D%E7%BD%AE
	public DataSource getDataSource() {
		DruidDataSource ds = new DruidDataSource();
		/**
		 * 基本属性 url、user、password
		 */
		ds.setUrl(null);
		ds.setUsername(username);
		ds.setPassword(password);
		/**
		 * 配置初始化大小、最小、最大
		 */
		ds.setInitialSize(initialSize);
		ds.setMinIdle(value);
		ds.setMaxActive(maxActive);
		/**
		 * 配置获取连接等待超时的时间
		 */
		ds.setMaxWait(maxWaitMillis);
		/**
		 * 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
		 */
		ds.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
		/**
		 * 配置一个连接在池中最小生存的时间，单位是毫秒
		 */
		ds.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
		
		ds.setValidationQuery("SELECT 'x'");
		ds.setTestWhileIdle(true);
		ds.setTestOnBorrow(false);
		ds.setTestOnReturn(false);
		
		/**
		 * 打开PSCache，并且指定每个连接上PSCache的大小
		 */
		ds.setPoolPreparedStatements(false);
		ds.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
		/**
		 * 配置监控统计拦截的filters
		 */
		ds.setFilters("stat");
		return ds;
	}

}
