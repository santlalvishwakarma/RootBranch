package com.web.common.dvo.opr.retail;

import java.util.ArrayList;

import com.web.common.dvo.common.CountryDVO;
import com.web.common.dvo.common.OperationalDataValueObject;
import com.web.common.dvo.retail.modules.SendEnquiryDVO;
import com.web.common.dvo.retail.modules.ShoppingCartProductDVO;
import com.web.common.dvo.systemowner.CurrencyDVO;
import com.web.common.dvo.systemowner.ProductSkuDVO;

public class ReadMoreOpr extends OperationalDataValueObject {

	private static final long serialVersionUID = 6892351380308314053L;
	private ProductSkuDVO productSkuRecord;
	private ShoppingCartProductDVO shoppingCartProduct;
	private ArrayList<ProductSkuDVO> displayProductList;
	private SendEnquiryDVO sendEnquiryRecord;
	private CountryDVO countryRecord;
	private CurrencyDVO currencyRecord;

	public ShoppingCartProductDVO getShoppingCartProduct() {
		if (shoppingCartProduct == null) {
			shoppingCartProduct = new ShoppingCartProductDVO();
		}
		return shoppingCartProduct;
	}

	public void setShoppingCartProduct(ShoppingCartProductDVO shoppingCartProduct) {
		this.shoppingCartProduct = shoppingCartProduct;
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

	public ArrayList<ProductSkuDVO> getDisplayProductList() {
		if (displayProductList == null) {
			displayProductList = new ArrayList<ProductSkuDVO>();
		}
		return displayProductList;
	}

	public void setDisplayProductList(ArrayList<ProductSkuDVO> displayProductList) {
		this.displayProductList = displayProductList;
	}

	public SendEnquiryDVO getSendEnquiryRecord() {
		if (sendEnquiryRecord == null) {
			sendEnquiryRecord = new SendEnquiryDVO();
		}
		return sendEnquiryRecord;
	}

	public void setSendEnquiryRecord(SendEnquiryDVO sendEnquiryRecord) {
		this.sendEnquiryRecord = sendEnquiryRecord;
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

	public CurrencyDVO getCurrencyRecord() {
		if (currencyRecord == null) {
			currencyRecord = new CurrencyDVO();
		}
		return currencyRecord;
	}

	public void setCurrencyRecord(CurrencyDVO currencyRecord) {
		this.currencyRecord = currencyRecord;
	}
}
