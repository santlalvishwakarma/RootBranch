package com.web.common.dvo.retail.modules;

import com.web.common.dvo.common.BaseDVO;

public class GuestDVO extends BaseDVO {

	private static final long serialVersionUID = -5420398086282614543L;
	private String name;
	private String emailId;
	private String phoneNumber;

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

}
