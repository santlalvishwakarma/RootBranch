package com.web.foundation.validation;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;

public class FoundationValidator {
	private transient ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());

	// commented this method by Pankaj Chavan - ideally any code in screen
	// allowed only alphanumeric
	/*
	 * public boolean validateChars(String temp) { boolean b = true; // String
	 * myString=temp; String validChars =
	 * "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_1234567890"; char
	 * c; for (int i = 0; i < temp.length(); i++) { c = temp.charAt(i); if
	 * (validChars.indexOf(c) == -1) { // myLog.debug("Invalid character["+c+"]
	 * at position // "+i); return b = false; } } return b; }
	 */

	public boolean validateCharsWithoutSpecialChars(String temp) {
		boolean b = true;
		String validChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		char c;
		for (int i = 0; i < temp.length(); i++) {
			c = temp.charAt(i);
			if (validChars.indexOf(c) == -1) {
				b = false;
				return b;
			}
		}
		return b;
	}

	public boolean validateChars(String temp) {
		boolean b = true;
		// String myString=temp;
		String validChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890_-";
		char c;
		for (int i = 0; i < temp.length(); i++) {
			c = temp.charAt(i);
			if (validChars.indexOf(c) == -1) {
				// myLog.debug("Invalid character["+c+"] at position
				// "+i);
				b = false;
				return b;
			}
		}
		return b;
	}

	public boolean validateCharsWithSpace(String temp) {
		boolean b = true;
		// String myString=temp;
		String validChars = " abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_";
		char c;
		for (int i = 0; i < temp.length(); i++) {
			c = temp.charAt(i);
			if (validChars.indexOf(c) == -1) {
				// myLog.debug("Invalid character["+c+"] at position
				// "+i);
				b = false;
				return b;
			}
		}
		return b;
	}

	public boolean validateCharsWithSpaceAndDot(String temp) {
		boolean b = true;
		// String myString=temp;
		String validChars = " abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_1234567890.";
		char c;
		for (int i = 0; i < temp.length(); i++) {
			c = temp.charAt(i);
			if (validChars.indexOf(c) == -1) {
				// myLog.debug("Invalid character["+c+"] at position
				// "+i);
				b = false;
				return b;
			}
		}
		return b;
	}

	public boolean validateAddress(String temp) {
		boolean b = true;
		// String myString=temp;
		String validChars = " abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_1234567890.,+&-";
		char c;
		for (int i = 0; i < temp.length(); i++) {
			c = temp.charAt(i);
			if (validChars.indexOf(c) == -1) {
				// myLog.debug("Invalid character["+c+"] at position "+i);
				b = false;
				return b;
			}
		}
		return b;
	}

	public boolean validateCharsWithDot(String temp) {
		boolean b = true;
		// String myString=temp;
		String validChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_1234567890.";
		char c;
		for (int i = 0; i < temp.length(); i++) {
			c = temp.charAt(i);
			if (validChars.indexOf(c) == -1) {
				// myLog.debug("Invalid character["+c+"] at position
				// "+i);
				b = false;
				return b;
			}
		}
		return b;
	}

	public boolean validateDateNull(Date temp) {
		if (temp == null)
			return false;
		else
			return true;
	}

	/*
	 * public boolean validateEmail(String temp) { if ((temp == null) ||
	 * (temp.indexOf("@") == -1) || (temp.indexOf(".") == -1)) return false;
	 * else return true; }
	 */

	public boolean validateLength(String temp) {
		if (temp.length() < 3)
			return false;
		else
			return true;
	}

	public boolean validateMaxLength(String temp) {
		if (temp.length() > 60)
			return false;
		else
			return true;
	}

	public boolean validateNull(String temp) {
		if ((temp == null) || (temp.trim().length() == 0))
			return false;
		else
			return true;
	}

	public boolean validateNumber(String temp) {
		boolean b = true;
		// String myString=temp;
		String validNum = "-1234567890 +";
		char c;
		for (int i = 0; i < temp.length(); i++) {
			c = temp.charAt(i);
			if (validNum.indexOf(c) == -1) {
				// myLog.debug("Invalid character["+c+"] at position
				// "+i);
				b = false;
				return b;
			}
		}
		return b;
	}

	public boolean validateIntegerNumber(String temp) {
		boolean b = true;
		// String myString=temp;
		String validNum = "1234567890";
		char c;
		if (temp != null) {
			for (int i = 0; i < temp.length(); i++) {
				c = temp.charAt(i);
				if (validNum.indexOf(c) == -1) {
					// myLog.debug("Invalid character["+c+"] at position
					// "+i);
					b = false;
					return b;
				}
			}
		}
		return b;
	}

	public boolean validateDecimalNumber(String temp) {
		boolean b = true;
		// String myString=temp;
		String validNum = "-1234567890.+";
		char c;
		for (int i = 0; i < temp.length(); i++) {
			c = temp.charAt(i);
			if (validNum.indexOf(c) == -1) {
				// myLog.debug("Invalid character["+c+"] at position
				// "+i);
				b = false;
				return b;
			}
		}
		return b;
	}

	/**
	 * @param temp1
	 *            -> value holder for first date field
	 * @param temp2
	 *            -> value holder for second date field
	 * @return tempBoolean -> returns true if second date field is greater than
	 *         or equal to first date field
	 */
	public int validateTwoDates(Date temp1, Date temp2) {
		myLog.debug("Inside validateTwoDates.");
		myLog.debug("Date1: " + temp1);
		myLog.debug("Date2: " + temp2);
		int returnCode;
		// Following code clips the dates using a calendar component and
		// compares.
		GregorianCalendar cDateA = new GregorianCalendar();
		GregorianCalendar cDateB = new GregorianCalendar();
		GregorianCalendar tempDate1 = new GregorianCalendar();
		GregorianCalendar tempDate2 = new GregorianCalendar();
		cDateA.setTime(temp1);
		cDateB.setTime(temp2);
		// myLog.debug("Old Date1: " + cDateA.getTime()+ "Year: " +
		// cDateA.get(Calendar.YEAR) + "Month: " + cDateA.get(Calendar.MONTH) +
		// "Day: " + cDateA.get(Calendar.DATE));
		// myLog.debug("Old Date2: " + cDateB.getTime()+ "Year: " +
		// cDateB.get(Calendar.YEAR) + "Month: " + cDateB.get(Calendar.MONTH) +
		// "Day: " + cDateB.get(Calendar.DATE));
		tempDate1.set(cDateA.get(Calendar.YEAR), cDateA.get(Calendar.MONTH), cDateA.get(Calendar.DATE));
		tempDate2.set(cDateB.get(Calendar.YEAR), cDateB.get(Calendar.MONTH), cDateB.get(Calendar.DATE));
		myLog.debug("New Date1: " + tempDate1.getTime());
		myLog.debug("New Date2: " + tempDate2.getTime());
		if (tempDate2.compareTo(tempDate1) > 0) {
			myLog.debug("Date2 is greater than Date1.");
			returnCode = 1;
		} else if (tempDate2.compareTo(tempDate1) == 0) {
			returnCode = 0;
		} else {
			myLog.debug("Date2 is less than Date1.");
			returnCode = -1;
		}
		return returnCode;
	}

	public boolean validateDate(Date dateVar) {
		boolean validateFlag = true;
		GregorianCalendar cDateA = new GregorianCalendar();
		String yearVal = null;

		cDateA.setTime(dateVar);
		yearVal = cDateA.get(Calendar.YEAR) + "";
		myLog.debug("yearVal ::: " + yearVal);

		if (yearVal.trim().length() != 4) {
			validateFlag = false;
		}
		return validateFlag;
	}

	public boolean validateMinMaxLength(String temp, int minLength, int maxLength) {
		if (temp.length() < minLength || temp.length() > maxLength)
			return false;
		else
			return true;
	}

	/**
	 * This method checks whether the string passed is a double or not.It takes
	 * the no of digits allowed after dot. returns false if error else true.
	 * 
	 * @param temp
	 * @return
	 */

	public boolean validateDoubleAndSpecialChr(String temp, int noOfDigAftDec) {
		try {
			Double.parseDouble(temp);
			String validChars = "1234567890.";
			char c;
			for (int i = 0; i < temp.length(); i++) {
				c = temp.charAt(i);
				String str = "" + c;

				if (validChars.contains(str)) {
					// mylog.debug("inside validateDoubleAndSpecialChr CC
					// is:>>"+str);
				} else {
					return false;
				}
			}
			StringTokenizer stzr = new StringTokenizer(temp, ".");
			if (stzr.hasMoreTokens() && temp.contains(".")) {
				stzr.nextToken(); // This gives the value before dot
				String afterDot = stzr.nextToken();
				if (afterDot.length() > noOfDigAftDec) {
					// mylog.error("Only 2 digits are allowed after the decimal
					// point");
					// String sprDimValuelgthErr = "Only 2 digits are allowed
					// after the decimal point";
					// addToErrorList(sprDimValuelgthErr);
					return false;
				}
			}

		} catch (Exception e) {

			return false;
		}
		return true;
	}

	public boolean validateIntegerObjectNull(Integer temp) {
		if (temp == null) {
			return false;
		}
		return true;
	}

	public boolean validateFloatObjectNull(Float temp) {
		if (temp == null) {
			return false;
		}
		return true;
	}

	public boolean validateLongObjectNull(Long temp) {
		if (temp == null) {
			return false;
		}
		return true;
	}

	public boolean validateCharsAndNumbers(String temp) {
		boolean b = true;
		// String myString=temp;
		String validChars = " abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		char c;
		for (int i = 0; i < temp.length(); i++) {
			c = temp.charAt(i);
			if (validChars.indexOf(c) == -1) {
				// myLog.debug("Invalid character["+c+"] at position
				// "+i);
				b = false;
				return b;
			}
		}
		return b;
	}

	public boolean validateEmail(String temp) {
		// a null string is invalid
		if (temp == null)
			return false;

		// a string without a "@" is an invalid email address
		if (temp.indexOf("@") < 0)
			return false;

		// a string without a "." is an invalid email address
		if (temp.indexOf(".") < 0)
			return false;

		if (lastEmailFieldTwoCharsOrMore(temp) == false)
			return false;

		try {
			new InternetAddress(temp);
			return true;
		} catch (AddressException ae) {
			// log exception
			return false;
		}
	}

	private static boolean lastEmailFieldTwoCharsOrMore(String temp) {
		if (temp == null)
			return false;
		StringTokenizer st = new StringTokenizer(temp, ".");
		String lastToken = null;
		while (st.hasMoreTokens()) {
			lastToken = st.nextToken();
		}

		if (lastToken.length() >= 2) {
			return true;
		} else {
			return false;
		}
	}

	// to validate afterDate is not less than beforeDate
	public boolean validateAfterDateNotSmaller(Date beforeDate, Date afterDate) {
		boolean b = true;
		int factor = 0;

		Calendar before_date = new GregorianCalendar();
		before_date.setTime(beforeDate);

		Calendar after_date = new GregorianCalendar();
		after_date.setTime(afterDate);

		if (after_date.get(Calendar.YEAR) - before_date.get(Calendar.YEAR)
				+ ((after_date.get(Calendar.DAY_OF_YEAR) < before_date.get(Calendar.DAY_OF_YEAR)) ? -1 : factor) < 0) {
			return b = false;
		}
		return b;
	}

	public boolean validateForNoOfDecimals(String temp, int decimalPlacesToValidate) {
		boolean validateFlag = true;

		if (temp != null && temp.contains(".")) {
			StringTokenizer st = new StringTokenizer(temp, ".");
			String lastToken = null;
			while (st.hasMoreTokens()) {
				lastToken = st.nextToken();
			}
			if (lastToken.length() > decimalPlacesToValidate)
				validateFlag = false;
		}

		return validateFlag;
	}

}
