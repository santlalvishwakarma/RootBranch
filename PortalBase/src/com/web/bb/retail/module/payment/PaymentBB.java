package com.web.bb.retail.module.payment;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import urn.ebay.api.PayPalAPI.PayPalAPIInterfaceServiceService;
import urn.ebay.api.PayPalAPI.SetExpressCheckoutReq;
import urn.ebay.api.PayPalAPI.SetExpressCheckoutRequestType;
import urn.ebay.api.PayPalAPI.SetExpressCheckoutResponseType;
import urn.ebay.apis.CoreComponentTypes.BasicAmountType;
import urn.ebay.apis.eBLBaseComponents.AddressType;
import urn.ebay.apis.eBLBaseComponents.CountryCodeType;
import urn.ebay.apis.eBLBaseComponents.CurrencyCodeType;
import urn.ebay.apis.eBLBaseComponents.PaymentActionCodeType;
import urn.ebay.apis.eBLBaseComponents.PaymentDetailsItemType;
import urn.ebay.apis.eBLBaseComponents.PaymentDetailsType;
import urn.ebay.apis.eBLBaseComponents.SetExpressCheckoutRequestDetailsType;

import com.web.bf.retail.modules.payment.PaymentBF;
import com.web.common.constants.CommonConstant;
import com.web.common.dvo.opr.retail.ShoppingCartOpr;
import com.web.common.dvo.retail.modules.PaymentDVO;
import com.web.common.dvo.retail.modules.PaymentOptionDVO;
import com.web.common.dvo.util.OptionsDVO;
import com.web.common.parents.BackingBean;
import com.web.common.parents.CCAvenueHelper;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;
import com.web.foundation.mail.MailParameters;
import com.web.foundation.mail.WebMail;
import com.web.foundation.validation.FoundationValidator;
import com.web.util.PropertiesReader;

public class PaymentBB extends BackingBean {
	private static final long serialVersionUID = -5937367364735170436L;
	private String propertiesLocation = "com/web/bb/retail/module/payment/payment";
	private ShoppingCartOpr paymentOpr;
	private boolean submitForm;
	private ArrayList<Object> paymentOptions;
	private String paymentGatewayProvider;
	private String iciciPaysealMerchantId;
	private String paymentFormAction;
	private boolean paymentButtonFlag;
	private boolean paymentOfflineButtonFlag;
	private boolean paymentEBSButtonFlag = true;
	private boolean bankDeposite;
	private String wireText = "<label style=\"font-weight: normal;\">Use Wire transfer option using the below details to transfer money to our account:</label><br /><table cellpadding=\"2\" cellspacing=\"0\" border=\"0\" style=\"margin-left: 20px;\"><tr><td>Bank Name</td><td>:</td><td>ICICI Bank</td></tr><tr><td>Account Name</td><td>:</td><td>Amar Chitra Katha</td></tr><tr><td>Branch</td><td>:</td><td>MIDC Branch</td></tr><tr><td>Account No</td><td>:</td><td>054405005310</td></tr><tr><td>IFSC Code</td><td>:</td><td>ICIC0000544</td></tr><tr><td>Swift code</td><td>:</td><td>ICICINBBCTS</td></tr></table>";
	private String depositeText = "<label style=\"font-weight: normal;\">Use the below details to deposit money in our bank account.You can walk into any ICICI bank and deposit cash in our account:</label><br /><table cellpadding=\"2\" cellspacing=\"0\" border=\"0\" style=\"margin-left: 20px;\"><tr><td>Bank Name</td><td>:</td><td>ICICI Bank</td></tr><tr><td>Account Name</td><td>:</td><td>Amar Chitra Katha</td></tr><tr><td>Branch</td><td>:</td><td>MIDC Branch</td></tr><tr><td>Account No</td><td>:</td><td>054405005310</td></tr><tr><td>IFSC Code</td><td>:</td><td>ICIC0000544</td></tr></table>";
	private ArrayList<PaymentOptionDVO> offlineList;
	private ArrayList<PaymentOptionDVO> cashPayList;
	private boolean cashOption;
	private String chequeText = "<label style=\"font-weight: normal;\">Cheques can be drawn in favour of \"Amar Chitra Katha\" and deposited it in an ICICI back or can be sent to us</label>";
	private String ddText = "<label style=\"font-weight: normal;\">Send us the Demand Draft drawn in favour of \"Amar Chitra Katha\"</label>";
	private String visitText = "<label style=\"font-weight: normal;\">Visit our office in Mumbai to pay cash.</label><br /><table cellpadding=\"2\" cellspacing=\"0\" border=\"0\" style=\"margin-left: 20px;\"><tr><td>Amar Chitra Katha</td></tr><tr><td>105, Niraj Industrial Estate</td></tr><tr><td>Off Mahakali Caves Road</td></tr><tr><td>Andheri East</td></tr><tr><td>Mumbai 400093</td></tr></table> ";
	private String codText = "<label style=\"font-weight: normal;\">COD is available for residents of Mumbai, Delhi, Kolkata Bengaluru and Agra. We require a 15% deposit at the time of purchase(which you can make using any mode of payment. Please call us in case of questions) and the remaining 85% can be COD.</label>";
	private String paymentCode;
	private Float emiPrice;
	private boolean paypalFlag = false;
	private boolean displayEmi;

	@Override
	public void executeSearch(ActionEvent event) {

	}

	@Override
	public boolean validateSearch() {

		return false;
	}

	@Override
	public void executeSave(ActionEvent event) {

	}

	@Override
	public boolean validateSave() {

		return false;
	}

	@Override
	public void editDetails() {

	}

	@Override
	public OptionsDVO getAllOptions() {

		if (allOptions == null) {
			allOptions = new OptionsDVO();
		}

		if (allOptions.getAllOptionsValues() == null || allOptions.getAllOptionsValues().isEmpty()) {
			try {
				allOptions = new PaymentBF().getAllOptions(allOptions);

				// paymentOptions =
				// allOptions.getAllOptionsValues().get("OFFLINE");
				// if (paymentOptions != null) {
				// int size = paymentOptions.size();
				// offlineList = new ArrayList<PaymentOptionDVO>();
				// cashPayList = new ArrayList<PaymentOptionDVO>();
				//
				// for (int i = 0; i < size; i++) {
				// PaymentOptionDVO paymentOptionDVO = new PaymentOptionDVO();
				// PaymentOptionDVO paymentCashOptionDVO = new
				// PaymentOptionDVO();
				// String code = (String) paymentOptions.get(i).getValue();
				// String description = paymentOptions.get(i).getLabel();
				// if (code != null) {
				// if (code.equalsIgnoreCase("Cheque Payment")) {
				// paymentOptionDVO.setPaymentCode(code);
				// paymentOptionDVO.setPaymentDescription(description);
				// offlineList.add(paymentOptionDVO);
				// } else if (code.equalsIgnoreCase("Demand Draft")) {
				// paymentOptionDVO.setPaymentCode(code);
				// paymentOptionDVO.setPaymentDescription(description);
				// offlineList.add(paymentOptionDVO);
				// } else if (code.equalsIgnoreCase("Wire Transfer")) {
				// paymentOptionDVO.setPaymentCode(code);
				// paymentOptionDVO.setPaymentDescription(description);
				// offlineList.add(paymentOptionDVO);
				// } else if
				// (code.equalsIgnoreCase("Deposit in our Bank Account")) {
				// paymentCashOptionDVO.setPaymentCode(code);
				// paymentCashOptionDVO.setPaymentDescription(description);
				// cashPayList.add(paymentCashOptionDVO);
				// } else if
				// (code.equalsIgnoreCase("Visit our factory and pay cash")) {
				// paymentCashOptionDVO.setPaymentCode(code);
				// paymentCashOptionDVO.setPaymentDescription(description);
				// cashPayList.add(paymentCashOptionDVO);
				// } else if (code.equalsIgnoreCase("COD")) {
				// paymentCashOptionDVO.setPaymentCode(code);
				// paymentCashOptionDVO.setPaymentDescription(description);
				// cashPayList.add(paymentCashOptionDVO);
				// }
				//
				// }
				//
				// }
				// if (!offlineList.isEmpty()) {
				//
				// for (int i = 0; i < offlineList.size(); i++) {
				// if
				// (offlineList.get(i).getPaymentCode().equals("Wire Transfer"))
				// {
				// offlineList.get(i).getOperationalAttributes().setRecordSelected(true);
				// offlineList.get(i).setPaymentDescription(wireText);
				// }
				// }
				// }
				// }
			} catch (FrameworkException e) {
				// handle framework exception
				handleException((Exception) e, propertiesLocation);
			} catch (BusinessException e) {
				// handle BusinessException;
				handleException((Exception) e, propertiesLocation);
			}
		}

		return allOptions;
	}

	@Override
	public void setAllOptions(OptionsDVO allOptions) {
	}

