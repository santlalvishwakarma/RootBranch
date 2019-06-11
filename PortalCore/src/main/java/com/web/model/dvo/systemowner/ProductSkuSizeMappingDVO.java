package com.web.model.dvo.systemowner;

import java.util.ArrayList;
import java.util.List;

import com.web.model.dvo.BaseDVO;

public class ProductSkuSizeMappingDVO extends BaseDVO {

	private static final long serialVersionUID = 8515520224110546000L;

	private ProductDVO productRecord;
	private ProductSkuDVO productSkuRecord;
	private PropertyValueMappingDVO propertyValueMappingRecord;
	private List<String> propertyValueList;

	public ProductDVO getProductRecord() {
		if (productRecord == null) {
			productRecord = new ProductDVO();
		}
		return productRecord;
	}

	public void setProductRecord(ProductDVO productRecord) {
		this.productRecord = productRecord;
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

	public PropertyValueMappingDVO getPropertyValueMappingRecord() {
		if (propertyValueMappingRecord == null) {
			propertyValueMappingRecord = new PropertyValueMappingDVO();
		}
		return propertyValueMappingRecord;
	}

	public void setPropertyValueMappingRecord(PropertyValueMappingDVO propertyValueMappingRecord) {
		this.propertyValueMappingRecord = propertyValueMappingRecord;
	}

	public List<String> getPropertyValueList() {
		if (propertyValueList == null) {
			propertyValueList = new ArrayList<String>();
		}
		return propertyValueList;
	}

	public void setPropertyValueList(List<String> propertyValueList) {
		this.propertyValueList = propertyValueList;
	}

}
