package com.web.common.startup.systemowner;

import com.web.foundation.dao.SqlTemplate;

public class StartupSqlTemplate implements SqlTemplate {

	private static final long serialVersionUID = 8265495332573365038L;

static final String PAGE_NAVIGATION_DETAILS = " SELECT cpn.core_page_navigation_id, cpn.page_code, cpn.page_url, cpn.page_display_name, "
			+ " cpn.page_title, cpn.page_description, cpn.page_keywords, cpn.page_robots, cpn.page_type, cpn.page_canonical_href, "
			+ " cpn.page_canonical_title, cpn.page_contents_href, cpn.page_contents_title, cpn.page_start_href, cpn.page_start_title, "
			+ " cpn.page_abstract FROM core_page_navigation cpn; ";

}
