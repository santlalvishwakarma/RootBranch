package com.web.bb.retail.module.loginpanel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import com.ocpsoft.pretty.PrettyContext;
import com.web.bf.retail.modules.home.HomeBF;
import com.web.bf.retail.modules.loginpanel.LoginPanelBF;
import com.web.common.constants.CommonConstant;
import com.web.common.dvo.opr.retail.LoginPanelOpr;
import com.web.common.dvo.util.OptionsDVO;
import com.web.common.parents.BackingBean;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;
import com.web.foundation.validation.FoundationValidator;
import com.web.util.PropertiesReader;

public class LoginPanelBB extends BackingBean {

	private static final long serialVersionUID = 7781795087882864778L;
	private String propertiesLocation = "com/web/bb/retail/module/loginpanel/loginpanel";
	private String commonPropertiesLocation = CommonConstant.MessageLocation.COMMON_MESSAGES;
	private LoginPanelOpr loginPanelOpr;
	private LoginPanelOpr forgotPasswordOpr;
	private String displayName;
	private String retailLogin;
	private String wholesaleLogin;
	private String systemOwnerLogin;
	private String fromShoppingCartPage;
	private boolean navigationFlag = true;
	private boolean forgotPasswordSent;
	private LoginPanelOpr loginOpr;

	public boolean isNavigationFlag() {
		return navigationFlag;
	}

	public void setNavigationFlag(boolean navigationFlag) {
		this.navigationFlag = navigationFlag;
	}

	public String getFromShoppingCartPage() {
		return fromShoppingCartPage;
	}

	public void setFromShoppingCartPage(String fromShoppingCartPage) {
		this.fromShoppingCartPage = fromShoppingCartPage;
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

	public String getWholesaleLogin() {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.put(CommonConstant.LOGIN_TYPE, CommonConstant.WHOLESALE_LOGIN_TYPE);
		return wholesaleLogin;
	}

	public void setWholesaleLogin(String wholesaleLogin) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.put(CommonConstant.LOGIN_TYPE, CommonConstant.WHOLESALE_LOGIN_TYPE);
		this.wholesaleLogin = wholesaleLogin;
	}

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

	public void openLoginPopup(ActionEvent event) {
		getRetailLogin();
		loginPanelOpr = new LoginPanelOpr();
	}

	public void openAdminLoginPopup(ActionEvent event) {
		getSystemOwnerLogin();
		loginPanelOpr = new LoginPanelOpr();
	}

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
		loginPanelOpr = new LoginPanelOpr();
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

	public LoginPanelOpr getLoginPanelOpr() {
		// ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		if (loginPanelOpr == null
				|| FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
						.containsKey(CommonConstant.RE_INITIALIZE_OPR)) {
			loginPanelOpr = new LoginPanelOpr();
		}

