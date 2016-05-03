package com.web.foundation.logger;

public interface ITSDLogger {

	// Creation & retrieval methods:
	// --public static ITSDLogger getRootLogger();
	// --public static ITSDLogger getLogger(String name);

	// printing methods:
	void trace(Object message);

	void debug(Object message);

	void info(Object message);

	void warn(Object message);

	void error(Object message);

	void fatal(Object message);

	void trace(Object message, Throwable t);

	void debug(Object message, Throwable t);

	void info(Object message, Throwable t);

	void warn(Object message, Throwable t);

	void error(Object message, Throwable t);

	void fatal(Object message, Throwable t);

	// generic printing method:
	// --public void log(Level l, Object message);

}
