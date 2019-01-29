package com.web.model.dvo;

public class StateDVO extends BaseDVO {
	private static final long serialVersionUID = -6454398272911105334L;
	private boolean stateEnabled;
	private boolean shippingEnabled;
	private CityDVO cityRecord;

	public boolean isShippingEnabled() {
		return shippingEnabled;
	}

	public void setShippingEnabled(boolean shippingEnabled) {
		this.shippingEnabled = shippingEnabled;
	}

	public boolean isStateEnabled() {
		return stateEnabled;
	}

	public void setStateEnabled(boolean stateEnabled) {
		this.stateEnabled = stateEnabled;
	}

	public CityDVO getCityRecord() {
		if (cityRecord == null) {
			cityRecord = new CityDVO();
		}
		return cityRecord;
	}

	public void setCityRecord(CityDVO cityRecord) {
		this.cityRecord = cityRecord;
	}

}
