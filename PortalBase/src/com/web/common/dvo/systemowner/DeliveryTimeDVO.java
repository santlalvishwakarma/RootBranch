package com.web.common.dvo.systemowner;

import com.web.common.dvo.common.BaseDVO;
import com.web.common.dvo.common.CountryDVO;

public class DeliveryTimeDVO extends BaseDVO {

	private static final long serialVersionUID = 2962746475609072324L;
	private CountryDVO countryRecord;
	private Integer leadTimeInDays;
	private Integer leadTimeInDaysStockUnavailable;

	public Integer getLeadTimeInDaysStockUnavailable() {
		return leadTimeInDaysStockUnavailable;
	}

	public void setLeadTimeInDaysStockUnavailable(Integer leadTimeInDaysStockUnavailable) {
		this.leadTimeInDaysStockUnavailable = leadTimeInDaysStockUnavailable;
	}

	/**
	 * @return the countryRecord
	 */
	public CountryDVO getCountryRecord() {
		if (countryRecord == null) {
			countryRecord = new CountryDVO();
		}
		return countryRecord;
	}

	/**
	 * @param countryRecord
	 *            the countryRecord to set
	 */
	public void setCountryRecord(CountryDVO countryRecord) {
		this.countryRecord = countryRecord;
	}

	/**
	 * @return the leadTimeInDays
	 */
	public Integer getLeadTimeInDays() {
		return leadTimeInDays;
	}

	/**
	 * @param leadTimeInDays
	 *            the leadTimeInDays to set
	 */
	public void setLeadTimeInDays(Integer leadTimeInDays) {
		this.leadTimeInDays = leadTimeInDays;
	}

}
