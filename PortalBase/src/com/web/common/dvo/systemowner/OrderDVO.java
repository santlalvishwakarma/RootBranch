package com.web.common.dvo.systemowner;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.web.common.dvo.common.BaseDVO;
import com.web.common.dvo.common.Parameter;

public abstract class OrderDVO extends BaseDVO {

	private static final long serialVersionUID = -3470081149271594989L;
	private String orderNumber;
	private Integer totalQuantity;
	private Float totalAmount;
	private Float totalPayableAmount;
	private DeliveryTimeDVO deliveryTimeRecord;
	private DeliveryChargesDVO deliveryChargesRecord;
	private Parameter paymentStatus;
	private Parameter orderStatus;
	private String paymentTrackingNumber;
	private String orderTrackingNumber;
	private Date orderDate;
	private String orderDateString;
	private String deliveryDateString;
	private Date deliveryDate;
	private Parameter courierRecord;
	private String chequeNumber;
	private String bankName;
	private Float chequeAmt;
	private Float treeAmt;
	// added for GEOPLUGIN
	private Float originalTotalPayableAmount;
	private String promotionSource;

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Integer getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(Integer totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public Float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Float totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Float getTotalPayableAmount() {
		return totalPayableAmount;
	}

	public void setTotalPayableAmount(Float totalPayableAmount) {
		this.totalPayableAmount = totalPayableAmount;
	}

	public DeliveryTimeDVO getDeliveryTimeRecord() {
		if (deliveryTimeRecord == null) {
			deliveryTimeRecord = new DeliveryTimeDVO();
		}
		return deliveryTimeRecord;
	}

	public void setDeliveryTimeRecord(DeliveryTimeDVO deliveryTimeRecord) {
		this.deliveryTimeRecord = deliveryTimeRecord;
	}

	public DeliveryChargesDVO getDeliveryChargesRecord() {
		if (deliveryChargesRecord == null) {
			deliveryChargesRecord = new DeliveryChargesDVO();
		}
		return deliveryChargesRecord;
	}

	public void setDeliveryChargesRecord(DeliveryChargesDVO deliveryChargesRecord) {
		this.deliveryChargesRecord = deliveryChargesRecord;
	}

	public Parameter getPaymentStatus() {
		if (paymentStatus == null) {
			paymentStatus = new Parameter();
		}
		return paymentStatus;
	}

	public void setPaymentStatus(Parameter paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public Parameter getOrderStatus() {
		if (orderStatus == null) {
			orderStatus = new Parameter();
		}
		return orderStatus;
	}

	public void setOrderStatus(Parameter orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getPaymentTrackingNumber() {
		return paymentTrackingNumber;
	}

	public void setPaymentTrackingNumber(String paymentTrackingNumber) {
		this.paymentTrackingNumber = paymentTrackingNumber;
	}

	public String getOrderTrackingNumber() {
		return orderTrackingNumber;
	}

	public void setOrderTrackingNumber(String orderTrackingNumber) {
		this.orderTrackingNumber = orderTrackingNumber;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Parameter getCourierRecord() {
		if (courierRecord == null) {
			courierRecord = new Parameter();
		}
		return courierRecord;
	}

	public void setCourierRecord(Parameter courierRecord) {
		this.courierRecord = courierRecord;
	}

	public String getOrderDateString() {
		if (orderDate != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			orderDateString = sdf.format(orderDate);
		} else {
			orderDateString = "";
		}
		return orderDateString;
	}

	public void setOrderDateString(String orderDateString) {
		this.orderDateString = orderDateString;
	}

	public String getDeliveryDateString() {
		if (deliveryDate != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			deliveryDateString = sdf.format(deliveryDate);
		} else {
			deliveryDateString = "";
		}
		return deliveryDateString;
	}

	public void setDeliveryDateString(String deliveryDateString) {
		this.deliveryDateString = deliveryDateString;
	}

	public Integer getTotalPayableAmountIntValue() {
		Integer totalPayableAmountIntValue = null;
		if (totalPayableAmount != null) {
			totalPayableAmountIntValue = Math.round(totalPayableAmount);
		}
		return totalPayableAmountIntValue;
	}

	public String getChequeNumber() {
		return chequeNumber;
	}

	public void setChequeNumber(String chequeNumber) {
		this.chequeNumber = chequeNumber;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public Float getChequeAmt() {
		return chequeAmt;
	}

	public void setChequeAmt(Float chequeAmt) {
		this.chequeAmt = chequeAmt;
	}

	public Float getOriginalTotalPayableAmount() {
		return originalTotalPayableAmount;
	}

	public void setOriginalTotalPayableAmount(Float originalTotalPayableAmount) {
		this.originalTotalPayableAmount = originalTotalPayableAmount;
	}

	public Float getTreeAmt() {
		return treeAmt;
	}

	public void setTreeAmt(Float treeAmt) {
		this.treeAmt = treeAmt;
	}

	public String getPromotionSource() {
		return promotionSource;
	}

	public void setPromotionSource(String promotionSource) {
		this.promotionSource = promotionSource;
	}

}
