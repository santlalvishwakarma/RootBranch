package com.web.common.dvo.systemowner;

import com.web.common.dvo.common.BaseDVO;

/**
 * @author Santlal
 * 
 */
public class ProductCategoryPropertiesMappingDVO extends BaseDVO {

	private static final long serialVersionUID = -9110865200156945194L;

	private ProductPropertiesDVO productPropertiesRecord;

	private Boolean skuEditable;

	private String skuEditableDescription;

	private String imageUrl;

	public ProductPropertiesDVO getProductPropertiesRecord() {
		if (productPropertiesRecord == null) {
			productPropertiesRecord = new ProductPropertiesDVO();
		}
		return productPropertiesRecord;
	}

	public void setProductPropertiesRecord(ProductPropertiesDVO productPropertiesRecord) {
		this.productPropertiesRecord = productPropertiesRecord;
	}

	public Boolean getSkuEditable() {
		return skuEditable;
	}

	public void setSkuEditable(Boolean skuEditable) {
		this.skuEditable = skuEditable;
	}

	public String getSkuEditableDescription() {
		return skuEditableDescription;
	}

	public void setSkuEditableDescription(String skuEditableDescription) {
		this.skuEditableDescription = skuEditableDescription;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}
