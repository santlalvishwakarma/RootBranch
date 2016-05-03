package com.web.common.dvo.retail.modules;

import com.web.common.dvo.common.BaseDVO;
import com.web.common.dvo.retail.modules.user.UserDVO;
import com.web.common.dvo.systemowner.AddressDetailDVO;
import com.web.common.dvo.systemowner.ProductDVO;

public class SendEnquiryDVO extends BaseDVO {

	private static final long serialVersionUID = -8122748383712819631L;

	private String comments;
	private AddressDetailDVO address;
	private UserDVO user;
	private ProductDVO productRecord;

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public UserDVO getUser() {
		if (user == null) {
			user = new UserDVO();
		}
		return user;
	}

	public void setUser(UserDVO user) {
		this.user = user;
	}

	public void setAddress(AddressDetailDVO address) {
		this.address = address;
	}

	public AddressDetailDVO getAddress() {
		if (address == null) {
			address = new AddressDetailDVO();
		}
		return address;
	}

	public ProductDVO getProductRecord() {
		if (productRecord == null) {
			productRecord = new ProductDVO();
		}
		return productRecord;
	}

	public void setProductRecord(ProductDVO productRecord) {
		this.productRecord = productRecord;
	}

}
