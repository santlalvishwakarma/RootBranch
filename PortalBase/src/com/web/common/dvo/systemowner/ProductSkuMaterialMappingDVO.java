package com.web.common.dvo.systemowner;

import com.web.common.dvo.common.BaseDVO;

public class ProductSkuMaterialMappingDVO extends BaseDVO {

	private static final long serialVersionUID = 6162616626360353986L;

	private ProductDVO productRecord;
	private ProductSkuDVO productSkuRecord;
	private MaterialDVO materialRecord;

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

	public MaterialDVO getMaterialRecord() {
		if (materialRecord == null) {
			materialRecord = new MaterialDVO();
		}
		return materialRecord;
	}

	public void setMaterialRecord(MaterialDVO materialRecord) {
		this.materialRecord = materialRecord;
	}

}
