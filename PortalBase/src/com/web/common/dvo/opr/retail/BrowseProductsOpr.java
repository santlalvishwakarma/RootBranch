package com.web.common.dvo.opr.retail;

import java.util.ArrayList;

import com.web.common.dvo.common.CountryDVO;
import com.web.common.dvo.common.OperationalDataValueObject;
import com.web.common.dvo.retail.modules.ShoppingCartProductDVO;
import com.web.common.dvo.systemowner.CategoryDVO;
import com.web.common.dvo.systemowner.CategoryPropertyMappingDVO;
import com.web.common.dvo.systemowner.CurrencyDVO;
import com.web.common.dvo.systemowner.ImageDVO;
import com.web.common.dvo.systemowner.ProductSkuDVO;

public class BrowseProductsOpr extends OperationalDataValueObject {
	private static final long serialVersionUID = -3511809318826653456L;
	private ProductSkuDVO productSkuRecord;
	private ArrayList<ProductSkuDVO> productSkuList;
	private ShoppingCartProductDVO shoppingCartProduct;
	private ArrayList<CategoryPropertyMappingDVO> metalInfoProperties;
	private ArrayList<CategoryPropertyMappingDVO> diamondInfoProperties;
	private ArrayList<CategoryPropertyMappingDVO> otherProperties;
	private CountryDVO countryRecord;
	private CurrencyDVO currencyRecord;
	private ArrayList<ImageDVO> categoryImageList;
	private ArrayList<CategoryDVO> subCategoryList;

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

	public ArrayList<ProductSkuDVO> getProductSkuList() {
		if (productSkuList == null) {
			productSkuList = new ArrayList<ProductSkuDVO>();
		}
		return productSkuList;
	}

	public void setProductSkuList(ArrayList<ProductSkuDVO> productSkuList) {
		this.productSkuList = productSkuList;
	}

	public ArrayList<CategoryPropertyMappingDVO> getMetalInfoProperties() {
		if (metalInfoProperties == null) {
			metalInfoProperties = new ArrayList<CategoryPropertyMappingDVO>();
		}
		return metalInfoProperties;
	}

	public void setMetalInfoProperties(ArrayList<CategoryPropertyMappingDVO> metalInfoProperties) {
		this.metalInfoProperties = metalInfoProperties;
	}

	public ArrayList<CategoryPropertyMappingDVO> getDiamondInfoProperties() {
		if (diamondInfoProperties == null) {
			diamondInfoProperties = new ArrayList<CategoryPropertyMappingDVO>();
		}
		return diamondInfoProperties;
	}

	public void setDiamondInfoProperties(ArrayList<CategoryPropertyMappingDVO> diamondInfoProperties) {
		this.diamondInfoProperties = diamondInfoProperties;
	}

	public ArrayList<CategoryPropertyMappingDVO> getOtherProperties() {
		if (otherProperties == null) {
			otherProperties = new ArrayList<CategoryPropertyMappingDVO>();
		}
		return otherProperties;
	}

	public void setOtherProperties(ArrayList<CategoryPropertyMappingDVO> otherProperties) {
		this.otherProperties = otherProperties;
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

	public ArrayList<ImageDVO> getCategoryImageList() {
		if (categoryImageList == null) {
			categoryImageList = new ArrayList<ImageDVO>();
		}
		return categoryImageList;
	}

	public void setCategoryImageList(ArrayList<ImageDVO> categoryImageList) {
		this.categoryImageList = categoryImageList;
	}

	public ArrayList<CategoryDVO> getSubCategoryList() {
		if (subCategoryList == null) {
			subCategoryList = new ArrayList<CategoryDVO>();
		}
		return subCategoryList;
	}

	public void setSubCategoryList(ArrayList<CategoryDVO> subCategoryList) {
		this.subCategoryList = subCategoryList;
	}

}
