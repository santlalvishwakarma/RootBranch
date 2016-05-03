package com.web.foundation.logger;

import org.apache.log4j.Logger;

public class Log4jLoggerImpl extends TSDAbstractLogger {

	private Logger logger = null;

	public Log4jLoggerImpl(String strLoggerName) {

		logger = Logger.getLogger(strLoggerName);

	}

	// this block below not required since l0g4j available in the class path

	// static{
	// BasicConfigurator.configure();
	// }

	public void debug(Object message) {
		logger.debug(message);

	}

	public void error(Object message) {

		logger.error(message);

	}

	public void fatal(Object message) {
		logger.fatal(message);
	}

	public void info(Object message) {

		logger.info(message);

	}

	/*
	 * public final void trace(final Object message) {
	 * 
	 * ((ITSDLogger) logger).trace(message); }
	 */
	public void warn(Object message) {

		logger.warn(message);
	}

	/*
	 * public final void trace(final Object message,final Throwable t){
	 * ((ITSDLogger) logger).trace(message, t); }
	 */

	public void debug(Object message, Throwable t) {
		logger.debug(message, t);
	}

	public void info(Object message, Throwable t) {

		logger.info(message, t);
	}

	public void warn(Object message, Throwable t) {
		logger.warn(message, t);
	}

	public void error(Object message, Throwable t) {
		logger.error(message, t);
	}

	public void fatal(Object message, Throwable t) {
		logger.fatal(message, t);
	}

	public void trace(Object message) {
		logger.debug(message);
	}

	public void trace(Object message, Throwable t) {
		logger.debug(message, t);

	}
}
