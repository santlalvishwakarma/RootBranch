package com.web.bc.retail.modules.browseproducts;

import java.util.HashMap;

import com.web.common.constants.CommonConstant;
import com.web.common.dvo.opr.retail.BrowseProductsOpr;
import com.web.common.dvo.systemowner.CategoryDVO;
import com.web.common.dvo.systemowner.ProductSkuDVO;
import com.web.common.parents.BackingClass;
import com.web.foundation.dao.DAOResult;
import com.web.foundation.dao.IDAOConstant;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;

public class BrowseProductsBC extends BackingClass {

	// public BrowseProductsOpr makeEnquiry(BrowseProductsOpr
	// makeEnquiryProductRecord) throws FrameworkException {
	// // BACKING CLASS METHOD TEMPLATE ver 1.0
	// ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
	// HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
	// DAOResult daoResult = new DAOResult();
	//
	// queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.INSERT_SQL);
	// queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE,
	// IDAOConstant.PREPARED_STATEMENT);
	// queryDetailsMap.put(IDAOConstant.SQL_TEXT,
	// BrowseProductsSqlTemplate.MAKE_PRODUCT_ENQUIRY);
	//
	// Object strSqlParams[][] = new Object[7][3];
	//
	// strSqlParams[0][0] = "1";
	// strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
	// strSqlParams[0][2] =
	// makeEnquiryProductRecord.getProductSkuRecord().getId();
	//
	// strSqlParams[1][0] = "2";
	// strSqlParams[1][1] = IDAOConstant.LONG_DATATYPE;
	// strSqlParams[1][2] =
	// makeEnquiryProductRecord.getProductSkuRecord().getVariantId();
	//
	// strSqlParams[2][0] = "3";
	// strSqlParams[2][1] = IDAOConstant.STRING_DATATYPE;
	// strSqlParams[2][2] =
	// makeEnquiryProductRecord.getSendEnquiryRecord().getAddress().getFirstName();
	//
	// strSqlParams[3][0] = "4";
	// strSqlParams[3][1] = IDAOConstant.STRING_DATATYPE;
	// strSqlParams[3][2] =
	// makeEnquiryProductRecord.getSendEnquiryRecord().getAddress().getEmail();
	//
	// strSqlParams[4][0] = "5";
	// strSqlParams[4][1] = IDAOConstant.STRING_DATATYPE;
	// strSqlParams[4][2] =
	// makeEnquiryProductRecord.getSendEnquiryRecord().getComments();
	//
	// strSqlParams[5][0] = "6";
	// strSqlParams[5][1] = IDAOConstant.STRING_DATATYPE;
	// strSqlParams[5][2] =
	// makeEnquiryProductRecord.getApplicationFlags().getApplicationFlagMap()
	// .get(CommonConstant.LOGGED_USER_KEY);
	//
	// strSqlParams[6][0] = "7";
	// strSqlParams[6][1] = IDAOConstant.STRING_DATATYPE;
	// strSqlParams[6][2] =
	// makeEnquiryProductRecord.getApplicationFlags().getApplicationFlagMap()
	// .get(CommonConstant.LOGGED_USER_KEY);
	//
	// // in the following call replace null with dynamic where clause if
	// // required
	// daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
	// HashMap responseMap = daoResult.getInvocationResult();
	// myLog.debug(":: Resultset got ::" + responseMap);
	//
	// return makeEnquiryProductRecord;
	// }

