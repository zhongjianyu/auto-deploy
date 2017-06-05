package auto.deploy.web.aspect;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import auto.deploy.security.CustomUser;
import auto.deploy.util.DateUtil;

/**
 * 
 * @描述：web日志统一处理
 *
 * @作者：zhongjy
 * 
 * @时间：2017年6月5日 下午3:43:57
 */
@Aspect
@Component
public class WebLogAspect {

	private final static Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

	/**
	 * 
	 * @描述：auto.deploy.web.controller包下的所有函数
	 *
	 * @返回：void
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年6月5日 下午3:53:56
	 */
	@Pointcut("execution(public * auto.deploy.web.controller..*.*(..))")
	public void webLog() {
	}

	@Before("webLog()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {
		// 接收到请求，记录请求内容
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();

		// 记录下请求内容
		logger.info("请求地址 : " + request.getRequestURL().toString());
		logger.info("请求时间 : " + DateUtil.datetime2str(new Date()));
		logger.info("请求类型 : " + request.getMethod());
		logger.info("请求IP : " + request.getRemoteAddr());
		logger.info("请求方法 : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
		Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (object instanceof CustomUser) {
			CustomUser customUser = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			logger.info("用户ID : " + customUser.getUserId());
			logger.info("用户账号 : " + customUser.getUsername());
		}
	}

}
