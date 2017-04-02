package com.web.bf.retail.modules.shoppingcart;

import java.util.ArrayList;
import java.util.HashMap;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.transaction.UserTransaction;

import com.web.bc.common.OptionsHelperBC;
import com.web.bc.retail.modules.shoppingcart.ShoppingCartBC;
import com.web.bf.retail.modules.registrationpanel.RegistrationPanelBF;
import com.web.common.constants.CommonConstant;
import com.web.common.dvo.common.CountryDVO;
import com.web.common.dvo.common.OperationalDataValueObject;
import com.web.common.dvo.common.StateDVO;
import com.web.common.dvo.opr.retail.RegistrationPanelOpr;
import com.web.common.dvo.opr.retail.ShoppingCartOpr;
import com.web.common.dvo.retail.modules.ShoppingCartProductDVO;
import com.web.common.parents.BusinessFacade;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;
import com.web.foundation.mail.MailParameters;
import com.web.foundation.mail.WebMail;
import com.web.util.MessageFormatter;
import com.web.util.PropertiesReader;

public class ShoppingCartBF extends BusinessFacade {

	private String propertiesLocation = getClass().getPackage().getName().replaceAll("\\.", "/") + "/shoppingcart";

	public HashMap<String, ArrayList<Object>> getAllOptionsValues() throws FrameworkException, BusinessException {
		HashMap<String, ArrayList<Object>> allOptionsValues = new HashMap<String, ArrayList<Object>>();
		OptionsHelperBC optionsHelperBC = new OptionsHelperBC();
		allOptionsValues.put("yesNoList", optionsHelperBC.getYesNoList());

		allOptionsValues.put("countryList", optionsHelperBC.getCountryList(new CountryDVO()));

		return allOptionsValues;
	}

	public ArrayList<Object> getStateList(CountryDVO contryDvo) throws FrameworkException, BusinessException {
		return new OptionsHelperBC().getStateList(contryDvo);
	}

	public ArrayList<Object> getCityList(StateDVO stateDVO) throws FrameworkException, BusinessException {
		return new OptionsHelperBC().getCityList(stateDVO);
	}

	public void saveProductForLater(ShoppingCartOpr shoppingCartSaveOpr) throws FrameworkException, BusinessException {
		// TEMPLATE TO CALL BC METHOD FROM BF ver 1.0
		try {
			// add your BC call here

			String userLogin = shoppingCartSaveOpr.getRetailUserDetails().getUserLogin();

			WishlistIntegrationOpr wishlistIntegrationOpr = new WishlistIntegrationOpr();

			wishlistIntegrationOpr.getWishlistRecord().getUserRecord().setUserLogin(userLogin);
			if (shoppingCartSaveOpr.getShoppingCartProductRecord() != null) {
				WishlistDVO wishlistDVO = new WishlistDVO();
				wishlistDVO.setProductRecord(shoppingCartSaveOpr.getShoppingCartProductRecord().getProductRecord());
				wishlistIntegrationOpr.getWishlistRecordList().add(wishlistDVO);
			}

			wishlistIntegrationOpr = new WishlistIntegration().saveWishlistProduct(wishlistIntegrationOpr);

			// new ShoppingCartBC().saveProductForLater(shoppingCartSaveOpr);
		} catch (FrameworkException e) {
			throw e;
		}

	}

	public ShoppingCartOpr confirmOrder(ShoppingCartOpr shoppingCartOpr) throws FrameworkException, BusinessException {
		return new ShoppingCartBC().confirmOrder(shoppingCartOpr);
	}

	public ShoppingCartOpr populateOtherCharges(ShoppingCartOpr shoppingCartOpr) throws FrameworkException,
			BusinessException {
		ShoppingCartOpr returnShoppingCartOpr = new ShoppingCartOpr();

		DeliveryChargesIntegrationOpr deliveryChargesIntegrationOpr = new DeliveryChargesIntegrationOpr();
		deliveryChargesIntegrationOpr.getDeliveryChargesRecord().setCountryRecord(
				shoppingCartOpr.getRetailOrderRecord().getShippingDetails().getCountry());
		DeliveryChargesIntegrationOpr returnDeliveryChargesIntegrationOpr = new DeliveryChargesIntegration()
				.defaultCharges(deliveryChargesIntegrationOpr);
		returnShoppingCartOpr.getRetailOrderRecord().getDeliveryChargesRecord()
				.setDeliveryCharge(returnDeliveryChargesIntegrationOpr.getDeliveryChargesRecord().getDeliveryCharge());

		DeliveryTimeIntegrationOpr deliveryTimeIntegrationOpr = new DeliveryTimeIntegrationOpr();
		deliveryTimeIntegrationOpr.getDeliveryTimeRecord().setCountryRecord(
				shoppingCartOpr.getRetailOrderRecord().getShippingDetails().getCountry());
		DeliveryTimeIntegrationOpr returnDeliveryTimeIntegrationOpr = new DeliveryTimeIntegration()
				.defaultTime(deliveryTimeIntegrationOpr);
		returnShoppingCartOpr.getRetailOrderRecord().getDeliveryTimeRecord()
				.setLeadTimeInDays(returnDeliveryTimeIntegrationOpr.getDeliveryTimeRecord().getLeadTimeInDays());
		returnShoppingCartOpr
				.getRetailOrderRecord()
				.getDeliveryTimeRecord()
				.setLeadTimeInDaysStockUnavailable(
						returnDeliveryTimeIntegrationOpr.getDeliveryTimeRecord().getLeadTimeInDaysStockUnavailable());

		return returnShoppingCartOpr;
	}

