package com.web.common.parents;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;

import com.web.common.dvo.common.BaseDVO;
import com.web.common.dvo.common.ImplDataValueObject;
import com.web.foundation.dao.DAOResult;
import com.web.foundation.dao.DAOUtil;
import com.web.foundation.dao.DBDelegator;
import com.web.foundation.dao.IDAOConstant;
import com.web.foundation.dao.SqlRequest;
import com.web.foundation.dao.SqlResponse;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.DAOException;
import com.web.foundation.exception.FrameworkException;
import com.web.util.CommonUtil;

public abstract class ParentBackingClass {

	public SqlResponse performDBOperation(SqlRequest sqlRequest) throws FrameworkException {
		SqlResponse sqlResponse = null;
		try {
			sqlResponse = DBDelegator.getDBdelegator().invoke(sqlRequest);
		} catch (DAOException e) {
			if ((e.getCause() != null) && (e.getCause() instanceof SQLException)) {
				throw new FrameworkException(e.getCause().getMessage(), e);
			} else {
				throw new FrameworkException(e.getMessage(), e);
			}
			// throw new FrameworkException("dao_sql_excep_msg");
		}
		return sqlResponse;
	}

	public DAOResult performDBOperation(HashMap<String, String> strSqlConfigParams, Object[][] strPrepareParams,
			String strDynamicWhere) throws FrameworkException {
		SqlResponse sqlResponse = null;

		Integer iDataType = null;
		Object iDataValue = null;
		for (int i = 0; i <= strPrepareParams.length - 1; i++) {
			// System.out.println("i is " + i);
			// System.out.println("strPrepareParams[i][1] " +
			// strPrepareParams[i][1]);
			iDataType = Integer.valueOf(strPrepareParams[i][1].toString());
			if (iDataType.equals(IDAOConstant.DATE_DATATYPE)) {
				iDataValue = strPrepareParams[i][2];
				if (iDataValue != null) {
					java.util.Date utilDate = (Date) iDataValue;
					strPrepareParams[i][2] = CommonUtil.sqlFormat(utilDate);
				}
			}
		}

		DAOUtil dAOUtil = new DAOUtil();
		SqlRequest sqlRequest = dAOUtil.getSqlRequest(strSqlConfigParams, strPrepareParams, strDynamicWhere);
		sqlResponse = performDBOperation(sqlRequest);

		DAOResult daoResult = new DAOResult();
		if (sqlResponse != null) {
			Integer updateResult = sqlResponse.getUpdateResult();
			HashMap<Integer, HashMap<String, Object>> invocationResult = sqlResponse.getHmDataValues();
			HashMap<Object, HashMap<Integer, HashMap<String, Object>>> multipleResultSet = sqlResponse
					.getMultipleResultSet();
			daoResult.setUpdateResult(updateResult);
			if (invocationResult != null) {
				daoResult.setInvocationResult(invocationResult);
				daoResult.setMultipleResultSet(multipleResultSet);
			} else {
				daoResult.setInvocationResult(new HashMap<Integer, HashMap<String, Object>>());
				daoResult.setMultipleResultSet(new HashMap<Object, HashMap<Integer, HashMap<String, Object>>>());
			}

			if (sqlResponse.getSqlException() != null) {
				daoResult.setException(sqlResponse.getSqlException());
			}

		}

		return daoResult;
	}

	// set audit attributes
	protected ImplDataValueObject setAuditAttributes(ImplDataValueObject dbRecord, HashMap<String, Object> dbHashMap) {
		dbRecord.getAuditAttributes().setCreatedDate((Date) dbHashMap.get("created_date"));
		dbRecord.getAuditAttributes().setLastModifiedDate((Date) dbHashMap.get("modified_date"));
		dbRecord.getAuditAttributes().setCreatedBy((String) dbHashMap.get("created_by"));
		dbRecord.getAuditAttributes().setModifiedBy((String) dbHashMap.get("modified_by"));
		return dbRecord;
	}

	// set effective from and to
	protected BaseDVO setEffectiveFromAndTo(BaseDVO dbRecord, HashMap<String, Object> dbHashMap) {
		dbRecord.setEffectiveFrom((Date) dbHashMap.get("effective_date_from"));
		dbRecord.setEffectiveTo((Date) dbHashMap.get("effective_date_to"));
		return dbRecord;
	}

	protected void handleAndThrowException(HashMap<String, Object> resultSetMap) throws BusinessException {
		if (resultSetMap.get("p_error_code") != null) {
			String errorMessage = (String) resultSetMap.get("p_error_message");
			if (errorMessage != null && errorMessage.trim().length() > 0)
				throw new BusinessException(errorMessage);
			else
				throw new BusinessException((String) resultSetMap.get("p_error_code"));
		}
	}
}
