package com.web.model.dvo.systemowner;

import java.util.ArrayList;

import com.web.model.dvo.BaseDVO;

public class UomTypeDVO extends BaseDVO {

	private static final long serialVersionUID = -16547395176705646L;
	private String uomType;
	private ArrayList<UomDVO> uomList;

	public UomTypeDVO() {
		super();
	}

	public UomTypeDVO(String uomType) {
		super();
		this.uomType = uomType;
	}

	/**
	 * @return the uomType
	 */
	public String getUomType() {
		return uomType;
	}

	/**
	 * @param uomType
	 *            the uomType to set
	 */
	public void setUomType(String uomType) {
		this.uomType = uomType;
	}

	/**
	 * @return the uomList
	 */
	public ArrayList<UomDVO> getUomList() {
		if (uomList == null) {
			uomList = new ArrayList<UomDVO>();
		}
		return uomList;
	}

	/**
	 * @param uomList
	 *            the uomList to set
	 */
	public void setUomList(ArrayList<UomDVO> uomList) {
		this.uomList = uomList;
	}

//	public ArrayList<SelectItem> getUomListAsSelect() {
//		ArrayList<SelectItem> uomListAsSelect = new ArrayList<SelectItem>();
//		if (uomList != null) {
//			for (int i = 0; i < uomList.size(); i++) {
//				uomListAsSelect.add(new SelectItem(uomList.get(i).getCode(), uomList.get(i).getName()));
//			}
//		}
//		return uomListAsSelect;
//	}
}
