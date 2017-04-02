package com.web.common.dvo.opr.systemowner;

import java.util.ArrayList;
import java.util.List;

import com.web.common.dvo.common.OperationalDataValueObject;
import com.web.common.dvo.systemowner.SkuPropertyDVO;

public class SkuPropertyOpr extends OperationalDataValueObject {

	private static final long serialVersionUID = -3149643346010592282L;

	private SkuPropertyDVO skuPropertyRecord;
	private List<SkuPropertyDVO> skuPropertyList;
	private SkuPropertyDVO selectedSkuPropertyRecord;

	public SkuPropertyDVO getSkuPropertyRecord() {
		if (skuPropertyRecord == null) {
			skuPropertyRecord = new SkuPropertyDVO();
		}
		return skuPropertyRecord;
	}

	public void setSkuPropertyRecord(SkuPropertyDVO skuPropertyRecord) {
		this.skuPropertyRecord = skuPropertyRecord;
	}

	public List<SkuPropertyDVO> getSkuPropertyList() {
		if (skuPropertyList == null) {
			skuPropertyList = new ArrayList<>();
		}
		return skuPropertyList;
	}

	public void setSkuPropertyList(List<SkuPropertyDVO> skuPropertyList) {
		this.skuPropertyList = skuPropertyList;
	}

	public SkuPropertyDVO getSelectedSkuPropertyRecord() {
		if (selectedSkuPropertyRecord == null) {
			selectedSkuPropertyRecord = new SkuPropertyDVO();
		}
		return selectedSkuPropertyRecord;
	}

	public void setSelectedSkuPropertyRecord(SkuPropertyDVO selectedSkuPropertyRecord) {
		this.selectedSkuPropertyRecord = selectedSkuPropertyRecord;
	}

}
