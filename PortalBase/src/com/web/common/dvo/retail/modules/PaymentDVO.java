package com.web.common.dvo.retail.modules;

import com.web.common.dvo.common.BaseDVO;
import com.web.common.dvo.systemowner.AddressDetailDVO;

public class PaymentDVO extends BaseDVO {
	private static final long serialVersionUID = -7361811121161145220L;
	private String sentMerchantId;
	private String sentAmount;
	// GEOPLUGIN- added to send money in rupees
	private String sentOriginalAmount;

	private String sentOrderId;
	private String sentRedirectUrl;
	private String sentCheckSum;
	private AddressDetailDVO sentBillingAddress;
	private AddressDetailDVO sentDeliveryAddress;
	private String sentDeliveryNotes;
	private String sentMerchantParameter;
	private String sentServerMode; // this is whether it is TEST mode or LIVE
									// mode
	private String sentCurrencyCode;
	private String sentTransactionDate;
	private String receivedAmount;
	private String receivedAuthorizationDescription;
	private String receivedBankName;
	private String receivedCardCategory;
	private String receivedCheckSum;
	private String receivedMerchantId;
	private String receivedMerchantParameter;
	private String receivedNbBid;
	private String receivedNbOrderNumber;
	private String receivedNotes;
	private String receivedOrderId;
	private String receivedReturnUrl;
	private AddressDetailDVO receivedBillingAddress;
	private AddressDetailDVO receivedShippingAddress;
	private String receivedServerMode; // this is whether it is TEST mode or
										// LIVE mode
	private String receivedCurrencyCode;
	private String receivedTransactionDate;
	private String receivedTransactionId;
	private String userMessage;
	private boolean checkSumVerified;
	private String errorMessage;
	private String userLogin;

	// private PaymentOptionDVO paymentOption;
	private String paymentType;
	private String paymentTypeCode;

	// added by Dheeraj for ICICI payseal integration
	private String receivedTransactionReferenceNumber;
	private String receivedAuthIdCode;
	private String receivedRrn;
	private String receivedCvrespCode;
	private String receivedFdmsScore;
	private String receivedFdmsResult;
	private String receivedCookie;
	private Float originalBasePrice;
	private Float originalSubTotal;

	// added by ketan for paypal
	private String sentToken;
	private String recievedToken;
	private String payPalAck;
	private String sentCancelUrl;
	private String recievedCancelUrl;
	// private String sentCurrency;
	private String recievedPayerId;
	private String recievedPayerStatus;
	private String recievedBilllingAgreementId;
	private PaymentOptionDVO paymentOption;

	public Float getOriginalBasePrice() {
		return originalBasePrice;
	}

	public void setOriginalBasePrice(Float originalBasePrice) {
		this.originalBasePrice = originalBasePrice;
	}

	public Float getOriginalSubTotal() {
		return originalSubTotal;
	}

	public void setOriginalSubTotal(Float originalSubTotal) {
		this.originalSubTotal = originalSubTotal;
	}

	public boolean isCheckSumVerified() {
		return checkSumVerified;
	}

	public void setCheckSumVerified(boolean checkSumVerified) {
		this.checkSumVerified = checkSumVerified;
	}

	public String getUserMessage() {
		return userMessage;
	}

	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getSentMerchantId() {
		return sentMerchantId;
	}

	public void setSentMerchantId(String sentMerchantId) {
		this.sentMerchantId = sentMerchantId;
	}

	public String getSentAmount() {
		return sentAmount;
	}

	public void setSentAmount(String sentAmount) {
		this.sentAmount = sentAmount;
	}

	public String getSentOrderId() {
		return sentOrderId;
	}

	public void setSentOrderId(String sentOrderId) {
		this.sentOrderId = sentOrderId;
	}

	public String getSentRedirectUrl() {
		return sentRedirectUrl;
	}

	public void setSentRedirectUrl(String sentRedirectUrl) {
		this.sentRedirectUrl = sentRedirectUrl;
	}

	public String getSentCheckSum() {
		return sentCheckSum;
	}

	public void setSentCheckSum(String sentCheckSum) {
		this.sentCheckSum = sentCheckSum;
	}

	public AddressDetailDVO getSentBillingAddress() {
		if (sentBillingAddress == null) {
			sentBillingAddress = new AddressDetailDVO();
		}
		return sentBillingAddress;
	}

	public void setSentBillingAddress(AddressDetailDVO sentBillingAddress) {
		this.sentBillingAddress = sentBillingAddress;
	}

	public AddressDetailDVO getSentDeliveryAddress() {
		if (sentDeliveryAddress == null) {
			sentDeliveryAddress = new AddressDetailDVO();
		}
		return sentDeliveryAddress;
	}

