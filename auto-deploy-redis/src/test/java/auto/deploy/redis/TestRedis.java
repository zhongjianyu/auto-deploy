package auto.deploy.redis;

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

	@Test
	public void testString() {

		stringRedisTemplate.opsForValue().set("aaa", "12345a", 30, TimeUnit.SECONDS);
		System.out.println(stringRedisTemplate.opsForValue().get("aaa"));

		AutUser user = new AutUser();
		user.setUserName("ÖÐ¹úÈË");
		redisTemplate.opsForValue().set("bbb", user, 30, TimeUnit.SECONDS);
		AutUser user2 = (AutUser) redisTemplate.opsForValue().get("bbb");
		System.out.println(user2.getUserName());

	}

}
