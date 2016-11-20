package com.web.common.dvo.systemowner;

import com.web.common.dvo.common.BaseDVO;

public class HierarchyCategoryMappingDVO extends BaseDVO {

	private static final long serialVersionUID = 124375919051838284L;
	private HierarchyDVO hierarchyRecord;
	private CategoryDVO categoryLevelOneRecord;
	private CategoryDVO categoryLevelTwoRecord;
	private CategoryDVO categoryLevelThreeRecord;
	private CategoryDVO categoryLevelFourRecord;
	private CategoryDVO categoryLevelFiveRecord;
	private Integer categoryLevel;
	private CategoryDVO categoryRecord;

	public HierarchyDVO getHierarchyRecord() {
		if (hierarchyRecord == null) {
			hierarchyRecord = new HierarchyDVO();
		}
		return hierarchyRecord;
	}

	public void setHierarchyRecord(HierarchyDVO hierarchyRecord) {
		this.hierarchyRecord = hierarchyRecord;
	}

	public CategoryDVO getCategoryLevelOneRecord() {
		if (categoryLevelOneRecord == null) {
			categoryLevelOneRecord = new CategoryDVO();
		}
		return categoryLevelOneRecord;
	}

	public void setCategoryLevelOneRecord(CategoryDVO categoryLevelOneRecord) {
		this.categoryLevelOneRecord = categoryLevelOneRecord;
	}

	public CategoryDVO getCategoryLevelTwoRecord() {
		if (categoryLevelTwoRecord == null) {
			categoryLevelTwoRecord = new CategoryDVO();
		}
		return categoryLevelTwoRecord;
	}

	public void setCategoryLevelTwoRecord(CategoryDVO categoryLevelTwoRecord) {
		this.categoryLevelTwoRecord = categoryLevelTwoRecord;
	}

	public CategoryDVO getCategoryLevelThreeRecord() {
		if (categoryLevelThreeRecord == null) {
			categoryLevelThreeRecord = new CategoryDVO();
		}
		return categoryLevelThreeRecord;
	}

	public void setCategoryLevelThreeRecord(CategoryDVO categoryLevelThreeRecord) {
		this.categoryLevelThreeRecord = categoryLevelThreeRecord;
	}

	public CategoryDVO getCategoryLevelFourRecord() {
		if (categoryLevelFourRecord == null) {
			categoryLevelFourRecord = new CategoryDVO();
		}
		return categoryLevelFourRecord;
	}

	public void setCategoryLevelFourRecord(CategoryDVO categoryLevelFourRecord) {
		this.categoryLevelFourRecord = categoryLevelFourRecord;
	}

	public CategoryDVO getCategoryLevelFiveRecord() {
		if (categoryLevelFiveRecord == null) {
			categoryLevelFiveRecord = new CategoryDVO();
		}
		return categoryLevelFiveRecord;
	}

	public void setCategoryLevelFiveRecord(CategoryDVO categoryLevelFiveRecord) {
		this.categoryLevelFiveRecord = categoryLevelFiveRecord;
	}

	public Integer getCategoryLevel() {
		return categoryLevel;
	}

	public void setCategoryLevel(Integer categoryLevel) {
		this.categoryLevel = categoryLevel;
	}

	public CategoryDVO getCategoryRecord() {
		if (categoryRecord == null) {
			categoryRecord = new CategoryDVO();
		}
		return categoryRecord;
	}

	public void setCategoryRecord(CategoryDVO categoryRecord) {
		this.categoryRecord = categoryRecord;
	}

}
