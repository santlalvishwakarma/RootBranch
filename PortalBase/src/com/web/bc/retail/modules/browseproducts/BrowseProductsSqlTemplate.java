package com.web.bc.retail.modules.browseproducts;

import com.web.foundation.dao.SqlTemplate;

public class BrowseProductsSqlTemplate implements SqlTemplate {

	static final String MAKE_PRODUCT_ENQUIRY = "INSERT INTO core_make_enquiry(core_make_enquiry_id,product_id,product_variant_id,user_name,"
			+ "user_email,comments,created_by,created_date,modified_by,modified_date) VALUES(null,?,?,?,?,?,?,NOW(),?,NOW())";

	static final String SEARCH_MAPPING_ON_CATEGORY_CODES = "CALL sp_core_search_category_mapping_for_filter(?,?,?,?,?)";

	static final String SEARCH_USER_COMMENTS = "SELECT cg.user_name,cg.comments,cc.flag_url "
			+ "FROM core_guest_book cg, core_country cc " + "WHERE cc.core_country_id = cg.country_id "
			+ "ORDER BY RAND() LIMIT 3;";

	static final String SEARCH_USER_COMMENTS_ON_CATEGORY = "SELECT cg.user_name,cg.comments,cc.flag_url "
			+ "FROM core_guest_book cg, core_country cc "
			+ "WHERE cc.core_country_id = cg.country_id AND  IFNULL(cg.category_code,'') = IFNULL(?,'')"
			+ "ORDER BY RAND() LIMIT 3; ";

	public static final String GET_CURRENCY_DETAILS = "SELECT cc.currency_symbol, cccm.conversion_multiplier "
			+ " FROM core_currency cc, core_country cco, core_currency_conversion_mapping cccm "
			+ " WHERE cco.core_country_id=(select core_country_id from core_country where country_name=?)"
			+ " AND cc.country_id = cco.core_country_id AND cccm.mapped_currency_id = cc.core_currency_id;";

	public static final String SEARCH_IMAGES_ON_CATEGORY = "CALL sp_core_search_categoris_images(?,?,?,?)";

	public static final String SEARCH_PRODUCT_ON_CATEGORY = "CALL sp_search_products_on_category(?,?,?,?,?,?,?);";

	public static final String SEARCH_SUB_CATEGORY_ON_MAIN_CATEGORY = "CALL sp_search_sub_category_based_on_main_category(?,?,?,?,?)";

}
