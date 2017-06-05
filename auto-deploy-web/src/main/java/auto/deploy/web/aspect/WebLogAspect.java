package auto.deploy.web.aspect;

import java.lang.reflect.Method;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import auto.deploy.dao.entity.sys.SysOperateLog;
import auto.deploy.security.CustomUser;
import auto.deploy.web.annotation.FuncObj;
import auto.deploy.web.task.WebTask;

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

	@Resource
	private WebTask webTask;

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

	/**
	 * 
	 * @描述：访问目标方法前处理
	 *
	 * @返回：void
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年6月5日 下午10:26:22
	 */
	@Before("webLog()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {
		// 获取目标方法对象
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		Method targetMethod = methodSignature.getMethod();
		FuncObj funcObj = targetMethod.getAnnotation(FuncObj.class);
		// 如果有FuncObj注解则记录操作日志
		if (funcObj != null) {
			// 接收到请求，记录请求内容
			ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
			HttpServletRequest request = attributes.getRequest();
			// 操作日志对象
			SysOperateLog sysOperateLog = new SysOperateLog();
			Date date = new Date();
			Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (object instanceof CustomUser) {
				CustomUser customUser = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				sysOperateLog.setOperateUserId(customUser.getUserId());
				sysOperateLog.setOperateUserName(customUser.getUsername());
			} else {
				sysOperateLog.setOperateUserId(0L);
				sysOperateLog.setOperateUserName("anonymous");
			}
			sysOperateLog.setOperateUserIp(request.getRemoteAddr());
			sysOperateLog.setOperateTime(date);
			sysOperateLog.setRequestAddress(request.getRequestURL().toString());
			sysOperateLog.setMethodName(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());

			sysOperateLog.setOperateLogName(funcObj.desc());
			sysOperateLog.setOperateDetailDesc(funcObj.desc());
			// 调用异步线程
			webTask.webLogTask(sysOperateLog);
		}

	}

}
