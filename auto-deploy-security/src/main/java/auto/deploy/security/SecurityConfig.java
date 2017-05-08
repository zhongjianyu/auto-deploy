package auto.deploy.security;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

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

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService).passwordEncoder(customPasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 允许访问的页面
		String[] limitVisitHtml = { "/login.html", "/validateCode/codeImg.html", "/index.html" };
		// 允许访问的资源
		String[] limitVisitResource = { "/**/*.js", "/**/*.css", "/**/*.woff", "/**/*.woff2", "/**/*.otf", "/**/*.eot", "/**/*.svg", "/**/*.ttf", "/**/*.png", "/**/*.jpg", "/**/*.gif", "/**/*.json" };
		http.authorizeRequests().antMatchers(limitVisitHtml).permitAll()// 访问匹配的url无需认证
				.antMatchers(limitVisitResource).permitAll()// 不拦截静态资源
				.anyRequest().authenticated()// 所有资源都需要认证，登陆后访问
				.and().formLogin()// (1)---------------.登录表单配置
				.loginPage("/login.html")// 登录表单
				.loginProcessingUrl("/j_spring_security_check")// 登录请求url
				.usernameParameter("loginUserName")// 登录表单账户的name
				.passwordParameter("loginUserPwd")// 登录表单密码的name
				.successHandler(customSuccessHandler)// 自定义登录成功处理
				.failureHandler(customFailureHandler)// 自定义登录失败处理
				//.failureUrl("/login.html")// 验证失败跳转
				.and().logout()// (2)---------------.登出表单配置
				.logoutSuccessUrl("/login.html")// 退出成功跳转
				.logoutUrl("/j_spring_security_logout")// 登出请求url
				.and().csrf()// (3)---------------.启用跨站请求伪造(CSRF)保护,如果启用了CSRF，那么在登录或注销页面中必须包括_csrf.token
				.and().headers().defaultsDisabled().cacheControl();// 解决iframe加载问题（x-frame-options）
		;
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// 不拦截静态资源
		web.ignoring().antMatchers("/resources/static/**");
	}

}
