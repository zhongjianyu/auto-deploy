package auto.deploy.web.tag;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import auto.deploy.dao.config.DynamicDataSource;
import auto.deploy.service.aut.AutWidgetRoleService;
import auto.deploy.service.sys.SysDataDictService;

/**
 * 
 * @描述：自定义标签配置
 *
 * @作者：zhongjy
 *
 * @时间：2017年5月21日 下午9:03:05
 */
@Configuration
public class TagConfig {

	@Resource
	private AutWidgetRoleService autWidgetRoleService;
	@Resource
	private SysDataDictService sysDataDictService;
	@Resource
	private DynamicDataSource dataSource;

	@Bean
	public FreeMarkerConfigurer freeMarkerConfigurer() {
		FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
		Map<String, Object> map = new HashMap<String, Object>();
		// 按钮标签
		map.put("a", new A(autWidgetRoleService));
		// 按钮标签
		map.put("button", new Button(autWidgetRoleService));
		// 下拉标签
		map.put("select", new Select(sysDataDictService));
		// 下拉标签
		map.put("selectTable", new SelectTable(dataSource));
		// li标签
		map.put("li", new Li(autWidgetRoleService));
		freeMarkerConfigurer.setFreemarkerVariables(map);
		freeMarkerConfigurer.setTemplateLoaderPath("classpath:/templates");
		return freeMarkerConfigurer;
	}
}
