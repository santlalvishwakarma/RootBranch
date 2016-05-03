package com.web.common.dvo.retail.modules;

import com.web.common.dvo.common.BaseDVO;

public class PaymentOptionDVO extends BaseDVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5995658166222083120L;

	private String paymentCode;
	private String paymentName;
	private String paymentDescription;
	private String paymentGatewayProvider;

	public String getPaymentCode() {
		return paymentCode;
	}

	public void setPaymentCode(String paymentCode) {
		this.paymentCode = paymentCode;
	}

	public String getPaymentName() {
		return paymentName;
	}

	public void setPaymentName(String paymentName) {
		this.paymentName = paymentName;
	}

	public String getPaymentDescription() {
		return paymentDescription;
	}

	public void setPaymentDescription(String paymentDescription) {
		this.paymentDescription = paymentDescription;
	}

	public String getPaymentGatewayProvider() {
		return paymentGatewayProvider;
	}

	public void setPaymentGatewayProvider(String paymentGatewayProvider) {
		this.paymentGatewayProvider = paymentGatewayProvider;
	}
}
