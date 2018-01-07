package auto.deploy.web.task;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import auto.deploy.activiti.service.ActivitiService;

//告诉Junit运行使用Spring 的单元测试支持
@RunWith(SpringRunner.class)
// 带有Spring Boot支持的引导程序
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ActivitiTest {

	@Resource
	private ActivitiService activitiService;

	@Test
	public void deployTest() throws Exception {
		activitiService.doDeploy("ItProjectDevelop");
	}

}