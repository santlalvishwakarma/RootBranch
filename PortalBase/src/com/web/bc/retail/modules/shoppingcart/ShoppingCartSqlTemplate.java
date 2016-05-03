package com.web.bc.retail.modules.shoppingcart;

import com.web.foundation.dao.SqlTemplate;

public interface ShoppingCartSqlTemplate extends SqlTemplate {

	static final String SAVE_PRODUCT_FOR_LATER = "INSERT INTO r_saved_shopping_cart_products(r_saved_shopping_cart_products_id,"
			+ "product_id, product_variant_id, user_login, product_saved_date, created_by, created_date, modified_by, modified_date) "
			+ "VALUES (NULL,?,?, ?,NOW(),?,NOW(),?,NOW());";

	static final String GET_SAVED_PRODUCTS = " SELECT r_saved_shopping_cart_products_id,product_id, product_variant_id, user_login, "
			+ "product_saved_date, created_by, created_date, modified_by, modified_date "
			+ "FROM r_saved_shopping_cart_products WHERE user_login=?";

	static final String DELETE_PRODUCT_FROM_SAVED_LIST = "DELETE FROM r_saved_shopping_cart_products "
			+ "WHERE r_saved_shopping_cart_products_id = ?";

	static final String GENERATE_NEW_ORDER = "CALL sp_retail_generate_new_order(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

	static final String GET_DELIVERY_CHARGES_FOR_COUNTRY = "SELECT cdc.delivery_charge "
			+ "FROM core_delivery_charges cdc where cdc.country_id=?;";

	static final String GET_DELIVERY_TIME_FOR_COUNTRY = "SELECT cdt.leadtime_days, cdt.leadtime_days_stock_unavailable  "
			+ "FROM core_delivery_time cdt WHERE cdt.country_id=?;";

	static final String USER_DETAILS_QUERY = "SELECT cu.user_id, cu.user_login, cu.login_password, cu.first_name, cu.middle_name, cu.last_name, cu.primary_email_id, "
			+ " cu.alternate_email_id, cu.primary_phone_number, cu.alternate_phone_number, cu.birth_date, cu.anniversary_date, cu.billing_address_id, cu.shipping_address_id, cb.address_line1 'billing_address_line1', "
			+ " cb.address_line2 'billing_address_line2', cb.address_line3 'billing_address_line3', cb.country_code 'billing_country_code', fn_core_get_country_name(cb.country_code) 'billing_country_name', "
			+ " cb.state_code 'billing_state_code', fn_core_get_state_name(cb.state_code) 'billing_state_name', "
			+ " cb.city_code 'billing_city_code', fn_core_get_city_name(cb.city_code) 'billing_city_name', cb.zip_code 'billing_zip_code', cb.email_1 'billing_email1', cb.email_2 'billing_email2', "
			+ " cb.contact_person_1 'billing_contact_person_1', cb.contact_person_2 'billing_contact_person_2', cb.landmark 'billing_landmark', "
			+ " cs.address_line1 'shipping_address_line1', cs.address_line2 'shipping_address_line2', cs.address_line3 'shipping_address_line3', cs.country_code 'shipping_country_code', "
			+ " fn_core_get_country_name(cs.country_code) 'shipping_country_name', cs.state_code 'shipping_state_code', fn_core_get_state_name(cs.state_code) 'shipping_state_name', "
			+ " cs.city_code 'shipping_city_code', fn_core_get_city_name(cs.city_code) 'shipping_city_name', cs.zip_code 'shipping_zip_code', cs.email_1 'shipping_email1', cs.email_2 'shipping_email2', "
			+ " cs.contact_person_1 'shipping_contact_person_1', cs.contact_person_2 'shipping_contact_person_2', cs.landmark 'shipping_landmark', "
			+ " cu.marital_status, fn_get_parameter_value_text(cu.marital_status) 'marital_status_value', "
			+ " cu.is_admin, cu.is_active, cu.newsletter_subscription, cu.sms_alert_subscription "
			+ " FROM core_user_master cu, core_billing_address cb, core_shipping_address cs WHERE user_login = ? AND login_password = MD5(?) "
			+ " AND cu.shipping_address_id = cs.shipping_address_id AND cu.billing_address_id = cb.billing_address_id ;";

