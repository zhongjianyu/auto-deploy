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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

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
	 * 读取配置文件
	 */
	@Resource
	private Environment environment;

	@Bean
	public ProcessEngine getProcessEngine() {
		/**
		 * 获取流程引擎配置
		 */
		ProcessEngineConfiguration pec = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
		/**
		 * 配置驱动
		 */
		pec.setJdbcDriver(environment.getProperty("spring.datasource.driver-class-name"));
		/**
		 * 配置连接地址
		 */
		pec.setJdbcUrl(environment.getProperty("spring.datasource.url"));
		/**
		 * 用户名
		 */
		pec.setJdbcUsername(environment.getProperty("spring.datasource.username"));
		/**
		 * 密码
		 */
		pec.setJdbcPassword(environment.getProperty("spring.datasource.password"));

		/**
		 * 配置模式 true 自动创建和更新表
		 */
		pec.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

		/**
		 * 获取流程引擎对象
		 */
		ProcessEngine pe = pec.buildProcessEngine();

		return pe;
	}

	@Bean
	public DynamicBpmnService getDynamicBpmnService() {
		return getProcessEngine().getDynamicBpmnService();
	}

	@Bean
	public FormService getFormService() {
		return getProcessEngine().getFormService();
	}

	@Bean
	public HistoryService getHistoryService() {
		return getProcessEngine().getHistoryService();
	}

	@Bean
	public IdentityService getIdentityService() {
		return getProcessEngine().getIdentityService();
	}

	@Bean
	public ManagementService getManagementService() {
		return getProcessEngine().getManagementService();
	}

	@Bean
	public RepositoryService getRepositoryService() {
		return getProcessEngine().getRepositoryService();
	}

	@Bean
	public RuntimeService getRuntimeService() {
		return getProcessEngine().getRuntimeService();
	}

	@Bean
	public TaskService getTaskService() {
		return getProcessEngine().getTaskService();
	}
}
