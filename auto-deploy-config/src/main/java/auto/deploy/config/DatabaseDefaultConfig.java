package auto.deploy.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 
 * @描述：默认数据库配置
 *
 * @作者：zhongjy
 *
 * @时间：2017年6月5日 下午6:38:51
 */
@ConfigurationProperties(prefix = "spring.db.default")
public class DatabaseDefaultConfig extends Database {

}
