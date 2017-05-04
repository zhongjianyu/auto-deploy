package auto.deploy.security;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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
	private CustomUserDetailsService customUserDetailsService;
	@Resource
	private CustomPasswordEncoder customPasswordEncoder;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService).passwordEncoder(customPasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		String[] limitVisit = { "/login.html", "/validateCode/codeImg.html" };
		http.authorizeRequests().antMatchers(limitVisit).permitAll()// 访问匹配的url无需认证
				.anyRequest().authenticated()// 所有资源都需要认证，登陆后访问
				.and().formLogin()// (1)---------------.登录表单配置
				.loginPage("/login.html")// 登录表单
				.loginProcessingUrl("/j_spring_security_check")// 登录请求url
				.usernameParameter("loginUserName")// 登录表单账户的name
				.passwordParameter("loginUserPwd")// 登录表单密码的name
				.successHandler(customSuccessHandler)// 自定义登录成功处理
				.failureUrl("/login.html")// 验证失败跳转
				.and().logout()// (2)---------------.登出表单配置
				.logoutSuccessUrl("/login.html")// 退出成功跳转
				.logoutUrl("/j_spring_security_logout")// 登出请求url
				.and().csrf()// (3)---------------.启用跨站请求伪造(CSRF)保护,如果启用了CSRF，那么在登录或注销页面中必须包括_csrf.token
				;
	}

}
