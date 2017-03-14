package com.web.bc.retail.modules.readmorepanel;

import java.util.HashMap;

import com.web.common.dvo.opr.retail.ReadMoreOpr;
import com.web.common.dvo.systemowner.HierarchyCategoryMappingDVO;
import com.web.common.dvo.systemowner.ProductSkuColorMappingDVO;
import com.web.common.dvo.systemowner.ProductSkuDVO;
import com.web.common.dvo.systemowner.ProductSkuImageMappingDVO;
import com.web.common.dvo.systemowner.ProductSkuMaterialMappingDVO;
import com.web.common.dvo.systemowner.ProductSkuSizeMappingDVO;
import com.web.common.parents.BackingClass;
import com.web.foundation.dao.DAOResult;
import com.web.foundation.dao.IDAOConstant;
import com.web.foundation.exception.FrameworkException;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;

public class ReadMoreBC extends BackingClass {

	public ReadMoreOpr makeEnquiry(ReadMoreOpr makeEnquiryProductRecord) throws FrameworkException {

		return makeEnquiryProductRecord;
	}

	public ReadMoreOpr getCountryDependantData(ReadMoreOpr readMoreOpr) throws FrameworkException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		DAOResult daoResult = new DAOResult();
		myLog.debug("inside getCountryDependantData");

