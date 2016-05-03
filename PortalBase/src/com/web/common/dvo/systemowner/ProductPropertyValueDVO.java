package com.web.common.dvo.systemowner;

import com.web.common.dvo.common.BaseDVO;

public class ProductPropertyValueDVO extends BaseDVO {

	private static final long serialVersionUID = 3635503114321304438L;

	private String propertyValue;

	private Integer sequenceNumber;

	private ImageDVO imageRecord;

	public Integer getSequenceNumber() {
		if (sequenceNumber == null) {
			sequenceNumber = 1;
		}
		return sequenceNumber;
	}

	public void setSequenceNumber(Integer sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public String getPropertyValue() {
		return propertyValue;
	}

	public void setPropertyValue(String propertyValue) {
		this.propertyValue = propertyValue;
	}

	public ImageDVO getImageRecord() {
		if (imageRecord == null) {
			imageRecord = new ImageDVO();
		}
		return imageRecord;
	}

	public void setImageRecord(ImageDVO imageRecord) {
		this.imageRecord = imageRecord;
	}

}
