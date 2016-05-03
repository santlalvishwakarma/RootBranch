package com.web.bb.systemowner.loginpanel;

import java.io.IOException;
import java.util.ArrayList;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import com.ocpsoft.pretty.PrettyContext;
import com.web.bf.retail.modules.home.HomeBF;
import com.web.common.constants.CommonConstant;
import com.web.common.dvo.opr.retail.LoginPanelOpr;
import com.web.common.dvo.retail.modules.user.UserDVO;
import com.web.common.dvo.util.OptionsDVO;
import com.web.common.parents.BackingBean;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;
import com.web.foundation.validation.FoundationValidator;
import com.web.util.PropertiesReader;

public class LoginBean extends BackingBean {

	private static final long serialVersionUID = 6801537928330246587L;
	private String propertiesLocation = CommonConstant.MessageLocation.COMMON_MESSAGES;
	private LoginPanelOpr loginOpr;
	private String systemOwnerLogin;
	private String retailLogin;
	private boolean navigationFlag;

	public String getSystemOwnerLogin() {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.put(CommonConstant.LOGIN_TYPE, CommonConstant.SYSTEMOWNER_LOGIN_TYPE);
		return systemOwnerLogin;
	}

	public void setSystemOwnerLogin(String systemOwnerLogin) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.put(CommonConstant.LOGIN_TYPE, CommonConstant.SYSTEMOWNER_LOGIN_TYPE);
		this.systemOwnerLogin = systemOwnerLogin;
	}

	public String getRetailLogin() {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.put(CommonConstant.LOGIN_TYPE, CommonConstant.RETAIL_LOGIN_TYPE);
		return retailLogin;
	}

	public void setRetailLogin(String retailLogin) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.put(CommonConstant.LOGIN_TYPE, CommonConstant.RETAIL_LOGIN_TYPE);
		this.retailLogin = retailLogin;
	}

	public boolean isNavigationFlag() {
		return navigationFlag;
	}

	public void setNavigationFlag(boolean navigationFlag) {
		this.navigationFlag = navigationFlag;
	}

	public LoginPanelOpr getLoginOpr() {
		if (loginOpr == null) {
			loginOpr = new LoginPanelOpr();
		}
		return loginOpr;
	}

	public void setLoginOpr(LoginPanelOpr loginOpr) {
		this.loginOpr = loginOpr;
	}

	@Override
	public void executeSearch(ActionEvent event) {
	}

	@Override
	public boolean validateSearch() {
		return false;
	}

	public void openLoginPopup(ActionEvent event) {
		getRetailLogin();
		loginOpr = new LoginPanelOpr();
	}

	public void openAdminLoginPopup(ActionEvent event) {
		getSystemOwnerLogin();
		loginOpr = new LoginPanelOpr();
	}

	@Override
	public void executeSave(ActionEvent event) {

		setSuccessMsg("");

		if (validateSave()) {

			try {

				loginOpr = new HomeBF().validateLoginDetails(loginOpr);
				UserDVO userRecord = loginOpr.getUserRecord();

				String userLogin = userRecord.getUserLogin();
				// putting user login into session
				ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
				externalContext.getSessionMap().put(CommonConstant.LOGGED_USER_KEY, userLogin);
				putObjectInCache(CommonConstant.LOGGED_USER_DATA, userRecord);

				String userName = "";
				if (userRecord.getFirstName() != null)
					userName += userRecord.getFirstName();
				if (userRecord.getLastName() != null)
					userName += " " + userRecord.getLastName();
				putObjectInCache(CommonConstant.LOGGED_USER_NAME, userName);

				RequestContext.getCurrentInstance().execute("loginDialog.hide(); refreshLoginDetails();");
				PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
				setSuccessMsg(propertiesReader.getValueOfKey("user_logged_in_successfully_message"));

			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);
			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
		}
	}

	@Override
	public boolean validateSave() {

		FoundationValidator validator = new FoundationValidator();
		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
		boolean validateFlag = true;
		setErrorList(new ArrayList<String>());

		String userLogin = loginOpr.getUserRecord().getUserLogin();
		String password = loginOpr.getUserRecord().getLoginPassword();

		if (!validator.validateNull(userLogin))
			addToErrorList(propertiesReader.getValueOfKey("user_login_null"));

		if (!validator.validateNull(password))
			addToErrorList(propertiesReader.getValueOfKey("password_null"));

		if (getErrorList().size() > 0) {
			validateFlag = false;
			navigationFlag = false;
		} else {
			validateFlag = true;
		}

		return validateFlag;
	}

	public void validateAdminLoginDetails(ActionEvent event) {
		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
		setSuccessMsg("");
		setErrorList(new ArrayList<String>());

		if (validateSave()) {
			try {
				loginOpr = new HomeBF().validateLoginDetails(loginOpr);

				if (loginOpr.getUserRecord().getIsAdmin()) {
					navigationFlag = true;
					String userLogin = loginOpr.getUserRecord().getUserLogin();
					// putting user login into session
					ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
					externalContext.getSessionMap().put(CommonConstant.LOGGED_USER_KEY, userLogin);
					putObjectInCache(CommonConstant.LOGGED_USER_DATA, loginOpr.getUserRecord());

					RequestContext.getCurrentInstance().execute("loginDialog.hide(); refreshLoginDetails();");

					setSuccessMsg(propertiesReader.getValueOfKey("user_logged_in_successfully_message"));
				} else {
					navigationFlag = false;
					addToErrorList(propertiesReader.getValueOfKey("not_authorized_admin"));
				}

			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);
			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			}
		}
	}

	public void executeLogout(ActionEvent event) {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		removeUserCache((String) externalContext.getSessionMap().get(CommonConstant.LOGGED_USER_KEY));
		externalContext.getSessionMap().remove(CommonConstant.LOGGED_USER_KEY);
		removeObjectFromCache(CommonConstant.LOGGED_USER_DATA);

		try {

			HttpSession httpSession = (HttpSession) externalContext.getSession(false);
			httpSession.invalidate();
			httpSession = null;

			PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
			setSuccessMsg(propertiesReader.getValueOfKey("logout_success"));

		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	public String navigateAfterLogin() throws FrameworkException, BusinessException {
		validateAdminLoginDetails(null);
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		String websiteURL = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest())
				.getServerName();
		String requestUrl = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest())
				.getContextPath() + PrettyContext.getCurrentInstance().getRequestURL();
		myLog.debug("websiteURL " + websiteURL + "AND" + requestUrl);
		websiteURL = "http://" + websiteURL + requestUrl;
		// initialised to null for postback in case of error
		String returnNavigationString = null;
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		if (navigationFlag) {
			// navigate to appropriate home page
			if (externalContext.getSessionMap().get(CommonConstant.LOGIN_TYPE) != null) {
				if (CommonConstant.RETAIL_LOGIN_TYPE.equals(externalContext.getSessionMap().get(
						CommonConstant.LOGIN_TYPE))) {
					try {
						externalContext.redirect(websiteURL);
					} catch (IOException e) {
						e.printStackTrace();
					}

				} else if (CommonConstant.SYSTEMOWNER_LOGIN_TYPE.equals(externalContext.getSessionMap().get(
						CommonConstant.LOGIN_TYPE))) {
					returnNavigationString = "pretty:adminHome";
				}
			}
		}
		return returnNavigationString;
	}

	@Override
	public void editDetails() {
	}

	@Override
	public void retrieveData() {
	}

	@Override
	public void executeAddRow(ActionEvent event) {
	}

	@Override
	public OptionsDVO getAllOptions() {
		return null;
	}

	@Override
	public void setAllOptions(OptionsDVO allOptions) {
	}

}
