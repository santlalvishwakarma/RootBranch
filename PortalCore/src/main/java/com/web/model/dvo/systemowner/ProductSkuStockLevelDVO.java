package com.web.model.dvo.systemowner;

import com.web.model.dvo.BaseDVO;

public class ProductSkuStockLevelDVO extends BaseDVO {

	private static final long serialVersionUID = -1158481736838006391L;
	private ProductDVO productRecord;
	private ProductSkuDVO productSkuRecord;
	private Integer reorderLevel;
	private Integer availableQuantity;
	private Integer blockedQuantity;
	private SizeDVO sizeRecord;

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

	public Integer getReorderLevel() {
		return reorderLevel;
	}

	public void setReorderLevel(Integer reorderLevel) {
		this.reorderLevel = reorderLevel;
	}

	public Integer getAvailableQuantity() {
		return availableQuantity;
	}

	public void setAvailableQuantity(Integer availableQuantity) {
		this.availableQuantity = availableQuantity;
	}

	public Integer getBlockedQuantity() {
		return blockedQuantity;
	}

	public void setBlockedQuantity(Integer blockedQuantity) {
		this.blockedQuantity = blockedQuantity;
	}

	public SizeDVO getSizeRecord() {
		if (sizeRecord == null) {
			sizeRecord = new SizeDVO();
		}
		return sizeRecord;
	}

	public void setSizeRecord(SizeDVO sizeRecord) {
		this.sizeRecord = sizeRecord;
	}

}
