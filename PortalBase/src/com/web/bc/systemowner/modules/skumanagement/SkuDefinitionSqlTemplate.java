package com.web.bc.systemowner.modules.skumanagement;

import com.web.foundation.dao.SqlTemplate;

public interface SkuDefinitionSqlTemplate extends SqlTemplate {

	static final String SEARCH_SKU_DETAILS = "CALL sp_sku_search_skus(?,?,?,?,?, @p_error_code, @p_error_message);";

	static final String GET_IMAGE_MAPPING_LIST = "SELECT psim.product_sku_image_mapping_id AS product_image_mapping_id, psim.thumbnail_image_url, psim.sequence_number, psim.zoom_image_url, psim.image_type, psim.image_url "
			+ " FROM product_sku_image_mapping psim, product_sku_header psh "
			+ " WHERE psim.product_sku_id = ? AND psim.product_sku_id = psh.product_sku_id "
			+ " ORDER BY psim.sequence_number;";

	static final String SAVE_IMAGE_MAPPING_LIST = "CALL sp_product_save_product_sku_image_mapping(?,?,?,?, @p_error_code, @p_error_message);";

	static final String SAVE_SKU_DETAILS = "CALL sp_product_save_sku_details(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?, @p_last_inserted_id, @p_error_code, @p_error_message);";

	static final String GET_SKU_DETAILS = "SELECT product_sku_id, product_id, sku_code, sku_name, sku_description, sku_property_text, seo_title, seo_keyword, seo_description, default_thumbnail_image_url, "
			+ " default_image_url, default_zoom_image_url, base_price, discount_amount, discount_percent, final_base_price, default_sku, is_active, modified_by, modified_date,"
			+ " fn_product_get_product_code(product_id) AS product_code, fn_product_get_product_name(product_id) AS product_name "
			+ " FROM product_sku_header WHERE product_sku_id = ?;";

}
