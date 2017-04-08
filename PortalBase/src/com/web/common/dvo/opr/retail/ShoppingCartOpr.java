package com.web.common.dvo.opr.retail;

import java.util.ArrayList;

import com.web.common.dvo.common.CountryDVO;
import com.web.common.dvo.common.OperationalDataValueObject;
import com.web.common.dvo.retail.modules.GuestUserDVO;
import com.web.common.dvo.retail.modules.PaymentDVO;
import com.web.common.dvo.retail.modules.RetailOrderDVO;
import com.web.common.dvo.retail.modules.ShoppingCartProductDVO;
import com.web.common.dvo.retail.modules.user.UserDVO;
import com.web.common.dvo.systemowner.CurrencyDVO;
import com.web.common.dvo.systemowner.ProductSkuStockLevelDVO;

public class ShoppingCartOpr extends OperationalDataValueObject {

	private static final long serialVersionUID = 6434967670664219087L;
	private ShoppingCartProductDVO shoppingCartProductRecord;
	private ArrayList<ShoppingCartProductDVO> shoppingCartProductList;
	private RetailOrderDVO retailOrderRecord;
	private UserDVO retailUserDetails;
	private CountryDVO countryRecord;
	private ArrayList<CountryDVO> countryList;
	private Integer totalQuantity;
	private Float totalPrice;
	private Float totalOrderPrice;
	private Float newDiscountedPrice;
	private PaymentDVO paymentDVO;
	// added for GEOPLUGIN
	private Float originaltotalPrice;
	private Float originaltotalOrderPrice;
	private String convertedCurrencySymbol;
	private Boolean currencySymbolFlag;
	// added for Green Tree
	private Boolean greenFlag = false;
	private Integer treeAmount = 0;
	// added for ccavenue PG
	// private CCPaymentDVO ccPaymentDVO;
	private ArrayList<ProductSkuStockLevelDVO> currentProductSkuStockLevels;
	private CurrencyDVO currencyRecord;
	private String chargesMode;
	private GuestUserDVO guestRecord;

	// private SmsGateWayDVO smsGateWayRecord;

	public UserDVO getRetailUserDetails() {
		if (retailUserDetails == null) {
			retailUserDetails = new UserDVO();
		}
		return retailUserDetails;
	}

	public ArrayList<ProductSkuStockLevelDVO> getCurrentProductSkuStockLevels() {
		if (currentProductSkuStockLevels == null) {
			currentProductSkuStockLevels = new ArrayList<ProductSkuStockLevelDVO>();
		}
		return currentProductSkuStockLevels;
	}

	public void setCurrentProductSkuStockLevels(ArrayList<ProductSkuStockLevelDVO> currentProductSkuStockLevels) {
		this.currentProductSkuStockLevels = currentProductSkuStockLevels;
	}

	public void setRetailUserDetails(UserDVO retailUserDetails) {
		this.retailUserDetails = retailUserDetails;
	}

	public Float getTotalOrderPrice() {
		totalOrderPrice = Float.valueOf(0);
		totalOrderPrice = getNewDiscountedPrice()
				+ (getRetailOrderRecord().getDeliveryChargesRecord().getDeliveryCharge() == null ? Float.valueOf(0)
						: getRetailOrderRecord().getDeliveryChargesRecord().getDeliveryCharge())
				+ (getRetailOrderRecord().getDeliveryChargesRecord().getExpressCharge() == null ? Float.valueOf(0)
						: getRetailOrderRecord().getDeliveryChargesRecord().getExpressCharge());
		// + (getRetailOrderRecord().getTreeAmt() == null ? Float.valueOf(0) :
		// getRetailOrderRecord().getTreeAmt());
		if (getGreenFlag()) {
			totalOrderPrice = totalOrderPrice
					+ (getRetailOrderRecord().getTreeAmt() == null ? Float.valueOf(0) : getRetailOrderRecord()
							.getTreeAmt());
		}
		return totalOrderPrice;
	}

	public void setTotalOrderPrice(Float totalOrderPrice) {
		this.totalOrderPrice = totalOrderPrice;
	}

	public ArrayList<CountryDVO> getCountryList() {
		if (countryList == null) {
			countryList = new ArrayList<CountryDVO>();
		}
		return countryList;
	}

	public void setCountryList(ArrayList<CountryDVO> countryList) {
		this.countryList = countryList;
	}

	public CountryDVO getCountryRecord() {
		if (countryRecord == null) {
			countryRecord = new CountryDVO();
		}
		return countryRecord;
	}

