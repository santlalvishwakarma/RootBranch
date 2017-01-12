package com.web.bc.systemowner.modules.master.propertyvaluemaster;

import com.web.foundation.dao.SqlTemplate;

public interface PropertyValueMasterSqlTemplate extends SqlTemplate {

	String SEARCH_UNIT_DETAILS = "SELECT unit_id, unit_code, unit_name, unit_description, display_name, is_active, modified_by, modified_date FROM core_unit_master ";

	String SAVE_UNIT_DETAILS = "CALL sp_core_save_unit_details(?,?,?,?,?,?,?,?, @p_error_code, @p_error_message);";

	String GET_UNIT_DETAILS = "SELECT unit_id, unit_code, unit_name, unit_description, display_name, is_active, modified_by, modified_date FROM core_unit_master"
			+ " WHERE unit_id = ?;";

	String SEARCH_PROPERTY_DETAILS = "SELECT property_value_mapping_id, size_id, property_value, unit_id, is_active, fn_core_get_unit_name_based_on_id(unit_id) AS unit_name,"
			+ " modified_by, modified_date FROM property_value_mapping WHERE size_id = ? ";

}
