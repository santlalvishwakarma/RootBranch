package com.web.sf.modules.core;

import com.web.bf.systemowner.modules.websitemaster.WebsiteMasterBF;
import com.web.bf.systemowner.rolemanagement.RoleManagementBF;
import com.web.common.dvo.opr.systemowner.RoleManagementOpr;
import com.web.common.dvo.opr.systemowner.WebsiteOpr;
import com.web.common.parents.BusinessFacade;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;

public class CoreSF extends BusinessFacade {

	public RoleManagementOpr getAllRoles() throws FrameworkException, BusinessException {
		RoleManagementOpr resultRoleManagementOpr = new RoleManagementBF().getAllRoles(new RoleManagementOpr());
		return resultRoleManagementOpr;
	}

	public WebsiteOpr getAllWebsites(WebsiteOpr websiteOpr) throws FrameworkException, BusinessException {
		WebsiteOpr returnWebsiteOpr = new WebsiteMasterBF().getAllWebsites(websiteOpr);
		return returnWebsiteOpr;
	}

}
