package com.web.common.dvo.systemowner;

import com.web.common.dvo.common.BaseDVO;

public class PropertyValueMappingDVO extends BaseDVO {

	private static final long serialVersionUID = 3384686881478271718L;
	private SizeDVO sizeRecord;
	private UnitDVO unitRecord;

	public SizeDVO getSizeRecord() {
		if (sizeRecord == null) {
			sizeRecord = new SizeDVO();
		}
		return sizeRecord;
	}

	public void setSizeRecord(SizeDVO sizeRecord) {
		this.sizeRecord = sizeRecord;
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
