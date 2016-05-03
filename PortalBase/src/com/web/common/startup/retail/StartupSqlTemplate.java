package com.web.common.startup.retail;

import com.web.foundation.dao.SqlTemplate;

public class StartupSqlTemplate implements SqlTemplate {

	private static final long serialVersionUID = 8265495332573365038L;

	static final String GET_HIERARCHICAL_MENU_MAPPING = " SELECT hierarchy_category_mapping_id, hierarchy_id, "
			+ " fn_get_hierarchy_code_based_on_id(hierarchy_id) 'hierarchy_code', "
			+ " fn_get_hierarchy_name_based_on_id(hierarchy_id) 'hierarchy_name', "
			+ " category_level_1, fn_get_category_code_based_on_id(category_level_1) 'category_level_one_code', "
			+ " fn_get_category_name_based_on_id(category_level_1) 'category_level_one_name', "
			+ " category_level_2, fn_get_category_code_based_on_id(category_level_2) 'category_level_two_code', "
			+ " fn_get_category_name_based_on_id(category_level_2) 'category_level_two_name', "
			+ " category_level_3, fn_get_category_code_based_on_id(category_level_3) 'category_level_three_code', "
			+ " fn_get_category_name_based_on_id(category_level_3) 'category_level_three_name', "
			+ " category_level_4, fn_get_category_code_based_on_id(category_level_4) 'category_level_four_code', "
			+ " fn_get_category_name_based_on_id(category_level_4) 'category_level_four_name' "
			+ " FROM hierarchy_category_mapping ; ";

}
