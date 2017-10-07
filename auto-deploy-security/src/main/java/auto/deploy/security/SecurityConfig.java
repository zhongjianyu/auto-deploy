package auto.deploy.security;

import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.util.StringUtils;

import auto.deploy.websocket.WebSocketMsg;
import auto.deploy.websocket.service.WebSocketService;

/**
 * 
 * @描述：security配置
 *
 * @作者：zhongjy
 *
 * @时间：2017年5月2日 下午9:43:01
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Resource
	private CustomSuccessHandler customSuccessHandler;
	@Resource
	private CustomFailureHandler customFailureHandler;
	@Resource
	private CustomUserDetailsService customUserDetailsService;
	@Resource
	private CustomPasswordEncoder customPasswordEncoder;
	@Resource
	private CustomAuthenticationDetailsSource customAuthenticationDetailsSource;
	@Resource
	private DataSource dataSource;
	@Resource
	private SessionRegistry sessionRegistry;
	@Resource
	private WebSocketService webSocketService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(getAuthenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 允许访问的页面
		String[] limitVisitHtml = { "/login.html", "/validateCode/codeImg.html" };
		// 允许访问的资源
		String[] limitVisitResource = { "/**/*.js", "/**/*.css", "/**/*.woff", "/**/*.woff2", "/**/*.otf", "/**/*.eot", "/**/*.svg", "/**/*.ttf",
				"/**/*.png", "/**/*.jpg", "/**/*.gif", "/**/*.json" };
		http.authorizeRequests().antMatchers(limitVisitHtml).permitAll()// 访问匹配的url无需认证
				.antMatchers(limitVisitResource).permitAll()// 不拦截静态资源
				.anyRequest().authenticated()// 所有资源都需要认证，登陆后访问
				.and().formLogin().loginPage("/login.html")// 登录表单
				.loginProcessingUrl("/login.do")// 登录请求url
				.usernameParameter("loginUserName")// 登录表单账户的name
				.passwordParameter("loginUserPwd")// 登录表单密码的name
				.successHandler(customSuccessHandler)// 自定义登录成功处理
				.failureHandler(customFailureHandler)// 自定义登录失败处理
				.authenticationDetailsSource(customAuthenticationDetailsSource)// 自定义额外表单数据
				.and().rememberMe().rememberMeParameter("loginRememberMe")// 登录表单记住我name
				.tokenRepository(persistentTokenRepository()).tokenValiditySeconds(86400)// 记住我持久化并设定时间
				.and().logout()// (2)---------------.登出表单配置
				.logoutSuccessUrl("/login.html")// 退出成功跳转
				.logoutUrl("/logout.do")// 登出请求url
				.addLogoutHandler(new CustomSecurityContextLogoutHandler(sessionRegistry()))// 自定义退出处理器
				.and().csrf()// (3)---------------.启用跨站请求伪造(CSRF)保护,如果启用了CSRF，那么在登录或注销页面中必须包括_csrf.token
				.and().headers().defaultsDisabled().cacheControl()// 解决iframe加载问题（x-frame-options）
		;
		// 限制只能单用户登录，如果重复登录，则先登录的用户被强制退出
		http.sessionManagement().maximumSessions(1).expiredUrl("/login.html").sessionRegistry(sessionRegistry());
		// 限制只能单用户登录，如果重复登录，则提示不能登录(这个方法如果没有进行退出操作直接关掉浏览器，重新登录有问题)
		// http.sessionManagement().maximumSessions(1).maxSessionsPreventsLogin(true).sessionRegistry(sessionRegistry());
	}

	/**
	 * 
	 * @描述：session注册器
	 *
	 * @返回：SessionRegistry
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年7月28日 下午10:25:45
	 */
	@Bean
	public SessionRegistry sessionRegistry() {
		return new CustomSessionRegistryImpl();
	}

	/**
	 * 
	 * @描述：自定义权限提供者
	 *
	 * @返回：DaoAuthenticationProvider
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年5月9日 下午9:09:38
	 */
	public DaoAuthenticationProvider getAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider() {
			@Override
			public Authentication authenticate(Authentication authentication) throws AuthenticationException {
				// 增加对验证码的判断
				CustomWebAuthenticationDetails form = (CustomWebAuthenticationDetails) authentication.getDetails();
				if (StringUtils.isEmpty(form.getLoginValidateCode())) {
					// 判断是否为空
					throw new UsernameNotFoundException("验证码不能为空");
				} else {
					// 判断是否正确
					if (StringUtils.isEmpty(form.getLoginSessionCode())) {
						throw new UsernameNotFoundException("服务器验证码异常");
					} else {
						if (!(form.getLoginSessionCode()).equals(form.getLoginValidateCode())) {
							throw new UsernameNotFoundException("验证码错误");
						}
					}
				}
				return super.authenticate(authentication);
			}

			@Override
			protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication)
					throws AuthenticationException {
				super.additionalAuthenticationChecks(userDetails, authentication);
				// 校验通过后，获取第一次登录用户信息,推送强制离线消息
				System.out.println("校验通过。。。");
				List<SessionInformation> infoList2 = sessionRegistry.getAllSessions(userDetails, false);
				if (infoList2.size() > 0) {
					CustomUser customUser = (CustomUser) userDetails;
					WebSocketMsg msg = new WebSocketMsg();
					msg.setCode(1);
					msg.setMessage("重复登录提示");
					CustomWebAuthenticationDetails details = (CustomWebAuthenticationDetails) authentication.getDetails();
					msg.setObject("你的账号[" + authentication.getPrincipal() + "]在[" + details.getRemoteAddress() + "]中登录，本次登录被强制退出");
					webSocketService.pushMessageToUser(msg, customUser.getUsername());
				}

			}

		};
		// 自定义用户登录
		provider.setUserDetailsService(customUserDetailsService);
		// 密码加密器
		provider.setPasswordEncoder(customPasswordEncoder);
		// 是否隐藏用户不存在异常(提示)
		provider.setHideUserNotFoundExceptions(false);
		// 制定本地提示消息文件
		provider.setMessageSource(getMessageSource());
		return provider;
	}

	/**
	 * 
	 * @描述：重定义消息文件
	 *
	 * @返回：ReloadableResourceBundleMessageSource
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年5月9日 下午10:32:54
	 */
	public ReloadableResourceBundleMessageSource getMessageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:security_messages_zh_CN");
		return messageSource;
	}

	/**
	 * 
	 * @描述：自定义remember me持久化
	 *
	 * @返回：PersistentTokenRepository
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年5月10日 下午9:52:25
	 */
	public PersistentTokenRepository persistentTokenRepository() {
		CustomJdbcTokenRepositoryImpl tokenRepositoryImpl = new CustomJdbcTokenRepositoryImpl();
		tokenRepositoryImpl.setDataSource(dataSource);
		return tokenRepositoryImpl;
	}

	/**
	 * remember-me必须指定UserDetailsService
	 */
	@Override
	protected UserDetailsService userDetailsService() {
		return customUserDetailsService;
	}

}
