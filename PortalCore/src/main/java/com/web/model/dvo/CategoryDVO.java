package com.web.model.dvo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "category_master_new")
@AttributeOverrides({
    @AttributeOverride(name = "id", column = @Column(name = "category_id")),
    @AttributeOverride(name = "code", column = @Column(name = "category_code")),
    @AttributeOverride(name = "name", column = @Column(name = "category_name")),
    @AttributeOverride(name = "description", column = @Column(name = "category_description")),
    @AttributeOverride(name = "active", column = @Column(name = "is_active"))
})
public class CategoryDVO extends BaseDVO {
	private static final long serialVersionUID = -191198761087365578L;

	@Transient
	private String extraInformation;
	@Transient
	private Integer deliveryTime;
	private String seoTitle;
	private String seoDescription;
	private String imageUrl;
	private String seoKeyword;
	@Transient
	private Boolean selectedCategory;
	private Boolean publishToHomePage;
	private Integer publishPosition;
	@Transient
	private CategoryDVO publishCategoryRecord;
	@Transient
	private List<CategoryLevelDVO> categoryLevels;
	@Transient
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

	public Boolean getPublishToHomePage() {
		return publishToHomePage;
	}

	public void setPublishToHomePage(Boolean publishToHomePage) {
		this.publishToHomePage = publishToHomePage;
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
