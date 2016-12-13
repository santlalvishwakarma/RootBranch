package com.web.bc.systemowner.modules.categorymaster;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.web.common.dvo.opr.systemowner.CategoryOpr;
import com.web.common.dvo.systemowner.CategoryDVO;
import com.web.common.dvo.systemowner.CategoryLevelDVO;
import com.web.common.dvo.systemowner.HierarchyCategoryMappingDVO;
import com.web.common.parents.BackingClass;
import com.web.foundation.dao.DAOResult;
import com.web.foundation.dao.IDAOConstant;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;

public class CategoryMasterBC extends BackingClass {

	public List<CategoryDVO> getAllCategories() throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Product Category BC :: getAllCategories starts ");

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, CategoryMasterSqlTemplate.GET_ALL_CATEGORIES);

		Object strSqlParams[][] = new Object[0][3];

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" getAllCategories :: Resultset got ::" + responseMap);

		List<CategoryDVO> productCategoryList = new ArrayList<CategoryDVO>();
		if (responseMap.size() > 0) {

			for (int i = 0; i < responseMap.size(); i++) {

				HashMap<String, Object> resultSetMap = responseMap.get(i);

				CategoryDVO productCategoryRecord = new CategoryDVO();
				if (resultSetMap.get("category_id") != null)
					productCategoryRecord.setId(Long.valueOf(resultSetMap.get("category_id").toString()));
				productCategoryRecord.setCode((String) resultSetMap.get("category_code"));
				productCategoryRecord.setName((String) resultSetMap.get("category_name"));
				productCategoryRecord.setDescription((String) resultSetMap.get("category_description"));

				productCategoryList.add(productCategoryRecord);
			}
		}
		return productCategoryList;
	}

	public CategoryOpr executeSearch(CategoryOpr searchCategoryOpr) throws FrameworkException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug(" Inside executeSearch: ");

		String code = searchCategoryOpr.getCategoryRecord().getCode();
		String name = searchCategoryOpr.getCategoryRecord().getName();

		StringBuffer dynamicWhere = new StringBuffer();
		int parameterCount = 0;

		if (code != null && code.trim().length() > 0) {
			code = code.trim().concat("%");
			dynamicWhere.append(" cm.category_code LIKE '" + code + "'");
			parameterCount++;
		}

		if (name != null && name.trim().length() > 0) {
			name = name.trim().concat("%");
			if (parameterCount > 0) {
				dynamicWhere.append(" AND cm.category_name LIKE '" + name + "'");
			} else {
				dynamicWhere.append(" cm.category_name LIKE '" + name + "'");
			}
			parameterCount++;
		}

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, CategoryMasterSqlTemplate.SEARCH_CATEGORY);

		Object strSqlParams[][] = new Object[0][3];

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, dynamicWhere.toString());
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" executeSearch :: Resultset got ::" + responseMap);

		List<CategoryDVO> categoryList = new ArrayList<CategoryDVO>();
		int responseSize = responseMap.size();
		if (responseSize > 0) {

			for (int i = 0; i < responseSize; i++) {

				HashMap<String, Object> resultSetMap = responseMap.get(i);

				CategoryDVO categoryRecord = new CategoryDVO();
				if (resultSetMap.get("category_id") != null) {
					categoryRecord.setId(Long.valueOf(resultSetMap.get("category_id").toString()));
				}
				categoryRecord.setCode((String) resultSetMap.get("category_code"));
				categoryRecord.setName((String) resultSetMap.get("category_name"));
				categoryRecord.setDescription((String) resultSetMap.get("category_description"));

				categoryRecord.setSeoTitle((String) resultSetMap.get("seo_title"));
				categoryRecord.setSeoKeyword((String) resultSetMap.get("seo_keyword"));
				categoryRecord.setSeoDescription((String) resultSetMap.get("seo_description"));

				categoryRecord.setImageUrl((String) resultSetMap.get("image_url"));

				categoryRecord.setActive((Boolean) resultSetMap.get("is_active"));

				setAuditAttributes(categoryRecord, resultSetMap);

				categoryList.add(categoryRecord);
			}
		}
		searchCategoryOpr.setCategoryList(categoryList);

		return searchCategoryOpr;
	}

	public CategoryOpr executeSave(CategoryOpr addEditCategoryOpr) throws FrameworkException {

		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug(" Inside executeSave: ");

		Long categoryId = addEditCategoryOpr.getCategoryRecord().getId();
		String categoryCode = addEditCategoryOpr.getCategoryRecord().getCode();
		String categoryName = addEditCategoryOpr.getCategoryRecord().getName();
		String description = addEditCategoryOpr.getCategoryRecord().getDescription();
		String seoTitle = addEditCategoryOpr.getCategoryRecord().getSeoTitle();
		String seoKeyword = addEditCategoryOpr.getCategoryRecord().getSeoKeyword();
		String seoDescription = addEditCategoryOpr.getCategoryRecord().getSeoDescription();
		String imageUrl = addEditCategoryOpr.getCategoryRecord().getImageUrl();
		Boolean active = addEditCategoryOpr.getCategoryRecord().getActive();
		String userLogin = addEditCategoryOpr.getCategoryRecord().getUserLogin();

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, CategoryMasterSqlTemplate.SAVE_EDIT_CATEGORY);

		Object strSqlParams[][] = new Object[10][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[0][2] = categoryId;

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[1][2] = categoryCode;

		strSqlParams[2][0] = "3";
		strSqlParams[2][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[2][2] = categoryName;

		strSqlParams[3][0] = "4";
		strSqlParams[3][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[3][2] = description;

		strSqlParams[4][0] = "5";
		strSqlParams[4][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[4][2] = seoTitle;

		strSqlParams[5][0] = "6";
		strSqlParams[5][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[5][2] = seoKeyword;

		strSqlParams[6][0] = "7";
		strSqlParams[6][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[6][2] = seoDescription;

		strSqlParams[7][0] = "8";
		strSqlParams[7][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[7][2] = imageUrl;

		strSqlParams[8][0] = "9";
		strSqlParams[8][1] = IDAOConstant.BOOLEAN_DATATYPE;
		strSqlParams[8][2] = addEditCategoryOpr.getCategoryRecord().getActive();

		strSqlParams[9][0] = "10";
		strSqlParams[9][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[9][2] = userLogin;

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" executeSearch :: Resultset got ::" + responseMap);

		int responseSize = responseMap.size();
		if (responseSize > 0) {

			for (int i = 0; i < responseSize; i++) {

				HashMap<String, Object> resultSetMap = responseMap.get(i);

				if (resultSetMap.get("category_id") != null) {
					addEditCategoryOpr.getCategoryRecord().setId(
							Long.valueOf(resultSetMap.get("category_id").toString()));
				}

			}
		}

		return addEditCategoryOpr;
	}

	public CategoryOpr mapCategoryToLevels(CategoryOpr addEditCategoryOpr) throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug(" Inside mapCategoryToLevels: ");

		Long categoryId = addEditCategoryOpr.getCategoryRecord().getId();
		String userLogin = addEditCategoryOpr.getCategoryRecord().getUserLogin();

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.DELETE_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);

		queryDetailsMap.put(IDAOConstant.SQL_TEXT, CategoryMasterSqlTemplate.DELETE_MAPPED_CATEGORY_LEVELS);
		Object strSqlParams[][] = new Object[1][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[0][2] = categoryId;

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);

		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, CategoryMasterSqlTemplate.MAP_CATEGORY_TO_LEVELS);

		strSqlParams = new Object[5][3];

		List<CategoryLevelDVO> categoryLevels = addEditCategoryOpr.getCategoryRecord().getCategoryLevels();
		int categorySize = categoryLevels.size();

		myLog.debug(" 1::: ");
		myLog.debug(" categorySize::: " + categorySize);
		for (int i = 0; i < categorySize; i++) {
			myLog.debug(" categorySize::: " + categorySize);
			CategoryLevelDVO categoryLevelRecord = categoryLevels.get(i);
			Long categoryLevelMappingId = categoryLevelRecord.getId();
			Integer levelNo = categoryLevelRecord.getLevelNo();
			Boolean levelActive = categoryLevelRecord.getActive();

			strSqlParams[0][0] = "1";
			strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
			strSqlParams[0][2] = null;
			// strSqlParams[0][2] = categoryLevelMappingId;

			strSqlParams[1][0] = "2";
			strSqlParams[1][1] = IDAOConstant.LONG_DATATYPE;
			strSqlParams[1][2] = categoryId;

			strSqlParams[2][0] = "3";
			strSqlParams[2][1] = IDAOConstant.INT_DATATYPE;
			strSqlParams[2][2] = levelNo;

			strSqlParams[3][0] = "4";
			strSqlParams[3][1] = IDAOConstant.BOOLEAN_DATATYPE;
			strSqlParams[3][2] = levelActive;

			strSqlParams[4][0] = "5";
			strSqlParams[4][1] = IDAOConstant.STRING_DATATYPE;
			strSqlParams[4][2] = userLogin;

			daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
			HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
			myLog.debug(" 2::: ");
			int responseSize = responseMap.size();
			if (responseSize > 0) {

				for (int j = 0; j < responseSize; j++) {

					HashMap<String, Object> resultSetMap = responseMap.get(j);

					if (resultSetMap.get("category_level_mapping_id") != null) {
						categoryLevelRecord.setId(Long
								.valueOf(resultSetMap.get("category_level_mapping_id").toString()));
					}

				}
			}
		}
		return addEditCategoryOpr;
	}

	public CategoryOpr getMappedCategoryLevel(CategoryOpr addEditCategoryOpr) throws FrameworkException,
			BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug(" Inside executeSearch: ");

		Long categoryId = addEditCategoryOpr.getCategoryRecord().getId();

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, CategoryMasterSqlTemplate.GET_MAPPED_CATEGORY);

		Object strSqlParams[][] = new Object[1][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[0][2] = categoryId;
		myLog.debug(" categoryId: " + categoryId);

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" executeSearch :: Resultset got ::" + responseMap);

		List<CategoryLevelDVO> categoryLevels = new ArrayList<CategoryLevelDVO>();
		int responseSize = responseMap.size();
		if (responseSize > 0) {
			for (int i = 0; i < responseSize; i++) {
				HashMap<String, Object> resultSetMap = responseMap.get(i);

				CategoryLevelDVO categoryLevelRecord = new CategoryLevelDVO();
				if (resultSetMap.get("category_level_mapping_id") != null) {
					categoryLevelRecord.setId(Long.valueOf(resultSetMap.get("category_level_mapping_id").toString()));
				}
				if (resultSetMap.get("category_id") != null) {
					categoryLevelRecord.getCategoryRecord().setId(
							Long.valueOf(resultSetMap.get("category_id").toString()));
				}
				if (resultSetMap.get("level_no") != null) {
					categoryLevelRecord.setLevelNo(Integer.valueOf(resultSetMap.get("level_no").toString()));
				}

				categoryLevelRecord.setActive((Boolean) resultSetMap.get("is_active"));

				setAuditAttributes(categoryLevelRecord, resultSetMap);

				categoryLevels.add(categoryLevelRecord);
			}
		}

		addEditCategoryOpr.getCategoryRecord().setCategoryLevels(categoryLevels);

		return addEditCategoryOpr;
	}

	public CategoryOpr getPublishToHomeCategoryList() throws BusinessException, FrameworkException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug(" CategoryMasterBC Inside getPublishToHomeCategoryList: ");
		CategoryOpr categoryOpr = new CategoryOpr();

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, CategoryMasterSqlTemplate.GET_PUBLISH_TO_HOME_CATEGORY);

		Object strSqlParams[][] = new Object[0][3];

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" CategoryMasterBC executeSearch :: Resultset got ::" + responseMap);

		List<CategoryDVO> publishCategoryList = new ArrayList<CategoryDVO>();
		int responseSize = responseMap.size();
		if (responseSize > 0) {
			for (int i = 0; i < responseSize; i++) {
				HashMap<String, Object> resultSetMap = responseMap.get(i);

				CategoryDVO publishedCategoryRecord = new CategoryDVO();
				if (resultSetMap.get("category_id") != null) {
					publishedCategoryRecord.getPublishCategoryRecord().setId(
							Long.valueOf(resultSetMap.get("category_id").toString()));
				}

				publishedCategoryRecord.getPublishCategoryRecord().setCode((String) resultSetMap.get("category_code"));
				publishedCategoryRecord.getPublishCategoryRecord().setName((String) resultSetMap.get("category_name"));

				publishedCategoryRecord.getPublishCategoryRecord().setPublishToHome(
						(Boolean) resultSetMap.get("publish_to_home_page"));

				if (resultSetMap.get("publish_position") != null) {
					publishedCategoryRecord.getPublishCategoryRecord().setPublishPosition(
							Integer.valueOf(resultSetMap.get("publish_position").toString()));
				}

				publishedCategoryRecord.getPublishCategoryRecord().setImageUrl((String) resultSetMap.get("image_url"));

				setAuditAttributes(publishedCategoryRecord, resultSetMap);

				publishCategoryList.add(publishedCategoryRecord);
			}
		}

		categoryOpr.setPublishToHomeCategoryList(publishCategoryList);

		return categoryOpr;
	}

	public List<Object> getSuggestedCategories(CategoryDVO categoryDVO) throws BusinessException, FrameworkException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In CategoryMasterBC :: getSuggestedCategories starts ");

		String code = categoryDVO.getCode();
		String name = categoryDVO.getName();
		Boolean isActive = categoryDVO.getActive();
		myLog.debug(" parameter 1 code:: " + code);
		myLog.debug(" parameter 2 name:: " + name);

		StringBuffer dynamicWhere = new StringBuffer();

		int parameterCount = 0;
		// setup the dynamic where clause to include all entered params
		if (code != null && code.trim().length() > 0) {
			code = code.trim().concat("%");
			if (parameterCount > 0) {
				dynamicWhere.append(" AND category_code LIKE '" + code + "'");
			} else {
				dynamicWhere.append(" category_code LIKE '" + code + "'");
			}
			parameterCount++;
		}

		if (name != null && name.trim().length() > 0) {
			name = name.trim().concat("%");
			if (parameterCount > 0) {
				dynamicWhere.append(" AND category_name LIKE '" + name + "'");
			} else {
				dynamicWhere.append(" category_name LIKE '" + name + "'");
			}
			parameterCount++;
		}

		if (isActive != null && isActive) {
			if (parameterCount > 0) {
				dynamicWhere.append(" AND is_active = 1 ");
			} else {
				dynamicWhere.append(" is_active = 1 ");
			}
			parameterCount++;
		}

		// to get all data
		if (parameterCount == 0) {
			dynamicWhere.append(" 1 = 1 ");
		}
		dynamicWhere.append(" ORDER BY category_name ");
		myLog.debug("dynamicWhere ::::: " + dynamicWhere);

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, CategoryMasterSqlTemplate.GET_SUGGESTED_CATEGORIES);
		// AND product_category_name LIKE ?

		Object strSqlParams[][] = new Object[0][3];

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, dynamicWhere.toString());
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" getSuggestedCategoriesBasedOnMainLevel :: Resultset got ::" + responseMap);

		List<Object> categoryList = new ArrayList<Object>();
		if (responseMap.size() > 0) {

			for (int i = 0; i < responseMap.size(); i++) {

				HashMap<String, Object> resultSetMap = responseMap.get(i);

				CategoryDVO categoryRecord = new CategoryDVO();
				if (resultSetMap.get("category_id") != null)
					categoryRecord.setId(Long.valueOf(resultSetMap.get("category_id").toString()));

				categoryRecord.setCode((String) resultSetMap.get("category_code"));
				categoryRecord.setName((String) resultSetMap.get("category_name"));
				categoryRecord.setDescription((String) resultSetMap.get("category_description"));

				categoryList.add(categoryRecord);
			}
		}
		return categoryList;
	}

	public CategoryOpr executeSavePublishCategory(CategoryOpr addEditCategoryOpr) throws BusinessException,
			FrameworkException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In CategoryMasterBC :: executeSavePublishCategory starts ");
		CategoryOpr categoryOprRet = new CategoryOpr();

		Long categoryId = addEditCategoryOpr.getCategoryRecord().getId();
		myLog.debug("category Id::" + categoryId);
		StringBuffer parseCategoryParseString = new StringBuffer();
		String userLogin = addEditCategoryOpr.getCategoryRecord().getUserLogin();
		String lastModifiedDate = null;

		if (addEditCategoryOpr.getCategoryRecord().getAuditAttributes().getLastModifiedDate() != null)
			lastModifiedDate = addEditCategoryOpr.getCategoryRecord().getAuditAttributes().getLastModifiedDate()
					.toString();

		for (CategoryDVO categoryRecord : addEditCategoryOpr.getPublishToHomeCategoryList()) {
			Long publishCategoryId = categoryRecord.getPublishCategoryRecord().getId();
			myLog.debug("publishCategoryId Id::" + publishCategoryId);
			Integer publishingPosition = categoryRecord.getPublishCategoryRecord().getPublishPosition();
			Boolean recordDeleted = categoryRecord.getPublishCategoryRecord().getOperationalAttributes()
					.getRecordDeleted();

			if (publishCategoryId != null)
				parseCategoryParseString.append(publishCategoryId);
			else
				parseCategoryParseString.append("");
			parseCategoryParseString.append("~");

			if (publishingPosition != null)
				parseCategoryParseString.append(publishingPosition);
			else
				parseCategoryParseString.append("");
			parseCategoryParseString.append("~");

			if (recordDeleted)
				parseCategoryParseString.append("1");
			else
				parseCategoryParseString.append("0");
			parseCategoryParseString.append(";");
		}
		if (parseCategoryParseString != null && parseCategoryParseString.length() > 1) {
			// this is to remove the last ; sign
			parseCategoryParseString.deleteCharAt(parseCategoryParseString.length() - 1);
		}

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, CategoryMasterSqlTemplate.SAVE_PUBLISH_CATEGORY_LIST);

		Object strSqlParams[][] = new Object[4][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[0][2] = categoryId;
		myLog.debug(" parameter 1 categoryId:: " + categoryId);

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.STRING_DATATYPE;

		if (parseCategoryParseString.length() > 0)
			strSqlParams[1][2] = parseCategoryParseString.toString();
		else
			strSqlParams[1][2] = null;
		myLog.debug(" parameter 2 strSqlParams[1][2]:: " + strSqlParams[1][2]);

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
		myLog.debug(" Product Definition saveHierarchiesMappingList :: Resultset got ::" + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {

				HashMap<String, Object> resultSetMap = responseMap.get(i);
				handleAndThrowException(resultSetMap);

				setAuditAttributes(categoryOprRet.getCategoryRecord(), resultSetMap);

			}
		}
		CategoryOpr categoryOprSend = getPublishToHomeCategoryList();

		categoryOprRet.setPublishToHomeCategoryList(categoryOprSend.getPublishToHomeCategoryList());

		return categoryOprRet;
	}

	public ArrayList<Object> getMappedCategoryLevels(CategoryOpr addEditCategoryOpr) throws FrameworkException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug(" Inside getMappedCategoryLevels: ");

		Long categoryId = addEditCategoryOpr.getCategoryRecord().getId();

		ArrayList<Object> mappedCategoryLevels = null;

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, CategoryMasterSqlTemplate.GET_MAPPED_LEVELS_TO_CATEGORY);

		Object strSqlParams[][] = new Object[1][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[0][2] = categoryId;

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" executeSearch :: Resultset got ::" + responseMap);

		int responseSize = responseMap.size();

		if (responseSize > 0) {
			for (int i = 0; i < responseSize; i++) {
				HashMap<String, Object> resultSetMap = responseMap.get(i);

				mappedCategoryLevels = new ArrayList<Object>();

				if (resultSetMap.get("level_no") != null) {
					mappedCategoryLevels.add(Integer.valueOf(resultSetMap.get("level_no").toString()));
				}
			}
		}

		return mappedCategoryLevels;
	}

	public ArrayList<Object> getSuggestedHierarchyMappingForCode(CategoryOpr addEditCategoryOpr, String query)
			throws FrameworkException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug(" Inside getSuggestedHierarchyMappingForCode: ");

		Long categoryId = addEditCategoryOpr.getCategoryRecord().getId();

		ArrayList<Object> suggestedHierarchies = null;

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, CategoryMasterSqlTemplate.GET_MAPPED_HIERARCHY_TO_CATEGORY_LEVEL);

		Object strSqlParams[][] = new Object[3][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[0][2] = categoryId;
		myLog.debug(" categoryId: " + categoryId);

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.INT_DATATYPE;
		strSqlParams[1][2] = addEditCategoryOpr.getHierarchyCategoryMappingLevelNo();
		myLog.debug(" category level: " + addEditCategoryOpr.getHierarchyCategoryMappingLevelNo());

		strSqlParams[2][0] = "3";
		strSqlParams[2][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[2][2] = query;
		myLog.debug(" hierarchy query: " + query);

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" executeSearch :: Resultset got ::" + responseMap);

		int responseSize = responseMap.size();

		if (responseSize > 0) {
			suggestedHierarchies = new ArrayList<Object>();

			for (int i = 0; i < responseSize; i++) {
				HashMap<String, Object> resultSetMap = responseMap.get(i);

				HierarchyCategoryMappingDVO hierarchyCategoryMappingRecord = new HierarchyCategoryMappingDVO();

				if (resultSetMap.get("hierarchy_category_mapping_id") != null) {
					hierarchyCategoryMappingRecord.setId(Long.valueOf(resultSetMap.get("hierarchy_category_mapping_id")
							.toString()));
				}

				if (resultSetMap.get("hierarchy_id") != null) {
					hierarchyCategoryMappingRecord.getHierarchyRecord().setId(
							Long.valueOf(resultSetMap.get("hierarchy_id").toString()));
				}

				hierarchyCategoryMappingRecord.getHierarchyRecord()
						.setCode((String) resultSetMap.get("hierarchy_code"));
				hierarchyCategoryMappingRecord.getHierarchyRecord()
						.setName((String) resultSetMap.get("hierarchy_name"));

				if (resultSetMap.get("category_level_1_id") != null) {
					hierarchyCategoryMappingRecord.getCategoryLevelOneRecord().setId(
							Long.valueOf(resultSetMap.get("category_level_1_id").toString()));
				}

				hierarchyCategoryMappingRecord.getCategoryLevelOneRecord().setCode(
						(String) resultSetMap.get("category_level_1_code"));
				hierarchyCategoryMappingRecord.getCategoryLevelOneRecord().setName(
						(String) resultSetMap.get("category_level_1_name"));

				if (resultSetMap.get("category_level_2_id") != null) {
					hierarchyCategoryMappingRecord.getCategoryLevelTwoRecord().setId(
							Long.valueOf(resultSetMap.get("category_level_2_id").toString()));
				}

				hierarchyCategoryMappingRecord.getCategoryLevelTwoRecord().setCode(
						(String) resultSetMap.get("category_level_2_code"));
				hierarchyCategoryMappingRecord.getCategoryLevelTwoRecord().setName(
						(String) resultSetMap.get("category_level_2_name"));

				if (resultSetMap.get("category_level_3_id") != null) {
					hierarchyCategoryMappingRecord.getCategoryLevelThreeRecord().setId(
							Long.valueOf(resultSetMap.get("category_level_3_id").toString()));
				}

				hierarchyCategoryMappingRecord.getCategoryLevelThreeRecord().setCode(
						(String) resultSetMap.get("category_level_3_code"));
				hierarchyCategoryMappingRecord.getCategoryLevelThreeRecord().setName(
						(String) resultSetMap.get("category_level_3_name"));

				if (resultSetMap.get("category_level_4_id") != null) {
					hierarchyCategoryMappingRecord.getCategoryLevelFourRecord().setId(
							Long.valueOf(resultSetMap.get("category_level_4_id").toString()));
				}

				hierarchyCategoryMappingRecord.getCategoryLevelFourRecord().setCode(
						(String) resultSetMap.get("category_level_4_code"));
				hierarchyCategoryMappingRecord.getCategoryLevelFourRecord().setName(
						(String) resultSetMap.get("category_level_4_name"));

				String hierarchyCategoryMappingCode = hierarchyCategoryMappingRecord.getHierarchyRecord().getCode() != null ? hierarchyCategoryMappingRecord
						.getHierarchyRecord().getCode() : "";
				hierarchyCategoryMappingCode += hierarchyCategoryMappingRecord.getCategoryLevelOneRecord().getCode() != null ? "-"
						+ hierarchyCategoryMappingRecord.getCategoryLevelOneRecord().getCode()
						: "";
				hierarchyCategoryMappingCode += hierarchyCategoryMappingRecord.getCategoryLevelTwoRecord().getCode() != null ? "-"
						+ hierarchyCategoryMappingRecord.getCategoryLevelTwoRecord().getCode()
						: "";
				hierarchyCategoryMappingCode += hierarchyCategoryMappingRecord.getCategoryLevelThreeRecord().getCode() != null ? "-"
						+ hierarchyCategoryMappingRecord.getCategoryLevelThreeRecord().getCode()
						: "";
				hierarchyCategoryMappingCode += hierarchyCategoryMappingRecord.getCategoryLevelFourRecord().getCode() != null ? "-"
						+ hierarchyCategoryMappingRecord.getCategoryLevelFourRecord().getCode()
						: "";

				hierarchyCategoryMappingRecord.setCode(hierarchyCategoryMappingCode);
				myLog.debug("Hierarchy Category Mapping code: " + hierarchyCategoryMappingRecord.getCode());
				suggestedHierarchies.add(hierarchyCategoryMappingRecord);
			}
		}

		return suggestedHierarchies;
	}

	public CategoryOpr mapHierarchyToCategory(CategoryOpr addEditCategoryOpr) throws FrameworkException,
			BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug(" Inside mapHierarchyToCategory: ");

		List<HierarchyCategoryMappingDVO> hierarchyCategoryMappingList = addEditCategoryOpr
				.getMappedHierarchyCategoryMappingList();
		int hierarchyCategorySize = hierarchyCategoryMappingList.size();

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, CategoryMasterSqlTemplate.MAP_HIERARCHY_TO_CATEGORY_LEVEL);

		Object strSqlParams[][] = new Object[9][3];

		for (int i = 0; i < hierarchyCategorySize; i++) {
			HierarchyCategoryMappingDVO hierarchyCategoryMappingRecord = addEditCategoryOpr
					.getMappedHierarchyCategoryMappingList().get(i);

			strSqlParams[0][0] = "1";
			strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
			strSqlParams[0][2] = hierarchyCategoryMappingRecord.getId();

			strSqlParams[1][0] = "2";
			strSqlParams[1][1] = IDAOConstant.LONG_DATATYPE;
			strSqlParams[1][2] = hierarchyCategoryMappingRecord.getHierarchyRecord().getId();

			strSqlParams[2][0] = "3";
			strSqlParams[2][1] = IDAOConstant.LONG_DATATYPE;
			strSqlParams[2][2] = hierarchyCategoryMappingRecord.getCategoryLevelOneRecord().getId();

			strSqlParams[3][0] = "4";
			strSqlParams[3][1] = IDAOConstant.LONG_DATATYPE;
			strSqlParams[3][2] = hierarchyCategoryMappingRecord.getCategoryLevelTwoRecord().getId();

			strSqlParams[4][0] = "5";
			strSqlParams[4][1] = IDAOConstant.LONG_DATATYPE;
			strSqlParams[4][2] = hierarchyCategoryMappingRecord.getCategoryLevelThreeRecord().getId();

			strSqlParams[5][0] = "6";
			strSqlParams[5][1] = IDAOConstant.LONG_DATATYPE;
			strSqlParams[5][2] = hierarchyCategoryMappingRecord.getCategoryLevelFourRecord().getId();

			strSqlParams[6][0] = "7";
			strSqlParams[6][1] = IDAOConstant.LONG_DATATYPE;
			strSqlParams[6][2] = hierarchyCategoryMappingRecord.getCategoryLevelFiveRecord().getId();

			strSqlParams[7][0] = "8";
			strSqlParams[7][1] = IDAOConstant.BOOLEAN_DATATYPE;
			strSqlParams[7][2] = hierarchyCategoryMappingRecord.getActive();

			strSqlParams[8][0] = "9";
			strSqlParams[8][1] = IDAOConstant.STRING_DATATYPE;
			strSqlParams[8][2] = addEditCategoryOpr.getCategoryRecord().getUserLogin();

			DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
			HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
			myLog.debug(" executeSearch :: Resultset got ::" + responseMap);

			int responseSize = responseMap.size();

			for (int j = 0; j < responseSize; j++) {
				HashMap<String, Object> resultSetMap = responseMap.get(j);

				handleAndThrowException(resultSetMap);

				if (resultSetMap.get("hierarchy_category_mapping_id") != null) {
					hierarchyCategoryMappingRecord.setId(Long.valueOf(resultSetMap.get("hierarchy_category_mapping_id")
							.toString()));
				}
			}

		}

		return addEditCategoryOpr;
	}

	public CategoryOpr getMappedHierarchyCategory(CategoryOpr addEditCategoryOpr) throws FrameworkException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug(" Inside mapHierarchyToCategory: ");

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, CategoryMasterSqlTemplate.GET_MAPPED_HIERARCHY_TO_CATEGORY_LEVEL);

		Object strSqlParams[][] = new Object[3][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[0][2] = addEditCategoryOpr.getCategoryRecord().getId();

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[1][2] = null;

		strSqlParams[2][0] = "3";
		strSqlParams[2][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[2][2] = null;

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" executeSearch :: Resultset got ::" + responseMap);

		int responseSize = responseMap.size();

		for (int j = 0; j < responseSize; j++) {
			HashMap<String, Object> resultSetMap = responseMap.get(j);

			HierarchyCategoryMappingDVO hierarchyCategoryMappingRecord = new HierarchyCategoryMappingDVO();

			if (resultSetMap.get("hierarchy_category_mapping_id") != null) {
				hierarchyCategoryMappingRecord.setId(Long.valueOf(resultSetMap.get("hierarchy_category_mapping_id")
						.toString()));
			}

			if (resultSetMap.get("hierarchy_id") != null) {
				hierarchyCategoryMappingRecord.getHierarchyRecord().setId(
						Long.valueOf(resultSetMap.get("hierarchy_id").toString()));
			}

			hierarchyCategoryMappingRecord.getHierarchyRecord().setCode((String) resultSetMap.get("hierarchy_code"));
			hierarchyCategoryMappingRecord.getHierarchyRecord().setName((String) resultSetMap.get("hierarchy_name"));

			if (resultSetMap.get("category_level_1_id") != null) {
				hierarchyCategoryMappingRecord.getCategoryLevelOneRecord().setId(
						Long.valueOf(resultSetMap.get("category_level_1_id").toString()));
			}

			hierarchyCategoryMappingRecord.getCategoryLevelOneRecord().setCode(
					(String) resultSetMap.get("category_level_1_code"));
			hierarchyCategoryMappingRecord.getCategoryLevelOneRecord().setName(
					(String) resultSetMap.get("category_level_1_name"));

			if (resultSetMap.get("category_level_2_id") != null) {
				hierarchyCategoryMappingRecord.getCategoryLevelTwoRecord().setId(
						Long.valueOf(resultSetMap.get("category_level_2_id").toString()));
			}

			hierarchyCategoryMappingRecord.getCategoryLevelTwoRecord().setCode(
					(String) resultSetMap.get("category_level_2_code"));
			hierarchyCategoryMappingRecord.getCategoryLevelTwoRecord().setName(
					(String) resultSetMap.get("category_level_2_name"));

			if (resultSetMap.get("category_level_3_id") != null) {
				hierarchyCategoryMappingRecord.getCategoryLevelThreeRecord().setId(
						Long.valueOf(resultSetMap.get("category_level_3_id").toString()));
			}

			hierarchyCategoryMappingRecord.getCategoryLevelThreeRecord().setCode(
					(String) resultSetMap.get("category_level_3_code"));
			hierarchyCategoryMappingRecord.getCategoryLevelThreeRecord().setName(
					(String) resultSetMap.get("category_level_3_name"));

			if (resultSetMap.get("category_level_4_id") != null) {
				hierarchyCategoryMappingRecord.getCategoryLevelFourRecord().setId(
						Long.valueOf(resultSetMap.get("category_level_4_id").toString()));
			}

			hierarchyCategoryMappingRecord.getCategoryLevelFourRecord().setCode(
					(String) resultSetMap.get("category_level_4_code"));
			hierarchyCategoryMappingRecord.getCategoryLevelFourRecord().setName(
					(String) resultSetMap.get("category_level_4_name"));

			if (resultSetMap.get("category_level_5_id") != null) {
				hierarchyCategoryMappingRecord.getCategoryLevelFiveRecord().setId(
						Long.valueOf(resultSetMap.get("category_level_5_id").toString()));
			}

			hierarchyCategoryMappingRecord.getCategoryLevelFiveRecord().setCode(
					(String) resultSetMap.get("category_level_5_code"));
			hierarchyCategoryMappingRecord.getCategoryLevelFiveRecord().setName(
					(String) resultSetMap.get("category_level_5_name"));

			addEditCategoryOpr.getCategoryRecord().getHierarchyCategoryMappingList()
					.add(hierarchyCategoryMappingRecord);
		}

		return addEditCategoryOpr;
	}

}
