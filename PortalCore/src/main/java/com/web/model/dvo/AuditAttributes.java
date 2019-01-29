package com.web.model.dvo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AuditAttributes extends DataValueObject {

	private static final long serialVersionUID = 8572804641868370387L;

	private Date createdDate;

	private Date lastModifiedDate;

	private String createdBy;

	private String modifiedBy;

	private String createdDateString;

	private String lastModifiedDateString;

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getCreatedDateString() {
		if (createdDate != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			createdDateString = sdf.format(createdDate);
		} else {
			createdDateString = "";
		}
		return createdDateString;
	}

	public void setCreatedDateString(String createdDateString) {
		this.createdDateString = createdDateString;
	}

	public String getLastModifiedDateString() {
		if (lastModifiedDate != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			lastModifiedDateString = sdf.format(lastModifiedDate);
		} else {
			lastModifiedDateString = "";
		}
		return lastModifiedDateString;
	}

	public void setLastModifiedDateString(String lastModifiedDateString) {
		this.lastModifiedDateString = lastModifiedDateString;
	}
}
