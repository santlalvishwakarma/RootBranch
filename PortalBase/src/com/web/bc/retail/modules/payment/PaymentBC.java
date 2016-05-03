package com.web.bc.retail.modules.payment;

import java.util.HashMap;

import com.web.bc.retail.modules.shoppingcart.ShoppingCartSqlTemplate;
import com.web.common.constants.CommonConstant;
import com.web.common.dvo.common.OperationalDataValueObject;
import com.web.common.dvo.opr.retail.ShoppingCartOpr;
import com.web.common.parents.BackingClass;
import com.web.foundation.dao.DAOResult;
import com.web.foundation.dao.IDAOConstant;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;

public class PaymentBC extends BackingClass {

	public void cancelOrder(ShoppingCartOpr shoppingCartOpr) throws FrameworkException {
		// BACKING CLASS METHOD TEMPLATE ver 1.0
		// ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		DAOResult daoResult = new DAOResult();

		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.UPDATE_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ShoppingCartSqlTemplate.UPDATE_ORDER_CANCELLED);

		Object strSqlParams[][] = new Object[1][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[0][2] = shoppingCartOpr.getRetailOrderRecord().getId();

		// in the following call replace null with dynamic where clause if
		// required
		daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
	}

	public void updateOrderStatusPendingDispatch(ShoppingCartOpr shoppingCartOpr) throws FrameworkException {
		// BACKING CLASS METHOD TEMPLATE ver 1.0
		// ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		DAOResult daoResult = new DAOResult();

		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.DELETE_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ShoppingCartSqlTemplate.UPDATE_ORDER_PENDING_DISPATCH);

		Object strSqlParams[][] = new Object[1][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[0][2] = shoppingCartOpr.getRetailOrderRecord().getId();

		// in the following call replace null with dynamic where clause if
		// required
		daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
	}

	public void saveProductForLater(ShoppingCartOpr shoppingCartSaveOpr) throws FrameworkException {
		// BACKING CLASS METHOD TEMPLATE ver 1.0
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		DAOResult daoResult = new DAOResult();

		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.INSERT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ShoppingCartSqlTemplate.SAVE_PRODUCT_FOR_LATER);

		Object strSqlParams[][] = new Object[5][3];

