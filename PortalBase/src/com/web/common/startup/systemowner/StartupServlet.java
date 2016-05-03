package com.web.common.startup.systemowner;

import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import com.web.common.constants.CommonConstant;
import com.web.common.dvo.common.WebsiteDVO;
import com.web.common.dvo.systemowner.PageNavigationDVO;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;

public class StartupServlet extends HttpServlet {

	private static final long serialVersionUID = 5640813349861451799L;

	private ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());

	private CacheManager singletonManager;

	@Override
	public void init(ServletConfig config) throws ServletException {
		try {

			singletonManager = CacheManager.create();

			StartupBF startupBF = new StartupBF();

			String globalCacheName = CommonConstant.GLOBAL_MENU_NAVIGATION_CACHE;
			if (globalCacheName != null) {
				Cache globalCache = singletonManager.getCache(globalCacheName);
				if (globalCache != null) {

					HashMap<String, PageNavigationDVO> pageNavigationDetailsMap = new HashMap<String, PageNavigationDVO>();

					pageNavigationDetailsMap = startupBF
							.getPageNavigationDetails(pageNavigationDetailsMap);

					String pageNavigationElement = CommonConstant.GLOBAL_MENU_NAVIGATION_ELEMENT;
					if (pageNavigationElement != null) {
						Element navigationElement = new Element(
								pageNavigationElement, pageNavigationDetailsMap);
						navigationElement.setTimeToIdle(CommonConstant.FOREVER);
						navigationElement.setTimeToLive(CommonConstant.FOREVER);
						globalCache.put(navigationElement);
					} else {
						throw new BusinessException(
								"Error getting page navigation rules");
					}
					// why to put in servlet context
					// if already put in cache manager
					config.getServletContext().setAttribute(
							"PAGE_NAVIGATION_MAP", pageNavigationDetailsMap);
				} else {
					throw new BusinessException("Could not locate cache "
							+ globalCacheName);
				}
			} else {
				throw new BusinessException(
						"Global Cache name picked up from property file is null");
			}

			// String globalRoleCacheName = CommonConstant.GLOBAL_ROLE_CACHE;
			// Cache globalRoleCache = singletonManager
			// .getCache(globalRoleCacheName);
			// if (globalRoleCache != null) {
			//
			// HashMap<String, RoleDVO> roleMap = new HashMap<String,
			// RoleDVO>();
			// roleMap = startupBF.getAllRoles(roleMap);
			// String roleElement = CommonConstant.GLOBAL_ROLE_ELEMENT;
			// if (roleElement != null) {
			// Element navigationElement = new Element(roleElement,
			// roleMap);
			// navigationElement.setTimeToIdle(CommonConstant.FOREVER);
			// navigationElement.setTimeToLive(CommonConstant.FOREVER);
			// globalRoleCache.put(navigationElement);
			// } else {
			// throw new BusinessException("Error getting role details");
			// }
			// // why to put in servlet context
			// // if already put in cache manager
			// config.getServletContext().setAttribute(
			// CommonConstant.LOGGED_USER_ROLES, roleMap);
			//
			// } else {
			// throw new BusinessException("Could not locate cache "
			// + globalRoleCache);
			// }

			String globalWebSiteCacheName = CommonConstant.GLOBAL_WEBSITE_CACHE;
			Cache globalWebSiteCache = singletonManager
					.getCache(globalWebSiteCacheName);
			if (globalWebSiteCache != null) {

				HashMap<String, WebsiteDVO> websiteMap = new HashMap<String, WebsiteDVO>();
				websiteMap = startupBF.getAllWebSites(websiteMap);
				String websiteElement = CommonConstant.GLOBAL_WEBSITE_ELEMENT;
				if (websiteElement != null) {
					Element navigationElement = new Element(websiteElement,
							websiteMap);
					navigationElement.setTimeToIdle(CommonConstant.FOREVER);
					navigationElement.setTimeToLive(CommonConstant.FOREVER);
					globalWebSiteCache.put(navigationElement);
				} else {
					throw new BusinessException("Error getting website details");
				}
			} else {
				throw new BusinessException("Could not locate cache "
						+ globalWebSiteCache);
			}

		} catch (CacheException e) {
			myLog.error("Cache exception occurred ", e);
		} catch (IllegalStateException e) {
			myLog.error("Illegal state exception occurred ", e);
		} catch (ClassCastException e) {
			myLog.error("Class Cast exception occurred ", e);
		} catch (IllegalArgumentException e) {
			myLog.error("Ilegal argument exception occurred ", e);
		} catch (Throwable t) {
			if (t instanceof FrameworkException) {
				myLog.error("Framework exception occurred ", t);
			} else if (t instanceof BusinessException) {
				myLog.error("Business exception occurred ", t);
			} else {
				myLog.error("Unhandled exception occurred ", t);
			}
		}
	}

}