	public ShoppingCartOpr getRetailUserDetails(String userLogin) throws FrameworkException, BusinessException {
		return new ShoppingCartBC().getRetailUserDetails(userLogin);
	}

	public ShoppingCartOpr recalculate(ShoppingCartOpr shoppingCartOpr) throws FrameworkException, BusinessException {
		return new ShoppingCartBC().recalculate(shoppingCartOpr);
	}

	public ShoppingCartOpr saveInitialPaymentTransaction(ShoppingCartOpr shoppingCartOpr) throws FrameworkException {
		return new ShoppingCartBC().saveInitialPaymentTransaction(shoppingCartOpr);
	}

	public void updatePaymentGatewaytransaction(ShoppingCartOpr shoppingCartOpr) throws FrameworkException {
		new ShoppingCartBC().updatePaymentGatewaytransaction(shoppingCartOpr);
	}

	public ShoppingCartOpr getPaymentGatewayDetailsForId(OperationalDataValueObject searchParameters)
			throws FrameworkException, BusinessException {
		return new ShoppingCartBC().getPaymentGatewayDetailsForId(searchParameters);
	}

	public ShoppingCartOpr executeRegister(ShoppingCartOpr shoppingcartOpr) throws FrameworkException,
			BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("check for application flagmap:::::::"
				+ shoppingcartOpr.getApplicationFlags().getApplicationFlagMap().get(CommonConstant.WEBSITE_URL));

		RegistrationPanelOpr registrationPanelOpr = new RegistrationPanelOpr();

		// set registration data
		myLog.debug("bfore calling integration:::::");
		registrationPanelOpr.getUserDetails().setFirstName(
				shoppingcartOpr.getRetailOrderRecord().getBillingDetails().getFirstName());
		registrationPanelOpr.getUserDetails().setLastName(
				shoppingcartOpr.getRetailOrderRecord().getBillingDetails().getLastName());
		registrationPanelOpr.getUserDetails().setPrimaryEmailId(
				shoppingcartOpr.getRetailOrderRecord().getBillingDetails().getEmail1());
		registrationPanelOpr.getUserDetails().setPrimaryPhoneNumber(
				shoppingcartOpr.getRetailOrderRecord().getBillingDetails().getPhone1());
		registrationPanelOpr.setNewPassword(shoppingcartOpr.getRetailOrderRecord().getBillingDetails().getPhone1());
		registrationPanelOpr.getUserDetails().setUserLogin(shoppingcartOpr.getRetailUserDetails().getUserLogin());
		registrationPanelOpr.getUserDetails().setConditionAccepted(
				shoppingcartOpr.getRetailUserDetails().isConditionAccepted());
		registrationPanelOpr.getApplicationFlags().setApplicationFlagMap(
				shoppingcartOpr.getApplicationFlags().getApplicationFlagMap());

		RegistrationPanelOpr registrationPanelOprRet = new RegistrationPanelBF().executeRegister(registrationPanelOpr);

		// roleIntegrationOpr.getUserRecord().setId(registrationPanelOprRet.getRetailUserDetails().getId());
		// roleIntegrationOpr = new
		// RoleIntegration().getRolesForUser(roleIntegrationOpr);
		// String resultRoleList = "";
		// ArrayList<UserRoleMappingDVO> roleList =
		// roleIntegrationOpr.getUserRecord().getUserRolesMappingList();
		// myLog.debug("check rolelist size for " + roleList.size());
		// for (int i = 0; i < roleList.size(); i++) {
		// if (resultRoleList == "") {
		// resultRoleList = roleList.get(i).getRoleRecord().getCode();
		// } else {
		// resultRoleList = resultRoleList +
		// CommonConstant.ROLE_CODE_SEPARATOR
		// + roleList.get(i).getRoleRecord().getCode();
		// }
		// }
		// returnRegistrationOpr.getApplicationFlags().getApplicationFlagMap()
		// .put(CommonConstant.LOGGED_USER_ROLES, resultRoleList);

