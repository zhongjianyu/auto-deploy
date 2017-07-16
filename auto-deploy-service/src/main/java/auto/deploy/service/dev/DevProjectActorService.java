package auto.deploy.service.dev;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import auto.deploy.dao.entity.dev.DevProjectActor;
import auto.deploy.object.PageBean;
import auto.deploy.object.aut.vo.ProjectUserVO;

/**
 * 
 * @描述：项目参与者表(服务类).
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-07-16
 */
public interface DevProjectActorService extends IService<DevProjectActor> {

	/**
	 * 
	 * @描述：项目参与者表(分页列表).
	 *
	 * @返回：Page<DevProjectActor>
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017-07-16
	 */
	public Page<DevProjectActor> list(PageBean pageBean, DevProjectActor obj) throws Exception;

	/**
	 * 
	 * @描述：根据项目ID获取项目参与者列表
	 *
	 * @返回：DevProjectActor
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年7月16日 下午8:32:13
	 */
	public ProjectUserVO getUserListByProjectId(Long projectId);
}
