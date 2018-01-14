package auto.deploy.security;

import java.util.List;

import org.springframework.security.core.session.SessionDestroyedEvent;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistryImpl;

/**
 * 
 * @描述：自定义SessionRegistry实现
 *
 * @作者：zhongjy
 *
 * @时间：2017年7月28日 下午10:07:06
 */
public class CustomSessionRegistryImpl extends SessionRegistryImpl {

	@Override
	public List<Object> getAllPrincipals() {
		// System.out.println("################################################getAllPrincipals");
		return super.getAllPrincipals();
	}

	@Override
	public List<SessionInformation> getAllSessions(Object principal, boolean includeExpiredSessions) {
		// System.out.println("################################################getAllSessions");
		return super.getAllSessions(principal, includeExpiredSessions);
	}

	@Override
	public SessionInformation getSessionInformation(String sessionId) {
		// System.out.println("################################################getSessionInformation");
		return super.getSessionInformation(sessionId);
	}

	@Override
	public void onApplicationEvent(SessionDestroyedEvent event) {
		// System.out.println("################################################onApplicationEvent");
		super.onApplicationEvent(event);
	}

	@Override
	public void refreshLastRequest(String sessionId) {
		// System.out.println("################################################refreshLastRequest");
		super.refreshLastRequest(sessionId);
	}

	@Override
	public void registerNewSession(String sessionId, Object principal) {
		/// System.out.println("################################################registerNewSession");
		super.registerNewSession(sessionId, principal);
	}

	@Override
	public void removeSessionInformation(String sessionId) {
		// System.out.println("################################################removeSessionInformation");
		super.removeSessionInformation(sessionId);
	}

}
