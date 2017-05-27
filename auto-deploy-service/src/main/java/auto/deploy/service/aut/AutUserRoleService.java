package auto.deploy.service.aut;

import com.baomidou.mybatisplus.plugins.Page;
import auto.deploy.object.PageBean;
import auto.deploy.dao.entity.aut.AutUserRole;
import com.baomidou.mybatisplus.service.IService;

/**
 * 
 * @描述：用户-角色关联表(服务类).
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-05-27
 */
public interface AutUserRoleService extends IService<AutUserRole> {

	/**
	 * 
	 * @描述：用户-角色关联表(分页列表).
	 *
	 * @返回：Page<AutUserRole>
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-05-27
	 */
	public Page<AutUserRole> list(PageBean pageBean, AutUserRole obj) throws Exception;
	}
