package com.web.foundation.logger;

import java.util.logging.Logger;

/**
 * Sample JDK logger implementation.
 * 
 * @author shrikantk
 * 
 */
public class Jdk14LoggerImpl extends TSDAbstractLogger {

	private Logger logger = null;

	public Jdk14LoggerImpl(String strLoggerName) {
		logger = Logger.getLogger(strLoggerName);
	}

	public void debug(Object message) {

		logger.info("DEBUG-INFO" + message.toString());

	}

	public void error(Object message) {

	}

	public void fatal(Object message) {

	}

	public void info(Object message) {
		logger.info("INFO-INFO" + message.toString());
	}

	/*
	 * public void trace(final Object message) {
	 * 
	 * }
	 */

	public void warn(Object message) {
		logger.warning(message.toString());
	}

	public void trace(Object message) {
		logger.fine(message.toString());

	}

	public void trace(Object message, Throwable t) {
		logger.fine(message.toString());
	}

	@Override
	public void debug(Object message, Throwable t) {

	}

	@Override
	public void error(Object message, Throwable t) {
	}

	@Override
	public void fatal(Object message, Throwable t) {

	}

	@Override
	public void info(Object message, Throwable t) {

	}

	@Override
	public void warn(Object message, Throwable t) {

	}

}