	static final String PROMO_CODE_DETAILS = "SELECT r_vouchers_id, rv.r_voucher_schemes_id, voucher_code, voucher_from_date, rv.voucher_type, rv.voucher_value_in_percent, rv.voucher_value_in_absolute, rv.voucher_value_type, generation_date, rv.created_by, rv.created_date, rv.modified_by, "
			+ " rv.modified_date, voucher_to_date, is_active, is_voucher_retired, promo_code, product_price, invoice_amount, max_discount_value "
			+ " FROM r_vouchers rv, r_voucher_schemes rvs where promo_code =? "
			+ " AND is_active=(SELECT core_parameters_id FROM core_parameters WHERE param_code = 'YES_NO' AND sequence_number = 1)"
			+ " AND is_voucher_retired=(SELECT core_parameters_id FROM core_parameters WHERE param_code = 'YES_NO' AND sequence_number = 2) "
			+ " AND voucher_to_date BETWEEN curdate() AND (select voucher_to_date from r_vouchers where promo_code=?) "
			+ " AND rv.r_voucher_schemes_id = rvs.r_voucher_schemes_id;";

	static final String UPDATE_ORDER_PENDING_DISPATCH = "update r_order_header "
			+ " set order_status=(select core_parameters_id from core_parameters "
			+ " where param_code='ORDER_STATUS' and sequence_number=2),"
			+ " payment_status=(select core_parameters_id from core_parameters "
			+ " where param_code='PAYMENT_STATUS' and sequence_number=2) WHERE r_order_header_id= ?;";

	static final String UPDATE_ORDER_CANCELLED = "update r_order_header "
			+ " set order_status=(select core_parameters_id from core_parameters "
			+ " where param_code='ORDER_STATUS' and sequence_number=2),"
			+ " payment_status=(select core_parameters_id from core_parameters "
			+ " where param_code='PAYMENT_STATUS' and sequence_number=3) WHERE r_order_header_id= ?;";

	static final String INIT_PAYMENT_GATEWAY_TRANSACTION = "CALL sp_retail_insert_payment_gateway_details(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	// static final String GET_PAYMENT_GATEWAY_TRANSACTION_PK =
	// "SELECT r_payment_transactions_id FROM r_payment_transactions WHERE sent_checksum = ?";

	static final String UPDATE_PAYMENT_GATEWAY_TRANSACTION = "UPDATE r_payment_transactions SET received_amount = ?, received_auth_desc = ?, received_bank_name = ?,received_card_category = ?, "
			+ " received_checksum = ?,received_merchant_id = ?, received_merchant_parameter = ?,received_nb_bid = ?,received_nb_order_number = ?,received_notes = ?,received_order_id = ?,"
			+ " received_return_url = ?,received_billing_customer_name = ?,received_billing_customer_address = ?,received_billing_customer_country = ?,received_billing_customer_state = ?,"
			+ " received_billing_customer_tel = ?,received_billing_customer_email = ?,received_billing_customer_city = ?,received_billing_customer_zip_code = ?,received_delivery_customer_name = ?,"
			+ " received_delivery_customer_address = ?,received_delivery_customer_country = ?,received_delivery_customer_state = ?,received_delivery_customer_tel = ?,received_delivery_customer_city = ?,"
			+ " received_delivery_customer_zip_code = ?,error_message = ?,modified_by = ?,modified_date = NOW(), received_transactiion_id = ?, received_transaction_date = NOW(),"
			+ " received_server_mode = ?, received_transaction_reference_number=?, received_auth_id_code=?, received_rrn=?, received_cvresp_code=?, received_fdms_score=?, received_fdms_result=?, received_cookie=? WHERE r_payment_transactions_id = ?";

