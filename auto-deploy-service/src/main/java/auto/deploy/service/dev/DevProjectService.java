package auto.deploy.service.dev;

import com.baomidou.mybatisplus.plugins.Page;
import auto.deploy.object.PageBean;
import auto.deploy.object.RetMsg;
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

	/**
	 * 
	 * @描述：新增项目
	 *
	 * @返回：void
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年7月8日 上午9:11:12
	 */
	public void add(DevProject obj) throws Exception;

	/**
	 * 
	 * @描述：删除项目
	 *
	 * @返回：void
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年7月8日 上午9:11:12
	 */
	public void del(DevProject obj) throws Exception;

	/**
	 * 
	 * @描述：保存项目配置人员
	 *
	 * @返回：RetMsg
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年7月16日 下午11:00:59
	 */
	public RetMsg setActor(DevProject obj, String devUserIds, String testUserIds, String checkUserIds, String prepareUserIds, String produceUserIds,
			String testApprovalUserIds, String checkApprovalUserIds, String prepareApprovalUserIds, String produceApprovalUserIds);
}
