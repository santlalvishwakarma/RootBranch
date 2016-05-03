package com.web.common.dvo.retail.modules;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.web.common.dvo.common.BaseDVO;
import com.web.common.dvo.retail.modules.user.UserDVO;
import com.web.common.dvo.systemowner.ProductSkuDVO;

public class ShoppingCartProductDVO extends BaseDVO {

	private static final long serialVersionUID = 4168235283946416903L;
	private ProductSkuDVO productSkuRecord;
	private UserDVO userRecord;
	private Date savedDate;
	private String savedDateString;
	private Integer quantity;
	private Float subTotal;
	private String comments;
	private Float originalSubTotal;

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getSavedDateString() {
		if (savedDate != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy H:m");
			savedDateString = sdf.format(savedDate);
		} else {
			savedDateString = "";
		}
		return savedDateString;
	}

	public void setSavedDateString(String savedDateString) {
		this.savedDateString = savedDateString;
	}

	public UserDVO getUserRecord() {
		if (userRecord == null) {
			userRecord = new UserDVO();
		}
		return userRecord;
	}

	public void setUserRecord(UserDVO userRecord) {
		this.userRecord = userRecord;
	}

	public Date getSavedDate() {
		return savedDate;
	}

	public void setSavedDate(Date savedDate) {
		this.savedDate = savedDate;
	}

	public Float getSubTotal() {
		subTotal = Float.valueOf(0);
		if (productSkuRecord != null) {
			if (productSkuRecord.getBasePrice() != null) {
				// if (quantity != null
				// && (productRecord.getDiscountPrice() != null &&
				// productRecord.getDiscountPrice() <= 0.0)) {
				// subTotal = quantity * productRecord.getBasePrice();
				// } else {
				// if (quantity != null
				// && (productRecord.getDiscountPrice() != null &&
				// productRecord.getDiscountPrice() > 0.0)) {
				// subTotal = quantity * productRecord.getDiscountPrice();
				// }
				// }

				if (quantity != null) {
					subTotal = quantity * productSkuRecord.getBasePrice();
					if (productSkuRecord.getDiscountPrice() != null && productSkuRecord.getDiscountPrice() > 0.0) {
						subTotal = quantity * productSkuRecord.getDiscountPrice();
					}
				}
			}
		}
		return subTotal;
	}

	public void setSubTotal(Float subTotal) {
		this.subTotal = subTotal;
	}

	public ProductSkuDVO getProductSkuRecord() {
		if (productSkuRecord == null) {
			productSkuRecord = new ProductSkuDVO();
		}
		return productSkuRecord;
	}

	public void setProductSkuRecord(ProductSkuDVO productSkuRecord) {
		this.productSkuRecord = productSkuRecord;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getSubTotalIntValue() {
		Integer subTotalIntValue = null;
		if (subTotal != null) {
			subTotalIntValue = Integer.valueOf(subTotal.intValue());
		}
		return subTotalIntValue;
	}

	public Float getOriginalSubTotal() {
		originalSubTotal = Float.valueOf(0);
		if (productSkuRecord != null) {
			if (productSkuRecord.getOriginalBasePrice() != null) {
				if (quantity != null && productSkuRecord.getOriginalDiscountPrice() != null
						&& productSkuRecord.getOriginalDiscountPrice() <= 0.0) {
					originalSubTotal = quantity * productSkuRecord.getOriginalBasePrice();
				} else {
					if (quantity != null && productSkuRecord.getOriginalDiscountPrice() != null
							&& productSkuRecord.getOriginalDiscountPrice() > 0.0) {
						originalSubTotal = quantity * productSkuRecord.getOriginalDiscountPrice();
					}
				}
			}
		}
		return originalSubTotal;
	}

	public void setOriginalSubTotal(Float originalSubTotal) {
		this.originalSubTotal = originalSubTotal;
	}
}
