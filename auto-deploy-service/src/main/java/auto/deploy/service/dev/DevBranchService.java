package auto.deploy.service.dev;

import java.util.Map;

import org.activiti.engine.runtime.ProcessInstance;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import auto.deploy.dao.entity.dev.DevBranch;
import auto.deploy.object.PageBean;
import auto.deploy.object.aut.vo.DevTaskVO;

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

	/**
	 * 
	 * @描述：设置候选人
	 *
	 * @返回：void
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2018年1月9日 下午9:47:07
	 */
	public void setCandidateUser(ProcessInstance processInstance, Long projectId) throws Exception;

	/**
	 * 
	 * @描述：获取具有权限的项目的流程分支(实际上是获取待办（候选）权限的任务).
	 *
	 * @返回：List<DevBranch>
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2018年1月14日 上午10:52:45
	 */
	Page<DevTaskVO> getProcessBranch(PageBean pageBean, Map<String, String> param) throws Exception;
}
