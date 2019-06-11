package com.web.model.dvo.systemowner;

import com.web.model.dvo.BaseDVO;
import com.web.model.dvo.CategoryDVO;

public class CategoryPropertyMappingDVO extends BaseDVO {

	private static final long serialVersionUID = 1873027706379773321L;
	private CategoryDVO categoryRecord;
	private PropertyDVO propertyRecord;
	private Integer propertySequence;

	public CategoryDVO getCategoryRecord() {
		if (categoryRecord == null) {
			categoryRecord = new CategoryDVO();
		}
		return categoryRecord;
	}

	public void setCategoryRecord(CategoryDVO categoryRecord) {
		this.categoryRecord = categoryRecord;
	}

	public PropertyDVO getPropertyRecord() {
		if (propertyRecord == null) {
			propertyRecord = new PropertyDVO();
		}
		return propertyRecord;
	}

	public void setPropertyRecord(PropertyDVO propertyRecord) {
		this.propertyRecord = propertyRecord;
	}

	public Integer getPropertySequence() {
		return propertySequence;
	}

	public void setPropertySequence(Integer propertySequence) {
		this.propertySequence = propertySequence;
	}

}
