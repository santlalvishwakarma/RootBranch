package com.web.bc.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.web.common.constants.CommonConstant;
import com.web.common.dvo.common.CountryDVO;
import com.web.common.dvo.common.Parameter;
import com.web.common.dvo.common.StateDVO;
import com.web.common.dvo.opr.common.ParameterOpr;
import com.web.common.dvo.opr.retail.LoginPanelOpr;
import com.web.common.dvo.retail.modules.user.UserDVO;
import com.web.common.dvo.retail.modules.user.UserRoleMappingDVO;
import com.web.common.dvo.systemowner.CityDVO;
import com.web.common.parents.BackingClass;
import com.web.foundation.dao.DAOResult;
import com.web.foundation.dao.IDAOConstant;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;

public class OptionsHelperBC extends BackingClass {

	public ArrayList<Object> getYesNoList() throws FrameworkException, BusinessException {
		ArrayList<Object> yesNoValuesList = new ArrayList<Object>();
		Parameter parameterYes = new Parameter();
		parameterYes.setParameterCode(CommonConstant.YesNoValues.YES);
		parameterYes.setParameterBooleanValue(true);
		parameterYes.setParameterStringValue("Yes");
		yesNoValuesList.add(parameterYes);

		Parameter parameterNo = new Parameter();
		parameterNo.setParameterCode(CommonConstant.YesNoValues.NO);
		parameterNo.setParameterBooleanValue(false);
		parameterNo.setParameterStringValue("No");
		yesNoValuesList.add(parameterNo);

		return yesNoValuesList;
	}

