package com.web.common.parents;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.web.common.constants.CommonConstant;
import com.web.foundation.exception.LocatorException;

public abstract class BackingClass extends ParentBackingClass {

	public Connection createDBConnection() throws LocatorException, SQLException {
		Context objContext;
		Connection connect = null;
		try {
			objContext = new InitialContext();
			javax.sql.DataSource ds = (javax.sql.DataSource) objContext.lookup(CommonConstant.CONTEXT_LOOKUP_ROOT
					+ CommonConstant.SYSTEM_DATASOURCE);
			connect = ds.getConnection();
		} catch (NamingException nex) {
			nex.printStackTrace();
			throw new LocatorException("locator_excep_msg", nex);
		}
		return connect;
	}

	// Return convert object to date.
	public Date convertObjectToDate(Object objValue) {
		Date returnDt = null;
		if (objValue != null) {
			if (objValue instanceof java.sql.Timestamp) {
				Date objDate = (Date) objValue;
				returnDt = new Date(objDate.getTime());

			} else if (objValue instanceof java.sql.Date) {
				Date objDate = (Date) objValue;
				returnDt = new Date(objDate.getTime());
			}
		}
		return returnDt;
	}

	public String convertObjectToString(Object objValue) {
		return (String) objValue;
	}

	public Integer convertObjectToInteger(Object objValue) {
		if (objValue != null)
			return Integer.valueOf(objValue.toString());
		return null;
	}

	public Long convertObjectToLong(Object objValue) {
		if (objValue != null)
			return Long.valueOf(objValue.toString());
		return null;
	}

	public Float convertObjectToFloat(Object objValue) {
		if (objValue != null)
			return Float.valueOf(objValue.toString());
		return null;
	}

	public Double convertObjectToDouble(Object objValue) {
		if (objValue != null)
			return Double.valueOf(objValue.toString());
		return null;
	}

	public Boolean convertObjectToBoolean(Object objValue) {
		if (objValue != null)
			return (Boolean) objValue;
		return null;
	}

	protected PreparedStatement setParameters(PreparedStatement cstmt, Integer position, Object value)
			throws SQLException {
		if (value == null) {
			int valueType = 0;
			if (value instanceof Boolean) {
				valueType = Types.BOOLEAN;

			} else if (value instanceof String) {
				valueType = Types.VARCHAR;

			} else if (value instanceof Long) {
				valueType = Types.BOOLEAN;

			} else if (value instanceof Float) {
				valueType = Types.FLOAT;

			} else if (value instanceof Timestamp) {
				valueType = Types.TIMESTAMP;

			} else if (value instanceof Date) {
				valueType = Types.DATE;

			} else if (value instanceof Integer) {
				valueType = Types.INTEGER;

			} else if (value instanceof Double) {
				valueType = Types.DOUBLE;

			} else if (value instanceof Object) {
				valueType = Types.JAVA_OBJECT;

			} else if (value instanceof BigDecimal) {
				valueType = Types.DECIMAL;
			}
			cstmt.setNull(position, valueType);

		} else {
			if (value instanceof Boolean) {
				cstmt.setBoolean(position, (Boolean) value);

			} else if (value instanceof String) {
				cstmt.setString(position, (String) value);

			} else if (value instanceof Long) {
				cstmt.setLong(position, (Long) value);

			} else if (value instanceof Float) {
				cstmt.setFloat(position, (Float) value);

			} else if (value instanceof Timestamp) {
				cstmt.setTimestamp(position, (Timestamp) value);

			} else if (value instanceof Date) {
				cstmt.setTimestamp(position, new Timestamp(((Date) value).getTime()));

			} else if (value instanceof Integer) {
				cstmt.setInt(position, (Integer) value);

			} else if (value instanceof Double) {
				cstmt.setDouble(position, (Double) value);

			} else if (value instanceof Object) {
				cstmt.setObject(position, value);

			} else if (value instanceof BigDecimal) {
				cstmt.setBigDecimal(position, (BigDecimal) value);
			}
		}
		return cstmt;
	}
}
