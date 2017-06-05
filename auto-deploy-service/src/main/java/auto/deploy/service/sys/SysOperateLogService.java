package auto.deploy.service.sys;

import com.baomidou.mybatisplus.plugins.Page;
import auto.deploy.object.PageBean;
import auto.deploy.dao.entity.sys.SysOperateLog;
import com.baomidou.mybatisplus.service.IService;

/**
 * 
 * @描述：操作日志表(服务类).
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-06-05
 */
public interface SysOperateLogService extends IService<SysOperateLog> {

	/**
	 * 
	 * @描述：操作日志表(分页列表).
	 *
	 * @返回：Page<SysOperateLog>
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-06-05
	 */
	public Page<SysOperateLog> list(PageBean pageBean, SysOperateLog obj) throws Exception;
	}
