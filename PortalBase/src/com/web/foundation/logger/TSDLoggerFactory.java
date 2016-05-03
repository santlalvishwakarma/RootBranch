package com.web.foundation.logger;

public class TSDLoggerFactory {

	private static String strLoggerType = null;

	static {
		new TSDLoggerFactory().getLoggerType();
	}

	public static ITSDLogger getLoggerInstance(String strLoggerName) {

		if (strLoggerType.equals(ITSDConstant.LOG4J)) {
			return new Log4jLoggerImpl(strLoggerName);
		} else if (strLoggerType.equals(ITSDConstant.JDK14)) {
			return new JdkLoggerImpl(strLoggerName);
		} else if (strLoggerType.equals(ITSDConstant.JDK15)) {
			return new JdkLoggerImpl(strLoggerName);
		} else {
			return new Log4jLoggerImpl(strLoggerName);
		}

	}

	private void getLoggerType() {

		String retLoggerType = null;
		// Libin - commented the code that reads the logger type from a property
		// file since we are using log4j right
		// now
		// use static block to load the properties into the system properties.
		// InputStream logPropertyFile =
		// getClass().getClassLoader().getResourceAsStream("TSDLogger.properties");
		// InputStream logPropertyFile = new
		// FileInputStream("TSDLogger.properties");
		// Properties prop = new Properties();

		// prop.load(logPropertyFile);
		retLoggerType = ITSDConstant.LOG4J;// prop.getProperty("tsd-logger-type");
											// // put this in the constant.

		strLoggerType = retLoggerType;
	}

}
