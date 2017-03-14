package com.web.bc.retail.modules.readmorepanel;

import com.web.foundation.dao.SqlTemplate;

public interface ReadMoreSqlTemplate extends SqlTemplate {

	public static final String MAKE_PRODUCT_ENQUIRY = "INSERT INTO core_make_enquiry(core_make_enquiry_id,product_id,product_variant_id,user_name,"
			+ "user_email,comments,created_by,created_date,modified_by,modified_date,website_id) VALUES(null,?,?,?,?,?,?,NOW(),?,NOW(),?)";

	public static final String GET_CURRENCY_DETAILS = "SELECT cc.currency_symbol, cccm.conversion_multiplier "
			+ " FROM core_currency cc, core_country cco, core_currency_conversion_mapping cccm "
			+ " WHERE cco.core_country_id=(select core_country_id from core_country where country_name=?)"
			+ " AND cc.country_id = cco.core_country_id AND cccm.mapped_currency_id = cc.core_currency_id;";

	public static final String GET_PRODUCT_HEADER = " SELECT ph.product_id, ph.product_code, ph.product_name, ph.status_code, "
			+ " fn_get_hierarchy_name_based_on_id(phcm.hierarchy_id) hierarchy_name, "
			+ " fn_get_hierarchy_code_based_on_id(phcm.hierarchy_id) hierarchy_code, psh.default_image_url, "
			+ " psh.product_sku_id, psh.sku_code, psh.sku_name, psh.sku_description, psh.sku_property_text, "
			+ " psh.seo_title, psh.seo_keyword, psh.seo_description, psh.default_thumbnail_image_url, "
			+ " psh.default_zoom_image_url, psh.base_price, psh.discount_amount, psh.discount_percent, psh.final_base_price "
			+ " FROM product_header ph, product_sku_header psh, product_hierarchy_category_mapping phcm "
			+ " WHERE ph.is_active = 1 AND psh.is_active = 1 AND ph.product_id = psh.product_id "
			+ " AND psh.product_id = ? AND psh.product_sku_id = ? AND phcm.product_id = ph.product_id; ";

	public static final String GET_PRODUCT_ALTERNATIVE_IMAGES = " SELECT psim.product_sku_image_mapping_id, psim.product_sku_id, "
			+ " psim.image_type, psim.thumbnail_image_url, psim.zoom_image_url, psim.sequence_number, psim.image_url "
			+ " FROM product_sku_image_mapping psim, product_sku_header psh "
			+ " WHERE psim.product_sku_id =  psh.product_sku_id AND psim.is_active = 1 AND psim.product_sku_id = ? ; ";

	public static final String GET_CATEGORIES_FOR_PRODUCT = " SELECT phcm.product_hierarchy_category_mapping_id, "
			+ " phcm.hierarchy_id, fn_get_hierarchy_name_based_on_id(phcm.hierarchy_id) hierarchy_name, "
			+ " phcm.category_level_1, fn_get_category_name_based_on_id(phcm.category_level_1) category_level_1_name, "
			+ " fn_get_category_code_based_on_id(phcm.category_level_1) category_level_1_code, "
			+ " phcm.category_level_2, fn_get_category_name_based_on_id(phcm.category_level_2) category_level_2_name, "
			+ " fn_get_category_code_based_on_id(phcm.category_level_2) category_level_2_code, "
			+ " phcm.category_level_3, fn_get_category_name_based_on_id(phcm.category_level_3) category_level_3_name, "
			+ " fn_get_category_code_based_on_id(phcm.category_level_3) category_level_3_code, "
			+ " phcm.category_level_4, fn_get_category_name_based_on_id(phcm.category_level_4) category_level_4_name, "
			+ " fn_get_category_code_based_on_id(phcm.category_level_4) category_level_4_code, "
			+ " phcm.category_level_5, fn_get_category_name_based_on_id(phcm.category_level_5) category_level_5_name, "
			+ " fn_get_category_code_based_on_id(phcm.category_level_5) category_level_5_code "
			+ " FROM product_hierarchy_category_mapping phcm WHERE phcm.product_id = ?  ";

	public static final String GET_PRODUCT_PROPERTIES = "CALL sp_sku_get_mapped_properties(?,?, @p_error_code, @p_error_message);";

}
