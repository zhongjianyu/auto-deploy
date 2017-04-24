package auto.deploy.service.authority;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import auto.deploy.dao.entity.User;

/**
 * 
 * @描述：用户service测试类
 *
 * @作者：zhongjy
 *
 * @时间：2017年4月24日 下午12:28:57
 */
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
