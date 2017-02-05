package com.web.bc.systemowner.modules.publishtohomecategory;

import java.util.HashMap;

import com.web.bc.systemowner.modules.categorymaster.CategoryMasterSqlTemplate;
import com.web.common.dvo.opr.systemowner.CategoryOpr;
import com.web.common.dvo.opr.systemowner.PublishToHomeCategoryOpr;
import com.web.common.dvo.systemowner.CategoryDVO;
import com.web.common.dvo.systemowner.PublishToHomeCategoryDVO;
import com.web.common.parents.BackingClass;
import com.web.foundation.dao.DAOResult;
import com.web.foundation.dao.IDAOConstant;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;

public class PublishToHomeCategoryBC extends BackingClass {

	public PublishToHomeCategoryOpr getPublishToHomeCategories() throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In PublishToHomeCategoryBC :: getPublishToHomeCategories starts ");
		PublishToHomeCategoryOpr publishToHomeCategoryOpr = new PublishToHomeCategoryOpr();

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, PublishToHomeCategorySqlTemplate.GET_ALL_PUBLISH_TO_HOME_CATEGORIES);

		Object strSqlParams[][] = new Object[0][3];

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" PublishToHomeCategoryBC getPublishToHomeCategories :: Resultset got ::" + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {

				HashMap<String, Object> resultSetMap = responseMap.get(i);
				handleAndThrowException(resultSetMap);

				PublishToHomeCategoryDVO publishToHomeCategoryDVO = new PublishToHomeCategoryDVO();
				if (resultSetMap.get("publish_to_home_category_id") != null)
					publishToHomeCategoryDVO.setId(Long.valueOf(resultSetMap.get("publish_to_home_category_id")
							.toString()));

				if (resultSetMap.get("hierarchy_id") != null)
					publishToHomeCategoryDVO.getHierarchyRecord().setId(
							Long.valueOf(resultSetMap.get("hierarchy_id").toString()));

				publishToHomeCategoryDVO.getHierarchyRecord().setCode((String) resultSetMap.get("hierarchy_code"));
				publishToHomeCategoryDVO.getHierarchyRecord().setName((String) resultSetMap.get("hierarchy_name"));

				if (resultSetMap.get("category_level_1") != null)
					publishToHomeCategoryDVO.getCategoryLevelOneRecord().setId(
							Long.valueOf(resultSetMap.get("category_level_1").toString()));

				publishToHomeCategoryDVO.getCategoryLevelOneRecord().setCode(
						(String) resultSetMap.get("category_level_1_code"));
				publishToHomeCategoryDVO.getCategoryLevelOneRecord().setName(
						(String) resultSetMap.get("category_level_1_name"));

				if (resultSetMap.get("category_level_2") != null)
					publishToHomeCategoryDVO.getCategoryLevelTwoRecord().setId(
							Long.valueOf(resultSetMap.get("category_level_2").toString()));

				publishToHomeCategoryDVO.getCategoryLevelTwoRecord().setCode(
						(String) resultSetMap.get("category_level_2_code"));
				publishToHomeCategoryDVO.getCategoryLevelTwoRecord().setName(
						(String) resultSetMap.get("category_level_2_name"));

				if (resultSetMap.get("category_level_3") != null)
					publishToHomeCategoryDVO.getCategoryLevelThreeRecord().setId(
							Long.valueOf(resultSetMap.get("category_level_3").toString()));

				publishToHomeCategoryDVO.getCategoryLevelThreeRecord().setCode(
						(String) resultSetMap.get("category_level_3_code"));
				publishToHomeCategoryDVO.getCategoryLevelThreeRecord().setName(
						(String) resultSetMap.get("category_level_3_name"));

				if (resultSetMap.get("category_level_4") != null)
					publishToHomeCategoryDVO.getCategoryLevelFourRecord().setId(
							Long.valueOf(resultSetMap.get("category_level_4").toString()));

				publishToHomeCategoryDVO.getCategoryLevelFourRecord().setCode(
						(String) resultSetMap.get("category_level_4_code"));
				publishToHomeCategoryDVO.getCategoryLevelFourRecord().setName(
						(String) resultSetMap.get("category_level_4_name"));

				if (resultSetMap.get("publish_position") != null)
					publishToHomeCategoryDVO.setPublishPosition(Integer.valueOf(resultSetMap.get("publish_position")
							.toString()));

				if (resultSetMap.get("is_active") != null)
					publishToHomeCategoryDVO.setActive((Boolean) resultSetMap.get("is_active"));

				setAuditAttributes(publishToHomeCategoryDVO, resultSetMap);
				publishToHomeCategoryOpr.getPublishToHomeCategoryList().add(publishToHomeCategoryDVO);
			}
		}
		return publishToHomeCategoryOpr;
	}

	public PublishToHomeCategoryOpr executeSavePublishCategory(PublishToHomeCategoryOpr publishToHomeCategoryOpr)
			throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In PublishToHomeCategoryBC :: executeSavePublishCategory starts ");

		PublishToHomeCategoryOpr publishToHomeCategoryOprRet = new PublishToHomeCategoryOpr();
		StringBuffer categoryParseString = new StringBuffer();
		String userLogin = publishToHomeCategoryOpr.getPublishToHomeCategoryRecord().getUserLogin();

		for (PublishToHomeCategoryDVO publishToHomeCategoryRecord : publishToHomeCategoryOpr
				.getPublishToHomeCategoryList()) {
			Long publishCategoryId = publishToHomeCategoryRecord.getId();
			Long hierarchyId = publishToHomeCategoryRecord.getHierarchyRecord().getId();
			Long categoryLevel1Id = publishToHomeCategoryRecord.getCategoryLevelOneRecord().getId();
			Long categoryLevel2Id = publishToHomeCategoryRecord.getCategoryLevelTwoRecord().getId();
			Long categoryLevel3Id = publishToHomeCategoryRecord.getCategoryLevelThreeRecord().getId();
			Long categoryLevel4Id = publishToHomeCategoryRecord.getCategoryLevelFourRecord().getId();

			Integer publishingPosition = publishToHomeCategoryRecord.getPublishPosition();
			Boolean recordDeleted = publishToHomeCategoryRecord.getOperationalAttributes().getRecordDeleted();
			String lastModifiedDate = null;

			if (publishToHomeCategoryRecord.getAuditAttributes().getLastModifiedDate() != null)
				lastModifiedDate = publishToHomeCategoryRecord.getAuditAttributes().getLastModifiedDate().toString();

			if (publishCategoryId != null)
				categoryParseString.append(publishCategoryId);
			else
				categoryParseString.append("");
			categoryParseString.append("~");

			if (hierarchyId != null)
				categoryParseString.append(hierarchyId);
			else
				categoryParseString.append("");
			categoryParseString.append("~");

			if (categoryLevel1Id != null)
				categoryParseString.append(categoryLevel1Id);
			else
				categoryParseString.append("");
			categoryParseString.append("~");

			if (categoryLevel2Id != null)
				categoryParseString.append(categoryLevel2Id);
			else
				categoryParseString.append("");
			categoryParseString.append("~");

			if (categoryLevel3Id != null)
				categoryParseString.append(categoryLevel3Id);
			else
				categoryParseString.append("");
			categoryParseString.append("~");

			if (categoryLevel4Id != null)
				categoryParseString.append(categoryLevel4Id);
			else
				categoryParseString.append("");
			categoryParseString.append("~");

			if (publishingPosition != null)
				categoryParseString.append(publishingPosition);
			else
				categoryParseString.append("");
			categoryParseString.append("~");

			if (lastModifiedDate != null)
				categoryParseString.append(lastModifiedDate);
			else
				categoryParseString.append("");
			categoryParseString.append("~");

			if (recordDeleted != null && recordDeleted)
				categoryParseString.append("1");
			else
				categoryParseString.append("0");
			categoryParseString.append(";");
		}
		if (categoryParseString != null && categoryParseString.length() > 1) {
			// this is to remove the last ; sign
			categoryParseString.deleteCharAt(categoryParseString.length() - 1);
		}

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, PublishToHomeCategorySqlTemplate.SAVE_PUBLISH_CATEGORY_LIST);

		Object strSqlParams[][] = new Object[2][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.STRING_DATATYPE;

		if (categoryParseString.length() > 0)
			strSqlParams[0][2] = categoryParseString.toString();
		else
			strSqlParams[0][2] = null;
		myLog.debug(" parameter 1 strSqlParams[0][2]:: " + strSqlParams[1][2]);

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[1][2] = userLogin;
		myLog.debug(" parameter 2 userLogin:: " + userLogin);

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" PublishToHomeCategoryBC executeSavePublishCategory :: Resultset got ::" + responseMap);

		publishToHomeCategoryOprRet = getPublishToHomeCategories();
		return publishToHomeCategoryOprRet;
	}

}
