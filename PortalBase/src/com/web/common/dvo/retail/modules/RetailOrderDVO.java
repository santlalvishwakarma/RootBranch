package com.web.common.dvo.retail.modules;

import java.util.ArrayList;
import java.util.List;

import com.web.common.dvo.retail.modules.user.UserDVO;
import com.web.common.dvo.systemowner.AddressDetailDVO;
import com.web.common.dvo.systemowner.OrderDVO;
import com.web.common.dvo.systemowner.VoucherDVO;

public class RetailOrderDVO extends OrderDVO {

	private static final long serialVersionUID = 6947977062200676129L;
	private UserDVO userRecord;
	private AddressDetailDVO billingDetails;
	private AddressDetailDVO shippingDetails;
	private ArrayList<RetailOrderDetailsDVO> orderDetailsList;
	private RetailOrderDetailsDVO retailOrderDetailsRecord;
	private Float discountedPrice;
	private VoucherDVO voucherRecord;
	private List<PaymentDVO> transactions;
	private boolean giftWrappingRequired;
	private String giftWrappingRequiredText;
	private String orderComments;
	private PaymentDVO payementRecord;
	private Integer discountedPriceIntValue;
	private String productsNameString;
	private String productsPriceString;

	public UserDVO getUserRecord() {
		if (userRecord == null) {
			userRecord = new UserDVO();
		}
		return userRecord;
	}

	public void setUserRecord(UserDVO userRecord) {
		this.userRecord = userRecord;
	}

	public ArrayList<RetailOrderDetailsDVO> getOrderDetailsList() {
		if (orderDetailsList == null) {
			orderDetailsList = new ArrayList<RetailOrderDetailsDVO>();
		}
		return orderDetailsList;
	}

	public void setOrderDetailsList(ArrayList<RetailOrderDetailsDVO> orderDetailsList) {
		this.orderDetailsList = orderDetailsList;
	}

	public AddressDetailDVO getBillingDetails() {
		if (billingDetails == null) {
			billingDetails = new AddressDetailDVO();
		}
		return billingDetails;
	}

	public void setBillingDetails(AddressDetailDVO billingDetails) {
		this.billingDetails = billingDetails;
	}

	public AddressDetailDVO getShippingDetails() {
		if (shippingDetails == null) {
			shippingDetails = new AddressDetailDVO();
		}
		return shippingDetails;
	}

	public void setShippingDetails(AddressDetailDVO shippingDetails) {
		this.shippingDetails = shippingDetails;
	}

	public RetailOrderDetailsDVO getRetailOrderDetailsRecord() {
		if (retailOrderDetailsRecord == null) {
			retailOrderDetailsRecord = new RetailOrderDetailsDVO();
		}
		return retailOrderDetailsRecord;
	}

	public void setRetailOrderDetailsRecord(RetailOrderDetailsDVO retailOrderDetailsRecord) {
		this.retailOrderDetailsRecord = retailOrderDetailsRecord;
	}

	public Float getDiscountedPrice() {
		if (discountedPrice == null) {
			discountedPrice = Float.valueOf(0);
		}

		return discountedPrice;
	}

	public void setDiscountedPrice(Float discountedPrice) {
		this.discountedPrice = discountedPrice;
	}

	public VoucherDVO getVoucherRecord() {
		if (voucherRecord == null) {
			voucherRecord = new VoucherDVO();
		}
		return voucherRecord;
	}

	public void setVoucherRecord(VoucherDVO voucherRecord) {
		this.voucherRecord = voucherRecord;
	}

	public boolean isGiftWrappingRequired() {

		return giftWrappingRequired;
	}

	public void setGiftWrappingRequired(boolean giftWrappingRequired) {
		this.giftWrappingRequired = giftWrappingRequired;
	}

	public String getGiftWrappingRequiredText() {

		return giftWrappingRequiredText;
	}

	public void setGiftWrappingRequiredText(String giftWrappingRequiredText) {
		this.giftWrappingRequiredText = giftWrappingRequiredText;
	}

	public List<PaymentDVO> getTransactions() {
		if (transactions == null) {
			transactions = new ArrayList<PaymentDVO>();
		}
		return transactions;
	}

	public void setTransactions(List<PaymentDVO> transactions) {
		this.transactions = transactions;
	}

	public String getOrderComments() {
		return orderComments;
	}

	public void setOrderComments(String orderComments) {
		this.orderComments = orderComments;
	}

	public PaymentDVO getPayementRecord() {
		if (payementRecord == null) {
			payementRecord = new PaymentDVO();
		}
		return payementRecord;
	}

	public void setPayementRecord(PaymentDVO payementRecord) {
		this.payementRecord = payementRecord;
	}

	public Integer getDiscountedPriceIntValue() {
		if (discountedPrice != null && discountedPrice > 0) {
			discountedPriceIntValue = Integer.valueOf(discountedPrice.toString());
		}
		return discountedPriceIntValue;
	}

	public void setDiscountedPriceIntValue(Integer discountedPriceIntValue) {
		this.discountedPriceIntValue = discountedPriceIntValue;
	}

	public String getProductsNameString() {
		return productsNameString;
	}

	public void setProductsNameString(String productsNameString) {
		this.productsNameString = productsNameString;
	}

	public String getProductsPriceString() {
		return productsPriceString;
	}

	public void setProductsPriceString(String productsPriceString) {
		this.productsPriceString = productsPriceString;
	}

}
