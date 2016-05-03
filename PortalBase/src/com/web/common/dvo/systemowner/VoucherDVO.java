package com.web.common.dvo.systemowner;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.web.common.dvo.common.BaseDVO;
import com.web.common.dvo.common.Parameter;

public class VoucherDVO extends BaseDVO {

	private static final long serialVersionUID = -3235835971659188929L;
	private Date voucherFromDate;
	private Date voucherToDate;
	private String promoCode;
	private boolean schemeRetired;
	private Parameter voucherType;
	private Parameter voucherValueType;
	private Float voucherValueInAbsolute;
	private Float voucherValueInPercent;
	private Date generationDate;
	private CurrencyDVO currencyRecord;
	// private ArrayList<VoucherUsageDVO> voucherUsageList;
	private String voucherTypeName;
	private String voucherValueTypeName;
	private String retireDescription;
	private String voucherFromDateString;
	private String voucherToDateString;
	private String isactive;
	private Float voucherDiscountValue;
	private Parameter voucherTimePeriod;
	private String voucherTimePeriodName;
	private String discountValueType;
	// added by ketan for shopping cart
	private Float productPrice;
	private Float invoiceAmount;
	private Float maxDiscount;

	public Parameter getVoucherType() {
		if (voucherType == null) {
			voucherType = new Parameter();
		}
		return voucherType;
	}

	public void setVoucherType(Parameter voucherType) {
		this.voucherType = voucherType;
	}

	public Parameter getVoucherValueType() {
		if (voucherValueType == null) {
			voucherValueType = new Parameter();
		}
		return voucherValueType;
	}

	public void setVoucherValueType(Parameter voucherValueType) {
		this.voucherValueType = voucherValueType;
	}

	public Float getVoucherValueInPercent() {
		if (voucherValueInPercent == null) {
			voucherValueInPercent = Float.valueOf(0);
		}

		return voucherValueInPercent;
	}

	public void setVoucherValueInPercent(Float voucherValueInPercent) {
		this.voucherValueInPercent = voucherValueInPercent;
	}

	public Date getGenerationDate() {

		return generationDate;
	}

	public void setGenerationDate(Date generationDate) {
		this.generationDate = generationDate;
	}

	public CurrencyDVO getCurrencyRecord() {
		if (currencyRecord == null) {
			currencyRecord = new CurrencyDVO();
		}
		return currencyRecord;
	}

	public void setCurrencyRecord(CurrencyDVO currencyRecord) {
		this.currencyRecord = currencyRecord;
	}

	public Date getVoucherFromDate() {

		return voucherFromDate;
	}

	public void setVoucherFromDate(Date voucherFromDate) {
		this.voucherFromDate = voucherFromDate;
	}

	public Date getVoucherToDate() {

		return voucherToDate;
	}

	public void setVoucherToDate(Date voucherToDate) {
		this.voucherToDate = voucherToDate;
	}

	public String getPromoCode() {

		return promoCode;
	}

	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}

	public boolean isSchemeRetired() {

		return schemeRetired;
	}

	public void setSchemeRetired(boolean schemeRetired) {
		this.schemeRetired = schemeRetired;
	}

	// public ArrayList<VoucherUsageDVO> getVoucherUsageList() {
	// if (voucherUsageList == null) {
	// voucherUsageList = new ArrayList<VoucherUsageDVO>();
	// }
	// return voucherUsageList;
	// }
	//
	// public void setVoucherUsageList(ArrayList<VoucherUsageDVO> voucherUsageList) {
	// this.voucherUsageList = voucherUsageList;
	// }

	public String getVoucherTypeName() {

		return voucherTypeName;
	}

	public void setVoucherTypeName(String voucherTypeName) {
		this.voucherTypeName = voucherTypeName;
	}

	public String getVoucherValueTypeName() {

		return voucherValueTypeName;
	}

	public void setVoucherValueTypeName(String voucherValueTypeName) {
		this.voucherValueTypeName = voucherValueTypeName;
	}

	public String getRetireDescription() {

		return retireDescription;
	}

	public void setRetireDescription(String retireDescription) {
		this.retireDescription = retireDescription;
	}

	public String getIsactive() {

		return isactive;
	}

	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}

	public String getVoucherFromDateString() {
		if (voucherFromDate != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			voucherFromDateString = sdf.format(voucherFromDate);
		} else {
			voucherFromDateString = "";
		}
		return voucherFromDateString;
	}

	public void setVoucherFromDateString(String voucherFromDateString) {
		this.voucherFromDateString = voucherFromDateString;
	}

	public String getVoucherToDateString() {
		if (voucherToDate != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			voucherToDateString = sdf.format(voucherToDate);
		} else {
			voucherToDateString = "";
		}
		return voucherToDateString;
	}

	public void setVoucherToDateString(String voucherToDateString) {
		this.voucherToDateString = voucherToDateString;
	}

	public Float getVoucherValueInAbsolute() {
		if (voucherValueInAbsolute == null) {
			voucherValueInAbsolute = Float.valueOf(0);
		}

		return voucherValueInAbsolute;
	}

	public void setVoucherValueInAbsolute(Float voucherValueInAbsolute) {
		this.voucherValueInAbsolute = voucherValueInAbsolute;
	}

	public Float getVoucherDiscountValue() {
		if (voucherDiscountValue == null) {
			voucherDiscountValue = Float.valueOf(0);
		}
		return voucherDiscountValue;
	}

	public void setVoucherDiscountValue(Float voucherDiscountValue) {
		this.voucherDiscountValue = voucherDiscountValue;
	}

	public Parameter getVoucherTimePeriod() {
		if (voucherTimePeriod == null) {
			voucherTimePeriod = new Parameter();
		}
		return voucherTimePeriod;
	}

	public void setVoucherTimePeriod(Parameter voucherTimePeriod) {
		this.voucherTimePeriod = voucherTimePeriod;
	}

	public String getVoucherTimePeriodName() {

		return voucherTimePeriodName;
	}

	public void setVoucherTimePeriodName(String voucherTimePeriodName) {
		this.voucherTimePeriodName = voucherTimePeriodName;
	}

	public String getDiscountValueType() {
		return discountValueType;
	}

	public void setDiscountValueType(String discountValueType) {
		this.discountValueType = discountValueType;
	}

	public Float getProductPrice() {
		if (productPrice == null) {
			productPrice = Float.valueOf(0);
		}
		return productPrice;
	}

	public void setProductPrice(Float productPrice) {
		this.productPrice = productPrice;
	}

	public Float getInvoiceAmount() {
		if (invoiceAmount == null) {
			invoiceAmount = Float.valueOf(0);
		}
		return invoiceAmount;
	}

	public void setInvoiceAmount(Float invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	public Float getMaxDiscount() {
		if (maxDiscount == null) {
			maxDiscount = Float.valueOf(0);
		}
		return maxDiscount;
	}

	public void setMaxDiscount(Float maxDiscount) {
		this.maxDiscount = maxDiscount;
	}

}
