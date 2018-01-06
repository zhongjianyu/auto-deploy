package auto.deploy.service.dev;

import com.baomidou.mybatisplus.plugins.Page;
import auto.deploy.object.PageBean;
import auto.deploy.dao.entity.dev.DevBranch;
import com.baomidou.mybatisplus.service.IService;

/**
 * 
 * @描述：项目分支表(服务类).
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-06-17
 */
public interface DevBranchService extends IService<DevBranch> {

	/**
	 * 
	 * @描述：项目分支表(分页列表).
	 *
	 * @返回：Page<DevBranch>
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-06-17
	 */
	public Page<DevBranch> list(PageBean pageBean, DevBranch obj) throws Exception;

	/**
	 * 
	 * @描述：新建分支
	 *
	 * @返回：void
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年7月25日 下午10:00:03
	 */
	public void add(DevBranch obj, Long userId) throws Exception;
}
