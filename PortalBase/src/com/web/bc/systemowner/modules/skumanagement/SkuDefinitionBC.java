package com.web.bc.systemowner.modules.skumanagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.web.common.constants.CommonConstant;
import com.web.common.dvo.opr.systemowner.SkuOpr;
import com.web.common.dvo.systemowner.ColorDVO;
import com.web.common.dvo.systemowner.MaterialDVO;
import com.web.common.dvo.systemowner.ProductDVO;
import com.web.common.dvo.systemowner.ProductSkuColorMappingDVO;
import com.web.common.dvo.systemowner.ProductSkuDVO;
import com.web.common.dvo.systemowner.ProductSkuImageMappingDVO;
import com.web.common.dvo.systemowner.ProductSkuMaterialMappingDVO;
import com.web.common.dvo.systemowner.ProductSkuSizeMappingDVO;
import com.web.common.dvo.systemowner.PropertyValueMappingDVO;
import com.web.common.parents.BackingClass;
import com.web.foundation.dao.DAOResult;
import com.web.foundation.dao.IDAOConstant;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;

public class SkuDefinitionBC extends BackingClass {

	public SkuOpr executeSearch(SkuOpr skuOpr) throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In SkuDefinitionBC :: executeSearch starts ");
		SkuOpr skuOprRet = new SkuOpr();

		ProductSkuDVO productSkuRecord = skuOpr.getProductSkuRecord();

