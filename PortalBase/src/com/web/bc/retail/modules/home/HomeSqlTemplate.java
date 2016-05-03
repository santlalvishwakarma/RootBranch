package com.web.bc.retail.modules.home;

import com.web.foundation.dao.SqlTemplate;

public class HomeSqlTemplate implements SqlTemplate {

	static final String SEARCH_ROLLING_IMAGES = "SELECT core_rolling_images_id, param_sequence_number, image_url, link_page_url FROM core_rolling_images;";

	public static final String GET_CURRENCY_DETAILS = "SELECT cc.currency_symbol, cccm.conversion_multiplier "
			+ " FROM core_currency cc, core_country cco, core_currency_conversion_mapping cccm "
			+ " WHERE cco.core_country_id=(select core_country_id from core_country where country_name=?)"
			+ " AND cc.country_id = cco.core_country_id AND cccm.mapped_currency_id = cc.core_currency_id;";
}
