package com.web.common.dvo.systemowner;

import com.web.common.dvo.common.BaseDVO;

public class CategorySizeMappingDVO extends BaseDVO {

	private static final long serialVersionUID = 719316145023275959L;
	private CategoryDVO categoryRecord;
	private Float sizeValue1;
	private Float sizeValue2;
	private UnitDVO unitRecord;

	public CategoryDVO getCategoryRecord() {
		if (categoryRecord == null) {
			categoryRecord = new CategoryDVO();
		}
		return categoryRecord;
	}

	public void setCategoryRecord(CategoryDVO categoryRecord) {
		this.categoryRecord = categoryRecord;
	}

	public Float getSizeValue1() {
		return sizeValue1;
	}

	public void setSizeValue1(Float sizeValue1) {
		this.sizeValue1 = sizeValue1;
	}

	public Float getSizeValue2() {
		return sizeValue2;
	}

	public void setSizeValue2(Float sizeValue2) {
		this.sizeValue2 = sizeValue2;
	}

	public UnitDVO getUnitRecord() {
		if (unitRecord == null) {
			unitRecord = new UnitDVO();
		}
		return unitRecord;
	}

	public void setUnitRecord(UnitDVO unitRecord) {
		this.unitRecord = unitRecord;
	}

}
