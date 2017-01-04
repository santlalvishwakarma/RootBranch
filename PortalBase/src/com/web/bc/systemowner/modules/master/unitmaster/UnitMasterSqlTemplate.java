package com.web.bc.systemowner.modules.master.unitmaster;

import com.web.foundation.dao.SqlTemplate;

public interface UnitMasterSqlTemplate extends SqlTemplate {

	String SEARCH_UNIT_DETAILS = "SELECT unit_id, unit_code, unit_name, unit_description, display_name, is_active, modified_by, modified_date FROM core_unit_master ";

	String SAVE_UNIT_DETAILS = "CALL sp_core_save_unit_details(?,?,?,?,?,?,?,?, @p_error_code, @p_error_message);";

	String GET_UNIT_DETAILS = "SELECT unit_id, unit_code, unit_name, unit_description, display_name, is_active, modified_by, modified_date FROM core_unit_master"
			+ " WHERE unit_id = ?;";

}
