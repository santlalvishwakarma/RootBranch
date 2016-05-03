package com.web.foundation.logger;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.util.Properties;

public class TSDLogClassLoader {

	private static String strLoggerClassName;
	private static Class<?> klass;

	static {

		TSDLogClassLoader tsdClassLoader = new TSDLogClassLoader();
		tsdClassLoader.setLoggerClassName();
		tsdClassLoader.loadLogger();
	}

	private void setLoggerClassName() {

		String retLoggerClassName = null;
		try {
			// use static block to load the properties into the system
			// properties.
			InputStream logPropertyFile = getClass().getClassLoader().getResourceAsStream("TSDLogger.properties");
			Properties prop = new Properties();

			prop.load(logPropertyFile);
			retLoggerClassName = prop.getProperty("tsd-logger-class"); // put
																		// this
																		// in
																		// the
																		// constant.

			if (retLoggerClassName == null || retLoggerClassName.equals("")) {

			}

		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		strLoggerClassName = retLoggerClassName;
	}

	private void loadLogger() {

		try {
			klass = Class.forName(strLoggerClassName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public static Object getLoggerObjectInstance(String strLoggerName) {

		Object obj = null;
		try {

			Class<?>[] paramTypes = { String.class };
			Constructor<?> c = klass.getConstructor(paramTypes);
			Object[] args = { strLoggerName };
			obj = c.newInstance(args);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return obj;

	}

}