	public void setCountryRecord(CountryDVO countryRecord) {
		this.countryRecord = countryRecord;
	}

	public RetailOrderDVO getRetailOrderRecord() {
		if (retailOrderRecord == null) {
			retailOrderRecord = new RetailOrderDVO();
		}
		return retailOrderRecord;
	}

	public void setRetailOrderRecord(RetailOrderDVO retailOrderRecord) {
		this.retailOrderRecord = retailOrderRecord;
	}

	public ShoppingCartProductDVO getShoppingCartProductRecord() {
		if (shoppingCartProductRecord == null) {
			shoppingCartProductRecord = new ShoppingCartProductDVO();
		}
		return shoppingCartProductRecord;
	}

	public void setShoppingCartProductRecord(ShoppingCartProductDVO shoppingCartProductRecord) {
		this.shoppingCartProductRecord = shoppingCartProductRecord;
	}

	public Integer getTotalQuantity() {
		totalQuantity = Integer.valueOf(0);
		for (int i = 0; i < getShoppingCartProductList().size(); i++) {
			totalQuantity = totalQuantity
					+ (getShoppingCartProductList().get(i) == null ? 0 : getShoppingCartProductList().get(i)
							.getQuantity());
		}
		return totalQuantity;
	}

	public void setTotalQuantity(Integer totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public Float getTotalPrice() {
		totalPrice = Float.valueOf(0);
		for (int i = 0; i < getShoppingCartProductList().size(); i++) {
			totalPrice = totalPrice
					+ (getShoppingCartProductList().get(i) == null ? Float.valueOf(0) : getShoppingCartProductList()
							.get(i).getSubTotal());
		}
		return totalPrice;
	}

	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public ArrayList<ShoppingCartProductDVO> getShoppingCartProductList() {
		if (shoppingCartProductList == null) {
			shoppingCartProductList = new ArrayList<ShoppingCartProductDVO>();
		}
		return shoppingCartProductList;
	}

	public void setShoppingCartProductList(ArrayList<ShoppingCartProductDVO> shoppingCartProductList) {
		this.shoppingCartProductList = shoppingCartProductList;
	}

	public void setNewDiscountedPrice(Float newDiscountedPrice) {
		this.newDiscountedPrice = newDiscountedPrice;
	}

	public Float getNewDiscountedPrice() {
		// ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		newDiscountedPrice = getTotalPrice();

		// Added by Dheeraj
		// if
		// (getRetailOrderRecord().getVoucherRecord().getVoucherValueInPercent()
		// != null
		// ||
		// getRetailOrderRecord().getVoucherRecord().getVoucherValueInAbsolute()
		// != null) {
		// Float totalDiscountedProductPrice = 0.0F;
		// Float totalUnDiscountedProductPrice = 0.0F;
		// for (int i = 0; i < getShoppingCartProductList().size(); i++) {
		// if
		// (getShoppingCartProductList().get(i).getProductRecord().isGiftVoucherDiscountApplicable())
		// {
		// totalDiscountedProductPrice +=
		// getShoppingCartProductList().get(i).getSubTotal();
		// } else {
		// totalUnDiscountedProductPrice +=
		// getShoppingCartProductList().get(i).getSubTotal();
		// }
		// }
		//
		// if
		// (getRetailOrderRecord().getVoucherRecord().getVoucherValueInPercent()
		// != null) {
		// newDiscountedPrice = totalUnDiscountedProductPrice
		// + (totalDiscountedProductPrice - (totalDiscountedProductPrice *
		// (getRetailOrderRecord()
		// .getVoucherRecord().getVoucherValueInPercent() / 100)));
		// } else if
		// (getRetailOrderRecord().getVoucherRecord().getVoucherValueInPercent()
		// != null) {
		// newDiscountedPrice = totalUnDiscountedProductPrice
		// + (totalDiscountedProductPrice -
		// getRetailOrderRecord().getVoucherRecord()
		// .getVoucherValueInPercent());
		// }
		// } else {
		newDiscountedPrice = (getTotalPrice() - getRetailOrderRecord().getDiscountedPrice());
		// }

		// Commented by Dheeraj for applying new logic for gift voucher discount
		// for (int i = 0; i < getShoppingCartProductList().size(); i++) {
		// if
		// ((getRetailOrderRecord().getVoucherRecord().getVoucherValueInPercent()
		// != null)
		// &&
		// !getRetailOrderRecord().getVoucherRecord().getVoucherValueInPercent().equals(Float.valueOf(0)))
		// {
		//
		// newDiscountedPrice = getTotalPrice()
		// - (getTotalPrice() *
		// (getRetailOrderRecord().getVoucherRecord().getVoucherValueInPercent()
		// / 100));
		// myLog.debug("In PRECENT ==========newDiscountedPrice Value" +
		// newDiscountedPrice);
		// } else if
		// ((getRetailOrderRecord().getVoucherRecord().getVoucherValueInAbsolute()
		// != null)
		// &&
		// !getRetailOrderRecord().getVoucherRecord().getVoucherValueInAbsolute().equals(Float.valueOf(0)))
		// {
		// newDiscountedPrice = (getTotalPrice() -
		// (getRetailOrderRecord().getVoucherRecord()
		// .getVoucherValueInAbsolute()));
		// myLog.debug("In ABSOLUTE ==========newDiscountedPrice Value" +
		// newDiscountedPrice);
		//
		// } else {
		// newDiscountedPrice = (getTotalPrice() -
		// getRetailOrderRecord().getDiscountedPrice());
		// }
		//
		// }

		return newDiscountedPrice;
	}

	public Integer getTotalPriceIntValue() {
		Integer totalPriceIntValue = null;
		if (totalPrice != null) {
			totalPriceIntValue = Integer.valueOf(totalPrice.intValue());
		}
		return totalPriceIntValue;
	}

	public Integer getTotalOrderPriceIntValue() {
		Integer totalOrderPriceIntValue = null;
		if (totalOrderPrice != null) {
			totalOrderPriceIntValue = Math.round(totalOrderPrice);
		}
		return totalOrderPriceIntValue;
	}

	public PaymentDVO getPaymentDVO() {
		if (paymentDVO == null) {
			paymentDVO = new PaymentDVO();
		}
		return paymentDVO;
	}

	public void setPaymentDVO(PaymentDVO paymentDVO) {
		this.paymentDVO = paymentDVO;
	}

	public Float getOriginaltotalPrice() {
		return originaltotalPrice;
	}

	public void setOriginaltotalPrice(Float originaltotalPrice) {
		this.originaltotalPrice = originaltotalPrice;
	}

	public Float getOriginaltotalOrderPrice() {
		return originaltotalOrderPrice;
	}

	public void setOriginaltotalOrderPrice(Float originaltotalOrderPrice) {
		this.originaltotalOrderPrice = originaltotalOrderPrice;
	}

	public Boolean getCurrencySymbolFlag() {
		return currencySymbolFlag;
	}

	public void setCurrencySymbolFlag(Boolean currencySymbolFlag) {
		this.currencySymbolFlag = currencySymbolFlag;
	}

	public String getConvertedCurrencySymbol() {
		return convertedCurrencySymbol;
	}

	public void setConvertedCurrencySymbol(String convertedCurrencySymbol) {
		this.convertedCurrencySymbol = convertedCurrencySymbol;
	}

	public Boolean getGreenFlag() {
		return greenFlag;
	}

	public void setGreenFlag(Boolean greenFlag) {
		this.greenFlag = greenFlag;
	}

	public Integer getTreeAmount() {
		return treeAmount;
	}

	public void setTreeAmount(Integer treeAmount) {
		this.treeAmount = treeAmount;
	}

	// public CCPaymentDVO getCcPaymentDVO() {
	// if (ccPaymentDVO == null) {
	// ccPaymentDVO = new CCPaymentDVO();
	// }
	// return ccPaymentDVO;
	// }
	//
	// public void setCcPaymentDVO(CCPaymentDVO ccPaymentDVO) {
	// this.ccPaymentDVO = ccPaymentDVO;
	// }

	public CurrencyDVO getCurrencyRecord() {
		if (currencyRecord == null) {
			currencyRecord = new CurrencyDVO();
		}
		return currencyRecord;
	}

	public void setCurrencyRecord(CurrencyDVO currencyRecord) {
		this.currencyRecord = currencyRecord;
	}

	public String getChargesMode() {
		return chargesMode;
	}

	public void setChargesMode(String chargesMode) {
		this.chargesMode = chargesMode;
	}

	public GuestUserDVO getGuestRecord() {
		if (guestRecord == null) {
			guestRecord = new GuestUserDVO();
		}
		return guestRecord;
	}

	public void setGuestRecord(GuestUserDVO guestRecord) {
		this.guestRecord = guestRecord;
	}

}
