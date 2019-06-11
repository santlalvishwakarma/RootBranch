package com.web.model.dvo.systemowner;

import java.text.NumberFormat;

import com.web.model.dvo.BaseDVO;
import com.web.model.dvo.CountryDVO;

public class DeliveryChargesDVO extends BaseDVO {

	private static final long serialVersionUID = 4022447125471696773L;
	private CountryDVO countryRecord;
	private Float deliveryCharge = 0.0f;
	private CurrencyDVO currencyRecord;
	private Float originalDeliveryCharge = 0.0F;
	private Integer deliveryChargeIntValue = 0;
	private Float expressCharge = 0.0f;
	private Float dutiesCharge = 0.0f;
	private Float processingCharge = 0.0f;
	private Float originalExpressCharge = 0.0F;
	private Float originalProcessingCharge = 0.0F;
	private Float originalDutiesCharge = 0.0F;

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
	 * @return the deliveryCharge
	 */
	public Float getDeliveryCharge() {
		return deliveryCharge;
	}

	/**
	 * @param deliveryCharge
	 *            the deliveryCharge to set
	 */
	public void setDeliveryCharge(Float deliveryCharge) {
		this.deliveryCharge = deliveryCharge;
	}

	/**
	 * @return the currencyRecord
	 */
	public CurrencyDVO getCurrencyRecord() {
		if (currencyRecord == null) {
			currencyRecord = new CurrencyDVO();
		}
		return currencyRecord;
	}

	/**
	 * @param currencyRecord
	 *            the currencyRecord to set
	 */
	public void setCurrencyRecord(CurrencyDVO currencyRecord) {
		this.currencyRecord = currencyRecord;
	}

	public String getDeliveryChargesString() {
		String deliveryChargeString = null;
		if (deliveryCharge != null) {
			NumberFormat nf = NumberFormat.getInstance();
			nf.setMinimumFractionDigits(2);
			deliveryChargeString = nf.format(deliveryCharge);
		}
		return deliveryChargeString;
	}

	public Float getOriginalDeliveryCharge() {
		return originalDeliveryCharge;
	}

	public void setOriginalDeliveryCharge(Float originalDeliveryCharge) {
		this.originalDeliveryCharge = originalDeliveryCharge;
	}

	public Integer getDeliveryChargeIntValue() {
		if (deliveryCharge != null && deliveryCharge > 0) {
			deliveryChargeIntValue = Integer.valueOf(deliveryCharge.intValue());
		}
		return deliveryChargeIntValue;
	}

	public void setDeliveryChargeIntValue(Integer deliveryChargeIntValue) {
		this.deliveryChargeIntValue = deliveryChargeIntValue;
	}

	public Float getExpressCharge() {
		return expressCharge;
	}

	public void setExpressCharge(Float expressCharge) {
		this.expressCharge = expressCharge;
	}

	public Float getDutiesCharge() {
		return dutiesCharge;
	}

	public void setDutiesCharge(Float dutiesCharge) {
		this.dutiesCharge = dutiesCharge;
	}

	public Float getProcessingCharge() {
		return processingCharge;
	}

	public void setProcessingCharge(Float processingCharge) {
		this.processingCharge = processingCharge;
	}

	public Float getOriginalExpressCharge() {
		return originalExpressCharge;
	}

	public void setOriginalExpressCharge(Float originalExpressCharge) {
		this.originalExpressCharge = originalExpressCharge;
	}

	public Float getOriginalProcessingCharge() {
		return originalProcessingCharge;
	}

	public void setOriginalProcessingCharge(Float originalProcessingCharge) {
		this.originalProcessingCharge = originalProcessingCharge;
	}

	public Float getOriginalDutiesCharge() {
		return originalDutiesCharge;
	}

	public void setOriginalDutiesCharge(Float originalDutiesCharge) {
		this.originalDutiesCharge = originalDutiesCharge;
	}

}
