package com.web.bc.retail.modules.registrationpanel;

import java.util.HashMap;

import com.web.common.dvo.common.OperationalDataValueObject;
import com.web.common.dvo.opr.retail.RegistrationPanelOpr;
import com.web.common.parents.BackingClass;
import com.web.foundation.dao.DAOResult;
import com.web.foundation.dao.IDAOConstant;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;

public class RegistrationPanelBC extends BackingClass {

	public RegistrationPanelOpr executeRegister(RegistrationPanelOpr registrationPanelOpr) throws FrameworkException,
			BusinessException {
		// BACKING CLASS METHOD TEMPLATE ver 1.0
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug(" inside executeRegister: ");

		String userLogin = registrationPanelOpr.getUserDetails().getUserLogin();
		String firstName = registrationPanelOpr.getUserDetails().getFirstName();
		String lastName = registrationPanelOpr.getUserDetails().getLastName();
		String primaryEmailId = registrationPanelOpr.getUserDetails().getPrimaryEmailId();
		Boolean conditionAccepted = registrationPanelOpr.getUserDetails().isConditionAccepted();
		String password = registrationPanelOpr.getUserDetails().getLoginPassword();
		String primaryPhoneNumber = registrationPanelOpr.getUserDetails().getPrimaryPhoneNumber();

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, RegistrationPanelSqlTemplate.REGISTER_USER);

