package com.web.model.dvo;

public class CategoryLevelDVO extends BaseDVO {

	private static final long serialVersionUID = 4900768993826543652L;

	private CategoryDVO categoryRecord;
	private Integer levelNo;

	public CategoryDVO getCategoryRecord() {
		if (categoryRecord == null) {
			categoryRecord = new CategoryDVO();
		}
		return categoryRecord;
	}

	public void setCategoryRecord(CategoryDVO categoryRecord) {
		this.categoryRecord = categoryRecord;
	}

	public Integer getLevelNo() {
		return levelNo;
	}

	public void setLevelNo(Integer levelNo) {
		this.levelNo = levelNo;
	}

	// private String comments;

	// private ArrayList<ProductCategoryPropertiesMappingDVO>
	// productCategoryPropertiesMappingList;

	// private String categoryLevels;

	// private Parameter allocationBasedOn;

	// private String billComments;

}
