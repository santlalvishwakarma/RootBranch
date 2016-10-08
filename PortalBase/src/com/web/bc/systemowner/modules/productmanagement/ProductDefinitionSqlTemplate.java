package com.web.bc.systemowner.modules.productmanagement;

import com.web.foundation.dao.SqlTemplate;

/**
 * @author NIRAJ
 * 
 */
public interface ProductDefinitionSqlTemplate extends SqlTemplate {

	static final String SEARCH_PRODUCTS_DETAILS = "CALL sp_product_search_products(?,?,?,?,?,?,?,?,?, @p_error_code, @p_error_message);";

	static final String GET_PRODUCTS_DETAILS = "CALL sp_product_get_product_sku_details(?,?);";

	static final String GET_SUGGESTED_PRODUCTS_LIST = "SELECT ph.product_id, ph.product_code,ph.product_name, ph.product_description, ph.product_version,  ph.uom_code"
			+ " FROM product_header ph ";

	static final String GET_SUGGESTED_SKU_LIST = "SELECT psh.product_sku_id, psh.product_id, psh.sku_code, psh.sku_name, psh.sku_description, psh.sku_version, psh.status_code, psh.is_active"
			+ " FROM product_sku_header psh ";

	static final String GET_HIERARCHIES_MAPPING_LIST = "CALL sp_product_get_hierarchies_mapping(?);";

	static final String SAVE_HIERARCHY_MAPPING_LIST = "CALL sp_product_save_product_hierarchies_mapping(?,?,?,?,?, @p_error_code, @p_error_message);";

	static final String GET_PROPERTIES_MAPPING_LIST = "CALL sp_product_get_properties_mapping(?,?,?);";

	static final String SAVE_PROPERTIES_MAPPING_LIST = "CALL sp_product_save_product_sku_properties_mapping(?,?,?,?,?,?,?,?,?,?, @p_error_code, @p_error_message);";

	static final String GET_RAW_MATERIAL_MAPPING_LIST = "CALL sp_product_get_product_sku_rm_details(?,?);";

	static final String SAVE_RAW_MATERIAL_MAPPING_LIST = "CALL sp_product_save_product_sku_rm_details(?,?,?,?,?,?, @p_error_code, @p_error_message);";

	static final String GET_PROCESS_VARIATION_MAPPING_LIST = "CALL sp_product_get_product_sku_process_variation_mapping(?,?);";

	static final String SAVE_PROCESS_VARIATION_MAPPING_LIST = "CALL sp_product_save_product_sku_process_variation_mapping(?,?,?,?,?,?, @p_error_code, @p_error_message);";

	static final String GET_COMPLEMENTARY_SKU_MAPPING_LIST = "CALL sp_product_get_product_sku_complementary_sku_mapping(?,?);";

	static final String SAVE_COMPLEMENTARY_SKU_MAPPING_LIST = "CALL sp_product_save_product_sku_complementary_sku_mapping(?,?,?,?,?,?, @p_error_code, @p_error_message);";

	static final String GET_SIMILAR_SKU_MAPPING_LIST = "CALL sp_product_get_product_sku_similar_sku_mapping(?,?);";

	static final String SAVE_SIMILAR_SKU_MAPPING_LIST = "CALL sp_product_save_product_sku_similar_sku_mapping(?,?,?,?,?,?, @p_error_code, @p_error_message);";

	static final String GET_PROPS_MAPPING_LIST = "CALL sp_product_get_product_sku_props_mapping(?,?);";

	static final String SAVE_PROPS_MAPPING_LIST = "CALL sp_product_save_product_sku_props_mapping(?,?,?,?,?,?, @p_error_code, @p_error_message);";

	static final String GET_CATALOG_MAPPING_LIST = "CALL sp_product_get_product_sku_catalog_mapping(?,?);";

	static final String SAVE_CATALOG_MAPPING_LIST = "CALL sp_product_save_product_sku_catalog_mapping(?,?,?,?,?,?, @p_error_code, @p_error_message);";

	static final String COPY_PRODUCT_SKU = "CALL sp_product_copy_all_records_for_selected_product_sku(?,?,?,?,?,?,?,?,?,?,?,?,?,?, @p_error_code, @p_error_message);";

	static final String SAVE_PRODUCT_DETAILS = "CALL sp_product_save_product_details(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?, @p_last_inserted_id, @p_error_code, @p_error_message);";

	static final String SAVE_SKU_DETAILS = "CALL sp_product_save_sku_details(?,?,?,?,?,?,?,?, @p_last_inserted_id, @p_error_code, @p_error_message);";

	static final String APPROVE_PRODUCT_SKU = "CALL sp_product_approve_product_sku(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?, @p_error_code, @p_error_message);";

	static final String ACTIVATE_DEACTIVATE_PRODUCT_SKU = "CALL sp_product_active_deactive_product_sku(?,?,?,?,?, @p_error_code, @p_error_message);";

	static final String MODIFY_PRODUCTS_SKU = "CALL sp_product_modify_product_sku(?,?,?,?,?,?, @p_error_code, @p_error_message);";

	static final String GET_PRODUCTS_SKU_DETAILS_REPORT = " CALL sp_product_report_product_details(); ";

