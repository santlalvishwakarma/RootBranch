package com.web.common.dvo.opr.systemowner;

import java.util.ArrayList;
import java.util.List;

import com.web.common.dvo.common.OperationalDataValueObject;
import com.web.common.dvo.systemowner.UnitDVO;

public class UnitOpr extends OperationalDataValueObject {

	private static final long serialVersionUID = 6335984695516954828L;
	private UnitDVO unitRecord;
	private List<UnitDVO> unitList;
	private UnitDVO unitAddEditRecord;

	public UnitDVO getUnitRecord() {
		if (unitRecord == null) {
			unitRecord = new UnitDVO();
		}
		return unitRecord;
	}

	public void setUnitRecord(UnitDVO unitRecord) {
		this.unitRecord = unitRecord;
	}

	public List<UnitDVO> getUnitList() {
		if (unitList == null) {
			unitList = new ArrayList<UnitDVO>();
		}
		return unitList;
	}

	public void setUnitList(List<UnitDVO> unitList) {
		this.unitList = unitList;
	}

	public UnitDVO getUnitAddEditRecord() {
		if (unitAddEditRecord == null) {
			unitAddEditRecord = new UnitDVO();
		}
		return unitAddEditRecord;
	}

	public void setUnitAddEditRecord(UnitDVO unitAddEditRecord) {
		this.unitAddEditRecord = unitAddEditRecord;
	}

}
