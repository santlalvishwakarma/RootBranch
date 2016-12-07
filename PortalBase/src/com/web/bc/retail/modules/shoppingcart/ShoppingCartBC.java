package com.web.bc.retail.modules.shoppingcart;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.web.common.constants.CommonConstant;
import com.web.common.dvo.common.OperationalDataValueObject;
import com.web.common.dvo.opr.retail.ShoppingCartOpr;
import com.web.common.dvo.retail.modules.PaymentDVO;
import com.web.common.dvo.retail.modules.ShoppingCartProductDVO;
import com.web.common.dvo.retail.modules.user.UserDVO;
import com.web.common.dvo.systemowner.DeliveryChargesDVO;
import com.web.common.dvo.systemowner.ProductSkuStockLevelDVO;
import com.web.common.dvo.systemowner.VoucherDVO;
import com.web.common.parents.BackingClass;
import com.web.foundation.dao.DAOResult;
import com.web.foundation.dao.IDAOConstant;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;

public class ShoppingCartBC extends BackingClass {

	public void saveProductForLater(ShoppingCartOpr shoppingCartSaveOpr) throws FrameworkException {
		// BACKING CLASS METHOD TEMPLATE ver 1.0
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		DAOResult daoResult = new DAOResult();

		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.INSERT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ShoppingCartSqlTemplate.SAVE_PRODUCT_FOR_LATER);

