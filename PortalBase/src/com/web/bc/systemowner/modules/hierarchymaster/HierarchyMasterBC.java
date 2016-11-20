package com.web.bc.systemowner.modules.hierarchymaster;

import java.util.ArrayList;
import java.util.HashMap;

import com.web.common.dvo.systemowner.HierarchyDVO;
import com.web.common.parents.BackingClass;
import com.web.foundation.dao.DAOResult;
import com.web.foundation.dao.IDAOConstant;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;

public class HierarchyMasterBC extends BackingClass {

	public ArrayList<Object> getSuggestedHierarchies(HierarchyDVO hierarchyDVO) throws FrameworkException,
			BusinessException {

		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In HierarchyMasterBC :: getSuggestedHierarchies starts ");

		String code = hierarchyDVO.getCode();
		String name = hierarchyDVO.getName();
		String description = hierarchyDVO.getDescription();
		myLog.debug(" parameter 1 code:: " + code);
		myLog.debug(" parameter 2 name:: " + name);
		myLog.debug(" parameter 3 description:: " + description);

		StringBuffer dynamicWhere = new StringBuffer();

		int parameterCount = 0;
		// setup the dynamic where clause to include all entered params
		if (code != null && code.trim().length() > 0) {
			dynamicWhere.append(" hierarchy_code like '" + code.trim() + "%'");
			parameterCount += 1;
		}

		if (name != null && name.trim().length() > 0) {
			name = name.trim().concat("%");
			if (parameterCount > 0) {
				dynamicWhere.append(" AND hierarchy_name like '" + name + "'");
			} else {
				dynamicWhere.append(" hierarchy_name like '" + name + "'");
			}
			parameterCount += 1;
		}

		if (description != null && description.trim().length() > 0) {
			description = description.trim().concat("%");
			if (parameterCount > 0) {
				dynamicWhere.append(" AND hierarchy_description like '" + description + "'");
			} else {
				dynamicWhere.append(" hierarchy_description like '" + description + "'");
			}
			parameterCount += 1;
		}

		// to get all data
		if (parameterCount == 0) {
			dynamicWhere.append(" 1 = 1 ");
		}
		dynamicWhere.append(" ORDER BY hierarchy_name;");
		myLog.debug("dynamicWhere ::::: " + dynamicWhere);

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, HierarchyMasterSqlTemplate.GET_SUGGESTED_HIERARCHY);

		Object strSqlParams[][] = new Object[0][3];

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, dynamicWhere.toString());
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" hierarchy getSuggestedHierarchies :: Resultset got :: " + responseMap);

		ArrayList<Object> hierarchyList = new ArrayList<Object>();
		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {

				HashMap<String, Object> resultSetMap = responseMap.get(i);
				handleAndThrowException(resultSetMap);

				HierarchyDVO hierarchyRecord = new HierarchyDVO();
				if (resultSetMap.get("hierarchy_id") != null)
					hierarchyRecord.setId(Long.valueOf(resultSetMap.get("hierarchy_id").toString()));

				hierarchyRecord.setCode((String) resultSetMap.get("hierarchy_code"));
				hierarchyRecord.setName((String) resultSetMap.get("hierarchy_name"));
				hierarchyRecord.setDescription((String) resultSetMap.get("hierarchy_description"));

				setAuditAttributes(hierarchyRecord, resultSetMap);
				hierarchyList.add(hierarchyRecord);

			}
		}
		myLog.debug("productHierarchyList size ::::: " + hierarchyList.size());

		return hierarchyList;
	}

}
