package com.web.common.startup.systemowner;

import java.util.HashMap;

import com.web.common.dvo.systemowner.PageNavigationDVO;
import com.web.common.parents.BackingClass;
import com.web.foundation.dao.DAOResult;
import com.web.foundation.dao.IDAOConstant;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;

public class StartupBC extends BackingClass {

	public HashMap<String, PageNavigationDVO> getPageNavigationDetails(HashMap pageNavigationArguments)
			throws FrameworkException, BusinessException {

		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug(" inside getPageNavigationDetails ::: ");

		HashMap<String, PageNavigationDVO> pageNavigationDetailsMap = new HashMap<String, PageNavigationDVO>();

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();

		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE,
				IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT,
				StartupSqlTemplate.PAGE_NAVIGATION_DETAILS);

		Object strSqlParams[][] = new Object[0][0];

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams,
				null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult
				.getInvocationResult();
		myLog.debug(" resultset got ::: " + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {
				HashMap<String, Object> resultRow = responseMap.get(i);
				PageNavigationDVO pageNavigationDetails = new PageNavigationDVO();

				pageNavigationDetails.setPageNavigationId(Long
						.valueOf(resultRow.get("core_page_navigation_id")
								.toString()));

				pageNavigationDetails.setPageCode((String) resultRow
						.get("page_code"));

				pageNavigationDetails.setPageURL((String) resultRow
						.get("page_url"));

				pageNavigationDetails.setPageDisplayName((String) resultRow
						.get("page_display_name"));

				pageNavigationDetails.getWebResourceAttributes().setTitle(
						(String) resultRow.get("page_title"));

				pageNavigationDetails.getWebResourceAttributes()
						.setResourceDescription(
								(String) resultRow.get("page_description"));

				pageNavigationDetails.getWebResourceAttributes()
						.setResourceKeywords(
								(String) resultRow.get("page_keywords"));

				pageNavigationDetails.getWebResourceAttributes()
						.setWebBotDirectives(
								(String) resultRow.get("page_robots"));

				pageNavigationDetails.getWebResourceAttributes().setPageType(
						(String) resultRow.get("page_type"));

				// below is a hack for backward compatibility of engine SEO

				pageNavigationDetails.getWebResourceAttributes()
						.setCanonicalURI(
								(String) resultRow.get("page_canonical_href"));

				pageNavigationDetails.getWebResourceAttributes()
						.setCanonicalURITitle(
								(String) resultRow.get("page_canonical_title"));

				pageNavigationDetails.getWebResourceAttributes()
						.setContentsResourceURI(
								(String) resultRow.get("page_contents_href"));

				pageNavigationDetails.getWebResourceAttributes()
						.setContentsResourceTitle(
								(String) resultRow.get("page_contents_title"));

				pageNavigationDetails.getWebResourceAttributes()
						.setStartResourceURI(
								(String) resultRow.get("page_start_href"));

				pageNavigationDetails.getWebResourceAttributes()
						.setStartResourceTitle(
								(String) resultRow.get("page_start_title"));

				pageNavigationDetails.getWebResourceAttributes()
						.setResourceAbstract(
								(String) resultRow.get("page_abstract"));

				pageNavigationDetailsMap.put(
						(String) resultRow.get("page_code"),
						pageNavigationDetails);
			}
		} else {
			myLog.error("StartupBC :: getPageNavigationDetails failed :: Return Record empty ::::: ");
			throw new BusinessException("no_data_from_db_excep_msg");
		}

		return pageNavigationDetailsMap;
	}

}
