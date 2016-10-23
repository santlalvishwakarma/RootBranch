DELIMITER $$

DROP PROCEDURE IF EXISTS sp_product_get_product_sku_details $$
CREATE  PROCEDURE sp_product_get_product_sku_details(
IN p_product_id INT(10),
IN p_product_sku_id INT(10)
)

MODIFIES SQL DATA
DETERMINISTIC
BEGIN

DECLARE v_hierarchy TINYINT(1);
DECLARE v_properties TINYINT(1);
DECLARE v_rm TINYINT(1);
DECLARE v_process_variation TINYINT(1);
DECLARE v_complementary_sku TINYINT(1);
DECLARE v_similar_sku TINYINT(1);
DECLARE v_props TINYINT(1);
DECLARE v_catalog TINYINT(1);
DECLARE v_images TINYINT(1);
DECLARE v_items TINYINT(1);

IF p_product_id IS NOT NULL AND p_product_sku_id IS NULL THEN	

	SELECT 	'HDR', ph.product_id, ph.product_code, ph.product_name, ph.product_description,
			ph.status_code, fn_core_get_status_name(ph.status_code) AS  status_name, ph.is_active,
			fn_product_get_product_sku_image(p_product_id, p_product_sku_id) AS image_url, ph.created_by, ph.created_date, ph.modified_by, ph.modified_date
	FROM 	product_header ph
	WHERE 	ph.product_id =  p_product_id;
	
	SELECT 	'DTL',psh.product_sku_id, psh.sku_code,sku_name, psh.sku_description, psh.sku_property_text, psh.seo_title, psh.seo_keyword, psh.seo_description, psh.default_thumbnail_image_url,
			psh.default_zoom_image_url, psh.base_price, psh.discount_amount, psh.discount_percent, psh.final_base_price, psh.is_active, psh.created_by, psh.created_date, psh.modified_by, psh.modified_date,
			psh.default_sku
	FROM 	product_header ph, product_sku_header psh
	WHERE 	ph.product_id =  p_product_id
	AND		psh.product_id = ph.product_id;
	
ELSEIF p_product_sku_id IS NOT NULL THEN
	
	SELECT 	'DTL',psh.product_id, psh.product_sku_id, psh.sku_code, psh.sku_name, psh.sku_description,
			psh.is_active AS sku_is_active, psh.modified_date,
			ph.product_code, ph.product_name, ph.product_description,
			fn_product_get_product_sku_image(p_product_id, p_product_sku_id) AS image_url, ph.created_by, ph.created_date, ph.modified_by, ph.modified_date
	FROM 	product_sku_header psh,
			product_header ph
	WHERE 	psh.product_sku_id =  p_product_sku_id
	AND 	psh.product_id = ph.product_id;
	
	
END IF;

END $$
/*
call sp_product_get_product_sku_details (
1, -- p_product_id,
null --  p_product_sku_id INT(10)
);
*/
DELIMITER ;

