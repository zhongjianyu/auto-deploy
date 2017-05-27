package auto.deploy.service.sys;

import com.baomidou.mybatisplus.plugins.Page;
import auto.deploy.object.PageBean;
import auto.deploy.dao.entity.sys.SysDataDict;
import com.baomidou.mybatisplus.service.IService;

/**
 * 
 * @描述：数据字典表(服务类).
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-05-27
 */
public interface SysDataDictService extends IService<SysDataDict> {

	/**
	 * 
	 * @描述：数据字典表(分页列表).
	 *
	 * @返回：Page<SysDataDict>
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-05-27
	 */
	public Page<SysDataDict> list(PageBean pageBean, SysDataDict obj) throws Exception;
	}
