package com.web.common.dvo.systemowner;

import java.io.Serializable;

import com.web.common.dvo.common.CountryDVO;
import com.web.common.dvo.common.StateDVO;

public class AddressDetailDVO implements Serializable {

	private static final long serialVersionUID = -5466350365420936479L;
	private String addressLine1;
	private String addressLine2;
	private String addressLine3;
	private String phone1;
	private String phone2;
	private String mobileNo;
	private CountryDVO countryDvo;
	private StateDVO stateDVO;
	private CityDVO cityDvo;
	private String fax;
	private String pincode;
	private String email1;
	private String email2;
	private String contactPerson1;
	private String contactPerson2;
	private AreaDVO areaRecord;
	private PinDVO pinRecord;
	private String firstName;
	private String lastName;
	private String landmark;

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public CountryDVO getCountryDvo() {
		if (countryDvo == null) {
			countryDvo = new CountryDVO();
		}
		return countryDvo;
	}

	public void setCountryDvo(CountryDVO countryDvo) {
		this.countryDvo = countryDvo;
	}

	public StateDVO getStateDVO() {
		if (stateDVO == null) {
			stateDVO = new StateDVO();
		}
		return stateDVO;
	}

	public void setStateDVO(StateDVO stateDVO) {
		this.stateDVO = stateDVO;
	}

	public CityDVO getCityDvo() {
		if (cityDvo == null) {
			cityDvo = new CityDVO();
		}
		return cityDvo;
	}

	public void setCityDvo(CityDVO cityDvo) {
		this.cityDvo = cityDvo;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getEmail1() {
		return email1;
	}

	public void setEmail1(String email1) {
		this.email1 = email1;
	}

	public String getEmail2() {
		return email2;
	}

	public void setEmail2(String email2) {
		this.email2 = email2;
	}

	public String getContactPerson1() {
		return contactPerson1;
	}

	public void setContactPerson1(String contactPerson1) {
		this.contactPerson1 = contactPerson1;
	}

	public String getContactPerson2() {
		return contactPerson2;
	}

	public void setContactPerson2(String contactPerson2) {
		this.contactPerson2 = contactPerson2;
	}

	public String getAddressLine3() {
		return addressLine3;
	}

	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}

	public AreaDVO getAreaRecord() {
		if (areaRecord == null) {
			areaRecord = new AreaDVO();
		}
		return areaRecord;
	}

	public void setAreaRecord(AreaDVO areaRecord) {
		this.areaRecord = areaRecord;
	}

	public PinDVO getPinRecord() {
		if (pinRecord == null) {
			pinRecord = new PinDVO();
		}
		return pinRecord;
	}

	public void setPinRecord(PinDVO pinRecord) {
		this.pinRecord = pinRecord;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

}
