

import java.util.List;

import auto.deploy.dao.entity.AutUser;

/**
 * 
 * @æè¿°ï¼šç”¨æˆ·å?¼å¯¹è±?
 *
 * @ä½œè?…ï¼šzhongjy
 *
 * @æ—¶é—´ï¼?2017å¹?5æœ?3æ—? ä¸‹åˆ5:08:24
 */
public final class AutUserVO extends AutUser {

	/**
	 * @æè¿°ï¼šTODO
	 */
	private static final long serialVersionUID = 1L;

	private List<AutRoleVO> roleList;

	public List<AutRoleVO> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<AutRoleVO> roleList) {
		this.roleList = roleList;
	}

}
