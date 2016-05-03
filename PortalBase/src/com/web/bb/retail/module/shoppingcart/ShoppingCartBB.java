package com.web.bb.retail.module.shoppingcart;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;

import com.ocpsoft.pretty.PrettyContext;
import com.web.bf.retail.modules.shoppingcart.ShoppingCartBF;
import com.web.common.constants.CommonConstant;
import com.web.common.dvo.common.CountryDVO;
import com.web.common.dvo.common.StateDVO;
import com.web.common.dvo.opr.retail.ShoppingCartOpr;
import com.web.common.dvo.retail.modules.ShoppingCartProductDVO;
import com.web.common.dvo.retail.modules.user.UserDVO;
import com.web.common.dvo.systemowner.DeliveryChargesDVO;
import com.web.common.dvo.systemowner.ProductSkuStockLevelDVO;
import com.web.common.dvo.systemowner.VoucherDVO;
import com.web.common.dvo.util.OptionsDVO;
import com.web.common.parents.BackingBean;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;
import com.web.foundation.mail.MailParameters;
import com.web.foundation.mail.WebMail;
import com.web.foundation.validation.FoundationValidator;
import com.web.util.MessageFormatter;
import com.web.util.PropertiesReader;
import com.web.util.deepcopy.DeepCopy;

public class ShoppingCartBB extends BackingBean {

	private static final long serialVersionUID = -7255793601312709793L;
	private String propertiesLocation = getClass().getPackage().getName().replaceAll("\\.", "/") + "/shoppingcart";
	private Boolean shoppingCartRendered;
	private ShoppingCartOpr shoppingCartOpr;
	private ShoppingCartProductDVO productToDelete;
	private ShoppingCartProductDVO productToSave;
	private ArrayList<SelectItem> billingStates;
	private ArrayList<SelectItem> shippingStates;
	private boolean conditionAccepted;
	private String fromShoppingCartPage;
	private boolean discount;
	private boolean sameAsBilling;
	private boolean registerFlag;
	private Integer maxDeliveryTime;
	private boolean convertCurrencyFlag;
	private Float conversionRate;
	// added for number of trees
	private boolean expressDeliveryFlag;
	private DeliveryChargesDVO deliveryCharges;
	private Float expressDeliveryCharge;
	private boolean shippingCountryDisable;
	private boolean vcode = false;

	public String getFromShoppingCartPage() {
		return fromShoppingCartPage;
	}

