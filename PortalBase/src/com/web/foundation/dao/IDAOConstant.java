package com.web.foundation.dao;

public final class IDAOConstant {

	private IDAOConstant() {

	}

	public static final String SQL_TYPE = "SQLTYPE";
	public static final String SQL_ID = "SQLID";
	public static final String SQL_PATH_ID = "SQLPATHID";
	public static final String SQL_TEXT = "SQL_TEXT";

	public static final String SELECT_SQL = "SELECT";
	public static final String INSERT_SQL = "INSERT";
	public static final String UPDATE_SQL = "UPDATE";
	public static final String DELETE_SQL = "DELETE";

	public static final String STATEMENT_TYPE = "STATEMENT_TYPE";
	public static final String PREPARED_STATEMENT = "PREPARED_STATEMENT";
	public static final String STATEMENT = "STATEMENT";
	public static final String CALLABLE_STATEMENT = "CALLABLE_STATEMENT";

	public static final int SHORT_DATATYPE = 0;
	public static final int INT_DATATYPE = 1;
	public static final int FLOAT_DATATYPE = 2;
	public static final int LONG_DATATYPE = 3;
	public static final int DOUBLE_DATATYPE = 4;
	public static final int STRING_DATATYPE = 5;
	public static final int BOOLEAN_DATATYPE = 6;
	public static final int DATE_DATATYPE = 7;
	public static final int CHAR_DATATYPE = 8;
	public static final int OBJECT_DATATYPE = 9;
	public static final int BIGDECIMAL_DATATYPE = 10;
	// // Added newly Shrikant
	public static final int REF_CURSOR = 11; // to be used for stored procs only
	public static final String DYNAMIC_WHERE = "DYNAMIC_WHERE"; // for search
																// screens(e.g.
																// dynamic IN
																// clause).

	public static final int DATETIME_DATATYPE = 12;
}