		return shoppingcartOpr;
	}

	public ShoppingCartOpr checkUserAvailability(OperationalDataValueObject queryParameters) throws FrameworkException,
			BusinessException {
		ShoppingCartBC shoppingCartBC = new ShoppingCartBC();
		ShoppingCartOpr returnshShoppingCartOpr = new ShoppingCartOpr();
		returnshShoppingCartOpr = shoppingCartBC.checkUserAvailability(queryParameters);
		return returnshShoppingCartOpr;
	}

	public ShoppingCartOpr getInvoiceDetails(OperationalDataValueObject searchParameters) throws FrameworkException,
			BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
		PropertiesReader propertiesReaderCommon = new PropertiesReader(CommonConstant.MessageLocation.COMMON_MESSAGES);
		ShoppingCartBC shoppingCartBC = new ShoppingCartBC();
		ShoppingCartOpr shoppingCartOprGot = (ShoppingCartOpr) searchParameters;
		ShoppingCartOpr shoppingCartOpr = new ShoppingCartOpr();
		// to be removed
		// shoppingCartOpr =
		// shoppingCartBC.getPaymentRecordOnReceivedMerchantParameter(searchParameters);
		myLog.debug("received order id inside get invoice detail s:::::::::::"
				+ shoppingCartOprGot.getPaymentDVO().getReceivedOrderId());

		shoppingCartOpr = shoppingCartBC.getOrderDetails(shoppingCartOprGot);
		shoppingCartOpr = shoppingCartBC.getOrderHeaderDetails(shoppingCartOpr);
		shoppingCartOpr = shoppingCartBC.getCustumizationRecord(shoppingCartOpr);

		// copy website url
		if (shoppingCartOprGot.getApplicationFlags().getApplicationFlagMap().containsKey(CommonConstant.WEBSITE_URL)
				&& shoppingCartOprGot.getApplicationFlags().getApplicationFlagMap().get(CommonConstant.WEBSITE_URL) != null) {
			shoppingCartOpr
					.getApplicationFlags()
					.getApplicationFlagMap()
					.put(CommonConstant.WEBSITE_URL,
							shoppingCartOprGot.getApplicationFlags().getApplicationFlagMap()
									.get(CommonConstant.WEBSITE_URL));
		}
		myLog.debug("in shopping cart bf domain passed :::: "
				+ shoppingCartOpr.getApplicationFlags().getApplicationFlagMap().get(CommonConstant.WEBSITE_URL));

		if ((propertiesReader.getValueOfKey("order_receipt_mail_enabled") != null)
				&& propertiesReader.getValueOfKey("order_receipt_mail_enabled").equals("1")) {
			MailParameters mailParameters = new MailParameters();

			InternetAddress[] addressTo = new InternetAddress[1];
			InternetAddress[] addressCC = new InternetAddress[1];
			InternetAddress[] addressBCC = new InternetAddress[3];

			InternetAddress ia = new InternetAddress();
			myLog.debug("check for email:::" + shoppingCartOpr.getPaymentDVO().getReceivedBillingAddress().getEmail1());
			ia.setAddress(shoppingCartOpr.getPaymentDVO().getReceivedBillingAddress().getEmail1());
			addressTo[0] = ia;

			InternetAddress iaBCC1 = new InternetAddress();
			iaBCC1.setAddress(propertiesReaderCommon.getValueOfKey("system_owner_email_id"));
			addressBCC[0] = iaBCC1;

			InternetAddress iaBCC2 = new InternetAddress();
			iaBCC2.setAddress(propertiesReaderCommon.getValueOfKey("system_owner_email_id_2"));
			addressBCC[1] = iaBCC2;

			InternetAddress iaBCC3 = new InternetAddress();
			iaBCC3.setAddress(propertiesReaderCommon.getValueOfKey("system_owner_email_id_5"));
			addressBCC[2] = iaBCC3;

			// InternetAddress iaCC = new InternetAddress();
			// iaCC.setAddress(propertiesReaderCommon.getValueOfKey("system_owner_email_id_2"));
			InternetAddress iaCC1 = new InternetAddress();
			iaCC1.setAddress(propertiesReaderCommon.getValueOfKey("system_owner_email_id_6"));
			// addressCC[0] = iaCC;
			addressCC[0] = iaCC1;

			mailParameters.setMailRecipients(addressTo);
			mailParameters.setMailRecipientsCC(addressCC);
			mailParameters.setMailRecipientsBCC(addressBCC);
			// mailParameters.setMailSubject(propertiesReader.getValueOfKey("order_receipt_mail_subject"));
			mailParameters.setMailSubject(MessageFormatter.getFormattedMessage(propertiesReader
					.getValueOfKey("order_receipt_mail_subject"), new String[] { shoppingCartOpr.getPaymentDVO()
					.getSentOrderId() }));
			mailParameters.setRoutingKey(propertiesReaderCommon
					.getValueOfKey("rabbitmq_email_routing_key_order_management_invoice"));
			mailParameters.setMessageQueue(propertiesReaderCommon
					.getValueOfKey("rabbitmq_email_message_queue_order_management_invoice"));
			// mailParameters.setMailMessage(MessageFormatter.getFormattedMessage(propertiesReader
			// .getValueOfKey("order_receipt_mail_body"), new String[] {
			// shoppingCartOpr.getRetailOrderRecord().getBillingDetails().getFirstName(),
			// shoppingCartOpr.getRetailOrderRecord().getOrderNumber() }));
			mailParameters.setMailDVOObject(shoppingCartOpr);

			mailParameters.setCustomerKey(propertiesReader.getValueOfKey("customer_key"));
			mailParameters.setMailFormat(CommonConstant.MimeType.TEXT_HTML);

			try {
				WebMail portalMail = new WebMail(mailParameters);

				portalMail.sendMultipleMail();
				myLog.debug("after sending successfully the mail:::::");
			} catch (MessagingException e) {
				throw new FrameworkException("messaging_exception", e.getCause());
			}
		}

		return shoppingCartOpr;
	}

	public ShoppingCartOpr getReceivedOrderIdOnPaymentTransactionId(ShoppingCartOpr shoppingCartOpr)
			throws FrameworkException, BusinessException {
		return new ShoppingCartBC().getPaymentRecordOnReceivedMerchantParameter(shoppingCartOpr);
	}

	// METHOD FOR ICICI PG
	public ShoppingCartOpr getReceivedOrderIdOnPaymentTransactionIdForICICI(ShoppingCartOpr shoppingCartOpr)
			throws FrameworkException, BusinessException {
		return new ShoppingCartBC().getPaymentRecordOnReceivedMerchantParameterForICICI(shoppingCartOpr);
	}

	public ShoppingCartOpr updateOrderStatusConfirmed(ShoppingCartOpr shoppingCartOpr) throws FrameworkException {
		ShoppingCartOpr returnShoppingCartOpr = new ShoppingCartOpr();
		try {
			returnShoppingCartOpr = new ShoppingCartBC().updateOrderStatusConfirmed(shoppingCartOpr);
		} catch (FrameworkException e) {
			throw e;
		}

		return returnShoppingCartOpr;
	}

	public ShoppingCartOpr getInvoiceDetailsForOrderManagement(ShoppingCartOpr ShoppingCartOpr)
			throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
		PropertiesReader propertiesReaderCommon = new PropertiesReader(CommonConstant.MessageLocation.COMMON_MESSAGES);
		ShoppingCartBC shoppingCartBC = new ShoppingCartBC();
		ShoppingCartOpr shoppingCartOprRet = new ShoppingCartOpr();

		myLog.debug("received order id :::::" + ShoppingCartOpr.getRetailOrderRecord().getId());
		// to be removed
		// shoppingCartOpr =
		// shoppingCartBC.getPaymentRecordOnReceivedMerchantParameter(searchParameters);
		if (ShoppingCartOpr.getRetailOrderRecord().getId() != null) {
			ShoppingCartOpr.getPaymentDVO().setReceivedOrderId(
					ShoppingCartOpr.getRetailOrderRecord().getId().toString());
		}
		myLog.debug("received order id inside get invoice detail s:::::::::::"
				+ ShoppingCartOpr.getPaymentDVO().getReceivedOrderId());

		shoppingCartOprRet = shoppingCartBC.getOrderDetails(ShoppingCartOpr);
		shoppingCartOprRet = shoppingCartBC.getOrderHeaderDetails(shoppingCartOprRet);
		shoppingCartOprRet = shoppingCartBC.getCustumizationRecord(shoppingCartOprRet);

		return shoppingCartOprRet;
	}

	public ShoppingCartOpr getCategoryWiseDeliveryTime(ShoppingCartOpr shoppingCartOpr) throws FrameworkException,
			BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		ShoppingCartOpr shoppingCartOprRet = new ShoppingCartOpr();
		myLog.debug("Inside shopping cart BF getCategoryWiseDeliveryTime");

		ProductIntegrationOpr productIntegrationOprSend = new ProductIntegrationOpr();
		ProductIntegrationOpr productIntegrationOprRet = new ProductIntegrationOpr();

		for (int i = 0; i < shoppingCartOpr.getShoppingCartProductList().size(); i++) {
			productIntegrationOprSend.getProductList().add(
					shoppingCartOpr.getShoppingCartProductList().get(i).getProductRecord());
		}

		productIntegrationOprRet = new ProductIntegration()
				.getWishListProductDetailsForShoppingCart(productIntegrationOprSend);

		if (productIntegrationOprRet != null && productIntegrationOprRet.getProductList() != null
				&& !productIntegrationOprRet.getProductList().isEmpty()) {
			ShoppingCartProductDVO shoppingCartProductDvo = new ShoppingCartProductDVO();
			for (int i = 0; i < productIntegrationOprRet.getProductList().size(); i++) {
				shoppingCartProductDvo.setProductRecord(productIntegrationOprRet.getProductList().get(i));
				shoppingCartOprRet.getShoppingCartProductList().add(shoppingCartProductDvo);
				myLog.debug("Inside Shopping cart BF getCategoryWiseDeliveryTime 1::"
						+ shoppingCartOprRet.getShoppingCartProductList().get(i).getProductSkuRecord()
								.getHierarchyLevelOne().getCategoryRecord().getDeliveryTime());
				myLog.debug("Inside Shopping cart BF getCategoryWiseDeliveryTime 2::"
						+ shoppingCartOprRet.getShoppingCartProductList().get(i).getProductRecord()
								.getHierarchyLevelTwo().getCategoryRecord().getDeliveryTime());
				myLog.debug("Inside Shopping cart BF getCategoryWiseDeliveryTime 3::"
						+ shoppingCartOprRet.getShoppingCartProductList().get(i).getProductRecord()
								.getHierarchyLevelThree().getCategoryRecord().getDeliveryTime());
				myLog.debug("Inside Shopping cart BF getCategoryWiseDeliveryTime 4::"
						+ shoppingCartOprRet.getShoppingCartProductList().get(i).getProductRecord()
								.getHierarchyLevelFour().getCategoryRecord().getDeliveryTime());
			}
		}

		return shoppingCartOprRet;
	}

	public ShoppingCartOpr generateOffLineInvoice(ShoppingCartOpr ShoppingCartOpr) throws FrameworkException,
			BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
		PropertiesReader propertiesReaderCommon = new PropertiesReader(CommonConstant.MessageLocation.COMMON_MESSAGES);
		ShoppingCartBC shoppingCartBC = new ShoppingCartBC();
		ShoppingCartOpr shoppingCartOprRet = new ShoppingCartOpr();

		myLog.debug("received order id :::::" + ShoppingCartOpr.getRetailOrderRecord().getId());
		// to be removed
		// shoppingCartOpr =
		// shoppingCartBC.getPaymentRecordOnReceivedMerchantParameter(searchParameters);
		if (ShoppingCartOpr.getRetailOrderRecord().getId() != null) {
			ShoppingCartOpr.getPaymentDVO().setReceivedOrderId(
					ShoppingCartOpr.getRetailOrderRecord().getId().toString());
		}
		myLog.debug("received order id inside get invoice detail s:::::::::::"
				+ ShoppingCartOpr.getPaymentDVO().getReceivedOrderId());

		shoppingCartOprRet = shoppingCartBC.getOrderDetails(ShoppingCartOpr);
		shoppingCartOprRet = shoppingCartBC.getOrderHeaderDetails(shoppingCartOprRet);
		shoppingCartOprRet = shoppingCartBC.getCustumizationRecord(shoppingCartOprRet);

		if ((propertiesReader.getValueOfKey("order_receipt_mail_enabled") != null)
				&& propertiesReader.getValueOfKey("order_receipt_mail_enabled").equals("1")) {
			MailParameters mailParameters = new MailParameters();

			InternetAddress[] addressTo = new InternetAddress[1];
			// InternetAddress[] addressCC = new InternetAddress[1];
			InternetAddress[] addressBCC = new InternetAddress[1];

			InternetAddress ia = new InternetAddress();
			myLog.debug("check for email:::"
					+ shoppingCartOprRet.getPaymentDVO().getReceivedBillingAddress().getEmail1());
			ia.setAddress(shoppingCartOprRet.getPaymentDVO().getReceivedBillingAddress().getEmail1());
			addressTo[0] = ia;

			InternetAddress iaBCC1 = new InternetAddress();
			iaBCC1.setAddress(propertiesReaderCommon.getValueOfKey("system_owner_email_id"));
			addressBCC[0] = iaBCC1;

			// InternetAddress iaBCC2 = new InternetAddress();
			// iaBCC2.setAddress(propertiesReaderCommon.getValueOfKey("system_owner_email_id_2"));
			// addressBCC[1] = iaBCC2;

			mailParameters.setMailRecipients(addressTo);
			// mailParameters.setMailRecipientsCC(addressCC);
			mailParameters.setMailRecipientsBCC(addressBCC);
			mailParameters.setMailSubject(propertiesReader.getValueOfKey("order_receipt_mail_subject"));
			mailParameters.setRoutingKey(propertiesReaderCommon
					.getValueOfKey("rabbitmq_email_routing_key_order_management_invoice"));
			mailParameters.setMessageQueue(propertiesReaderCommon
					.getValueOfKey("rabbitmq_email_message_queue_order_management_invoice"));
			// mailParameters.setMailMessage(MessageFormatter.getFormattedMessage(propertiesReader
			// .getValueOfKey("order_receipt_mail_body"), new String[] {
			// shoppingCartOpr.getRetailOrderRecord().getBillingDetails().getFirstName(),
			// shoppingCartOpr.getRetailOrderRecord().getOrderNumber() }));
			mailParameters.setMailDVOObject(shoppingCartOprRet);

			mailParameters.setCustomerKey(propertiesReader.getValueOfKey("customer_key"));
			mailParameters.setMailFormat(CommonConstant.MimeType.TEXT_HTML);

			try {
				WebMail portalMail = new WebMail(mailParameters);

				portalMail.sendMultipleMail();
				myLog.debug("after sending successfully the mail:::::");
			} catch (MessagingException e) {
				throw new FrameworkException("messaging_exception", e.getCause());
			}
		}

		return shoppingCartOprRet;
	}

	public ShoppingCartOpr populateExpressCharges(ShoppingCartOpr shoppingCartOpr) throws FrameworkException,
			BusinessException {
		ShoppingCartOpr returnShoppingCartOpr = new ShoppingCartOpr();

		DeliveryChargesIntegrationOpr deliveryChargesIntegrationOpr = new DeliveryChargesIntegrationOpr();
		deliveryChargesIntegrationOpr.getDeliveryChargesRecord().setCountryRecord(
				shoppingCartOpr.getRetailOrderRecord().getShippingDetails().getCountry());
		DeliveryChargesIntegrationOpr returnDeliveryChargesIntegrationOpr = new DeliveryChargesIntegration()
				.expressCharges(deliveryChargesIntegrationOpr);
		returnShoppingCartOpr.getRetailOrderRecord().getDeliveryChargesRecord()
				.setExpressCharge(returnDeliveryChargesIntegrationOpr.getDeliveryChargesRecord().getExpressCharge());

		return returnShoppingCartOpr;
	}

	// added by ketan
	public ShoppingCartOpr getInvoiceDetailsForPayPal(OperationalDataValueObject searchParameters)
			throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
		PropertiesReader propertiesReaderCommon = new PropertiesReader(CommonConstant.MessageLocation.COMMON_MESSAGES);
		ShoppingCartBC shoppingCartBC = new ShoppingCartBC();
		ShoppingCartOpr shoppingCartOprGot = (ShoppingCartOpr) searchParameters;
		ShoppingCartOpr shoppingCartOpr = new ShoppingCartOpr();
		// to be removed
		// shoppingCartOpr =
		// shoppingCartBC.getPaymentRecordOnReceivedMerchantParameter(searchParameters);
		myLog.debug("received order id inside get invoice detail s:::::::::::"
				+ shoppingCartOprGot.getPaymentDVO().getReceivedOrderId());

		shoppingCartOpr = shoppingCartBC.getOrderDetailsForPayPal(shoppingCartOprGot);
		shoppingCartOpr = shoppingCartBC.getOrderHeaderDetailsForPayPal(shoppingCartOpr);
		shoppingCartOpr = shoppingCartBC.getCustumizationRecord(shoppingCartOpr);

		// copy website url
		if (shoppingCartOprGot.getApplicationFlags().getApplicationFlagMap().containsKey(CommonConstant.WEBSITE_URL)
				&& shoppingCartOprGot.getApplicationFlags().getApplicationFlagMap().get(CommonConstant.WEBSITE_URL) != null) {
			shoppingCartOpr
					.getApplicationFlags()
					.getApplicationFlagMap()
					.put(CommonConstant.WEBSITE_URL,
							shoppingCartOprGot.getApplicationFlags().getApplicationFlagMap()
									.get(CommonConstant.WEBSITE_URL));
		}
		myLog.debug("in shopping cart bf domain passed :::: "
				+ shoppingCartOpr.getApplicationFlags().getApplicationFlagMap().get(CommonConstant.WEBSITE_URL));

		if ((propertiesReader.getValueOfKey("order_receipt_mail_enabled") != null)
				&& propertiesReader.getValueOfKey("order_receipt_mail_enabled").equals("1")) {
			MailParameters mailParameters = new MailParameters();

			InternetAddress[] addressTo = new InternetAddress[1];
			// InternetAddress[] addressCC = new InternetAddress[1];
			InternetAddress[] addressBCC = new InternetAddress[1];

			InternetAddress ia = new InternetAddress();
			myLog.debug("check for email:::" + shoppingCartOpr.getPaymentDVO().getReceivedBillingAddress().getEmail1());
			ia.setAddress(shoppingCartOpr.getPaymentDVO().getReceivedBillingAddress().getEmail1());
			addressTo[0] = ia;

			InternetAddress iaBCC1 = new InternetAddress();
			iaBCC1.setAddress(propertiesReaderCommon.getValueOfKey("system_owner_email_id"));
			addressBCC[0] = iaBCC1;

			// InternetAddress iaBCC2 = new InternetAddress();
			// iaBCC2.setAddress(propertiesReaderCommon.getValueOfKey("system_owner_email_id_2"));
			// addressBCC[1] = iaBCC2;

			mailParameters.setMailRecipients(addressTo);
			// mailParameters.setMailRecipientsCC(addressCC);
			mailParameters.setMailRecipientsBCC(addressBCC);
			mailParameters.setMailSubject(propertiesReader.getValueOfKey("order_receipt_mail_subject"));
			mailParameters.setRoutingKey(propertiesReaderCommon
					.getValueOfKey("rabbitmq_email_routing_key_order_management_invoice"));
			mailParameters.setMessageQueue(propertiesReaderCommon
					.getValueOfKey("rabbitmq_email_message_queue_order_management_invoice"));
			// mailParameters.setMailMessage(MessageFormatter.getFormattedMessage(propertiesReader
			// .getValueOfKey("order_receipt_mail_body"), new String[] {
			// shoppingCartOpr.getRetailOrderRecord().getBillingDetails().getFirstName(),
			// shoppingCartOpr.getRetailOrderRecord().getOrderNumber() }));
			mailParameters.setMailDVOObject(shoppingCartOpr);

			mailParameters.setCustomerKey(propertiesReader.getValueOfKey("customer_key"));
			mailParameters.setMailFormat(CommonConstant.MimeType.TEXT_HTML);

			try {
				WebMail portalMail = new WebMail(mailParameters);

				portalMail.sendMultipleMail();
				myLog.debug("after sending successfully the mail:::::");
			} catch (MessagingException e) {
				throw new FrameworkException("messaging_exception", e.getCause());
			}
		}

		return shoppingCartOpr;
	}

	public ShoppingCartOpr updateOrderStatusConfirmedForPayPal(ShoppingCartOpr shoppingCartOpr)
			throws FrameworkException {
		// TEMPLATE TO CALL BC METHOD FROM BF ver 1.0
		// ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		ShoppingCartOpr returnShoppingCartOpr = new ShoppingCartOpr();
		UserTransaction userTransaction = startUserTransaction();
		try {
			// add your BC call here
			returnShoppingCartOpr = new ShoppingCartBC().updateOrderStatusConfirmedForPayPal(shoppingCartOpr);
			setCommitFlag(true);
		} catch (FrameworkException e) {
			setCommitFlag(false);
			handleUserTransaction(userTransaction);
			throw e;
		}
		handleUserTransaction(userTransaction);

		return returnShoppingCartOpr;
	}

	// added by ketan

	public ShoppingCartOpr dispatchEmail(OperationalDataValueObject searchParameters) throws FrameworkException,
			BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
		PropertiesReader propertiesReaderCommon = new PropertiesReader(CommonConstant.MessageLocation.COMMON_MESSAGES);
		ShoppingCartBC shoppingCartBC = new ShoppingCartBC();
		ShoppingCartOpr shoppingCartOprGot = (ShoppingCartOpr) searchParameters;
		ShoppingCartOpr shoppingCartOpr = new ShoppingCartOpr();
		// to be removed
		// shoppingCartOpr =
		// shoppingCartBC.getPaymentRecordOnReceivedMerchantParameter(searchParameters);
		myLog.debug("received order id inside get invoice detail s:::::::::::"
				+ shoppingCartOprGot.getPaymentDVO().getReceivedOrderId());

		shoppingCartOpr = shoppingCartBC.getOrderDetails(shoppingCartOprGot);
		shoppingCartOpr = shoppingCartBC.getOrderHeaderDetails(shoppingCartOpr);
		// shoppingCartOpr =
		// shoppingCartBC.getCustumizationRecord(shoppingCartOpr);

		// copy website url
		if (shoppingCartOprGot.getApplicationFlags().getApplicationFlagMap().containsKey(CommonConstant.WEBSITE_URL)
				&& shoppingCartOprGot.getApplicationFlags().getApplicationFlagMap().get(CommonConstant.WEBSITE_URL) != null) {
			shoppingCartOpr
					.getApplicationFlags()
					.getApplicationFlagMap()
					.put(CommonConstant.WEBSITE_URL,
							shoppingCartOprGot.getApplicationFlags().getApplicationFlagMap()
									.get(CommonConstant.WEBSITE_URL));
		}
		myLog.debug("in shopping cart bf domain passed :::: "
				+ shoppingCartOpr.getApplicationFlags().getApplicationFlagMap().get(CommonConstant.WEBSITE_URL));

		if ((propertiesReader.getValueOfKey("dispatch_mail_enabled") != null)
				&& propertiesReader.getValueOfKey("dispatch_mail_enabled").equals("1")) {
			MailParameters mailParameters = new MailParameters();

			InternetAddress[] addressTo = new InternetAddress[1];
			// InternetAddress[] addressCC = new InternetAddress[2];
			InternetAddress[] addressBCC = new InternetAddress[1];

			InternetAddress ia = new InternetAddress();
			myLog.debug("check for email:::" + shoppingCartOpr.getPaymentDVO().getReceivedShippingAddress().getEmail1());
			ia.setAddress(shoppingCartOpr.getPaymentDVO().getReceivedShippingAddress().getEmail1());
			addressTo[0] = ia;

			InternetAddress iaBCC1 = new InternetAddress();
			iaBCC1.setAddress(propertiesReaderCommon.getValueOfKey("system_owner_email_id"));
			addressBCC[0] = iaBCC1;

			// InternetAddress iaBCC2 = new InternetAddress();
			// iaBCC2.setAddress(propertiesReaderCommon.getValueOfKey("system_owner_email_id_2"));
			// addressBCC[1] = iaBCC2;

			// InternetAddress iaCC = new InternetAddress();
			// iaCC.setAddress(propertiesReaderCommon.getValueOfKey("system_owner_email_id_2"));
			// InternetAddress iaCC1 = new InternetAddress();
			// iaCC1.setAddress(propertiesReaderCommon.getValueOfKey("system_owner_email_id_3"));
			// addressCC[0] = iaCC;
			// addressCC[1] = iaCC1;
			//
			mailParameters.setMailRecipients(addressTo);
			// mailParameters.setMailRecipientsCC(addressCC);
			mailParameters.setMailRecipientsBCC(addressBCC);
			// mailParameters.setMailSubject(propertiesReader.getValueOfKey("dispatch_mail_subject"));
			mailParameters.setMailSubject(MessageFormatter.getFormattedMessage(propertiesReader
					.getValueOfKey("dispatch_mail_subject"), new String[] { shoppingCartOpr.getPaymentDVO()
					.getSentOrderId() }));
			mailParameters.setMailMessage(MessageFormatter.getFormattedMessage(
					propertiesReader.getValueOfKey("dispatch_mail_body"), new String[] {
							shoppingCartOpr.getPaymentDVO().getReceivedShippingAddress().getFirstName(),
							shoppingCartOpr.getPaymentDVO().getSentOrderId(),
							shoppingCartOpr.getPaymentDVO().getReceivedShippingAddress().getLastName(),
							shoppingCartOpr.getPaymentDVO().getReceivedShippingAddress().getAddressLine1(),
							shoppingCartOpr.getPaymentDVO().getReceivedShippingAddress().getAddressLine2(),
							shoppingCartOpr.getPaymentDVO().getReceivedShippingAddress().getAddressLine3(),
							shoppingCartOpr.getPaymentDVO().getReceivedShippingAddress().getCityDvo().getName(),
							shoppingCartOpr.getPaymentDVO().getReceivedShippingAddress().getPinRecord().getCode(),
							shoppingCartOpr.getPaymentDVO().getReceivedShippingAddress().getStateDVO().getName(),
							shoppingCartOpr.getPaymentDVO().getReceivedShippingAddress().getCountryDvo().getName(),
							shoppingCartOpr.getRetailOrderRecord().getOrderTrackingNumber(),
							shoppingCartOpr.getRetailOrderRecord().getCourierRecord().getParameterCode() }));

			mailParameters.setCustomerKey(propertiesReaderCommon.getValueOfKey("customer_key"));
			mailParameters.setMailFormat(CommonConstant.MimeType.TEXT_PLAIN);

			try {
				WebMail portalMail = new WebMail(mailParameters);

				portalMail.sendMultipleMail();
				myLog.debug("after sending successfully the mail:::::");
			} catch (MessagingException e) {
				throw new FrameworkException("messaging_exception", e.getCause());
			}
		}

		return shoppingCartOpr;
	}

	public ShoppingCartOpr getShippingChargesMode(ShoppingCartOpr shoppingCartOpr) throws FrameworkException,
			BusinessException {
		return new ShoppingCartBC().getShippingChargesMode(shoppingCartOpr);

	}

	public ShoppingCartOpr populateShippingCharges(ShoppingCartOpr shoppingCartOpr) throws FrameworkException,
			BusinessException {
		return new ShoppingCartBC().populateShippingCharges(shoppingCartOpr);
	}

	public ShoppingCartOpr populateDefaultShippingCharges(ShoppingCartOpr shoppingCartOpr) throws FrameworkException,
			BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		ShoppingCartOpr returnShoppingCartOpr = new ShoppingCartOpr();

		HierarchyChargesIntegrationOpr hierarchyChargesIntegrationOpr = new HierarchyChargesIntegrationOpr();
		hierarchyChargesIntegrationOpr.getHierarchyChargesRecord().getDeliveryChargesRecord()
				.setCountryRecord(shoppingCartOpr.getRetailOrderRecord().getShippingDetails().getCountry());
		hierarchyChargesIntegrationOpr.setShoppingCartProductList(shoppingCartOpr.getShoppingCartProductList());
		HierarchyChargesIntegrationOpr returnHierarchyChargesIntegrationOpr = new HierarchyChargesIntegration()
				.populateDefaultShippingCharges(hierarchyChargesIntegrationOpr);
		returnShoppingCartOpr.setShoppingCartProductList(returnHierarchyChargesIntegrationOpr
				.getShoppingCartProductList());
		for (int i = 0; i < returnShoppingCartOpr.getShoppingCartProductList().size(); i++) {
			myLog.debug("check in returnHierarchyChargesIntegrationOpr for delivery chagres"
					+ returnShoppingCartOpr.getShoppingCartProductList().get(i).getProductSkuRecord()
							.getDeliverChargesRecord().getDeliveryCharge());
		}
		return returnShoppingCartOpr;
	}

	public OperationalDataValueObject getMappedProdutToPromoCode(OperationalDataValueObject searchParameters)
			throws FrameworkException, BusinessException {
		return new ShoppingCartBC().getMappedProdutToPromoCode((ShoppingCartOpr) searchParameters);
	}

	public ShoppingCartOpr saveSmsGateWayResponse(ShoppingCartOpr shoppingCartOpr) throws FrameworkException,
			BusinessException {
		return new ShoppingCartBC().saveSmsGateWayResponse(shoppingCartOpr);
	}

	public ShoppingCartOpr saveGuestUserDetails(ShoppingCartOpr shoppingCartOpr) throws FrameworkException,
			BusinessException {
		return new ShoppingCartBC().saveGuestUserDetails(shoppingCartOpr);
	}

}
