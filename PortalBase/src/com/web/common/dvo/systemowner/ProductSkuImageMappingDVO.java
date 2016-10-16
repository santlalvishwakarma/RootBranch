package com.web.common.dvo.systemowner;

import com.web.common.dvo.common.BaseDVO;
import com.web.common.dvo.common.Parameter;

public class ProductSkuImageMappingDVO extends BaseDVO {

	private static final long serialVersionUID = -2644298630396490209L;
	private ProductSkuDVO productSkuRecord;

	private ImageDVO imageRecord;
	private Parameter imageTypeRecord;

	public ProductSkuDVO getProductSkuRecord() {
		if (productSkuRecord == null) {
			productSkuRecord = new ProductSkuDVO();
		}
		return productSkuRecord;
	}

	public void setProductSkuRecord(ProductSkuDVO productSkuRecord) {
		this.productSkuRecord = productSkuRecord;
	}

	public Parameter getImageTypeRecord() {
		if (imageTypeRecord == null) {
			imageTypeRecord = new Parameter();
		}
		return imageTypeRecord;
	}

	public void setImageTypeRecord(Parameter imageTypeRecord) {
		this.imageTypeRecord = imageTypeRecord;
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

}
