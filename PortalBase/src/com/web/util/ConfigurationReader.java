package com.web.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigurationReader {

	public String getWebPropertyFile(String key) {
		String retWebPropertyFile = null;
		InputStream webConfPropertyFile = null;
		Properties propsRead = null;
		try {
			webConfPropertyFile = getClass().getClassLoader().getResourceAsStream("web.properties");
			propsRead = new Properties();
			propsRead.load(webConfPropertyFile);
			retWebPropertyFile = propsRead.getProperty(key);
			// put this in the constant.
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			propsRead = null;
			if (webConfPropertyFile != null) {
				try {
					webConfPropertyFile.close();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
		return retWebPropertyFile;
	}
}