	// public BrowseProductsOpr searchMappingOnCategoryCodes(BrowseProductsOpr
	// searchParameters)
	// throws FrameworkException, BusinessException {
	// BrowseProductsOpr searchForMappingOpr = (BrowseProductsOpr)
	// searchParameters;
	// BrowseProductsOpr searchResultsMappingOpr = new BrowseProductsOpr();
	// searchResultsMappingOpr.setProductRecord(searchForMappingOpr.getProductSkuRecord());
	//
	// ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
	// HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
	// DAOResult daoResult = new DAOResult();
	// String categoryCode = null;
	// myLog.debug("after click on any category at any level");
	//
	// Object strSqlParams[][];
	// queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
	// queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE,
	// IDAOConstant.PREPARED_STATEMENT);
	// queryDetailsMap.put(IDAOConstant.SQL_TEXT,
	// BrowseProductsSqlTemplate.SEARCH_MAPPING_ON_CATEGORY_CODES);
	//
	// strSqlParams = new Object[5][3];
	//
	// strSqlParams[0][0] = "1";
	// strSqlParams[0][1] = IDAOConstant.STRING_DATATYPE;
	// strSqlParams[0][2] =
	// searchForMappingOpr.getProductSkuRecord().getHierarchyRecord().getName();
	// myLog.debug("1::::::" + strSqlParams[0][2]);
	//
	// strSqlParams[1][0] = "2";
	// strSqlParams[1][1] = IDAOConstant.STRING_DATATYPE;
	// strSqlParams[1][2] =
	// searchForMappingOpr.getProductSkuRecord().getHierarchyLevelOne().getCategoryRecord()
	// .getCode();
	//
	// myLog.debug("2::::::" + strSqlParams[1][2]);
	//
	// strSqlParams[2][0] = "3";
	// strSqlParams[2][1] = IDAOConstant.STRING_DATATYPE;
	// strSqlParams[2][2] =
	// searchForMappingOpr.getProductSkuRecord().getHierarchyLevelTwo().getCategoryRecord()
	// .getCode();
	// if
	// (searchForMappingOpr.getProductSkuRecord().getHierarchyLevelTwo().getCategoryRecord().getCode()
	// != null) {
	// categoryCode =
	// searchForMappingOpr.getProductSkuRecord().getHierarchyLevelTwo().getCategoryRecord()
	// .getCode();
	// }
	// myLog.debug("3::::::" + strSqlParams[2][2]);
	//
	// strSqlParams[3][0] = "4";
	// strSqlParams[3][1] = IDAOConstant.STRING_DATATYPE;
	// strSqlParams[3][2] =
	// searchForMappingOpr.getProductSkuRecord().getHierarchyLevelThree().getCategoryRecord()
	// .getCode();
	// if
	// (searchForMappingOpr.getProductSkuRecord().getHierarchyLevelThree().getCategoryRecord().getCode()
	// != null) {
	// categoryCode =
	// searchForMappingOpr.getProductSkuRecord().getHierarchyLevelThree().getCategoryRecord()
	// .getCode();
	// }
	// myLog.debug("4::::::" + strSqlParams[3][2]);
	//
	// strSqlParams[4][0] = "5";
	// strSqlParams[4][1] = IDAOConstant.STRING_DATATYPE;
	// strSqlParams[4][2] =
	// searchForMappingOpr.getProductSkuRecord().getHierarchyLevelFour().getCategoryRecord()
	// .getCode();
	// if
	// (searchForMappingOpr.getProductSkuRecord().getHierarchyLevelFour().getCategoryRecord().getCode()
	// != null) {
	// categoryCode =
	// searchForMappingOpr.getProductSkuRecord().getHierarchyLevelFour().getCategoryRecord()
	// .getCode();
	// }
	// myLog.debug("5::::::" + strSqlParams[4][2]);
	//
	// daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
	// HashMap responseMap = daoResult.getInvocationResult();
	// myLog.debug("searchProductsOnCategoryCodes :: Resultset got ::" +
	// responseMap);
	//
	// if (responseMap.size() > 0) {
	// for (int i = 0; i < responseMap.size(); i++) {
	// HashMap resultRow = (HashMap) responseMap.get(i);
	//
	// ProductDVO productDVO = new ProductDVO();
	// if (resultRow.get("level_1_category_id") != null) {
	// productDVO.getHierarchyLevelOne().setId(
	// Long.valueOf(resultRow.get("level_1_category_id").toString()));
	// }
	// myLog.debug("id get from proc 1 is%%%%%%%" +
	// productDVO.getHierarchyLevelOne().getId());
	//
	// if (resultRow.get("level_2_category_id") != null) {
	// productDVO.getHierarchyLevelTwo().setId(
	// Long.valueOf(resultRow.get("level_2_category_id").toString()));
	// }
	// myLog.debug("id get from procv 2 is%%%%%%%" +
	// productDVO.getHierarchyLevelTwo().getId());
	//
	// if (resultRow.get("level_3_category_id") != null) {
	// productDVO.getHierarchyLevelThree().setId(
	// Long.valueOf(resultRow.get("level_3_category_id").toString()));
	// }
	// myLog.debug("id get from procv 3 is%%%%%%%" +
	// productDVO.getHierarchyLevelThree().getId());
	//
	// if (resultRow.get("level_4_category_id") != null) {
	// productDVO.getHierarchyLevelFour().setId(
	// Long.valueOf(resultRow.get("level_4_category_id").toString()));
	// }
	// myLog.debug("id get from procv 3 is%%%%%%%" +
	// productDVO.getHierarchyLevelFour().getId());
	//
	// productDVO.setCode((String) (resultRow.get("category_code")));
	// myLog.debug("code get from proc is%%%%%%%" + productDVO.getCode());
	//
	// productDVO.getImageRecord().setImageURL((String)
	// resultRow.get("image_url"));
	// myLog.debug("image url from proc is%%%%%%%%" +
	// productDVO.getImageRecord().getImageURL());
	//
	// searchResultsMappingOpr.getCategoryMappingList().add(productDVO);
	//
	// }
	// } else {
	// myLog.debug("Browse Product Management :: searchCategoriesMappingOnCategoryCodes failed :: Return Record empty ::::: ");
	// // throw new BusinessException("no_data_from_db_excep_msg");
	// }
	//
	// myLog.debug("category clicked::::" + categoryCode);
	//
	// searchResultsMappingOpr.getCommentRecord().setCode(categoryCode);
	//
	// // searchResultsMappingOpr =
	// // searchUserComments(searchResultsMappingOpr);
	// // myLog.debug("commnetssssss::::" +
	// // searchResultsMappingOpr.getCommentsList().size());
	//
	// return searchResultsMappingOpr;
	//
	// }
	//
	// public BrowseProductsOpr searchUserComments(BrowseProductsOpr
	// commentsOpr) throws FrameworkException {
	//
	// ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
	// HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
	// DAOResult daoResult = new DAOResult();
	// BrowseProductsOpr returnBrowseProductsOpr = commentsOpr;
	//
	// Object strSqlParams[][] = null;
	// queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
	// queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE,
	// IDAOConstant.PREPARED_STATEMENT);
	// HashMap responseMap = new HashMap();
	// if (commentsOpr.getCommentRecord().getCode() != null) {
	// queryDetailsMap.put(IDAOConstant.SQL_TEXT,
	// BrowseProductsSqlTemplate.SEARCH_USER_COMMENTS_ON_CATEGORY);
	//
	// strSqlParams = new Object[1][3];
	//
	// strSqlParams[0][0] = "1";
	// strSqlParams[0][1] = IDAOConstant.STRING_DATATYPE;
	// strSqlParams[0][2] = commentsOpr.getCommentRecord().getCode();
	//
	// daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
	// responseMap = daoResult.getInvocationResult();
	// myLog.debug("searchComments :: Resultset got ::" + responseMap);
	// }
	//
	// if (commentsOpr.getCommentRecord().getCode() == null ||
	// responseMap.size() == 0) {
	// queryDetailsMap.put(IDAOConstant.SQL_TEXT,
	// BrowseProductsSqlTemplate.SEARCH_USER_COMMENTS);
	//
	// strSqlParams = new Object[0][0];
	// daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
	// responseMap = daoResult.getInvocationResult();
	// myLog.debug("searchComments :: Resultset got ::" + responseMap);
	// }
	//
	// if (responseMap.size() > 0) {
	// for (int i = 0; i < responseMap.size(); i++) {
	// HashMap resultRow = (HashMap) responseMap.get(i);
	//
	// GuestBookDVO guestBookDVO = new GuestBookDVO();
	//
	// guestBookDVO.getUserDetails().setFirstName((String)
	// resultRow.get("user_name"));
	// guestBookDVO.setComments((String) resultRow.get("comments"));
	// guestBookDVO.getCountry().setFlagUrl((String) resultRow.get("flag_url"));
	//
	// returnBrowseProductsOpr.getCommentsList().add(guestBookDVO);
	// }
	// }
	//
	// return returnBrowseProductsOpr;
	//
	// }
	//
	// public BrowseProductsOpr searchUserCommentsForCatalog(BrowseProductsOpr
	// commentsOpr) throws FrameworkException {
	//
	// ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
	// HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
	// DAOResult daoResult = new DAOResult();
	// BrowseProductsOpr returnBrowseProductsOpr = new BrowseProductsOpr();
	//
	// Object strSqlParams[][] = null;
	// queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
	// queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE,
	// IDAOConstant.PREPARED_STATEMENT);
	// HashMap responseMap = new HashMap();
	//
	// queryDetailsMap.put(IDAOConstant.SQL_TEXT,
	// BrowseProductsSqlTemplate.SEARCH_USER_COMMENTS);
	//
	// strSqlParams = new Object[0][0];
	// daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
	// responseMap = daoResult.getInvocationResult();
	// myLog.debug("searchComments :: Resultset got ::" + responseMap);
	//
	// if (responseMap.size() > 0) {
	// for (int i = 0; i < responseMap.size(); i++) {
	// HashMap resultRow = (HashMap) responseMap.get(i);
	//
	// GuestBookDVO guestBookDVO = new GuestBookDVO();
	//
	// guestBookDVO.getUserDetails().setFirstName((String)
	// resultRow.get("user_name"));
	// guestBookDVO.setComments((String) resultRow.get("comments"));
	// guestBookDVO.getCountry().setFlagUrl((String) resultRow.get("flag_url"));
	//
	// returnBrowseProductsOpr.getCommentsList().add(guestBookDVO);
	// }
	// }
	//
	// return returnBrowseProductsOpr;
	//
	// }

