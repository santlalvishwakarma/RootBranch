package com.web.common.dvo.opr.systemowner;

import java.util.ArrayList;
import java.util.List;

import com.web.common.dvo.common.OperationalDataValueObject;
import com.web.common.dvo.systemowner.SizeDVO;

public class SizeOpr extends OperationalDataValueObject {

	private static final long serialVersionUID = 6315509217120631754L;
	private SizeDVO sizeRecord;
	private List<SizeDVO> sizeList;

	public SizeDVO getSizeRecord() {
		if (sizeRecord == null) {
			sizeRecord = new SizeDVO();
		}
		return sizeRecord;
	}

	public void setSizeRecord(SizeDVO sizeRecord) {
		this.sizeRecord = sizeRecord;
	}

	public List<SizeDVO> getSizeList() {
		if (sizeList == null) {
			sizeList = new ArrayList<SizeDVO>();
		}
		return sizeList;
	}

	public void setSizeList(List<SizeDVO> sizeList) {
		this.sizeList = sizeList;
	}

}