		String skuCode = productSkuRecord.getCode();
		String skuName = productSkuRecord.getName();
		String skuDescription = productSkuRecord.getDescription();
		String productCode = productSkuRecord.getProductRecord().getCode();
		String statusCode = productSkuRecord.getStatusRecord().getCode();

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, SkuDefinitionSqlTemplate.SEARCH_SKU_DETAILS);

		Object strSqlParams[][] = new Object[5][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[0][2] = skuCode;
		myLog.debug(" parameter 1 skuCode:: " + skuCode);

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[1][2] = skuName;
		myLog.debug(" parameter 2 skuName:: " + skuName);

		strSqlParams[2][0] = "3";
		strSqlParams[2][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[2][2] = skuDescription;
		myLog.debug(" parameter 3 skuDescription:: " + skuDescription);

		strSqlParams[3][0] = "4";
		strSqlParams[3][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[3][2] = productCode;
		myLog.debug(" parameter 4 productCode:: " + productCode);

		strSqlParams[4][0] = "5";
		strSqlParams[4][1] = IDAOConstant.BOOLEAN_DATATYPE;
		if (statusCode != null && statusCode.equalsIgnoreCase(CommonConstant.StatusCodes.ACTIVE))
			strSqlParams[4][2] = true;
		else if (statusCode != null && statusCode.equalsIgnoreCase(CommonConstant.StatusCodes.INACTIVE))
			strSqlParams[4][2] = false;
		myLog.debug(" parameter 5 statusCode:: " + strSqlParams[4][2]);

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" SkuDefinitionBC executeSearch :: Resultset got ::" + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {

				HashMap<String, Object> resultSetMap = responseMap.get(i);
				handleAndThrowException(resultSetMap);

				ProductSkuDVO productSkuDVO = new ProductSkuDVO();
				if (resultSetMap.get("product_sku_id") != null)
					productSkuDVO.setId(Long.valueOf(resultSetMap.get("product_sku_id").toString()));

				productSkuDVO.setCode((String) resultSetMap.get("sku_code"));
				productSkuDVO.setName((String) resultSetMap.get("sku_name"));
				productSkuDVO.setDescription((String) resultSetMap.get("sku_description"));

				if (resultSetMap.get("is_active") != null)
					productSkuDVO.setActive((Boolean) resultSetMap.get("is_active"));

				if (resultSetMap.get("product_id") != null)
					productSkuDVO.getProductRecord().setId(Long.valueOf(resultSetMap.get("product_id").toString()));

				productSkuDVO.getProductRecord().setCode((String) resultSetMap.get("product_code"));
				productSkuDVO.getProductRecord().setName((String) resultSetMap.get("product_name"));

				productSkuDVO.getDefaultImageRecord().setThumbnailImageURL(
						(String) resultSetMap.get("thumbnail_image_url"));
				productSkuDVO.getDefaultImageRecord().setImageURL((String) resultSetMap.get("default_image_url"));
				productSkuDVO.getDefaultImageRecord().setZoomImageURL((String) resultSetMap.get("zoom_image_url"));

				productSkuDVO.setSkuSEOKeyword((String) resultSetMap.get("seo_keyword"));
				productSkuDVO.setSkuSEODescription((String) resultSetMap.get("seo_description"));
				productSkuDVO.setSkuSEOTitle((String) resultSetMap.get("seo_title"));

				if (resultSetMap.get("base_price") != null)
					productSkuDVO.setBasePrice(Float.valueOf(resultSetMap.get("base_price").toString()));

				if (resultSetMap.get("discount_amount") != null)
					productSkuDVO.setDiscountAmount(Float.valueOf(resultSetMap.get("discount_amount").toString()));

				if (resultSetMap.get("discount_percent") != null)
					productSkuDVO.setDiscountPercent(Float.valueOf(resultSetMap.get("discount_percent").toString()));

				if (resultSetMap.get("final_base_price") != null)
					productSkuDVO.setFinalBasePrice(Float.valueOf(resultSetMap.get("final_base_price").toString()));

				if (resultSetMap.get("default_sku") != null)
					productSkuDVO.setDefaultSku((Boolean) resultSetMap.get("default_sku"));

				setAuditAttributes(productSkuDVO, resultSetMap);
				skuOprRet.getProductSkuList().add(productSkuDVO);
			}
		} else {
			throw new BusinessException("no_data_from_db_excep_msg");
		}
		return skuOprRet;
	}

	public SkuOpr getSkuDetails(SkuOpr skuOpr) throws FrameworkException, BusinessException {

		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In SkuDefinitionBC :: getSkuDetails starts ");

		ProductSkuDVO productSkuRecord = skuOpr.getProductSkuRecord();

		Long skuId = productSkuRecord.getId();

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, SkuDefinitionSqlTemplate.GET_SKU_DETAILS);

		Object strSqlParams[][] = new Object[1][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[0][2] = skuId;
		myLog.debug(" parameter 1 skuId:: " + skuId);

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" SkuDefinitionBC getSkuDetails :: Resultset got ::" + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {

				HashMap<String, Object> resultSetMap = responseMap.get(i);

				if (resultSetMap.get("product_sku_id") != null)
					skuOpr.getProductSkuRecord().setId(Long.valueOf(resultSetMap.get("product_sku_id").toString()));

				if (resultSetMap.get("product_id") != null)
					skuOpr.getProductSkuRecord().getProductRecord()
							.setId(Long.valueOf(resultSetMap.get("product_id").toString()));

				skuOpr.getProductSkuRecord().getProductRecord().setCode((String) resultSetMap.get("product_code"));
				skuOpr.getProductSkuRecord().getProductRecord().setName((String) resultSetMap.get("product_name"));

				skuOpr.getProductSkuRecord().setCode((String) resultSetMap.get("sku_code"));

				skuOpr.getProductSkuRecord().setName((String) resultSetMap.get("sku_name"));

				skuOpr.getProductSkuRecord().setDescription((String) resultSetMap.get("sku_description"));
				skuOpr.getProductSkuRecord().setSkuPropertyText((String) resultSetMap.get("sku_property_text"));
				skuOpr.getProductSkuRecord().setSkuSEOTitle((String) resultSetMap.get("seo_title"));
				skuOpr.getProductSkuRecord().setSkuSEOKeyword((String) resultSetMap.get("seo_keyword"));
				skuOpr.getProductSkuRecord().setSkuSEODescription((String) resultSetMap.get("seo_description"));

				skuOpr.getProductSkuRecord().getDefaultImageRecord()
						.setThumbnailImageURL((String) resultSetMap.get("default_thumbnail_image_url"));
				skuOpr.getProductSkuRecord().getDefaultImageRecord()
						.setImageURL((String) resultSetMap.get("default_image_url"));
				skuOpr.getProductSkuRecord().getDefaultImageRecord()
						.setZoomImageURL((String) resultSetMap.get("default_zoom_image_url"));

				if (resultSetMap.get("base_price") != null)
					skuOpr.getProductSkuRecord().setBasePrice(Float.valueOf(resultSetMap.get("base_price").toString()));
				if (resultSetMap.get("discount_amount") != null)
					skuOpr.getProductSkuRecord().setDiscountAmount(
							Float.valueOf(resultSetMap.get("discount_amount").toString()));
				if (resultSetMap.get("discount_percent") != null)
					skuOpr.getProductSkuRecord().setDiscountPercent(
							Float.valueOf(resultSetMap.get("discount_percent").toString()));
				if (resultSetMap.get("final_base_price") != null)
					skuOpr.getProductSkuRecord().setFinalBasePrice(
							Float.valueOf(resultSetMap.get("final_base_price").toString()));
				skuOpr.getProductSkuRecord().setDefaultSku((Boolean) resultSetMap.get("default_sku"));

				if (resultSetMap.get("is_active") != null)
					skuOpr.getProductSkuRecord().setActive((Boolean) resultSetMap.get("is_active"));

				setAuditAttributes(skuOpr.getProductSkuRecord(), resultSetMap);

			}
		}
		return skuOpr;
	}

	public SkuOpr getImageMappingList(SkuOpr skuOpr) throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In SkuDefinitionBC :: getImageMappingList starts ");
		SkuOpr skuOprRet = new SkuOpr();

		Long skuId = skuOpr.getProductSkuRecord().getId();

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, SkuDefinitionSqlTemplate.GET_IMAGE_MAPPING_LIST);

		Object strSqlParams[][] = new Object[1][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[0][2] = skuId;
		myLog.debug(" parameter 1 skuId:: " + skuId);

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" SkuDefinitionBC getImageMappingList :: Resultset got ::" + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {

				HashMap<String, Object> resultSetMap = responseMap.get(i);

				ProductSkuImageMappingDVO productSkuImageMappingRecord = new ProductSkuImageMappingDVO();

				if (resultSetMap.get("product_image_mapping_id") != null)
					productSkuImageMappingRecord.setId(Long.valueOf(resultSetMap.get("product_image_mapping_id")
							.toString()));

				productSkuImageMappingRecord.getImageRecord().setThumbnailImageURL(
						(String) resultSetMap.get("thumbnail_image_url"));
				productSkuImageMappingRecord.getImageRecord().setImageURL((String) resultSetMap.get("image_url"));
				productSkuImageMappingRecord.getImageRecord().setZoomImageURL(
						(String) resultSetMap.get("zoom_image_url"));
				if (resultSetMap.get("sequence_number") != null)
					productSkuImageMappingRecord.setSequenceNumber(Long.valueOf(resultSetMap.get("sequence_number")
							.toString()));

				skuOprRet.getProductSkuRecord().getProductSkuImageMappingList().add(productSkuImageMappingRecord);
			}
		}
		return skuOprRet;
	}

	public SkuOpr saveImageMappingList(SkuOpr skuOpr) throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In SkuDefinitionBC :: saveImageMappingList starts ");
		SkuOpr skuOprRet = new SkuOpr();

		ProductSkuDVO productSkuRecord = skuOpr.getProductSkuRecord();
		Long skuId = productSkuRecord.getId();
		StringBuffer parseImageDetailsString = new StringBuffer();
		String userLogin = productSkuRecord.getUserLogin();
		String lastModifiedDate = null;
		if (productSkuRecord.getAuditAttributes().getLastModifiedDate() != null)
			lastModifiedDate = productSkuRecord.getAuditAttributes().getLastModifiedDate().toString();

		if (!skuOpr.getProductSkuRecord().getProductSkuImageMappingList().isEmpty()) {
			for (ProductSkuImageMappingDVO productSkuImageMappingDVO : skuOpr.getProductSkuRecord()
					.getProductSkuImageMappingList()) {

				Long imageMappingId = productSkuImageMappingDVO.getId();
				String imageURL = productSkuImageMappingDVO.getImageRecord().getImageURL();
				String thumbnailImageURL = productSkuImageMappingDVO.getImageRecord().getThumbnailImageURL();
				String zoomImageURL = productSkuImageMappingDVO.getImageRecord().getZoomImageURL();
				Long sequenceNumber = productSkuImageMappingDVO.getSequenceNumber();
				Boolean recordDeleted = productSkuImageMappingDVO.getOperationalAttributes().getRecordDeleted();

				if (recordDeleted != null)
					if ((imageMappingId != null && recordDeleted) || (imageMappingId == null && !recordDeleted)
							|| (imageMappingId != null && !recordDeleted)) {

						if (imageMappingId != null)
							parseImageDetailsString.append(imageMappingId);
						else
							parseImageDetailsString.append("");
						parseImageDetailsString.append("~");

						if (imageURL != null)
							parseImageDetailsString.append(imageURL);
						else
							parseImageDetailsString.append("");
						parseImageDetailsString.append("~");

						if (thumbnailImageURL != null)
							parseImageDetailsString.append(thumbnailImageURL);
						else
							parseImageDetailsString.append("");
						parseImageDetailsString.append("~");

						if (zoomImageURL != null)
							parseImageDetailsString.append(zoomImageURL);
						else
							parseImageDetailsString.append("");
						parseImageDetailsString.append("~");

						if (sequenceNumber != null)
							parseImageDetailsString.append(sequenceNumber);
						else
							parseImageDetailsString.append("");
						parseImageDetailsString.append("~");

						if (recordDeleted)
							parseImageDetailsString.append("1");
						else
							parseImageDetailsString.append("0");
						parseImageDetailsString.append(";");

					}
			}
		}

		if (parseImageDetailsString != null && parseImageDetailsString.length() > 1) {
			// this is to remove the last ; sign
			parseImageDetailsString.deleteCharAt(parseImageDetailsString.length() - 1);
		}

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, SkuDefinitionSqlTemplate.SAVE_IMAGE_MAPPING_LIST);

		Object strSqlParams[][] = new Object[4][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[0][2] = skuId;
		myLog.debug(" parameter 1 skuId:: " + skuId);

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.STRING_DATATYPE;
		if (parseImageDetailsString.length() > 0)
			strSqlParams[1][2] = parseImageDetailsString.toString();
		else
			strSqlParams[1][2] = null;
		myLog.debug(" parameter 2 parseImageDetailsString:: " + strSqlParams[1][2]);

		strSqlParams[2][0] = "3";
		strSqlParams[2][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[2][2] = userLogin;
		myLog.debug(" parameter 3 userLogin:: " + userLogin);

		strSqlParams[3][0] = "4";
		strSqlParams[3][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[3][2] = lastModifiedDate;
		myLog.debug(" parameter 4 lastModifiedDate:: " + lastModifiedDate);

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" SkuDefinitionBC saveImageMappingList :: Resultset got ::" + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {

				HashMap<String, Object> resultSetMap = responseMap.get(i);
				handleAndThrowException(resultSetMap);

				setAuditAttributes(skuOprRet.getProductSkuRecord(), resultSetMap);
			}
		}
		skuOpr = getImageMappingList(skuOpr);
		skuOprRet.getProductSkuRecord().setProductSkuImageMappingList(
				skuOpr.getProductSkuRecord().getProductSkuImageMappingList());

		return skuOprRet;
	}

	public SkuOpr executeSaveSkuDetails(SkuOpr skuOpr) throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In SkuDefinitionBC :: executeSaveSKUDetails starts ");
		ProductSkuDVO productSkuRecord = skuOpr.getProductSkuRecord();
		ProductDVO productRecord = productSkuRecord.getProductRecord();

		Long skuId = productSkuRecord.getId();
		Long productId = productRecord.getId();
		String skuName = productSkuRecord.getName();
		Boolean active = productSkuRecord.getActive();
		Boolean defaultSKU = productSkuRecord.getDefaultSku();
		String skuCode = productSkuRecord.getCode();
		String userLogin = productSkuRecord.getUserLogin();
		String lastModifiedDate = null;
		if (productSkuRecord.getAuditAttributes().getLastModifiedDate() != null)
			lastModifiedDate = productSkuRecord.getAuditAttributes().getLastModifiedDate().toString();

		String skuDescription = productSkuRecord.getDescription();
		Float basePrice = productSkuRecord.getBasePrice();
		Float discountAmount = productSkuRecord.getDiscountAmount();
		Float discountPercent = productSkuRecord.getDiscountPercent();
		Float finalBasePrice = productSkuRecord.getFinalBasePrice();
		String skuSeoTitle = productSkuRecord.getSkuSEOTitle();
		String skuSeoKeyword = productSkuRecord.getSkuSEOKeyword();
		String skuSeoDescription = productSkuRecord.getSkuSEODescription();

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, SkuDefinitionSqlTemplate.SAVE_SKU_DETAILS);

		Object strSqlParams[][] = new Object[16][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[0][2] = skuId;
		myLog.debug(" parameter 1 skuId:: " + skuId);

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[1][2] = productId;
		myLog.debug(" parameter 2 productId:: " + productId);

		strSqlParams[2][0] = "3";
		strSqlParams[2][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[2][2] = skuName;
		myLog.debug(" parameter 3 skuName:: " + skuName);

		strSqlParams[3][0] = "4";
		strSqlParams[3][1] = IDAOConstant.BOOLEAN_DATATYPE;
		strSqlParams[3][2] = active;
		myLog.debug(" parameter 4 active:: " + active);

		strSqlParams[4][0] = "5";
		strSqlParams[4][1] = IDAOConstant.BOOLEAN_DATATYPE;
		strSqlParams[4][2] = defaultSKU;
		myLog.debug(" parameter 5 defaultSKU:: " + defaultSKU);

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
		strSqlParams[7][2] = skuDescription;
		myLog.debug(" parameter 8 skuDescription:: " + skuDescription);

		strSqlParams[8][0] = "9";
		strSqlParams[8][1] = IDAOConstant.FLOAT_DATATYPE;
		strSqlParams[8][2] = basePrice;
		myLog.debug(" parameter 9 basePrice:: " + basePrice);

		strSqlParams[9][0] = "10";
		strSqlParams[9][1] = IDAOConstant.FLOAT_DATATYPE;
		strSqlParams[9][2] = discountAmount;
		myLog.debug(" parameter 10 discountAmount:: " + discountAmount);

		strSqlParams[10][0] = "11";
		strSqlParams[10][1] = IDAOConstant.FLOAT_DATATYPE;
		strSqlParams[10][2] = discountPercent;
		myLog.debug(" parameter 11 discountPercent:: " + discountPercent);

		strSqlParams[11][0] = "12";
		strSqlParams[11][1] = IDAOConstant.FLOAT_DATATYPE;
		strSqlParams[11][2] = finalBasePrice;
		myLog.debug(" parameter 12 finalBasePrice:: " + finalBasePrice);

		strSqlParams[12][0] = "13";
		strSqlParams[12][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[12][2] = skuCode;
		myLog.debug(" parameter 13 skuCode:: " + skuCode);

		strSqlParams[13][0] = "14";
		strSqlParams[13][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[13][2] = skuSeoTitle;
		myLog.debug(" parameter 14 skuSeoTitle:: " + skuSeoTitle);

		strSqlParams[14][0] = "15";
		strSqlParams[14][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[14][2] = skuSeoKeyword;
		myLog.debug(" parameter 15 skuSeoKeyword:: " + skuSeoKeyword);

		strSqlParams[15][0] = "16";
		strSqlParams[15][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[15][2] = skuSeoDescription;
		myLog.debug(" parameter 16 skuSeoDescription:: " + skuSeoDescription);

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" SkuDefinitionBC executeSaveSKUDetails :: Resultset got ::" + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {

				HashMap<String, Object> resultSetMap = responseMap.get(i);
				handleAndThrowException(resultSetMap);
				if (resultSetMap.get("product_sku_id") != null) {
					skuId = Long.valueOf(resultSetMap.get("product_sku_id").toString());
					skuOpr.getProductSkuRecord().setId(skuId);
				}

			}
		}
		return getSkuDetails(skuOpr);
	}

	public SkuOpr getSkuPropertyMappingList(SkuOpr skuOpr) throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In SkuDefinitionBC :: getSkuPropertyMappingList starts ");
		ProductSkuDVO productSkuRecord = skuOpr.getProductSkuRecord();
		ProductDVO productRecord = productSkuRecord.getProductRecord();

		Long skuId = productSkuRecord.getId();
		Long productId = productRecord.getId();

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, SkuDefinitionSqlTemplate.GET_MAPPED_PROPERTY_DETAILS);

		Object strSqlParams[][] = new Object[2][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[0][2] = skuId;
		myLog.debug(" parameter 1 skuId:: " + skuId);

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[1][2] = productId;
		myLog.debug(" parameter 2 productId:: " + productId);

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> sizeResponseMap = daoResult.getMultipleResultSet().get("SIZ");
		HashMap<Integer, HashMap<String, Object>> colorResponseMap = daoResult.getMultipleResultSet().get("COL");
		HashMap<Integer, HashMap<String, Object>> materialResponseMap = daoResult.getMultipleResultSet().get("MAT");
		myLog.debug(" Purchase Order getSkuPropertyMappingList :: Resultset got size ::" + sizeResponseMap);
		myLog.debug(" Purchase Order getSkuPropertyMappingList :: Resultset got color :: " + colorResponseMap);
		myLog.debug(" Purchase Order getSkuPropertyMappingList :: Resultset got material :: " + materialResponseMap);

		if (sizeResponseMap.size() > 0) {
			for (int i = 0; i < sizeResponseMap.size(); i++) {
				ProductSkuSizeMappingDVO productSkuSizeMappingRecord = new ProductSkuSizeMappingDVO();

				HashMap<String, Object> resultSetMap = sizeResponseMap.get(i);
				handleAndThrowException(resultSetMap);
				if (resultSetMap.get("product_sku_size_mapping_id") != null) {
					productSkuSizeMappingRecord.setId(Long.valueOf(resultSetMap.get("product_sku_size_mapping_id")
							.toString()));
				}

				if (resultSetMap.get("size_id") != null) {
					productSkuSizeMappingRecord.getPropertyValueMappingRecord().getSizeRecord()
							.setId(Long.valueOf(resultSetMap.get("size_id").toString()));
				}

				productSkuSizeMappingRecord.getPropertyValueMappingRecord().getSizeRecord()
						.setCode((String) resultSetMap.get("size_code"));
				productSkuSizeMappingRecord.getPropertyValueMappingRecord().getSizeRecord()
						.setName((String) resultSetMap.get("size_name"));

				if (resultSetMap.get("unit_id") != null) {
					productSkuSizeMappingRecord.getPropertyValueMappingRecord().getUnitRecord()
							.setId(Long.valueOf(resultSetMap.get("unit_id").toString()));
				}

				productSkuSizeMappingRecord.getPropertyValueMappingRecord().getUnitRecord()
						.setCode((String) resultSetMap.get("unit_code"));
				productSkuSizeMappingRecord.getPropertyValueMappingRecord().getUnitRecord()
						.setName((String) resultSetMap.get("unit_name"));

				productSkuSizeMappingRecord.getPropertyValueMappingRecord().setPropertyValue(
						(String) resultSetMap.get("property_value"));

				setAuditAttributes(productSkuSizeMappingRecord, resultSetMap);

				skuOpr.getProductSkuRecord().getProductSkuSizeMappingList().add(productSkuSizeMappingRecord);

			}
		}

		if (colorResponseMap.size() > 0) {
			for (int i = 0; i < colorResponseMap.size(); i++) {
				ProductSkuColorMappingDVO productSkuColorMappingRecord = new ProductSkuColorMappingDVO();

				HashMap<String, Object> resultSetMap = colorResponseMap.get(i);
				handleAndThrowException(resultSetMap);

				if (resultSetMap.get("product_sku_color_mapping_id") != null) {
					productSkuColorMappingRecord.setId(Long.valueOf(resultSetMap.get("product_sku_color_mapping_id")
							.toString()));
				}

				if (resultSetMap.get("color_id") != null) {
					productSkuColorMappingRecord.getColorRecord().setId(
							Long.valueOf(resultSetMap.get("color_id").toString()));
				}

				productSkuColorMappingRecord.getColorRecord().setCode((String) resultSetMap.get("color_code"));
				productSkuColorMappingRecord.getColorRecord().setName((String) resultSetMap.get("color_name"));

				setAuditAttributes(productSkuColorMappingRecord, resultSetMap);

				skuOpr.getProductSkuRecord().getProductSkuColorMappingList().add(productSkuColorMappingRecord);

			}
		}

		if (materialResponseMap.size() > 0) {
			for (int i = 0; i < materialResponseMap.size(); i++) {
				ProductSkuMaterialMappingDVO productSkuMaterialMappingRecord = new ProductSkuMaterialMappingDVO();

				HashMap<String, Object> resultSetMap = materialResponseMap.get(i);
				handleAndThrowException(resultSetMap);

				if (resultSetMap.get("product_sku_material_mapping_id") != null) {
					productSkuMaterialMappingRecord.setId(Long.valueOf(resultSetMap.get(
							"product_sku_material_mapping_id").toString()));
				}

				if (resultSetMap.get("material_id") != null) {
					productSkuMaterialMappingRecord.getMaterialRecord().setId(
							Long.valueOf(resultSetMap.get("material_id").toString()));
				}

				productSkuMaterialMappingRecord.getMaterialRecord().setCode((String) resultSetMap.get("material_code"));
				productSkuMaterialMappingRecord.getMaterialRecord().setName((String) resultSetMap.get("material_name"));

				setAuditAttributes(productSkuMaterialMappingRecord, resultSetMap);

				skuOpr.getProductSkuRecord().getProductSkuMaterialMappingList().add(productSkuMaterialMappingRecord);

			}
		}

		return skuOpr;
	}

	public List<Object> getSuggestedSizeMappingRecord(PropertyValueMappingDVO propertyValueMappingRecord)
			throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In SizeMasterBC executeSearch ");

		String sizeCode = propertyValueMappingRecord.getSizeRecord().getCode();
		String sizeName = propertyValueMappingRecord.getSizeRecord().getName();
		Boolean isActive = propertyValueMappingRecord.getActive();
		String propertyValue = propertyValueMappingRecord.getPropertyValue();
		Long sizeId = propertyValueMappingRecord.getSizeRecord().getId();
		String unitName = propertyValueMappingRecord.getUnitRecord().getName();
		String unitCode = propertyValueMappingRecord.getUnitRecord().getCode();
		Long unitId = propertyValueMappingRecord.getUnitRecord().getId();

		myLog.debug(" sizeCode " + sizeCode);
		myLog.debug(" sizeName " + sizeName);
		myLog.debug(" isActive " + isActive);
		myLog.debug(" propertyValue " + propertyValue);
		myLog.debug(" sizeId " + sizeId);
		myLog.debug(" unitId " + unitId);
		myLog.debug(" unitName " + unitName);
		myLog.debug(" unitCode " + unitCode);

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

		if (propertyValue != null && propertyValue.trim().length() > 0) {
			if (parameterCount > 0) {
				dynamicWhere.append(" AND property_value = '" + propertyValue + "'");
			} else {
				dynamicWhere.append(" property_value = '" + propertyValue + "'");
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

		if (sizeId != null) {
			if (parameterCount > 0) {
				dynamicWhere.append(" AND size_id = '" + sizeId + "'");
			} else {
				dynamicWhere.append(" size_id = '" + sizeId + "'");
			}
			parameterCount++;
		}

		if (unitId != null) {
			if (parameterCount > 0) {
				dynamicWhere.append(" AND unit_id = '" + unitId + "'");
			} else {
				dynamicWhere.append(" unit_id = '" + unitId + "'");
			}
			parameterCount++;
		}

		if (unitCode != null && unitCode.trim().length() > 0) {
			unitCode = unitCode.trim().concat("%");
			if (parameterCount > 0) {
				dynamicWhere.append(" AND unit_code LIKE '" + unitCode + "'");
			} else {
				dynamicWhere.append(" unit_code LIKE '" + unitCode + "'");
			}
			parameterCount++;
		}

		if (unitName != null && unitName.trim().length() > 0) {
			unitName = unitName.trim().concat("%");
			if (parameterCount > 0) {
				dynamicWhere.append(" AND unit_name LIKE '" + unitName + "'");
			} else {
				dynamicWhere.append(" unit_name LIKE '" + unitName + "'");
			}
			parameterCount++;
		}

		if (isActive == null || isActive) {
			if (parameterCount > 0) {
				dynamicWhere.append(" AND is_active = 1 ");
			} else {
				dynamicWhere.append(" is_active = 1 ");
			}
			parameterCount += 1;
		}

		if (parameterCount == 0) {
			dynamicWhere.append(" 1 = 1 ");
		}
		dynamicWhere.append(" ORDER BY size_code ");
		myLog.debug("dynamicWhere ::::: " + dynamicWhere);

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, SkuDefinitionSqlTemplate.GET_PROPERTY_VALUE_DETAILS);

		Object strSqlParams[][] = new Object[0][0];

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, dynamicWhere.toString());
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" SizeMasterBC executeSearch :: Resultset got ::" + responseMap);

		List<Object> propertyValueMappingList = new ArrayList<Object>();

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
				PropertyValueMappingRecord.getUnitRecord().setCode((String) resultSetMap.get("unit_code"));

				PropertyValueMappingRecord.setPropertyValue((String) resultSetMap.get("property_value"));

				if (resultSetMap.get("is_active") != null) {
					PropertyValueMappingRecord.setActive((Boolean) resultSetMap.get("is_active"));
				} else {
					PropertyValueMappingRecord.setActive(false);
				}

				propertyValueMappingList.add(PropertyValueMappingRecord);
			}
		}

		return propertyValueMappingList;
	}

	public List<Object> getSuggestedColorRecord(ColorDVO colorRecord) throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In ColorMasterBC executeSearch ");

		String colorCode = colorRecord.getCode();
		String colorName = colorRecord.getName();
		Boolean isActive = colorRecord.getActive();

		myLog.debug(" colorCode " + colorCode);
		myLog.debug(" colorName " + colorName);
		myLog.debug(" isActive " + isActive);

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

		if (isActive == null || isActive) {
			if (parameterCount > 0) {
				dynamicWhere.append(" AND is_active = 1 ");
			} else {
				dynamicWhere.append(" is_active = 1 ");
			}
			parameterCount += 1;
		}

		if (parameterCount == 0) {
			dynamicWhere.append(" 1 = 1 ");
		}
		dynamicWhere.append(" ORDER BY color_name ");
		myLog.debug("dynamicWhere ::::: " + dynamicWhere);

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, SkuDefinitionSqlTemplate.GET_SUGGESTED_COLOR_DETAILS);

		Object strSqlParams[][] = new Object[0][0];

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, dynamicWhere.toString());
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" ColorMasterBC executeSearch :: Resultset got ::" + responseMap);

		List<Object> colorList = new ArrayList<Object>();

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {
				ColorDVO colorDVO = new ColorDVO();
				HashMap<String, Object> resultSetMap = responseMap.get(i);
				handleAndThrowException(resultSetMap);

				if (resultSetMap.get("color_id") != null)
					colorDVO.setId(Long.valueOf(resultSetMap.get("color_id").toString()));

				colorDVO.setCode((String) resultSetMap.get("color_code"));
				colorDVO.setName((String) resultSetMap.get("color_name"));
				colorDVO.setDescription((String) resultSetMap.get("color_description"));

				if (resultSetMap.get("is_active") != null) {
					colorDVO.setActive((Boolean) resultSetMap.get("is_active"));
				} else {
					colorDVO.setActive(false);
				}

				setAuditAttributes(colorDVO, resultSetMap);

				colorList.add(colorDVO);

			}
		}

		return colorList;
	}

	public List<Object> getSuggestedMaterialRecord(MaterialDVO materialRecord) throws FrameworkException,
			BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In MaterialMasterBC executeSearch ");

		String materialCode = materialRecord.getCode();
		String materialName = materialRecord.getName();
		Boolean isActive = materialRecord.getActive();

		myLog.debug(" materialCode " + materialCode);
		myLog.debug(" materialName " + materialName);
		myLog.debug(" isActive " + isActive);

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

		if (isActive == null || isActive) {
			if (parameterCount > 0) {
				dynamicWhere.append(" AND is_active = 1 ");
			} else {
				dynamicWhere.append(" is_active = 1 ");
			}
			parameterCount += 1;
		}

		if (parameterCount == 0) {
			dynamicWhere.append(" 1 = 1 ");
		}
		dynamicWhere.append(" ORDER BY material_name ");
		myLog.debug("dynamicWhere ::::: " + dynamicWhere);

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, SkuDefinitionSqlTemplate.GET_SUGGESTED_MATERIAL_DETAILS);

		Object strSqlParams[][] = new Object[0][0];

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, dynamicWhere.toString());
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" MaterialMasterBC executeSearch :: Resultset got ::" + responseMap);

		List<Object> materialList = new ArrayList<Object>();

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {

				HashMap<String, Object> resultSetMap = responseMap.get(i);
				handleAndThrowException(resultSetMap);

				MaterialDVO materialDVO = new MaterialDVO();

				if (resultSetMap.get("material_id") != null)
					materialDVO.setId(Long.valueOf(resultSetMap.get("material_id").toString()));

				materialDVO.setCode((String) resultSetMap.get("material_code"));
				materialDVO.setName((String) resultSetMap.get("material_name"));
				materialDVO.setDescription((String) resultSetMap.get("material_description"));

				if (resultSetMap.get("is_active") != null) {
					materialDVO.setActive((Boolean) resultSetMap.get("is_active"));
				} else {
					materialDVO.setActive(false);
				}

				setAuditAttributes(materialDVO, resultSetMap);

				materialList.add(materialDVO);

			}
		}

		return materialList;
	}

	public SkuOpr executeSavePropertyMapping(SkuOpr skuOpr) throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In SkuDefinitionBC :: executeSavePropertyMapping starts ");

		StringBuffer sizeParseString = new StringBuffer();
		StringBuffer colorParseString = new StringBuffer();
		StringBuffer materialParseString = new StringBuffer();
		String userLogin = skuOpr.getProductSkuRecord().getUserLogin();
		Long productId = skuOpr.getProductSkuRecord().getProductRecord().getId();
		Long productSkuId = skuOpr.getProductSkuRecord().getId();

		String lastModifiedDate = null;
		if (skuOpr.getProductSkuRecord().getAuditAttributes().getLastModifiedDate() != null)
			lastModifiedDate = skuOpr.getProductSkuRecord().getAuditAttributes().getLastModifiedDate().toString();

		for (ProductSkuSizeMappingDVO productSkuSizeMappingRecord : skuOpr.getProductSkuRecord()
				.getProductSkuSizeMappingList()) {
			Long productSkuSizeMappingId = productSkuSizeMappingRecord.getId();
			Long sizeId = productSkuSizeMappingRecord.getPropertyValueMappingRecord().getSizeRecord().getId();
			String propertyValue = productSkuSizeMappingRecord.getPropertyValueMappingRecord().getPropertyValue();
			Long unitId = productSkuSizeMappingRecord.getPropertyValueMappingRecord().getUnitRecord().getId();
			Boolean recordDeleted = productSkuSizeMappingRecord.getOperationalAttributes().getRecordDeleted();

			if (productSkuSizeMappingId != null)
				sizeParseString.append(productSkuSizeMappingId);
			else
				sizeParseString.append("");
			sizeParseString.append("~");

			if (sizeId != null)
				sizeParseString.append(sizeId);
			else
				sizeParseString.append("");
			sizeParseString.append("~");

			if (propertyValue != null)
				sizeParseString.append(propertyValue);
			else
				sizeParseString.append("");
			sizeParseString.append("~");

			if (unitId != null)
				sizeParseString.append(unitId);
			else
				sizeParseString.append("");
			sizeParseString.append("~");

			if (recordDeleted != null && recordDeleted)
				sizeParseString.append("1");
			else
				sizeParseString.append("0");
			sizeParseString.append(";");

		}

		if (sizeParseString != null && sizeParseString.length() > 1) {
			// this is to remove the last ; sign
			sizeParseString.deleteCharAt(sizeParseString.length() - 1);
		}

		for (ProductSkuColorMappingDVO productSkuColorMappingRecord : skuOpr.getProductSkuRecord()
				.getProductSkuColorMappingList()) {
			Long productSkuColorMappingId = productSkuColorMappingRecord.getId();
			Long colorId = productSkuColorMappingRecord.getColorRecord().getId();
			Boolean recordDeleted = productSkuColorMappingRecord.getOperationalAttributes().getRecordDeleted();

			if (productSkuColorMappingId != null)
				colorParseString.append(productSkuColorMappingId);
			else
				colorParseString.append("");
			colorParseString.append("~");

			if (colorId != null)
				colorParseString.append(colorId);
			else
				colorParseString.append("");
			colorParseString.append("~");

			if (recordDeleted != null && recordDeleted)
				colorParseString.append("1");
			else
				colorParseString.append("0");
			colorParseString.append(";");
		}

		if (colorParseString != null && colorParseString.length() > 1) {
			// this is to remove the last ; sign
			colorParseString.deleteCharAt(colorParseString.length() - 1);
		}

		for (ProductSkuMaterialMappingDVO productSkuMaterialMappingRecord : skuOpr.getProductSkuRecord()
				.getProductSkuMaterialMappingList()) {
			Long productSkuMaterialMappingId = productSkuMaterialMappingRecord.getId();
			Long materialId = productSkuMaterialMappingRecord.getMaterialRecord().getId();
			Boolean recordDeleted = productSkuMaterialMappingRecord.getOperationalAttributes().getRecordDeleted();

			if (productSkuMaterialMappingId != null)
				materialParseString.append(productSkuMaterialMappingId);
			else
				materialParseString.append("");
			materialParseString.append("~");

			if (materialId != null)
				materialParseString.append(materialId);
			else
				materialParseString.append("");
			materialParseString.append("~");

			if (recordDeleted != null && recordDeleted)
				materialParseString.append("1");
			else
				materialParseString.append("0");
			materialParseString.append(";");
		}

		if (materialParseString != null && materialParseString.length() > 1) {
			// this is to remove the last ; sign
			materialParseString.deleteCharAt(materialParseString.length() - 1);
		}

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, SkuDefinitionSqlTemplate.SAVE_PRODUCT_SKU_PROPERTY_MAPPING);

		Object strSqlParams[][] = new Object[7][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[0][2] = productId;
		myLog.debug(" parameter 1 productId:: " + productId);

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[1][2] = productSkuId;
		myLog.debug(" parameter 2 productSkuId:: " + productSkuId);

		strSqlParams[2][0] = "3";
		strSqlParams[2][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[2][2] = userLogin;
		myLog.debug(" parameter 3 userLogin:: " + userLogin);

		strSqlParams[3][0] = "4";
		strSqlParams[3][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[3][2] = lastModifiedDate;
		myLog.debug(" parameter 4 lastModifiedDate:: " + lastModifiedDate);

		strSqlParams[4][0] = "5";
		strSqlParams[4][1] = IDAOConstant.STRING_DATATYPE;

		if (sizeParseString.length() > 0)
			strSqlParams[4][2] = sizeParseString.toString();
		else
			strSqlParams[4][2] = null;
		myLog.debug(" parameter 5 strSqlParams[4][2]:: " + strSqlParams[4][2]);

		strSqlParams[5][0] = "6";
		strSqlParams[5][1] = IDAOConstant.STRING_DATATYPE;

		if (colorParseString.length() > 0)
			strSqlParams[5][2] = colorParseString.toString();
		else
			strSqlParams[5][2] = null;
		myLog.debug(" parameter 6 strSqlParams[5][2]:: " + strSqlParams[5][2]);

		strSqlParams[6][0] = "7";
		strSqlParams[6][1] = IDAOConstant.STRING_DATATYPE;

		if (materialParseString.length() > 0)
			strSqlParams[6][2] = materialParseString.toString();
		else
			strSqlParams[6][2] = null;
		myLog.debug(" parameter 7 strSqlParams[6][2]:: " + strSqlParams[6][2]);

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" SkuDefinitionBC executeSavePropertyMapping :: Resultset got ::" + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {

				HashMap<String, Object> resultSetMap = responseMap.get(i);
				handleAndThrowException(resultSetMap);

				setAuditAttributes(skuOpr.getProductSkuRecord(), resultSetMap);
			}
		}
		return skuOpr;
	}
}
