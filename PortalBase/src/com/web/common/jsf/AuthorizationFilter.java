package com.web.common.jsf;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.web.common.constants.CommonConstant;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;

public class AuthorizationFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException,
			IOException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		HttpSession session = httpServletRequest.getSession(false);

		myLog.debug(":PATH::" + httpServletRequest.getScheme());
		myLog.debug(":PATH 2::" + httpServletRequest.getContextPath());

		// myLog.debug("URL MAPPED::::" + mappedUserUrl.size());

		myLog.debug("::REQUESTED URL ::" + httpServletRequest.getServletPath());
		if (session != null && !session.isNew()) {
			myLog.debug(":SESSION EXISTS::");
			myLog.debug("USER IN FILTER::::" + session.getAttribute("LOGGED_USER_KEY"));
			String userLogin = (String) session.getAttribute("LOGGED_USER_KEY");

			if (userLogin == null || userLogin.trim().length() == 0) {
				myLog.debug(":REDIRECTING TO LOGIN 22 ::");
				httpServletResponse.sendRedirect(CommonConstant.Urls.CONTEXT_NAME + CommonConstant.Urls.LOGIN);

			} else {

				chain.doFilter(request, response);

			}

		} else {
			myLog.debug(":REDIRECTING TO LOGIN::");
			httpServletResponse.sendRedirect(CommonConstant.Urls.CONTEXT_NAME + CommonConstant.Urls.LOGIN);
		}

	}

	public void destroy() {
	}

	public void init(FilterConfig arg0) throws ServletException {
	}

}
