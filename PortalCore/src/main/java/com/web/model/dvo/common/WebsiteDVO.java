package com.web.model.dvo.common;

import java.util.ArrayList;

import com.web.model.dvo.BaseDVO;

public class WebsiteDVO extends BaseDVO {

	private static final long serialVersionUID = 5238071705494596925L;
	String siteURL;
	private ArrayList<CatalogWebsiteMappingDVO> mappedCatalogList;

	/**
	 * @return the siteURL
	 */
	public String getSiteURL() {
		return siteURL;
	}

	/**
	 * @param siteURL
	 *            the siteURL to set
	 */
	public void setSiteURL(String siteURL) {
		this.siteURL = siteURL;
	}

	/**
	 * @return the mappedCatalogList
	 */
	public ArrayList<CatalogWebsiteMappingDVO> getMappedCatalogList() {
		if (mappedCatalogList == null) {
			mappedCatalogList = new ArrayList<CatalogWebsiteMappingDVO>();
		}
		return mappedCatalogList;
	}

	/**
	 * @param mappedCatalogList
	 *            the mappedCatalogList to set
	 */
	public void setMappedCatalogList(ArrayList<CatalogWebsiteMappingDVO> mappedCatalogList) {
		this.mappedCatalogList = mappedCatalogList;
	}
}
