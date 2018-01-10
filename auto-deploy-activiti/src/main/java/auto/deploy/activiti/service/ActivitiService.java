package auto.deploy.activiti.service;

import java.util.Map;

import org.activiti.engine.runtime.ProcessInstance;

/**
 * 
 * @描述：流程服务类(接口).
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-05-27
 */
public interface ActivitiService {

	/**
	 * 
	 * @描述：新增用户
	 *
	 * @返回：void
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年6月10日 下午4:21:33
	 */
	public void addUser(Long userId);

	/**
	 * 
	 * @描述：删除用户
	 *
	 * @返回：void
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年6月10日 下午4:21:43
	 */
	public void delUser(Long userId);

	/**
	 * 
	 * @描述：新增分组
	 *
	 * @返回：void
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年6月10日 下午4:21:55
	 */
	public void addGroup(Long groupId);

	/**
	 * 
	 * @描述：删除分组
	 *
	 * @返回：void
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年6月10日 下午4:22:05
	 */
	public void delGroup(Long groupId);

	/**
	 * 
	 * @描述：新增用户分组
	 *
	 * @返回：void
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年6月10日 下午4:22:16
	 */
	public void addUserGroup(Long userId, Long groupId);

	/**
	 * 
	 * @描述：删除
	 *
	 * @返回：void
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年6月10日 下午4:22:30
	 */
	public void delUserGroup(Long userId, Long groupId);

	/**
	 * 
	 * @描述：流程部署
	 *
	 * @返回：void
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年11月18日 下午5:00:01
	 */
	public void doDeploy(String fileName);

	/**
	 * 
	 * @描述：任务提交
	 *
	 * @返回：void
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年11月18日 下午5:00:01
	 */
	public void doNext(Map<String, Object> variables, String taskId);

	/**
	 * 
	 * @描述：启动流程
	 *
	 * @返回：void
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年11月18日 下午5:00:01
	 */
	public ProcessInstance startProcess(String processId);
	
	/**
	 * 
	 * @描述：启动流程
	 *
	 * @返回：void
	 *
	 * @作者：zhongjy
	 *
	 * @时间：2017年11月18日 下午5:00:01
	 */
	public ProcessInstance startProcess(String processId,Map<String,String> param);

}
