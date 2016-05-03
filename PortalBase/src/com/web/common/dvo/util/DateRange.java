package com.web.common.dvo.util;

import java.util.Date;

import com.web.common.dvo.common.DataValueObject;

public class DateRange extends DataValueObject {

	private static final long serialVersionUID = 491055234097456520L;
	private String dateRangeType;
	private Date effectiveDateFrom = null;
	private Date effectiveDateTo = null;
	private Date dateOn = null;
	private String effectiveDateFromString = null;
	private String effectiveDateToString = null;
	private String dateOnString = null;

	public Date getEffectiveDateFrom() {
		return effectiveDateFrom;
	}

	public void setEffectiveDateFrom(Date effectiveDateFrom) {
		this.effectiveDateFrom = effectiveDateFrom;
	}

	public Date getEffectiveDateTo() {
		return effectiveDateTo;
	}

	public void setEffectiveDateTo(Date effectiveDateTo) {
		this.effectiveDateTo = effectiveDateTo;
	}

	public String getDateRangeType() {
		return dateRangeType;
	}

	public void setDateRangeType(String dateRangeType) {
		this.dateRangeType = dateRangeType;
	}

	public Date getDateOn() {
		return dateOn;
	}

	public void setDateOn(Date dateOn) {
		this.dateOn = dateOn;
	}

	public String getEffectiveDateFromString() {
		if (effectiveDateFrom != null) {
			effectiveDateFromString = effectiveDateFrom.toString();
		}
		return effectiveDateFromString;
	}

	public void setEffectiveDateFromString(String effectiveDateFromString) {
		this.effectiveDateFromString = effectiveDateFromString;
	}

	public String getEffectiveDateToString() {
		if (effectiveDateTo != null) {
			effectiveDateToString = effectiveDateTo.toString();
		}
		return effectiveDateToString;
	}

	public void setEffectiveDateToString(String effectiveDateToString) {
		this.effectiveDateToString = effectiveDateToString;
	}

	public String getDateOnString() {
		if (dateOn != null) {
			dateOnString = dateOn.toString();
		}
		return dateOnString;
	}

	public void setDateOnString(String dateOnString) {
		this.dateOnString = dateOnString;
	}

}
