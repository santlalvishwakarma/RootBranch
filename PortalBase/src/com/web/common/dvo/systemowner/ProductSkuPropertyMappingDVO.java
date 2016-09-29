package com.web.common.dvo.systemowner;

import com.web.common.dvo.common.BaseDVO;

/**
 * @author NIRAJ
 * 
 */
public class ProductSkuPropertyMappingDVO extends BaseDVO {

	private static final long serialVersionUID = -3480793044311366324L;

	private ProductDVO productRecord;

	private ProductSkuDVO productSkuRecord;

	private PropertyDVO propertyrecord;

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

	public PropertyDVO getPropertyrecord() {
		if (propertyrecord == null) {
			propertyrecord = new PropertyDVO();
		}
		return propertyrecord;
	}

	public void setPropertyrecord(PropertyDVO propertyrecord) {
		this.propertyrecord = propertyrecord;
	}

}