	static final String GET_PAYMENT_DETAILS_FOR_PAYMENT_ID = "SELECT r_payment_transactions_id, sent_merchant_id, sent_amount,"
			+ " sent_order_id, sent_checksum, sent_billing_customer_name, sent_billing_customer_address, sent_billing_customer_country,"
			+ " sent_billing_customer_state, sent_billing_customer_tel, sent_billing_customer_email, sent_billing_customer_city,"
			+ " sent_billing_customer_zip_code, sent_delivery_customer_name, sent_delivery_customer_address, sent_delivery_customer_country,"
			+ " sent_delivery_customer_state, sent_delivery_customer_tel, sent_delivery_customer_city, sent_delivery_customer_zip_code,"
			+ " sent_merchant_parameter, received_amount, received_checksum, received_merchant_id, received_merchant_parameter,"
			+ " received_order_id, received_billing_customer_name, received_billing_customer_address,"
			+ " received_billing_customer_country, received_billing_customer_state, received_billing_customer_tel,"
			+ " received_billing_customer_email, received_billing_customer_city, received_billing_customer_zip_code,"
			+ " received_delivery_customer_name, received_delivery_customer_address, received_delivery_customer_country,"
			+ " received_delivery_customer_state, received_delivery_customer_tel, received_delivery_customer_city,"
			+ " received_delivery_customer_zip_code, error_message, sent_transaction_date, received_transaction_date,"
			+ " sent_server_mode, received_server_mode"
			+ " FROM r_payment_transactions WHERE r_payment_transactions_id = ?;";

	static final String CHECK_USER_AVAILABILITY = "SELECT user_login FROM core_user_master WHERE user_login = ?;";

	static final String GET_PAYMENT_DETAILS_FOR_RECEIVED_MERCHANT_PARAMETER = "SELECT received_order_id,received_merchant_parameter FROM r_payment_transactions "
			+ "WHERE sent_merchant_parameter = ?;";

	// added by dheeraj as ICICI payment gateway does not return received order
	// id
	static final String GET_PAYMENT_DETAILS_FOR_SENT_MERCHANT_PARAMETER = "SELECT sent_order_id FROM r_payment_transactions "
			+ "WHERE r_payment_transactions_id = ?;";

	static final String ORDER_DETAILS = " SELECT tod.product_id, tod.price_per_piece, tdm.product_image_url, tdm.product_thumbnail_url, "
			+ " tod.quantity, tod.total_price, tdm.product_short_name product_short_name, tdm.product_description, tdm.product_code, "
			+ " tdm.currency_id, cc.currency_symbol, tdm.discount_price, tdm.core_product_variants_id, tod.customization_font, "
			+ " tod.customization_text, tod.size_code, roh.converted_currency_symbol, roh.currency_conversion_rate, "
			+ " tod.original_price_per_piece, tod.original_total_price, tdm.hierarchy_name, "
			+ " tdm.level_1_hierarchy_id, category_name_fc(tdm.level_1_hierarchy_id) level_1_hierarchy_name, "
			+ " tdm.level_2_hierarchy_id, category_name_fc(tdm.level_2_hierarchy_id) level_2_hierarchy_name, "
			+ " tdm.level_3_hierarchy_id, category_name_fc(tdm.level_3_hierarchy_id) level_3_hierarchy_name, "
			+ " tdm.level_4_hierarchy_id, category_name_fc(tdm.level_4_hierarchy_id) level_4_hierarchy_name "
			+ " FROM core_product_variants_view tdm, core_currency cc, r_order_details tod, r_order_header roh"
			+ " WHERE roh.r_order_header_id = ? "
			+ " AND roh.r_order_header_id = tod.order_header_id"
			+ " AND tod.product_id= tdm.core_products_id "
			+ " AND tod.product_variant_id = tdm.core_product_variants_id "
			+ " AND tdm.currency_id = cc.core_currency_id;";

