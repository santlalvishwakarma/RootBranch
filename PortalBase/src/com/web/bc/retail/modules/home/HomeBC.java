package com.web.bc.retail.modules.home;

import java.util.HashMap;

import com.web.common.dvo.common.OperationalDataValueObject;
import com.web.common.dvo.opr.retail.HomeOpr;
import com.web.common.dvo.systemowner.ImageDVO;
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
}
