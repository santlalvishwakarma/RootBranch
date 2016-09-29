package com.web.bc.systemowner.modules.hierarchymaster;

import com.web.foundation.dao.SqlTemplate;

/**
 * @author NIRAJ
 * 
 */
public interface HierarchyMasterSqlTemplate extends SqlTemplate {

	static final String SEARCH_PRODUCTS_HIERARCHY = "CALL sp_product_search_hierarchies(?,?,?, @p_error_code, @p_error_message);";

	static final String GET_PRODUCTS_HIERARCHY_LEVEL = "SELECT phcm.product_hierarchy_category_mapping_id, phcm.product_hierarchy_id, phcm.level,"
			+ " phcm.product_category_id, pcm.product_category_code, pcm.product_category_name, pcm.product_category_description"
			+ " FROM product_category_master pcm, product_hierarchy_category_mapping phcm"
			+ " WHERE phcm.product_hierarchy_id = ? AND pcm.product_category_id = phcm.product_category_id ORDER BY phcm.level;";

	static final String SAVE_PRODUCTS_HIERARCHY = "CALL sp_product_save_hierarchies(?,?,?,?,?,?,?, @p_last_inserted_id, @p_error_code, @p_error_message);";

	static final String GET_SUGGESTED_HIERARCHY = "SELECT hierarchy_id, hierarchy_code, hierarchy_name,"
			+ " hierarchy_description, modified_date FROM hierarchy_master";

	static final String GET_SUGGESTED_PRODUCTS_HIERARCHY_BASED_ON_CATEGORY = "SELECT DISTINCT phmm.product_hierarchy_id ,"
			+ " phmm.product_hierarchy_code, phmm.product_hierarchy_name, phmm.product_hierarchy_description"
			+ " FROM product_category_master pcm, product_hierarchy_category_mapping phcm, product_category_level_master pclm,"
			+ " product_hierarchy_mapping phm, product_hierarchy_master phmm, product_sku_header_detail_mapping pshdm, product_header ph"
			+ " WHERE pcm.product_category_id = phcm.product_category_id AND phcm.product_hierarchy_id = phm.product_hierarchy_id"
			+ " AND phm.product_hierarchy_id = phmm.product_hierarchy_id AND pclm.level = phcm.level AND pclm.main_level = 1"
			+ " AND phm.fetch_properties = 1 AND pshdm.table_name = 'product_hierarchy_mapping' AND ph.status_code ='APPROVED'"
			+ " AND ph.is_active = 1 AND pshdm.details_id = phm.product_hierarchy_mapping_id AND pshdm.product_sku_id IS NULL AND ph.product_id = pshdm.product_id ";
}