		Object strSqlParams[][] = new Object[1][3];
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ReadMoreSqlTemplate.GET_CURRENCY_DETAILS);

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[0][2] = readMoreOpr.getCountryRecord().getName();
		myLog.debug("param 1 : " + strSqlParams[0][2]);

		daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap responseMap = daoResult.getInvocationResult();

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {
				HashMap resultRow = (HashMap) responseMap.get(i);

				if (resultRow.get("currency_symbol") != null) {
					readMoreOpr.getCurrencyRecord().setCurrencySymbol((String) resultRow.get("currency_symbol"));
				}
				myLog.debug("Inside browseproductsbc currency_symbol::"
						+ readMoreOpr.getCurrencyRecord().getCurrencySymbol());
				if (resultRow.get("conversion_multiplier") != null) {
					readMoreOpr.getCurrencyRecord().setCurrencyConversionMultiplier(
							Float.valueOf(resultRow.get("conversion_multiplier").toString()));
				}
				myLog.debug("Inside browseproductsbc conversion_multiplier::"
						+ readMoreOpr.getCurrencyRecord().getCurrencyConversionMultiplier());
			}
		}

		return readMoreOpr;
	}

	public ReadMoreOpr getProductDetailsForReadMore(ReadMoreOpr readMoreOpr) throws FrameworkException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug(" inside getProductDetailsForReadMore ::: ");

		Long productId = readMoreOpr.getProductSkuRecord().getProductRecord().getId();
		Long productSkuId = readMoreOpr.getProductSkuRecord().getId();

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ReadMoreSqlTemplate.GET_PRODUCT_HEADER);

		Object strSqlParams[][] = new Object[2][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[0][2] = productId;
		myLog.debug(" parameter 1 ::: productId ::: " + productId);

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[1][2] = productSkuId;
		myLog.debug(" parameter 2 ::: productSkuId ::: " + productSkuId);

		// in the following call replace null with dynamic where clause if
		// required
		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap responseMap = daoResult.getInvocationResult();
		myLog.debug("getProductHeaderDetails :: Resultset got ::" + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {
				HashMap resultRow = (HashMap) responseMap.get(i);
				ProductSkuDVO productSkuDVO = new ProductSkuDVO();

				// Product Header Information
				productSkuDVO.setId(Long.valueOf((resultRow.get("product_sku_id").toString())));

				productSkuDVO.getProductRecord().setId(Long.valueOf((resultRow.get("product_id").toString())));

				productSkuDVO.getProductRecord().setCode((String) (resultRow.get("product_code")));
				productSkuDVO.getProductRecord().setName((String) (resultRow.get("product_name")));
				productSkuDVO.getProductRecord().getStatusRecord().setCode((String) (resultRow.get("status_code")));

				if (resultRow.get("default_hierarchy_id") != null) {
					productSkuDVO.getProductRecord().getHierarchyRecord()
							.setId(Long.valueOf(resultRow.get("default_hierarchy_id").toString()));
				}

				productSkuDVO.getProductRecord().getHierarchyRecord()
						.setName((String) resultRow.get("hierarchy_name").toString());

				productSkuDVO.getProductRecord().getHierarchyRecord()
						.setCode((String) resultRow.get("hierarchy_code").toString());

				// Product Detail Information (SKU Information)
				productSkuDVO.setId(Long.valueOf(resultRow.get("product_sku_id").toString()));

				productSkuDVO.setCode((String) resultRow.get("sku_code"));

				productSkuDVO.setName((String) resultRow.get("sku_name"));

				productSkuDVO.setDescription((String) resultRow.get("sku_description"));

				productSkuDVO.setSkuPropertyText((String) resultRow.get("sku_property_text"));

				productSkuDVO.setSkuSEOTitle((String) resultRow.get("seo_title"));

				productSkuDVO.setSkuSEOKeyword((String) resultRow.get("seo_keyword"));

				productSkuDVO.setSkuSEODescription((String) resultRow.get("seo_description"));

				productSkuDVO.getDefaultImageRecord().setThumbnailImageURL(
						(String) resultRow.get("default_thumbnail_image_url"));

				productSkuDVO.getDefaultImageRecord().setZoomImageURL((String) resultRow.get("default_zoom_image_url"));

				productSkuDVO.getDefaultImageRecord().setImageURL((String) resultRow.get("default_image_url"));

				if (resultRow.get("base_price") != null) {
					productSkuDVO.setBasePrice(Float.valueOf(resultRow.get("base_price").toString()));
				}

				if (resultRow.get("discount_amount") != null) {
					productSkuDVO.setDiscountAmount(Float.valueOf(resultRow.get("discount_amount").toString()));
				}

				if (resultRow.get("discount_percent") != null) {
					productSkuDVO.setDiscountPercent(Float.valueOf(resultRow.get("discount_percent").toString()));
				}

				if (resultRow.get("final_base_price") != null) {
					productSkuDVO.setFinalBasePrice(Float.valueOf(resultRow.get("final_base_price").toString()));
				}

				if (productSkuDVO.getDiscountPercent() != null || productSkuDVO.getDiscountAmount() != null) {
					myLog.debug("discount percent not null:::" + productSkuDVO.getDiscountPercent() + ":::"
							+ productSkuDVO.getDiscountAmount());
					productSkuDVO.setRenderedDiscountPrice(true);
				}

				readMoreOpr.setProductSkuRecord(productSkuDVO);

			}
		} else {
			myLog.error(" getProductDetailsForReadMore ::: Select from Product Header failed ::: Return Record empty ::: ");
		}

		return readMoreOpr;
	}

	public ReadMoreOpr getProductAlternativeImages(ReadMoreOpr readMoreOpr) throws FrameworkException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug(" inside getProductAlternativeImages ::: ");

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();

		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ReadMoreSqlTemplate.GET_PRODUCT_ALTERNATIVE_IMAGES);

		Object[][] strSqlParams = new Object[1][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[0][2] = readMoreOpr.getProductSkuRecord().getId();
		myLog.debug(" parameter 1 ::: strSqlParams[0][2] ::: " + strSqlParams[0][2]);

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" resultSet got ::: " + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {
				HashMap<String, Object> resultRow = responseMap.get(i);
				ProductSkuImageMappingDVO productSkuImageMappingRecord = new ProductSkuImageMappingDVO();

				if (resultRow.get("product_sku_image_mapping_id") != null) {
					productSkuImageMappingRecord.setId(Long.valueOf(resultRow.get("product_sku_image_mapping_id")
							.toString()));
				}

				if (resultRow.get("product_sku_id") != null) {
					productSkuImageMappingRecord.getProductSkuRecord().setId(
							Long.valueOf(resultRow.get("product_sku_id").toString()));
				}

				if (resultRow.get("image_type") != null) {
					productSkuImageMappingRecord.getImageTypeRecord().setParameterID(
							Integer.valueOf(resultRow.get("image_type").toString()));
				}

				productSkuImageMappingRecord.getImageRecord().setThumbnailImageURL(
						(String) resultRow.get("thumbnail_image_url"));

				productSkuImageMappingRecord.getImageRecord().setImageURL((String) resultRow.get("image_url"));

				productSkuImageMappingRecord.getImageRecord().setZoomImageURL((String) resultRow.get("zoom_image_url"));

				if (resultRow.get("sequence_number") != null) {
					productSkuImageMappingRecord.getImageRecord().setSequenceNumber(
							Long.valueOf(resultRow.get("sequence_number").toString()));
				}

				readMoreOpr.getProductSkuRecord().getProductSkuImageMappingList().add(productSkuImageMappingRecord);

			}
		}

		return readMoreOpr;
	}

	public ReadMoreOpr getProductProperties(ReadMoreOpr readMoreOpr) throws FrameworkException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug(" inside getProductSizes ::: ");

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();

		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ReadMoreSqlTemplate.GET_PRODUCT_PROPERTIES);

		Object[][] strSqlParams = new Object[2][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[0][2] = readMoreOpr.getProductSkuRecord().getProductRecord().getId();
		myLog.debug(" parameter 1 ::: productId ::: " + strSqlParams[0][2]);

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[1][2] = readMoreOpr.getProductSkuRecord().getId();
		myLog.debug(" parameter 2 ::: productSkuId ::: " + strSqlParams[1][2]);

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> sizeResponseMap = daoResult.getMultipleResultSet().get("SIZ");
		HashMap<Integer, HashMap<String, Object>> colorResponseMap = daoResult.getMultipleResultSet().get("COL");
		HashMap<Integer, HashMap<String, Object>> materialResponseMap = daoResult.getMultipleResultSet().get("MAT");
		myLog.debug(" ReadMoreBC getSkuPropertyMappingList :: Resultset got size ::" + sizeResponseMap);
		myLog.debug(" ReadMoreBC getSkuPropertyMappingList :: Resultset got color :: " + colorResponseMap);
		myLog.debug(" ReadMoreBC getSkuPropertyMappingList :: Resultset got material :: " + materialResponseMap);

		if (sizeResponseMap.size() > 0) {
			for (int i = 0; i < sizeResponseMap.size(); i++) {
				ProductSkuSizeMappingDVO productSkuSizeMappingRecord = new ProductSkuSizeMappingDVO();

				HashMap<String, Object> resultSetMap = sizeResponseMap.get(i);
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

				readMoreOpr.getProductSkuRecord().getProductSkuSizeMappingList().add(productSkuSizeMappingRecord);

			}
		}

		if (colorResponseMap.size() > 0) {
			for (int i = 0; i < colorResponseMap.size(); i++) {
				ProductSkuColorMappingDVO productSkuColorMappingRecord = new ProductSkuColorMappingDVO();

				HashMap<String, Object> resultSetMap = colorResponseMap.get(i);

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

				readMoreOpr.getProductSkuRecord().getProductSkuColorMappingList().add(productSkuColorMappingRecord);

			}
		}

		if (materialResponseMap.size() > 0) {
			for (int i = 0; i < materialResponseMap.size(); i++) {
				ProductSkuMaterialMappingDVO productSkuMaterialMappingRecord = new ProductSkuMaterialMappingDVO();

				HashMap<String, Object> resultSetMap = materialResponseMap.get(i);

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

				readMoreOpr.getProductSkuRecord().getProductSkuMaterialMappingList()
						.add(productSkuMaterialMappingRecord);

			}
		}

		return readMoreOpr;
	}

	public ReadMoreOpr getCategoriesForProduct(ReadMoreOpr readMoreOpr) throws FrameworkException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug(" inside getCategoriesForProduct ::: ");

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();

		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ReadMoreSqlTemplate.GET_CATEGORIES_FOR_PRODUCT);

		Object[][] strSqlParams = new Object[1][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[0][2] = readMoreOpr.getProductSkuRecord().getProductRecord().getId();
		myLog.debug(" parameter 1 ::: strSqlParams[0][2] ::: " + strSqlParams[0][2]);

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" resultSet got ::: " + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {
				HashMap<String, Object> resultRow = responseMap.get(i);
				HierarchyCategoryMappingDVO hierarchyCategoryMappingRecord = new HierarchyCategoryMappingDVO();

				if (resultRow.get("product_hierarchy_category_mapping_id") != null) {
					hierarchyCategoryMappingRecord.setId(Long.valueOf(resultRow.get(
							"product_hierarchy_category_mapping_id").toString()));
				}

				if (resultRow.get("hierarchy_id") != null) {
					hierarchyCategoryMappingRecord.getHierarchyRecord().setId(
							Long.valueOf(resultRow.get("hierarchy_id").toString()));
				}

				hierarchyCategoryMappingRecord.getHierarchyRecord().setName((String) resultRow.get("hierarchy_name"));

				if (resultRow.get("category_level_1") != null) {
					hierarchyCategoryMappingRecord.getCategoryLevelOneRecord().setId(
							Long.valueOf(resultRow.get("category_level_1").toString()));
				}

				hierarchyCategoryMappingRecord.getCategoryLevelOneRecord().setCode(
						(String) resultRow.get("category_level_1_code"));

				hierarchyCategoryMappingRecord.getCategoryLevelOneRecord().setName(
						(String) resultRow.get("category_level_1_name"));

				if (resultRow.get("category_level_2") != null) {
					hierarchyCategoryMappingRecord.getCategoryLevelTwoRecord().setId(
							Long.valueOf(resultRow.get("category_level_2").toString()));
				}

				hierarchyCategoryMappingRecord.getCategoryLevelTwoRecord().setCode(
						(String) resultRow.get("category_level_2_code"));

				hierarchyCategoryMappingRecord.getCategoryLevelTwoRecord().setName(
						(String) resultRow.get("category_level_2_name"));

				if (resultRow.get("category_level_3") != null) {
					hierarchyCategoryMappingRecord.getCategoryLevelThreeRecord().setId(
							Long.valueOf(resultRow.get("category_level_3").toString()));
				}

				hierarchyCategoryMappingRecord.getCategoryLevelThreeRecord().setCode(
						(String) resultRow.get("category_level_3_code"));

				hierarchyCategoryMappingRecord.getCategoryLevelThreeRecord().setName(
						(String) resultRow.get("category_level_3_name"));

				if (resultRow.get("category_level_4") != null) {
					hierarchyCategoryMappingRecord.getCategoryLevelFourRecord().setId(
							Long.valueOf(resultRow.get("category_level_4").toString()));
				}

				hierarchyCategoryMappingRecord.getCategoryLevelFourRecord().setCode(
						(String) resultRow.get("category_level_4_code"));

				hierarchyCategoryMappingRecord.getCategoryLevelFourRecord().setName(
						(String) resultRow.get("category_level_4_name"));

				if (resultRow.get("category_level_5") != null) {
					hierarchyCategoryMappingRecord.getCategoryLevelFiveRecord().setId(
							Long.valueOf(resultRow.get("category_level_5").toString()));
				}

				hierarchyCategoryMappingRecord.getCategoryLevelFiveRecord().setCode(
						(String) resultRow.get("category_level_5_code"));

				hierarchyCategoryMappingRecord.getCategoryLevelFiveRecord().setName(
						(String) resultRow.get("category_level_5_name"));

				readMoreOpr.getProductSkuRecord().setHierarchyCategoryMappingRecord(hierarchyCategoryMappingRecord);
			}
		}

		return readMoreOpr;
	}
}
