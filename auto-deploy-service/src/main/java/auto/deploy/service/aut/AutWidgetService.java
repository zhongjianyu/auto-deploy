package auto.deploy.service.aut;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import auto.deploy.dao.entity.aut.AutWidget;
import auto.deploy.object.PageBean;
import auto.deploy.object.aut.dto.AutWidgetDO;

/**
 * 
 * @描述：控件表(服务类).
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-05-27
 */
public interface AutWidgetService extends IService<AutWidget> {

	/**
	 * 
	 * @描述：控件表(分页列表).
	 *
	 * @返回：Page<AutWidget>
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-05-27
	 */
	public Page<AutWidget> list(PageBean pageBean, AutWidget obj) throws Exception;

	/**
	 * 
	 * @描述：控件表,含菜单信息(分页列表).
	 *
	 * @返回：Page<AutWidget>
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-05-27
	 */
	public Page<AutWidgetDO> list(PageBean pageBean, AutWidgetDO obj) throws Exception;
}
