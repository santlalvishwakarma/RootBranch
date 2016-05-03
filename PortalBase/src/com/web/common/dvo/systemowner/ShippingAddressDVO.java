package com.web.common.dvo.systemowner;

import com.web.common.dvo.common.BaseDVO;

public class ShippingAddressDVO extends BaseDVO {

	private static final long serialVersionUID = -5352214462976979593L;
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
