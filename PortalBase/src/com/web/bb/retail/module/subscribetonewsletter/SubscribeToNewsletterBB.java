package com.web.bb.retail.module.subscribetonewsletter;

import java.util.ArrayList;
import java.util.Locale;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.web.bf.retail.modules.subscribetonewsletter.SubscribeToNewsletterBF;
import com.web.common.constants.CommonConstant;
import com.web.common.dvo.opr.retail.SubscribeToNewsletterOpr;
import com.web.common.dvo.util.OptionsDVO;
import com.web.common.parents.BackingBean;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;
import com.web.foundation.validation.FoundationValidator;
import com.web.util.PropertiesReader;

public class SubscribeToNewsletterBB extends BackingBean {

	private static final long serialVersionUID = 6138425704905234572L;
	private SubscribeToNewsletterOpr subscribeToNewsletterOpr;
	private String propertiesLocation = "com/web/bb/retail/module/subscribetonewsletter/subscribetonewsletter";

	@Override
	public OptionsDVO getAllOptions() {
		return null;
	}

	@Override
	public void setAllOptions(OptionsDVO allOptions) {

	}

	@Override
	public void editDetails() {

	}

	@Override
	public void executeSave(ActionEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		setErrorList(new ArrayList<String>());
		setSuccessMsg("");

		boolean validateResult = validateSave();

		if (validateResult) {
			PropertiesReader propertiesReader = new PropertiesReader();
			propertiesReader.setResourceBundle(propertiesLocation, Locale.getDefault());
			SubscribeToNewsletterBF subscribeToNewsletterBF = new SubscribeToNewsletterBF();
			String userName = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
					.get(CommonConstant.LOGGED_USER_KEY);

			if (userName == null) {
				subscribeToNewsletterOpr.getUserDetails().setUserLogin(CommonConstant.GUEST_USER);
				// subscribeToNewsletterOpr.getUserDetails().getAuditAttributes().setCreatedBy(CommonConstant.SYSTEM_USER);
			} else {
				subscribeToNewsletterOpr.getUserDetails().setUserLogin(userName);
				subscribeToNewsletterOpr.getUserDetails().getAuditAttributes().setCreatedBy(userName);
			}

			try {
				subscribeToNewsletterOpr = subscribeToNewsletterBF.executeSave(subscribeToNewsletterOpr);
				subscribeToNewsletterOpr.getUserDetails().setPrimaryEmailId("");
				setSuccessMsg(propertiesReader.getValueOfKey("save_success"));
			} catch (FrameworkException e) {
				// handle framework exception
				handleException((Exception) e, propertiesLocation);
			} catch (BusinessException e) {
				// handle BusinessException;
				handleException((Exception) e, propertiesLocation);
			}
		}
	}

	@Override
	public void executeSearch(ActionEvent event) {

	}

	@Override
	public boolean validateSave() {
		// TEMPLATE FOR VALIDATE METHOD ver 1.1
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		FoundationValidator validator = new FoundationValidator();
		PropertiesReader propertiesReader = new PropertiesReader();
		propertiesReader.setResourceBundle(propertiesLocation, Locale.getDefault());
		boolean validationFlag = false;
		String errorMessage;

		// do the error checking here
		if (!validator.validateNull(subscribeToNewsletterOpr.getUserDetails().getPrimaryEmailId())) {
			errorMessage = propertiesReader.getValueOfKey("email_null");
			addToErrorList(errorMessage);
		} else if (!validator.validateEmail(subscribeToNewsletterOpr.getUserDetails().getPrimaryEmailId())) {
			errorMessage = propertiesReader.getValueOfKey("email_invalid");
			addToErrorList(errorMessage);
		}

		if (getErrorList().size() > 0) {
			validationFlag = false;
		} else {
			validationFlag = true;
		}

		return validationFlag;
	}

	@Override
	public boolean validateSearch() {
		return false;
	}

	public SubscribeToNewsletterOpr getSubscribeToNewsletterOpr() {
		if (subscribeToNewsletterOpr == null) {
			subscribeToNewsletterOpr = new SubscribeToNewsletterOpr();
		}
		return subscribeToNewsletterOpr;
	}

	public void setSubscribeToNewsletterOpr(SubscribeToNewsletterOpr subscribeToNewsletterOpr) {
		this.subscribeToNewsletterOpr = subscribeToNewsletterOpr;
	}

	@Override
	public void retrieveData() {
	}

	@Override
	public void executeAddRow(ActionEvent event) {
	}

}
