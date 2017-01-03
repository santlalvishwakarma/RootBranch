package com.web.bc.systemowner.modules.master.materialmaster;

import com.web.foundation.dao.SqlTemplate;

public interface MaterialMasterSqlTemplate extends SqlTemplate {

	String SEARCH_MATERIAL_DETAILS = "SELECT material_id, material_code, material_name, material_description, is_active, modified_by, modified_date FROM core_material_master ";

	String SAVE_MATERIAL_DETAILS = "CALL sp_core_save_material_details(?,?,?,?,?,?,?, @p_error_code, @p_error_message);";

	String GET_MATERIAL_DETAILS = "SELECT material_id, material_code, material_name, material_description, is_active, modified_by, modified_date FROM core_material_master"
			+ " WHERE material_id = ?;";

}
