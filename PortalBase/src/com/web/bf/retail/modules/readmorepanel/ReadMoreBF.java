package com.web.bf.retail.modules.readmorepanel;

import com.web.bc.retail.modules.readmorepanel.ReadMoreBC;
import com.web.common.dvo.opr.retail.ReadMoreOpr;
import com.web.common.dvo.util.OptionsDVO;
import com.web.common.parents.BusinessFacade;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;

public class ReadMoreBF extends BusinessFacade {
	private String propertiesLocation = "/com/web/bb/retail/module/readmorepanel/readmore";

	public OptionsDVO getAllOptions(OptionsDVO options) throws FrameworkException, BusinessException {
		// OptionsHelperBC optionsHelperBC = new OptionsHelperBC();
		OptionsDVO optionsDVO = new OptionsDVO();
		// HashMap<String, ArrayList<SelectItem>> allOptionsMap = new
		// HashMap<String, ArrayList<SelectItem>>();
		// allOptionsMap = optionsHelperBC.getAllCurrencies(new HashMap());
		// optionsDVO.getAllOptionsValues().put(CommonConstant.CURRENCY_LIST,
		// allOptionsMap.get(CommonConstant.CURRENCY_LIST));
		//
		// // fetch sizes mapped to this product

		return optionsDVO;
	}

	public ReadMoreOpr getProductDetailsForReadMore(ReadMoreOpr readMoreOpr) throws FrameworkException,
			BusinessException {
		return new ReadMoreBC().getProductDetailsForReadMore(readMoreOpr);
	}

	public void saveProductForLater(ReadMoreOpr readMoreSaveOpr) throws FrameworkException, BusinessException {
		// ShoppingCartIntegrationOpr shoppingCartIntegrationOpr = new
		// ShoppingCartIntegrationOpr();
		//
		// ShoppingCartIntegration shoppingCartIntegration = new
		// ShoppingCartIntegration();
		// shoppingCartIntegrationOpr.setShoppingCartProductRecord(readMoreSaveOpr
		// .getShoppingCartProduct());
		// shoppingCartIntegration.saveProductForLater(shoppingCartIntegrationOpr);
		//
		// }

		// public ReadMoreOpr makeEnquiry(ReadMoreOpr makeEnquiryProductRecord)
		// throws FrameworkException {
		// ReadMoreOpr readMoreOpr = new ReadMoreOpr();
		// UserTransaction userTransaction = startUserTransaction();
		// try {
		// readMoreOpr = new ReadMoreBC()
		// .makeEnquiry(makeEnquiryProductRecord);
		// setCommitFlag(true);
		// } catch (FrameworkException e) {
		// setCommitFlag(false);
		// handleUserTransaction(userTransaction);
		// throw e;
		// }
		// handleUserTransaction(userTransaction);
		//
		// PropertiesReader propertiesReader = new PropertiesReader(
		// propertiesLocation);
		// if ((propertiesReader.getValueOfKey("send_enquiry_mail_enabled") !=
		// null)
		// && propertiesReader.getValueOfKey("send_enquiry_mail_enabled")
		// .equals("1")) {
		// PropertiesReader propertiesReaderCommon = new PropertiesReader(
		// CommonConstant.COMMON_MESSAGE_LOCATION);
		// MailParameters mailParameters = new MailParameters();
		//
		// InternetAddress[] addressTo = new InternetAddress[1];
		//
		// InternetAddress ia = new InternetAddress();
		// ia.setAddress(propertiesReaderCommon
		// .getValueOfKey("system_owner_email_id"));
		// addressTo[0] = ia;
		//
		// mailParameters.setMailRecipients(addressTo);
		//
		// mailParameters
		// .setMailSubject(MessageFormatter.getFormattedMessage(
		// propertiesReader
		// .getValueOfKey("send_enquiry_mail_subject"),
		// new String[]{makeEnquiryProductRecord
		// .getProductRecord().getName()}));
		// mailParameters.setMailMessage(MessageFormatter.getFormattedMessage(
		// propertiesReader.getValueOfKey("send_enquiry_mail_body"),
		// new String[]{
		// makeEnquiryProductRecord.getProductRecord()
		// .getCode(),
		// makeEnquiryProductRecord.getSendEnquiryRecord()
		// .getAddress().getFirstName(),
		// makeEnquiryProductRecord.getSendEnquiryRecord()
		// .getAddress().getEmail(),
		// makeEnquiryProductRecord.getSendEnquiryRecord()
		// .getComments()}));
		//
		// mailParameters.setCustomerKey(propertiesReaderCommon
		// .getValueOfKey("customer_key"));
		// mailParameters.setMailFormat(CommonConstant.MIME_TYPE_TEXT_PLAIN);
		//
		// try {
		// PortalMail portalMail = new PortalMail(mailParameters);
		// portalMail.sendMultipleMail();
		// } catch (MessagingException e) {
		// throw new FrameworkException("messaging_exception",
		// e.getCause());
		// }
		// }
		//
		// return readMoreOpr;
	}

	public ReadMoreOpr getCountryDependentData(ReadMoreOpr readMoreOpr) throws FrameworkException {
		return new ReadMoreBC().getCountryDependantData(readMoreOpr);
	}

	public ReadMoreOpr getProductAlternativeImages(ReadMoreOpr readMoreOpr) throws FrameworkException {
		return new ReadMoreBC().getProductAlternativeImages(readMoreOpr);
	}

	public ReadMoreOpr getProductProperties(ReadMoreOpr readMoreOpr) throws FrameworkException {
		return new ReadMoreBC().getProductProperties(readMoreOpr);
	}

	public ReadMoreOpr getCategoriesForProduct(ReadMoreOpr readMoreOpr) throws FrameworkException {
		return new ReadMoreBC().getCategoriesForProduct(readMoreOpr);
	}
}
