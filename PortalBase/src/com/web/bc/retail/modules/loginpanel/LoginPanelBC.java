package com.web.bc.retail.modules.loginpanel;

import java.util.Date;
import java.util.HashMap;

import com.web.common.dvo.common.OperationalDataValueObject;
import com.web.common.dvo.opr.retail.LoginPanelOpr;
import com.web.common.dvo.retail.modules.user.UserDVO;
import com.web.common.parents.BackingClass;
import com.web.foundation.dao.DAOResult;
import com.web.foundation.dao.IDAOConstant;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;

public class LoginPanelBC extends BackingClass {

	public LoginPanelOpr executeLogin(LoginPanelOpr loginPanelOpr) throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();

		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, LoginPanelSqlTemplate.LOGIN_USER_BASE_QUERY);

		Object strSqlParams[][] = new Object[2][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[0][2] = loginPanelOpr.getUserDetails().getUserLogin().trim();

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[1][2] = loginPanelOpr.getUserDetails().getLoginPassword().trim();

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(":: Resultset got ::" + responseMap);

		// NOTE : WHEN B2B comes in picture, we will have to handle both retail
		// and wholesale user dvos here. Probably
		// based on site url
		UserDVO userDVO = new UserDVO();

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {
				HashMap<String, Object> resultRow = responseMap.get(i);
				userDVO.setId((Long) resultRow.get("user_id"));
				userDVO.setUserLogin((String) resultRow.get("user_login"));
				userDVO.setFirstName((String) resultRow.get("first_name"));
				userDVO.setLastName((String) resultRow.get("last_name"));
				userDVO.setPrimaryEmailId((String) resultRow.get("primary_email_id"));
				userDVO.setSecondaryEmailId((String) resultRow.get("alternate_email_id"));
				userDVO.setPrimaryPhoneNumber((String) resultRow.get("primary_phone_number"));
				userDVO.setAlternatePhoneNumber((String) resultRow.get("alternate_phone_number"));
				userDVO.setBirthDate((Date) resultRow.get("birth_date"));
				userDVO.setAnniversaryDate((Date) resultRow.get("anniversary_date"));

				if (resultRow.get("billing_address_id") != null) {
					userDVO.getBillingAddressRecord().setId(
							Long.valueOf(resultRow.get("billing_address_id").toString()));
				}

				if (resultRow.get("shipping_address_id") != null) {
					userDVO.getShippingAddressRecord().setId(
							Long.valueOf(resultRow.get("shipping_address_id").toString()));
				}

				userDVO.getBillingAddressRecord().getAddressDetailsRecord()
						.setAddressLine1((String) resultRow.get("billing_address_line1"));
				userDVO.getBillingAddressRecord().getAddressDetailsRecord()
						.setAddressLine2((String) resultRow.get("billing_address_line2"));
				userDVO.getBillingAddressRecord().getAddressDetailsRecord()
						.setAddressLine3((String) resultRow.get("billing_address_line3"));
				userDVO.getBillingAddressRecord().getAddressDetailsRecord().getCityDvo()
						.setCode((String) resultRow.get("billing_city_code"));
				userDVO.getBillingAddressRecord().getAddressDetailsRecord().getCityDvo()
						.setName((String) resultRow.get("billing_city_name"));
				userDVO.getBillingAddressRecord().getAddressDetailsRecord().getPinRecord()
						.setCode((String) resultRow.get("billing_zip_code"));

				userDVO.getBillingAddressRecord().getAddressDetailsRecord()
						.setPhone1((String) resultRow.get("billing_contact_person_1"));
				userDVO.getBillingAddressRecord().getAddressDetailsRecord()
						.setPhone2((String) resultRow.get("billing_contact_person_2"));

				userDVO.getBillingAddressRecord().getAddressDetailsRecord()
						.setEmail1((String) resultRow.get("billing_email1"));
				userDVO.getBillingAddressRecord().getAddressDetailsRecord()
						.setEmail2((String) resultRow.get("billing_email2"));

				userDVO.getBillingAddressRecord().getAddressDetailsRecord()
						.setContactPerson1((String) resultRow.get("billing_contact_person_1"));
				userDVO.getBillingAddressRecord().getAddressDetailsRecord()
						.setContactPerson2((String) resultRow.get("billing_contact_person_2"));
				userDVO.getBillingAddressRecord().getAddressDetailsRecord()
						.setLandmark((String) resultRow.get("billing_landmark"));

				userDVO.getBillingAddressRecord().getAddressDetailsRecord().getStateDVO()
						.setCode((String) resultRow.get("billing_state_code"));
				userDVO.getBillingAddressRecord().getAddressDetailsRecord().getStateDVO()
						.setName((String) resultRow.get("billing_state_name"));

				userDVO.getBillingAddressRecord().getAddressDetailsRecord().getCountryDvo()
						.setCode((String) resultRow.get("billing_country_code"));
				userDVO.getBillingAddressRecord().getAddressDetailsRecord().getCountryDvo()
						.setName((String) resultRow.get("billing_country_name"));

				userDVO.getShippingAddressRecord().getAddressDetailsRecord()
						.setAddressLine1((String) resultRow.get("shipping_address_line1"));
				userDVO.getShippingAddressRecord().getAddressDetailsRecord()
						.setAddressLine2((String) resultRow.get("shipping_address_line2"));
				userDVO.getShippingAddressRecord().getAddressDetailsRecord()
						.setAddressLine3((String) resultRow.get("shipping_address_line3"));
				userDVO.getShippingAddressRecord().getAddressDetailsRecord().getCityDvo()
						.setCode((String) resultRow.get("shipping_city_code"));
				userDVO.getShippingAddressRecord().getAddressDetailsRecord().getCityDvo()
						.setName((String) resultRow.get("shipping_city_name"));
				userDVO.getShippingAddressRecord().getAddressDetailsRecord().getPinRecord()
						.setCode((String) resultRow.get("shipping_zip_code"));

				userDVO.getShippingAddressRecord().getAddressDetailsRecord()
						.setPhone1((String) resultRow.get("shipping_contact_person_1"));
				userDVO.getShippingAddressRecord().getAddressDetailsRecord()
						.setPhone2((String) resultRow.get("shipping_contact_person_2"));

				userDVO.getShippingAddressRecord().getAddressDetailsRecord()
						.setEmail1((String) resultRow.get("shipping_email1"));
				userDVO.getShippingAddressRecord().getAddressDetailsRecord()
						.setEmail2((String) resultRow.get("shipping_email2"));

				userDVO.getShippingAddressRecord().getAddressDetailsRecord()
						.setContactPerson1((String) resultRow.get("shipping_contact_person_1"));
				userDVO.getShippingAddressRecord().getAddressDetailsRecord()
						.setContactPerson2((String) resultRow.get("shipping_contact_person_2"));
				userDVO.getShippingAddressRecord().getAddressDetailsRecord()
						.setLandmark((String) resultRow.get("shipping_landmark"));

				userDVO.getShippingAddressRecord().getAddressDetailsRecord().getStateDVO()
						.setCode((String) resultRow.get("shipping_state_code"));
				userDVO.getShippingAddressRecord().getAddressDetailsRecord().getStateDVO()
						.setName((String) resultRow.get("shipping_state_name"));

				userDVO.getShippingAddressRecord().getAddressDetailsRecord().getCountryDvo()
						.setCode((String) resultRow.get("shipping_country_code"));
				userDVO.getShippingAddressRecord().getAddressDetailsRecord().getCountryDvo()
						.setName((String) resultRow.get("shipping_country_name"));

				if (resultRow.get("marital_status") != null) {
					userDVO.getMaritalStatus().setParameterID(
							Integer.valueOf(resultRow.get("marital_status").toString()));
				}
				userDVO.getMaritalStatus().setParameterStringValue((String) resultRow.get("marital_status_value"));
				if (resultRow.get("newsletter_subscription") != null) {
					userDVO.setNewsletterSubscription((Boolean) resultRow.get("newsletter_subscription"));
				}

				if (resultRow.get("sms_alert_subscription") != null) {
					userDVO.setSmsAlertSubscription((Boolean) resultRow.get("sms_alert_subscription"));
				}

				if (resultRow.get("is_active") != null) {
					userDVO.setActive((Boolean) resultRow.get("is_active"));
				}

				if (resultRow.get("is_admin") != null) {
					userDVO.setIsAdmin((Boolean) resultRow.get("is_admin"));
				}
				setAuditAttributes(userDVO, resultRow);
				myLog.debug("user login:::" + userDVO.getUserLogin());

				myLog.debug("::login panel bc country code::"
						+ userDVO.getBillingAddressRecord().getAddressDetailsRecord().getCountryDvo().getCode());
			}
		} else {
			myLog.error("LoginPanelBC :: executeLogin failed :: Return Record empty ::::: ");
		}

		loginPanelOpr.setUserDetails(userDVO);

		return loginPanelOpr;
	}

	public LoginPanelOpr executeForgotPassword(OperationalDataValueObject queryParameters) throws FrameworkException,
			BusinessException {
		// BACKING CLASS METHOD TEMPLATE ver 1.0
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		DAOResult daoResult = new DAOResult();
		LoginPanelOpr loginPanelOpr = (LoginPanelOpr) queryParameters;

		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, LoginPanelSqlTemplate.FORGOT_PASSWORD_QUERY);

		Object strSqlParams[][] = new Object[1][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[0][2] = loginPanelOpr.getUserDetails().getUserLogin().trim();

		// strSqlParams[1][0] = "2";
		// strSqlParams[1][1] = IDAOConstant.STRING_DATATYPE;
		// strSqlParams[1][2] =
		// loginPanelOpr.getUserDetails().getPrimaryEmailId().trim();

		// in the following call replace null with dynamic where clause if
		// required
		daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap responseMap = daoResult.getInvocationResult();
		myLog.debug(":: Resultset got ::" + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {
				HashMap resultRow = (HashMap) responseMap.get(i);
				loginPanelOpr.getUserDetails().setLoginPassword((String) resultRow.get("login_password"));
				loginPanelOpr.getUserDetails().setFirstName((String) resultRow.get("first_name"));
				loginPanelOpr.getUserDetails().setPrimaryEmailId((String) resultRow.get("primary_email_id"));
			}
		} else {
			myLog.error("LoginPanelBC :: executeForgotPassword failed :: Return Record empty ::::: ");
			throw new BusinessException("no_data_from_db_excep_msg");
		}

		return loginPanelOpr;
	}

	public LoginPanelOpr executeUpdatePassword(OperationalDataValueObject queryParameters) throws FrameworkException,
			BusinessException {
		// BACKING CLASS METHOD TEMPLATE ver 1.0
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		DAOResult daoResult = new DAOResult();
		LoginPanelOpr loginPanelOpr = (LoginPanelOpr) queryParameters;

		myLog.debug("inside method executeUpdatePassword");
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.UPDATE_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, LoginPanelSqlTemplate.UPDATE_PASSWORD_QUERY);

		Object strSqlParams[][] = new Object[1][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[0][2] = loginPanelOpr.getUserDetails().getUserLogin().trim();

		// strSqlParams[1][0] = "2";
		// strSqlParams[1][1] = IDAOConstant.STRING_DATATYPE;
		// strSqlParams[1][2] =
		// loginPanelOpr.getUserDetails().getPrimaryEmailId().trim();

		// in the following call replace null with dynamic where clause if
		// required
		daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		loginPanelOpr = executeForgotPassword(queryParameters);
		return loginPanelOpr;
	}
}
