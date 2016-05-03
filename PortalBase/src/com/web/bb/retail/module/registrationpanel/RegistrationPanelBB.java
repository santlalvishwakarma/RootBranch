package com.web.bb.retail.module.registrationpanel;

import java.util.ArrayList;
import java.util.Locale;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.web.bf.retail.modules.registrationpanel.RegistrationPanelBF;
import com.web.common.constants.CommonConstant;
import com.web.common.dvo.opr.retail.RegistrationPanelOpr;
import com.web.common.dvo.util.OptionsDVO;
import com.web.common.parents.BackingBean;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;
import com.web.foundation.validation.FoundationValidator;
import com.web.util.PropertiesReader;

public class RegistrationPanelBB extends BackingBean {

	private static final long serialVersionUID = -7504284781250691375L;
	RegistrationPanelOpr registrationPanelOpr;
	private String propertiesLocation = "com/web/bb/retail/module/registrationpanel/registrationpanel";
	private String termsOfUsePage;

	@Override
	public OptionsDVO getAllOptions() {

		return null;
	}

	@Override
	public void setAllOptions(OptionsDVO allOptions) {

	}

	public void clearPage(ActionEvent event) {
		// TEMPLATE FOR CLEARPAGE METHOD ver 1.1
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
				.put(CommonConstant.RE_INITIALIZE_OPR, CommonConstant.RE_INITIALIZE_OPR);
		setErrorList(new ArrayList<String>());
		setSuccessMsg("");
		registrationPanelOpr = new RegistrationPanelOpr();
	}

	@Override
	public void editDetails() {

	}

	@Override
	public void executeSave(ActionEvent event) {

	}

	@Override
	public void executeSearch(ActionEvent event) {

	}

	@Override
	public boolean validateSave() {

		return false;
	}

	@Override
	public boolean validateSearch() {

		return false;
	}

	public RegistrationPanelOpr getRegistrationPanelOpr() {
		// ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		// myLog.debug("inside getRegistrationPanelOpr");
		if (registrationPanelOpr == null
				|| FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
						.get(CommonConstant.RE_INITIALIZE_OPR) != null) {
			registrationPanelOpr = new RegistrationPanelOpr();
		}

		return registrationPanelOpr;
	}

	public void setRegistrationPanelOpr(RegistrationPanelOpr registrationPanelOpr) {
		this.registrationPanelOpr = registrationPanelOpr;
	}

	public String executeRegister() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		String domain = FacesContext.getCurrentInstance().getExternalContext().getRequestServerName();
		myLog.debug("Domain is:::::::" + domain);
		registrationPanelOpr.getApplicationFlags().getApplicationFlagMap().put(CommonConstant.WEBSITE_URL, domain);
		boolean validationFlag = false;

		setErrorList(new ArrayList<String>());
		setSuccessMsg("");

		validationFlag = validateRegistration();

		if (validationFlag) {
			PropertiesReader propertiesReader = new PropertiesReader();
			propertiesReader.setResourceBundle(propertiesLocation, Locale.getDefault());
			try {
				registrationPanelOpr = new RegistrationPanelBF().executeRegister(registrationPanelOpr);
				myLog.debug("after BF call sucessful::::");
				setSuccessMsg(propertiesReader.getValueOfKey("successfully_registered"));
				externalContext.getSessionMap().put(CommonConstant.LOGGED_USER_KEY,
						registrationPanelOpr.getUserDetails().getUserLogin().trim());
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
						.put(CommonConstant.LOGGED_USER_KEY, registrationPanelOpr.getUserDetails().getUserLogin());
				FacesContext
						.getCurrentInstance()
						.getExternalContext()
						.getSessionMap()
						.put(CommonConstant.LOGGED_USER_ROLES,
								registrationPanelOpr.getApplicationFlags().getApplicationFlagMap()
										.get(CommonConstant.LOGGED_USER_ROLES));
				FacesContext
						.getCurrentInstance()
						.getExternalContext()
						.getSessionMap()
						.put(CommonConstant.LOGGED_USER_DATA,
								registrationPanelOpr.getUserDetails().getFirstName() + " "
										+ registrationPanelOpr.getUserDetails().getLastName());
				registrationPanelOpr.getApplicationFlags().getApplicationFlagMap()
						.remove(CommonConstant.LOGGED_USER_ROLES);
				FacesContext
						.getCurrentInstance()
						.getExternalContext()
						.getSessionMap()
						.put(CommonConstant.RERENDER_COMPONENT,
								"welcomeDisplayName,topLinksForm,systemownerMainDisplayPanel,shoppingCartForm,saveProduct,addToCartLink");
			} catch (FrameworkException e) {
				// handle framework exception
				handleException((Exception) e, propertiesLocation);
			} catch (BusinessException e) {
				// handle BusinessException;
				handleException((Exception) e, propertiesLocation);
			}

		}

