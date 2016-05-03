package com.web.common.dvo.common;

import java.util.ArrayList;

public class CatalogDVO extends BaseDVO {

	private static final long serialVersionUID = -2353846966708850544L;
	private ArrayList<CatalogWebsiteMappingDVO> mappedWebsiteList;
	private boolean publishToMenu;
	private Integer sequenceNumber;
	private boolean giftVoucherDiscountApplicable;

	/**
	 * @return the mappedWebsiteList
	 */
	public ArrayList<CatalogWebsiteMappingDVO> getMappedWebsiteList() {
		if (mappedWebsiteList == null) {
			mappedWebsiteList = new ArrayList<CatalogWebsiteMappingDVO>();
		}
		return mappedWebsiteList;
	}

	/**
	 * @param mappedWebsiteList
	 *            the mappedWebsiteList to set
	 */
	public void setMappedWebsiteList(ArrayList<CatalogWebsiteMappingDVO> mappedWebsiteList) {
		this.mappedWebsiteList = mappedWebsiteList;
	}

	public String toDropDownHtml(String urlPrefix) {
		StringBuffer sb = new StringBuffer();
		sb.append("<li>");
		// sb.append("<a href=\"" + urlPrefix + code + "\" title=\"" + name +
		// "\" rel=\"" + urlPrefix + code + "\" >");
		sb.append("<a href=\"" + urlPrefix + code + "\" >");
		sb.append(name);
		sb.append("</a>");
		sb.append("</li>");
		// System.out.println("toDropDownHtml :: sb.toString() :: " +
		// sb.toString());
		return sb.toString();
	}

	public boolean isPublishToMenu() {
		return publishToMenu;
	}

	public void setPublishToMenu(boolean publishToMenu) {
		this.publishToMenu = publishToMenu;
	}

	public Integer getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(Integer sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public boolean isGiftVoucherDiscountApplicable() {
		return giftVoucherDiscountApplicable;
	}

	public void setGiftVoucherDiscountApplicable(boolean giftVoucherDiscountApplicable) {
		this.giftVoucherDiscountApplicable = giftVoucherDiscountApplicable;
	}
}
