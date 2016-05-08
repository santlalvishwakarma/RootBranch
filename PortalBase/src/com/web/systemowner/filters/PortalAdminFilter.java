package com.web.systemowner.filters;

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

public class PortalAdminFilter implements Filter {

	public PortalAdminFilter() {
	}

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		HttpSession session = httpServletRequest.getSession(true);

		/**
		 * flag is used to check authorization of user
		 */
		boolean unauthorized = false;

		String currentPage = httpServletRequest.getRequestURI();
		myLog.debug(" currentPage ::: " + currentPage);

		/**
		 * First login credentials of user are checked, if user is logged-in
		 * then based on roles, user is allowed to access admin modules.
		 */
		if (session.getAttribute(CommonConstant.LOGGED_USER_KEY) != null) {
			String userRoles = (String) session.getAttribute(CommonConstant.LOGGED_USER_ROLES);
			if (userRoles != null) {
				if (!(userRoles.contains(CommonConstant.UserRoles.ADMINISTRATOR)
						|| userRoles.contains(CommonConstant.UserRoles.SYSTEM_OWNER)
						|| userRoles.contains(CommonConstant.UserRoles.REPORTS_USER) || userRoles
							.contains(CommonConstant.UserRoles.COMPANY_ADMINISTRATOR))) {
					/**
					 * if none of the roles are mapped for user then user is
					 * redirected to forbidden page
					 */
					unauthorized = true;
				} else if (currentPage.contains("/pages/systemowner/modules/login/login.jsf")) {
					/**
					 * if user hit admin login page url and is logged in and has
					 * specified role then user is redirected to admin home page
					 */
					httpServletResponse.sendRedirect("/p/admin/home");
				}

			} else {
				/**
				 * if no roles are found in session then user is forbidden to
				 * access admin modules
				 */
				unauthorized = true;
			}
		} else if (!currentPage.contains("/pages/systemowner/modules/login/login.jsf")) {
			/**
			 * if user requests different url(inside admin module) then login
			 * page, the user is then redirected to login page.
			 */
			httpServletResponse.sendRedirect("/p/admin/login");
		}

		if (unauthorized) {
			/**
			 * if user is logged-in and not allowed to access admin module page.
			 * Then that user is served with forbidden error.
			 */
			httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
		} else {
			/**
			 * if above condition is satisfied then user request is chained.
			 */
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
