package com.web.foundation.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import com.web.foundation.exception.FrameworkException;
import com.web.foundation.locator.ILocatorConstants;
import com.web.foundation.locator.ResourceFacade;
import com.web.foundation.locator.ResourceRequest;
import com.web.foundation.logger.ITSDConstant;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLoggerFactory;

public final class JdbcRequestHandler {

	private Object firstColumnData = null;

	// Responsibilities
	// 1. Look up the Datasource
	// 2. get DB connection
	// 3. prepare the statement, prepared statements, callable statements.
	// 4. executeQuery,executeUpdate, executeBatchUpdate.
	// 5. Return resultset to the client.

	private JdbcRequestHandler() {

	}

	public static JdbcRequestHandler getJdbcRequestHandler() {

		return new JdbcRequestHandler();
	}

	public SqlResponse handleJdbcRequest(SqlContext sqlContext) throws FrameworkException {

		tsdLogger.trace("JdbcRequestHandler :: handleJdbcRequest");

		SqlResponse sqlResponse = new SqlResponse();
		Connection conn = null;
		Statement stmt = null;
		try {
			// SqlRequest sqlRequest = sqlContext.getSqlRequest();

			// Populate the ResourceRequest object.
			String strDSource = sqlContext.getSqlRequest().getDataSourceName();
			ResourceRequest rsReq = new ResourceRequest();
			HashMap<String, String> hmInput = new HashMap<String, String>();
			hmInput.put("DATASOURCE_NAME", strDSource);
			rsReq.setHmProperties(hmInput);
			rsReq.setResourceType(ILocatorConstants.LOCATOR_DATASOURCE);

			javax.sql.DataSource ds = (javax.sql.DataSource) ResourceFacade.getResource(rsReq);
			tsdLogger.trace("JdbcRequestHandler :: handleJdbcRequest:: GOT RESOURCE" + ds);

			// prepare DB request

			conn = ds.getConnection();
			// conn.setAutoCommit(false);

			if (conn != null && conn.isClosed()) {
				tsdLogger.error("JdbcRequestHandler :: test for conn.isClosed() ---> true");

				conn = ds.getConnection();

				tsdLogger.trace("JdbcRequestHandler :: test for conn.isClosed() ---> got connection");
			}
			tsdLogger.trace("JdbcRequestHandler :: test for conn.isClosed() ends");

			stmt = new JdbcStatement(sqlContext).getSqlStatement(conn);

			if (sqlContext.getSqlRequest().getHmInputParams().get(IDAOConstant.STATEMENT_TYPE).equals(
					IDAOConstant.CALLABLE_STATEMENT)) {
				// if the return type is ref_cursor then call executeProc and
				// set the return type to
				// the sqlResponse
				sqlResponse = executeProc((CallableStatement) stmt);

			} else if (sqlContext.getSqlRequest().getHmInputParams().get(IDAOConstant.SQL_TYPE).equals(
					IDAOConstant.SELECT_SQL)) {
				if (sqlContext.getSqlRequest().getHmInputParams().get(IDAOConstant.STATEMENT_TYPE).equals(
						IDAOConstant.STATEMENT))
					sqlResponse = executeQuery((Statement) stmt, sqlContext.getSqlRequest().getSql());
				else
					sqlResponse = executeQuery((PreparedStatement) stmt);

			} else {
				sqlResponse = executeUpdate((PreparedStatement) stmt);
			}
			// prepare Statement
			// executeQuery
		} catch (SQLException sqlex) {
			tsdLogger.error("JdbcRequestHandler :: handleJdbcRequest :: SQLException", sqlex);

			sqlResponse.setSqlException(sqlex);
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException sqlex1) {
				tsdLogger.error("JdbcRequestHandler :: handleJdbcRequest :: finally 1 :: SQLException", sqlex1);
				sqlResponse.setSqlException(sqlex1);
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqlex1) {
				tsdLogger.error("JdbcRequestHandler :: handleJdbcRequest :: finally :: SQLException", sqlex1);

				sqlResponse.setSqlException(sqlex1);
			}
		}

		tsdLogger.trace("JdbcRequestHandler :: handleJdbcRequest :: returning sql response");

