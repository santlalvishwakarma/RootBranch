package com.web.model.dvo.common;

import com.web.model.dvo.BaseDVO;

public class CatalogWebsiteMappingDVO extends BaseDVO {

	private static final long serialVersionUID = 3334155848634271752L;
	private CatalogDVO catalogRecord;
	private WebsiteDVO websiteRecord;

	/**
	 * @return the catalogRecord
	 */
	public CatalogDVO getCatalogRecord() {
		if (catalogRecord == null) {
			catalogRecord = new CatalogDVO();
		}
		return catalogRecord;
	}

	/**
	 * @param catalogRecord
	 *            the catalogRecord to set
	 */
	public void setCatalogRecord(CatalogDVO catalogRecord) {
		this.catalogRecord = catalogRecord;
	}

	/**
	 * @return the websiteRecord
	 */
	public WebsiteDVO getWebsiteRecord() {
		if (websiteRecord == null) {
			websiteRecord = new WebsiteDVO();
		}
		return websiteRecord;
	}

	/**
	 * @param websiteRecord
	 *            the websiteRecord to set
	 */
	public void setWebsiteRecord(WebsiteDVO websiteRecord) {
		this.websiteRecord = websiteRecord;
	}
}