		for (int i = 0; i < shoppingCartSaveOpr.getShoppingCartProductList().size(); i++) {
			myLog.debug("checking inside bc"
					+ shoppingCartSaveOpr.getShoppingCartProductList().get(i).getProductSkuRecord().getProductRecord()
							.getId() + "AND"
					+ shoppingCartSaveOpr.getShoppingCartProductList().get(i).getProductSkuRecord().getId());

			strSqlParams[0][0] = "1";
			strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
			strSqlParams[0][2] = shoppingCartSaveOpr.getShoppingCartProductList().get(i).getProductSkuRecord()
					.getProductRecord().getId();

			strSqlParams[1][0] = "2";
			strSqlParams[1][1] = IDAOConstant.LONG_DATATYPE;
			strSqlParams[1][2] = shoppingCartSaveOpr.getShoppingCartProductList().get(i).getProductSkuRecord().getId();
			myLog.debug("check variant in bc:::::" + strSqlParams[1][2]);

			strSqlParams[2][0] = "3";
			strSqlParams[2][1] = IDAOConstant.STRING_DATATYPE;
			strSqlParams[2][2] = shoppingCartSaveOpr.getRetailUserDetails().getUserLogin();

			strSqlParams[3][0] = "4";
			strSqlParams[3][1] = IDAOConstant.STRING_DATATYPE;
			strSqlParams[3][2] = shoppingCartSaveOpr.getRetailUserDetails().getUserLogin();

			strSqlParams[4][0] = "5";
			strSqlParams[4][1] = IDAOConstant.STRING_DATATYPE;
			strSqlParams[4][2] = shoppingCartSaveOpr.getRetailUserDetails().getUserLogin();

			daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		}

	}

	public ShoppingCartOpr checkUserAvailability(OperationalDataValueObject queryParameters) throws FrameworkException,
			BusinessException {
		// BACKING CLASS METHOD TEMPLATE ver 1.0
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		DAOResult daoResult = new DAOResult();
		ShoppingCartOpr shoppingCartOpr = (ShoppingCartOpr) queryParameters;
		ShoppingCartOpr returnshoppShoppingCartOpr = new ShoppingCartOpr();

		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ShoppingCartSqlTemplate.CHECK_USER_AVAILABILITY);

		Object strSqlParams[][] = new Object[1][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[0][2] = shoppingCartOpr.getRetailUserDetails().getUserLogin().trim();

		myLog.debug(" inside CHECK_USER_AVAILABILITY" + strSqlParams[0][2]);

		// in the following call replace null with dynamic where clause if
		// required
		daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap responseMap = daoResult.getInvocationResult();
		myLog.debug(":: Resultset got ::" + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {
				HashMap resultRow = (HashMap) responseMap.get(i);
				returnshoppShoppingCartOpr.getRetailUserDetails().setUserLogin((String) resultRow.get("user_login"));
			}
		}

		return returnshoppShoppingCartOpr;
	}

	public void updateOrderStatusSavedToWishlist(ShoppingCartOpr shoppingCartSaveOpr) throws FrameworkException {
		// BACKING CLASS METHOD TEMPLATE ver 1.0
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		DAOResult daoResult = new DAOResult();

		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.INSERT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ShoppingCartSqlTemplate.UPDATE_ORDER_SAVED_TO_WISHLIST);

		Object strSqlParams[][] = new Object[1][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[0][2] = shoppingCartSaveOpr.getRetailOrderRecord().getId();

		daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);

	}

	public ShoppingCartOpr updateOrderStatusAsPaymentInitiated(ShoppingCartOpr shoppingCartOpr)
			throws FrameworkException, BusinessException {
		// BACKING CLASS METHOD TEMPLATE ver 1.0
		// ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		DAOResult daoResult = new DAOResult();

		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.UPDATE_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ShoppingCartSqlTemplate.UPDATE_ORDER_STATUS_PAYMENT_INITIATED);

		Object strSqlParams[][] = new Object[1][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[0][2] = shoppingCartOpr.getRetailOrderRecord().getId();

		// in the following call replace null with dynamic where clause if
		// required
		daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);

		if (daoResult.getUpdateResult() == 0) {
			throw new BusinessException("order_status_not_updated");
		}

		return shoppingCartOpr;
	}

	public ShoppingCartOpr updateOrderPaymentType(ShoppingCartOpr shoppingCartOpr) throws FrameworkException,
			BusinessException {
		// BACKING CLASS METHOD TEMPLATE ver 1.0
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("Inside updateOrderPaymentType:::::::::");
		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		DAOResult daoResult = new DAOResult();

		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.UPDATE_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ShoppingCartSqlTemplate.UPDATE_ORDER_PAYMENT_TYPE);

		Object strSqlParams[][] = new Object[2][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[0][2] = shoppingCartOpr.getPaymentDVO().getPaymentType();
		myLog.debug("Inside updateOrderPaymentType:::::::::Param 1: " + strSqlParams[0][2]);

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[1][2] = shoppingCartOpr.getPaymentDVO().getSentMerchantParameter();
		myLog.debug("Inside updateOrderPaymentType:::::::::Param 2: " + strSqlParams[1][2]);

		// in the following call replace null with dynamic where clause if
		// required
		daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);

		if (daoResult.getUpdateResult() == 0) {
			throw new BusinessException("order_paymentType_not_updated");
		}

		return shoppingCartOpr;
	}

	public ShoppingCartOpr saveInitialPaymentTransaction(ShoppingCartOpr shoppingCartOpr) throws FrameworkException {
		// BACKING CLASS METHOD TEMPLATE ver 1.0
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		DAOResult daoResult = new DAOResult();

		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ShoppingCartSqlTemplate.INIT_PAYMENT_GATEWAY_TRANSACTION);
		// if (shoppingCartOpr.getPaymentDVO().getPaymentType()
		// .equalsIgnoreCase(CommonConstant.PAYMENT_GATEWAY_PROVIDER_CCAVENUE))
		// {
		// queryDetailsMap.put(IDAOConstant.SQL_TEXT,
		// ShoppingCartSqlTemplate.INIT_CC_AVENUE_TRANSACTION);
		// }
		Object strSqlParams[][] = new Object[63][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[0][2] = shoppingCartOpr.getPaymentDVO().getSentMerchantId();
		myLog.debug("strSqlParams[0][2] = " + strSqlParams[0][2]);

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[1][2] = shoppingCartOpr.getPaymentDVO().getSentAmount();
		myLog.debug("strSqlParams[1][2] = " + strSqlParams[1][2]);

		strSqlParams[2][0] = "3";
		strSqlParams[2][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[2][2] = shoppingCartOpr.getPaymentDVO().getSentOrderId();
		myLog.debug("strSqlParams[2][2] = " + strSqlParams[2][2]);

		strSqlParams[3][0] = "4";
		strSqlParams[3][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[3][2] = shoppingCartOpr.getPaymentDVO().getSentRedirectUrl();
		myLog.debug("strSqlParams[3][2] = " + strSqlParams[3][2]);

		strSqlParams[4][0] = "5";
		strSqlParams[4][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[4][2] = shoppingCartOpr.getPaymentDVO().getSentCheckSum();
		myLog.debug("strSqlParams[4][2] = " + strSqlParams[4][2]);

		strSqlParams[5][0] = "6";
		strSqlParams[5][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[5][2] = shoppingCartOpr.getPaymentDVO().getSentBillingAddress().getFirstName() + " "
				+ shoppingCartOpr.getPaymentDVO().getSentBillingAddress().getLastName();
		myLog.debug("strSqlParams[5][2] = " + strSqlParams[5][2]);

		strSqlParams[6][0] = "7";
		strSqlParams[6][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[6][2] = shoppingCartOpr.getPaymentDVO().getSentBillingAddress().getAddressLine1();
		myLog.debug("strSqlParams[6][2] = " + strSqlParams[6][2]);

		strSqlParams[7][0] = "8";
		strSqlParams[7][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[7][2] = shoppingCartOpr.getPaymentDVO().getSentBillingAddress().getCountryDvo().getName();
		myLog.debug("strSqlParams[7][2] = " + strSqlParams[7][2]);

		strSqlParams[8][0] = "9";
		strSqlParams[8][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[8][2] = shoppingCartOpr.getPaymentDVO().getSentBillingAddress().getStateDVO().getCode();
		myLog.debug("strSqlParams[8][2] = " + strSqlParams[8][2]);

		strSqlParams[9][0] = "10";
		strSqlParams[9][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[9][2] = shoppingCartOpr.getPaymentDVO().getSentBillingAddress().getPhone1();
		myLog.debug("strSqlParams[9][2] = " + strSqlParams[9][2]);

		strSqlParams[10][0] = "11";
		strSqlParams[10][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[10][2] = shoppingCartOpr.getPaymentDVO().getSentBillingAddress().getEmail1();
		myLog.debug("strSqlParams[10][2] = " + strSqlParams[10][2]);

		strSqlParams[11][0] = "12";
		strSqlParams[11][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[11][2] = shoppingCartOpr.getPaymentDVO().getSentBillingAddress().getCityDvo().getCode();
		myLog.debug("strSqlParams[11][2] = " + strSqlParams[11][2]);

		strSqlParams[12][0] = "13";
		strSqlParams[12][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[12][2] = shoppingCartOpr.getPaymentDVO().getSentBillingAddress().getPinRecord().getCode();
		myLog.debug("strSqlParams[12][2] = " + strSqlParams[12][2]);

		strSqlParams[13][0] = "14";
		strSqlParams[13][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[13][2] = shoppingCartOpr.getPaymentDVO().getSentDeliveryAddress().getFirstName() + " "
				+ shoppingCartOpr.getPaymentDVO().getSentDeliveryAddress().getLastName();
		myLog.debug("strSqlParams[13][2] = " + strSqlParams[13][2]);

		strSqlParams[14][0] = "15";
		strSqlParams[14][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[14][2] = shoppingCartOpr.getPaymentDVO().getSentDeliveryAddress().getAddressLine1();
		myLog.debug("strSqlParams[14][2] = " + strSqlParams[14][2]);

		strSqlParams[15][0] = "16";
		strSqlParams[15][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[15][2] = shoppingCartOpr.getPaymentDVO().getSentDeliveryAddress().getCountryDvo().getName();
		myLog.debug("strSqlParams[15][2] = " + strSqlParams[15][2]);

		strSqlParams[16][0] = "17";
		strSqlParams[16][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[16][2] = shoppingCartOpr.getPaymentDVO().getSentDeliveryAddress().getStateDVO().getCode();
		myLog.debug("strSqlParams[16][2] = " + strSqlParams[16][2]);

		strSqlParams[17][0] = "18";
		strSqlParams[17][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[17][2] = shoppingCartOpr.getPaymentDVO().getSentDeliveryAddress().getPhone1();
		myLog.debug("strSqlParams[17][2] = " + strSqlParams[17][2]);

		strSqlParams[18][0] = "19";
		strSqlParams[18][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[18][2] = shoppingCartOpr.getPaymentDVO().getSentDeliveryAddress().getCityDvo().getCode();
		myLog.debug("strSqlParams[18][2] = " + strSqlParams[18][2]);
		;

		strSqlParams[19][0] = "20";
		strSqlParams[19][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[19][2] = shoppingCartOpr.getPaymentDVO().getSentDeliveryAddress().getPinRecord().getCode();
		myLog.debug("strSqlParams[19][2] = " + strSqlParams[19][2]);

		strSqlParams[20][0] = "21";
		strSqlParams[20][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[20][2] = shoppingCartOpr.getPaymentDVO().getSentDeliveryNotes();
		myLog.debug("strSqlParams[20][2] = " + strSqlParams[20][2]);

		strSqlParams[21][0] = "22";
		strSqlParams[21][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[21][2] = shoppingCartOpr.getPaymentDVO().getSentMerchantParameter();
		myLog.debug("strSqlParams[21][2] = " + strSqlParams[21][2]);

		strSqlParams[22][0] = "23";
		strSqlParams[22][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[22][2] = shoppingCartOpr.getPaymentDVO().getReceivedAmount();
		myLog.debug("strSqlParams[22][2] = " + strSqlParams[22][2]);

		strSqlParams[23][0] = "24";
		strSqlParams[23][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[23][2] = shoppingCartOpr.getPaymentDVO().getReceivedAuthorizationDescription();
		myLog.debug("strSqlParams[23][2] = " + strSqlParams[23][2]);

		strSqlParams[24][0] = "25";
		strSqlParams[24][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[24][2] = shoppingCartOpr.getPaymentDVO().getReceivedBankName();
		myLog.debug("strSqlParams[24][2] = " + strSqlParams[24][2]);

		strSqlParams[25][0] = "26";
		strSqlParams[25][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[25][2] = shoppingCartOpr.getPaymentDVO().getReceivedCardCategory();
		myLog.debug("strSqlParams[25][2] = " + strSqlParams[25][2]);

		strSqlParams[26][0] = "27";
		strSqlParams[26][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[26][2] = shoppingCartOpr.getPaymentDVO().getReceivedCheckSum();
		myLog.debug("strSqlParams[26][2] = " + strSqlParams[26][2]);

		strSqlParams[27][0] = "28";
		strSqlParams[27][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[27][2] = shoppingCartOpr.getPaymentDVO().getReceivedMerchantId();
		myLog.debug("strSqlParams[27][2] = " + strSqlParams[27][2]);

		strSqlParams[28][0] = "29";
		strSqlParams[28][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[28][2] = shoppingCartOpr.getPaymentDVO().getReceivedMerchantParameter();
		myLog.debug("strSqlParams[28][2] = " + strSqlParams[28][2]);

		strSqlParams[29][0] = "30";
		strSqlParams[29][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[29][2] = shoppingCartOpr.getPaymentDVO().getReceivedNbBid();
		myLog.debug("strSqlParams[29][2] = " + strSqlParams[29][2]);

		strSqlParams[30][0] = "31";
		strSqlParams[30][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[30][2] = shoppingCartOpr.getPaymentDVO().getReceivedNbOrderNumber();
		myLog.debug("strSqlParams[30][2] = " + strSqlParams[30][2]);

		strSqlParams[31][0] = "32";
		strSqlParams[31][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[31][2] = shoppingCartOpr.getPaymentDVO().getReceivedNotes();
		myLog.debug("strSqlParams[31][2] = " + strSqlParams[31][2]);

		strSqlParams[32][0] = "33";
		strSqlParams[32][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[32][2] = shoppingCartOpr.getPaymentDVO().getReceivedOrderId();
		myLog.debug("strSqlParams[32][2] = " + strSqlParams[32][2]);

		strSqlParams[33][0] = "34";
		strSqlParams[33][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[33][2] = shoppingCartOpr.getPaymentDVO().getReceivedReturnUrl();
		myLog.debug("strSqlParams[33][2] = " + strSqlParams[33][2]);

		strSqlParams[34][0] = "35";
		strSqlParams[34][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[34][2] = shoppingCartOpr.getPaymentDVO().getReceivedBillingAddress().getFirstName() + " "
				+ shoppingCartOpr.getPaymentDVO().getReceivedBillingAddress().getLastName();
		myLog.debug("strSqlParams[34][2] = " + strSqlParams[34][2]);

		strSqlParams[35][0] = "36";
		strSqlParams[35][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[35][2] = shoppingCartOpr.getPaymentDVO().getReceivedBillingAddress().getAddressLine1();
		myLog.debug("strSqlParams[35][2] = " + strSqlParams[35][2]);

		strSqlParams[36][0] = "37";
		strSqlParams[36][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[36][2] = shoppingCartOpr.getPaymentDVO().getReceivedBillingAddress().getCountryDvo().getName();
		myLog.debug("strSqlParams[36][2] = " + strSqlParams[36][2]);

		strSqlParams[37][0] = "38";
		strSqlParams[37][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[37][2] = shoppingCartOpr.getPaymentDVO().getReceivedBillingAddress().getStateDVO().getCode();
		myLog.debug("strSqlParams[37][2] = " + strSqlParams[37][2]);

		strSqlParams[38][0] = "39";
		strSqlParams[38][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[38][2] = shoppingCartOpr.getPaymentDVO().getReceivedBillingAddress().getPhone1();
		myLog.debug("strSqlParams[38][2] = " + strSqlParams[38][2]);

		strSqlParams[39][0] = "40";
		strSqlParams[39][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[39][2] = shoppingCartOpr.getPaymentDVO().getReceivedBillingAddress().getEmail1();
		myLog.debug("strSqlParams[39][2] = " + strSqlParams[39][2]);

		strSqlParams[40][0] = "41";
		strSqlParams[40][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[40][2] = shoppingCartOpr.getPaymentDVO().getReceivedBillingAddress().getCityDvo().getCode();
		myLog.debug("strSqlParams[40][2] = " + strSqlParams[40][2]);

		strSqlParams[41][0] = "42";
		strSqlParams[41][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[41][2] = shoppingCartOpr.getPaymentDVO().getReceivedBillingAddress().getPinRecord().getCode();
		myLog.debug("strSqlParams[41][2] = " + strSqlParams[41][2]);

		strSqlParams[42][0] = "43";
		strSqlParams[42][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[42][2] = shoppingCartOpr.getPaymentDVO().getReceivedShippingAddress().getFirstName() + " "
				+ shoppingCartOpr.getPaymentDVO().getReceivedShippingAddress().getLastName();
		myLog.debug("strSqlParams[42][2] = " + strSqlParams[42][2]);

		strSqlParams[43][0] = "44";
		strSqlParams[43][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[43][2] = shoppingCartOpr.getPaymentDVO().getReceivedShippingAddress().getAddressLine1();
		myLog.debug("strSqlParams[43][2] = " + strSqlParams[43][2]);

		strSqlParams[44][0] = "45";
		strSqlParams[44][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[44][2] = shoppingCartOpr.getPaymentDVO().getReceivedShippingAddress().getCountryDvo().getName();
		myLog.debug("strSqlParams[44][2] = " + strSqlParams[44][2]);

		strSqlParams[45][0] = "46";
		strSqlParams[45][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[45][2] = shoppingCartOpr.getPaymentDVO().getReceivedShippingAddress().getStateDVO().getCode();
		myLog.debug("strSqlParams[45][2] = " + strSqlParams[45][2]);

		strSqlParams[46][0] = "47";
		strSqlParams[46][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[46][2] = shoppingCartOpr.getPaymentDVO().getReceivedShippingAddress().getPhone1();
		myLog.debug("strSqlParams[46][2] = " + strSqlParams[46][2]);

		strSqlParams[47][0] = "48";
		strSqlParams[47][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[47][2] = shoppingCartOpr.getPaymentDVO().getReceivedShippingAddress().getCityDvo().getCode();
		myLog.debug("strSqlParams[47][2] = " + strSqlParams[47][2]);

		strSqlParams[48][0] = "49";
		strSqlParams[48][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[48][2] = shoppingCartOpr.getPaymentDVO().getReceivedShippingAddress().getPinRecord().getCode();
		myLog.debug("strSqlParams[48][2] = " + strSqlParams[48][2]);

		strSqlParams[49][0] = "50";
		strSqlParams[49][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[49][2] = shoppingCartOpr.getPaymentDVO().getErrorMessage();
		myLog.debug("strSqlParams[49][2] = " + strSqlParams[49][2]);

		strSqlParams[50][0] = "51";
		strSqlParams[50][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[50][2] = shoppingCartOpr.getPaymentDVO().getUserLogin();
		myLog.debug("strSqlParams[50][2] = " + strSqlParams[50][2]);

		strSqlParams[51][0] = "52";
		strSqlParams[51][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[51][2] = shoppingCartOpr.getPaymentDVO().getUserLogin();
		myLog.debug("strSqlParams[51][2] = " + strSqlParams[51][2]);

		strSqlParams[52][0] = "53";
		strSqlParams[52][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[52][2] = shoppingCartOpr.getPaymentDVO().getSentServerMode();
		myLog.debug("strSqlParams[52][2] = " + strSqlParams[52][2]);

		strSqlParams[53][0] = "54";
		strSqlParams[53][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[53][2] = shoppingCartOpr.getPaymentDVO().getPaymentType();
		myLog.debug("strSqlParams[53][2] = " + strSqlParams[53][2]);

		strSqlParams[54][0] = "55";
		strSqlParams[54][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[54][2] = shoppingCartOpr.getPaymentDVO().getPaymentOption().getPaymentGatewayProvider();
		myLog.debug("strSqlParams[54][2] = " + strSqlParams[54][2]);

		strSqlParams[55][0] = "56";
		strSqlParams[55][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[55][2] = shoppingCartOpr.getPaymentDVO().getReceivedTransactionReferenceNumber();
		myLog.debug("strSqlParams[55][2] = " + strSqlParams[55][2]);

		strSqlParams[56][0] = "57";
		strSqlParams[56][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[56][2] = shoppingCartOpr.getPaymentDVO().getReceivedAuthIdCode();
		myLog.debug("strSqlParams[56][2] = " + strSqlParams[56][2]);

		strSqlParams[57][0] = "58";
		strSqlParams[57][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[57][2] = shoppingCartOpr.getPaymentDVO().getReceivedRrn();
		myLog.debug("strSqlParams[57][2] = " + strSqlParams[57][2]);

		strSqlParams[58][0] = "59";
		strSqlParams[58][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[58][2] = shoppingCartOpr.getPaymentDVO().getReceivedCvrespCode();
		myLog.debug("strSqlParams[58][2] = " + strSqlParams[58][2]);

		strSqlParams[59][0] = "60";
		strSqlParams[59][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[59][2] = shoppingCartOpr.getPaymentDVO().getReceivedFdmsScore();
		myLog.debug("strSqlParams[59][2] = " + strSqlParams[59][2]);

		strSqlParams[60][0] = "61";
		strSqlParams[60][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[60][2] = shoppingCartOpr.getPaymentDVO().getReceivedFdmsResult();
		myLog.debug("strSqlParams[60][2] = " + strSqlParams[60][2]);

		strSqlParams[61][0] = "62";
		strSqlParams[61][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[61][2] = shoppingCartOpr.getPaymentDVO().getReceivedCookie();
		myLog.debug("strSqlParams[61][2] = " + strSqlParams[61][2]);

		strSqlParams[62][0] = "63";
		strSqlParams[62][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[62][2] = shoppingCartOpr.getPaymentDVO().getPaymentOption().getPaymentCode();
		myLog.debug("strSqlParams[62][2] = " + strSqlParams[62][2]);

		// in the following call replace null with dynamic where clause if
		// required
		daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap responseMap = daoResult.getInvocationResult();
		myLog.debug(":: Resultset got ::" + responseMap);

		for (int i = 0; i < responseMap.size(); i++) {
			HashMap resultRow = (HashMap) responseMap.get(i);
			if (resultRow.get("payment_gateway_transactions_id") != null) {
				shoppingCartOpr.getPaymentDVO().setId(
						Long.valueOf(resultRow.get("payment_gateway_transactions_id").toString()));
				shoppingCartOpr.getPaymentDVO().setSentMerchantParameter(
						resultRow.get("payment_gateway_transactions_id").toString());
			}
			if (resultRow.get("sent_billing_country_code") != null) {
				shoppingCartOpr.getPaymentDVO().getSentBillingAddress().getCountryDvo()
						.setCode(resultRow.get("sent_billing_country_code").toString());
			}
			if (resultRow.get("sent_delivery_country_code") != null) {
				shoppingCartOpr.getPaymentDVO().getSentDeliveryAddress().getCountryDvo()
						.setCode(resultRow.get("sent_delivery_country_code").toString());
			}
			if (resultRow.get("received_billing_country_code") != null) {
				shoppingCartOpr.getPaymentDVO().getReceivedBillingAddress().getCountryDvo()
						.setCode(resultRow.get("received_billing_country_code").toString());
			}
			if (resultRow.get("received_delivery_country_code") != null) {
				shoppingCartOpr.getPaymentDVO().getReceivedShippingAddress().getCountryDvo()
						.setCode(resultRow.get("received_delivery_country_code").toString());
			}
		}

		return shoppingCartOpr;
	}

	public ShoppingCartOpr updateOrderStatusAsPaymentOffline(ShoppingCartOpr shoppingCartOpr)
			throws FrameworkException, BusinessException {
		// BACKING CLASS METHOD TEMPLATE ver 1.0
		// ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		DAOResult daoResult = new DAOResult();

		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.UPDATE_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ShoppingCartSqlTemplate.UPDATE_ORDER_STATUS_PAYMENT_OFFLINE);

		Object strSqlParams[][] = new Object[1][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[0][2] = shoppingCartOpr.getRetailOrderRecord().getId();

		// in the following call replace null with dynamic where clause if
		// required
		daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);

		if (daoResult.getUpdateResult() == 0) {
			throw new BusinessException("order_status_not_updated");
		}

		return shoppingCartOpr;
	}

	public void saveCCAvenueInitTransaction(ShoppingCartOpr shoppingCartOpr) throws FrameworkException {
		// BACKING CLASS METHOD TEMPLATE ver 1.0
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		DAOResult daoResult = new DAOResult();

		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.INSERT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ShoppingCartSqlTemplate.INIT_CC_AVENUE_TRANSACTION);

		Object strSqlParams[][] = new Object[53][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[0][2] = shoppingCartOpr.getPaymentDVO().getSentMerchantId();

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[1][2] = shoppingCartOpr.getPaymentDVO().getSentAmount();

		strSqlParams[2][0] = "3";
		strSqlParams[2][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[2][2] = shoppingCartOpr.getPaymentDVO().getSentOrderId();

		strSqlParams[3][0] = "4";
		strSqlParams[3][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[3][2] = shoppingCartOpr.getPaymentDVO().getSentRedirectUrl();

		strSqlParams[4][0] = "5";
		strSqlParams[4][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[4][2] = shoppingCartOpr.getPaymentDVO().getSentCheckSum();

		strSqlParams[5][0] = "6";
		strSqlParams[5][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[5][2] = shoppingCartOpr.getPaymentDVO().getSentBillingAddress().getFirstName() + " "
				+ shoppingCartOpr.getPaymentDVO().getSentBillingAddress().getLastName();

		strSqlParams[6][0] = "7";
		strSqlParams[6][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[6][2] = shoppingCartOpr.getPaymentDVO().getSentBillingAddress().getAddressLine1();

		strSqlParams[7][0] = "8";
		strSqlParams[7][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[7][2] = shoppingCartOpr.getPaymentDVO().getSentBillingAddress().getCountryDvo().getName();

		strSqlParams[8][0] = "9";
		strSqlParams[8][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[8][2] = shoppingCartOpr.getPaymentDVO().getSentBillingAddress().getStateDVO().getCode();

		strSqlParams[9][0] = "10";
		strSqlParams[9][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[9][2] = shoppingCartOpr.getPaymentDVO().getSentBillingAddress().getPhone1();

		strSqlParams[10][0] = "11";
		strSqlParams[10][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[10][2] = shoppingCartOpr.getPaymentDVO().getSentBillingAddress().getEmail1();

		strSqlParams[11][0] = "12";
		strSqlParams[11][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[11][2] = shoppingCartOpr.getPaymentDVO().getSentBillingAddress().getCityDvo().getCode();

		strSqlParams[12][0] = "13";
		strSqlParams[12][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[12][2] = shoppingCartOpr.getPaymentDVO().getSentBillingAddress().getPinRecord().getCode();

		strSqlParams[13][0] = "14";
		strSqlParams[13][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[13][2] = shoppingCartOpr.getPaymentDVO().getSentDeliveryAddress().getFirstName() + " "
				+ shoppingCartOpr.getPaymentDVO().getSentDeliveryAddress().getLastName();

		strSqlParams[14][0] = "15";
		strSqlParams[14][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[14][2] = shoppingCartOpr.getPaymentDVO().getSentDeliveryAddress().getAddressLine1();

		strSqlParams[15][0] = "16";
		strSqlParams[15][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[15][2] = shoppingCartOpr.getPaymentDVO().getSentDeliveryAddress().getCountryDvo().getName();

		strSqlParams[16][0] = "17";
		strSqlParams[16][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[16][2] = shoppingCartOpr.getPaymentDVO().getSentDeliveryAddress().getStateDVO().getCode();

		strSqlParams[17][0] = "18";
		strSqlParams[17][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[17][2] = shoppingCartOpr.getPaymentDVO().getSentDeliveryAddress().getPhone1();

		strSqlParams[18][0] = "19";
		strSqlParams[18][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[18][2] = shoppingCartOpr.getPaymentDVO().getSentDeliveryAddress().getCityDvo().getCode();

		strSqlParams[19][0] = "20";
		strSqlParams[19][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[19][2] = shoppingCartOpr.getPaymentDVO().getSentDeliveryAddress().getPinRecord().getCode();

		strSqlParams[20][0] = "21";
		strSqlParams[20][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[20][2] = shoppingCartOpr.getPaymentDVO().getSentDeliveryNotes();

		strSqlParams[21][0] = "22";
		strSqlParams[21][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[21][2] = shoppingCartOpr.getPaymentDVO().getSentMerchantParameter();

		strSqlParams[22][0] = "23";
		strSqlParams[22][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[22][2] = shoppingCartOpr.getPaymentDVO().getReceivedAmount();

		strSqlParams[23][0] = "24";
		strSqlParams[23][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[23][2] = shoppingCartOpr.getPaymentDVO().getReceivedAuthorizationDescription();

		strSqlParams[24][0] = "25";
		strSqlParams[24][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[24][2] = shoppingCartOpr.getPaymentDVO().getReceivedBankName();

		strSqlParams[25][0] = "26";
		strSqlParams[25][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[25][2] = shoppingCartOpr.getPaymentDVO().getReceivedCardCategory();

		strSqlParams[26][0] = "27";
		strSqlParams[26][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[26][2] = shoppingCartOpr.getPaymentDVO().getReceivedCheckSum();

		strSqlParams[27][0] = "28";
		strSqlParams[27][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[27][2] = shoppingCartOpr.getPaymentDVO().getReceivedMerchantId();

		strSqlParams[28][0] = "29";
		strSqlParams[28][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[28][2] = shoppingCartOpr.getPaymentDVO().getReceivedMerchantParameter();

		strSqlParams[29][0] = "30";
		strSqlParams[29][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[29][2] = shoppingCartOpr.getPaymentDVO().getReceivedNbBid();

		strSqlParams[30][0] = "31";
		strSqlParams[30][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[30][2] = shoppingCartOpr.getPaymentDVO().getReceivedNbOrderNumber();

		strSqlParams[31][0] = "32";
		strSqlParams[31][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[31][2] = shoppingCartOpr.getPaymentDVO().getReceivedNotes();

		strSqlParams[32][0] = "33";
		strSqlParams[32][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[32][2] = shoppingCartOpr.getPaymentDVO().getReceivedOrderId();

		strSqlParams[33][0] = "34";
		strSqlParams[33][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[33][2] = shoppingCartOpr.getPaymentDVO().getReceivedReturnUrl();

		strSqlParams[34][0] = "35";
		strSqlParams[34][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[34][2] = shoppingCartOpr.getPaymentDVO().getReceivedBillingAddress().getFirstName() + " "
				+ shoppingCartOpr.getPaymentDVO().getReceivedBillingAddress().getLastName();

		strSqlParams[35][0] = "36";
		strSqlParams[35][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[35][2] = shoppingCartOpr.getPaymentDVO().getReceivedBillingAddress().getAddressLine1();

		strSqlParams[36][0] = "37";
		strSqlParams[36][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[36][2] = shoppingCartOpr.getPaymentDVO().getReceivedBillingAddress().getCountryDvo().getName();

		strSqlParams[37][0] = "38";
		strSqlParams[37][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[37][2] = shoppingCartOpr.getPaymentDVO().getReceivedBillingAddress().getStateDVO().getCode();

		strSqlParams[38][0] = "39";
		strSqlParams[38][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[38][2] = shoppingCartOpr.getPaymentDVO().getReceivedBillingAddress().getPhone1();

		strSqlParams[39][0] = "40";
		strSqlParams[39][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[39][2] = shoppingCartOpr.getPaymentDVO().getReceivedBillingAddress().getEmail1();

		strSqlParams[40][0] = "41";
		strSqlParams[40][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[40][2] = shoppingCartOpr.getPaymentDVO().getReceivedBillingAddress().getCityDvo().getCode();
		// /shoppingCartOpr.getPaymentDVO().getReceivedBillingAddress().getBillingOther()
		// == null ? shoppingCartOpr
		// .getPaymentDVO().getReceivedBillingAddress().getCityRecord().getName()
		// :
		// shoppingCartOpr.getPaymentDVO().getReceivedBillingAddress().getBillingOther();

		strSqlParams[41][0] = "42";
		strSqlParams[41][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[41][2] = shoppingCartOpr.getPaymentDVO().getReceivedBillingAddress().getPinRecord().getCode();

		strSqlParams[42][0] = "43";
		strSqlParams[42][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[42][2] = shoppingCartOpr.getPaymentDVO().getReceivedShippingAddress().getFirstName() + " "
				+ shoppingCartOpr.getPaymentDVO().getReceivedShippingAddress().getLastName();

		strSqlParams[43][0] = "44";
		strSqlParams[43][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[43][2] = shoppingCartOpr.getPaymentDVO().getReceivedShippingAddress().getAddressLine1();

		strSqlParams[44][0] = "45";
		strSqlParams[44][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[44][2] = shoppingCartOpr.getPaymentDVO().getReceivedShippingAddress().getCountryDvo().getName();

		strSqlParams[45][0] = "46";
		strSqlParams[45][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[45][2] = shoppingCartOpr.getPaymentDVO().getReceivedShippingAddress().getStateDVO().getCode();

		strSqlParams[46][0] = "47";
		strSqlParams[46][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[46][2] = shoppingCartOpr.getPaymentDVO().getReceivedShippingAddress().getPhone1();

		strSqlParams[47][0] = "48";
		strSqlParams[47][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[47][2] = shoppingCartOpr.getPaymentDVO().getReceivedShippingAddress().getCityDvo().getCode();
		// shoppingCartOpr.getPaymentDVO().getReceivedShippingAddress().getShippingOther()
		// == null ? shoppingCartOpr
		// .getPaymentDVO().getReceivedShippingAddress().getCityRecord().getName()
		// :
		// shoppingCartOpr.getPaymentDVO().getReceivedShippingAddress().getShippingOther();

		strSqlParams[48][0] = "49";
		strSqlParams[48][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[48][2] = shoppingCartOpr.getPaymentDVO().getReceivedShippingAddress().getPinRecord().getCode();

		strSqlParams[49][0] = "50";
		strSqlParams[49][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[49][2] = shoppingCartOpr.getPaymentDVO().getErrorMessage();

		strSqlParams[50][0] = "51";
		strSqlParams[50][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[50][2] = shoppingCartOpr.getApplicationFlags().getApplicationFlagMap()
				.get(CommonConstant.LOGGED_USER_KEY);

		strSqlParams[51][0] = "52";
		strSqlParams[51][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[51][2] = shoppingCartOpr.getApplicationFlags().getApplicationFlagMap()
				.get(CommonConstant.LOGGED_USER_KEY);

		strSqlParams[52][0] = "53";
		strSqlParams[52][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[52][2] = "CC Avenue";

		// in the following call replace null with dynamic where clause if
		// required
		daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);

	}

	public Long getSavedCCAvenueTransactionPK(ShoppingCartOpr shoppingCartOpr) throws FrameworkException {
		// BACKING CLASS METHOD TEMPLATE ver 1.0
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		DAOResult daoResult = new DAOResult();

		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ShoppingCartSqlTemplate.GET_CC_AVENUE_TRANSACTION_PK);

		Object strSqlParams[][] = new Object[1][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[0][2] = shoppingCartOpr.getPaymentDVO().getSentCheckSum();

		// in the following call replace null with dynamic where clause if
		// required
		daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap responseMap = daoResult.getInvocationResult();
		myLog.debug(":: Resultset got ::" + responseMap);
		myLog.debug(":: Resultset got ::" + responseMap.size());

		Long primaryKey = null;
		for (int i = 0; i < responseMap.size(); i++) {
			HashMap resultRow = (HashMap) responseMap.get(i);
			if (resultRow.get("r_payment_transactions_id") != null) {
				primaryKey = Long.valueOf(resultRow.get("r_payment_transactions_id").toString());
			}
		}

		return primaryKey;
	}

	public void updateCCAvenuetransaction(ShoppingCartOpr shoppingCartOpr) throws FrameworkException {
		// BACKING CLASS METHOD TEMPLATE ver 1.0
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		DAOResult daoResult = new DAOResult();

		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.INSERT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ShoppingCartSqlTemplate.UPDATE_CC_AVENUE_TRANSACTION);

		Object strSqlParams[][] = new Object[30][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[0][2] = shoppingCartOpr.getPaymentDVO().getReceivedAmount();
		myLog.debug("strSqlParams[0][2]::" + strSqlParams[0][2]);

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[1][2] = shoppingCartOpr.getPaymentDVO().getReceivedAuthorizationDescription();
		myLog.debug("strSqlParams[1][2]::" + strSqlParams[1][2]);

		strSqlParams[2][0] = "3";
		strSqlParams[2][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[2][2] = shoppingCartOpr.getPaymentDVO().getReceivedBankName();
		myLog.debug("strSqlParams[2][2]::" + strSqlParams[2][2]);

		strSqlParams[3][0] = "4";
		strSqlParams[3][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[3][2] = shoppingCartOpr.getPaymentDVO().getReceivedCardCategory();
		myLog.debug("strSqlParams[3][2]::" + strSqlParams[3][2]);

		strSqlParams[4][0] = "5";
		strSqlParams[4][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[4][2] = shoppingCartOpr.getPaymentDVO().getReceivedCheckSum();
		myLog.debug("strSqlParams[4][2]::" + strSqlParams[4][2]);

		strSqlParams[5][0] = "6";
		strSqlParams[5][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[5][2] = shoppingCartOpr.getPaymentDVO().getReceivedMerchantId();
		myLog.debug("strSqlParams[5][2]::" + strSqlParams[5][2]);

		strSqlParams[6][0] = "7";
		strSqlParams[6][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[6][2] = shoppingCartOpr.getPaymentDVO().getReceivedMerchantParameter();
		myLog.debug("strSqlParams[6][2]::" + strSqlParams[6][2]);

		strSqlParams[7][0] = "8";
		strSqlParams[7][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[7][2] = shoppingCartOpr.getPaymentDVO().getReceivedNbBid();
		myLog.debug("strSqlParams[7][2]::" + strSqlParams[7][2]);

		strSqlParams[8][0] = "9";
		strSqlParams[8][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[8][2] = shoppingCartOpr.getPaymentDVO().getReceivedNbOrderNumber();
		myLog.debug("strSqlParams[8][2]::" + strSqlParams[8][2]);

		strSqlParams[9][0] = "10";
		strSqlParams[9][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[9][2] = shoppingCartOpr.getPaymentDVO().getReceivedNotes();
		myLog.debug("strSqlParams[9][2]::" + strSqlParams[9][2]);

		strSqlParams[10][0] = "11";
		strSqlParams[10][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[10][2] = shoppingCartOpr.getPaymentDVO().getReceivedOrderId();
		myLog.debug("strSqlParams[10][2]::" + strSqlParams[10][2]);

		strSqlParams[11][0] = "12";
		strSqlParams[11][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[11][2] = shoppingCartOpr.getPaymentDVO().getReceivedReturnUrl();
		myLog.debug("strSqlParams[11][2]::" + strSqlParams[11][2]);

		strSqlParams[12][0] = "13";
		strSqlParams[12][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[12][2] = shoppingCartOpr.getPaymentDVO().getReceivedBillingAddress().getFirstName();
		myLog.debug("strSqlParams[12][2]::" + strSqlParams[12][2]);

		strSqlParams[13][0] = "14";
		strSqlParams[13][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[13][2] = shoppingCartOpr.getPaymentDVO().getReceivedBillingAddress().getAddressLine1();
		myLog.debug("strSqlParams[13][2]::" + strSqlParams[13][2]);

		strSqlParams[14][0] = "15";
		strSqlParams[14][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[14][2] = shoppingCartOpr.getPaymentDVO().getReceivedBillingAddress().getCountryDvo().getName();
		myLog.debug("strSqlParams[14][2]::" + strSqlParams[14][2]);

		strSqlParams[15][0] = "16";
		strSqlParams[15][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[15][2] = shoppingCartOpr.getPaymentDVO().getReceivedBillingAddress().getStateDVO().getCode();
		myLog.debug("strSqlParams[15][2]::" + strSqlParams[15][2]);

		strSqlParams[16][0] = "17";
		strSqlParams[16][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[16][2] = shoppingCartOpr.getPaymentDVO().getReceivedBillingAddress().getPhone1();
		myLog.debug("strSqlParams[16][2]::" + strSqlParams[16][2]);

		strSqlParams[17][0] = "18";
		strSqlParams[17][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[17][2] = shoppingCartOpr.getPaymentDVO().getReceivedBillingAddress().getEmail1();
		myLog.debug("strSqlParams[17][2]::" + strSqlParams[17][2]);

		strSqlParams[18][0] = "19";
		strSqlParams[18][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[18][2] = shoppingCartOpr.getPaymentDVO().getReceivedBillingAddress().getCityDvo().getCode();
		myLog.debug("strSqlParams[18][2]::" + strSqlParams[18][2]);

		strSqlParams[19][0] = "20";
		strSqlParams[19][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[19][2] = shoppingCartOpr.getPaymentDVO().getReceivedBillingAddress().getPinRecord().getCode();
		myLog.debug("strSqlParams[19][2]::" + strSqlParams[19][2]);

		strSqlParams[20][0] = "21";
		strSqlParams[20][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[20][2] = shoppingCartOpr.getPaymentDVO().getReceivedShippingAddress().getFirstName();
		myLog.debug("strSqlParams[20][2]::" + strSqlParams[20][2]);

		strSqlParams[21][0] = "22";
		strSqlParams[21][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[21][2] = shoppingCartOpr.getPaymentDVO().getReceivedShippingAddress().getAddressLine1();
		myLog.debug("strSqlParams[21][2]::" + strSqlParams[21][2]);

		strSqlParams[22][0] = "23";
		strSqlParams[22][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[22][2] = shoppingCartOpr.getPaymentDVO().getReceivedShippingAddress().getCountryDvo().getName();
		myLog.debug("strSqlParams[22][2]::" + strSqlParams[22][2]);

		strSqlParams[23][0] = "24";
		strSqlParams[23][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[23][2] = shoppingCartOpr.getPaymentDVO().getReceivedShippingAddress().getStateDVO().getCode();
		myLog.debug("strSqlParams[23][2]::" + strSqlParams[23][2]);

		strSqlParams[24][0] = "25";
		strSqlParams[24][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[24][2] = shoppingCartOpr.getPaymentDVO().getReceivedShippingAddress().getPhone1();
		myLog.debug("strSqlParams[24][2]::" + strSqlParams[24][2]);

		strSqlParams[25][0] = "26";
		strSqlParams[25][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[25][2] = shoppingCartOpr.getPaymentDVO().getReceivedShippingAddress().getCityDvo().getCode();
		myLog.debug("strSqlParams[25][2]::" + strSqlParams[25][2]);

		strSqlParams[26][0] = "27";
		strSqlParams[26][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[26][2] = shoppingCartOpr.getPaymentDVO().getReceivedShippingAddress().getPinRecord().getCode();
		myLog.debug("strSqlParams[26][2]::" + strSqlParams[26][2]);

		strSqlParams[27][0] = "28";
		strSqlParams[27][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[27][2] = shoppingCartOpr.getPaymentDVO().getErrorMessage();
		myLog.debug("strSqlParams[27][2]::" + strSqlParams[27][2]);

		strSqlParams[28][0] = "29";
		strSqlParams[28][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[28][2] = shoppingCartOpr.getApplicationFlags().getApplicationFlagMap()
				.get(CommonConstant.LOGGED_USER_KEY);
		myLog.debug("strSqlParams[28][2]::" + strSqlParams[28][2]);

		strSqlParams[29][0] = "30";
		strSqlParams[29][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[29][2] = shoppingCartOpr.getPaymentDVO().getReceivedMerchantParameter();
		myLog.debug("id :::: " + shoppingCartOpr.getPaymentDVO().getReceivedMerchantParameter());

		// in the following call replace null with dynamic where clause if
		// required
		daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);

	}

	public ShoppingCartOpr insertCCAvenuePaymentGatewayDetails(ShoppingCartOpr shoppingCartOpr)
			throws FrameworkException {
		// BACKING CLASS METHOD TEMPLATE ver 1.0
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		DAOResult daoResult = new DAOResult();

		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.INSERT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ShoppingCartSqlTemplate.INSERT_CC_AVENUE_PAYMENT_TRANSACTIONS);

		Object strSqlParams[][] = new Object[57][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[0][2] = shoppingCartOpr.getPaymentDVO().getSentMerchantId();

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[1][2] = shoppingCartOpr.getPaymentDVO().getSentAmount();

		strSqlParams[2][0] = "3";
		strSqlParams[2][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[2][2] = shoppingCartOpr.getPaymentDVO().getSentOrderId();

		strSqlParams[3][0] = "4";
		strSqlParams[3][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[3][2] = shoppingCartOpr.getPaymentDVO().getSentRedirectUrl();

		strSqlParams[4][0] = "5";
		strSqlParams[4][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[4][2] = shoppingCartOpr.getPaymentDVO().getSentCheckSum();

		strSqlParams[5][0] = "6";
		strSqlParams[5][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[5][2] = shoppingCartOpr.getPaymentDVO().getSentBillingAddress().getFirstName() + " "
				+ shoppingCartOpr.getPaymentDVO().getSentBillingAddress().getLastName();

		strSqlParams[6][0] = "7";
		strSqlParams[6][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[6][2] = shoppingCartOpr.getPaymentDVO().getSentBillingAddress().getAddressLine1();

		strSqlParams[7][0] = "8";
		strSqlParams[7][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[7][2] = shoppingCartOpr.getPaymentDVO().getSentBillingAddress().getCountryDvo().getName();

		strSqlParams[8][0] = "9";
		strSqlParams[8][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[8][2] = shoppingCartOpr.getPaymentDVO().getSentBillingAddress().getStateDVO().getCode();

		strSqlParams[9][0] = "10";
		strSqlParams[9][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[9][2] = shoppingCartOpr.getPaymentDVO().getSentBillingAddress().getPhone1();

		strSqlParams[10][0] = "11";
		strSqlParams[10][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[10][2] = shoppingCartOpr.getPaymentDVO().getSentBillingAddress().getEmail1();

		strSqlParams[11][0] = "12";
		strSqlParams[11][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[11][2] = shoppingCartOpr.getPaymentDVO().getSentBillingAddress().getCityDvo().getCode();

		strSqlParams[12][0] = "13";
		strSqlParams[12][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[12][2] = shoppingCartOpr.getPaymentDVO().getSentBillingAddress().getPinRecord().getCode();

		strSqlParams[13][0] = "14";
		strSqlParams[13][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[13][2] = shoppingCartOpr.getPaymentDVO().getSentDeliveryAddress().getFirstName() + " "
				+ shoppingCartOpr.getPaymentDVO().getSentDeliveryAddress().getLastName();

		strSqlParams[14][0] = "15";
		strSqlParams[14][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[14][2] = shoppingCartOpr.getPaymentDVO().getSentDeliveryAddress().getAddressLine1();

		strSqlParams[15][0] = "16";
		strSqlParams[15][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[15][2] = shoppingCartOpr.getPaymentDVO().getSentDeliveryAddress().getCountryDvo().getName();

		strSqlParams[16][0] = "17";
		strSqlParams[16][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[16][2] = shoppingCartOpr.getPaymentDVO().getSentDeliveryAddress().getStateDVO().getCode();

		strSqlParams[17][0] = "18";
		strSqlParams[17][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[17][2] = shoppingCartOpr.getPaymentDVO().getSentDeliveryAddress().getPhone1();

		strSqlParams[18][0] = "19";
		strSqlParams[18][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[18][2] = shoppingCartOpr.getPaymentDVO().getSentDeliveryAddress().getCityDvo().getCode();
		;

		strSqlParams[19][0] = "20";
		strSqlParams[19][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[19][2] = shoppingCartOpr.getPaymentDVO().getSentDeliveryAddress().getPinRecord().getCode();

		strSqlParams[20][0] = "21";
		strSqlParams[20][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[20][2] = shoppingCartOpr.getPaymentDVO().getSentDeliveryNotes();

		strSqlParams[21][0] = "22";
		strSqlParams[21][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[21][2] = shoppingCartOpr.getPaymentDVO().getSentMerchantParameter();

		strSqlParams[22][0] = "23";
		strSqlParams[22][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[22][2] = shoppingCartOpr.getPaymentDVO().getReceivedAmount();

		strSqlParams[23][0] = "24";
		strSqlParams[23][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[23][2] = shoppingCartOpr.getPaymentDVO().getReceivedAuthorizationDescription();

		strSqlParams[24][0] = "25";
		strSqlParams[24][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[24][2] = shoppingCartOpr.getPaymentDVO().getReceivedBankName();

		strSqlParams[25][0] = "26";
		strSqlParams[25][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[25][2] = shoppingCartOpr.getPaymentDVO().getReceivedCardCategory();

		strSqlParams[26][0] = "27";
		strSqlParams[26][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[26][2] = shoppingCartOpr.getPaymentDVO().getReceivedCheckSum();

		strSqlParams[27][0] = "28";
		strSqlParams[27][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[27][2] = shoppingCartOpr.getPaymentDVO().getReceivedMerchantId();

		strSqlParams[28][0] = "29";
		strSqlParams[28][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[28][2] = shoppingCartOpr.getPaymentDVO().getReceivedMerchantParameter();

		strSqlParams[29][0] = "30";
		strSqlParams[29][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[29][2] = shoppingCartOpr.getPaymentDVO().getReceivedNbBid();

		strSqlParams[30][0] = "31";
		strSqlParams[30][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[30][2] = shoppingCartOpr.getPaymentDVO().getReceivedNbOrderNumber();

		strSqlParams[31][0] = "32";
		strSqlParams[31][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[31][2] = shoppingCartOpr.getPaymentDVO().getReceivedNotes();

		strSqlParams[32][0] = "33";
		strSqlParams[32][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[32][2] = shoppingCartOpr.getPaymentDVO().getReceivedOrderId();

		strSqlParams[33][0] = "34";
		strSqlParams[33][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[33][2] = shoppingCartOpr.getPaymentDVO().getReceivedReturnUrl();

		strSqlParams[34][0] = "35";
		strSqlParams[34][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[34][2] = shoppingCartOpr.getPaymentDVO().getReceivedBillingAddress().getFirstName() + " "
				+ shoppingCartOpr.getPaymentDVO().getReceivedBillingAddress().getLastName();

		strSqlParams[35][0] = "36";
		strSqlParams[35][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[35][2] = shoppingCartOpr.getPaymentDVO().getReceivedBillingAddress().getAddressLine1();

		strSqlParams[36][0] = "37";
		strSqlParams[36][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[36][2] = shoppingCartOpr.getPaymentDVO().getReceivedBillingAddress().getCountryDvo().getName();

		strSqlParams[37][0] = "38";
		strSqlParams[37][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[37][2] = shoppingCartOpr.getPaymentDVO().getReceivedBillingAddress().getStateDVO().getCode();

		strSqlParams[38][0] = "39";
		strSqlParams[38][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[38][2] = shoppingCartOpr.getPaymentDVO().getReceivedBillingAddress().getPhone1();

		strSqlParams[39][0] = "40";
		strSqlParams[39][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[39][2] = shoppingCartOpr.getPaymentDVO().getReceivedBillingAddress().getEmail1();

		strSqlParams[40][0] = "41";
		strSqlParams[40][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[40][2] = shoppingCartOpr.getPaymentDVO().getReceivedBillingAddress().getCityDvo().getCode();

		strSqlParams[41][0] = "42";
		strSqlParams[41][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[41][2] = shoppingCartOpr.getPaymentDVO().getReceivedBillingAddress().getPinRecord().getCode();

		strSqlParams[42][0] = "43";
		strSqlParams[42][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[42][2] = shoppingCartOpr.getPaymentDVO().getReceivedShippingAddress().getFirstName() + " "
				+ shoppingCartOpr.getPaymentDVO().getReceivedShippingAddress().getLastName();

		strSqlParams[43][0] = "44";
		strSqlParams[43][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[43][2] = shoppingCartOpr.getPaymentDVO().getReceivedShippingAddress().getAddressLine1();

		strSqlParams[44][0] = "45";
		strSqlParams[44][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[44][2] = shoppingCartOpr.getPaymentDVO().getReceivedShippingAddress().getCountryDvo().getName();

		strSqlParams[45][0] = "46";
		strSqlParams[45][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[45][2] = shoppingCartOpr.getPaymentDVO().getReceivedShippingAddress().getStateDVO().getCode();

		strSqlParams[46][0] = "47";
		strSqlParams[46][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[46][2] = shoppingCartOpr.getPaymentDVO().getReceivedShippingAddress().getPhone1();

		strSqlParams[47][0] = "48";
		strSqlParams[47][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[47][2] = shoppingCartOpr.getPaymentDVO().getReceivedShippingAddress().getCityDvo().getCode();

		strSqlParams[48][0] = "49";
		strSqlParams[48][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[48][2] = shoppingCartOpr.getPaymentDVO().getReceivedShippingAddress().getPinRecord().getCode();

		strSqlParams[49][0] = "50";
		strSqlParams[49][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[49][2] = shoppingCartOpr.getPaymentDVO().getErrorMessage();

		strSqlParams[50][0] = "51";
		strSqlParams[50][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[50][2] = shoppingCartOpr.getApplicationFlags().getApplicationFlagMap()
				.get(CommonConstant.LOGGED_USER_KEY);

		strSqlParams[51][0] = "52";
		strSqlParams[51][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[51][2] = shoppingCartOpr.getApplicationFlags().getApplicationFlagMap()
				.get(CommonConstant.LOGGED_USER_KEY);

		strSqlParams[52][0] = "53";
		strSqlParams[52][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[52][2] = null;

		strSqlParams[53][0] = "54";
		strSqlParams[53][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[53][2] = null;

		strSqlParams[54][0] = "55";
		strSqlParams[54][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[54][2] = null;

		strSqlParams[55][0] = "56";
		strSqlParams[55][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[55][2] = null;

		strSqlParams[56][0] = "57";
		strSqlParams[56][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[56][2] = null;
		daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);

		return shoppingCartOpr;
	}

	public ShoppingCartOpr getReceivedOrderIdOnPaymentTransactionIdForCCAvenue(
			OperationalDataValueObject searchParameters) throws FrameworkException, BusinessException {
		// BACKING CLASS METHOD TEMPLATE ver 1.0
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		DAOResult daoResult = new DAOResult();
		ShoppingCartOpr shoppingCartOpr = (ShoppingCartOpr) searchParameters;

		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT,
				ShoppingCartSqlTemplate.GET_PAYMENT_DETAILS_FOR_RECEIVED_MERCHANT_PARAMETER);

		Object strSqlParams[][] = new Object[1][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[0][2] = shoppingCartOpr.getPaymentDVO().getReceivedMerchantParameter();
		myLog.debug("Received Merchant Parameter : " + shoppingCartOpr.getPaymentDVO().getReceivedMerchantParameter());

		// in the following call replace null with dynamic where clause if
		// required
		daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap responseMap = daoResult.getInvocationResult();
		myLog.error(":: Resultset got ::" + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {
				HashMap resultRow = (HashMap) responseMap.get(i);

				// paymentDVO.setReceivedOrderId((String) resultRow
				// .get("sent_order_id"));
				// myLog.debug("sent order id ::::"
				// + paymentDVO.getReceivedOrderId());
				// shoppingCartOpr.setPaymentDVO(paymentDVO);

				if (resultRow.get("received_merchant_parameter") != null) {
					shoppingCartOpr.getRetailOrderRecord().setId(
							Long.valueOf(resultRow.get("received_merchant_parameter").toString()));
					myLog.debug("order id got :::: " + shoppingCartOpr.getRetailOrderRecord().getId());
				}
			}
		} else {
			myLog.error("PaymentBC :: getPaymentRecordOnReceivedMerchantParameter :: did not find the data related to id provided");
			throw new BusinessException("no_data_from_db_excep_msg");
		}

		return shoppingCartOpr;
	}

	public ShoppingCartOpr getPaymentCurrency(OperationalDataValueObject searchParameters) throws FrameworkException,
			BusinessException {
		// BACKING CLASS METHOD TEMPLATE ver 1.0
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		DAOResult daoResult = new DAOResult();
		ShoppingCartOpr shoppingCartOpr = (ShoppingCartOpr) searchParameters;

		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ShoppingCartSqlTemplate.GET_PAYMENT_CONVERSION);

		Object strSqlParams[][] = new Object[1][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[0][2] = shoppingCartOpr.getCurrencyRecord().getCurrencySymbol();
		myLog.debug("check for symbol in bc : " + strSqlParams[0][2]);

		// in the following call replace null with dynamic where clause if
		// required
		daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap responseMap = daoResult.getInvocationResult();
		myLog.error(":: Resultset got ::" + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {
				HashMap resultRow = (HashMap) responseMap.get(i);

				if (resultRow.get("payment_multiplier") != null) {
					shoppingCartOpr.getCurrencyRecord().setPaymentMultiplier(
							(Float) resultRow.get("payment_multiplier"));
					myLog.debug("order id got :::: " + shoppingCartOpr.getRetailOrderRecord().getId());
				}

			}
		} else {
			myLog.error("PaymentBC :: getPaymentCurrency :: did not find the data related to id provided");
			throw new BusinessException("no_data_from_db_excep_msg");
		}

		return shoppingCartOpr;
	}

	// added by ketan for paypal
	public ShoppingCartOpr savePayPalInitTransaction(ShoppingCartOpr shoppingCartOpr) throws FrameworkException {
		// BACKING CLASS METHOD TEMPLATE ver 1.0
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		DAOResult daoResult = new DAOResult();

		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.INSERT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ShoppingCartSqlTemplate.INSERT_PAYPAL_PAYMENT_TRANSACTIONS);

		Object strSqlParams[][] = new Object[25][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[0][2] = shoppingCartOpr.getPaymentDVO().getSentAmount();

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[1][2] = shoppingCartOpr.getPaymentDVO().getSentMerchantParameter();

		strSqlParams[2][0] = "3";
		strSqlParams[2][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[2][2] = shoppingCartOpr.getPaymentDVO().getSentRedirectUrl();

		strSqlParams[3][0] = "4";
		strSqlParams[3][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[3][2] = shoppingCartOpr.getPaymentDVO().getSentBillingAddress().getFirstName() + " "
				+ shoppingCartOpr.getPaymentDVO().getSentBillingAddress().getLastName();

		strSqlParams[4][0] = "5";
		strSqlParams[4][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[4][2] = shoppingCartOpr.getPaymentDVO().getSentBillingAddress().getAddressLine1();

		strSqlParams[5][0] = "6";
		strSqlParams[5][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[5][2] = shoppingCartOpr.getPaymentDVO().getSentBillingAddress().getCountryDvo().getName();

		strSqlParams[6][0] = "7";
		strSqlParams[6][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[6][2] = shoppingCartOpr.getPaymentDVO().getSentBillingAddress().getStateDVO().getCode();

		strSqlParams[7][0] = "8";
		strSqlParams[7][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[7][2] = shoppingCartOpr.getPaymentDVO().getSentBillingAddress().getPhone1();

		strSqlParams[8][0] = "9";
		strSqlParams[8][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[8][2] = shoppingCartOpr.getPaymentDVO().getSentBillingAddress().getEmail1();

		strSqlParams[9][0] = "10";
		strSqlParams[9][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[9][2] = shoppingCartOpr.getPaymentDVO().getSentBillingAddress().getCityDvo().getCode();

		strSqlParams[10][0] = "11";
		strSqlParams[10][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[10][2] = shoppingCartOpr.getPaymentDVO().getSentBillingAddress().getPinRecord().getCode();

		strSqlParams[11][0] = "12";
		strSqlParams[11][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[11][2] = shoppingCartOpr.getPaymentDVO().getSentDeliveryAddress().getFirstName() + " "
				+ shoppingCartOpr.getPaymentDVO().getSentDeliveryAddress().getLastName();

		strSqlParams[12][0] = "13";
		strSqlParams[12][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[12][2] = shoppingCartOpr.getPaymentDVO().getSentDeliveryAddress().getAddressLine1();

		strSqlParams[13][0] = "14";
		strSqlParams[13][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[13][2] = shoppingCartOpr.getPaymentDVO().getSentDeliveryAddress().getCountryDvo().getName();

		strSqlParams[14][0] = "15";
		strSqlParams[14][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[14][2] = shoppingCartOpr.getPaymentDVO().getSentDeliveryAddress().getStateDVO().getCode();

		strSqlParams[15][0] = "16";
		strSqlParams[15][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[15][2] = shoppingCartOpr.getPaymentDVO().getSentDeliveryAddress().getPhone1();

		strSqlParams[16][0] = "17";
		strSqlParams[16][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[16][2] = shoppingCartOpr.getPaymentDVO().getSentDeliveryAddress().getCityDvo().getCode();

		strSqlParams[17][0] = "18";
		strSqlParams[17][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[17][2] = shoppingCartOpr.getPaymentDVO().getSentDeliveryAddress().getPinRecord().getCode();

		strSqlParams[18][0] = "19";
		strSqlParams[18][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[18][2] = shoppingCartOpr.getPaymentDVO().getErrorMessage();

		strSqlParams[19][0] = "20";
		strSqlParams[19][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[19][2] = shoppingCartOpr.getApplicationFlags().getApplicationFlagMap()
				.get(CommonConstant.LOGGED_USER_KEY);

		strSqlParams[20][0] = "21";
		strSqlParams[20][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[20][2] = shoppingCartOpr.getApplicationFlags().getApplicationFlagMap()
				.get(CommonConstant.LOGGED_USER_KEY);

		strSqlParams[21][0] = "22";
		strSqlParams[21][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[21][2] = shoppingCartOpr.getPaymentDVO().getPayPalAck();

		strSqlParams[22][0] = "23";
		strSqlParams[22][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[22][2] = shoppingCartOpr.getPaymentDVO().getSentToken();

		strSqlParams[23][0] = "24";
		strSqlParams[23][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[23][2] = shoppingCartOpr.getPaymentDVO().getSentCancelUrl();

		strSqlParams[24][0] = "25";
		strSqlParams[24][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[24][2] = shoppingCartOpr.getPaymentDVO().getSentCurrencyCode();

		daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);

		return shoppingCartOpr;
	}

	public void updatePayPaltransaction(ShoppingCartOpr shoppingCartOpr) throws FrameworkException {
		// BACKING CLASS METHOD TEMPLATE ver 1.0
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		DAOResult daoResult = new DAOResult();

		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.UPDATE_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ShoppingCartSqlTemplate.UPDATE_PAYPAL_TRANSACTION);

		Object strSqlParams[][] = new Object[6][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[0][2] = shoppingCartOpr.getPaymentDVO().getRecievedToken();
		myLog.debug("strSqlParams[0][2]::" + strSqlParams[0][2]);

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[1][2] = shoppingCartOpr.getPaymentDVO().getRecievedPayerId();
		myLog.debug("strSqlParams[1][2]::" + strSqlParams[1][2]);

		strSqlParams[2][0] = "3";
		strSqlParams[2][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[2][2] = shoppingCartOpr.getPaymentDVO().getRecievedPayerStatus();
		myLog.debug("strSqlParams[2][2]::" + strSqlParams[2][2]);

		strSqlParams[3][0] = "4";
		strSqlParams[3][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[3][2] = shoppingCartOpr.getPaymentDVO().getReceivedOrderId();
		myLog.debug("strSqlParams[3][2]::" + strSqlParams[3][2]);

		strSqlParams[4][0] = "5";
		strSqlParams[4][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[4][2] = shoppingCartOpr.getApplicationFlags().getApplicationFlagMap()
				.get(CommonConstant.LOGGED_USER_KEY);
		myLog.debug("strSqlParams[4][2]::" + strSqlParams[4][2]);

		strSqlParams[5][0] = "6";
		strSqlParams[5][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[5][2] = shoppingCartOpr.getPaymentDVO().getReceivedOrderId();
		myLog.debug("id :::: " + shoppingCartOpr.getPaymentDVO().getReceivedOrderId());

		daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);

	}

	public ShoppingCartOpr getDetailsForDoExpressAndInvoice(OperationalDataValueObject searchParameters)
			throws FrameworkException, BusinessException {
		// BACKING CLASS METHOD TEMPLATE ver 1.0
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		DAOResult daoResult = new DAOResult();
		ShoppingCartOpr shoppingCartOpr = (ShoppingCartOpr) searchParameters;

		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ShoppingCartSqlTemplate.GET_PAYMENT_DETAILS_FOR_DO_EXPRESS_PAYMENT);

		Object strSqlParams[][] = new Object[1][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[0][2] = shoppingCartOpr.getPaymentDVO().getReceivedOrderId();
		myLog.debug("Received Merchant Parameter : " + shoppingCartOpr.getPaymentDVO().getReceivedOrderId());

		// in the following call replace null with dynamic where clause if
		// required
		daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap responseMap = daoResult.getInvocationResult();
		myLog.error(":: Resultset got ::" + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {
				HashMap resultRow = (HashMap) responseMap.get(i);

				// if (resultRow.get("received_order_id") != null) {
				// shoppingCartOpr.getRetailOrderRecord().setId(
				// Long.valueOf(resultRow.get("received_order_id").toString()));
				// myLog.debug("order id got :::: " +
				// shoppingCartOpr.getRetailOrderRecord().getId());
				// }

				shoppingCartOpr.getPaymentDVO().setSentOrderId((String) resultRow.get("sent_order_id"));
				shoppingCartOpr.getPaymentDVO().setReceivedOrderId((String) resultRow.get("received_order_id"));
				shoppingCartOpr.getPaymentDVO().setSentToken((String) resultRow.get("sent_token"));
				shoppingCartOpr.getPaymentDVO().setRecievedToken((String) resultRow.get("recieved_token"));
				shoppingCartOpr.getPaymentDVO().setSentCurrencyCode((String) resultRow.get("sent_currency"));
				shoppingCartOpr.getPaymentDVO().setSentAmount((String) resultRow.get("sent_amount"));
				shoppingCartOpr.getPaymentDVO().setRecievedPayerId((String) resultRow.get("recieved_payer_id"));
			}
		} else {
			myLog.error("PaymentBC :: getPaymentRecordOnReceivedMerchantParameter :: did not find the data related to id provided");
			throw new BusinessException("no_data_from_db_excep_msg");
		}

		return shoppingCartOpr;
	}

	public ShoppingCartOpr updateForTransactionIdPayPal(ShoppingCartOpr shoppingCartOpr) throws FrameworkException {
		// BACKING CLASS METHOD TEMPLATE ver 1.0
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		DAOResult daoResult = new DAOResult();

		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.UPDATE_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ShoppingCartSqlTemplate.UPDATE_PAYPAL_TRANSACTION_ID);

		Object strSqlParams[][] = new Object[2][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[0][2] = shoppingCartOpr.getPaymentDVO().getReceivedTransactionId();
		myLog.debug("strSqlParams[0][2]::" + strSqlParams[0][2]);

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[1][2] = shoppingCartOpr.getPaymentDVO().getReceivedOrderId();
		myLog.debug("strSqlParams[1][2]::" + strSqlParams[1][2]);

		daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		return shoppingCartOpr;

	}

	public void updateErrorForPayPal(ShoppingCartOpr shoppingCartOpr) throws FrameworkException {
		// BACKING CLASS METHOD TEMPLATE ver 1.0
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		DAOResult daoResult = new DAOResult();

		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.UPDATE_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ShoppingCartSqlTemplate.UPDATE_PAYPAL_ERROR);

		Object strSqlParams[][] = new Object[2][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[0][2] = shoppingCartOpr.getPaymentDVO().getErrorMessage();
		myLog.debug("strSqlParams[0][2]::" + strSqlParams[0][2]);

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[1][2] = shoppingCartOpr.getPaymentDVO().getReceivedOrderId();
		myLog.debug("strSqlParams[1][2]::" + strSqlParams[1][2]);

		daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);

	}

	// added by ketan for payu

	public void updatePayUtransaction(ShoppingCartOpr shoppingCartOpr) throws FrameworkException {
		// BACKING CLASS METHOD TEMPLATE ver 1.0
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		// DAOResult daoResult = new DAOResult();

		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.INSERT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ShoppingCartSqlTemplate.UPDATE_PAY_U_TRANSACTION);

		Object strSqlParams[][] = new Object[24][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[0][2] = shoppingCartOpr.getPaymentDVO().getReceivedAmount();
		myLog.debug("strSqlParams[0][2]::" + strSqlParams[0][2]);

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[1][2] = shoppingCartOpr.getPaymentDVO().getReceivedBankName();
		myLog.debug("strSqlParams[1][2]::" + strSqlParams[1][2]);

		strSqlParams[2][0] = "3";
		strSqlParams[2][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[2][2] = shoppingCartOpr.getPaymentDVO().getReceivedCardCategory();
		myLog.debug("strSqlParams[2][2]::" + strSqlParams[2][2]);

		strSqlParams[3][0] = "4";
		strSqlParams[3][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[3][2] = shoppingCartOpr.getPaymentDVO().getReceivedCheckSum();
		myLog.debug("strSqlParams[3][2]::" + strSqlParams[3][2]);

		strSqlParams[4][0] = "5";
		strSqlParams[4][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[4][2] = shoppingCartOpr.getPaymentDVO().getReceivedMerchantId();
		myLog.debug("strSqlParams[4][2]::" + strSqlParams[4][2]);

		strSqlParams[5][0] = "6";
		strSqlParams[5][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[5][2] = shoppingCartOpr.getPaymentDVO().getReceivedNbBid();
		myLog.debug("strSqlParams[5][2]::" + strSqlParams[5][2]);

		strSqlParams[6][0] = "7";
		strSqlParams[6][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[6][2] = shoppingCartOpr.getPaymentDVO().getReceivedNbOrderNumber();
		myLog.debug("strSqlParams[6][2]::" + strSqlParams[6][2]);

		strSqlParams[7][0] = "8";
		strSqlParams[7][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[7][2] = shoppingCartOpr.getPaymentDVO().getReceivedNotes();
		myLog.debug("strSqlParams[9][2]::" + strSqlParams[7][2]);

		strSqlParams[8][0] = "9";
		strSqlParams[8][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[8][2] = shoppingCartOpr.getPaymentDVO().getReceivedOrderId();
		myLog.debug("strSqlParams[8][2]::" + strSqlParams[8][2]);

		strSqlParams[9][0] = "10";
		strSqlParams[9][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[9][2] = shoppingCartOpr.getPaymentDVO().getReceivedBillingAddress().getFirstName();
		myLog.debug("strSqlParams[9][2]::" + strSqlParams[9][2]);

		strSqlParams[10][0] = "11";
		strSqlParams[10][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[10][2] = shoppingCartOpr.getPaymentDVO().getReceivedBillingAddress().getAddressLine1();
		myLog.debug("strSqlParams[10][2]::" + strSqlParams[10][2]);

		strSqlParams[11][0] = "12";
		strSqlParams[11][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[11][2] = shoppingCartOpr.getPaymentDVO().getReceivedBillingAddress().getCountryDvo().getName();
		myLog.debug("strSqlParams[11][2]::" + strSqlParams[11][2]);

		strSqlParams[12][0] = "13";
		strSqlParams[12][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[12][2] = shoppingCartOpr.getPaymentDVO().getReceivedBillingAddress().getStateDVO().getCode();
		myLog.debug("strSqlParams[12][2]::" + strSqlParams[12][2]);

		strSqlParams[13][0] = "14";
		strSqlParams[13][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[13][2] = shoppingCartOpr.getPaymentDVO().getReceivedBillingAddress().getPhone1();
		myLog.debug("strSqlParams[13][2]::" + strSqlParams[13][2]);

		strSqlParams[14][0] = "15";
		strSqlParams[14][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[14][2] = shoppingCartOpr.getPaymentDVO().getReceivedBillingAddress().getEmail1();
		myLog.debug("strSqlParams[14][2]::" + strSqlParams[14][2]);

		strSqlParams[15][0] = "16";
		strSqlParams[15][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[15][2] = shoppingCartOpr.getPaymentDVO().getReceivedBillingAddress().getCityDvo().getCode();
		myLog.debug("strSqlParams[15][2]::" + strSqlParams[15][2]);

		strSqlParams[16][0] = "17";
		strSqlParams[16][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[16][2] = shoppingCartOpr.getPaymentDVO().getReceivedBillingAddress().getPinRecord().getCode();
		myLog.debug("strSqlParams[16][2]::" + strSqlParams[16][2]);

		strSqlParams[17][0] = "18";
		strSqlParams[17][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[17][2] = shoppingCartOpr.getPaymentDVO().getErrorMessage();
		myLog.debug("strSqlParams[17][2]::" + strSqlParams[17][2]);

		strSqlParams[18][0] = "19";
		strSqlParams[18][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[18][2] = shoppingCartOpr.getPaymentDVO().getReceivedTransactionId();
		myLog.debug("strSqlParams[18][2]::" + strSqlParams[18][2]);

		strSqlParams[19][0] = "20";
		strSqlParams[19][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[19][2] = shoppingCartOpr.getPaymentDVO().getReceivedTransactionDate();
		myLog.debug("strSqlParams[19][2]::" + strSqlParams[20][2]);

		strSqlParams[20][0] = "21";
		strSqlParams[20][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[20][2] = shoppingCartOpr.getPaymentDVO().getReceivedServerMode();
		myLog.debug("strSqlParams[20][2]::" + strSqlParams[21][2]);

		strSqlParams[21][0] = "22";
		strSqlParams[21][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[21][2] = shoppingCartOpr.getPaymentDVO().getReceivedTransactionReferenceNumber();
		myLog.debug("strSqlParams[21][2]::" + strSqlParams[22][2]);

		strSqlParams[22][0] = "23";
		strSqlParams[22][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[22][2] = shoppingCartOpr.getApplicationFlags().getApplicationFlagMap()
				.get(CommonConstant.LOGGED_USER_KEY);
		myLog.debug("strSqlParams[22][2]::" + strSqlParams[22][2]);

		strSqlParams[23][0] = "24";
		strSqlParams[23][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[23][2] = shoppingCartOpr.getPaymentDVO().getReceivedOrderId();
		myLog.debug("id :::: " + shoppingCartOpr.getPaymentDVO().getReceivedOrderId());

		// in the following call replace null with dynamic where clause if
		// required
		performDBOperation(queryDetailsMap, strSqlParams, null);

	}

	// added by ketan for payu

	public void savePayuInitTransaction(ShoppingCartOpr shoppingCartOpr) throws FrameworkException {
		// BACKING CLASS METHOD TEMPLATE ver 1.0
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		DAOResult daoResult = new DAOResult();

		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.INSERT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ShoppingCartSqlTemplate.INIT_PAY_U_TRANSACTION);

		Object strSqlParams[][] = new Object[54][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[0][2] = shoppingCartOpr.getPaymentDVO().getSentMerchantId();

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[1][2] = shoppingCartOpr.getPaymentDVO().getSentAmount();

		strSqlParams[2][0] = "3";
		strSqlParams[2][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[2][2] = shoppingCartOpr.getPaymentDVO().getSentOrderId();

		strSqlParams[3][0] = "4";
		strSqlParams[3][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[3][2] = shoppingCartOpr.getPaymentDVO().getSentRedirectUrl();

		strSqlParams[4][0] = "5";
		strSqlParams[4][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[4][2] = shoppingCartOpr.getPaymentDVO().getSentCheckSum();

		strSqlParams[5][0] = "6";
		strSqlParams[5][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[5][2] = shoppingCartOpr.getPaymentDVO().getSentBillingAddress().getFirstName() + " "
				+ shoppingCartOpr.getPaymentDVO().getSentBillingAddress().getLastName();

		strSqlParams[6][0] = "7";
		strSqlParams[6][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[6][2] = shoppingCartOpr.getPaymentDVO().getSentBillingAddress().getAddressLine1();

		strSqlParams[7][0] = "8";
		strSqlParams[7][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[7][2] = shoppingCartOpr.getPaymentDVO().getSentBillingAddress().getCountryDvo().getName();

		strSqlParams[8][0] = "9";
		strSqlParams[8][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[8][2] = shoppingCartOpr.getPaymentDVO().getSentBillingAddress().getStateDVO().getCode();

		strSqlParams[9][0] = "10";
		strSqlParams[9][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[9][2] = shoppingCartOpr.getPaymentDVO().getSentBillingAddress().getPhone1();

		strSqlParams[10][0] = "11";
		strSqlParams[10][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[10][2] = shoppingCartOpr.getPaymentDVO().getSentBillingAddress().getEmail1();

		strSqlParams[11][0] = "12";
		strSqlParams[11][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[11][2] = shoppingCartOpr.getPaymentDVO().getSentBillingAddress().getCityDvo().getCode();

		strSqlParams[12][0] = "13";
		strSqlParams[12][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[12][2] = shoppingCartOpr.getPaymentDVO().getSentBillingAddress().getPinRecord().getCode();

		strSqlParams[13][0] = "14";
		strSqlParams[13][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[13][2] = shoppingCartOpr.getPaymentDVO().getSentDeliveryAddress().getFirstName() + " "
				+ shoppingCartOpr.getPaymentDVO().getSentDeliveryAddress().getLastName();

		strSqlParams[14][0] = "15";
		strSqlParams[14][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[14][2] = shoppingCartOpr.getPaymentDVO().getSentDeliveryAddress().getAddressLine1();

		strSqlParams[15][0] = "16";
		strSqlParams[15][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[15][2] = shoppingCartOpr.getPaymentDVO().getSentDeliveryAddress().getCountryDvo().getName();

		strSqlParams[16][0] = "17";
		strSqlParams[16][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[16][2] = shoppingCartOpr.getPaymentDVO().getSentDeliveryAddress().getStateDVO().getCode();

		strSqlParams[17][0] = "18";
		strSqlParams[17][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[17][2] = shoppingCartOpr.getPaymentDVO().getSentDeliveryAddress().getPhone1();

		strSqlParams[18][0] = "19";
		strSqlParams[18][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[18][2] = shoppingCartOpr.getPaymentDVO().getSentDeliveryAddress().getCityDvo().getCode();

		strSqlParams[19][0] = "20";
		strSqlParams[19][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[19][2] = shoppingCartOpr.getPaymentDVO().getSentDeliveryAddress().getPinRecord().getCode();

		strSqlParams[20][0] = "21";
		strSqlParams[20][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[20][2] = shoppingCartOpr.getPaymentDVO().getSentDeliveryNotes();

		strSqlParams[21][0] = "22";
		strSqlParams[21][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[21][2] = shoppingCartOpr.getPaymentDVO().getSentMerchantParameter();

		strSqlParams[22][0] = "23";
		strSqlParams[22][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[22][2] = shoppingCartOpr.getPaymentDVO().getReceivedAmount();

		strSqlParams[23][0] = "24";
		strSqlParams[23][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[23][2] = shoppingCartOpr.getPaymentDVO().getReceivedAuthorizationDescription();

		strSqlParams[24][0] = "25";
		strSqlParams[24][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[24][2] = shoppingCartOpr.getPaymentDVO().getReceivedBankName();

		strSqlParams[25][0] = "26";
		strSqlParams[25][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[25][2] = shoppingCartOpr.getPaymentDVO().getReceivedCardCategory();

		strSqlParams[26][0] = "27";
		strSqlParams[26][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[26][2] = shoppingCartOpr.getPaymentDVO().getReceivedCheckSum();

		strSqlParams[27][0] = "28";
		strSqlParams[27][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[27][2] = shoppingCartOpr.getPaymentDVO().getReceivedMerchantId();

		strSqlParams[28][0] = "29";
		strSqlParams[28][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[28][2] = shoppingCartOpr.getPaymentDVO().getReceivedMerchantParameter();

		strSqlParams[29][0] = "30";
		strSqlParams[29][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[29][2] = shoppingCartOpr.getPaymentDVO().getReceivedNbBid();

		strSqlParams[30][0] = "31";
		strSqlParams[30][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[30][2] = shoppingCartOpr.getPaymentDVO().getReceivedNbOrderNumber();

		strSqlParams[31][0] = "32";
		strSqlParams[31][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[31][2] = shoppingCartOpr.getPaymentDVO().getReceivedNotes();

		strSqlParams[32][0] = "33";
		strSqlParams[32][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[32][2] = shoppingCartOpr.getPaymentDVO().getReceivedOrderId();

		strSqlParams[33][0] = "34";
		strSqlParams[33][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[33][2] = shoppingCartOpr.getPaymentDVO().getReceivedReturnUrl();

		strSqlParams[34][0] = "35";
		strSqlParams[34][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[34][2] = shoppingCartOpr.getPaymentDVO().getReceivedBillingAddress().getFirstName() + " "
				+ shoppingCartOpr.getPaymentDVO().getReceivedBillingAddress().getLastName();

		strSqlParams[35][0] = "36";
		strSqlParams[35][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[35][2] = shoppingCartOpr.getPaymentDVO().getReceivedBillingAddress().getAddressLine1();

		strSqlParams[36][0] = "37";
		strSqlParams[36][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[36][2] = shoppingCartOpr.getPaymentDVO().getReceivedBillingAddress().getCountryDvo().getName();

		strSqlParams[37][0] = "38";
		strSqlParams[37][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[37][2] = shoppingCartOpr.getPaymentDVO().getReceivedBillingAddress().getStateDVO().getCode();

		strSqlParams[38][0] = "39";
		strSqlParams[38][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[38][2] = shoppingCartOpr.getPaymentDVO().getReceivedBillingAddress().getPhone1();

		strSqlParams[39][0] = "40";
		strSqlParams[39][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[39][2] = shoppingCartOpr.getPaymentDVO().getReceivedBillingAddress().getEmail1();

		strSqlParams[40][0] = "41";
		strSqlParams[40][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[40][2] = shoppingCartOpr.getPaymentDVO().getReceivedBillingAddress().getCityDvo().getCode();
		// /shoppingCartOpr.getPaymentDVO().getReceivedBillingAddress().getBillingOther()
		// == null ? shoppingCartOpr
		// .getPaymentDVO().getReceivedBillingAddress().getCityRecord().getName()
		// :
		// shoppingCartOpr.getPaymentDVO().getReceivedBillingAddress().getBillingOther();

		strSqlParams[41][0] = "42";
		strSqlParams[41][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[41][2] = shoppingCartOpr.getPaymentDVO().getReceivedBillingAddress().getPinRecord().getCode();

		strSqlParams[42][0] = "43";
		strSqlParams[42][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[42][2] = shoppingCartOpr.getPaymentDVO().getReceivedShippingAddress().getFirstName() + " "
				+ shoppingCartOpr.getPaymentDVO().getReceivedShippingAddress().getLastName();

		strSqlParams[43][0] = "44";
		strSqlParams[43][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[43][2] = shoppingCartOpr.getPaymentDVO().getReceivedShippingAddress().getAddressLine1();

		strSqlParams[44][0] = "45";
		strSqlParams[44][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[44][2] = shoppingCartOpr.getPaymentDVO().getReceivedShippingAddress().getCountryDvo().getName();

		strSqlParams[45][0] = "46";
		strSqlParams[45][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[45][2] = shoppingCartOpr.getPaymentDVO().getReceivedShippingAddress().getStateDVO().getCode();

		strSqlParams[46][0] = "47";
		strSqlParams[46][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[46][2] = shoppingCartOpr.getPaymentDVO().getReceivedShippingAddress().getPhone1();

		strSqlParams[47][0] = "48";
		strSqlParams[47][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[47][2] = shoppingCartOpr.getPaymentDVO().getReceivedShippingAddress().getCityDvo().getCode();
		// shoppingCartOpr.getPaymentDVO().getReceivedShippingAddress().getShippingOther()
		// == null ? shoppingCartOpr
		// .getPaymentDVO().getReceivedShippingAddress().getCityRecord().getName()
		// :
		// shoppingCartOpr.getPaymentDVO().getReceivedShippingAddress().getShippingOther();

		strSqlParams[48][0] = "49";
		strSqlParams[48][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[48][2] = shoppingCartOpr.getPaymentDVO().getReceivedShippingAddress().getPinRecord().getCode();

		strSqlParams[49][0] = "50";
		strSqlParams[49][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[49][2] = shoppingCartOpr.getPaymentDVO().getErrorMessage();

		strSqlParams[50][0] = "51";
		strSqlParams[50][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[50][2] = shoppingCartOpr.getApplicationFlags().getApplicationFlagMap()
				.get(CommonConstant.LOGGED_USER_KEY);

		strSqlParams[51][0] = "52";
		strSqlParams[51][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[51][2] = shoppingCartOpr.getApplicationFlags().getApplicationFlagMap()
				.get(CommonConstant.LOGGED_USER_KEY);

		strSqlParams[52][0] = "53";
		strSqlParams[52][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[52][2] = "PAY U";

		strSqlParams[53][0] = "54";
		strSqlParams[53][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[53][2] = shoppingCartOpr.getPaymentDVO().getPaymentOption().getPaymentName();

		// in the following call replace null with dynamic where clause if
		// required
		daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);

	}
}
