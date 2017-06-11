package auto.deploy.gitlab.service;

import auto.deploy.dao.entity.aut.AutUser;

/**
 * 
 * @描述：gitlab服务接口
 *
 * @作者：zhongjy
 *
 * @时间：2017年6月10日 下午5:21:07
 */
public interface GitlabService {

	/**
	 * 
	 * @描述：添加用户
	 *
	 * @返回：void
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年6月10日 下午8:44:27
	 */
	public void addUser(AutUser autUser);

	/**
	 * 
	 * @描述：删除用户
	 *
	 * @返回：void
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年6月10日 下午8:07:03
	 */
	public void delUser(AutUser autUser);
}
