package com.web.foundation.dao;

import java.io.Serializable;
import java.util.HashMap;

public class SqlRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	private String dataSourceName = null;
	private String sql = null;
	// --private Connection connection = null;
	private HashMap<String, String> hmInputParams = null;
	private HashMap<Integer, SeqTypeValueMapper> hmOutputParams = null; // used
																		// for
																		// stored
																		// proc.
																		// for
																		// out
																		// params.
	private HashMap<String, String> hmSqlProperties = null; // this can contain
															// some properties
															// which
	private HashMap<Integer, SeqTypeValueMapper> hmSqlQuery = null;

	public HashMap<Integer, SeqTypeValueMapper> getHmSqlQuery() {
		return hmSqlQuery;
	}

	public void setHmSqlQuery(HashMap<Integer, SeqTypeValueMapper> hmSqlQuery) {
		this.hmSqlQuery = hmSqlQuery;
	}

	// can contain the addition properties like
	/**
	 * @return the hmInputParams
	 */
	public HashMap<String, String> getHmInputParams() {
		return hmInputParams;
	}

	/**
	 * @param hmInputParams
	 *            the hmInputParams to set
	 */
	public void setHmInputParams(HashMap<String, String> hmInputParams) {
		this.hmInputParams = hmInputParams;
	}

	/**
	 * @return the hmSqlProperties
	 */
	public HashMap<String, String> getHmSqlProperties() {
		return hmSqlProperties;
	}

	/**
	 * @param hmSqlProperties
	 *            the hmSqlProperties to set
	 */
	public void setHmSqlProperties(HashMap<String, String> hmSqlProperties) {
		this.hmSqlProperties = hmSqlProperties;
	}

	/**
	 * @return the sql
	 */
	public String getSql() {
		return sql;
	}

	/**
	 * @param sql
	 *            the sql to set
	 */
	public void setSql(String sql) {
		this.sql = sql;
	}

	/**
	 * @return the hmOutputParams
	 */
	public HashMap<Integer, SeqTypeValueMapper> getHmOutputParams() {
		return hmOutputParams;
	}

	/**
	 * @param hmOutputParams
	 *            the hmOutputParams to set
	 */
	public void setHmOutputParams(HashMap<Integer, SeqTypeValueMapper> hmOutputParams) {
		this.hmOutputParams = hmOutputParams;
	}

	/**
	 * /**
	 * 
	 * @return the dataSourceName
	 */
	public String getDataSourceName() {
		return dataSourceName;
	}

	/**
	 * @param dataSourceName
	 *            the dataSourceName to set
	 */
	public void setDataSourceName(String dataSourceName) {
		this.dataSourceName = dataSourceName;
	}

}
