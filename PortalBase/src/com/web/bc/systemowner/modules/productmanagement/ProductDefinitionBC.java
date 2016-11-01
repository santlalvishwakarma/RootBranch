package com.web.bc.systemowner.modules.productmanagement;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.web.common.constants.CommonConstant;
import com.web.common.dvo.common.CatalogDVO;
import com.web.common.dvo.opr.systemowner.ProductOpr;
import com.web.common.dvo.systemowner.CategoryDVO;
import com.web.common.dvo.systemowner.HierarchyDVO;
import com.web.common.dvo.systemowner.ProductDVO;
import com.web.common.dvo.systemowner.ProductSkuDVO;
import com.web.common.dvo.systemowner.UomDVO;
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
public class ProductDefinitionBC extends BackingClass {

	public ProductOpr executeSearch(ProductOpr productOpr) throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition BC :: executeSearch starts ");
		ProductOpr productOprRet = new ProductOpr();

		ProductDVO productRecord = productOpr.getProductRecord();

		StringBuffer parseCategoryNameString = new StringBuffer();
		String productCode = productRecord.getCode();
		String productName = productRecord.getName();
		String productDescription = productRecord.getDescription();
		String skuCode = productRecord.getProductSkuRecord().getCode();
		String skuDescription = productRecord.getProductSkuRecord().getDescription();
		StringBuffer parseProductHierarchyString = new StringBuffer();
		StringBuffer parseProductPropertiesString = new StringBuffer();
		String statusCode = productRecord.getStatusRecord().getCode();

		if (!productOpr.getProductHierarchyList().isEmpty()) {
			for (HierarchyDVO productHierarchy : productOpr.getProductHierarchyList()) {
				parseProductHierarchyString.append(productHierarchy.getId());
				parseProductHierarchyString.append("~");
			}

		}

		if (!productOpr.getProductCategoryList().isEmpty()) {
			for (CategoryDVO productCategoryDVO : productOpr.getProductCategoryList()) {
				Long categoryId = productCategoryDVO.getId();
				if (categoryId != null)
					parseCategoryNameString.append(categoryId);
				parseCategoryNameString.append("~");
			}
		}

		if (parseProductHierarchyString != null && parseProductHierarchyString.length() > 1) {
			// this is to remove the last ~ sign
			parseProductHierarchyString.deleteCharAt(parseProductHierarchyString.length() - 1);
		}

		if (parseProductPropertiesString != null && parseProductPropertiesString.length() > 1) {
			// this is to remove the last ; sign
			parseProductPropertiesString.deleteCharAt(parseProductPropertiesString.length() - 1);
		}

