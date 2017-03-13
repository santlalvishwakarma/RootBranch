package com.web.bc.systemowner.modules.master.propertyvaluemaster;

import java.util.HashMap;

import com.web.common.dvo.opr.systemowner.PropertyValueMappingOpr;
import com.web.common.dvo.systemowner.PropertyValueMappingDVO;
import com.web.common.parents.BackingClass;
import com.web.foundation.dao.DAOResult;
import com.web.foundation.dao.IDAOConstant;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;

public class PropertyValueMasterBC extends BackingClass {

	public PropertyValueMappingOpr executeSearch(PropertyValueMappingOpr propertyValueMappingOpr)
			throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In PropertyValueMasterBC executeSearch ");

		PropertyValueMappingOpr PropertyValueMappingOprRet = new PropertyValueMappingOpr();

		Long sizeId = propertyValueMappingOpr.getPropertyValueMappingRecord().getSizeRecord().getId();

		myLog.debug(" sizeId " + sizeId);
		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, PropertyValueMasterSqlTemplate.SEARCH_PROPERTY_DETAILS);

		Object strSqlParams[][] = new Object[1][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[0][2] = sizeId;
		myLog.debug(" parameter 1 sizeId:: " + sizeId);

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug("PropertyValueMasterBC executeSave:: Resultset got ::" + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {

				HashMap<String, Object> resultSetMap = responseMap.get(i);
				handleAndThrowException(resultSetMap);

				PropertyValueMappingDVO PropertyValueMappingRecord = new PropertyValueMappingDVO();

				if (resultSetMap.get("property_value_mapping_id") != null)
					PropertyValueMappingRecord.setId(Long.valueOf(resultSetMap.get("property_value_mapping_id")
							.toString()));

				if (resultSetMap.get("size_id") != null)
					PropertyValueMappingRecord.getSizeRecord().setId(
							Long.valueOf(resultSetMap.get("size_id").toString()));

				PropertyValueMappingRecord.getSizeRecord().setName((String) resultSetMap.get("size_name"));
				PropertyValueMappingRecord.getSizeRecord().setCode((String) resultSetMap.get("size_code"));

				if (resultSetMap.get("unit_id") != null)
					PropertyValueMappingRecord.getUnitRecord().setId(
							Long.valueOf(resultSetMap.get("unit_id").toString()));

				PropertyValueMappingRecord.getUnitRecord().setName((String) resultSetMap.get("unit_name"));

				PropertyValueMappingRecord.setPropertyValue((String) resultSetMap.get("property_value"));

				if (resultSetMap.get("is_active") != null) {
					PropertyValueMappingRecord.setActive((Boolean) resultSetMap.get("is_active"));
				} else {
					PropertyValueMappingRecord.setActive(false);
				}

				setAuditAttributes(PropertyValueMappingRecord, resultSetMap);

				PropertyValueMappingOprRet.getPropertyValueMappingList().add(PropertyValueMappingRecord);

			}
		} else {
			throw new BusinessException("no_data_from_db_excep_msg");
		}

		return PropertyValueMappingOprRet;
	}

	public PropertyValueMappingOpr executeSave(PropertyValueMappingOpr propertyValueMappingOpr)
			throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In PropertyValueMasterBC executeSave ");

		PropertyValueMappingDVO propertyValueMappingRecord = propertyValueMappingOpr
				.getSelectedPropertyValueMappingRecord();

		Long propertyValueMappingId = propertyValueMappingRecord.getId();
		Long sizeId = propertyValueMappingRecord.getSizeRecord().getId();
		String propertyValue = propertyValueMappingRecord.getPropertyValue();
		Long unitId = propertyValueMappingRecord.getUnitRecord().getId();
		String sizeCode = propertyValueMappingRecord.getSizeRecord().getCode();
		String sizeName = propertyValueMappingRecord.getSizeRecord().getName();
		String unitCode = propertyValueMappingRecord.getUnitRecord().getCode();
		String unitName = propertyValueMappingRecord.getUnitRecord().getName();

		Boolean active = propertyValueMappingRecord.getActive();
		String userLogin = propertyValueMappingRecord.getUserLogin();
		String lastModifiedDate = null;
		if (propertyValueMappingRecord.getAuditAttributes().getLastModifiedDate() != null)
			lastModifiedDate = propertyValueMappingRecord.getAuditAttributes().getLastModifiedDate().toString();

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, PropertyValueMasterSqlTemplate.SAVE_PROPERTY_VALUE_MAPPING_DETAILS);

		Object strSqlParams[][] = new Object[11][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[0][2] = propertyValueMappingId;
		myLog.debug(" parameter 1 propertyValueMappingId:: " + propertyValueMappingId);

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[1][2] = sizeId;
		myLog.debug(" parameter 2 sizeId:: " + sizeId);

		strSqlParams[2][0] = "3";
		strSqlParams[2][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[2][2] = propertyValue;
		myLog.debug(" parameter 3 propertyValue:: " + propertyValue);

		strSqlParams[3][0] = "4";
		strSqlParams[3][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[3][2] = unitId;
		myLog.debug(" parameter 4 unitId:: " + unitId);

		strSqlParams[4][0] = "5";
		strSqlParams[4][1] = IDAOConstant.BOOLEAN_DATATYPE;
		strSqlParams[4][2] = active;
		myLog.debug(" parameter 5 active:: " + active);

		strSqlParams[5][0] = "6";
		strSqlParams[5][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[5][2] = userLogin;
		myLog.debug(" parameter 6 userLogin:: " + userLogin);

		strSqlParams[6][0] = "7";
		strSqlParams[6][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[6][2] = lastModifiedDate;
		myLog.debug(" parameter 7 lastModifiedDate:: " + lastModifiedDate);

		strSqlParams[7][0] = "8";
		strSqlParams[7][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[7][2] = sizeCode;
		myLog.debug(" parameter 7 sizeCode:: " + sizeCode);

		strSqlParams[8][0] = "9";
		strSqlParams[8][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[8][2] = sizeName;
		myLog.debug(" parameter 7 sizeName:: " + sizeName);

		strSqlParams[9][0] = "10";
		strSqlParams[9][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[9][2] = unitCode;
		myLog.debug(" parameter 7 unitCode:: " + unitCode);

		strSqlParams[10][0] = "11";
		strSqlParams[10][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[10][2] = unitName;
		myLog.debug(" parameter 7 unitName:: " + unitName);

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug("PropertyValueMasterBC executeSave:: Resultset got ::" + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {

				HashMap<String, Object> resultSetMap = responseMap.get(i);
				handleAndThrowException(resultSetMap);

				if (resultSetMap.get("property_value_mapping_id") != null)
					propertyValueMappingOpr.getPropertyValueMappingRecord().setId(
							Long.valueOf(resultSetMap.get("property_value_mapping_id").toString()));

				setAuditAttributes(propertyValueMappingOpr.getSelectedPropertyValueMappingRecord(), resultSetMap);

			}
		}
		return propertyValueMappingOpr;
	}
}
