package com.web.foundation.dao;

import java.util.HashMap;

import com.web.common.constants.CommonConstant;
import com.web.foundation.exception.DAOException;
import com.web.foundation.exception.FrameworkException;
import com.web.foundation.logger.ITSDConstant;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLoggerFactory;

/**
 * to create the SqlContext object from the BL request and configurations.
 * invoke the call to the JdbcRequestHandler
 * 
 * @author shrikantk
 * 
 */
public final class DBDelegator {

	public SqlResponse invoke(SqlRequest sqlRequest) throws FrameworkException {
		// load the configuration files
		// get the sqlRequest object.

		tsdLogger.trace(" DBDelegator invoke");

		SqlContext sqlContext = getSqlContext(sqlRequest);

		JdbcRequestHandler jdbcRequestHandler = JdbcRequestHandler.getJdbcRequestHandler();
		SqlResponse sqlResponse = jdbcRequestHandler.handleJdbcRequest(sqlContext);

		// check if there is any sql exception during the sql execution

		if (sqlResponse.getSqlException() != null) {
			tsdLogger.error(sqlResponse.getSqlException());
			throw new DAOException("dao_sql_excep_msg", sqlResponse.getSqlException());
		}

		tsdLogger.trace(" DBDelegator invoke returning sql response");
		return sqlResponse;// temp. remove it afterwards.
	}

	private DBDelegator() {

	}

	public static DBDelegator getDBdelegator() {
		return new DBDelegator();
	}

	// Context can be cached for each session.
	public SqlContext getSqlContext(SqlRequest sqlRequest) throws DAOException {

		String sqlText = sqlRequest.getHmInputParams().get(IDAOConstant.SQL_TEXT);

		/*
		 * String sqlId = null; Object sqlIdObj = ((HashMap)
		 * sqlRequest.getHmInputParams()).get(IDAOConstant.SQL_ID); if (sqlIdObj
		 * != null) { sqlId = sqlIdObj.toString(); } tsdLogger.trace("sqlId " +
		 * sqlId);
		 */

		SqlContext sqlcontext = new SqlContext();

		/*
		 * HashMap hmConfig; hmConfig =
		 * XMLConfig.getDBConfiguration(sqlRequest.getHmInputParams());
		 */

		// HashMap tempSqlProperties = new HashMap();
		// Iterator itr;
		// String key;

		sqlRequest.setDataSourceName(CommonConstant.SYSTEM_DATASOURCE);
		HashMap<String, String> sqlPropertiesMap = sqlRequest.getHmSqlProperties();
		// it expects the sql type and statement type
		// if the sql properties are pre-set then append to them, else add a new
		// set of properties
		/*
		 * if (sqlRequest.getHmSqlProperties() != null &&
		 * !sqlRequest.getHmSqlProperties().isEmpty()) { tempSqlProperties =
		 * (HashMap) hmConfig.get(ICommonConstant.CONFIG_SQLPROPERTIES);
		 * tsdLogger.trace("tempSqlProperties " + tempSqlProperties); // itr =
		 * tempSqlProperties.keySet().iterator();
		 * 
		 * // while (itr.hasNext()) { // key = (String) itr.next();
		 * sqlPropertiesMap.putAll(tempSqlProperties); // } } else {
		 * sqlRequest.setHmSqlProperties((HashMap)
		 * hmConfig.get(ICommonConstant.CONFIG_SQLPROPERTIES)); }
		 */

		tsdLogger.trace("sqlPropertiesMap 1" + sqlPropertiesMap);

		if (sqlText == null) {
			throw new DAOException("dao_sql_excep_msg", new Exception("Sql Text is null in DAO"));
			/*
			 * String sqlPathId = ((HashMap)
			 * sqlRequest.getHmInputParams()).get(IDAOConstant
			 * .SQL_PATH_ID).toString(); tsdLogger.trace("sqlPathId " +
			 * sqlPathId); HashMap sqlRepoPath = (HashMap)
			 * hmConfig.get(ICommonConstant.CONFIG_SQLREPOPATH);
			 * tsdLogger.trace("sqlRepoPath " + sqlRepoPath.toString()); String
			 * sqlPathString = sqlRepoPath.get(sqlPathId).toString();
			 * tsdLogger.trace("sqlPathString " + sqlPathString);
			 * 
			 * sqlText = new XMLProcessor().getSQLString(sqlId, sqlPathString);
			 */
		}
		tsdLogger.trace("sqlText " + sqlText);

		// *** Newly added for dynamic where clause
		// if there is a dynamic where clause add it to the
		// THe client will have to pass the where clause without WHERE word.
		// e.g. EmpName IN ('aaa','bbb','ccc') AND DEPT =20.
		if (sqlPropertiesMap != null && !sqlPropertiesMap.isEmpty()) {
			String strWhereClause = (String) sqlPropertiesMap.get(IDAOConstant.DYNAMIC_WHERE);
			tsdLogger.trace("strWhereClause " + strWhereClause);

			if (strWhereClause != null && !strWhereClause.equals("")) {
				if (sqlText.toUpperCase().indexOf("WHERE") > 0) {
					sqlText = sqlText + " AND " + strWhereClause;
					tsdLogger.trace("Final sqlText=== " + sqlText);
				} else {
					sqlText = sqlText + " WHERE " + strWhereClause;
					tsdLogger.trace("Final sqlText " + sqlText);
				}

			}
		}
		// *** End of Newly Added code

		sqlRequest.setSql(sqlText);

		sqlcontext.setSqlRequest(sqlRequest);

		return sqlcontext;

	}

	private ITSDLogger tsdLogger = TSDLoggerFactory.getLoggerInstance(ITSDConstant.DAO_LOG);
}
