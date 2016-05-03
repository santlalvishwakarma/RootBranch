package com.web.common.dvo.systemowner;

import java.util.ArrayList;

import com.web.common.dvo.common.BaseDVO;

public class PageNavigationDVO extends BaseDVO {

	private static final long serialVersionUID = 2584405130755975146L;
	private Long pageNavigationId;
	private String pageCode;
	private String pageURL;
	private String pageDisplayName;
	private ArrayList<RoleDVO> roleList;

	/**
	 * @return the pageCode
	 */
	public String getPageCode() {
		return pageCode;
	}

	/**
	 * @param pageCode
	 *            the pageCode to set
	 */
	public void setPageCode(String pageCode) {
		this.pageCode = pageCode;
	}

	/**
	 * @return the pageURL
	 */
	public String getPageURL() {
		return pageURL;
	}

	/**
	 * @param pageURL
	 *            the pageURL to set
	 */
	public void setPageURL(String pageURL) {
		this.pageURL = pageURL;
	}

	/**
	 * @return the pageDisplayName
	 */
	public String getPageDisplayName() {
		return pageDisplayName;
	}

	/**
	 * @param pageDisplayName
	 *            the pageDisplayName to set
	 */
	public void setPageDisplayName(String pageDisplayName) {
		this.pageDisplayName = pageDisplayName;
	}

	/**
	 * @return the pageNavigationId
	 */
	public Long getPageNavigationId() {
		return pageNavigationId;
	}

	/**
	 * @param pageNavigationId
	 *            the pageNavigationId to set
	 */
	public void setPageNavigationId(Long pageNavigationId) {
		this.pageNavigationId = pageNavigationId;
	}

	/**
	 * @return the roleList
	 */
	public ArrayList<RoleDVO> getRoleList() {
		if (roleList == null) {
			roleList = new ArrayList<RoleDVO>();
		}
		return roleList;
	}

	/**
	 * @param roleList
	 *            the roleList to set
	 */
	public void setRoleList(ArrayList<RoleDVO> roleList) {
		this.roleList = roleList;
	}

}
