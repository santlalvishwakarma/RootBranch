package com.web.bc.systemowner.modules.master.colormaster;

import com.web.foundation.dao.SqlTemplate;

public interface ColorMasterSqlTemplate extends SqlTemplate {

	String SEARCH_COLOR_DETAILS = "SELECT color_id, color_code, color_name, color_description, is_active, modified_by, modified_date FROM core_color_master ";

	String SAVE_COLOR_DETAILS = "CALL sp_core_save_color_details(?,?,?,?,?,?,?, @p_error_code, @p_error_message);";

	String GET_COLOR_DETAILS = "SELECT color_id, color_code, color_name, color_description, is_active, modified_by, modified_date FROM core_color_master"
			+ " WHERE color_id = ?;";

}
