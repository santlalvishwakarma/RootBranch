package com.web.common.dvo.systemowner;

import com.web.common.dvo.common.BaseDVO;
import com.web.common.dvo.common.CountryDVO;
import com.web.common.dvo.common.StateDVO;

public class PinDVO extends BaseDVO {
	private static final long serialVersionUID = 6271298613071402726L;
	private CountryDVO countryRecord;
	private StateDVO stateRecord;
	private CityDVO cityRecord;
	private AreaDVO areaRecord;

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

	public AreaDVO getAreaRecord() {
		if (areaRecord == null) {
			areaRecord = new AreaDVO();
		}
		return areaRecord;
	}

	public void setAreaRecord(AreaDVO areaRecord) {
		this.areaRecord = areaRecord;
	}

}