		Object strSqlParams[][] = new Object[5][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[0][2] = shoppingCartSaveOpr.getShoppingCartProductRecord().getProductRecord().getId();

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[1][2] = shoppingCartSaveOpr.getShoppingCartProductRecord().getProductRecord().getVariantId();

		strSqlParams[2][0] = "3";
		strSqlParams[2][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[2][2] = shoppingCartSaveOpr.getShoppingCartProductRecord().getUserRecord().getUserLogin();

		strSqlParams[3][0] = "4";
		strSqlParams[3][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[3][2] = shoppingCartSaveOpr.getShoppingCartProductRecord().getUserRecord().getUserLogin();

		strSqlParams[4][0] = "5";
		strSqlParams[4][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[4][2] = shoppingCartSaveOpr.getShoppingCartProductRecord().getUserRecord().getUserLogin();

		// in the following call replace null with dynamic where clause if
		// required
		daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);

	}

	// public ShoppingCartOpr getSavedProducts(ShoppingCartOpr shoppingCartOpr)
	// throws BusinessException,
	// FrameworkException {
	// ShoppingCartOpr returnShoppingCartOpr = new ShoppingCartOpr();
	// // BACKING CLASS METHOD TEMPLATE ver 1.0
	// ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
	// HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
	// DAOResult daoResult = new DAOResult();
	//
	// queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
	// queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE,
	// IDAOConstant.PREPARED_STATEMENT);
	// queryDetailsMap.put(IDAOConstant.SQL_TEXT,
	// ShoppingCartSqlTemplate.GET_SAVED_PRODUCTS);
	//
	// Object strSqlParams[][] = new Object[1][3];
	//
	// strSqlParams[0][0] = "1";
	// strSqlParams[0][1] = IDAOConstant.STRING_DATATYPE;
	// strSqlParams[0][2] =
	// shoppingCartOpr.getShoppingCartProductRecord().getUserRecord().getUserLogin();
	//
	// // in the following call replace null with dynamic where clause if
	// // required
	// daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
	// HashMap responseMap = daoResult.getInvocationResult();
	// myLog.debug(":: Resultset got ::" + responseMap);
	//
	// if (responseMap.size() > 0) {
	// for (int i = 0; i < responseMap.size(); i++) {
	// HashMap resultRow = (HashMap) responseMap.get(i);
	// // PROCESS OBJECT RETURNED IN THE resultRow IF SELECT QUERY HAS
	// // BEEN FIRED
	// ShoppingCartProductDVO shoppingCartProductDVO = new
	// ShoppingCartProductDVO();
	// if (resultRow.get("r_saved_shopping_cart_products_id") != null) {
	// shoppingCartProductDVO.setId(Long.valueOf(resultRow.get("r_saved_shopping_cart_products_id")
	// .toString()));
	// }
	// if (resultRow.get("product_id") != null) {
	// shoppingCartProductDVO.getProductRecord().setId(
	// Long.valueOf(resultRow.get("product_id").toString()));
	// }
	// if (resultRow.get("product_variant_id") != null) {
	// shoppingCartProductDVO.getProductRecord().setVariantId(
	// Long.valueOf(resultRow.get("product_variant_id").toString()));
	// }
	// if (resultRow.get("user_login") != null) {
	// shoppingCartProductDVO.getUserRecord().setUserLogin((String)
	// (resultRow.get("user_login")));
	// }
	// if (resultRow.get("product_saved_date") != null) {
	// shoppingCartProductDVO.setSavedDate((Date)
	// (resultRow.get("product_saved_date")));
	// }
	// setAuditAttributes(shoppingCartProductDVO, resultRow);
	//
	// returnShoppingCartOpr.getShoppingCartProductList().add(shoppingCartProductDVO);
	// }
	// } else {
	// myLog.error("getSavedProducts :: database query failed :: Return Record empty ::::: ");
	// // throw new BusinessException("no_data_from_db_excep_msg");
	// }
	//
	// return returnShoppingCartOpr;
	// }

	// public void deleteProductFromSavedList(ShoppingCartOpr
	// shoppingCartSaveOpr) throws FrameworkException {
	// // BACKING CLASS METHOD TEMPLATE ver 1.0
	// ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
	// HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
	// DAOResult daoResult = new DAOResult();
	//
	// queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.DELETE_SQL);
	// queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE,
	// IDAOConstant.PREPARED_STATEMENT);
	// queryDetailsMap.put(IDAOConstant.SQL_TEXT,
	// ShoppingCartSqlTemplate.DELETE_PRODUCT_FROM_SAVED_LIST);
	//
	// Object strSqlParams[][] = new Object[1][3];
	//
	// strSqlParams[0][0] = "1";
	// strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
	// strSqlParams[0][2] =
	// shoppingCartSaveOpr.getShoppingCartProductRecord().getId();
	//
	// // in the following call replace null with dynamic where clause if
	// // required
	// daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
	// }

	public ShoppingCartOpr confirmOrder(ShoppingCartOpr shoppingCartOpr) throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("ShoppingCartBC :: confirmOrder::");
		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		DAOResult daoResult = new DAOResult();
		ArrayList<ShoppingCartProductDVO> shoppingCartProductDVOList = shoppingCartOpr.getShoppingCartProductList();

		String userLogin = (String) shoppingCartOpr.getApplicationFlags().getApplicationFlagMap()
				.get(CommonConstant.LOGGED_USER_KEY);
		Integer totalQuantity = shoppingCartOpr.getTotalQuantity();
		Float totalOrderPrice = shoppingCartOpr.getTotalOrderPrice();
		Integer leadTimeInDays = shoppingCartOpr.getRetailOrderRecord().getDeliveryTimeRecord().getLeadTimeInDays();
		Float deliveryCharge = shoppingCartOpr.getRetailOrderRecord().getDeliveryChargesRecord().getDeliveryCharge();
		String billingFirstName = shoppingCartOpr.getRetailOrderRecord().getBillingDetails().getFirstName();
		String billingLastName = shoppingCartOpr.getRetailOrderRecord().getBillingDetails().getLastName();
		String billingAddressLine1 = shoppingCartOpr.getRetailOrderRecord().getBillingDetails().getAddressLine1();
		String billingAddressLine2 = shoppingCartOpr.getRetailOrderRecord().getBillingDetails().getAddressLine2();
		String billingAddressLine3 = shoppingCartOpr.getRetailOrderRecord().getBillingDetails().getAddressLine3();
		String billingCity = shoppingCartOpr.getRetailOrderRecord().getBillingDetails().getCityDvo().getCode();
		String billingPincode = shoppingCartOpr.getRetailOrderRecord().getBillingDetails().getPinRecord().getCode();
		String billingState = shoppingCartOpr.getRetailOrderRecord().getBillingDetails().getStateDVO().getCode();
		String billingCountry = shoppingCartOpr.getRetailOrderRecord().getBillingDetails().getCountryDvo().getCode();

		String shippingFirstName = shoppingCartOpr.getRetailOrderRecord().getShippingDetails().getFirstName();
		String shippingLastName = shoppingCartOpr.getRetailOrderRecord().getShippingDetails().getLastName();
		String shippingAddressLine1 = shoppingCartOpr.getRetailOrderRecord().getShippingDetails().getAddressLine1();
		String shippingAddressLine2 = shoppingCartOpr.getRetailOrderRecord().getShippingDetails().getAddressLine2();
		String shippingAddressLine3 = shoppingCartOpr.getRetailOrderRecord().getShippingDetails().getAddressLine3();
		String shippingCity = shoppingCartOpr.getRetailOrderRecord().getShippingDetails().getCityDvo().getCode();
		String shippingPincode = shoppingCartOpr.getRetailOrderRecord().getShippingDetails().getPinRecord().getCode();
		String shippingState = shoppingCartOpr.getRetailOrderRecord().getShippingDetails().getStateDVO().getCode();
		String shippingCountry = shoppingCartOpr.getRetailOrderRecord().getShippingDetails().getCountryDvo().getCode();

		String billingPhone1 = shoppingCartOpr.getRetailOrderRecord().getBillingDetails().getPhone1();
		String billingPhone2 = shoppingCartOpr.getRetailOrderRecord().getBillingDetails().getPhone2();

		String shippingPhone1 = shoppingCartOpr.getRetailOrderRecord().getShippingDetails().getPhone1();
		String shippingPhone2 = shoppingCartOpr.getRetailOrderRecord().getShippingDetails().getPhone2();
		String billingEmail = shoppingCartOpr.getRetailOrderRecord().getBillingDetails().getEmail1();
		String shippingEmail = shoppingCartOpr.getRetailOrderRecord().getShippingDetails().getEmail1();

		StringBuffer parseString = new StringBuffer();

		for (int i = 0; i < shoppingCartProductDVOList.size(); i++) {
			ShoppingCartProductDVO currentRow = shoppingCartProductDVOList.get(i);

			Long productId = currentRow.getProductSkuRecord().getProductRecord().getId();
			Long productSkuId = currentRow.getProductSkuRecord().getId();
			Float finalBasePrice = currentRow.getProductSkuRecord().getFinalBasePrice();
			Float basePrice = currentRow.getProductSkuRecord().getBasePrice();
			Float discountAmount = currentRow.getProductSkuRecord().getDiscountAmount();
			Float discountPercent = currentRow.getProductSkuRecord().getDiscountPercent();
			Integer quantity = currentRow.getQuantity();
			Float subTotal = currentRow.getSubTotal();
			String comments = currentRow.getComments();
			Float originalBasePrice = currentRow.getProductSkuRecord().getOriginalBasePrice();
			Float originalDiscountPrice = currentRow.getProductSkuRecord().getOriginalDiscountPrice();
			Float originalSubTotal = currentRow.getOriginalSubTotal();

			if (productId != null) {
				parseString.append(productId);
			}

			parseString.append("~");

			if (productSkuId != null) {
				parseString.append(productSkuId);
			}

			parseString.append("~");

			if (basePrice != null) {
				parseString.append(basePrice);
			}

			parseString.append("~");

			if (discountAmount != null) {
				parseString.append(discountAmount);
			}

			parseString.append("~");

			if (quantity != null) {
				parseString.append(quantity);
			}

			parseString.append("~");

			if (subTotal != null) {
				parseString.append(subTotal);
			}

			parseString.append("~");

			if (comments != null) {
				parseString.append(comments);
			}

			parseString.append("~");

			if (originalBasePrice != null) {
				parseString.append(originalBasePrice);
			}

			parseString.append("~");

			if (originalDiscountPrice != null) {
				parseString.append(originalDiscountPrice);
			}

			parseString.append("~");

			if (originalSubTotal != null) {
				parseString.append(originalSubTotal);
			}

			parseString.append("~");

			if (finalBasePrice != null) {
				parseString.append(finalBasePrice);
			}

			parseString.append("~");

			if (discountPercent != null) {
				parseString.append(discountPercent);
			}

			parseString.append(";");
		}

		if (parseString != null && parseString.length() > 0) {
			parseString.deleteCharAt(parseString.lastIndexOf(";"));
		}

		String promoCode = shoppingCartOpr.getRetailOrderRecord().getVoucherRecord().getPromoCode();
		Float voucherDiscount = shoppingCartOpr.getRetailOrderRecord().getDiscountedPrice();
		Long voucherId = shoppingCartOpr.getRetailOrderRecord().getVoucherRecord().getId();
		String isGiftWrapRequired = shoppingCartOpr.getRetailOrderRecord().getGiftWrappingRequiredText();

		String domainKey = (String) shoppingCartOpr.getApplicationFlags().getApplicationFlagMap()
				.get(CommonConstant.DOAMIN_KEY);
		Float conversionRate = shoppingCartOpr.getShoppingCartProductRecord().getProductSkuRecord().getConversionRate();
		String currencySymbol = (String) shoppingCartOpr.getApplicationFlags().getApplicationFlagMap()
				.get(CommonConstant.CONVERTED_CURRENCY_SYMBOL);
		Float originalTotalOrderPrice = shoppingCartOpr.getOriginaltotalOrderPrice();
		Boolean currencyFlag = (Boolean) shoppingCartOpr.getApplicationFlags().getApplicationFlagMap()
				.get(CommonConstant.CONVERTED_CURRENCY_SYMBOL);
		Float originalDeliveryCharge = shoppingCartOpr.getRetailOrderRecord().getDeliveryChargesRecord()
				.getOriginalDeliveryCharge();

		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ShoppingCartSqlTemplate.GENERATE_NEW_ORDER);

		Object strSqlParams[][] = new Object[40][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[0][2] = userLogin;
		myLog.debug("parameter 1 userLogin:::" + userLogin);

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.INT_DATATYPE;
		strSqlParams[1][2] = totalQuantity;
		myLog.debug("parameter 2 totalQuantity:::" + totalQuantity);

		strSqlParams[2][0] = "3";
		strSqlParams[2][1] = IDAOConstant.FLOAT_DATATYPE;
		strSqlParams[2][2] = totalOrderPrice;
		myLog.debug("parameter 3 totalOrderPrice:::" + totalOrderPrice);

		strSqlParams[3][0] = "4";
		strSqlParams[3][1] = IDAOConstant.INT_DATATYPE;
		strSqlParams[3][2] = leadTimeInDays;
		myLog.debug("parameter 4 leadTimeInDays:::" + leadTimeInDays);

		strSqlParams[4][0] = "5";
		strSqlParams[4][1] = IDAOConstant.FLOAT_DATATYPE;
		strSqlParams[4][2] = deliveryCharge;
		myLog.debug("parameter 5 deliveryCharge" + deliveryCharge);

		strSqlParams[5][0] = "6";
		strSqlParams[5][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[5][2] = billingFirstName;
		myLog.debug("parameter 6 billingFirstName:::" + billingFirstName);

		strSqlParams[6][0] = "7";
		strSqlParams[6][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[6][2] = billingLastName;
		myLog.debug("parameter 7 billingLastName:::" + billingLastName);

		strSqlParams[7][0] = "8";
		strSqlParams[7][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[7][2] = billingAddressLine1;
		myLog.debug("parameter 8 billingAddressLine1:::" + billingAddressLine1);

		strSqlParams[8][0] = "9";
		strSqlParams[8][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[8][2] = billingAddressLine2;
		myLog.debug("parameter 9 billingAddressLine2:::" + billingAddressLine2);

		strSqlParams[9][0] = "10";
		strSqlParams[9][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[9][2] = billingAddressLine3;
		myLog.debug("parameter 10 billingAddressLine3:::" + billingAddressLine3);

		strSqlParams[10][0] = "11";
		strSqlParams[10][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[10][2] = billingCity;
		myLog.debug("parameter 11 billingCity:::" + billingCity);

		strSqlParams[11][0] = "12";
		strSqlParams[11][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[11][2] = billingPincode;
		myLog.debug("parameter 12 billingPincode:::" + billingPincode);

		strSqlParams[12][0] = "13";
		strSqlParams[12][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[12][2] = billingState;
		myLog.debug("parameter 13 billingState:::" + billingState);

		strSqlParams[13][0] = "14";
		strSqlParams[13][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[13][2] = billingCountry;
		myLog.debug("parameter 14 billingCountry:::" + billingCountry);

		strSqlParams[14][0] = "15";
		strSqlParams[14][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[14][2] = shippingFirstName;
		myLog.debug("parameter 15 shippingFirstName:::" + shippingFirstName);

		strSqlParams[15][0] = "16";
		strSqlParams[15][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[15][2] = shippingLastName;
		myLog.debug("parameter 16 shippingLastName:::" + shippingLastName);

		strSqlParams[16][0] = "17";
		strSqlParams[16][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[16][2] = shippingAddressLine1;
		myLog.debug("parameter 17 shippingAddressLine1:::" + shippingAddressLine1);

		strSqlParams[17][0] = "18";
		strSqlParams[17][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[17][2] = shippingAddressLine2;
		myLog.debug("parameter 18 shippingAddressLine2:::" + shippingAddressLine2);

		strSqlParams[18][0] = "19";
		strSqlParams[18][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[18][2] = shippingAddressLine3;
		myLog.debug("parameter 19 shippingAddressLine3:::" + shippingAddressLine3);

		strSqlParams[19][0] = "20";
		strSqlParams[19][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[19][2] = shippingCity;
		myLog.debug("parameter 20 shippingCity:::" + shippingCity);

		strSqlParams[20][0] = "21";
		strSqlParams[20][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[20][2] = shippingPincode;
		myLog.debug("parameter 21 shippingPincode:::" + shippingPincode);

		strSqlParams[21][0] = "22";
		strSqlParams[21][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[21][2] = shippingState;
		myLog.debug("parameter 22 shippingState:::" + shippingState);

		strSqlParams[22][0] = "23";
		strSqlParams[22][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[22][2] = shippingCountry;
		myLog.debug("parameter 23 shippingCountry:::" + shippingCountry);

		strSqlParams[23][0] = "24";
		strSqlParams[23][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[23][2] = billingPhone1;
		myLog.debug("parameter 24 billingPhone1:::" + billingPhone1);

		strSqlParams[24][0] = "25";
		strSqlParams[24][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[24][2] = billingPhone2;
		myLog.debug("parameter 25 billingPhone2:::" + billingPhone2);

		strSqlParams[25][0] = "26";
		strSqlParams[25][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[25][2] = shippingPhone1;
		myLog.debug("parameter 26 shippingPhone1:::" + shippingPhone1);

		strSqlParams[26][0] = "27";
		strSqlParams[26][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[26][2] = shippingPhone2;
		myLog.debug("parameter 27 shippingPhone2 :::" + shippingPhone2);

		strSqlParams[27][0] = "28";
		strSqlParams[27][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[27][2] = billingEmail;
		myLog.debug("parameter 28 billingEmail:::" + billingEmail);

		strSqlParams[28][0] = "29";
		strSqlParams[28][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[28][2] = shippingEmail;
		myLog.debug("parameter 29 shippingEmail ::::::" + shippingEmail);

		strSqlParams[29][0] = "30";
		strSqlParams[29][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[29][2] = parseString.toString();
		myLog.debug("parameter 30 parseString ::::::" + parseString);

		strSqlParams[30][0] = "31";
		strSqlParams[30][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[30][2] = promoCode;
		myLog.debug("parameter 31 promoCode:::" + promoCode);

		strSqlParams[31][0] = "32";
		strSqlParams[31][1] = IDAOConstant.FLOAT_DATATYPE;
		strSqlParams[31][2] = voucherDiscount;
		myLog.debug("parameter 32 voucherDiscount::" + voucherDiscount);

		strSqlParams[32][0] = "33";
		strSqlParams[32][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[32][2] = voucherId;
		myLog.debug("parameter 33 voucherId::" + voucherId);

		strSqlParams[33][0] = "34";
		strSqlParams[33][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[33][2] = isGiftWrapRequired;
		myLog.debug("parameter 34 valueisGiftWrapRequired ::" + isGiftWrapRequired);

		strSqlParams[34][0] = "35";
		strSqlParams[34][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[34][2] = domainKey;
		myLog.debug("parameter 35 domainKey::" + domainKey);

		strSqlParams[35][0] = "36";
		strSqlParams[35][1] = IDAOConstant.FLOAT_DATATYPE;
		strSqlParams[35][2] = conversionRate;
		myLog.debug("parameter 36 conversionRate" + conversionRate);

		strSqlParams[36][0] = "37";
		strSqlParams[36][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[36][2] = currencySymbol;
		myLog.debug("parameter 37 currencySymbol" + currencySymbol);

		strSqlParams[37][0] = "38";
		strSqlParams[37][1] = IDAOConstant.FLOAT_DATATYPE;
		strSqlParams[37][2] = originalTotalOrderPrice;
		myLog.debug("parameter 38 originalTotalOrderPrice" + originalTotalOrderPrice);

		strSqlParams[38][0] = "39";
		strSqlParams[38][1] = IDAOConstant.BOOLEAN_DATATYPE;
		strSqlParams[38][2] = currencyFlag;
		myLog.debug("parameter 39 currencyFlag:::" + currencyFlag);

		strSqlParams[39][0] = "40";
		strSqlParams[39][1] = IDAOConstant.FLOAT_DATATYPE;
		strSqlParams[39][2] = originalDeliveryCharge;
		myLog.debug("parameter 40 originalDeliveryCharge:::" + originalDeliveryCharge);

		// strSqlParams[40][0] = "41";
		// strSqlParams[40][1] = IDAOConstant.FLOAT_DATATYPE;
		// strSqlParams[40][2] =
		// shoppingCartOpr.getShoppingCartProductRecord().getProductRecord().getConversionRate();
		//
		// myLog.debug("Conversion Rate=="
		// +
		// shoppingCartOpr.getShoppingCartProductRecord().getProductRecord().getConversionRate());
		//
		// strSqlParams[41][0] = "42";
		// strSqlParams[41][1] = IDAOConstant.STRING_DATATYPE;
		// strSqlParams[41][2] =
		// shoppingCartOpr.getApplicationFlags().getApplicationFlagMap()
		// .get(CommonConstant.CONVERTED_CURRENCY_SYMBOL);
		//
		// myLog.debug("Currency Symbol==" + strSqlParams[41][2]);
		//
		// strSqlParams[42][0] = "43";
		// strSqlParams[42][1] = IDAOConstant.FLOAT_DATATYPE;
		// strSqlParams[42][2] = shoppingCartOpr.getOriginaltotalPrice();
		// myLog.debug("parameter 43:::" + strSqlParams[42][2]);
		//
		// strSqlParams[43][0] = "44";
		// strSqlParams[43][1] = IDAOConstant.FLOAT_DATATYPE;
		// strSqlParams[43][2] = shoppingCartOpr.getOriginaltotalOrderPrice();
		// myLog.debug("parameter 44:::" + strSqlParams[43][2]);
		//
		// strSqlParams[44][0] = "45";
		// strSqlParams[44][1] = IDAOConstant.BOOLEAN_DATATYPE;
		// strSqlParams[44][2] =
		// shoppingCartOpr.getApplicationFlags().getApplicationFlagMap()
		// .get(CommonConstant.CURRENCY_FLAG);
		// myLog.debug("parameter 45:::" + strSqlParams[44][2]);
		//
		// strSqlParams[45][0] = "46";
		// strSqlParams[45][1] = IDAOConstant.FLOAT_DATATYPE;
		// strSqlParams[45][2] =
		// shoppingCartOpr.getRetailOrderRecord().getDeliveryChargesRecord()
		// .getOriginalDeliveryCharge();
		// myLog.debug("parameter 46:::" + strSqlParams[45][2]);
		//
		// strSqlParams[46][0] = "47";
		// strSqlParams[46][1] = IDAOConstant.FLOAT_DATATYPE;
		// strSqlParams[46][2] =
		// shoppingCartOpr.getRetailOrderRecord().getTreeAmt();
		// myLog.debug("parameter 47:::" + strSqlParams[46][2]);
		//
		// HttpServletRequest request = (HttpServletRequest)
		// FacesContext.getCurrentInstance().getExternalContext()
		// .getRequest();
		// String promotion = (String)
		// request.getSession().getAttribute(CommonConstant.PROMOTION_SOURCE);
		//
		// strSqlParams[47][0] = "48";
		// strSqlParams[47][1] = IDAOConstant.STRING_DATATYPE;
		// strSqlParams[47][2] = promotion;
		// myLog.debug("parameter 48:::" + strSqlParams[47][2]);

		// strSqlParams[40][0] = "41";
		// strSqlParams[40][1] = IDAOConstant.FLOAT_DATATYPE;
		// strSqlParams[40][2] = shoppingCartOpr.getProcessingCharge();

		// in the following call replace null with dynamic where clause if
		// required
		daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		if (daoResult.getException() != null) {
			myLog.error("Shopping Cart :: Confirm Order :: Error occurred -  " + daoResult.getException());
			throw new BusinessException("default_error_message");
		}
		HashMap responseMap = daoResult.getInvocationResult();
		myLog.debug(":: Resultset got ::" + responseMap);

		ArrayList<ProductSkuStockLevelDVO> currentProductStockLevels = new ArrayList<ProductSkuStockLevelDVO>();

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {
				HashMap resultRow = (HashMap) responseMap.get(0);

				if (resultRow.get("v_order_header_id") != null) {
					shoppingCartOpr.getRetailOrderRecord().setId(
							Long.valueOf(resultRow.get("v_order_header_id").toString()));
				}

				shoppingCartOpr.getRetailOrderRecord().setOrderNumber(resultRow.get("v_order_number").toString());

				shoppingCartOpr.getRetailOrderRecord().setOrderDate((Date) resultRow.get("v_order_date"));
				shoppingCartOpr.getRetailOrderRecord().setDeliveryDate((Date) resultRow.get("v_delivery_date"));

				// Long productId =
				// Long.valueOf(resultRow.get("product_id").toString());
				// Long productSkuId =
				// Long.valueOf(resultRow.get("product_sku_id").toString());
				// String productCode = (String) resultRow.get("product_code");
				// String productName = (String) resultRow.get("product_name");
				// Integer availableQuantity = (Integer)
				// resultRow.get("available_quantity");
				// Integer blockedQuantity = (Integer)
				// resultRow.get("blocked_quantity");
				// // Integer shippedQuantity = (Integer)
				// // resultRow.get("shipped_quantity");
				// Integer reOrderLevel = (Integer)
				// resultRow.get("reorder_level");
				// myLog.debug("Product Stock Level Details " + productId + "/"
				// + productCode + "/" + productName + "/"
				// + productSkuId + "/" + availableQuantity + "/" +
				// blockedQuantity + "/" + reOrderLevel);
				//
				// ProductSkuStockLevelDVO productStockLevelDVO = new
				// ProductSkuStockLevelDVO();
				// productStockLevelDVO.getProductRecord().setId(productId);
				// productStockLevelDVO.getProductSkuRecord().setId(productSkuId);
				// productStockLevelDVO.getProductSkuRecord().setName(productName);
				// productStockLevelDVO.getProductSkuRecord().setCode(productCode);
				//
				// currentProductStockLevels.add(productStockLevelDVO);
			}
		} else {
			myLog.error("ShoppingCartBC :: Confirm Order :: Return Record empty ::::: ");
			throw new BusinessException("default_error_message");
		}
		shoppingCartOpr.setCurrentProductSkuStockLevels(currentProductStockLevels);
		return shoppingCartOpr;
	}

	public ShoppingCartOpr populateOtherCharges(ShoppingCartOpr shoppingCartOpr) throws FrameworkException {
		ShoppingCartOpr returnShoppingCartOpr = new ShoppingCartOpr();
		// BACKING CLASS METHOD TEMPLATE ver 1.0
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		DAOResult daoResult = new DAOResult();
		// ////////////////////////
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ShoppingCartSqlTemplate.GET_DELIVERY_TIME_FOR_COUNTRY);

		Object strSqlParams[][] = new Object[1][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[0][2] = shoppingCartOpr.getRetailOrderRecord().getShippingDetails().getCountryDvo().getId();

		// in the following call replace null with dynamic where clause if
		// required
		daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap responseMap = daoResult.getInvocationResult();
		myLog.debug(":: Resultset got ::" + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {
				HashMap resultRow = (HashMap) responseMap.get(i);
				// PROCESS OBJECT RETURNED IN THE resultRow IF SELECT QUERY HAS
				// BEEN FIRED
				if (resultRow.get("leadtime_days") != null) {
					returnShoppingCartOpr.getRetailOrderRecord().getDeliveryTimeRecord()
							.setLeadTimeInDays(Integer.valueOf(resultRow.get("leadtime_days").toString()));
				}
				if (resultRow.get("leadtime_days_stock_unavailable") != null) {
					returnShoppingCartOpr
							.getRetailOrderRecord()
							.getDeliveryTimeRecord()
							.setLeadTimeInDaysStockUnavailable(
									Integer.valueOf(resultRow.get("leadtime_days_stock_unavailable").toString()));
				}
			}
		}
		// ///////////////////////
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ShoppingCartSqlTemplate.GET_DELIVERY_CHARGES_FOR_COUNTRY);

		Object strSqlParams1[][] = new Object[1][3];

		strSqlParams1[0][0] = "1";
		strSqlParams1[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams1[0][2] = shoppingCartOpr.getRetailOrderRecord().getShippingDetails().getCountryDvo().getId();

		// in the following call replace null with dynamic where clause if
		// required
		daoResult = performDBOperation(queryDetailsMap, strSqlParams1, null);
		HashMap responseMap1 = daoResult.getInvocationResult();
		myLog.debug(":: Resultset got ::" + responseMap1);

		if (responseMap1.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {
				HashMap resultRow = (HashMap) responseMap1.get(i);
				// PROCESS OBJECT RETURNED IN THE resultRow IF SELECT QUERY HAS
				// BEEN FIRED
				if (resultRow.get("delivery_charge") != null) {
					returnShoppingCartOpr.getRetailOrderRecord().getDeliveryChargesRecord()
							.setDeliveryCharge(Float.valueOf(resultRow.get("delivery_charge").toString()));
				}
			}
		}

		return returnShoppingCartOpr;
	}

	public ShoppingCartOpr getRetailUserDetails(String userLogin) throws FrameworkException, BusinessException {
		// BACKING CLASS METHOD TEMPLATE ver 1.0
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		DAOResult daoResult = new DAOResult();
		ShoppingCartOpr returnShoppingCartOpr = new ShoppingCartOpr();

		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ShoppingCartSqlTemplate.USER_DETAILS_QUERY);

		Object strSqlParams[][] = new Object[1][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[0][2] = userLogin.trim();

		// in the following call replace null with dynamic where clause if
		// required
		daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap responseMap = daoResult.getInvocationResult();
		myLog.debug(":: Resultset got ::" + responseMap);

		// NOTE : WHEN B2B comes in picture, we will have to handle both retail
		// and wholesale user dvos here. Probably
		// based on site url
		UserDVO retailUserDVO = new UserDVO();

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {
				HashMap resultRow = (HashMap) responseMap.get(i);
				retailUserDVO.setId((Long) resultRow.get("user_id"));
				retailUserDVO.setUserLogin((String) resultRow.get("user_login"));
				retailUserDVO.setFirstName((String) resultRow.get("first_name"));
				retailUserDVO.setLastName((String) resultRow.get("last_name"));
				retailUserDVO.setPrimaryEmailId((String) resultRow.get("primary_email_id"));
				retailUserDVO.setSecondaryEmailId((String) resultRow.get("alternate_email_id"));
				retailUserDVO.setPrimaryPhoneNumber((String) resultRow.get("primary_phone_number"));
				retailUserDVO.setAlternatePhoneNumber((String) resultRow.get("alternate_phone_number"));
				retailUserDVO.setBirthDate((Date) resultRow.get("birth_date"));
				retailUserDVO.setAnniversaryDate((Date) resultRow.get("anniversary_date"));

				if (resultRow.get("billing_address_id") != null) {
					retailUserDVO.getBillingAddressRecord().setId(
							Long.valueOf(resultRow.get("billing_address_id").toString()));
				}

				if (resultRow.get("shipping_address_id") != null) {
					retailUserDVO.getShippingAddressRecord().setId(
							Long.valueOf(resultRow.get("shipping_address_id").toString()));
				}

				retailUserDVO.getBillingAddressRecord().getAddressDetailsRecord()
						.setAddressLine1((String) resultRow.get("billing_address_line1"));
				retailUserDVO.getBillingAddressRecord().getAddressDetailsRecord()
						.setAddressLine2((String) resultRow.get("billing_address_line2"));
				retailUserDVO.getBillingAddressRecord().getAddressDetailsRecord()
						.setAddressLine3((String) resultRow.get("billing_address_line3"));
				retailUserDVO.getBillingAddressRecord().getAddressDetailsRecord().getCityDvo()
						.setCode((String) resultRow.get("billing_city_code"));
				retailUserDVO.getBillingAddressRecord().getAddressDetailsRecord().getCityDvo()
						.setName((String) resultRow.get("billing_city_name"));
				retailUserDVO.getBillingAddressRecord().getAddressDetailsRecord().getPinRecord()
						.setCode((String) resultRow.get("billing_zip_code"));

				retailUserDVO.getBillingAddressRecord().getAddressDetailsRecord()
						.setPhone1((String) resultRow.get("billing_contact_person_1"));
				retailUserDVO.getBillingAddressRecord().getAddressDetailsRecord()
						.setPhone2((String) resultRow.get("billing_contact_person_2"));

				retailUserDVO.getBillingAddressRecord().getAddressDetailsRecord()
						.setEmail1((String) resultRow.get("billing_email1"));
				retailUserDVO.getBillingAddressRecord().getAddressDetailsRecord()
						.setEmail2((String) resultRow.get("billing_email2"));

				retailUserDVO.getBillingAddressRecord().getAddressDetailsRecord()
						.setContactPerson1((String) resultRow.get("billing_contact_person_1"));
				retailUserDVO.getBillingAddressRecord().getAddressDetailsRecord()
						.setContactPerson2((String) resultRow.get("billing_contact_person_2"));
				retailUserDVO.getBillingAddressRecord().getAddressDetailsRecord()
						.setLandmark((String) resultRow.get("billing_landmark"));

				retailUserDVO.getBillingAddressRecord().getAddressDetailsRecord().getStateDVO()
						.setCode((String) resultRow.get("billing_state_code"));
				retailUserDVO.getBillingAddressRecord().getAddressDetailsRecord().getStateDVO()
						.setName((String) resultRow.get("billing_state_name"));

				retailUserDVO.getBillingAddressRecord().getAddressDetailsRecord().getCountryDvo()
						.setCode((String) resultRow.get("billing_country_code"));
				retailUserDVO.getBillingAddressRecord().getAddressDetailsRecord().getCountryDvo()
						.setName((String) resultRow.get("billing_country_name"));

				retailUserDVO.getShippingAddressRecord().getAddressDetailsRecord()
						.setAddressLine1((String) resultRow.get("shipping_address_line1"));
				retailUserDVO.getShippingAddressRecord().getAddressDetailsRecord()
						.setAddressLine2((String) resultRow.get("shipping_address_line2"));
				retailUserDVO.getShippingAddressRecord().getAddressDetailsRecord()
						.setAddressLine3((String) resultRow.get("shipping_address_line3"));
				retailUserDVO.getShippingAddressRecord().getAddressDetailsRecord().getCityDvo()
						.setCode((String) resultRow.get("shipping_city_code"));
				retailUserDVO.getShippingAddressRecord().getAddressDetailsRecord().getCityDvo()
						.setName((String) resultRow.get("shipping_city_name"));
				retailUserDVO.getShippingAddressRecord().getAddressDetailsRecord().getPinRecord()
						.setCode((String) resultRow.get("shipping_zip_code"));

				retailUserDVO.getShippingAddressRecord().getAddressDetailsRecord()
						.setPhone1((String) resultRow.get("shipping_contact_person_1"));
				retailUserDVO.getShippingAddressRecord().getAddressDetailsRecord()
						.setPhone2((String) resultRow.get("shipping_contact_person_1"));

				retailUserDVO.getShippingAddressRecord().getAddressDetailsRecord()
						.setEmail1((String) resultRow.get("shipping_email1"));
				retailUserDVO.getShippingAddressRecord().getAddressDetailsRecord()
						.setEmail2((String) resultRow.get("shipping_email2"));

				retailUserDVO.getShippingAddressRecord().getAddressDetailsRecord()
						.setContactPerson1((String) resultRow.get("shipping_contact_person_1"));
				retailUserDVO.getShippingAddressRecord().getAddressDetailsRecord()
						.setContactPerson2((String) resultRow.get("shipping_contact_person_1"));
				retailUserDVO.getShippingAddressRecord().getAddressDetailsRecord()
						.setLandmark((String) resultRow.get("shipping_landmark"));

				retailUserDVO.getShippingAddressRecord().getAddressDetailsRecord().getStateDVO()
						.setCode((String) resultRow.get("shipping_state_code"));
				retailUserDVO.getShippingAddressRecord().getAddressDetailsRecord().getStateDVO()
						.setName((String) resultRow.get("shipping_state_name"));

				retailUserDVO.getShippingAddressRecord().getAddressDetailsRecord().getCountryDvo()
						.setCode((String) resultRow.get("shipping_country_code"));
				retailUserDVO.getShippingAddressRecord().getAddressDetailsRecord().getCountryDvo()
						.setName((String) resultRow.get("shipping_country_name"));

				if (resultRow.get("marital_status") != null) {
					retailUserDVO.getMaritalStatus().setParameterID(
							Integer.valueOf(resultRow.get("marital_status").toString()));
				}
				retailUserDVO.getMaritalStatus()
						.setParameterStringValue((String) resultRow.get("marital_status_value"));

				if (resultRow.get("newsletter_subscription") != null) {
					retailUserDVO.setNewsletterSubscription((Boolean) resultRow.get("newsletter_subscription"));
				}

				if (resultRow.get("sms_alert_subscription") != null) {
					retailUserDVO.setSmsAlertSubscription((Boolean) resultRow.get("sms_alert_subscription"));
				}

				if (resultRow.get("is_active") != null) {
					retailUserDVO.setActive((Boolean) resultRow.get("is_active"));
				}

				if (resultRow.get("is_admin") != null) {
					retailUserDVO.setIsAdmin((Boolean) resultRow.get("is_admin"));
				}
				setAuditAttributes(retailUserDVO, resultRow);
			}
		} else {
			myLog.debug("ShoppingCartBC :: getRetailUserDetails failed :: Return Record empty ::::: ");
			throw new BusinessException("login_invalid");
		}
		returnShoppingCartOpr.setRetailUserDetails(retailUserDVO);
		return returnShoppingCartOpr;
	}

	public ShoppingCartOpr recalculate(ShoppingCartOpr promoCodeOpr) throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		DAOResult daoResult = new DAOResult();
		// ShoppingCartOpr promoCodeOpr = (ShoppingCartOpr) searchParameters;
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ShoppingCartSqlTemplate.PROMO_CODE_DETAILS);
		// RetailOrderDVO orderRecord = new RetailOrderDVO();
		VoucherDVO voucherRecord = new VoucherDVO();

		myLog.debug("IN EXECUTE SEARCH BC =========="
				+ promoCodeOpr.getRetailOrderRecord().getVoucherRecord().getPromoCode());

		Object strSqlParams[][] = new Object[2][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[0][2] = promoCodeOpr.getRetailOrderRecord().getVoucherRecord().getPromoCode();
		myLog.debug("CODE==========" + promoCodeOpr.getRetailOrderRecord().getVoucherRecord().getPromoCode());

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[1][2] = promoCodeOpr.getRetailOrderRecord().getVoucherRecord().getPromoCode();

		// in the following call replace null with dynamic where clause if
		// required
		daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap responseMap = daoResult.getInvocationResult();
		myLog.debug(":: Resultset got ::" + responseMap);
		myLog.debug(":: Resultset got ::" + responseMap.size());

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {
				HashMap resultRow = (HashMap) responseMap.get(i);

				if (resultRow.get("r_vouchers_id") != null) {
					voucherRecord.setId(Long.valueOf(resultRow.get("r_vouchers_id").toString()));
				}
				myLog.debug("r_vouchers_id==========" + voucherRecord.getId());

				if (resultRow.get("voucher_code") != null) {
					voucherRecord.setCode((String) (resultRow.get("voucher_code")));
				}
				myLog.debug("voucher_code==========" + voucherRecord.getCode());

				if (resultRow.get("voucher_type") != null) {
					voucherRecord.getVoucherType().setParameterID(
							Integer.valueOf(resultRow.get("voucher_type").toString()));
				}
				myLog.debug("voucher_type==========" + voucherRecord.getVoucherType().getParameterID());

				if (resultRow.get("voucher_value_in_percent") != null) {
					voucherRecord.setVoucherValueInPercent(Float.valueOf(resultRow.get("voucher_value_in_percent")
							.toString()));
				}
				myLog.debug("voucher_value_in_percent==========" + voucherRecord.getVoucherValueInPercent());

				if (resultRow.get("voucher_value_in_absolute") != null) {
					voucherRecord.setVoucherValueInAbsolute(Float.valueOf(resultRow.get("voucher_value_in_absolute")
							.toString()));
				}
				myLog.debug("voucher_value_in_absolute==========" + voucherRecord.getVoucherValueInAbsolute());

				if (resultRow.get("voucher_from_date") != null) {
					voucherRecord.setVoucherFromDate((Date) resultRow.get("voucher_from_date"));
				}
				myLog.debug("voucher_from_date==========" + voucherRecord.getVoucherFromDate());

				if (resultRow.get("voucher_to_date") != null) {
					voucherRecord.setVoucherToDate((Date) resultRow.get("voucher_to_date"));
				}
				myLog.debug("voucher_to_date==========" + voucherRecord.getVoucherToDate());

				if (resultRow.get("currency_id") != null) {
					voucherRecord.getCurrencyRecord().setId(Long.valueOf(resultRow.get("currency_id").toString()));
				}
				myLog.debug("currency_id==========" + voucherRecord.getCurrencyRecord().getId());

				if (resultRow.get("voucher_value_type") != null) {
					voucherRecord.setVoucherValueTypeName((String) resultRow.get("voucher_value_type"));
				}
				myLog.debug("voucher_value_type==========" + voucherRecord.getVoucherValueTypeName());

				if (resultRow.get("promo_code") != null) {
					voucherRecord.setPromoCode((String) resultRow.get("promo_code"));
				}

				if (resultRow.get("product_price") != null) {
					voucherRecord.setProductPrice(Float.valueOf(resultRow.get("product_price").toString()));
				}

				if (resultRow.get("invoice_amount") != null) {
					voucherRecord.setInvoiceAmount(Float.valueOf(resultRow.get("invoice_amount").toString()));
					myLog.debug("inside bc check invoice amount value" + voucherRecord.getInvoiceAmount());
				}

				if (resultRow.get("max_discount_value") != null) {
					voucherRecord.setMaxDiscount(Float.valueOf(resultRow.get("max_discount_value").toString()));
				}

			}

			promoCodeOpr.getRetailOrderRecord().setVoucherRecord(voucherRecord);

		} else {
			myLog.debug("ShoppingCartBC :: recalculate failed :: Return Record empty ::::: ");
			// throw new BusinessException("no_data_from_db_excep_msg");
		}
		return promoCodeOpr;
	}

	// public void savePayPalTransactionResponse(Response resp, ShoppingCartOpr
	// shoppingCartOpr) throws FrameworkException {
	// TransactionResponse TrxnResponse = resp.getTransactionResponse();
	// FraudResponse FraudResp = resp.getFraudResponse();
	// Context TransCtx = resp.getContext();
	// // // BACKING CLASS METHOD TEMPLATE ver 1.0
	// ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
	// HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
	// DAOResult daoResult = new DAOResult();
	//
	// queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.INSERT_SQL);
	// queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE,
	// IDAOConstant.PREPARED_STATEMENT);
	// queryDetailsMap.put(IDAOConstant.SQL_TEXT,
	// ShoppingCartSqlTemplate.SAVE_PAYPAL_RESPONSE);
	//
	// Object strSqlParams[][] = new Object[17][3];
	//
	// strSqlParams[0][0] = "1";
	// strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
	// strSqlParams[0][2] = shoppingCartOpr.getRetailOrderRecord().getId();
	//
	// strSqlParams[1][0] = "2";
	// strSqlParams[1][1] = IDAOConstant.STRING_DATATYPE;
	// strSqlParams[1][2] =
	// shoppingCartOpr.getRetailOrderRecord().getOrderNumber();
	//
	// strSqlParams[2][0] = "3";
	// strSqlParams[2][1] = IDAOConstant.STRING_DATATYPE;
	// strSqlParams[2][2] = String.valueOf(TrxnResponse.getResult());
	//
	// strSqlParams[3][0] = "4";
	// strSqlParams[3][1] = IDAOConstant.STRING_DATATYPE;
	// strSqlParams[3][2] = TrxnResponse.getPnref();
	//
	// strSqlParams[4][0] = "5";
	// strSqlParams[4][1] = IDAOConstant.STRING_DATATYPE;
	// strSqlParams[4][2] = TrxnResponse.getRespMsg();
	//
	// strSqlParams[5][0] = "6";
	// strSqlParams[5][1] = IDAOConstant.STRING_DATATYPE;
	// strSqlParams[5][2] = TrxnResponse.getAuthCode();
	//
	// strSqlParams[6][0] = "7";
	// strSqlParams[6][1] = IDAOConstant.STRING_DATATYPE;
	// strSqlParams[6][2] = TrxnResponse.getAvsAddr();
	//
	// strSqlParams[7][0] = "8";
	// strSqlParams[7][1] = IDAOConstant.STRING_DATATYPE;
	// strSqlParams[7][2] = TrxnResponse.getAvsZip();
	//
	// strSqlParams[8][0] = "9";
	// strSqlParams[8][1] = IDAOConstant.STRING_DATATYPE;
	// strSqlParams[8][2] = TrxnResponse.getIavs();
	//
	// strSqlParams[9][0] = "10";
	// strSqlParams[9][1] = IDAOConstant.STRING_DATATYPE;
	// strSqlParams[9][2] = TrxnResponse.getCvv2Match();
	//
	// strSqlParams[10][0] = "11";
	// strSqlParams[10][1] = IDAOConstant.STRING_DATATYPE;
	// strSqlParams[10][2] = TrxnResponse.getProcAvs();
	//
	// strSqlParams[11][0] = "12";
	// strSqlParams[11][1] = IDAOConstant.STRING_DATATYPE;
	// strSqlParams[11][2] = TrxnResponse.getProcCVV2();
	//
	// strSqlParams[12][0] = "13";
	// strSqlParams[12][1] = IDAOConstant.STRING_DATATYPE;
	// strSqlParams[12][2] = FraudResp.getPreFpsMsg();
	//
	// strSqlParams[13][0] = "14";
	// strSqlParams[13][1] = IDAOConstant.STRING_DATATYPE;
	// strSqlParams[13][2] = FraudResp.getPostFpsMsg();
	//
	// strSqlParams[14][0] = "15";
	// strSqlParams[14][1] = IDAOConstant.STRING_DATATYPE;
	// strSqlParams[14][2] = TrxnResponse.getDuplicate();
	//
	// strSqlParams[15][0] = "16";
	// strSqlParams[15][1] = IDAOConstant.STRING_DATATYPE;
	// strSqlParams[15][2] = PayflowUtility.getStatus(resp);
	//
	// strSqlParams[16][0] = "17";
	// strSqlParams[16][1] = IDAOConstant.STRING_DATATYPE;
	// strSqlParams[16][2] = TransCtx.toString();
	//
	// // in the following call replace null with dynamic where clause if
	// // required
	// daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
	// }

	// public void updateOrderStatusPendingDispatch(ShoppingCartOpr
	// shoppingCartOpr) throws FrameworkException {
	// // BACKING CLASS METHOD TEMPLATE ver 1.0
	// // ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
	// HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
	// DAOResult daoResult = new DAOResult();
	//
	// queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.DELETE_SQL);
	// queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE,
	// IDAOConstant.PREPARED_STATEMENT);
	// queryDetailsMap.put(IDAOConstant.SQL_TEXT,
	// ShoppingCartSqlTemplate.UPDATE_ORDER_PENDING_DISPATCH);
	//
	// Object strSqlParams[][] = new Object[1][3];
	//
	// strSqlParams[0][0] = "1";
	// strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
	// strSqlParams[0][2] = shoppingCartOpr.getRetailOrderRecord().getId();
	//
	// // in the following call replace null with dynamic where clause if
	// // required
	// daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
	// }

	// public void cancelOrder(ShoppingCartOpr shoppingCartOpr) throws
	// FrameworkException {
	// // BACKING CLASS METHOD TEMPLATE ver 1.0
	// // ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
	// HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
	// DAOResult daoResult = new DAOResult();
	//
	// queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.UPDATE_SQL);
	// queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE,
	// IDAOConstant.PREPARED_STATEMENT);
	// queryDetailsMap.put(IDAOConstant.SQL_TEXT,
	// ShoppingCartSqlTemplate.UPDATE_ORDER_CANCELLED);
	//
	// Object strSqlParams[][] = new Object[1][3];
	//
	// strSqlParams[0][0] = "1";
	// strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
	// strSqlParams[0][2] = shoppingCartOpr.getRetailOrderRecord().getId();
	//
	// // in the following call replace null with dynamic where clause if
	// // required
	// daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
	// }

	public ShoppingCartOpr saveInitialPaymentTransaction(ShoppingCartOpr shoppingCartOpr) throws FrameworkException {
		// BACKING CLASS METHOD TEMPLATE ver 1.0
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		DAOResult daoResult = new DAOResult();

		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ShoppingCartSqlTemplate.INIT_PAYMENT_GATEWAY_TRANSACTION);

		Object strSqlParams[][] = new Object[62][3];

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
		strSqlParams[8][2] = shoppingCartOpr.getPaymentDVO().getSentBillingAddress().getStateDVO().getName();

		strSqlParams[9][0] = "10";
		strSqlParams[9][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[9][2] = shoppingCartOpr.getPaymentDVO().getSentBillingAddress().getPhoneNumber();

		strSqlParams[10][0] = "11";
		strSqlParams[10][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[10][2] = shoppingCartOpr.getPaymentDVO().getSentBillingAddress().getEmail();

		strSqlParams[11][0] = "12";
		strSqlParams[11][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[11][2] = shoppingCartOpr.getPaymentDVO().getSentBillingAddress().getCity();

		strSqlParams[12][0] = "13";
		strSqlParams[12][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[12][2] = shoppingCartOpr.getPaymentDVO().getSentBillingAddress().getZipCode();

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
		strSqlParams[16][2] = shoppingCartOpr.getPaymentDVO().getSentDeliveryAddress().getStateDVO().getName();

		strSqlParams[17][0] = "18";
		strSqlParams[17][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[17][2] = shoppingCartOpr.getPaymentDVO().getSentDeliveryAddress().getPhoneNumber();

		strSqlParams[18][0] = "19";
		strSqlParams[18][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[18][2] = shoppingCartOpr.getPaymentDVO().getSentDeliveryAddress().getCity();
		;

		strSqlParams[19][0] = "20";
		strSqlParams[19][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[19][2] = shoppingCartOpr.getPaymentDVO().getSentDeliveryAddress().getZipCode();

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
		strSqlParams[37][2] = shoppingCartOpr.getPaymentDVO().getReceivedBillingAddress().getStateDVO().getName();

		strSqlParams[38][0] = "39";
		strSqlParams[38][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[38][2] = shoppingCartOpr.getPaymentDVO().getReceivedBillingAddress().getPhoneNumber();

		strSqlParams[39][0] = "40";
		strSqlParams[39][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[39][2] = shoppingCartOpr.getPaymentDVO().getReceivedBillingAddress().getEmail();

		strSqlParams[40][0] = "41";
		strSqlParams[40][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[40][2] = shoppingCartOpr.getPaymentDVO().getReceivedBillingAddress().getCity();

		strSqlParams[41][0] = "42";
		strSqlParams[41][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[41][2] = shoppingCartOpr.getPaymentDVO().getReceivedBillingAddress().getZipCode();

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
		strSqlParams[45][2] = shoppingCartOpr.getPaymentDVO().getReceivedShippingAddress().getStateDVO().getName();

		strSqlParams[46][0] = "47";
		strSqlParams[46][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[46][2] = shoppingCartOpr.getPaymentDVO().getReceivedShippingAddress().getPhoneNumber();

		strSqlParams[47][0] = "48";
		strSqlParams[47][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[47][2] = shoppingCartOpr.getPaymentDVO().getReceivedShippingAddress().getCity();

		strSqlParams[48][0] = "49";
		strSqlParams[48][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[48][2] = shoppingCartOpr.getPaymentDVO().getReceivedShippingAddress().getZipCode();

		strSqlParams[49][0] = "50";
		strSqlParams[49][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[49][2] = shoppingCartOpr.getPaymentDVO().getErrorMessage();

		strSqlParams[50][0] = "51";
		strSqlParams[50][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[50][2] = shoppingCartOpr.getPaymentDVO().getUserLogin();

		strSqlParams[51][0] = "52";
		strSqlParams[51][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[51][2] = shoppingCartOpr.getPaymentDVO().getUserLogin();

		strSqlParams[52][0] = "53";
		strSqlParams[52][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[52][2] = shoppingCartOpr.getPaymentDVO().getSentServerMode();

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
				shoppingCartOpr.getPaymentDVO().getReceivedBillingAddress().getCountryDvo()
						.setCode(resultRow.get("received_delivery_country_code").toString());
			}
		}

		return shoppingCartOpr;
	}

	// public Long getSavedPaymentTransactionPK(ShoppingCartOpr shoppingCartOpr)
	// throws FrameworkException {
	// // BACKING CLASS METHOD TEMPLATE ver 1.0
	// ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
	// HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
	// DAOResult daoResult = new DAOResult();
	//
	// queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
	// queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE,
	// IDAOConstant.PREPARED_STATEMENT);
	// queryDetailsMap.put(IDAOConstant.SQL_TEXT,
	// ShoppingCartSqlTemplate.GET_PAYMENT_GATEWAY_TRANSACTION_PK);
	//
	// Object strSqlParams[][] = new Object[1][3];
	//
	// strSqlParams[0][0] = "1";
	// strSqlParams[0][1] = IDAOConstant.STRING_DATATYPE;
	// strSqlParams[0][2] = shoppingCartOpr.getPaymentDVO().getSentCheckSum();
	//
	// // in the following call replace null with dynamic where clause if
	// // required
	// daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
	// HashMap responseMap = daoResult.getInvocationResult();
	// myLog.debug(":: Resultset got ::" + responseMap);
	// myLog.debug(":: Resultset got ::" + responseMap.size());
	//
	// Long primaryKey = null;
	// for (int i = 0; i < responseMap.size(); i++) {
	// HashMap resultRow = (HashMap) responseMap.get(i);
	// if (resultRow.get("r_payment_transactions_id") != null) {
	// primaryKey =
	// Long.valueOf(resultRow.get("r_payment_transactions_id").toString());
	// }
	// }
	//
	// return primaryKey;
	// }

	// to get payment details based on primary key
	public ShoppingCartOpr getPaymentGatewayDetailsForId(OperationalDataValueObject searchParameters)
			throws FrameworkException, BusinessException {
		// BACKING CLASS METHOD TEMPLATE ver 1.0
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		DAOResult daoResult = new DAOResult();
		ShoppingCartOpr shoppingCartOpr = (ShoppingCartOpr) searchParameters;

		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ShoppingCartSqlTemplate.GET_PAYMENT_DETAILS_FOR_PAYMENT_ID);

		Object strSqlParams[][] = new Object[1][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[0][2] = shoppingCartOpr.getPaymentDVO().getId();
		myLog.debug("Id : " + shoppingCartOpr.getPaymentDVO().getId());

		// in the following call replace null with dynamic where clause if
		// required
		daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap responseMap = daoResult.getInvocationResult();
		myLog.debug(":: Resultset got ::" + responseMap);

		PaymentDVO paymentDVO = new PaymentDVO();
		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {
				HashMap resultRow = (HashMap) responseMap.get(i);

				paymentDVO.setId((Long) resultRow.get("payment_gateway_transactions_id"));
				paymentDVO.setSentMerchantParameter((String) resultRow.get("sent_merchant_parameter"));
				paymentDVO.setSentMerchantId((String) resultRow.get("sent_merchant_id"));
				paymentDVO.setSentAmount((String) resultRow.get("sent_amount"));
				paymentDVO.setSentOrderId((String) resultRow.get("sent_order_id"));
				paymentDVO.setSentCheckSum((String) resultRow.get("sent_checksum"));
				paymentDVO.getSentBillingAddress().setFirstName((String) resultRow.get("sent_billing_customer_name"));
				paymentDVO.getSentBillingAddress().setAddressLine1(
						(String) resultRow.get("sent_billing_customer_address"));
				paymentDVO.getSentBillingAddress().getCountryDvo()
						.setName((String) resultRow.get("sent_billing_customer_country"));
				paymentDVO.getSentBillingAddress().getStateDVO()
						.setName((String) resultRow.get("sent_billing_customer_state"));
				paymentDVO.getSentBillingAddress().setPhoneNumber((String) resultRow.get("sent_billing_customer_tel"));
				paymentDVO.getSentBillingAddress().setEmail((String) resultRow.get("sent_billing_customer_email"));
				paymentDVO.getSentBillingAddress().setCity((String) resultRow.get("sent_billing_customer_city"));
				paymentDVO.getSentBillingAddress().setZipCode((String) resultRow.get("sent_billing_customer_zip_code"));
				paymentDVO.getSentDeliveryAddress().setFirstName((String) resultRow.get("sent_delivery_customer_name"));
				paymentDVO.getSentDeliveryAddress().setAddressLine1(
						(String) resultRow.get("sent_delivery_customer_address"));
				paymentDVO.getSentDeliveryAddress().getCountryDvo()
						.setName((String) resultRow.get("sent_delivery_customer_country"));
				paymentDVO.getSentDeliveryAddress().getStateDVO()
						.setName((String) resultRow.get("sent_delivery_customer_state"));
				paymentDVO.getSentDeliveryAddress()
						.setPhoneNumber((String) resultRow.get("sent_delivery_customer_tel"));
				paymentDVO.getSentDeliveryAddress().setCity((String) resultRow.get("sent_delivery_customer_city"));
				paymentDVO.getSentDeliveryAddress().setZipCode(
						(String) resultRow.get("sent_delivery_customer_zip_code"));
				paymentDVO.setReceivedAmount((String) resultRow.get("received_amount"));
				paymentDVO.setReceivedCheckSum((String) resultRow.get("received_checksum"));
				paymentDVO.setReceivedMerchantId((String) resultRow.get("received_merchant_id"));
				paymentDVO.setReceivedMerchantParameter((String) resultRow.get("received_merchant_parameter"));
				paymentDVO.setReceivedOrderId((String) resultRow.get("received_order_id"));
				paymentDVO.getReceivedBillingAddress().setFirstName(
						(String) resultRow.get("sent_billing_customer_name"));
				paymentDVO.getReceivedBillingAddress().setAddressLine1(
						(String) resultRow.get("received_billing_customer_address"));
				paymentDVO.getReceivedBillingAddress().getCountryDvo()
						.setName((String) resultRow.get("received_billing_customer_country"));
				paymentDVO.getReceivedBillingAddress().getStateDVO()
						.setName((String) resultRow.get("received_billing_customer_state"));
				paymentDVO.getReceivedBillingAddress().setPhoneNumber(
						(String) resultRow.get("received_billing_customer_tel"));
				paymentDVO.getReceivedBillingAddress().setEmail(
						(String) resultRow.get("received_billing_customer_email"));
				paymentDVO.getReceivedBillingAddress()
						.setCity((String) resultRow.get("received_billing_customer_city"));
				paymentDVO.getReceivedBillingAddress().setZipCode(
						(String) resultRow.get("received_billing_customer_zip_code"));
				paymentDVO.getReceivedShippingAddress().setFirstName(
						(String) resultRow.get("received_billing_customer_name"));
				paymentDVO.getReceivedShippingAddress().setAddressLine1(
						(String) resultRow.get("received_billing_customer_address"));
				paymentDVO.getReceivedShippingAddress().getCountryDvo()
						.setName((String) resultRow.get("received_delivery_customer_country"));
				paymentDVO.getReceivedShippingAddress().getStateDVO()
						.setName((String) resultRow.get("received_delivery_customer_state"));
				paymentDVO.getReceivedShippingAddress().setPhoneNumber(
						(String) resultRow.get("received_delivery_customer_tel"));
				paymentDVO.getReceivedShippingAddress().setCity(
						(String) resultRow.get("received_delivery_customer_city"));
				paymentDVO.getReceivedShippingAddress().setZipCode(
						(String) resultRow.get("received_delivery_customer_zip_code"));
				paymentDVO.setErrorMessage((String) resultRow.get("error_message"));
				paymentDVO.setSentTransactionDate((String) resultRow.get("sent_transaction_date"));
				paymentDVO.setReceivedTransactionDate((String) resultRow.get("received_transaction_date"));
				paymentDVO.setSentServerMode((String) resultRow.get("sent_server_mode"));
				paymentDVO.setPaymentType((String) resultRow.get("payment_type"));

				paymentDVO.setReceivedTransactionId((String) resultRow.get("received_transaction_id"));
				// paymentDVO.setReceivedTransactionId((String)
				// resultRow.get("received_transaction_id"));
				paymentDVO.setReceivedTransactionReferenceNumber((String) resultRow
						.get("received_transaction_reference_number"));
				paymentDVO.setReceivedAuthIdCode((String) resultRow.get("received_auth_id_code"));
				paymentDVO.setReceivedRrn((String) resultRow.get("received_rrn"));
				paymentDVO.setReceivedCvrespCode((String) resultRow.get("received_cvresp_code"));
				paymentDVO.setReceivedFdmsScore((String) resultRow.get("received_fdms_score"));
				paymentDVO.setReceivedFdmsResult((String) resultRow.get("received_fdms_result"));
				paymentDVO.setReceivedCookie((String) resultRow.get("received_cookie"));

				// paymentDVO.setSentCurrencyCode((String)
				// resultRow.get("sent_currency_code"));

				// paymentDVO.setReceivedCurrencyCode((String)
				// resultRow.get("received_currency_code"));

				shoppingCartOpr.setPaymentDVO(paymentDVO);
			}
		} else {
			myLog.error("ShoppingCartBC :: getPaymentGatewayDetailsForId :: did not find the data related to id provided");
			throw new BusinessException("no_data_from_db_excep_msg");
		}

		return shoppingCartOpr;
	}

	public void updatePaymentGatewaytransaction(ShoppingCartOpr shoppingCartOpr) throws FrameworkException {
		// BACKING CLASS METHOD TEMPLATE ver 1.0
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		DAOResult daoResult = new DAOResult();

		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.INSERT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ShoppingCartSqlTemplate.UPDATE_PAYMENT_GATEWAY_TRANSACTION);

		Object strSqlParams[][] = new Object[39][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[0][2] = shoppingCartOpr.getPaymentDVO().getReceivedAmount();

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[1][2] = shoppingCartOpr.getPaymentDVO().getReceivedAuthorizationDescription();

		strSqlParams[2][0] = "3";
		strSqlParams[2][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[2][2] = shoppingCartOpr.getPaymentDVO().getReceivedBankName();

		strSqlParams[3][0] = "4";
		strSqlParams[3][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[3][2] = shoppingCartOpr.getPaymentDVO().getReceivedCardCategory();

		strSqlParams[4][0] = "5";
		strSqlParams[4][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[4][2] = shoppingCartOpr.getPaymentDVO().getReceivedCheckSum();

		strSqlParams[5][0] = "6";
		strSqlParams[5][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[5][2] = shoppingCartOpr.getPaymentDVO().getReceivedMerchantId();

		strSqlParams[6][0] = "7";
		strSqlParams[6][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[6][2] = shoppingCartOpr.getPaymentDVO().getReceivedMerchantParameter();

		strSqlParams[7][0] = "8";
		strSqlParams[7][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[7][2] = shoppingCartOpr.getPaymentDVO().getReceivedNbBid();

		strSqlParams[8][0] = "9";
		strSqlParams[8][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[8][2] = shoppingCartOpr.getPaymentDVO().getReceivedNbOrderNumber();

		strSqlParams[9][0] = "10";
		strSqlParams[9][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[9][2] = shoppingCartOpr.getPaymentDVO().getReceivedNotes();

		strSqlParams[10][0] = "11";
		strSqlParams[10][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[10][2] = shoppingCartOpr.getPaymentDVO().getReceivedOrderId();

		strSqlParams[11][0] = "12";
		strSqlParams[11][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[11][2] = shoppingCartOpr.getPaymentDVO().getReceivedReturnUrl();

		strSqlParams[12][0] = "13";
		strSqlParams[12][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[12][2] = shoppingCartOpr.getPaymentDVO().getReceivedBillingAddress().getFirstName();

		strSqlParams[13][0] = "14";
		strSqlParams[13][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[13][2] = shoppingCartOpr.getPaymentDVO().getReceivedBillingAddress().getAddressLine1();

		strSqlParams[14][0] = "15";
		strSqlParams[14][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[14][2] = shoppingCartOpr.getPaymentDVO().getReceivedBillingAddress().getCountryDvo().getName();

		strSqlParams[15][0] = "16";
		strSqlParams[15][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[15][2] = shoppingCartOpr.getPaymentDVO().getReceivedBillingAddress().getStateDVO().getName();

		strSqlParams[16][0] = "17";
		strSqlParams[16][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[16][2] = shoppingCartOpr.getPaymentDVO().getReceivedBillingAddress().getPhoneNumber();

		strSqlParams[17][0] = "18";
		strSqlParams[17][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[17][2] = shoppingCartOpr.getPaymentDVO().getReceivedBillingAddress().getEmail();

		strSqlParams[18][0] = "19";
		strSqlParams[18][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[18][2] = shoppingCartOpr.getPaymentDVO().getReceivedBillingAddress().getCity();

		strSqlParams[19][0] = "20";
		strSqlParams[19][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[19][2] = shoppingCartOpr.getPaymentDVO().getReceivedBillingAddress().getZipCode();

		strSqlParams[20][0] = "21";
		strSqlParams[20][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[20][2] = shoppingCartOpr.getPaymentDVO().getReceivedShippingAddress().getFirstName();

		strSqlParams[21][0] = "22";
		strSqlParams[21][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[21][2] = shoppingCartOpr.getPaymentDVO().getReceivedShippingAddress().getAddressLine1();

		strSqlParams[22][0] = "23";
		strSqlParams[22][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[22][2] = shoppingCartOpr.getPaymentDVO().getReceivedShippingAddress().getCountryDvo().getName();

		strSqlParams[23][0] = "24";
		strSqlParams[23][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[23][2] = shoppingCartOpr.getPaymentDVO().getReceivedShippingAddress().getStateDVO().getName();

		strSqlParams[24][0] = "25";
		strSqlParams[24][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[24][2] = shoppingCartOpr.getPaymentDVO().getReceivedShippingAddress().getPhoneNumber();

		strSqlParams[25][0] = "26";
		strSqlParams[25][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[25][2] = shoppingCartOpr.getPaymentDVO().getReceivedShippingAddress().getCity();

		strSqlParams[26][0] = "27";
		strSqlParams[26][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[26][2] = shoppingCartOpr.getPaymentDVO().getReceivedShippingAddress().getZipCode();

		strSqlParams[27][0] = "28";
		strSqlParams[27][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[27][2] = shoppingCartOpr.getPaymentDVO().getErrorMessage();

		strSqlParams[28][0] = "29";
		strSqlParams[28][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[28][2] = shoppingCartOpr.getPaymentDVO().getUserLogin();

		strSqlParams[29][0] = "30";
		strSqlParams[29][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[29][2] = shoppingCartOpr.getPaymentDVO().getReceivedTransactionId();

		strSqlParams[30][0] = "31";
		strSqlParams[30][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[30][2] = shoppingCartOpr.getPaymentDVO().getReceivedServerMode();

		strSqlParams[31][0] = "32";
		strSqlParams[31][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[31][2] = shoppingCartOpr.getPaymentDVO().getReceivedTransactionReferenceNumber();

		strSqlParams[32][0] = "33";
		strSqlParams[32][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[32][2] = shoppingCartOpr.getPaymentDVO().getReceivedAuthIdCode();

		strSqlParams[33][0] = "34";
		strSqlParams[33][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[33][2] = shoppingCartOpr.getPaymentDVO().getReceivedRrn();

		strSqlParams[34][0] = "35";
		strSqlParams[34][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[34][2] = shoppingCartOpr.getPaymentDVO().getReceivedCvrespCode();

		strSqlParams[35][0] = "36";
		strSqlParams[35][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[35][2] = shoppingCartOpr.getPaymentDVO().getReceivedFdmsScore();

		strSqlParams[36][0] = "37";
		strSqlParams[36][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[36][2] = shoppingCartOpr.getPaymentDVO().getReceivedFdmsResult();

		strSqlParams[37][0] = "38";
		strSqlParams[37][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[37][2] = shoppingCartOpr.getPaymentDVO().getReceivedCookie();

		strSqlParams[38][0] = "39";
		strSqlParams[38][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[38][2] = shoppingCartOpr.getPaymentDVO().getId();

		// in the following call replace null with dynamic where clause if
		// required
		daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);

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

	public ShoppingCartOpr getPaymentRecordOnReceivedMerchantParameter(OperationalDataValueObject searchParameters)
			throws FrameworkException, BusinessException {
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
		myLog.debug(":: Resultset got ::" + responseMap);

		PaymentDVO paymentDVO = new PaymentDVO();
		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {
				HashMap resultRow = (HashMap) responseMap.get(i);

				paymentDVO.setReceivedOrderId((String) resultRow.get("received_order_id"));
				myLog.debug("sent order id ::::" + paymentDVO.getReceivedOrderId());
				shoppingCartOpr.setPaymentDVO(paymentDVO);
			}
		} else {
			myLog.error("ShoppingCartBC :: getPaymentRecordOnReceivedMerchantParameter :: did not find the data related to id provided");
			throw new BusinessException("no_data_from_db_excep_msg");
		}

		return shoppingCartOpr;
	}

	public ShoppingCartOpr getOrderDetails(ShoppingCartOpr selectedOrderManagementOpr) throws FrameworkException,
			BusinessException {
		// BACKING CLASS METHOD TEMPLATE ver 1.0
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("inside getOrderDetails ::");
		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		DAOResult daoResult = new DAOResult();

		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ShoppingCartSqlTemplate.ORDER_DETAILS);
		myLog.debug("Query" + ShoppingCartSqlTemplate.ORDER_DETAILS);
		ShoppingCartOpr shoppingCartOpr = (ShoppingCartOpr) selectedOrderManagementOpr;
		Object strSqlParams[][] = new Object[1][3];
		// Long orderId = orderManagementOpr.getOrderRecord().getId();
		String orderId = shoppingCartOpr.getPaymentDVO().getReceivedOrderId();
		Long orderIdLongValue = 0L;
		if (orderId != null) {
			orderIdLongValue = Long.valueOf(orderId);
		}

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[0][2] = orderIdLongValue;
		myLog.debug("order id got ::::::::::::::::::" + orderIdLongValue);

		// in the following call replace null with dynamic where clause if
		// required
		daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap responseMap = daoResult.getInvocationResult();
		myLog.debug(":: Resultset got ::" + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {
				HashMap resultRow = (HashMap) responseMap.get(i);
				// RetailOrderDetailsDVO orderRecord = new
				// RetailOrderDetailsDVO();
				ShoppingCartProductDVO orderRecord = new ShoppingCartProductDVO();
				if (resultRow.get("product_id") != null) {

					orderRecord.getProductRecord().setId(Long.valueOf(resultRow.get("product_id").toString()));

				}

				if (resultRow.get("core_product_variants_id") != null) {
					orderRecord.getProductRecord().setVariantId(
							Long.valueOf(resultRow.get("core_product_variants_id").toString()));
				}
				if (resultRow.get("product_code") != null) {

					orderRecord.getProductRecord().setCode((String) (resultRow.get("product_code")));

				}
				myLog.debug("=========code======" + orderRecord.getProductRecord().getCode());
				if (resultRow.get("product_short_name") != null) {
					orderRecord.getProductRecord().setName(resultRow.get("product_short_name").toString());

				}

				// if (resultRow.get("base_price") != null) {
				// orderRecord.getProductRecord().setBasePrice(Float.valueOf(resultRow.get("base_price").toString()));
				// }

				if (resultRow.get("original_price_per_piece") != null) {
					orderRecord.getProductRecord().setOriginalBasePrice(
							Float.valueOf(resultRow.get("original_price_per_piece").toString()));
					orderRecord.getProductRecord().setOriginalDiscountPrice(
							Float.valueOf(resultRow.get("original_price_per_piece").toString()));
				}

				if (resultRow.get("price_per_piece") != null) {
					orderRecord.getProductRecord().setBasePrice(
							Float.valueOf(resultRow.get("price_per_piece").toString()));
					orderRecord.getProductRecord().setDiscountPrice(
							Float.valueOf(resultRow.get("price_per_piece").toString()));
				}

				if (resultRow.get("quantity") != null) {
					orderRecord.setQuantity(Integer.valueOf(resultRow.get("quantity").toString()));
				}
				if (resultRow.get("total_price") != null) {
					orderRecord.setSubTotal(Float.valueOf(resultRow.get("total_price").toString()));
				}

				if (resultRow.get("original_total_price") != null) {
					orderRecord.setOriginalSubTotal(Float.valueOf(resultRow.get("original_total_price").toString()));
				}

				if (resultRow.get("product_description") != null) {
					orderRecord.getProductRecord().setDescription(
							String.valueOf(resultRow.get("product_description").toString()));
				}

				orderRecord.getProductRecord().getHierarchyRecord().setName((String) resultRow.get("hierarchy_name"));

				if (resultRow.get("level_1_hierarchy_id") != null) {
					orderRecord.getProductRecord().getHierarchyLevelOne()
							.setId(Long.valueOf(resultRow.get("level_1_hierarchy_id").toString()));
				}

				orderRecord.getProductRecord().getHierarchyLevelOne()
						.setName((String) resultRow.get("level_1_hierarchy_name"));

				if (resultRow.get("level_2_hierarchy_id") != null) {
					orderRecord.getProductRecord().getHierarchyLevelTwo()
							.setId(Long.valueOf(resultRow.get("level_2_hierarchy_id").toString()));
				}

				orderRecord.getProductRecord().getHierarchyLevelTwo()
						.setName((String) resultRow.get("level_2_hierarchy_name"));

				if (resultRow.get("level_3_hierarchy_id") != null) {
					orderRecord.getProductRecord().getHierarchyLevelThree()
							.setId(Long.valueOf(resultRow.get("level_3_hierarchy_id").toString()));
				}

				orderRecord.getProductRecord().getHierarchyLevelThree()
						.setName((String) resultRow.get("level_3_hierarchy_name"));

				if (resultRow.get("level_4_hierarchy_id") != null) {
					orderRecord.getProductRecord().getHierarchyLevelFour()
							.setId(Long.valueOf(resultRow.get("level_4_hierarchy_id").toString()));
				}

				orderRecord.getProductRecord().getHierarchyLevelFour()
						.setName((String) resultRow.get("level_4_hierarchy_name"));

				if (resultRow.get("currency_symbol") != null) {
					orderRecord.getProductRecord().getOriginalCurrencyRecord()
							.setCurrencySymbol(String.valueOf(resultRow.get("currency_symbol").toString()));
				}

				if (resultRow.get("converted_currency_symbol") != null) {
					orderRecord.getProductRecord().getCurrencyRecord()
							.setCurrencySymbol((String) resultRow.get("converted_currency_symbol"));
				}
				myLog.debug("Inside shopping cart BC ORDER_DETAILS converted_currency_symbol=="
						+ orderRecord.getProductRecord().getCurrencyRecord().getCurrencySymbol());

				if (resultRow.get("product_code") != null) {
					orderRecord.getProductRecord().setCode(String.valueOf(resultRow.get("product_code").toString()));
				}

				if (resultRow.get("product_image_url") != null) {
					orderRecord.getProductRecord().getImageRecord()
							.setImageURL((String) (resultRow.get("product_image_url")));
				}

				if (resultRow.get("product_thumbnail_url") != null) {
					orderRecord.getProductRecord().getImageRecord()
							.setThumbnailImageURL((String) (resultRow.get("product_thumbnail_url")));
				}

				orderRecord.getProductRecord().setEngraveText((String) resultRow.get("customization_text"));

				orderRecord.getProductRecord().setEngraveFont((String) resultRow.get("customization_font"));

				orderRecord.getProductRecord().getSizeRecord().setCode((String) resultRow.get("size_code"));

				shoppingCartOpr.getShoppingCartProductList().add(orderRecord);
			}
		} else {
			myLog.error("ShoppingCartBC :: getOrderDetails failed :: Return Record empty ::::: ");
			throw new BusinessException("no_data_from_db_excep_msg");
		}

		return shoppingCartOpr;

	}

	public ShoppingCartOpr getCustumizationRecord(ShoppingCartOpr shoppingCartOpr) throws FrameworkException,
			BusinessException {
		// BACKING CLASS METHOD TEMPLATE ver 1.0
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("inside getcustomize data ::");
		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		DAOResult daoResult = new DAOResult();

		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ShoppingCartSqlTemplate.GET_CUSTOMIZE_RECORD);
		myLog.debug("Query" + ShoppingCartSqlTemplate.GET_CUSTOMIZE_RECORD);
		ShoppingCartOpr returnShoppingCartOpr = (ShoppingCartOpr) shoppingCartOpr;
		Object strSqlParams[][] = new Object[1][3];
		Long orderId = returnShoppingCartOpr.getRetailOrderRecord().getId();
		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[0][2] = orderId;
		myLog.debug("orderId:::" + orderId);

		// in the following call replace null with dynamic where clause if
		// required
		daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap responseMap = daoResult.getInvocationResult();
		myLog.debug(":: Resultset got ::" + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {
				HashMap resultRow = (HashMap) responseMap.get(i);
				// RetailOrderDetailsDVO orderRecord = new
				// RetailOrderDetailsDVO();
				ProductDropDownDVO customizeRecord = new ProductDropDownDVO();
				if (resultRow.get("product_id") != null) {

					customizeRecord.setProduct_id(Long.valueOf(resultRow.get("product_id").toString()));

				}

				if (resultRow.get("product_variant_id") != null) {
					customizeRecord.setProduct_variant_id(Long.valueOf(resultRow.get("product_variant_id").toString()));
				}

				customizeRecord.setChosenValue((String) resultRow.get("property_value_text"));

				customizeRecord.setName((String) resultRow.get("product_property_short_name"));

				returnShoppingCartOpr.getShoppingCartProductRecord().getProductRecord().getEditablePropertiesList()
						.add(customizeRecord);
			}
		} else {
			myLog.debug("ShoppingCartBC :: getCustumizationRecord failed :: Return Record empty ::::: ");
			// throw new BusinessException("no_data_from_db_excep_msg");
		}
		ProductDropDownDVO customizeDvo = new ProductDropDownDVO();
		for (int i = 0; i < returnShoppingCartOpr.getShoppingCartProductList().size(); i++) {
			for (int j = 0; j < returnShoppingCartOpr.getShoppingCartProductRecord().getProductRecord()
					.getEditablePropertiesList().size(); j++) {
				myLog.debug("1st arrylist data:::::"
						+ returnShoppingCartOpr.getShoppingCartProductList().get(i).getProductRecord().getId() + "and"
						+ returnShoppingCartOpr.getShoppingCartProductList().get(i).getProductRecord().getVariantId());

				myLog.debug("2nd array list record:::::"
						+ returnShoppingCartOpr.getShoppingCartProductRecord().getProductRecord()
								.getEditablePropertiesList().get(j).getProduct_id()
						+ "And"
						+ returnShoppingCartOpr.getShoppingCartProductRecord().getProductRecord()
								.getEditablePropertiesList().get(j).getProduct_variant_id());
				if (returnShoppingCartOpr
						.getShoppingCartProductList()
						.get(i)
						.getProductRecord()
						.getId()
						.equals(returnShoppingCartOpr.getShoppingCartProductRecord().getProductRecord()
								.getEditablePropertiesList().get(j).getProduct_id())
						&& returnShoppingCartOpr
								.getShoppingCartProductList()
								.get(i)
								.getProductRecord()
								.getVariantId()
								.equals(returnShoppingCartOpr.getShoppingCartProductRecord().getProductRecord()
										.getEditablePropertiesList().get(j).getProduct_variant_id())) {
					customizeDvo = returnShoppingCartOpr.getShoppingCartProductRecord().getProductRecord()
							.getEditablePropertiesList().get(j);
					returnShoppingCartOpr.getShoppingCartProductList().get(i).getProductRecord()
							.getEditablePropertiesList().add(customizeDvo);

				}

			}
			myLog.debug("check size inde array list"
					+ returnShoppingCartOpr.getShoppingCartProductList().get(i).getProductRecord()
							.getEditablePropertiesList().size());
		}

		return returnShoppingCartOpr;

	}

	public ShoppingCartOpr getOrderHeaderDetails(ShoppingCartOpr selectedOrderManagementOpr) throws FrameworkException,
			BusinessException {
		// BACKING CLASS METHOD TEMPLATE ver 1.0
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("inside getOrderHeaderDetails ::");
		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		DAOResult daoResult = new DAOResult();

		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ShoppingCartSqlTemplate.ORDER_HEADER_DETAILS);
		myLog.debug("Query" + ShoppingCartSqlTemplate.ORDER_HEADER_DETAILS);
		ShoppingCartOpr shoppingCartOpr = (ShoppingCartOpr) selectedOrderManagementOpr;
		Object strSqlParams[][] = new Object[1][3];
		// Long orderId = orderManagementOpr.getOrderRecord().getId();
		String OrderId = shoppingCartOpr.getPaymentDVO().getReceivedOrderId();
		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[0][2] = OrderId;
		myLog.debug(":: OrderId ::" + OrderId);

		// in the following call replace null with dynamic where clause if
		// required
		daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap responseMap = daoResult.getInvocationResult();
		myLog.debug(":: Resultset got ::" + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {
				HashMap resultRow = (HashMap) responseMap.get(i);
				// RetailOrderDetailsDVO orderRecord = new
				// RetailOrderDetailsDVO();
				PaymentDVO paymentDVO = new PaymentDVO();
				myLog.debug("inside getting values from responsemap::::::");

				if (resultRow.get("r_order_header_id") != null) {
					shoppingCartOpr.getRetailOrderRecord().setId(
							Long.valueOf(resultRow.get("r_order_header_id").toString()));
					myLog.debug("r order header id :::::" + shoppingCartOpr.getRetailOrderRecord().getId());
				}

				if (resultRow.get("total_quantity") != null) {
					shoppingCartOpr.setTotalQuantity(Integer.valueOf(resultRow.get("total_quantity").toString()));
					myLog.debug("total quantity :::::");
				}
				if (resultRow.get("total_amount") != null) {
					shoppingCartOpr.setTotalPrice(Float.valueOf(resultRow.get("total_amount").toString()));
					myLog.debug("total amount:::::" + shoppingCartOpr.getTotalPrice());
				}

				if (resultRow.get("original_total_amount") != null) {
					shoppingCartOpr.setOriginaltotalPrice(Float.valueOf(resultRow.get("original_total_amount")
							.toString()));
					myLog.debug("total amount original:::::" + shoppingCartOpr.getOriginaltotalPrice());
				}

				if (resultRow.get("total_payable_amount") != null) {
					shoppingCartOpr.setTotalOrderPrice(Float.valueOf(resultRow.get("total_payable_amount").toString()));
					myLog.debug("total payable amount:::::" + shoppingCartOpr.getTotalOrderPrice());
				}

				if (resultRow.get("original_total_payable_amount") != null) {
					shoppingCartOpr.setOriginaltotalOrderPrice(Float.valueOf(resultRow.get(
							"original_total_payable_amount").toString()));
					myLog.debug("total payable amount original:::::" + shoppingCartOpr.getOriginaltotalOrderPrice());
				}

				if (resultRow.get("green_book_club_amt") != null) {
					shoppingCartOpr.getRetailOrderRecord().setTreeAmt(
							Float.valueOf(resultRow.get("green_book_club_amt").toString()));
					myLog.debug("green tree amt:::::" + shoppingCartOpr.getRetailOrderRecord().getTreeAmt());
				}

				shoppingCartOpr.setConvertedCurrencySymbol((String) resultRow.get("converted_currency_symbol"));
				shoppingCartOpr.setCurrencySymbolFlag((Boolean) resultRow.get("currency_symbol_flag"));

				myLog.debug("Inside shopping cart BC::" + shoppingCartOpr.getCurrencySymbolFlag());

				if (resultRow.get("purchased_delivery_charges") != null) {
					shoppingCartOpr.getRetailOrderRecord().getDeliveryChargesRecord()
							.setDeliveryCharge(Float.valueOf(resultRow.get("purchased_delivery_charges").toString()));
					myLog.debug("purchased_delivery_charges");
				}
				if (resultRow.get("discount_value") != null) {
					shoppingCartOpr.getRetailOrderRecord().setDiscountedPrice(
							Float.valueOf(resultRow.get("discount_value").toString()));
					shoppingCartOpr.getRetailOrderRecord().getVoucherRecord()
							.setVoucherDiscountValue(Float.valueOf(resultRow.get("discount_value").toString()));
					myLog.debug("discount_value");
				}

				if (resultRow.get("voucher_code") != null) {
					shoppingCartOpr.getRetailOrderRecord().getVoucherRecord()
							.setPromoCode((String) resultRow.get("voucher_code"));
					myLog.debug("voucher_code");
				}

				if (resultRow.get("voucher_discount_percent") != null) {
					shoppingCartOpr
							.getRetailOrderRecord()
							.getVoucherRecord()
							.setVoucherValueInPercent(
									Float.valueOf(resultRow.get("voucher_discount_percent").toString()));
					myLog.debug("voucher_discount_percent");
				}

				if (resultRow.get("voucher_discount_absolute") != null) {
					shoppingCartOpr
							.getRetailOrderRecord()
							.getVoucherRecord()
							.setVoucherValueInAbsolute(
									Float.valueOf(resultRow.get("voucher_discount_absolute").toString()));
					myLog.debug("voucher_discount_absolute");
				}

				if (resultRow.get("lead_time") != null) {
					shoppingCartOpr.getRetailOrderRecord().getDeliveryTimeRecord()
							.setLeadTimeInDays(Integer.valueOf(resultRow.get("lead_time").toString()));
					myLog.debug("lead_time"
							+ shoppingCartOpr.getRetailOrderRecord().getDeliveryTimeRecord().getLeadTimeInDays());
				}

				shoppingCartOpr.getRetailOrderRecord().setOrderTrackingNumber(
						(String) resultRow.get("order_tracking_number"));

				shoppingCartOpr.getRetailOrderRecord().getCourierRecord()
						.setParameterCode((String) resultRow.get("courier_name"));

				// billing
				paymentDVO.setReceivedOrderId(shoppingCartOpr.getPaymentDVO().getReceivedOrderId());
				paymentDVO.getReceivedBillingAddress().setFirstName((String) resultRow.get("customer_billing_fname"));
				myLog.debug("inside get received billing addresses1 ::::::");
				paymentDVO.getReceivedBillingAddress().setLastName((String) resultRow.get("customer_billing_sname"));
				myLog.debug("inside get received billing addresses2 ::::::");
				paymentDVO.getReceivedBillingAddress().setAddressLine1(
						(String) resultRow.get("customer_billing_address_line1"));
				myLog.debug("inside get received billing addresses3 ::::::");
				paymentDVO.getReceivedBillingAddress().setAddressLine2(
						(String) resultRow.get("customer_billing_address_line2"));
				myLog.debug("inside get received billing addresses4 ::::::");
				if (resultRow.get("customer_billing_address_line3") != null) {
					paymentDVO.getReceivedBillingAddress().setAddressLine3(
							(String) resultRow.get("customer_billing_address_line3"));
				}
				paymentDVO.getReceivedBillingAddress().getCountryDvo()
						.setName((String) resultRow.get("billing_address_country"));
				myLog.debug("inside get received billing addresses5 ::::::");
				paymentDVO.getReceivedBillingAddress().getStateDVO()
						.setName((String) resultRow.get("billing_address_state"));
				myLog.debug("inside get received billing addresses6 ::::::");
				paymentDVO.getReceivedBillingAddress().setPhone1(
						(String) resultRow.get("customer_billing_primary_phone_number"));
				myLog.debug("inside get received billing addresses7 ::::::");
				paymentDVO.getReceivedBillingAddress().setEmail1((String) resultRow.get("billing_email_address"));
				myLog.debug("inside get received billing addresses ::::::"
						+ paymentDVO.getReceivedBillingAddress().getEmail1());
				paymentDVO.getReceivedBillingAddress().getCityDvo()
						.setCode((String) resultRow.get("customer_billing_address_city"));
				myLog.debug("inside get received billing addresses8 ::::::");
				paymentDVO.getReceivedBillingAddress().setZipCode(
						(String) resultRow.get("customer_billing_address_zip_code"));
				myLog.debug("inside get received billing addresses9 ::::::");

				// shipping
				paymentDVO.getReceivedShippingAddress().setFirstName((String) resultRow.get("customer_shipping_fname"));
				myLog.debug("inside get received shipping addresses1 ::::::");
				paymentDVO.getReceivedShippingAddress().setLastName((String) resultRow.get("customer_shipping_sname"));
				myLog.debug("inside get received shipping addresses2 ::::::");
				paymentDVO.getReceivedShippingAddress().setAddressLine1(
						(String) resultRow.get("customer_shipping_address_line1"));
				myLog.debug("inside get received shipping addresses3 ::::::");
				paymentDVO.getReceivedShippingAddress().setAddressLine2(
						(String) resultRow.get("customer_shipping_address_line2"));
				myLog.debug("inside get received shipping addresses4 ::::::");
				if (resultRow.get("customer_shipping_address_line3") != null) {
					paymentDVO.getReceivedShippingAddress().setAddressLine3(
							(String) resultRow.get("customer_shipping_address_line3"));
					myLog.debug("inside get received shipping addresses5 ::::::");
				}
				paymentDVO.getReceivedShippingAddress().getCountryDvo()
						.setName((String) resultRow.get("shipping_address_country"));
				myLog.debug("inside get received shipping addresses6 ::::::");
				paymentDVO.getReceivedShippingAddress().getStateDVO()
						.setName((String) resultRow.get("shipping_address_state"));
				myLog.debug("inside get received shipping addresses7 ::::::");
				paymentDVO.getReceivedShippingAddress().setPhoneNumber(
						(String) resultRow.get("customer_shipping_primary_phone_number"));
				myLog.debug("inside get received shipping addresses8 ::::::");
				paymentDVO.getReceivedShippingAddress().setCity(
						(String) resultRow.get("customer_shipping_address_city"));
				myLog.debug("inside get received shipping addresses9 ::::::");
				paymentDVO.getReceivedShippingAddress().setZipCode(
						(String) resultRow.get("customer_shipping_address_zip_code"));
				myLog.debug("inside get received shipping addresses10 ::::::");
				paymentDVO.getReceivedShippingAddress().setEmail((String) resultRow.get("shipping_email_address"));

				// order details
				// paymentDVO.setReceivedAmount((String)
				// resultRow.get("total_payable_amount"));
				myLog.debug("inside get received shipping addresses11 ::::::");
				paymentDVO.setReceivedOrderId((String) resultRow.get("order_no"));
				myLog.debug("inside get received shipping addresses12 ::::::");
				paymentDVO.setSentOrderId((String) resultRow.get("order_no"));
				myLog.debug("inside get received shipping addresses12 ::::::");
				paymentDVO.setPaymentType((String) resultRow.get("payment_type"));

				setAuditAttributes(paymentDVO, resultRow);

				shoppingCartOpr.setPaymentDVO(paymentDVO);
			}
		} else {
			myLog.error("ShoppingCartBC :: getOrderHeaderDetails failed :: Return Record empty ::::: ");
			throw new BusinessException("no_data_from_db_excep_msg");
		}

		return shoppingCartOpr;

	}

	// public void updateOrderStatusSavedToWishlist(ShoppingCartOpr
	// shoppingCartSaveOpr) throws FrameworkException {
	// // BACKING CLASS METHOD TEMPLATE ver 1.0
	// ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
	// HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
	// DAOResult daoResult = new DAOResult();
	//
	// queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.INSERT_SQL);
	// queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE,
	// IDAOConstant.PREPARED_STATEMENT);
	// queryDetailsMap.put(IDAOConstant.SQL_TEXT,
	// ShoppingCartSqlTemplate.UPDATE_ORDER_SAVED_TO_WISHLIST);
	//
	// Object strSqlParams[][] = new Object[1][3];
	//
	// strSqlParams[0][0] = "1";
	// strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
	// strSqlParams[0][2] = shoppingCartSaveOpr.getRetailOrderRecord().getId();
	//
	// // in the following call replace null with dynamic where clause if
	// // required
	// daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
	// }

	public ShoppingCartOpr updateOrderStatusConfirmed(ShoppingCartOpr shoppingCartOpr) throws FrameworkException {
		// BACKING CLASS METHOD TEMPLATE ver 1.0
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		DAOResult daoResult = new DAOResult();

		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.UPDATE_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ShoppingCartSqlTemplate.UPDATE_ORDER_STATUS_CONFIRMED);

		Object strSqlParams[][] = new Object[1][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[0][2] = shoppingCartOpr.getPaymentDVO().getReceivedOrderId();

		myLog.debug("UPDATE_ORDER_STATUS_CONFIRMED: " + strSqlParams[0][2]);
		// in the following call replace null with dynamic where clause if
		// required
		daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);

		if (daoResult.getUpdateResult() > 0) {
			queryDetailsMap = new HashMap<String, String>();
			queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
			queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
			queryDetailsMap.put(IDAOConstant.SQL_TEXT, ShoppingCartSqlTemplate.GET_ORDER_ID);
			daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
			HashMap responseMap = daoResult.getInvocationResult();
			myLog.debug(":: Resultset got ::UPDATE_ORDER_STATUS_CONFIRMED: " + responseMap);

			if (responseMap.size() > 0) {
				for (int i = 0; i < responseMap.size(); i++) {
					HashMap resultRow = (HashMap) responseMap.get(i);

					if (resultRow.get("r_order_header_id") != null) {
						shoppingCartOpr.getRetailOrderRecord().setId(
								Long.valueOf(resultRow.get("r_order_header_id").toString()));
						shoppingCartOpr.getPaymentDVO().setReceivedOrderId(
								resultRow.get("r_order_header_id").toString());
					}
				}
			}
		} else {
			myLog.error("ShoppingCartBC :: updateOrderStatusConfirmed failed :: update unsuccesful");
		}

		return shoppingCartOpr;
	}

	public ShoppingCartOpr getPaymentRecordOnReceivedMerchantParameterForICICI(
			OperationalDataValueObject searchParameters) throws FrameworkException, BusinessException {
		// BACKING CLASS METHOD TEMPLATE ver 1.0
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		DAOResult daoResult = new DAOResult();
		ShoppingCartOpr shoppingCartOpr = (ShoppingCartOpr) searchParameters;

		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT,
				ShoppingCartSqlTemplate.GET_PAYMENT_DETAILS_FOR_SENT_MERCHANT_PARAMETER);

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

		PaymentDVO paymentDVO = new PaymentDVO();
		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {
				HashMap resultRow = (HashMap) responseMap.get(i);

				paymentDVO.setReceivedOrderId((String) resultRow.get("sent_order_id"));
				myLog.debug("sent order id ::::" + paymentDVO.getReceivedOrderId());
				shoppingCartOpr.setPaymentDVO(paymentDVO);
			}
		} else {
			myLog.error("ShoppingCartBC :: getPaymentRecordOnReceivedMerchantParameter :: did not find the data related to id provided");
			throw new BusinessException("no_data_from_db_excep_msg");
		}

		return shoppingCartOpr;
	}

	// added by ketan fro paypal
	public ShoppingCartOpr getOrderDetailsForPayPal(ShoppingCartOpr selectedOrderManagementOpr)
			throws FrameworkException, BusinessException {
		// BACKING CLASS METHOD TEMPLATE ver 1.0
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("inside getOrderDetails ::");
		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		DAOResult daoResult = new DAOResult();

		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ShoppingCartSqlTemplate.ORDER_DETAILS_FOR_PAY_PAL);
		myLog.debug("Query" + ShoppingCartSqlTemplate.ORDER_DETAILS_FOR_PAY_PAL);
		ShoppingCartOpr shoppingCartOpr = (ShoppingCartOpr) selectedOrderManagementOpr;
		Object strSqlParams[][] = new Object[1][3];
		// Long orderId = orderManagementOpr.getOrderRecord().getId();
		String orderId = shoppingCartOpr.getPaymentDVO().getReceivedOrderId();

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[0][2] = orderId;
		myLog.debug("order id got ::::::::::::::::::" + orderId);

		// in the following call replace null with dynamic where clause if
		// required
		daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap responseMap = daoResult.getInvocationResult();
		myLog.debug(":: Resultset got ::" + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {
				HashMap resultRow = (HashMap) responseMap.get(i);
				// RetailOrderDetailsDVO orderRecord = new
				// RetailOrderDetailsDVO();
				ShoppingCartProductDVO orderRecord = new ShoppingCartProductDVO();
				if (resultRow.get("product_id") != null) {

					orderRecord.getProductSkuRecord().setId(Long.valueOf(resultRow.get("product_id").toString()));

				}

				if (resultRow.get("core_product_variants_id") != null) {
					orderRecord.getProductSkuRecord().setVariantId(
							Long.valueOf(resultRow.get("core_product_variants_id").toString()));
				}
				if (resultRow.get("product_code") != null) {

					orderRecord.getProductRecord().setCode((String) (resultRow.get("product_code")));

				}
				myLog.debug("=========code======" + orderRecord.getProductRecord().getCode());
				if (resultRow.get("product_short_name") != null) {
					orderRecord.getProductRecord().setName(resultRow.get("product_short_name").toString());

				}

				// if (resultRow.get("base_price") != null) {
				// orderRecord.getProductRecord().setBasePrice(Float.valueOf(resultRow.get("base_price").toString()));
				// }

				if (resultRow.get("price_per_piece") != null) {
					orderRecord.getProductRecord().setBasePrice(
							Float.valueOf(resultRow.get("price_per_piece").toString()));
					orderRecord.getProductRecord().setDiscountPrice(
							Float.valueOf(resultRow.get("price_per_piece").toString()));
				}
				if (resultRow.get("quantity") != null) {
					orderRecord.setQuantity(Integer.valueOf(resultRow.get("quantity").toString()));
				}
				if (resultRow.get("total_price") != null) {
					orderRecord.setSubTotal(Float.valueOf(resultRow.get("total_price").toString()));
				}

				if (resultRow.get("product_description") != null) {
					orderRecord.getProductRecord().setDescription(
							String.valueOf(resultRow.get("product_description").toString()));
				}

				if (resultRow.get("currency_symbol") != null) {
					orderRecord.getProductRecord().getOriginalCurrencyRecord()
							.setCurrencySymbol(String.valueOf(resultRow.get("currency_symbol").toString()));
				}

				if (resultRow.get("converted_currency_symbol") != null) {
					orderRecord.getProductRecord().getCurrencyRecord()
							.setCurrencySymbol((String) resultRow.get("converted_currency_symbol"));
				}
				myLog.debug("Inside shopping cart BC ORDER_DETAILS converted_currency_symbol=="
						+ orderRecord.getProductRecord().getCurrencyRecord().getCurrencySymbol());

				if (resultRow.get("product_code") != null) {
					orderRecord.getProductRecord().setCode(String.valueOf(resultRow.get("product_code").toString()));
				}

				if (resultRow.get("product_image_url") != null) {
					orderRecord.getProductRecord().getImageRecord()
							.setImageURL((String) (resultRow.get("product_image_url")));
				}

				if (resultRow.get("product_thumbnail_url") != null) {
					orderRecord.getProductRecord().getImageRecord()
							.setThumbnailImageURL((String) (resultRow.get("product_thumbnail_url")));
				}

				orderRecord.getProductRecord().setEngraveText((String) resultRow.get("customization_text"));

				orderRecord.getProductRecord().setEngraveFont((String) resultRow.get("customization_font"));

				orderRecord.getProductRecord().getSizeRecord().setCode((String) resultRow.get("size_code"));

				shoppingCartOpr.getShoppingCartProductList().add(orderRecord);
			}
		} else {
			myLog.error("ShoppingCartBC :: getOrderDetails failed :: Return Record empty ::::: ");
			throw new BusinessException("no_data_from_db_excep_msg");
		}

		return shoppingCartOpr;

	}

	public ShoppingCartOpr getOrderHeaderDetailsForPayPal(ShoppingCartOpr selectedOrderManagementOpr)
			throws FrameworkException, BusinessException {
		// BACKING CLASS METHOD TEMPLATE ver 1.0
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("inside getOrderHeaderDetails ::");
		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		DAOResult daoResult = new DAOResult();

		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ShoppingCartSqlTemplate.ORDER_HEADER_DETAILS_FOR_PAYPAL);
		myLog.debug("Query" + ShoppingCartSqlTemplate.ORDER_HEADER_DETAILS_FOR_PAYPAL);
		ShoppingCartOpr shoppingCartOpr = (ShoppingCartOpr) selectedOrderManagementOpr;
		Object strSqlParams[][] = new Object[1][3];
		// Long orderId = orderManagementOpr.getOrderRecord().getId();
		String OrderId = shoppingCartOpr.getPaymentDVO().getReceivedOrderId();
		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[0][2] = OrderId;
		myLog.debug(":: OrderId ::" + OrderId);

		// in the following call replace null with dynamic where clause if
		// required
		daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap responseMap = daoResult.getInvocationResult();
		myLog.debug(":: Resultset got ::" + responseMap);

		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {
				HashMap resultRow = (HashMap) responseMap.get(i);
				// RetailOrderDetailsDVO orderRecord = new
				// RetailOrderDetailsDVO();
				PaymentDVO paymentDVO = new PaymentDVO();
				myLog.debug("inside getting values from responsemap::::::");

				if (resultRow.get("r_order_header_id") != null) {
					shoppingCartOpr.getRetailOrderRecord().setId(
							Long.valueOf(resultRow.get("r_order_header_id").toString()));
					myLog.debug("r order header id :::::" + shoppingCartOpr.getRetailOrderRecord().getId());
				}

				if (resultRow.get("total_quantity") != null) {
					shoppingCartOpr.setTotalQuantity(Integer.valueOf(resultRow.get("total_quantity").toString()));
					myLog.debug("total quantity :::::");
				}
				if (resultRow.get("total_amount") != null) {
					shoppingCartOpr.setTotalPrice(Float.valueOf(resultRow.get("total_amount").toString()));
					myLog.debug("total amount:::::" + shoppingCartOpr.getTotalPrice());
				}

				if (resultRow.get("total_payable_amount") != null) {
					shoppingCartOpr.setTotalOrderPrice(Float.valueOf(resultRow.get("total_payable_amount").toString()));
					myLog.debug("total payable amount:::::" + shoppingCartOpr.getTotalOrderPrice());
				}

				shoppingCartOpr.setConvertedCurrencySymbol((String) resultRow.get("converted_currency_symbol"));
				shoppingCartOpr.setCurrencySymbolFlag((Boolean) resultRow.get("currency_symbol_flag"));

				myLog.debug("Inside shopping cart BC::" + shoppingCartOpr.getCurrencySymbolFlag());

				if (resultRow.get("purchased_delivery_charges") != null) {
					shoppingCartOpr.getRetailOrderRecord().getDeliveryChargesRecord()
							.setDeliveryCharge(Float.valueOf(resultRow.get("purchased_delivery_charges").toString()));
					myLog.debug("purchased_delivery_charges");
				}
				if (resultRow.get("discount_value") != null) {
					shoppingCartOpr.getRetailOrderRecord().setDiscountedPrice(
							Float.valueOf(resultRow.get("discount_value").toString()));
					shoppingCartOpr.getRetailOrderRecord().getVoucherRecord()
							.setVoucherDiscountValue(Float.valueOf(resultRow.get("discount_value").toString()));
					myLog.debug("discount_value");
				}

				if (resultRow.get("voucher_code") != null) {
					shoppingCartOpr.getRetailOrderRecord().getVoucherRecord()
							.setPromoCode((String) resultRow.get("voucher_code"));
					myLog.debug("voucher_code");
				}

				if (resultRow.get("voucher_discount_percent") != null) {
					shoppingCartOpr
							.getRetailOrderRecord()
							.getVoucherRecord()
							.setVoucherValueInPercent(
									Float.valueOf(resultRow.get("voucher_discount_percent").toString()));
					myLog.debug("voucher_discount_percent");
				}

				if (resultRow.get("voucher_discount_absolute") != null) {
					shoppingCartOpr
							.getRetailOrderRecord()
							.getVoucherRecord()
							.setVoucherValueInAbsolute(
									Float.valueOf(resultRow.get("voucher_discount_absolute").toString()));
					myLog.debug("voucher_discount_absolute");
				}

				if (resultRow.get("lead_time") != null) {
					shoppingCartOpr.getRetailOrderRecord().getDeliveryTimeRecord()
							.setLeadTimeInDays(Integer.valueOf(resultRow.get("lead_time").toString()));
					myLog.debug("lead_time"
							+ shoppingCartOpr.getRetailOrderRecord().getDeliveryTimeRecord().getLeadTimeInDays());
				}

				// billing
				paymentDVO.setReceivedOrderId(shoppingCartOpr.getPaymentDVO().getReceivedOrderId());
				paymentDVO.getReceivedBillingAddress().setFirstName((String) resultRow.get("customer_billing_fname"));
				myLog.debug("inside get received billing addresses1 ::::::");
				paymentDVO.getReceivedBillingAddress().setLastName((String) resultRow.get("customer_billing_sname"));
				myLog.debug("inside get received billing addresses2 ::::::");
				paymentDVO.getReceivedBillingAddress().setAddressLine1(
						(String) resultRow.get("customer_billing_address_line1"));
				myLog.debug("inside get received billing addresses3 ::::::");
				paymentDVO.getReceivedBillingAddress().setAddressLine2(
						(String) resultRow.get("customer_billing_address_line2"));
				myLog.debug("inside get received billing addresses4 ::::::");
				if (resultRow.get("customer_billing_address_line3") != null) {
					paymentDVO.getReceivedBillingAddress().setAddressLine3(
							(String) resultRow.get("customer_billing_address_line3"));
				}
				paymentDVO.getReceivedBillingAddress().getCountryDvo()
						.setName((String) resultRow.get("billing_address_country"));
				myLog.debug("inside get received billing addresses5 ::::::");
				paymentDVO.getReceivedBillingAddress().getStateDVO()
						.setName((String) resultRow.get("billing_address_state"));
				myLog.debug("inside get received billing addresses6 ::::::");
				paymentDVO.getReceivedBillingAddress().setPhoneNumber(
						(String) resultRow.get("customer_billing_primary_phone_number"));
				myLog.debug("inside get received billing addresses7 ::::::");
				paymentDVO.getReceivedBillingAddress().setEmail((String) resultRow.get("billing_email_address"));
				myLog.debug("inside get received billing addresses ::::::"
						+ paymentDVO.getReceivedBillingAddress().getEmail());
				paymentDVO.getReceivedBillingAddress().setCity((String) resultRow.get("customer_billing_address_city"));
				myLog.debug("inside get received billing addresses8 ::::::");
				paymentDVO.getReceivedBillingAddress().setZipCode(
						(String) resultRow.get("customer_billing_address_zip_code"));
				myLog.debug("inside get received billing addresses9 ::::::");

				// shipping
				paymentDVO.getReceivedShippingAddress().setFirstName((String) resultRow.get("customer_shipping_fname"));
				myLog.debug("inside get received shipping addresses1 ::::::");
				paymentDVO.getReceivedShippingAddress().setLastName((String) resultRow.get("customer_shipping_sname"));
				myLog.debug("inside get received shipping addresses2 ::::::");
				paymentDVO.getReceivedShippingAddress().setAddressLine1(
						(String) resultRow.get("customer_shipping_address_line1"));
				myLog.debug("inside get received shipping addresses3 ::::::");
				paymentDVO.getReceivedShippingAddress().setAddressLine2(
						(String) resultRow.get("customer_shipping_address_line2"));
				myLog.debug("inside get received shipping addresses4 ::::::");
				if (resultRow.get("customer_shipping_address_line3") != null) {
					paymentDVO.getReceivedShippingAddress().setAddressLine3(
							(String) resultRow.get("customer_shipping_address_line3"));
					myLog.debug("inside get received shipping addresses5 ::::::");
				}
				paymentDVO.getReceivedShippingAddress().getCountryDvo()
						.setName((String) resultRow.get("shipping_address_country"));
				myLog.debug("inside get received shipping addresses6 ::::::");
				paymentDVO.getReceivedShippingAddress().getStateDVO()
						.setName((String) resultRow.get("shipping_address_state"));
				myLog.debug("inside get received shipping addresses7 ::::::");
				paymentDVO.getReceivedShippingAddress().setPhoneNumber(
						(String) resultRow.get("customer_shipping_primary_phone_number"));
				myLog.debug("inside get received shipping addresses8 ::::::");
				paymentDVO.getReceivedShippingAddress().setCity(
						(String) resultRow.get("customer_shipping_address_city"));
				myLog.debug("inside get received shipping addresses9 ::::::");
				paymentDVO.getReceivedShippingAddress().setZipCode(
						(String) resultRow.get("customer_shipping_address_zip_code"));
				myLog.debug("inside get received shipping addresses10 ::::::");

				// order details
				// paymentDVO.setReceivedAmount((String)
				// resultRow.get("total_payable_amount"));
				myLog.debug("inside get received shipping addresses11 ::::::");
				paymentDVO.setReceivedOrderId((String) resultRow.get("order_no"));
				myLog.debug("inside get received shipping addresses12 ::::::");
				paymentDVO.setSentOrderId((String) resultRow.get("order_no"));
				myLog.debug("inside get received shipping addresses12 ::::::");
				paymentDVO.setPaymentType((String) resultRow.get("payment_type"));

				setAuditAttributes(paymentDVO, resultRow);

				shoppingCartOpr.setPaymentDVO(paymentDVO);
			}
		} else {
			myLog.error("ShoppingCartBC :: getOrderHeaderDetails failed :: Return Record empty ::::: ");
			throw new BusinessException("no_data_from_db_excep_msg");
		}

		return shoppingCartOpr;

	}

	public ShoppingCartOpr updateOrderStatusConfirmedForPayPal(ShoppingCartOpr shoppingCartOpr)
			throws FrameworkException {
		// BACKING CLASS METHOD TEMPLATE ver 1.0
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		DAOResult daoResult = new DAOResult();

		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.UPDATE_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ShoppingCartSqlTemplate.UPDATE_ORDER_STATUS_CONFIRMED_PAY_PAL);

		Object strSqlParams[][] = new Object[1][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[0][2] = shoppingCartOpr.getPaymentDVO().getReceivedOrderId();

		myLog.debug("UPDATE_ORDER_STATUS_CONFIRMED: " + strSqlParams[0][2]);
		// in the following call replace null with dynamic where clause if
		// required
		daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);

		// if (daoResult.getUpdateResult() > 0) {
		// queryDetailsMap = new HashMap<String, String>();
		// queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		// queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE,
		// IDAOConstant.PREPARED_STATEMENT);
		// queryDetailsMap.put(IDAOConstant.SQL_TEXT,
		// ShoppingCartSqlTemplate.GET_ORDER_ID);
		// daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		// HashMap responseMap = daoResult.getInvocationResult();
		// myLog.debug(":: Resultset got ::UPDATE_ORDER_STATUS_CONFIRMED: " +
		// responseMap);
		//
		// if (responseMap.size() > 0) {
		// for (int i = 0; i < responseMap.size(); i++) {
		// HashMap resultRow = (HashMap) responseMap.get(i);
		//
		// if (resultRow.get("r_order_header_id") != null) {
		// shoppingCartOpr.getRetailOrderRecord().setId(
		// Long.valueOf(resultRow.get("r_order_header_id").toString()));
		// shoppingCartOpr.getPaymentDVO().setReceivedOrderId(
		// resultRow.get("r_order_header_id").toString());
		// }
		// }
		// }
		// } else {
		// myLog.error("ShoppingCartBC :: updateOrderStatusConfirmed failed :: update unsuccesful");
		// }

		return shoppingCartOpr;
	}

	public ShoppingCartOpr getShippingChargesMode(ShoppingCartOpr shoppingCartOpr) throws FrameworkException,
			BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("inside method getShippingChargesMode:::");
		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		DAOResult daoResult = new DAOResult();

		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ShoppingCartSqlTemplate.GET_SHIPPING_CHARGES_MODE);

		Object strSqlParams[][] = new Object[0][0];

		daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap responseMap = daoResult.getInvocationResult();
		myLog.error(":: Resultset got ::" + responseMap);

		PaymentDVO paymentDVO = new PaymentDVO();
		if (responseMap.size() > 0) {
			for (int i = 0; i < responseMap.size(); i++) {
				HashMap resultRow = (HashMap) responseMap.get(i);

				shoppingCartOpr.setChargesMode((String) resultRow.get("charges_mode"));
			}
		} else {
			myLog.error("ShoppingCartBC :: getShippingChargesMode :: did not find the data related to id provided");
			throw new BusinessException("no_data_from_db_excep_msg");
		}

		return shoppingCartOpr;

	}

	public ShoppingCartOpr getMappedProdutToPromoCode(ShoppingCartOpr shoppingCartOpr) throws FrameworkException,
			BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("inside method getMappedProdutToPromoCode:::");
		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		for (int i = 0; i < shoppingCartOpr.getShoppingCartProductList().size(); i++) {
			DAOResult daoResult = new DAOResult();

			queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
			queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
			queryDetailsMap.put(IDAOConstant.SQL_TEXT, ShoppingCartSqlTemplate.GET_MAPPED_PRODUCT_TO_PROMO_CODE);

			Object strSqlParams[][] = new Object[3][3];

			strSqlParams[0][0] = "1";
			strSqlParams[0][1] = IDAOConstant.STRING_DATATYPE;
			strSqlParams[0][2] = shoppingCartOpr.getRetailOrderRecord().getVoucherRecord().getPromoCode();
			myLog.debug(" parameter 1 ::: " + strSqlParams[0][2]);

			strSqlParams[1][0] = "2";
			strSqlParams[1][1] = IDAOConstant.LONG_DATATYPE;
			strSqlParams[1][2] = shoppingCartOpr.getShoppingCartProductList().get(i).getProductSkuRecord().getId();
			myLog.debug(" parameter 2 ::: " + strSqlParams[1][2]);

			strSqlParams[2][0] = "3";
			strSqlParams[2][1] = IDAOConstant.LONG_DATATYPE;
			strSqlParams[2][2] = shoppingCartOpr.getShoppingCartProductList().get(i).getProductSkuRecord()
					.getVariantId();
			myLog.debug(" parameter 3 ::: " + strSqlParams[2][2]);

			daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
			HashMap responseMap = daoResult.getInvocationResult();
			myLog.error(":: Resultset got ::" + responseMap);

			PaymentDVO paymentDVO = new PaymentDVO();
			if (responseMap.size() > 0) {
				for (int j = 0; j < responseMap.size(); j++) {
					HashMap resultRow = (HashMap) responseMap.get(j);
					if (resultRow.get("v_count") != null) {
						int count = Integer.valueOf(resultRow.get("v_count").toString());
						myLog.debug("check count value::" + count);
						if (count > 0) {

							shoppingCartOpr.getShoppingCartProductList().get(i).getProductRecord()
									.setGiftVoucherDiscountApplicable(true);
						}
					}

					shoppingCartOpr.setChargesMode((String) resultRow.get("charges_mode"));
				}
			}
		}

		return shoppingCartOpr;

	}

	public ShoppingCartOpr saveSmsGateWayResponse(ShoppingCartOpr shoppingCartOpr) throws FrameworkException,
			BusinessException {
		// BACKING CLASS METHOD TEMPLATE ver 1.0
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		DAOResult daoResult = new DAOResult();
		// OrderManagementOpr addOrderManagementOpr = (OrderManagementOpr)
		// saveParameters;
		myLog.debug("Inside InvoiceBC :: saveSmsGateWayResponse"
				+ shoppingCartOpr.getSmsGateWayRecord().getSmsResponseCode());
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.INSERT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, ShoppingCartSqlTemplate.SAVE_SMS_GATEWAYDATA);

		Object strSqlParams[][] = new Object[6][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[0][2] = "Order Confirmed";
		myLog.debug("strSqlParams[0][2]::::" + strSqlParams[0][2]);

		strSqlParams[1][0] = "2";
		strSqlParams[1][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[1][2] = shoppingCartOpr.getSmsGateWayRecord().getSmsResponseCode();
		myLog.debug("strSqlParams[1][2]::::" + strSqlParams[1][2]);

		strSqlParams[2][0] = "3";
		strSqlParams[2][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[2][2] = shoppingCartOpr.getSmsGateWayRecord().getDestinationNumber();
		myLog.debug("strSqlParams[2][2]::::" + strSqlParams[2][2]);

		strSqlParams[3][0] = "4";
		strSqlParams[3][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[3][2] = shoppingCartOpr.getSmsGateWayRecord().getOrderNumber();
		myLog.debug("strSqlParams[3][2]::::" + strSqlParams[3][2]);

		strSqlParams[4][0] = "5";
		strSqlParams[4][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[4][2] = shoppingCartOpr.getSmsGateWayRecord().getAuditAttributes().getModifiedBy();
		myLog.debug("strSqlParams[4][2]::::" + strSqlParams[4][2]);

		strSqlParams[5][0] = "6";
		strSqlParams[5][1] = IDAOConstant.STRING_DATATYPE;
		strSqlParams[5][2] = shoppingCartOpr.getSmsGateWayRecord().getAuditAttributes().getModifiedBy();
		myLog.debug("strSqlParams[5][2]::::" + strSqlParams[5][2]);

		performDBOperation(queryDetailsMap, strSqlParams, null);

		return shoppingCartOpr;
	}

	public ShoppingCartOpr populateShippingCharges(ShoppingCartOpr shoppingCartOpr) throws FrameworkException,
			BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());

		ShoppingCartOpr shoppingCartOprRet = new ShoppingCartOpr();

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		DAOResult daoResult = new DAOResult();
		myLog.debug("inside bc populateShippingCharges::::::::");

		String country = shoppingCartOpr.getRetailOrderRecord().getShippingDetails().getCountryDvo().getCode();

		for (int i = 0; i < shoppingCartOpr.getShoppingCartProductList().size(); i++) {
			ShoppingCartProductDVO shoppingCartProductDVO = shoppingCartOpr.getShoppingCartProductList().get(i);

			Long hierarchyId = shoppingCartProductDVO.getProductSkuRecord().getHierarchyCategoryMappingRecord()
					.getHierarchyRecord().getId();
			Long categoryLevel1Id = shoppingCartProductDVO.getProductSkuRecord().getHierarchyCategoryMappingRecord()
					.getCategoryLevelOneRecord().getId();
			Long categoryLevel2Id = shoppingCartProductDVO.getProductSkuRecord().getHierarchyCategoryMappingRecord()
					.getCategoryLevelTwoRecord().getId();
			Long categoryLevel3Id = shoppingCartProductDVO.getProductSkuRecord().getHierarchyCategoryMappingRecord()
					.getCategoryLevelThreeRecord().getId();
			Long categoryLevel4Id = shoppingCartProductDVO.getProductSkuRecord().getHierarchyCategoryMappingRecord()
					.getCategoryLevelFourRecord().getId();

			Object strSqlParams[][];
			queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
			queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
			queryDetailsMap.put(IDAOConstant.SQL_TEXT, ShoppingCartSqlTemplate.POPULATE_SHIPPING_CHARGES);
			strSqlParams = new Object[6][3];

			strSqlParams[0][0] = "1";
			strSqlParams[0][1] = IDAOConstant.STRING_DATATYPE;
			strSqlParams[0][2] = country;
			myLog.debug("country :::" + country);

			strSqlParams[1][0] = "2";
			strSqlParams[1][1] = IDAOConstant.LONG_DATATYPE;
			strSqlParams[1][2] = hierarchyId;
			myLog.debug("hierarchyId :::" + hierarchyId);

			strSqlParams[2][0] = "3";
			strSqlParams[2][1] = IDAOConstant.LONG_DATATYPE;
			strSqlParams[2][2] = categoryLevel1Id;
			myLog.debug("categoryLevel1Id :::" + categoryLevel1Id);

			strSqlParams[3][0] = "4";
			strSqlParams[3][1] = IDAOConstant.LONG_DATATYPE;
			strSqlParams[3][2] = categoryLevel2Id;
			myLog.debug("categoryLevel2Id :::" + categoryLevel2Id);

			strSqlParams[4][0] = "5";
			strSqlParams[4][1] = IDAOConstant.LONG_DATATYPE;
			strSqlParams[4][2] = categoryLevel3Id;
			myLog.debug("categoryLevel3Id :::" + categoryLevel3Id);

			strSqlParams[5][0] = "6";
			strSqlParams[5][1] = IDAOConstant.LONG_DATATYPE;
			strSqlParams[5][2] = categoryLevel4Id;
			myLog.debug("categoryLevel4Id :::" + categoryLevel4Id);

			daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
			HashMap responseMap = daoResult.getInvocationResult();
			myLog.debug("populateShippingCharges :: Resultset got ::" + responseMap);

			if (responseMap.size() > 0) {
				for (int j = 0; j < responseMap.size(); j++) {
					HashMap resultRow = (HashMap) responseMap.get(j);
					DeliveryChargesDVO deliveryChargesDVO = new DeliveryChargesDVO();

					if (resultRow.get("v_delivery_charge") != null) {
						myLog.debug("v_delivery_charge:::" + resultRow.get("v_delivery_charge"));
						deliveryChargesDVO.setDeliveryCharge(Float.valueOf(resultRow.get("v_delivery_charge")
								.toString()));
					}

					if (resultRow.get("v_processing_charge") != null) {
						deliveryChargesDVO.setProcessingCharge(Float.valueOf(resultRow.get("v_processing_charge")
								.toString()));
					}

					if (resultRow.get("v_duties_charge") != null) {
						deliveryChargesDVO.setDutiesCharge(Float.valueOf(resultRow.get("v_duties_charge").toString()));
					}

					if (resultRow.get("v_express_charge") != null) {
						deliveryChargesDVO
								.setExpressCharge(Float.valueOf(resultRow.get("v_express_charge").toString()));
					}

					shoppingCartProductDVO.getProductSkuRecord().setDeliverChargesRecord(deliveryChargesDVO);
				}
			} else {
				myLog.error("Product Management :: executeSearch failed :: Return Record empty ::::: ");
				throw new BusinessException("no_data_from_db_excep_msg");
			}
			shoppingCartOprRet.getShoppingCartProductList().add(shoppingCartProductDVO);
		}

		return shoppingCartOprRet;
	}
}
