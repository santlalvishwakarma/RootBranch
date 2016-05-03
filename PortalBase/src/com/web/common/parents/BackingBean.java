package com.web.common.parents;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.web.common.constants.CommonConstant;
import com.web.common.dvo.retail.modules.user.UserDVO;
import com.web.foundation.exception.BusinessException;
import com.web.util.CommonUtil;

public abstract class BackingBean extends ParentBackingBean implements Serializable, IBackingBean {

	private static final long serialVersionUID = -2916890749918364191L;
	private String propertiesLocation = CommonConstant.MessageLocation.COMMON_MESSAGES;

	protected void getImageURL() {
		String serverlUrl = CommonUtil.getServerUrl();
		FacesContext
				.getCurrentInstance()
				.getExternalContext()
				.getApplicationMap()
				.put("IMAGE_URL",
						(CommonConstant.HttpSchemes.HTTP + serverlUrl + CommonConstant.Urls.WEBDAV_CONTEXT_NAME));
	}

	protected String validateLoginDetailsAndRedirect(String pageURL, String errorMessage) {
		String userLogin;
		try {
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			userLogin = getUserLoginFromSession(externalContext);
			if (userLogin == null || userLogin.trim().length() == 0) {
				if (pageURL == null) {
					// pageURL = "pretty:homePage";
					String websiteURL = ((HttpServletRequest) externalContext.getRequest()).getServerName();
					String requestUrl = ((HttpServletRequest) externalContext.getRequest()).getContextPath()
							+ CommonConstant.Urls.HOME;
					System.out.println("websiteURL " + websiteURL + "AND" + requestUrl);
					pageURL = "http://" + websiteURL + requestUrl;
					System.out.println("pageURL " + pageURL);
				}

				if (errorMessage != null)
					addToErrorList(errorMessage);

			} else {
				pageURL = null;
			}
			if (pageURL != null)
				externalContext.redirect(pageURL);

		} catch (BusinessException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pageURL;
	}

	protected void putUserDetailsInCache(UserDVO userRecord) {
		String userLogin = userRecord.getUserLogin();
		// putting user login into session
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		externalContext.getSessionMap().put(CommonConstant.LOGGED_USER_KEY, userLogin);
		putObjectInCache(CommonConstant.LOGGED_USER_DATA, userRecord);

		String userName = "";
		if (userRecord.getFirstName() != null)
			userName += userRecord.getFirstName();
		// if (userRecord.getLastName() != null)
		// userName += " " + userRecord.getLastName();
		putObjectInCache(CommonConstant.LOGGED_USER_NAME, userName);
	}
}