	public void setFromShoppingCartPage(String fromShoppingCartPage) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.put(CommonConstant.FROM_SHOPPING_CART_PAGE, CommonConstant.FROM_SHOPPING_CART_PAGE);
		this.fromShoppingCartPage = fromShoppingCartPage;
	}

	public boolean isConditionAccepted() {
		return conditionAccepted;
	}

	public void setConditionAccepted(boolean conditionAccepted) {
		this.conditionAccepted = conditionAccepted;
	}

	public ArrayList<SelectItem> getBillingStates() {
		if (billingStates == null) {
			billingStates = new ArrayList<SelectItem>();
		}
		if (billingStates.size() == 0) {
			billingStates.add(new SelectItem(Long.valueOf(0), "Select Country first"));
		}
		return billingStates;
	}

	public void setBillingStates(ArrayList<SelectItem> billingStates) {
		this.billingStates = billingStates;
	}

	public ArrayList<SelectItem> getShippingStates() {
		if (shippingStates == null) {
			shippingStates = new ArrayList<SelectItem>();
		}
		if (shippingStates.size() == 0) {
			shippingStates.add(new SelectItem(Long.valueOf(0), "Select Country first"));
		}
		return shippingStates;
	}

	public void setShippingStates(ArrayList<SelectItem> shippingStates) {
		this.shippingStates = shippingStates;
	}

	@Override
	public void retrieveData() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In ShoppingCartBB :: retrieveData starts ");
		if (getAllOptions().getAllOptionsValues() == null || allOptions.getAllOptionsValues().isEmpty()) {
			try {
				allOptions.setAllOptionsValues(new ShoppingCartBF().getAllOptionsValues());
				myLog.debug("list size::" + allOptions.getAllOptionsValues().size());
			} catch (FrameworkException e) {
				handleException((Exception) e, propertiesLocation);
			} catch (BusinessException e) {
				handleException((Exception) e, propertiesLocation);
			}
		}

		if (shoppingCartOpr == null) {
			shoppingCartOpr = new ShoppingCartOpr();
		}

		if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.containsKey(CommonConstant.SHOPPING_CART_OPR)) {
			shoppingCartOpr = (ShoppingCartOpr) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
					.get(CommonConstant.SHOPPING_CART_OPR);
		}

		changeBillingCountry(null);
		changeBillingState(null);
		changeShippingCountry(null);
		changeShippingState(null);

		calculateTotalPriceOfProducts();
		getLoggedInUserData();
		calculateDeliveryTime();

	}

	public void calculateTotalPriceOfProducts() {
		if (shoppingCartOpr.getShoppingCartProductList() != null
				&& !shoppingCartOpr.getShoppingCartProductList().isEmpty()) {

			Float totalPrice = 0F;

			for (ShoppingCartProductDVO shoppingCartProductDVO : shoppingCartOpr.getShoppingCartProductList()) {
				Integer quantity = shoppingCartProductDVO.getQuantity();
				if (shoppingCartProductDVO.getProductSkuRecord().getDiscountPrice() != null
						&& shoppingCartProductDVO.getProductSkuRecord().getDiscountPrice() > 0) {
					totalPrice = quantity * shoppingCartProductDVO.getProductSkuRecord().getDiscountPrice();
				} else if (shoppingCartProductDVO.getProductSkuRecord().getBasePrice() != null
						&& shoppingCartProductDVO.getProductSkuRecord().getBasePrice() > 0) {
					totalPrice = quantity * shoppingCartProductDVO.getProductSkuRecord().getBasePrice();
				}

				totalPrice += totalPrice;
			}

			shoppingCartOpr.setTotalPrice(totalPrice);
		}
	}

	public void getLoggedInUserData() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Shopping cart BB ::getLoggedInUserData::");

		UserDVO userDVO = (UserDVO) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get(CommonConstant.LOGGED_USER_DATA);

		if (userDVO == null || userDVO.getId() == null) {
			if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
					.get(CommonConstant.LOGGED_USER_KEY) != null) {
				String userLogin = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
						.get(CommonConstant.LOGGED_USER_KEY);
				ShoppingCartOpr returnShoppingCartOpr = new ShoppingCartOpr();
				try {
					returnShoppingCartOpr = new ShoppingCartBF().getRetailUserDetails(userLogin);
				} catch (FrameworkException e) {
					handleException(e, propertiesLocation);
				} catch (BusinessException e) {
					handleException(e, propertiesLocation);
				}
				userDVO = returnShoppingCartOpr.getRetailUserDetails();
			}
		}

		if (userDVO != null && userDVO.getUserLogin() != null) {
			myLog.debug("::getLoggedInUserData:: inside if");
			shoppingCartOpr.getRetailOrderRecord().setUserRecord(userDVO);

			// copying billing details
			shoppingCartOpr.getRetailOrderRecord().getBillingDetails().setFirstName(userDVO.getFirstName());
			shoppingCartOpr.getRetailOrderRecord().getBillingDetails().setLastName(userDVO.getLastName());
			shoppingCartOpr.getRetailOrderRecord().getBillingDetails()
					.setAddressLine1(userDVO.getBillingAddressRecord().getAddressDetailsRecord().getAddressLine1());
			shoppingCartOpr.getRetailOrderRecord().getBillingDetails()
					.setAddressLine2(userDVO.getBillingAddressRecord().getAddressDetailsRecord().getAddressLine2());
			shoppingCartOpr.getRetailOrderRecord().getBillingDetails()
					.setAddressLine3(userDVO.getBillingAddressRecord().getAddressDetailsRecord().getAddressLine3());
			shoppingCartOpr.getRetailOrderRecord().getBillingDetails()
					.setPhone1(userDVO.getBillingAddressRecord().getAddressDetailsRecord().getPhone1());
			shoppingCartOpr.getRetailOrderRecord().getBillingDetails()
					.setPhone2(userDVO.getBillingAddressRecord().getAddressDetailsRecord().getPhone2());
			shoppingCartOpr.getRetailOrderRecord().getBillingDetails()
					.setEmail1(userDVO.getBillingAddressRecord().getAddressDetailsRecord().getEmail1());
			shoppingCartOpr.getRetailOrderRecord().getBillingDetails().getCountryDvo()
					.setCode(userDVO.getBillingAddressRecord().getAddressDetailsRecord().getCountryDvo().getCode());
			shoppingCartOpr.getRetailOrderRecord().getBillingDetails().getStateDVO()
					.setCode(userDVO.getBillingAddressRecord().getAddressDetailsRecord().getStateDVO().getCode());
			shoppingCartOpr.getRetailOrderRecord().getBillingDetails().getCityDvo()
					.setCode(userDVO.getBillingAddressRecord().getAddressDetailsRecord().getCityDvo().getCode());
			shoppingCartOpr.getRetailOrderRecord().getBillingDetails().getPinRecord()
					.setCode(userDVO.getBillingAddressRecord().getAddressDetailsRecord().getPinRecord().getCode());

			// copying shipping details
			shoppingCartOpr.getRetailOrderRecord().getShippingDetails().setFirstName(userDVO.getFirstName());
			shoppingCartOpr.getRetailOrderRecord().getShippingDetails().setLastName(userDVO.getLastName());
			shoppingCartOpr.getRetailOrderRecord().getShippingDetails()
					.setAddressLine1(userDVO.getShippingAddressRecord().getAddressDetailsRecord().getAddressLine1());
			shoppingCartOpr.getRetailOrderRecord().getShippingDetails()
					.setAddressLine2(userDVO.getShippingAddressRecord().getAddressDetailsRecord().getAddressLine2());
			shoppingCartOpr.getRetailOrderRecord().getShippingDetails()
					.setAddressLine3(userDVO.getShippingAddressRecord().getAddressDetailsRecord().getAddressLine3());
			shoppingCartOpr.getRetailOrderRecord().getShippingDetails()
					.setPhone1(userDVO.getShippingAddressRecord().getAddressDetailsRecord().getPhone1());
			shoppingCartOpr.getRetailOrderRecord().getShippingDetails()
					.setPhone2(userDVO.getShippingAddressRecord().getAddressDetailsRecord().getPhone2());
			shoppingCartOpr.getRetailOrderRecord().getShippingDetails()
					.setEmail1(userDVO.getShippingAddressRecord().getAddressDetailsRecord().getEmail1());
			shoppingCartOpr.getRetailOrderRecord().getShippingDetails().getCountryDvo()
					.setCode(userDVO.getShippingAddressRecord().getAddressDetailsRecord().getCountryDvo().getCode());
			shoppingCartOpr.getRetailOrderRecord().getShippingDetails().getStateDVO()
					.setCode(userDVO.getShippingAddressRecord().getAddressDetailsRecord().getStateDVO().getCode());
			shoppingCartOpr.getRetailOrderRecord().getShippingDetails().getCityDvo()
					.setCode(userDVO.getShippingAddressRecord().getAddressDetailsRecord().getCityDvo().getCode());
			shoppingCartOpr.getRetailOrderRecord().getShippingDetails().getPinRecord()
					.setCode(userDVO.getShippingAddressRecord().getAddressDetailsRecord().getPinRecord().getCode());

			myLog.debug("::inside if country code::"
					+ userDVO.getBillingAddressRecord().getAddressDetailsRecord().getCountryDvo().getCode());

			changeBillingCountry(null);
			changeBillingState(null);
			changeShippingCountry(null);
			changeShippingState(null);
		}
	}

	public void changeBillingCountry(AjaxBehaviorEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Shopping cart BB ::changeBillingCountry changeCountry::");
		CountryDVO selectedCountry = shoppingCartOpr.getRetailOrderRecord().getBillingDetails().getCountryDvo();
		myLog.debug("In Shopping cart BB ::changeBillingCountry country code::" + selectedCountry.getCode());
		try {
			ArrayList<Object> stateList = null;
			if (selectedCountry.getCode() != null) {
				stateList = new ShoppingCartBF().getStateList(selectedCountry);
			}
			allOptions.getAllOptionsValues().put("customerBillingStateList", stateList);
		} catch (FrameworkException e) {
			handleException(e, propertiesLocation);
		} catch (BusinessException e) {
			handleException(e, propertiesLocation);
		}
	}

	public void changeBillingState(AjaxBehaviorEvent event) {
		StateDVO selectedState = shoppingCartOpr.getRetailOrderRecord().getBillingDetails().getStateDVO();
		try {
			ArrayList<Object> cityList = null;
			if (selectedState.getCode() != null) {
				cityList = new ShoppingCartBF().getCityList(selectedState);
			}
			allOptions.getAllOptionsValues().put("customerBillingCityList", cityList);

		} catch (FrameworkException e) {
			handleException(e, propertiesLocation);
		} catch (BusinessException e) {
			handleException(e, propertiesLocation);
		}
	}

	public void changeShippingCountry(AjaxBehaviorEvent event) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("In Shopping cart BB ::changeCountry::");
		CountryDVO selectedCountry = shoppingCartOpr.getRetailOrderRecord().getShippingDetails().getCountryDvo();
		myLog.debug("In Shopping cart BB ::country code::" + selectedCountry.getCode());
		try {
			ArrayList<Object> stateList = null;
			if (selectedCountry.getCode() != null) {
				stateList = new ShoppingCartBF().getStateList(selectedCountry);
			}
			allOptions.getAllOptionsValues().put("customerShippingStateList", stateList);
		} catch (FrameworkException e) {
			handleException(e, propertiesLocation);
		} catch (BusinessException e) {
			handleException(e, propertiesLocation);
		}
	}

	public void changeShippingState(AjaxBehaviorEvent event) {
		StateDVO selectedState = shoppingCartOpr.getRetailOrderRecord().getShippingDetails().getStateDVO();
		try {
			ArrayList<Object> cityList = null;
			if (selectedState.getCode() != null) {
				cityList = new ShoppingCartBF().getCityList(selectedState);
			}
			allOptions.getAllOptionsValues().put("customerShippingCityList", cityList);

		} catch (FrameworkException e) {
			handleException(e, propertiesLocation);
		} catch (BusinessException e) {
			handleException(e, propertiesLocation);
		}
	}

	public String confirmOrder() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("in confirm order>>>");
		setErrorList(new ArrayList<String>());
		setSuccessMsg("");
		String navigationString = null;
		try {

			boolean validateResult = validateOrder();

			myLog.debug("in confirm order :: check register parameter :: " + registerFlag);
			if (validateResult) {
				if (registerFlag) {
					myLog.debug("check register parameter123::::" + registerFlag);
					executeRegister();
				}
				String userLogin = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
						.get(CommonConstant.LOGGED_USER_KEY);
				if (userLogin == null) {
					userLogin = CommonConstant.GUEST_USER;
				}

				shoppingCartOpr.getApplicationFlags().getApplicationFlagMap()
						.put(CommonConstant.LOGGED_USER_KEY, userLogin);

				// GEOPLUGIN setting conversion rate,currency symbol and
				// currencySymbolFlag in application flag map
				Float conversionRate = (Float) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
						.get(CommonConstant.CURRENCY_CONVERSION_RATE);
				shoppingCartOpr.getShoppingCartProductRecord().getProductSkuRecord().setConversionRate(conversionRate);
				shoppingCartOpr.getApplicationFlags().getApplicationFlagMap()
						.put(CommonConstant.CURRENCY_CONVERSION_RATE, conversionRate);

				String currencySymbol = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
						.get(CommonConstant.CONVERTED_CURRENCY_SYMBOL);
				// if
				// (FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				// .get(CommonConstant.CURRENCY_FLAG) != null
				// && (Boolean)
				// FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				// .get(CommonConstant.CURRENCY_FLAG)) {
				// currencySymbol = "Rs.";
				// }
				shoppingCartOpr.getShoppingCartProductRecord().getProductSkuRecord().getCurrencyRecord()
						.setConvertedCurrencySymbol(currencySymbol);

				shoppingCartOpr.getApplicationFlags().getApplicationFlagMap()
						.put(CommonConstant.CONVERTED_CURRENCY_SYMBOL, currencySymbol);

				Boolean currencySymbolFlag = (Boolean) FacesContext.getCurrentInstance().getExternalContext()
						.getSessionMap().get(CommonConstant.CURRENCY_FLAG);
				shoppingCartOpr.getApplicationFlags().getApplicationFlagMap()
						.put(CommonConstant.CURRENCY_FLAG, currencySymbolFlag);

				// need to do coding of country list

				// ArrayList<SelectItem> countryList =
				// getAllOptions().getAllOptionsValues().get("countryList");
				//
				// // SET BILLING COUNTRY AND STATE LABELS
				// if (countryList != null) {
				// for (int i = 0; i < countryList.size(); i++) {
				// if
				// (shoppingCartOpr.getRetailOrderRecord().getBillingDetails().getCountryDvo().getCode()
				// .equals(((SelectItem) countryList.get(i)).getValue())) {
				// shoppingCartOpr.getRetailOrderRecord().getBillingDetails().getCountry()
				// .setName(((SelectItem) countryList.get(i)).getLabel());
				// break;
				// }
				// }
				// }
				// if (billingStates != null) {
				// for (int i = 0; i < billingStates.size(); i++) {
				// if
				// ((shoppingCartOpr.getRetailOrderRecord().getBillingDetails().getState().getId()
				// != null &&
				// shoppingCartOpr
				// .getRetailOrderRecord().getBillingDetails().getState().getId()
				// !=
				// 0)
				// &&
				// shoppingCartOpr.getRetailOrderRecord().getBillingDetails().getState().getId()
				// .equals(billingStates.get(i).getValue())) {
				// shoppingCartOpr.getRetailOrderRecord().getBillingDetails().getState()
				// .setName(billingStates.get(i).getLabel());
				// break;
				// }
				// }
				// }
				//
				// // SET SHIPPING COUNTRY AND STATE LABELS
				// if (countryList != null) {
				// for (int i = 0; i < countryList.size(); i++) {
				// if
				// (shoppingCartOpr.getRetailOrderRecord().getShippingDetails().getCountryDvo().getCode()
				// .equals(((SelectItem) countryList.get(i)).getValue())) {
				// shoppingCartOpr.getRetailOrderRecord().getShippingDetails().getCountry()
				// .setName(((SelectItem) countryList.get(i)).getLabel());
				// break;
				// }
				// }
				// }
				// if (shippingStates != null) {
				// for (int i = 0; i < shippingStates.size(); i++) {
				// if
				// ((shoppingCartOpr.getRetailOrderRecord().getShippingDetails().getState().getId()
				// != null &&
				// shoppingCartOpr
				// .getRetailOrderRecord().getShippingDetails().getState().getId()
				// != 0)
				// &&
				// shoppingCartOpr.getRetailOrderRecord().getShippingDetails().getState().getId()
				// .equals(shippingStates.get(i).getValue())) {
				// shoppingCartOpr.getRetailOrderRecord().getShippingDetails().getState()
				// .setName(shippingStates.get(i).getLabel());
				// break;
				// }
				// }
				// }
				String domain = FacesContext.getCurrentInstance().getExternalContext().getRequestServerName();
				myLog.debug("Selected domain is::::::" + domain);
				int pos = domain.lastIndexOf(".");
				myLog.debug("check for the position:::::" + pos);
				String domainPrefix = domain.substring(pos + 1);
				domainPrefix = domainPrefix.toUpperCase();

				// if (domain.substring(0,
				// domain.indexOf(".")).equalsIgnoreCase("dev")) {
				// domainPrefix = domain.substring(0,
				// domain.indexOf(".")).toUpperCase();
				// }

				// comment following line while putting build on live -
				// applicable only for live site not for dev site
				// For creating order on local as ORDER-TEST-???? and on live
				// ORDER-COM/IN-????
				// domainPrefix = "TEST";

				myLog.debug("domainLast:::::" + domainPrefix);

				shoppingCartOpr.getApplicationFlags().getApplicationFlagMap()
						.put(CommonConstant.DOAMIN_KEY, domainPrefix);

				// GEOPLUGIN- for converting total price and sub total to
				// original total & subtotal price
				conversionRate = (Float) shoppingCartOpr.getApplicationFlags().getApplicationFlagMap()
						.get(CommonConstant.CURRENCY_CONVERSION_RATE);
				Float originalBasePrice = 0.0F;
				Float originalSubTotal = 0.0F;
				originalBasePrice = shoppingCartOpr.getTotalPrice();
				if (conversionRate != null) {
					originalBasePrice = originalBasePrice / conversionRate;
				}

				originalSubTotal = shoppingCartOpr.getTotalOrderPrice();
				if (conversionRate != null) {
					originalSubTotal = originalSubTotal / conversionRate;
				}
				myLog.debug("originalBasePrice In BB::" + originalBasePrice);
				myLog.debug("originalSubTotal In BB::" + originalSubTotal);
				shoppingCartOpr.setOriginaltotalPrice(originalBasePrice);
				shoppingCartOpr.setOriginaltotalOrderPrice(originalSubTotal);

				if (shoppingCartOpr.getRetailOrderRecord().getVoucherRecord().getPromoCode() != null
						&& shoppingCartOpr.getRetailOrderRecord().getVoucherRecord().getPromoCode().trim().length() > 0) {
					// if
					// (shoppingCartOpr.getRetailOrderRecord().getVoucherRecord().getPromoCode()
					// .equals("Enter a Coupon Code")) {
					// shoppingCartOpr.getRetailOrderRecord().getVoucherRecord().setPromoCode(null);
					// }

				}

				ShoppingCartOpr shoppingCartSendOpr = new ShoppingCartBF().confirmOrder(shoppingCartOpr);

				// send an email to system owner if the available level for any
				// product has failed below the reorder
				// mark
				for (int i = 0; i < shoppingCartSendOpr.getCurrentProductSkuStockLevels().size(); i++) {
					ProductSkuStockLevelDVO productStockLevelDVO = shoppingCartSendOpr
							.getCurrentProductSkuStockLevels().get(i);
					if (productStockLevelDVO.getAvailableQuantity() != null
							&& productStockLevelDVO.getReorderLevel() != null) {
						if (productStockLevelDVO.getAvailableQuantity() <= productStockLevelDVO.getReorderLevel()) {
							// send email
							PropertiesReader propertiesReaderCommon = new PropertiesReader(
									CommonConstant.MessageLocation.COMMON_MESSAGES);
							MailParameters mailParameters = new MailParameters();

							InternetAddress[] addressTo = new InternetAddress[1];
							InternetAddress[] addressCC = new InternetAddress[2];

							InternetAddress ia = new InternetAddress();
							ia.setAddress(propertiesReaderCommon.getValueOfKey("system_owner_email_id"));
							addressTo[0] = ia;
							InternetAddress iaCC = new InternetAddress();
							iaCC.setAddress(propertiesReaderCommon.getValueOfKey("system_owner_email_id_2"));
							InternetAddress iaCC1 = new InternetAddress();
							iaCC1.setAddress(propertiesReaderCommon.getValueOfKey("system_owner_email_id_3"));
							addressCC[0] = iaCC;
							addressCC[1] = iaCC1;

							mailParameters.setMailRecipients(addressTo);
							mailParameters.setMailRecipientsCC(addressCC);
							String[] messageSubjectArguments = {
									new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date()),
									productStockLevelDVO.getProductSkuRecord().getName(),
									productStockLevelDVO.getProductSkuRecord().getCode() };
							String subject = MessageFormatter.getFormattedMessage(new PropertiesReader(
									propertiesLocation).getValueOfKey("product_reorder_required_subject"),
									messageSubjectArguments);
							mailParameters.setMailSubject(subject);
							String[] messageBodyArguments = {
									productStockLevelDVO.getProductSkuRecord().getName(),
									productStockLevelDVO.getProductSkuRecord().getCode(),
									productStockLevelDVO.getReorderLevel().toString(),
									new SimpleDateFormat("dd/mmm/yyyy HH:MM").format(new Date()),
									shoppingCartOpr.getRetailOrderRecord().getOrderNumber(),
									productStockLevelDVO.getAvailableQuantity().toString(),
									productStockLevelDVO.getBlockedQuantity() == null ? "0" : productStockLevelDVO
											.getBlockedQuantity().toString(),
									// productStockLevelDVO.getShippedQuantity()
									// == null ? "0" : productStockLevelDVO
									// .getShippedQuantity().toString(),
									productStockLevelDVO.getReorderLevel().toString() };
							String mailMessage = MessageFormatter.getFormattedMessage(new PropertiesReader(
									propertiesLocation).getValueOfKey("product_reorder_required_body"),
									messageBodyArguments);
							mailParameters.setMailMessage(mailMessage);
							// mailParameters.setMailDVOObject(shoppingCartOpr);

							mailParameters.setCustomerKey(propertiesReaderCommon.getValueOfKey("customer_key"));
							mailParameters.setMailFormat(CommonConstant.MimeType.TEXT_HTML);
							// mailParameters.setRoutingKey(propertiesReaderCommon
							// .getValueOfKey("rabbitmq_email_routing_key"));
							mailParameters.setMessageQueue(propertiesReaderCommon
									.getValueOfKey("rabbitmq_email_queue_name"));

							try {
								WebMail webMail = new WebMail(mailParameters);
								webMail.sendMultipleMail();
							} catch (MessagingException e) {
								// we are in the shopping cart and the user is
								// about to make a payment
								// cant afford to let the transaction bomb even
								// if the reorder level mail doesn't go

								// throw new
								// FrameworkException("messaging_exception",
								// e.getCause());
							}

						}
					}
				}

				if (shoppingCartSendOpr.getRetailOrderRecord().getOrderNumber() != null) {
					// save sent payment data into payment gateway table
					// The method is moved to paymentBB so that ICICI Payment
					// gateway - paySeal could also be integrated
					// initiatePaymentTransaction();

					if (shoppingCartOpr.getRetailOrderRecord().getDiscountedPrice() != null
							&& shoppingCartOpr.getRetailOrderRecord().getDiscountedPrice() != Float.valueOf(0)) {
						shoppingCartOpr.getRetailOrderRecord().getVoucherRecord()
								.setVoucherDiscountValue(shoppingCartOpr.getRetailOrderRecord().getDiscountedPrice());
					}

					FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
							.put(CommonConstant.PAYMENT_OPR, shoppingCartOpr);
					// FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
					// .remove(CommonConstant.SHOPPING_CART_OPR);

					// navigate to payment page if all is well
					navigationString = "pretty:shoppingPayment";
				} else {
					addToErrorList(new PropertiesReader(propertiesLocation).getValueOfKey("order_generation_error"));
				}
			}
		} catch (FrameworkException e) {
			// handle framework exception
			handleException(e, propertiesLocation);
		} catch (BusinessException e) {
			// handle BusinessException;
			handleException(e, propertiesLocation);
		}

		return navigationString;
	}

	// not in use because method is moved to paymentBB to implement ICICI
	// payseal PG

	public boolean validateOrder() {
		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
		FoundationValidator portalValidator = new FoundationValidator();
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("in validateOrder");
		if (shoppingCartOpr.getShoppingCartProductList().size() == 0) {
			addToErrorList(propertiesReader.getValueOfKey("shopping_cart_empty"));
		}
		for (int i = 0; i < shoppingCartOpr.getShoppingCartProductList().size(); i++) {
			ProductSkuStockLevelDVO productStockLevelDVO = shoppingCartOpr.getShoppingCartProductList().get(i)
					.getProductSkuRecord().getProductSkuStockLevelRecord();
			Integer stock = productStockLevelDVO.getAvailableQuantity();
			myLog.debug("STOCK: " + stock);
			if (shoppingCartOpr.getShoppingCartProductList().get(i).getQuantity() != null && stock != null) {
				if ((shoppingCartOpr.getShoppingCartProductList().get(i).getQuantity().intValue() <= 0)
						|| (shoppingCartOpr.getShoppingCartProductList().get(i).getQuantity().intValue() > stock)) {
					String[] messageArguments = {
							shoppingCartOpr.getShoppingCartProductList().get(i).getProductSkuRecord().getName(),
							Integer.valueOf(i + 1).toString(), stock.toString() };
					addToErrorList(MessageFormatter.getFormattedMessage(
							new PropertiesReader(propertiesLocation).getValueOfKey("quantity_incorrect"),
							messageArguments));
				}
			}
		}
		if (!portalValidator.validateNull(shoppingCartOpr.getRetailOrderRecord().getBillingDetails().getFirstName())) {
			addToErrorList(propertiesReader.getValueOfKey("billing_f_name_null"));
		}
		if (!portalValidator.validateNull(shoppingCartOpr.getRetailOrderRecord().getBillingDetails().getLastName())) {
			addToErrorList(propertiesReader.getValueOfKey("billing_l_name_null"));
		}
		if (!portalValidator.validateNull(shoppingCartOpr.getRetailOrderRecord().getBillingDetails().getPhone1())) {
			addToErrorList(propertiesReader.getValueOfKey("billing_phone_number_null"));
		}
		if (!portalValidator.validateNull(shoppingCartOpr.getRetailOrderRecord().getBillingDetails().getEmail1())) {
			addToErrorList(propertiesReader.getValueOfKey("billing_email_null"));
		} else if (!portalValidator.validateEmail(shoppingCartOpr.getRetailOrderRecord().getBillingDetails()
				.getEmail1())) {
			addToErrorList(propertiesReader.getValueOfKey("billing_email_invalid"));
		}
		if (!portalValidator.validateNull(shoppingCartOpr.getRetailOrderRecord().getBillingDetails().getAddressLine1())) {
			addToErrorList(propertiesReader.getValueOfKey("billing_addr_line1_null"));
		}
		if (!portalValidator.validateNull(shoppingCartOpr.getRetailOrderRecord().getBillingDetails().getAddressLine2())) {
			addToErrorList(propertiesReader.getValueOfKey("billing_addr_line2_null"));
		}
		if (!portalValidator.validateNull(shoppingCartOpr.getRetailOrderRecord().getBillingDetails().getCityDvo()
				.getCode())) {
			addToErrorList(propertiesReader.getValueOfKey("billing_city_null"));
		}
		if (!portalValidator.validateNull(shoppingCartOpr.getRetailOrderRecord().getBillingDetails().getCountryDvo()
				.getCode())) {
			addToErrorList(propertiesReader.getValueOfKey("billing_country_null"));
		}

		// to set state as null
		if (!portalValidator.validateNull(shoppingCartOpr.getRetailOrderRecord().getBillingDetails().getStateDVO()
				.getCode())) {
			shoppingCartOpr.getRetailOrderRecord().getBillingDetails().setStateDVO(new StateDVO());
			myLog.debug("set state as new state because no state is selected:::::");
		}

		if (!portalValidator.validateNull(shoppingCartOpr.getRetailOrderRecord().getBillingDetails().getPinRecord()
				.getCode())) {
			addToErrorList(propertiesReader.getValueOfKey("billing_zipcode_null"));
		}

		if (portalValidator.validateNull(shoppingCartOpr.getRetailOrderRecord().getBillingDetails().getPinRecord()
				.getCode())
				&& !portalValidator.validateCharsAndNumbers(shoppingCartOpr.getRetailOrderRecord().getBillingDetails()
						.getPinRecord().getCode())) {
			addToErrorList(propertiesReader.getValueOfKey("billing_zipcode_invalid"));
		}

		if (!portalValidator.validateNull(shoppingCartOpr.getRetailOrderRecord().getShippingDetails().getFirstName())) {
			addToErrorList(propertiesReader.getValueOfKey("shipping_f_name_null"));
		}
		if (!portalValidator.validateNull(shoppingCartOpr.getRetailOrderRecord().getShippingDetails().getLastName())) {
			addToErrorList(propertiesReader.getValueOfKey("shipping_l_name_null"));
		}
		if (!portalValidator.validateNull(shoppingCartOpr.getRetailOrderRecord().getShippingDetails().getPhone1())) {
			addToErrorList(propertiesReader.getValueOfKey("shipping_phone_number_null"));
		}
		if (!portalValidator.validateNull(shoppingCartOpr.getRetailOrderRecord().getShippingDetails().getEmail1())) {
			addToErrorList(propertiesReader.getValueOfKey("shipping_email_null"));
		} else if (!portalValidator.validateEmail(shoppingCartOpr.getRetailOrderRecord().getShippingDetails()
				.getEmail1())) {
			addToErrorList(propertiesReader.getValueOfKey("shipping_email_invalid"));
		}
		if (!portalValidator
				.validateNull(shoppingCartOpr.getRetailOrderRecord().getShippingDetails().getAddressLine1())) {
			addToErrorList(propertiesReader.getValueOfKey("shipping_addr_line1_null"));
		}
		if (!portalValidator
				.validateNull(shoppingCartOpr.getRetailOrderRecord().getShippingDetails().getAddressLine2())) {
			addToErrorList(propertiesReader.getValueOfKey("shipping_addr_line2_null"));
		}
		if (!portalValidator.validateNull(shoppingCartOpr.getRetailOrderRecord().getShippingDetails().getCityDvo()
				.getCode())) {
			addToErrorList(propertiesReader.getValueOfKey("shipping_city_null"));
		}

		if (!portalValidator.validateNull(shoppingCartOpr.getRetailOrderRecord().getShippingDetails().getCountryDvo()
				.getCode())) {
			addToErrorList(propertiesReader.getValueOfKey("shipping_country_null"));
		}

		// to set shipping state as null
		if (!portalValidator.validateNull(shoppingCartOpr.getRetailOrderRecord().getShippingDetails().getStateDVO()
				.getCode())) {
			shoppingCartOpr.getRetailOrderRecord().getShippingDetails().setStateDVO(new StateDVO());
			myLog.debug("after setting shipping address state as new because nothing selected;;;;;");
		}

		if (!portalValidator.validateNull(shoppingCartOpr.getRetailOrderRecord().getShippingDetails().getPinRecord()
				.getCode())) {
			addToErrorList(propertiesReader.getValueOfKey("shipping_zipcode_null"));
		}
		if (portalValidator.validateNull(shoppingCartOpr.getRetailOrderRecord().getShippingDetails().getPinRecord()
				.getCode())
				&& !portalValidator.validateCharsAndNumbers(shoppingCartOpr.getRetailOrderRecord().getShippingDetails()
						.getPinRecord().getCode())) {
			addToErrorList(propertiesReader.getValueOfKey("shipping_zipcode_invalid"));
		}
		myLog.debug("condition accepted flag::" + conditionAccepted);

		if (!conditionAccepted) {
			addToErrorList(propertiesReader.getValueOfKey("terms_of_use_not_accepted"));
		} else {
			shoppingCartOpr.getRetailUserDetails().setConditionAccepted(conditionAccepted);
		}

		if (registerFlag) {
			boolean validationFlag = false;
			try {
				validationFlag = validateRegistration();
			} catch (FrameworkException e) {
				handleException((Exception) e, propertiesLocation);
			} catch (BusinessException e) {
				handleException((Exception) e, propertiesLocation);
			}

			if (!validationFlag) {
				addToErrorList(propertiesReader.getValueOfKey("user_not_available"));
			}

		}
		if (getErrorList().size() > 0) {
			return false;
		} else {
			return true;
		}
	}

	// public void copyBillingAddress(ActionEvent ae) {
	public void copyBillingAddress(AjaxBehaviorEvent ae) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("sameAsBilling flag :: " + sameAsBilling);

		// if checkbox checked then copy, else reset shipping address
		if (sameAsBilling) {

			shoppingCartOpr.getRetailOrderRecord().getShippingDetails()
					.setFirstName(shoppingCartOpr.getRetailOrderRecord().getBillingDetails().getFirstName());
			shoppingCartOpr.getRetailOrderRecord().getShippingDetails()
					.setLastName(shoppingCartOpr.getRetailOrderRecord().getBillingDetails().getLastName());
			shoppingCartOpr.getRetailOrderRecord().getShippingDetails()
					.setPhone1(shoppingCartOpr.getRetailOrderRecord().getBillingDetails().getPhone1());
			shoppingCartOpr.getRetailOrderRecord().getShippingDetails()
					.setEmail1(shoppingCartOpr.getRetailOrderRecord().getBillingDetails().getEmail1());
			shoppingCartOpr.getRetailOrderRecord().getShippingDetails()
					.setPhone2(shoppingCartOpr.getRetailOrderRecord().getBillingDetails().getPhone2());
			shoppingCartOpr.getRetailOrderRecord().getShippingDetails()
					.setEmail2(shoppingCartOpr.getRetailOrderRecord().getBillingDetails().getEmail2());
			shoppingCartOpr.getRetailOrderRecord().getShippingDetails()
					.setAddressLine1(shoppingCartOpr.getRetailOrderRecord().getBillingDetails().getAddressLine1());
			shoppingCartOpr.getRetailOrderRecord().getShippingDetails()
					.setAddressLine2(shoppingCartOpr.getRetailOrderRecord().getBillingDetails().getAddressLine2());
			shoppingCartOpr.getRetailOrderRecord().getShippingDetails().getPinRecord()
					.setCode(shoppingCartOpr.getRetailOrderRecord().getBillingDetails().getPinRecord().getCode());
			shoppingCartOpr.getRetailOrderRecord().getShippingDetails().getCountryDvo()
					.setCode(shoppingCartOpr.getRetailOrderRecord().getBillingDetails().getCountryDvo().getCode());

			myLog.debug("billing country code ::::"
					+ shoppingCartOpr.getRetailOrderRecord().getBillingDetails().getCountryDvo().getCode());

			if (shoppingCartOpr.getRetailOrderRecord().getBillingDetails().getCountryDvo().getCode() != null) {
				String stateCode = shoppingCartOpr.getRetailOrderRecord().getBillingDetails().getStateDVO().getCode();
				myLog.debug("billing state code ::::::::" + stateCode);
				if (stateCode != null) {
					changeShippingCountry(null);
					shoppingCartOpr.getRetailOrderRecord().getShippingDetails().getStateDVO().setCode(stateCode);
				}
				String cityCode = shoppingCartOpr.getRetailOrderRecord().getBillingDetails().getCityDvo().getCode();
				if (cityCode != null) {
					changeShippingState(null);
					shoppingCartOpr.getRetailOrderRecord().getShippingDetails().getCityDvo().setCode(cityCode);
				}

			}
			// populateCharges();
			if (shoppingCartOpr.getRetailOrderRecord().getShippingDetails().getCountryDvo().getCode() != null) {
				populateShippingCharges();
			}
			if (expressDeliveryFlag) {
				populateExpressCharges();
			}
		}

	}

	public ShoppingCartProductDVO getProductToDelete() {
		if (productToDelete == null) {
			productToDelete = new ShoppingCartProductDVO();
		}
		return productToDelete;
	}

	public void setProductToDelete(ShoppingCartProductDVO productToDelete) {
		this.productToDelete = productToDelete;
	}

	public ShoppingCartProductDVO getProductToSave() {
		if (productToSave == null) {
			productToSave = new ShoppingCartProductDVO();
		}
		return productToSave;
	}

	public void setProductToSave(ShoppingCartProductDVO productToSave) {
		this.productToSave = productToSave;
	}

	// public ProductOpr getProductImageRecord() {
	// if (productImageRecord == null) {
	// productImageRecord = new ProductOpr();
	// }
	// return productImageRecord;
	// }
	//
	// public void setProductImageRecord(ProductOpr productImageRecord) {
	// this.productImageRecord = productImageRecord;
	// }

	public void enlargeImageClicked() {
		FacesContext
				.getCurrentInstance()
				.getExternalContext()
				.getRequestMap()
				.put(CommonConstant.IMAGE_DVO,
						shoppingCartOpr.getShoppingCartProductRecord().getProductSkuRecord().getImageRecord());
	}

	public ShoppingCartOpr getShoppingCartOpr() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		if (shoppingCartOpr == null) {
			shoppingCartOpr = new ShoppingCartOpr();
		}

		if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.containsKey(CommonConstant.SHOPPING_CART_OPR)) {
			shoppingCartOpr = (ShoppingCartOpr) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
					.get(CommonConstant.SHOPPING_CART_OPR);
		}

		if (shoppingCartOpr.getShoppingCartProductList().size() > 0 && (!vcode)) {
			shoppingCartOpr.getRetailOrderRecord().getVoucherRecord().setPromoCode(null);
			vcode = true;
			recalculateTotalPriceBasedOnAction();
		}

		// TODO Auto-generated method stub
		// whish list logic needs to change

		if (shoppingCartOpr.getApplicationFlags().getApplicationFlagMap().containsKey(CommonConstant.FROM_WISHLIST)
				&& shoppingCartOpr.getApplicationFlags().getApplicationFlagMap().get(CommonConstant.FROM_WISHLIST) != null) {
			// recalculate();
			// myLog.debug("Inside wishlist shopping cart opr");

			// code added by santlal to get level 1-4 categories and
			// their respective delivery times
			ShoppingCartOpr shoppingCartFromWishlistRecords = new ShoppingCartOpr();
			for (int i = 0; i < shoppingCartOpr.getShoppingCartProductList().size(); i++) {
				myLog.debug("Inside wishlist for loop");
				if (shoppingCartOpr.getShoppingCartProductList().get(i).getApplicationFlags().getApplicationFlagMap()
						.containsKey(CommonConstant.WISHLIST_SHOPPING_CART_FLAG)
						&& shoppingCartOpr.getShoppingCartProductList().get(i).getApplicationFlags()
								.getApplicationFlagMap().get(CommonConstant.WISHLIST_SHOPPING_CART_FLAG) != null
						&& (Boolean) shoppingCartOpr.getShoppingCartProductList().get(i).getApplicationFlags()
								.getApplicationFlagMap().get(CommonConstant.WISHLIST_SHOPPING_CART_FLAG)) {
					myLog.debug("Inside wishlist for loop if");
					shoppingCartFromWishlistRecords.getShoppingCartProductList().add(
							shoppingCartOpr.getShoppingCartProductList().get(i));
				}
			}

			// calling BF
			try {
				shoppingCartFromWishlistRecords = new ShoppingCartBF()
						.getCategoryWiseDeliveryTime(shoppingCartFromWishlistRecords);

				Long productId = 0L;
				Long productSkuId = 0L;
				Integer leadTimeLevel1 = 0;
				Integer leadTimeLevel2 = 0;
				Integer leadTimeLevel3 = 0;
				Integer leadTimeLevel4 = 0;

				for (int i = 0; i < shoppingCartFromWishlistRecords.getShoppingCartProductList().size(); i++) {
					productId = shoppingCartFromWishlistRecords.getShoppingCartProductList().get(i)
							.getProductSkuRecord().getProductRecord().getId();
					productSkuId = shoppingCartFromWishlistRecords.getShoppingCartProductList().get(i)
							.getProductSkuRecord().getId();
					myLog.debug("product Id==" + productId);
					myLog.debug("sku Id==" + productSkuId);
					leadTimeLevel1 = shoppingCartFromWishlistRecords.getShoppingCartProductList().get(i)
							.getProductSkuRecord().getHierarchyCategoryMappingRecord().getCategoryLevelOneRecord()
							.getDeliveryTime();
					myLog.debug("leadTimeLevel1   " + leadTimeLevel1);
					leadTimeLevel2 = shoppingCartFromWishlistRecords.getShoppingCartProductList().get(i)
							.getProductSkuRecord().getHierarchyCategoryMappingRecord().getCategoryLevelTwoRecord()
							.getDeliveryTime();
					myLog.debug("leadTimeLevel2   " + leadTimeLevel2);
					leadTimeLevel3 = shoppingCartFromWishlistRecords.getShoppingCartProductList().get(i)
							.getProductSkuRecord().getHierarchyCategoryMappingRecord().getCategoryLevelThreeRecord()
							.getDeliveryTime();
					myLog.debug("leadTimeLevel3   " + leadTimeLevel3);
					leadTimeLevel4 = shoppingCartFromWishlistRecords.getShoppingCartProductList().get(i)
							.getProductSkuRecord().getHierarchyCategoryMappingRecord().getCategoryLevelFourRecord()
							.getDeliveryTime();
					myLog.debug("leadTimeLevel4   " + leadTimeLevel4);

					for (int j = 0; j < shoppingCartOpr.getShoppingCartProductList().size(); j++) {
						if ((productId != null
								&& productSkuId != null
								&& shoppingCartOpr.getShoppingCartProductList().get(j).getProductSkuRecord().getId() != null && shoppingCartOpr
								.getShoppingCartProductList().get(j).getProductSkuRecord().getProductRecord().getId() != null)
								&& (productId.equals(shoppingCartOpr.getShoppingCartProductList().get(j)
										.getProductSkuRecord().getProductRecord().getId()) && productSkuId
										.equals(shoppingCartOpr.getShoppingCartProductList().get(j)
												.getProductSkuRecord().getId()))) {
							shoppingCartOpr.getShoppingCartProductList().get(j).getProductSkuRecord()
									.getHierarchyCategoryMappingRecord().getCategoryLevelOneRecord()
									.setDeliveryTime(leadTimeLevel1);
							shoppingCartOpr.getShoppingCartProductList().get(j).getProductSkuRecord()
									.getHierarchyCategoryMappingRecord().getCategoryLevelTwoRecord()
									.setDeliveryTime(leadTimeLevel2);
							shoppingCartOpr.getShoppingCartProductList().get(j).getProductSkuRecord()
									.getHierarchyCategoryMappingRecord().getCategoryLevelThreeRecord()
									.setDeliveryTime(leadTimeLevel3);
							shoppingCartOpr.getShoppingCartProductList().get(j).getProductSkuRecord()
									.getHierarchyCategoryMappingRecord().getCategoryLevelFourRecord()
									.setDeliveryTime(leadTimeLevel4);
						}
					}
				}

			} catch (FrameworkException e) {
				// handle framework exception
				handleException(e, propertiesLocation);
			} catch (BusinessException e) {
				// handle BusinessException;
				handleException(e, propertiesLocation);
			}

			calculateDeliveryTime();
			shoppingCartOpr.getApplicationFlags().getApplicationFlagMap().remove(CommonConstant.FROM_WISHLIST);
		}

		// myLog.debug("===countryForAutoSuggestBilling 1======" +
		// countryForAutoSuggestBilling);
		// if (countryForAutoSuggestBilling == null) {
		// // countryForAutoSuggestBilling =
		// //
		// shoppingCartOpr.getRetailOrderRecord().getBillingDetails().getCountry()
		// // .getName();
		// countryForAutoSuggestBilling = CommonConstant.DEFAULT_COUNTRY;
		// executeAutoSuggestSearchBilling(countryForAutoSuggestBilling);
		// }
		//
		// if (billingStates == null || billingStates.isEmpty()) {
		// getBillingDependentStates();
		// }
		// if (countryForAutoSuggestShipping == null) {
		// // countryForAutoSuggestShipping =
		// //
		// shoppingCartOpr.getRetailOrderRecord().getShippingDetails().getCountry()
		// // .getName();
		// countryForAutoSuggestShipping = CommonConstant.DEFAULT_COUNTRY;
		// executeAutoSuggestSearchShipping(countryForAutoSuggestShipping);
		// }
		// if (shippingStates == null || shippingStates.isEmpty()) {
		// getShippingDependentData();
		// }

		// TODO Auto-generated method stub

		// why this calls are there need to check

		// fetch billing states data if there is something in the shopping cart
		// if (shoppingCartOpr.getShoppingCartProductList().size() > 0
		// &&
		// shoppingCartOpr.getRetailOrderRecord().getBillingDetails().getCountryDvo().getCode()
		// != null) {
		// if (billingStates == null || billingStates.isEmpty()) {
		// getBillingDependentStates();
		//
		// }
		//
		// }
		// fetch shipping states data if there is something in the shopping cart
		// if (shoppingCartOpr.getShoppingCartProductList().size() > 0
		// &&
		// shoppingCartOpr.getRetailOrderRecord().getShippingDetails().getCountryDvo().getCode()
		// != null) {
		// if (shippingStates == null || shippingStates.isEmpty()) {
		// getShippingDependentData();
		// }
		// }

		// myLog.debug("refresh saved products from application flag map:::::"
		// +
		// FacesContext.getCurrentInstance().getExternalContext().getApplicationMap()
		// .get(CommonConstant.REFRESH_SAVED_PRODUCTS));

		// if (savedItemsShoppingCartOpr == null) {
		// getSavedItemsShoppingCartOpr();
		// }
		// if
		// (!savedItemsShoppingCartOpr.getShoppingCartProductRecord().getOperationalAttributes().getRecordPopulated())
		// {
		// myLog.debug("going to database to fetch saved items");
		// ShoppingCartProductDVO shoppingCartProductDVO = new
		// ShoppingCartProductDVO();
		// shoppingCartProductDVO.getUserRecord().setUserLogin(
		// (String)
		// FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
		// .get(CommonConstant.LOGGED_USER_KEY));
		// ShoppingCartOpr getSavedProductsOpr = new ShoppingCartOpr();
		// getSavedProductsOpr.setShoppingCartProductRecord(shoppingCartProductDVO);
		// try {
		// myLog.debug("savedItemsShoppingCartOpr :: making BF call");
		// savedItemsShoppingCartOpr = new
		// ShoppingCartBF().getSavedProducts(getSavedProductsOpr);
		// savedItemsShoppingCartOpr.getShoppingCartProductRecord().getOperationalAttributes()
		// .setRecordPopulated(true);
		// } catch (FrameworkException e) {
		// // handle framework exception
		// handleException(e, propertiesLocation);
		// } catch (BusinessException e) {
		// // handle framework exception
		// handleException(e, propertiesLocation);
		// }
		// }

		// written by santlal - for setting currency conversion flag based on
		// country
		if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.containsKey(CommonConstant.CURRENCY_FLAG)
				&& FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
						.get(CommonConstant.CURRENCY_FLAG) != null) {
			convertCurrencyFlag = (Boolean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
					.get(CommonConstant.CURRENCY_FLAG);
		}
		shoppingCartOpr.getShoppingCartProductRecord().getOperationalAttributes().setRecordPopulated(true);

		return shoppingCartOpr;
	}

	public void setShoppingCartOpr(ShoppingCartOpr shoppingCartOpr) {
		this.shoppingCartOpr = shoppingCartOpr;
	}

	public Boolean getShoppingCartRendered() {
		if ((getShoppingCartOpr() != null) && (shoppingCartOpr.getShoppingCartProductList().size() > 0)) {
			shoppingCartRendered = true;
		} else {
			shoppingCartRendered = false;
		}
		return shoppingCartRendered;
	}

	public void setShoppingCartRendered(Boolean shoppingCartRendered) {
		this.shoppingCartRendered = shoppingCartRendered;
	}

	public void removeProduct() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("productToDelete.hashcode() " + productToDelete.hashCode());
		if (productToDelete.getProductSkuRecord().getId() != null) {
			for (int i = 0; i < shoppingCartOpr.getShoppingCartProductList().size(); i++) {
				ShoppingCartProductDVO shoppingCartProductDVO = shoppingCartOpr.getShoppingCartProductList().get(i);

				if (productToDelete.getProductSkuRecord().getId()
						.equals(shoppingCartProductDVO.getProductSkuRecord().getId())) {
					shoppingCartOpr.getShoppingCartProductList().remove(shoppingCartProductDVO);
					break;
				}
			}

			if (!shoppingCartOpr.getShoppingCartProductList().isEmpty()) {
				recalculateTotalPriceBasedOnAction();
				calculateDeliveryTime();
				// development of populate shipping charges based on country
				// remains
				populateShippingCharges();
			}

		}
	}

	public void saveProductForLater() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		// TEMPLATE TO CALL SAVE BF METHOD FROM BB ver 1.1
		setErrorList(new ArrayList<String>());
		setSuccessMsg("");

		try {
			ShoppingCartOpr shoppingCartSaveOpr = new ShoppingCartOpr();
			shoppingCartSaveOpr.setShoppingCartProductRecord(productToSave);

			shoppingCartSaveOpr.getRetailUserDetails().setUserLogin(
					(String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
							.get(CommonConstant.LOGGED_USER_KEY));

			new ShoppingCartBF().saveProductForLater(shoppingCartSaveOpr);
			String[] messageArguments = { productToSave.getProductSkuRecord().getName() };
			setSuccessMsg(MessageFormatter.getFormattedMessage(
					new PropertiesReader(propertiesLocation).getValueOfKey("save_success"), messageArguments));
			myLog.debug("productToSave.hashcode() " + productToSave.hashCode());
			for (int i = 0; i < shoppingCartOpr.getShoppingCartProductList().size(); i++) {
				if (productToSave.hashCode() == shoppingCartOpr.getShoppingCartProductList().get(i).hashCode()) {
					myLog.debug("shoppingCartOpr.getShoppingCartProductList().get(i).hashCode() "
							+ shoppingCartOpr.getShoppingCartProductList().get(i).hashCode());
					shoppingCartOpr.getShoppingCartProductList().remove(i);
				}
			}
		} catch (FrameworkException e) {
			// handle framework exception
			handleException(e, propertiesLocation);
		} catch (BusinessException e) {
			// handle business exception
			handleException(e, propertiesLocation);
		}
		expressDeliveryFlag = false;
		// savedItemsShoppingCartOpr.getShoppingCartProductRecord().getOperationalAttributes().setRecordPopulated(false);
		recalculateTotalPriceBasedOnAction();
		calculateDeliveryTime();
		// populateCharges();
		if (shoppingCartOpr.getRetailOrderRecord().getShippingDetails().getCountryDvo().getCode() != null) {
			populateShippingCharges();
		}

		// return null;
	}

	// public void moveToShoppingCart() {
	// try {
	// ShoppingCartOpr shoppingCartSaveOpr = new ShoppingCartOpr();
	// shoppingCartSaveOpr.setShoppingCartProductRecord(productToMoveToCart);
	// new ShoppingCartBF().deleteProductFromSavedList(shoppingCartSaveOpr);
	// } catch (FrameworkException e) {
	// // handle framework exception
	// handleException(e, propertiesLocation);
	// }
	//
	// ShoppingCartOpr shoppingCartOpr = (ShoppingCartOpr)
	// FacesContext.getCurrentInstance().getExternalContext()
	// .getSessionMap().get(CommonConstant.SHOPPING_CART_OPR);
	// if (shoppingCartOpr == null) {
	// shoppingCartOpr = new ShoppingCartOpr();
	// }
	//
	// shoppingCartOpr.getShoppingCartProductList().add(productToMoveToCart);
	// FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
	// .put(CommonConstant.SHOPPING_CART_OPR, shoppingCartOpr);
	//
	// String[] messageArguments = {
	// productToMoveToCart.getProductRecord().getName() };
	// setSuccessMsg(MessageFormatter.getFormattedMessage(
	// new
	// PropertiesReader(propertiesLocation).getValueOfKey("product_added_to_cart"),
	// messageArguments));
	//
	// // for (int i = 0; i <
	// // savedItemsShoppingCartOpr.getShoppingCartProductList().size(); i++) {
	// // if (productToMoveToCart.hashCode() ==
	// // savedItemsShoppingCartOpr.getShoppingCartProductList().get(i)
	// // .hashCode()) {
	// // savedItemsShoppingCartOpr.getShoppingCartProductList().remove(i);
	// // }
	// // }
	//
	// // FacesContext.getCurrentInstance().getExternalContext().getRequestMap()
	// // .put(CommonConstant.REFRESH_SAVED_PRODUCTS,
	// // CommonConstant.REFRESH_SAVED_PRODUCTS);
	// // return null;
	// }

	public String populateOtherCharges() {
		// populateCharges();
		if (expressDeliveryFlag) {
			populateExpressCharges();
		}
		return null;
	}

	private void populateCharges() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("Country id for getting charges "
				+ shoppingCartOpr.getRetailOrderRecord().getShippingDetails().getCountryDvo().getCode());
		if (shoppingCartOpr.getRetailOrderRecord().getShippingDetails().getCountryDvo().getCode() != null) {
			// TEMPLATE TO CALL SAVE BF METHOD FROM BB ver 1.1
			setErrorList(new ArrayList<String>());
			setSuccessMsg("");

			try {
				myLog.debug("Country Id in BB"
						+ shoppingCartOpr.getRetailOrderRecord().getShippingDetails().getCountryDvo().getCode());
				ShoppingCartOpr returnShoppingCartOpr = new ShoppingCartBF().populateOtherCharges(shoppingCartOpr);

				// GEOPLUGIN- converting delivery charges according to currency
				shoppingCartOpr
						.getRetailOrderRecord()
						.getDeliveryChargesRecord()
						.setOriginalDeliveryCharge(
								returnShoppingCartOpr.getRetailOrderRecord().getDeliveryChargesRecord()
										.getDeliveryCharge());
				myLog.debug("Original Delivery charges"
						+ shoppingCartOpr.getRetailOrderRecord().getDeliveryChargesRecord().getOriginalDeliveryCharge());
				shoppingCartOpr
						.getRetailOrderRecord()
						.getDeliveryChargesRecord()
						.setDeliveryCharge(
								(Float) convertCurrency(returnShoppingCartOpr.getRetailOrderRecord()
										.getDeliveryChargesRecord().getDeliveryCharge()));
				if (shoppingCartOpr.getNewDiscountedPrice() >= 200) {
					shoppingCartOpr.getRetailOrderRecord().getDeliveryChargesRecord().setDeliveryCharge(0.0f);
				}
				// shoppingCartOpr
				// .getRetailOrderRecord()
				// .getDeliveryTimeRecord()
				// .setLeadTimeInDays(
				// returnShoppingCartOpr.getRetailOrderRecord().getDeliveryTimeRecord()
				// .getLeadTimeInDays());
				// shoppingCartOpr
				// .getRetailOrderRecord()
				// .getDeliveryChargesRecord()
				// .setDeliveryCharge(
				// returnShoppingCartOpr.getRetailOrderRecord().getDeliveryChargesRecord()
				// .getDeliveryCharge());

				// convertedProcessingCharges(shoppingCartOpr);
				myLog.debug("delivery charges "
						+ returnShoppingCartOpr.getRetailOrderRecord().getDeliveryChargesRecord().getDeliveryCharge());

				boolean allProductsAvailable = true;

				for (int i = 0; i < shoppingCartOpr.getShoppingCartProductList().size(); i++) {
					myLog.debug("shoppingCartOpr.getShoppingCartProductList().get(i).getProductRecord().isInStock() "
							+ shoppingCartOpr.getShoppingCartProductList().get(i).getProductSkuRecord().isInStock());
					if (!(shoppingCartOpr.getShoppingCartProductList().get(i).getProductSkuRecord().isInStock())) {
						myLog.debug("setting allProductsAvailable to false");
						allProductsAvailable = false;
					}
				}
				// if (allProductsAvailable) {
				// shoppingCartOpr
				// .getRetailOrderRecord()
				// .getDeliveryTimeRecord()
				// .setLeadTimeInDays(
				// returnShoppingCartOpr.getRetailOrderRecord().getDeliveryTimeRecord()
				// .getLeadTimeInDays());
				// myLog.debug("all products are available . Setting lead time to "
				// +
				// shoppingCartOpr.getRetailOrderRecord().getDeliveryTimeRecord().getLeadTimeInDays());
				// } else {
				// shoppingCartOpr
				// .getRetailOrderRecord()
				// .getDeliveryTimeRecord()
				// .setLeadTimeInDays(
				// returnShoppingCartOpr.getRetailOrderRecord().getDeliveryTimeRecord()
				// .getLeadTimeInDays());
				// myLog.debug("all products are not available . Setting lead time to "
				// +
				// shoppingCartOpr.getRetailOrderRecord().getDeliveryTimeRecord().getLeadTimeInDays());
				// }
				//
				// myLog.debug("delivery time "
				// +
				// returnShoppingCartOpr.getRetailOrderRecord().getDeliveryTimeRecord().getLeadTimeInDays());
			} catch (BusinessException e) {
				// handle framework exception

				handleException(e, propertiesLocation);
			} catch (FrameworkException e) {
				// handle framework exception
				handleException(e, propertiesLocation);
			}
		}
	}

	@Override
	public OptionsDVO getAllOptions() {
		if (allOptions == null) {
			allOptions = new OptionsDVO();
		}

		return allOptions;
	}

	@Override
	public void setAllOptions(OptionsDVO allOptions) {
	}

	@Override
	public void editDetails() {
	}

	@Override
	public void executeSave(ActionEvent event) {
	}

	@Override
	public void executeSearch(ActionEvent event) {
	}

	@Override
	public boolean validateSave() {
		return false;
	}

	@Override
	public boolean validateSearch() {
		return false;
	}

	// this is a wrapper method for getShippingDependentData() to call as
	// listener
	public void getShippingDependentDataListener(AjaxBehaviorEvent abe) {
		shoppingCartOpr.getRetailOrderRecord().getShippingDetails().setStateDVO(new StateDVO());
		getShippingDependentData();
	}

	public void getShippingDependentData() {
		// TEMPLATE TO CALL SEARCH BF METHOD FROM BB ver 1.2
		// TODO Auto-generated method stub
		// need to improve code
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		ShoppingCartBF shoppingCartBF = new ShoppingCartBF();

		myLog.debug("inside getShippingDependentStates ::: "
				+ shoppingCartOpr.getRetailOrderRecord().getShippingDetails().getCountryDvo().getCode());

		// if
		// (shoppingCartOpr.getRetailOrderRecord().getShippingDetails().getCountryDvo().getCode()
		// != null) {
		//
		// OptionsDVO optionsDVO = new OptionsDVO();
		// optionsDVO.setCountryDVO(shoppingCartOpr.getRetailOrderRecord().getShippingDetails().getCountryDvo());
		// optionsDVO.getApplicationFlags().getApplicationFlagMap().put(CommonConstant.COUNTRY_OPTIONS_KEY,
		// true);
		// optionsDVO.getApplicationFlags().getApplicationFlagMap()
		// .put(CommonConstant.COUNTRY_SHIPPING_OPTIONS_KEY, true);
		// optionsDVO.getApplicationFlags().getApplicationFlagMap()
		// .put(CommonConstant.SHIPPING_STATES_ONLY,
		// CommonConstant.SHIPPING_STATES_ONLY);
		//
		// try {
		// //
		// myLog.debug("b4 bf call for getShippingDependentStates ::getAllOptions()");
		// allOptions = shoppingCartBF.getAllOptions(optionsDVO);
		// //
		// myLog.debug("after bf call for getShippingDependentStates ::getAllOptions()");
		// shippingStates =
		// allOptions.getAllOptionsValues().get(CommonConstant.STATE_LIST);
		// } catch (FrameworkException e) {
		// // handle framework exception
		// handleException(e, propertiesLocation);
		// } catch (BusinessException e) {
		// // handle BusinessException;
		// handleException(e, propertiesLocation);
		// }
		// } else {
		// myLog.error("in getShippingDependentData :: country id is null");
		// }
		//
		// // whenever shipping data is required, recalculate charges
		// // populateCharges();
		// if
		// (shoppingCartOpr.getRetailOrderRecord().getShippingDetails().getCountryDvo().getCode()
		// != null) {
		// populateShippingCharges();
		// }
		// if (expressDeliveryFlag) {
		// populateExpressCharges();
		// }

	}

	public void validateQuantity(ShoppingCartProductDVO shoppingCartProductDVO) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		ArrayList<ShoppingCartProductDVO> gotShoppingCartProductDVOList = shoppingCartOpr.getShoppingCartProductList();
		myLog.debug("inside validateQuantity");
		boolean quantityValid = true;
		Integer currentRowIndex = (Integer) UIComponent.getCurrentComponent(FacesContext.getCurrentInstance())
				.getAttributes().get("currentRowIndex");

		if (shoppingCartProductDVO.getQuantity() <= 0) {
			String[] messageArguments = { shoppingCartProductDVO.getProductSkuRecord().getName(),
					Integer.valueOf(currentRowIndex + 1).toString() };
			addToErrorList(MessageFormatter.getFormattedMessage(
					new PropertiesReader(propertiesLocation).getValueOfKey("quantity_incorrect"), messageArguments));
			quantityValid = false;
		}

		// TODO Auto-generated method stub

		if (quantityValid) {
			myLog.debug("asdfasdfasdfasdfasdfasfquantityvalid now calling recalculate ");
			expressDeliveryFlag = false;
			recalculateTotalPriceBasedOnAction();
			// populateCharges();
			if (shoppingCartOpr.getRetailOrderRecord().getShippingDetails().getCountryDvo().getCode() != null) {
				populateShippingCharges();
			}

		}
	}

	public boolean validatePromoCode() {
		PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);
		FoundationValidator portalValidator = new FoundationValidator();
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());

		boolean validationFlag = true;
		myLog.debug("in Validate Save=================");

		if (!portalValidator.validateNull(shoppingCartOpr.getRetailOrderRecord().getVoucherRecord().getPromoCode())) {
			addToErrorList(propertiesReader.getValueOfKey("promo_code_null"));
		}

		if (getErrorList().size() > 0) {
			validationFlag = false;
		} else {
			validationFlag = true;
		}

		return validationFlag;
	}

	public boolean isDiscount() {
		if (!shoppingCartOpr.getRetailOrderRecord().getVoucherRecord().getVoucherDiscountValue().equals((float) 0.0)) {
			discount = true;
		} else {
			discount = false;
		}
		return discount;
	}

	public void setDiscount(boolean discount) {
		this.discount = discount;
	}

	public void populateWebAttributes() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		String pageName = null;
		pageName = PrettyContext.getCurrentInstance().getRequestURL().toString();
		myLog.debug("pageName " + pageName);
		populateWebResourceAttributes(pageName);
	}

	public boolean isSameAsBilling() {
		return sameAsBilling;
	}

	public void setSameAsBilling(boolean sameAsBilling) {
		this.sameAsBilling = sameAsBilling;
	}

	public boolean isRegisterFlag() {
		return registerFlag;
	}

	public void setRegisterFlag(boolean registerFlag) {
		this.registerFlag = registerFlag;
	}

	public String executeRegister() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		String domain = FacesContext.getCurrentInstance().getExternalContext().getRequestServerName();
		myLog.debug("Domain is:::::::" + domain);
		shoppingCartOpr.getApplicationFlags().getApplicationFlagMap().put(CommonConstant.WEBSITE_URL, domain);
		boolean validationFlag = false;

		setErrorList(new ArrayList<String>());
		setSuccessMsg("");

		try {

			validationFlag = validateRegistration();

		} catch (FrameworkException e) {
			handleException((Exception) e, propertiesLocation);
		} catch (BusinessException e) {
			handleException((Exception) e, propertiesLocation);
		}

		if (validationFlag) {
			try {
				shoppingCartOpr = new ShoppingCartBF().executeRegister(shoppingCartOpr);
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
						.put(CommonConstant.LOGGED_USER_KEY, shoppingCartOpr.getRetailUserDetails().getUserLogin());

				// TODO Auto-generated method stub
				// why user roles need to check

				// FacesContext
				// .getCurrentInstance()
				// .getExternalContext()
				// .getSessionMap()
				// .put(CommonConstant.LOGGED_USER_ROLES,
				// shoppingCartOpr.getApplicationFlags().getApplicationFlagMap()
				// .get(CommonConstant.LOGGED_USER_ROLES));

				// shoppingCartOpr.getApplicationFlags().getApplicationFlagMap().remove(CommonConstant.LOGGED_USER_ROLES);
				FacesContext
						.getCurrentInstance()
						.getExternalContext()
						.getSessionMap()
						.put(CommonConstant.LOGGED_USER_DATA,
								shoppingCartOpr.getRetailOrderRecord().getBillingDetails().getFirstName() + " "
										+ shoppingCartOpr.getRetailOrderRecord().getBillingDetails().getLastName());

				FacesContext
						.getCurrentInstance()
						.getExternalContext()
						.getSessionMap()
						.put(CommonConstant.RERENDER_COMPONENT,
								"welcomeDisplayName,topLinksForm,systemownerMainDisplayPanel,shoppingCartForm,saveProduct,addToCartLink");
			} catch (FrameworkException e) {
				// handle framework exception
				handleException((Exception) e, propertiesLocation);
			} catch (BusinessException e) {
				// handle BusinessException;
				handleException((Exception) e, propertiesLocation);
			}

			// send sms

			// need to implement later

			// try {
			// // get URL content
			// String a =
			// "http://www.unicel.in/SendSMS/sendmsg.php?uname=ACKMTR&pass=j4d3j4h7&send=ACKMDA&dest="
			// +
			// shoppingCartOpr.getRetailOrderRecord().getBillingDetails().getPhone1()
			// +
			// "&msg=We%20are%20pleased%20to%20confirm%20your%20registration%20with%20www.amarchitrakatha.com.%20Your%20login%20ID%20is%20"
			// + shoppingCartOpr.getRetailUserDetails().getUserLogin();
			// // String a =
			// //
			// "http://www.unicel.in/SendSMS/sendmsg.php?uname=ACKMTR&pass=j4d3j4h7&send=ACKMDA&dest=919821627099&msg=We%20are%20pleased%20to%20confirm%20your%20registration%20with%20www.amarchitrakatha.com.%20Your%20login%20ID%20is%20ketan.";
			// // String a =
			// //
			// "http://www.unicel.in/SendSMS/sendmsg.php?uname=ACKMTR&pass=j4d3j4h7&send=ACKMDA&dest=919821627099&msg=Dear%20Customer,%20Your%20order%20ACKTEST-12345%20has%20been%20cancelled.%20Please%20call%201234567890%20customer%20service%20for%20further%20queries.";
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
			// shoppingCartOpr.getSmsGateWayRecord().setSmsResponseCode(inputLine);
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
			//
			// myLog.debug("after send sms to user save sms response code ");
			//
			// shoppingCartOpr.getSmsGateWayRecord().setOrderNumber(null);
			// shoppingCartOpr.getSmsGateWayRecord().setDestinationNumber(
			// shoppingCartOpr.getRetailOrderRecord().getBillingDetails().getPhoneNumber());
			// String loggedUser = (String)
			// externalContext.getSessionMap().get(CommonConstant.LOGGED_USER_KEY);
			// shoppingCartOpr.getSmsGateWayRecord().getAuditAttributes().setModifiedBy(loggedUser);
			//
			// try {
			// shoppingCartOpr = new
			// ShoppingCartBF().saveSmsGateWayResponse(shoppingCartOpr);
			// } catch (FrameworkException e) {
			//
			// e.printStackTrace();
			// } catch (BusinessException e) {
			//
			// e.printStackTrace();
			// }

		}

		return null;

	}

	public boolean validateRegistration() throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		FoundationValidator portalValidator = new FoundationValidator();
		ShoppingCartOpr shoppingRegistrationCartOpr = new ShoppingCartOpr();
		boolean flag = false;

		try {
			shoppingCartOpr.getRetailUserDetails().setUserLogin(
					shoppingCartOpr.getRetailOrderRecord().getBillingDetails().getEmail1());

			myLog.debug("check for the value##############" + shoppingCartOpr.getRetailUserDetails().getUserLogin());

			shoppingRegistrationCartOpr = new ShoppingCartBF().checkUserAvailability(shoppingCartOpr);
			if (portalValidator.validateNull(shoppingRegistrationCartOpr.getRetailUserDetails().getUserLogin())) {
				myLog.debug("inside if:::::::" + shoppingRegistrationCartOpr.getRetailUserDetails().getUserLogin());
			} else {
				flag = true;
			}
		} catch (FrameworkException e) {
			handleException((Exception) e, propertiesLocation);
		} catch (BusinessException e) {
			handleException((Exception) e, propertiesLocation);
		}

		return flag;
	}

	public void recalculate(ActionEvent event) {
		recalculate();
	}

	public String recalculate() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		setErrorList(new ArrayList<String>());
		setSuccessMsg("");
		boolean validateResult = validatePromoCode();
		myLog.debug("inside recalculate ::::::");

		if (validateResult) {
			setErrorList(new ArrayList<String>());
			PropertiesReader propertiesReader = new PropertiesReader(propertiesLocation);

			ShoppingCartOpr returnShoppingCartOpr = new ShoppingCartOpr();
			myLog.debug("inside if block ::::::");

			try {
				myLog.debug("B4 BF call===============Promo Code code"
						+ shoppingCartOpr.getRetailOrderRecord().getVoucherRecord().getPromoCode());

				// populateCharges();
				if (shoppingCartOpr.getRetailOrderRecord().getShippingDetails().getCountryDvo().getCode() != null) {
					populateShippingCharges();
				}
				myLog.debug("Country Id===============Promo Code code"
						+ shoppingCartOpr.getRetailOrderRecord().getShippingDetails().getCountryDvo().getCode());
				shoppingCartOpr.getRetailOrderRecord().getShippingDetails().getCountryDvo()
						.setCode(shoppingCartOpr.getRetailOrderRecord().getShippingDetails().getCountryDvo().getCode());
				returnShoppingCartOpr = new ShoppingCartBF().recalculate(shoppingCartOpr);
				myLog.debug("check for invoice amount::::"
						+ returnShoppingCartOpr.getRetailOrderRecord().getVoucherRecord().getInvoiceAmount());
				// added by ketan for per product starts

				for (int i = 0; i < shoppingCartOpr.getShoppingCartProductList().size(); i++) {
					shoppingCartOpr.getShoppingCartProductList().get(i).getProductSkuRecord()
							.setGiftVoucherDiscountApplicable(false);
				}

				shoppingCartOpr = (ShoppingCartOpr) new ShoppingCartBF().getMappedProdutToPromoCode(shoppingCartOpr);

				for (int i = 0; i < shoppingCartOpr.getShoppingCartProductList().size(); i++) {
					myLog.debug("chehck for true and flase:::"
							+ shoppingCartOpr.getShoppingCartProductList().get(i).getProductSkuRecord()
									.isGiftVoucherDiscountApplicable());
				}

				// added by ketan for per product ends

				// to seperate out totalDiscounted Price and undescounted price
				Float totalDiscountedProductPrice = 0.0F;
				Float totalUnDiscountedProductPrice = 0.0F;
				for (int i = 0; i < shoppingCartOpr.getShoppingCartProductList().size(); i++) {
					if (shoppingCartOpr.getShoppingCartProductList().get(i).getProductSkuRecord()
							.isGiftVoucherDiscountApplicable()) {
						totalDiscountedProductPrice += shoppingCartOpr.getShoppingCartProductList().get(i)
								.getSubTotal();
					} else {
						totalUnDiscountedProductPrice += shoppingCartOpr.getShoppingCartProductList().get(i)
								.getSubTotal();
					}
				}
				myLog.debug("total discounted price::::::" + totalDiscountedProductPrice);
				myLog.debug("total un discounted price::::::" + totalUnDiscountedProductPrice);
				if (totalDiscountedProductPrice == 0.0F) {

					addToErrorList(propertiesReader.getValueOfKey("product_issue"));
					shoppingCartOpr.getRetailOrderRecord().setVoucherRecord(new VoucherDVO());
					shoppingCartOpr.getRetailOrderRecord().setDiscountedPrice(totalDiscountedProductPrice);
					myLog.debug("check for diacounted amount::::"
							+ shoppingCartOpr.getRetailOrderRecord().getDiscountedPrice());
					return null;

				}

				if ((returnShoppingCartOpr.getRetailOrderRecord().getVoucherRecord().getVoucherValueInAbsolute() != null && !returnShoppingCartOpr
						.getRetailOrderRecord().getVoucherRecord().getVoucherValueInAbsolute().equals(Float.valueOf(0)))
						|| (returnShoppingCartOpr.getRetailOrderRecord().getVoucherRecord().getVoucherValueInPercent() != null && !returnShoppingCartOpr
								.getRetailOrderRecord().getVoucherRecord().getVoucherValueInPercent()
								.equals(Float.valueOf(0)))) {

					if (returnShoppingCartOpr.getRetailOrderRecord().getVoucherRecord().getVoucherValueInAbsolute() != null
							&& !returnShoppingCartOpr.getRetailOrderRecord().getVoucherRecord()
									.getVoucherValueInAbsolute().equals(Float.valueOf(0))) {
						if (totalDiscountedProductPrice < returnShoppingCartOpr.getRetailOrderRecord()
								.getVoucherRecord().getVoucherValueInAbsolute()) {
							addToErrorList(propertiesReader.getValueOfKey("discount_more_than_total"));
							shoppingCartOpr.getRetailOrderRecord().setVoucherRecord(new VoucherDVO());
							return null;
						}

						if (returnShoppingCartOpr.getRetailOrderRecord().getVoucherRecord().getInvoiceAmount() != null
								&& returnShoppingCartOpr.getRetailOrderRecord().getVoucherRecord().getInvoiceAmount() > 0.0) {
							myLog.debug("check for total invoice amount of propmo::::");
							if (shoppingCartOpr.getTotalPrice() < returnShoppingCartOpr.getRetailOrderRecord()
									.getVoucherRecord().getInvoiceAmount()) {
								addToErrorList(propertiesReader.getValueOfKey("unsufficient_total_price"));
								shoppingCartOpr.getRetailOrderRecord().setVoucherRecord(new VoucherDVO());
								return null;
							}
						}

						shoppingCartOpr.getRetailOrderRecord().setDiscountedPrice(
								(Float) convertCurrency(returnShoppingCartOpr.getRetailOrderRecord().getVoucherRecord()
										.getVoucherValueInAbsolute()));

						shoppingCartOpr
								.getRetailOrderRecord()
								.getVoucherRecord()
								.setVoucherDiscountValue(
										(Float) convertCurrency(returnShoppingCartOpr.getRetailOrderRecord()
												.getVoucherRecord().getVoucherValueInAbsolute()));
						shoppingCartOpr
								.getRetailOrderRecord()
								.getVoucherRecord()
								.setVoucherValueInAbsolute(
										(Float) convertCurrency(returnShoppingCartOpr.getRetailOrderRecord()
												.getVoucherRecord().getVoucherValueInAbsolute()));

					}
					if (returnShoppingCartOpr.getRetailOrderRecord().getVoucherRecord().getVoucherValueInPercent() != null
							&& !returnShoppingCartOpr.getRetailOrderRecord().getVoucherRecord()
									.getVoucherValueInPercent().equals(Float.valueOf(0))) {
						myLog.debug("percentage voucher::::"
								+ returnShoppingCartOpr.getRetailOrderRecord().getVoucherRecord().getInvoiceAmount());

						if (returnShoppingCartOpr.getRetailOrderRecord().getVoucherRecord().getInvoiceAmount() != null
								&& returnShoppingCartOpr.getRetailOrderRecord().getVoucherRecord().getInvoiceAmount() > 0.0) {
							myLog.debug("check for total invoice amount of propmo::::");
							if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
									.get(CommonConstant.CONVERTED_CURRENCY_SYMBOL).equals("$")) {
								if (shoppingCartOpr.getTotalPrice() < convertCurrency(returnShoppingCartOpr
										.getRetailOrderRecord().getVoucherRecord().getInvoiceAmount())) {
									addToErrorList(propertiesReader.getValueOfKey("unsufficient_total_price"));
									shoppingCartOpr.getRetailOrderRecord().setVoucherRecord(new VoucherDVO());
									return null;
								}

							} else {
								if (shoppingCartOpr.getTotalPrice() < returnShoppingCartOpr.getRetailOrderRecord()
										.getVoucherRecord().getInvoiceAmount()) {
									addToErrorList(propertiesReader.getValueOfKey("unsufficient_total_price"));
									shoppingCartOpr.getRetailOrderRecord().setVoucherRecord(new VoucherDVO());
									return null;
								}
							}
						}

						shoppingCartOpr
								.getRetailOrderRecord()
								.getVoucherRecord()
								.setVoucherDiscountValue(
										returnShoppingCartOpr.getRetailOrderRecord().getVoucherRecord()
												.getVoucherValueInPercent());

						shoppingCartOpr
								.getRetailOrderRecord()
								.getVoucherRecord()
								.setVoucherValueInPercent(
										returnShoppingCartOpr.getRetailOrderRecord().getVoucherRecord()
												.getVoucherValueInPercent());

						shoppingCartOpr.getRetailOrderRecord().setDiscountedPrice(
								totalDiscountedProductPrice
										* (returnShoppingCartOpr.getRetailOrderRecord().getVoucherRecord()
												.getVoucherValueInPercent() / 100));

						myLog.debug("check for diacounted amount::::"
								+ shoppingCartOpr.getRetailOrderRecord().getDiscountedPrice());
						if (returnShoppingCartOpr.getRetailOrderRecord().getVoucherRecord().getMaxDiscount() > 0.0) {
							if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
									.get(CommonConstant.CONVERTED_CURRENCY_SYMBOL).equals("$")) {
								if (convertCurrency(returnShoppingCartOpr.getRetailOrderRecord().getVoucherRecord()
										.getMaxDiscount()) < shoppingCartOpr.getRetailOrderRecord()
										.getDiscountedPrice()) {
									shoppingCartOpr.getRetailOrderRecord().setDiscountedPrice(
											convertCurrency(returnShoppingCartOpr.getRetailOrderRecord()
													.getVoucherRecord().getMaxDiscount()));
								}

							} else {
								if (returnShoppingCartOpr.getRetailOrderRecord().getVoucherRecord().getMaxDiscount() < shoppingCartOpr
										.getRetailOrderRecord().getDiscountedPrice()) {
									shoppingCartOpr.getRetailOrderRecord().setDiscountedPrice(
											returnShoppingCartOpr.getRetailOrderRecord().getVoucherRecord()
													.getMaxDiscount());
								}
							}
						}

					}

					myLog.debug("===Get Country id in bf==="
							+ shoppingCartOpr.getRetailOrderRecord().getShippingDetails().getCountryDvo().getCode());

					setSuccessMsg(propertiesReader.getValueOfKey("discount_applied"));
				} else {
					setSuccessMsg(propertiesReader.getValueOfKey("invalid_code"));
				}

			} catch (FrameworkException e) {
				// handle framework exception
				handleException(e, propertiesLocation);
			} catch (BusinessException e) {
				// handle BusinessException;
				shoppingCartOpr.getRetailOrderRecord().getVoucherRecord().setPromoCode(null);
				addToErrorList(propertiesReader.getValueOfKey("invalid_code"));
				shoppingCartOpr.getRetailOrderRecord().getVoucherRecord().setVoucherDiscountValue((float) 0.0);
				recalculateTotalPriceBasedOnAction();
				// handleException(e, propertiesLocation);
			}
		} else {
			shoppingCartOpr.getRetailOrderRecord().setDiscountedPrice(0.0F);
			shoppingCartOpr.getRetailOrderRecord().getVoucherRecord().setVoucherValueInAbsolute(0.0F);
			shoppingCartOpr.getRetailOrderRecord().getVoucherRecord().setVoucherValueInPercent(0.0F);
			shoppingCartOpr.getRetailOrderRecord().getVoucherRecord().setVoucherDiscountValue(0.0F);
		}
		if (expressDeliveryFlag) {
			populateExpressCharges();
		}
		return null;
	}

	// WRAPPER TO CALL THE PRIVATE METHOD WHEN THE APPLY DISCOUNT IS CLICKED
	public void recalculateTotalPriceBasedOnAction(ActionEvent abe) {
		validatePromoCode();
		if (getErrorList().size() == 0) {
			recalculateTotalPriceBasedOnAction();
		}
	}

	public void recalculateTotalPriceBasedOnAction(AjaxBehaviorEvent abe) {
		validatePromoCode();
		recalculateTotalPriceBasedOnAction();
		// if (getErrorList().size() == 0) {
		// recalculateTotalPriceBasedOnAction();
		// }
	}

	private void recalculateTotalPriceBasedOnAction() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		if (!(shoppingCartOpr.getRetailOrderRecord().getVoucherRecord().getPromoCode() == null || shoppingCartOpr
				.getRetailOrderRecord().getVoucherRecord().getPromoCode().trim().length() == 0)) {
			myLog.debug("recalculating");
			recalculate();
		} else {
			// reset all discount values and recalculate
			shoppingCartOpr.getRetailOrderRecord().getVoucherRecord().setVoucherValueInAbsolute(0.0f);
			shoppingCartOpr.getRetailOrderRecord().getVoucherRecord().setVoucherDiscountValue(0.0f);
			shoppingCartOpr.getRetailOrderRecord().getVoucherRecord().setVoucherValueInPercent(0.0f);
			shoppingCartOpr.getRetailOrderRecord().setDiscountedPrice(0.0f);
			// set the new price
			shoppingCartOpr.setNewDiscountedPrice(shoppingCartOpr.getTotalPrice());
			myLog.debug("shoppingCartOpr.getTotalOrderPrice() " + shoppingCartOpr.getTotalOrderPrice());
		}
	}

	public void calculateDeliveryTime() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("inside shopping cart bb :::::calculateDeliveryTime ");
		ShoppingCartOpr shoppingCartOprForDeliveryTime = new ShoppingCartOpr();

		if (shoppingCartOpr == null) {
			shoppingCartOprForDeliveryTime = (ShoppingCartOpr) FacesContext.getCurrentInstance().getExternalContext()
					.getSessionMap().get(CommonConstant.SHOPPING_CART_OPR);
		} else if (shoppingCartOpr != null) {
			shoppingCartOprForDeliveryTime = (ShoppingCartOpr) DeepCopy.copy(shoppingCartOpr);
		}

		if (shoppingCartOprForDeliveryTime != null
				&& !shoppingCartOprForDeliveryTime.getShoppingCartProductList().isEmpty()) {

			maxDeliveryTime = 0;
			for (int i = 0; i < shoppingCartOprForDeliveryTime.getShoppingCartProductList().size(); i++) {
				if (shoppingCartOprForDeliveryTime.getShoppingCartProductList().get(i).getProductSkuRecord()
						.getHierarchyCategoryMappingRecord().getCategoryLevelOneRecord().getDeliveryTime() != null) {
					if (maxDeliveryTime < shoppingCartOprForDeliveryTime.getShoppingCartProductList().get(i)
							.getProductSkuRecord().getHierarchyCategoryMappingRecord().getCategoryLevelOneRecord()
							.getDeliveryTime()) {
						maxDeliveryTime = shoppingCartOprForDeliveryTime.getShoppingCartProductList().get(i)
								.getProductSkuRecord().getHierarchyCategoryMappingRecord().getCategoryLevelOneRecord()
								.getDeliveryTime();
						myLog.debug("Level 1::" + maxDeliveryTime);
					}
				}
				if (shoppingCartOprForDeliveryTime.getShoppingCartProductList().get(i).getProductSkuRecord()
						.getHierarchyCategoryMappingRecord().getCategoryLevelTwoRecord().getDeliveryTime() != null) {
					if (maxDeliveryTime < shoppingCartOprForDeliveryTime.getShoppingCartProductList().get(i)
							.getProductSkuRecord().getHierarchyCategoryMappingRecord().getCategoryLevelTwoRecord()
							.getDeliveryTime()) {
						maxDeliveryTime = shoppingCartOprForDeliveryTime.getShoppingCartProductList().get(i)
								.getProductSkuRecord().getHierarchyCategoryMappingRecord().getCategoryLevelTwoRecord()
								.getDeliveryTime();
						myLog.debug("Level 2::" + maxDeliveryTime);
					}
				}
				if (shoppingCartOprForDeliveryTime.getShoppingCartProductList().get(i).getProductSkuRecord()
						.getHierarchyCategoryMappingRecord().getCategoryLevelThreeRecord().getDeliveryTime() != null) {
					if (maxDeliveryTime < shoppingCartOprForDeliveryTime.getShoppingCartProductList().get(i)
							.getProductSkuRecord().getHierarchyCategoryMappingRecord().getCategoryLevelThreeRecord()
							.getDeliveryTime()) {
						maxDeliveryTime = shoppingCartOprForDeliveryTime.getShoppingCartProductList().get(i)
								.getProductSkuRecord().getHierarchyCategoryMappingRecord()
								.getCategoryLevelThreeRecord().getDeliveryTime();
						myLog.debug("Level 3::" + maxDeliveryTime);
					}
				}
				if (shoppingCartOprForDeliveryTime.getShoppingCartProductList().get(i).getProductSkuRecord()
						.getHierarchyCategoryMappingRecord().getCategoryLevelFourRecord().getDeliveryTime() != null) {
					if (maxDeliveryTime < shoppingCartOprForDeliveryTime.getShoppingCartProductList().get(i)
							.getProductSkuRecord().getHierarchyCategoryMappingRecord().getCategoryLevelFourRecord()
							.getDeliveryTime()) {
						maxDeliveryTime = shoppingCartOprForDeliveryTime.getShoppingCartProductList().get(i)
								.getProductSkuRecord().getHierarchyCategoryMappingRecord().getCategoryLevelFourRecord()
								.getDeliveryTime();
						myLog.debug("Level 4::" + maxDeliveryTime);
					}
				}
			}

			myLog.debug("inside shopping cart bb :::::calculateDeliveryTime max : " + maxDeliveryTime);

			shoppingCartOpr.getRetailOrderRecord().getDeliveryTimeRecord().setLeadTimeInDays(maxDeliveryTime);
			myLog.debug("leadTime=="
					+ shoppingCartOpr.getRetailOrderRecord().getDeliveryTimeRecord().getLeadTimeInDays());

			FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
					.put(CommonConstant.SHOPPING_CART_OPR, shoppingCartOpr);
		}
	}

	public void recalculateDiscount() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		getShoppingCartOpr();
		setErrorList(new ArrayList<String>());
		PropertiesReader propertiesReader = new PropertiesReader();
		propertiesReader.setResourceBundle(propertiesLocation, Locale.getDefault());
		ShoppingCartOpr returnShoppingCartOpr = new ShoppingCartOpr();

		if (!(shoppingCartOpr.getRetailOrderRecord().getVoucherRecord().getPromoCode() == null || shoppingCartOpr
				.getRetailOrderRecord().getVoucherRecord().getPromoCode().trim().length() == 0)) {
			if (shoppingCartOpr != null && shoppingCartOpr.getShoppingCartProductList() != null
					&& !shoppingCartOpr.getShoppingCartProductList().isEmpty()) {
				boolean giftVoucherApplicableFlag = false;

				for (int i = 0; i < shoppingCartOpr.getShoppingCartProductList().size(); i++) {
					myLog.debug("shoppingCartProductList flag:::::::::::::::::"
							+ shoppingCartOpr.getShoppingCartProductList().get(i).getProductSkuRecord()
									.isGiftVoucherDiscountApplicable());
					if (shoppingCartOpr.getShoppingCartProductList().get(i).getProductSkuRecord()
							.isGiftVoucherDiscountApplicable()) {
						myLog.debug("got product for which gift voucher is applicable ::::::::");
						giftVoucherApplicableFlag = true;
						break;
					}
				}

				if (giftVoucherApplicableFlag) {
					myLog.debug("inside if block ::::::");

					try {
						returnShoppingCartOpr = (ShoppingCartOpr) new ShoppingCartBF().recalculate(shoppingCartOpr);

						// to seperate out totalDiscounted Price and
						// undescounted price
						Float totalDiscountedProductPrice = 0.0F;
						Float totalUnDiscountedProductPrice = 0.0F;
						for (int i = 0; i < shoppingCartOpr.getShoppingCartProductList().size(); i++) {
							if (shoppingCartOpr.getShoppingCartProductList().get(i).getProductSkuRecord()
									.isGiftVoucherDiscountApplicable()) {
								totalDiscountedProductPrice += shoppingCartOpr.getShoppingCartProductList().get(i)
										.getSubTotal();
							} else {
								totalUnDiscountedProductPrice += shoppingCartOpr.getShoppingCartProductList().get(i)
										.getSubTotal();
							}
						}
						myLog.debug("total discounted price::::::" + totalDiscountedProductPrice);
						myLog.debug("total un discounted price::::::" + totalUnDiscountedProductPrice);

						if (returnShoppingCartOpr.getRetailOrderRecord().getVoucherRecord().getVoucherValueInAbsolute() != null
								&& !returnShoppingCartOpr.getRetailOrderRecord().getVoucherRecord()
										.getVoucherValueInAbsolute().equals(Float.valueOf(0))) {
							if (totalDiscountedProductPrice < returnShoppingCartOpr.getRetailOrderRecord()
									.getVoucherRecord().getVoucherValueInAbsolute()) {
								addToErrorList(propertiesReader.getValueOfKey("discount_more_than_total"));
								shoppingCartOpr.getRetailOrderRecord().setVoucherRecord(new VoucherDVO());
								return;
							}

							shoppingCartOpr.getRetailOrderRecord().setDiscountedPrice(
									returnShoppingCartOpr.getRetailOrderRecord().getVoucherRecord()
											.getVoucherValueInAbsolute());

							shoppingCartOpr
									.getRetailOrderRecord()
									.getVoucherRecord()
									.setVoucherDiscountValue(
											returnShoppingCartOpr.getRetailOrderRecord().getVoucherRecord()
													.getVoucherValueInAbsolute());

							shoppingCartOpr
									.getRetailOrderRecord()
									.getVoucherRecord()
									.setVoucherValueInAbsolute(
											returnShoppingCartOpr.getRetailOrderRecord().getVoucherRecord()
													.getVoucherValueInAbsolute());
						}
						if (returnShoppingCartOpr.getRetailOrderRecord().getVoucherRecord().getVoucherValueInPercent() != null
								&& !returnShoppingCartOpr.getRetailOrderRecord().getVoucherRecord()
										.getVoucherValueInPercent().equals(Float.valueOf(0))) {
							myLog.debug("value in percent ::::::::::::::::::::::::: greater ");
							shoppingCartOpr
									.getRetailOrderRecord()
									.getVoucherRecord()
									.setVoucherDiscountValue(
											returnShoppingCartOpr.getRetailOrderRecord().getVoucherRecord()
													.getVoucherValueInPercent());

							shoppingCartOpr
									.getRetailOrderRecord()
									.getVoucherRecord()
									.setVoucherValueInPercent(
											returnShoppingCartOpr.getRetailOrderRecord().getVoucherRecord()
													.getVoucherValueInPercent());

							shoppingCartOpr.getRetailOrderRecord().setDiscountedPrice(
									totalDiscountedProductPrice
											* (returnShoppingCartOpr.getRetailOrderRecord().getVoucherRecord()
													.getVoucherValueInPercent() / 100));

						}

						// setSuccessMsg(propertiesReader.getValueOfKey("discount_applied"));

					} catch (FrameworkException e) {
						handleException(e, propertiesLocation);
					} catch (BusinessException e) {
						handleException(e, propertiesLocation);
					}
				}
			}
		}
	}

	private Float convertCurrency(Float originalValue) {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("Inside currencyBasedConversion()::");
		Float convertedValue = originalValue;
		myLog.debug("originalValue==" + originalValue);
		conversionRate = (Float) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.get(CommonConstant.CURRENCY_CONVERSION_RATE);
		myLog.debug("Conversion Rate==" + conversionRate);
		if (conversionRate != null && conversionRate > 0) {
			convertedValue = convertedValue * conversionRate;
		}

		myLog.debug("convertedValue==" + convertedValue);
		return convertedValue;
	}

	public boolean isConvertCurrencyFlag() {
		return convertCurrencyFlag;
	}

	public void setConvertCurrencyFlag(boolean convertCurrencyFlag) {
		this.convertCurrencyFlag = convertCurrencyFlag;
	}

	public boolean isExpressDeliveryFlag() {
		return expressDeliveryFlag;
	}

	public void setExpressDeliveryFlag(boolean expressDeliveryFlag) {
		this.expressDeliveryFlag = expressDeliveryFlag;
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("expressDeliveryFlag::" + expressDeliveryFlag);
		if (expressDeliveryFlag) {
			myLog.debug("inside TRUE expressDeliveryFlag::" + expressDeliveryFlag);
			if (shoppingCartOpr.getTotalPrice() < 1001 || shoppingCartOpr.getTotalPrice() > 3000) {
				addToErrorList(new PropertiesReader(propertiesLocation)
						.getValueOfKey("invalid_order_price_for_express_delivery"));
				this.expressDeliveryFlag = false;
				return;
			}
			populateExpressCharges();
			this.expressDeliveryCharge = shoppingCartOpr.getRetailOrderRecord().getDeliveryChargesRecord()
					.getExpressCharge();
			myLog.debug("expressDeliveryCharge====" + expressDeliveryCharge);
		} else {
			shoppingCartOpr.getRetailOrderRecord().getDeliveryChargesRecord().setExpressCharge(0.0f);
		}
	}

	public Float getExpressDeliveryCharge() {
		return expressDeliveryCharge;
	}

	public void setExpressDeliveryCharge(Float expressDeliveryCharge) {
		this.expressDeliveryCharge = expressDeliveryCharge;
	}

	public DeliveryChargesDVO getDeliveryCharges() {
		if (deliveryCharges == null) {
			deliveryCharges = new DeliveryChargesDVO();
		}
		return deliveryCharges;
	}

	public void setDeliveryCharges(DeliveryChargesDVO deliveryCharges) {
		this.deliveryCharges = deliveryCharges;
	}

	private void populateExpressCharges() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("Country id for getting charges "
				+ shoppingCartOpr.getRetailOrderRecord().getShippingDetails().getCountryDvo().getCode());
		if (shoppingCartOpr.getRetailOrderRecord().getShippingDetails().getCountryDvo().getCode() != null) {
			// TEMPLATE TO CALL SAVE BF METHOD FROM BB ver 1.1
			setErrorList(new ArrayList<String>());
			setSuccessMsg("");

			try {
				myLog.debug("Country Id in BB"
						+ shoppingCartOpr.getRetailOrderRecord().getShippingDetails().getCountryDvo().getCode());
				ShoppingCartOpr returnShoppingCartOpr = new ShoppingCartBF().populateExpressCharges(shoppingCartOpr);

				// GEOPLUGIN- converting delivery charges according to currency
				shoppingCartOpr
						.getRetailOrderRecord()
						.getDeliveryChargesRecord()
						.setOriginalExpressCharge(
								returnShoppingCartOpr.getRetailOrderRecord().getDeliveryChargesRecord()
										.getExpressCharge());
				myLog.debug("Original Express charges"
						+ returnShoppingCartOpr.getRetailOrderRecord().getDeliveryChargesRecord().getExpressCharge());
				shoppingCartOpr
						.getRetailOrderRecord()
						.getDeliveryChargesRecord()
						.setExpressCharge(
								(Float) convertCurrency(returnShoppingCartOpr.getRetailOrderRecord()
										.getDeliveryChargesRecord().getExpressCharge()));

				expressDeliveryCharge = shoppingCartOpr.getRetailOrderRecord().getDeliveryChargesRecord()
						.getExpressCharge();
				myLog.debug("express charges " + expressDeliveryCharge);

			} catch (BusinessException e) {
				// handle framework exception

				handleException(e, propertiesLocation);
			} catch (FrameworkException e) {
				// handle framework exception
				handleException(e, propertiesLocation);
			}
		} else {
			expressDeliveryCharge = 0.0f;
			shoppingCartOpr.getRetailOrderRecord().getDeliveryChargesRecord().setExpressCharge(expressDeliveryCharge);
			myLog.debug("experss_charge in else --------"
					+ shoppingCartOpr.getRetailOrderRecord().getDeliveryChargesRecord().getExpressCharge());
		}
	}

	public boolean isShippingCountryDisable() {
		return shippingCountryDisable;
	}

	public void setShippingCountryDisable(boolean shippingCountryDisable) {
		this.shippingCountryDisable = shippingCountryDisable;
	}

	public void populateShippingCharges() {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("Country id for getting charges "
				+ shoppingCartOpr.getRetailOrderRecord().getShippingDetails().getCountryDvo().getCode());
		if (shoppingCartOpr.getRetailOrderRecord().getShippingDetails().getCountryDvo().getCode() != null) {
			for (int i = 0; i < shoppingCartOpr.getShoppingCartProductList().size(); i++) {
				ShoppingCartProductDVO shoppingCartProductDVO = shoppingCartOpr.getShoppingCartProductList().get(i);
			}

			try {
				shoppingCartOpr = new ShoppingCartBF().getShippingChargesMode(shoppingCartOpr);
				ShoppingCartOpr returnShoppingCartOpr = new ShoppingCartOpr();
				myLog.debug("check for shopping mode:::" + shoppingCartOpr.getChargesMode());
				if (shoppingCartOpr.getChargesMode() != null
						&& shoppingCartOpr.getChargesMode().equalsIgnoreCase("Yes")) {
					myLog.debug("Need to find data with all catagories");
					returnShoppingCartOpr = new ShoppingCartBF().populateShippingCharges(shoppingCartOpr);

				} else if (shoppingCartOpr.getChargesMode().equals("Default")) {
					myLog.debug("Need to find data for default catagories");
					returnShoppingCartOpr = new ShoppingCartBF().populateDefaultShippingCharges(shoppingCartOpr);
				}
				Float delivery = 0.0f;
				Float processing = 0.0f;
				Float duties = 0.0f;
				Float express = 0.0f;
				for (int i = 0; i < returnShoppingCartOpr.getShoppingCartProductList().size(); i++) {
					Float dutiesPercent = 0.0f;
					Float productPrice = 0.0f;
					myLog.debug("check in shoppingcartBB for delivery chagres"
							+ returnShoppingCartOpr.getShoppingCartProductList().get(i).getProductSkuRecord()
									.getDeliverChargesRecord().getDeliveryCharge());
					myLog.debug("processing chagres::"
							+ returnShoppingCartOpr.getShoppingCartProductList().get(i).getProductSkuRecord()
									.getDeliverChargesRecord().getProcessingCharge());
					myLog.debug("dutiesPercent chagres::"
							+ returnShoppingCartOpr.getShoppingCartProductList().get(i).getProductSkuRecord()
									.getDeliverChargesRecord().getDutiesCharge());
					myLog.debug("check for qty:::"
							+ returnShoppingCartOpr.getShoppingCartProductList().get(i).getQuantity());
					delivery = delivery
							+ (returnShoppingCartOpr.getShoppingCartProductList().get(i).getProductSkuRecord()
									.getDeliverChargesRecord().getDeliveryCharge() * returnShoppingCartOpr
									.getShoppingCartProductList().get(i).getQuantity());
					processing = processing
							+ (returnShoppingCartOpr.getShoppingCartProductList().get(i).getProductSkuRecord()
									.getDeliverChargesRecord().getProcessingCharge() * returnShoppingCartOpr
									.getShoppingCartProductList().get(i).getQuantity());
					dutiesPercent = (returnShoppingCartOpr.getShoppingCartProductList().get(i).getProductSkuRecord()
							.getDeliverChargesRecord().getDutiesCharge()) / 100;

					if (returnShoppingCartOpr.getShoppingCartProductList().get(i).getProductSkuRecord()
							.getDiscountPrice() == null) {
						productPrice = (float) (returnShoppingCartOpr.getShoppingCartProductList().get(i)
								.getProductSkuRecord().getOriginalBasePrice() * returnShoppingCartOpr
								.getShoppingCartProductList().get(i).getQuantity());
					} else if (returnShoppingCartOpr.getShoppingCartProductList().get(i).getProductSkuRecord()
							.getDiscountPrice() > 0.0f) {
						productPrice = (float) (returnShoppingCartOpr.getShoppingCartProductList().get(i)
								.getProductSkuRecord().getOriginalDiscountPrice() * returnShoppingCartOpr
								.getShoppingCartProductList().get(i).getQuantity());
					}

					duties = duties + (productPrice * dutiesPercent);

					express = express
							+ (returnShoppingCartOpr.getShoppingCartProductList().get(i).getProductSkuRecord()
									.getDeliverChargesRecord().getExpressCharge() * returnShoppingCartOpr
									.getShoppingCartProductList().get(i).getQuantity());
					myLog.debug("total deliverycharge::: " + delivery + ":" + processing + ":" + duties + ":" + express);
				}

				Float conversionRate = (Float) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
						.get(CommonConstant.CURRENCY_CONVERSION_RATE);
				myLog.debug("check in new total::" + getShoppingCartOpr().getTotalPrice() + "::AND::" + conversionRate);
				Float originalBasePrice = getShoppingCartOpr().getTotalPrice();
				if (conversionRate != null) {
					originalBasePrice = originalBasePrice / conversionRate;
				}

				shoppingCartOpr.getRetailOrderRecord().getDeliveryChargesRecord().setDeliveryCharge(delivery);

			} catch (BusinessException e) {
				handleException(e, propertiesLocation);
			} catch (FrameworkException e) {
				handleException(e, propertiesLocation);
			}
		}

	}

	public boolean isVcode() {
		return vcode;
	}

	public void setVcode(boolean vcode) {
		this.vcode = vcode;
	}

	@Override
	public void executeAddRow(ActionEvent event) {
	}

}
