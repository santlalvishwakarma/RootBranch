package com.web.common.dvo.opr.retail;

import com.web.common.dvo.common.OperationalDataValueObject;
import com.web.common.dvo.retail.modules.user.UserDVO;

public class SubscribeToNewsletterOpr extends OperationalDataValueObject {

	private static final long serialVersionUID = -6070704099989341489L;
	private UserDVO userDetails;

	public UserDVO getUserDetails() {
		if (userDetails == null) {
			userDetails = new UserDVO();
		}
		return userDetails;
	}

	public void setUserDetails(UserDVO userDetails) {
		this.userDetails = userDetails;
	}
}
