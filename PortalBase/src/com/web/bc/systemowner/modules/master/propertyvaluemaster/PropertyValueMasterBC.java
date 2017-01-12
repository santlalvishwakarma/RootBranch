package com.web.bc.systemowner.modules.master.propertyvaluemaster;

import java.util.HashMap;

import com.web.bc.systemowner.modules.master.unitmaster.UnitMasterSqlTemplate;
import com.web.common.dvo.opr.systemowner.PropertyValueMappingOpr;
import com.web.common.dvo.opr.systemowner.UnitOpr;
import com.web.common.dvo.systemowner.PropertyValueMappingDVO;
import com.web.common.dvo.systemowner.UnitDVO;
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

	public UnitOpr executeSave(UnitOpr addEditUnitOpr) throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In PropertyValueMasterBC executeSave ");

		UnitDVO unitRecord = addEditUnitOpr.getUnitRecord();

		Long unitId = unitRecord.getId();
		String unitCode = unitRecord.getCode();
		String unitName = unitRecord.getName();
		String unitDescription = unitRecord.getDescription();
		String displayName = unitRecord.getDisplayName();

		Boolean active = unitRecord.getActive();
		String userLogin = unitRecord.getUserLogin();
		String lastModifiedDate = null;
		if (unitRecord.getAuditAttributes().getLastModifiedDate() != null)
			lastModifiedDate = unitRecord.getAuditAttributes().getLastModifiedDate().toString();

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, UnitMasterSqlTemplate.SAVE_UNIT_DETAILS);

		Object strSqlParams[][] = new Object[8][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[0][2] = unitCode;
		myLog.debug(" parameter 1 unitCode:: " + unitCode);

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[1][2] = unitName;
		myLog.debug(" parameter 2 unitName:: " + unitName);

		strSqlParams[2][0] = "3";
		strSqlParams[2][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[2][2] = unitDescription;
		myLog.debug(" parameter 3 unitDescription:: " + unitDescription);

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
		strSqlParams[6][2] = unitId;
		myLog.debug(" parameter 7 unitId:: " + unitId);

		strSqlParams[7][0] = "8";
		strSqlParams[7][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[7][2] = displayName;
		myLog.debug(" parameter 8 displayName:: " + displayName);

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug("PropertyValueMasterBC executeSave:: Resultset got ::" + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {

				HashMap<String, Object> resultSetMap = responseMap.get(i);
				handleAndThrowException(resultSetMap);

				if (resultSetMap.get("unit_id") != null)
					unitRecord.setId(Long.valueOf(resultSetMap.get("unit_id").toString()));

				setAuditAttributes(addEditUnitOpr.getUnitRecord(), resultSetMap);

			}
		}
		return getUnitDetails(unitRecord.getId());
	}

	public UnitOpr getUnitDetails(Long unitId) throws FrameworkException, BusinessException {

		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In PropertyValueMasterBC :: getunitDetails starts ");

		UnitOpr unitOpr = new UnitOpr();

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, UnitMasterSqlTemplate.GET_UNIT_DETAILS);

		Object strSqlParams[][] = new Object[1][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[0][2] = unitId;
		myLog.debug(" parameter 1 unitId:: " + unitId);

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" PropertyValueMasterBC getunitDetails :: Resultset got ::" + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {

				HashMap<String, Object> resultSetMap = responseMap.get(i);

				UnitDVO unitRecord = new UnitDVO();

				if (resultSetMap.get("unit_id") != null)
					unitRecord.setId(Long.valueOf(resultSetMap.get("unit_id").toString()));

				unitRecord.setCode((String) resultSetMap.get("unit_code"));
				unitRecord.setName((String) resultSetMap.get("unit_name"));
				unitRecord.setDescription((String) resultSetMap.get("unit_description"));
				unitRecord.setDisplayName((String) resultSetMap.get("display_name"));

				if (resultSetMap.get("is_active") != null) {
					unitRecord.setActive((Boolean) resultSetMap.get("is_active"));
				} else {
					unitRecord.setActive(false);
				}

				setAuditAttributes(unitRecord, resultSetMap);

				unitOpr.setUnitRecord(unitRecord);

			}
		}
		return unitOpr;
	}
}
