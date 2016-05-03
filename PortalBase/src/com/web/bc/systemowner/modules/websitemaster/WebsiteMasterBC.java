package com.web.bc.systemowner.modules.websitemaster;

import java.util.Date;
import java.util.HashMap;

import com.web.common.dvo.common.CatalogWebsiteMappingDVO;
import com.web.common.dvo.common.OperationalDataValueObject;
import com.web.common.dvo.common.WebsiteDVO;
import com.web.common.dvo.opr.systemowner.WebsiteOpr;
import com.web.common.parents.BackingClass;
import com.web.foundation.dao.DAOResult;
import com.web.foundation.dao.IDAOConstant;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;

public class WebsiteMasterBC extends BackingClass {

	public WebsiteOpr executeQuickEditSave(OperationalDataValueObject saveParameters) throws FrameworkException,
			BusinessException {
		// TODO Auto-generated method stub
		// BACKING CLASS METHOD TEMPLATE ver 1.0
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("inside WebsiteMasterBC :: executeQuickEditSave");
		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		DAOResult daoResult = new DAOResult();

		WebsiteOpr quickEditWebsiteOpr = (WebsiteOpr) saveParameters;

		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.UPDATE_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, WebsiteMasterSQLTemplate.QUICK_EDIT_SAVE);

		Object strSqlParams[][] = new Object[4][3];
		String websiteName = null;
		if (quickEditWebsiteOpr.getWebsiteRecord().getName() != null) {
			websiteName = quickEditWebsiteOpr.getWebsiteRecord().getName().trim();
		} else {
			websiteName = quickEditWebsiteOpr.getWebsiteRecord().getName();
		}

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[0][2] = websiteName;
		myLog.debug("website name ::" + websiteName);

		String websiteUrl = null;
		if (quickEditWebsiteOpr.getWebsiteRecord().getName() != null) {
			websiteUrl = quickEditWebsiteOpr.getWebsiteRecord().getSiteURL().trim();
		} else {
			websiteUrl = quickEditWebsiteOpr.getWebsiteRecord().getSiteURL();
		}

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[1][2] = websiteUrl;
		myLog.debug("website url ::" + websiteUrl);

		strSqlParams[2][0] = "3";
		strSqlParams[2][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[2][2] = quickEditWebsiteOpr.getWebsiteRecord().getAuditAttributes().getModifiedBy();
		myLog.debug("modified by ::" + quickEditWebsiteOpr.getWebsiteRecord().getAuditAttributes().getModifiedBy());

		strSqlParams[3][0] = "4";
		strSqlParams[3][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[3][2] = quickEditWebsiteOpr.getWebsiteRecord().getId();
		myLog.debug("website id ::" + quickEditWebsiteOpr.getWebsiteRecord().getId());

		// in the following call replace null with dynamic where clause if
		// required
		daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		myLog.debug("::Updated Resultset got ::" + daoResult.getUpdateResult());

		if (daoResult.getUpdateResult() > 0) {
			myLog.error("WebsiteMasterBC:: executeQuickEditSave :::update is successfull:::");
		} else {
			myLog.error("WebsiteMasterBC:: executeQuickEditSave failed :: Return Record empty ::::: ");
			throw new BusinessException("no_data_from_db_excep_msg");
		}
		myLog.debug("outside WebsiteMasterBC :: executeQuickEditSave");
		return quickEditWebsiteOpr;
	}

	public WebsiteOpr checkWebsiteUrlAvailability(OperationalDataValueObject saveParameters) throws FrameworkException,
			BusinessException {
		// BACKING CLASS METHOD TEMPLATE ver 1.0
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("inside WebsiteMasterBC ::checkWebsiteUrlAvailability::");
		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		DAOResult daoResult = new DAOResult();

		WebsiteOpr addWebsiteOpr = (WebsiteOpr) saveParameters;
		WebsiteOpr returnWebsiteOpr = new WebsiteOpr();

		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, WebsiteMasterSQLTemplate.CHECK_WEBSITE_URL_AVAILABILITY);

		Object strSqlParams[][] = new Object[1][3];

		String websiteUrl = addWebsiteOpr.getWebsiteRecord().getSiteURL();

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[0][2] = websiteUrl;

		// in the following call replace null with dynamic where clause if
		// required
		daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap responseMap = daoResult.getInvocationResult();
		myLog.debug(":: Resultset got ::" + responseMap);

		if (responseMap == null) {
			responseMap = new HashMap();
		}

		myLog.debug("inside checkWebsiteUrlAvailability :::" + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {
				HashMap resultRow = (HashMap) responseMap.get(i);
				// PROCESS OBJECT RETURNED IN THE resultRow IF SELECT QUERY HAS
				// BEEN FIRED
				myLog.debug("inside for loop ::: responseMap.size() " + responseMap.size());

				if (resultRow.get("website_url") != null) {
					returnWebsiteOpr.getWebsiteRecord().setSiteURL((String) resultRow.get("website_url"));
					myLog.debug("website_url ::: " + returnWebsiteOpr.getWebsiteRecord().getSiteURL());
				}
			}
		} else {
			myLog.debug("inside else part");
		}

		myLog.debug("outside WebsiteMasterBC ::: checkWebsiteUrlAvailability");
		return returnWebsiteOpr;
	}

