package com.web.common.dvo.opr.retail;

import com.web.common.dvo.common.OperationalDataValueObject;
import com.web.common.dvo.retail.modules.user.UserDVO;

public class RegistrationPanelOpr extends OperationalDataValueObject {

	private static final long serialVersionUID = -733817207980789332L;
	private UserDVO userDetails;
	private String captchaEnteredValue;// for captcha validation
	private String newPassword;
	private String confirmPassword;

	public UserDVO getUserDetails() {
		if (userDetails == null) {
			userDetails = new UserDVO();
		}
		return userDetails;
	}

	public void setUserDetails(UserDVO userDetails) {
		this.userDetails = userDetails;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getCaptchaEnteredValue() {
		return captchaEnteredValue;
	}

	public void setCaptchaEnteredValue(String captchaEnteredValue) {
		this.captchaEnteredValue = captchaEnteredValue;
	}

}
