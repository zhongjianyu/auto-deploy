package auto.deploy.service.aut.impl;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import auto.deploy.service.aut.AutMenuService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AutMenuServiceImplTest {

	@Resource
	private AutMenuService autMenuService;

	@Test
	public void test() throws Exception {
		try {
			System.out.println(autMenuService.getNextCode(2, "000100", true));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
