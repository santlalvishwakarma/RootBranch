package com.web.bc.systemowner.modules.skumanagement;

import java.util.HashMap;

import com.web.common.dvo.opr.systemowner.SkuOpr;
import com.web.common.dvo.systemowner.ProductDVO;
import com.web.common.dvo.systemowner.ProductSkuDVO;
import com.web.common.dvo.systemowner.ProductSkuImageMappingDVO;
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
		if (statusCode != null && statusCode.equalsIgnoreCase("active"))
			strSqlParams[4][2] = true;
		else if (statusCode != null && statusCode.equalsIgnoreCase("inactive"))
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
}
