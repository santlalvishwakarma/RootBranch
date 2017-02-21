package com.web.bf.retail.modules.loginpanel;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;

import com.web.bc.retail.modules.loginpanel.LoginPanelBC;
import com.web.common.constants.CommonConstant;
import com.web.common.dvo.common.OperationalDataValueObject;
import com.web.common.dvo.opr.retail.LoginPanelOpr;
import com.web.common.parents.BusinessFacade;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;
import com.web.foundation.mail.MailParameters;
import com.web.foundation.mail.WebMail;
import com.web.util.PropertiesReader;

public class LoginPanelBF extends BusinessFacade {
	private String propertiesLocation = "/com/web/bb/retail/module/loginpanel/loginpanel";

	public LoginPanelOpr executeLogin(LoginPanelOpr loginPanelOpr) throws FrameworkException, BusinessException {
		LoginPanelBC loginPanelBC = new LoginPanelBC();
		LoginPanelOpr returnLoginPanelOpr = loginPanelBC.executeLogin(loginPanelOpr);

		// RoleIntegrationOpr queryRoleIntegrationOpr = new
		// RoleIntegrationOpr();
		// RoleIntegrationOpr resultRoleIntegrationOpr = new
		// RoleIntegrationOpr();
		// queryRoleIntegrationOpr.getUserRecord().setId(returnLoginPanelOpr.getUserDetails().getId());
		//
		// // / call integration layer method to get user roles from data owner
		// // component
		// resultRoleIntegrationOpr = new
		// RoleIntegration().getRolesForUser(queryRoleIntegrationOpr);
		// String resultRoleList = "";
		// ArrayList<UserRoleMappingDVO> roleList =
		// resultRoleIntegrationOpr.getUserRecord().getUserRolesMappingList();
		// for (int i = 0; i < roleList.size(); i++) {
		// if (resultRoleList == "") {
		// resultRoleList = roleList.get(i).getRoleRecord().getCode();
		// } else {
		// resultRoleList = resultRoleList + CommonConstant.ROLE_CODE_SEPARATOR
		// + roleList.get(i).getRoleRecord().getCode();
		// }
		// }
		// FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
		// .put(CommonConstant.LOGGED_USER_ROLES, resultRoleList);

		return returnLoginPanelOpr;
	}

	public LoginPanelOpr executeForgotPassword(OperationalDataValueObject queryParameters) throws FrameworkException,
			BusinessException {
		// TEMPLATE TO CALL BC METHOD FROM BF ver 1.0
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		LoginPanelBC loginPanelBC = new LoginPanelBC();
		LoginPanelOpr returnLoginPanelOpr = new LoginPanelOpr();
		try {
			// returnLoginPanelOpr =
			// loginPanelBC.executeForgotPassword(queryParameters);
			returnLoginPanelOpr = loginPanelBC.executeUpdatePassword(queryParameters);
		} catch (FrameworkException e) {
			throw e;
		} catch (BusinessException e) {
			throw e;
		}
		// send email
		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
		if ((propertiesReader.getValueOfKey("forgot_password_intimation_mechanism") != null)
				&& propertiesReader.getValueOfKey("forgot_password_intimation_mechanism").contains("mail")) {
			PropertiesReader propertiesReaderCommon = new PropertiesReader(
					CommonConstant.MessageLocation.COMMON_MESSAGES);
			MailParameters mailParameters = new MailParameters();

			InternetAddress[] addressTo = new InternetAddress[1];
			InternetAddress[] addressBCC = new InternetAddress[1];

			myLog.debug("check for email id" + returnLoginPanelOpr.getUserDetails().getPrimaryEmailId());

			InternetAddress ia = new InternetAddress();
			ia.setAddress(returnLoginPanelOpr.getUserDetails().getPrimaryEmailId());
			addressTo[0] = ia;
			InternetAddress iaBCC = new InternetAddress();
			iaBCC.setAddress(propertiesReaderCommon.getValueOfKey("system_owner_email_id"));
			addressBCC[0] = iaBCC;

			mailParameters.setMailRecipients(addressTo);
			mailParameters.setMailRecipientsBCC(addressBCC);
			mailParameters.setMailSubject(propertiesReader.getValueOfKey("forgot_password_mail_subject"));

			mailParameters.setMailDVOObject(returnLoginPanelOpr);

			mailParameters.setRoutingKey(propertiesReaderCommon
					.getValueOfKey("rabbitmq_email_routing_key_forgot_password"));
			mailParameters.setMessageQueue(propertiesReaderCommon
					.getValueOfKey("rabbitmq_email_message_queue_forgot_password"));

			mailParameters.setCustomerKey(propertiesReaderCommon.getValueOfKey("customer_key"));
			mailParameters.setMailFormat(CommonConstant.MimeType.TEXT_HTML);

			try {
				WebMail portalMail = new WebMail(mailParameters);
				portalMail.sendMultipleMail();
			} catch (MessagingException e) {
				throw new FrameworkException("messaging_exception", e.getCause());
			}
		}
		return returnLoginPanelOpr;
	}
}
