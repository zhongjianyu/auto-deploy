package auto.deploy.object.aut.vo;

import java.util.List;

import auto.deploy.dao.entity.aut.AutUser;

/**
 * 
 * @描述：项目对象VO
 *
 * @作者：zhongjy
 *
 * @时间：2017年5月3日 下午5:08:24
 */
public final class ProjectUserVO {

	/**
	 * 开发人员
	 */
	private List<AutUser> devUserList;
	/**
	 * 测试人员
	 */
	private List<AutUser> testUserList;

	/**
	 * 验收测试
	 */
	private List<AutUser> checkUserList;

	/**
	 * 预发环境
	 */
	private List<AutUser> prepareUserList;

	/**
	 * 生产环境
	 */
	private List<AutUser> produceUserList;

	/**
	 * 测试审批
	 */
	private List<AutUser> testApprovalUserList;
	/**
	 * 验收审批
	 */
	private List<AutUser> checkApprovalUserList;
	/**
	 * 预发审批
	 */
	private List<AutUser> prepareApprovalUserList;
	/**
	 * 生产审批
	 */
	private List<AutUser> produceApprovalUserList;

	public List<AutUser> getDevUserList() {
		return devUserList;
	}

	public void setDevUserList(List<AutUser> devUserList) {
		this.devUserList = devUserList;
	}

	public List<AutUser> getTestUserList() {
		return testUserList;
	}

	public void setTestUserList(List<AutUser> testUserList) {
		this.testUserList = testUserList;
	}

	public List<AutUser> getCheckUserList() {
		return checkUserList;
	}

	public void setCheckUserList(List<AutUser> checkUserList) {
		this.checkUserList = checkUserList;
	}

	public List<AutUser> getPrepareUserList() {
		return prepareUserList;
	}

	public void setPrepareUserList(List<AutUser> prepareUserList) {
		this.prepareUserList = prepareUserList;
	}

	public List<AutUser> getProduceUserList() {
		return produceUserList;
	}

	public void setProduceUserList(List<AutUser> produceUserList) {
		this.produceUserList = produceUserList;
	}

	public List<AutUser> getTestApprovalUserList() {
		return testApprovalUserList;
	}

	public void setTestApprovalUserList(List<AutUser> testApprovalUserList) {
		this.testApprovalUserList = testApprovalUserList;
	}

	public List<AutUser> getCheckApprovalUserList() {
		return checkApprovalUserList;
	}

	public void setCheckApprovalUserList(List<AutUser> checkApprovalUserList) {
		this.checkApprovalUserList = checkApprovalUserList;
	}

	public List<AutUser> getPrepareApprovalUserList() {
		return prepareApprovalUserList;
	}

	public void setPrepareApprovalUserList(List<AutUser> prepareApprovalUserList) {
		this.prepareApprovalUserList = prepareApprovalUserList;
	}

	public List<AutUser> getProduceApprovalUserList() {
		return produceApprovalUserList;
	}

	public void setProduceApprovalUserList(List<AutUser> produceApprovalUserList) {
		this.produceApprovalUserList = produceApprovalUserList;
	}

}
