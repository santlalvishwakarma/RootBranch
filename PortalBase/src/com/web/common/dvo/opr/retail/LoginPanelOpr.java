package com.web.common.dvo.opr.retail;

import com.web.common.dvo.common.LoginDVO;
import com.web.common.dvo.common.OperationalDataValueObject;
import com.web.common.dvo.retail.modules.user.UserDVO;

public class LoginPanelOpr extends OperationalDataValueObject {

	private static final long serialVersionUID = -8971890561057292182L;
	private UserDVO userDetails;
	private LoginDVO retailHome;
	private LoginDVO wholesaleHome;
	private LoginDVO systemOwnerHome;

	public UserDVO getUserDetails() {
		if (userDetails == null) {
			userDetails = new UserDVO();
		}
		return userDetails;
	}

	public void setUserDetails(UserDVO userDetails) {
		this.userDetails = userDetails;
	}

	public LoginDVO getRetailHome() {
		return retailHome;
	}

	public void setRetailHome(LoginDVO retailHome) {
		this.retailHome = retailHome;
	}

	public LoginDVO getWholesaleHome() {
		return wholesaleHome;
	}

	public void setWholesaleHome(LoginDVO wholesaleHome) {
		this.wholesaleHome = wholesaleHome;
	}

	public LoginDVO getSystemOwnerHome() {
		return systemOwnerHome;
	}

	public void setSystemOwnerHome(LoginDVO systemOwnerHome) {
		this.systemOwnerHome = systemOwnerHome;
	}
}
