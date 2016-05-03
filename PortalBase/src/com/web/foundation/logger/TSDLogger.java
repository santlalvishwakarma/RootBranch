package com.web.foundation.logger;

public class TSDLogger {

	public static ITSDLogger getLogger(String strLoggerName) {

		// return
		// (ITSDLogger)TSDLogClassLoader.getLoggerObjectInstance(strLoggerName);
		return TSDLoggerFactory.getLoggerInstance(strLoggerName);
	}
}
