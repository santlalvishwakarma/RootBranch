package com.web.common.dvo.common;

public class OperationalAttributes extends DataValueObject {

	private static final long serialVersionUID = -3507746259105983034L;

	private Boolean recordCreated;
	private Boolean recordUpdated;
	private Boolean recordDeleted;
	private Boolean recordSelected;
	private Boolean recordPopulated;

	private Parameter recordCreatedParameter;
	private Parameter recordUpdatedParameter;
	private Parameter recordDeletedParameter;
	private Parameter recordSelectedParameter;
	private Parameter recordPopulatedParameter;

	public Parameter getRecordCreatedParameter() {
		if (recordCreatedParameter == null) {
			recordCreatedParameter = new Parameter();
		}
		return recordCreatedParameter;
	}

	public void setRecordCreatedParameter(Parameter recordCreatedParameter) {
		this.recordCreatedParameter = recordCreatedParameter;
	}

	public Parameter getRecordDeletedParameter() {
		if (recordDeletedParameter == null) {
			recordDeletedParameter = new Parameter();
		}
		return recordDeletedParameter;
	}

	public void setRecordDeletedParameter(Parameter recordDeletedParameter) {
		this.recordDeletedParameter = recordDeletedParameter;
	}

	public Parameter getRecordPopulatedParameter() {
		if (recordPopulatedParameter == null) {
			recordPopulatedParameter = new Parameter();
		}
		return recordPopulatedParameter;
	}

	public void setRecordPopulatedParameter(Parameter recordPopulatedParameter) {
		this.recordPopulatedParameter = recordPopulatedParameter;
	}

	public Parameter getRecordSelectedParameter() {
		if (recordSelectedParameter == null) {
			recordSelectedParameter = new Parameter();
		}
		return recordSelectedParameter;
	}

	public void setRecordSelectedParameter(Parameter recordSelectedParameter) {
		this.recordSelectedParameter = recordSelectedParameter;
	}

	public Parameter getRecordUpdatedParameter() {
		if (recordUpdatedParameter == null) {
			recordUpdatedParameter = new Parameter();
		}
		return recordUpdatedParameter;
	}

	public void setRecordUpdatedParameter(Parameter recordUpdatedParameter) {
		this.recordUpdatedParameter = recordUpdatedParameter;
	}

	public Boolean getRecordCreated() {
		if (recordCreated == null) {
			recordCreated = Boolean.FALSE;
		}
		return recordCreated;
	}

	public void setRecordCreated(Boolean recordCreated) {
		this.recordCreated = recordCreated;
	}

	public Boolean getRecordDeleted() {
		if (recordDeleted == null) {
			recordDeleted = Boolean.FALSE;
		}
		return recordDeleted;
	}

	public void setRecordDeleted(Boolean recordDeleted) {
		this.recordDeleted = recordDeleted;
	}

	public Boolean getRecordPopulated() {
		if (recordPopulated == null) {
			recordPopulated = Boolean.FALSE;
		}
		return recordPopulated;
	}

	public void setRecordPopulated(Boolean recordPopulated) {
		this.recordPopulated = recordPopulated;
	}

	public Boolean getRecordSelected() {
		if (recordSelected == null) {
			recordSelected = Boolean.FALSE;
		}
		return recordSelected;
	}

	public void setRecordSelected(Boolean recordSelected) {
		this.recordSelected = recordSelected;
	}

	public Boolean getRecordUpdated() {
		if (recordUpdated == null) {
			recordUpdated = Boolean.FALSE;
		}
		return recordUpdated;
	}

	public void setRecordUpdated(Boolean recordUpdated) {
		this.recordUpdated = recordUpdated;
	}
}
