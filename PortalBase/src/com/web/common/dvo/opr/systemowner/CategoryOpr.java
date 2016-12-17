package com.web.common.dvo.opr.systemowner;

import java.util.ArrayList;
import java.util.List;

import com.web.common.dvo.common.OperationalDataValueObject;
import com.web.common.dvo.systemowner.CategoryDVO;
import com.web.common.dvo.systemowner.CategoryLevelDVO;
import com.web.common.dvo.systemowner.HierarchyCategoryMappingDVO;

public class CategoryOpr extends OperationalDataValueObject {

	private static final long serialVersionUID = -4500345020599911808L;

	private CategoryDVO categoryRecord;
	private CategoryDVO selectedCategoryRecord;
	private List<CategoryDVO> categoryList;
	private List<CategoryDVO> publishToHomeCategoryList;
	private Integer hierarchyCategoryMappingLevelNo;
	private HierarchyCategoryMappingDVO selectedHierarchyCategoryMappingRecord;
	private List<HierarchyCategoryMappingDVO> mappedHierarchyCategoryMappingList;
	private CategoryLevelDVO categoryLevelRecord;

	public CategoryDVO getCategoryRecord() {
		if (categoryRecord == null) {
			categoryRecord = new CategoryDVO();
		}
		return categoryRecord;
	}

	public void setCategoryRecord(CategoryDVO categoryRecord) {
		this.categoryRecord = categoryRecord;
	}

	public CategoryDVO getSelectedCategoryRecord() {
		if (selectedCategoryRecord == null) {
			selectedCategoryRecord = new CategoryDVO();
		}
		return selectedCategoryRecord;
	}

	public void setSelectedCategoryRecord(CategoryDVO selectedCategoryRecord) {
		this.selectedCategoryRecord = selectedCategoryRecord;
	}

	public List<CategoryDVO> getCategoryList() {
		if (categoryList == null) {
			categoryList = new ArrayList<CategoryDVO>();
		}
		return categoryList;
	}

	public void setCategoryList(List<CategoryDVO> categoryList) {
		this.categoryList = categoryList;
	}

	public List<CategoryDVO> getPublishToHomeCategoryList() {
		if (publishToHomeCategoryList == null) {
			publishToHomeCategoryList = new ArrayList<CategoryDVO>();
		}
		return publishToHomeCategoryList;
	}

	public void setPublishToHomeCategoryList(List<CategoryDVO> publishToHomeCategoryList) {
		this.publishToHomeCategoryList = publishToHomeCategoryList;
	}

	public Integer getHierarchyCategoryMappingLevelNo() {
		return hierarchyCategoryMappingLevelNo;
	}

	public void setHierarchyCategoryMappingLevelNo(Integer hierarchyCategoryMappingLevelNo) {
		this.hierarchyCategoryMappingLevelNo = hierarchyCategoryMappingLevelNo;
	}

	public HierarchyCategoryMappingDVO getSelectedHierarchyCategoryMappingRecord() {
		if (selectedHierarchyCategoryMappingRecord == null) {
			selectedHierarchyCategoryMappingRecord = new HierarchyCategoryMappingDVO();
		}
		return selectedHierarchyCategoryMappingRecord;
	}

	public void setSelectedHierarchyCategoryMappingRecord(
			HierarchyCategoryMappingDVO selectedHierarchyCategoryMappingRecord) {
		this.selectedHierarchyCategoryMappingRecord = selectedHierarchyCategoryMappingRecord;
	}

	public List<HierarchyCategoryMappingDVO> getMappedHierarchyCategoryMappingList() {
		if (mappedHierarchyCategoryMappingList == null) {
			mappedHierarchyCategoryMappingList = new ArrayList<HierarchyCategoryMappingDVO>();
		}
		return mappedHierarchyCategoryMappingList;
	}

	public void setMappedHierarchyCategoryMappingList(
			List<HierarchyCategoryMappingDVO> mappedHierarchyCategoryMappingList) {
		this.mappedHierarchyCategoryMappingList = mappedHierarchyCategoryMappingList;
	}

	public CategoryLevelDVO getCategoryLevelRecord() {
		if (categoryLevelRecord == null) {
			categoryLevelRecord = new CategoryLevelDVO();
		}
		return categoryLevelRecord;
	}

	public void setCategoryLevelRecord(CategoryLevelDVO categoryLevelRecord) {
		this.categoryLevelRecord = categoryLevelRecord;
	}

}