	static final String ORDER_HEADER_DETAILS = "SELECT r_order_header_id, user_id, user_login, order_no, total_quantity, total_amount, "
			+ "total_payable_amount, converted_currency_symbol,currency_symbol_flag, lead_time,purchased_delivery_charges, customer_billing_fname, customer_billing_sname, "
			+ "customer_billing_address_line1,customer_billing_address_line2, customer_billing_address_line3, "
			+ "customer_billing_address_city,customer_billing_address_zip_code, customer_billing_address_state, "
			+ "core_state_name_fc(customer_billing_address_state) billing_address_state,customer_billing_address_country, "
			+ "core_country_name_fc(customer_billing_address_country) AS billing_address_country, customer_shipping_fname, "
			+ "customer_shipping_sname, customer_shipping_address_line1, customer_shipping_address_line2,customer_shipping_address_line3, "
			+ "customer_shipping_address_city, customer_shipping_address_zip_code,core_state_name_fc(customer_shipping_address_state) "
			+ "AS shipping_address_state, customer_shipping_address_country, core_country_name_fc(customer_shipping_address_country) "
			+ "AS shipping_address_country,customer_billing_primary_phone_number, customer_billing_alternate_phone_number, "
			+ "customer_billing_handy_phone_number,customer_shipping_primary_phone_number, payment_status, order_status, "
			+ "payment_tracking_number, order_tracking_number,order_date, delivery_date, courier_id, created_by, created_date, "
			+ "modified_by, modified_date, billing_email_address,shipping_email_address, voucher_code, voucher_discount_percent, "
			+ "voucher_discount_absolute, discount_value,gift_wrapping_required, order_comments,original_total_amount,original_total_payable_amount, "
			+ "courier_name_fc(courier_id) courier_name,green_book_club_amt,payment_type_on_order_no_n_id_fc(order_no,r_order_header_id) payment_type FROM r_order_header WHERE r_order_header_id = ?;";

	static final String GET_CUSTOMIZE_RECORD = "SELECT product_id,product_variant_id,property_value_text,product_property_short_name "
			+ "FROM r_order_details_customization WHERE order_header_id = ?;";

	static final String UPDATE_ORDER_SAVED_TO_WISHLIST = "UPDATE r_order_header SET order_status = "
			+ "(SELECT core_parameters_id FROM core_parameters WHERE param_code='ORDER_STATUS' "
			+ "AND sequence_number=3) WHERE r_order_header_id = ?;";

	static final String UPDATE_ORDER_STATUS_CONFIRMED = "UPDATE r_order_header SET order_status = (SELECT core_parameters_id "
			+ "FROM core_parameters WHERE param_code='ORDER_STATUS' AND sequence_number=5) WHERE order_no = ?;";

	static final String UPDATE_ORDER_STATUS_PAYMENT_INITIATED = "UPDATE r_order_header SET order_status = (SELECT core_parameters_id "
			+ "FROM core_parameters WHERE param_code='ORDER_STATUS' AND sequence_number=4) WHERE r_order_header_id = ?;";

	static final String GET_ORDER_ID = "SELECT r_order_header_id FROM r_order_header WHERE order_no = ?;";

	static final String UPDATE_ORDER_PAYMENT_TYPE = "UPDATE r_payment_transactions SET payment_type = ? "
			+ "WHERE sent_merchant_parameter = ?;";

	static final String UPDATE_ORDER_STATUS_PAYMENT_OFFLINE = "UPDATE r_order_header SET order_status = (SELECT core_parameters_id "
			+ "FROM core_parameters WHERE param_code='ORDER_STATUS' AND sequence_number=12) WHERE r_order_header_id = ?;";

	static final String INIT_CC_AVENUE_TRANSACTION = "INSERT INTO r_payment_transactions(r_payment_transactions_id,sent_merchant_id,sent_amount,sent_order_id,sent_redirect_url,"
			+ "sent_checksum,sent_billing_customer_name,sent_billing_customer_address,sent_billing_customer_country,sent_billing_customer_state,sent_billing_customer_tel,"
			+ "sent_billing_customer_email,sent_billing_customer_city,sent_billing_customer_zip_code,sent_delivery_customer_name,sent_delivery_customer_address,"
			+ "sent_delivery_customer_country,sent_delivery_customer_state,sent_delivery_customer_tel,sent_delivery_customer_city,sent_delivery_customer_zip_code,"
			+ "sent_delivery_customer_notes,sent_merchant_parameter,received_amount,received_auth_desc,received_bank_name,received_card_category,received_checksum,"
			+ "received_merchant_id,received_merchant_parameter,received_nb_bid,received_nb_order_number,received_notes,received_order_id,received_return_url,"
			+ "received_billing_customer_name,received_billing_customer_address,received_billing_customer_country,received_billing_customer_state,received_billing_customer_tel,"
			+ "received_billing_customer_email,received_billing_customer_city,received_billing_customer_zip_code,received_delivery_customer_name,received_delivery_customer_address,"
			+ "received_delivery_customer_country,received_delivery_customer_state,received_delivery_customer_tel,received_delivery_customer_city,received_delivery_customer_zip_code,"
			+ "error_message,created_by,created_date,modified_by,modified_date,payment_type)"
			+ "VALUES(NULL,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,NOW(),?,NOW(),?);";

