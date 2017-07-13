package auto.deploy.service.aut;

import com.baomidou.mybatisplus.plugins.Page;
import auto.deploy.object.PageBean;
import auto.deploy.object.RetMsg;
import auto.deploy.dao.entity.aut.AutRole;
import com.baomidou.mybatisplus.service.IService;

/**
 * 
 * @描述：角色表(服务类).
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-05-27
 */
public interface AutRoleService extends IService<AutRole> {

	/**
	 * 
	 * @描述：角色表(分页列表).
	 *
	 * @返回：Page<AutRole>
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-05-27
	 */
	public Page<AutRole> list(PageBean pageBean, AutRole obj) throws Exception;

	/**
	 * 
	 * @描述：新增角色
	 *
	 * @返回：RetMsg
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年7月13日 下午8:55:40
	 */
	public RetMsg add(AutRole obj) throws Exception;

	/**
	 * 
	 * @描述：删除角色
	 *
	 * @返回：void
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年7月13日 下午9:01:02
	 */
	public RetMsg delete(AutRole obj) throws Exception;
}
