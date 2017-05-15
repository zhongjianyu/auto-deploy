package auto.deploy.redis;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TestRedis {

	@Resource
	private StringRedisTemplate template;
	
	@Test
	public void testString(){
		template.opsForValue().set("aaa", "aaaaa");
		System.out.println(template.opsForValue().get("aaa"));
	}

}
