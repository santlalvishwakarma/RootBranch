package com.web.model.dvo.systemowner;

import com.web.model.dvo.BaseDVO;
import com.web.model.dvo.Parameter;

public class ProductSkuImageMappingDVO extends BaseDVO {

	private static final long serialVersionUID = -2644298630396490209L;
	private ProductSkuDVO productSkuRecord;

	private ImageDVO imageRecord;
	private Parameter imageTypeRecord;
	private Long sequenceNumber;

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

	public Long getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(Long sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

}
