package auto.deploy.service.dev;

import com.baomidou.mybatisplus.plugins.Page;
import auto.deploy.object.PageBean;
import auto.deploy.dao.entity.dev.DevServer;
import com.baomidou.mybatisplus.service.IService;

/**
 * 
 * @描述：服务器表(服务类).
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-06-17
 */
public interface DevServerService extends IService<DevServer> {

	/**
	 * 
	 * @描述：服务器表(分页列表).
	 *
	 * @返回：Page<DevServer>
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-06-17
	 */
	public Page<DevServer> list(PageBean pageBean, DevServer obj) throws Exception;
	}
