package com.web.common.dvo.common;

import java.util.Date;

public class Parameter extends DataValueObject {

	private static final long serialVersionUID = -636500477404220330L;
	private Integer parameterID;
	private String parameterCode;
	private String parameterDescription;
	private String parameterDataType; // This will be either D, N or S (for
	// Date, Numeric and String
	// respectively)
	private String parameterStringValue;
	private Float parameterNumberValue;
	private Date parameterDateValue;
	private Boolean parameterBooleanValue;
	private String languageCode;
	private Integer sequenceNumber;
	private Date effectiveFrom;
	private Date effectiveTo;

	public Parameter() {

	}

	public Parameter(String parameterCode, String parameterDescription) {
		super();
		this.parameterCode = parameterCode;
		this.parameterDescription = parameterDescription;
	}

	public Parameter(String parameterCode, String parameterDescription, Integer parameterId, Integer sequenceNumber,
			String stringValue, Float numberValue, Date dateValue, String dataType) {
		super();
		this.parameterCode = parameterCode;
		this.parameterDescription = parameterDescription;
		parameterID = parameterId;
		parameterStringValue = stringValue;
		parameterNumberValue = numberValue;
		parameterDateValue = dateValue;
		this.sequenceNumber = sequenceNumber;
		parameterDataType = dataType;
	}

	public Integer getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(Integer sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public Integer getParameterID() {
		return parameterID;
	}

	public void setParameterID(Integer parameterID) {
		this.parameterID = parameterID;
	}

	public String getParameterCode() {
		return parameterCode;
	}

	public void setParameterCode(String parameterCode) {
		this.parameterCode = parameterCode;
	}

	public String getParameterDescription() {
		return parameterDescription;
	}

	public void setParameterDescription(String parameterDescription) {
		this.parameterDescription = parameterDescription;
	}

	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	public String getParameterDataType() {
		return parameterDataType;
	}

	public void setParameterDataType(String parameterDataType) {
		this.parameterDataType = parameterDataType;
	}

	public Date getParameterDateValue() {
		return parameterDateValue;
	}

	public void setParameterDateValue(Date parameterDateValue) {
		this.parameterDateValue = parameterDateValue;
	}

	public Float getParameterNumberValue() {
		return parameterNumberValue;
	}

	public void setParameterNumberValue(Float parameterNumberValue) {
		this.parameterNumberValue = parameterNumberValue;

	}

	public Boolean getParameterBooleanValue() {
		return parameterBooleanValue;
	}

	public void setParameterBooleanValue(Boolean parameterBooleanValue) {
		this.parameterBooleanValue = parameterBooleanValue;
	}

	public String getParameterNumberValueStringValue() {
		String parameterNumberValueString = null;
		if (parameterNumberValue != null) {
			parameterNumberValueString = parameterNumberValue.toString();
		}
		return parameterNumberValueString;
	}

	public void setParameterNumberValueStringValue(String parameterNumberValueString) {
		if (parameterNumberValueString != null) {
			parameterNumberValue = Float.valueOf(parameterNumberValueString);
		}
	}

	public Long getParameterNumberValueLongValue() {
		Long parameterNumberValueLong = null;
		if (parameterNumberValue != null) {
			parameterNumberValueLong = Long.valueOf(parameterNumberValue.longValue());
		}
		return parameterNumberValueLong;

	}

	public void setParameterNumberValueLongValue(Long parameterNumberValueLong) {
		if (parameterNumberValueLong != null) {
			parameterNumberValue = Float.valueOf(parameterNumberValueLong.floatValue());
		}
	}

	public String getParameterStringValue() {
		return parameterStringValue;
	}

	public void setParameterStringValue(String parameterStringValue) {
		this.parameterStringValue = parameterStringValue;
	}

	public Long getParameterIDLongValue() {
		Long parameterIDLongValue = null;
		if (parameterID != null) {
			parameterIDLongValue = Long.valueOf(parameterID.toString());
		}
		return parameterIDLongValue;
	}

	public void setParameterIDLongValue(Long parameterIDLongValue) {
		if (parameterIDLongValue != null) {
			parameterID = Integer.valueOf(parameterIDLongValue.toString());
		}
	}

	public Date getEffectiveFrom() {
		return effectiveFrom;
	}

	public void setEffectiveFrom(Date effectiveFrom) {
		this.effectiveFrom = effectiveFrom;
	}

	public Date getEffectiveTo() {
		return effectiveTo;
	}

	public void setEffectiveTo(Date effectiveTo) {
		this.effectiveTo = effectiveTo;
	}
}
