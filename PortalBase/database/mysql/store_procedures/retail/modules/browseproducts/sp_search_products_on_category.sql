DELIMITER $$
DROP PROCEDURE IF EXISTS sp_search_products_on_category $$
CREATE PROCEDURE sp_search_products_on_category(
 IN p_hierarchy_name VARCHAR(500),
 IN p_level_1_category_code VARCHAR(500),
 IN p_level_2_category_code VARCHAR(500),
 IN p_level_3_category_code VARCHAR(500),
 IN p_level_4_category_code VARCHAR(500),
 IN p_user_login VARCHAR(500),
 IN p_website_id INT(10)
)
    MODIFIES SQL DATA
    DETERMINISTIC
BEGIN
DECLARE v_hierarchy_id INT(10);
DECLARE v_level_1_category_id INT(10);
DECLARE v_level_2_category_id INT(10);
DECLARE v_level_3_category_id INT(10);
DECLARE v_level_4_category_id INT(10);

SELECT 11111111111;

SET v_hierarchy_id = fn_get_hierarchy_id_based_on_name(p_hierarchy_name);

IF p_level_4_category_code IS NOT NULL THEN

	SET v_level_4_category_id = fn_get_category_id_based_on_code(p_level_4_category_code);

END IF;

IF p_level_3_category_code IS NOT NULL THEN

	SET v_level_3_category_id = fn_get_category_id_based_on_code(p_level_3_category_code);

END IF;

IF p_level_2_category_code IS NOT NULL THEN

	SET v_level_2_category_id = fn_get_category_id_based_on_code(p_level_2_category_code);

END IF;

IF p_level_1_category_code IS NOT NULL THEN

	SET v_level_1_category_id = fn_get_category_id_based_on_code(p_level_1_category_code);

END IF;

IF p_level_4_category_code IS NOT NULL THEN
	
	SELECT v_level_4_category_id;

	SELECT  psh.sku_code, psh.sku_name, psh.sku_description, psh.product_sku_id, psh.product_id, psh.sku_property_text, psh.default_thumbnail_image_url, psh.default_zoom_image_url, ph.product_id,
			psh.base_price, psh.discount_amount, psh.discount_percent, psh.final_base_price, psh.is_active, phcm.hierarchy_id, phcm.category_level_1, phcm.category_level_2, phcm.category_level_3,
			phcm.category_level_4, phcm.category_level_5, fn_get_category_name_based_on_id(phcm.category_level_1) 'category_level_1_name', fn_get_category_name_based_on_id(phcm.category_level_2) 'category_level_2_name',
			fn_get_category_name_based_on_id(phcm.category_level_3) 'category_level_3_name', fn_get_category_name_based_on_id(phcm.category_level_4) 'category_level_4_name'
	FROM	product_hierarchy_category_mapping phcm, product_header ph, product_sku_header psh, product_sku_stock_level pssl
	WHERE	phcm.category_level_4 = v_level_4_category_id
	AND		phcm.category_level_3 = v_level_3_category_id
	AND		phcm.category_level_2 = v_level_2_category_id
	AND		phcm.category_level_1 = v_level_1_category_id
	AND		phcm.hierarchy_id = v_hierarchy_id
	AND		phcm.product_id = ph.product_id
	AND		ph.is_active = 1
	AND		ph.product_id = psh.product_id
	AND		psh.is_active = 1
	AND		pssl.product_sku_id = psh.product_sku_id
	AND		pssl.available_quantity > pssl.blocked_quantity;

	
