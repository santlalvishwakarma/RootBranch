package com.web.common.dvo.systemowner;

import com.web.common.dvo.common.BaseDVO;

public class CategoryDVO extends BaseDVO {
	private static final long serialVersionUID = -191198761087365578L;
	private String extraInformation;
	private Integer deliveryTime;
	private String seoTitle;
	private String seoDescription;
	private String imageUrl;
	private String seoKeyword;
	private Boolean selectedCategory;

	public String getExtraInformation() {
		return extraInformation;
	}

	public void setExtraInformation(String extraInformation) {
		this.extraInformation = extraInformation;
	}

	public Integer getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Integer deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public String getSeoTitle() {
		return seoTitle;
	}

	public void setSeoTitle(String seoTitle) {
		this.seoTitle = seoTitle;
	}

	public String getSeoDescription() {
		return seoDescription;
	}

	public void setSeoDescription(String seoDescription) {
		this.seoDescription = seoDescription;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getSeoKeyword() {
		return seoKeyword;
	}

	public void setSeoKeyword(String seoKeyword) {
		this.seoKeyword = seoKeyword;
	}

	public Boolean getSelectedCategory() {
		return selectedCategory;
	}

	public void setSelectedCategory(Boolean selectedCategory) {
		this.selectedCategory = selectedCategory;
	}

}
