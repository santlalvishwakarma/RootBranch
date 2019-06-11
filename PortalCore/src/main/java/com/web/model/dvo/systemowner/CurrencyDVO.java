package com.web.model.dvo.systemowner;

import java.util.ArrayList;

import com.web.model.dvo.BaseDVO;
import com.web.model.dvo.CountryDVO;
import com.web.model.dvo.Parameter;

public class CurrencyDVO extends BaseDVO {
	private static final long serialVersionUID = 1L;
	private CountryDVO countryRecord;
	private String currencySymbol;
	private Parameter defaultCurrency;
	private ArrayList<CurrencyConversionMappingDVO> conversionCurrencyList;
	// added for GEOPLUGIN
	private String convertedCurrencySymbol;
	private Float currencyConversionMultiplier;
	private Float paymentMultiplier;

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
	 * @return the currencySymbol
	 */
	public String getCurrencySymbol() {
		return currencySymbol;
	}

	/**
	 * @param currencySymbol
	 *            the currencySymbol to set
	 */
	public void setCurrencySymbol(String currencySymbol) {
		this.currencySymbol = currencySymbol;
	}

	public Parameter getDefaultCurrency() {
		if (defaultCurrency == null) {
			defaultCurrency = new Parameter();
		}
		return defaultCurrency;
	}

	public void setDefaultCurrency(Parameter defaultCurrency) {
		this.defaultCurrency = defaultCurrency;
	}

	public ArrayList<CurrencyConversionMappingDVO> getConversionCurrencyList() {
		if (conversionCurrencyList == null) {
			conversionCurrencyList = new ArrayList<CurrencyConversionMappingDVO>();
		}
		return conversionCurrencyList;
	}

	public void setConversionCurrencyList(ArrayList<CurrencyConversionMappingDVO> conversionCurrencyList) {
		this.conversionCurrencyList = conversionCurrencyList;
	}

	public String getConvertedCurrencySymbol() {
		return convertedCurrencySymbol;
	}

	public void setConvertedCurrencySymbol(String convertedCurrencySymbol) {
		this.convertedCurrencySymbol = convertedCurrencySymbol;
	}

	public Float getCurrencyConversionMultiplier() {
		return currencyConversionMultiplier;
	}

	public void setCurrencyConversionMultiplier(Float currencyConversionMultiplier) {
		this.currencyConversionMultiplier = currencyConversionMultiplier;
	}

	public Float getPaymentMultiplier() {

		return paymentMultiplier;
	}

	public void setPaymentMultiplier(Float paymentMultiplier) {
		this.paymentMultiplier = paymentMultiplier;
	}

}