	public void setSentDeliveryAddress(AddressDetailDVO sentDeliveryAddress) {
		this.sentDeliveryAddress = sentDeliveryAddress;
	}

	public String getSentDeliveryNotes() {

		return sentDeliveryNotes;
	}

	public void setSentDeliveryNotes(String sentDeliveryNotes) {
		this.sentDeliveryNotes = sentDeliveryNotes;
	}

	public String getSentMerchantParameter() {
		return sentMerchantParameter;
	}

	public void setSentMerchantParameter(String sentMerchantParameter) {
		this.sentMerchantParameter = sentMerchantParameter;
	}

	public String getReceivedAmount() {

		return receivedAmount;
	}

	public void setReceivedAmount(String receivedAmount) {
		this.receivedAmount = receivedAmount;
	}

	public String getReceivedAuthorizationDescription() {

		return receivedAuthorizationDescription;
	}

	public void setReceivedAuthorizationDescription(String receivedAuthorizationDescription) {
		this.receivedAuthorizationDescription = receivedAuthorizationDescription;
	}

	public String getReceivedBankName() {

		return receivedBankName;
	}

	public void setReceivedBankName(String receivedBankName) {
		this.receivedBankName = receivedBankName;
	}

	public String getReceivedCardCategory() {

		return receivedCardCategory;
	}

	public void setReceivedCardCategory(String receivedCardCategory) {
		this.receivedCardCategory = receivedCardCategory;
	}

	public String getReceivedCheckSum() {

		return receivedCheckSum;
	}

	public void setReceivedCheckSum(String receivedCheckSum) {
		this.receivedCheckSum = receivedCheckSum;
	}

	public String getReceivedMerchantId() {

		return receivedMerchantId;
	}

	public void setReceivedMerchantId(String receivedMerchantId) {
		this.receivedMerchantId = receivedMerchantId;
	}

	public String getReceivedMerchantParameter() {

		return receivedMerchantParameter;
	}

	public void setReceivedMerchantParameter(String receivedMerchantParameter) {
		this.receivedMerchantParameter = receivedMerchantParameter;
	}

	public String getReceivedNbBid() {

		return receivedNbBid;
	}

	public void setReceivedNbBid(String receivedNbBid) {
		this.receivedNbBid = receivedNbBid;
	}

	public String getReceivedNbOrderNumber() {

		return receivedNbOrderNumber;
	}

	public void setReceivedNbOrderNumber(String receivedNbOrderNumber) {
		this.receivedNbOrderNumber = receivedNbOrderNumber;
	}

	public String getReceivedNotes() {

		return receivedNotes;
	}

	public void setReceivedNotes(String receivedNotes) {
		this.receivedNotes = receivedNotes;
	}

	public String getReceivedOrderId() {

		return receivedOrderId;
	}

	public void setReceivedOrderId(String receivedOrderId) {
		this.receivedOrderId = receivedOrderId;
	}

	public String getReceivedReturnUrl() {

		return receivedReturnUrl;
	}

	public void setReceivedReturnUrl(String receivedReturnUrl) {
		this.receivedReturnUrl = receivedReturnUrl;
	}

	public AddressDetailDVO getReceivedBillingAddress() {
		if (receivedBillingAddress == null) {
			receivedBillingAddress = new AddressDetailDVO();
		}
		return receivedBillingAddress;
	}

	public void setReceivedBillingAddress(AddressDetailDVO receivedBillingAddress) {
		this.receivedBillingAddress = receivedBillingAddress;
	}

	public AddressDetailDVO getReceivedShippingAddress() {
		if (receivedShippingAddress == null) {
			receivedShippingAddress = new AddressDetailDVO();
		}
		return receivedShippingAddress;
	}

	public void setReceivedShippingAddress(AddressDetailDVO receivedShippingAddress) {
		this.receivedShippingAddress = receivedShippingAddress;
	}

	public String getSentServerMode() {
		return sentServerMode;
	}

	public void setSentServerMode(String sentServerMode) {
		this.sentServerMode = sentServerMode;
	}

	public String getReceivedServerMode() {
		return receivedServerMode;
	}

	public void setReceivedServerMode(String receivedServerMode) {
		this.receivedServerMode = receivedServerMode;
	}

	public String getSentCurrencyCode() {
		return sentCurrencyCode;
	}

	public void setSentCurrencyCode(String sentCurrencyCode) {
		this.sentCurrencyCode = sentCurrencyCode;
	}

	public String getReceivedCurrencyCode() {
		return receivedCurrencyCode;
	}

	public void setReceivedCurrencyCode(String receivedCurrencyCode) {
		this.receivedCurrencyCode = receivedCurrencyCode;
	}

	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	public String getSentTransactionDate() {
		return sentTransactionDate;
	}

