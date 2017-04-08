package com.web.bc.systemowner.modules.master.guestusermaster;

import java.util.HashMap;

import com.web.common.constants.CommonConstant;
import com.web.common.dvo.opr.systemowner.GuestUserOpr;
import com.web.common.dvo.retail.modules.GuestUserDVO;
import com.web.common.parents.BackingClass;
import com.web.foundation.dao.DAOResult;
import com.web.foundation.dao.IDAOConstant;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;

public class GuestUserMasterBC extends BackingClass {

	public GuestUserOpr executeSearch(GuestUserOpr guestUserOpr) throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In GuestUserMasterBC executeSearch ");

		GuestUserOpr guestUserOprRet = new GuestUserOpr();

		String name = guestUserOpr.getGuestUserRecord().getCode();
		String email = guestUserOpr.getGuestUserRecord().getEmailId();
		String statusCode = guestUserOpr.getGuestUserRecord().getStatusRecord().getCode();
		String phone = guestUserOpr.getGuestUserRecord().getPhoneNumber();

		myLog.debug(" unitCode " + name);
		myLog.debug(" unitName " + email);
		myLog.debug(" statusCode " + statusCode);
		myLog.debug(" phone " + phone);

		StringBuffer dynamicWhere = new StringBuffer();

		int parameterCount = 0;

		if (name != null && name.trim().length() > 0) {
			name = name.trim().concat("%");
			if (parameterCount > 0) {
				dynamicWhere.append(" AND name LIKE '" + name + "'");
			} else {
				dynamicWhere.append(" name LIKE '" + name + "'");
			}
			parameterCount++;
		}

		if (phone != null && phone.trim().length() > 0) {
			phone = phone.trim().concat("%");
			if (parameterCount > 0) {
				dynamicWhere.append(" AND phone_number LIKE '" + phone + "'");
			} else {
				dynamicWhere.append(" phone_number LIKE '" + phone + "'");
			}
			parameterCount++;
		}

		if (email != null && email.trim().length() > 0) {
			email = email.trim().concat("%");
			if (parameterCount > 0) {
				dynamicWhere.append(" AND email_id LIKE '" + email + "'");
			} else {
				dynamicWhere.append(" email_id LIKE '" + email + "'");
			}
			parameterCount++;
		}

		if (statusCode != null && statusCode.trim().length() > 0) {
			if (statusCode.equalsIgnoreCase(CommonConstant.StatusCodes.ACTIVE)) {
				if (parameterCount > 0) {
					dynamicWhere.append(" AND is_active = 1 ");
				} else {
					dynamicWhere.append(" is_active = 1 ");
				}
			} else if (statusCode.equalsIgnoreCase(CommonConstant.StatusCodes.INACTIVE)) {
				if (parameterCount > 0) {
					dynamicWhere.append(" AND is_active = 0 ");
				} else {
					dynamicWhere.append(" is_active = 0 ");
				}
			}

			parameterCount++;
		}

		if (parameterCount == 0) {
			dynamicWhere.append(" 1 = 1 ");
		}
		dynamicWhere.append(" ORDER BY name ");
		myLog.debug("dynamicWhere ::::: " + dynamicWhere);

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, GuestuserMasterSqlTemplate.SEARCH_GUEST_USER_DETAILS);

		Object strSqlParams[][] = new Object[0][0];

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, dynamicWhere.toString());
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" GuestUserMasterBC executeSearch :: Resultset got ::" + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {

				HashMap<String, Object> resultSetMap = responseMap.get(i);
				handleAndThrowException(resultSetMap);

				GuestUserDVO guestUserRecord = new GuestUserDVO();

				if (resultSetMap.get("guest_id") != null)
					guestUserRecord.setId(Long.valueOf(resultSetMap.get("guest_id").toString()));

				guestUserRecord.setPhoneNumber((String) resultSetMap.get("phone_number"));
				guestUserRecord.setName((String) resultSetMap.get("name"));
				guestUserRecord.setEmailId((String) resultSetMap.get("email_id"));

				if (resultSetMap.get("is_active") != null) {
					guestUserRecord.setActive((Boolean) resultSetMap.get("is_active"));
				} else {
					guestUserRecord.setActive(false);
				}

				setAuditAttributes(guestUserRecord, resultSetMap);

				guestUserOprRet.getGuestUserList().add(guestUserRecord);

			}
		} else {
			throw new BusinessException("no_data_from_db_excep_msg");
		}

		return guestUserOprRet;
	}

	public GuestUserOpr executeSave(GuestUserOpr guestUserOpr) throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In GuestUserMasterBC executeSave ");

		GuestUserDVO guestUserRecord = guestUserOpr.getSelectedUserRecord();

		Long guestUserId = guestUserRecord.getId();
		String name = guestUserRecord.getName();
		String emailId = guestUserRecord.getEmailId();
		String phone = guestUserRecord.getPhoneNumber();

		Boolean active = guestUserRecord.getActive();
		String userLogin = guestUserRecord.getUserLogin();
		String lastModifiedDate = null;
		if (guestUserRecord.getAuditAttributes().getLastModifiedDate() != null)
			lastModifiedDate = guestUserRecord.getAuditAttributes().getLastModifiedDate().toString();

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, GuestuserMasterSqlTemplate.SAVE_GUEST_USER_DETAILS);

		Object strSqlParams[][] = new Object[7][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[0][2] = guestUserId;
		myLog.debug(" parameter 1 propertyValueMappingId:: " + guestUserId);

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[1][2] = name;
		myLog.debug(" parameter 2 name:: " + name);

		strSqlParams[2][0] = "3";
		strSqlParams[2][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[2][2] = emailId;
		myLog.debug(" parameter 3 emailId:: " + emailId);

		strSqlParams[3][0] = "4";
		strSqlParams[3][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[3][2] = phone;
		myLog.debug(" parameter 4 phone:: " + phone);

		strSqlParams[4][0] = "5";
		strSqlParams[4][1] = IDAOConstant.BOOLEAN_DATATYPE;
		strSqlParams[4][2] = active;
		myLog.debug(" parameter 5 active:: " + active);

		strSqlParams[5][0] = "6";
		strSqlParams[5][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[5][2] = userLogin;
		myLog.debug(" parameter 6 userLogin:: " + userLogin);

		strSqlParams[6][0] = "7";
		strSqlParams[6][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[6][2] = lastModifiedDate;
		myLog.debug(" parameter 7 lastModifiedDate:: " + lastModifiedDate);

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug("GuestUserMasterBC executeSave:: Resultset got ::" + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {

				HashMap<String, Object> resultSetMap = responseMap.get(i);
				handleAndThrowException(resultSetMap);

				if (resultSetMap.get("guest_id") != null)
					guestUserOpr.getSelectedUserRecord().setId(Long.valueOf(resultSetMap.get("guest_id").toString()));

				setAuditAttributes(guestUserOpr.getSelectedUserRecord(), resultSetMap);

			}
		}
		return guestUserOpr;
	}

}