		return loginPanelOpr;

	}

	public void setLoginPanelOpr(LoginPanelOpr loginPanelOpr) {
		this.loginPanelOpr = loginPanelOpr;
	}

	public LoginPanelOpr getForgotPasswordOpr() {
		if (forgotPasswordOpr == null
				|| FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
						.containsKey(CommonConstant.RE_INITIALIZE_OPR)) {
			forgotPasswordOpr = new LoginPanelOpr();
		}
		return forgotPasswordOpr;
	}

	public void setForgotPasswordOpr(LoginPanelOpr forgotPasswordOpr) {
		this.forgotPasswordOpr = forgotPasswordOpr;
	}

	private void doLogin() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		setErrorList(new ArrayList<String>());
		setSuccessMsg("");
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		FoundationValidator validator = new FoundationValidator();
		boolean validationFlag = true;
		PropertiesReader propertiesReader = new PropertiesReader();
		propertiesReader.setResourceBundle(propertiesLocation, Locale.getDefault());

		if (!validator.validateNull(loginPanelOpr.getUserDetails().getUserLogin())) {
			String errorMessage = propertiesReader.getValueOfKey("login_null");
			addToErrorList(errorMessage);
			validationFlag = false;
		}
		if (!validator.validateNull(loginPanelOpr.getUserDetails().getLoginPassword())) {
			String errorMessage = propertiesReader.getValueOfKey("login_password_null");
			addToErrorList(errorMessage);
			validationFlag = false;
		}

		if (validationFlag) {
			try {
				myLog.debug("login type:::" + externalContext.getSessionMap().get(CommonConstant.LOGIN_TYPE));
				if (externalContext.getSessionMap().get(CommonConstant.LOGIN_TYPE) != null) {
					loginPanelOpr
							.getApplicationFlags()
							.getApplicationFlagMap()
							.put(CommonConstant.LOGIN_TYPE,
									externalContext.getSessionMap().get(CommonConstant.LOGIN_TYPE));
					loginPanelOpr = new LoginPanelBF().executeLogin(loginPanelOpr);
					externalContext.getSessionMap().put(CommonConstant.LOGGED_USER_KEY,
							loginPanelOpr.getUserDetails().getUserLogin());

					FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
							.put(CommonConstant.LOGGED_USER_DATA, loginPanelOpr.getUserDetails());
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
							.put(CommonConstant.LOGGED_USER_NAME, loginPanelOpr.getUserDetails().getFirstName());
					RequestContext.getCurrentInstance().execute("refreshLoginDetails();");
				} else {
					String errorMessage = propertiesReader.getValueOfKey("system_error_login_type_null");
					addToErrorList(errorMessage);
					navigationFlag = false;
				}
			} catch (FrameworkException e) {
				// handle framework exception
				handleException((Exception) e, propertiesLocation);
				navigationFlag = false;
			} catch (BusinessException e) {
				// handle BusinessException;
				handleException((Exception) e, propertiesLocation);
				navigationFlag = false;
			}

		}
	}

	public void executeLogin(ActionEvent ae) {
		doLogin();
	}

	public String navigateAfterLogin() {
		doLogin();
		String returnNavigationString = null;
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		if (navigationFlag) {
			// navigate to appropriate home page
			if (externalContext.getSessionMap().get(CommonConstant.LOGIN_TYPE) != null) {
				if (CommonConstant.RETAIL_LOGIN_TYPE.equals(externalContext.getSessionMap().get(
						CommonConstant.LOGIN_TYPE))) {
					returnNavigationString = "pretty:homePage";
				} else if (CommonConstant.SYSTEMOWNER_LOGIN_TYPE.equals(externalContext.getSessionMap().get(
						CommonConstant.LOGIN_TYPE))) {
					returnNavigationString = "pretty:adminHome";
				}
			}
		}
		return returnNavigationString;
	}

	public String logout() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

		String returnNavigationString = null;
		if (externalContext.getSessionMap().get(CommonConstant.LOGIN_TYPE) != null) {
			if (CommonConstant.RETAIL_LOGIN_TYPE.equals(externalContext.getSessionMap().get(CommonConstant.LOGIN_TYPE))) {
				returnNavigationString = "pretty:homePage";
			}
			// else if
			// (CommonConstant.WHOLESALE_LOGIN_TYPE.equals(externalContext.getSessionMap().get(
			// CommonConstant.LOGIN_TYPE))) {
			// returnNavigationString = CommonConstant.WHOLESALE_HOME_PAGE;
			// }
			else if (CommonConstant.SYSTEMOWNER_LOGIN_TYPE.equals(externalContext.getSessionMap().get(
					CommonConstant.LOGIN_TYPE))) {
				returnNavigationString = "pretty:adminHome";
			}
		}

		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}

		return returnNavigationString;
	}

	public void performLogin(ActionEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug(" inside performLogin ::: ");

		if (validateLogin()) {
			try {
				loginOpr = new HomeBF().performLogin(loginOpr);
				loginOpr = new HomeBF().getUserBasedRole(loginOpr);

				putObjectInCache(CommonConstant.LOGGED_USER_KEY, loginOpr
						.getUserDetails().getUserLogin());

				putObjectInCache(CommonConstant.LOGGED_USER_DATA,
						loginOpr.getUserDetails());

				String userName = "";
				if (loginOpr.getUserDetails().getFirstName() != null) {
					userName += loginOpr.getUserDetails().getFirstName();
				}
				if (loginOpr.getUserDetails().getLastName() != null) {
					userName += " " + loginOpr.getUserDetails().getLastName();
				}
				putObjectInCache(CommonConstant.LOGGED_USER_NAME, userName);

				for (int i = 0; i < loginOpr.getUserDetails()
						.getUserRolesMappingList().size();) {
					putObjectInCache(CommonConstant.LOGGED_USER_ROLES, loginOpr
							.getUserDetails().getUserRolesMappingList().get(i)
							.getRoleRecord().getCode());
					break;
				}

				ExternalContext externalContext = FacesContext
						.getCurrentInstance().getExternalContext();

				String websiteUrl = ((HttpServletRequest) externalContext
						.getRequest()).getServerName();

				String requestPage = "p"
						+ PrettyContext.getCurrentInstance().getRequestURL();

				websiteUrl = "http://" + websiteUrl + ":8080/" + requestPage;
				myLog.debug(" websiteUrl ::: " + websiteUrl);
				externalContext.redirect(websiteUrl);

			} catch (FrameworkException e) {
				handleException(e, commonPropertiesLocation);
			} catch (BusinessException e) {
				handleException(e, commonPropertiesLocation);
			} catch (IOException e) {
				myLog.error(" error message occured during redirect ::: "
						+ e.getMessage());
				e.printStackTrace();
			}
		}
	}

	private boolean validateLogin() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug(" inside validateLogin ::: ");

		FoundationValidator validator = new FoundationValidator();
		PropertiesReader propertiesReader = new PropertiesReader(
				propertiesLocation);

		boolean validateFlag = true;

		String userLogin = loginOpr.getUserDetails().getUserLogin();
		String password = loginOpr.getUserDetails().getLoginPassword();

		if (!validator.validateNull(userLogin)) {
			addToErrorList(propertiesReader.getValueOfKey("login_null"));
		}

		if (!validator.validateNull(password)) {
			addToErrorList(propertiesReader
					.getValueOfKey("login_password_null"));
		}

		if (getErrorList().size() > 0) {
			validateFlag = false;
		}

		return validateFlag;
	}

	public void executeForgotPassword(ActionEvent event) {
		// ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		setErrorList(new ArrayList<String>());
		setSuccessMsg("");
		PropertiesReader propertiesReader = new PropertiesReader();
		propertiesReader.setResourceBundle(propertiesLocation, Locale.getDefault());
		FoundationValidator validator = new FoundationValidator();
		boolean validationFlag = false;

		if (!validator.validateNull(forgotPasswordOpr.getUserDetails().getUserLogin())) {
			String errorMessage = propertiesReader.getValueOfKey("enter_email");
			addToErrorList(errorMessage);
		}

		if (getErrorList().size() > 0) {
			validationFlag = false;
		} else {
			validationFlag = true;
		}

		String domain = FacesContext.getCurrentInstance().getExternalContext().getRequestServerName();
		// myLog.debug("check for domain:::::::" + domain);
		forgotPasswordOpr.getApplicationFlags().getApplicationFlagMap().put(CommonConstant.WEBSITE_URL, domain);

		if (validationFlag) {
			try {
				forgotPasswordOpr = new LoginPanelBF().executeForgotPassword(forgotPasswordOpr);
				forgotPasswordSent = true;
			} catch (FrameworkException e) {
				// handle framework exception
				handleException((Exception) e, propertiesLocation);
			} catch (BusinessException e) {
				// handle BusinessException;
				handleException((Exception) e, propertiesLocation);
			}
		}
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public boolean isForgotPasswordSent() {
		return forgotPasswordSent;
	}

	public void setForgotPasswordSent(boolean forgotPasswordSent) {
		this.forgotPasswordSent = forgotPasswordSent;
	}

	@Override
	public void retrieveData() {
	}

	@Override
	public void executeAddRow(ActionEvent event) {
	}

	public void executeLogout(ActionEvent event) {
		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();

		removeUserCache((String) externalContext.getSessionMap().get(
				CommonConstant.LOGGED_USER_KEY));
		externalContext.getSessionMap().remove(CommonConstant.LOGGED_USER_KEY);
		externalContext.getSessionMap().remove(CommonConstant.LOGGED_USER_NAME);
		removeObjectFromCache(CommonConstant.LOGGED_USER_DATA);

		try {
			HttpSession httpSession = (HttpSession) externalContext
					.getSession(false);
			httpSession.invalidate();
			httpSession = null;

			PropertiesReader propertiesReader = new PropertiesReader(
					commonPropertiesLocation);
			setSuccessMsg(propertiesReader.getValueOfKey("logout_success"));

			externalContext.redirect("http://localhost:8080/p/admin/login");
			// RequestContext.getCurrentInstance().execute(
			// "refreshLoginDetails();");

		} catch (Exception e) {
			handleException(e, "Logout was not successfull.");
		}
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
}
