package com.web.common.dvo.systemowner;

import com.web.common.dvo.common.BaseDVO;
import com.web.common.dvo.common.Parameter;

public class ProductSkuImageMappingDVO extends BaseDVO {

	private static final long serialVersionUID = -2644298630396490209L;
	private ProductSkuDVO productSkuRecord;

	// Instead of this ImageDVO should be used
	private String multiImageUrl;
	private String thumbnailImageURL;
	private String zoomImageURL;
	private Integer sequenceNumber;

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

	public String getMultiImageUrl() {
		return multiImageUrl;
	}

	public void setMultiImageUrl(String multiImageUrl) {
		this.multiImageUrl = multiImageUrl;
	}

	public Integer getSequenceNumber() {

		return sequenceNumber;
	}

	public void setSequenceNumber(Integer sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public String getThumbnailImageWrapper() {
		if (thumbnailImageURL == null) {
			return getMultiImageUrl();
		} else {
			return getThumbnailImageURL();
		}
	}

	public String getZoomImageWrapper() {
		if (zoomImageURL == null) {
			return getMultiImageUrl();
		} else {
			return getZoomImageURL();
		}
	}

	public String getThumbnailImageURL() {
		return thumbnailImageURL;
	}

	public void setThumbnailImageURL(String thumbnailImageURL) {
		this.thumbnailImageURL = thumbnailImageURL;
	}

	public String getZoomImageURL() {
		return zoomImageURL;
	}

	public void setZoomImageURL(String zoomImageURL) {
		this.zoomImageURL = zoomImageURL;
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
