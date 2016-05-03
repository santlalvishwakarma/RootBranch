package com.web.util;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.context.FacesContext;

import com.web.common.constants.CommonConstant;

public class CommonUtil {
	/**
	 * this method takes java.util.Date and returns java.sql.Date
	 * 
	 * @param date
	 * @return sql format Date
	 */
	public static java.sql.Date sqlFormat(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String sqlDateString = sdf.format(date);
		java.sql.Date sqlDate = java.sql.Date.valueOf(sqlDateString);
		return sqlDate;
	}

	public static String formatDateTimeToString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sqlDateString = sdf.format(date);
		return sqlDateString;
	}

	public static byte[] toByteArray(Object obj) throws IOException {
		ObjectOutputStream os = null;
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();

		byte[] sendBuf = null;
		try {
			os = new ObjectOutputStream(new BufferedOutputStream(byteStream));
			os.flush();
			os.writeObject(obj);
			os.flush();
			sendBuf = byteStream.toByteArray();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sendBuf;
	}

	public static Object toObject(byte[] bytes) {
		Object obj = null;
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bis);
			obj = ois.readObject();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return obj;
	}

	public static String toProperCase(String name) {
		return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
	}

	public static String removeSpecialCharactersFromString(String inputString) {
		// String str = "~`!@#$%^&*()={[}]\\:;\'\"<,>/";
		String str = "$&+,/:;=?@ <>#%{}|\\^~[]`\'\"";
		StringBuffer str1 = new StringBuffer();
		for (int i = 0; i < inputString.length(); i++) {
			char c = inputString.charAt(i);
			if (str.indexOf(c) == -1) {
				str1.append(c);
			}
		}
		return str1.toString();
	}

	public static Float roundOffFloat(Float roundOfFloat) {
		// for rounding off values to 3 digits
		float mulFactor = (float) Math.pow(10, 3);
		roundOfFloat = Math.round(roundOfFloat * mulFactor) / mulFactor;
		return roundOfFloat;
	}

	public static Double roundOffDouble(Double roundOfFloat) {
		// for rounding off values to 3 digits
		Double mulFactor = (Double) Math.pow(10, 3);
		roundOfFloat = Math.round(roundOfFloat * mulFactor) / mulFactor;
		return roundOfFloat;
	}

	public static String getServerUrl() {
		String serverUrl = null;
		if (FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().containsKey("SERVER_URL")
				&& FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get("SERVER_URL") != null) {
			serverUrl = (String) FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get(
					"SERVER_URL");
		}

		if (serverUrl == null) {
			ConfigurationReader configurationReader = new ConfigurationReader();
			serverUrl = configurationReader.getWebPropertyFile("server_url");
			FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().put("SERVER_URL", serverUrl);
		}

		if (serverUrl == null) {
			serverUrl = CommonConstant.ServerName.SERVER_NAME;
			FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().put("SERVER_URL", serverUrl);
		}
		return serverUrl;
	}

}
