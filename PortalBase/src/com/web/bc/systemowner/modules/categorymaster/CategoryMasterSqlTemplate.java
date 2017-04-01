package com.web.bc.systemowner.modules.categorymaster;

import com.web.foundation.dao.SqlTemplate;

/**
 * @author NIRAJ
 * 
 */
public interface CategoryMasterSqlTemplate extends SqlTemplate {
	static final String SEARCH_PRODUCTS_CATEGORIES = "CALL sp_product_search_categories(?,?,?,?,?, @p_error_code, @p_error_message);";

	static final String SAVE_PRODUCTS_CATEGORIES = "CALL sp_product_save_categories(?,?,?,?,?,?,?,?,?,?,?,?,?, @p_last_inserted_id, @p_error_code, @p_error_message);";

	static final String GET_PRODUCTS_CATEGORIES_LEVELS = "SELECT product_category_level_mapping_id, level, is_active "
			+ " FROM product_category_level_mapping WHERE product_category_id = ? ORDER BY level;";

	static final String GET_PRODUCTS_CATEGORIES_PROPERTIES = "CALL sp_product_get_categories_properties(?);";

	static final String SAVE_PRODUCTS_CATEGORIES_PROPERTIES = "CALL sp_product_save_category_properties(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?, @p_last_inserted_id, @p_error_code, @p_error_message);";

	static final String GET_ALL_CATEGORIES_WITH_LEVELS = "SELECT pcm.product_category_id, pcm.product_category_code, pcm.product_category_name,"
			+ " pcm.product_category_description, pclm.level FROM product_category_master pcm, product_category_level_mapping pclm"
			+ " WHERE pcm.product_category_id = pclm.product_category_id AND pcm.is_active = 1 AND pclm.is_active = 1"
			+ " ORDER BY pcm.product_category_code;";

	static final String GET_ALL_CATEGORIES = "SELECT pcm.category_id, pcm.category_code, pcm.category_name,"
			+ " pcm.category_description FROM category_master pcm"
			+ " WHERE pcm.is_active = 1  ORDER BY pcm.category_name;";

	static final String GET_ALL_LEVEL_NAMES = "SELECT pclm.product_category_level_id, pclm.level, pclm.level_name, pclm.level_description,"
			+ " pclm.is_active, pclm.main_level FROM product_category_level_master pclm WHERE pclm.is_active = 1 ORDER BY pclm.level;";

	static final String GET_MAX_SEQUENCE_NUMBER = "SELECT MAX(sequence_number) AS seq_no FROM product_category_properties_mapping;";

	static final String GET_CATEGORIES_BASED_ON_MAIN_LEVEL = "SELECT DISTINCT pcm.product_category_id, pcm.product_category_code, pcm.product_category_name, pcm.product_category_description, "
			+ " pcm.quantity_uom_code, fn_core_get_uom_name(pcm.quantity_uom_code) AS quantity_uom_name, "
			+ " pcm.weight_uom_code, fn_core_get_uom_name(pcm.weight_uom_code) AS weight_uom_name, fn_core_get_parameter_sequence(allocation_based_on) AS allocation_based_on_seq_no"
			+ " FROM product_category_master pcm, product_category_level_mapping pclm, product_category_level_master pclm2"
			+ " WHERE pcm.product_category_id = pclm.product_category_id AND pclm.level = pclm2.level"
			+ " AND pclm2.main_level = 1 AND pclm2.is_active = 1 AND pclm.is_active = 1 AND pcm.is_active = 1 ";

	static final String SAVE_CATEGORY_TOLERANCE = "CALL sp_product_save_category_tolerance(?,?,?,?, @p_error_code, @p_error_message);";

	static final String OPEN_CATEGORY_TOLERANCE_WINDOW = "SELECT product_category_tolerance_mapping_id, upper_weight_tolerance, upper_weight_tolerance_pct, "
			+ " lower_weight_tolerance,lower_weight_tolerance_pct, is_active FROM product_category_tolerance_mapping WHERE product_category_id = ? ";

	static final String GET_CATEGORIES_BASED_ON_LEVEL_2 = "SELECT d.product_category_id, d.product_category_code, d.product_category_name FROM product_sku_header a, product_header b,product_hierarchy_category_mapping c , product_category_master d"
			+ " WHERE  a.product_id = b.product_id"
			+ " AND c.product_hierarchy_id = b.product_hierarchy_id"
			+ " AND c.level IN (2)" + " AND d.product_category_id = c.product_category_id ";

	public static final String SEARCH_CATEGORY = " SELECT cm.category_id, cm.category_code, cm.category_name, cm.category_description, "
			+ " cm.seo_title, cm.seo_keyword, cm.seo_description, cm.image_url, cm.is_active, cm.modified_by, cm.modified_date FROM category_master cm ";

	public static final String SAVE_EDIT_CATEGORY = " CALL sp_save_edit_category(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, @p_error_code, @p_error_message); ";

	public static final String GET_MAPPED_CATEGORY = " SELECT clm.category_level_mapping_id, clm.category_id, clm.level_no, clm.is_active, "
			+ " clm.created_date, clm.created_by, clm.modified_by, clm.modified_date FROM category_level_mapping clm WHERE clm.category_id = ? ";

	public static final String GET_PUBLISH_TO_HOME_CATEGORY = "SELECT category_id, category_code, category_name, category_description, publish_to_home_page, publish_position, image_url "
			+ " FROM category_master WHERE publish_to_home_page = 1 AND is_active = 1 ORDER BY publish_position ";

	static final String GET_SUGGESTED_CATEGORIES = "SELECT category_id, category_code, category_name, category_description FROM category_master ";

	static final String SAVE_PUBLISH_CATEGORY_LIST = "CALL sp_product_save_publish_category(?,?,?,?, @p_error_code, @p_error_message);";

	public static final String MAP_CATEGORY_TO_LEVELS = " CALL sp_save_edit_category_levels(?, ?, ?, ?, ?, ?, @p_error_code, @p_error_message); ";

	public static final String GET_MAPPED_LEVELS_TO_CATEGORY = " SELECT level_no FROM category_level_mapping WHERE category_id = ? ";

	public static final String GET_MAPPED_HIERARCHY_TO_CATEGORY_LEVEL = " CALL sp_hierarchy_category_level_mapped(?, ?, ?, @p_error_code, @p_error_message); ";

	public static final String MAP_HIERARCHY_TO_CATEGORY_LEVEL = " CALL sp_save_edit_hierarchy_to_category_level(?, ?, ?, ?, ?, ?, ?, ?, ?, @p_error_code, @p_error_message); ";

	static final String SAVE_CATEGORY_SIZE_MAPPING = "CALL sp_save_category_size_mapping(?,?,?,?,?,?,?,?, @p_error_code, @p_error_message)";

	static final String GET_SIZE_MAPPING_DETAILS = "SELECT category_size_mapping_id, category_id, size_value_1, size_value_2, unit_id, fn_core_get_unit_code_based_on_id(unit_id) 'unit_code', "
			+ " fn_core_get_unit_name_based_on_id(unit_id) 'unit_name',  is_active, modified_by, modified_date FROM category_size_mapping WHERE category_size_mapping_id = ? ;";

}
