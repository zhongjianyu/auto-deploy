package auto.deploy.activiti;

import javax.annotation.Resource;

import org.activiti.engine.DynamicBpmnService;
import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import auto.deploy.dao.config.DynamicDataSource;

/**
 * 
 * @描述：流程配置
 *
 * @作者：zhongjy
 *
 * @时间：2017年6月10日 上午10:54:49
 */
@Configuration
public class ActivitiConfig {

	/**
	 * 动态数据源
	 */
	@Resource
	private DynamicDataSource dataSource;
	/**
	 * 事务管理器
	 */
	@Resource
	private DataSourceTransactionManager dataSourceTransactionManager;

	@Bean
	public ProcessEngine getProcessEngine() {
		/**
		 * 获取流程引擎配置
		 */
		//ProcessEngineConfiguration pec = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
		SpringProcessEngineConfiguration pec = new SpringProcessEngineConfiguration();
		pec.setDataSource(dataSource);

		/**
		 * 设置事务管理器(使用spring事务管理器)
		 */
		pec.setTransactionManager(dataSourceTransactionManager);
		/**
		 * 配置驱动
		 */
		// pec.setJdbcDriver(environment.getProperty("spring.datasource.driver-class-name"));
		/**
		 * 配置连接地址
		 */
		// pec.setJdbcUrl(environment.getProperty("spring.datasource.url"));
		/**
		 * 用户名
		 */
		// pec.setJdbcUsername(environment.getProperty("spring.datasource.username"));
		/**
		 * 密码
		 */
		// pec.setJdbcPassword(environment.getProperty("spring.datasource.password"));

		/**
		 * 配置模式 true 自动创建和更新表
		 */
		pec.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

		/**
		 * 流程图片乱码问题
		 */
		pec.setActivityFontName("宋体");
		pec.setLabelFontName("宋体");

		/**
		 * 获取流程引擎对象
		 */
		ProcessEngine pe = pec.buildProcessEngine();

		return pe;
	}

	/**
	 * 
	 * @描述：动态Bpmn服务
	 *
	 * @返回：DynamicBpmnService
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年6月10日 下午2:58:32
	 */
	@Bean
	public DynamicBpmnService getDynamicBpmnService() {
		return getProcessEngine().getDynamicBpmnService();
	}

	/**
	 * 
	 * @描述：表单服务
	 *
	 * @返回：FormService
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年6月10日 下午2:58:20
	 */
	@Bean
	public FormService getFormService() {
		return getProcessEngine().getFormService();
	}

	/**
	 * 
	 * @描述：历史服务
	 *
	 * @返回：HistoryService
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年6月10日 下午2:58:11
	 */
	@Bean
	public HistoryService getHistoryService() {
		return getProcessEngine().getHistoryService();
	}

	/**
	 * 
	 * @描述：身份服务
	 *
	 * @返回：IdentityService
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年6月10日 下午2:57:59
	 */
	@Bean
	public IdentityService getIdentityService() {
		return getProcessEngine().getIdentityService();
	}

	/**
	 * 
	 * @描述：管理服务
	 *
	 * @返回：ManagementService
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年6月10日 下午2:57:51
	 */
	@Bean
	public ManagementService getManagementService() {
		return getProcessEngine().getManagementService();
	}

	/**
	 * 
	 * @描述：仓库服务
	 *
	 * @返回：RepositoryService
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年6月10日 下午2:57:06
	 */
	@Bean
	public RepositoryService getRepositoryService() {
		return getProcessEngine().getRepositoryService();
	}

	/**
	 * 
	 * @描述：运行时服务
	 *
	 * @返回：RuntimeService
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年6月10日 下午2:56:48
	 */
	@Bean
	public RuntimeService getRuntimeService() {
		return getProcessEngine().getRuntimeService();
	}

	/**
	 * 
	 * @描述：任务服务
	 *
	 * @返回：TaskService
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年6月10日 下午2:56:33
	 */
	@Bean
	public TaskService getTaskService() {
		return getProcessEngine().getTaskService();
	}
}
