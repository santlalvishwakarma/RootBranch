package com.web.common.dvo.opr.systemowner;

import java.util.ArrayList;

import com.web.common.dvo.common.CatalogDVO;
import com.web.common.dvo.common.CatalogWebsiteMappingDVO;
import com.web.common.dvo.common.OperationalDataValueObject;
import com.web.common.dvo.common.WebsiteDVO;
import com.web.util.deepcopy.DeepCopy;

public class WebsiteOpr extends OperationalDataValueObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 949511378458814637L;
	private WebsiteDVO websiteRecord;
	private WebsiteDVO quickEditWebsiteRecord;
	private ArrayList<WebsiteDVO> websiteList;
	private CatalogDVO catalogRecord;
	private ArrayList<CatalogWebsiteMappingDVO> catalogWebsiteList;

	public CatalogDVO getCatalogRecord() {
		if (catalogRecord == null) {
			catalogRecord = new CatalogDVO();
		}
		return catalogRecord;
	}

	public void setCatalogRecord(CatalogDVO catalogRecord) {
		this.catalogRecord = catalogRecord;
	}

	public WebsiteDVO getWebsiteRecord() {
		if (websiteRecord == null) {
			websiteRecord = new WebsiteDVO();
		}
		return websiteRecord;
	}

	public void setWebsiteRecord(WebsiteDVO websiteRecord) {
		this.websiteRecord = websiteRecord;
	}

	public ArrayList<WebsiteDVO> getWebsiteList() {
		if (websiteList == null) {
			websiteList = new ArrayList<WebsiteDVO>();
		}
		return websiteList;
	}

	public void setWebsiteList(ArrayList<WebsiteDVO> websiteList) {
		this.websiteList = websiteList;
	}

	public WebsiteDVO getQuickEditWebsiteRecord() {
		if (quickEditWebsiteRecord == null) {
			quickEditWebsiteRecord = new WebsiteDVO();
		}
		return quickEditWebsiteRecord;
	}

	public void setQuickEditWebsiteRecord(WebsiteDVO quickEditWebsiteRecord) {
		this.quickEditWebsiteRecord = (WebsiteDVO) DeepCopy.copy(quickEditWebsiteRecord);
	}

	public ArrayList<CatalogWebsiteMappingDVO> getCatalogWebsiteList() {
		if (catalogWebsiteList == null) {
			catalogWebsiteList = new ArrayList<CatalogWebsiteMappingDVO>();
		}
		return catalogWebsiteList;
	}

	public void setCatalogWebsiteList(ArrayList<CatalogWebsiteMappingDVO> catalogWebsiteList) {
		this.catalogWebsiteList = catalogWebsiteList;
	}

}
