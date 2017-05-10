package auto.deploy.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

/**
 * 
 * @描述：自定义额外表单数据
 *
 * @作者：zhongjy
 *
 * @时间：2017年5月9日 下午11:27:31
 */
public class CustomWebAuthenticationDetails extends WebAuthenticationDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7241654791403589332L;

	/**
	 * 登录验证码
	 */
	private final String loginValidateCode;

	public CustomWebAuthenticationDetails(HttpServletRequest request) {
		super(request);
		loginValidateCode = request.getParameter("loginValidateCode");

	}

	public String getLoginValidateCode() {
		return loginValidateCode;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString()).append("; loginValidateCode: ").append(this.getLoginValidateCode());
		return sb.toString();
	}
}
