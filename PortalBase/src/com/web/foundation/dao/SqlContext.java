package com.web.foundation.dao;

import java.io.Serializable;
import java.util.HashMap;

/**
 * This class prepares the SQLConext by using the configurations in the
 * respective SQL repositories.
 */

public class SqlContext implements Serializable {
	private static final long serialVersionUID = 1L;
	private SqlRequest sqlRequest = null;

	// add any other properties that we want to add here.
	// like UserContext etc.

	public void initSqlContext() {

		sqlRequest = new SqlRequest();
		sqlRequest.setSql(getSQLText());
		sqlRequest.setDataSourceName(getDataSourceName());
		sqlRequest.setHmInputParams(getInputParams());
		sqlRequest.setHmSqlProperties(getSqlProperties());

	}

	public void loadSqlRepository() {

	}

	public String getSQLText() {
		return "SELECT DNAME FROM DEPT WHERE ROWNUM=1";
	}

	public String getDataSourceName() {
		return "oracleDataSource";
	}

	public String getSqlRepositoryName(Object object) {

		return "TSDRepository";
	}

	private HashMap<String, String> getInputParams() {
		return new HashMap<String, String>();
	}

	private HashMap<String, String> getSqlProperties() {
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		hm.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		return hm;
	}

	/**
	 * @return the sqlRequst
	 */
	public SqlRequest getSqlRequest() {
		return sqlRequest;
	}

	public void setSqlRequest(SqlRequest sqlRequest) {
		this.sqlRequest = sqlRequest;
	}

}
