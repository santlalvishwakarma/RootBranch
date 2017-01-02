package com.web.common.dvo.opr.systemowner;

import java.util.ArrayList;
import java.util.List;

import com.web.common.dvo.common.OperationalDataValueObject;
import com.web.common.dvo.systemowner.ColorDVO;

public class ColorOpr extends OperationalDataValueObject {

	private static final long serialVersionUID = 4422271397047166015L;
	private ColorDVO colorRecord;
	private List<ColorDVO> colorList;
	private ColorDVO colorAddEditRecord;

	public ColorDVO getColorRecord() {
		if (colorRecord == null) {
			colorRecord = new ColorDVO();
		}
		return colorRecord;
	}

	public void setColorRecord(ColorDVO colorRecord) {
		this.colorRecord = colorRecord;
	}

	public List<ColorDVO> getColorList() {
		if (colorList == null) {
			colorList = new ArrayList<ColorDVO>();
		}
		return colorList;
	}

	public void setColorList(List<ColorDVO> colorList) {
		this.colorList = colorList;
	}

	public ColorDVO getColorAddEditRecord() {
		if (colorAddEditRecord == null) {
			colorAddEditRecord = new ColorDVO();
		}
		return colorAddEditRecord;
	}

	public void setColorAddEditRecord(ColorDVO colorAddEditRecord) {
		this.colorAddEditRecord = colorAddEditRecord;
	}

}
