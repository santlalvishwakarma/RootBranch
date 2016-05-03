package com.web.foundation.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import com.web.foundation.logger.ITSDConstant;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLoggerFactory;

public class JdbcStatement {

	public SqlContext sqlContext = null;

	public Connection conn = null;

	public JdbcStatement(SqlContext sqlContext) {
		this.sqlContext = sqlContext;
	}

	public Statement getSqlStatement(Connection conn) throws SQLException {
		tsdLogger.trace("JdbcStatement :: getSqlStatement ");

		this.conn = conn;
		SqlRequest sqlRequest = this.sqlContext.getSqlRequest();

		tsdLogger.trace("JdbcStatement :: getSqlStatement sqlRequest");

		String stmtType = (String) sqlRequest.getHmInputParams().get(IDAOConstant.STATEMENT_TYPE);
		tsdLogger.trace("JdbcStatement :: getSqlStatement sqlRequest stmtType" + stmtType);

		if (stmtType.equals(IDAOConstant.PREPARED_STATEMENT)) {
			tsdLogger.trace("JdbcStatement :: getSqlStatement :: returning preparedStatement");

			return getPreparedStatement();
		} else if (stmtType.equals(IDAOConstant.STATEMENT)) {
			tsdLogger.trace("JdbcStatement :: getSqlStatement :: returning Statement");

			return getStatement();
		} else { // by default prepared statement
			tsdLogger.trace("JdbcStatement :: getSqlStatement :: returning default preparedStatement");

			return getPreparedStatement();
		}
		/*
		 * else if (stmtType.equals(IDAOConstant.CALLABLE_STATEMENT)) {
		 * tsdLogger
		 * .trace("JdbcStatement :: getSqlStatement :: returning callable Statement"
		 * );
		 * 
		 * return getCallableStatement(); }
		 */

	}

	// dynamic sql where clause setup
	// Think of in clause as well.
	private PreparedStatement getPreparedStatement() throws SQLException {

		tsdLogger.trace("JdbcStatement :: getPreparedStatement");

		PreparedStatement pstmt = this.conn.prepareStatement(this.sqlContext.getSqlRequest().getSql());

		// HashMap hmInputParams =
		// this.sqlContext.getSqlRequest().getHmInputParams();
		HashMap<Integer, SeqTypeValueMapper> hmInputParams = this.sqlContext.getSqlRequest().getHmSqlQuery();
		// hmInputParams=this.sqlContext.getSqlRequest().getHmSqlQuery();

		pstmt = prepareStatement(hmInputParams, pstmt);
		/* my code */

		/* end mycode */

		tsdLogger.trace("JdbcStatement :: getPreparedStatement:: returning preparedStatement");

		return pstmt;

	}

	private Statement getStatement() throws SQLException {
		tsdLogger.trace("JdbcStatement :: getPreparedStatement::returning Statement");

		return this.conn.createStatement();

	}

	/*
	 * private Statement getCallableStatement() throws SQLException {
	 * 
	 * tsdLogger.trace("JdbcStatement :: getCallableStatement");
	 * tsdLogger.trace("Value of sql request got: " +
	 * this.sqlContext.getSqlRequest().getSql()); CallableStatement cstmt =
	 * this.conn.prepareCall(this.sqlContext.getSqlRequest().getSql());
	 * tsdLogger.trace("CallableStatement set."); HashMap<String, String>
	 * hmInputParams = this.sqlContext.getSqlRequest().getHmInputParams();
	 * tsdLogger.trace("HMInputParams :" + hmInputParams); HashMap<Integer,
	 * SeqTypeValueMapper> hmRegisterOutParams =
	 * this.sqlContext.getSqlRequest().getHmOutputParams();
	 * tsdLogger.trace("HMOutputParams :" + hmRegisterOutParams); cstmt =
	 * (CallableStatement) prepareCallableStatement(hmInputParams,
	 * hmRegisterOutParams, cstmt); // prepare the output params.
	 * 
	 * tsdLogger.trace(
	 * "JdbcStatement :: getCallableStatement :: returning callable statement");
	 * return cstmt; }
	 */