		if (parseCategoryNameString != null && parseCategoryNameString.length() > 1) {
			// this is to remove the last ; sign
			parseCategoryNameString.deleteCharAt(parseCategoryNameString.length() - 1);
		}

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ProductDefinitionSqlTemplate.SEARCH_PRODUCTS_DETAILS);

		Object strSqlParams[][] = new Object[9][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.STRING_DATATYPE;
		if (parseCategoryNameString.length() > 0)
			strSqlParams[0][2] = parseCategoryNameString.toString();
		else
			strSqlParams[0][2] = null;
		myLog.debug(" parameter 1 :: " + parseCategoryNameString);

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[1][2] = productCode;
		myLog.debug(" parameter 2 :: " + productCode);

		strSqlParams[2][0] = "3";
		strSqlParams[2][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[2][2] = productName;
		myLog.debug(" parameter 3 :: " + productName);

		strSqlParams[3][0] = "4";
		strSqlParams[3][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[3][2] = productDescription;
		myLog.debug(" parameter 4 :: " + productDescription);

		strSqlParams[4][0] = "5";
		strSqlParams[4][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[4][2] = skuCode;
		myLog.debug(" parameter 5 :: " + skuCode);

		strSqlParams[5][0] = "6";
		strSqlParams[5][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[5][2] = skuDescription;
		myLog.debug(" parameter 6 :: " + skuDescription);

		strSqlParams[6][0] = "7";
		strSqlParams[6][1] = IDAOConstant.STRING_DATATYPE;
		if (parseProductHierarchyString.length() > 0)
			strSqlParams[6][2] = parseProductHierarchyString.toString();
		else
			strSqlParams[6][2] = null;
		myLog.debug(" parameter 7 :: " + strSqlParams[6][2]);

		strSqlParams[7][0] = "8";
		strSqlParams[7][1] = IDAOConstant.STRING_DATATYPE;

		if (parseProductPropertiesString.length() > 0)
			strSqlParams[7][2] = parseProductPropertiesString.toString();
		else
			strSqlParams[7][2] = null;
		myLog.debug(" parameter 8 :: " + strSqlParams[7][2]);

		strSqlParams[8][0] = "9";
		strSqlParams[8][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[8][2] = statusCode;
		myLog.debug(" parameter 9 :: " + statusCode);

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" Product Definition executeSearch :: Resultset got ::" + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {

				HashMap<String, Object> resultSetMap = responseMap.get(i);
				handleAndThrowException(resultSetMap);

				ProductDVO productDVO = new ProductDVO();
				if (resultSetMap.get("product_id") != null)
					productDVO.setId(Long.valueOf(resultSetMap.get("product_id").toString()));

				productDVO.setCode((String) resultSetMap.get("product_code"));
				productDVO.setName((String) resultSetMap.get("product_name"));
				productDVO.setDescription((String) resultSetMap.get("product_description"));
				productDVO.getStatusRecord().setCode((String) resultSetMap.get("status_code"));
				productDVO.getStatusRecord().setName((String) resultSetMap.get("status_name"));
				if (resultSetMap.get("is_active") != null)
					productDVO.setActive((Boolean) resultSetMap.get("is_active"));

				if (resultSetMap.get("product_sku_id") != null)
					productDVO.getProductSkuRecord().setId(Long.valueOf(resultSetMap.get("product_sku_id").toString()));
				productDVO.getProductSkuRecord().setCode((String) resultSetMap.get("sku_code"));
				productDVO.getProductSkuRecord().setDescription((String) resultSetMap.get("sku_description"));
				productDVO.getProductSkuRecord().getDefaultImageRecord()
						.setThumbnailImageURL((String) resultSetMap.get("thumbnail_image_url"));
				productDVO.getProductSkuRecord().getDefaultImageRecord()
						.setZoomImageURL((String) resultSetMap.get("zoom_image_url"));

				setAuditAttributes(productDVO, resultSetMap);
				productOprRet.getProductDVOList().add(productDVO);
			}
		} else {
			throw new BusinessException("no_data_from_db_excep_msg");
		}
		return productOprRet;
	}

	public ProductOpr executeSaveProductSKUDetails(ProductOpr productOpr) throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition BC :: executeSaveProductSKUDetails starts ");

		String saveFlag = (String) productOpr.getApplicationFlags().getApplicationFlagMap().get("SAVE_FLAG");
		myLog.debug(" saveFlag ---> " + saveFlag);

		if ("SAVE_PRODUCT".equals(saveFlag)) {
			Long productId = executeSaveProductDetails(productOpr);
			productOpr.getProductRecord().setId(productId);

		} else {
			Long skuId = executeSaveSKUDetails(productOpr);
			productOpr.getProductRecord().getProductSkuRecord().setId(skuId);
		}

		return getProductDetails(productOpr);
	}

	public ProductOpr executeSaveProductDetails(ProductOpr productOpr) throws FrameworkException, BusinessException {

		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition BC :: executeSaveProductDetails starts ");
		ProductDVO productRecord = productOpr.getProductRecord();

		Long productId = productRecord.getId();
		String productCode = productRecord.getCode();
		String productName = productRecord.getName();
		String productDesc = productRecord.getDescription();
		Boolean active = false;
		if (productRecord.getActive() != null)
			active = !(productRecord.getActive());
		String userLogin = productRecord.getUserLogin();
		String lastModifiedDate = null;
		if (productRecord.getAuditAttributes().getLastModifiedDate() != null)
			lastModifiedDate = productRecord.getAuditAttributes().getLastModifiedDate().toString();

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ProductDefinitionSqlTemplate.SAVE_PRODUCT_DETAILS);

		Object strSqlParams[][] = new Object[7][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[0][2] = productId;
		myLog.debug(" parameter 1 productId:: " + productId);

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[1][2] = productCode;
		myLog.debug(" parameter 2 productCode:: " + productCode);

		strSqlParams[2][0] = "3";
		strSqlParams[2][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[2][2] = productName;
		myLog.debug(" parameter 3 productName:: " + productName);

		strSqlParams[3][0] = "4";
		strSqlParams[3][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[3][2] = productDesc;
		myLog.debug(" parameter 4 productDesc:: " + productDesc);

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

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" Product Definition executeSaveProductDetails :: Resultset got ::" + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {

				HashMap<String, Object> resultSetMap = responseMap.get(i);
				handleAndThrowException(resultSetMap);
				if (resultSetMap.get("product_id") != null)
					productId = Long.valueOf(resultSetMap.get("product_id").toString());
				productOpr.getProductRecord().setId(productId);

			}
		}
		return getProductDetails(productOpr);
	}

	public Long executeSaveSKUDetails(ProductOpr productOpr) throws FrameworkException, BusinessException {

		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition BC :: executeSaveSKUDetails starts ");
		ProductSkuDVO productSkuRecord = productOpr.getProductRecord().getProductSkuRecord();
		ProductDVO productRecord = productOpr.getProductRecord();

		Long skuId = productSkuRecord.getId();
		Long productId = productOpr.getProductRecord().getId();
		String skuName = productSkuRecord.getName();
		Boolean active = false;
		if (productSkuRecord.getActive() != null)
			active = !(productSkuRecord.getActive());
		Boolean masterSKU = productSkuRecord.getMasterSKU();
		String skuComments = productSkuRecord.getSkuComments();
		String userLogin = productRecord.getUserLogin();
		String lastModifiedDate = null;
		if (productRecord.getAuditAttributes().getLastModifiedDate() != null)
			lastModifiedDate = productRecord.getAuditAttributes().getLastModifiedDate().toString();

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ProductDefinitionSqlTemplate.SAVE_SKU_DETAILS);

		Object strSqlParams[][] = new Object[8][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[0][2] = skuId;
		myLog.debug(" parameter 1 :: " + skuId);

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[1][2] = productId;
		myLog.debug(" parameter 2 :: " + productId);

		strSqlParams[2][0] = "3";
		strSqlParams[2][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[2][2] = skuName;
		myLog.debug(" parameter 3 :: " + skuName);

		strSqlParams[3][0] = "4";
		strSqlParams[3][1] = IDAOConstant.BOOLEAN_DATATYPE;
		strSqlParams[3][2] = active;
		myLog.debug(" parameter 4 :: " + active);

		strSqlParams[4][0] = "5";
		strSqlParams[4][1] = IDAOConstant.BOOLEAN_DATATYPE;
		strSqlParams[4][2] = masterSKU;
		myLog.debug(" parameter 5 :: " + masterSKU);

		strSqlParams[5][0] = "6";
		strSqlParams[5][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[5][2] = userLogin;
		myLog.debug(" parameter 6 :: " + userLogin);

		strSqlParams[6][0] = "7";
		strSqlParams[6][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[6][2] = lastModifiedDate;
		myLog.debug(" parameter 7 :: " + lastModifiedDate);

		strSqlParams[7][0] = "8";
		strSqlParams[7][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[7][2] = skuComments;
		myLog.debug(" parameter 8 :: " + skuComments);

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" Product Definition executeSaveSKUDetails :: Resultset got ::" + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {

				HashMap<String, Object> resultSetMap = responseMap.get(i);
				handleAndThrowException(resultSetMap);
				if (resultSetMap.get("product_sku_id") != null)
					skuId = Long.valueOf(resultSetMap.get("product_sku_id").toString());

			}
		}
		return skuId;
	}

	public ProductOpr getProductDetails(ProductOpr productOpr) throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition BC :: getProductDetails starts ");
		ProductOpr productOprRet = new ProductOpr();

		ProductDVO productRecord = productOpr.getProductRecord();

		Long productId = productRecord.getId();
		Long skuId = productRecord.getProductSkuRecord().getId();

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ProductDefinitionSqlTemplate.GET_PRODUCTS_DETAILS);

		Object strSqlParams[][] = new Object[2][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[0][2] = productId;
		myLog.debug(" parameter 1 :: " + productId);

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[1][2] = skuId;
		myLog.debug(" parameter 2 :: " + skuId);

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);

		HashMap<Integer, HashMap<String, Object>> headerResponseMap = daoResult.getMultipleResultSet().get("HDR");
		HashMap<Integer, HashMap<String, Object>> detailResponseMap = daoResult.getMultipleResultSet().get("DTL");
		myLog.debug(" Product Definition getProductDetails :: Resultset got header ::" + headerResponseMap);
		myLog.debug(" Product Definition getProductDetails :: Resultset got uom :: " + detailResponseMap);

		if (headerResponseMap != null && headerResponseMap.size() > 0) {
			int size = headerResponseMap.size();
			for (int i = 0; i < size; i++) {

				HashMap<String, Object> resultSetMap = headerResponseMap.get(i);

				ProductDVO productDVO = new ProductDVO();

				if (resultSetMap.get("product_id") != null)
					productDVO.setId(Long.valueOf(resultSetMap.get("product_id").toString()));

				productDVO.setCode((String) resultSetMap.get("product_code"));
				productDVO.setName((String) resultSetMap.get("product_name"));
				productDVO.setDescription((String) resultSetMap.get("product_description"));
				if (resultSetMap.get("is_active") != null)
					productDVO.setActive(!((Boolean) resultSetMap.get("is_active")));
				// productDVO.getStatusRecord().setCode((String)
				// resultSetMap.get("status_code"));
				// productDVO.getStatusRecord().setName((String)
				// resultSetMap.get("status_name"));

				// sku data
				if (resultSetMap.get("product_sku_id") != null)
					productDVO.getProductSkuRecord().setId(Long.valueOf(resultSetMap.get("product_sku_id").toString()));
				productDVO.getProductSkuRecord().setCode((String) resultSetMap.get("sku_code"));
				productDVO.getProductSkuRecord().setName((String) resultSetMap.get("sku_name"));
				productDVO.getProductSkuRecord().setDescription((String) resultSetMap.get("sku_description"));

				productDVO.getProductSkuRecord().getStatusRecord()
						.setCode((String) resultSetMap.get("sku_status_code"));
				productDVO.getProductSkuRecord().getStatusRecord()
						.setName((String) resultSetMap.get("sku_status_name"));
				if (resultSetMap.get("sku_is_active") != null)
					productDVO.getProductSkuRecord().setActive(!((Boolean) resultSetMap.get("sku_is_active")));

				String imageUrlString = (String) resultSetMap.get("image_url");
				myLog.debug(" imageUrlString :: " + imageUrlString);

				setAuditAttributes(productDVO, resultSetMap);
				productOprRet.setProductRecord(productDVO);

			}
		}

		if (detailResponseMap != null && detailResponseMap.size() > 0) {
			int size = detailResponseMap.size();
			for (int i = 0; i < size; i++) {

				HashMap<String, Object> resultSetMap = detailResponseMap.get(i);

				ProductSkuDVO productSkuRecord = new ProductSkuDVO();

				if (resultSetMap.get("product_sku_id") != null)
					productSkuRecord.setId(Long.valueOf(resultSetMap.get("product_sku_id").toString()));
				productSkuRecord.setCode((String) resultSetMap.get("sku_code"));
				productSkuRecord.setName((String) resultSetMap.get("sku_name"));
				productSkuRecord.setDescription((String) resultSetMap.get("sku_description"));
				productSkuRecord.setSkuPropertyText((String) resultSetMap.get("sku_property_text"));
				productSkuRecord.setSkuSEOTitle((String) resultSetMap.get("seo_title"));
				productSkuRecord.setSkuSEOKeyword((String) resultSetMap.get("seo_keyword"));
				productSkuRecord.setSkuSEODescription((String) resultSetMap.get("seo_description"));
				productSkuRecord.getDefaultImageRecord().setThumbnailImageURL(
						(String) resultSetMap.get("default_thumbnail_image_url"));
				productSkuRecord.getDefaultImageRecord().setZoomImageURL(
						(String) resultSetMap.get("default_zoom_image_url"));
				if (resultSetMap.get("base_price") != null)
					productSkuRecord.setBasePrice(Float.valueOf(resultSetMap.get("base_price").toString()));
				if (resultSetMap.get("discount_amount") != null)
					productSkuRecord.setDiscountAmount(Float.valueOf(resultSetMap.get("discount_amount").toString()));
				if (resultSetMap.get("discount_percent") != null)
					productSkuRecord.setDiscountPercent(Float.valueOf(resultSetMap.get("discount_percent").toString()));
				if (resultSetMap.get("final_base_price") != null)
					productSkuRecord.setFinalBasePrice(Float.valueOf(resultSetMap.get("final_base_price").toString()));

				// productSkuRecord.getStatusRecord().setCode((String)
				// resultSetMap.get("sku_status_code"));
				// productSkuRecord.getStatusRecord().setName((String)
				// resultSetMap.get("sku_status_name"));
				if (resultSetMap.get("sku_is_active") != null)
					productSkuRecord.setActive(!((Boolean) resultSetMap.get("sku_is_active")));

				if (resultSetMap.get("default_sku") != null)
					productSkuRecord.setDefaultSku(!((Boolean) resultSetMap.get("default_sku")));

				productOprRet.getProductRecord().getProductSkuList().add(productSkuRecord);

			}
		}

		return productOprRet;
	}

	public ArrayList<Object> getSuggestedProductsList(ProductDVO productDVO) throws FrameworkException,
			BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition BC :: getSuggestedProductsList starts ");

		String code = productDVO.getCode();
		String name = productDVO.getName();
		Boolean isActive = productDVO.getActive();
		// String statusCode = (String)
		// productDVO.getApplicationFlags().getApplicationFlagMap()
		// .get("STATUS_NEW_APPROVED");
		// String approved = (String)
		// productDVO.getApplicationFlags().getApplicationFlagMap().get("STATUS_APPROVED");
		myLog.debug(" parameter 1 :: " + code);
		myLog.debug(" parameter 2 :: " + name);
		myLog.debug(" parameter 3 :: " + isActive);
		// myLog.debug(" parameter 4 :: " + statusCode);
		// myLog.debug(" parameter 5 :: " + approved);

		StringBuffer dynamicWhere = new StringBuffer();

		int parameterCount = 0;
		// setup the dynamic where clause to include all entered params
		if (code != null && code.trim().length() > 0) {
			code = code.trim().concat("%");
			dynamicWhere.append(" ph.product_code LIKE '" + code + "'");
			parameterCount++;
		}

		if (name != null && name.trim().length() > 0) {
			name = name.trim().concat("%");
			if (parameterCount > 0) {
				dynamicWhere.append(" AND ph.product_name LIKE '" + name + "'");
			} else {
				dynamicWhere.append(" ph.product_name LIKE '" + name + "'");
			}
			parameterCount++;
		}

		if (isActive == null || !isActive) {
			if (parameterCount > 0) {
				dynamicWhere.append(" AND is_active = 1");
			} else {
				dynamicWhere.append(" is_active = 1");
			}
			parameterCount += 1;
		}

		// if (statusCode != null && statusCode.equals("STATUS_NEW_APPROVED")) {
		// if (parameterCount > 0) {
		// dynamicWhere.append(" AND ph.status_code IN ('" +
		// CommonConstant.StatusCodes.NEW + "','"
		// + CommonConstant.StatusCodes.APPROVED + "')");
		// } else {
		// dynamicWhere.append(" ph.status_code IN ('" +
		// CommonConstant.StatusCodes.NEW + "','"
		// + CommonConstant.StatusCodes.APPROVED + "')");
		// }
		// parameterCount++;
		// }
		//
		// if (approved != null && approved.equals("STATUS_APPROVED")) {
		// if (parameterCount > 0) {
		// dynamicWhere.append(" AND ph.status_code IN ('" +
		// CommonConstant.StatusCodes.APPROVED + "')");
		// } else {
		// dynamicWhere.append(" ph.status_code IN ('" +
		// CommonConstant.StatusCodes.APPROVED + "')");
		// }
		// parameterCount++;
		// }

		// to get all data
		if (parameterCount == 0) {
			dynamicWhere.append(" 1 = 1 ");
		}
		dynamicWhere.append(" ORDER BY ph.product_name;");
		myLog.debug("dynamicWhere ::::: " + dynamicWhere);

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ProductDefinitionSqlTemplate.GET_SUGGESTED_PRODUCTS_LIST);

		Object strSqlParams[][] = new Object[0][0];

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, dynamicWhere.toString());
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" getSuggestedProductsList :: Resultset got ::" + responseMap);

		ArrayList<Object> productList = new ArrayList<Object>();
		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {
				HashMap<String, Object> resultSetMap = responseMap.get(i);
				ProductDVO productRecord = new ProductDVO();
				if (resultSetMap.get("product_id") != null)
					productRecord.setId(Long.valueOf(resultSetMap.get("product_id").toString()));

				productRecord.setCode((String) resultSetMap.get("product_code"));
				productRecord.setName((String) resultSetMap.get("product_name"));
				productRecord.setDescription((String) resultSetMap.get("product_description"));
				productList.add(productRecord);
			}
		}
		return productList;
	}

	public ArrayList<Object> getSuggestedSKUList(ProductSkuDVO productSkuDVO) throws FrameworkException,
			BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition BC :: getSuggestedSKUList starts ");

		String code = productSkuDVO.getCode();
		String name = productSkuDVO.getName();
		Long productId = productSkuDVO.getMappedProductRecord().getId();
		Boolean isActive = productSkuDVO.getActive();
		String statusCode = (String) productSkuDVO.getApplicationFlags().getApplicationFlagMap()
				.get("STATUS_NEW_APPROVED");
		String description = productSkuDVO.getDescription();
		String skuStatusCode = productSkuDVO.getStatusRecord().getCode();
		Boolean isMaster = productSkuDVO.getMasterSKU();
		myLog.debug(" parameter 1 :: " + code);
		myLog.debug(" parameter 2 :: " + name);
		myLog.debug(" parameter 3 :: " + productId);
		myLog.debug(" parameter 4 :: " + isActive);
		myLog.debug(" parameter 5 :: " + statusCode);
		myLog.debug(" parameter 6 :: " + skuStatusCode);
		myLog.debug(" parameter 7 :: " + description);
		myLog.debug(" parameter 8 :: " + isMaster);

		StringBuffer dynamicWhere = new StringBuffer();

		int parameterCount = 0;
		// setup the dynamic where clause to include all entered params
		if (code != null && code.trim().length() > 0) {
			code = code.trim().concat("%");
			dynamicWhere.append(" psh.sku_code LIKE '" + code + "'");
			parameterCount++;
		}

		if (name != null && name.trim().length() > 0) {
			name = name.trim().concat("%");
			if (parameterCount > 0) {
				dynamicWhere.append(" AND psh.sku_name LIKE '" + name + "'");
			} else {
				dynamicWhere.append(" psh.sku_name LIKE '" + name + "'");
			}
			parameterCount++;
		}

		if (productId != null) {
			if (parameterCount > 0) {
				dynamicWhere.append(" AND psh.product_id = " + productId);
			} else {
				dynamicWhere.append(" psh.product_id = " + productId);
			}
			parameterCount++;
		}

		if (isActive == null || !isActive) {
			if (parameterCount > 0) {
				dynamicWhere.append(" AND psh.is_active = 1");
			} else {
				dynamicWhere.append(" psh.is_active = 1");
			}
			parameterCount += 1;
		}

		if (statusCode != null && statusCode.equals("STATUS_NEW_APPROVED")) {
			if (parameterCount > 0) {
				dynamicWhere.append(" AND psh.status_code IN ('" + CommonConstant.StatusCodes.NEW + "','"
						+ CommonConstant.StatusCodes.APPROVED + "')");
			} else {
				dynamicWhere.append(" psh.status_code IN ('" + CommonConstant.StatusCodes.NEW + "','"
						+ CommonConstant.StatusCodes.APPROVED + "')");
			}
			parameterCount++;
		}

		if (skuStatusCode != null && skuStatusCode.trim().length() > 0) {
			if (parameterCount > 0) {
				dynamicWhere.append(" AND psh.status_code = '" + skuStatusCode + "'");
			} else {
				dynamicWhere.append(" psh.status_code = '" + skuStatusCode + "'");
			}
			parameterCount++;
		}

		if (description != null && description.trim().length() > 0) {
			description = description.trim().concat("%");
			if (parameterCount > 0) {
				dynamicWhere.append(" AND psh.sku_description LIKE '" + description + "'");
			} else {
				dynamicWhere.append(" psh.sku_description LIKE '" + description + "'");
			}
			parameterCount++;
		}

		if (isMaster != null && isMaster) {
			if (parameterCount > 0) {
				dynamicWhere.append(" AND psh.is_master = 1");
			} else {
				dynamicWhere.append(" psh.is_master = 1");
			}
			parameterCount += 1;
		}

		// to get all data
		if (parameterCount == 0) {
			dynamicWhere.append(" 1 = 1 ");
		}
		dynamicWhere.append(" ORDER BY psh.sku_description ");
		dynamicWhere.append(putLimit(productSkuDVO));

		myLog.debug("dynamicWhere ::::: " + dynamicWhere);

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ProductDefinitionSqlTemplate.GET_SUGGESTED_SKU_LIST);

		Object strSqlParams[][] = new Object[0][0];

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, dynamicWhere.toString());
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" getSuggestedSKUList :: Resultset got ::" + responseMap);

		ArrayList<Object> productSkuList = new ArrayList<Object>();
		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {
				HashMap<String, Object> resultSetMap = responseMap.get(i);
				ProductSkuDVO productSkuRecord = new ProductSkuDVO();
				if (resultSetMap.get("product_sku_id") != null)
					productSkuRecord.setId(Long.valueOf(resultSetMap.get("product_sku_id").toString()));

				productSkuRecord.setCode((String) resultSetMap.get("sku_code"));
				productSkuRecord.setName((String) resultSetMap.get("sku_name"));
				productSkuRecord.setDescription((String) resultSetMap.get("sku_description"));
				if (resultSetMap.get("sku_version") != null)
					productSkuRecord.setSkuVersion(Integer.valueOf(resultSetMap.get("sku_version").toString()));
				if (resultSetMap.get("is_active") != null)
					productSkuRecord.setActive((Boolean) resultSetMap.get("is_active"));
				if (resultSetMap.get("product_id") != null)
					productSkuRecord.getMappedProductRecord().setId(
							Long.valueOf(resultSetMap.get("product_id").toString()));
				productSkuRecord.getStatusRecord().setCode((String) resultSetMap.get("status_code"));

				productSkuList.add(productSkuRecord);
			}
		}
		return productSkuList;
	}

	public ProductOpr getHierarchiesMappingList(ProductOpr productOpr) throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition BC :: getHierarchiesMappingList starts ");
		ProductOpr productOprRet = new ProductOpr();

		Long productId = productOpr.getProductRecord().getId();

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ProductDefinitionSqlTemplate.GET_HIERARCHIES_MAPPING_LIST);

		Object strSqlParams[][] = new Object[1][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[0][2] = productId;
		myLog.debug(" parameter 1 :: " + productId);

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" Product Definition getHierarchiesMappingList :: Resultset got ::" + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {

				HashMap<String, Object> resultSetMap = responseMap.get(i);

				ProductHierarchyMappingDVO productHierarchyMappingRecord = new ProductHierarchyMappingDVO();
				if (resultSetMap.get("product_hierarchy_mapping_id") != null)
					productHierarchyMappingRecord.setId(Long.valueOf(resultSetMap.get("product_hierarchy_mapping_id")
							.toString()));

				// productHierarchyMappingRecord.setCode((String)
				// resultSetMap.get("product_hierarchy_code"));
				// productHierarchyMappingRecord.setName((String)
				// resultSetMap.get("product_hierarchy_name"));
				// productHierarchyMappingRecord
				// .setDescription((String)
				// resultSetMap.get("product_hierarchy_description"));
				if (resultSetMap.get("fetch_properties") != null)
					productHierarchyMappingRecord.setFetchProperties((Boolean) resultSetMap.get("fetch_properties"));

				if (resultSetMap.get("product_hierarchy_id") != null)
					productHierarchyMappingRecord.getProductHierarchyRecord().setId(
							Long.valueOf(resultSetMap.get("product_hierarchy_id").toString()));
				productHierarchyMappingRecord.getProductHierarchyRecord().setCode(
						(String) resultSetMap.get("product_hierarchy_code"));
				productHierarchyMappingRecord.getProductHierarchyRecord().setName(
						(String) resultSetMap.get("product_hierarchy_name"));
				productHierarchyMappingRecord.getProductHierarchyRecord().setDescription(
						(String) resultSetMap.get("product_hierarchy_description"));
				if (resultSetMap.get("properties") != null)
					productOprRet.getIconProductSKURecord().setMapProperties((Boolean) resultSetMap.get("properties"));

				productOprRet.getProductRecord().getProductHierarchyMappingList().add(productHierarchyMappingRecord);
			}
		}
		return productOprRet;
	}

	public ProductOpr saveHierarchiesMappingList(ProductOpr productOpr) throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition BC :: saveHierarchiesMappingList starts ");
		ProductOpr productOprRet = new ProductOpr();

		ProductDVO productRecord = productOpr.getProductRecord();
		Long productId = productRecord.getId();
		StringBuffer parseProductHierarchyString = new StringBuffer();
		Boolean modifyHierarchy = productOpr.getProductRecord().getModifyProductSKURecord().getModifyHierarchy();
		String userLogin = productRecord.getUserLogin();
		String lastModifiedDate = null;
		if (productRecord.getAuditAttributes().getLastModifiedDate() != null)
			lastModifiedDate = productRecord.getAuditAttributes().getLastModifiedDate().toString();

		if (!productOpr.getProductRecord().getProductHierarchyMappingList().isEmpty()) {
			for (ProductHierarchyMappingDVO productHierarchyRecord : productOpr.getProductRecord()
					.getProductHierarchyMappingList()) {
				Long productHierarchyMappingId = productHierarchyRecord.getId();
				Boolean recordDeleted = productHierarchyRecord.getOperationalAttributes().getRecordDeleted();

				if (recordDeleted != null)
					if ((productHierarchyMappingId != null && recordDeleted)
							|| (productHierarchyMappingId != null && !recordDeleted)
							|| (productHierarchyMappingId == null && !recordDeleted)) {

						Long hierarchyId = productHierarchyRecord.getProductHierarchyRecord().getId();
						Boolean fetchProperties = productHierarchyRecord.getFetchProperties();

						if (productHierarchyMappingId != null)
							parseProductHierarchyString.append(productHierarchyMappingId);
						else
							parseProductHierarchyString.append("");
						parseProductHierarchyString.append("~");

						if (hierarchyId != null)
							parseProductHierarchyString.append(hierarchyId);
						else
							parseProductHierarchyString.append("");
						parseProductHierarchyString.append("~");

						if (fetchProperties != null && fetchProperties)
							parseProductHierarchyString.append("1");
						else
							parseProductHierarchyString.append("0");
						parseProductHierarchyString.append("~");

						if (recordDeleted)
							parseProductHierarchyString.append("1");
						else
							parseProductHierarchyString.append("0");
						parseProductHierarchyString.append(";");
					}
			}
		}
		if (parseProductHierarchyString != null && parseProductHierarchyString.length() > 1) {
			// this is to remove the last ; sign
			parseProductHierarchyString.deleteCharAt(parseProductHierarchyString.length() - 1);
		}

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ProductDefinitionSqlTemplate.SAVE_HIERARCHY_MAPPING_LIST);

		Object strSqlParams[][] = new Object[5][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[0][2] = productId;
		myLog.debug(" parameter 1 :: " + productId);

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.STRING_DATATYPE;

		if (parseProductHierarchyString.length() > 0)
			strSqlParams[1][2] = parseProductHierarchyString.toString();
		else
			strSqlParams[1][2] = null;
		myLog.debug(" parameter 2 :: " + strSqlParams[1][2]);

		strSqlParams[2][0] = "3";
		strSqlParams[2][1] = IDAOConstant.BOOLEAN_DATATYPE;
		strSqlParams[2][2] = modifyHierarchy;
		myLog.debug(" parameter 3 :: " + modifyHierarchy);

		strSqlParams[3][0] = "4";
		strSqlParams[3][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[3][2] = userLogin;
		myLog.debug(" parameter 4 :: " + userLogin);

		strSqlParams[4][0] = "5";
		strSqlParams[4][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[4][2] = lastModifiedDate;
		myLog.debug(" parameter 5 :: " + lastModifiedDate);

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" Product Definition saveHierarchiesMappingList :: Resultset got ::" + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {

				HashMap<String, Object> resultSetMap = responseMap.get(i);
				handleAndThrowException(resultSetMap);

				setAuditAttributes(productOprRet.getProductRecord(), resultSetMap);

			}
		}
		productOpr = getHierarchiesMappingList(productOpr);
		productOprRet.getProductRecord().setProductHierarchyMappingList(
				productOpr.getProductRecord().getProductHierarchyMappingList());
		productOprRet.getIconProductSKURecord().setMapProperties(
				productOpr.getIconProductSKURecord().getMapProperties());

		return productOprRet;
	}

	public ProductOpr getPropertiesMappingList(ProductOpr productOpr) throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition BC :: getPropertiesMappingList starts ");
		ProductOpr productOprRet = new ProductOpr();

		Long productId = productOpr.getProductRecord().getId();
		Long skuId = productOpr.getProductRecord().getProductSkuRecord().getId();
		String saveFlag = (String) productOpr.getApplicationFlags().getApplicationFlagMap().get("SAVE_FLAG");
		myLog.debug(" saveFlag ---> " + saveFlag);

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ProductDefinitionSqlTemplate.GET_PROPERTIES_MAPPING_LIST);

		Object strSqlParams[][] = new Object[3][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[0][2] = productId;
		myLog.debug(" parameter 1 :: " + productId);

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[1][2] = skuId;
		myLog.debug(" parameter 2 :: " + skuId);

		strSqlParams[2][0] = "3";
		strSqlParams[2][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[2][2] = saveFlag;
		myLog.debug(" parameter 3 :: " + saveFlag);

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" Product Definition getPropertiesMappingList :: Resultset got ::" + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {

				HashMap<String, Object> resultSetMap = responseMap.get(i);
				Integer valueTypeSequenceNumber = 0;

				ProductPropertiesMappingDVO productPropertiesMappingRecord = new ProductPropertiesMappingDVO();
				if (resultSetMap.get("product_properties_mapping_id") != null)
					productPropertiesMappingRecord.setId(Long.valueOf(resultSetMap.get("product_properties_mapping_id")
							.toString()));

				if (resultSetMap.get("product_properties_id") != null)
					productPropertiesMappingRecord.getProductPropertiesRecord().setId(
							Long.valueOf(resultSetMap.get("product_properties_id").toString()));
				productPropertiesMappingRecord.getProductPropertiesRecord().setCode(
						(String) resultSetMap.get("product_property_code"));
				productPropertiesMappingRecord.getProductPropertiesRecord().setName(
						(String) resultSetMap.get("product_property_name"));

				if (resultSetMap.get("is_mandatory") != null)
					productPropertiesMappingRecord.getProductPropertiesRecord().setMandatory(
							(Boolean) resultSetMap.get("is_mandatory"));
				if (resultSetMap.get("is_mandatory_sku") != null)
					productPropertiesMappingRecord.setMandatorySKU((Boolean) resultSetMap.get("is_mandatory_sku"));

				if (resultSetMap.get("sku_editable") != null)
					productPropertiesMappingRecord.setSkuEditable((Boolean) resultSetMap.get("sku_editable"));
				else
					productPropertiesMappingRecord.setSkuEditable(true);
				if (resultSetMap.get("product_category_properties_mapping_id") != null)
					productPropertiesMappingRecord.setProductCategoryPropertiesMappingId(Long.valueOf(resultSetMap.get(
							"product_category_properties_mapping_id").toString()));
				productPropertiesMappingRecord.getProductPropertiesRecord().getUomRecord()
						.setName((String) resultSetMap.get("product_property_uom"));

				if (resultSetMap.get("value_type") != null)
					valueTypeSequenceNumber = Integer.valueOf(resultSetMap.get("value_type").toString());
				productPropertiesMappingRecord.getProductPropertiesRecord().getValueType()
						.setSequenceNumber(valueTypeSequenceNumber);

				if (!CommonConstant.ParameterSequenceNumber.THREE.equals(valueTypeSequenceNumber))
					productPropertiesMappingRecord
							.setPropertyValue((String) resultSetMap.get("product_property_value"));

				if (CommonConstant.ParameterSequenceNumber.TWO.equals(valueTypeSequenceNumber)) {
					String productPropertyValues = (String) resultSetMap.get("product_property_values");
					if (productPropertyValues != null) {
						String[] propertyValues = productPropertyValues.split(",");
						for (String value : propertyValues) {
							if (value != null && value.trim().length() > 0) {

								ProductPropertiesValuesDVO productPropertiesValuesDVO = new ProductPropertiesValuesDVO();
								productPropertiesValuesDVO.setPropertyValue(value);
								productPropertiesMappingRecord.getProductPropertiesRecord()
										.getProductPropertiesValuesList().add(productPropertiesValuesDVO);
							}
						}
					}

				} else if (CommonConstant.ParameterSequenceNumber.THREE.equals(valueTypeSequenceNumber)) {
					// user selected list
					String productPropertyValueStr = (String) resultSetMap.get("product_property_value");
					if (productPropertyValueStr != null) {
						String[] propertyValues = productPropertyValueStr.split(";;");
						for (String value : propertyValues) {
							if (value != null && value.trim().length() > 0) {

								ProductPropertiesMappingDVO productPropertiesMappingDVO = new ProductPropertiesMappingDVO();
								productPropertiesMappingDVO.setCode(value);
								productPropertiesMappingDVO.setName(value);
								productPropertiesMappingRecord.getPropertyValuesList().add(productPropertiesMappingDVO);
							}
						}
					}

					// suggestion box list
					String productPropertyValues = (String) resultSetMap.get("product_property_values");
					if (productPropertyValues != null) {
						String[] propertyValues = productPropertyValues.split(",");
						String propertyCode = productPropertiesMappingRecord.getProductPropertiesRecord().getCode();
						for (String value : propertyValues) {
							if (value != null && value.trim().length() > 0) {

								ProductPropertiesMappingDVO productPropertiesMappingDVO = new ProductPropertiesMappingDVO();
								productPropertiesMappingDVO.setCode(value);
								productPropertiesMappingDVO.setName(value);
								if (propertyCode != null
										&& propertyCode.equalsIgnoreCase(CommonConstant.ParameterCode.KARATAGE)) {
									String categoryCode = getPropertyValueCategory(value);
									productPropertiesMappingDVO.setPropertyValueCategory(categoryCode);
								}
								productPropertiesMappingRecord.getSuggestedValuesList()
										.add(productPropertiesMappingDVO);
							}
						}
					}
				}

				productOprRet.getProductRecord().getProductPropertiesMappingList().add(productPropertiesMappingRecord);
			}
		}
		return productOprRet;
	}

	public ProductOpr savePropertiesMappingList(ProductOpr productOpr) throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition BC :: savePropertiesMappingList starts ");
		ProductOpr productOprRet = new ProductOpr();

		ProductDVO productRecord = productOpr.getProductRecord();
		ProductSkuDVO productSkuRecord = productOpr.getProductRecord().getProductSkuRecord();
		Long productId = productRecord.getId();
		Long skuId = productRecord.getProductSkuRecord().getId();
		StringBuffer parseProductPropertiesString = new StringBuffer();
		String skuName = productSkuRecord.getName();
		Boolean activeSKU = false;
		if (productSkuRecord.getActive() != null)
			activeSKU = !(productSkuRecord.getActive());
		Boolean masterSKU = productSkuRecord.getMasterSKU();
		String userLogin = productRecord.getUserLogin();
		String skuComments = productSkuRecord.getSkuComments();
		String lastModifiedDate = null;
		if (productRecord.getAuditAttributes().getLastModifiedDate() != null)
			lastModifiedDate = productRecord.getAuditAttributes().getLastModifiedDate().toString();
		String saveFlag = (String) productOpr.getApplicationFlags().getApplicationFlagMap().get("SAVE_FLAG");
		myLog.debug(" saveFlag ---> " + saveFlag);

		if (!productOpr.getProductRecord().getProductPropertiesMappingList().isEmpty()) {
			for (ProductPropertiesMappingDVO productPropertiesMappingRecord : productOpr.getProductRecord()
					.getProductPropertiesMappingList()) {
				if (!productPropertiesMappingRecord.getOperationalAttributes().getRecordDeleted()) {
					Long productPropertiesMappingId = productPropertiesMappingRecord.getId();
					Long propertyId = productPropertiesMappingRecord.getProductPropertiesRecord().getId();
					String propertyValue = productPropertiesMappingRecord.getPropertyValue();
					Boolean mandatory = productPropertiesMappingRecord.getProductPropertiesRecord().getMandatory();
					Boolean mandatorySKU = productPropertiesMappingRecord.getMandatorySKU();
					Boolean skuEditable = productPropertiesMappingRecord.getSkuEditable();
					Long productCategoryPropertiesMappingId = productPropertiesMappingRecord
							.getProductCategoryPropertiesMappingId();
					Integer valueTypeSequenceNumber = productPropertiesMappingRecord.getProductPropertiesRecord()
							.getValueType().getSequenceNumber();

					if (!CommonConstant.ParameterSequenceNumber.THREE.equals(valueTypeSequenceNumber)) {
						if (productPropertiesMappingId != null)
							parseProductPropertiesString.append(productPropertiesMappingId);
						else
							parseProductPropertiesString.append("");
						parseProductPropertiesString.append("~");

						if (propertyId != null)
							parseProductPropertiesString.append(propertyId);
						else
							parseProductPropertiesString.append("");
						parseProductPropertiesString.append("~");

						if (propertyValue != null)
							parseProductPropertiesString.append(propertyValue);
						else
							parseProductPropertiesString.append("");
						parseProductPropertiesString.append("~");

						if (mandatory != null && mandatory)
							parseProductPropertiesString.append("1");
						else
							parseProductPropertiesString.append("0");
						parseProductPropertiesString.append("~");

						if (mandatorySKU != null && mandatorySKU)
							parseProductPropertiesString.append("1");
						else
							parseProductPropertiesString.append("0");
						parseProductPropertiesString.append("~");

						if (skuEditable != null && skuEditable)
							parseProductPropertiesString.append("1");
						else
							parseProductPropertiesString.append("0");
						parseProductPropertiesString.append("~");

						if (productCategoryPropertiesMappingId != null)
							parseProductPropertiesString.append(productCategoryPropertiesMappingId);
						else
							parseProductPropertiesString.append("");
						parseProductPropertiesString.append("~");

						if (valueTypeSequenceNumber != null)
							parseProductPropertiesString.append(valueTypeSequenceNumber);
						else
							parseProductPropertiesString.append("");

						parseProductPropertiesString.append(";");

					} else {

						int size = productPropertiesMappingRecord.getPropertyValuesList().size();
						if (size > 0) {

							for (int i = 0; i < size; i++) {
								ProductPropertiesMappingDVO productPropertiesMappingDVO = productPropertiesMappingRecord
										.getPropertyValuesList().get(i);
								productPropertiesMappingId = productPropertiesMappingDVO.getId();
								propertyValue = productPropertiesMappingDVO.getName();

								// pass id as null for delete & insert
								parseProductPropertiesString.append("");
								parseProductPropertiesString.append("~");

								if (propertyId != null)
									parseProductPropertiesString.append(propertyId);
								else
									parseProductPropertiesString.append("");
								parseProductPropertiesString.append("~");

								if (propertyValue != null)
									parseProductPropertiesString.append(propertyValue);
								else
									parseProductPropertiesString.append("");
								parseProductPropertiesString.append("~");

								if (mandatory != null && mandatory)
									parseProductPropertiesString.append("1");
								else
									parseProductPropertiesString.append("0");
								parseProductPropertiesString.append("~");

								if (mandatorySKU != null && mandatorySKU)
									parseProductPropertiesString.append("1");
								else
									parseProductPropertiesString.append("0");
								parseProductPropertiesString.append("~");

								if (skuEditable != null && skuEditable)
									parseProductPropertiesString.append("1");
								else
									parseProductPropertiesString.append("0");
								parseProductPropertiesString.append("~");

								if (productCategoryPropertiesMappingId != null)
									parseProductPropertiesString.append(productCategoryPropertiesMappingId);
								else
									parseProductPropertiesString.append("");
								parseProductPropertiesString.append("~");

								if (valueTypeSequenceNumber != null && i == 0)
									parseProductPropertiesString.append(valueTypeSequenceNumber);
								else
									parseProductPropertiesString.append("");
								parseProductPropertiesString.append(";");
							}

						} else {
							// pass id as null for delete & insert
							parseProductPropertiesString.append("");
							parseProductPropertiesString.append("~");

							if (propertyId != null)
								parseProductPropertiesString.append(propertyId);
							else
								parseProductPropertiesString.append("");
							parseProductPropertiesString.append("~");

							// user have not selected any thing - property value
							parseProductPropertiesString.append("");
							parseProductPropertiesString.append("~");

							if (mandatory != null && mandatory)
								parseProductPropertiesString.append("1");
							else
								parseProductPropertiesString.append("0");
							parseProductPropertiesString.append("~");

							if (mandatorySKU != null && mandatorySKU)
								parseProductPropertiesString.append("1");
							else
								parseProductPropertiesString.append("0");
							parseProductPropertiesString.append("~");

							if (skuEditable != null && skuEditable)
								parseProductPropertiesString.append("1");
							else
								parseProductPropertiesString.append("0");
							parseProductPropertiesString.append("~");

							if (productCategoryPropertiesMappingId != null)
								parseProductPropertiesString.append(productCategoryPropertiesMappingId);
							else
								parseProductPropertiesString.append("");
							parseProductPropertiesString.append("~");

							if (valueTypeSequenceNumber != null)
								parseProductPropertiesString.append(valueTypeSequenceNumber);
							else
								parseProductPropertiesString.append("");
							parseProductPropertiesString.append(";");
						}
					}
				}
			}
		}
		if (parseProductPropertiesString != null && parseProductPropertiesString.length() > 1) {
			// this is to remove the last ; sign
			parseProductPropertiesString.deleteCharAt(parseProductPropertiesString.length() - 1);
		}

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ProductDefinitionSqlTemplate.SAVE_PROPERTIES_MAPPING_LIST);

		Object strSqlParams[][] = new Object[10][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[0][2] = productId;
		myLog.debug(" parameter 1 :: " + productId);

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[1][2] = skuId;
		myLog.debug(" parameter 2 :: " + skuId);

		strSqlParams[2][0] = "3";
		strSqlParams[2][1] = IDAOConstant.STRING_DATATYPE;

		if (parseProductPropertiesString.length() > 0)
			strSqlParams[2][2] = parseProductPropertiesString.toString();
		else
			strSqlParams[2][2] = null;
		myLog.debug(" parameter 3 :: " + strSqlParams[2][2]);

		strSqlParams[3][0] = "4";
		strSqlParams[3][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[3][2] = skuName;
		myLog.debug(" parameter 4 :: " + skuName);

		strSqlParams[4][0] = "5";
		strSqlParams[4][1] = IDAOConstant.BOOLEAN_DATATYPE;
		strSqlParams[4][2] = activeSKU;
		myLog.debug(" parameter 5 :: " + activeSKU);

		strSqlParams[5][0] = "6";
		strSqlParams[5][1] = IDAOConstant.BOOLEAN_DATATYPE;
		strSqlParams[5][2] = masterSKU;
		myLog.debug(" parameter 6 :: " + masterSKU);

		strSqlParams[6][0] = "7";
		strSqlParams[6][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[6][2] = userLogin;
		myLog.debug(" parameter 7 :: " + userLogin);

		strSqlParams[7][0] = "8";
		strSqlParams[7][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[7][2] = lastModifiedDate;
		myLog.debug(" parameter 8 :: " + lastModifiedDate);

		strSqlParams[8][0] = "9";
		strSqlParams[8][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[8][2] = saveFlag;
		myLog.debug(" parameter 9 :: " + saveFlag);

		strSqlParams[9][0] = "10";
		strSqlParams[9][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[9][2] = skuComments;
		myLog.debug(" parameter 10 :: " + skuComments);

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" Product Definition savePropertiesMappingList :: Resultset got ::" + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {

				HashMap<String, Object> resultSetMap = responseMap.get(i);
				handleAndThrowException(resultSetMap);

				if (resultSetMap.get("product_sku_id") != null)
					skuId = Long.valueOf(resultSetMap.get("product_sku_id").toString());

				setAuditAttributes(productOprRet.getProductRecord(), resultSetMap);
			}
		}

		if (skuId != null)
			productOpr.getProductRecord().getProductSkuRecord().setId(skuId);

		productOprRet = getProductDetails(productOpr);
		productOpr = getPropertiesMappingList(productOpr);
		productOprRet.getProductRecord().setProductPropertiesMappingList(
				productOpr.getProductRecord().getProductPropertiesMappingList());

		return productOprRet;
	}

	public ProductOpr getRawMaterialMappingList(ProductOpr productOpr) throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition BC :: getRawMaterialMappingList starts ");
		ProductOpr productOprRet = new ProductOpr();

		Long productId = productOpr.getProductRecord().getId();
		Long skuId = productOpr.getProductRecord().getProductSkuRecord().getId();

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ProductDefinitionSqlTemplate.GET_RAW_MATERIAL_MAPPING_LIST);

		Object strSqlParams[][] = new Object[2][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[0][2] = productId;
		myLog.debug(" parameter 1 :: " + productId);

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[1][2] = skuId;
		myLog.debug(" parameter 2 :: " + skuId);

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" Product Definition getRawMaterialMappingList :: Resultset got ::" + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {

				HashMap<String, Object> resultSetMap = responseMap.get(i);

				ProductRMDetailsDVO productRMDetailsRecord = new ProductRMDetailsDVO();
				if (resultSetMap.get("product_rm_details_id") != null)
					productRMDetailsRecord.setId(Long.valueOf(resultSetMap.get("product_rm_details_id").toString()));

				productRMDetailsRecord.getItemRecord().setCode((String) resultSetMap.get("item_code"));
				productRMDetailsRecord.getItemRecord().setName((String) resultSetMap.get("item_name"));

				if (resultSetMap.get("quantity") != null)
					productRMDetailsRecord.setQuantity(Integer.valueOf(resultSetMap.get("quantity").toString()));
				productRMDetailsRecord.getQuantityUomRecord().setCode((String) resultSetMap.get("quantity_uom_code"));
				productRMDetailsRecord.getQuantityUomRecord().setName((String) resultSetMap.get("quantity_uom_name"));

				if (resultSetMap.get("weight") != null)
					productRMDetailsRecord.setWeight(Float.valueOf(resultSetMap.get("weight").toString()));
				productRMDetailsRecord.getWeightUomRecord().setCode((String) resultSetMap.get("weight_uom_code"));
				productRMDetailsRecord.getWeightUomRecord().setName((String) resultSetMap.get("weight_uom_name"));

				String pvValues = (String) resultSetMap.get("pv_details");
				if (pvValues != null && pvValues.trim().length() > 0) {
					String[] propertyValues = pvValues.split(",");
					for (String value : propertyValues) {
						if (value != null && value.trim().length() > 0) {

							String[] processVariation = value.split("~");

							ProductRMProcessVariationDetailsDVO productRMProcessVariationDetailsDVO = new ProductRMProcessVariationDetailsDVO();
							productRMProcessVariationDetailsDVO.setId(Long.valueOf(processVariation[0]));
							productRMProcessVariationDetailsDVO.setName(processVariation[1]);
							productRMProcessVariationDetailsDVO.getProcessVariationMappingRecord().getProcessRecord()
									.setCode(processVariation[2]);
							productRMDetailsRecord.getProductRMProcessVariationDetailsList().add(
									productRMProcessVariationDetailsDVO);
						}
					}
				}

				productOprRet.getProductRecord().getProductRMDetailsList().add(productRMDetailsRecord);
			}
		}
		return productOprRet;
	}

	public ProductOpr saveRawMaterialMappingList(ProductOpr productOpr) throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition BC :: saveRawMaterialMappingList starts ");
		ProductOpr productOprRet = new ProductOpr();

		ProductDVO productRecord = productOpr.getProductRecord();
		Long productId = productRecord.getId();
		Long skuId = productRecord.getProductSkuRecord().getId();
		StringBuffer parseRMDetailsString = new StringBuffer();
		Boolean modifyRawMaterials = productOpr.getProductRecord().getModifyProductSKURecord().getModifyRawMaterials();
		String userLogin = productRecord.getUserLogin();
		String lastModifiedDate = null;
		if (productRecord.getAuditAttributes().getLastModifiedDate() != null)
			lastModifiedDate = productRecord.getAuditAttributes().getLastModifiedDate().toString();

		if (!productOpr.getProductRecord().getProductRMDetailsList().isEmpty()) {
			for (ProductRMDetailsDVO productRMDetailsRecord : productOpr.getProductRecord().getProductRMDetailsList()) {

				Long rmDetailsId = productRMDetailsRecord.getId();
				String itemCode = productRMDetailsRecord.getItemRecord().getCode();
				Integer quantity = productRMDetailsRecord.getQuantity();
				Float weight = productRMDetailsRecord.getWeight();
				StringBuffer parseTempString = new StringBuffer();
				Boolean recordDeleted = productRMDetailsRecord.getOperationalAttributes().getRecordDeleted();

				if (recordDeleted != null)
					if ((rmDetailsId != null && recordDeleted) || (rmDetailsId != null && !recordDeleted)
							|| (rmDetailsId == null && !recordDeleted)) {

						if (rmDetailsId != null)
							parseTempString.append(rmDetailsId);
						else
							parseTempString.append("");
						parseTempString.append("~");

						if (itemCode != null)
							parseTempString.append(itemCode);
						else
							parseTempString.append("");
						parseTempString.append("~");

						if (quantity != null)
							parseTempString.append(quantity);
						else
							parseTempString.append("");
						parseTempString.append("~");

						if (weight != null)
							parseTempString.append(weight);
						else
							parseTempString.append("");
						parseTempString.append("~");

						if (!productRMDetailsRecord.getProductRMProcessVariationDetailsList().isEmpty()) {
							for (ProductRMProcessVariationDetailsDVO productRMProcessVariationDetailsDVO : productRMDetailsRecord
									.getProductRMProcessVariationDetailsList()) {
								Long rmProcessVariationDetailsId = productRMProcessVariationDetailsDVO.getId();
								parseRMDetailsString.append(parseTempString);
								if (rmProcessVariationDetailsId != null)
									parseRMDetailsString.append(rmProcessVariationDetailsId);
								else
									parseRMDetailsString.append("");

								parseRMDetailsString.append("~");
								if (recordDeleted)
									parseRMDetailsString.append("1");
								else
									parseRMDetailsString.append("0");

								parseRMDetailsString.append(";");

							}

						} else {
							parseRMDetailsString.append(parseTempString);
							parseRMDetailsString.append("~");
							if (recordDeleted)
								parseRMDetailsString.append("1");
							else
								parseRMDetailsString.append("0");

							parseRMDetailsString.append(";");
						}
					}
			}
		}

		if (parseRMDetailsString != null && parseRMDetailsString.length() > 1) {
			// this is to remove the last ; sign
			parseRMDetailsString.deleteCharAt(parseRMDetailsString.length() - 1);
		}

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ProductDefinitionSqlTemplate.SAVE_RAW_MATERIAL_MAPPING_LIST);

		Object strSqlParams[][] = new Object[6][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[0][2] = productId;
		myLog.debug(" parameter 1 :: " + productId);

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[1][2] = skuId;
		myLog.debug(" parameter 2 :: " + skuId);

		strSqlParams[2][0] = "3";
		strSqlParams[2][1] = IDAOConstant.STRING_DATATYPE;

		if (parseRMDetailsString.length() > 0)
			strSqlParams[2][2] = parseRMDetailsString.toString();
		else
			strSqlParams[2][2] = null;
		myLog.debug(" parameter 3 :: " + strSqlParams[2][2]);

		strSqlParams[3][0] = "4";
		strSqlParams[3][1] = IDAOConstant.BOOLEAN_DATATYPE;
		strSqlParams[3][2] = modifyRawMaterials;
		myLog.debug(" parameter 4 :: " + modifyRawMaterials);

		strSqlParams[4][0] = "5";
		strSqlParams[4][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[4][2] = userLogin;
		myLog.debug(" parameter 5 :: " + userLogin);

		strSqlParams[5][0] = "6";
		strSqlParams[5][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[5][2] = lastModifiedDate;
		myLog.debug(" parameter 6 :: " + lastModifiedDate);

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" Product Definition saveRawMaterialMappingList :: Resultset got ::" + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {

				HashMap<String, Object> resultSetMap = responseMap.get(i);
				handleAndThrowException(resultSetMap);

				setAuditAttributes(productOprRet.getProductRecord(), resultSetMap);
			}
		}
		productOpr = getRawMaterialMappingList(productOpr);
		productOprRet.getProductRecord().setProductRMDetailsList(
				productOpr.getProductRecord().getProductRMDetailsList());

		return productOprRet;
	}

	public ProductOpr getProcessVariationMappingList(ProductOpr productOpr) throws FrameworkException,
			BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition BC :: getProcessVariationMappingList starts ");
		ProductOpr productOprRet = new ProductOpr();

		Long productId = productOpr.getProductRecord().getId();
		Long skuId = productOpr.getProductRecord().getProductSkuRecord().getId();

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ProductDefinitionSqlTemplate.GET_PROCESS_VARIATION_MAPPING_LIST);

		Object strSqlParams[][] = new Object[2][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[0][2] = productId;
		myLog.debug(" parameter 1 :: " + productId);

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[1][2] = skuId;
		myLog.debug(" parameter 2 :: " + skuId);

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" Product Definition getProcessVariationMappingList :: Resultset got ::" + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {

				HashMap<String, Object> resultSetMap = responseMap.get(i);

				ProductProcessVariationMappingDVO productProcessVariationMappingDVO = new ProductProcessVariationMappingDVO();
				if (resultSetMap.get("process_id") != null)
					productProcessVariationMappingDVO.getProcessRecord().setId(
							Long.valueOf(resultSetMap.get("process_id").toString()));
				productProcessVariationMappingDVO.getProcessRecord().setCode((String) resultSetMap.get("process_code"));
				productProcessVariationMappingDVO.getProcessRecord().setName((String) resultSetMap.get("process_name"));

				String pvValues = (String) resultSetMap.get("pv_details");
				if (pvValues != null && pvValues.trim().length() > 0) {
					String[] propertyValues = pvValues.split(",");
					for (String value : propertyValues) {
						if (value != null && value.trim().length() > 0) {

							String[] processVariation = value.split("~");

							ProcessVariationMappingDVO processVariationMappingDVO = new ProcessVariationMappingDVO();
							processVariationMappingDVO.setId(Long.valueOf(processVariation[0]));
							processVariationMappingDVO.setName(processVariation[1]);
							productProcessVariationMappingDVO.getProcessRecord().getProcessVariationMappingList()
									.add(processVariationMappingDVO);
						}
					}
				}

				productOprRet.getProductRecord().getProductProcessVariationMappingList()
						.add(productProcessVariationMappingDVO);
			}
		}
		return productOprRet;
	}

	public ProductOpr saveProcessVariationMappingList(ProductOpr productOpr) throws FrameworkException,
			BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition BC :: saveProcessVariationMappingList starts ");
		ProductOpr productOprRet = new ProductOpr();

		ProductDVO productRecord = productOpr.getProductRecord();
		Long productId = productRecord.getId();
		Long skuId = productRecord.getProductSkuRecord().getId();
		StringBuffer parsePVDetailsString = new StringBuffer();
		Boolean modifyProcessVariation = productOpr.getProductRecord().getModifyProductSKURecord()
				.getModifyProcessVariation();
		String userLogin = productRecord.getUserLogin();
		String lastModifiedDate = null;
		if (productRecord.getAuditAttributes().getLastModifiedDate() != null)
			lastModifiedDate = productRecord.getAuditAttributes().getLastModifiedDate().toString();

		if (!productOpr.getProductRecord().getProductProcessVariationMappingList().isEmpty()) {
			for (ProductProcessVariationMappingDVO productProcessVariationMappingDVO : productOpr.getProductRecord()
					.getProductProcessVariationMappingList()) {
				if (!productProcessVariationMappingDVO.getOperationalAttributes().getRecordDeleted()) {

					Long processId = productProcessVariationMappingDVO.getProcessRecord().getId();

					if (!productProcessVariationMappingDVO.getProcessRecord().getProcessVariationMappingList()
							.isEmpty()) {
						for (ProcessVariationMappingDVO processVariationMappingDVO : productProcessVariationMappingDVO
								.getProcessRecord().getProcessVariationMappingList()) {
							Long processVariationDetailsId = processVariationMappingDVO.getId();

							if (processId != null)
								parsePVDetailsString.append(processId);
							else
								parsePVDetailsString.append("");
							parsePVDetailsString.append("~");

							if (processVariationDetailsId != null)
								parsePVDetailsString.append(processVariationDetailsId);
							else
								parsePVDetailsString.append("");
							parsePVDetailsString.append(";");
						}
					}
				}
			}
		}

		if (parsePVDetailsString != null && parsePVDetailsString.length() > 1) {
			// this is to remove the last ; sign
			parsePVDetailsString.deleteCharAt(parsePVDetailsString.length() - 1);
		}

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ProductDefinitionSqlTemplate.SAVE_PROCESS_VARIATION_MAPPING_LIST);

		Object strSqlParams[][] = new Object[6][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[0][2] = productId;
		myLog.debug(" parameter 1 :: " + productId);

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[1][2] = skuId;
		myLog.debug(" parameter 2 :: " + skuId);

		strSqlParams[2][0] = "3";
		strSqlParams[2][1] = IDAOConstant.STRING_DATATYPE;

		if (parsePVDetailsString.length() > 0)
			strSqlParams[2][2] = parsePVDetailsString.toString();
		else
			strSqlParams[2][2] = null;
		myLog.debug(" parameter 3 :: " + strSqlParams[2][2]);

		strSqlParams[3][0] = "4";
		strSqlParams[3][1] = IDAOConstant.BOOLEAN_DATATYPE;
		strSqlParams[3][2] = modifyProcessVariation;
		myLog.debug(" parameter 4 :: " + modifyProcessVariation);

		strSqlParams[4][0] = "5";
		strSqlParams[4][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[4][2] = userLogin;
		myLog.debug(" parameter 5 :: " + userLogin);

		strSqlParams[5][0] = "6";
		strSqlParams[5][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[5][2] = lastModifiedDate;
		myLog.debug(" parameter 6 :: " + lastModifiedDate);

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" Product Definition saveProcessVariationMappingList :: Resultset got ::" + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {

				HashMap<String, Object> resultSetMap = responseMap.get(i);
				handleAndThrowException(resultSetMap);

				setAuditAttributes(productOprRet.getProductRecord(), resultSetMap);
			}
		}
		productOpr = getProcessVariationMappingList(productOpr);
		productOprRet.getProductRecord().setProductProcessVariationMappingList(
				productOpr.getProductRecord().getProductProcessVariationMappingList());

		return productOprRet;
	}

	public ProductOpr getComplementarySkuMappingList(ProductOpr productOpr) throws FrameworkException,
			BusinessException {

		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition BC :: getComplementarySkuMappingList starts ");
		ProductOpr productOprRet = new ProductOpr();

		Long productId = productOpr.getProductRecord().getId();
		Long skuId = productOpr.getProductRecord().getProductSkuRecord().getId();

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ProductDefinitionSqlTemplate.GET_COMPLEMENTARY_SKU_MAPPING_LIST);

		Object strSqlParams[][] = new Object[2][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[0][2] = productId;
		myLog.debug(" parameter 1 :: " + productId);

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[1][2] = skuId;
		myLog.debug(" parameter 2 :: " + skuId);

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" Product Definition getComplementarySkuMappingList :: Resultset got ::" + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {

				HashMap<String, Object> resultSetMap = responseMap.get(i);

				ProductComplementarySkuMappingDVO productComplementarySkuMappingDVO = new ProductComplementarySkuMappingDVO();
				if (resultSetMap.get("product_complementary_sku_mapping_id") != null)
					productComplementarySkuMappingDVO.setId(Long.valueOf(resultSetMap.get(
							"product_complementary_sku_mapping_id").toString()));

				if (resultSetMap.get("complementary_product_sku_id") != null)
					productComplementarySkuMappingDVO.getProductSkuRecord().setId(
							Long.valueOf(resultSetMap.get("complementary_product_sku_id").toString()));
				productComplementarySkuMappingDVO.getProductSkuRecord().setCode((String) resultSetMap.get("sku_code"));
				productComplementarySkuMappingDVO.getProductSkuRecord().setDescription(
						(String) resultSetMap.get("sku_description"));

				productOprRet.getProductRecord().getProductComplementarySkuMappingList()
						.add(productComplementarySkuMappingDVO);
			}
		}
		return productOprRet;
	}

	public ProductOpr saveComplementarySkuMappingList(ProductOpr productOpr) throws FrameworkException,
			BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition BC :: saveComplementarySkuMappingList starts ");
		ProductOpr productOprRet = new ProductOpr();

		ProductDVO productRecord = productOpr.getProductRecord();
		Long productId = productRecord.getId();
		Long skuId = productRecord.getProductSkuRecord().getId();
		StringBuffer parseSkuDetailsString = new StringBuffer();
		Boolean modifyComplementarySKU = productOpr.getProductRecord().getModifyProductSKURecord()
				.getModifyComplementarySKU();
		String userLogin = productRecord.getUserLogin();
		String lastModifiedDate = null;
		if (productRecord.getAuditAttributes().getLastModifiedDate() != null)
			lastModifiedDate = productRecord.getAuditAttributes().getLastModifiedDate().toString();

		if (!productOpr.getProductRecord().getProductComplementarySkuMappingList().isEmpty()) {
			for (ProductComplementarySkuMappingDVO productComplementarySkuMappingDVO : productOpr.getProductRecord()
					.getProductComplementarySkuMappingList()) {

				Long complementarySkuMappingId = productComplementarySkuMappingDVO.getId();
				Long skuMappingId = productComplementarySkuMappingDVO.getProductSkuRecord().getId();
				Boolean recordDeleted = productComplementarySkuMappingDVO.getOperationalAttributes().getRecordDeleted();

				if (recordDeleted != null)
					if ((complementarySkuMappingId != null && recordDeleted)
							|| (complementarySkuMappingId != null && !recordDeleted)
							|| (complementarySkuMappingId == null && !recordDeleted)) {

						if (complementarySkuMappingId != null)
							parseSkuDetailsString.append(complementarySkuMappingId);
						else
							parseSkuDetailsString.append("");
						parseSkuDetailsString.append("~");

						if (skuMappingId != null)
							parseSkuDetailsString.append(skuMappingId);
						else
							parseSkuDetailsString.append("");
						parseSkuDetailsString.append("~");

						if (recordDeleted)
							parseSkuDetailsString.append("1");
						else
							parseSkuDetailsString.append("0");
						parseSkuDetailsString.append(";");

					}
			}
		}

		if (parseSkuDetailsString != null && parseSkuDetailsString.length() > 1) {
			// this is to remove the last ; sign
			parseSkuDetailsString.deleteCharAt(parseSkuDetailsString.length() - 1);
		}

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ProductDefinitionSqlTemplate.SAVE_COMPLEMENTARY_SKU_MAPPING_LIST);

		Object strSqlParams[][] = new Object[6][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[0][2] = productId;
		myLog.debug(" parameter 1 :: " + productId);

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[1][2] = skuId;
		myLog.debug(" parameter 2 :: " + skuId);

		strSqlParams[2][0] = "3";
		strSqlParams[2][1] = IDAOConstant.STRING_DATATYPE;

		if (parseSkuDetailsString.length() > 0)
			strSqlParams[2][2] = parseSkuDetailsString.toString();
		else
			strSqlParams[2][2] = null;
		myLog.debug(" parameter 3 :: " + strSqlParams[2][2]);

		strSqlParams[3][0] = "4";
		strSqlParams[3][1] = IDAOConstant.BOOLEAN_DATATYPE;
		strSqlParams[3][2] = modifyComplementarySKU;
		myLog.debug(" parameter 4 :: " + modifyComplementarySKU);

		strSqlParams[4][0] = "5";
		strSqlParams[4][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[4][2] = userLogin;
		myLog.debug(" parameter 5 :: " + userLogin);

		strSqlParams[5][0] = "6";
		strSqlParams[5][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[5][2] = lastModifiedDate;
		myLog.debug(" parameter 6 :: " + lastModifiedDate);

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" Product Definition saveComplementarySkuMappingList :: Resultset got ::" + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {

				HashMap<String, Object> resultSetMap = responseMap.get(i);
				handleAndThrowException(resultSetMap);

				setAuditAttributes(productOprRet.getProductRecord(), resultSetMap);
			}
		}
		productOpr = getComplementarySkuMappingList(productOpr);
		productOprRet.getProductRecord().setProductComplementarySkuMappingList(
				productOpr.getProductRecord().getProductComplementarySkuMappingList());

		return productOprRet;
	}

	public ProductOpr getSimilarSkuMappingList(ProductOpr productOpr) throws FrameworkException, BusinessException {

		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition BC :: getSimilarSkuMappingList starts ");
		ProductOpr productOprRet = new ProductOpr();

		Long productId = productOpr.getProductRecord().getId();
		Long skuId = productOpr.getProductRecord().getProductSkuRecord().getId();

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ProductDefinitionSqlTemplate.GET_SIMILAR_SKU_MAPPING_LIST);

		Object strSqlParams[][] = new Object[2][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[0][2] = productId;
		myLog.debug(" parameter 1 :: " + productId);

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[1][2] = skuId;
		myLog.debug(" parameter 2 :: " + skuId);

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" Product Definition getSimilarSkuMappingList :: Resultset got ::" + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {

				HashMap<String, Object> resultSetMap = responseMap.get(i);

				ProductSimilarSkuMappingDVO productSimilarSkuMappingDVO = new ProductSimilarSkuMappingDVO();
				if (resultSetMap.get("product_sku_similar_sku_mapping_id") != null)
					productSimilarSkuMappingDVO.setId(Long.valueOf(resultSetMap.get(
							"product_sku_similar_sku_mapping_id").toString()));

				if (resultSetMap.get("similar_product_sku_id") != null)
					productSimilarSkuMappingDVO.getProductSkuRecord().setId(
							Long.valueOf(resultSetMap.get("similar_product_sku_id").toString()));
				productSimilarSkuMappingDVO.getProductSkuRecord().setCode((String) resultSetMap.get("sku_code"));
				productSimilarSkuMappingDVO.getProductSkuRecord().setDescription(
						(String) resultSetMap.get("sku_description"));

				productOprRet.getProductRecord().getProductSimilarSkuMappingList().add(productSimilarSkuMappingDVO);
			}
		}
		return productOprRet;
	}

	public ProductOpr saveSimilarSkuMappingList(ProductOpr productOpr) throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition BC :: saveSimilarSkuMappingList starts ");
		ProductOpr productOprRet = new ProductOpr();

		ProductDVO productRecord = productOpr.getProductRecord();
		Long productId = productRecord.getId();
		Long skuId = productRecord.getProductSkuRecord().getId();
		StringBuffer parseSkuDetailsString = new StringBuffer();
		Boolean modifySimiliarSKU = productOpr.getProductRecord().getModifyProductSKURecord().getModifySimiliarSKU();
		String userLogin = productRecord.getUserLogin();
		String lastModifiedDate = null;
		if (productRecord.getAuditAttributes().getLastModifiedDate() != null)
			lastModifiedDate = productRecord.getAuditAttributes().getLastModifiedDate().toString();

		if (!productOpr.getProductRecord().getProductSimilarSkuMappingList().isEmpty()) {
			for (ProductSimilarSkuMappingDVO productSimilarSkuMappingDVO : productOpr.getProductRecord()
					.getProductSimilarSkuMappingList()) {

				Long similiarSkuMappingId = productSimilarSkuMappingDVO.getId();
				Long skuMappingId = productSimilarSkuMappingDVO.getProductSkuRecord().getId();
				Boolean recordDeleted = productSimilarSkuMappingDVO.getOperationalAttributes().getRecordDeleted();

				if (recordDeleted != null)
					if ((similiarSkuMappingId != null && recordDeleted)
							|| (similiarSkuMappingId != null && !recordDeleted)
							|| (similiarSkuMappingId == null && !recordDeleted)) {

						if (similiarSkuMappingId != null)
							parseSkuDetailsString.append(similiarSkuMappingId);
						else
							parseSkuDetailsString.append("");
						parseSkuDetailsString.append("~");

						if (skuMappingId != null)
							parseSkuDetailsString.append(skuMappingId);
						else
							parseSkuDetailsString.append("");
						parseSkuDetailsString.append("~");

						if (recordDeleted)
							parseSkuDetailsString.append("1");
						else
							parseSkuDetailsString.append("0");
						parseSkuDetailsString.append(";");

					}
			}
		}

		if (parseSkuDetailsString != null && parseSkuDetailsString.length() > 1) {
			// this is to remove the last ; sign
			parseSkuDetailsString.deleteCharAt(parseSkuDetailsString.length() - 1);
		}

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ProductDefinitionSqlTemplate.SAVE_SIMILAR_SKU_MAPPING_LIST);

		Object strSqlParams[][] = new Object[6][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[0][2] = productId;
		myLog.debug(" parameter 1 :: " + productId);

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[1][2] = skuId;
		myLog.debug(" parameter 2 :: " + skuId);

		strSqlParams[2][0] = "3";
		strSqlParams[2][1] = IDAOConstant.STRING_DATATYPE;

		if (parseSkuDetailsString.length() > 0)
			strSqlParams[2][2] = parseSkuDetailsString.toString();
		else
			strSqlParams[2][2] = null;
		myLog.debug(" parameter 3 :: " + strSqlParams[2][2]);

		strSqlParams[3][0] = "4";
		strSqlParams[3][1] = IDAOConstant.BOOLEAN_DATATYPE;
		strSqlParams[3][2] = modifySimiliarSKU;
		myLog.debug(" parameter 4 :: " + modifySimiliarSKU);

		strSqlParams[4][0] = "5";
		strSqlParams[4][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[4][2] = userLogin;
		myLog.debug(" parameter 5 :: " + userLogin);

		strSqlParams[5][0] = "6";
		strSqlParams[5][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[5][2] = lastModifiedDate;
		myLog.debug(" parameter 6 :: " + lastModifiedDate);

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" Product Definition saveSimilarSkuMappingList :: Resultset got ::" + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {

				HashMap<String, Object> resultSetMap = responseMap.get(i);
				handleAndThrowException(resultSetMap);

				setAuditAttributes(productOprRet.getProductRecord(), resultSetMap);
			}
		}
		productOpr = getSimilarSkuMappingList(productOpr);
		productOprRet.getProductRecord().setProductSimilarSkuMappingList(
				productOpr.getProductRecord().getProductSimilarSkuMappingList());

		return productOprRet;
	}

	public ProductOpr getPropsMappingList(ProductOpr productOpr) throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition BC :: getPropsMappingList starts ");
		ProductOpr productOprRet = new ProductOpr();

		Long productId = productOpr.getProductRecord().getId();
		Long skuId = productOpr.getProductRecord().getProductSkuRecord().getId();

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ProductDefinitionSqlTemplate.GET_PROPS_MAPPING_LIST);

		Object strSqlParams[][] = new Object[2][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[0][2] = productId;
		myLog.debug(" parameter 1 :: " + productId);

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[1][2] = skuId;
		myLog.debug(" parameter 2 :: " + skuId);

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" Product Definition getPropsMappingList :: Resultset got ::" + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {

				HashMap<String, Object> resultSetMap = responseMap.get(i);

				ProductPropsMappingDVO productPropsMappingRecord = new ProductPropsMappingDVO();
				// Record
				if (resultSetMap.get("product_props_mapping_id") != null)
					productPropsMappingRecord.setId(Long.valueOf(resultSetMap.get("product_props_mapping_id")
							.toString()));

				productPropsMappingRecord.getItemRecord().setCode((String) resultSetMap.get("item_code"));
				productPropsMappingRecord.getItemRecord().setName((String) resultSetMap.get("item_name"));

				if (resultSetMap.get("quantity") != null)
					productPropsMappingRecord.setQuantity(Integer.valueOf(resultSetMap.get("quantity").toString()));
				productPropsMappingRecord.getQuantityUomRecord()
						.setCode((String) resultSetMap.get("quantity_uom_code"));
				productPropsMappingRecord.getQuantityUomRecord()
						.setName((String) resultSetMap.get("quantity_uom_name"));

				if (resultSetMap.get("weight") != null)
					productPropsMappingRecord.setWeight(Float.valueOf(resultSetMap.get("weight").toString()));
				productPropsMappingRecord.getWeightUomRecord().setCode((String) resultSetMap.get("weight_uom_code"));
				productPropsMappingRecord.getWeightUomRecord().setName((String) resultSetMap.get("weight_uom_name"));

				productOprRet.getProductRecord().getProductPropsMappingList().add(productPropsMappingRecord);
			}
		}
		return productOprRet;
	}

	public ProductOpr executeSavePropsMapping(ProductOpr productOpr) throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition BC :: executeSavePropsMapping starts ");
		ProductOpr productOprRet = new ProductOpr();

		ProductDVO productRecord = productOpr.getProductRecord();
		Long productId = productRecord.getId();
		Long skuId = productRecord.getProductSkuRecord().getId();
		StringBuffer parsePropsString = new StringBuffer();
		Boolean modifyProps = productOpr.getProductRecord().getModifyProductSKURecord().getModifyProps();
		String userLogin = productRecord.getUserLogin();
		String lastModifiedDate = null;
		if (productRecord.getAuditAttributes().getLastModifiedDate() != null)
			lastModifiedDate = productRecord.getAuditAttributes().getLastModifiedDate().toString();

		if (!productOpr.getProductRecord().getProductPropsMappingList().isEmpty()) {
			for (ProductPropsMappingDVO productPropsMappingRecord : productOpr.getProductRecord()
					.getProductPropsMappingList()) {

				Long propsMappingId = productPropsMappingRecord.getId();
				String itemCode = productPropsMappingRecord.getItemRecord().getCode();
				Integer quantity = productPropsMappingRecord.getQuantity();
				Float weight = productPropsMappingRecord.getWeight();
				Boolean recordDeleted = productPropsMappingRecord.getOperationalAttributes().getRecordDeleted();

				if (recordDeleted != null)
					if ((propsMappingId != null && recordDeleted) || (propsMappingId != null && !recordDeleted)
							|| (propsMappingId == null && !recordDeleted)) {
						if (propsMappingId != null)
							parsePropsString.append(propsMappingId);
						else
							parsePropsString.append("");
						parsePropsString.append("~");

						if (itemCode != null)
							parsePropsString.append(itemCode);
						else
							parsePropsString.append("");
						parsePropsString.append("~");

						if (quantity != null)
							parsePropsString.append(quantity);
						else
							parsePropsString.append("");
						parsePropsString.append("~");

						if (weight != null)
							parsePropsString.append(weight);
						else
							parsePropsString.append("");
						parsePropsString.append("~");

						if (recordDeleted)
							parsePropsString.append("1");
						else
							parsePropsString.append("0");
						parsePropsString.append(";");
					}
			}

		}

		if (parsePropsString != null && parsePropsString.length() > 1) {
			// this is to remove the last ; sign
			parsePropsString.deleteCharAt(parsePropsString.length() - 1);
		}

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ProductDefinitionSqlTemplate.SAVE_PROPS_MAPPING_LIST);

		Object strSqlParams[][] = new Object[6][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[0][2] = productId;
		myLog.debug(" parameter 1 :: " + productId);

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[1][2] = skuId;
		myLog.debug(" parameter 2 :: " + skuId);

		strSqlParams[2][0] = "3";
		strSqlParams[2][1] = IDAOConstant.STRING_DATATYPE;

		if (parsePropsString.length() > 0)
			strSqlParams[2][2] = parsePropsString.toString();
		else
			strSqlParams[2][2] = null;
		myLog.debug(" parameter 3 :: " + strSqlParams[2][2]);

		strSqlParams[3][0] = "4";
		strSqlParams[3][1] = IDAOConstant.BOOLEAN_DATATYPE;
		strSqlParams[3][2] = modifyProps;
		myLog.debug(" parameter 4 :: " + modifyProps);

		strSqlParams[4][0] = "5";
		strSqlParams[4][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[4][2] = userLogin;
		myLog.debug(" parameter 5 :: " + userLogin);

		strSqlParams[5][0] = "6";
		strSqlParams[5][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[5][2] = lastModifiedDate;
		myLog.debug(" parameter 6 :: " + lastModifiedDate);

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" Product Definition executeSavePropsMapping :: Resultset got ::" + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {

				HashMap<String, Object> resultSetMap = responseMap.get(i);
				handleAndThrowException(resultSetMap);

				setAuditAttributes(productOprRet.getProductRecord(), resultSetMap);
			}
		}
		productOpr = getPropsMappingList(productOpr);
		productOprRet.getProductRecord().setProductPropsMappingList(
				productOpr.getProductRecord().getProductPropsMappingList());

		return productOprRet;
	}

	public ProductOpr getCatalogMappingList(ProductOpr productOpr) throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition BC :: getCatalogMappingList starts ");
		ProductOpr productOprRet = new ProductOpr();

		Long productId = productOpr.getProductRecord().getId();
		Long skuId = productOpr.getProductRecord().getProductSkuRecord().getId();

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ProductDefinitionSqlTemplate.GET_CATALOG_MAPPING_LIST);

		Object strSqlParams[][] = new Object[2][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[0][2] = productId;
		myLog.debug(" parameter 1 :: " + productId);

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[1][2] = skuId;
		myLog.debug(" parameter 2 :: " + skuId);

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" Product Definition getCatalogMappingList :: Resultset got ::" + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {

				HashMap<String, Object> resultSetMap = responseMap.get(i);

				ProductCatalogMappingDVO productCatalogMappingRecord = new ProductCatalogMappingDVO();

				if (resultSetMap.get("product_catalog_mapping_id") != null)
					productCatalogMappingRecord.setId(Long.valueOf(resultSetMap.get("product_catalog_mapping_id")
							.toString()));

				if (resultSetMap.get("catalog_id") != null)
					productCatalogMappingRecord.getCatalogRecord().setId(
							Long.valueOf(resultSetMap.get("catalog_id").toString()));
				productCatalogMappingRecord.getCatalogRecord().setName((String) resultSetMap.get("catalog_name"));

				Long catalogSelected = 0L;
				if (resultSetMap.get("catalog_selected") != null)
					catalogSelected = Long.valueOf(resultSetMap.get("catalog_selected").toString());
				if (catalogSelected == 1)
					productCatalogMappingRecord.getOperationalAttributes().setRecordSelected(true);
				else
					productCatalogMappingRecord.getOperationalAttributes().setRecordSelected(false);

				Integer active = 0;
				if (resultSetMap.get("is_active") != null)
					active = Integer.valueOf(resultSetMap.get("is_active").toString());
				if (active == 1)
					productCatalogMappingRecord.setActive(true);
				else
					productCatalogMappingRecord.setActive(false);

				productOprRet.getProductRecord().getProductCatalogMappingList().add(productCatalogMappingRecord);
			}
		}
		return productOprRet;
	}

	public ProductOpr executeSaveCatalogMapping(ProductOpr productOpr) throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition BC :: executeSaveCatalogMapping starts ");
		ProductOpr productOprRet = new ProductOpr();

		ProductDVO productRecord = productOpr.getProductRecord();
		Long productId = productRecord.getId();
		Long skuId = productRecord.getProductSkuRecord().getId();
		StringBuffer parseCatalogString = new StringBuffer();
		Boolean modifyCatalogs = productOpr.getProductRecord().getModifyProductSKURecord().getModifyCatalogs();
		String userLogin = productRecord.getUserLogin();
		String lastModifiedDate = null;
		if (productRecord.getAuditAttributes().getLastModifiedDate() != null)
			lastModifiedDate = productRecord.getAuditAttributes().getLastModifiedDate().toString();

		if (!productOpr.getProductRecord().getProductCatalogMappingList().isEmpty()) {
			for (ProductCatalogMappingDVO productCatalogMappingRecord : productOpr.getProductRecord()
					.getProductCatalogMappingList()) {

				Long catalogMappingId = productCatalogMappingRecord.getId();
				Long catalogId = productCatalogMappingRecord.getCatalogRecord().getId();
				Boolean recordSelected = productCatalogMappingRecord.getOperationalAttributes().getRecordSelected();

				if (catalogMappingId != null)
					parseCatalogString.append(catalogMappingId);
				else
					parseCatalogString.append("");
				parseCatalogString.append("~");

				if (catalogId != null)
					parseCatalogString.append(catalogId);
				else
					parseCatalogString.append("");
				parseCatalogString.append("~");

				if (recordSelected != null && recordSelected)
					parseCatalogString.append("1");
				else
					parseCatalogString.append("0");
				parseCatalogString.append(";");

			}

		}

		if (parseCatalogString != null && parseCatalogString.length() > 1) {
			// this is to remove the last ; sign
			parseCatalogString.deleteCharAt(parseCatalogString.length() - 1);
		}

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ProductDefinitionSqlTemplate.SAVE_CATALOG_MAPPING_LIST);

		Object strSqlParams[][] = new Object[6][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[0][2] = productId;
		myLog.debug(" parameter 1 :: " + productId);

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[1][2] = skuId;
		myLog.debug(" parameter 2 :: " + skuId);

		strSqlParams[2][0] = "3";
		strSqlParams[2][1] = IDAOConstant.STRING_DATATYPE;

		if (parseCatalogString.length() > 0)
			strSqlParams[2][2] = parseCatalogString.toString();
		else
			strSqlParams[2][2] = null;
		myLog.debug(" parameter 3 :: " + strSqlParams[2][2]);

		strSqlParams[3][0] = "4";
		strSqlParams[3][1] = IDAOConstant.BOOLEAN_DATATYPE;
		strSqlParams[3][2] = modifyCatalogs;
		myLog.debug(" parameter 4 :: " + modifyCatalogs);

		strSqlParams[4][0] = "5";
		strSqlParams[4][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[4][2] = userLogin;
		myLog.debug(" parameter 5 :: " + userLogin);

		strSqlParams[5][0] = "6";
		strSqlParams[5][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[5][2] = lastModifiedDate;
		myLog.debug(" parameter 6 :: " + lastModifiedDate);

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" Product Definition executeSaveCatalogMapping :: Resultset got ::" + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {

				HashMap<String, Object> resultSetMap = responseMap.get(i);
				handleAndThrowException(resultSetMap);

				setAuditAttributes(productOprRet.getProductRecord(), resultSetMap);
			}
		}
		productOpr = getCatalogMappingList(productOpr);
		productOprRet.getProductRecord().setProductCatalogMappingList(
				productOpr.getProductRecord().getProductCatalogMappingList());

		return productOprRet;
	}

	public ProductOpr executeCopyProductSKU(ProductOpr productOpr) throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition BC :: executeCopyProductSKU starts ");

		ProductDVO productRecord = productOpr.getProductRecord();
		CopyProductSKUDVO copyProductSKURecord = productOpr.getCopyProductSKURecord();

		Long productIdSource = copyProductSKURecord.getSourceProductRecord().getId();
		Long productIdDestination = productRecord.getId();
		Long skuIdSource = copyProductSKURecord.getSourceProductRecord().getProductSkuRecord().getId();
		Long skuIdDestination = productRecord.getProductSkuRecord().getId();

		Boolean mapCatalogs = copyProductSKURecord.getMapCatalogs();
		Boolean mapComplementarySKU = copyProductSKURecord.getMapComplementarySKU();
		Boolean mapHierarchy = copyProductSKURecord.getMapHierarchy();
		Boolean mapProcessVariation = copyProductSKURecord.getMapProcessVariation();
		Boolean mapProperties = copyProductSKURecord.getMapProperties();
		Boolean mapProps = copyProductSKURecord.getMapProps();
		Boolean mapRawMaterials = copyProductSKURecord.getMapRawMaterials();
		Boolean mapSimiliarSKU = copyProductSKURecord.getMapSimiliarSKU();

		String userLogin = productRecord.getUserLogin();
		String lastModifiedDate = null;
		if (productRecord.getAuditAttributes().getLastModifiedDate() != null)
			lastModifiedDate = productRecord.getAuditAttributes().getLastModifiedDate().toString();

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ProductDefinitionSqlTemplate.COPY_PRODUCT_SKU);

		Object strSqlParams[][] = new Object[14][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[0][2] = productIdSource;
		myLog.debug(" parameter 1 :: " + productIdSource);

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[1][2] = productIdDestination;
		myLog.debug(" parameter 2 :: " + productIdDestination);

		strSqlParams[2][0] = "3";
		strSqlParams[2][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[2][2] = skuIdSource;
		myLog.debug(" parameter 3 :: " + skuIdSource);

		strSqlParams[3][0] = "4";
		strSqlParams[3][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[3][2] = skuIdDestination;
		myLog.debug(" parameter 4 :: " + skuIdDestination);

		strSqlParams[4][0] = "5";
		strSqlParams[4][1] = IDAOConstant.BOOLEAN_DATATYPE;
		strSqlParams[4][2] = mapCatalogs;
		myLog.debug(" parameter 5 :: " + mapCatalogs);

		strSqlParams[5][0] = "6";
		strSqlParams[5][1] = IDAOConstant.BOOLEAN_DATATYPE;
		strSqlParams[5][2] = mapComplementarySKU;
		myLog.debug(" parameter 6 :: " + mapComplementarySKU);

		strSqlParams[6][0] = "7";
		strSqlParams[6][1] = IDAOConstant.BOOLEAN_DATATYPE;
		strSqlParams[6][2] = mapHierarchy;
		myLog.debug(" parameter 7 :: " + mapHierarchy);

		strSqlParams[7][0] = "8";
		strSqlParams[7][1] = IDAOConstant.BOOLEAN_DATATYPE;
		strSqlParams[7][2] = mapProcessVariation;
		myLog.debug(" parameter 8 :: " + mapProcessVariation);

		strSqlParams[8][0] = "9";
		strSqlParams[8][1] = IDAOConstant.BOOLEAN_DATATYPE;
		strSqlParams[8][2] = mapProperties;
		myLog.debug(" parameter 9 :: " + mapProperties);

		strSqlParams[9][0] = "10";
		strSqlParams[9][1] = IDAOConstant.BOOLEAN_DATATYPE;
		strSqlParams[9][2] = mapProps;
		myLog.debug(" parameter 10 :: " + mapProps);

		strSqlParams[10][0] = "11";
		strSqlParams[10][1] = IDAOConstant.BOOLEAN_DATATYPE;
		strSqlParams[10][2] = mapRawMaterials;
		myLog.debug(" parameter 11 :: " + mapRawMaterials);

		strSqlParams[11][0] = "12";
		strSqlParams[11][1] = IDAOConstant.BOOLEAN_DATATYPE;
		strSqlParams[11][2] = mapSimiliarSKU;
		myLog.debug(" parameter 12 :: " + mapSimiliarSKU);

		strSqlParams[12][0] = "13";
		strSqlParams[12][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[12][2] = userLogin;
		myLog.debug(" parameter 13 :: " + userLogin);

		strSqlParams[13][0] = "14";
		strSqlParams[13][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[13][2] = lastModifiedDate;
		myLog.debug(" parameter 14 :: " + lastModifiedDate);

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" Product Definition executeCopyProductSKU :: Resultset got ::" + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {

				HashMap<String, Object> resultSetMap = responseMap.get(i);
				handleAndThrowException(resultSetMap);

				setAuditAttributes(productOpr.getProductRecord(), resultSetMap);
			}
		}

		return getProductDetails(productOpr);
	}

	public ProductOpr executeApproveProductSku(ProductOpr productOpr) throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition BC :: executeApproveProductSku starts ");
		ProductDVO productRecord = productOpr.getProductRecord();
		ProductSkuDVO productSkuRecord = productOpr.getProductRecord().getProductSkuRecord();

		Long productId = productRecord.getId();
		String productCode = productRecord.getCode();
		String productName = productRecord.getName();
		String productDesc = productRecord.getDescription();
		String uomCode = productRecord.getUomRecord().getCode();
		String uomCategoryCode = null;
		Integer allocationBasedOn = productRecord.getAllocationBasedOn().getParameterID();
		Boolean active = false;
		if (productRecord.getActive() != null)
			active = !(productRecord.getActive());
		Boolean autoReplenish = productRecord.getAutoReplenish();
		Integer defaultPricingModel = productRecord.getDefaultPricingModel().getParameterID();
		StringBuffer parseProductUOMListString = new StringBuffer();
		Long skuId = productRecord.getProductSkuRecord().getId();
		String skuName = productSkuRecord.getName();
		String skuComments = productSkuRecord.getSkuComments();
		Boolean activeSKU = false;
		if (productSkuRecord.getActive() != null)
			activeSKU = !(productSkuRecord.getActive());
		Boolean masterSKU = productSkuRecord.getMasterSKU();

		String userLogin = productRecord.getUserLogin();
		String lastModifiedDate = null;
		if (productRecord.getAuditAttributes().getLastModifiedDate() != null)
			lastModifiedDate = productRecord.getAuditAttributes().getLastModifiedDate().toString();
		String weightUomCode = productRecord.getWeightUomRecord().getCode();
		String productNameForBill = productRecord.getProductNameForBill();

		for (UomDVO uomDVO : productRecord.getUomList()) {
			parseProductUOMListString.append(uomDVO.getCode());
			parseProductUOMListString.append("~");
		}
		if (parseProductUOMListString != null && parseProductUOMListString.length() > 1) {
			// this is to remove the last ~ sign
			parseProductUOMListString.deleteCharAt(parseProductUOMListString.length() - 1);
		}

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ProductDefinitionSqlTemplate.APPROVE_PRODUCT_SKU);

		Object strSqlParams[][] = new Object[20][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[0][2] = productId;
		myLog.debug(" parameter 1 :: " + productId);

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[1][2] = productCode;
		myLog.debug(" parameter 2 :: " + productCode);

		strSqlParams[2][0] = "3";
		strSqlParams[2][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[2][2] = productName;
		myLog.debug(" parameter 3 :: " + productName);

		strSqlParams[3][0] = "4";
		strSqlParams[3][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[3][2] = productDesc;
		myLog.debug(" parameter 4 :: " + productDesc);

		strSqlParams[4][0] = "5";
		strSqlParams[4][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[4][2] = uomCode;
		myLog.debug(" parameter 5 :: " + uomCode);

		strSqlParams[5][0] = "6";
		strSqlParams[5][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[5][2] = uomCategoryCode;
		myLog.debug(" parameter 6 :: " + uomCategoryCode);

		strSqlParams[6][0] = "7";
		strSqlParams[6][1] = IDAOConstant.INT_DATATYPE;
		strSqlParams[6][2] = allocationBasedOn;
		myLog.debug(" parameter 7 :: " + allocationBasedOn);

		strSqlParams[7][0] = "8";
		strSqlParams[7][1] = IDAOConstant.BOOLEAN_DATATYPE;
		strSqlParams[7][2] = active;
		myLog.debug(" parameter 8 :: " + active);

		strSqlParams[8][0] = "9";
		strSqlParams[8][1] = IDAOConstant.BOOLEAN_DATATYPE;
		strSqlParams[8][2] = autoReplenish;
		myLog.debug(" parameter 9 :: " + autoReplenish);

		strSqlParams[9][0] = "10";
		strSqlParams[9][1] = IDAOConstant.INT_DATATYPE;
		strSqlParams[9][2] = defaultPricingModel;
		myLog.debug(" parameter 10 :: " + defaultPricingModel);

		strSqlParams[10][0] = "11";
		strSqlParams[10][1] = IDAOConstant.STRING_DATATYPE;
		if (parseProductUOMListString.length() > 0)
			strSqlParams[10][2] = parseProductUOMListString.toString();
		else
			strSqlParams[10][2] = null;
		myLog.debug(" parameter 11 :: " + strSqlParams[10][2]);

		strSqlParams[11][0] = "12";
		strSqlParams[11][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[11][2] = skuId;
		myLog.debug(" parameter 12 :: " + skuId);

		strSqlParams[12][0] = "13";
		strSqlParams[12][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[12][2] = skuName;
		myLog.debug(" parameter 13 :: " + skuName);

		strSqlParams[13][0] = "14";
		strSqlParams[13][1] = IDAOConstant.BOOLEAN_DATATYPE;
		strSqlParams[13][2] = activeSKU;
		myLog.debug(" parameter 14 :: " + activeSKU);

		strSqlParams[14][0] = "15";
		strSqlParams[14][1] = IDAOConstant.BOOLEAN_DATATYPE;
		strSqlParams[14][2] = masterSKU;
		myLog.debug(" parameter 15 :: " + masterSKU);

		strSqlParams[15][0] = "16";
		strSqlParams[15][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[15][2] = userLogin;
		myLog.debug(" parameter 16 :: " + userLogin);

		strSqlParams[16][0] = "17";
		strSqlParams[16][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[16][2] = lastModifiedDate;
		myLog.debug(" parameter 17 :: " + lastModifiedDate);

		strSqlParams[17][0] = "18";
		strSqlParams[17][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[17][2] = weightUomCode;
		myLog.debug(" parameter 18 :: " + weightUomCode);

		strSqlParams[18][0] = "19";
		strSqlParams[18][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[18][2] = skuComments;
		myLog.debug(" parameter 19 :: " + skuComments);

		strSqlParams[19][0] = "20";
		strSqlParams[19][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[19][2] = productNameForBill;
		myLog.debug(" parameter 20 :: " + productNameForBill);

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" Product Definition executeApproveProductSku :: Resultset got ::" + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {

				HashMap<String, Object> resultSetMap = responseMap.get(i);
				handleAndThrowException(resultSetMap);
			}
		}
		return getProductDetails(productOpr);
	}

	public ProductOpr executeDeactivateProductSku(ProductOpr productOpr) throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition BC :: executeDeactivateProductSku starts ");
		ProductOpr productOprRet = new ProductOpr();

		ProductDVO productRecord = productOpr.getProductRecord();
		Long productId = productRecord.getId();
		Long skuId = productRecord.getProductSkuRecord().getId();
		Boolean active = false;
		String userLogin = productRecord.getUserLogin();
		String lastModifiedDate = null;
		if (productRecord.getAuditAttributes().getLastModifiedDate() != null)
			lastModifiedDate = productRecord.getAuditAttributes().getLastModifiedDate().toString();

		String activeFlag = (String) productOpr.getApplicationFlags().getApplicationFlagMap().get("DEACTIVATE_FLAG");
		myLog.debug(" activeFlag ---> " + activeFlag);

		if ("PRODUCT".equals(activeFlag))
			active = !productOpr.getProductRecord().getActive();
		else
			active = !productOpr.getProductRecord().getProductSkuRecord().getActive();

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ProductDefinitionSqlTemplate.ACTIVATE_DEACTIVATE_PRODUCT_SKU);

		Object strSqlParams[][] = new Object[5][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[0][2] = productId;
		myLog.debug(" parameter 1 :: " + productId);

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[1][2] = skuId;
		myLog.debug(" parameter 2 :: " + skuId);

		strSqlParams[2][0] = "3";
		strSqlParams[2][1] = IDAOConstant.BOOLEAN_DATATYPE;
		strSqlParams[2][2] = active;
		myLog.debug(" parameter 3 :: " + active);

		strSqlParams[3][0] = "4";
		strSqlParams[3][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[3][2] = userLogin;
		myLog.debug(" parameter 4 :: " + userLogin);

		strSqlParams[4][0] = "5";
		strSqlParams[4][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[4][2] = lastModifiedDate;
		myLog.debug(" parameter 5 :: " + lastModifiedDate);

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" Product Definition executeDeactivateProductSku :: Resultset got ::" + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {

				HashMap<String, Object> resultSetMap = responseMap.get(i);
				handleAndThrowException(resultSetMap);

				setAuditAttributes(productOprRet.getProductRecord(), resultSetMap);
			}
		}
		productOpr = getCatalogMappingList(productOpr);
		productOprRet.getProductRecord().setProductCatalogMappingList(
				productOpr.getProductRecord().getProductCatalogMappingList());

		return productOprRet;
	}

	public ProductOpr modifyProductSKU(ProductOpr productOpr) throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition BC :: modifyProductSKU starts ");

		ProductDVO productRecord = productOpr.getProductRecord();
		Long productId = productRecord.getId();
		Long skuId = productRecord.getProductSkuRecord().getId();
		String reasonCode = productRecord.getModifyReasonRecord().getCode();
		String modifyDescription = productRecord.getModifyDescription();
		String userLogin = productRecord.getUserLogin();
		String lastModifiedDate = null;
		if (productRecord.getAuditAttributes().getLastModifiedDate() != null)
			lastModifiedDate = productRecord.getAuditAttributes().getLastModifiedDate().toString();

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ProductDefinitionSqlTemplate.MODIFY_PRODUCTS_SKU);

		Object strSqlParams[][] = new Object[6][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[0][2] = productId;
		myLog.debug(" parameter 1 :: " + productId);

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[1][2] = skuId;
		myLog.debug(" parameter 2 :: " + skuId);

		strSqlParams[2][0] = "3";
		strSqlParams[2][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[2][2] = reasonCode;
		myLog.debug(" parameter 3 :: " + reasonCode);

		strSqlParams[3][0] = "4";
		strSqlParams[3][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[3][2] = modifyDescription;
		myLog.debug(" parameter 4 :: " + modifyDescription);

		strSqlParams[4][0] = "5";
		strSqlParams[4][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[4][2] = userLogin;
		myLog.debug(" parameter 5 :: " + userLogin);

		strSqlParams[5][0] = "6";
		strSqlParams[5][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[5][2] = lastModifiedDate;
		myLog.debug(" parameter 6 :: " + lastModifiedDate);

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" Product Definition modifyProductSKU :: Resultset got ::" + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {

				HashMap<String, Object> resultSetMap = responseMap.get(i);
				handleAndThrowException(resultSetMap);

				if (resultSetMap.get("product_id") != null)
					productOpr.getProductRecord().setId(Long.valueOf(resultSetMap.get("product_id").toString()));
				if (resultSetMap.get("sku_id") != null)
					productOpr.getProductRecord().getProductSkuRecord()
							.setId(Long.valueOf(resultSetMap.get("sku_id").toString()));
			}
		}

		return getProductDetails(productOpr);
	}

	public ProductDefinitionOpr getSkuDetailsReport(ProductDefinitionOpr productDefinitionOpr)
			throws FrameworkException, BusinessException {

		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition BC :: getSkuDetails starts ");

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ProductDefinitionSqlTemplate.GET_PRODUCTS_SKU_DETAILS_REPORT);

		Object strSqlParams[][] = new Object[0][0];

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);

		// Result-set for Header
		HashMap resultHeaderMap = (HashMap) daoResult.getMultipleResultSet().get("hdr");
		myLog.debug("resultHeaderMap+++" + resultHeaderMap);

		// Result-set for Details
		HashMap resultDetailsMap = (HashMap) daoResult.getMultipleResultSet().get("dtl");
		myLog.debug("resultDetailsMap+++" + resultDetailsMap);

		// Result-set for Report
		HashMap resultReportMap = (HashMap) daoResult.getMultipleResultSet().get("csv");
		myLog.debug("resultReportMap+++" + resultReportMap);

		// code for getting columns names from 'hdr' result set and separating
		// them based on ','
		ArrayList<String> tempList1 = new ArrayList<String>();
		if (resultHeaderMap != null && !resultHeaderMap.isEmpty()) {
			for (int i = 0; i < resultHeaderMap.size(); i++) {
				HashMap innerMap = (HashMap) resultHeaderMap.get(Integer.valueOf(i));

				if (innerMap.get("@v_sql_statement_3") != null) {
					String columnsBuffer = (String) innerMap.get("@v_sql_statement_3");

					if (columnsBuffer != null && columnsBuffer.length() > 0) {
						// for getting value for header in format: Column
						// Name~column_name
						String[] arr1 = columnsBuffer.split(",");
						if (arr1 != null && arr1.length > 0) {
							for (int j = 0; j < arr1.length; j++) {
								tempList1.add(arr1[j].trim());
							}
						}
					}
				}
			}
		}
		myLog.debug("tempList1+++" + tempList1);

		// code for generating HEADER list
		// Separating the string - 'Column Name~column_name' based on '~' and
		// adding them in two different list
		// 1.columnsList - is for columns to be displayed on screen and
		// 2.columnsListForDetails - is for fetching column value from 'dtl'
		// result set
		ArrayList<String> columnsList = new ArrayList<String>();
		ArrayList<String> columnsListForDetails = new ArrayList<String>();
		if (tempList1 != null && tempList1.size() > 0) {
			for (int i = 0; i < tempList1.size(); i++) {
				String[] tempArr = tempList1.get(i).split("~");
				if (tempArr != null && tempArr.length == 2) {
					columnsList.add(tempArr[0].trim());
					columnsListForDetails.add(tempArr[1].trim());
				}
			}
			myLog.debug("columnsList+++" + columnsList);
			myLog.debug("columnsListForDetails+++" + columnsListForDetails);
		}

		// code for generating DETAILS list
		ArrayList<ArrayList<ProductSkuDVO>> mainList = new ArrayList<ArrayList<ProductSkuDVO>>();
		if (resultDetailsMap != null && !resultDetailsMap.isEmpty()) {
			for (int i = 0; i < resultDetailsMap.size(); i++) {
				HashMap innerMap = (HashMap) resultDetailsMap.get(Integer.valueOf(i));
				ArrayList<ProductSkuDVO> detailsList = new ArrayList<ProductSkuDVO>();

				if (columnsListForDetails != null && columnsListForDetails.size() > 0) {
					for (int j = 0; j < columnsListForDetails.size(); j++) {
						ProductSkuDVO listDVO = new ProductSkuDVO();
						if (innerMap.get(columnsListForDetails.get(j)) != null) {
							listDVO.setCode(innerMap.get(columnsListForDetails.get(j)).toString());
							// myLog.debug("COL IN BC+++" +
							// columnsListForDetails.get(j));
							// myLog.debug("IN BC VALUE+++" +
							// innerMap.get(columnsListForDetails.get(j)));
						} else {
							listDVO.setCode(null);
							// myLog.debug("COL IN BC+++" +
							// columnsListForDetails.get(j));
							// myLog.debug("IN BC VALUE+++" +
							// innerMap.get(columnsListForDetails.get(j)));
						}
						detailsList.add(listDVO);
					}
					mainList.add(detailsList);
				}
			}
		}

		// code for generating CSV list
		ArrayList<ProductSkuDVO> reportList = new ArrayList<ProductSkuDVO>();
		if (resultReportMap != null && !resultReportMap.isEmpty()) {
			for (int i = 0; i < resultReportMap.size(); i++) {
				HashMap innerMap = (HashMap) resultReportMap.get(Integer.valueOf(i));
				ProductSkuDVO listDVO = new ProductSkuDVO();
				if (innerMap.get("all_columns") != null) {
					listDVO.setCode(innerMap.get("all_columns").toString());
				}
				reportList.add(listDVO);

				// productDefinitionOpr.getProductSkuRecord().setCode("");
			}
		}

		productDefinitionOpr.getApplicationFlags().getApplicationFlagMap().put("HEADER", columnsList);
		productDefinitionOpr.getApplicationFlags().getApplicationFlagMap().put("DETAILS", mainList);
		productDefinitionOpr.getApplicationFlags().getApplicationFlagMap().put("CSV", reportList);
		return productDefinitionOpr;
	}

	public List<Object> getSuggestedVendorStyleNumberBasedOnVendor(ProductVendorMappingDVO productVendorMappingRecord)
			throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition BC :: getSuggestedVendorStyleNumberBasedOnVendor starts ");

		String vendorCode = productVendorMappingRecord.getVendorRecord().getCode();
		// Long vendorId = productVendorMappingRecord.getVendorRecord().getId();
		String vendorStyleNumber = productVendorMappingRecord.getVendorStyleNumber();

		if (vendorStyleNumber != null)
			vendorStyleNumber += "%";
		else
			vendorStyleNumber = "%";

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ProductDefinitionSqlTemplate.GET_SUGGESTED_VENDOR_STYLE_NUMBER);

		Object strSqlParams[][] = new Object[2][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[0][2] = vendorCode;
		myLog.debug(" parameter 1 :: " + vendorCode);

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[1][2] = vendorStyleNumber;
		myLog.debug(" parameter 2 :: " + vendorStyleNumber);

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" getSuggestedVendorStyleNumberBasedOnVendor :: Resultset got ::" + responseMap);

		List<Object> productStyleNoList = new ArrayList<Object>();
		if (responseMap.size() > 0) {

			for (int i = 0; i < responseMap.size(); i++) {

				HashMap<String, Object> resultSetMap = responseMap.get(i);

				ProductVendorMappingDVO productVendorMappingDVO = new ProductVendorMappingDVO();
				productVendorMappingDVO.setCode((String) resultSetMap.get("vendor_style_number"));
				productVendorMappingDVO.setActive(true);

				productStyleNoList.add(productVendorMappingDVO);
			}
		}
		return productStyleNoList;
	}

	public ArrayList<Object> getSuggestedProductsListBasedOnCriteria(ProductDVO productDVO) throws FrameworkException,
			BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition BC :: getSuggestedProductsListBasedOnCriteria starts ");

		String productCode = productDVO.getCode();
		String productName = productDVO.getName();
		String vendorCode = (String) productDVO.getApplicationFlags().getApplicationFlagMap().get("VENDOR_CODE");
		String vendorStyleNumber = (String) productDVO.getApplicationFlags().getApplicationFlagMap()
				.get("VENDOR_STYLE_NO");
		String hierarchyIdStr = (String) productDVO.getApplicationFlags().getApplicationFlagMap().get("HIERARCHY_ID");
		String categoryIdStr = (String) productDVO.getApplicationFlags().getApplicationFlagMap().get("CATEGORY_ID");
		Long hierarchyId = null;
		Long categoryId = null;
		// Long vendorId = null;

		if (hierarchyIdStr != null)
			hierarchyId = Long.valueOf(hierarchyIdStr);
		if (categoryIdStr != null)
			categoryId = Long.valueOf(categoryIdStr);
		// if (vendorIdStr != null)
		// vendorId = Long.valueOf(vendorIdStr);

		String productDetailsFlag = (String) productDVO.getApplicationFlags().getApplicationFlagMap()
				.get("GET_ALL_PRODUCT_DETAILS");
		myLog.debug(" productDetailsFlag " + productDetailsFlag);
		if (productCode != null)
			productCode += "%";
		if (productName != null)
			productName += "%";

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		if (productDetailsFlag == null)
			queryDetailsMap.put(IDAOConstant.SQL_TEXT,
					ProductDefinitionSqlTemplate.GET_SUGGESTED_PRODUCTS_LIST_BASED_ON_CRITERIA);
		else
			queryDetailsMap.put(IDAOConstant.SQL_TEXT, ProductDefinitionSqlTemplate.GET_SUGGESTED_ALL_PRODUCTS_LIST);

		Object strSqlParams[][] = new Object[6][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[0][2] = productCode;
		myLog.debug(" parameter 1 :: " + productCode);

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[1][2] = productName;
		myLog.debug(" parameter 2 :: " + productName);

		strSqlParams[2][0] = "3";
		strSqlParams[2][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[2][2] = vendorCode;
		myLog.debug(" parameter 3 :: " + vendorCode);

		strSqlParams[3][0] = "4";
		strSqlParams[3][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[3][2] = vendorStyleNumber;
		myLog.debug(" parameter 4 :: " + vendorStyleNumber);

		strSqlParams[4][0] = "5";
		strSqlParams[4][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[4][2] = hierarchyId;
		myLog.debug(" parameter 5 :: " + hierarchyId);

		strSqlParams[5][0] = "6";
		strSqlParams[5][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[5][2] = categoryId;
		myLog.debug(" parameter 6 :: " + categoryId);

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" getSuggestedProductsListBasedOnCriteria :: Resultset got ::" + responseMap);

		ArrayList<Object> productList = new ArrayList<Object>();
		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {
				HashMap<String, Object> resultSetMap = responseMap.get(i);
				ProductDVO productRecord = new ProductDVO();
				if (resultSetMap.get("product_id") != null)
					productRecord.setId(Long.valueOf(resultSetMap.get("product_id").toString()));

				productRecord.setCode((String) resultSetMap.get("product_code"));
				productRecord.setName((String) resultSetMap.get("product_name"));
				productRecord.setDescription((String) resultSetMap.get("product_description"));
				productRecord.getUomRecord().setCode((String) resultSetMap.get("uom_code"));
				productRecord.getUomRecord().setName((String) resultSetMap.get("uom_name"));
				productRecord.getWeightUomRecord().setCode((String) resultSetMap.get("weight_uom_code"));
				productRecord.getWeightUomRecord().setName((String) resultSetMap.get("weight_uom_name"));
				if (resultSetMap.get("allocation_based_on_seq_no") != null)
					productRecord.getAllocationBasedOn().setSequenceNumber(
							Integer.valueOf(resultSetMap.get("allocation_based_on_seq_no").toString()));

				if (resultSetMap.get("product_hierarchy_id") != null) {
					ProductHierarchyMappingDVO productHierarchyMappingDVO = new ProductHierarchyMappingDVO();
					productHierarchyMappingDVO.getProductHierarchyRecord().setId(
							Long.valueOf(resultSetMap.get("product_hierarchy_id").toString()));
					productHierarchyMappingDVO.getProductHierarchyRecord().setCode(
							(String) resultSetMap.get("product_hierarchy_code"));
					productHierarchyMappingDVO.getProductHierarchyRecord().setName(
							(String) resultSetMap.get("product_hierarchy_name"));
					productRecord.getProductHierarchyMappingList().add(productHierarchyMappingDVO);
				}

				if (resultSetMap.get("product_category_id") != null)
					productRecord.getProductCategoryRecord().setId(
							Long.valueOf(resultSetMap.get("product_category_id").toString()));
				productRecord.getProductCategoryRecord().setName((String) resultSetMap.get("product_category_name"));

				productList.add(productRecord);
			}
		}
		return productList;
	}

	public ArrayList<ProductPropertiesMappingDVO> getPropertiesMappingListBasedOnCategoryHierarchy(ProductDVO productDVO)
			throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition BC :: getPropertiesMappingListBasedOnCategoryHierarchy starts ");

		Long hierarchyId = null;
		Long categoryId = productDVO.getProductCategoryRecord().getId();
		String hierarchyIdStr = (String) productDVO.getApplicationFlags().getApplicationFlagMap().get("HIERARCHY_ID");
		Long productId = productDVO.getId();

		if (hierarchyIdStr != null)
			hierarchyId = Long.valueOf(hierarchyIdStr);

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT,
				ProductDefinitionSqlTemplate.GET_PROPERTIES_MAPPING_LIST_BASED_ON_CATEGORY_HIERARCHY);

		Object strSqlParams[][] = new Object[3][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[0][2] = hierarchyId;
		myLog.debug(" parameter 1 :: " + hierarchyId);

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[1][2] = categoryId;
		myLog.debug(" parameter 2 :: " + categoryId);

		strSqlParams[2][0] = "3";
		strSqlParams[2][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[2][2] = productId;
		myLog.debug(" parameter 3 :: " + productId);

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" Product Definition getPropertiesMappingListBasedOnCategoryHierarchy :: Resultset got ::"
				+ responseMap);

		ArrayList<ProductPropertiesMappingDVO> productPropertiesMappingList = new ArrayList<ProductPropertiesMappingDVO>();
		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {

				HashMap<String, Object> resultSetMap = responseMap.get(i);
				Integer valueTypeSequenceNumber = 0;

				ProductPropertiesMappingDVO productPropertiesMappingRecord = new ProductPropertiesMappingDVO();

				if (resultSetMap.get("product_properties_id") != null)
					productPropertiesMappingRecord.getProductPropertiesRecord().setId(
							Long.valueOf(resultSetMap.get("product_properties_id").toString()));
				productPropertiesMappingRecord.getProductPropertiesRecord().setCode(
						(String) resultSetMap.get("product_property_code"));
				productPropertiesMappingRecord.getProductPropertiesRecord().setName(
						(String) resultSetMap.get("product_property_name"));

				productPropertiesMappingRecord.getProductPropertiesRecord().getUomRecord()
						.setName((String) resultSetMap.get("product_property_uom"));

				if (resultSetMap.get("value_type") != null)
					valueTypeSequenceNumber = Integer.valueOf(resultSetMap.get("value_type").toString());
				productPropertiesMappingRecord.getProductPropertiesRecord().getValueType()
						.setSequenceNumber(valueTypeSequenceNumber);

				if (CommonConstant.ParameterSequenceNumber.TWO.equals(valueTypeSequenceNumber)) {
					String productPropertyValues = (String) resultSetMap.get("product_property_values");
					if (productPropertyValues != null) {
						String[] propertyValues = productPropertyValues.split(",");
						for (String value : propertyValues) {
							if (value != null && value.trim().length() > 0) {

								ProductPropertiesValuesDVO productPropertiesValuesDVO = new ProductPropertiesValuesDVO();
								productPropertiesValuesDVO.setPropertyValue(value);
								productPropertiesMappingRecord.getProductPropertiesRecord()
										.getProductPropertiesValuesList().add(productPropertiesValuesDVO);
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

								ProductPropertiesMappingDVO productPropertiesMappingDVO = new ProductPropertiesMappingDVO();
								productPropertiesMappingDVO.setCode(value);
								productPropertiesMappingDVO.setName(value);
								productPropertiesMappingRecord.getSuggestedValuesList()
										.add(productPropertiesMappingDVO);
							}
						}
					}
				}

				productPropertiesMappingList.add(productPropertiesMappingRecord);
			}
		}
		return productPropertiesMappingList;
	}

	public ArrayList<ProductSkuDVO> searchProductSkuDetails(ProductDVO productRecord) throws FrameworkException,
			BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition BC :: searchProductSkuDetails starts ");

		Long productId = productRecord.getId();
		String vendorCode = (String) productRecord.getApplicationFlags().getApplicationFlagMap().get("VENDOR_CODE");
		String vendorStyleNumber = (String) productRecord.getApplicationFlags().getApplicationFlagMap()
				.get("VENDOR_STYLE_NO");
		StringBuffer parseProductPropertiesString = new StringBuffer();

		// Long vendorId = null;
		// if (vendorIdStr != null)
		// vendorId = Long.valueOf(vendorIdStr);

		if (!productRecord.getProductPropertiesMappingList().isEmpty()) {
			for (ProductPropertiesMappingDVO productPropertiesMappingRecord : productRecord
					.getProductPropertiesMappingList()) {
				if (!productPropertiesMappingRecord.getOperationalAttributes().getRecordDeleted()) {

					Long propertyId = productPropertiesMappingRecord.getProductPropertiesRecord().getId();
					String propertyValue = productPropertiesMappingRecord.getPropertyValue();
					Integer valueTypeSequenceNumber = productPropertiesMappingRecord.getProductPropertiesRecord()
							.getValueType().getSequenceNumber();

					if (!CommonConstant.ParameterSequenceNumber.THREE.equals(valueTypeSequenceNumber)) {

						if (propertyId != null && propertyValue != null) {

							parseProductPropertiesString.append(propertyId);
							parseProductPropertiesString.append("~");

							parseProductPropertiesString.append(propertyValue);
							parseProductPropertiesString.append(";");
						}

					} else {

						int size = productPropertiesMappingRecord.getPropertyValuesList().size();
						if (size > 0) {

							for (int i = 0; i < size; i++) {
								ProductPropertiesMappingDVO productPropertiesMappingDVO = productPropertiesMappingRecord
										.getPropertyValuesList().get(i);
								propertyValue = productPropertiesMappingDVO.getName();
								if (propertyId != null && propertyValue != null) {

									parseProductPropertiesString.append(propertyId);
									parseProductPropertiesString.append("~");

									parseProductPropertiesString.append(propertyValue);
									parseProductPropertiesString.append(";");
								}
							}
						}
					}
				}
			}
		}
		if (parseProductPropertiesString != null && parseProductPropertiesString.length() > 1) {
			// this is to remove the last ; sign
			parseProductPropertiesString.deleteCharAt(parseProductPropertiesString.length() - 1);
		}

		String skuDetailsFlag = (String) productRecord.getApplicationFlags().getApplicationFlagMap()
				.get("GET_ALL_SKU_DETAILS");

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		if (skuDetailsFlag == null)
			queryDetailsMap.put(IDAOConstant.SQL_TEXT, ProductDefinitionSqlTemplate.SEARCH_PRODUCT_SKU_DETAILS);
		else
			queryDetailsMap.put(IDAOConstant.SQL_TEXT, ProductDefinitionSqlTemplate.SEARCH_PRODUCT_ALL_SKU_DETAILS);

		Object strSqlParams[][] = new Object[4][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[0][2] = productId;
		myLog.debug(" parameter 1 :: " + productId);

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[1][2] = vendorCode;
		myLog.debug(" parameter 2 :: " + vendorCode);

		strSqlParams[2][0] = "3";
		strSqlParams[2][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[2][2] = vendorStyleNumber;
		myLog.debug(" parameter 3 :: " + vendorStyleNumber);

		strSqlParams[3][0] = "4";
		strSqlParams[3][1] = IDAOConstant.STRING_DATATYPE;
		if (parseProductPropertiesString.length() > 0)
			strSqlParams[3][2] = parseProductPropertiesString.toString();
		else
			strSqlParams[3][2] = null;
		myLog.debug(" parameter 4 :: " + strSqlParams[3][2]);

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" searchProductSkuDetails :: Resultset got ::" + responseMap);

		ArrayList<ProductSkuDVO> productSkuList = new ArrayList<ProductSkuDVO>();
		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {

				HashMap<String, Object> resultSetMap = responseMap.get(i);
				handleAndThrowException(resultSetMap);

				ProductSkuDVO productSkuDVO = new ProductSkuDVO();

				if (resultSetMap.get("product_sku_id") != null)
					productSkuDVO.setId(Long.valueOf(resultSetMap.get("product_sku_id").toString()));
				productSkuDVO.setCode((String) resultSetMap.get("sku_code"));
				productSkuDVO.setDescription((String) resultSetMap.get("sku_description"));

				productSkuList.add(productSkuDVO);
			}
		} else {
			throw new BusinessException("no_data_from_db_excep_msg");
		}
		return productSkuList;
	}

	public ProductDVO getProductsListBasedOnVendorStyleNo(ProductVendorMappingDVO productVendorMappingDVO)
			throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition BC :: getProductsListBasedOnVendorStyleNo starts ");

		String vendorCode = productVendorMappingDVO.getVendorRecord().getCode();
		// Long vendorId = productVendorMappingDVO.getVendorRecord().getId();
		String vendorStyleNumber = productVendorMappingDVO.getVendorStyleNumber();

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT,
				ProductDefinitionSqlTemplate.GET_PRODUCTS_DETAILS_BASED_ON_VENDOR_STYLE_NO);

		Object strSqlParams[][] = new Object[2][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[0][2] = vendorCode;
		myLog.debug(" parameter 1 :: " + vendorCode);

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[1][2] = vendorStyleNumber;
		myLog.debug(" parameter 2 :: " + vendorStyleNumber);

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" getProductsListBasedOnVendorStyleNo :: Resultset got ::" + responseMap);

		ProductDVO productRecord = new ProductDVO();
		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {
				HashMap<String, Object> resultSetMap = responseMap.get(i);

				Long productId = null;
				if (resultSetMap.get("product_id") != null)
					productId = Long.valueOf(resultSetMap.get("product_id").toString());

				if (productId != null) {
					productRecord.setId(productId);
					productRecord.setCode((String) resultSetMap.get("product_code"));
					productRecord.setName((String) resultSetMap.get("product_name"));
					productRecord.setDescription((String) resultSetMap.get("product_description"));
					productRecord.getUomRecord().setCode((String) resultSetMap.get("uom_code"));
					productRecord.getWeightUomRecord().setCode((String) resultSetMap.get("weight_uom_code"));

					if (resultSetMap.get("product_hierarchy_id") != null) {
						ProductHierarchyMappingDVO productHierarchyMappingDVO = new ProductHierarchyMappingDVO();
						productHierarchyMappingDVO.getProductHierarchyRecord().setId(
								Long.valueOf(resultSetMap.get("product_hierarchy_id").toString()));
						productHierarchyMappingDVO.getProductHierarchyRecord().setCode(
								(String) resultSetMap.get("product_hierarchy_code"));
						productHierarchyMappingDVO.getProductHierarchyRecord().setName(
								(String) resultSetMap.get("product_hierarchy_name"));
						productRecord.getProductHierarchyMappingList().add(productHierarchyMappingDVO);
					}

					if (resultSetMap.get("product_category_id") != null)
						productRecord.getProductCategoryRecord().setId(
								Long.valueOf(resultSetMap.get("product_category_id").toString()));
					productRecord.getProductCategoryRecord()
							.setName((String) resultSetMap.get("product_category_name"));
				}
			}
		}
		return productRecord;
	}

	public ProductDVO getUOMListMappedToProduct(ProductDVO productDVO) throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition BC :: getUOMListMappedToProduct starts ");

		Long productId = productDVO.getId();

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ProductDefinitionSqlTemplate.GET_UOM_LIST_MAPPED_TO_PRODUCT);

		Object strSqlParams[][] = new Object[1][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[0][2] = productId;
		myLog.debug(" parameter 1 :: " + productId);

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" getUOMListMappedToProduct :: Resultset got ::" + responseMap);

		ProductDVO productRecord = new ProductDVO();
		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {
				HashMap<String, Object> resultSetMap = responseMap.get(i);

				UomDVO uomRecord = new UomDVO();
				uomRecord.setCode((String) resultSetMap.get("uom_code"));
				uomRecord.setName((String) resultSetMap.get("uom_name"));
				uomRecord.setDescription((String) resultSetMap.get("uom_description"));
				productRecord.getUomList().add(uomRecord);
			}
		}
		return productRecord;
	}

	public ArrayList<Object> getSuggestedSKUListBasedOnVendorStyleNo(ProductSkuDVO productSkuDVO)
			throws FrameworkException, BusinessException {

		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition BC :: getSuggestedSKUListBasedOnVendorStyleNo starts ");

		String skuDescription = productSkuDVO.getDescription();
		Long productId = productSkuDVO.getMappedProductRecord().getId();
		String vendorCode = (String) productSkuDVO.getApplicationFlags().getApplicationFlagMap().get("VENDOR_CODE");
		String vendorStyleNumber = (String) productSkuDVO.getApplicationFlags().getApplicationFlagMap()
				.get("VENDOR_STYLE_NO");

		if (skuDescription != null)
			skuDescription += "%";
		else
			skuDescription = "%";

		// Long vendorId = null;
		// if (vendorIdStr != null)
		// vendorId = Long.valueOf(vendorIdStr);

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT,
				ProductDefinitionSqlTemplate.GET_SUGGESTED_SKU_LIST_BASED_ON_VENDOR_STYLE_NO);

		Object strSqlParams[][] = new Object[4][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[0][2] = vendorCode;
		myLog.debug(" parameter 1 :: " + vendorCode);

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[1][2] = vendorStyleNumber;
		myLog.debug(" parameter 2 :: " + vendorStyleNumber);

		strSqlParams[2][0] = "3";
		strSqlParams[2][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[2][2] = productId;
		myLog.debug(" parameter 3 :: " + productId);

		strSqlParams[3][0] = "4";
		strSqlParams[3][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[3][2] = skuDescription;
		myLog.debug(" parameter 4 :: " + skuDescription);

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" getSuggestedSKUListBasedOnVendorStyleNo :: Resultset got ::" + responseMap);

		ArrayList<Object> productSkuList = new ArrayList<Object>();
		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {
				HashMap<String, Object> resultSetMap = responseMap.get(i);
				ProductSkuDVO productSkuRecord = new ProductSkuDVO();
				if (resultSetMap.get("product_sku_id") != null)
					productSkuRecord.setId(Long.valueOf(resultSetMap.get("product_sku_id").toString()));

				productSkuRecord.setCode((String) resultSetMap.get("sku_code"));
				productSkuRecord.setName((String) resultSetMap.get("sku_name"));
				productSkuRecord.setDescription((String) resultSetMap.get("sku_description"));
				productSkuList.add(productSkuRecord);
			}
		}
		return productSkuList;
	}

	public ArrayList<Object> getSuggestedOtherSKUList(ProductSkuDVO productSkuDVO) throws FrameworkException,
			BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition BC :: getSuggestedOtherSKUList starts ");
		String code = productSkuDVO.getCode();
		Long mainProductSkuId = (Long) productSkuDVO.getApplicationFlags().getApplicationFlagMap()
				.get("MAIN_PRODUCT_SKU_ID");

		if (code == null) {
			code = new String();
		}
		code = code.concat("%");

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ProductDefinitionSqlTemplate.GET_SUGGESTED_ACTIVE_SKU_LIST);

		Object strSqlParams[][] = new Object[2][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[0][2] = code;
		myLog.debug(":: parameter 1 - SKU CODE like :: " + code);

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.INT_DATATYPE;
		strSqlParams[1][2] = CommonConstant.ParameterSequenceNumber.ONE;
		myLog.debug(":: parameter 2 - Active :: " + CommonConstant.ParameterSequenceNumber.ONE);

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" getSuggestedOtherSKUList :: Resultset got ::" + responseMap);

		ArrayList<Object> productSkuList = new ArrayList<Object>();
		if (responseMap != null && responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {
				HashMap<String, Object> resultSetMap = responseMap.get(i);
				ProductSkuDVO productSkuRecord = new ProductSkuDVO();
				Long subProductId = null;
				if (resultSetMap.get("product_sku_id") != null) {
					subProductId = Long.valueOf(resultSetMap.get("product_sku_id").toString());

					// not adding main product in list
					if (subProductId != null && mainProductSkuId != null && !subProductId.equals(mainProductSkuId)) {
						productSkuRecord.setId(subProductId);
						if (resultSetMap.get("product_id") != null)
							productSkuRecord.getMappedProductRecord().setId(
									Long.valueOf(resultSetMap.get("product_id").toString()));

						if (resultSetMap.get("sku_version") != null)
							productSkuRecord.setSkuVersion(Integer.valueOf(resultSetMap.get("sku_version").toString()));

						productSkuRecord.getStatusRecord().setCode((String) resultSetMap.get("status_code"));
						productSkuRecord.setCode((String) resultSetMap.get("sku_code"));
						productSkuRecord.setName((String) resultSetMap.get("sku_name"));
						productSkuRecord.setDescription((String) resultSetMap.get("sku_description"));
						productSkuList.add(productSkuRecord);
					}
				}
			}
		}
		return productSkuList;
	}

	public ProductOpr getOtherSkuMappingMappingDetails(ProductOpr productOpr) throws FrameworkException,
			BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition BC :: getOtherSkuMappingMappingDetails starts ");

		Long mainProductSkuId = productOpr.getProductRecord().getProductSkuRecord().getId();
		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ProductDefinitionSqlTemplate.GET_OTHER_SKU_MAPPING_LIST);

		Object strSqlParams[][] = new Object[1][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[0][2] = mainProductSkuId;
		myLog.debug(":: parameter 1 - SKU ID :: " + mainProductSkuId);

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);

		// RESULT SET 1
		HashMap resultMainMap = (HashMap) daoResult.getMultipleResultSet().get("MAIN");
		myLog.debug("MAIN MAP-------->" + resultMainMap);

		// RESULT SET 2
		HashMap resultSkuMap = (HashMap) daoResult.getMultipleResultSet().get("SKU");
		myLog.debug("SKU MAP-------->" + resultSkuMap);

		if (resultMainMap != null && resultMainMap.size() > 0) {
			ArrayList<ProductOtherSKUMappingDVO> skuMappingList = new ArrayList<ProductOtherSKUMappingDVO>();
			for (int i = 0; i < resultMainMap.size(); i++) {
				ProductOtherSKUMappingDVO skuMappingDvo = new ProductOtherSKUMappingDVO();
				HashMap innerMap = (HashMap) resultMainMap.get(Integer.valueOf(i));

				if (innerMap.get("product_sub_product_sku_mapping_id") != null) {
					skuMappingDvo.setId(Long.valueOf(innerMap.get("product_sub_product_sku_mapping_id").toString()));
				}

				if (innerMap.get("main_product_id") != null) {
					skuMappingDvo.getMainProductRecord()
							.setId(Long.valueOf(innerMap.get("main_product_id").toString()));
				}

				if (innerMap.get("main_product_sku_id") != null) {
					skuMappingDvo.getMainProductSkuRecord().setId(
							Long.valueOf(innerMap.get("main_product_sku_id").toString()));
				}

				if (innerMap.get("sub_product_id") != null) {
					skuMappingDvo.getSubProductRecord().setId(Long.valueOf(innerMap.get("sub_product_id").toString()));
					skuMappingDvo.getSubProductSkuRecord().getMappedProductRecord()
							.setId(Long.valueOf(innerMap.get("sub_product_id").toString()));
				}

				if (innerMap.get("sub_product_sku_id") != null) {
					skuMappingDvo.getSubProductSkuRecord().setId(
							Long.valueOf(innerMap.get("sub_product_sku_id").toString()));
				}

				if (innerMap.get("quantity") != null) {
					skuMappingDvo.setSkuQuantity(Float.valueOf(innerMap.get("quantity").toString()));
				}
				skuMappingDvo.getSkuQuantityUOM().getUomCode().setCode((String) innerMap.get("quantity_uom"));

				skuMappingDvo.getSubProductSkuRecord().setCode((String) innerMap.get("sku_code"));
				skuMappingDvo.getSubProductSkuRecord().setName((String) innerMap.get("sku_name"));
				skuMappingDvo.getSubProductSkuRecord().setDescription((String) innerMap.get("sku_description"));
				skuMappingDvo.getSubProductSkuRecord().getStatusRecord().setCode((String) innerMap.get("status_code"));

				if (innerMap.get("sku_version") != null) {
					skuMappingDvo.getSubProductSkuRecord().setSkuVersion(
							Integer.valueOf(innerMap.get("sku_version").toString()));
				}
				setAuditAttributes(skuMappingDvo, innerMap);
				skuMappingList.add(skuMappingDvo);
			}
			productOpr.getProductRecord().setProductOtherSkuMappingList(skuMappingList);
		}

		if (resultSkuMap != null && resultSkuMap.size() > 0) {
			for (int i = 0; i < resultSkuMap.size(); i++) {
				HashMap innerMap = (HashMap) resultSkuMap.get(Integer.valueOf(i));
				if (innerMap.get("product_id") != null) {
					productOpr.getProductRecord().getProductOtherSkuMappingDVO().getMainProductRecord()
							.setId(Long.valueOf(innerMap.get("product_id").toString()));
				}
				if (innerMap.get("product_sku_id") != null) {
					productOpr.getProductRecord().getProductOtherSkuMappingDVO().getMainProductSkuRecord()
							.setId(Long.valueOf(innerMap.get("product_sku_id").toString()));
				}
				productOpr.getProductRecord().getProductOtherSkuMappingDVO().getMainProductSkuRecord()
						.getAuditAttributes().setLastModifiedDate((Date) innerMap.get("modified_date"));

				productOpr.getProductRecord().getProductOtherSkuMappingDVO().getMainProductSkuRecord()
						.getAuditAttributes().setModifiedBy((String) innerMap.get("modified_by"));
			}
		}
		return productOpr;
	}

	public ProductOpr executeSaveOtherSkuMapping(ProductOpr productOpr) throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition BC :: executeSaveOtherSkuMapping starts ");

		Long mainProductId = productOpr.getProductRecord().getProductOtherSkuMappingDVO().getMainProductRecord()
				.getId();
		Long mainProductSkuId = productOpr.getProductRecord().getProductOtherSkuMappingDVO().getMainProductSkuRecord()
				.getId();
		String userLogin = productOpr.getProductRecord().getProductOtherSkuMappingDVO().getUserLogin();
		Date modifiedDate = productOpr.getProductRecord().getProductOtherSkuMappingDVO().getMainProductSkuRecord()
				.getAuditAttributes().getLastModifiedDate();
		if (modifiedDate == null) {
			modifiedDate = new Date();
		}
		StringBuffer parseString = new StringBuffer();

		ArrayList<ProductOtherSKUMappingDVO> otherSkuMappingList = productOpr.getProductRecord()
				.getProductOtherSkuMappingList();
		if (otherSkuMappingList != null && otherSkuMappingList.size() > 0) {
			for (ProductOtherSKUMappingDVO listDvo : otherSkuMappingList) {
				Long productSubProductSkuMappingId = listDvo.getId();
				Long subProductId = listDvo.getSubProductSkuRecord().getMappedProductRecord().getId();
				Long subProductSkuId = listDvo.getSubProductSkuRecord().getId();
				Float skuQuantity = listDvo.getSkuQuantity();
				String quantityUomCode = listDvo.getSkuQuantityUOM().getUomCode().getCode();

				if (productSubProductSkuMappingId != null) {
					parseString.append(productSubProductSkuMappingId);
				}
				parseString.append(",");

				if (subProductId != null) {
					parseString.append(subProductId);
				}
				parseString.append(",");

				if (subProductSkuId != null) {
					parseString.append(subProductSkuId);
				}
				parseString.append(",");

				if (skuQuantity != null) {
					parseString.append(skuQuantity);
				}
				parseString.append(",");

				if (quantityUomCode != null) {
					parseString.append(quantityUomCode);
				}
				parseString.append("~");
			}

			if (parseString != null) {
				// finding last occurrence of last tilda and deleting it
				// string format:
				// mappingId,subProductId,subProductSkuId,skuQuantity,quantityUomCode~
				// mappingId,subProductId,subProductSkuId,skuQuantity,quantityUomCode
				int lastTilda = parseString.lastIndexOf("~");
				if (lastTilda > 0) {
					parseString.deleteCharAt(lastTilda);
				}
				myLog.debug(" ::final parseString :: " + parseString);
			}

			HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
			queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
			queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
			queryDetailsMap.put(IDAOConstant.SQL_TEXT, ProductDefinitionSqlTemplate.SAVE_OTHER_SKU_MAPPING_DETAILS);

			Object strSqlParams[][] = new Object[5][3];

			strSqlParams[0][0] = "1";
			strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
			strSqlParams[0][2] = mainProductId;
			myLog.debug(" ::parameter 1- mainProductId :: " + mainProductId);

			strSqlParams[1][0] = "2";
			strSqlParams[1][1] = IDAOConstant.LONG_DATATYPE;
			strSqlParams[1][2] = mainProductSkuId;
			myLog.debug(" ::parameter 2- mainProductSkuId :: " + mainProductSkuId);

			strSqlParams[2][0] = "3";
			strSqlParams[2][1] = IDAOConstant.STRING_DATATYPE;
			strSqlParams[2][2] = parseString.toString();
			myLog.debug(" ::parameter 3- parseString :: " + parseString.toString());

			strSqlParams[3][0] = "4";
			strSqlParams[3][1] = IDAOConstant.STRING_DATATYPE;
			strSqlParams[3][2] = userLogin;
			myLog.debug(" ::parameter 4- userLogin :: " + userLogin);

			strSqlParams[4][0] = "5";
			strSqlParams[4][1] = IDAOConstant.STRING_DATATYPE;
			strSqlParams[4][2] = modifiedDate.toString();
			myLog.debug(" ::parameter 5- modifiedDate :: " + modifiedDate.toString());

			DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
			HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
			myLog.debug(" save mapping :: Resultset got ::" + responseMap);

			int errorCounter = 0;
			if (responseMap != null && responseMap.size() > 0) {
				for (int i = 0; i < responseMap.size(); i++) {
					HashMap<String, Object> resultSetMap = responseMap.get(i);
					if (resultSetMap.get("p_error_code") != null) {
						errorCounter++;
						handleAndThrowException(resultSetMap);
					}
				}
			}
		}
		return productOpr;
	}

	public ProductOpr getProductSkuBomDetailsList(ProductOpr productOpr) throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition BC :: getProductSkuBomDetailsList starts ");

		Long productId = productOpr.getProductRecord().getId();
		Long productSkuId = productOpr.getProductRecord().getProductSkuRecord().getId();

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ProductDefinitionSqlTemplate.GET_PRODUCT_SKU_BOM_LIST);

		Object strSqlParams[][] = new Object[2][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[0][2] = productId;
		myLog.debug(":: parameter 1 - product ID :: " + productId);

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[1][2] = productSkuId;
		myLog.debug(":: parameter 2 - product SKU ID :: " + productSkuId);

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);

		// RESULT SET 1
		HashMap resultMainMap = daoResult.getMultipleResultSet().get("BOM");
		myLog.debug("MAIN MAP-------->" + resultMainMap);

		// RESULT SET 2
		HashMap resultProductMap = daoResult.getMultipleResultSet().get("PRO");
		myLog.debug("MAIN MAP-------->" + resultProductMap);

		// RESULT SET 3
		HashMap resultProductPropertyMap = daoResult.getMultipleResultSet().get("PROP");
		myLog.debug("PROP MAP-------->" + resultProductPropertyMap);

		if (resultMainMap != null && resultMainMap.size() > 0) {
			ArrayList<ProductItemPropertyMappingDVO> productSkuBomList = new ArrayList<ProductItemPropertyMappingDVO>();
			for (int i = 0; i < resultMainMap.size(); i++) {
				ProductItemPropertyMappingDVO productSkuBomDvo = new ProductItemPropertyMappingDVO();
				HashMap innerMap = (HashMap) resultMainMap.get(Integer.valueOf(i));

				if (innerMap.get("product_sku_bom_header_id") != null) {
					productSkuBomDvo.setId(Long.valueOf(innerMap.get("product_sku_bom_header_id").toString()));
				}
				myLog.debug("ID-------->" + productSkuBomDvo.getId());
				if (innerMap.get("item_category_id") != null) {
					productSkuBomDvo.getItemCategoryRecord().setId(
							Long.valueOf(innerMap.get("item_category_id").toString()));
				}
				productSkuBomDvo.getItemCategoryRecord().setName((String) innerMap.get("item_category_name"));
				productSkuBomDvo.getItemCategoryRecord().setCode((String) innerMap.get("item_category_code"));
				productSkuBomDvo.getItemRecord().setCode((String) innerMap.get("item_code"));
				productSkuBomDvo.getItemRecord().setName((String) innerMap.get("item_name"));
				productSkuBomDvo.getItemRecord().getQuantityUomRecord()
						.setCode((String) innerMap.get("item_quantity_uom_code"));
				productSkuBomDvo.getItemRecord().getWeightUomRecord()
						.setCode((String) innerMap.get("item_weight_uom_code"));

				if (innerMap.get("allocation_based_on") != null) {
					productSkuBomDvo.getItemRecord().getAllocationBasis()
							.setSequenceNumber(Integer.valueOf(innerMap.get("allocation_based_on").toString()));
					productSkuBomDvo.getItemRecord().getAllocationBasedOn()
							.setSequenceNumber(Integer.valueOf(innerMap.get("allocation_based_on").toString()));
				}

				if (innerMap.get("quantity") != null) {
					productSkuBomDvo.setQuantity(Float.valueOf(innerMap.get("quantity").toString()));
				}
				productSkuBomDvo.getQuantityUOM().setCode((String) innerMap.get("quantity_uom"));

				if (innerMap.get("per_unit_weight") != null) {
					productSkuBomDvo.setPerUnitWeight(Float.valueOf(innerMap.get("per_unit_weight").toString()));
				}

				if (innerMap.get("edited_weight") != null) {
					productSkuBomDvo.setEditedWeight(Float.valueOf(innerMap.get("edited_weight").toString()));
				}
				productSkuBomDvo.getWeightUOM().setCode((String) innerMap.get("weight_uom"));

				if (innerMap.get("item_properties_combination_id") != null) {
					productSkuBomDvo.getItemPropertiesRecordEdited().setId(
							Long.valueOf(innerMap.get("item_properties_combination_id").toString()));
				}
				productSkuBomDvo.getItemPropertiesRecordEdited().setValueTypeDescription(
						(String) innerMap.get("item_properties_combination_description"));
				productSkuBomDvo.setCenterStone((Boolean) innerMap.get("is_center_stone"));
				productSkuBomDvo.setLineNumber(i + 1);
				setAuditAttributes(productSkuBomDvo, innerMap);
				productSkuBomList.add(productSkuBomDvo);
			}
			productOpr.getProductRecord().setProductItemPropertyMappingList(productSkuBomList);
		}

		if (resultProductMap != null && resultProductMap.size() > 0) {
			for (int i = 0; i < resultProductMap.size(); i++) {
				HashMap innerMap = (HashMap) resultProductMap.get(Integer.valueOf(i));
				productOpr.getProductRecord().getProductItemPropertyMappingRecord().getAuditAttributes()
						.setLastModifiedDate((Date) innerMap.get("modified_date"));
				productOpr.getProductRecord().getAuditAttributes()
						.setLastModifiedDate((Date) innerMap.get("modified_date"));
				productOpr.getProductRecord().getModifyProductSKURecord()
						.setModifyItem((Boolean) innerMap.get("is_modify_item"));
				productOpr.getIconProductSKURecord().setMapItem((Boolean) innerMap.get("is_modify_item"));
			}
		}

		if (resultProductPropertyMap != null && resultProductPropertyMap.size() > 0) {
			productOpr.getProductRecord().getProductItemPropertyMappingRecord().setMappedPropertiesList(null);
			for (int i = 0; i < resultProductPropertyMap.size(); i++) {
				HashMap innerMap = (HashMap) resultProductPropertyMap.get(Integer.valueOf(i));
				ItemPropertiesMappingDVO mappedPopertyRecord = new ItemPropertiesMappingDVO();
				mappedPopertyRecord.setCode((String) innerMap.get("product_property_code"));
				mappedPopertyRecord.setDefaultPropertyValue((String) innerMap.get("sku_property_value"));
				productOpr.getProductRecord().getProductItemPropertyMappingRecord().getMappedPropertiesList()
						.add(mappedPopertyRecord);
			}
		}

		return productOpr;
	}

	public ProductOpr saveBOMCaptureDetailsForProduct(ProductOpr productOpr) throws FrameworkException,
			BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug(" ::saveBOMCaptureDetailsForProduct starts :: ");
		StringBuffer parseString = new StringBuffer();
		String userLogin = productOpr.getProductRecord().getProductItemPropertyMappingRecord().getUserLogin();
		Long productId = productOpr.getProductRecord().getId();
		Long productSKUId = productOpr.getProductRecord().getProductSkuRecord().getId();
		Date modifiedDate = productOpr.getProductRecord().getAuditAttributes().getLastModifiedDate();
		Boolean modifyItems = productOpr.getProductRecord().getModifyProductSKURecord().getModifyItem();
		if (modifiedDate == null) {
			modifiedDate = new Date();
		}

		ArrayList<ProductItemPropertyMappingDVO> productBomCaptureList = productOpr.getProductRecord()
				.getProductItemPropertyMappingList();
		for (ProductItemPropertyMappingDVO listDVO : productBomCaptureList) {
			Long productSkuBomId = listDVO.getId();
			if (productSkuBomId != null) {
				parseString.append(productSkuBomId.toString());
			}
			parseString.append("~");

			Long itemCategoryId = listDVO.getItemCategoryRecord().getId();
			if (itemCategoryId != null) {
				parseString.append(itemCategoryId.toString());
			}
			parseString.append("~");

			String itemCode = listDVO.getItemRecord().getCode();
			if (itemCode != null && itemCode.trim().length() > 0) {
				parseString.append(itemCode);
			}
			parseString.append("~");

			Float quantity = listDVO.getQuantity();
			if (quantity != null) {
				parseString.append(quantity.toString());
			}
			parseString.append("~");

			String quantityUOM = listDVO.getQuantityUOM().getCode();
			if (quantityUOM != null && quantityUOM.trim().length() > 0) {
				parseString.append(quantityUOM);
			}
			parseString.append("~");

			Float perUnitWeight = listDVO.getPerUnitWeight();
			if (perUnitWeight != null) {
				parseString.append(perUnitWeight.toString());
			}
			parseString.append("~");

			Float editedWeight = listDVO.getEditedWeight();
			if (editedWeight != null) {
				parseString.append(editedWeight.toString());
			}
			parseString.append("~");

			String editedWeightUOM = listDVO.getWeightUOM().getCode();
			if (editedWeightUOM != null && editedWeightUOM.trim().length() > 0) {
				parseString.append(editedWeightUOM);
			}
			parseString.append("~");

			Long itemPropertiesCombinationId = listDVO.getItemPropertiesRecordEdited().getId();
			if (itemPropertiesCombinationId != null) {
				parseString.append(itemPropertiesCombinationId.toString());
			}
			parseString.append("~");

			Boolean isCenterStone = listDVO.getCenterStone();
			if (isCenterStone != null && isCenterStone) {
				parseString.append("1");
			} else {
				parseString.append("0");
			}
			parseString.append("~");

			Boolean recordDeleted = listDVO.getOperationalAttributes().getRecordDeleted();
			if (recordDeleted != null && recordDeleted) {
				parseString.append("1");
			} else {
				parseString.append("0");
			}
			parseString.append("~");

			if (productSkuBomId != null && listDVO.getSkuBomProcessVariationList() != null
					&& listDVO.getSkuBomProcessVariationList().size() > 0) {
				String skuBomProcessVariationString = generateParseStringForPVDetails(listDVO);
				parseString.append(skuBomProcessVariationString);
			}
			parseString.append(";");
		}

		if (parseString != null) {
			// finding last occurrence of last lastsemiColon and deleting it
			int lastsemiColon = parseString.lastIndexOf(";");
			if (lastsemiColon > 0) {
				parseString.deleteCharAt(lastsemiColon);
			}
			myLog.debug(" ::final parseString :: " + parseString);
		}

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ProductDefinitionSqlTemplate.SAVE_PRODUCT_SKU_BOM_DETAILS);

		Object strSqlParams[][] = new Object[6][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[0][2] = productId;
		myLog.debug(" ::parameter 1- productId :: " + productId);

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[1][2] = productSKUId;
		myLog.debug(" ::parameter 2- productSKUId :: " + productSKUId);

		strSqlParams[2][0] = "3";
		strSqlParams[2][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[2][2] = parseString.toString();
		myLog.debug(" ::parameter 3- parseString :: " + parseString.toString());

		strSqlParams[3][0] = "4";
		strSqlParams[3][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[3][2] = userLogin;
		myLog.debug(" ::parameter 4- userLogin :: " + userLogin);

		strSqlParams[4][0] = "5";
		strSqlParams[4][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[4][2] = modifiedDate.toString();
		myLog.debug(" ::parameter 5- modifiedDate :: " + modifiedDate.toString());

		strSqlParams[5][0] = "6";
		strSqlParams[5][1] = IDAOConstant.BOOLEAN_DATATYPE;
		strSqlParams[5][2] = modifyItems;
		myLog.debug(" parameter 6 - modifyItems :: " + modifyItems);

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" save mapping :: Resultset got ::" + responseMap);

		int errorCounter = 0;
		if (responseMap != null && responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {
				HashMap<String, Object> resultSetMap = responseMap.get(i);
				if (resultSetMap.get("p_error_code") != null) {
					errorCounter++;
					handleAndThrowException(resultSetMap);
				}
			}
		}

		return productOpr;
	}

	public ArrayList<Object> getSuggestedProductsListBasedOnCategoryMappingId(ProductDVO productDVO)
			throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition BC :: getSuggestedProductsList starts ");

		String code = productDVO.getCode();
		String name = productDVO.getName();
		Boolean isActive = productDVO.getActive();
		String statusCode = (String) productDVO.getApplicationFlags().getApplicationFlagMap()
				.get("STATUS_NEW_APPROVED");
		String approved = (String) productDVO.getApplicationFlags().getApplicationFlagMap().get("STATUS_APPROVED");
		Long productId = productDVO.getId();
		myLog.debug(" parameter 1 :: " + code);
		myLog.debug(" parameter 2 :: " + name);
		myLog.debug(" parameter 3 :: " + isActive);
		myLog.debug(" parameter 4 :: " + statusCode);
		myLog.debug(" parameter 5 :: " + approved);
		myLog.debug(" parameter 6 :: " + productId);

		StringBuffer dynamicWhere = new StringBuffer();

		int parameterCount = 0;
		if (productId != null) {
			dynamicWhere
					.append(" phcm.product_hierarchy_id = ph.product_hierarchy_id AND  phcm.product_category_id IN "
							+ " (fn_product_get_product_default_category_id(" + productId + ")) ");
			parameterCount++;
		}

		if (code != null && code.trim().length() > 0) {
			code = code.trim().concat("%");
			if (parameterCount > 0) {
				dynamicWhere.append(" AND ph.product_code LIKE '" + code + "'");
			} else {
				dynamicWhere.append(" ph.product_code LIKE '" + code + "'");
			}
			parameterCount++;
		}

		if (name != null && name.trim().length() > 0) {
			name = name.trim().concat("%");
			if (parameterCount > 0) {
				dynamicWhere.append(" AND ph.product_name LIKE '" + name + "'");
			} else {
				dynamicWhere.append(" ph.product_name LIKE '" + name + "'");
			}
			parameterCount++;
		}

		if (isActive == null || !isActive) {
			if (parameterCount > 0) {
				dynamicWhere.append(" AND is_active = 1");
			} else {
				dynamicWhere.append(" is_active = 1");
			}
			parameterCount += 1;
		}

		if (statusCode != null && statusCode.equals("STATUS_NEW_APPROVED")) {
			if (parameterCount > 0) {
				dynamicWhere.append(" AND ph.status_code IN ('" + CommonConstant.StatusCodes.NEW + "','"
						+ CommonConstant.StatusCodes.APPROVED + "')");
			} else {
				dynamicWhere.append(" ph.status_code IN ('" + CommonConstant.StatusCodes.NEW + "','"
						+ CommonConstant.StatusCodes.APPROVED + "')");
			}
			parameterCount++;
		}

		if (approved != null && approved.equals("STATUS_APPROVED")) {
			if (parameterCount > 0) {
				dynamicWhere.append(" AND ph.status_code IN ('" + CommonConstant.StatusCodes.APPROVED + "')");
			} else {
				dynamicWhere.append(" ph.status_code IN ('" + CommonConstant.StatusCodes.APPROVED + "')");
			}
			parameterCount++;
		}

		// to get all data
		if (parameterCount == 0) {
			dynamicWhere.append(" 1 = 1 ");
		}
		dynamicWhere.append(" ORDER BY ph.product_name;");
		myLog.debug("dynamicWhere ::::: " + dynamicWhere);

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT,
				ProductDefinitionSqlTemplate.GET_SUGGESTED_PRODUCTS_LIST_BASED_CATEGORY);

		Object strSqlParams[][] = new Object[0][0];

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, dynamicWhere.toString());
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" getSuggestedProductsList :: Resultset got ::" + responseMap);

		ArrayList<Object> productList = new ArrayList<Object>();
		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {
				HashMap<String, Object> resultSetMap = responseMap.get(i);
				ProductDVO productRecord = new ProductDVO();
				if (resultSetMap.get("product_id") != null)
					productRecord.setId(Long.valueOf(resultSetMap.get("product_id").toString()));

				productRecord.setCode((String) resultSetMap.get("product_code"));
				productRecord.setName((String) resultSetMap.get("product_name"));
				productRecord.setDescription((String) resultSetMap.get("product_description"));
				if (resultSetMap.get("product_version") != null)
					productRecord.setProductVersion(Integer.valueOf(resultSetMap.get("product_version").toString()));

				productList.add(productRecord);
			}
		}
		return productList;
	}

	public ProductOpr getSkuBomProcessVariationDetails(ProductOpr productOpr) throws FrameworkException,
			BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition BC :: getSkuBomProcessVariationDetails starts ");

		Long productSkuBomHeaderId = productOpr.getProductRecord().getProductItemPropertyMappingRecord().getId();

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ProductDefinitionSqlTemplate.GET_SKU_BOM_PROCESS_VARIATION_DETAILS);

		Object strSqlParams[][] = new Object[1][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[0][2] = productSkuBomHeaderId;
		myLog.debug(" parameter 1 ::productSkuBomHeaderId :: " + productSkuBomHeaderId);

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);

		HashMap<Integer, HashMap<String, Object>> resultMap = daoResult.getInvocationResult();
		myLog.debug("PV MAP-------->" + resultMap);

		ArrayList<ProductSkuBomProcessVariationDetailsDVO> productSkuBomProcessVariationDetailsList = new ArrayList<ProductSkuBomProcessVariationDetailsDVO>();
		if (resultMap != null && resultMap.size() > 0) {
			for (int i = 0; i < resultMap.size(); i++) {
				ProductSkuBomProcessVariationDetailsDVO listDVO = new ProductSkuBomProcessVariationDetailsDVO();
				HashMap<String, Object> resultSetMap = resultMap.get(Integer.valueOf(i));

				if (resultSetMap.get("product_sku_bom_process_variation_details_id") != null) {
					listDVO.setId(Long.valueOf(resultSetMap.get("product_sku_bom_process_variation_details_id")
							.toString()));
				}

				if (resultSetMap.get("product_id") != null) {
					listDVO.getProductRecord().setId(Long.valueOf(resultSetMap.get("product_id").toString()));
				}

				if (resultSetMap.get("product_sku_id") != null) {
					listDVO.getProductSkuRecord().setId(Long.valueOf(resultSetMap.get("product_sku_id").toString()));
				}

				if (resultSetMap.get("item_category_id") != null) {
					listDVO.getItemCategoryRecord()
							.setId(Long.valueOf(resultSetMap.get("item_category_id").toString()));
				}

				listDVO.getItemRecord().setCode((String) resultSetMap.get("item_code"));
				if (resultSetMap.get("item_properties_combination_header_id") != null) {
					listDVO.getItemPropertiesCombinationHeaderRecord().setId(
							Long.valueOf(resultSetMap.get("item_properties_combination_header_id").toString()));
				}

				if (resultSetMap.get("process_id") != null) {
					listDVO.getProcessRecord().setId(Long.valueOf(resultSetMap.get("process_id").toString()));
				}

				if (resultSetMap.get("process_variation_id") != null) {
					listDVO.getProcessVariationMappingRecord().setId(
							Long.valueOf(resultSetMap.get("process_variation_id").toString()));
				}
				listDVO.getProcessVariationMappingRecord().setCode((String) resultSetMap.get("process_variation_code"));
				listDVO.getProcessVariationMappingRecord().setName((String) resultSetMap.get("process_variation_name"));

				if (resultSetMap.get("quantity") != null) {
					listDVO.setQuantity(Float.valueOf(resultSetMap.get("quantity").toString()));
				}
				listDVO.getQuantityUomRecord().setCode((String) resultSetMap.get("quantity_uom"));
				setAuditAttributes(listDVO, resultSetMap);
				productSkuBomProcessVariationDetailsList.add(listDVO);
			}
		}
		productOpr.getProductRecord().getProductItemPropertyMappingRecord()
				.setSkuBomProcessVariationList(productSkuBomProcessVariationDetailsList);

		return productOpr;
	}

	public String generateParseStringForPVDetails(ProductItemPropertyMappingDVO productItemPropertyMappingDVO) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition BC :: generateParseStringForPVDetails starts ");
		StringBuffer parseString = new StringBuffer();
		ArrayList<ProductSkuBomProcessVariationDetailsDVO> skuPVList = productItemPropertyMappingDVO
				.getSkuBomProcessVariationList();

		if (skuPVList != null && skuPVList.size() > 0) {
			for (int i = 0; i < skuPVList.size(); i++) {
				ProductSkuBomProcessVariationDetailsDVO skuPVListRecord = skuPVList.get(i);
				Long skuBomPvId = skuPVListRecord.getId();
				Boolean recordDeleted = skuPVListRecord.getOperationalAttributes().getRecordDeleted();

				if (!recordDeleted) {
					if (skuBomPvId != null) {
						parseString.append(skuBomPvId);
					}
					parseString.append("$");

					Long processId = skuPVListRecord.getProcessRecord().getId();
					if (processId != null) {
						parseString.append(processId);
					}
					parseString.append("$");

					Long processVariationId = skuPVListRecord.getProcessVariationMappingRecord().getId();
					if (processVariationId != null) {
						parseString.append(processVariationId);
					}
					parseString.append("$");

					Float qty = skuPVListRecord.getQuantity();
					if (qty != null) {
						parseString.append(qty);
					}
					parseString.append("$");

					String qtyUOM = skuPVListRecord.getQuantityUomRecord().getCode();
					if (qtyUOM != null) {
						parseString.append(qtyUOM);
					}
					parseString.append("$");

					if (recordDeleted != null && recordDeleted) {
						parseString.append("1");
					} else {
						parseString.append("0");
					}
					parseString.append("^");
				} else if (recordDeleted && skuBomPvId != null) {
					if (skuBomPvId != null) {
						parseString.append(skuBomPvId);
					}
					parseString.append("$");

					Long processId = skuPVListRecord.getProcessRecord().getId();
					if (processId != null) {
						parseString.append(processId);
					}
					parseString.append("$");

					Long processVariationId = skuPVListRecord.getProcessVariationMappingRecord().getId();
					if (processVariationId != null) {
						parseString.append(processVariationId);
					}
					parseString.append("$");

					Float qty = skuPVListRecord.getQuantity();
					if (qty != null) {
						parseString.append(qty);
					}
					parseString.append("$");

					String qtyUOM = skuPVListRecord.getQuantityUomRecord().getCode();
					if (qtyUOM != null) {
						parseString.append(qtyUOM);
					}
					parseString.append("$");

					if (recordDeleted != null && recordDeleted) {
						parseString.append("1");
					} else {
						parseString.append("0");
					}
					parseString.append("^");
				}
			}

			if (parseString.length() > 0) {
				int lastComma = parseString.lastIndexOf("^");
				parseString.deleteCharAt(lastComma);
			}
			myLog.debug("Final Parse String for pv :::::: " + parseString);

		}
		return parseString.toString();
	}

	public List<Object> getSuggestedVendorStyleNumberBasedOnProductSku(
			ProductVendorMappingDVO productVendorMappingRecord) throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition BC :: getSuggestedVendorStyleNumberBasedOnProductSku starts ");

		String vendorCode = productVendorMappingRecord.getVendorRecord().getCode();
		Long productId = productVendorMappingRecord.getProductRecord().getId();
		Long productSkuId = productVendorMappingRecord.getProductRecord().getProductSkuRecord().getId();
		String vendorStyleNumber = productVendorMappingRecord.getVendorStyleNumber();

		if (vendorStyleNumber != null)
			vendorStyleNumber += "%";
		else
			vendorStyleNumber = "%";

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT,
				ProductDefinitionSqlTemplate.GET_SUGGESTED_VENDOR_STYLE_NUMBER_BASED_ON_PRODUCT_SKU);

		Object strSqlParams[][] = new Object[4][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[0][2] = vendorCode;
		myLog.debug(" parameter 1 :: " + vendorCode);

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[1][2] = productId;
		myLog.debug(" parameter 2 :: " + productId);

		strSqlParams[2][0] = "3";
		strSqlParams[2][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[2][2] = productSkuId;
		myLog.debug(" parameter 3 :: " + productSkuId);

		strSqlParams[3][0] = "4";
		strSqlParams[3][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[3][2] = vendorStyleNumber;
		myLog.debug(" parameter 4 :: " + vendorStyleNumber);

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" getSuggestedVendorStyleNumberBasedOnProductSku :: Resultset got ::" + responseMap);

		List<Object> productStyleNoList = new ArrayList<Object>();
		if (responseMap.size() > 0) {

			for (int i = 0; i < responseMap.size(); i++) {

				HashMap<String, Object> resultSetMap = responseMap.get(i);

				ProductVendorMappingDVO productVendorMappingDVO = new ProductVendorMappingDVO();
				productVendorMappingDVO.setCode((String) resultSetMap.get("vendor_style_number"));
				productVendorMappingDVO.setActive(true);

				productStyleNoList.add(productVendorMappingDVO);
			}
		}
		return productStyleNoList;
	}

	public String getPropertyValueCategory(String propertyValue) throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition BC ::getPropertyValueCategory starts ");

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ProductDefinitionSqlTemplate.GET_CATEGORY_FOR_PROPERTY_VALUE);

		Object strSqlParams[][] = new Object[1][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[0][2] = propertyValue;
		myLog.debug(" parameter 1:: propertyValue :: " + propertyValue);

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" getPropertyValueCategory :: Resultset got ::" + responseMap);

		String categoryCode = null;
		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {
				HashMap<String, Object> resultSetMap = responseMap.get(i);
				categoryCode = (String) resultSetMap.get("category_code");
			}
		}
		return categoryCode;
	}

	public ProductDVO getProductSpecificDetails(ProductDVO productDVO) throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition BC ::getProductSpecificDetails starts ");

		Long productId = productDVO.getId();
		Long productSkuId = productDVO.getProductSkuRecord().getId();

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ProductDefinitionSqlTemplate.GET_PRODUCT_SPECIFIC_DETAILS);

		Object strSqlParams[][] = new Object[2][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[0][2] = productId;
		myLog.debug(" parameter 1:: productId :: " + productId);

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[1][2] = productSkuId;
		myLog.debug(" parameter 2:: productSkuId :: " + productSkuId);

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> headerResponseMap = daoResult.getMultipleResultSet().get("PRO");
		HashMap<Integer, HashMap<String, Object>> skuResponseMap = daoResult.getMultipleResultSet().get("SKU");
		HashMap<Integer, HashMap<String, Object>> detailResponseMap = daoResult.getMultipleResultSet().get("UOM");
		myLog.debug(" getProductSpecificDetails :: Resultset got header ::" + headerResponseMap);
		myLog.debug(" getProductSpecificDetails :: Resultset got uom :: " + detailResponseMap);

		if (headerResponseMap != null && headerResponseMap.size() > 0) {
			int size = headerResponseMap.size();
			for (int i = 0; i < size; i++) {
				HashMap<String, Object> resultSetMap = headerResponseMap.get(i);
				productDVO.getUomRecord().setCode((String) resultSetMap.get("uom_code"));
				productDVO.getUomRecord().getUomCategory().setCode((String) resultSetMap.get("uom_category_code"));
				productDVO.getAuditAttributes().setLastModifiedDate((Date) resultSetMap.get("modified_date"));
				productDVO.getWeightUomRecord().setCode((String) resultSetMap.get("weight_uom_code"));
				productDVO.setProductNameForBill((String) resultSetMap.get("product_name_for_bill"));
				if (resultSetMap.get("allocation_based_on") != null) {
					productDVO.getAllocationBasedOn().setParameterID(
							Integer.valueOf(resultSetMap.get("allocation_based_on").toString()));
				}
				if (resultSetMap.get("pricing_model") != null) {
					productDVO.getDefaultPricingModel().setParameterID(
							Integer.valueOf(resultSetMap.get("pricing_model").toString()));
				}
				if (resultSetMap.get("is_auto_replenish") != null) {
					productDVO.setAutoReplenish((Boolean) resultSetMap.get("is_auto_replenish"));
				}
			}
		}

		if (skuResponseMap != null && skuResponseMap.size() > 0) {
			int size = skuResponseMap.size();
			for (int i = 0; i < size; i++) {
				HashMap<String, Object> resultSetMap = skuResponseMap.get(i);
				productDVO.getProductSkuRecord().getAuditAttributes()
						.setModifiedBy((String) resultSetMap.get("modified_by"));
				productDVO.getProductSkuRecord().getAuditAttributes()
						.setLastModifiedDate((Date) resultSetMap.get("modified_date"));
			}
		}

		if (detailResponseMap != null && detailResponseMap.size() > 0) {
			int size = detailResponseMap.size();
			for (int i = 0; i < size; i++) {
				HashMap<String, Object> resultSetMap = detailResponseMap.get(i);
				UomDVO uomRecord = new UomDVO();
				uomRecord.setCode((String) resultSetMap.get("uom_code"));
				uomRecord.setName((String) resultSetMap.get("uom_name"));
				productDVO.getUomList().add(uomRecord);
			}
		}
		return productDVO;
	}

	public ProductDVO getProductSkuPropertySpecificDetails(ProductDVO productDVO) throws FrameworkException,
			BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Definition BC ::getProductSkuPropertySpecificDetails starts ");

		Long productId = productDVO.getId();
		Long productSkuId = productDVO.getProductSkuRecord().getId();

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT,
				ProductDefinitionSqlTemplate.GET_PRODUCT_SKU_PROPERTY_SPECIFIC_DETAILS);

		Object strSqlParams[][] = new Object[2][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[0][2] = productId;
		myLog.debug(" parameter 1:: productId :: " + productId);

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[1][2] = productSkuId;
		myLog.debug(" parameter 1:: productSkuId :: " + productSkuId);

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> headerResponseMap = daoResult.getInvocationResult();
		myLog.debug(" getProductSkuPropertySpecificDetails :: Resultset got uom :: " + headerResponseMap);

		if (headerResponseMap != null && headerResponseMap.size() > 0) {
			ArrayList<ProductPropertiesMappingDVO> productPropertiesMappingList = new ArrayList<ProductPropertiesMappingDVO>();
			int size = headerResponseMap.size();
			for (int i = 0; i < size; i++) {
				HashMap<String, Object> resultSetMap = headerResponseMap.get(i);
				ProductPropertiesMappingDVO productPropertiesMappingDVO = new ProductPropertiesMappingDVO();
				if (resultSetMap.get("product_property_code") != null) {
					productPropertiesMappingDVO.getProductPropertiesRecord().setCode(
							(String) (resultSetMap.get("product_property_code")));
				}
				if (resultSetMap.get("value_type_sequence") != null) {
					productPropertiesMappingDVO.getProductPropertiesRecord().getValueType()
							.setSequenceNumber(Integer.valueOf(resultSetMap.get("value_type_sequence").toString()));
				}
				productPropertiesMappingList.add(productPropertiesMappingDVO);
			}
			productDVO.setProductPropertiesMappingList(productPropertiesMappingList);
		}
		return productDVO;
	}

	public ProductOpr getProductsInCatalog(ProductOpr queryProductOpr) throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("inside ProductDefinitionBC :: getProductsInCatalog()");

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		DAOResult daoResult = new DAOResult();
		String loginUser = null;
		if (queryProductOpr.getApplicationFlags().getApplicationFlagMap()
				.get(CommonConstant.ParameterCode.EXCLUSIVITY_FILTER_USER_LOGIN) != null) {
			loginUser = queryProductOpr.getApplicationFlags().getApplicationFlagMap()
					.get(CommonConstant.ParameterCode.EXCLUSIVITY_FILTER_USER_LOGIN).toString();
		}

		myLog.debug("inside ProductDefinitionBC :: getProductsInCatalog() 1st::");

		Long webSiteID = queryProductOpr.getApplicationFlags().getApplicationFlagMap()
				.get(CommonConstant.ParameterCode.EXCLUSIVITY_FILTER_WEBSITE_ID) == null ? null : Long
				.valueOf(queryProductOpr.getApplicationFlags().getApplicationFlagMap()
						.get(CommonConstant.ParameterCode.EXCLUSIVITY_FILTER_WEBSITE_ID).toString());

		Long companyId = queryProductOpr.getApplicationFlags().getApplicationFlagMap()
				.get(CommonConstant.ParameterCode.EXCLUSIVITY_FILTER_COMPANY_ID) == null ? null : Long
				.valueOf(queryProductOpr.getApplicationFlags().getApplicationFlagMap()
						.get(CommonConstant.ParameterCode.EXCLUSIVITY_FILTER_COMPANY_ID).toString());

		Long retailerGroupId = queryProductOpr.getApplicationFlags().getApplicationFlagMap()
				.get(CommonConstant.ParameterCode.EXCLUSIVITY_FILTER_RETAILER_GROUP_ID) == null ? null : Long
				.valueOf(queryProductOpr.getApplicationFlags().getApplicationFlagMap()
						.get(CommonConstant.ParameterCode.EXCLUSIVITY_FILTER_RETAILER_GROUP_ID).toString());

		String catalogCode = null;
		if (queryProductOpr.getApplicationFlags().getApplicationFlagMap()
				.get(CommonConstant.ParameterCode.CATALOG_CODE_FOR_SEARCH) != null) {
			catalogCode = queryProductOpr.getApplicationFlags().getApplicationFlagMap()
					.get(CommonConstant.ParameterCode.CATALOG_CODE_FOR_SEARCH).toString();
		}

		myLog.debug("inside ProductDefinitionBC :: getProductsInCatalog() 2nd::");

		ProductOpr productOpr = queryProductOpr;
		ProductOpr searchResultsProductOpr = new ProductOpr();
		searchResultsProductOpr.setProductRecord(productOpr.getProductRecord());

		Object strSqlParams[][] = new Object[4][3];
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);

		if (queryProductOpr.getApplicationFlags().getApplicationFlagMap()
				.get(CommonConstant.ParameterCode.USE_EXCLUSIVITY_FILTER) != null) {

			myLog.debug("inside USE_EXCLUSIVITY_FILTER :::: "
					+ queryProductOpr.getApplicationFlags().getApplicationFlagMap()
							.get(CommonConstant.ParameterCode.EXCLUSIVITY_FILTER_WEBSITE_ID));

			queryDetailsMap.put(IDAOConstant.SQL_TEXT,
					ProductDefinitionSqlTemplate.GET_PRODUCTS_IN_CATALOG_WITH_EXCLUSIVITY_FILTER);
			strSqlParams = new Object[5][3];

			strSqlParams[0][0] = "1";
			strSqlParams[0][1] = IDAOConstant.STRING_DATATYPE;
			strSqlParams[0][2] = loginUser;
			myLog.debug("parameters 1 : " + strSqlParams[0][2]);

			strSqlParams[1][0] = "2";
			strSqlParams[1][1] = IDAOConstant.LONG_DATATYPE;
			strSqlParams[1][2] = webSiteID;
			myLog.debug("parameters 2 : " + strSqlParams[1][2]);

			strSqlParams[2][0] = "3";
			strSqlParams[2][1] = IDAOConstant.LONG_DATATYPE;
			strSqlParams[2][2] = companyId;
			myLog.debug("parameters 3 : " + strSqlParams[2][2]);

			strSqlParams[3][0] = "4";
			strSqlParams[3][1] = IDAOConstant.LONG_DATATYPE;
			strSqlParams[3][2] = retailerGroupId;
			myLog.debug("parameters 4 : " + strSqlParams[3][2]);

			strSqlParams[4][0] = "5";
			strSqlParams[4][1] = IDAOConstant.STRING_DATATYPE;
			strSqlParams[4][2] = catalogCode;
			myLog.debug("parameters 5 : " + strSqlParams[4][2]);

			daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
			myLog.debug("daoResult = performDBOperation(queryDetailsMap, strSqlParams, null)");
			HashMap<Object, HashMap<Integer, HashMap<String, Object>>> responseMap = daoResult.getMultipleResultSet();
			myLog.debug("getProductsInCatalog :: Resultset got ::" + responseMap);
			CatalogDVO catalogDvo = new CatalogDVO();

			if (responseMap != null && responseMap.size() > 0) {
				HashMap<Integer, HashMap<String, Object>> catalogNameMap = responseMap.get("catalog_name");
				HashMap<Integer, HashMap<String, Object>> catalogProductMap = responseMap
						.get("core_catalog_product_mapping_id");

				if (catalogNameMap != null && !catalogNameMap.isEmpty()) {
					HashMap<String, Object> innerMap = catalogNameMap.get(0);
					if (innerMap.get("catalog_name") != null) {
						catalogDvo.setName((String) innerMap.get("catalog_name"));
					}
				}

				if (catalogProductMap != null && !catalogProductMap.isEmpty()) {

					for (int i = 0; i < catalogProductMap.size(); i++) {
						HashMap<String, Object> resultRow = catalogProductMap.get(i);
						// PROCESS OBJECT RETURNED IN THE resultRow IF SELECT
						// QUERY
						// HAS BEEN FIRED
						ProductDVO productDVO = new ProductDVO();

						if (resultRow.get("core_catalog_product_mapping_id") != null) {
							productDVO.getMappedToCatalogRecord().setId(
									Long.valueOf(resultRow.get("core_catalog_product_mapping_id").toString()));
						}

						if (resultRow.get("product_id") != null) {
							productDVO.setId(Long.valueOf(resultRow.get("product_id").toString()));
						}

						if (resultRow.get("product_sku_id") != null) {
							productDVO.getProductSkuRecord().setId(
									Long.valueOf(resultRow.get("product_sku_id").toString()));
						}

						productDVO.setCode((String) (resultRow.get("product_code")));

						productDVO.setName((String) (resultRow.get("product_name")));

						productDVO.setDescription((String) (resultRow.get("product_description")));

						productDVO.getProductSkuRecord().setDescription((String) (resultRow.get("sku_description")));

						productDVO.getCatalogRecord().setName((String) resultRow.get("catalog_name"));

						setAuditAttributes(productDVO, resultRow);
						searchResultsProductOpr.getSearchResultList().add(productDVO);

					}
				}
			} else {
				myLog.debug("ProductBC :: getProductsInCatalog failed :: Return Record empty ::::: ");
			}

			searchResultsProductOpr.getProductRecord().setCatalogRecord(catalogDvo);
		}
		myLog.debug("outside ProductBC :: getProductsInCatalog()");
		return searchResultsProductOpr;
	}
}
