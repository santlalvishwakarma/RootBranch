package com.web.bc.systemowner.modules.master.sizemaster;

import java.util.HashMap;

import com.web.common.constants.CommonConstant;
import com.web.common.dvo.opr.systemowner.SizeOpr;
import com.web.common.dvo.systemowner.SizeDVO;
import com.web.common.parents.BackingClass;
import com.web.foundation.dao.DAOResult;
import com.web.foundation.dao.IDAOConstant;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;

public class SizeMasterBC extends BackingClass {

	public SizeOpr executeSearch(SizeOpr sizeOpr) throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In SizeMasterBC executeSearch ");

		SizeOpr sizeOprRet = new SizeOpr();

		String sizeCode = sizeOpr.getSizeRecord().getCode();
		String sizeName = sizeOpr.getSizeRecord().getName();
		String statusCode = sizeOpr.getSizeRecord().getStatusRecord().getCode();

		myLog.debug(" sizeCode " + sizeCode);
		myLog.debug(" sizeName " + sizeName);
		myLog.debug(" statusCode " + statusCode);

		StringBuffer dynamicWhere = new StringBuffer();

		int parameterCount = 0;

		if (sizeCode != null && sizeCode.trim().length() > 0) {
			sizeCode = sizeCode.trim().concat("%");
			if (parameterCount > 0) {
				dynamicWhere.append(" AND size_code LIKE '" + sizeCode + "'");
			} else {
				dynamicWhere.append(" size_code LIKE '" + sizeCode + "'");
			}
			parameterCount++;
		}

		if (sizeName != null && sizeName.trim().length() > 0) {
			sizeName = sizeName.trim().concat("%");
			if (parameterCount > 0) {
				dynamicWhere.append(" AND size_name LIKE '" + sizeName + "'");
			} else {
				dynamicWhere.append(" size_name LIKE '" + sizeName + "'");
			}
			parameterCount++;
		}

		if (statusCode != null && statusCode.trim().length() > 0) {
			if (statusCode.equalsIgnoreCase(CommonConstant.StatusCodes.ACTIVE)) {
				if (parameterCount > 0) {
					dynamicWhere.append(" AND is_active = 1 ");
				} else {
					dynamicWhere.append(" is_active = 1 ");
				}
			} else if (statusCode.equalsIgnoreCase(CommonConstant.StatusCodes.INACTIVE)) {
				if (parameterCount > 0) {
					dynamicWhere.append(" AND is_active = 0 ");
				} else {
					dynamicWhere.append(" is_active = 0 ");
				}
			}

			parameterCount++;
		}

