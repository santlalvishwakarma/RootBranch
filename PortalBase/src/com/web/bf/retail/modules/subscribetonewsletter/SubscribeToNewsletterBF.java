package com.web.bf.retail.modules.subscribetonewsletter;

import com.web.bc.retail.modules.subscribetonewsletter.SubscribeToNewsletterBC;
import com.web.common.dvo.opr.retail.SubscribeToNewsletterOpr;
import com.web.common.parents.BusinessFacade;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;

public class SubscribeToNewsletterBF extends BusinessFacade {

	public SubscribeToNewsletterOpr executeSave(SubscribeToNewsletterOpr subscribeToNewsletterOpr)
			throws FrameworkException, BusinessException {
		return new SubscribeToNewsletterBC().executeSave(subscribeToNewsletterOpr);
	}

}