ELSEIF p_level_3_category_code IS NOT NULL AND p_level_4_category_code IS NULL THEN

	SELECT v_level_3_category_id;

	SELECT  psh.sku_code, psh.sku_name, psh.sku_description, psh.product_sku_id, psh.product_id, psh.sku_property_text, psh.default_thumbnail_image_url, psh.default_zoom_image_url, ph.product_id,
			psh.base_price, psh.discount_amount, psh.discount_percent, psh.final_base_price, psh.is_active, phcm.hierarchy_id, phcm.category_level_1, phcm.category_level_2, phcm.category_level_3,
			phcm.category_level_4, phcm.category_level_5, fn_get_category_name_based_on_id(phcm.category_level_1) 'category_level_1_name', fn_get_category_name_based_on_id(phcm.category_level_2) 'category_level_2_name',
			fn_get_category_name_based_on_id(phcm.category_level_3) 'category_level_3_name'
	FROM	product_hierarchy_category_mapping phcm, product_header ph, product_sku_header psh, product_sku_stock_level pssl
	WHERE	phcm.category_level_3 = v_level_3_category_id
	AND		phcm.category_level_2 = v_level_2_category_id
	AND		phcm.category_level_1 = v_level_1_category_id
	AND		phcm.hierarchy_id = v_hierarchy_id
	AND		phcm.product_id = ph.product_id
	AND		ph.is_active = 1
	AND		ph.product_id = psh.product_id
	AND		psh.is_active = 1
	AND		pssl.product_sku_id = psh.product_sku_id
	AND		pssl.available_quantity > pssl.blocked_quantity;
	

ELSEIF p_level_2_category_code IS NOT NULL AND p_level_3_category_code IS NULL AND p_level_4_category_code IS NULL THEN

	SELECT v_level_2_category_id;

	SELECT  psh.sku_code, psh.sku_name, psh.sku_description, psh.product_sku_id, psh.product_id, psh.sku_property_text, psh.default_thumbnail_image_url, psh.default_zoom_image_url, ph.product_id,
			psh.base_price, psh.discount_amount, psh.discount_percent, psh.final_base_price, psh.is_active, phcm.hierarchy_id, phcm.category_level_1, phcm.category_level_2, phcm.category_level_3,
			phcm.category_level_4, phcm.category_level_5, fn_get_category_name_based_on_id(phcm.category_level_1) 'category_level_1_name', fn_get_category_name_based_on_id(phcm.category_level_2) 'category_level_2_name'
	FROM	product_hierarchy_category_mapping phcm, product_header ph, product_sku_header psh, product_sku_stock_level pssl
	WHERE	phcm.category_level_2 = v_level_2_category_id
	AND		phcm.category_level_1 = v_level_1_category_id
	AND		phcm.hierarchy_id = v_hierarchy_id
	AND		phcm.product_id = ph.product_id
	AND		ph.is_active = 1
	AND		ph.product_id = psh.product_id
	AND		psh.is_active = 1
	AND		pssl.product_sku_id = psh.product_sku_id
	AND		pssl.available_quantity > pssl.blocked_quantity;
	
	
ELSEIF p_level_1_category_code IS NOT NULL AND p_level_2_category_code IS NULL AND p_level_3_category_code IS NULL AND p_level_4_category_code IS NULL THEN

	SELECT v_level_1_category_id;

	SELECT  psh.sku_code, psh.sku_name, psh.sku_description, psh.product_sku_id, psh.product_id, psh.sku_property_text, psh.default_thumbnail_image_url, psh.default_zoom_image_url, ph.product_id,
			psh.base_price, psh.discount_amount, psh.discount_percent, psh.final_base_price, psh.is_active, phcm.hierarchy_id, phcm.category_level_1, phcm.category_level_2, phcm.category_level_3,
			phcm.category_level_4, phcm.category_level_5, fn_get_category_name_based_on_id(phcm.category_level_1) 'category_level_1_name'
	FROM	product_hierarchy_category_mapping phcm, product_header ph, product_sku_header psh, product_sku_stock_level pssl
	WHERE	phcm.category_level_1 = v_level_1_category_id
	AND		phcm.hierarchy_id = v_hierarchy_id
	AND		phcm.product_id = ph.product_id
	AND		ph.is_active = 1
	AND		ph.product_id = psh.product_id
	AND		psh.is_active = 1
	AND		pssl.product_sku_id = psh.product_sku_id
	AND		pssl.available_quantity > pssl.blocked_quantity;


END IF;





END $$
DELIMITER ;

