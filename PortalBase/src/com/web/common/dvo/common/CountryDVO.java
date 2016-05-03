package com.web.common.dvo.common;

import java.util.ArrayList;

public class CountryDVO extends BaseDVO {
	private static final long serialVersionUID = -8701167012475546275L;
	private boolean shippingEnabled;
	private ArrayList<StateDVO> stateList;
	private StateDVO stateRecord;
	protected String codeISO3;
	private String flagUrl;

	public StateDVO getStateRecord() {
		if (stateRecord == null) {
			stateRecord = new StateDVO();
		}
		return stateRecord;
	}

	public void setStateRecord(StateDVO stateRecord) {
		this.stateRecord = stateRecord;
	}

	public boolean isShippingEnabled() {
		return shippingEnabled;
	}

	public void setShippingEnabled(boolean shippingEnabled) {
		this.shippingEnabled = shippingEnabled;
	}

	/**
	 * @return the stateList
	 */
	public ArrayList<StateDVO> getStateList() {
		if (stateList == null) {
			stateList = new ArrayList<StateDVO>();
		}
		return stateList;
	}

	/**
	 * @param stateList
	 *            the stateList to set
	 */
	public void setStateList(ArrayList<StateDVO> stateList) {
		this.stateList = stateList;
	}

	public String getCodeISO3() {
		return codeISO3;
	}

	public void setCodeISO3(String codeISO3) {
		this.codeISO3 = codeISO3;
	}

	public String getFlagUrl() {
		return flagUrl;
	}

	public void setFlagUrl(String flagUrl) {
		this.flagUrl = flagUrl;
	}

}