	public BrowseProductsOpr getCountryDependantData(BrowseProductsOpr browseProductsOpr) throws FrameworkException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		DAOResult daoResult = new DAOResult();
		myLog.debug("inside getCountryDependantData BC");

		Object strSqlParams[][] = new Object[1][3];
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, BrowseProductsSqlTemplate.GET_CURRENCY_DETAILS);

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[0][2] = browseProductsOpr.getCountryRecord().getName();
		myLog.debug("param 1 : " + strSqlParams[0][2]);

		daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap responseMap = daoResult.getInvocationResult();

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {
				HashMap resultRow = (HashMap) responseMap.get(i);

				if (resultRow.get("currency_symbol") != null) {
					browseProductsOpr.getCurrencyRecord().setCurrencySymbol((String) resultRow.get("currency_symbol"));
				}
				myLog.debug("Inside browseproductsbc currency_symbol::"
						+ browseProductsOpr.getCurrencyRecord().getCurrencySymbol());
				if (resultRow.get("conversion_multiplier") != null) {
					browseProductsOpr.getCurrencyRecord().setCurrencyConversionMultiplier(
							Float.valueOf(resultRow.get("conversion_multiplier").toString()));
				}
				myLog.debug("Inside browseproductsbc conversion_multiplier::"
						+ browseProductsOpr.getCurrencyRecord().getCurrencyConversionMultiplier());
			}
		}

		return browseProductsOpr;
	}

	// public BrowseProductsOpr searchCategoryMappingImages(BrowseProductsOpr
	// commentsOpr) throws FrameworkException {
	//
	// ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
	// HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
	// DAOResult daoResult = new DAOResult();
	// BrowseProductsOpr returnBrowseProductsOpr = commentsOpr;
	// myLog.debug("searchCategoryMappingImages :check for codes"
	// +
	// commentsOpr.getProductSkuRecord().getHierarchyLevelOne().getCategoryRecord().getCode());
	//
	// Object strSqlParams[][] = null;
	// queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
	// queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE,
	// IDAOConstant.PREPARED_STATEMENT);
	// HashMap responseMap = new HashMap();
	//
	// queryDetailsMap.put(IDAOConstant.SQL_TEXT,
	// BrowseProductsSqlTemplate.SEARCH_IMAGES_ON_CATEGORY);
	//
	// strSqlParams = new Object[4][3];
	//
	// strSqlParams[0][0] = "1";
	// strSqlParams[0][1] = IDAOConstant.STRING_DATATYPE;
	// strSqlParams[0][2] =
	// commentsOpr.getProductSkuRecord().getHierarchyLevelOne().getCategoryRecord().getCode();
	//
	// strSqlParams[1][0] = "2";
	// strSqlParams[1][1] = IDAOConstant.STRING_DATATYPE;
	// strSqlParams[1][2] =
	// commentsOpr.getProductSkuRecord().getHierarchyLevelTwo().getCategoryRecord().getCode();
	//
	// strSqlParams[2][0] = "3";
	// strSqlParams[2][1] = IDAOConstant.STRING_DATATYPE;
	// strSqlParams[2][2] =
	// commentsOpr.getProductSkuRecord().getHierarchyLevelThree().getCategoryRecord().getCode();
	//
	// strSqlParams[3][0] = "4";
	// strSqlParams[3][1] = IDAOConstant.STRING_DATATYPE;
	// strSqlParams[3][2] =
	// commentsOpr.getProductSkuRecord().getHierarchyLevelFour().getCategoryRecord().getCode();
	//
	// daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
	// responseMap = daoResult.getInvocationResult();
	// myLog.debug("searchComments :: Resultset got ::" + responseMap);
	//
	// if (responseMap.size() > 0) {
	// for (int i = 0; i < responseMap.size(); i++) {
	// HashMap resultRow = (HashMap) responseMap.get(i);
	//
	// ImageDVO categoryImages = new ImageDVO();
	//
	// categoryImages.setLinkingUrl((String) resultRow.get("link_url"));
	//
	// categoryImages.setImageURL((String) resultRow.get("image_url"));
	//
	// returnBrowseProductsOpr.getCategoryImageList().add(categoryImages);
	// }
	// }
	//
	// return returnBrowseProductsOpr;
	//
	// }

	public BrowseProductsOpr searchProductsOnCategoryCodes(BrowseProductsOpr searchBrowseProductsOpr)
			throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("Inside BrowseProductsBC::searchProductsOnCategoryCodes:");

		String hierarchyName = searchBrowseProductsOpr.getProductSkuRecord().getHierarchyCategoryMappingRecord()
				.getHierarchyRecord().getName();
		String levelOneCategoryCode = searchBrowseProductsOpr.getProductSkuRecord().getHierarchyCategoryMappingRecord()
				.getCategoryLevelOneRecord().getCode();
		String levelTwoCategoryCode = searchBrowseProductsOpr.getProductSkuRecord().getHierarchyCategoryMappingRecord()
				.getCategoryLevelTwoRecord().getCode();
		String levelThreeCategoryCode = searchBrowseProductsOpr.getProductSkuRecord()
				.getHierarchyCategoryMappingRecord().getCategoryLevelThreeRecord().getCode();
		String levelFourCategoryCode = searchBrowseProductsOpr.getProductSkuRecord()
				.getHierarchyCategoryMappingRecord().getCategoryLevelFourRecord().getCode();
		String userLogin = searchBrowseProductsOpr.getProductSkuRecord().getUserLogin();
		Long webSiteId = (Long) searchBrowseProductsOpr.getApplicationFlags().getApplicationFlagMap()
				.get(CommonConstant.EXCLUSIVITY_FILTER_WEBSITE_ID);

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, BrowseProductsSqlTemplate.SEARCH_PRODUCT_ON_CATEGORY);
		Object strSqlParams[][] = new Object[7][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[0][2] = hierarchyName;
		myLog.debug(" parameter 1 hierarchyName :: " + hierarchyName);

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[1][2] = levelOneCategoryCode;
		myLog.debug(" parameter 2 levelOneCategoryCode :: " + levelOneCategoryCode);

		strSqlParams[2][0] = "3";
		strSqlParams[2][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[2][2] = levelTwoCategoryCode;
		myLog.debug(" parameter 3 levelTwoCategoryCode :: " + levelTwoCategoryCode);

		strSqlParams[3][0] = "4";
		strSqlParams[3][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[3][2] = levelThreeCategoryCode;
		myLog.debug(" parameter 4 levelThreeCategoryCode :: " + levelThreeCategoryCode);

		strSqlParams[4][0] = "5";
		strSqlParams[4][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[4][2] = levelFourCategoryCode;
		myLog.debug(" parameter 5 levelFourCategoryCode :: " + levelFourCategoryCode);

		strSqlParams[5][0] = "6";
		strSqlParams[5][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[5][2] = userLogin;
		myLog.debug(" parameter 6 userLogin :: " + userLogin);

		strSqlParams[6][0] = "7";
		strSqlParams[6][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[6][2] = webSiteId;
		myLog.debug(" parameter 7 webSiteId :: " + webSiteId);

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" searchProductsOnCategoryCodes :: Resultset got ::" + responseMap);
		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {
				HashMap<String, Object> resultSetMap = responseMap.get(i);
				handleAndThrowException(resultSetMap);

				ProductSkuDVO productSkuDVO = new ProductSkuDVO();
				if (resultSetMap.get("product_sku_id") != null) {
					productSkuDVO.setId(Long.valueOf(resultSetMap.get("product_sku_id").toString()));
				}

				if (resultSetMap.get("product_id") != null) {
					productSkuDVO.getProductRecord().setId(Long.valueOf(resultSetMap.get("product_id").toString()));
				}
				productSkuDVO.setCode((String) (resultSetMap.get("sku_code")));
				productSkuDVO.setName((String) (resultSetMap.get("sku_name")));
				productSkuDVO.setDescription((String) (resultSetMap.get("sku_description")));
				productSkuDVO.getImageRecord().setImageURL((String) (resultSetMap.get("default_zoom_image_url")));
				productSkuDVO.getImageRecord().setThumbnailImageURL(
						(String) (resultSetMap.get("default_thumbnail_image_url")));
				productSkuDVO.getImageRecord().setZoomImageURL((String) (resultSetMap.get("default_zoom_image_url")));
				productSkuDVO.getHierarchyCategoryMappingRecord().getHierarchyRecord().setName(hierarchyName);

				if (resultSetMap.get("hierarchy_id") != null) {
					productSkuDVO.getHierarchyCategoryMappingRecord().getHierarchyRecord()
							.setId(Long.valueOf(resultSetMap.get("hierarchy_id").toString()));
				}

				if (resultSetMap.get("category_level_1") != null) {
					productSkuDVO.getHierarchyCategoryMappingRecord().getCategoryLevelOneRecord()
							.setId(Long.valueOf(resultSetMap.get("category_level_1").toString()));
				}

				productSkuDVO.getHierarchyCategoryMappingRecord().getCategoryLevelOneRecord()
						.setCode(levelOneCategoryCode);

				productSkuDVO.getHierarchyCategoryMappingRecord().getCategoryLevelOneRecord()
						.setName((String) (resultSetMap.get("category_level_1_name")));

				if (resultSetMap.get("category_level_2") != null) {
					productSkuDVO.getHierarchyCategoryMappingRecord().getCategoryLevelTwoRecord()
							.setId(Long.valueOf(resultSetMap.get("category_level_2").toString()));
				}

				productSkuDVO.getHierarchyCategoryMappingRecord().getCategoryLevelTwoRecord()
						.setCode(levelTwoCategoryCode);

				productSkuDVO.getHierarchyCategoryMappingRecord().getCategoryLevelTwoRecord()
						.setName((String) (resultSetMap.get("category_level_2_name")));

				if (resultSetMap.get("category_level_3") != null) {
					productSkuDVO.getHierarchyCategoryMappingRecord().getCategoryLevelThreeRecord()
							.setId(Long.valueOf(resultSetMap.get("category_level_3").toString()));
				}

				productSkuDVO.getHierarchyCategoryMappingRecord().getCategoryLevelThreeRecord()
						.setCode(levelThreeCategoryCode);

				productSkuDVO.getHierarchyCategoryMappingRecord().getCategoryLevelThreeRecord()
						.setName((String) (resultSetMap.get("category_level_3_name")));

				if (resultSetMap.get("category_level_4") != null) {
					productSkuDVO.getHierarchyCategoryMappingRecord().getCategoryLevelFourRecord()
							.setId(Long.valueOf(resultSetMap.get("category_level_4").toString()));
				}

				productSkuDVO.getHierarchyCategoryMappingRecord().getCategoryLevelFourRecord()
						.setCode(levelFourCategoryCode);

				productSkuDVO.getHierarchyCategoryMappingRecord().getCategoryLevelFourRecord()
						.setName((String) (resultSetMap.get("category_level_4_name")));

				if (resultSetMap.get("base_price") != null) {
					productSkuDVO.setBasePrice(Float.valueOf(resultSetMap.get("base_price").toString()));
				}
				if (resultSetMap.get("discount_amount") != null) {
					productSkuDVO.setDiscountAmount(Float.valueOf(resultSetMap.get("discount_amount").toString()));
				}

				if (resultSetMap.get("discount_percent") != null) {
					productSkuDVO.setDiscountPercent(Float.valueOf(resultSetMap.get("discount_percent").toString()));
				}

				if (resultSetMap.get("final_base_price") != null) {
					productSkuDVO.setFinalBasePrice(Float.valueOf(resultSetMap.get("final_base_price").toString()));
				}

				if (productSkuDVO.getDiscountPercent() != null || productSkuDVO.getDiscountAmount() != null) {
					myLog.debug("discount percent not null:::" + productSkuDVO.getDiscountPercent() + ":::"
							+ productSkuDVO.getDiscountAmount());
					productSkuDVO.setDiscountPrice(productSkuDVO.getFinalBasePrice());
				}

				// if (productSkuDVO.getDiscountPrice() != 0.0f) {
				if (productSkuDVO.getDiscountPrice() != null && productSkuDVO.getDiscountPrice() > Float.valueOf(0)) {
					productSkuDVO.setRenderedDiscountPrice(Boolean.TRUE);
				} else {
					productSkuDVO.setRenderedDiscountPrice(Boolean.FALSE);
				}
				// productSkuDVO.setBasePrice((Float)
				// (resultSetMap.get("base_price")));
				productSkuDVO.getCurrencyRecord().setId((Long) (resultSetMap.get("currency_id")));
				productSkuDVO.getCurrencyRecord().setName((String) (resultSetMap.get("currency_name")));
				productSkuDVO.getCurrencyRecord().setCode((String) (resultSetMap.get("currency_code")));
				productSkuDVO.getCurrencyRecord().setCurrencySymbol((String) (resultSetMap.get("currency_symbol")));

				if (resultSetMap.get("product_rating") != null) {
					productSkuDVO.setRating((Double) (resultSetMap.get("product_rating")));
				}

				// if (resultSetMap.get("is_online_product") != null) {
				// productSkuDVO.setOnlineProduct((Boolean) resultSetMap
				// .get("is_online_product"));
				// //myLog.debug("is online product : " +
				// productSkuDVO.isOnlineProduct());
				// }

				setAuditAttributes(productSkuDVO, resultSetMap);

				searchBrowseProductsOpr.getProductSkuList().add(productSkuDVO);

			}

			// call method for filtering on properties
			// searchResultsProductOpr =
			// createPropertyMapForFilters(searchResultsProductOpr);
			//
			// myLog.debug("out in outter::::::::::::");
			// ProductOpr returnSearchResultsProductOpr =
			// getCategoryLevelImageDetails(searchProductOpr);
			// searchResultsProductOpr.setCategoryImageDetailsUrlList(returnSearchResultsProductOpr
			// .getCategoryImageDetailsUrlList());
		}
		return searchBrowseProductsOpr;
	}

	public BrowseProductsOpr searchSubCategory(BrowseProductsOpr searchBrowseProductsOpr) throws FrameworkException,
			BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("Inside BrowseProductsBC::searchSubCategory:");

		String hierarchyName = searchBrowseProductsOpr.getProductSkuRecord().getHierarchyCategoryMappingRecord()
				.getHierarchyRecord().getName();
		String levelOneCategoryCode = searchBrowseProductsOpr.getProductSkuRecord().getHierarchyCategoryMappingRecord()
				.getCategoryLevelOneRecord().getCode();
		String levelTwoCategoryCode = searchBrowseProductsOpr.getProductSkuRecord().getHierarchyCategoryMappingRecord()
				.getCategoryLevelTwoRecord().getCode();
		String levelThreeCategoryCode = searchBrowseProductsOpr.getProductSkuRecord()
				.getHierarchyCategoryMappingRecord().getCategoryLevelThreeRecord().getCode();
		String levelFourCategoryCode = searchBrowseProductsOpr.getProductSkuRecord()
				.getHierarchyCategoryMappingRecord().getCategoryLevelFourRecord().getCode();

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, BrowseProductsSqlTemplate.SEARCH_SUB_CATEGORY_ON_MAIN_CATEGORY);
		Object strSqlParams[][] = new Object[5][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[0][2] = hierarchyName;
		myLog.debug(" parameter 1 hierarchyName :: " + hierarchyName);

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[1][2] = levelOneCategoryCode;
		myLog.debug(" parameter 2 levelOneCategoryCode :: " + levelOneCategoryCode);

		strSqlParams[2][0] = "3";
		strSqlParams[2][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[2][2] = levelTwoCategoryCode;
		myLog.debug(" parameter 3 levelTwoCategoryCode :: " + levelTwoCategoryCode);

		strSqlParams[3][0] = "4";
		strSqlParams[3][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[3][2] = levelThreeCategoryCode;
		myLog.debug(" parameter 4 levelThreeCategoryCode :: " + levelThreeCategoryCode);

		strSqlParams[4][0] = "5";
		strSqlParams[4][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[4][2] = levelFourCategoryCode;
		myLog.debug(" parameter 5 levelFourCategoryCode :: " + levelFourCategoryCode);

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" executeSave :: Resultset got ::" + responseMap);
		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {
				HashMap<String, Object> resultSetMap = responseMap.get(i);
				handleAndThrowException(resultSetMap);
				CategoryDVO categoryDVO = new CategoryDVO();

				if (resultSetMap.get("category_id") != null) {
					categoryDVO.setId(Long.valueOf(resultSetMap.get("category_id").toString()));
				}

				categoryDVO.setCode((String) (resultSetMap.get("category_code")));

				categoryDVO.setName((String) (resultSetMap.get("category_name")));
				categoryDVO.setDescription((String) (resultSetMap.get("category_description")));
				categoryDVO.setSeoTitle((String) (resultSetMap.get("seo_title")));
				categoryDVO.setSeoDescription((String) (resultSetMap.get("seo_description")));
				categoryDVO.setSeoKeyword((String) (resultSetMap.get("seo_keyword")));
				categoryDVO.setImageUrl((String) (resultSetMap.get("image_url")));

				searchBrowseProductsOpr.getSubCategoryList().add(categoryDVO);
			}
		}

		return searchBrowseProductsOpr;
	}
}
