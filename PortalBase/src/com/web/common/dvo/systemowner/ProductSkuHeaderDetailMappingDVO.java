package com.web.common.dvo.systemowner;

import com.web.common.dvo.common.BaseDVO;

public class ProductSkuHeaderDetailMappingDVO extends BaseDVO {

	private static final long serialVersionUID = -5182802824970713818L;

	private ProductDVO productRecord;
	private ProductSkuDVO productSkuRecord;
	private Boolean defaultSku;

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

	public Boolean getDefaultSku() {
		return defaultSku;
	}

	public void setDefaultSku(Boolean defaultSku) {
		this.defaultSku = defaultSku;
	}

}
