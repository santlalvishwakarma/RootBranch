package com.web.bc.systemowner.modules.productskuproperty;

import com.web.foundation.dao.SqlTemplate;

/**
 * @author NIRAJ
 * 
 */
public interface ProductSkuPropertySqlTemplate extends SqlTemplate {

	static final String SEARCH_PRODUCTS_PROPERTIES = "CALL sp_product_search_properties(?,?,?,?, @p_error_code, @p_error_message);";

	static final String GET_PRODUCTS_PROPERTIES_VALUES = "SELECT product_properties_values_id, product_properties_id, product_property_value, "
			+ "include_report, image_url, default_value, created_by, created_date, modified_by, modified_date "
			+ "FROM product_properties_values WHERE product_properties_id = ? ORDER BY product_property_value;";

	static final String SAVE_PRODUCTS_PROPERTIES = "CALL sp_product_save_properties(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?, @p_last_inserted_id, @p_error_code, @p_error_message);";

	static final String GET_SUGGESTED_PRODUCTS_PROPERTIES = "SELECT pp.property_id, pp.property_code, pp.property_name, "
			+ " pp.property_description "
			+ " FROM property_master pp WHERE pp.is_active = 1 AND pp.property_name LIKE ? ORDER BY pp.property_name;";

	static final String GET_MAX_SEQUENCE_NUMBER = "SELECT MAX(sequence_number) AS seq_no FROM product_properties;";

	static final String GET_SUGGESTED_PRODUCTS_PROPERTIES_WITH_VALUES_LIST = "SELECT pp.product_properties_id, pp.product_property_name, pp.product_property_description, pp.is_active, "
			+ " fn_core_get_parameter_sequence(pp.value_type) AS value_type_seq_no, CASE WHEN fn_core_get_parameter_sequence(pp.value_type) = 3"
			+ " THEN fn_product_get_mapped_item_category_product_properties_values(pp.category_id)"
			+ " ELSE (SELECT GROUP_CONCAT(DISTINCT(ppv.product_property_value) ORDER BY ppv.default_value, ppv.include_report DESC , product_property_value)"
			+ " FROM product_properties_values ppv WHERE ppv.product_properties_id = pp.product_properties_id) END AS product_property_values"
			+ " FROM product_properties pp WHERE pp.is_active = 1 AND pp.product_property_name LIKE ? ORDER BY pp.product_property_name;";
}
