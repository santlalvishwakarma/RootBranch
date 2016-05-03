package com.web.bc.retail.modules.registrationpanel;

import com.web.foundation.dao.SqlTemplate;

public interface RegistrationPanelSqlTemplate extends SqlTemplate {

	static final String REGISTER_USER = "CALL sp_retail_register_user(?, ?, ?, ?, ?, ?, ?, @p_error_code, @p_error_message);";

	static final String CHECK_USER_AVAILABILITY = "SELECT user_login FROM core_user_master cu WHERE cu.user_login = ?;";

	static final String REGISTER_USER_DETAILS = "CALL sp_retail_register_user_details(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?, @p_error_code, @p_error_message)";

	static final String SAVE_SMS_GATEWAYDATA = "INSERT INTO core_sms_gateway_report (core_sms_gateway_report_id, sms_type, sms_response_code, destination_number, created_by, created_date, modified_by, modified_date) VALUES (NULL, ?, ?, ?, ?, NOW(), ?, NOW());";
}
