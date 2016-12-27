package com.web.bc.systemowner.modules.master.sizemaster;

import com.web.foundation.dao.SqlTemplate;

public interface SizeMasterSqlTemplate extends SqlTemplate {

	String SEARCH_SIZE_DETAILS = "SELECT size_id, size_code, size_name, size_description, is_active, modified_by, modified_date FROM core_size_master ";

}
