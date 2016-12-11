package com.web.bc.systemowner.modules.publishtohomecategory;

import com.web.foundation.dao.SqlTemplate;

public interface PublishToHomeCategorySqlTemplate extends SqlTemplate {

	String GET_ALL_PUBLISH_TO_HOME_CATEGORIES = "SELECT publish_to_home_category_id, hierarchy_id, fn_get_hierarchy_code_based_on_id(hierarchy_id) AS hierarchy_code, fn_get_hierarchy_name_based_on_id(hierarchy_id) AS hierarchy_name,"
			+ " category_level_1, fn_get_category_code_based_on_id(category_level_1) AS category_level_1_code, fn_get_category_name_based_on_id(category_level_1) AS category_level_1_name, "
			+ " category_level_2, fn_get_category_code_based_on_id(category_level_2) AS category_level_2_code, fn_get_category_name_based_on_id(category_level_2) AS category_level_2_name,"
			+ " category_level_3, fn_get_category_code_based_on_id(category_level_3) AS category_level_3_code, fn_get_category_name_based_on_id(category_level_3) AS category_level_3_name, "
			+ " category_level_4, fn_get_category_code_based_on_id(category_level_4) AS category_level_4_code, fn_get_category_name_based_on_id(category_level_4) AS category_level_4_name, "
			+ " category_level_5, fn_get_category_code_based_on_id(category_level_5) AS category_level_5_code, fn_get_category_name_based_on_id(category_level_5) AS category_level_5_name,"
			+ " is_active, publish_position, modified_by, modified_date FROM publish_to_home_category WHERE is_active = 1 ORDER BY publish_position ;";

	String SAVE_PUBLISH_CATEGORY_LIST = "CALL sp_product_save_publish_category(?,?, @p_error_code, @p_error_message);";

}
