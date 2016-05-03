package com.web.util;

import java.text.MessageFormat;
import java.util.Locale;

public class MessageFormatter {

	/**
	 * 
	 * @param strMessageWithPattern
	 * @param arrString
	 * @return
	 */
	public static String getFormattedMessage(String strMessageWithPattern, String[] arrString) {

		MessageFormat formatter = new MessageFormat(strMessageWithPattern);
		// set the locale to US
		formatter.setLocale(Locale.getDefault());
		// format the message and print it out
		return (formatter.format(arrString));
	}

	/**
	 * 
	 * @param strMessageWithPattern
	 * @param arrString
	 * @param locale
	 * @return
	 */
	public static String getFormattedMessage(String strMessageWithPattern, String[] arrString, Locale locale) {

		MessageFormat formatter = new MessageFormat(strMessageWithPattern);

		formatter.setLocale(locale);
		// format the message
		return (formatter.format(arrString));
	}

}
