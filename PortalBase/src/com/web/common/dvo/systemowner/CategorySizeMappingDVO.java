package com.web.common.dvo.systemowner;

import com.web.common.dvo.common.BaseDVO;

public class CategorySizeMappingDVO extends BaseDVO {

	private static final long serialVersionUID = 719316145023275959L;
	private CategoryDVO categoryRecord;
	private SizeDVO sizeRecord;

	public CategoryDVO getCategoryRecord() {
		if (categoryRecord == null) {
			categoryRecord = new CategoryDVO();
		}
		return categoryRecord;
	}

	public void setCategoryRecord(CategoryDVO categoryRecord) {
		this.categoryRecord = categoryRecord;
	}

	public SizeDVO getSizeRecord() {
		if (sizeRecord == null) {
			sizeRecord = new SizeDVO();
		}
		return sizeRecord;
	}

	public void setSizeRecord(SizeDVO sizeRecord) {
		this.sizeRecord = sizeRecord;
	}

}
