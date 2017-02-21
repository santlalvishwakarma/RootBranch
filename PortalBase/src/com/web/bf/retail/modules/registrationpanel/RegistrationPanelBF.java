package com.web.bf.retail.modules.registrationpanel;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;

import com.web.bc.retail.modules.registrationpanel.RegistrationPanelBC;
import com.web.common.constants.CommonConstant;
import com.web.common.dvo.common.OperationalDataValueObject;
import com.web.common.dvo.opr.retail.RegistrationPanelOpr;
import com.web.common.parents.BusinessFacade;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;
import com.web.foundation.mail.MailParameters;
import com.web.foundation.mail.WebMail;
import com.web.util.PropertiesReader;

public class RegistrationPanelBF extends BusinessFacade {
	private String propertiesLocation = "com/web/bb/retail/module/registrationpanel/registrationpanel";

	public RegistrationPanelOpr executeRegister(RegistrationPanelOpr registrationPanelOpr) throws FrameworkException,
			BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("inside RegistrationPanelBF executeRegister:::::");

		// TEMPLATE TO CALL BC METHOD FROM BF ver 1.0
		// add your BC call here
		RegistrationPanelOpr returnRegistrationPanelOpr = new RegistrationPanelBC()
				.executeRegister(registrationPanelOpr);

		myLog.debug("before calling send email in RegistrationPanelBF ::::");

		// send email
		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
		if ((propertiesReader.getValueOfKey("registration_welcome_mail_enabled") != null)
				&& propertiesReader.getValueOfKey("registration_welcome_mail_enabled").equals("1")) {

			PropertiesReader propertiesReaderCommon = new PropertiesReader(
					CommonConstant.MessageLocation.COMMON_MESSAGES);
			MailParameters mailParameters = new MailParameters();

			InternetAddress[] addressTo = new InternetAddress[1];
			InternetAddress[] addressBCC = new InternetAddress[3];

			InternetAddress ia = new InternetAddress();
			ia.setAddress(returnRegistrationPanelOpr.getUserDetails().getPrimaryEmailId());
			addressTo[0] = ia;

			InternetAddress iaBCC = new InternetAddress();
			iaBCC.setAddress(propertiesReaderCommon.getValueOfKey("system_owner_email_id"));
			addressBCC[0] = iaBCC;

			InternetAddress iaBCC1 = new InternetAddress();
			iaBCC1.setAddress(propertiesReaderCommon.getValueOfKey("system_owner_email_id_1"));
			addressBCC[1] = iaBCC1;

			InternetAddress iaBCC2 = new InternetAddress();
			iaBCC2.setAddress(propertiesReaderCommon.getValueOfKey("system_owner_email_id_2"));
			addressBCC[2] = iaBCC2;

			mailParameters.setMailRecipients(addressTo);
			mailParameters.setMailRecipientsBCC(addressBCC);
			mailParameters.setMailSubject(propertiesReader.getValueOfKey("registration_welcome_mail_subject"));
			myLog.debug("mail subject in RegistrationPanelBF ::::" + mailParameters.getMailSubject());
			String mailMessage = "user id = " + returnRegistrationPanelOpr.getUserDetails().getPrimaryEmailId()
					+ " password = " + returnRegistrationPanelOpr.getUserDetails().getPrimaryPhoneNumber();
			mailParameters.setMailMessage(mailMessage);
			mailParameters.setCustomerKey(propertiesReaderCommon.getValueOfKey("customer_key"));
			mailParameters.setMailFormat(CommonConstant.MimeType.TEXT_HTML);

			try {
				WebMail webMail = new WebMail(mailParameters);
				webMail.sendMultipleMail();
			} catch (MessagingException e) {
				throw new FrameworkException("messaging_exception", e.getCause());
			}
		}

