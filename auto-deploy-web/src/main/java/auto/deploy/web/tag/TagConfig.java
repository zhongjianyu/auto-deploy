package auto.deploy.web.tag;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import auto.deploy.service.aut.AutWidgetRoleService;

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

	@Bean
	public FreeMarkerConfigurer freeMarkerConfigurer() {
		FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("a", new A(autWidgetRoleService));
		freeMarkerConfigurer.setFreemarkerVariables(map);
		freeMarkerConfigurer.setTemplateLoaderPath("classpath:/templates");
		return freeMarkerConfigurer;
	}
}
