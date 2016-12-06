package com.web.common.dvo.opr.retail;

import java.util.ArrayList;
import java.util.List;

import com.web.common.dvo.common.CountryDVO;
import com.web.common.dvo.common.OperationalDataValueObject;
import com.web.common.dvo.systemowner.CurrencyDVO;
import com.web.common.dvo.systemowner.HierarchyCategoryMappingDVO;
import com.web.common.dvo.systemowner.ImageDVO;
import com.web.common.dvo.systemowner.ProductDVO;

public class HomeOpr extends OperationalDataValueObject {

	private static final long serialVersionUID = -747600318741367713L;
	private ProductDVO productRecord;
	private ArrayList<ProductDVO> productList;
	private ImageDVO imageRecord;
	private ArrayList<ImageDVO> imageList;
	private CountryDVO countryRecord;
	private CurrencyDVO currencyRecord;
	private List<HierarchyCategoryMappingDVO> homePageCategoryList;

	public ProductDVO getProductRecord() {
		if (productRecord == null) {
			productRecord = new ProductDVO();
		}
		return productRecord;
	}

	public void setProductRecord(ProductDVO productRecord) {
		this.productRecord = productRecord;
	}

	public ArrayList<ProductDVO> getProductList() {
		if (productList == null) {
			productList = new ArrayList<ProductDVO>();
		}
		return productList;
	}

	public void setProductList(ArrayList<ProductDVO> productList) {
		this.productList = productList;
	}

	public ImageDVO getImageRecord() {
		if (imageRecord == null) {
			imageRecord = new ImageDVO();
		}
		return imageRecord;
	}

	public void setImageRecord(ImageDVO imageRecord) {
		this.imageRecord = imageRecord;
	}

	public ArrayList<ImageDVO> getImageList() {
		if (imageList == null) {
			imageList = new ArrayList<ImageDVO>();
		}
		return imageList;
	}

	public void setImageList(ArrayList<ImageDVO> imageList) {
		this.imageList = imageList;
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

	public List<HierarchyCategoryMappingDVO> getHomePageCategoryList() {
		if (homePageCategoryList == null) {
			homePageCategoryList = new ArrayList<HierarchyCategoryMappingDVO>();
		}
		return homePageCategoryList;
	}

	public void setHomePageCategoryList(List<HierarchyCategoryMappingDVO> homePageCategoryList) {
		this.homePageCategoryList = homePageCategoryList;
	}

}
