package com.web.bc.systemowner.modules.rolemanagement;

import java.util.HashMap;

import com.web.common.dvo.common.OperationalDataValueObject;
import com.web.common.dvo.opr.systemowner.RoleManagementOpr;
import com.web.common.dvo.retail.modules.user.UserRoleMappingDVO;
import com.web.common.dvo.systemowner.RoleDVO;
import com.web.common.parents.BackingClass;
import com.web.foundation.dao.DAOResult;
import com.web.foundation.dao.IDAOConstant;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;

public class RoleManagementBC extends BackingClass {

	public RoleManagementOpr getAllRoles(
			OperationalDataValueObject queryParameters)
			throws FrameworkException, BusinessException {
		// BACKING CLASS METHOD TEMPLATE ver 1.0
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug(" inside getAllRoles ::: ");

		RoleManagementOpr returnRoleManagementOpr = new RoleManagementOpr();

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();

		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE,
				IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT,
				RoleManagementSqlTemplate.GET_ALL_ROLES);

		Object strSqlParams[][] = new Object[0][0];

		// in the following call replace null with dynamic where clause if
		// required
		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams,
				null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult
				.getInvocationResult();
		myLog.debug(" resultset got ::: " + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {
				HashMap<String, Object> resultRow = responseMap.get(i);
				RoleDVO roleRecord = new RoleDVO();

				if (resultRow.get("core_roles_id") != null) {
					roleRecord.setId(Long.valueOf(resultRow
							.get("core_roles_id").toString()));
				}

				roleRecord.setCode((String) (resultRow.get("role_code")));

				roleRecord.setName((String) (resultRow.get("role_name")));

				roleRecord.setDescription((String) (resultRow
						.get("role_description")));

				returnRoleManagementOpr.getRoleList().add(roleRecord);
			}
		} else {
			myLog.error(" RoleManagementBC ::: getAllRoles failed ::: Return Record empty ::: ");
		}

		return returnRoleManagementOpr;
	}

	public RoleManagementOpr getRolesForUser(OperationalDataValueObject queryParameters) throws FrameworkException,
			BusinessException {
		// BACKING CLASS METHOD TEMPLATE ver 1.0
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		DAOResult daoResult = new DAOResult();
		RoleManagementOpr queryRoleManagementOpr = (RoleManagementOpr) queryParameters;
		RoleManagementOpr returnRoleManagementOpr = new RoleManagementOpr();

		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, RoleManagementSqlTemplate.GET_ROLES_FOR_USER);

		Object strSqlParams[][] = new Object[1][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[0][2] = queryRoleManagementOpr.getUserRecord().getId();

		// in the following call replace null with dynamic where clause if
		// required
		daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap responseMap = daoResult.getInvocationResult();
		myLog.debug(":: Resultset got ::" + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {
				HashMap resultRow = (HashMap) responseMap.get(i);
				UserRoleMappingDVO userRoleMappingDVO = new UserRoleMappingDVO();
				if (resultRow.get("core_roles_id") != null) {
					userRoleMappingDVO.getRoleRecord().setId(Long.valueOf(resultRow.get("core_roles_id").toString()));
				}
				if (resultRow.get("role_code") != null) {
					userRoleMappingDVO.getRoleRecord().setCode((String) (resultRow.get("role_code")));
				}
				if (resultRow.get("role_name") != null) {
					userRoleMappingDVO.getRoleRecord().setName((String) (resultRow.get("role_name")));
				}
				if (resultRow.get("role_description") != null) {
					userRoleMappingDVO.getRoleRecord().setDescription((String) (resultRow.get("role_description")));
				}

				returnRoleManagementOpr.getUserRecord().getUserRolesMappingList().add(userRoleMappingDVO);
			}
		}
		// else {
		// myLog.error("RoleManagementBC :: getAllRoles failed :: Return Record empty ::::: ");
		// throw new BusinessException("no_data_from_db_excep_msg");
		// }

		return returnRoleManagementOpr;
	}

}
