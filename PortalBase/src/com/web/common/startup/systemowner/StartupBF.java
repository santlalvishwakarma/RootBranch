package com.web.common.startup.systemowner;

import java.util.ArrayList;
import java.util.HashMap;

import com.web.common.dvo.common.WebsiteDVO;
import com.web.common.dvo.opr.systemowner.RoleManagementOpr;
import com.web.common.dvo.opr.systemowner.WebsiteOpr;
import com.web.common.dvo.systemowner.PageNavigationDVO;
import com.web.common.dvo.systemowner.RoleDVO;
import com.web.common.parents.BusinessFacade;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;
import com.web.sf.modules.core.CoreSF;

public class StartupBF extends BusinessFacade {
	public HashMap<String, PageNavigationDVO> getPageNavigationDetails(HashMap pageNavigationArguments)
			throws FrameworkException, BusinessException {
		return new StartupBC().getPageNavigationDetails(pageNavigationArguments);
	}

	public HashMap<String, RoleDVO> getAllRoles(HashMap<String, RoleDVO> roleMap) throws FrameworkException,
			BusinessException {
		RoleManagementOpr resultRoleManagementOpr = new CoreSF().getAllRoles();
		HashMap<String, RoleDVO> resultRoleMap = new HashMap<String, RoleDVO>();
		ArrayList<RoleDVO> roleList = resultRoleManagementOpr.getRoleList();

		for (RoleDVO roleRecord : roleList) {
			resultRoleMap.put(roleRecord.getCode(), roleRecord);
		}

		return resultRoleMap;
	}

	public HashMap<String, WebsiteDVO> getAllWebSites(HashMap<String, WebsiteDVO> websiteMap)
			throws FrameworkException, BusinessException {
		WebsiteOpr resultWebsiteOpr = new CoreSF().getAllWebsites(new WebsiteOpr());
		HashMap<String, WebsiteDVO> resultWebSiteMap = new HashMap<String, WebsiteDVO>();
		ArrayList<WebsiteDVO> websiteList = resultWebsiteOpr.getWebsiteList();

		for (WebsiteDVO WebsiteRecord : websiteList) {
			resultWebSiteMap.put(WebsiteRecord.getSiteURL(), WebsiteRecord);
		}
		return resultWebSiteMap;
	}
}