		Object strSqlParams[][] = new Object[7][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[0][2] = userLogin;
		myLog.debug(" parameter 1 userLogin:: " + userLogin);

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[1][2] = firstName;
		myLog.debug(" parameter 2 firstName:: " + firstName);

		strSqlParams[2][0] = "3";
		strSqlParams[2][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[2][2] = lastName;
		myLog.debug(" parameter 3 lastName:: " + lastName);

		strSqlParams[3][0] = "4";
		strSqlParams[3][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[3][2] = primaryEmailId;
		myLog.debug(" parameter 4 primaryEmailId:: " + primaryEmailId);

		strSqlParams[4][0] = "5";
		strSqlParams[4][1] = IDAOConstant.BOOLEAN_DATATYPE;
		strSqlParams[4][2] = conditionAccepted;
		myLog.debug(" parameter 5 conditionAccepted:: " + conditionAccepted);

		strSqlParams[5][0] = "6";
		strSqlParams[5][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[5][2] = password;
		myLog.debug(" parameter 6 password:: " + password);

		strSqlParams[6][0] = "7";
		strSqlParams[6][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[6][2] = primaryPhoneNumber;
		myLog.debug(" parameter 7 primaryPhoneNumber:: " + primaryPhoneNumber);

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug("::executeRegister Resultset got ::" + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {
				HashMap<String, Object> resultSetMap = responseMap.get(i);
				handleAndThrowException(resultSetMap);
				if (resultSetMap.get("v_user_id") != null) {
					registrationPanelOpr.getUserDetails().setId(Long.valueOf(resultSetMap.get("v_user_id").toString()));
				}
			}
		} else {
			myLog.error("RegistrationPanelBC :: executeRegister failed :: Return Record empty ::::: ");
		}

		return registrationPanelOpr;
	}

	public RegistrationPanelOpr checkUserAvailability(OperationalDataValueObject queryParameters)
			throws FrameworkException, BusinessException {
		// BACKING CLASS METHOD TEMPLATE ver 1.0
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		DAOResult daoResult = new DAOResult();
		RegistrationPanelOpr registrationPanelOpr = (RegistrationPanelOpr) queryParameters;
		RegistrationPanelOpr returnRegistrationPanelOpr = new RegistrationPanelOpr();

		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, RegistrationPanelSqlTemplate.CHECK_USER_AVAILABILITY);

		Object strSqlParams[][] = new Object[1][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[0][2] = registrationPanelOpr.getUserDetails().getUserLogin();

		// in the following call replace null with dynamic where clause if
		// required
		daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(":: Resultset got ::" + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {
				HashMap<String, Object> resultSetMap = responseMap.get(i);
				handleAndThrowException(resultSetMap);
				returnRegistrationPanelOpr.getUserDetails().setUserLogin((String) resultSetMap.get("user_login"));
			}
		}

		return returnRegistrationPanelOpr;
	}

	public RegistrationPanelOpr executeRegisterDetails(OperationalDataValueObject queryParameters)
			throws FrameworkException, BusinessException {
		// BACKING CLASS METHOD TEMPLATE ver 1.0
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		RegistrationPanelOpr registrationPanelOpr = (RegistrationPanelOpr) queryParameters;

		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, RegistrationPanelSqlTemplate.REGISTER_USER_DETAILS);

		myLog.debug("billing user name" + registrationPanelOpr.getUserDetails().getUserLogin());
		myLog.debug("billing email id::::"
				+ registrationPanelOpr.getUserDetails().getPermanentAddressRecord().getEmail1());

		Object strSqlParams[][] = new Object[19][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[0][2] = registrationPanelOpr.getUserDetails().getUserLogin();

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[1][2] = registrationPanelOpr.getUserDetails().getPermanentAddressRecord().getLastName();

		strSqlParams[2][0] = "3";
		strSqlParams[2][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[2][2] = registrationPanelOpr.getUserDetails().getPermanentAddressRecord().getFirstName();

		strSqlParams[3][0] = "4";
		strSqlParams[3][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[3][2] = registrationPanelOpr.getUserDetails().getPermanentAddressRecord().getLastName();

		strSqlParams[4][0] = "5";
		strSqlParams[4][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[4][2] = registrationPanelOpr.getUserDetails().getPermanentAddressRecord().getEmail1();

		strSqlParams[5][0] = "6";
		strSqlParams[5][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[5][2] = registrationPanelOpr.getUserDetails().getPermanentAddressRecord().getPhone1();

		strSqlParams[6][0] = "7";
		strSqlParams[6][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[6][2] = registrationPanelOpr.getUserDetails().getPermanentAddressRecord().getAddressLine1();

		strSqlParams[7][0] = "8";
		strSqlParams[7][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[7][2] = registrationPanelOpr.getUserDetails().getPermanentAddressRecord().getAddressLine2();

		strSqlParams[8][0] = "9";
		strSqlParams[8][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[8][2] = registrationPanelOpr.getUserDetails().getPermanentAddressRecord().getCityDvo().getCode();

		strSqlParams[9][0] = "10";
		strSqlParams[9][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[9][2] = registrationPanelOpr.getUserDetails().getPermanentAddressRecord().getPinRecord().getCode();

		strSqlParams[10][0] = "11";
		strSqlParams[10][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[10][2] = registrationPanelOpr.getUserDetails().getPermanentAddressRecord().getCountryDvo()
				.getCode();

		strSqlParams[11][0] = "12";
		strSqlParams[11][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[11][2] = registrationPanelOpr.getUserDetails().getPermanentAddressRecord().getStateDVO().getCode();

		strSqlParams[12][0] = "13";
		strSqlParams[12][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[12][2] = registrationPanelOpr.getUserDetails().getShippingAddressRecord().getAddressLine1();

		strSqlParams[13][0] = "14";
		strSqlParams[13][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[13][2] = registrationPanelOpr.getUserDetails().getShippingAddressRecord().getAddressLine2();

		strSqlParams[14][0] = "15";
		strSqlParams[14][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[14][2] = registrationPanelOpr.getUserDetails().getShippingAddressRecord().getCityDvo().getCode();

		strSqlParams[15][0] = "16";
		strSqlParams[15][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[15][2] = registrationPanelOpr.getUserDetails().getShippingAddressRecord().getPinRecord().getCode();

		strSqlParams[16][0] = "17";
		strSqlParams[16][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[16][2] = registrationPanelOpr.getUserDetails().getShippingAddressRecord().getCountryDvo()
				.getCode();

		strSqlParams[17][0] = "18";
		strSqlParams[17][1] = IDAOConstant.BOOLEAN_DATATYPE;
		strSqlParams[17][2] = registrationPanelOpr.getUserDetails().isConditionAccepted();

		strSqlParams[18][0] = "19";
		strSqlParams[18][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[18][2] = registrationPanelOpr.getUserDetails().getShippingAddressRecord().getStateDVO().getCode();

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(":: Resultset got ::" + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {
				HashMap<String, Object> resultRow = responseMap.get(i);
				handleAndThrowException(resultRow);
				if (resultRow.get("v_user_id") != null) {

					registrationPanelOpr.getUserDetails().setId(Long.valueOf(resultRow.get("v_user_id").toString()));
				}
			}
		} else {
			myLog.error("RegistrationPanelBC :: executeRegister failed :: Return Record empty ::::: ");
			throw new BusinessException("no_data_from_db_excep_msg");
		}

		return registrationPanelOpr;
	}

	// public RegistrationPanelOpr saveSmsGateWayResponse(RegistrationPanelOpr
	// registrationPanelOpr)
	// throws FrameworkException, BusinessException {
	// // BACKING CLASS METHOD TEMPLATE ver 1.0
	// ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
	// HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
	// DAOResult daoResult = new DAOResult();
	// // OrderManagementOpr addOrderManagementOpr = (OrderManagementOpr)
	// // saveParameters;
	// myLog.debug("Inside RgistrationPanelBC :: saveSmsGateWayResponse"
	// + registrationPanelOpr.getSmsGateWayRecord().getSmsResponseCode());
	// queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.INSERT_SQL);
	// queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE,
	// IDAOConstant.PREPARED_STATEMENT);
	// queryDetailsMap.put(IDAOConstant.SQL_TEXT,
	// RegistrationPanelSqlTemplate.SAVE_SMS_GATEWAYDATA);
	//
	// Object strSqlParams[][] = new Object[5][3];
	//
	// strSqlParams[0][0] = "1";
	// strSqlParams[0][1] = IDAOConstant.STRING_DATATYPE;
	// strSqlParams[0][2] = "User Registration";
	// myLog.debug("strSqlParams[0][2]::::" + strSqlParams[0][2]);
	//
	// strSqlParams[1][0] = "2";
	// strSqlParams[1][1] = IDAOConstant.STRING_DATATYPE;
	// strSqlParams[1][2] =
	// registrationPanelOpr.getSmsGateWayRecord().getSmsResponseCode();
	// myLog.debug("strSqlParams[1][2]::::" + strSqlParams[1][2]);
	//
	// strSqlParams[2][0] = "3";
	// strSqlParams[2][1] = IDAOConstant.STRING_DATATYPE;
	// strSqlParams[2][2] =
	// registrationPanelOpr.getSmsGateWayRecord().getDestinationNumber();
	// myLog.debug("strSqlParams[2][2]::::" + strSqlParams[2][2]);
	//
	// strSqlParams[3][0] = "4";
	// strSqlParams[3][1] = IDAOConstant.STRING_DATATYPE;
	// strSqlParams[3][2] =
	// registrationPanelOpr.getUserDetails().getUserLogin();
	// myLog.debug("strSqlParams[3][2]::::" + strSqlParams[3][2]);
	//
	// strSqlParams[4][0] = "5";
	// strSqlParams[4][1] = IDAOConstant.STRING_DATATYPE;
	// strSqlParams[4][2] =
	// registrationPanelOpr.getUserDetails().getUserLogin();
	// myLog.debug("strSqlParams[4][2]::::" + strSqlParams[4][2]);
	//
	// performDBOperation(queryDetailsMap, strSqlParams, null);
	//
	// return registrationPanelOpr;
	// }
}
