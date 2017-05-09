package auto.deploy.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;

/**
 * 
 * @描述：自定义额外表单数据
 *
 * @作者：zhongjy
 *
 * @时间：2017年5月9日 下午11:27:43
 */
@Service
public class CustomAuthenticationDetailsSource
		implements AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> {

	@Override
	public WebAuthenticationDetails buildDetails(HttpServletRequest request) {
		return new CustomWebAuthenticationDetails(request);
	}

}