	private PreparedStatement prepareStatement(HashMap<Integer, SeqTypeValueMapper> hmInputParams,
			PreparedStatement pstmt) throws SQLException {

		tsdLogger.trace("JdbcStatement :: prepareStatement");

		SeqTypeValueMapper mapper = null;
		int iSeq = -1;

		if (hmInputParams != null) {
			tsdLogger.trace("hmInputParams is " + hmInputParams);
			for (int i = 0; i <= hmInputParams.size(); i++) {
				tsdLogger.trace("The current value of counter i for hmInputParams is" + i);
				mapper = (SeqTypeValueMapper) hmInputParams.get(Integer.valueOf(i));

				if (mapper != null) {
					tsdLogger.trace(mapper.toString());
					iSeq = mapper.getSeq();
					if (mapper.getValue() == null || (mapper.getValue() != null && mapper.getValue().equals(""))) {
						pstmt.setNull(iSeq, mapper.getType());
						continue;
					}
					switch (mapper.getType()) {
					case IDAOConstant.BOOLEAN_DATATYPE:
						tsdLogger.trace("in case boolean");
						tsdLogger.trace(mapper.getValue().toString());
						pstmt.setBoolean(iSeq, ((Boolean) mapper.getValue()).booleanValue());
						break;
					case IDAOConstant.SHORT_DATATYPE:
						tsdLogger.trace("in case short");
						tsdLogger.trace(mapper.getValue().toString());
						pstmt.setShort(iSeq, ((Short) mapper.getValue()).shortValue());
						break;
					case IDAOConstant.INT_DATATYPE:
						tsdLogger.trace("in case int");
						tsdLogger.trace(mapper.getValue().toString());
						pstmt.setInt(iSeq, ((Integer) mapper.getValue()).intValue());
						break;
					case IDAOConstant.LONG_DATATYPE:
						tsdLogger.trace("in case long");
						tsdLogger.trace(mapper.getValue().toString());
						pstmt.setLong(iSeq, ((Long) mapper.getValue()).longValue());
						break;
					case IDAOConstant.FLOAT_DATATYPE:
						tsdLogger.trace("in case float");
						tsdLogger.trace(mapper.getValue().toString());
						pstmt.setFloat(iSeq, ((Float) mapper.getValue()).floatValue());
						break;
					case IDAOConstant.DOUBLE_DATATYPE:
						tsdLogger.trace("in case double");
						tsdLogger.trace(mapper.getValue().toString());
						pstmt.setDouble(iSeq, ((Double) mapper.getValue()).doubleValue());
						break;
					case IDAOConstant.STRING_DATATYPE:
						tsdLogger.trace("in case string");
						tsdLogger.trace(mapper.getValue().toString());
						pstmt.setString(iSeq, ((String) mapper.getValue()));
						break;
					case IDAOConstant.DATE_DATATYPE:
						tsdLogger.trace("in case date");
						tsdLogger.trace(mapper.getValue().toString());
						pstmt.setDate(iSeq, ((Date) mapper.getValue()));
						break;
					case IDAOConstant.OBJECT_DATATYPE:
						tsdLogger.trace("in case object");
						tsdLogger.trace(mapper.getValue().toString());
						pstmt.setObject(iSeq, mapper.getValue());
						break;
					case IDAOConstant.BIGDECIMAL_DATATYPE:
						pstmt.setBigDecimal(iSeq, ((BigDecimal) mapper.getValue()));
						break;
					}
				}
			}

		}
		tsdLogger.trace("JdbcStatement :: prepareStatement:: returning preparedStatement (common)");

		return pstmt;

	}