		registrationPanelOpr.setNewPassword(null);
		registrationPanelOpr.setConfirmPassword(null);
		return null;

	}

	private boolean validateRegistration() {
		// ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		FoundationValidator validator = new FoundationValidator();
		PropertiesReader propertiesReader = new PropertiesReader();
		propertiesReader.setResourceBundle(propertiesLocation, Locale.getDefault());
		boolean validationFlag = false;
		String errorMessage;
		RegistrationPanelOpr returnRegistrationPanelOpr = new RegistrationPanelOpr();
		RegistrationPanelBF registrationPanelBF = new RegistrationPanelBF();

		try {
			if (!validator.validateNull(registrationPanelOpr.getUserDetails().getFirstName())) {
				errorMessage = propertiesReader.getValueOfKey("first_name_null");
				addToErrorList(errorMessage);
			}
			if (!validator.validateNull(registrationPanelOpr.getUserDetails().getLastName())) {
				errorMessage = propertiesReader.getValueOfKey("last_name_null");
				addToErrorList(errorMessage);
			}
			if (!validator.validateNull(registrationPanelOpr.getUserDetails().getPrimaryPhoneNumber())) {
				errorMessage = propertiesReader.getValueOfKey("mobile_no_null");
				addToErrorList(errorMessage);
			}
			if (validator.validateNull(returnRegistrationPanelOpr.getUserDetails().getUserLogin())) {
				String availabilityMessage = propertiesReader.getValueOfKey("user_not_available");
				addToErrorList(availabilityMessage);
			} else {
				if (!validator.validateNull(registrationPanelOpr.getUserDetails().getPrimaryEmailId())) {
					errorMessage = propertiesReader.getValueOfKey("email_null");
					addToErrorList(errorMessage);
				} else if (!validator.validateEmail(registrationPanelOpr.getUserDetails().getPrimaryEmailId())) {
					errorMessage = propertiesReader.getValueOfKey("email_invalid");
					addToErrorList(errorMessage);
				} else if (registrationPanelOpr.getUserDetails().getPrimaryEmailId() != null
						&& registrationPanelOpr.getUserDetails().getPrimaryEmailId().length() > 50) {
					errorMessage = propertiesReader.getValueOfKey("email_length");
					addToErrorList(errorMessage);
				} else {
					// if email validations are passed then set user login as
					// email id
					registrationPanelOpr.getUserDetails().setUserLogin(
							registrationPanelOpr.getUserDetails().getPrimaryEmailId());
					returnRegistrationPanelOpr = registrationPanelBF.checkUserAvailability(registrationPanelOpr);
					if (validator.validateNull(returnRegistrationPanelOpr.getUserDetails().getUserLogin())) {
						errorMessage = propertiesReader.getValueOfKey("user_not_available");
						addToErrorList(errorMessage);
					}

				}
				// country validation
				// myLog.debug("country validation :::: "
				// +
				// registrationPanelOpr.getUserDetails().getPermanentAddress().getCountry().getId());
				// if
				// (!validator.validateLongObjectNull(registrationPanelOpr.getUserDetails().getPermanentAddress()
				// .getCountry().getId())
				// ||
				// registrationPanelOpr.getUserDetails().getPermanentAddress().getCountry().getId()
				// == 0) {
				// myLog.debug("inside country id :: "
				// +
				// registrationPanelOpr.getUserDetails().getPermanentAddress().getCountry().getId());
				// errorMessage =
				// propertiesReader.getValueOfKey("country_null");
				// addToErrorList(errorMessage);
				// }

				// password validation
				if (validator.validateNull(registrationPanelOpr.getNewPassword())) {
					if (!registrationPanelOpr.getNewPassword().equals(registrationPanelOpr.getConfirmPassword())) {
						errorMessage = propertiesReader.getValueOfKey("confirm_password_mismatch");
						addToErrorList(errorMessage);
					}
				} else {
					errorMessage = propertiesReader.getValueOfKey("password_null");
					addToErrorList(errorMessage);
				}

				// "I agree" validation
				if (!registrationPanelOpr.getUserDetails().isConditionAccepted()) {
					errorMessage = propertiesReader.getValueOfKey("condition_null");
					addToErrorList(errorMessage);
				}
			}
		} catch (FrameworkException e) {
			// handle framework exception
			handleException((Exception) e, propertiesLocation);
		} catch (BusinessException e) {
			// handle BusinessException;
			handleException((Exception) e, propertiesLocation);
		}

		if (getErrorList().size() > 0) {
			validationFlag = false;
		} else {
			validationFlag = true;
		}
		return validationFlag;
	}

	// private void checkUserAvailabilityInternal(ActionEvent event) {
	// // TEMPLATE TO CALL SEARCH BF METHOD FROM BB ver 1.1
	// // ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
	// setErrorList(new ArrayList<String>());
	// setSuccessMsg("");
	// PortalValidator validator = new PortalValidator();
	// String availabilityMessage;
	// RegistrationPanelBF registrationPanelBF = new RegistrationPanelBF();
	// RegistrationPanelOpr returnRegistrationPanelOpr = new
	// RegistrationPanelOpr();
	// PropertiesReader propertiesReader = new PropertiesReader();
	// propertiesReader.setResourceBundle(propertiesLocation,
	// Locale.getDefault());
	// if
	// (!validator.validateNull(registrationPanelOpr.getUserDetails().getUserLogin()))
	// {
	// availabilityMessage = propertiesReader.getValueOfKey("login_null");
	// addToErrorList(availabilityMessage);
	// } else {
	// try {
	// returnRegistrationPanelOpr =
	// registrationPanelBF.checkUserAvailability(registrationPanelOpr);
	// if
	// (validator.validateNull(returnRegistrationPanelOpr.getUserDetails().getUserLogin()))
	// {
	// availabilityMessage =
	// propertiesReader.getValueOfKey("user_not_available");
	// addToErrorList(availabilityMessage);
	// } else {
	// setSuccessMsg(propertiesReader.getValueOfKey("user_available"));
	// }
	// } catch (FrameworkException e) {
	// // handle framework exception
	// handleException((Exception) e, propertiesLocation);
	// } catch (BusinessException e) {
	// // handle BusinessException;
	// handleException((Exception) e, propertiesLocation);
	// }
	// }
	// }

	// public ArrayList<CountryDVO> executeAutoSuggestSearch(Object suggest) {
	// ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
	// CountryOpr returnCountryOpr = new CountryOpr();
	// String pref = (String) suggest;
	// CountryOpr queryCountryOpr = new CountryOpr();
	// if (pref != null && !("".equals(pref.trim()))) {
	//
	// queryCountryOpr.getCountryRecord().setName(pref);
	//
	// try {
	// returnCountryOpr = new
	// RegistrationPanelBF().executeSuggestCountrySearch(queryCountryOpr);
	// } catch (FrameworkException e) {
	// // handle framework exception
	// // handleException((Exception) e, propertiesLocation);
	// } catch (BusinessException e) {
	// // handle BusinessException;
	// // handleException((Exception) e, propertiesLocation);
	// }
	// }
	//
	// if ((pref == null) && pref == "") {
	// myLog.debug("pref is null");
	// queryCountryOpr.getCountryRecord().setName(pref);
	// queryCountryOpr.getCountryRecord().setId(null);
	// myLog.debug("country name :: " +
	// queryCountryOpr.getCountryRecord().getName());
	// myLog.debug("country id :: " +
	// queryCountryOpr.getCountryRecord().getId());
	// }
	//
	// return returnCountryOpr.getSearchResultList();
	// }

	/**
	 * @return the countryForAutoSuggest
	 */
	// public String getCountryForAutoSuggest() {
	// return countryForAutoSuggest;
	// }

	/**
	 * @param countryForAutoSuggest
	 *            the countryForAutoSuggest to set
	 */
	// public void setCountryForAutoSuggest(String countryForAutoSuggest) {
	// if (countryForAutoSuggest == null ||
	// countryForAutoSuggest.trim().length() == 0) {
	// registrationPanelOpr.getUserDetails().getPermanentAddress().setCountry(null);
	// }
	// this.countryForAutoSuggest = countryForAutoSuggest;
	// }

	/**
	 * @return the selectedCountry
	 */
	// public CountryDVO getSelectedCountry() {
	// if (selectedCountry == null) {
	// selectedCountry = new CountryDVO();
	// }
	// return selectedCountry;
	// }

	/**
	 * @param selectedCountry
	 *            the selectedCountry to set
	 */
	// public void setSelectedCountry(CountryDVO selectedCountry) {
	// registrationPanelOpr.getUserDetails().getPermanentAddress().setCountry(selectedCountry);
	// this.selectedCountry = selectedCountry;
	// }

	// public boolean validateCountry() {
	// ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
	// myLog.debug("inside validateCountry ::");
	// PortalValidator validator = new PortalValidator();
	// PropertiesReader propertiesReader = new PropertiesReader();
	// propertiesReader.setResourceBundle(propertiesLocation,
	// Locale.getDefault());
	// boolean validationFlag = false;
	// // String errorMessage;
	// if
	// (!validator.validateLongObjectNull(registrationPanelOpr.getUserDetails().getPermanentAddress().getCountry()
	// .getId())
	// ||
	// registrationPanelOpr.getUserDetails().getPermanentAddress().getCountry().getId()
	// == 0) {
	// myLog.debug("country name ::");
	// myLog.debug("country id ::"
	// +
	// registrationPanelOpr.getUserDetails().getPermanentAddress().getCountry().getId());
	// addToErrorList(new
	// PropertiesReader(propertiesLocation).getValueOfKey("country_null"));
	// }
	//
	//
	// if (getErrorList().size() > 0) {
	//
	// validationFlag = false;
	// } else {
	// validationFlag = true;
	// }
	//
	// return validationFlag;
	// }

	public String getTermsOfUsePage() {
		return "ui/retail/modules/static/termsandconditions.jsf";
	}

	public void setTermsOfUsePage(String termsOfUsePage) {
		this.termsOfUsePage = termsOfUsePage;
	}

	@Override
	public void retrieveData() {
	}

	@Override
	public void executeAddRow(ActionEvent event) {
	}
}