	public void setSentTransactionDate(String sentTransactionDate) {
		this.sentTransactionDate = sentTransactionDate;
	}

	public String getReceivedTransactionDate() {
		return receivedTransactionDate;
	}

	public void setReceivedTransactionDate(String receivedTransactionDate) {
		this.receivedTransactionDate = receivedTransactionDate;
	}

	public String getReceivedTransactionId() {
		return receivedTransactionId;
	}

	public void setReceivedTransactionId(String receivedTransactionId) {
		this.receivedTransactionId = receivedTransactionId;
	}

	// public PaymentOptionDVO getPaymentOption() {
	// if (paymentOption == null) {
	// paymentOption = new PaymentOptionDVO();
	// }
	// return paymentOption;
	// }
	//
	// public void setPaymentOption(PaymentOptionDVO paymentOption) {
	// this.paymentOption = paymentOption;
	// }

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getReceivedTransactionReferenceNumber() {
		return receivedTransactionReferenceNumber;
	}

	public void setReceivedTransactionReferenceNumber(String receivedTransactionReferenceNumber) {
		this.receivedTransactionReferenceNumber = receivedTransactionReferenceNumber;
	}

	public String getReceivedAuthIdCode() {
		return receivedAuthIdCode;
	}

	public void setReceivedAuthIdCode(String receivedAuthIdCode) {
		this.receivedAuthIdCode = receivedAuthIdCode;
	}

	public String getReceivedRrn() {
		return receivedRrn;
	}

	public void setReceivedRrn(String receivedRrn) {
		this.receivedRrn = receivedRrn;
	}

	public String getReceivedCvrespCode() {
		return receivedCvrespCode;
	}

	public void setReceivedCvrespCode(String receivedCvrespCode) {
		this.receivedCvrespCode = receivedCvrespCode;
	}

	public String getReceivedFdmsScore() {
		return receivedFdmsScore;
	}

	public void setReceivedFdmsScore(String receivedFdmsScore) {
		this.receivedFdmsScore = receivedFdmsScore;
	}

	public String getReceivedFdmsResult() {
		return receivedFdmsResult;
	}

	public void setReceivedFdmsResult(String receivedFdmsResult) {
		this.receivedFdmsResult = receivedFdmsResult;
	}

	public String getReceivedCookie() {
		return receivedCookie;
	}

	public void setReceivedCookie(String receivedCookie) {
		this.receivedCookie = receivedCookie;
	}

	public String getPaymentTypeCode() {
		return paymentTypeCode;
	}

	public void setPaymentTypeCode(String paymentTypeCode) {
		this.paymentTypeCode = paymentTypeCode;
	}

	public String getSentOriginalAmount() {
		return sentOriginalAmount;
	}

	public void setSentOriginalAmount(String sentOriginalAmount) {
		this.sentOriginalAmount = sentOriginalAmount;
	}

	public String getSentToken() {
		return sentToken;
	}

	public void setSentToken(String sentToken) {
		this.sentToken = sentToken;
	}

	public String getRecievedToken() {
		return recievedToken;
	}

	public void setRecievedToken(String recievedToken) {
		this.recievedToken = recievedToken;
	}

	public String getPayPalAck() {
		return payPalAck;
	}

	public void setPayPalAck(String payPalAck) {
		this.payPalAck = payPalAck;
	}

	public String getSentCancelUrl() {
		return sentCancelUrl;
	}

	public void setSentCancelUrl(String sentCancelUrl) {
		this.sentCancelUrl = sentCancelUrl;
	}

	public String getRecievedCancelUrl() {
		return recievedCancelUrl;
	}

	public void setRecievedCancelUrl(String recievedCancelUrl) {
		this.recievedCancelUrl = recievedCancelUrl;
	}

	public String getRecievedPayerId() {
		return recievedPayerId;
	}

	public void setRecievedPayerId(String recievedPayerId) {
		this.recievedPayerId = recievedPayerId;
	}

	public String getRecievedPayerStatus() {
		return recievedPayerStatus;
	}

	public void setRecievedPayerStatus(String recievedPayerStatus) {
		this.recievedPayerStatus = recievedPayerStatus;
	}

	public String getRecievedBilllingAgreementId() {
		return recievedBilllingAgreementId;
	}

	public void setRecievedBilllingAgreementId(String recievedBilllingAgreementId) {
		this.recievedBilllingAgreementId = recievedBilllingAgreementId;
	}

	public PaymentOptionDVO getPaymentOption() {
		if (paymentOption == null) {
			paymentOption = new PaymentOptionDVO();
		}
		return paymentOption;
	}

	public void setPaymentOption(PaymentOptionDVO paymentOption) {
		this.paymentOption = paymentOption;
	}

}
