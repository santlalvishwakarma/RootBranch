package com.web.model.dvo.systemowner;

import com.web.model.dvo.BaseDVO;

public class ProductSkuColorMappingDVO extends BaseDVO {

	private static final long serialVersionUID = -7706304240684869084L;

	private ProductDVO productRecord;
	private ProductSkuDVO productSkuRecord;
	private ColorDVO colorRecord;

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

	public ColorDVO getColorRecord() {
		if (colorRecord == null) {
			colorRecord = new ColorDVO();
		}
		return colorRecord;
	}

	public void setColorRecord(ColorDVO colorRecord) {
		this.colorRecord = colorRecord;
	}

}
