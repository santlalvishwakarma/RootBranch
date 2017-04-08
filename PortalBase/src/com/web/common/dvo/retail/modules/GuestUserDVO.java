package com.web.common.dvo.retail.modules;

import com.web.common.dvo.common.BaseDVO;
import com.web.common.dvo.common.StatusDVO;

public class GuestUserDVO extends BaseDVO {

	private static final long serialVersionUID = -5420398086282614543L;
	private String name;
	private String emailId;
	private String phoneNumber;
	private StatusDVO statusRecord;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public StatusDVO getStatusRecord() {
		if (statusRecord == null) {
			statusRecord = new StatusDVO();
		}
		return statusRecord;
	}

	public void setStatusRecord(StatusDVO statusRecord) {
		this.statusRecord = statusRecord;
	}

}
