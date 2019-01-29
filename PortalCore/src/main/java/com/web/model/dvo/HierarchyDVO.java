package com.web.model.dvo;

import java.util.ArrayList;
import java.util.List;

public class HierarchyDVO extends BaseDVO {

	private static final long serialVersionUID = -1978672298186636620L;
	private CategoryDVO categoryRecord;
	private Parameter categoryLevel;
	private Long sequence;

	private List<HierarchyCategoryMappingDVO> hierarchyCategoryMappingList;

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
