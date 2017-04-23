package auto.deploy.service.authority;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import auto.deploy.dao.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserServiceTest {

	@Resource
	private UserService userService;

	@Test
	public void testMyInsert() {
		User user = new User();
		user.setName("张无忌");
		userService.myInsert(user);
	}

}