	public WebsiteOpr checkWebsiteNameAvailability(OperationalDataValueObject saveParameters)
			throws FrameworkException, BusinessException {
		// BACKING CLASS METHOD TEMPLATE ver 1.0
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("inside WebsiteMasterBC :::checkWebsiteNameAvailability");
		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		DAOResult daoResult = new DAOResult();

		WebsiteOpr addWebsiteOpr = (WebsiteOpr) saveParameters;
		WebsiteOpr returnWebsiteOpr = new WebsiteOpr();

		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, WebsiteMasterSQLTemplate.CHECK_WEBSITE_NAME_AVAILABILITY);

		Object strSqlParams[][] = new Object[1][3];

		String websiteName = addWebsiteOpr.getWebsiteRecord().getName();

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[0][2] = websiteName;

		// in the following call replace null with dynamic where clause if
		// required
		daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap responseMap = daoResult.getInvocationResult();
		myLog.debug(":: Resultset got ::" + responseMap);

		if (responseMap == null) {
			responseMap = new HashMap();

		}

		myLog.debug("invoked checkWebsiteNameAvailability ::: " + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {
				HashMap resultRow = (HashMap) responseMap.get(i);
				// PROCESS OBJECT RETURNED IN THE resultRow IF SELECT QUERY HAS
				// BEEN FIRED
				myLog.debug("inside for loop ::: responseMap.size() " + responseMap.size());

				if (resultRow.get("website_name") != null) {
					returnWebsiteOpr.getWebsiteRecord().setName((String) resultRow.get("website_name"));
					myLog.debug("website name ::: " + returnWebsiteOpr.getWebsiteRecord().getName());
				}
			}
		} else {
			myLog.error("inside else part");
		}
		myLog.debug("outside WebsiteMasterBC :::checkWebsiteNameAvailability");
		return returnWebsiteOpr;
	}

	public WebsiteOpr executeSave(OperationalDataValueObject saveParameters) throws FrameworkException,
			BusinessException {
		// BACKING CLASS METHOD TEMPLATE ver 1.0
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		DAOResult daoResult = new DAOResult();

		WebsiteOpr addWebsiteOpr = (WebsiteOpr) saveParameters;

		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, WebsiteMasterSQLTemplate.ADD_WEBSITE);

		Object strSqlParams[][] = new Object[3][3];
		String websiteName = null;
		if (addWebsiteOpr.getWebsiteRecord().getName() != null) {
			websiteName = addWebsiteOpr.getWebsiteRecord().getName().trim();
		} else {
			websiteName = addWebsiteOpr.getWebsiteRecord().getName();
		}
		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[0][2] = websiteName;
		String websiteUrl = null;
		if (addWebsiteOpr.getWebsiteRecord().getSiteURL() != null) {
			websiteUrl = addWebsiteOpr.getWebsiteRecord().getSiteURL().trim();
		} else {
			websiteUrl = addWebsiteOpr.getWebsiteRecord().getSiteURL();
		}

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[1][2] = websiteUrl;

		strSqlParams[2][0] = "3";
		strSqlParams[2][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[2][2] = addWebsiteOpr.getWebsiteRecord().getAuditAttributes().getModifiedBy();

		// in the following call replace null with dynamic where clause if
		// required
		daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap responseMap = daoResult.getInvocationResult();
		myLog.debug(":: Resultset got ::" + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {
				HashMap resultRow = (HashMap) responseMap.get(i);

				// FOLLOWING CODE TO BE KEPT FOR INSERT/UPDATE/DELETE STORED
				// PROCEDURE CALL ONLY
				if (resultRow.get("success_flag") != null && resultRow.get("core_website_id") != null) {
					myLog.debug(" WebsiteMasterBC :: executeAddNewWebsite Successful");
					addWebsiteOpr.getWebsiteRecord().setName(addWebsiteOpr.getWebsiteRecord().getName());
					addWebsiteOpr.getWebsiteRecord().setSiteURL(addWebsiteOpr.getWebsiteRecord().getSiteURL());
					addWebsiteOpr.getWebsiteRecord().getAuditAttributes()
							.setModifiedBy(addWebsiteOpr.getWebsiteRecord().getAuditAttributes().getModifiedBy());

				} else {
					myLog.error("WebsiteMasterBC :: executeAddNewWebsite failed :: Success Flag empty ::::: ");
					throw new BusinessException("default_error_message");
				}
				// END CODE TO BE KEPT FOR INSERT/UPDATE/DELETE STORED PROCEDURE
				// CALL ONLY
			}
		} else {
			myLog.error("WebsiteMasterBC:: executeAddNewWebsite failed :: Return Record empty ::::: ");
			throw new BusinessException("no_data_from_db_excep_msg");
		}
		myLog.debug("outside WebsiteMasterBC ::executeAddNewWebsite");
		return addWebsiteOpr;
	}

	public WebsiteOpr getAllWebsites(WebsiteOpr searchParameters) throws FrameworkException, BusinessException {
		// BACKING CLASS METHOD TEMPLATE ver 1.0
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug(" inside getAllWebsites ::: ");

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();

		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE,
				IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT,
				WebsiteMasterSQLTemplate.ALL_WEBSITE_NAMES);

		WebsiteOpr websiteOprReturn = new WebsiteOpr();

		Object strSqlParams[][] = new Object[0][3];

		// in the following call replace null with dynamic where clause if
		// required
		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams,
				null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult
				.getInvocationResult();
		myLog.debug(" resultset got ::: " + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {
				HashMap<String, Object> resultRow = responseMap.get(i);
				WebsiteDVO websiteDVO = new WebsiteDVO();

				if (resultRow.get("core_website_id") != null) {
					websiteDVO.setId(Long.valueOf(resultRow.get(
							"core_website_id").toString()));
				}

				websiteDVO.setName((String) resultRow.get("website_name"));

				websiteDVO.setSiteURL((String) resultRow.get("website_url"));

				websiteDVO.getAuditAttributes().setCreatedBy(
						(String) resultRow.get("created_by"));

				websiteDVO.getAuditAttributes().setCreatedDate(
						((Date) resultRow.get("created_date")));

				websiteDVO.getAuditAttributes().setModifiedBy(
						(String) resultRow.get("modified_by"));

				websiteDVO.getAuditAttributes().setLastModifiedDate(
						(Date) resultRow.get("modified_date"));

				websiteOprReturn.getWebsiteList().add(websiteDVO);

			}
		} else {
			myLog.error("WebsiteMasterBC :: getAllWebsites failed :: Return Record empty ::::: ");
			// throw new BusinessException("no_data_from_db_excep_msg");
		}

		return websiteOprReturn;
	}

	public WebsiteOpr getWebsitesForCatalog(OperationalDataValueObject searchParameters) throws FrameworkException,
			BusinessException {
		// BACKING CLASS METHOD TEMPLATE ver 1.0
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("inside WebsiteMasterBC ::getWebsitesForCatalog() :: ");
		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		DAOResult daoResult = new DAOResult();

		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, WebsiteMasterSQLTemplate.WEBSITES_FOR_CATALOG);

		WebsiteOpr websiteOpr = (WebsiteOpr) searchParameters;
		WebsiteOpr returnWebsiteOpr = new WebsiteOpr();

		Object strSqlParams[][] = new Object[1][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[0][2] = websiteOpr.getCatalogRecord().getId();

		// in the following call replace null with dynamic where clause if
		// required
		daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap responseMap = daoResult.getInvocationResult();
		myLog.debug(":: Resultset got ::" + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {
				HashMap resultRow = (HashMap) responseMap.get(i);
				// PROCESS OBJECT RETURNED IN THE resultRow IF SELECT QUERY HAS
				// BEEN FIRED
				CatalogWebsiteMappingDVO catalogWebsiteMappingDVO = new CatalogWebsiteMappingDVO();

				if (resultRow.get("core_website_id") != null) {
					catalogWebsiteMappingDVO.getWebsiteRecord().setId(
							Long.valueOf(resultRow.get("core_website_id").toString()));
				}
				if (resultRow.get("website_name") != null) {
					catalogWebsiteMappingDVO.getWebsiteRecord().setName((String) (resultRow.get("website_name")));
				}
				if (resultRow.get("website_url") != null) {
					catalogWebsiteMappingDVO.getWebsiteRecord().setName((String) (resultRow.get("website_url")));
				}
				returnWebsiteOpr.getCatalogRecord().getMappedWebsiteList().add(catalogWebsiteMappingDVO);
			}
		} else {
			myLog.error(":: WebsiteMasterBC getWebsitesForCatalog failed :: Return Record empty ::::: ");
			throw new BusinessException("no_data_from_db_excep_msg");
		}

		return returnWebsiteOpr;

	}

	public WebsiteOpr executeWebsiteNameAutoSuggestSearch(OperationalDataValueObject searchParameters)
			throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("WebsiteMasterBC :: inside executeWebsiteNameAutoSuggestSearch()");

		WebsiteOpr searchWebsiteOpr = (WebsiteOpr) searchParameters;
		WebsiteOpr searchResultsWebsiteOpr = new WebsiteOpr();

		searchResultsWebsiteOpr.setWebsiteRecord(searchWebsiteOpr.getWebsiteRecord());

		// BACKING CLASS METHOD TEMPLATE ver 1.0

		myLog.debug("m in website name auto suggest.....");
		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		DAOResult daoResult = new DAOResult();

		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, WebsiteMasterSQLTemplate.SEARCH_AUTOSUGGEST_WEBSITE_NAMES);

		myLog.debug("executing statement " + WebsiteMasterSQLTemplate.SEARCH_AUTOSUGGEST_WEBSITE_NAMES);

		Object strSqlParams[][] = new Object[1][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[0][2] = searchWebsiteOpr.getWebsiteRecord().getName().trim().concat("%");

		// in the following call replace null with dynamic where clause if
		// required
		daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap responseMap = daoResult.getInvocationResult();
		myLog.debug(":: Resultset got ::" + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {
				HashMap resultRow = (HashMap) responseMap.get(i);
				// PROCESS OBJECT RETURNED IN THE resultRow IF SELECT QUERY HAS
				// BEEN FIRED
				WebsiteDVO websiteRecord = new WebsiteDVO();

				websiteRecord.setId((Long) (resultRow.get("core_website_id")));
				websiteRecord.setName((String) (resultRow.get("website_name")));
				websiteRecord.setSiteURL((String) (resultRow.get("website_url")));
				searchResultsWebsiteOpr.getWebsiteList().add(websiteRecord);
			}
		} else {
			myLog.error("WebsiteMasterBC :: Autosuggest Search failed :: Return Record empty ::::: ");
			throw new BusinessException("no_data_from_db_excep_msg");
		}
		myLog.debug("WebsiteMasterBC :: getting out executeWebsiteNameAutoSuggestSearch()");
		return searchResultsWebsiteOpr;
	}
}
