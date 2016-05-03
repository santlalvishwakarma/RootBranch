package com.web.bf.systemowner.modules.websitemaster;

import com.web.bc.systemowner.modules.websitemaster.WebsiteMasterBC;
import com.web.common.dvo.common.OperationalDataValueObject;
import com.web.common.dvo.opr.systemowner.WebsiteOpr;
import com.web.common.parents.BusinessFacade;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;

public class WebsiteMasterBF extends BusinessFacade {

	public WebsiteOpr executeQuickEditSave(OperationalDataValueObject saveParameters) throws FrameworkException,
			BusinessException {
		// TEMPLATE TO CALL BC METHOD FROM BF ver 1.0
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("inside WebsiteMasterBF :: executeQuickEditSave");
		WebsiteOpr returnWebsiteOpr = new WebsiteOpr();
		WebsiteMasterBC websiteMasterBC = new WebsiteMasterBC();

		try {
			// add your BC call here
			returnWebsiteOpr = websiteMasterBC.executeQuickEditSave(saveParameters);
		} catch (FrameworkException e) {
			throw e;
		} catch (BusinessException e) {
			throw e;
		}
		myLog.debug("outside WebsiteMasterBF :: executeQuickEditSave");
		return returnWebsiteOpr;
	}

	public WebsiteOpr executeSave(OperationalDataValueObject saveParameters) throws FrameworkException,
			BusinessException {
		// TEMPLATE TO CALL BC METHOD FROM BF ver 1.0
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("inside WebsiteMasterBF ::executeAddNewWebsite");
		WebsiteOpr returnWebsiteOpr = new WebsiteOpr();
		WebsiteMasterBC websiteMasterBC = new WebsiteMasterBC();

		try {
			// add your BC call here
			returnWebsiteOpr = websiteMasterBC.executeSave(saveParameters);
		} catch (FrameworkException e) {
			throw e;
		} catch (BusinessException e) {
			throw e;
		}
		myLog.debug("outside WebsiteMasterBF ::executeAddNewWebsite");
		return returnWebsiteOpr;
	}

	public WebsiteOpr getAllWebsites(WebsiteOpr addWebsiteOpr)
			throws FrameworkException, BusinessException {
		return new WebsiteMasterBC().getAllWebsites(addWebsiteOpr);
	}

	public WebsiteOpr checkWebsiteUrlAvailability(OperationalDataValueObject saveParameters) throws FrameworkException,
			BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("inside WebsiteMasterBF :: checkWebsiteUrlAvailability()");
		WebsiteMasterBC websiteMasterBC = new WebsiteMasterBC();
		WebsiteOpr returnWebsiteUrlOpr = new WebsiteOpr();
		returnWebsiteUrlOpr = websiteMasterBC.checkWebsiteUrlAvailability(saveParameters);
		myLog.debug("outside WebsiteMasterBF :: checkWebsiteUrlAvailability()");
		return returnWebsiteUrlOpr;
	}

	public WebsiteOpr checkWebsiteNameAvailability(OperationalDataValueObject saveParameters)
			throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("inside WebsiteMasterBF :: checkWebsiteNameAvailability()");
		WebsiteMasterBC websiteMasterBC = new WebsiteMasterBC();
		WebsiteOpr returnWebsiteNameOpr = new WebsiteOpr();
		returnWebsiteNameOpr = websiteMasterBC.checkWebsiteNameAvailability(saveParameters);
		myLog.debug("outside WebsiteMasterBF :: checkWebsiteNameAvailability()");
		return returnWebsiteNameOpr;
	}

	public WebsiteOpr getWebsitesForCatalog(OperationalDataValueObject addWebsiteOpr) throws FrameworkException,
			BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("inside WebsiteMasterBF :: getWebsitesForCatalog()");
		WebsiteMasterBC websiteMasterBC = new WebsiteMasterBC();
		WebsiteOpr returnWebsiteList = new WebsiteOpr();
		returnWebsiteList = websiteMasterBC.getWebsitesForCatalog(addWebsiteOpr);
		myLog.debug("outside WebsiteMasterBF :: getWebsitesForCatalog()");
		return returnWebsiteList;
	}

	public WebsiteOpr executeWebsiteNameAutoSuggestSearch(OperationalDataValueObject searchParameters)
			throws FrameworkException, BusinessException {
		return new WebsiteMasterBC().executeWebsiteNameAutoSuggestSearch(searchParameters);
	}

}
