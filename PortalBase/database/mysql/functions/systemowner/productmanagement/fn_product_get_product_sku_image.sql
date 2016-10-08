DELIMITER $$

DROP FUNCTION IF EXISTS fn_product_get_product_sku_image $$
CREATE FUNCTION fn_product_get_product_sku_image(p_product_id INT(10), p_product_sku_id INT(10)) RETURNS TEXT 
    READS SQL DATA
BEGIN
DECLARE output_text TEXT;
          
IF p_product_id IS NOT NULL AND p_product_sku_id IS NULL THEN
		
	SELECT 	GROUP_CONCAT(pim.sequence_no,'~',pim.zoom_image_url,'~',pim.thumbnail_image_url ORDER BY pim.sequence_no SEPARATOR ';;') INTO output_text
	FROM 	product_sku_image_mapping pim,
			product_sku_header_detail_mapping pshdm
	WHERE 	pshdm.product_id = p_product_id
  	AND     pshdm.product_sku_id = pim.product_sku_id
  	AND 	pshdm.default_sku = 1;

  
ELSEIF p_product_sku_id IS NOT NULL THEN
		
	SELECT 	GROUP_CONCAT(psim.sequence_no,'~',psim.zoom_image_url,'~',psim.thumbnail_image_url ORDER BY psim.sequence_no  SEPARATOR ';;') INTO output_text
	FROM 	product_sku_image_mapping psim,
			product_sku_header_detail_mapping pshdm
	WHERE 	pshdm.product_sku_id = p_product_sku_id
  	AND     pshdm.product_sku_id = psim.product_sku_id;
  	
  
END IF;
	
RETURN(output_text);
END $$

DELIMITER ;

