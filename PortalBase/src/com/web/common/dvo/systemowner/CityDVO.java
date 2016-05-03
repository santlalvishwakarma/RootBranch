package com.web.common.dvo.systemowner;

import com.web.common.dvo.common.BaseDVO;
import com.web.common.dvo.common.CountryDVO;
import com.web.common.dvo.common.StateDVO;

public class CityDVO extends BaseDVO {

	private static final long serialVersionUID = -1365837047919464018L;
	private String cityCode;
	private String cityName;
	private String cityDescription;
	private CountryDVO countryRecord;
	private StateDVO stateRecord;
	private String isActive;
	private String createdBy;

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCityDescription() {
		return cityDescription;
	}

	public void setCityDescription(String cityDescription) {
		this.cityDescription = cityDescription;
	}

	public CountryDVO getCountryRecord() {
		if (countryRecord == null) {
			countryRecord = new CountryDVO();
		}
		return countryRecord;
	}

	public void setCountryRecord(CountryDVO countryRecord) {
		this.countryRecord = countryRecord;
	}

	public StateDVO getStateRecord() {
		if (stateRecord == null) {
			stateRecord = new StateDVO();
		}
		return stateRecord;
	}

	public void setStateRecord(StateDVO stateRecord) {
		this.stateRecord = stateRecord;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

}
