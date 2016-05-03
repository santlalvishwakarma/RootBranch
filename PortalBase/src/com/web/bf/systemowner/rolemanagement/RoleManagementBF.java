package com.web.bf.systemowner.rolemanagement;

import com.web.bc.systemowner.modules.rolemanagement.RoleManagementBC;
import com.web.common.dvo.common.OperationalDataValueObject;
import com.web.common.dvo.opr.systemowner.RoleManagementOpr;
import com.web.common.parents.BusinessFacade;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;

public class RoleManagementBF extends BusinessFacade {

	public RoleManagementOpr getAllRoles(
			OperationalDataValueObject queryParameters)
			throws FrameworkException, BusinessException {
		return new RoleManagementBC().getAllRoles(queryParameters);
	}

	public RoleManagementOpr getRolesForUser(OperationalDataValueObject queryParameters) throws FrameworkException,
			BusinessException {
		RoleManagementBC roleManagementBC = new RoleManagementBC();
		RoleManagementOpr roleManagementOpr = new RoleManagementOpr();
		roleManagementOpr = roleManagementBC.getRolesForUser(queryParameters);
		return roleManagementOpr;
	}
}
