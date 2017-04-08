package com.web.bc.systemowner.modules.master.guestusermaster;

import com.web.foundation.dao.SqlTemplate;

public interface GuestuserMasterSqlTemplate extends SqlTemplate {

	String SEARCH_GUEST_USER_DETAILS = "SELECT guest_id, name, email_id, phone_number, is_active, modified_by, modified_date FROM core_guest_master ";

	String SAVE_GUEST_USER_DETAILS = "CALL sp_core_save_guest_user_master_details(?,?,?,?,?,?,?, @p_error_code, @p_error_message)";

}
