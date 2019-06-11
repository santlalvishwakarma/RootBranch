package com.web.model.dvo.systemowner;

import com.web.model.dvo.BaseDVO;

public class CurrencyConversionMappingDVO extends BaseDVO {
	private static final long serialVersionUID = 4337643920875820663L;
	private CurrencyDVO parentCurrencyRecord;
	private CurrencyDVO mappedCurrencyRecord;
	private Float conversionMultiplier;
	private Float paymentMultiplier;

	public CurrencyDVO getParentCurrencyRecord() {
		if (parentCurrencyRecord == null) {
			parentCurrencyRecord = new CurrencyDVO();
		}
		return parentCurrencyRecord;
	}

	public void setParentCurrencyRecord(CurrencyDVO parentCurrencyRecord) {
		this.parentCurrencyRecord = parentCurrencyRecord;
	}

	public CurrencyDVO getMappedCurrencyRecord() {
		if (mappedCurrencyRecord == null) {
			mappedCurrencyRecord = new CurrencyDVO();
		}
		return mappedCurrencyRecord;
	}

	public void setMappedCurrencyRecord(CurrencyDVO mappedCurrencyRecord) {
		this.mappedCurrencyRecord = mappedCurrencyRecord;
	}

	public Float getConversionMultiplier() {
		return conversionMultiplier;
	}

	public void setConversionMultiplier(Float conversionMultiplier) {
		this.conversionMultiplier = conversionMultiplier;
	}

	public Float getPaymentMultiplier() {
		return paymentMultiplier;
	}

	public void setPaymentMultiplier(Float paymentMultiplier) {
		this.paymentMultiplier = paymentMultiplier;
	}

}
