package com.web.bc.retail.modules.home;

import java.util.HashMap;

import com.web.common.dvo.common.OperationalDataValueObject;
import com.web.common.dvo.opr.retail.HomeOpr;
import com.web.common.dvo.systemowner.HierarchyCategoryMappingDVO;
import com.web.common.dvo.systemowner.ImageDVO;
import com.web.common.dvo.systemowner.PublishToHomeCategoryDVO;
import com.web.common.parents.BackingClass;
import com.web.foundation.dao.DAOResult;
import com.web.foundation.dao.IDAOConstant;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;

public class HomeBC extends BackingClass {

	public OperationalDataValueObject executeSearch(OperationalDataValueObject searchParameters)
			throws FrameworkException, BusinessException {
		HomeOpr searchHomeOpr = new HomeOpr();

		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		DAOResult daoResult = new DAOResult();
		myLog.debug("inside bc at here::::::::");

		Object strSqlParams[][];
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, HomeSqlTemplate.SEARCH_ROLLING_IMAGES);

		strSqlParams = new Object[0][3];

		daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap responseMap = daoResult.getInvocationResult();
		myLog.debug("executeSearch :: Resultset got ::" + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {
				HashMap resultRow = (HashMap) responseMap.get(i);

				ImageDVO imageDvo = new ImageDVO();

				if (resultRow.get("core_rolling_images_id") != null) {

					imageDvo.setId(Long.valueOf(resultRow.get("core_rolling_images_id").toString()));
				}

				if (resultRow.get("param_sequence_number") != null) {
					imageDvo.setSequenceNumber(Long.valueOf(resultRow.get("param_sequence_number").toString()));
				}

				imageDvo.setImageURL((String) resultRow.get("image_url"));

				imageDvo.setZoomImageURL((String) resultRow.get("link_page_url"));

				searchHomeOpr.getImageList().add(imageDvo);
			}
		} else {
			myLog.error("Home page :: executeSearch failed :: Return Record empty ::::: ");
			throw new BusinessException("no_data_from_db_excep_msg");
		}

		return searchHomeOpr;
	}

	public HomeOpr getCurrencyData(HomeOpr homeOpr) throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		DAOResult daoResult = new DAOResult();

		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, HomeSqlTemplate.GET_CURRENCY_DETAILS);

		Object strSqlParams[][] = new Object[1][3];
		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[0][2] = homeOpr.getCountryRecord().getName();

		daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap responseMap = daoResult.getInvocationResult();

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {
				HashMap resultRow = (HashMap) responseMap.get(i);

				if (resultRow.get("currency_symbol") != null) {
					homeOpr.getCurrencyRecord().setCurrencySymbol((String) resultRow.get("currency_symbol"));
				}
				myLog.debug("Inside SessionAttributeListenerBC currency_symbol::"
						+ homeOpr.getCurrencyRecord().getCurrencySymbol());
				if (resultRow.get("conversion_multiplier") != null) {
					homeOpr.getCurrencyRecord().setCurrencyConversionMultiplier(
							Float.valueOf(resultRow.get("conversion_multiplier").toString()));
				}
				myLog.debug("Inside SessionAttributeListenerBC conversion_multiplier::"
						+ homeOpr.getCurrencyRecord().getCurrencyConversionMultiplier());
			}
		}

		return homeOpr;
	}

	public HomeOpr getCategoryForHomePage(HomeOpr homeOpr) throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();

		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, HomeSqlTemplate.GET_CATEGORY_FOR_HOME_PAGE);

		Object strSqlParams[][] = new Object[0][3];

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" resultSet got ::: " + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {
				HashMap<String, Object> resultRow = responseMap.get(i);

				PublishToHomeCategoryDVO publishToHomeCategoryRecord = new PublishToHomeCategoryDVO();

				if (resultRow.get("publish_to_home_category_id") != null) {
					publishToHomeCategoryRecord.setId(Long.valueOf(resultRow.get("publish_to_home_category_id")
							.toString()));
				}

				if (resultRow.get("hierarchy_id") != null) {
					publishToHomeCategoryRecord.getHierarchyRecord().setId(
							Long.valueOf(resultRow.get("hierarchy_id").toString()));
				}

				publishToHomeCategoryRecord.getHierarchyRecord().setCode((String) resultRow.get("hierarchy_code"));

				if (resultRow.get("category_level_4") != null) {
					publishToHomeCategoryRecord.getCategoryRecord().setCode(
							(String) resultRow.get("level_4_category_code"));
					publishToHomeCategoryRecord.getCategoryRecord().setName(
							(String) resultRow.get("level_4_category_name"));
				} else if (resultRow.get("category_level_3") != null) {
					publishToHomeCategoryRecord.getCategoryRecord().setCode(
							(String) resultRow.get("level_3_category_code"));
					publishToHomeCategoryRecord.getCategoryRecord().setName(
							(String) resultRow.get("level_3_category_name"));
				} else if (resultRow.get("category_level_2") != null) {
					publishToHomeCategoryRecord.getCategoryRecord().setCode(
							(String) resultRow.get("level_2_category_code"));
					publishToHomeCategoryRecord.getCategoryRecord().setName(
							(String) resultRow.get("level_2_category_name"));
				} else if (resultRow.get("category_level_1") != null) {
					publishToHomeCategoryRecord.getCategoryRecord().setCode(
							(String) resultRow.get("level_1_category_code"));
					publishToHomeCategoryRecord.getCategoryRecord().setName(
							(String) resultRow.get("level_1_category_name"));
				}

				if (resultRow.get("publish_position") != null) {
					publishToHomeCategoryRecord.setPublishPosition(Integer.valueOf(resultRow.get("publish_position")
							.toString()));
				}

				publishToHomeCategoryRecord.getCategoryRecord().setImageUrl((String) resultRow.get("image_url"));

				publishToHomeCategoryRecord.getCategoryLevelOneRecord().setCode(
						(String) resultRow.get("level_1_category_code"));

				publishToHomeCategoryRecord.getCategoryLevelTwoRecord().setCode(
						(String) resultRow.get("level_2_category_code"));

				publishToHomeCategoryRecord.getCategoryLevelThreeRecord().setCode(
						(String) resultRow.get("level_3_category_code"));

				publishToHomeCategoryRecord.getCategoryLevelFourRecord().setCode(
						(String) resultRow.get("level_4_category_code"));

				homeOpr.getHomePageCategoryList().add(publishToHomeCategoryRecord);
			}
		}

		return homeOpr;
	}
}
