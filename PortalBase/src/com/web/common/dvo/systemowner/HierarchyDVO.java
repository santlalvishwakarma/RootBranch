package com.web.common.dvo.systemowner;

import com.web.common.dvo.common.BaseDVO;
import com.web.common.dvo.common.Parameter;

public class HierarchyDVO extends BaseDVO {

	private static final long serialVersionUID = -1978672298186636620L;
	private CategoryDVO categoryRecord;
	private Parameter categoryLevel;
	private Long sequence;

	public CategoryDVO getCategoryRecord() {
		if (categoryRecord == null) {
			categoryRecord = new CategoryDVO();
		}
		return categoryRecord;
	}

	public void setCategoryRecord(CategoryDVO categoryRecord) {
		this.categoryRecord = categoryRecord;
	}

	public Parameter getCategoryLevel() {
		if (categoryLevel == null) {
			categoryLevel = new Parameter();
		}
		return categoryLevel;
	}

	public void setCategoryLevel(Parameter categoryLevel) {
		this.categoryLevel = categoryLevel;
	}

	public Long getSequence() {
		return sequence;
	}

	public void setSequence(Long sequence) {
		this.sequence = sequence;
	}

}
