package com.web.bc.systemowner.modules.master.sizemaster;

import com.web.foundation.dao.SqlTemplate;

public interface SizeMasterSqlTemplate extends SqlTemplate {

	static final String SEARCH_SIZE_DETAILS = "SELECT size_id, size_code, size_name, size_description, is_active, modified_by, modified_date FROM core_size_master ";

	static final String SAVE_SIZE_DETAILS = "CALL sp_core_save_size_details(?,?,?,?,?,?,?, @p_error_code, @p_error_message);";

	static final String GET_SIZE_DETAILS = "SELECT size_id, size_code, size_name, size_description, is_active, modified_by, modified_date FROM core_size_master"
			+ " WHERE size_id = ?;";

}