		if (parameterCount == 0) {
			dynamicWhere.append(" 1 = 1 ");
		}
		dynamicWhere.append(" ORDER BY size_code ");
		myLog.debug("dynamicWhere ::::: " + dynamicWhere);

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, SizeMasterSqlTemplate.SEARCH_SIZE_DETAILS);

		Object strSqlParams[][] = new Object[0][0];

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, dynamicWhere.toString());
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" SizeMasterBC executeSearch :: Resultset got ::" + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {

				HashMap<String, Object> resultSetMap = responseMap.get(i);
				handleAndThrowException(resultSetMap);

				SizeDVO sizeRecord = new SizeDVO();

				if (resultSetMap.get("size_id") != null)
					sizeRecord.setId(Long.valueOf(resultSetMap.get("size_id").toString()));

				sizeRecord.setCode((String) resultSetMap.get("size_code"));
				sizeRecord.setName((String) resultSetMap.get("size_name"));
				sizeRecord.setDescription((String) resultSetMap.get("size_description"));

				if (resultSetMap.get("is_active") != null) {
					sizeRecord.setActive((Boolean) resultSetMap.get("is_active"));
				} else {
					sizeRecord.setActive(false);
				}

				setAuditAttributes(sizeRecord, resultSetMap);

				sizeOprRet.getSizeList().add(sizeRecord);

			}
		} else {
			throw new BusinessException("no_data_from_db_excep_msg");
		}

		return sizeOprRet;
	}

	public SizeOpr executeSave(SizeOpr addEditSizeOpr) throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In SizeMasterBC executeSave ");

		SizeDVO sizeRecord = addEditSizeOpr.getSizeRecord();

		Long sizeId = sizeRecord.getId();
		String sizeCode = sizeRecord.getCode();
		String sizeName = sizeRecord.getName();
		String sizeDescription = sizeRecord.getDescription();

		Boolean active = sizeRecord.getActive();
		String userLogin = sizeRecord.getUserLogin();
		String lastModifiedDate = null;
		if (sizeRecord.getAuditAttributes().getLastModifiedDate() != null)
			lastModifiedDate = sizeRecord.getAuditAttributes().getLastModifiedDate().toString();

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, SizeMasterSqlTemplate.SAVE_SIZE_DETAILS);

		Object strSqlParams[][] = new Object[7][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[0][2] = sizeCode;
		myLog.debug(" parameter 1 sizeCode:: " + sizeCode);

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[1][2] = sizeName;
		myLog.debug(" parameter 2 sizeName:: " + sizeName);

		strSqlParams[2][0] = "3";
		strSqlParams[2][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[2][2] = sizeDescription;
		myLog.debug(" parameter 3 sizeDescription:: " + sizeDescription);

		strSqlParams[3][0] = "4";
		strSqlParams[3][1] = IDAOConstant.BOOLEAN_DATATYPE;
		strSqlParams[3][2] = active;
		myLog.debug(" parameter 4 active:: " + active);

		strSqlParams[4][0] = "5";
		strSqlParams[4][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[4][2] = userLogin;
		myLog.debug(" parameter 5 userLogin:: " + userLogin);

		strSqlParams[5][0] = "6";
		strSqlParams[5][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[5][2] = lastModifiedDate;
		myLog.debug(" parameter 6 lastModifiedDate:: " + lastModifiedDate);

		strSqlParams[6][0] = "7";
		strSqlParams[6][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[6][2] = sizeId;
		myLog.debug(" parameter 7 sizeId:: " + sizeId);

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug("SizeMasterBC executeSave:: Resultset got ::" + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {

				HashMap<String, Object> resultSetMap = responseMap.get(i);
				handleAndThrowException(resultSetMap);

				if (resultSetMap.get("size_id") != null)
					sizeRecord.setId(Long.valueOf(resultSetMap.get("size_id").toString()));

				setAuditAttributes(addEditSizeOpr.getSizeRecord(), resultSetMap);

			}
		}
		return getSizeDetails(sizeRecord.getId());
	}

	public SizeOpr getSizeDetails(Long sizeId) throws FrameworkException, BusinessException {

		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In SizeMasterBC :: getSizeDetails starts ");

		SizeOpr sizeOpr = new SizeOpr();

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, SizeMasterSqlTemplate.GET_SIZE_DETAILS);

		Object strSqlParams[][] = new Object[1][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[0][2] = sizeId;
		myLog.debug(" parameter 1 sizeId:: " + sizeId);

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" SizeMasterBC getSizeDetails :: Resultset got ::" + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {

				HashMap<String, Object> resultSetMap = responseMap.get(i);

				SizeDVO sizeRecord = new SizeDVO();

				if (resultSetMap.get("size_id") != null)
					sizeRecord.setId(Long.valueOf(resultSetMap.get("size_id").toString()));

				sizeRecord.setCode((String) resultSetMap.get("size_code"));
				sizeRecord.setName((String) resultSetMap.get("size_name"));
				sizeRecord.setDescription((String) resultSetMap.get("size_description"));

				if (resultSetMap.get("is_active") != null) {
					sizeRecord.setActive((Boolean) resultSetMap.get("is_active"));
				} else {
					sizeRecord.setActive(false);
				}

				sizeOpr.setSizeRecord(sizeRecord);

			}
		}
		return sizeOpr;
	}
}
