package auto.deploy.service.dev;

import com.baomidou.mybatisplus.plugins.Page;
import auto.deploy.object.PageBean;
import auto.deploy.dao.entity.dev.DevProjectGroup;
import com.baomidou.mybatisplus.service.IService;

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
	}
