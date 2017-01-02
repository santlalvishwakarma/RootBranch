package com.web.bc.systemowner.modules.master.colormaster;

import java.util.HashMap;

import com.web.common.constants.CommonConstant;
import com.web.common.dvo.opr.systemowner.ColorOpr;
import com.web.common.dvo.systemowner.ColorDVO;
import com.web.common.parents.BackingClass;
import com.web.foundation.dao.DAOResult;
import com.web.foundation.dao.IDAOConstant;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;

public class ColorMasterBC extends BackingClass {

	public ColorOpr executeSearch(ColorOpr colorOpr) throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In ColorMasterBC executeSearch ");

		ColorOpr colorOprRet = new ColorOpr();

		String colorCode = colorOpr.getColorRecord().getCode();
		String colorName = colorOpr.getColorRecord().getName();
		String statusCode = colorOpr.getColorRecord().getStatusRecord().getCode();

		myLog.debug(" colorCode " + colorCode);
		myLog.debug(" colorName " + colorName);
		myLog.debug(" statusCode " + statusCode);

		StringBuffer dynamicWhere = new StringBuffer();

		int parameterCount = 0;

		if (colorCode != null && colorCode.trim().length() > 0) {
			colorCode = colorCode.trim().concat("%");
			if (parameterCount > 0) {
				dynamicWhere.append(" AND color_code LIKE '" + colorCode + "'");
			} else {
				dynamicWhere.append(" color_code LIKE '" + colorCode + "'");
			}
			parameterCount++;
		}

		if (colorName != null && colorName.trim().length() > 0) {
			colorName = colorName.trim().concat("%");
			if (parameterCount > 0) {
				dynamicWhere.append(" AND color_name LIKE '" + colorName + "'");
			} else {
				dynamicWhere.append(" color_name LIKE '" + colorName + "'");
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
		dynamicWhere.append(" ORDER BY color_code ");
		myLog.debug("dynamicWhere ::::: " + dynamicWhere);

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ColorMasterSqlTemplate.SEARCH_COLOR_DETAILS);

		Object strSqlParams[][] = new Object[0][0];

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, dynamicWhere.toString());
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" ColorMasterBC executeSearch :: Resultset got ::" + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {

				HashMap<String, Object> resultSetMap = responseMap.get(i);
				handleAndThrowException(resultSetMap);

				ColorDVO colorRecord = new ColorDVO();

				if (resultSetMap.get("color_id") != null)
					colorRecord.setId(Long.valueOf(resultSetMap.get("color_id").toString()));

				colorRecord.setCode((String) resultSetMap.get("color_code"));
				colorRecord.setName((String) resultSetMap.get("color_name"));
				colorRecord.setDescription((String) resultSetMap.get("color_description"));

				if (resultSetMap.get("is_active") != null) {
					colorRecord.setActive((Boolean) resultSetMap.get("is_active"));
				} else {
					colorRecord.setActive(false);
				}

				setAuditAttributes(colorRecord, resultSetMap);

				colorOprRet.getColorList().add(colorRecord);

			}
		} else {
			throw new BusinessException("no_data_from_db_excep_msg");
		}

		return colorOprRet;
	}

	public ColorOpr executeSave(ColorOpr addEditColorOpr) throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In ColorMasterBC executeSave ");

		ColorDVO colorRecord = addEditColorOpr.getColorRecord();

		Long colorId = colorRecord.getId();
		String colorCode = colorRecord.getCode();
		String colorName = colorRecord.getName();
		String colorDescription = colorRecord.getDescription();

		Boolean active = colorRecord.getActive();
		String userLogin = colorRecord.getUserLogin();
		String lastModifiedDate = null;
		if (colorRecord.getAuditAttributes().getLastModifiedDate() != null)
			lastModifiedDate = colorRecord.getAuditAttributes().getLastModifiedDate().toString();

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ColorMasterSqlTemplate.SAVE_COLOR_DETAILS);

		Object strSqlParams[][] = new Object[7][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[0][2] = colorCode;
		myLog.debug(" parameter 1 colorCode:: " + colorCode);

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[1][2] = colorName;
		myLog.debug(" parameter 2 colorName:: " + colorName);

		strSqlParams[2][0] = "3";
		strSqlParams[2][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[2][2] = colorDescription;
		myLog.debug(" parameter 3 colorDescription:: " + colorDescription);

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
		strSqlParams[6][2] = colorId;
		myLog.debug(" parameter 7 colorId:: " + colorId);

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug("ColorMasterBC executeSave:: Resultset got ::" + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {

				HashMap<String, Object> resultSetMap = responseMap.get(i);
				handleAndThrowException(resultSetMap);

				if (resultSetMap.get("color_id") != null)
					colorRecord.setId(Long.valueOf(resultSetMap.get("color_id").toString()));

				setAuditAttributes(addEditColorOpr.getColorRecord(), resultSetMap);

			}
		}
		return getColorDetails(colorRecord.getId());
	}

	public ColorOpr getColorDetails(Long colorId) throws FrameworkException, BusinessException {

		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In ColorMasterBC :: getcolorDetails starts ");

		ColorOpr colorOpr = new ColorOpr();

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ColorMasterSqlTemplate.GET_COLOR_DETAILS);

		Object strSqlParams[][] = new Object[1][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[0][2] = colorId;
		myLog.debug(" parameter 1 colorId:: " + colorId);

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" ColorMasterBC getcolorDetails :: Resultset got ::" + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {

				HashMap<String, Object> resultSetMap = responseMap.get(i);

				ColorDVO colorRecord = new ColorDVO();

				if (resultSetMap.get("color_id") != null)
					colorRecord.setId(Long.valueOf(resultSetMap.get("color_id").toString()));

				colorRecord.setCode((String) resultSetMap.get("color_code"));
				colorRecord.setName((String) resultSetMap.get("color_name"));
				colorRecord.setDescription((String) resultSetMap.get("color_description"));

				if (resultSetMap.get("is_active") != null) {
					colorRecord.setActive((Boolean) resultSetMap.get("is_active"));
				} else {
					colorRecord.setActive(false);
				}

				setAuditAttributes(colorRecord, resultSetMap);

				colorOpr.setColorRecord(colorRecord);

			}
		}
		return colorOpr;
	}
}
