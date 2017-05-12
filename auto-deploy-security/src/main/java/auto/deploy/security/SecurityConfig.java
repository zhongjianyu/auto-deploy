package auto.deploy.security;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.util.StringUtils;

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

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(getAuthenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 允许访问的页面
		String[] limitVisitHtml = { "/login.html", "/validateCode/codeImg.html" };
		// 允许访问的资源
		String[] limitVisitResource = { "/**/*.js", "/**/*.css", "/**/*.woff", "/**/*.woff2", "/**/*.otf", "/**/*.eot",
				"/**/*.svg", "/**/*.ttf", "/**/*.png", "/**/*.jpg", "/**/*.gif", "/**/*.json" };
		http.authorizeRequests().antMatchers(limitVisitHtml).permitAll()// 访问匹配的url无需认证
				.antMatchers(limitVisitResource).permitAll()// 不拦截静态资源
				.anyRequest().authenticated()// 所有资源都需要认证，登陆后访问
				.and().formLogin()// (1)---------------.登录表单配置
				.loginPage("/login.html")// 登录表单
				.loginProcessingUrl("/login.do")// 登录请求url
				.usernameParameter("loginUserName")// 登录表单账户的name
				.passwordParameter("loginUserPwd")// 登录表单密码的name
				.successHandler(customSuccessHandler)// 自定义登录成功处理
				.failureHandler(customFailureHandler)// 自定义登录失败处理
				.authenticationDetailsSource(customAuthenticationDetailsSource)// 自定义额外表单数据
				// .failureUrl("/login.html")// 验证失败跳转
				.and().rememberMe().rememberMeParameter("loginRememberMe")// 登录表单记住我name
				.tokenRepository(persistentTokenRepository()).tokenValiditySeconds(86400)// 记住我持久化并设定时间
				.and().logout()// (2)---------------.登出表单配置
				.logoutSuccessUrl("/login.html")// 退出成功跳转
				.logoutUrl("/j_spring_security_logout")// 登出请求url
				.and().csrf()// (3)---------------.启用跨站请求伪造(CSRF)保护,如果启用了CSRF，那么在登录或注销页面中必须包括_csrf.token
				.and().headers().defaultsDisabled().cacheControl()// 解决iframe加载问题（x-frame-options）
				;

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
