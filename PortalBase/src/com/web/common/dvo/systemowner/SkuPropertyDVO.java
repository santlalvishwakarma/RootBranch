package com.web.common.dvo.systemowner;

import com.web.common.dvo.common.BaseDVO;
import com.web.common.dvo.common.Parameter;

public class SkuPropertyDVO extends BaseDVO {

	private static final long serialVersionUID = 7421563461669764716L;

	private Parameter skuPropertyType;
	private Integer skuPropertyPrice;

	public Parameter getSkuPropertyType() {
		if (skuPropertyType == null) {
			skuPropertyType = new Parameter();
		}
		return skuPropertyType;
	}

	public void setSkuPropertyType(Parameter skuPropertyType) {
		this.skuPropertyType = skuPropertyType;
	}

	public Integer getSkuPropertyPrice() {
		return skuPropertyPrice;
	}

	public void setSkuPropertyPrice(Integer skuPropertyPrice) {
		this.skuPropertyPrice = skuPropertyPrice;
	}

}