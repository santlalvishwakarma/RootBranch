package com.web.foundation.dao;

import java.util.HashMap;

public class DAOResult {

	private Integer updateResult;

	private HashMap<Integer, HashMap<String, Object>> invocationResult;

	private Exception exception;

	private HashMap<Object, HashMap<Integer, HashMap<String, Object>>> multipleResultSet;

	public HashMap<Integer, HashMap<String, Object>> getInvocationResult() {
		if (invocationResult == null) {
			invocationResult = new HashMap<Integer, HashMap<String, Object>>();
		}
		return invocationResult;
	}

	public void setInvocationResult(HashMap<Integer, HashMap<String, Object>> invocationResult) {
		this.invocationResult = invocationResult;
	}

	public Integer getUpdateResult() {
		return updateResult;
	}

	public void setUpdateResult(Integer updateResult) {
		this.updateResult = updateResult;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

	public HashMap<Object, HashMap<Integer, HashMap<String, Object>>> getMultipleResultSet() {
		if (multipleResultSet == null) {
			multipleResultSet = new HashMap<Object, HashMap<Integer, HashMap<String, Object>>>();
		}
		return multipleResultSet;
	}

	public void setMultipleResultSet(HashMap<Object, HashMap<Integer, HashMap<String, Object>>> multipleResultSet) {
		this.multipleResultSet = multipleResultSet;
	}

}
