package auto.deploy.web.controller;

import org.springframework.security.core.context.SecurityContextHolder;

import auto.deploy.security.CustomUser;

/**
 * 
 * @描述：基础控制器类
 *
 * @作者：zhongjy
 *
 * @时间：2017年5月25日 下午8:30:31
 */
public class BaseController {
	/**
	 * 
	 * @描述：获取当前用户信息
	 *
	 * @返回：CustomUser
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年5月25日 下午8:49:45
	 */
	public CustomUser getCustomDetail() {
		return (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

}
