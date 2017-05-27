package auto.deploy.service.aut;

import com.baomidou.mybatisplus.plugins.Page;
import auto.deploy.object.PageBean;
import auto.deploy.dao.entity.aut.AutMenu;
import com.baomidou.mybatisplus.service.IService;

/**
 * 
 * @描述：菜单表(服务类).
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-05-27
 */
public interface AutMenuService extends IService<AutMenu> {

	/**
	 * 
	 * @描述：菜单表(分页列表).
	 *
	 * @返回：Page<AutMenu>
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-05-27
	 */
	public Page<AutMenu> list(PageBean pageBean, AutMenu obj) throws Exception;
	}
