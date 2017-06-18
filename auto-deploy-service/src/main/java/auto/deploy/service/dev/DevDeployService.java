package auto.deploy.service.dev;

import com.baomidou.mybatisplus.plugins.Page;
import auto.deploy.object.PageBean;
import auto.deploy.dao.entity.dev.DevDeploy;
import com.baomidou.mybatisplus.service.IService;

/**
 * 
 * @描述：项目部署表(服务类).
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-06-17
 */
public interface DevDeployService extends IService<DevDeploy> {

	/**
	 * 
	 * @描述：项目部署表(分页列表).
	 *
	 * @返回：Page<DevDeploy>
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-06-17
	 */
	public Page<DevDeploy> list(PageBean pageBean, DevDeploy obj) throws Exception;
	}
