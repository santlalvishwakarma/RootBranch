package com.web.model.dvo.systemowner;

import com.web.model.dvo.BaseDVO;
import com.web.model.dvo.common.StatusDVO;

public class SizeDVO extends BaseDVO {

	private static final long serialVersionUID = 1409960788331184120L;
	private Float increasingPriceInPercent;
	private ProductSkuStockLevelDVO stockLevel;
	private StatusDVO statusRecord;

	public Float getIncreasingPriceInPercent() {
		if (increasingPriceInPercent == null) {
			increasingPriceInPercent = Float.valueOf(0);
		}

		return increasingPriceInPercent;
	}

	public void setIncreasingPriceInPercent(Float increasingPriceInPercent) {
		this.increasingPriceInPercent = increasingPriceInPercent;
	}

	public ProductSkuStockLevelDVO getStockLevel() {
		if (stockLevel == null) {
			stockLevel = new ProductSkuStockLevelDVO();
		}
		return stockLevel;
	}

	public void setStockLevel(ProductSkuStockLevelDVO stockLevel) {
		this.stockLevel = stockLevel;
	}

	public StatusDVO getStatusRecord() {
		if (statusRecord == null) {
			statusRecord = new StatusDVO();
		}
		return statusRecord;
	}

	public void setStatusRecord(StatusDVO statusRecord) {
		this.statusRecord = statusRecord;
	}

}
