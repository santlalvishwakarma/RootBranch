package com.web.bc.systemowner.modules.master.ordermanagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.web.common.dvo.common.Parameter;
import com.web.common.dvo.opr.systemowner.RetailOrderOpr;
import com.web.common.dvo.retail.modules.RetailOrderDVO;
import com.web.common.dvo.retail.modules.RetailOrderDetailsDVO;
import com.web.common.parents.BackingClass;
import com.web.foundation.dao.DAOResult;
import com.web.foundation.dao.IDAOConstant;
import com.web.foundation.exception.BusinessException;
import com.web.foundation.exception.FrameworkException;
import com.web.foundation.logger.ITSDLogger;
import com.web.foundation.logger.TSDLogger;

public class OrderMasterBC extends BackingClass {

	public RetailOrderOpr executeSearch(RetailOrderOpr searchRetailOrderOpr)
			throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug("Inside executeSearch: ");

		String orderNumber = searchRetailOrderOpr.getRetailOrderRecord().getOrderNumber();
		String userLogin = searchRetailOrderOpr.getRetailOrderRecord().getUserRecord().getUserLogin();
		Integer paymentStatus = searchRetailOrderOpr.getRetailOrderRecord().getPaymentStatus().getParameterID();
		Integer orderStatus = searchRetailOrderOpr.getRetailOrderRecord().getOrderStatus().getParameterID();

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, OrderMasterSqlTemplate.SEARCH_ORDERS);

		StringBuffer dynamicWhere = new StringBuffer();
		int parameterCount = 0;

		if (orderNumber != null && orderNumber.trim().length() > 0) {
			orderNumber = orderNumber.trim().concat("%");
			dynamicWhere.append(" order_no LIKE '" + orderNumber + "'");
			parameterCount++;
		}

		if (userLogin != null && userLogin.trim().length() > 0) {
			userLogin = userLogin.trim().concat("%");
			if (parameterCount > 0) {
				dynamicWhere.append(" AND user_login = '" + userLogin + "'");
			} else {
				dynamicWhere.append(" user_login = '" + userLogin + "'");
			}
			parameterCount++;
		}

		if (paymentStatus != null && !paymentStatus.equals(0)) {
			if (parameterCount > 0) {
				dynamicWhere.append(" AND payment_status = " + paymentStatus + "");
			} else {
				dynamicWhere.append(" payment_status = " + paymentStatus + "");
			}
			parameterCount++;
		}

		if (orderStatus != null && !orderStatus.equals(0)) {
			if (parameterCount > 0) {
				dynamicWhere.append(" AND order_status = " + orderStatus + "");
			} else {
				dynamicWhere.append(" order_status = " + orderStatus + "");
			}
			parameterCount++;
		}

		myLog.debug("Dynamic Where : " + dynamicWhere);
		System.out.println(dynamicWhere);
		Object strSqlParams[][] = new Object[0][3];

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, dynamicWhere.toString());
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" executeSearch :: Resultset got ::" + responseMap);

		List<RetailOrderDVO> orderList = new ArrayList<RetailOrderDVO>();
		int responseSize = responseMap.size();
		if (responseSize > 0) {

			for (int i = 0; i < responseSize; i++) {

				HashMap<String, Object> resultSetMap = responseMap.get(i);

				RetailOrderDVO retailOrderRecord = new RetailOrderDVO();
				Parameter paymentStatusParameter = new Parameter();
				Parameter orderStatusParameter = new Parameter();

				if (resultSetMap.get("order_header_id") != null) {
					retailOrderRecord.setId(Long.valueOf(resultSetMap.get("order_header_id").toString()));
				}

				if (resultSetMap.get("user_id") != null) {
					retailOrderRecord.getUserRecord().setId(Long.valueOf(resultSetMap.get("user_id").toString()));
				}

				retailOrderRecord.getUserRecord().setUserLogin((String) resultSetMap.get("user_login"));

				retailOrderRecord.setOrderNumber((String) resultSetMap.get("order_no"));

				if (resultSetMap.get("total_quantity") != null) {
					retailOrderRecord.setTotalQuantity(Integer.valueOf(resultSetMap.get("total_quantity").toString()));
				}

				if (resultSetMap.get("total_amount") != null) {
					retailOrderRecord.setTotalAmount(Float.valueOf(resultSetMap.get("total_amount").toString()));
				}

				if (resultSetMap.get("payment_status") != null) {
					paymentStatusParameter
							.setParameterID(Integer.valueOf(resultSetMap.get("payment_status").toString()));
				}
				paymentStatusParameter.setParameterStringValue((String) resultSetMap.get("payment_status_value"));
				retailOrderRecord.setPaymentStatus(paymentStatusParameter);

				if (resultSetMap.get("order_status") != null) {
					orderStatusParameter.setParameterID(Integer.valueOf(resultSetMap.get("order_status").toString()));
				}
				orderStatusParameter.setParameterStringValue((String) resultSetMap.get("order_status_value"));
				retailOrderRecord.setOrderStatus(orderStatusParameter);

				setAuditAttributes(retailOrderRecord, resultSetMap);

				orderList.add(retailOrderRecord);
			}
		} else {
			throw new BusinessException("no_data_from_db_excep_msg");
		}
		searchRetailOrderOpr.setRetailOrderList(orderList);

		return searchRetailOrderOpr;
	}

	public RetailOrderOpr getOrderHeaderRecord(RetailOrderOpr editRetailOrderOpr)
			throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug(" inside getOrderHeaderRecord: ");

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, OrderMasterSqlTemplate.GET_ORDER_HEADER);

		Object strSqlParams[][] = new Object[1][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[0][2] = editRetailOrderOpr.getRetailOrderRecord().getId();

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" executeSearch :: Resultset got ::" + responseMap);

		RetailOrderDVO retailOrderRecord = editRetailOrderOpr.getRetailOrderRecord();
		int responseSize = responseMap.size();
		if (responseSize > 0) {

			for (int i = 0; i < responseSize; i++) {
				HashMap<String, Object> resultSetMap = responseMap.get(i);

				setAuditAttributes(retailOrderRecord, resultSetMap);
				if (resultSetMap.get("user_id") != null) {
					retailOrderRecord.getUserRecord().setId(Long.valueOf(resultSetMap.get("user_id").toString()));
				}

				retailOrderRecord.setUserLogin((String) resultSetMap.get("user_login"));
				retailOrderRecord.getUserRecord().setUserLogin((String) resultSetMap.get("user_login"));

				retailOrderRecord.setOrderNumber((String) resultSetMap.get("order_no"));

				if (resultSetMap.get("total_quantity") != null) {
					retailOrderRecord.setTotalQuantity(Integer.valueOf(resultSetMap.get("total_quantity").toString()));
				}

				if (resultSetMap.get("lead_time") != null) {
					retailOrderRecord.getDeliveryTimeRecord()
							.setLeadTimeInDays(Integer.valueOf(resultSetMap.get("lead_time").toString()));
				}

				if (resultSetMap.get("total_amount") != null) {
					retailOrderRecord.setTotalAmount(Float.valueOf(resultSetMap.get("total_amount").toString()));
				}

				if (resultSetMap.get("original_total_amount") != null) {
					retailOrderRecord.setOriginalTotalPayableAmount(
							Float.valueOf(resultSetMap.get("original_total_amount").toString()));
				}

				if (resultSetMap.get("express_delivery_charge") != null) {
					retailOrderRecord.getDeliveryChargesRecord()
							.setExpressCharge(Float.valueOf(resultSetMap.get("express_delivery_charge").toString()));
				}

				if (resultSetMap.get("original_express_delivery_charge") != null) {
					retailOrderRecord.getDeliveryChargesRecord().setOriginalExpressCharge(
							Float.valueOf(resultSetMap.get("original_express_delivery_charge").toString()));
				}

				if (resultSetMap.get("duties") != null) {
					retailOrderRecord.getDeliveryChargesRecord()
							.setDutiesCharge(Float.valueOf(resultSetMap.get("duties").toString()));
				}

				if (resultSetMap.get("original_duties") != null) {
					retailOrderRecord.getDeliveryChargesRecord()
							.setOriginalDutiesCharge(Float.valueOf(resultSetMap.get("original_duties").toString()));
				}

				if (resultSetMap.get("currency_conversion_rate") != null) {
					retailOrderRecord.getDeliveryChargesRecord().getCurrencyRecord().setCurrencyConversionMultiplier(
							Float.valueOf(resultSetMap.get("currency_conversion_rate").toString()));
				}

				if (resultSetMap.get("currency_code") != null) {
					retailOrderRecord.getDeliveryChargesRecord().getCurrencyRecord()
							.setCode((String) resultSetMap.get("currency_code").toString());
				}

				if (resultSetMap.get("currency_symbol_flag") != null) {
					retailOrderRecord.getDeliveryChargesRecord().getCurrencyRecord()
							.setCurrencySymbol((String) resultSetMap.get("currency_symbol_flag").toString());
				}

				if (resultSetMap.get("payment_status") != null) {
					retailOrderRecord.getPaymentStatus()
							.setParameterID(Integer.valueOf(resultSetMap.get("payment_status").toString()));
				}
				retailOrderRecord.getPaymentStatus()
						.setParameterStringValue((String) resultSetMap.get("payment_status_value"));

				if (resultSetMap.get("order_status") != null) {
					retailOrderRecord.getOrderStatus()
							.setParameterID(Integer.valueOf(resultSetMap.get("order_status").toString()));
				}
				retailOrderRecord.getOrderStatus()
						.setParameterStringValue((String) resultSetMap.get("order_status_value"));

				if (resultSetMap.get("billing_status") != null) {
				}

				if (resultSetMap.get("billing_first_name") != null) {
					retailOrderRecord.getBillingDetails()
							.setFirstName((String) resultSetMap.get("billing_first_name").toString());
				}

				if (resultSetMap.get("billing_middle_name") != null) {
					retailOrderRecord.getBillingDetails()
							.setMiddleName((String) resultSetMap.get("billing_middle_name").toString());
				}

				if (resultSetMap.get("billing_last_name") != null) {
					retailOrderRecord.getBillingDetails()
							.setLastName((String) resultSetMap.get("billing_last_name").toString());
				}

				if (resultSetMap.get("billing_email_address_1") != null) {
					retailOrderRecord.getBillingDetails()
							.setEmail1((String) resultSetMap.get("billing_email_address_1").toString());
				}

				if (resultSetMap.get("billing_email_address_2") != null) {
					retailOrderRecord.getBillingDetails()
							.setEmail2((String) resultSetMap.get("billing_email_address_2").toString());
				}

				if (resultSetMap.get("billing_mobile_1") != null) {
					retailOrderRecord.getBillingDetails()
							.setMobileNo((String) resultSetMap.get("billing_mobile_1").toString());
				}

				if (resultSetMap.get("billing_mobile_2") != null) {
					retailOrderRecord.getBillingDetails()
							.setPhone1((String) resultSetMap.get("billing_mobile_2").toString());
				}

				if (resultSetMap.get("billing_address_line_1") != null) {
					retailOrderRecord.getBillingDetails()
							.setAddressLine1((String) resultSetMap.get("billing_address_line_1").toString());
				}

				if (resultSetMap.get("billing_address_line_2") != null) {
					retailOrderRecord.getBillingDetails()
							.setAddressLine2((String) resultSetMap.get("billing_address_line_2").toString());
				}

				if (resultSetMap.get("billing_address_line_3") != null) {
					retailOrderRecord.getBillingDetails()
							.setAddressLine3((String) resultSetMap.get("billing_address_line_3").toString());
				}

				if (resultSetMap.get("billing_city") != null) {
					retailOrderRecord.getBillingDetails().getCityDvo()
							.setCityName((String) resultSetMap.get("billing_city").toString());
				}

				if (resultSetMap.get("billing_state") != null) {
					retailOrderRecord.getBillingDetails().getStateDVO()
							.setName((String) resultSetMap.get("billing_state").toString());
				}

				if (resultSetMap.get("billing_country") != null) {
					retailOrderRecord.getBillingDetails().getCountryDvo()
							.setName((String) resultSetMap.get("billing_country").toString());
				}

				if (resultSetMap.get("billing_city") != null) {
					retailOrderRecord.getBillingDetails()
							.setPincode((String) resultSetMap.get("billing_city").toString());
				}

				if (resultSetMap.get("order_tracking_number") != null) {
					retailOrderRecord
							.setOrderTrackingNumber((String) resultSetMap.get("order_tracking_number").toString());
				}

				if (resultSetMap.get("order_date") != null) {
					// retailOrderRecord.setOrderDate(Date.parse(resultSetMap.get("order_date").toString());
				}

				if (resultSetMap.get("order_date") != null) {
					// retailOrderRecord.setDeliveryDate(Date.parse(resultSetMap.get("order_date").toString()));
				}

				if (resultSetMap.get("courier_id") != null) {
					retailOrderRecord.getCourierRecord()
							.setParameterID(Integer.valueOf(resultSetMap.get("courier_id").toString()));
				}

				if (resultSetMap.get("voucher_code") != null) {
					retailOrderRecord.getVoucherRecord().setCode((String) resultSetMap.get("voucher_code").toString());
				}

				if (resultSetMap.get("voucher_discount_percent") != null) {
					retailOrderRecord.getVoucherRecord().setVoucherValueInAbsolute(
							Float.valueOf(resultSetMap.get("voucher_discount_percent").toString()));
				}

				if (resultSetMap.get("voucher_discount_absolute") != null) {
					retailOrderRecord.getVoucherRecord().setVoucherValueInAbsolute(
							Float.valueOf(resultSetMap.get("voucher_discount_absolute").toString()));
				}

				if (resultSetMap.get("discount_value") != null) {
					retailOrderRecord.getVoucherRecord()
							.setVoucherDiscountValue(Float.valueOf(resultSetMap.get("discount_value").toString()));
				}

				if (resultSetMap.get("gift_wrapping_required") != null) {
					retailOrderRecord.setGiftWrappingRequired((Boolean) resultSetMap.get("gift_wrapping_required"));
				}

				if (resultSetMap.get("order_comments") != null) {
					retailOrderRecord.setOrderComments((String) resultSetMap.get("order_comments"));
				}

				if (resultSetMap.get("email_regenerated_by") != null) {
				}

				if (resultSetMap.get("promotion_source") != null) {
					retailOrderRecord.setPromotionSource((String) resultSetMap.get("promotion_source"));
				}

				if (resultSetMap.get("referer_source") != null) {
				}

				if (resultSetMap.get("payment_status_modifier") != null) {
				}

				// retailOrderRecord
			}
		}

		return editRetailOrderOpr;
	}

	public RetailOrderOpr getOrderDetailRecord(RetailOrderOpr editRetailOrderOpr)
			throws FrameworkException, BusinessException {
		ITSDLogger myLog = TSDLogger.getLogger(this.getClass().getName());
		myLog.debug(" inside getOrderDetailRecord: ");

		HashMap<String, String> queryDetailsMap = new HashMap<String, String>();
		queryDetailsMap.put(IDAOConstant.SQL_TYPE, IDAOConstant.SELECT_SQL);
		queryDetailsMap.put(IDAOConstant.STATEMENT_TYPE, IDAOConstant.PREPARED_STATEMENT);
		queryDetailsMap.put(IDAOConstant.SQL_TEXT, OrderMasterSqlTemplate.GET_ORDER_DETAIL);

		Object strSqlParams[][] = new Object[1][3];

		strSqlParams[0][0] = "1";
		strSqlParams[0][1] = IDAOConstant.LONG_DATATYPE;
		strSqlParams[0][2] = editRetailOrderOpr.getRetailOrderRecord().getId();

		DAOResult daoResult = performDBOperation(queryDetailsMap, strSqlParams, null);
		HashMap<Integer, HashMap<String, Object>> responseMap = daoResult.getInvocationResult();
		myLog.debug(" executeSearch :: Resultset got ::" + responseMap);

		RetailOrderDVO retailOrderRecord = editRetailOrderOpr.getRetailOrderRecord();
		ArrayList<RetailOrderDetailsDVO> orderDetails = new ArrayList<RetailOrderDetailsDVO>();

		int responseSize = responseMap.size();
		if (responseSize > 0) {

			for (int i = 0; i < responseSize; i++) {
				HashMap<String, Object> resultSetMap = responseMap.get(i);

				RetailOrderDetailsDVO retailOrderDetailsRecord = new RetailOrderDetailsDVO();

				if (resultSetMap.get("order_detail_id") != null) {
					retailOrderDetailsRecord.setId(Long.valueOf(resultSetMap.get("order_detail_id").toString()));
				}

				if (resultSetMap.get("product_id") != null) {
					retailOrderDetailsRecord.getProductSkuRecord().getProductRecord()
							.setId(Long.valueOf(resultSetMap.get("product_id").toString()));
				}

				if (resultSetMap.get("product_sku_id") != null) {
					retailOrderDetailsRecord.getProductSkuRecord()
							.setId(Long.valueOf(resultSetMap.get("product_sku_id").toString()));
				}

				retailOrderDetailsRecord.getProductSkuRecord().getProductRecord()
						.setName((String) resultSetMap.get("product_name"));
				retailOrderDetailsRecord.getProductSkuRecord().getProductRecord()
						.setCode((String) resultSetMap.get("product_code"));

				retailOrderDetailsRecord.getProductSkuRecord().setName((String) resultSetMap.get("sku_code"));
				retailOrderDetailsRecord.getProductSkuRecord().setCode((String) resultSetMap.get("sku_name"));

				if (resultSetMap.get("price_per_piece") != null) {
					retailOrderDetailsRecord
							.setPricePerPiece(Float.valueOf(resultSetMap.get("price_per_piece").toString()));
				}

				if (resultSetMap.get("original_price_per_piece") != null) {
					retailOrderDetailsRecord.setOriginalPricePerPiece(
							Float.valueOf(resultSetMap.get("original_price_per_piece").toString()));
				}

				if (resultSetMap.get("total_price") != null) {
					retailOrderDetailsRecord.setTotalPrice(Float.valueOf(resultSetMap.get("total_price").toString()));
				}

				if (resultSetMap.get("original_total_price") != null) {
					retailOrderDetailsRecord
							.setOriginalTotalPrice(Float.valueOf(resultSetMap.get("original_total_price").toString()));
				}

				if (resultSetMap.get("quantity") != null) {
					retailOrderDetailsRecord
							.setProductQuantity(Integer.valueOf(resultSetMap.get("quantity").toString()));
				}

				if (resultSetMap.get("delivery_date") != null) {
					// TODO: DN
					// field to add
				}

				if (resultSetMap.get("jewellery_valuation") != null) {
					// TODO: DN
					// field to add
				}

				if (resultSetMap.get("customization_text") != null) {
					// TODO: DN
					// field to add
				}

				if (resultSetMap.get("customization_font") != null) {
					// TODO: DN
					// field to add
				}

				if (resultSetMap.get("size_id") != null) {
					// TODO: DN
					// field to add
				}

				if (resultSetMap.get("product_shipping_charges") != null) {
					// TODO: DN
					// field to add7
				}

				if (resultSetMap.get("product_shipping_charges_original") != null) {
					// TODO: DN
					// field to add7
				}

				if (resultSetMap.get("product_shipping_duties") != null) {
					// TODO: DN
					// field to add7
				}

				if (resultSetMap.get("product_shipping_duties_original") != null) {
					// TODO: DN
					// field to add7
				}

				if (resultSetMap.get("product_shipping_express_charges") != null) {
					// TODO: DN
					// field to add7
				}

				if (resultSetMap.get("product_shipping_express_charges_original") != null) {
					// TODO: DN
					// field to add7
				}

				if (resultSetMap.get("product_shipping_processing_charges") != null) {
					// TODO: DN
					// field to add7
				}

				if (resultSetMap.get("product_shipping_processing_charges_original") != null) {
					// TODO: DN
					// field to add7
				}

				if (resultSetMap.get("product_shipping_mapped") != null) {
					// TODO: DN
					// field to add7
				}

				if (resultSetMap.get("store_location_id") != null) {
					// TODO: DN
					// field to add7
				}

				if (resultSetMap.get("shipping_status") != null) {
					// TODO: DN
					// field to add7
				}

				orderDetails.add(retailOrderDetailsRecord);
			}
		}
		retailOrderRecord.setOrderDetailsList(orderDetails);
		return editRetailOrderOpr;
	}
}
