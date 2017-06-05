package auto.deploy.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import auto.deploy.dao.entity.sys.SysOperateLog;
import auto.deploy.service.sys.SysOperateLogService;

/**
 * 
 * @描述：自定义成功处理
 *
 * @作者：zhongjy
 *
 * @时间：2017年5月3日 下午5:06:02
 */
@Service
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Resource
	private SysOperateLogService sysOperateLogService;

	@Override
	protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		// 获取用户角色
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		List<String> roles = new ArrayList<String>();
		for (GrantedAuthority a : authorities) {
			roles.add(a.getAuthority());
		}

		if (response.isCommitted()) {
			System.out.println("不能重定向...");
			return;
		}
		String targetUrl = "/";

		// 登录成功，插入操作日志(以后改成异步插入)
		SysOperateLog sysOperateLog = new SysOperateLog();
		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Date date = new Date();
		sysOperateLog.setOperateUserId(customUser.getUserId());
		sysOperateLog.setOperateUserName(customUser.getUsername());
		sysOperateLog.setOperateUserIp(request.getRemoteAddr());
		sysOperateLog.setOperateTime(date);
		sysOperateLog.setRequestAddress(request.getRequestURL().toString());
		sysOperateLog.setOperateLogName("[登录操作]");
		sysOperateLog.setOperateDetailDesc("[登录操作]");
		sysOperateLog.setMethodName("auto.deploy.security.CustomUserDetailsService.loadUserByUsername");
		sysOperateLogService.insert(sysOperateLog);

		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}

	protected RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}

}
