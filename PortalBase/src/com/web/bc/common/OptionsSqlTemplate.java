package com.web.bc.common;

import com.web.foundation.dao.SqlTemplate;

public interface OptionsSqlTemplate extends SqlTemplate {

	public static final String VALIDATE_LOGIN_DETAILS = "SELECT user_id, user_login, first_name, middle_name, last_name, primary_email_id,"
			+ " alternate_email_id, is_admin FROM core_user_master WHERE BINARY(user_login) = BINARY(?) AND login_password = MD5(?);";

	public static final String GET_USER_BASED_ROLE = " SELECT crm.role_id, crm.role_code, crm.role_name, crm.role_description, "
			+ " curm.users_roles_mapping_id, cum.user_id, cum.user_login FROM core_user_master cum, core_users_roles_mapping curm, "
			+ " core_role_master crm WHERE cum.user_id = curm.user_id AND curm.role_id = crm.role_id AND cum.user_id = ?; ";

	public static final String GET_PARAM_CODE_OPTIONS = " SELECT parameter_id, param_code, sequence_number, param_description, value_data_type, "
			+ " value_text, value_numeric, value_date, editable, record_deleted, effective_date_from, effective_date_to, "
			+ " created_by, created_date, modified_by, modified_date FROM parameter_master "
			+ " WHERE param_code = ? AND CURRENT_DATE() BETWEEN effective_date_from AND effective_date_to ORDER BY sequence_number; ";

	static final String GET_COUNTRY_LIST = "SELECT country_code, country_name, country_description"
			+ " FROM core_country_master ccm ";

	static final String GET_STATE_LIST = "SELECT state_code, state_name, state_description, country_code"
			+ " FROM core_state_master csm ";

	static final String GET_CITY_LIST = "SELECT city_code,city_name, city_description, state_code "
			+ " FROM core_city_master ccm ";
}
