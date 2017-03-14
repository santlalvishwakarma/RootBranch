DELIMITER $$

DROP PROCEDURE IF EXISTS sp_sku_get_mapped_properties $$
CREATE PROCEDURE sp_sku_get_mapped_properties(
IN p_sku_id INT(10),
IN p_product_id INT(10),
OUT p_error_code VARCHAR(50),
OUT p_error_message VARCHAR(500)
)

MODIFIES SQL DATA
DETERMINISTIC

BEGIN
	
   SELECT 'SIZ', product_sku_size_mapping_id, size_id, fn_core_get_size_code_based_on_id(size_id) 'size_code', fn_core_get_size_name_based_on_id(size_id) 'size_name', property_value, unit_id, 
		   fn_core_get_unit_name_based_on_id(unit_id) 'unit_name', fn_core_get_unit_code_based_on_id(unit_id) 'unit_code', is_active, modified_by, modified_date 
   FROM   product_sku_size_mapping
   WHERE  product_sku_id = p_sku_id
   AND 	  product_id = p_product_id;
   
   SELECT  'COL',product_sku_color_mapping_id, color_id, fn_core_get_color_code_based_on_id(color_id) 'color_code', fn_core_get_color_name_based_on_id(color_id) 'color_name', is_active, modified_by, modified_date
   FROM		product_sku_color_mapping
   WHERE	product_sku_id = p_sku_id
   AND 	    product_id = p_product_id;
   
   SELECT  'MAT', product_sku_material_mapping_id, material_id, fn_core_get_material_code_based_on_id(material_id) 'material_code', fn_core_get_material_name_based_on_id(material_id) 'material_name', is_active,
   		   modified_by, modified_date
   FROM		product_sku_material_mapping
   WHERE	product_sku_id = p_sku_id
   AND 	    product_id = p_product_id;
	
END $$

DELIMITER ;