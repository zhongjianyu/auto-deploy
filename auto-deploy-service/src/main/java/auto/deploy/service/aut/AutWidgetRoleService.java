package auto.deploy.service.aut;

import com.baomidou.mybatisplus.plugins.Page;
import auto.deploy.object.PageBean;
import auto.deploy.dao.entity.aut.AutWidgetRole;
import com.baomidou.mybatisplus.service.IService;

/**
 * 
 * @描述：控件-角色表(服务类).
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-05-27
 */
public interface AutWidgetRoleService extends IService<AutWidgetRole> {

	/**
	 * 
	 * @描述：控件-角色表(分页列表).
	 *
	 * @返回：Page<AutWidgetRole>
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-05-27
	 */
	public Page<AutWidgetRole> list(PageBean pageBean, AutWidgetRole obj) throws Exception;
	}
