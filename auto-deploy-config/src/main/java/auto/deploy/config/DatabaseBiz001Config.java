package auto.deploy.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 
 * @描述：数据源配置
 *
 * @作者：zhongjy
 *
 * @时间：2017年6月5日 下午6:38:51
 */
@ConfigurationProperties(prefix = "spring.db.biz001")
public class DatabaseBiz001Config extends Database {

}
