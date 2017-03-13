package com.web.bc.systemowner.modules.master.propertyvaluemaster;

import com.web.foundation.dao.SqlTemplate;

public interface PropertyValueMasterSqlTemplate extends SqlTemplate {

	String SEARCH_PROPERTY_DETAILS = "SELECT property_value_mapping_id, size_id, fn_core_get_size_code_based_on_id(size_id) AS size_code, fn_core_get_size_name_based_on_id(size_id) AS size_name,"
			+ " property_value, unit_id, is_active, fn_core_get_unit_name_based_on_id(unit_id) AS unit_name,"
			+ " modified_by, modified_date FROM core_property_value_mapping WHERE size_id = ? ";

	String SAVE_PROPERTY_VALUE_MAPPING_DETAILS = "CALL sp_core_save_property_value_mapping_details(?,?,?,?,?,?,?,?,?,?,?, @p_error_code, @p_error_message);";

}
