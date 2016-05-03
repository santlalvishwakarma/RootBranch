package com.web.common.dvo.systemowner;

import com.web.common.dvo.common.BaseDVO;

public class BillingAddressDVO extends BaseDVO {

	private static final long serialVersionUID = -6798473440376479445L;
	private AddressDetailDVO addressDetailsRecord;

	public AddressDetailDVO getAddressDetailsRecord() {
		if (addressDetailsRecord == null) {
			addressDetailsRecord = new AddressDetailDVO();
		}
		return addressDetailsRecord;
	}

	public void setAddressDetailsRecord(AddressDetailDVO addressDetailsRecord) {
		this.addressDetailsRecord = addressDetailsRecord;
	}

}
