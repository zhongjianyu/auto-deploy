package auto.deploy.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 
 * @描述：CAS配置
 *
 * @作者：zhongjy
 *
 * @时间：2017年6月5日 下午6:38:51
 */
@ConfigurationProperties(prefix = "cas.server.host")
public class CasConfig {

	/**
	 * CAS服务地址
	 */
	private String casUrl;
	/**
	 * CAS服务登录地址
	 */
	private String casLoginUrl;
	/**
	 * CAS服务登出地址
	 */
	private String casLogoutUrl;
	/**
	 * 应用访问地址
	 */
	private String appUrl;
	/**
	 * 应用登录地址
	 */
	private String appLoginUrl;
	/**
	 * 应用登出地址
	 */
	private String appLogoutUrl;

	public String getCasUrl() {
		return casUrl;
	}

	public void setCasUrl(String casUrl) {
		this.casUrl = casUrl;
	}

	public String getCasLoginUrl() {
		return casLoginUrl;
	}

	public void setCasLoginUrl(String casLoginUrl) {
		this.casLoginUrl = casLoginUrl;
	}

	public String getCasLogoutUrl() {
		return casLogoutUrl;
	}

	public void setCasLogoutUrl(String casLogoutUrl) {
		this.casLogoutUrl = casLogoutUrl;
	}

	public String getAppUrl() {
		return appUrl;
	}

	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}

	public String getAppLoginUrl() {
		return appLoginUrl;
	}

	public void setAppLoginUrl(String appLoginUrl) {
		this.appLoginUrl = appLoginUrl;
	}

	public String getAppLogoutUrl() {
		return appLogoutUrl;
	}

	public void setAppLogoutUrl(String appLogoutUrl) {
		this.appLogoutUrl = appLogoutUrl;
	}

}
