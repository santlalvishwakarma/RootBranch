package com.web.bc.systemowner.modules.master.skupropertymaster;

import com.web.foundation.dao.SqlTemplate;

public interface SkuPropertySqlTemplate extends SqlTemplate {

	public static final String SEARCH_SKU_PROPERTIES = " SELECT sku_property_id, sku_property_code, sku_property_name, sku_property_description, sku_property_type, "
			+ " fn_get_parameter_value_text(sku_property_type) sku_property_type_value_text, sku_property_price, is_active, created_by, created_date, "
			+ " modified_by, modified_date FROM core_sku_property_master ";

	public static final String SAVE_SKU_PROPERTIES = " CALL sp_save_edit_property(?, ?, ?, ?, ?, ?, ?, ?, @p_error_code, @p_error_message); ";

}
