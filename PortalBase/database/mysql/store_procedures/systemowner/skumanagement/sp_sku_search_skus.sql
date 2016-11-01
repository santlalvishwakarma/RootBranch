DELIMITER $$

DROP PROCEDURE IF EXISTS sp_sku_search_skus $$
CREATE PROCEDURE sp_sku_search_skus(
IN p_sku_code VARCHAR(25),
IN p_sku_name VARCHAR(60),
IN p_sku_description VARCHAR(255),
IN p_product_code VARCHAR(25),
IN p_status tinyint(1),
OUT p_error_code VARCHAR(50),
OUT p_error_message VARCHAR(500)
)

MODIFIES SQL DATA
DETERMINISTIC

BEGIN

DECLARE v_string_product_code VARCHAR(250) DEFAULT '';
DECLARE v_string_sku_name VARCHAR(250) DEFAULT '';
DECLARE v_string_sku_code VARCHAR(250) DEFAULT '';
DECLARE v_string_sku_description VARCHAR(250) DEFAULT '';
DECLARE v_string_status_code VARCHAR(250) DEFAULT '';
DECLARE  v_product_id INT;


  IF  p_product_code IS NOT NULL OR p_sku_name IS NOT NULL OR p_sku_code IS NOT NULL OR p_sku_description IS NOT NULL OR p_status IS NOT NULL THEN
  
      IF p_product_code IS NOT NULL THEN
          
      	  SET v_product_id = fn_product_get_product_id_based_on_code(p_product_code);
          SET v_string_product_code = " AND psh.product_id = ";
          SET v_string_product_code = CONCAT(v_string_product_code  , v_product_id);
      END IF;
      
      IF p_sku_name IS NOT NULL THEN
          
          SET v_string_sku_name = " AND psh.sku_name LIKE '";
          SET v_string_sku_name = CONCAT(v_string_sku_name, p_sku_name,"%'");
      END IF;
	  
      IF p_status IS NOT NULL THEN
      
          SET v_string_status_code = " AND psh.is_active = '";
          SET v_string_status_code = CONCAT(v_string_status_code, p_status,"'");
      END IF;
      
      IF p_sku_code IS NOT NULL THEN
          
          SET v_string_sku_code = " AND psh.sku_code LIKE '";
          SET v_string_sku_code = CONCAT(v_string_sku_code, p_sku_code,"%'");
      END IF;
      
      IF p_sku_description IS NOT NULL THEN
          
          SET v_string_sku_description = " AND psh.sku_description LIKE '";
          SET v_string_sku_description = CONCAT(v_string_sku_description, p_sku_description,"%'");
      END IF;
      
      
      	SET @v_sql_statement = CONCAT(
        "SELECT psh.product_id, ph.product_code, ph.product_name, ph.product_description, psh.product_sku_id, psh.sku_code, psh.sku_name, psh.sku_description, psh.is_active, psh.sku_property_text,psh.seo_title,
				psh.default_thumbnail_image_url AS thumbnail_image_url, psh.default_zoom_image_url AS zoom_image_url, psh.seo_keyword, psh.seo_description, psh.base_price, psh.discount_amount, psh.discount_percent,
				psh.final_base_price, psh.default_sku, psh.default_image_url 
        FROM    product_sku_header psh,
				product_header ph
        WHERE   1 = 1 
		AND		ph.product_id = psh.product_id ", v_string_product_code, v_string_sku_name, v_string_sku_code, v_string_sku_description, v_string_status_code);
          
        PREPARE stmnt3 FROM  @v_sql_statement;
        EXECUTE stmnt3;
        
        
  ELSE
  
      SET p_error_code = 'param_null';
      SET p_error_message = 'The parameters entered are null';            
      SELECT p_error_code, p_error_message;
                         
  END IF;
END $$
/*
CALL sp_product_search_products(
NULL, -- IN p_product_categories VARCHAR(60),
NULL, -- IN p_product_code varchar(25),
NULL, -- IN p_product_name varchar(60),
NULL, -- IN p_product_description VARCHAR(255),
NULL, -- IN p_sku_code varchar(25),
NULL, -- IN p_sku_description varchar(255),
NULL, -- IN p_product_hierarchies_id TEXT,
NULL, -- IN p_product_properties TEXT,
NULL, -- IN p_status_code,
@p_error_code,
@p_error_message);
*/
DELIMITER ;

