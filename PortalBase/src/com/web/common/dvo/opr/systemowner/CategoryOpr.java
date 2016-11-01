package com.web.common.dvo.opr.systemowner;

import java.util.ArrayList;
import java.util.List;

import com.web.common.dvo.common.OperationalDataValueObject;
import com.web.common.dvo.systemowner.CategoryDVO;

public class CategoryOpr extends OperationalDataValueObject {

	private static final long serialVersionUID = -4500345020599911808L;

	private CategoryDVO categoryRecord;
	private CategoryDVO selectedCategoryRecord;
	private List<CategoryDVO> categoryList;

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

}
