package auto.deploy.redis;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import auto.deploy.dao.entity.AutUser;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TestRedis {

	@Resource
	private StringRedisTemplate stringRedisTemplate;

	@Resource
	private RedisTemplate<String, Object> redisTemplate;

	@SuppressWarnings("unchecked")
	@Test
	public void testString() {

		// stringRedisTemplate.opsForValue().set("aaa", "12345a", 30,
		// TimeUnit.SECONDS);
		// System.out.println(stringRedisTemplate.opsForValue().get("aaa"));

		AutUser user1 = new AutUser();
		user1.setUserName("中国人");
		AutUser user2 = new AutUser();
		user2.setUserName("美国人");
		AutUser user3 = new AutUser();
		user3.setUserName("韩国人");
		List<AutUser> userList = new ArrayList<AutUser>();
		userList.add(user1);
		userList.add(user2);
		userList.add(user3);
		redisTemplate.opsForValue().set("aaa", userList, 30, TimeUnit.SECONDS);
		List<AutUser> userList2 = (List<AutUser>) redisTemplate.opsForValue().get("aaa");
		for (AutUser autUser : userList2) {
			System.out.println(autUser.getUserName());
		}

	}

}