	/*
	 * private PreparedStatement prepareCallableStatement(HashMap<Integer,
	 * SeqTypeValueMapper> hmInputParams, HashMap<Integer, SeqTypeValueMapper>
	 * hmRegisterOutParams, CallableStatement cstmt) throws SQLException {
	 * 
	 * tsdLogger.trace("JdbcStatement :: prepareCallableStatement");
	 * SeqTypeValueMapper mapper = null; int iSeq = -1; // SET OUT PARAMS if
	 * (hmRegisterOutParams != null) {
	 * 
	 * for (int i = 0; i <= hmRegisterOutParams.size(); i++) { mapper =
	 * (SeqTypeValueMapper) hmRegisterOutParams.get(Integer.valueOf(i)); if
	 * (mapper != null) {
	 * 
	 * iSeq = mapper.getSeq();
	 * 
	 * if (mapper.getType() == IDAOConstant.REF_CURSOR) {
	 * cstmt.registerOutParameter(iSeq, java.sql.Types.REF); // else do it for
	 * other datatypes..need to define // the jdbc and db datatype // mapping.
	 * switch (mapper.getType()) { case IDAOConstant.REF_CURSOR:
	 * tsdLogger.trace("Callable stmt out parameter: in case ref cursor");
	 * cstmt.registerOutParameter(iSeq, java.sql.Types.REF); break; case
	 * IDAOConstant.BOOLEAN_DATATYPE:
	 * tsdLogger.trace("Callable stmt out parameter: in case boolean");
	 * cstmt.registerOutParameter(iSeq, java.sql.Types.BOOLEAN); break; case
	 * IDAOConstant.SHORT_DATATYPE:
	 * tsdLogger.trace("Callable stmt out parameter: in case short");
	 * cstmt.registerOutParameter(iSeq, java.sql.Types.TINYINT); break; case
	 * IDAOConstant.INT_DATATYPE:
	 * tsdLogger.trace("Callable stmt out parameter: in case int");
	 * cstmt.registerOutParameter(iSeq, java.sql.Types.INTEGER); break; case
	 * IDAOConstant.LONG_DATATYPE:
	 * tsdLogger.trace("Callable stmt out parameter: in case long");
	 * cstmt.registerOutParameter(iSeq, java.sql.Types.BIGINT); break; case
	 * IDAOConstant.FLOAT_DATATYPE:
	 * tsdLogger.trace("Callable stmt out parameter: in case float");
	 * cstmt.registerOutParameter(iSeq, java.sql.Types.FLOAT); break; case
	 * IDAOConstant.DOUBLE_DATATYPE:
	 * tsdLogger.trace("Callable stmt out parameter: in case double");
	 * cstmt.registerOutParameter(iSeq, java.sql.Types.DOUBLE); break; case
	 * IDAOConstant.STRING_DATATYPE:
	 * tsdLogger.trace("Callable stmt out parameter: in case string");
	 * cstmt.registerOutParameter(iSeq, java.sql.Types.VARCHAR); break; case
	 * IDAOConstant.DATE_DATATYPE:
	 * tsdLogger.trace("Callable stmt out parameter: in case date");
	 * cstmt.registerOutParameter(iSeq, java.sql.Types.DATE); break; case
	 * IDAOConstant.OBJECT_DATATYPE:
	 * tsdLogger.trace("Callable stmt out parameter: in case object");
	 * cstmt.registerOutParameter(iSeq, java.sql.Types.STRUCT); break; case
	 * IDAOConstant.BIGDECIMAL_DATATYPE:
	 * tsdLogger.trace("Callable stmt out parameter: in case bigdecimal");
	 * cstmt.registerOutParameter(iSeq, java.sql.Types.DECIMAL); break; case
	 * IDAOConstant.DATETIME_DATATYPE:
	 * tsdLogger.trace("Callable stmt out parameter: in case datetime");
	 * cstmt.registerOutParameter(iSeq, java.sql.Types.TIMESTAMP); break; } } }
	 * } }
	 * 
	 * // SET THE INPUT PARAMS if (hmInputParams != null) {
	 * 
	 * for (int i = 0; i <= hmInputParams.size(); i++) { mapper =
	 * (SeqTypeValueMapper) hmInputParams.get(Integer.valueOf(i));
	 * 
	 * if (mapper != null) {
	 * 
	 * iSeq = mapper.getSeq(); if (mapper.getValue() == null) {
	 * cstmt.setNull(iSeq, mapper.getType()); continue; } switch
	 * (mapper.getType()) { case IDAOConstant.BOOLEAN_DATATYPE:
	 * tsdLogger.trace("Callable stmt check: in case boolean");
	 * cstmt.setBoolean(iSeq, ((Boolean) mapper.getValue()).booleanValue());
	 * break; case IDAOConstant.SHORT_DATATYPE:
	 * tsdLogger.trace("Callable stmt check: in case short");
	 * cstmt.setShort(iSeq, ((Short) mapper.getValue()).shortValue()); break;
	 * case IDAOConstant.INT_DATATYPE:
	 * tsdLogger.trace("Callable stmt check: in case int"); cstmt.setInt(iSeq,
	 * ((Integer) mapper.getValue()).intValue()); break; case
	 * IDAOConstant.LONG_DATATYPE:
	 * tsdLogger.trace("Callable stmt check: in case boolean");
	 * cstmt.setLong(iSeq, ((Long) mapper.getValue()).longValue()); break; case
	 * IDAOConstant.FLOAT_DATATYPE:
	 * tsdLogger.trace("Callable stmt check: in case float");
	 * cstmt.setFloat(iSeq, ((Float) mapper.getValue()).floatValue()); break;
	 * case IDAOConstant.DOUBLE_DATATYPE:
	 * tsdLogger.trace("Callable stmt check: in case double");
	 * cstmt.setDouble(iSeq, ((Double) mapper.getValue()).doubleValue()); break;
	 * case IDAOConstant.STRING_DATATYPE:
	 * tsdLogger.trace("Callable stmt check: in case string");
	 * cstmt.setString(iSeq, ((String) mapper.getValue())); break; case
	 * IDAOConstant.DATE_DATATYPE:
	 * tsdLogger.trace("Callable stmt check: in case date"); cstmt.setDate(iSeq,
	 * ((Date) mapper.getValue())); break; case IDAOConstant.OBJECT_DATATYPE:
	 * tsdLogger.trace("Callable stmt check: in case object");
	 * cstmt.setObject(iSeq, mapper.getValue()); break; case
	 * IDAOConstant.BIGDECIMAL_DATATYPE:
	 * tsdLogger.trace("Callable stmt check: in case bigdecimal");
	 * cstmt.setBigDecimal(iSeq, ((BigDecimal) mapper.getValue())); break; case
	 * IDAOConstant.DATETIME_DATATYPE:
	 * tsdLogger.trace("Callable stmt check: in case datetime");
	 * cstmt.setTimestamp(iSeq, ((Timestamp) mapper.getValue())); break; } } }
	 * 
	 * }
	 * 
	 * tsdLogger.trace(
	 * "JdbcStatement :: prepareStatement:: returning preparedStatement (common)"
	 * ); return cstmt;
	 * 
	 * }
	 */

	private ITSDLogger tsdLogger = TSDLoggerFactory.getLoggerInstance(ITSDConstant.DAO_LOG);
}
