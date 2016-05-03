package com.web.common.parents;

import java.util.zip.Adler32;

public class CCAvenueHelper {
	String verifyChecksum(String MerchantId, String OrderId, String Amount, String AuthDesc, String WorkingKey,
			String CheckSum) throws Exception {
		String str = MerchantId + "|" + OrderId + "|" + Amount + "|" + AuthDesc + "|" + WorkingKey;

		Adler32 adl = new Adler32();
		adl.update(str.getBytes());
		long adler = adl.getValue();

		String newChecksum = String.valueOf(adl.getValue());
		return (newChecksum.equals(CheckSum)) ? "true" : "false";

	}

	public String getChecksum(String MerchantId, String OrderId, String Amount, String redirectUrl, String WorkingKey)
			throws Exception {
		String str = MerchantId + "|" + OrderId + "|" + Amount + "|" + redirectUrl + "|" + WorkingKey;

		Adler32 adl = new Adler32();
		adl.update(str.getBytes());
		return String.valueOf(adl.getValue());
	}

}
