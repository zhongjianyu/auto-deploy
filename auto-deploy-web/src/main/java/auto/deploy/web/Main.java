package auto.deploy.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * @描述：项目启动类
 *
 * @作者：zhongjy
 *
 * @时间：2017年4月21日 上午8:40:51
 */
@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(Main.class);
		application.run(args);
	}

}
