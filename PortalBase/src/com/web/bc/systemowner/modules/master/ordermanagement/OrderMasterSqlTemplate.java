package com.web.bc.systemowner.modules.ordermanagement;

import com.web.foundation.dao.SqlTemplate;

public interface OrderMasterSqlTemplate extends SqlTemplate {

	public static String SEARCH_ORDERS = " SELECT order_header_id, user_id, user_login, order_no, total_quantity, total_amount, "
			+ " payment_status, order_status FROM order_header ";
	// fn_get_parameter_value_text(payment_status) payment_status_value,
	// fn_get_parameter_value_text(order_status) order_status_value

	public static String GET_ORDER_HEADER = " SELECT order_header_id, user_id, user_login, order_no, total_quantity, lead_time, total_amount, "
			+ " original_total_amount, express_delivery_charge, original_express_delivery_charge, duties, original_duties, "
			+ " currency_conversion_rate, currency_code, currency_symbol_flag, payment_status, order_status, "
			+ " billing_status, billing_first_name, billing_middle_name, billing_last_name, billing_email_address_1, "
			+ " billing_email_address_2, billing_mobile_1, billing_mobile_2, billing_address_line_1, billing_address_line_2, "
			+ " billing_address_line_3, billing_city, billing_zip_code, billing_state, billing_country, "
			+ " order_tracking_number, order_date, delivery_date, courier_id, voucher_code, voucher_discount_percent, "
			+ " voucher_discount_absolute, discount_value, gift_wrapping_required, order_comments, email_regenerated_by, "
			+ " promotion_source, referer_source, payment_status_modifier, website_id, created_by, created_date, "
			+ " modified_by, modified_date FROM order_header WHERE order_header_id = ? ";

	public static String GET_ORDER_DETAIL = " SELECT order_detail_id, order_header_id, product_id, "
			+ " fn_product_get_product_name(product_id) product_name, fn_product_get_product_code(product_id) product_code, "
			+ " product_sku_id, fn_get_sku_code(product_sku_id) sku_code, fn_get_sku_name(product_sku_id) sku_name, "
			+ " price_per_piece, original_price_per_piece, total_price, original_total_price, quantity, delivery_date, "
			+ " jewellery_valuation, customization_text,customization_font, size_id, product_shipping_charges, "
			+ " product_shipping_charges_original, product_shipping_duties, product_shipping_duties_original, "
			+ " product_shipping_express_charges, product_shipping_express_charges_original, product_shipping_processing_charges, "
			+ " product_shipping_processing_charges_original, product_shipping_mapped, store_location_id, shipping_status "
			+ " FROM order_detail WHERE order_header_id = ? ";
}
