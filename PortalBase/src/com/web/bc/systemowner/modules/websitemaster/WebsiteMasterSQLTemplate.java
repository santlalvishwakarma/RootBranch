package com.web.bc.systemowner.modules.websitemaster;

import com.web.foundation.dao.SqlTemplate;

public class WebsiteMasterSQLTemplate implements SqlTemplate {

	static final String ALL_WEBSITE_NAMES = " SELECT core_website_id, website_name, website_url, created_by, created_date, modified_by, "
			+ " modified_date FROM core_website;";

	static final String WEBSITES_FOR_CATALOG = "SELECT DISTINCT cw.core_website_id,cw.website_name,cw.website_url"
			+ " from core_website cw , core_catalog_web_site_mapping ccwsm where ccwsm.catalog_id = ?"
			+ " AND ccwsm.website_id = cw.core_website_id";

	static final String QUICK_EDIT_SAVE = "UPDATE core_website SET website_name=?, website_url =?,"
			+ " modified_by = ?, modified_date=NOW() WHERE core_website_id =?;";

	static final String CHECK_WEBSITE_NAME_AVAILABILITY = "SELECT website_name FROM core_website WHERE "
			+ " website_name = ?;";

	static final String CHECK_WEBSITE_URL_AVAILABILITY = "SELECT website_url FROM core_website WHERE"
			+ " website_url = ?;";

	static final String ADD_WEBSITE = "CALL sp_core_website_master_add(?,?,?);";

	static final String SEARCH_AUTOSUGGEST_WEBSITE_NAMES = "SELECT core_website_id, website_name, website_url,"
			+ " created_by, created_date, modified_by, modified_date from core_website where website_name like ?";

}
