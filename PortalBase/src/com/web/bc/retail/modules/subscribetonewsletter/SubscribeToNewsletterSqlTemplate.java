package com.web.bc.retail.modules.subscribetonewsletter;

import com.web.foundation.dao.SqlTemplate;

public interface SubscribeToNewsletterSqlTemplate extends SqlTemplate {

	static final String REGISTER_NEWSLETTER_SUBSCRIPTION = "CALL sp_retail_subscribe_to_news_letter(?,?, @p_error_code, @p_error_message);";
}
