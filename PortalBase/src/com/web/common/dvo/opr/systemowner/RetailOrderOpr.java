package com.web.common.dvo.opr.systemowner;

import java.util.ArrayList;
import java.util.List;

import com.web.common.dvo.retail.modules.RetailOrderDVO;

public class RetailOrderOpr extends OrderOpr {

	private static final long serialVersionUID = 5864629730668222429L;

	private RetailOrderDVO retailOrderRecord;
	private RetailOrderDVO selectedRetailOrderRecord;
	private List<RetailOrderDVO> retailOrderList;

	public RetailOrderDVO getRetailOrderRecord() {
		if (retailOrderRecord == null) {
			retailOrderRecord = new RetailOrderDVO();
		}
		return retailOrderRecord;
	}

	public void setRetailOrderRecord(RetailOrderDVO retailOrderRecord) {
		this.retailOrderRecord = retailOrderRecord;
	}

	public RetailOrderDVO getSelectedRetailOrderRecord() {
		if (selectedRetailOrderRecord == null) {
			selectedRetailOrderRecord = new RetailOrderDVO();
		}
		return selectedRetailOrderRecord;
	}

	public void setSelectedRetailOrderRecord(RetailOrderDVO selectedRetailOrderRecord) {
		this.selectedRetailOrderRecord = selectedRetailOrderRecord;
	}

	public List<RetailOrderDVO> getRetailOrderList() {
		if (retailOrderList == null) {
			retailOrderList = new ArrayList<RetailOrderDVO>();
		}
		return retailOrderList;
	}

	public void setRetailOrderList(List<RetailOrderDVO> retailOrderList) {
		this.retailOrderList = retailOrderList;
	}

}
