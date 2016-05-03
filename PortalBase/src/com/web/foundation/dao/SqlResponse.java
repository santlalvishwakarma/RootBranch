package com.web.foundation.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;

public class SqlResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private HashMap<Integer, HashMap<String, Object>> hmDataValues;

	private int updateResult;

	private SQLException sqlException;

	private HashMap<Object, HashMap<Integer, HashMap<String, Object>>> multipleResultSet;

	/**
	 * @return the sqlException
	 */
	public SQLException getSqlException() {
		return sqlException;
	}

	/**
	 * @param sqlException
	 *            the sqlException to set
	 */
	public void setSqlException(SQLException sqlException) {
		this.sqlException = sqlException;
	}

	/**
	 * @return the updateResult
	 */
	public int getUpdateResult() {
		return updateResult;
	}

	/**
	 * @param updateResult
	 *            the updateResult to set
	 */
	public void setUpdateResult(int updateResult) {
		this.updateResult = updateResult;
	}

	/**
	 * @return the hmDataValues
	 */
	public HashMap<Integer, HashMap<String, Object>> getHmDataValues() {
		return hmDataValues;
	}

	/**
	 * @param hmDataValues
	 *            the hmDataValues to set
	 */
	public void setHmDataValues(HashMap<Integer, HashMap<String, Object>> hmDataValues) {
		this.hmDataValues = hmDataValues;
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
