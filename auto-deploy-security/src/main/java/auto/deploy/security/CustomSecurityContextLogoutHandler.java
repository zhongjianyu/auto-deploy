package auto.deploy.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

/**
 * 
 * @描述：自定义退出处理类
 *
 * @作者：zhongjy
 *
 * @时间：2017年7月29日 下午3:22:14
 */
public class CustomSecurityContextLogoutHandler extends SecurityContextLogoutHandler {

	public CustomSecurityContextLogoutHandler(SessionRegistry sessionRegistry) {
		super();
		this.sessionRegistry = sessionRegistry;
	}

	private SessionRegistry sessionRegistry;

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		// 删除注册器中的用户信息在退出登录
		/*List<Object> list1 = sessionRegistry.getAllPrincipals();
		List<SessionInformation> infoList1 = sessionRegistry.getAllSessions(list1.get(0), false);
		System.out.println("当前用户数："+list1.size());
		List<Object> list3 = sessionRegistry.getAllPrincipals();
		List<SessionInformation> infoList3 = sessionRegistry.getAllSessions(list3.get(0), false);
		System.out.println("当前用户数A："+list3.size());*/
		sessionRegistry.removeSessionInformation(request.getSession().getId());
		super.logout(request, response, authentication);
		/*List<Object> list2 = sessionRegistry.getAllPrincipals();
		List<SessionInformation> infoList2 = sessionRegistry.getAllSessions(list2.get(0), false);
		System.out.println("退出后用户数："+list2.size());*/
	}

	@Override
	public boolean isInvalidateHttpSession() {
		return super.isInvalidateHttpSession();
	}

	@Override
	public void setInvalidateHttpSession(boolean invalidateHttpSession) {
		super.setInvalidateHttpSession(invalidateHttpSession);
	}

	@Override
	public void setClearAuthentication(boolean clearAuthentication) {
		super.setClearAuthentication(clearAuthentication);
	}

}