		return returnRegistrationPanelOpr;
	}

	public RegistrationPanelOpr checkUserAvailability(OperationalDataValueObject queryParameters)
			throws FrameworkException, BusinessException {
		RegistrationPanelBC registrationPanelBC = new RegistrationPanelBC();
		RegistrationPanelOpr returnRegistrationPanelOpr = new RegistrationPanelOpr();
		returnRegistrationPanelOpr = registrationPanelBC.checkUserAvailability(queryParameters);
		return returnRegistrationPanelOpr;
	}

	public RegistrationPanelOpr executeRegisterDetails(OperationalDataValueObject queryParameters)
			throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("inside RegistrationPanelBF:::::::::::");
		RegistrationPanelOpr returnRegistrationPanelOpr = (RegistrationPanelOpr) queryParameters;
		myLog.debug("check for application flagmap111:::::::"
				+ returnRegistrationPanelOpr.getApplicationFlags().getApplicationFlagMap()
						.get(CommonConstant.WEBSITE_URL));

		// TEMPLATE TO CALL BC METHOD FROM BF ver 1.0
		myLog.debug("before RegistrationPanelBC:::::::::::");
		returnRegistrationPanelOpr = new RegistrationPanelBC().executeRegisterDetails(queryParameters);
		myLog.debug("check for application flagmap222:::::::"
				+ returnRegistrationPanelOpr.getApplicationFlags().getApplicationFlagMap()
						.get(CommonConstant.WEBSITE_URL));
		// set roles data
		// queryRoleIntegrationOpr.getUserRecord().setId(returnRegistrationPanelOpr.getUserDetails().getId());
		// resultRoleIntegrationOpr = new
		// RoleIntegration().getRolesForUser(queryRoleIntegrationOpr);
		// String resultRoleList = "";
		// ArrayList<UserRoleMappingDVO> roleList =
		// resultRoleIntegrationOpr.getUserRecord().getUserRolesMappingList();
		// for (int i = 0; i < roleList.size(); i++) {
		// if (resultRoleList == "") {
		// resultRoleList = roleList.get(i).getRoleRecord().getCode();
		// } else {
		// resultRoleList = resultRoleList +
		// CommonConstant.ROLE_CODE_SEPARATOR
		// + roleList.get(i).getRoleRecord().getCode();
		// }
		// }
		// returnRegistrationPanelOpr.getApplicationFlags().getApplicationFlagMap()
		// .put(CommonConstant.LOGGED_USER_ROLES, resultRoleList);

		// send email
		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
		if ((propertiesReader.getValueOfKey("registration_welcome_mail_enabled") != null)
				&& propertiesReader.getValueOfKey("registration_welcome_mail_enabled").equals("1")) {
			RegistrationPanelOpr registrationPanelOpr = (RegistrationPanelOpr) queryParameters;
			if (registrationPanelOpr.getUserDetails().getPrimaryEmailId() == null) {
				registrationPanelOpr.getUserDetails().setPrimaryEmailId(
						registrationPanelOpr.getUserDetails().getPermanentAddressRecord().getEmail1());
			}

			if (registrationPanelOpr.getUserDetails().getFirstName() == null) {
				registrationPanelOpr.getUserDetails().setFirstName(
						registrationPanelOpr.getUserDetails().getPermanentAddressRecord().getFirstName());
			}
			if (registrationPanelOpr.getNewPassword() == null) {
				registrationPanelOpr.setNewPassword(registrationPanelOpr.getUserDetails().getPermanentAddressRecord()
						.getLastName());
			}
			myLog.debug("check for application flagmap333:::::::"
					+ registrationPanelOpr.getApplicationFlags().getApplicationFlagMap()
							.get(CommonConstant.WEBSITE_URL));

			PropertiesReader propertiesReaderCommon = new PropertiesReader(
					CommonConstant.MessageLocation.COMMON_MESSAGES);
			MailParameters mailParameters = new MailParameters();

			InternetAddress[] addressTo = new InternetAddress[1];
			InternetAddress[] addressBCC = new InternetAddress[1];
			myLog.debug("to check whether set it or not:::::::"
					+ registrationPanelOpr.getUserDetails().getPrimaryEmailId() + "and"
					+ registrationPanelOpr.getUserDetails().getFirstName() + "And"
					+ registrationPanelOpr.getUserDetails().getUserLogin() + "AND"
					+ registrationPanelOpr.getNewPassword());

			InternetAddress ia = new InternetAddress();
			ia.setAddress(registrationPanelOpr.getUserDetails().getPrimaryEmailId());
			addressTo[0] = ia;
			InternetAddress iaBCC = new InternetAddress();
			iaBCC.setAddress(propertiesReaderCommon.getValueOfKey("system_owner_email_id"));
			addressBCC[0] = iaBCC;

			mailParameters.setMailRecipients(addressTo);
			mailParameters.setMailRecipientsBCC(addressBCC);
			mailParameters.setMailSubject(propertiesReader.getValueOfKey("registration_welcome_mail_subject"));
			mailParameters.setMailDVOObject(registrationPanelOpr);
			mailParameters.setRoutingKey(propertiesReaderCommon
					.getValueOfKey("rabbitmq_email_routing_key_registration"));
			mailParameters.setMessageQueue(propertiesReaderCommon
					.getValueOfKey("rabbitmq_email_message_queue_registration"));
			mailParameters.setCustomerKey(propertiesReaderCommon.getValueOfKey("customer_key"));
			mailParameters.setMailFormat(CommonConstant.MimeType.TEXT_HTML);

			try {
				WebMail webMail = new WebMail(mailParameters);
				myLog.debug("to check for mail :::: in registration::::");
				webMail.sendMultipleMail();
			} catch (MessagingException e) {
				throw new FrameworkException("messaging_exception", e.getCause());
			}
		}

		return returnRegistrationPanelOpr;
	}

	// public RegistrationPanelOpr saveSmsGateWayResponse(RegistrationPanelOpr
	// registrationPanelOpr)
	// throws FrameworkException, BusinessException {
	// return new
	// RegistrationPanelBC().saveSmsGateWayResponse(registrationPanelOpr);
	//
	// }
}
