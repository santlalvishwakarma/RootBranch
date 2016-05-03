package com.web.common.dvo.systemowner;

import com.web.common.dvo.common.BaseDVO;
import com.web.common.dvo.common.CountryDVO;
import com.web.common.dvo.common.StateDVO;

public class AreaDVO extends BaseDVO {
	private static final long serialVersionUID = 8281172840825732608L;
	private CountryDVO countryRecord;
	private StateDVO stateRecord;
	private CityDVO cityRecord;

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

	public CityDVO getCityRecord() {
		if (cityRecord == null) {
			cityRecord = new CityDVO();
		}
		return cityRecord;
	}

	public void setCityRecord(CityDVO cityRecord) {
		this.cityRecord = cityRecord;
	}
}
