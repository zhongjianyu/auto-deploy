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
		user.setUserPwd("fffff");
		user.setUserName("fffff");
		user.setIsActive(1);
		user.setIsAccountExpired(0);
		user.setIsAccountLocked(0);
		user.setIsCredentialsExpired(0);
		user.setNickName("fffff");
		autUserService.insert(user);
	}

	@Test
	public void testUpdate() {
		AutUser user = new AutUser();
		user.setId(865743020264394752L);
		user = autUserService.selectById(865743020264394752L);
		user.setUserName("abc_2");
		user.setIsDelete(1);
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
