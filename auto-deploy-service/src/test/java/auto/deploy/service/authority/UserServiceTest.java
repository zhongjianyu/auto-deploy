package auto.deploy.service.authority;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import auto.deploy.dao.entity.aut.AutUser;
import auto.deploy.service.aut.AutUserService;

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
	private AutUserService autUserService;

	@Test
	public void testInsert() {
		AutUser user = new AutUser();
		user.setUserPwd("e");
		user.setUserName("e");
		user.setIsActive(1);
		user.setIsAccountExpired(0);
		user.setIsAccountLocked(0);
		user.setIsCredentialsExpired(0);
		user.setNickName("e用户");
		autUserService.insert(user);
	}

	@Test
	public void testUpdate() {
		AutUser user = autUserService.selectById(866162534596730880L);
		user.setUserName("d1");
		autUserService.updateById(user);
	}

	@Test
	public void testDelete() {
		autUserService.deleteById(859634951780552704L);
	}

	@Test
	public void testSelect() {
		AutUser user = new AutUser();
		user.setId(857596529251434496L);
		user = user.selectById();
		System.out.println(user);
	}

}
