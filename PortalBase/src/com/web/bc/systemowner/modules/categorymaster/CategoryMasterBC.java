package com.web.bc.systemowner.modules.categorymaster;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.web.common.dvo.opr.systemowner.CategoryOpr;
import com.web.common.dvo.systemowner.CategoryDVO;
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

				categoryRecord.setSeoTitle((String) resultSetMap.get("image_url"));

				categoryRecord.setActive((Boolean) resultSetMap.get("is_active"));

				categoryList.add(categoryRecord);
			}
		}
		searchCategoryOpr.setCategoryList(categoryList);

		return searchCategoryOpr;
	}

	public CategoryOpr executeSave(CategoryOpr addEditCategoryOpr) throws FrameworkException {

		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug(" Inside executeSave: ");

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, CategoryMasterSqlTemplate.SAVE_EDIT_CATEGORY);

		Object strSqlParams[][] = new Object[9][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[0][2] = addEditCategoryOpr.getCategoryRecord().getId();

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[1][2] = addEditCategoryOpr.getCategoryRecord().getCode();

		strSqlParams[2][0] = "3";
		strSqlParams[2][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[2][2] = addEditCategoryOpr.getCategoryRecord().getName();

		strSqlParams[3][0] = "4";
		strSqlParams[3][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[3][2] = addEditCategoryOpr.getCategoryRecord().getDescription();

		strSqlParams[4][0] = "5";
		strSqlParams[4][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[4][2] = addEditCategoryOpr.getCategoryRecord().getSeoTitle();

		strSqlParams[5][0] = "6";
		strSqlParams[5][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[5][2] = addEditCategoryOpr.getCategoryRecord().getSeoKeyword();

		strSqlParams[6][0] = "7";
		strSqlParams[6][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[6][2] = addEditCategoryOpr.getCategoryRecord().getSeoDescription();

		strSqlParams[7][0] = "8";
		strSqlParams[7][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[7][2] = addEditCategoryOpr.getCategoryRecord().getImageUrl();

		strSqlParams[8][0] = "9";
		strSqlParams[8][1] = IDAOConstant.BOOLEAN_DATATYPE;
		// strSqlParams[8][2] =
		// addEditCategoryOpr.getCategoryRecord().getActive() ? "1" : "0";
		strSqlParams[8][2] = addEditCategoryOpr.getCategoryRecord().getActive();

		strSqlParams[9][0] = "10";
		strSqlParams[9][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[9][2] = addEditCategoryOpr.getCategoryRecord().getUserLogin();

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
}
