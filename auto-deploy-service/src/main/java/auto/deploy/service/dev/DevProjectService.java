package auto.deploy.service.dev;

import com.baomidou.mybatisplus.plugins.Page;
import auto.deploy.object.PageBean;
import auto.deploy.dao.entity.dev.DevProject;
import com.baomidou.mybatisplus.service.IService;

/**
 * 
 * @描述：项目表(服务类).
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-06-17
 */
public interface DevProjectService extends IService<DevProject> {

	/**
	 * 
	 * @描述：项目表(分页列表).
	 *
	 * @返回：Page<DevProject>
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-06-17
	 */
	public Page<DevProject> list(PageBean pageBean, DevProject obj) throws Exception;
	}
