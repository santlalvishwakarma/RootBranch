package com.web.common.dvo.opr.systemowner;

import java.util.ArrayList;
import java.util.List;

import com.web.common.dvo.common.OperationalDataValueObject;
import com.web.common.dvo.systemowner.HierarchyDVO;

public class HierarchyOpr extends OperationalDataValueObject {

	private static final long serialVersionUID = 8360450671103851881L;

	private HierarchyDVO hierarchyRecord;
	private List<HierarchyDVO> hierarchyList;
	private HierarchyDVO selectedhierarchyRecord;

	public HierarchyDVO getHierarchyRecord() {
		if (hierarchyRecord == null) {
			hierarchyRecord = new HierarchyDVO();
		}
		return hierarchyRecord;
	}

	public void setHierarchyRecord(HierarchyDVO hierarchyRecord) {
		this.hierarchyRecord = hierarchyRecord;
	}

	public List<HierarchyDVO> getHierarchyList() {
		if (hierarchyList == null) {
			hierarchyList = new ArrayList<HierarchyDVO>();
		}
		return hierarchyList;
	}

	public void setHierarchyList(List<HierarchyDVO> hierarchyList) {
		this.hierarchyList = hierarchyList;
	}

	public HierarchyDVO getSelectedhierarchyRecord() {
		if (selectedhierarchyRecord == null) {
			selectedhierarchyRecord = new HierarchyDVO();
		}
		return selectedhierarchyRecord;
	}

	public void setSelectedhierarchyRecord(HierarchyDVO selectedhierarchyRecord) {
		this.selectedhierarchyRecord = selectedhierarchyRecord;
	}

}
