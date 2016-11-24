package com.web.common.dvo.systemowner;

import java.util.ArrayList;

import javax.faces.model.SelectItem;

import com.web.common.dvo.common.BaseDVO;
import com.web.common.dvo.common.StatusDVO;

public class SizeDVO extends BaseDVO {

	private static final long serialVersionUID = 1409960788331184120L;
	private Float increasingPriceInPercent;
	private ArrayList<SelectItem> allOptions;
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

	public ArrayList<SelectItem> getAllOptions() {
		if (allOptions == null) {
			allOptions = new ArrayList<SelectItem>();
		}
		return allOptions;
	}

	public void setAllOptions(ArrayList<SelectItem> allOptions) {
		this.allOptions = allOptions;
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
