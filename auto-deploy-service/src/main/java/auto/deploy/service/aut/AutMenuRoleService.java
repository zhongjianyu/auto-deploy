package auto.deploy.service.aut;

import com.baomidou.mybatisplus.plugins.Page;
import auto.deploy.object.PageBean;
import auto.deploy.dao.entity.aut.AutMenuRole;
import com.baomidou.mybatisplus.service.IService;

/**
 * 
 * @描述：菜单-角色表(服务类).
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-05-27
 */
public interface AutMenuRoleService extends IService<AutMenuRole> {

	/**
	 * 
	 * @描述：菜单-角色表(分页列表).
	 *
	 * @返回：Page<AutMenuRole>
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-05-27
	 */
	public Page<AutMenuRole> list(PageBean pageBean, AutMenuRole obj) throws Exception;
	}
