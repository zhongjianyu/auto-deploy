package auto.deploy.object;

/**
 * 
 * @描述：前后端分页参数类
 *
 * @作者：zhongjy
 *
 * @时间：2017年5月16日 下午10:14:31
 */
public class PageBean {
	/**
	 * 每页记录数
	 */
	private Integer pageSize;
	/**
	 * 当前页码
	 */
	private Integer pageNum;

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

}