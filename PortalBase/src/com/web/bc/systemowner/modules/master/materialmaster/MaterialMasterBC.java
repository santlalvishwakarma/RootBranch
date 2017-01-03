package com.web.bc.systemowner.modules.master.materialmaster;

import java.util.HashMap;

import com.web.common.constants.CommonConstant;
import com.web.common.dvo.opr.systemowner.MaterialOpr;
import com.web.common.dvo.systemowner.MaterialDVO;
import com.web.common.parents.BackingClass;
import com.web.foundation.dao.DAOResult;
import com.web.foundation.dao.IDAOConstant;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;

public class MaterialMasterBC extends BackingClass {

	public MaterialOpr executeSearch(MaterialOpr materialOpr) throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In MaterialMasterBC executeSearch ");

		MaterialOpr materialOprRet = new MaterialOpr();

		String materialCode = materialOpr.getMaterialRecord().getCode();
		String materialName = materialOpr.getMaterialRecord().getName();
		String statusCode = materialOpr.getMaterialRecord().getStatusRecord().getCode();

		myLog.debug(" materialCode " + materialCode);
		myLog.debug(" materialName " + materialName);
		myLog.debug(" statusCode " + statusCode);

		StringBuffer dynamicWhere = new StringBuffer();

		int parameterCount = 0;

		if (materialCode != null && materialCode.trim().length() > 0) {
			materialCode = materialCode.trim().concat("%");
			if (parameterCount > 0) {
				dynamicWhere.append(" AND material_code LIKE '" + materialCode + "'");
			} else {
				dynamicWhere.append(" material_code LIKE '" + materialCode + "'");
			}
			parameterCount++;
		}

		if (materialName != null && materialName.trim().length() > 0) {
			materialName = materialName.trim().concat("%");
			if (parameterCount > 0) {
				dynamicWhere.append(" AND material_name LIKE '" + materialName + "'");
			} else {
				dynamicWhere.append(" material_name LIKE '" + materialName + "'");
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
		dynamicWhere.append(" ORDER BY material_code ");
		myLog.debug("dynamicWhere ::::: " + dynamicWhere);

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, MaterialMasterSqlTemplate.SEARCH_MATERIAL_DETAILS);

		Object strSqlParams[][] = new Object[0][0];

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, dynamicWhere.toString());
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" MaterialMasterBC executeSearch :: Resultset got ::" + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {

				HashMap<String, Object> resultSetMap = responseMap.get(i);
				handleAndThrowException(resultSetMap);

				MaterialDVO materialRecord = new MaterialDVO();

				if (resultSetMap.get("material_id") != null)
					materialRecord.setId(Long.valueOf(resultSetMap.get("material_id").toString()));

				materialRecord.setCode((String) resultSetMap.get("material_code"));
				materialRecord.setName((String) resultSetMap.get("material_name"));
				materialRecord.setDescription((String) resultSetMap.get("material_description"));

				if (resultSetMap.get("is_active") != null) {
					materialRecord.setActive((Boolean) resultSetMap.get("is_active"));
				} else {
					materialRecord.setActive(false);
				}

				setAuditAttributes(materialRecord, resultSetMap);

				materialOprRet.getMaterialList().add(materialRecord);

			}
		} else {
			throw new BusinessException("no_data_from_db_excep_msg");
		}

		return materialOprRet;
	}

	public MaterialOpr executeSave(MaterialOpr addEditMaterialOpr) throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In MaterialMasterBC executeSave ");

		MaterialDVO materialRecord = addEditMaterialOpr.getMaterialRecord();

		Long materialId = materialRecord.getId();
		String materialCode = materialRecord.getCode();
		String materialName = materialRecord.getName();
		String materialDescription = materialRecord.getDescription();

		Boolean active = materialRecord.getActive();
		String userLogin = materialRecord.getUserLogin();
		String lastModifiedDate = null;
		if (materialRecord.getAuditAttributes().getLastModifiedDate() != null)
			lastModifiedDate = materialRecord.getAuditAttributes().getLastModifiedDate().toString();

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, MaterialMasterSqlTemplate.SAVE_MATERIAL_DETAILS);

		Object strSqlParams[][] = new Object[7][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[0][2] = materialCode;
		myLog.debug(" parameter 1 materialCode:: " + materialCode);

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[1][2] = materialName;
		myLog.debug(" parameter 2 materialName:: " + materialName);

		strSqlParams[2][0] = "3";
		strSqlParams[2][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[2][2] = materialDescription;
		myLog.debug(" parameter 3 materialDescription:: " + materialDescription);

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
		strSqlParams[6][2] = materialId;
		myLog.debug(" parameter 7 materialId:: " + materialId);

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug("MaterialMasterBC executeSave:: Resultset got ::" + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {

				HashMap<String, Object> resultSetMap = responseMap.get(i);
				handleAndThrowException(resultSetMap);

				if (resultSetMap.get("material_id") != null)
					materialRecord.setId(Long.valueOf(resultSetMap.get("material_id").toString()));

				setAuditAttributes(addEditMaterialOpr.getMaterialRecord(), resultSetMap);

			}
		}
		return getMaterialDetails(materialRecord.getId());
	}

	public MaterialOpr getMaterialDetails(Long materialId) throws FrameworkException, BusinessException {

		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In MaterialMasterBC :: getMaterialDetails starts ");

		MaterialOpr materialOpr = new MaterialOpr();

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, MaterialMasterSqlTemplate.GET_MATERIAL_DETAILS);

		Object strSqlParams[][] = new Object[1][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[0][2] = materialId;
		myLog.debug(" parameter 1 materialId:: " + materialId);

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" MaterialMasterBC getMaterialDetails :: Resultset got ::" + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {

				HashMap<String, Object> resultSetMap = responseMap.get(i);

				MaterialDVO materialRecord = new MaterialDVO();

				if (resultSetMap.get("material_id") != null)
					materialRecord.setId(Long.valueOf(resultSetMap.get("material_id").toString()));

				materialRecord.setCode((String) resultSetMap.get("material_code"));
				materialRecord.setName((String) resultSetMap.get("material_name"));
				materialRecord.setDescription((String) resultSetMap.get("material_description"));

				if (resultSetMap.get("is_active") != null) {
					materialRecord.setActive((Boolean) resultSetMap.get("is_active"));
				} else {
					materialRecord.setActive(false);
				}

				setAuditAttributes(materialRecord, resultSetMap);

				materialOpr.setMaterialRecord(materialRecord);

			}
		}
		return materialOpr;
	}
}
