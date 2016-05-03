package com.web.bb.systemowner.modules.websitemaster;

import java.util.ArrayList;
import java.util.Locale;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.web.bf.systemowner.modules.websitemaster.WebsiteMasterBF;
import com.web.common.constants.CommonConstant;
import com.web.common.dvo.opr.systemowner.WebsiteOpr;
import com.web.common.dvo.util.OptionsDVO;
import com.web.common.parents.BackingBean;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;
import com.web.foundation.validation.FoundationValidator;
import com.web.util.PropertiesReader;

public class SearchWebsiteBB extends BackingBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7827596744247247945L;
	private WebsiteOpr searchWebsiteOpr;
	private WebsiteOpr addWebsiteOpr;
	private String propertiesLocation = getClass().getPackage().getName().replaceAll("\\.", "/") + "/websitemaster";

	@Override
	public OptionsDVO getAllOptions() {
		return null;
	}

	@Override
	public void setAllOptions(OptionsDVO allOptions) {

	}

	public void clearPage(ActionEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.put(CommonConstant.RE_INITIALIZE_OPR, CommonConstant.RE_INITIALIZE_OPR);
		setErrorList(new ArrayList<String>());
		setSuccessMsg("");
		myLog.debug("inside SearchWebsiteBB :: clearAddPage ");
		addWebsiteOpr = new WebsiteOpr();
	}

	@Override
	public void editDetails() {

	}

	public void executeQuickEditSave(ActionEvent event) {
		// TEMPLATE TO CALL SAVE BF METHOD FROM BB ver 1.0
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		setErrorList(new ArrayList<String>());
		setSuccessMsg("");

		myLog.debug("inside SearchWebsiteBB :: executeQuickEditSave");
		boolean validateResult = validateQuickEditSave();

		if (validateResult) {
			PropertiesReader propertiesReader = new PropertiesReader();
			propertiesReader.setResourceBundle(propertiesLocation, Locale.getDefault());
			WebsiteMasterBF websiteMasterBF = new WebsiteMasterBF();
			String modifiedBy = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
					.get(CommonConstant.LOGGED_USER_KEY);

			try {
				searchWebsiteOpr.getQuickEditWebsiteRecord().getAuditAttributes().setModifiedBy(modifiedBy);
				myLog.debug("b4 bf call");
				searchWebsiteOpr = websiteMasterBF.executeQuickEditSave(searchWebsiteOpr);
				myLog.debug("after bf call");
				setSuccessMsg(propertiesReader.getValueOfKey("save_success"));

				if (searchWebsiteOpr.getWebsiteRecord().getOperationalAttributes().getRecordPopulated() == true) {
					searchWebsiteOpr = new WebsiteOpr();
				}

			}

			catch (FrameworkException e) {
				// handle framework exception
				handleException((Exception) e, propertiesLocation);
			} catch (BusinessException e) {
				// handle BusinessException;
				handleException((Exception) e, propertiesLocation);
			}
		}
		myLog.debug("outside SearchWebsiteBB :: executeQuickEditSave");

	}

	@Override
	public void executeSave(ActionEvent event) {
		// TEMPLATE TO CALL SAVE BF METHOD FROM BB ver 1.0
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("inside SearchWebsiteBB ::: executeAddNewWebsite");
		myLog.debug("id is :::::" + addWebsiteOpr.getWebsiteRecord().getId());
		setErrorList(new ArrayList<String>());
		setSuccessMsg("");
		if (addWebsiteOpr.getWebsiteRecord().getId() == null || addWebsiteOpr.getWebsiteRecord().getId() == 0) {
			boolean validateResult = validateSave();

			if (validateResult) {
				PropertiesReader propertiesReader = new PropertiesReader();
				propertiesReader.setResourceBundle(propertiesLocation, Locale.getDefault());
				WebsiteMasterBF websiteMasterBF = new WebsiteMasterBF();
				String modifiedBy = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
						.get(CommonConstant.LOGGED_USER_KEY);

				try {
					addWebsiteOpr.getWebsiteRecord().getAuditAttributes().setModifiedBy(modifiedBy);
					myLog.debug("b4 bf call");
					addWebsiteOpr = websiteMasterBF.executeSave(addWebsiteOpr);
					myLog.debug("after bf call");
					setSuccessMsg(propertiesReader.getValueOfKey("add_success"));
					// fire getter to insert the record in the website list on
					// screenf
					// getSearchWebsiteOpr();

					if (searchWebsiteOpr.getWebsiteRecord().getOperationalAttributes().getRecordPopulated() == true) {
						searchWebsiteOpr = new WebsiteOpr();
					}

				}

				catch (FrameworkException e) {
					// handle framework exception
					handleException((Exception) e, propertiesLocation);
				} catch (BusinessException e) {
					// handle BusinessException;
					handleException((Exception) e, propertiesLocation);
				}
			}
			myLog.debug("outside SearchWebsiteBB ::: executeAddNewWebsite");

		} else if (addWebsiteOpr.getWebsiteRecord().getId() != null || addWebsiteOpr.getWebsiteRecord().getId() != 0) {

			boolean validateResult = validateQuickEditSave();

			if (validateResult) {
				PropertiesReader propertiesReader = new PropertiesReader();
				propertiesReader.setResourceBundle(propertiesLocation, Locale.getDefault());
				WebsiteMasterBF websiteMasterBF = new WebsiteMasterBF();
				String modifiedBy = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
						.get(CommonConstant.LOGGED_USER_KEY);

				try {
					addWebsiteOpr.getQuickEditWebsiteRecord().getAuditAttributes().setModifiedBy(modifiedBy);
					myLog.debug("b4 bf call");
					addWebsiteOpr = websiteMasterBF.executeQuickEditSave(addWebsiteOpr);
					myLog.debug("after bf call");
					setSuccessMsg(propertiesReader.getValueOfKey("save_success"));

					if (searchWebsiteOpr.getWebsiteRecord().getOperationalAttributes().getRecordPopulated() == true) {
						searchWebsiteOpr = new WebsiteOpr();
					}

				}

				catch (FrameworkException e) {
					// handle framework exception
					handleException((Exception) e, propertiesLocation);
				} catch (BusinessException e) {
					// handle BusinessException;
					handleException((Exception) e, propertiesLocation);
				}
			}
			myLog.debug("outside SearchWebsiteBB :: executeQuickEditSave");
		}

	}

	@Override
	public void executeSearch(ActionEvent event) {

	}

	public String executeSearch() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("after clicking in menu:::::::::");
		WebsiteMasterBF websiteMasterBF = new WebsiteMasterBF();
		try {
			searchWebsiteOpr = websiteMasterBF.getAllWebsites(searchWebsiteOpr);
			FacesContext.getCurrentInstance().getExternalContext().getFlash()
					.put(CommonConstant.WEBSITE_MASTER_OPR, searchWebsiteOpr);
			myLog.debug("set data in opr in flash^^^^^^^^");
		} catch (FrameworkException e) {
			// handleException((Exception) e, propertiesLocation);
		} catch (BusinessException e) {
			// handleException((Exception) e, propertiesLocation);
		}
		myLog.debug("before returning the string:::::::");
		return "pretty:websiteMaster";
	}

	public boolean validateQuickEditSave() {
		// TEMPLATE FOR VALIDATE METHOD ver 1.0
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		FoundationValidator validator = new FoundationValidator();
		PropertiesReader propertiesReader = new PropertiesReader();
		propertiesReader.setResourceBundle(propertiesLocation, Locale.getDefault());
		boolean validationFlag = false;
		setErrorList(new ArrayList<String>());
		String errorMessage;

		myLog.debug("Inside validateQuickEditSave");

		// do the error checking here
		if (!validator.validateNull(addWebsiteOpr.getWebsiteRecord().getName())) {
			errorMessage = propertiesReader.getValueOfKey("website_name_null");
			myLog.debug("Website Name cannot be empty");
			addToErrorList(errorMessage);
		}

		if (!validator.validateNull(addWebsiteOpr.getWebsiteRecord().getSiteURL())) {
			errorMessage = propertiesReader.getValueOfKey("website_url_null");
			myLog.debug("Website Url cannot be empty");
			addToErrorList(errorMessage);
		}

		if (!validator.validateCharsWithDot(addWebsiteOpr.getWebsiteRecord().getSiteURL())) {
			errorMessage = propertiesReader.getValueOfKey("website_url_with_space");
			myLog.debug("Website Url cannot contain space");
			addToErrorList(errorMessage);
		}

		if (getErrorList().size() > 0) {
			validationFlag = false;
		} else {
			validationFlag = true;
		}
		myLog.debug("outside validateQuickEditSave");
		return validationFlag;
	}

	@Override
	public boolean validateSave() {
		// TEMPLATE FOR VALIDATE METHOD ver 1.0
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		FoundationValidator validator = new FoundationValidator();
		PropertiesReader propertiesReader = new PropertiesReader();
		propertiesReader.setResourceBundle(propertiesLocation, Locale.getDefault());
		boolean validationFlag = false;
		setErrorList(new ArrayList<String>());
		String errorMessage;
		WebsiteMasterBF websiteMasterBF = new WebsiteMasterBF();
		WebsiteOpr returnWebsiteOpr = new WebsiteOpr();

		myLog.debug("inside validateAddNewWebsite");

		if (addWebsiteOpr.getWebsiteRecord().getId() == null) {
			try {

				returnWebsiteOpr = websiteMasterBF.checkWebsiteUrlAvailability(addWebsiteOpr);
				if (validator.validateNull(returnWebsiteOpr.getWebsiteRecord().getSiteURL())) {
					myLog.debug("==website_url_not_available==");
					errorMessage = propertiesReader.getValueOfKey("website_url_not_available");
					addToErrorList(errorMessage);
				}
				//
				// returnWebsiteOpr = null;
				//
				// returnWebsiteOpr =
				// websiteMasterBF.checkWebsiteNameAvailability(addWebsiteOpr);
				//
				// if
				// (validator.validateNull(returnWebsiteOpr.getWebsiteRecord().getName()))
				// {
				// myLog.debug("==website_name_not_available==");
				// errorMessage =
				// propertiesReader.getValueOfKey("website_name_not_available");
				// addToErrorList(errorMessage);
				// } else {
				// myLog.debug("inside website name else part");
				// }
			} catch (FrameworkException e) {
				// handle framework exception
				handleException((Exception) e, propertiesLocation);
			} catch (BusinessException e) {
				// handle BusinessException;
				handleException((Exception) e, propertiesLocation);
			}
		}

		// do the error checking here

		if (!validator.validateNull(addWebsiteOpr.getWebsiteRecord().getName())) {
			errorMessage = propertiesReader.getValueOfKey("website_name_null");
			myLog.debug("website name cannot be empty ");
			addToErrorList(errorMessage);
		}

		if (!validator.validateNull(addWebsiteOpr.getWebsiteRecord().getSiteURL())) {
			errorMessage = propertiesReader.getValueOfKey("website_url_null");
			myLog.debug("website url cannot be empty ");
			addToErrorList(errorMessage);
		}

		if (!validator.validateCharsWithDot(addWebsiteOpr.getWebsiteRecord().getSiteURL())) {
			errorMessage = propertiesReader.getValueOfKey("website_url_with_space");
			myLog.debug("website url cannot contain space ");
			addToErrorList(errorMessage);
		}

		if (getErrorList().size() > 0) {
			validationFlag = false;
		} else {
			validationFlag = true;
		}
		myLog.debug("outside validateAddNewWebsite");
		return validationFlag;
	}

	@Override
	public boolean validateSearch() {
		return false;
	}

	public WebsiteOpr getSearchWebsiteOpr() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());

		if (searchWebsiteOpr == null) {
			searchWebsiteOpr = new WebsiteOpr();
		}
		if (FacesContext.getCurrentInstance().getExternalContext().getFlash().get(CommonConstant.WEBSITE_MASTER_OPR) != null) {
			myLog.debug("After checking opr==========");
			WebsiteOpr websiteOpr = (WebsiteOpr) FacesContext.getCurrentInstance().getExternalContext().getFlash()
					.get(CommonConstant.WEBSITE_MASTER_OPR);
			searchWebsiteOpr = websiteOpr;
			FacesContext.getCurrentInstance().getExternalContext().getFlash().remove(CommonConstant.WEBSITE_MASTER_OPR);
		}

		/*
		 * WebsiteMasterBF websiteMasterBF = new WebsiteMasterBF(); try { if
		 * (searchWebsiteOpr
		 * .getWebsiteRecord().getOperationalAttributes().getRecordPopulated()
		 * == false) { searchWebsiteOpr =
		 * websiteMasterBF.getAllWebsites(searchWebsiteOpr); }
		 * searchWebsiteOpr.getWebsiteRecord
		 * ().getOperationalAttributes().setRecordPopulated(true);
		 * 
		 * }
		 * 
		 * catch (FrameworkException e) { // handle framework exception
		 * handleException((Exception) e, propertiesLocation); } catch
		 * (BusinessException e) { // handle BusinessException;
		 * handleException((Exception) e, propertiesLocation); }
		 */

		return searchWebsiteOpr;
	}

	public void setSearchWebsiteOpr(WebsiteOpr searchWebsiteOpr) {
		this.searchWebsiteOpr = searchWebsiteOpr;
	}

	public WebsiteOpr getAddWebsiteOpr() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("inside getAddWebsiteOpr");
		if (addWebsiteOpr == null
				|| FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
						.containsKey(CommonConstant.RE_INITIALIZE_OPR)) {
			addWebsiteOpr = new WebsiteOpr();
		}

		/*
		 * if
		 * (FacesContext.getCurrentInstance().getExternalContext().getSessionMap
		 * () .containsKey(CommonConstant.RE_INITIALIZE_MODAL_OPR)) {
		 * 
		 * if
		 * (FacesContext.getCurrentInstance().getExternalContext().getSessionMap
		 * () .get(CommonConstant.RE_INITIALIZE_MODAL_OPR) != null) {
		 * myLog.debug("RE_INITIALIZE_MODAL_OPR is not null");
		 * myLog.debug("if RE_INITIALIZE_MODAL_OPR key is present den make new opr"
		 * ); addWebsiteOpr = new WebsiteOpr();
		 * myLog.debug("RE_INITIALIZE_MODAL_OPR made new opr .........");
		 * myLog.debug("b4 removing RE_INITIALIZE_MODAL_OPR .........");
		 * myLog.debug
		 * ("after removing RE_INITIALIZE_MODAL_OPR .................."); }
		 * 
		 * FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
		 * .remove(CommonConstant.RE_INITIALIZE_MODAL_OPR); }
		 */
		myLog.debug("outside getAddWebsiteOpr");
		return addWebsiteOpr;
	}

	public void setAddWebsiteOpr(WebsiteOpr addWebsiteOpr) {
		this.addWebsiteOpr = addWebsiteOpr;
	}

	public void addNewWebsite(ActionEvent ae) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("Add New Website Modal Panel is clicked :::");

		if (true) {
			myLog.debug("RE_INITIALIZE_MODAL_OPR if cond");
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
					.put(CommonConstant.RE_INITIALIZE_MODAL_OPR, "addNewWebsiteTable");
			myLog.debug("after RE_INITIALIZE_MODAL_OPR if cond");
		}
		myLog.debug("getting out of Add New Website Modal Panel is clicked :::");
	}

	public void checkWebsiteUrlAvailability(ActionEvent ae) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("inside SearchWebsiteBB :: checkWebsiteUrlAvailability ");
		// TEMPLATE TO CALL SEARCH BF METHOD FROM BB ver 1.0
		String errorMessage;
		setErrorList(new ArrayList<String>());
		setSuccessMsg("");
		String websiteUrl = addWebsiteOpr.getWebsiteRecord().getSiteURL();
		myLog.debug("checkWebsiteUrlAvailability :: websiteUrl = " + websiteUrl);

		WebsiteMasterBF websiteMasterBF = new WebsiteMasterBF();

		PropertiesReader propertiesReader = new PropertiesReader();
		propertiesReader.setResourceBundle(propertiesLocation, Locale.getDefault());
		FoundationValidator validator = new FoundationValidator();

		if (!validator.validateNull(addWebsiteOpr.getWebsiteRecord().getSiteURL())) {
			errorMessage = propertiesReader.getValueOfKey("website_url_null");
			addToErrorList(errorMessage);
		} else {
			if (!validator.validateCharsWithDot(addWebsiteOpr.getWebsiteRecord().getSiteURL())) {
				errorMessage = propertiesReader.getValueOfKey("website_url_with_space");
				addToErrorList(errorMessage);
			} else {
				try {
					addWebsiteOpr = websiteMasterBF.checkWebsiteUrlAvailability(addWebsiteOpr);
					if (validator.validateNull(addWebsiteOpr.getWebsiteRecord().getSiteURL())) {
						addWebsiteOpr.getWebsiteRecord().setSiteURL(websiteUrl);
						errorMessage = propertiesReader.getValueOfKey("website_url_not_available");
						addToErrorList(errorMessage);
					} else {
						setSuccessMsg(propertiesReader.getValueOfKey("website_url_available"));
					}

				}

				catch (FrameworkException e) {
					// handle framework exception
					handleException((Exception) e, propertiesLocation);
				} catch (BusinessException e) {
					// handle BusinessException;
					handleException((Exception) e, propertiesLocation);
				}
			}
		}

		myLog.debug("outside SearchWebsiteBB :: checkWebsiteUrlAvailability ");
	}

	public void checkWebsiteNameAvailability(ActionEvent ae) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("inside SearchWebsiteBB :: checkWebsiteNameAvailability ");
		// TEMPLATE TO CALL SEARCH BF METHOD FROM BB ver 1.0

		String errorMessage;
		setErrorList(new ArrayList<String>());
		setSuccessMsg("");
		String websiteName = addWebsiteOpr.getWebsiteRecord().getName();
		myLog.debug("checkWebsiteUrlAvailability :: websiteName = " + websiteName);

		WebsiteMasterBF websiteMasterBF = new WebsiteMasterBF();

		PropertiesReader propertiesReader = new PropertiesReader();
		propertiesReader.setResourceBundle(propertiesLocation, Locale.getDefault());
		FoundationValidator validator = new FoundationValidator();

		if (!validator.validateNull(addWebsiteOpr.getWebsiteRecord().getName())) {
			errorMessage = propertiesReader.getValueOfKey("website_name_null");
			addToErrorList(errorMessage);
		}

		else {
			try {
				addWebsiteOpr = websiteMasterBF.checkWebsiteNameAvailability(addWebsiteOpr);
				if (validator.validateNull(addWebsiteOpr.getWebsiteRecord().getName())) {
					addWebsiteOpr.getWebsiteRecord().setName(websiteName);
					errorMessage = propertiesReader.getValueOfKey("website_name_not_available");
					addToErrorList(errorMessage);
				} else {
					setSuccessMsg(propertiesReader.getValueOfKey("website_name_available"));
				}
			}

			catch (FrameworkException e) {
				// handle framework exception
				handleException((Exception) e, propertiesLocation);
			} catch (BusinessException e) {
				// handle BusinessException;
				handleException((Exception) e, propertiesLocation);
			}
		}

		myLog.debug("outside SearchWebsiteBB :: checkWebsiteNameAvailability ");
	}

	public void clearAddPage(ActionEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.put(CommonConstant.RE_INITIALIZE_OPR, CommonConstant.RE_INITIALIZE_OPR);
		setErrorList(new ArrayList<String>());
		setSuccessMsg("");
		myLog.debug("inside SearchWebsiteBB :: clearAddPage ");
		addWebsiteOpr = new WebsiteOpr();
	}

	@Override
	public void retrieveData() {
	}

	@Override
	public void executeAddRow(ActionEvent event) {
	}

}
