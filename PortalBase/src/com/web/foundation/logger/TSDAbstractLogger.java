package com.web.foundation.logger;

public abstract class TSDAbstractLogger implements ITSDLogger {

	public abstract void debug(Object message);

	public abstract void debug(Object message, Throwable t);

	public abstract void error(Object message);

	public abstract void error(Object message, Throwable t);

	public abstract void fatal(Object message);

	public abstract void fatal(Object message, Throwable t);

	public abstract void info(Object message);

	public abstract void info(Object message, Throwable t);

	/*
	 * public void trace(final Object message) {
	 * 
	 * 
	 * }
	 * 
	 * public void trace(final Object message , final Throwable t) {
	 * 
	 * 
	 * }
	 */

	public abstract void warn(Object message);

	public abstract void warn(Object message, Throwable t);

}
