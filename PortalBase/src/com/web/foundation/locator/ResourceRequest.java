package com.web.foundation.locator;

import java.util.HashMap;

public class ResourceRequest {

	private int iResourceType; // use the constants.
	private Object objRequest;
	private HashMap<String, String> hmProperties;

	/**
	 * @return the hmProperties
	 */
	public HashMap<String, String> getHmProperties() {
		return hmProperties;
	}

	/**
	 * @param hmProperties
	 *            the hmProperties to set
	 */
	public void setHmProperties(HashMap<String, String> hmProperties) {
		this.hmProperties = hmProperties;
	}

	/**
	 * @return the objRequest
	 */
	public Object getObjRequest() {
		return objRequest;
	}

	/**
	 * @param objRequest
	 *            the objRequest to set
	 */
	public void setObjRequest(Object objRequest) {
		this.objRequest = objRequest;
	}

	/**
	 * @return the resourceType
	 */
	public int getResourceType() {
		return iResourceType;
	}

	/**
	 * @param resourceType
	 *            the resourceType to set
	 */
	public void setResourceType(int resourceType) {
		iResourceType = resourceType;
	}

}
