package com.web.common.dvo.systemowner;

import com.web.common.dvo.common.BaseDVO;

public class PropertyDVO extends BaseDVO {

	private static final long serialVersionUID = 1911185714579524670L;

	private UomTypeDVO uomTypeRecord;
	private String propertyGroup;
	private Integer propertySequence;
	private boolean disabled;

	public String getPropertyGroup() {
		return propertyGroup;
	}

	public void setPropertyGroup(String propertyGroup) {
		this.propertyGroup = propertyGroup;
	}

	public Integer getPropertySequence() {
		return propertySequence;
	}

	public void setPropertySequence(Integer propertySequence) {
		this.propertySequence = propertySequence;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	/**
	 * @return the uomRecord
	 */
	public UomTypeDVO getUomTypeRecord() {
		if (uomTypeRecord == null) {
			uomTypeRecord = new UomTypeDVO();
		}
		return uomTypeRecord;
	}

	/**
	 * @param uomRecord
	 *            the uomRecord to set
	 */
	public void setUomTypeRecord(UomTypeDVO uomRecord) {
		uomTypeRecord = uomRecord;
	}

}
