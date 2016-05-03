package com.web.bc.retail.modules.loginpanel;

import com.web.foundation.dao.SqlTemplate;

public interface LoginPanelSqlTemplate extends SqlTemplate {

	public static final String LOGIN_USER_BASE_QUERY = "SELECT cu.user_id, cu.user_login, cu.login_password, cu.first_name, cu.middle_name, cu.last_name, cu.primary_email_id, "
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

	static final String FORGOT_PASSWORD_QUERY = "SELECT login_password,first_name,primary_email_id FROM core_users cu WHERE user_login = ? ;";

	static final String UPDATE_PASSWORD_QUERY = "UPDATE core_users SET login_password =MD5(primary_email_id) WHERE user_login = ?; ";

}
