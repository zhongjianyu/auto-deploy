package auto.deploy.service.sys;

import com.baomidou.mybatisplus.plugins.Page;
import auto.deploy.object.PageBean;
import auto.deploy.dao.entity.sys.SysParameter;
import com.baomidou.mybatisplus.service.IService;

/**
 * 
 * @描述：系统参数表(服务类).
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-06-04
 */
public interface SysParameterService extends IService<SysParameter> {

	/**
	 * 
	 * @描述：系统参数表(分页列表).
	 *
	 * @返回：Page<SysParameter>
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-06-04
	 */
	public Page<SysParameter> list(PageBean pageBean, SysParameter obj) throws Exception;
	}
