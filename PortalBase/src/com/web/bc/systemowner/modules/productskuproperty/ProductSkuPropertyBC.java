package com.web.bc.systemowner.modules.productskuproperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.web.common.constants.CommonConstant;
import com.web.common.dvo.systemowner.PropertyDVO;
import com.web.common.parents.BackingClass;
import com.web.foundation.dao.DAOResult;
import com.web.foundation.dao.IDAOConstant;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;

/**
 * @author NIRAJ
 * 
 */
public class ProductSkuPropertyBC extends BackingClass {

	public ProductPropertiesOpr executeSearch(ProductPropertiesOpr searchParameters) throws FrameworkException,
			BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		ProductPropertiesOpr productPropertiesOprRet = new ProductPropertiesOpr();

		String propertyCode = searchParameters.getProductPropertiesRecord().getCode();
		String propertyName = searchParameters.getProductPropertiesRecord().getName();
		String propertyDescription = searchParameters.getProductPropertiesRecord().getDescription();
		String categoryCode = searchParameters.getProductPropertiesRecord().getProductCategoryRecord().getCode();

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ProductPropertySqlTemplate.SEARCH_PRODUCTS_PROPERTIES);

		Object strSqlParams[][] = new Object[4][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[0][2] = propertyCode;
		myLog.debug(" parameter 1 :: " + propertyCode);

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[1][2] = propertyName;
		myLog.debug(" parameter 2 :: " + propertyName);

		strSqlParams[2][0] = "3";
		strSqlParams[2][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[2][2] = propertyDescription;
		myLog.debug(" parameter 3 :: " + propertyDescription);

		strSqlParams[3][0] = "4";
		strSqlParams[3][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[3][2] = categoryCode;
		myLog.debug(" parameter 4 :: " + categoryCode);

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" property executeSearch :: Resultset got ::" + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {

				HashMap<String, Object> resultSetMap = responseMap.get(i);
				handleAndThrowException(resultSetMap);

				ProductPropertiesDVO productPropertiesRecord = new ProductPropertiesDVO();
				if (resultSetMap.get("product_properties_id") != null)
					productPropertiesRecord.setId(Long.valueOf(resultSetMap.get("product_properties_id").toString()));
				productPropertiesRecord.setCode((String) resultSetMap.get("product_property_code"));
				productPropertiesRecord.setName((String) resultSetMap.get("product_property_name"));
				productPropertiesRecord.setDescription((String) resultSetMap.get("product_property_description"));

				if (resultSetMap.get("value_type") != null)
					productPropertiesRecord.getValueType().setParameterID(
							Integer.valueOf(resultSetMap.get("value_type").toString()));
				if (resultSetMap.get("value_type_seq_no") != null)
					productPropertiesRecord.getValueType().setSequenceNumber(
							Integer.valueOf(resultSetMap.get("value_type_seq_no").toString()));
				// productPropertiesRecord.setValueType((Boolean)
				// resultSetMap.get("value_type"));

				if (resultSetMap.get("is_mandatory") != null)
					productPropertiesRecord.setMandatory((Boolean) resultSetMap.get("is_mandatory"));
				productPropertiesRecord.getUomRecord().setCode((String) resultSetMap.get("uom_code"));
				productPropertiesRecord.getUomRecord().setName((String) resultSetMap.get("uom_name"));

				// productPropertiesRecord.getUomRecord().setUomCategoryCode(
				// (String)
				// resultSetMap.get("uom_category_code"));

				if (resultSetMap.get("assortment_planning") != null)
					productPropertiesRecord.setAssortmentPlanning((Boolean) resultSetMap.get("assortment_planning"));
				if (resultSetMap.get("sku_code_generation") != null)
					productPropertiesRecord.setSkuCodeGeneration((Boolean) resultSetMap.get("sku_code_generation"));
				if (resultSetMap.get("is_active") != null)
					productPropertiesRecord.setActive(!((Boolean) resultSetMap.get("is_active")));

				if (resultSetMap.get("sequence_number") != null)
					productPropertiesRecord.setSequenceNumber(Integer.valueOf(resultSetMap.get("sequence_number")
							.toString()));
				if (resultSetMap.get("category_id") != null)
					productPropertiesRecord.getItemCategoryRecord().setId(
							Long.valueOf(resultSetMap.get("category_id").toString()));

				setAuditAttributes(productPropertiesRecord, resultSetMap);
				productPropertiesOprRet.getProductPropertiesList().add(productPropertiesRecord);

			}
		} else {
			throw new BusinessException("no_data_from_db_excep_msg");
		}

		return productPropertiesOprRet;
	}

	public ProductPropertiesOpr getProductPropertiesValues(Long productPropertiesId) throws FrameworkException,
			BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		ProductPropertiesOpr productPropertiesOprRet = new ProductPropertiesOpr();

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ProductPropertySqlTemplate.GET_PRODUCTS_PROPERTIES_VALUES);

		Object strSqlParams[][] = new Object[1][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[0][2] = productPropertiesId;
		myLog.debug(" parameter 1 :: " + productPropertiesId);

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug("get property values :: Resultset got ::" + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {

				HashMap<String, Object> resultSetMap = responseMap.get(i);
				handleAndThrowException(resultSetMap);

				ProductPropertiesValuesDVO productPropertiesValuesRecord = new ProductPropertiesValuesDVO();

				if (resultSetMap.get("product_properties_values_id") != null)
					productPropertiesValuesRecord.setId(Long.valueOf(resultSetMap.get("product_properties_values_id")
							.toString()));
				productPropertiesValuesRecord.setPropertyValue((String) resultSetMap.get("product_property_value"));
				if (resultSetMap.get("include_report") != null)
					productPropertiesValuesRecord.setIncludeInReport((Boolean) resultSetMap.get("include_report"));
				productPropertiesValuesRecord.setImageUrl((String) resultSetMap.get("image_url"));
				if (resultSetMap.get("default_value") != null)
					productPropertiesValuesRecord.setDefaultValue((Boolean) resultSetMap.get("default_value"));

				setAuditAttributes(productPropertiesValuesRecord, resultSetMap);
				productPropertiesOprRet.getProductPropertiesRecord().getProductPropertiesValuesList()
						.add(productPropertiesValuesRecord);
			}
		}

		return productPropertiesOprRet;
	}

	public ProductPropertiesOpr executeSave(ProductPropertiesOpr saveParameters) throws FrameworkException,
			BusinessException {

		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		ProductPropertiesOpr productPropertiesOprRet = new ProductPropertiesOpr();

		ProductPropertiesDVO productPropertiesRecord = saveParameters.getProductPropertiesRecord();

		Long propertyId = productPropertiesRecord.getId();
		String propertyCode = productPropertiesRecord.getCode();
		String propertyName = productPropertiesRecord.getName();
		String propertyDescription = productPropertiesRecord.getDescription();
		Integer valueType = productPropertiesRecord.getValueType().getParameterID();
		Boolean mandatory = productPropertiesRecord.getMandatory();
		Integer sequenceNumber = productPropertiesRecord.getSequenceNumber();
		String uomCode = productPropertiesRecord.getUomRecord().getCode();
		Boolean assortmentPlanning = productPropertiesRecord.getAssortmentPlanning();
		Boolean skuCodeGeneration = productPropertiesRecord.getSkuCodeGeneration();
		Boolean active = false;
		if (productPropertiesRecord.getActive() != null)
			active = !(productPropertiesRecord.getActive());
		String userLogin = productPropertiesRecord.getUserLogin();
		String lastModifiedDate = null;
		if (productPropertiesRecord.getAuditAttributes().getLastModifiedDate() != null)
			lastModifiedDate = productPropertiesRecord.getAuditAttributes().getLastModifiedDate().toString();
		StringBuffer parsePropertiesValuesString = new StringBuffer();
		Long itemCategory = productPropertiesRecord.getItemCategoryRecord().getId();

		if (mandatory == null)
			mandatory = false;
		if (assortmentPlanning == null)
			assortmentPlanning = false;
		if (skuCodeGeneration == null)
			skuCodeGeneration = false;

		// id~value~report~image~default;

		for (ProductPropertiesValuesDVO productPropertiesValuesRecord : productPropertiesRecord
				.getProductPropertiesValuesList()) {
			if (!(productPropertiesValuesRecord.getOperationalAttributes().getRecordDeleted())) {
				Long productPropertiesValueId = productPropertiesValuesRecord.getId();
				String propertyValue = productPropertiesValuesRecord.getPropertyValue();
				boolean includeInReport = productPropertiesValuesRecord.getIncludeInReport();
				String imageUrl = productPropertiesValuesRecord.getImageUrl();
				boolean defaultValue = productPropertiesValuesRecord.getDefaultValue();

				if (productPropertiesValueId != null)
					parsePropertiesValuesString.append(productPropertiesValueId);
				else
					parsePropertiesValuesString.append("");

				parsePropertiesValuesString.append("~");
				if (propertyValue != null)
					parsePropertiesValuesString.append(propertyValue);
				else
					parsePropertiesValuesString.append("");

				parsePropertiesValuesString.append("~");
				if (includeInReport)
					parsePropertiesValuesString.append("1");
				else
					parsePropertiesValuesString.append("0");

				parsePropertiesValuesString.append("~");
				if (imageUrl != null)
					parsePropertiesValuesString.append(imageUrl);
				else
					parsePropertiesValuesString.append("");

				parsePropertiesValuesString.append("~");
				if (defaultValue)
					parsePropertiesValuesString.append("1");
				else
					parsePropertiesValuesString.append("0");

				parsePropertiesValuesString.append(";");
			}
		}

		if (parsePropertiesValuesString != null && parsePropertiesValuesString.length() > 1) {
			// this is to remove the last ; sign
			parsePropertiesValuesString.deleteCharAt(parsePropertiesValuesString.length() - 1);
		}

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ProductPropertySqlTemplate.SAVE_PRODUCTS_PROPERTIES);

		Object strSqlParams[][] = new Object[15][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[0][2] = propertyId;
		myLog.debug(" parameter 1 :: " + propertyId);

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[1][2] = propertyCode;
		myLog.debug(" parameter 2 :: " + propertyCode);

		strSqlParams[2][0] = "3";
		strSqlParams[2][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[2][2] = propertyName;
		myLog.debug(" parameter 3 :: " + propertyName);

		strSqlParams[3][0] = "4";
		strSqlParams[3][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[3][2] = propertyDescription;
		myLog.debug(" parameter 4 :: " + propertyDescription);

		strSqlParams[4][0] = "5";
		strSqlParams[4][1] = IDAOConstant.INT_DATATYPE;
		strSqlParams[4][2] = valueType;
		myLog.debug(" parameter 5 :: " + valueType);

		strSqlParams[5][0] = "6";
		strSqlParams[5][1] = IDAOConstant.BOOLEAN_DATATYPE;
		strSqlParams[5][2] = mandatory;
		myLog.debug(" parameter 6 :: " + mandatory);

		strSqlParams[6][0] = "7";
		strSqlParams[6][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[6][2] = uomCode;
		myLog.debug(" parameter 7 :: " + uomCode);

		strSqlParams[7][0] = "8";
		strSqlParams[7][1] = IDAOConstant.INT_DATATYPE;
		strSqlParams[7][2] = sequenceNumber;
		myLog.debug(" parameter 8 :: " + sequenceNumber);

		strSqlParams[8][0] = "9";
		strSqlParams[8][1] = IDAOConstant.BOOLEAN_DATATYPE;
		strSqlParams[8][2] = assortmentPlanning;
		myLog.debug(" parameter 9 :: " + assortmentPlanning);

		strSqlParams[9][0] = "10";
		strSqlParams[9][1] = IDAOConstant.BOOLEAN_DATATYPE;
		strSqlParams[9][2] = skuCodeGeneration;
		myLog.debug(" parameter 10 :: " + skuCodeGeneration);

		strSqlParams[10][0] = "11";
		strSqlParams[10][1] = IDAOConstant.BOOLEAN_DATATYPE;
		strSqlParams[10][2] = active;
		myLog.debug(" parameter 11 :: " + active);

		strSqlParams[11][0] = "12";
		strSqlParams[11][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[11][2] = userLogin;
		myLog.debug(" parameter 12 :: " + userLogin);

		strSqlParams[12][0] = "13";
		strSqlParams[12][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[12][2] = parsePropertiesValuesString.toString();
		myLog.debug(" parameter 13 :: " + parsePropertiesValuesString);

		strSqlParams[13][0] = "14";
		strSqlParams[13][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[13][2] = lastModifiedDate;
		myLog.debug(" parameter 14 :: " + lastModifiedDate);

		strSqlParams[14][0] = "15";
		strSqlParams[14][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[14][2] = itemCategory;
		myLog.debug(" parameter 15 :: " + itemCategory);

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug("save property values :: Resultset got ::" + responseMap);

		Long productPropertiesId = null;
		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {

				HashMap<String, Object> resultSetMap = responseMap.get(i);
				handleAndThrowException(resultSetMap);

				if (resultSetMap.get("p_last_inserted_id") != null)
					productPropertiesId = Long.valueOf(resultSetMap.get("p_last_inserted_id").toString());
				productPropertiesOprRet.getProductPropertiesRecord().setId(productPropertiesId);

				setAuditAttributes(productPropertiesOprRet.getProductPropertiesRecord(), resultSetMap);

			}
		}

		saveParameters = getProductPropertiesValues(productPropertiesId);
		productPropertiesOprRet.getProductPropertiesRecord().setProductPropertiesValuesList(
				saveParameters.getProductPropertiesRecord().getProductPropertiesValuesList());

		return productPropertiesOprRet;
	}

	public List<PropertyDVO> getSuggestedPropertiesBasedOnName(String propertyName) throws FrameworkException,
			BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ProductSkuPropertySqlTemplate.GET_SUGGESTED_PRODUCTS_PROPERTIES);

		Object strSqlParams[][] = new Object[1][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[0][2] = propertyName + "%";
		myLog.debug(" parameter 1 :: " + propertyName);

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" getSuggestedPropertiesBasedOnName :: Resultset got ::" + responseMap);

		List<PropertyDVO> productPropertiesList = new ArrayList<PropertyDVO>();
		if (responseMap.size() > 0) {

			for (int i = 0; i < responseMap.size(); i++) {

				HashMap<String, Object> resultSetMap = responseMap.get(i);

				PropertyDVO productPropertiesRecord = new PropertyDVO();
				if (resultSetMap.get("product_properties_id") != null)
					productPropertiesRecord.setId(Long.valueOf(resultSetMap.get("product_properties_id").toString()));
				productPropertiesRecord.setCode((String) resultSetMap.get("product_property_code"));
				productPropertiesRecord.setName((String) resultSetMap.get("product_property_name"));
				productPropertiesRecord.setDescription((String) resultSetMap.get("product_property_description"));

				/*
				 * if (resultSetMap.get("value_type") != null)
				 * productPropertiesRecord.getValueType().setParameterID(
				 * Integer.valueOf(resultSetMap.get("value_type").toString()));
				 * if (resultSetMap.get("value_type_seq_no") != null)
				 * productPropertiesRecord.getValueType().setSequenceNumber(
				 * Integer
				 * .valueOf(resultSetMap.get("value_type_seq_no").toString()));
				 * if (resultSetMap.get("is_mandatory") != null)
				 * productPropertiesRecord.setMandatory((Boolean)
				 * resultSetMap.get("is_mandatory"));
				 * productPropertiesRecord.getUomRecord().setCode((String)
				 * resultSetMap.get("uom_code")); //
				 * productPropertiesRecord.getUomRecord().setUomCategoryCode( //
				 * (String) resultSetMap.get("uom_category_code"));
				 * 
				 * if (resultSetMap.get("assortment_planning") != null)
				 * productPropertiesRecord.setAssortmentPlanning((Boolean)
				 * resultSetMap.get("assortment_planning")); if
				 * (resultSetMap.get("sku_code_generation") != null)
				 * productPropertiesRecord.setSkuCodeGeneration((Boolean)
				 * resultSetMap.get("sku_code_generation")); if
				 * (resultSetMap.get("is_active") != null)
				 * productPropertiesRecord.setActive(!((Boolean)
				 * resultSetMap.get("is_active"))); if
				 * (resultSetMap.get("category_id") != null)
				 * productPropertiesRecord.getItemCategoryRecord().setId(
				 * Long.valueOf(resultSetMap.get("category_id").toString()));
				 */

				productPropertiesList.add(productPropertiesRecord);

			}
		}

		return productPropertiesList;
	}

	public Integer getMaxSequenceNumber() throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		Integer maxSequenceNumber = 1;

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ProductPropertySqlTemplate.GET_MAX_SEQUENCE_NUMBER);

		Object strSqlParams[][] = new Object[0][3];

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" In Property BC :: getMaxSequenceNumber :: Resultset got ::" + responseMap);

		if (responseMap.size() > 0) {

			for (int i = 0; i < responseMap.size(); i++) {

				HashMap<String, Object> resultSetMap = responseMap.get(i);

				if (resultSetMap.get("seq_no") != null)
					maxSequenceNumber = Integer.valueOf(resultSetMap.get("seq_no").toString());

			}
		}
		return maxSequenceNumber;
	}

	public List<ProductPropertiesDVO> getSuggestedPropertiesWithValuesList(ProductPropertiesDVO productPropertiesDVO)
			throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Property BC :: getSuggestedPropertiesWithValuesList starts ");

		String propertyName = productPropertiesDVO.getName();
		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT,
				ProductPropertySqlTemplate.GET_SUGGESTED_PRODUCTS_PROPERTIES_WITH_VALUES_LIST);

		Object strSqlParams[][] = new Object[1][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[0][2] = propertyName + "%";
		myLog.debug(" parameter 1 :: " + propertyName);

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" getSuggestedPropertiesWithValuesList :: Resultset got ::" + responseMap);

		List<ProductPropertiesDVO> productPropertiesList = new ArrayList<ProductPropertiesDVO>();
		if (responseMap.size() > 0) {

			for (int i = 0; i < responseMap.size(); i++) {

				HashMap<String, Object> resultSetMap = responseMap.get(i);
				Integer valueTypeSequenceNumber = 0;

				ProductPropertiesDVO productPropertiesRecord = new ProductPropertiesDVO();
				if (resultSetMap.get("product_properties_id") != null)
					productPropertiesRecord.setId(Long.valueOf(resultSetMap.get("product_properties_id").toString()));
				productPropertiesRecord.setName((String) resultSetMap.get("product_property_name"));
				productPropertiesRecord.setDescription((String) resultSetMap.get("product_property_description"));

				if (resultSetMap.get("value_type_seq_no") != null)
					valueTypeSequenceNumber = Integer.valueOf(resultSetMap.get("value_type_seq_no").toString());
				productPropertiesRecord.getValueType().setSequenceNumber(valueTypeSequenceNumber);

				if (resultSetMap.get("is_active") != null)
					productPropertiesRecord.setActive(!((Boolean) resultSetMap.get("is_active")));

				if (CommonConstant.ParameterSequenceNumber.TWO.equals(valueTypeSequenceNumber)) {
					String productPropertyValues = (String) resultSetMap.get("product_property_values");
					if (productPropertyValues != null) {
						String[] propertyValues = productPropertyValues.split(",");
						for (String value : propertyValues) {
							if (value != null && value.trim().length() > 0) {

								ProductPropertiesValuesDVO productPropertiesValuesDVO = new ProductPropertiesValuesDVO();
								productPropertiesValuesDVO.setPropertyValue(value);
								productPropertiesRecord.getProductPropertiesValuesList()
										.add(productPropertiesValuesDVO);
							}
						}
					}

				} else if (CommonConstant.ParameterSequenceNumber.THREE.equals(valueTypeSequenceNumber)) {

					// suggestion box list
					String productPropertyValues = (String) resultSetMap.get("product_property_values");
					if (productPropertyValues != null) {
						String[] propertyValues = productPropertyValues.split(",");
						for (String value : propertyValues) {
							if (value != null && value.trim().length() > 0) {

								ProductPropertiesValuesDVO productPropertiesValuesDVO = new ProductPropertiesValuesDVO();
								productPropertiesValuesDVO.setCode(value);
								productPropertiesValuesDVO.setName(value);
								productPropertiesRecord.getProductPropertiesValuesList()
										.add(productPropertiesValuesDVO);
							}
						}
					}
				}

				productPropertiesList.add(productPropertiesRecord);
			}
		}
		return productPropertiesList;
	}
}
