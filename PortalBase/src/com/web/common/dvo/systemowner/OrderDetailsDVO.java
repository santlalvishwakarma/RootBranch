package com.web.common.dvo.systemowner;

import com.web.common.dvo.common.BaseDVO;

public abstract class OrderDetailsDVO extends BaseDVO {

	private static final long serialVersionUID = 99533855501576032L;
	private ProductSkuDVO productSkuRecord;
	private Float pricePerPiece;
	private Integer productQuantity;
	private Float totalPrice;
	private String comments;
	private Float subTotal;

	// GEOPLUGIN to save originalPricePerPiece and originalSubTotal
	private Float originalPricePerPiece;
	private Float originalTotalPrice;
	private Float originalSubTotal;

	public ProductSkuDVO getProductSkuRecord() {
		if (productSkuRecord == null) {
			productSkuRecord = new ProductSkuDVO();
		}
		return productSkuRecord;
	}

	public void setProductSkuRecord(ProductSkuDVO productSkuRecord) {
		this.productSkuRecord = productSkuRecord;
	}

	public Float getPricePerPiece() {
		return pricePerPiece;
	}

	public void setPricePerPiece(Float pricePerPiece) {
		this.pricePerPiece = pricePerPiece;
	}

	public Integer getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(Integer productQuantity) {
		this.productQuantity = productQuantity;
	}

	public Float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Float getSubTotal() {
		subTotal = Float.valueOf(0);
		if (productSkuRecord != null) {
			if (productSkuRecord.getBasePrice() != null) {
				if (productQuantity != null) {
					subTotal = productQuantity * productSkuRecord.getBasePrice();
				}
				// else {
				// if (productQuantity != null &&
				// productSkuRecord.getDiscountPrice() > 0.0) {
				// subTotal = productQuantity *
				// productSkuRecord.getDiscountPrice();
				// }
				// }
			}
		}
		return subTotal;
	}

	public void setSubTotal(Float subTotal) {
		this.subTotal = subTotal;
	}

	public Integer getSubTotalIntValue() {
		Integer subTotalIntValue = null;
		if (subTotal != null) {
			subTotalIntValue = Integer.valueOf(subTotal.intValue());
		}
		return subTotalIntValue;
	}

	public Integer getTotalPriceIntValue() {
		Integer totalPriceIntValue = null;
		if (totalPrice != null) {
			totalPriceIntValue = Integer.valueOf(totalPrice.intValue());
		}
		return totalPriceIntValue;
	}

	public Integer getPricePerPieceIntValue() {
		Integer pricePerPieceIntValue = null;
		if (pricePerPiece != null) {
			pricePerPieceIntValue = Integer.valueOf(pricePerPiece.intValue());
		}
		return pricePerPieceIntValue;
	}

	public Float getOriginalSubTotal() {
		subTotal = Float.valueOf(0);
		if (productSkuRecord != null) {
			if (productSkuRecord.getOriginalBasePrice() != null) {
				if (productQuantity != null && productSkuRecord.getOriginalDiscountPrice() <= 0.0) {
					subTotal = productQuantity * productSkuRecord.getOriginalBasePrice();
				} else {
					if (productQuantity != null && productSkuRecord.getOriginalDiscountPrice() > 0.0) {
						subTotal = productQuantity * productSkuRecord.getOriginalDiscountPrice();
					}
				}
			}
		}
		return originalSubTotal;
	}

	public void setOriginalSubTotal(Float originalSubTotal) {
		this.originalSubTotal = originalSubTotal;
	}

	public Float getOriginalPricePerPiece() {
		return originalPricePerPiece;
	}

	public void setOriginalPricePerPiece(Float originalPricePerPiece) {
		this.originalPricePerPiece = originalPricePerPiece;
	}

	public Float getOriginalTotalPrice() {
		return originalTotalPrice;
	}

	public void setOriginalTotalPrice(Float originalTotalPrice) {
		this.originalTotalPrice = originalTotalPrice;
	}
}
