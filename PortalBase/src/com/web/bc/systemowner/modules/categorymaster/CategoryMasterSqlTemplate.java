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
}