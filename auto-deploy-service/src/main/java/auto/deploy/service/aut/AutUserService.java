package auto.deploy.service.aut;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import auto.deploy.dao.entity.aut.AutUser;
import auto.deploy.object.PageBean;

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
}
