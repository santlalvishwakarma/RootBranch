package com.web.common.dvo.opr.systemowner;

import java.util.ArrayList;
import java.util.List;

import com.web.common.dvo.common.OperationalDataValueObject;
import com.web.common.dvo.systemowner.MaterialDVO;

public class MaterialOpr extends OperationalDataValueObject {

	private static final long serialVersionUID = 3804143009775838067L;
	private MaterialDVO materialRecord;
	private List<MaterialDVO> materialList;
	private MaterialDVO materialAddEditRecord;

	public MaterialDVO getMaterialRecord() {
		if (materialRecord == null) {
			materialRecord = new MaterialDVO();
		}
		return materialRecord;
	}

	public void setMaterialRecord(MaterialDVO materialRecord) {
		this.materialRecord = materialRecord;
	}

	public List<MaterialDVO> getMaterialList() {
		if (materialList == null) {
			materialList = new ArrayList<MaterialDVO>();
		}
		return materialList;
	}

	public void setMaterialList(List<MaterialDVO> materialList) {
		this.materialList = materialList;
	}

	public MaterialDVO getMaterialAddEditRecord() {
		if (materialAddEditRecord == null) {
			materialAddEditRecord = new MaterialDVO();
		}
		return materialAddEditRecord;
	}

	public void setMaterialAddEditRecord(MaterialDVO materialAddEditRecord) {
		this.materialAddEditRecord = materialAddEditRecord;
	}

}
