package com.web.common.dvo.retail.modules.user;

import java.util.ArrayList;
import java.util.Date;

import com.web.common.dvo.common.BaseDVO;
import com.web.common.dvo.common.Parameter;
import com.web.common.dvo.systemowner.BillingAddressDVO;
import com.web.common.dvo.systemowner.ShippingAddressDVO;

public class UserDVO extends BaseDVO {
	private static final long serialVersionUID = 4754494296105073068L;
	private String userLogin;
	private String loginPassword;
	private String firstName;
	private String middleName;
	private String lastName;
	private String primaryEmailId;
	private String secondaryEmailId;
	private String primaryPhoneNumber;
	private String alternatePhoneNumber;
	private boolean newsletterSubscription;
	private boolean smsAlertSubscription;
	private Parameter isApproved;
	private ArrayList<UserRoleMappingDVO> userRolesMappingList;
	private String comments;
	private Boolean isAdmin;
	private boolean conditionAccepted;
	private Parameter maritalStatus;
	private Date birthDate;
	private Date anniversaryDate;
	private BillingAddressDVO billingAddressRecord;
	private ShippingAddressDVO shippingAddressRecord;

	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPrimaryEmailId() {
		return primaryEmailId;
	}

	public void setPrimaryEmailId(String primaryEmailId) {
		this.primaryEmailId = primaryEmailId;
	}

	public String getSecondaryEmailId() {
		return secondaryEmailId;
	}

	public void setSecondaryEmailId(String secondaryEmailId) {
		this.secondaryEmailId = secondaryEmailId;
	}

	public String getPrimaryPhoneNumber() {
		return primaryPhoneNumber;
	}

	public void setPrimaryPhoneNumber(String primaryPhoneNumber) {
		this.primaryPhoneNumber = primaryPhoneNumber;
	}

	public String getAlternatePhoneNumber() {
		return alternatePhoneNumber;
	}

	public void setAlternatePhoneNumber(String alternatePhoneNumber) {
		this.alternatePhoneNumber = alternatePhoneNumber;
	}

	public Parameter getIsApproved() {
		if (isApproved == null) {
			isApproved = new Parameter();
		}
		return isApproved;
	}

	public void setIsApproved(Parameter isApproved) {
		this.isApproved = isApproved;
	}

	public ArrayList<UserRoleMappingDVO> getUserRolesMappingList() {
		if (userRolesMappingList == null) {
			userRolesMappingList = new ArrayList<UserRoleMappingDVO>();
		}
		return userRolesMappingList;
	}

	public void setUserRolesMappingList(ArrayList<UserRoleMappingDVO> userRolesMappingList) {
		this.userRolesMappingList = userRolesMappingList;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public boolean isNewsletterSubscription() {
		return newsletterSubscription;
	}

	public void setNewsletterSubscription(boolean newsletterSubscription) {
		this.newsletterSubscription = newsletterSubscription;
	}

	public boolean isSmsAlertSubscription() {
		return smsAlertSubscription;
	}

	public void setSmsAlertSubscription(boolean smsAlertSubscription) {
		this.smsAlertSubscription = smsAlertSubscription;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public boolean isConditionAccepted() {
		return conditionAccepted;
	}

	public void setConditionAccepted(boolean conditionAccepted) {
		this.conditionAccepted = conditionAccepted;
	}

	public Parameter getMaritalStatus() {
		if (maritalStatus == null) {
			maritalStatus = new Parameter();
		}
		return maritalStatus;
	}

	public void setMaritalStatus(Parameter maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Date getAnniversaryDate() {
		return anniversaryDate;
	}

	public void setAnniversaryDate(Date anniversaryDate) {
		this.anniversaryDate = anniversaryDate;
	}

	public BillingAddressDVO getBillingAddressRecord() {
		if (billingAddressRecord == null) {
			billingAddressRecord = new BillingAddressDVO();
		}
		return billingAddressRecord;
	}

	public void setBillingAddressRecord(BillingAddressDVO billingAddressRecord) {
		this.billingAddressRecord = billingAddressRecord;
	}

	public ShippingAddressDVO getShippingAddressRecord() {
		if (shippingAddressRecord == null) {
			shippingAddressRecord = new ShippingAddressDVO();
		}
		return shippingAddressRecord;
	}

	public void setShippingAddressRecord(ShippingAddressDVO shippingAddressRecord) {
		this.shippingAddressRecord = shippingAddressRecord;
	}

}
