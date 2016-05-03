package com.web.foundation.dao;

import java.util.HashMap;

import com.web.foundation.exception.FrameworkException;

/**
 * DAO Utils class provides the methods to form the SqlRequest.
 * 
 * @author shrikant
 * 
 */
public class DAOUtil {

	/**
	 * This method returns the SqlRequest Object to the client.
	 * 
	 * @param strSqlConfigParams
	 * @param strPrepareParams
	 * @param strDynamicWhere
	 * @return
	 */
	public SqlRequest getSqlRequest(HashMap<String, String> strSqlConfigParams, Object[][] strPrepareParams,
			String strDynamicWhere) throws FrameworkException {
		SqlRequest sqlRequest = new SqlRequest();

		sqlRequest.setHmInputParams(strSqlConfigParams);

		sqlRequest.setHmSqlQuery(prepareSeqTypeMapper(strPrepareParams));

		if (strDynamicWhere != null && !strDynamicWhere.equals(""))
			sqlRequest.setHmSqlProperties(getDynamicWhere(strDynamicWhere));

		return sqlRequest;
	}

	/**
	 * This method returns the hashmap which is used to set the setHmInputParams
	 * property of the SqlResuest
	 * 
	 * @param strSqlParams
	 * @return
	 */
	private HashMap<Integer, SeqTypeValueMapper> prepareSeqTypeMapper(Object[][] strSqlParams)
			throws FrameworkException {

		int arrSize = strSqlParams.length;
		SeqTypeValueMapper sqlTypeMapper = null;
		HashMap<Integer, SeqTypeValueMapper> prepareStmtQueryOrderValue = new HashMap<Integer, SeqTypeValueMapper>();
		Integer iSeq = null;
		Integer iDataType = null;
		Object iDataValue = null;
		try {
			for (int i = 0; i <= arrSize - 1; i++) {

				iSeq = Integer.valueOf((String) strSqlParams[i][0]);
				iDataType = Integer.valueOf(strSqlParams[i][1].toString());
				iDataValue = strSqlParams[i][2];
				if (strSqlParams[i][2] != null) {
					if (iDataType.equals(IDAOConstant.DATE_DATATYPE)) {
						if (iDataValue != null) {
							if (iDataValue instanceof java.util.Date) {
								java.sql.Date sqlDate = new java.sql.Date(((java.util.Date) iDataValue).getTime());
								iDataValue = sqlDate;
							}
						}
					}
					sqlTypeMapper = new SeqTypeValueMapper(iSeq, iDataType, strSqlParams[i][2]);
				} else {
					sqlTypeMapper = new SeqTypeValueMapper(iSeq, iDataType, null);
				}

				prepareStmtQueryOrderValue.put(iSeq, sqlTypeMapper);
			}
		} catch (Exception ex) {

			FrameworkException fex = new FrameworkException("Sql parameters not set correctly additional Message:");
			fex.setStackTrace(ex.getStackTrace());
			throw fex;
		}
		return prepareStmtQueryOrderValue;
	}

	/**
	 * this method return the dynamic where clause
	 * 
	 * @param strDynamicWhere
	 * @return
	 */
	private HashMap<String, String> getDynamicWhere(String strDynamicWhere) {

		HashMap<String, String> hmSqlProps = new HashMap<String, String>();
		hmSqlProps.put(IDAOConstant.DYNAMIC_WHERE, strDynamicWhere);

		return hmSqlProps;
	}

}
