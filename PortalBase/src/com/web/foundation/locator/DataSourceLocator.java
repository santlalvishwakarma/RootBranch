package com.web.foundation.locator;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.web.common.constants.CommonConstant;
import com.web.foundation.exception.LocatorException;
import com.web.foundation.logger.ITSDConstant;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLoggerFactory;

public class DataSourceLocator implements ILocator {

	public Context objContext;
	private String strDataSourceName;// ="jdbc/myoracle";

	public DataSourceLocator(ResourceRequest resourceReq) {
		this.strDataSourceName = (String) resourceReq.getHmProperties().get("DATASOURCE_NAME");
	}

	public Object locate() throws LocatorException {
		return getDataSource();
	}

	private javax.sql.DataSource getDataSource() throws LocatorException {
		tsdLogger.debug("=======================LOOK UP============");

		// read the config file to get the ds name and other properties
		javax.sql.DataSource ds;
		try {
			// Context objCtx = getDatasourceInitialContext();
			objContext = new InitialContext();
			ds = (javax.sql.DataSource) objContext.lookup(CommonConstant.CONTEXT_LOOKUP_ROOT + strDataSourceName);
		} catch (NamingException nex) {
			nex.printStackTrace();
			throw new LocatorException("locator_excep_msg", nex);
		}
		tsdLogger.debug("RETURNINGGGGGGGGGGGGDS " + ds);

		return ds;
	}

	// Add the required parameters here
	// these params can be read from the config/properties files etc.

	private ITSDLogger tsdLogger = TSDLoggerFactory.getLoggerInstance(ITSDConstant.DAO_LOG);
}
