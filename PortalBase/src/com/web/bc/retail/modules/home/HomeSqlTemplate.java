package com.web.bc.retail.modules.home;

import com.web.foundation.dao.SqlTemplate;

public class HomeSqlTemplate implements SqlTemplate {

	static final String SEARCH_ROLLING_IMAGES = "SELECT core_rolling_images_id, param_sequence_number, image_url, link_page_url FROM core_rolling_images;";

	public static final String GET_CURRENCY_DETAILS = "SELECT cc.currency_symbol, cccm.conversion_multiplier "
			+ " FROM core_currency cc, core_country cco, core_currency_conversion_mapping cccm "
			+ " WHERE cco.core_country_id=(select core_country_id from core_country where country_name=?)"
			+ " AND cc.country_id = cco.core_country_id AND cccm.mapped_currency_id = cc.core_currency_id;";

	public static final String GET_CATEGORY_FOR_HOME_PAGE_1 = "SELECT DISTINCT cm.category_id, cm.category_code, cm.category_name, cm.category_description, cm.publish_to_home_page, cm.publish_position, "
			+ " cm.image_url, cm.is_active, fn_get_hierarchy_code_based_on_id(hcm.hierarchy_id) AS hierarchy_code, hcm.hierarchy_id, hcm.category_level_1, "
			+ " fn_get_category_code_based_on_id(hcm.category_level_1) AS level_1_category_code, fn_get_category_code_based_on_id(hcm.category_level_2) AS level_2_category_code,"
			+ " fn_get_category_code_based_on_id(hcm.category_level_3) AS level_3_category_code, fn_get_category_code_based_on_id(hcm.category_level_4) AS level_4_category_code  "
			+ " FROM category_master cm, hierarchy_category_mapping hcm "
			+ " WHERE publish_to_home_page = 1 AND cm.is_active = 1 AND (cm.category_id = hcm.category_level_1 OR cm.category_id = hcm.category_level_2 OR cm.category_id = hcm.category_level_3 "
			+ " OR cm.category_id = hcm.category_level_5 OR cm.category_id = hcm.category_level_5)"
			+ " ORDER BY publish_position ";

	public static final String GET_CATEGORY_FOR_HOME_PAGE = "SELECT DISTINCT publish_to_home_category_id, fn_get_hierarchy_code_based_on_id(hierarchy_id) AS hierarchy_code, hierarchy_id, category_level_1, "
			+ " fn_get_category_code_based_on_id(category_level_1) AS level_1_category_code, fn_get_category_name_based_on_id(category_level_1) AS level_1_category_name, "
			+ " category_level_2, fn_get_category_code_based_on_id(category_level_2) AS level_2_category_code, fn_get_category_name_based_on_id(category_level_2) AS level_2_category_name, "
			+ " category_level_3, fn_get_category_code_based_on_id(category_level_3) AS level_3_category_code, fn_get_category_name_based_on_id(category_level_3) AS level_3_category_name, "
			+ " category_level_4, fn_get_category_code_based_on_id(category_level_4) AS level_4_category_code, fn_get_category_name_based_on_id(category_level_4) AS level_4_category_name, "
			+ " publish_position, fn_get_home_page_category_image_url(category_level_1, category_level_2, category_level_3, category_level_4) AS image_url "
			+ " FROM publish_to_home_category WHERE is_active = 1 ORDER BY publish_position ";
}
