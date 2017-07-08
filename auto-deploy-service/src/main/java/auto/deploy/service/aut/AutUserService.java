package auto.deploy.service.aut;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import auto.deploy.dao.entity.aut.AutUser;
import auto.deploy.object.PageBean;
import auto.deploy.object.RetMsg;

/**
 * 
 * @描述：用户表(服务类).
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-05-21
 */
public interface AutUserService extends IService<AutUser> {

	/**
	 * 
	 * @描述：用户表(分页列表).
	 *
	 * @返回：Page<AutUser>
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年5月27日 下午4:52:01
	 */
	public Page<AutUser> list(PageBean pageBean, AutUser obj) throws Exception;

	/**
	 * 
	 * @描述：删除用户
	 *
	 * @返回：void
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年7月8日 下午7:37:09
	 */
	public void delete(AutUser autUser) throws Exception;

	/**
	 * 
	 * @描述：新增用户
	 *
	 * @返回：void
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年7月8日 下午7:37:09
	 */
	public RetMsg add(AutUser autUser) throws Exception;
}
