package com.web.bc.systemowner.modules.master.skupropertymaster;

import java.util.HashMap;

import com.web.common.dvo.common.Parameter;
import com.web.common.dvo.opr.systemowner.SkuPropertyOpr;
import com.web.common.dvo.systemowner.SkuPropertyDVO;
import com.web.common.parents.BackingClass;
import com.web.foundation.dao.DAOResult;
import com.web.foundation.dao.IDAOConstant;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;

public class SkuPropertyBC extends BackingClass {

	public SkuPropertyOpr executeSearch(SkuPropertyOpr searchSkuPropertyOpr)
			throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("Inside executeSearch: ");

		String skuPropertyName = searchSkuPropertyOpr.getSkuPropertyRecord().getName();
		String skuPropertyCode = searchSkuPropertyOpr.getSkuPropertyRecord().getCode();
		Integer skuPropertyType = searchSkuPropertyOpr.getSkuPropertyRecord().getSkuPropertyType().getParameterID();

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, SkuPropertySqlTemplate.SEARCH_SKU_PROPERTIES);

		StringBuffer dynamicWhere = new StringBuffer();
		int parameterCount = 0;

		if (skuPropertyName != null && skuPropertyName.trim().length() > 0) {
			skuPropertyName = skuPropertyName.trim().concat("%");
			dynamicWhere.append(" sku_property_name LIKE '" + skuPropertyName + "'");
			parameterCount++;
		}

		if (skuPropertyCode != null && skuPropertyCode.trim().length() > 0) {
			skuPropertyCode = skuPropertyCode.trim().concat("%");
			if (parameterCount > 0) {
				dynamicWhere.append(" AND sku_property_code = '" + skuPropertyCode + "'");
			} else {
				dynamicWhere.append(" sku_property_code = '" + skuPropertyCode + "'");
			}
			parameterCount++;
		}

		if (skuPropertyType != null && !skuPropertyType.equals(0)) {
			if (parameterCount > 0) {
				dynamicWhere.append(" AND sku_property_type = " + skuPropertyType + "");
			} else {
				dynamicWhere.append(" sku_property_type = " + skuPropertyType + "");
			}
			parameterCount++;
		}

		myLog.debug("Dynamic Where : " + dynamicWhere);
		Object strSqlParams[][] = new Object[0][3];

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, dynamicWhere.toString());
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" executeSearch :: Resultset got ::" + responseMap);

		int responseSize = responseMap.size();
		if (responseSize > 0) {
			for (int i = 0; i < responseSize; i++) {
				HashMap<String, Object> resultSetMap = responseMap.get(i);
				SkuPropertyDVO skuPropertyRecord = new SkuPropertyDVO();

				setAuditAttributes(skuPropertyRecord, resultSetMap);

				Object data = resultSetMap.get("sku_property_id");
				if (data != null) {
					skuPropertyRecord.setId(Long.valueOf(data.toString()));
				}

				data = resultSetMap.get("sku_property_code");
				skuPropertyRecord.setCode((String) data);

				data = resultSetMap.get("sku_property_name");
				skuPropertyRecord.setName((String) data);

				data = resultSetMap.get("sku_property_description");
				skuPropertyRecord.setDescription((String) data);

				Parameter propertyParameter = new Parameter();

				data = resultSetMap.get("sku_property_type");
				if (data != null) {
					propertyParameter.setParameterIDLongValue(Long.valueOf(data.toString()));
				}

				data = resultSetMap.get("sku_property_type_value_text");
				propertyParameter.setParameterStringValue((String) data);

				skuPropertyRecord.setSkuPropertyType(propertyParameter);

				data = resultSetMap.get("sku_property_price");
				if (data != null) {
					skuPropertyRecord.setSkuPropertyPrice(Integer.valueOf(data.toString()));
				}

				data = resultSetMap.get("is_active");
				if (data != null) {
					String isActive = data.toString();
					if (isActive.equals("1")) {
						skuPropertyRecord.setActive(true);
					} else if (isActive.equals("0")) {
						skuPropertyRecord.setActive(false);
					}
				}
				searchSkuPropertyOpr.getSkuPropertyList().add(skuPropertyRecord);
			}
		}
		return searchSkuPropertyOpr;
	}

	public SkuPropertyOpr executeSave(SkuPropertyOpr addEditSkuPropertyOpr) throws FrameworkException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("Inside executeSave: ");

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, SkuPropertySqlTemplate.SAVE_SKU_PROPERTIES);

		Long id = addEditSkuPropertyOpr.getSkuPropertyRecord().getId();
		String code = addEditSkuPropertyOpr.getSkuPropertyRecord().getCode();
		String name = addEditSkuPropertyOpr.getSkuPropertyRecord().getName();
		String description = addEditSkuPropertyOpr.getSkuPropertyRecord().getDescription();
		boolean isActive = addEditSkuPropertyOpr.getSkuPropertyRecord().getActive();
		Integer skuPropertyPrice = addEditSkuPropertyOpr.getSkuPropertyRecord().getSkuPropertyPrice();
		Integer parameterType = addEditSkuPropertyOpr.getSkuPropertyRecord().getSkuPropertyType().getParameterID();
		String userLogin = addEditSkuPropertyOpr.getSkuPropertyRecord().getUserLogin();

		Object[][] strSqlParams = new Object[8][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[0][2] = id;

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[1][2] = code;

		strSqlParams[2][0] = "3";
		strSqlParams[2][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[2][2] = name;

		strSqlParams[3][0] = "4";
		strSqlParams[3][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[3][2] = description;

		strSqlParams[4][0] = "5";
		strSqlParams[4][1] = IDAOConstant.BOOLEAN_DATATYPE;
		strSqlParams[4][2] = isActive;

		strSqlParams[5][0] = "6";
		strSqlParams[5][1] = IDAOConstant.INT_DATATYPE;
		strSqlParams[5][2] = skuPropertyPrice;

		strSqlParams[6][0] = "7";
		strSqlParams[6][1] = IDAOConstant.INT_DATATYPE;
		strSqlParams[6][2] = parameterType;

		strSqlParams[7][0] = "8";
		strSqlParams[7][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[7][2] = userLogin;

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" executeSearch :: Resultset got ::" + responseMap);
		int resultSetSize = responseMap.size();
		if (resultSetSize > 0) {
			for (int i = 0; i < resultSetSize; i++) {
				HashMap<String, Object> resultSetMap = responseMap.get(i);
				if (resultSetMap.get("sku_property_id") != null) {
					addEditSkuPropertyOpr.getSkuPropertyRecord()
							.setId(Long.valueOf(resultSetMap.get("sku_property_id").toString()));
				}
			}
		}
		return addEditSkuPropertyOpr;
	}

}
