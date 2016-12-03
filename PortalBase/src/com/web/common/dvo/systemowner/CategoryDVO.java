package com.web.common.dvo.systemowner;

import java.util.ArrayList;
import java.util.List;

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
	private Boolean publishToHome;
	private Integer publishPosition;
	private CategoryDVO publishCategoryRecord;

	private List<CategoryLevelDVO> categoryLevels;
	private List<HierarchyCategoryMappingDVO> hierarchyCategoryMappingList;

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

	public List<CategoryLevelDVO> getCategoryLevels() {
		if (categoryLevels == null) {
			categoryLevels = new ArrayList<CategoryLevelDVO>();
		}
		return categoryLevels;
	}

	public void setCategoryLevels(List<CategoryLevelDVO> categoryLevels) {
		this.categoryLevels = categoryLevels;
	}

	public Boolean getPublishToHome() {
		return publishToHome;
	}

	public void setPublishToHome(Boolean publishToHome) {
		this.publishToHome = publishToHome;
	}

	public Integer getPublishPosition() {
		return publishPosition;
	}

	public void setPublishPosition(Integer publishPosition) {
		this.publishPosition = publishPosition;
	}

	public CategoryDVO getPublishCategoryRecord() {
		if (publishCategoryRecord == null) {
			publishCategoryRecord = new CategoryDVO();
		}
		return publishCategoryRecord;
	}

	public void setPublishCategoryRecord(CategoryDVO publishCategoryRecord) {
		this.publishCategoryRecord = publishCategoryRecord;
	}

	public List<HierarchyCategoryMappingDVO> getHierarchyCategoryMappingList() {
		if (hierarchyCategoryMappingList == null) {
			hierarchyCategoryMappingList = new ArrayList<HierarchyCategoryMappingDVO>();
		}
		return hierarchyCategoryMappingList;
	}

	public void setHierarchyCategoryMappingList(List<HierarchyCategoryMappingDVO> hierarchyCategoryMappingList) {
		this.hierarchyCategoryMappingList = hierarchyCategoryMappingList;

	}

}