	static final String GET_CC_AVENUE_TRANSACTION_PK = "SELECT r_payment_transactions_id FROM r_payment_transactions WHERE sent_checksum = ?";

	static final String UPDATE_CC_AVENUE_TRANSACTION = "UPDATE r_payment_transactions SET received_amount = ?, received_auth_desc = ?, received_bank_name = ?,received_card_category = ?, "
			+ "received_checksum = ?,received_merchant_id = ?, received_merchant_parameter = ?,received_nb_bid = ?,received_nb_order_number = ?,received_notes = ?,received_order_id = ?,"
			+ "received_return_url = ?,received_billing_customer_name = ?,received_billing_customer_address = ?,received_billing_customer_country = ?,received_billing_customer_state = ?,"
			+ "received_billing_customer_tel = ?,received_billing_customer_email = ?,received_billing_customer_city = ?,received_billing_customer_zip_code = ?,received_delivery_customer_name = ?,"
			+ "received_delivery_customer_address = ?,received_delivery_customer_country = ?,received_delivery_customer_state = ?,received_delivery_customer_tel = ?,received_delivery_customer_city = ?,"
			+ "received_delivery_customer_zip_code = ?,error_message = ?,modified_by = ?,modified_date = NOW() WHERE sent_merchant_parameter = ?";

	static final String INSERT_CC_AVENUE_PAYMENT_TRANSACTIONS = "INSERT INTO citywalkportal.r_payment_transactions (r_payment_transactions_id, sent_merchant_id, sent_amount, sent_order_id, sent_redirect_url, sent_checksum, sent_billing_customer_name, sent_billing_customer_address, sent_billing_customer_country, sent_billing_customer_state, sent_billing_customer_tel, sent_billing_customer_email, sent_billing_customer_city, sent_billing_customer_zip_code, sent_delivery_customer_name, sent_delivery_customer_address, sent_delivery_customer_country, sent_delivery_customer_state, sent_delivery_customer_tel, sent_delivery_customer_city, sent_delivery_customer_zip_code, sent_delivery_customer_notes, sent_merchant_parameter, received_amount, received_auth_desc, received_bank_name, received_card_category, received_checksum, received_merchant_id, received_merchant_parameter, received_nb_bid, received_nb_order_number, received_notes, received_order_id, received_return_url, received_billing_customer_name, received_billing_customer_address, received_billing_customer_country, received_billing_customer_state, received_billing_customer_tel, received_billing_customer_email, received_billing_customer_city, received_billing_customer_zip_code, received_delivery_customer_name, received_delivery_customer_address, received_delivery_customer_country, received_delivery_customer_state, received_delivery_customer_tel, received_delivery_customer_city, received_delivery_customer_zip_code, error_message, created_by, created_date, modified_by, modified_date, received_transactiion_id, sent_transaction_date, received_transaction_date, sent_server_mode, received_server_mode)VALUES(NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), ?, NOW(), ?, ?, ?, ?, ?);";

	static final String GET_PAYMENT_CONVERSION = "SELECT payment_multiplier FROM core_currency_conversion_mapping cccm, core_currency cc WHERE cc.core_currency_id = cccm.mapped_currency_id AND cc.currency_symbol = ?;";

