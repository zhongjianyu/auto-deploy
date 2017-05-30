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

	/**
	 * 
	 * @描述：修改数据字典
	 *
	 * @返回：void
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年5月30日 下午9:27:34
	 */
	public void update(SysDataDict obj, String[] dictKey1, String[] dictValue1, String[] isActive1, String[] id1) throws Exception;
}