	public LoginPanelOpr validateLoginDetails(LoginPanelOpr loginOpr) throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		String userLogin = loginOpr.getUserDetails().getUserLogin();
		String password = loginOpr.getUserDetails().getLoginPassword();

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, OptionsSqlTemplate.VALIDATE_LOGIN_DETAILS);

		Object strSqlParams[][] = new Object[2][3];
		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[0][2] = userLogin;
		myLog.debug("::: USER LOGIN IN BC :: " + userLogin);

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[1][2] = password;
		myLog.debug("::: PASSWORD IN BC :: " + password);

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);

		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" login result :: Resultset got ::" + responseMap);

		if (responseMap != null && responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {
				HashMap<String, Object> resultSetMap = responseMap.get(i);
				UserDVO userRecord = new UserDVO();

				userRecord.setUserLogin((String) resultSetMap.get("user_login"));
				if (resultSetMap.get("user_id") != null)
					userRecord.setId(Long.valueOf(resultSetMap.get("user_id").toString()));

				userRecord.setFirstName((String) resultSetMap.get("first_name"));
				userRecord.setMiddleName((String) resultSetMap.get("middle_name"));
				userRecord.setLastName((String) resultSetMap.get("last_name"));
				userRecord.setPrimaryEmailId((String) resultSetMap.get("primary_email_id"));
				userRecord.setSecondaryEmailId((String) resultSetMap.get("alternate_email_id"));
				userRecord.setIsAdmin((Boolean) resultSetMap.get("is_admin"));
				loginOpr.setUserDetails(userRecord);
			}
		} else {
			throw new BusinessException("invalid_user_msg");
		}
		return loginOpr;
	}

	public ParameterOpr getOptionsOnParameterCode(ParameterOpr parameterOpr) throws FrameworkException,
			BusinessException {
		ParameterOpr parameterOprRet = new ParameterOpr();
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("Inside getOptionsOnParameterCode method called");

		HashMap<String, String> hmInput = new HashMap<String, String>();

		hmInput.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		hmInput.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		hmInput.put(IDAOConstant.SQL_TEXT, OptionsSqlTemplate.GET_PARAM_CODE_OPTIONS);

		ArrayList<Parameter> parameterList = parameterOpr.getParameterList();
		HashMap<String, ArrayList<Object>> parameterOptionsMap = new HashMap<String, ArrayList<Object>>();
		int size = parameterList.size();

		for (int i = 0; i < size; i++) {

			Parameter parameter = parameterList.get(i);
			Object strSqlParams[][] = new Object[1][3];

			strSqlParams[0][0] = "1";
			strSqlParams[0][1] = IDAOConstant.STRING_DATATYPE;
			strSqlParams[0][2] = parameter.getParameterCode();
			myLog.debug(" parameter 1 :: " + parameter.getParameterCode());

			DAOResult daoResult = performDBOperation(hmInput, strSqlParams, null);
			HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
			myLog.debug(" getOptionsOnParameterCode :: Resultset got ::" + responseMap);

			if (responseMap.size() > 0) {
				ArrayList<Object> parameterListAL = new ArrayList<Object>();
				for (int k = 0; k < responseMap.size(); k++) {

					Parameter parameter1 = new Parameter();
					HashMap<String, Object> map = responseMap.get(k);

					parameter1.setParameterStringValue((String) map.get("value_text"));
					if (map.get("value_numeric") != null)
						parameter1.setParameterNumberValue(Float.valueOf(map.get("value_numeric").toString()));
					if (map.get("value_date") != null)
						parameter1.setParameterDateValue((Date) map.get("value_date"));

					String paramCode = (String) map.get("param_code");
					Integer paramId = map.get("parameter_id") != null ? Integer.valueOf(map.get("parameter_id")
							.toString()) : null;
					Integer sequenceNumber = map.get("sequence_number") != null ? Integer.valueOf(map.get(
							"sequence_number").toString()) : null;
					parameter1.setParameterCode(paramCode);
					parameter1.setParameterID(paramId);
					parameter1.setSequenceNumber(sequenceNumber);
					parameter1.setParameterDescription((String) map.get("param_description"));
					parameterListAL.add(parameter1);

				}
				parameterOptionsMap.put(parameter.getParameterCode(), parameterListAL);
			}
		}
		parameterOprRet.setParameterOptionsMap(parameterOptionsMap);
		return parameterOprRet;
	}

	public ArrayList<Object> getCountryList(CountryDVO countryDVO) throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Options Helper BC :: getCountryList starts ");

		String contryCode = countryDVO.getCode();
		String contryName = countryDVO.getName();
		String contryDiscription = countryDVO.getDescription();
		Boolean isActive = countryDVO.getActive();

		myLog.debug("In contryCode " + contryCode);
		myLog.debug("In contryName " + contryName);
		myLog.debug("In contryDiscription " + contryDiscription);
		myLog.debug("In isActive " + isActive);

		StringBuffer dynamicWhere = new StringBuffer();

		int parameterCount = 0;

		if (contryCode != null && contryCode.trim().length() > 0) {
			contryCode = contryCode.trim().concat("%");
			if (parameterCount > 0) {
				dynamicWhere.append(" AND ccm.country_code LIKE '" + contryCode + "'");
			} else {
				dynamicWhere.append(" ccm.country_code LIKE '" + contryCode + "'");
			}
			parameterCount++;
		}

		if (contryName != null && contryName.trim().length() > 0) {
			contryName = contryName.trim().concat("%");
			if (parameterCount > 0) {
				dynamicWhere.append(" AND ccm.country_name LIKE '" + contryName + "'");
			} else {
				dynamicWhere.append(" ccm.country_name LIKE '" + contryName + "'");
			}
			parameterCount++;
		}

		if (contryDiscription != null && contryDiscription.trim().length() > 0) {
			contryDiscription = contryDiscription.trim().concat("%");
			if (parameterCount > 0) {
				dynamicWhere.append(" AND ccm.country_description LIKE '" + contryDiscription + "'");
			} else {
				dynamicWhere.append(" ccm.country_description LIKE '" + contryDiscription + "'");
			}
			parameterCount++;
		}

		if (isActive == null || isActive) {
			if (parameterCount > 0) {
				dynamicWhere.append(" AND is_active = 1 ");
			} else {
				dynamicWhere.append(" is_active = 1 ");
			}
			parameterCount += 1;
		}
		if (parameterCount == 0) {
			dynamicWhere.append(" 1 = 1 ");
		}

		dynamicWhere.append(" ORDER BY ccm.country_name;");

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, OptionsSqlTemplate.GET_COUNTRY_LIST);
		Object strSqlParams[][] = new Object[0][3];

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, dynamicWhere.toString());
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" getCountryList :: Resultset got ::" + responseMap);

		ArrayList<Object> countryList = new ArrayList<Object>();
		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {
				HashMap<String, Object> resultSetMap = responseMap.get(i);
				CountryDVO countryDvo = new CountryDVO();

				countryDvo.setCode((String) resultSetMap.get("country_code"));
				countryDvo.setName((String) resultSetMap.get("country_name"));
				countryList.add(countryDvo);
			}
		}
		CountryDVO countryDvo = new CountryDVO();
		countryDvo.setCode("OTHER");
		countryDvo.setName("Other");
		countryList.add(countryDvo);

		return countryList;
	}

	public ArrayList<Object> getStateList(CountryDVO contryDvo) throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Options Helper BC :: getStateList starts ");

		String countryCode = contryDvo.getCode();
		String stateCode = contryDvo.getStateRecord().getCode();
		myLog.debug("Country Code is  " + countryCode + " State " + stateCode);

		StringBuffer dynamicWhere = new StringBuffer();
		int parameterCount = 0;

		if (countryCode != null && countryCode.trim().length() > 0) {
			if (parameterCount > 0) {
				dynamicWhere.append(" AND csm.country_code LIKE '" + countryCode + "'");
			} else {
				dynamicWhere.append(" csm.country_code LIKE '" + countryCode + "'");
			}
			parameterCount++;
		}

		if (stateCode != null && stateCode.trim().length() > 0) {
			stateCode = stateCode.trim().concat("%");
			if (parameterCount > 0) {
				dynamicWhere.append(" AND csm.state_code LIKE '" + stateCode + "'");
			} else {
				dynamicWhere.append(" csm.state_code LIKE '" + stateCode + "'");
			}
			parameterCount++;
		}
		if (parameterCount == 0) {
			dynamicWhere.append(" 1 = 1 ");
		}
		dynamicWhere.append(" ORDER BY csm.state_name;");
		myLog.debug("dynamicWhere ::::: " + dynamicWhere);

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, OptionsSqlTemplate.GET_STATE_LIST);
		Object strSqlParams[][] = new Object[0][3];

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, dynamicWhere.toString());
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" getUserLoginList :: Resultset got ::" + responseMap);

		ArrayList<Object> stateList = new ArrayList<Object>();
		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {
				HashMap<String, Object> resultSetMap = responseMap.get(i);
				StateDVO stateDvo = new StateDVO();

				stateDvo.setCode((String) resultSetMap.get("state_code"));
				stateDvo.setName((String) resultSetMap.get("state_name"));
				stateDvo.setDescription((String) resultSetMap.get("state_description"));
				stateList.add(stateDvo);
			}
		}
		return stateList;
	}

	public ArrayList<Object> getCityList(StateDVO stateDvo) throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Options Helper BC :: getCityList starts ");

		String cityCode = stateDvo.getCityRecord().getCode();
		String cityName = stateDvo.getCityRecord().getName();
		String cityDescription = stateDvo.getCityRecord().getDescription();
		Boolean isActive = stateDvo.getCityRecord().getActive();
		String stateCode = stateDvo.getCode();

		myLog.debug("In cityCode " + cityCode);
		myLog.debug("In cityName " + cityName);
		myLog.debug("In cityDescription " + cityDescription);
		myLog.debug("In stateCode " + stateCode);
		myLog.debug("In isActive " + isActive);

		StringBuffer dynamicWhere = new StringBuffer();

		int parameterCount = 0;

		if (cityCode != null && cityCode.trim().length() > 0) {
			cityCode = cityCode.trim().concat("%");
			if (parameterCount > 0) {
				dynamicWhere.append(" AND ccm.city_code LIKE '" + cityCode + "'");
			} else {
				dynamicWhere.append(" ccm.city_code LIKE '" + cityCode + "'");
			}
			parameterCount++;
		}

		if (stateCode != null && stateCode.trim().length() > 0) {
			if (parameterCount > 0) {
				dynamicWhere.append(" AND ccm.state_code = '" + stateCode + "'");
			} else {
				dynamicWhere.append(" ccm.state_code = '" + stateCode + "'");
			}
			parameterCount++;
		}

		if (cityName != null && cityName.trim().length() > 0) {
			cityName = cityName.trim().concat("%");
			if (parameterCount > 0) {
				dynamicWhere.append(" AND ccm.city_name LIKE '" + cityName + "'");
			} else {
				dynamicWhere.append(" ccm.city_name LIKE '" + cityName + "'");
			}
			parameterCount++;
		}

		if (cityDescription != null && cityDescription.trim().length() > 0) {
			cityDescription = cityDescription.trim().concat("%");
			if (parameterCount > 0) {
				dynamicWhere.append(" AND ccm.city_description LIKE '" + cityDescription + "'");
			} else {
				dynamicWhere.append(" ccm.city_description LIKE '" + cityDescription + "'");
			}
			parameterCount++;
		}

		if (isActive == null || isActive) {
			if (parameterCount > 0) {
				dynamicWhere.append(" AND is_active = 1 ");
			} else {
				dynamicWhere.append(" is_active = 1 ");
			}
			parameterCount += 1;
		}
		if (parameterCount == 0) {
			dynamicWhere.append(" 1 = 1 ");
		}

		dynamicWhere.append(" ORDER BY ccm.city_name;");

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, OptionsSqlTemplate.GET_CITY_LIST);
		Object strSqlParams[][] = new Object[0][3];

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, dynamicWhere.toString());
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" getCityList :: Resultset got ::" + responseMap);

		ArrayList<Object> cityList = new ArrayList<Object>();
		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {
				HashMap<String, Object> resultSetMap = responseMap.get(i);
				CityDVO cityDvo = new CityDVO();

				cityDvo.setCode((String) resultSetMap.get("city_code"));
				cityDvo.setName((String) resultSetMap.get("city_name"));
				cityDvo.setDescription((String) resultSetMap.get("city_description"));
				cityList.add(cityDvo);
			}
		}

		return cityList;
	}

	public LoginPanelOpr getUserBasedRole(LoginPanelOpr loginOpr)
			throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug(" inside getUserBasedRole ::: ");

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();

		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE,
				IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT,
				OptionsSqlTemplate.GET_USER_BASED_ROLE);

		Object[][] strSqlParams = new Object[1][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[0][2] = loginOpr.getUserDetails().getId();
		myLog.debug(" parameter 1 ::: " + strSqlParams[0][2]);

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams,
				null);

		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult
				.getInvocationResult();
		myLog.debug(" resultset got ::: " + responseMap);

		if (responseMap != null && responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size();) {
				HashMap<String, Object> resultRow = responseMap.get(i);
				UserRoleMappingDVO userRoleMappingRecord = new UserRoleMappingDVO();

				if (resultRow.get("users_roles_mapping_id") != null) {
					userRoleMappingRecord.setId(Long.valueOf(resultRow.get(
							"users_roles_mapping_id").toString()));
				}

				if (resultRow.get("role_id") != null) {
					userRoleMappingRecord.getRoleRecord().setId(
							Long.valueOf(resultRow.get("role_id").toString()));
				}

				userRoleMappingRecord.getRoleRecord().setCode(
						(String) resultRow.get("role_code"));

				userRoleMappingRecord.getRoleRecord().setName(
						(String) resultRow.get("role_name"));

				userRoleMappingRecord.getRoleRecord().setDescription(
						(String) resultRow.get("role_description"));

				if (resultRow.get("user_id") != null) {
					userRoleMappingRecord.getUserRecord().setId(
							Long.valueOf(resultRow.get("user_id").toString()));
				}

				userRoleMappingRecord.getUserRecord().setUserLogin(
						(String) resultRow.get("user_login"));

				loginOpr.getUserDetails().getUserRolesMappingList()
						.add(userRoleMappingRecord);
				break;
			}
		} else {
			throw new BusinessException("Roles not Defined for current user.");
		}

		return loginOpr;
	}

}
