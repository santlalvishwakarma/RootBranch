package com.web.model.dvo.systemowner;

import com.web.model.dvo.BaseDVO;

public class PropertyDVO extends BaseDVO {

	private static final long serialVersionUID = 1911185714579524670L;

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


}
