package com.web.common.dvo.common;

import java.util.HashMap;

public class ApplicationFlags extends DataValueObject {

	private static final long serialVersionUID = 4084220588314645620L;

	private HashMap<String, Object> applicationFlagMap;

	public HashMap<String, Object> getApplicationFlagMap() {
		if (applicationFlagMap == null) {
			applicationFlagMap = new HashMap<String, Object>();
		}
		return applicationFlagMap;
	}

	public void setApplicationFlagMap(HashMap<String, Object> applicationFlagMap) {
		this.applicationFlagMap = applicationFlagMap;
	}
}
