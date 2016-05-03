package com.web.bc.retail.modules.subscribetonewsletter;

import java.util.HashMap;

import com.web.common.dvo.opr.retail.SubscribeToNewsletterOpr;
import com.web.common.parents.BackingClass;
import com.web.foundation.dao.DAOResult;
import com.web.foundation.dao.IDAOConstant;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;

public class SubscribeToNewsletterBC extends BackingClass {

	public SubscribeToNewsletterOpr executeSave(SubscribeToNewsletterOpr subscribeToNewsletterOpr)
			throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());

		String primaryEmailId = subscribeToNewsletterOpr.getUserDetails().getPrimaryEmailId();

		if (primaryEmailId != null) {
			primaryEmailId = primaryEmailId.trim();
		}

		String userLogin = subscribeToNewsletterOpr.getUserDetails().getUserLogin();

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, SubscribeToNewsletterSqlTemplate.REGISTER_NEWSLETTER_SUBSCRIPTION);

		Object strSqlParams[][] = new Object[2][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[0][2] = primaryEmailId;

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[1][2] = userLogin;

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(":: Resultset got ::" + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {
				HashMap<String, Object> resultSetMap = responseMap.get(i);
				handleAndThrowException(resultSetMap);
			}
		}

		return subscribeToNewsletterOpr;
	}

}