	static final String GET_IMAGE_MAPPING_LIST = "CALL sp_product_get_product_sku_image_mapping(?,?);";

	static final String SAVE_IMAGE_MAPPING_LIST = "CALL sp_product_save_product_sku_image_mapping(?,?,?,?,?,?, @p_error_code, @p_error_message);";

	static final String GET_SUGGESTED_VENDOR_STYLE_NUMBER = "SELECT DISTINCT psvm.vendor_style_number FROM product_sku_vendor_mapping psvm"
			+ " WHERE psvm.is_active = 1 AND psvm.vendor_code = ? AND psvm.vendor_style_number LIKE ?;";

	static final String GET_SUGGESTED_PRODUCTS_LIST_BASED_ON_CRITERIA = "CALL sp_product_get_product_details_based_on_criteria(?,?,?,?,?,?);";

	static final String GET_PROPERTIES_MAPPING_LIST_BASED_ON_CATEGORY_HIERARCHY = "CALL sp_product_get_sku_properties_based_on_category_hierarchy(?,?,?);";

	static final String SEARCH_PRODUCT_SKU_DETAILS = "CALL sp_product_search_product_sku(?,?,?,?, @p_error_code, @p_error_message);";

	static final String GET_PRODUCTS_DETAILS_BASED_ON_VENDOR_STYLE_NO = "CALL sp_product_get_product_details_based_on_vendor_style_number(?,?);";

	static final String GET_UOM_LIST_MAPPED_TO_PRODUCT = "SELECT cum.uom_code, cum.uom_name, cum.uom_description"
			+ " FROM product_uom_mapping pum, core_uom_master cum WHERE pum.uom_code = cum.uom_code AND cum.is_active = 1 AND pum.product_id = ?"
			+ " ORDER BY cum.uom_name;";

	static final String GET_SUGGESTED_SKU_LIST_BASED_ON_VENDOR_STYLE_NO = "CALL sp_product_get_sku_details_based_on_vendor_style_number(?,?,?,?);";

	static final String GET_SUGGESTED_ALL_PRODUCTS_LIST = "CALL sp_product_get_all_product_details_based_on_criteria(?,?,?,?,?,?);";

	static final String SEARCH_PRODUCT_ALL_SKU_DETAILS = "CALL sp_product_search_all_product_sku(?,?,?,?, @p_error_code, @p_error_message);";
	static final String GET_SUGGESTED_PRODUCTS_LIST_BASED_CATEGORY = "SELECT ph.product_id, ph.product_code,ph.product_name, ph.product_description, ph.product_version"
			+ " FROM product_header ph, product_hierarchy_category_mapping phcm ";

	static final String GET_SUGGESTED_ACTIVE_SKU_LIST = " SELECT psh.product_sku_id, psh.product_id, psh.sku_code, psh.sku_name, psh.sku_description, "
			+ " psh.sku_version, psh.status_code, psh.is_active "
			+ " FROM product_sku_header psh WHERE psh.sku_code LIKE ? AND is_active = ? ; ";

	static final String GET_OTHER_SKU_MAPPING_LIST = " CALL sp_search_product_sub_product_sku_mapping(?) ; ";

	static final String SAVE_OTHER_SKU_MAPPING_DETAILS = " CALL sp_save_product_sub_product_sku_mapping(?,?,?,?,?, @p_error_code, @p_error_message);";

	static final String SAVE_PRODUCT_SKU_BOM_DETAILS = " CALL sp_save_product_sku_bom_details(?,?,?,?,?,?, @p_error_code, @p_error_message);";

	static final String GET_PRODUCT_SKU_BOM_LIST = " CALL sp_search_product_sku_bom_details(?, ?) ; ";

	static final String GET_SKU_BOM_PROCESS_VARIATION_DETAILS = " CALL sp_get_sku_bom_process_variation_details(?); ";

	static final String GET_SUGGESTED_VENDOR_STYLE_NUMBER_BASED_ON_PRODUCT_SKU = "CALL sp_product_get_vendor_style_number_based_on_product_sku(?,?,?,?);";

	static final String GET_CATEGORY_FOR_PROPERTY_VALUE = " SELECT iim.item_code, ihcm.category_id, fn_inv_get_item_category_code_based_on_id(ihcm.category_id) AS category_code "
			+ " FROM inv_item_master iim, inv_hierarchy_category_mapping ihcm "
			+ " WHERE iim.item_code = ? AND iim.hierarchy_id = ihcm.hierarchy_id "
			+ " AND fn_inv_get_is_main_level(ihcm.level) = 1; ";

	static final String GET_PRODUCT_SPECIFIC_DETAILS = "CALL sp_product_get_product_specific_details(?,?); ";

	static final String GET_PRODUCT_SKU_PROPERTY_SPECIFIC_DETAILS = "CALL sp_product_get_product_sku_property_specific_details(?,?); ";

	static final String GET_PRODUCTS_IN_CATALOG_WITH_EXCLUSIVITY_FILTER = "CALL sp_core_get_products_in_catalog_with_exclusivity_filter(?,?,?,?,?);";

}
