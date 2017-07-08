package auto.deploy.service.dev;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import auto.deploy.dao.entity.dev.DevProjectGroup;
import auto.deploy.object.PageBean;
import auto.deploy.object.RetMsg;

/**
 * 
 * @描述：项目分组表(服务类).
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-06-17
 */
public interface DevProjectGroupService extends IService<DevProjectGroup> {

	/**
	 * 
	 * @描述：项目分组表(分页列表).
	 *
	 * @返回：Page<DevProjectGroup>
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-06-17
	 */
	public Page<DevProjectGroup> list(PageBean pageBean, DevProjectGroup obj) throws Exception;

	/**
	 * 
	 * @描述：新增分组
	 *
	 * @返回：void
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年7月8日 下午3:44:02
	 */
	public RetMsg add(DevProjectGroup obj) throws Exception;

	/**
	 * 
	 * @描述：删除分组
	 *
	 * @返回：void
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年7月8日 下午3:44:02
	 */
	public RetMsg del(DevProjectGroup obj) throws Exception;
}