	static final String INSERT_PAYPAL_PAYMENT_TRANSACTIONS = "INSERT INTO r_payment_transactions(sent_amount,sent_order_id,sent_redirect_url,sent_billing_customer_name, "
			+ "sent_billing_customer_address,sent_billing_customer_country,sent_billing_customer_state,sent_billing_customer_tel, "
			+ "sent_billing_customer_email,sent_billing_customer_city,sent_billing_customer_zip_code,sent_delivery_customer_name,sent_delivery_customer_address,sent_delivery_customer_country, "
			+ "sent_delivery_customer_state,sent_delivery_customer_tel,sent_delivery_customer_city,sent_delivery_customer_zip_code,error_message,created_by,created_date,modified_by,modified_date,paypal_ack,sent_token,sent_cancel_url,sent_currency,payment_type) "
			+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,NOW(),?,NOW(),?,?,?,?,'PayPal')";

	static final String UPDATE_PAYPAL_TRANSACTION = "UPDATE r_payment_transactions SET recieved_token = ?, recieved_payer_id = ?, recieved_payer_status = ?, received_order_id =?, modified_by = ?, modified_date = NOW() WHERE sent_order_id = ? ";

	static final String GET_PAYMENT_DETAILS_FOR_DO_EXPRESS_PAYMENT = "SELECT sent_order_id,received_order_id,sent_token,recieved_token,sent_currency,sent_amount, recieved_payer_id "
			+ "FROM r_payment_transactions WHERE received_order_id = ? ";

	static final String UPDATE_PAYPAL_TRANSACTION_ID = "UPDATE r_payment_transactions SET received_transactiion_id = ? WHERE received_order_id =?";

	static final String ORDER_DETAILS_FOR_PAY_PAL = "SELECT  tod.product_id,tod.price_per_piece, tdm.product_image_url, tdm.product_thumbnail_url,tod.quantity,"
			+ "tod.total_price,tdm.product_short_name product_short_name, tdm.product_description,tdm.product_code, tdm.currency_id,"
			+ "cc.currency_symbol,tdm.discount_price,tdm.core_product_variants_id,tod.customization_font, tod.customization_text,tod.size_code, roh.converted_currency_symbol, roh.currency_conversion_rate  "
			+ " FROM core_product_variants_view tdm, core_currency cc, r_order_details tod, r_order_header roh"
			+ " WHERE roh.order_no = ? "
			+ " AND roh.r_order_header_id = tod.order_header_id"
			+ " AND tod.product_id= tdm.core_products_id "
			+ " AND tod.product_variant_id = tdm.core_product_variants_id "
			+ " AND tdm.currency_id = cc.core_currency_id;";

	static final String ORDER_HEADER_DETAILS_FOR_PAYPAL = "SELECT r_order_header_id, user_id, user_login, order_no, total_quantity, total_amount, "
			+ "total_payable_amount, converted_currency_symbol,currency_symbol_flag, lead_time,purchased_delivery_charges, customer_billing_fname, customer_billing_sname, "
			+ "customer_billing_address_line1,customer_billing_address_line2, customer_billing_address_line3, "
			+ "customer_billing_address_city,customer_billing_address_zip_code, customer_billing_address_state, "
			+ "core_state_name_fc(customer_billing_address_state) billing_address_state,customer_billing_address_country, "
			+ "core_country_name_fc(customer_billing_address_country) AS billing_address_country, customer_shipping_fname, "
			+ "customer_shipping_sname, customer_shipping_address_line1, customer_shipping_address_line2,customer_shipping_address_line3, "
			+ "customer_shipping_address_city, customer_shipping_address_zip_code,core_state_name_fc(customer_shipping_address_state) "
			+ "AS shipping_address_state, customer_shipping_address_country, core_country_name_fc(customer_shipping_address_country) "
			+ "AS shipping_address_country,customer_billing_primary_phone_number, customer_billing_alternate_phone_number, "
			+ "customer_billing_handy_phone_number,customer_shipping_primary_phone_number, payment_status, order_status, "
			+ "payment_tracking_number, order_tracking_number,order_date, delivery_date, courier_id, roh.created_by, roh.created_date, "
			+ "roh.modified_by, roh.modified_date, billing_email_address,shipping_email_address, voucher_code, voucher_discount_percent, "
			+ "voucher_discount_absolute, discount_value,gift_wrapping_required, order_comments, rpt.payment_type FROM r_order_header roh, r_payment_transactions rpt WHERE order_no = ? AND order_no=rpt.sent_order_id;";

	static final String UPDATE_PAYPAL_ERROR = "UPDATE r_payment_transactions SET error_message = ? WHERE received_order_id =? ";

	static final String UPDATE_ORDER_STATUS_CONFIRMED_PAY_PAL = "UPDATE r_order_header SET order_status = (SELECT core_parameters_id "
			+ "FROM core_parameters WHERE param_code='ORDER_STATUS' AND sequence_number=5) WHERE order_no = ?;";

	static final String GET_SHIPPING_CHARGES_MODE = "SELECT charges_mode FROM core_charges_apply WHERE is_active = 1;";

	static final String UPDATE_PAY_U_TRANSACTION = "UPDATE r_payment_transactions SET received_amount = ?, received_bank_name = ?, received_card_category = ?, "
			+ "received_checksum = ?, received_merchant_id = ?, received_nb_bid = ?,received_nb_order_number = ?, received_notes = ?, received_order_id = ?, "
			+ "received_billing_customer_name = ?, received_billing_customer_address = ?, received_billing_customer_country = ?, received_billing_customer_state = ?, "
			+ "received_billing_customer_tel = ?, received_billing_customer_email = ?, received_billing_customer_city = ?, received_billing_customer_zip_code = ?, "
			+ "error_message = ?, received_transactiion_id = ?, sent_transaction_date = ?, received_server_mode = ?, received_transaction_reference_number = ?,  "
			+ "modified_by = ?,modified_date = NOW() WHERE sent_order_id = ?; ";

	static final String INIT_PAY_U_TRANSACTION = "INSERT INTO r_payment_transactions(r_payment_transactions_id,sent_merchant_id,sent_amount,sent_order_id,sent_redirect_url,"
			+ "sent_checksum,sent_billing_customer_name,sent_billing_customer_address,sent_billing_customer_country,sent_billing_customer_state,sent_billing_customer_tel,"
			+ "sent_billing_customer_email,sent_billing_customer_city,sent_billing_customer_zip_code,sent_delivery_customer_name,sent_delivery_customer_address,"
			+ "sent_delivery_customer_country,sent_delivery_customer_state,sent_delivery_customer_tel,sent_delivery_customer_city,sent_delivery_customer_zip_code,"
			+ "sent_delivery_customer_notes,sent_merchant_parameter,received_amount,received_auth_desc,received_bank_name,received_card_category,received_checksum,"
			+ "received_merchant_id,received_merchant_parameter,received_nb_bid,received_nb_order_number,received_notes,received_order_id,received_return_url,"
			+ "received_billing_customer_name,received_billing_customer_address,received_billing_customer_country,received_billing_customer_state,received_billing_customer_tel,"
			+ "received_billing_customer_email,received_billing_customer_city,received_billing_customer_zip_code,received_delivery_customer_name,received_delivery_customer_address,"
			+ "received_delivery_customer_country,received_delivery_customer_state,received_delivery_customer_tel,received_delivery_customer_city,received_delivery_customer_zip_code,"
			+ "error_message,created_by,created_date,modified_by,modified_date,payment_type,payment_option)"
			+ "VALUES(NULL,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,NOW(),?,NOW(),?,?);";

	static final String GET_MAPPED_PRODUCT_TO_PROMO_CODE = "CALL sp_get_promo_code_mapped_products(?,?,?);";

	static final String SAVE_SMS_GATEWAYDATA = "INSERT INTO core_sms_gateway_report (core_sms_gateway_report_id, sms_type, sms_response_code, destination_number, order_number, created_by, created_date, modified_by, modified_date) VALUES (NULL, ?, ?, ?, ?, ?, NOW(), ?, NOW());";

	public static final String ORDER_HIERARCHY_DATA = " SELECT tdm.level_1_hierarchy_id, category_name_fc(tdm.level_1_hierarchy_id) level_1_hierarchy_name, "
			+ " tdm.level_2_hierarchy_id, category_name_fc(tdm.level_2_hierarchy_id) level_2_hierarchy_name, "
			+ " tdm.level_3_hierarchy_id, category_name_fc(tdm.level_3_hierarchy_id) level_3_hierarchy_name, "
			+ " tdm.level_4_hierarchy_id, category_name_fc(tdm.level_4_hierarchy_id) level_4_hierarchy_name "
			+ " FROM core_product_variants_view tdm WHERE tdm.core_products_id = ? AND tdm.core_product_variants_id = ? ; ";

	static final String POPULATE_SHIPPING_CHARGES = "Call sp_core_get_shipping_charges_yes(?,?,?,?,?,?);";
}
