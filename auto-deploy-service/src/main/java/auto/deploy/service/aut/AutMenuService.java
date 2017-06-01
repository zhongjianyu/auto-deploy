package auto.deploy.service.aut;

import java.util.List;

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

	/**
	 * 
	 * @描述：根据菜单级别和父级菜ID码获取下一个菜单编码
	 *
	 * @返回：String
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年5月29日 上午11:07:40
	 */
	public String getNextMenuCode(int menuLevel, String parentCode) throws Exception;

	/**
	 * 
	 * @描述：获取一二级菜单列表
	 *
	 * @返回：List<AutMenu>
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年6月1日 下午8:52:53
	 */
	List<AutMenu> list(AutMenu obj) throws Exception;
}