		return sqlResponse;
	}

	/**
	 * This method takes the preparedStatement, executes the query and returns
	 * the ResultSet Object.
	 * 
	 * @param preparedStatement
	 * @return rsResultSet
	 */

	private SqlResponse executeQuery(PreparedStatement a_oPreparedStatement) {
		tsdLogger.trace("JdbcRequestHandler :: executeQuery ::: " + a_oPreparedStatement);

		ResultSet rsResultSet = null;
		SqlResponse sqlResponse = new SqlResponse();
		try {
			rsResultSet = a_oPreparedStatement.executeQuery();
			HashMap<Integer, HashMap<String, Object>> hmValues = processResultSet(rsResultSet);
			HashMap<Object, HashMap<Integer, HashMap<String, Object>>> multipleResultSet = new HashMap<Object, HashMap<Integer, HashMap<String, Object>>>();
			multipleResultSet.put(firstColumnData, hmValues);
			while (a_oPreparedStatement.getMoreResults(PreparedStatement.KEEP_CURRENT_RESULT)) {
				rsResultSet = a_oPreparedStatement.getResultSet();
				hmValues = processResultSet(rsResultSet);
				multipleResultSet.put(firstColumnData, hmValues);
			}
			// Convert the ResultSet into the hashmap and set it in the
			// Response.
			// processResultSet
			sqlResponse.setHmDataValues(hmValues);
			sqlResponse.setMultipleResultSet(multipleResultSet);

		} catch (SQLException sqlex) {
			tsdLogger.error("JdbcRequestHandler :: executeQuery :: SQLException", sqlex);

			sqlResponse.setSqlException(sqlex);

		} finally {
			try {
				if (rsResultSet != null) {
					rsResultSet.close();
				}
			} catch (SQLException sqlex1) {
				tsdLogger.error("JdbcRequestHandler :: executeQuery :: finally :: ", sqlex1);
				sqlResponse.setSqlException(sqlex1);
			}
		}

		tsdLogger.trace("JdbcRequestHandler :: executeQuery:: returning sqlResponse");

		return sqlResponse;
	}

	private SqlResponse executeQuery(Statement a_oStatement, String a_strQuery) {
		tsdLogger.trace("JdbcRequestHandler :: executeQuery ::: " + a_strQuery);

		ResultSet rsResultSet = null;
		SqlResponse sqlResponse = new SqlResponse();
		try {

			rsResultSet = a_oStatement.executeQuery(a_strQuery);
			HashMap<Integer, HashMap<String, Object>> hmValues = processResultSet(rsResultSet);
			HashMap<Object, HashMap<Integer, HashMap<String, Object>>> multipleResultSet = new HashMap<Object, HashMap<Integer, HashMap<String, Object>>>();
			multipleResultSet.put(firstColumnData, hmValues);
			while (a_oStatement.getMoreResults(PreparedStatement.KEEP_CURRENT_RESULT)) {
				rsResultSet = a_oStatement.getResultSet();
				hmValues = processResultSet(rsResultSet);
				multipleResultSet.put(firstColumnData, hmValues);
			}
			// Convert the ResultSet into the hashmap and set it in the
			// Response.
			// processResultSet

			sqlResponse.setHmDataValues(hmValues);
			sqlResponse.setMultipleResultSet(multipleResultSet);

		} catch (SQLException sqlex) {
			tsdLogger.error("JdbcRequestHandler :: executeQuery :: SQLException", sqlex);

			sqlResponse.setSqlException(sqlex);

		} finally {
			try {
				if (rsResultSet != null) {
					rsResultSet.close();
				}
			} catch (SQLException sqlex1) {
				tsdLogger.error("JdbcRequestHandler :: executeQuery :: finally :: ", sqlex1);
				sqlResponse.setSqlException(sqlex1);
			}
		}

		tsdLogger.trace("JdbcRequestHandler :: executeQuery:: returning sqlResponse");

		return sqlResponse;
	}

	// Maxfetch size etc.
	private HashMap<Integer, HashMap<String, Object>> processResultSet(ResultSet rsResult) throws SQLException {

		tsdLogger.trace("JdbcRequestHandler :: processResultSet");

		HashMap<Integer, HashMap<String, Object>> hmRet = new HashMap<Integer, HashMap<String, Object>>();
		HashMap<String, Object> hmColVal = null;
		// Object retVal = null;

		ResultSetMetaData rsMeta = rsResult.getMetaData();
		int iTotColCount = rsMeta.getColumnCount();
		tsdLogger.trace("ASASASA" + rsMeta.getColumnLabel(1));

		firstColumnData = rsMeta.getColumnLabel(1);
		String strColName = "";
		Object objValue = null;
		int iRecCount = 0;
		tsdLogger.trace("JdbcRequestHandler :: processResultSet :: iTotColCount" + iTotColCount);

		try {
			while (rsResult.next()) {

				hmColVal = new HashMap<String, Object>();

				for (int iCount = 1; iCount <= iTotColCount; iCount++) {

					tsdLogger.trace("JdbcRequestHandler :: processResultSet :: iTotColCount TESSST");

					strColName = rsMeta.getColumnLabel(iCount);

					tsdLogger.trace("JdbcRequestHandler :: processResultSet :: iTotColCount" + iTotColCount + ":"
							+ strColName);

					objValue = rsResult.getObject(iCount);

					if (rsResult.wasNull())
						objValue = null;

					tsdLogger.trace("JdbcRequestHandler :: processResultSet :: iTotColCount objValue" + iTotColCount
							+ ":" + objValue);

					if (objValue instanceof java.sql.Date) {
						java.sql.Date objDate = (java.sql.Date) objValue;
						java.util.Date returnDate = new java.util.Date(objDate.getTime());
						hmColVal.put(strColName, returnDate);
					} else {
						hmColVal.put(strColName, objValue);
					}
				}

				hmRet.put(Integer.valueOf(iRecCount), hmColVal);
				iRecCount++;
			}
		} catch (SQLException e) {
			tsdLogger.error("JdbcRequestHandler :: processResultSet :: exceptio" + e);
			e.printStackTrace();
		}
		tsdLogger.trace("JdbcRequestHandler :: processResultSet :: returning hashmap");

		return hmRet;
	}

	/**
	 * This method takes the Statement and the SqlString, executes the query and
	 * returns the ResultSet Object.
	 * 
	 * @param a_sqlString
	 *            String
	 * @return rsResultSet
	 */
	/*
	 * public ResultSet executeQuery(String a_sqlString) { ResultSet rsResultSet
	 * = null; try{ rsResultSet = getStatement().executeQuery(a_sqlString); }
	 * catch(SQLException sqlex){ } return rsResultSet; }
	 */
	/**
	 * This method takes the preparedStatement, executes the update statement
	 * and returns the update count
	 * 
	 * @param preparedStatement
	 * @return iUpdateCount
	 * @throws EElixirException
	 */
	private SqlResponse executeUpdate(PreparedStatement a_oPreparedStatement) {

		tsdLogger.trace("JdbcRequestHandler :: executeUpdate");

		int iUpdateCount = 0;
		SqlResponse sqlResponse = new SqlResponse();
		try {
			iUpdateCount = a_oPreparedStatement.executeUpdate();
			sqlResponse.setUpdateResult(iUpdateCount);
		} catch (SQLException sqlex) {
			tsdLogger.error("JdbcRequestHandler :: executeUpdate :: SQLException", sqlex);

			sqlResponse.setSqlException(sqlex);
		}

		tsdLogger.trace("JdbcRequestHandler :: executeUpdate :: returning sqlresponse");

		return sqlResponse;
	}

	private SqlResponse executeProc(CallableStatement a_oStatement) {
		tsdLogger.trace("JdbcRequestHandler :: executeProc");

		ResultSet rsResultSet = null;
		SqlResponse sqlResponse = new SqlResponse();
		try {

			a_oStatement.execute();
			rsResultSet = (ResultSet) a_oStatement.getObject(1); // This code
			// is
			// specific
			// to the
			// cursor.

			// Convert the ResultSet into the hashmap and set it in the
			// Response.
			// processResultSet
			HashMap<Integer, HashMap<String, Object>> hmValues = processResultSet(rsResultSet);
			sqlResponse.setHmDataValues(hmValues);

		} catch (SQLException sqlex) {
			tsdLogger.error("JdbcRequestHandler :: executeQuery :: SQLException", sqlex);
			sqlResponse.setSqlException(sqlex);

		} finally {
			try {
				if (rsResultSet != null) {
					rsResultSet.close();
				}
			} catch (SQLException sqlex1) {
				tsdLogger.error("JdbcRequestHandler :: executeProc :: finally :: ", sqlex1);
				sqlResponse.setSqlException(sqlex1);
			}
		}

		tsdLogger.trace("JdbcRequestHandler :: executeQuery:: returning sqlResponse");
		return sqlResponse;
	}

	/**
	 * This method takes the preparedStatement, executes the bacth of statements
	 * 
	 * @param preparedStatement
	 * @return iUpdateCount
	 * @throws EElixirException
	 */
	/*
	 * private int[] executeBatch(PreparedStatement a_oPreparedStatement) {
	 * 
	 * int iUpdateCount[] = null; SqlResponse sqlResponse = new SqlResponse();
	 * 
	 * try { iUpdateCount = a_oPreparedStatement.executeBatch(); if
	 * (iUpdateCount != null) sqlResponse.setUpdateResult(iUpdateCount.length);
	 * } catch (SQLException sqlex) {
	 * tsdLogger.error("JdbcRequestHandler :: executeUpdate :: SQLException",
	 * sqlex);
	 * 
	 * sqlResponse.setSqlException(sqlex); } return iUpdateCount; }
	 */

	private ITSDLogger tsdLogger = TSDLoggerFactory.getLoggerInstance(ITSDConstant.DAO_LOG);

}
