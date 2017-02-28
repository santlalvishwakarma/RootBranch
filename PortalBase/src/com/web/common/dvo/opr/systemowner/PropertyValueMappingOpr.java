package com.web.common.dvo.opr.systemowner;

import java.util.ArrayList;
import java.util.List;

import com.web.common.dvo.common.OperationalDataValueObject;
import com.web.common.dvo.systemowner.PropertyValueMappingDVO;

public class PropertyValueMappingOpr extends OperationalDataValueObject {

	private static final long serialVersionUID = -4686866216857750255L;
	private PropertyValueMappingDVO propertyValueMappingRecord;
	private List<PropertyValueMappingDVO> propertyValueMappingList;
	private PropertyValueMappingDVO selectedPropertyValueMappingRecord;

	public PropertyValueMappingDVO getPropertyValueMappingRecord() {
		if (propertyValueMappingRecord == null) {
			propertyValueMappingRecord = new PropertyValueMappingDVO();
		}
		return propertyValueMappingRecord;
	}

	public void setPropertyValueMappingRecord(PropertyValueMappingDVO propertyValueMappingRecord) {
		this.propertyValueMappingRecord = propertyValueMappingRecord;
	}

	public List<PropertyValueMappingDVO> getPropertyValueMappingList() {
		if (propertyValueMappingList == null) {
			propertyValueMappingList = new ArrayList<PropertyValueMappingDVO>();
		}
		return propertyValueMappingList;
	}

	public void setPropertyValueMappingList(List<PropertyValueMappingDVO> propertyValueMappingList) {
		this.propertyValueMappingList = propertyValueMappingList;
	}

	public PropertyValueMappingDVO getSelectedPropertyValueMappingRecord() {
		if (selectedPropertyValueMappingRecord == null) {
			selectedPropertyValueMappingRecord = new PropertyValueMappingDVO();
		}
		return selectedPropertyValueMappingRecord;
	}

	public void setSelectedPropertyValueMappingRecord(PropertyValueMappingDVO selectedPropertyValueMappingRecord) {
		this.selectedPropertyValueMappingRecord = selectedPropertyValueMappingRecord;
	}

}
