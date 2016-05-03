package com.web.util;

import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * This Class is used to access property file by using ResourceBundle.
 * 
 * @author Vikas.
 * @version 1.0
 */

public class PropertiesReader {

	/**
	 * Resource Bundle variable.
	 */

	private ResourceBundle bundle = null;

	public PropertiesReader() {
		super();
	}

	public PropertiesReader(String propertiesLocation) {
		super();
		if (propertiesLocation != null && !propertiesLocation.trim().equals("")) {
			setResourceBundle(propertiesLocation, Locale.getDefault());
		}
	}

	public PropertiesReader(String propertiesLocation, Locale locale) {
		super();
		if (propertiesLocation != null && !propertiesLocation.trim().equals("")) {
			setResourceBundle(propertiesLocation, (locale == null ? Locale.getDefault() : locale));
		}
	}

	public void setResourceBundle(String propFileName, Locale currentLocale) {
		bundle = ResourceBundle.getBundle(propFileName, currentLocale);

	}

	public void setResourceBundle(String propFileName) {
		bundle = ResourceBundle.getBundle(propFileName);
	}

	public String getValueOfKey(String key) {
		String temp = null;
		if (bundle != null) {
			temp = bundle.getString(key);
		}
		return temp;
	}

	public Enumeration<String> getAllKeys() {
		return bundle.getKeys();
	}
}
