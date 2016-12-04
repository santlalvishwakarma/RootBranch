package com.web.bf.retail.modules.home;

import com.web.bc.common.OptionsHelperBC;
import com.web.bc.retail.modules.home.HomeBC;
import com.web.common.dvo.opr.retail.HomeOpr;
import com.web.common.dvo.opr.retail.LoginPanelOpr;
import com.web.common.parents.BusinessFacade;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;

public class HomeBF extends BusinessFacade {

	public LoginPanelOpr performLogin(LoginPanelOpr loginOpr) throws FrameworkException, BusinessException {
		return new OptionsHelperBC().validateLoginDetails(loginOpr);
	}

	public LoginPanelOpr getUserBasedRole(LoginPanelOpr loginOpr) throws FrameworkException, BusinessException {
		return new OptionsHelperBC().getUserBasedRole(loginOpr);
	}

	public HomeOpr getCategoryForHomePage(HomeOpr homeOpr) throws FrameworkException, BusinessException {
		return new HomeBC().getCategoryForHomePage(homeOpr);
	}

}