	public ShoppingCartOpr getPaymentOpr() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		// myLog.debug("check size in payment:::::");
		if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.containsKey(CommonConstant.PAYMENT_OPR)) {
			paymentOpr = (ShoppingCartOpr) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
					.get(CommonConstant.PAYMENT_OPR);
			myLog.debug("payment bb city name::"
					+ paymentOpr.getRetailOrderRecord().getBillingDetails().getCityDvo().getCode());
			paymentCode = "";

			if (paymentOpr.getOriginaltotalOrderPrice() >= 2000) {
				displayEmi = true;
			} else {
				displayEmi = false;
			}
		}

		if (paymentOpr == null) {
			paymentOpr = new ShoppingCartOpr();
		}

		return paymentOpr;
	}

	public void setPaymentOpr(ShoppingCartOpr paymentOpr) {
		this.paymentOpr = paymentOpr;
	}

	public void cancelPayment(ActionEvent ae) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		setErrorList(new ArrayList<String>());
		setSuccessMsg("");
		myLog.debug("inside cancelPayment ::: ");
		try {
			ShoppingCartOpr shoppingCartOpr = new ShoppingCartOpr();
			shoppingCartOpr.getRetailOrderRecord().setId(paymentOpr.getRetailOrderRecord().getId());
			new PaymentBF().cancelOrder(shoppingCartOpr);

			shoppingCartOpr = new ShoppingCartOpr();
			shoppingCartOpr.setShoppingCartProductList(paymentOpr.getShoppingCartProductList());
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
					.put(CommonConstant.SHOPPING_CART_OPR, shoppingCartOpr);
			paymentOpr = new ShoppingCartOpr();
		} catch (FrameworkException e) {
			myLog.error("error occurred while cancelling order " + e);
			handleException(e, propertiesLocation);
		} catch (BusinessException e) {
			// handle framework exception
			handleException(e, propertiesLocation);
		}
	}

	public void doPayment(ActionEvent ae) {
		// TEMPLATE TO CALL SAVE BF METHOD FROM BB ver 1.1
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		setErrorList(new ArrayList<String>());
		setSuccessMsg("");
		myLog.debug("inside doPayment :::::::: ");

		try {
			new PaymentBF().updateOrderStatusPendingDispatch(paymentOpr);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
					.put(CommonConstant.INVOICE_OPR, paymentOpr);

			FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
					.remove(CommonConstant.SHOPPING_CART_OPR);

			String domain = FacesContext.getCurrentInstance().getExternalContext().getRequestServerName();
			// myLog.debug("check for domain:::::::" + domain);
			paymentOpr.getApplicationFlags().getApplicationFlagMap().put(CommonConstant.WEBSITE_URL, domain);

			// mail invoice
			sendEmail(paymentOpr);

		} catch (FrameworkException e) {
			// handle framework exception
			myLog.error("error occurred while updating order status " + e);
			handleException((Exception) e, commonMessages);
		}
	}

	private void sendEmail(ShoppingCartOpr shoppingCartOpr) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
		PropertiesReader propertiesReaderCommon = new PropertiesReader(CommonConstant.MessageLocation.COMMON_MESSAGES);

		if ((propertiesReader.getValueOfKey("order_receipt_mail_enabled") != null)
				&& propertiesReader.getValueOfKey("order_receipt_mail_enabled").equals("1")) {
			MailParameters mailParameters = new MailParameters();

			InternetAddress[] addressTo = new InternetAddress[1];
			// InternetAddress[] addressCC = new InternetAddress[1];
			InternetAddress[] addressBCC = new InternetAddress[1];

			InternetAddress ia = new InternetAddress();
			ia.setAddress(shoppingCartOpr.getRetailOrderRecord().getBillingDetails().getEmail1());
			addressTo[0] = ia;
			myLog.debug("InternetAddress :: ia.getAddress() :: " + ia.getAddress());

			InternetAddress iaBCC1 = new InternetAddress();
			iaBCC1.setAddress(propertiesReaderCommon.getValueOfKey("system_owner_email_id"));
			addressBCC[0] = iaBCC1;
			myLog.debug("InternetAddress :: iaBCC1.getAddress() :: " + iaBCC1.getAddress());

			mailParameters.setMailRecipients(addressTo);
			mailParameters.setMailRecipientsBCC(addressBCC);
			mailParameters.setMailSubject(propertiesReader.getValueOfKey("order_receipt_mail_subject"));
			mailParameters.setCustomerKey(propertiesReader.getValueOfKey("customer_key"));
			mailParameters.setMailFormat(CommonConstant.MimeType.TEXT_HTML);

			try {
				WebMail webMail = new WebMail(mailParameters);
				webMail.sendMultipleMail();
			} catch (MessagingException e) {
				handleException(e, propertiesLocation);
			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);
			}
		}
	}

	public String saveToWishlist() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
		String navigationPage = null;
		myLog.debug("inside saveToWishlist" + paymentOpr.getRetailOrderRecord().getId());

		try {
			paymentOpr.getRetailUserDetails().setUserLogin(
					(String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
							.get(CommonConstant.LOGGED_USER_KEY));
			new PaymentBF().saveProductForLater(paymentOpr);

			setSuccessMsg(propertiesReader.getValueOfKey("save_success"));
			navigationPage = "pretty:homePage";
		} catch (FrameworkException e) {
			// handle framework exception
			handleException(e, propertiesLocation);
		} catch (BusinessException e) {
			// handle framework exception
			handleException(e, propertiesLocation);
		}
		return navigationPage;
	}

	public String registerSaveToWishlist() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("inside registerSaveToWishlist");
		String navigationPage = null;

		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		boolean validationFlag = false;

		setErrorList(new ArrayList<String>());
		setSuccessMsg("");

		validationFlag = validateRegistration();

		if (validationFlag) {
			try {
				String domain = FacesContext.getCurrentInstance().getExternalContext().getRequestServerName();
				myLog.debug("Domain is:::::::" + domain);
				paymentOpr.getApplicationFlags().getApplicationFlagMap().put(CommonConstant.WEBSITE_URL, domain);
				paymentOpr = new PaymentBF().executeRegister(paymentOpr);
				externalContext.getSessionMap().put(CommonConstant.LOGGED_USER_KEY,
						paymentOpr.getRetailUserDetails().getUserLogin().trim());
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
						.put(CommonConstant.LOGGED_USER_KEY, paymentOpr.getRetailUserDetails().getUserLogin());
				FacesContext
						.getCurrentInstance()
						.getExternalContext()
						.getSessionMap()
						.put(CommonConstant.LOGGED_USER_ROLES,
								paymentOpr.getApplicationFlags().getApplicationFlagMap()
										.get(CommonConstant.LOGGED_USER_ROLES));
				FacesContext
						.getCurrentInstance()
						.getExternalContext()
						.getSessionMap()
						.put(CommonConstant.LOGGED_USER_DATA,
								paymentOpr.getRetailOrderRecord().getBillingDetails().getFirstName() + " "
										+ paymentOpr.getRetailOrderRecord().getBillingDetails().getLastName());
				paymentOpr.getApplicationFlags().getApplicationFlagMap().remove(CommonConstant.LOGGED_USER_ROLES);
				FacesContext
						.getCurrentInstance()
						.getExternalContext()
						.getSessionMap()
						.put(CommonConstant.RERENDER_COMPONENT,

						"welcomeDisplayName,topLinksForm,systemownerMainDisplayPanel,shoppingCartForm,saveProduct,addToCartLink");
				// send sms
				// try {
				// // get URL content
				// String a =
				// "http://www.unicel.in/SendSMS/sendmsg.php?uname=ACKMTR&pass=j4d3j4h7&send=ACKMDA&dest="
				// +
				// paymentOpr.getRetailOrderRecord().getBillingDetails().getPhoneNumber()
				// +
				// "&msg=We%20are%20pleased%20to%20confirm%20your%20registration%20with%20www.amarchitrakatha.com.%20Your%20login%20ID%20is%20"
				// + paymentOpr.getRetailUserDetails().getUserLogin();
				// URL url = new URL(a);
				// myLog.debug("check here for url" + url);
				// HttpURLConnection conn = (HttpURLConnection)
				// url.openConnection();
				// conn.setRequestMethod("POST");
				//
				// // open the stream and put it into BufferedReader
				// BufferedReader br = new BufferedReader(new
				// InputStreamReader(conn.getInputStream()));
				//
				// myLog.debug(conn.getResponseCode());
				//
				// String inputLine;
				// while ((inputLine = br.readLine()) != null) {
				// myLog.debug(inputLine);
				// paymentOpr.getSmsGateWayRecord().setSmsResponseCode(inputLine);
				// }
				// br.close();
				//
				// myLog.debug("sms Done");
				//
				// // save response
				//
				// } catch (MalformedURLException e) {
				// e.printStackTrace();
				// } catch (IOException e) {
				// e.printStackTrace();
				// }

				// myLog.debug("after send sms to user save sms response code ");
				//
				// paymentOpr.getSmsGateWayRecord().setOrderNumber(null);
				// paymentOpr.getSmsGateWayRecord().setDestinationNumber(
				// paymentOpr.getRetailOrderRecord().getBillingDetails().getPhoneNumber());
				// String loggedUser = (String)
				// externalContext.getSessionMap().get(CommonConstant.LOGGED_USER_KEY);
				// paymentOpr.getSmsGateWayRecord().getAuditAttributes().setModifiedBy(loggedUser);
				//
				// try {
				// paymentOpr = new
				// ShoppingCartBF().saveSmsGateWayResponse(paymentOpr);
				// } catch (FrameworkException e) {
				// e.printStackTrace();
				// } catch (BusinessException e) {
				// e.printStackTrace();
				// }

				// saveToWishlist();
				navigationPage = "pretty:homePage";
			} catch (FrameworkException e) {
				// handle framework exception
				handleException((Exception) e, propertiesLocation);
			} catch (BusinessException e) {
				// handle BusinessException;
				handleException((Exception) e, propertiesLocation);
			}
		}

		return navigationPage;

	}

	public boolean validateRegistration() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
		FoundationValidator portalValidator = new FoundationValidator();
		// propertiesReader.setResourceBundle(propertiesLocation,
		// Locale.getDefault());
		ShoppingCartOpr shoppingRegistrationCartOpr = new ShoppingCartOpr();
		PaymentBF paymentBF = new PaymentBF();
		boolean flag = false;

		try {
			paymentOpr.getRetailUserDetails().setUserLogin(
					paymentOpr.getRetailOrderRecord().getBillingDetails().getEmail1());

			myLog.debug("check for the value##############" + paymentOpr.getRetailUserDetails().getUserLogin());

			shoppingRegistrationCartOpr = paymentBF.checkUserAvailability(paymentOpr);
			if (portalValidator.validateNull(shoppingRegistrationCartOpr.getRetailUserDetails().getUserLogin())) {
				myLog.debug("inside if:::::::" + shoppingRegistrationCartOpr.getRetailUserDetails().getUserLogin());
				addToErrorList(propertiesReader.getValueOfKey("user_not_available"));

			} else {
				flag = true;
			}

		} catch (FrameworkException e) {
			// handle framework exception
			handleException((Exception) e, propertiesLocation);
		} catch (BusinessException e) {
			// handle BusinessException;
			handleException((Exception) e, propertiesLocation);
		}

		return flag;
	}

	public boolean isSubmitForm() {
		return submitForm;
	}

	public void setSubmitForm(boolean submitForm) {
		this.submitForm = submitForm;
	}

	public void updateOrderStatusAsPaymentInitiated(ActionEvent ae) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("inside updateOrderStatusAsPaymentInitiated ::::::" + paymentOpr.getRetailOrderRecord().getId());
		myLog.debug("inside updateOrderStatusAsPaymentInitiated ::::::" + paymentOpr.getPaymentDVO().getPaymentType());
		submitForm = false;
		myLog.debug("payment code got :::::::::::::::" + paymentOpr.getPaymentDVO().getPaymentOption().getPaymentCode());

		boolean validateResult = validateOfflineSave();
		if (validateResult) {

			try {
				initiatePaymentTransaction();
				paymentOpr = new PaymentBF().updateOrderStatusAsPaymentInitiated(paymentOpr);
				paymentOpr = new PaymentBF().updateOrderPaymentType(paymentOpr);

				myLog.debug("payment merchant id ::::" + paymentOpr.getPaymentDVO().getSentMerchantId());

				submitForm = true;
			} catch (FrameworkException fwe) {
				myLog.debug("insidframework exception:::");
			} catch (BusinessException be) {
				myLog.debug("business exception ::::");
			}
		}
		myLog.debug("payment button flag ::::::" + paymentButtonFlag);

		// return null;
	}

	// public void getPaymentOptionDependent(AjaxBehaviorEvent abe) {
	// paymentOpr.getPaymentDVO().setPaymentOption(new PaymentOptionDVO());
	// ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
	// myLog.debug("inside getPaymentOptionDependent ::: " +
	// paymentOpr.getPaymentDVO().getPaymentType());
	// getPaymentOptionDependent();
	// }
	//
	// public void getPaymentOptionDependent() {
	// // TEMPLATE TO CALL SEARCH BF METHOD FROM BB ver 1.2
	// ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
	// // ShoppingCartBF shoppingCartBF = new ShoppingCartBF();
	// PaymentBF paymentBF = new PaymentBF();
	//
	// myLog.debug("inside getPaymentOptionDependent ::: " +
	// paymentOpr.getPaymentDVO().getPaymentType());
	//
	// if (paymentOpr.getPaymentDVO().getPaymentType() != null
	// && paymentOpr.getPaymentDVO().getPaymentType().trim().length() > 0) {
	// OptionsDVO optionsDVO = new OptionsDVO();
	// optionsDVO.setPaymentDVO(paymentOpr.getPaymentDVO());
	// optionsDVO.getApplicationFlags().getApplicationFlagMap().put(CommonConstant.PAYMENT_TYPE_KEY,
	// true);
	//
	// try {
	// myLog.debug("b4 bf call for getPaymentOptionDependent ::getAllOptions()");
	// allOptions = paymentBF.getAllOptions(optionsDVO);
	// myLog.debug("after bf call for getPaymentOptionDependent ::getAllOptions()");
	//
	// } catch (FrameworkException e) {
	// // handle framework exception
	// handleException(e, propertiesLocation);
	// } catch (BusinessException e) {
	// // handle BusinessException;
	// handleException(e, propertiesLocation);
	// }
	// } else {
	// myLog.error("in getPaymentOptionDependent :: payment type is null");
	// }
	// }

	public void paymentTypeChanged(AjaxBehaviorEvent event) {

		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("inside paymentTypeChanged:::::::");
		String payCode = null;
		int size = offlineList.size();
		myLog.debug("inside size:::::::" + size);
		for (int i = 0; i < size; i++) {
			offlineList.get(i).getOperationalAttributes()
					.setRecordPopulated(offlineList.get(i).getOperationalAttributes().getRecordSelected());
			myLog.debug("inside selected:::::::" + offlineList.get(i).getOperationalAttributes().getRecordSelected());
			if (offlineList.get(i).getOperationalAttributes().getRecordSelected()) {
				cashOption = false;
				for (int j = 0; j < cashPayList.size(); j++) {
					cashPayList.get(j).getOperationalAttributes().setRecordSelected(false);
				}
				myLog.debug("Selected option::::::" + offlineList.get(i).getPaymentCode());

				payCode = offlineList.get(i).getPaymentCode();
				if (payCode != null) {
					if (payCode.equalsIgnoreCase("Wire Transfer")) {
						offlineList.get(i).setPaymentDescription(wireText);
					} else if (payCode.equalsIgnoreCase("Cheque Payment")) {
						offlineList.get(i).setPaymentDescription(chequeText);
					} else if (payCode.equalsIgnoreCase("Demand Draft")) {
						offlineList.get(i).setPaymentDescription(ddText);
					}
				}

			}
		}
		paymentOpr.getPaymentDVO().getPaymentOption().setPaymentCode(payCode);

	}

	public ArrayList<Object> getPaymentOptions() {
		if (paymentOptions == null) {
			paymentOptions = new ArrayList<Object>();
		}
		return paymentOptions;
	}

	public void setPaymentOptions(ArrayList<Object> paymentOptions) {
		this.paymentOptions = paymentOptions;
	}

	// to implement CC Avenue with PayPal - multiple payment gateway
	private void initiatePaymentTransaction() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("inside initiatePaymentTransaction:::::::");
		myLog.debug("Currency Symbol=="
				+ paymentOpr.getShoppingCartProductList().get(0).getProductSkuRecord().getOriginalCurrencyRecord()
						.getCurrencySymbol());
		String paymentCode = paymentOpr.getPaymentDVO().getPaymentOption().getPaymentCode();
		myLog.debug("payment code ::::::::::" + paymentCode);
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
				.getResponse();
		String accountId = "";
		String payBy = "";
		String orderNumber = paymentOpr.getRetailOrderRecord().getOrderNumber();
		myLog.debug("order number :::::" + orderNumber);
		String orderIdStr = "";
		if (paymentOpr.getRetailOrderRecord().getId() != null && paymentOpr.getRetailOrderRecord().getId() > 0) {
			orderIdStr = paymentOpr.getRetailOrderRecord().getId().toString();
		}
		myLog.debug("order Id :::::" + orderIdStr);

		String amount = paymentOpr.getOriginaltotalOrderPrice() == null ? "0" : paymentOpr.getOriginaltotalOrderPrice()
				.toString();

		myLog.debug("amount :::" + amount);
		String userLogin = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get(CommonConstant.LOGGED_USER_KEY);
		if (userLogin == null) {
			userLogin = CommonConstant.GUEST_USER;
		}
		myLog.debug("userLogin :::::::" + userLogin);
		String responseUrl = "http://"
				+ ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest())
						.getServerName()
				+ ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest())
						.getContextPath();
		myLog.debug("response url :::::::" + responseUrl);

		if (paymentGatewayProvider != null) {
			payBy = paymentGatewayProvider;
		}
		myLog.debug("payBy :::::::::::::::::::::::::::" + payBy);

		// COMMON THINGS TO BE SENT TO PG
		paymentOpr.getPaymentDVO().setSentAmount(amount);
		paymentOpr.getApplicationFlags().getApplicationFlagMap().put(CommonConstant.LOGGED_USER_KEY, userLogin);
		paymentOpr.getPaymentDVO().setUserLogin(userLogin);
		paymentOpr.getPaymentDVO().setSentOrderId(paymentOpr.getRetailOrderRecord().getOrderNumber());
		paymentOpr.getPaymentDVO().setSentBillingAddress(paymentOpr.getRetailOrderRecord().getBillingDetails());
		paymentOpr.getPaymentDVO().setSentDeliveryAddress(paymentOpr.getRetailOrderRecord().getShippingDetails());
		paymentOpr.getPaymentDVO().getPaymentOption().setPaymentGatewayProvider(payBy);

		// if
		// (payBy.equalsIgnoreCase(CommonConstant.PAYMENT_GATEWAY_PROVIDER_CCAVENUE))
		// {
		// // GENERATE ICICI SPECIFIC STRING FOR WHICH MD5 NEEDS TO BE
		// // CALCULATED
		// myLog.debug("inside CCAVENUE pg process");
		//
		// accountId = iciciPaysealMerchantId;
		// myLog.debug("Account Id (Merchant Id):::::::" + accountId);
		//
		// responseUrl = responseUrl + "/payment/response"; // +
		// // responseUrlEnd;
		// myLog.debug("Response url :::::" + responseUrl);
		//
		// paymentOpr.getPaymentDVO().setSentMerchantId(accountId);
		// paymentOpr.getPaymentDVO().setSentRedirectUrl(responseUrl);
		//
		// try {
		// String checkSum = new CCAvenueHelper().getChecksum(accountId,
		// String.valueOf(paymentOpr
		// .getRetailOrderRecord().getId()),
		// (paymentOpr.getTotalOrderPriceIntValue() == null ? "0"
		// : paymentOpr.getTotalOrderPriceIntValue().toString()), responseUrl,
		// CommonConstant.CCAVENUE_SECRET_KEY);
		// paymentOpr.getPaymentDVO().setSentCheckSum(checkSum);
		// } catch (Exception e) {
		// myLog.error("Exception occurred while generating checksum, this transaction is going to fail");
		// paymentOpr.getPaymentDVO().setSentCheckSum("");
		// }
		// try {
		// myLog.debug("Saving initial payment gateway data ::");
		// ShoppingCartOpr paymentOprRet = new ShoppingCartOpr();
		// // SAVING BEFORE REDIRECTING TO PAYMENT GATEWAY
		// paymentOpr.getRetailOrderRecord().getBillingDetails().getCountry()
		// .setCode(paymentOprRet.getPaymentDVO().getSentBillingAddress().getCountry().getCode());
		// myLog.debug("got delivery address country code ::::"
		// +
		// paymentOprRet.getPaymentDVO().getSentDeliveryAddress().getCountry().getCode());
		// paymentOpr.getRetailOrderRecord().getShippingDetails().getCountry()
		// .setCode(paymentOprRet.getPaymentDVO().getSentDeliveryAddress().getCountry().getCode());
		// myLog.debug("sent merchant parametetr ::::::::::::"
		// + paymentOprRet.getPaymentDVO().getSentMerchantParameter());
		// paymentOpr.getPaymentDVO().setSentMerchantParameter(
		// paymentOprRet.getPaymentDVO().getSentMerchantParameter());
		// myLog.debug("got reference number as :: " +
		// paymentOprRet.getPaymentDVO().getSentMerchantParameter());
		// myLog.debug("got billing address country code ::::"
		// +
		// paymentOprRet.getPaymentDVO().getSentBillingAddress().getCountry().getCode());
		// new PaymentBF().saveCCAvenueInitTransaction(paymentOpr);
		//
		// } catch (FrameworkException e) {
		// // handle framework exception
		// handleException(e, propertiesLocation);
		// myLog.error("Error saving payment transaction data ::");
		// } catch (Exception e) {
		// myLog.error("Exception occurred while generating checksum, this transaction is going to fail");
		// }
		// } else if
		// (payBy.equalsIgnoreCase(CommonConstant.PAYMENT_GATEWAY_PROVIDER_PAYPAL))
		// {
		// paymentFormAction = "https://secure.ebs.in/pg/ma/sale/pay/";
		// String secretKey = CommonConstant.SECRET_KEY;
		// accountId = CommonConstant.MERCHANT_ID;
		// // String mode = CommonConstant.PG_MODE_TEST;
		// String mode = CommonConstant.PG_MODE_LIVE;
		//
		// // END GENERATE EBS SPECIFIC STRING FOR WHICH MD5 NEEDS TO BE
		// // CALCULATED
		// paymentOpr.getPaymentDVO().setSentServerMode(mode);
		// responseUrl = responseUrl + "/payment/response?DR={DR}";
		// paymentOpr.getPaymentDVO().setSentDeliveryNotes("");
		// paymentOpr.getPaymentDVO().setSentMerchantId(accountId);
		// paymentOpr.getPaymentDVO().setSentRedirectUrl(responseUrl);
		// // amount = paymentOpr.getTotalOrderPriceIntValue() == null ? "0" :
		// // paymentOpr.getTotalOrderPriceIntValue()
		// // .toString();
		// amount = paymentOpr.getOriginaltotalOrderPrice() == null ? "0" :
		// paymentOpr.getOriginaltotalOrderPrice()
		// .toString();
		// // END GENERATE EBS SPECIFIC STRING FOR WHICH MD5 NEEDS TO BE
		// // CALCULATED
		// paymentOpr.getPaymentDVO().setSentAmount(amount);
		// paymentOpr.getPaymentDVO().setSentOrderId(paymentOpr.getRetailOrderRecord().getOrderNumber());
		// paymentOpr.getPaymentDVO().setSentBillingAddress(paymentOpr.getRetailOrderRecord().getBillingDetails());
		// paymentOpr.getPaymentDVO().setSentDeliveryAddress(paymentOpr.getRetailOrderRecord().getShippingDetails());
		// paymentOpr.getApplicationFlags().getApplicationFlagMap().put(CommonConstant.LOGGED_USER_KEY,
		// userLogin);
		// paymentOpr.getPaymentDVO().setUserLogin(userLogin);
		//
		// try {
		// // SAVING BEFORE REDIRECTING TO PAYMENT GATEWAY
		// myLog.debug("Saving initial payment gateway data ::");
		// paymentOpr = new
		// PaymentBF().saveInitialPaymentTransaction(paymentOpr);
		//
		// // update opr with requisite values
		// // myLog.debug("calculating checkSum :: ");
		// // String checkSum = CommonUtil.getChecksum(accountId,
		// // shoppingCartOpr.getRetailOrderRecord().getOrderNumber(),
		// // amount,
		// // secretKey, shoppingCartOpr
		// // .getPaymentDVO().getIdString());
		// // myLog.debug("got checkSum as :: " + checkSum);
		// // shoppingCartOpr.getPaymentDVO().setSentCheckSum(checkSum);
		//
		// // update the payment gateway record - mthod not coded yet
		// // shoppingCartOpr = new
		// //
		// ShoppingCartBF().updatePaymentGatewayDetailsForId(shoppingCartOpr);
		// // myLog.debug("after  updatePaymentGatewaytransaction :: ");
		//
		// // refetch details for payment gateway data
		// // shoppingCartOpr = new
		// // ShoppingCartBF().getPaymentGatewayDetailsForId(shoppingCartOpr);
		// } catch (FrameworkException e) {
		// // handle framework exception
		// handleException(e, propertiesLocation);
		// myLog.error("Error saving payment transaction data ::");
		// } catch (Exception e) {
		// myLog.error("Exception occurred while generating checksum, this transaction is going to fail");
		// paymentOpr.getPaymentDVO().setSentCheckSum("");
		// }
		// } else if
		// (payBy.equalsIgnoreCase(CommonConstant.PAYMENT_GATEWAY_PROVIDER_OFFLINE))
		// {
		// String mode = CommonConstant.PAYMENT_GATEWAY_PROVIDER_OFFLINE;
		//
		// // END GENERATE EBS SPECIFIC STRING FOR WHICH MD5 NEEDS TO BE
		// // CALCULATED
		// paymentOpr.getPaymentDVO().setSentServerMode(mode);
		// paymentOpr.getPaymentDVO().setSentDeliveryNotes("");
		// amount = paymentOpr.getTotalOrderPriceIntValue() == null ? "0" :
		// paymentOpr.getTotalOrderPriceIntValue()
		// .toString();
		// END GENERATE EBS SPECIFIC STRING FOR WHICH MD5 NEEDS TO BE
		// CALCULATED
		// paymentOpr.getPaymentDVO().setSentAmount(amount);
		// paymentOpr.getPaymentDVO().setSentOrderId(paymentOpr.getRetailOrderRecord().getOrderNumber());
		// paymentOpr.getPaymentDVO().setSentBillingAddress(paymentOpr.getRetailOrderRecord().getBillingDetails());
		// paymentOpr.getPaymentDVO().setSentDeliveryAddress(paymentOpr.getRetailOrderRecord().getShippingDetails());
		// paymentOpr.getApplicationFlags().getApplicationFlagMap().put(CommonConstant.LOGGED_USER_KEY,
		// userLogin);
		// paymentOpr.getPaymentDVO().setUserLogin(userLogin);
		//
		// try {
		// // SAVING BEFORE REDIRECTING TO PAYMENT GATEWAY
		// myLog.debug("Saving initial payment gateway data ::");
		// paymentOpr = new
		// PaymentBF().saveInitialPaymentTransaction(paymentOpr);
		//
		// // update opr with requisite values
		// // myLog.debug("calculating checkSum :: ");
		// // String checkSum = CommonUtil.getChecksum(accountId,
		// // shoppingCartOpr.getRetailOrderRecord().getOrderNumber(),
		// // amount,
		// // secretKey, shoppingCartOpr
		// // .getPaymentDVO().getIdString());
		// // myLog.debug("got checkSum as :: " + checkSum);
		// // shoppingCartOpr.getPaymentDVO().setSentCheckSum(checkSum);
		//
		// // update the payment gateway record - mthod not coded yet
		// // shoppingCartOpr = new
		// //
		// ShoppingCartBF().updatePaymentGatewayDetailsForId(shoppingCartOpr);
		// // myLog.debug("after  updatePaymentGatewaytransaction :: ");
		//
		// // refetch details for payment gateway data
		// // shoppingCartOpr = new
		// // ShoppingCartBF().getPaymentGatewayDetailsForId(shoppingCartOpr);
		// } catch (FrameworkException e) {
		// // handle framework exception
		// handleException(e, propertiesLocation);
		// myLog.error("Error saving payment transaction data ::");
		// } catch (Exception e) {
		// myLog.error("Exception occurred while generating checksum, this transaction is going to fail");
		// paymentOpr.getPaymentDVO().setSentCheckSum("");
		// }
		// }
	}

	public String getPaymentGatewayProvider() {
		return paymentGatewayProvider;
	}

	public void setPaymentGatewayProvider(String paymentGatewayProvider) {
		this.paymentGatewayProvider = paymentGatewayProvider;
	}

	public String getPaymentFormAction() {
		return paymentFormAction;
	}

	public void setPaymentFormAction(String paymentFormAction) {
		this.paymentFormAction = paymentFormAction;
	}

	public String updateOrderStatusAsPaymentInitiatedCCAvenue() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		paymentGatewayProvider = "CCAVENUE";
		// paymentOpr.getPaymentDVO().setPaymentType(paymentGatewayProvider);
		// paymentOpr.getPaymentDVO().getPaymentOption().setPaymentCode("CC AVENUE");
		// iciciPaysealMerchantId = CommonConstant.CCAVENUE_MERCHANT_ID;
		// myLog.debug("inside updateOrderStatusAsPaymentInitiatedCC ::::::" +
		// paymentOpr.getRetailOrderRecord().getId());
		// myLog.debug("inside updateOrderStatusAsPaymentInitiatedCC ::::::" +
		// paymentOpr.getPaymentDVO().getPaymentType());
		// myLog.debug("payment code got CC :::::::::::::::"
		// + paymentOpr.getPaymentDVO().getPaymentOption().getPaymentCode());
		//
		// boolean validateResult = validateOfflineSave();
		// if (validateResult) {
		//
		// try {
		// paymentOpr = new
		// PaymentBF().updateOrderStatusAsPaymentInitiated(paymentOpr);
		// // initiatePaymentTransaction();
		// initiateCCPaymentTransaction();
		// // paymentOpr = new
		// // PaymentBF().updateOrderPaymentType(paymentOpr); inserted into
		// // initiate payment
		// // transaction method
		// } catch (FrameworkException fwe) {
		// myLog.debug("insidframework exception:::");
		// } catch (BusinessException be) {
		// myLog.debug("business exception ::::");
		// }
		// }
		return null;
	}

	public void selectedPaymentGatewayType(AjaxBehaviorEvent abe) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("into selectedPaymentGatewayType :::::" + paymentCode);
		PaymentDVO paymentDvoFromCache = new PaymentDVO();
		iciciPaysealMerchantId = null;
		paymentButtonFlag = false;
		paymentOfflineButtonFlag = false;
		paymentEBSButtonFlag = true;
		paymentOpr.getPaymentDVO().getPaymentOption().setPaymentCode(paymentCode);
		// get payment options map from cache
		// myLog.debug("payment code1111" + paymentCode);
		// CacheManager cacheManager = CacheManager.create();
		// Cache globalWebSiteCache =
		// cacheManager.getCache(CommonConstant.GLOBAL_PAYMENT_OPTIONS_CACHE);
		// myLog.debug("payment code 33333" + paymentCode);
		// if (globalWebSiteCache != null) {
		// myLog.debug("got cache" + paymentCode);
		// Element globalWebSiteElement =
		// globalWebSiteCache.get(CommonConstant.GLOBAL_PAYMENT_OPTIONS_ELEMENT);
		// if (globalWebSiteElement != null) {
		// myLog.debug("got element" + paymentCode);
		// HashMap<String, PaymentDVO> pgProviderMap = (HashMap<String,
		// PaymentDVO>) globalWebSiteElement
		// .getObjectValue();
		// myLog.debug("websiteMap " + pgProviderMap);
		// if (pgProviderMap != null) {
		// myLog.debug("got pg Provider" + paymentCode);
		// // paymentDvoFromCache =
		// // pgProviderMap.get(paymentOpr.getPaymentDVO().getPaymentOption()
		// // .getPaymentCode());
		// paymentDvoFromCache = pgProviderMap.get(paymentCode);
		// if (paymentDvoFromCache != null) {
		// paymentGatewayProvider =
		// paymentDvoFromCache.getPaymentOption().getPaymentGatewayProvider();
		// iciciPaysealMerchantId =
		// paymentDvoFromCache.getPaymentOption().getCode();
		// myLog.debug("payment gateway provider ::::::::: " +
		// paymentGatewayProvider);
		// myLog.debug("Merchant Id ::::::::: " + iciciPaysealMerchantId);
		// paymentOpr.getPaymentDVO().setPaymentType(paymentDvoFromCache.getPaymentType());
		//
		// if (paymentGatewayProvider != null
		// && paymentGatewayProvider
		// .equalsIgnoreCase(CommonConstant.PAYMENT_GATEWAY_PROVIDER_CCAVENUE))
		// {
		// paymentButtonFlag = true;
		// paymentEBSButtonFlag = false;
		// } else if (paymentGatewayProvider != null
		// && paymentGatewayProvider
		// .equalsIgnoreCase(CommonConstant.PAYMENT_GATEWAY_PROVIDER_OFFLINE)) {
		// paymentOfflineButtonFlag = true;
		// paymentEBSButtonFlag = false;
		// if (iciciPaysealMerchantId != null
		// &&
		// iciciPaysealMerchantId.equalsIgnoreCase("Deposit in Bank Account")) {
		// bankDeposite = true;
		// }
		//
		// } else if (paymentGatewayProvider != null
		// && paymentGatewayProvider
		// .equalsIgnoreCase(CommonConstant.PAYMENT_GATEWAY_PROVIDER_PAYPAL)) {
		// paymentEBSButtonFlag = true;
		// }
		//
		// }
		// }
		// }
		// }
		myLog.debug("payment button flag::::::::::::" + paymentButtonFlag);
		myLog.debug("paymentOfflineButtonFlag::::::::::::" + paymentOfflineButtonFlag);
		myLog.debug("paymentEBSButtonFlag:::::::::::" + paymentEBSButtonFlag);
	}

	public String getIciciPaysealMerchantId() {
		return iciciPaysealMerchantId;
	}

	public void setIciciPaysealMerchantId(String iciciPaysealMerchantId) {
		this.iciciPaysealMerchantId = iciciPaysealMerchantId;
	}

	public boolean isPaymentButtonFlag() {
		return paymentButtonFlag;
	}

	public void setPaymentButtonFlag(boolean paymentButtonFlag) {
		this.paymentButtonFlag = paymentButtonFlag;
	}

	public boolean isPaymentOfflineButtonFlag() {
		return paymentOfflineButtonFlag;
	}

	public void setPaymentOfflineButtonFlag(boolean paymentOfflineButtonFlag) {
		this.paymentOfflineButtonFlag = paymentOfflineButtonFlag;
	}

	public void updateOfflineOrderStatus(ActionEvent e) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("inside updateOfflineOrderStatus ::::::" + paymentOpr.getRetailOrderRecord().getId());
		paymentGatewayProvider = "OFFLINE";
		paymentOpr.getPaymentDVO().setPaymentType(paymentGatewayProvider);
		paymentOpr.getPaymentDVO().getPaymentOption().setPaymentCode("COD/Cheque/Money Order");

		boolean validateResult = validateOfflineSave();
		if (validateResult) {

			myLog.debug("inside updateOfflineOrderStatus ::::::" + paymentOpr.getPaymentDVO().getPaymentType());

			submitForm = false;
			myLog.debug("payment code got :::::::::::::::"
					+ paymentOpr.getPaymentDVO().getPaymentOption().getPaymentCode());

			try {
				initiatePaymentTransaction();
				paymentOpr = new PaymentBF().updateOrderStatusAsPaymentOffline(paymentOpr);
				paymentOpr = new PaymentBF().updateOrderPaymentType(paymentOpr);

				myLog.debug("payment merchant id ::::" + paymentOpr.getPaymentDVO().getSentMerchantId());
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
						.put(CommonConstant.OFFLINE_INVOICE_OPR, paymentOpr);

			} catch (FrameworkException fwe) {
				myLog.debug("insidframework exception:::");
			} catch (BusinessException be) {
				myLog.debug("business exception ::::");
			}

			myLog.debug("paymentOfflineButtonFlag ::::::" + paymentOfflineButtonFlag);
			// return "pretty:generateOfflineInvoice";
		}

	}

	public boolean isPaymentEBSButtonFlag() {
		return paymentEBSButtonFlag;
	}

	public void setPaymentEBSButtonFlag(boolean paymentEBSButtonFlag) {
		this.paymentEBSButtonFlag = paymentEBSButtonFlag;
	}

	public void selectOfflinePay(AjaxBehaviorEvent abe) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());

		// myLog.debug("##########into selectedOfflinePaymentGatewayType :::::"
		// + activeTab);
		//
		// if (activeTab != null && (activeTab.equals("offline") ||
		// activeTab.equals("wiretransfer"))) {
		// getAllOptions();
		// iciciPaysealMerchantId = null;
		// paymentButtonFlag = false;
		// paymentOfflineButtonFlag = true;
		// paymentEBSButtonFlag = false;
		// paymentGatewayProvider = "OFFLINE";
		// paymentOpr.getPaymentDVO().setPaymentType(paymentGatewayProvider);
		// paymentOpr.getPaymentDVO().getPaymentOption().setPaymentCode("Wire Transfer");
		// } else {
		// iciciPaysealMerchantId = null;
		// paymentButtonFlag = false;
		// paymentOfflineButtonFlag = false;
		// paymentEBSButtonFlag = true;
		// }
		//
		// if (activeTab != null && activeTab.equals("emi")) {
		// emiPrice = paymentOpr.getTotalOrderPrice();
		// emiPrice = (float) (emiPrice / 3);
		// }

		// get payment options map from cache

		myLog.debug("payment button flag::::::::::::" + paymentButtonFlag);
		myLog.debug("paymentOfflineButtonFlag::::::::::::" + paymentOfflineButtonFlag);
		myLog.debug("paymentEBSButtonFlag:::::::::::" + paymentEBSButtonFlag);

	}

	public boolean isBankDeposite() {
		return bankDeposite;
	}

	public void setBankDeposite(boolean bankDeposite) {
		this.bankDeposite = bankDeposite;
	}

	public ArrayList<PaymentOptionDVO> getOfflineList() {
		if (offlineList == null) {
			offlineList = new ArrayList<PaymentOptionDVO>();
		}
		return offlineList;
	}

	public void setOfflineList(ArrayList<PaymentOptionDVO> offlineList) {
		this.offlineList = offlineList;
	}

	public ArrayList<PaymentOptionDVO> getCashPayList() {
		if (cashPayList == null) {
			cashPayList = new ArrayList<PaymentOptionDVO>();
		}
		return cashPayList;
	}

	public void setCashPayList(ArrayList<PaymentOptionDVO> cashPayList) {
		this.cashPayList = cashPayList;
	}

	public void paymentTypeCashChanged(AjaxBehaviorEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("inside paymentTypeChanged:::::::" + cashOption);

		if (cashOption) {
			for (int i = 0; i < offlineList.size(); i++) {
				offlineList.get(i).getOperationalAttributes().setRecordSelected(false);
			}

			cashPayList.get(0).getOperationalAttributes().setRecordCreated(true);

		}

	}

	public boolean isCashOption() {
		return cashOption;
	}

	public void setCashOption(boolean cashOption) {
		this.cashOption = cashOption;
	}

	public void paymentTypeCashOptionChanged(AjaxBehaviorEvent event) {

		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("inside paymentTypeCashOptionChanged:::::::");
		String payCode = null;
		int size = cashPayList.size();
		myLog.debug("inside size:::::::" + size);
		for (int i = 0; i < size; i++) {
			cashPayList.get(i).getOperationalAttributes()
					.setRecordPopulated(cashPayList.get(i).getOperationalAttributes().getRecordSelected());
			myLog.debug("inside selected:::::::" + cashPayList.get(i).getOperationalAttributes().getRecordSelected());
			if (cashPayList.get(i).getOperationalAttributes().getRecordSelected()) {
				myLog.debug("Selected option::::::" + cashPayList.get(i).getPaymentCode());
				payCode = cashPayList.get(i).getPaymentCode();
				if (payCode != null) {
					if (payCode != null && payCode.equalsIgnoreCase("Deposit in our Bank Account")) {
						cashPayList.get(i).setPaymentDescription(depositeText);
					} else if (payCode.equalsIgnoreCase("Visit our factory and pay cash")) {
						cashPayList.get(i).setPaymentDescription(visitText);
					} else if (payCode.equalsIgnoreCase("COD")) {
						cashPayList.get(i).setPaymentDescription(codText);
					}
				}
			}
		}
		paymentOpr.getPaymentDVO().getPaymentOption().setPaymentCode(payCode);

	}

	public boolean validateOfflineSave() {
		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
		FoundationValidator portalValidator = new FoundationValidator();
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("in validateOfflineSave1" + paymentOpr.getPaymentDVO().getPaymentOption().getPaymentCode());
		getErrorList().clear();

		if (!portalValidator.validateNull(paymentOpr.getPaymentDVO().getPaymentOption().getPaymentCode())) {
			myLog.debug("in validateOfflineSave2");
			addToErrorList(propertiesReader.getValueOfKey("cash_option_null"));
		}
		if (getErrorList().size() > 0) {
			myLog.debug("return false");
			return false;
		} else {
			myLog.debug("return true");
			return true;
		}

	}

	public String getPaymentCode() {
		return paymentCode;
	}

	public void setPaymentCode(String paymentCode) {
		this.paymentCode = paymentCode;
	}

	public String getWireText() {
		return wireText;
	}

	public void setWireText(String wireText) {
		this.wireText = wireText;
	}

	public Float getEmiPrice() {
		return emiPrice;
	}

	public void setEmiPrice(Float emiPrice) {
		this.emiPrice = emiPrice;
	}

	private void initiateCCPaymentTransaction() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("inside initiateCCPaymentTransaction::");
		getPaymentOpr().getPaymentDVO().setSentMerchantId("M_spatil_6789");
		paymentOpr.getPaymentDVO().getPaymentOption().setPaymentCode("CCAVENUE");
		paymentGatewayProvider = "CCAVENUE";
		paymentOpr.getPaymentDVO().setPaymentType(paymentGatewayProvider);
		myLog.debug("CURRENCY FLAG::::"
				+ paymentOpr.getApplicationFlags().getApplicationFlagMap().get(CommonConstant.CURRENCY_FLAG));
		if ((Boolean) paymentOpr.getApplicationFlags().getApplicationFlagMap().get(CommonConstant.CURRENCY_FLAG)) {

			paymentOpr.getPaymentDVO().setSentAmount(
					(paymentOpr.getOriginaltotalOrderPrice() == null ? "0" : paymentOpr.getOriginaltotalOrderPrice()
							.toString()));

		} else {
			if (paymentOpr.getApplicationFlags().getApplicationFlagMap().get(CommonConstant.CONVERTED_CURRENCY_SYMBOL)
					.equals("$")) {
				paymentOpr.getCurrencyRecord().setCurrencySymbol(
						(String) paymentOpr.getApplicationFlags().getApplicationFlagMap()
								.get(CommonConstant.CONVERTED_CURRENCY_SYMBOL));
				PaymentBF paymentBF = new PaymentBF();
				try {
					paymentOpr = paymentBF.getPaymentCurrency(paymentOpr);
				} catch (Exception e) {
					handleException(e, propertiesLocation);
				}

				paymentOpr.getPaymentDVO().setSentAmount(
						(paymentOpr.getCurrencyRecord().getPaymentMultiplier() == null ? "1.0" : String.valueOf(
								paymentOpr.getTotalOrderPriceIntValue()
										* paymentOpr.getCurrencyRecord().getPaymentMultiplier()).toString()));
			}
		}
		myLog.debug("getOriginaltotalOrderPrice::::" + paymentOpr.getOriginaltotalOrderPrice());
		myLog.debug("getTotalOrderPriceIntValue::::" + paymentOpr.getTotalOrderPriceIntValue());
		myLog.debug("setSentAmount::::" + paymentOpr.getPaymentDVO().getSentAmount());

		// paymentOpr.getPaymentDVO().setSentOrderId(String.valueOf(paymentOpr.getRetailOrderRecord().getId()));
		paymentOpr.getPaymentDVO().setSentOrderId(String.valueOf(paymentOpr.getRetailOrderRecord().getOrderNumber()));

		myLog.debug("SENT ORDER ID: " + paymentOpr.getRetailOrderRecord().getId());
		String responseUrl = "http://"
				+ ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest())
						.getServerName()
				+ ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest())
						.getContextPath() + "/payment/response";
		myLog.debug("response url :::: " + responseUrl);
		paymentOpr.getPaymentDVO().setSentRedirectUrl(responseUrl);
		try {
			String checkSum = new CCAvenueHelper().getChecksum("M_spatil_6789", String.valueOf(paymentOpr
					.getRetailOrderRecord().getOrderNumber()),
					(paymentOpr.getPaymentDVO().getSentAmount() == null ? "0" : paymentOpr.getPaymentDVO()
							.getSentAmount().toString()), responseUrl, "uwcet0cxl7ip6h1zqg48ej6yefnb06u6");
			paymentOpr.getPaymentDVO().setSentCheckSum(checkSum);
		} catch (Exception e) {
			myLog.error("Exception occurred while generating checksum, this transaction is going to fail");
			paymentOpr.getPaymentDVO().setSentCheckSum("");
		}
		paymentOpr.getPaymentDVO().setSentBillingAddress(paymentOpr.getRetailOrderRecord().getBillingDetails());
		paymentOpr.getPaymentDVO().setSentDeliveryAddress(paymentOpr.getRetailOrderRecord().getShippingDetails());
		paymentOpr.getPaymentDVO().setSentDeliveryNotes("");
		// paymentOpr.getPaymentDVO().setSentMerchantParameter(paymentOpr.getRetailOrderRecord().getOrderNumber());
		paymentOpr.getPaymentDVO().setSentMerchantParameter(String.valueOf(paymentOpr.getRetailOrderRecord().getId()));
		myLog.debug("SENT ORDER" + paymentOpr.getRetailOrderRecord().getOrderNumber());

		String userLogin = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get(CommonConstant.LOGGED_USER_KEY);
		if (userLogin == null) {
			userLogin = CommonConstant.GUEST_USER;
		}
		paymentOpr.getApplicationFlags().getApplicationFlagMap().put(CommonConstant.LOGGED_USER_KEY, userLogin);

		try {
			new PaymentBF().saveCCAvenueInitTransaction(paymentOpr);
			myLog.debug("after saving initial transaction");
		} catch (FrameworkException e) {
			// handle framework exception
			handleException(e, propertiesLocation);
		}

		try {
			Long transactionInternalId = new PaymentBF().getSavedCCAvenueTransactionPK(paymentOpr);
			paymentOpr.getPaymentDVO().setId(transactionInternalId);
			// paymentOpr.getPaymentDVO().setSentMerchantParameter(String.valueOf(transactionInternalId));
			myLog.debug("after saving initial transaction PK");
		} catch (FrameworkException e) {
			// handle framework exception
			handleException(e, propertiesLocation);
		}

	}

	public boolean isPaypalFlag() {
		return paypalFlag;
	}

	public void setPaypalFlag(boolean paypalFlag) {
		this.paypalFlag = paypalFlag;
	}

	public String paypalClicked() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("inside paypalClicked:::" + paypalFlag);
		setPaypalFlag(true);
		myLog.debug("inside paypalClicked::: after clicked::" + paypalFlag);
		return null;
	}

	// added by ketan

	public String updateOrderStatusAsPaymentInitiatedPayPal() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		paymentGatewayProvider = "PayPal";
		paymentOpr.getPaymentDVO().setPaymentType(paymentGatewayProvider);
		paymentOpr.getPaymentDVO().getPaymentOption().setPaymentCode("PayPal");
		// iciciPaysealMerchantId = CommonConstant.CCAVENUE_MERCHANT_ID;
		myLog.debug("inside updateOrderStatusAsPaymentInitiatedCC ::::::" + paymentOpr.getRetailOrderRecord().getId());
		myLog.debug("inside updateOrderStatusAsPaymentInitiatedCC ::::::" + paymentOpr.getPaymentDVO().getPaymentType());
		myLog.debug("payment code got PAyPal :::::::::::::::"
				+ paymentOpr.getPaymentDVO().getPaymentOption().getPaymentCode());

		boolean validateResult = validateOfflineSave();
		if (validateResult) {

			try {
				paymentOpr = new PaymentBF().updateOrderStatusAsPaymentInitiated(paymentOpr);
				// initiatePaymentTransaction();
				// initiateCCPaymentTransaction();
				initiatePayPalPaymentTransaction();
				// paymentOpr = new
				// PaymentBF().updateOrderPaymentType(paymentOpr); inserted into
				// initiate payment
				// transaction method
			} catch (FrameworkException fwe) {
				myLog.debug("insidframework exception:::");
			} catch (BusinessException be) {
				myLog.debug("business exception ::::");
			}
		}
		return null;
	}

	private void initiatePayPalPaymentTransaction() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		String redirectUrl = null;
		myLog.debug("inside initiateCCPaymentTransaction::");
		// getPaymentOpr().getPaymentDVO().setSentMerchantId("M_spatil_6789");
		getPaymentOpr().getPaymentDVO().getPaymentOption().setPaymentCode("PayPal");
		paymentGatewayProvider = "PayPal";
		paymentOpr.getPaymentDVO().setPaymentType(paymentGatewayProvider);
		myLog.debug("CURRENCY FLAG::::"
				+ paymentOpr.getApplicationFlags().getApplicationFlagMap().get(CommonConstant.CURRENCY_FLAG));
		if (!(Boolean) paymentOpr.getApplicationFlags().getApplicationFlagMap().get(CommonConstant.CURRENCY_FLAG)) {

			paymentOpr.getPaymentDVO().setSentAmount(
					(paymentOpr.getTotalOrderPriceIntValue() == null ? "0" : paymentOpr.getTotalOrderPriceIntValue()
							.toString()));
			paymentOpr.getPaymentDVO().setSentCurrencyCode("USD");
		}
		myLog.debug("getOriginaltotalOrderPrice::::" + paymentOpr.getOriginaltotalOrderPrice());
		myLog.debug("getTotalOrderPriceIntValue::::" + paymentOpr.getTotalOrderPriceIntValue());
		// paymentOpr.getPaymentDVO().setSentAmount(paymentOpr.getTotalOrderPriceIntValue().toString());
		myLog.debug("setSentAmount::::" + paymentOpr.getPaymentDVO().getSentAmount());

		paymentOpr.getPaymentDVO().setSentOrderId(String.valueOf(paymentOpr.getRetailOrderRecord().getId()));
		myLog.debug("SENT ORDER ID: " + paymentOpr.getRetailOrderRecord().getId());
		String returnUrl = "http://"
				+ ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest())
						.getServerName()
				+ ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest())
						.getContextPath() + "/payment/paypalresponse";
		myLog.debug("returnUrl:::: " + returnUrl);
		paymentOpr.getPaymentDVO().setSentRedirectUrl(returnUrl);
		paymentOpr.getPaymentDVO().setSentCancelUrl(returnUrl);

		paymentOpr.getPaymentDVO().setSentBillingAddress(paymentOpr.getRetailOrderRecord().getBillingDetails());
		paymentOpr.getPaymentDVO().setSentDeliveryAddress(paymentOpr.getRetailOrderRecord().getShippingDetails());
		paymentOpr.getPaymentDVO().setSentDeliveryNotes("");
		paymentOpr.getPaymentDVO().setSentMerchantParameter(paymentOpr.getRetailOrderRecord().getOrderNumber());
		myLog.debug("SENT ORDER" + paymentOpr.getRetailOrderRecord().getOrderNumber());

		String userLogin = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get(CommonConstant.LOGGED_USER_KEY);
		if (userLogin == null) {
			userLogin = CommonConstant.GUEST_USER;
		}
		paymentOpr.getApplicationFlags().getApplicationFlagMap().put(CommonConstant.LOGGED_USER_KEY, userLogin);

		// try {
		// new PaymentBF().saveCCAvenueInitTransaction(paymentOpr);
		// myLog.debug("after saving initial transaction");
		// } catch (FrameworkException e) {
		// // handle framework exception
		// handleException(e, propertiesLocation);
		// }

		// try {
		// Long transactionInternalId = new
		// PaymentBF().getSavedCCAvenueTransactionPK(paymentOpr);
		// paymentOpr.getPaymentDVO().setId(transactionInternalId);
		// //
		// paymentOpr.getPaymentDVO().setSentMerchantParameter(String.valueOf(transactionInternalId));
		// myLog.debug("after saving initial transaction PK");
		// } catch (FrameworkException e) {
		// // handle framework exception
		// handleException(e, propertiesLocation);
		// }
		paymentOpr = ECSetExpressCheckoutCode(paymentOpr);
		if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.containsKey(CommonConstant.PAYPAL_ACK)
				&& FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
						.containsKey(CommonConstant.PAYPAL_TOKEN)) {
			myLog.debug("need to save paypal token and ack and will redirect to paypal");
			String ack = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
					.get(CommonConstant.PAYPAL_ACK);
			String token = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
					.get(CommonConstant.PAYPAL_TOKEN);
			paymentOpr.getPaymentDVO().setPayPalAck(ack);
			paymentOpr.getPaymentDVO().setSentToken(token);
			redirectUrl = "https://www.paypal.com/cgi-bin/webscr?cmd=_express-checkout&token=" + token;
			// redirectUrl =
			// "https://www.sandbox.paypal.com/cgi-bin/webscr?cmd=_express-checkout&token="
			// + token;
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			try {
				new PaymentBF().savePayPalInitTransaction(paymentOpr);
				myLog.debug("after saving initial transaction for paypal");
				externalContext.redirect(redirectUrl);
			} catch (Exception e) {
				handleException(e, propertiesLocation);
			}

			// try {
			// new PaymentBF().savePayPalInitTransaction(paymentOpr);
			// myLog.debug("after saving initial transaction for paypal");
			// } catch (FrameworkException e) {
			// // handle framework exception
			// handleException(e, propertiesLocation);
			// }
		}
		// return redirectUrl;
	}

	public ShoppingCartOpr ECSetExpressCheckoutCode(ShoppingCartOpr paymentOpr) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("Inside method ECSetExpressCheckoutCode:::");
		try {
			myLog.debug("Inside method ECSetExpressCheckoutCode inside try:::");
			// PayPalAPIInterfaceServiceService service = new
			// PayPalAPIInterfaceServiceService((new StringBuilder())
			// .append(getClass().getPackage().getName().replaceAll("\\.", "/")
			// + "/sdk_config.properties").toString());
			// File file = new File("D:/sdk_config.properties");
			// File file = new
			// File(getClass().getPackage().getName().replaceAll("\\.", "/") +
			// "/sdk_config");
			// myLog.debug(" file path ::: " + file.getAbsolutePath());
			// FileInputStream fstream = new FileInputStream(file);

			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

			PayPalAPIInterfaceServiceService service = new PayPalAPIInterfaceServiceService((new StringBuilder())
					.append(externalContext.getRealPath("/")).append("/WEB-INF/sdk_config.properties").toString());
			// PayPalAPIInterfaceServiceService service = new
			// PayPalAPIInterfaceServiceService("D:/sdk_config.properties");
			myLog.debug("after readind sdk property file:::");
			SetExpressCheckoutRequestType setExpressCheckoutReq = new SetExpressCheckoutRequestType();
			SetExpressCheckoutRequestDetailsType details = new SetExpressCheckoutRequestDetailsType();

			details.setReturnURL(paymentOpr.getPaymentDVO().getSentRedirectUrl());
			details.setCancelURL(paymentOpr.getPaymentDVO().getSentRedirectUrl());
			details.setBuyerEmail(paymentOpr.getPaymentDVO().getSentBillingAddress().getEmail1());

			List payDetails = new ArrayList();
			PaymentDetailsType paydtl = new PaymentDetailsType();
			BasicAmountType itemsTotal = new BasicAmountType();
			itemsTotal.setCurrencyID(CurrencyCodeType.fromValue(paymentOpr.getPaymentDVO().getSentCurrencyCode()));
			// Double total =
			// Double.parseDouble(paymentOpr.getPaymentDVO().getSentAmount());
			myLog.debug("check in method ECSetExpressCheckoutCode for sent amt:::"
					+ paymentOpr.getPaymentDVO().getSentAmount());
			paydtl.setOrderTotal(new BasicAmountType(CurrencyCodeType.fromValue(paymentOpr.getPaymentDVO()
					.getSentCurrencyCode()), paymentOpr.getPaymentDVO().getSentAmount()));
			paydtl.setInvoiceID(paymentOpr.getPaymentDVO().getSentMerchantParameter());
			paydtl.setPaymentAction(PaymentActionCodeType.fromValue("Sale"));
			List lineItems = new ArrayList();

			PaymentDetailsItemType item = new PaymentDetailsItemType();
			BasicAmountType amt = new BasicAmountType();
			amt.setCurrencyID(CurrencyCodeType.fromValue(paymentOpr.getPaymentDVO().getSentCurrencyCode()));
			// String value = String.valueOf(paymentOpr.getTotalPriceIntValue()
			// / paymentOpr.getTotalQuantity());
			// amt.setValue(value);
			// item.setQuantity(paymentOpr.getTotalQuantity());
			item.setName("AMAR CHITRA KATHA BOOKS");
			item.setAmount(new BasicAmountType(CurrencyCodeType.fromValue(paymentOpr.getPaymentDVO()
					.getSentCurrencyCode()), paymentOpr.getPaymentDVO().getSentAmount()));
			// item.setItemCategory(ItemCategoryType.fromValue(request.getParameter("itemCategory")));
			// item.setDescription(request.getParameter("itemDescription"));
			lineItems.add(item);
			paydtl.setPaymentDetailsItem(lineItems);

			payDetails.add(paydtl);
			details.setPaymentDetails(payDetails);

			AddressType shipToAddress = new AddressType();
			shipToAddress.setName(paymentOpr.getPaymentDVO().getSentDeliveryAddress().getFirstName() + " "
					+ paymentOpr.getPaymentDVO().getSentDeliveryAddress().getLastName());

			shipToAddress.setStreet1(paymentOpr.getPaymentDVO().getSentDeliveryAddress().getAddressLine1());
			shipToAddress.setStreet2(paymentOpr.getPaymentDVO().getSentDeliveryAddress().getAddressLine2());
			shipToAddress.setCityName(paymentOpr.getPaymentDVO().getSentDeliveryAddress().getCityDvo().getCode());
			shipToAddress.setStateOrProvince(paymentOpr.getPaymentDVO().getSentDeliveryAddress().getStateDVO()
					.getCode());
			shipToAddress.setPostalCode(paymentOpr.getPaymentDVO().getSentDeliveryAddress().getPinRecord().getCode());
			myLog.debug("check country code:::"
					+ paymentOpr.getPaymentDVO().getSentDeliveryAddress().getCountryDvo().getCode());
			shipToAddress.setCountry(CountryCodeType.fromValue("US"));
			details.setAddress(shipToAddress);
			details.setNoShipping("0");

			setExpressCheckoutReq.setSetExpressCheckoutRequestDetails(details);
			SetExpressCheckoutReq expressCheckoutReq = new SetExpressCheckoutReq();
			expressCheckoutReq.setSetExpressCheckoutRequest(setExpressCheckoutReq);
			SetExpressCheckoutResponseType setExpressCheckoutResponse = service.setExpressCheckout(expressCheckoutReq);

			if (setExpressCheckoutResponse != null) {
				if (setExpressCheckoutResponse.getAck().toString().equalsIgnoreCase("SUCCESS")) {
					myLog.debug("Check the ACK" + setExpressCheckoutResponse.getAck().toString());
					myLog.debug("Check the return token" + setExpressCheckoutResponse.getToken().toString());
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
							.put(CommonConstant.PAYPAL_ACK, setExpressCheckoutResponse.getAck().toString());
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
							.put(CommonConstant.PAYPAL_TOKEN, setExpressCheckoutResponse.getToken().toString());

				} else {
					myLog.debug("check the error::::" + setExpressCheckoutResponse.getErrors().toString());
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return paymentOpr;
	}

	// added by ketan for payu
	public void updateOrderStatusAsPaymentInitiatedPayu(ActionEvent ae) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		paymentGatewayProvider = "PAYU";
		paymentOpr.getPaymentDVO().setPaymentType(paymentGatewayProvider);
		paymentOpr.getPaymentDVO().getPaymentOption().setPaymentCode("PAYU");
		// iciciPaysealMerchantId = CommonConstant.PAYU_MERCHANT_ID;
		// myLog.debug("inside updateOrderStatusAsPaymentInitiatedPayu ::::::" +
		// paymentOpr.getRetailOrderRecord().getId());
		// myLog.debug("inside updateOrderStatusAsPaymentInitiatedPayu ::::::"
		// + paymentOpr.getPaymentDVO().getPaymentOption().getPaymentName());
		// myLog.debug("payment code got updateOrderStatusAsPaymentInitiatedPayu :::::::::::::::"
		// + paymentOpr.getPaymentDVO().getPaymentOption().getPaymentCode());
		//
		// boolean validateResult = validatePayuSave();
		// if (validateResult) {
		//
		// try {
		// paymentOpr = new
		// PaymentBF().updateOrderStatusAsPaymentInitiated(paymentOpr);
		// // initiatePaymentTransaction();
		// initiatePayuTransaction();
		// // paymentOpr = new
		// // PaymentBF().updateOrderPaymentType(paymentOpr); inserted into
		// // initiate payment
		// // transaction method
		// } catch (FrameworkException fwe) {
		// myLog.debug("insidframework exception:::");
		// } catch (BusinessException be) {
		// myLog.debug("business exception ::::");
		// }
		// }

	}

	private void initiatePayuTransaction() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("inside initiatePayuTransaction::");
		// getPaymentOpr().getPaymentDVO().setSentMerchantId(CommonConstant.PAYU_MERCHANT_ID);
		// paymentOpr.getPaymentDVO().getPaymentOption().setPaymentCode("PAYU");
		// paymentGatewayProvider = "PAYU";
		// submitForm = false;
		// paymentOpr.getPaymentDVO().setPaymentType(paymentGatewayProvider);
		// myLog.debug("CURRENCY FLAG::::"
		// +
		// paymentOpr.getApplicationFlags().getApplicationFlagMap().get(CommonConstant.CURRENCY_FLAG));
		// if ((Boolean)
		// paymentOpr.getApplicationFlags().getApplicationFlagMap().get(CommonConstant.CURRENCY_FLAG))
		// {
		//
		// paymentOpr.getPaymentDVO().setSentAmount(
		// (paymentOpr.getOriginaltotalOrderPrice() == null ? "0" :
		// paymentOpr.getOriginaltotalOrderPrice()
		// .toString()));
		//
		// } else {
		// if
		// (paymentOpr.getApplicationFlags().getApplicationFlagMap().get(CommonConstant.CONVERTED_CURRENCY_SYMBOL)
		// .equals("$")) {
		// paymentOpr.getCurrencyRecord().setCurrencySymbol(
		// (String) paymentOpr.getApplicationFlags().getApplicationFlagMap()
		// .get(CommonConstant.CONVERTED_CURRENCY_SYMBOL));
		// PaymentBF paymentBF = new PaymentBF();
		// try {
		// paymentOpr = paymentBF.getPaymentCurrency(paymentOpr);
		// } catch (Exception e) {
		// }
		//
		// paymentOpr.getPaymentDVO().setSentAmount(
		// (paymentOpr.getCurrencyRecord().getPaymentMultiplier() == null ?
		// "1.0" : String.valueOf(
		// paymentOpr.getTotalOrderPriceIntValue()
		// *
		// paymentOpr.getCurrencyRecord().getPaymentMultiplier()).toString()));
		// }
		// }
		// myLog.debug("getOriginaltotalOrderPrice::::" +
		// paymentOpr.getOriginaltotalOrderPrice());
		// myLog.debug("getTotalOrderPriceIntValue::::" +
		// paymentOpr.getTotalOrderPriceIntValue());
		// myLog.debug("setSentAmount::::" +
		// paymentOpr.getPaymentDVO().getSentAmount());
		//
		// //
		// paymentOpr.getPaymentDVO().setSentOrderId(String.valueOf(paymentOpr.getRetailOrderRecord().getId()));
		// paymentOpr.getPaymentDVO().setSentOrderId(String.valueOf(paymentOpr.getRetailOrderRecord().getOrderNumber()));
		//
		// myLog.debug("SENT ORDER ID: " +
		// paymentOpr.getRetailOrderRecord().getId());
		// String responseUrl = "http://"
		// + ((HttpServletRequest)
		// FacesContext.getCurrentInstance().getExternalContext().getRequest())
		// .getServerName()
		// + ((HttpServletRequest)
		// FacesContext.getCurrentInstance().getExternalContext().getRequest())
		// .getContextPath() + "/payment/payuresponse";
		// myLog.debug("response url :::: " + responseUrl);
		// paymentOpr.getPaymentDVO().setSentRedirectUrl(responseUrl);
		//
		// String txnid = "";
		// if (paymentOpr.getPaymentDVO().getSentOrderId() == null) {
		// Random rand = new Random();
		// String rndm = Integer.toString(rand.nextInt()) +
		// (System.currentTimeMillis() / 1000L);
		// txnid = hashCal("SHA-256", rndm).substring(0, 20);
		// } else
		// txnid = paymentOpr.getPaymentDVO().getSentOrderId();
		//
		// String hashString = "";
		// // String salt = "3sf0jURk";
		// String hash = "";
		//
		// if (hashString == "") {
		// hashString = CommonConstant.PAYU_MERCHANT_ID;
		// hashString = hashString.concat("|");
		// if (txnid != null && txnid.trim().length() > 0) {
		// hashString = hashString.concat(txnid);
		// hashString = hashString.concat("|");
		// }
		// if (paymentOpr.getPaymentDVO().getSentAmount() != null) {
		// hashString =
		// hashString.concat(paymentOpr.getPaymentDVO().getSentAmount());
		// hashString = hashString.concat("|");
		// } else if (paymentOpr.getPaymentDVO().getSentAmount() == null) {
		// hashString = hashString.concat("0");
		// hashString = hashString.concat("|");
		// }
		// hashString = hashString.concat("Books");
		// hashString = hashString.concat("|");
		// if
		// (paymentOpr.getRetailOrderRecord().getBillingDetails().getFirstName()
		// != null) {
		// hashString =
		// hashString.concat(paymentOpr.getRetailOrderRecord().getBillingDetails().getFirstName());
		// hashString = hashString.concat("|");
		// }
		// if (paymentOpr.getRetailOrderRecord().getBillingDetails().getEmail()
		// != null) {
		// hashString =
		// hashString.concat(paymentOpr.getRetailOrderRecord().getBillingDetails().getEmail());
		// hashString = hashString.concat("|");
		// }
		// for (int i = 0; i < 10; i++) {
		// hashString = hashString.concat("");
		// hashString = hashString.concat("|");
		// }
		//
		// // String hashSequence =
		// //
		// "C0Dr8m|txnid|amount|productinfo|firstname|email|udf1|udf2|udf3|udf4|udf5|udf6|udf7|udf8|udf9|udf10";
		// // String[] hashVarSeq = hashSequence.split("\\|");
		// //
		// // for (String part : hashVarSeq) {
		// // hashString = (empty(part)) ? hashString.concat("") :
		// // hashString.concat(part);
		// // hashString = hashString.concat("|");
		// // }
		// // myLog.debug("hashstring before salt:::" + hashString);
		// // hashString = hashString.concat(salt);
		//
		// myLog.debug("hashstring before salt:::" + hashString);
		// hashString = hashString.concat(CommonConstant.PAYU_SALT);
		// myLog.debug("hashstring after salt:::" + hashString);
		//
		// myLog.debug("hashstring before salt:::" + hashString);
		//
		// hash = hashCal("SHA-512", hashString);
		// myLog.debug("hashstring afet encrypt:::" + hash);
		// paymentOpr.getPaymentDVO().setSentCheckSum(hash);
		//
		// // try {
		// // String checkSum = new
		// // CCAvenueHelper().getChecksum("M_spatil_6789",
		// // String.valueOf(paymentOpr
		// // .getRetailOrderRecord().getOrderNumber()),
		// // (paymentOpr.getPaymentDVO().getSentAmount() == null ? "0" :
		// // paymentOpr.getPaymentDVO()
		// // .getSentAmount().toString()), responseUrl,
		// // "spui5o0o6pttevfklfpyzjotd0gqhe49");
		// // paymentOpr.getPaymentDVO().setSentCheckSum(checkSum);
		// // } catch (Exception e) {
		// //
		// myLog.error("Exception occurred while generating checksum, this transaction is going to fail");
		// // paymentOpr.getPaymentDVO().setSentCheckSum("");
		// // }
		//
		// paymentOpr.getPaymentDVO().setSentBillingAddress(paymentOpr.getRetailOrderRecord().getBillingDetails());
		// paymentOpr.getPaymentDVO().setSentDeliveryAddress(paymentOpr.getRetailOrderRecord().getShippingDetails());
		// paymentOpr.getPaymentDVO().setSentDeliveryNotes("");
		// //
		// paymentOpr.getPaymentDVO().setSentMerchantParameter(paymentOpr.getRetailOrderRecord().getOrderNumber());
		// paymentOpr.getPaymentDVO().setSentMerchantParameter(
		// String.valueOf(paymentOpr.getRetailOrderRecord().getId()));
		// myLog.debug("SENT ORDER" +
		// paymentOpr.getRetailOrderRecord().getOrderNumber());
		//
		// String userLogin = (String)
		// FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
		// .get(CommonConstant.LOGGED_USER_KEY);
		// if (userLogin == null) {
		// userLogin = CommonConstant.GUEST_USER;
		// }
		// paymentOpr.getApplicationFlags().getApplicationFlagMap().put(CommonConstant.LOGGED_USER_KEY,
		// userLogin);
		//
		// try {
		// new PaymentBF().savePayuInitTransaction(paymentOpr);
		// myLog.debug("after saving initial transaction");
		// } catch (FrameworkException e) {
		// // handle framework exception
		// handleException(e, propertiesLocation);
		// }
		//
		// try {
		// Long transactionInternalId = new
		// PaymentBF().getSavedCCAvenueTransactionPK(paymentOpr);
		// paymentOpr.getPaymentDVO().setId(transactionInternalId);
		// //
		// paymentOpr.getPaymentDVO().setSentMerchantParameter(String.valueOf(transactionInternalId));
		// myLog.debug("after saving initial transaction PK");
		// submitForm = true;
		// } catch (FrameworkException e) {
		// // handle framework exception
		// handleException(e, propertiesLocation);
		// }
		// }
	}

	public String hashCal(String type, String str) {
		byte[] hashseq = str.getBytes();
		StringBuffer hexString = new StringBuffer();
		try {
			MessageDigest algorithm = MessageDigest.getInstance(type);
			algorithm.reset();
			algorithm.update(hashseq);
			byte messageDigest[] = algorithm.digest();

			for (int i = 0; i < messageDigest.length; i++) {
				String hex = Integer.toHexString(0xFF & messageDigest[i]);
				if (hex.length() == 1)
					hexString.append("0");
				hexString.append(hex);
			}

		} catch (NoSuchAlgorithmException nsae) {
		}
		return hexString.toString();
	}

	public boolean empty(String s) {
		if (s == null || s.trim().equals(""))
			return true;
		else
			return false;
	}

	public boolean validatePayuSave() {
		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
		FoundationValidator portalValidator = new FoundationValidator();
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("in validatePayuSave1" + paymentOpr.getPaymentDVO().getPaymentOption().getPaymentName());
		getErrorList().clear();

		if (!portalValidator.validateNull(paymentOpr.getPaymentDVO().getPaymentOption().getPaymentName())) {
			myLog.debug("in validatePayuSave2");
			addToErrorList(propertiesReader.getValueOfKey("cash_option_null"));
		}
		if (getErrorList().size() > 0) {
			myLog.debug("return false");
			return false;
		} else {
			myLog.debug("return true");
			return true;
		}

	}

	public boolean isDisplayEmi() {
		return displayEmi;
	}

	public void setDisplayEmi(boolean displayEmi) {
		this.displayEmi = displayEmi;
	}

	@Override
	public void retrieveData() {
	}

	@Override
	public void executeAddRow(ActionEvent event) {
	}

}
