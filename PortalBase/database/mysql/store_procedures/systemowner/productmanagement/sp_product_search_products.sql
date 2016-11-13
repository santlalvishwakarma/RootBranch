DELIMITER $$

DROP PROCEDURE IF EXISTS sp_product_search_products $$
CREATE PROCEDURE sp_product_search_products(
IN p_product_categories VARCHAR(500),
IN p_product_code VARCHAR(25),
IN p_product_name VARCHAR(60),
IN p_product_description VARCHAR(255),
IN p_sku_code VARCHAR(25),
IN p_sku_description VARCHAR(255),
IN p_product_hierarchies_id VARCHAR(500),
IN p_product_properties TEXT,
IN p_status_code tinyint(1),
OUT p_error_code VARCHAR(50),
OUT p_error_message VARCHAR(500)
)

MODIFIES SQL DATA
DETERMINISTIC

BEGIN

DECLARE v_string_product_categories VARCHAR(500) DEFAULT '';
DECLARE v_string_product_code VARCHAR(250) DEFAULT '';
DECLARE v_string_product_name VARCHAR(250) DEFAULT '';
DECLARE v_string_product_description VARCHAR(250) DEFAULT '';
DECLARE v_string_sku_code VARCHAR(250) DEFAULT '';
DECLARE v_string_sku_description VARCHAR(250) DEFAULT '';
DECLARE v_string_status_code VARCHAR(250) DEFAULT '';
DECLARE  v_counter INT;

/*  
  DECLARE EXIT HANDLER FOR SQLEXCEPTION 
  BEGIN
    SET p_error_code = 'sql_exception';
    SET p_error_message = 'SQL Exception - please contact System Admin';
    SELECT p_error_code, p_error_message;
    -- select SQLEXCEPTION;   
  END;  
*/  
DROP TEMPORARY TABLE IF EXISTS temp_hierarchies;
CREATE TEMPORARY TABLE temp_hierarchies
(hierarchy_id INT(10));

DROP TEMPORARY TABLE IF EXISTS temp_hierarchy_products;
CREATE TEMPORARY TABLE temp_hierarchy_products
(product_id INT(10));

DROP TEMPORARY TABLE IF EXISTS temp_categories;
CREATE TEMPORARY TABLE temp_categories
(category_id INT(10));

  IF  p_product_categories IS NOT NULL OR p_product_code IS NOT NULL OR p_product_name IS NOT NULL OR p_product_description IS NOT NULL 
      OR p_sku_code IS NOT NULL OR p_sku_description IS NOT NULL 
      OR p_product_hierarchies_id IS NOT NULL OR p_product_properties IS NOT NULL OR p_status_code IS NOT NULL THEN 
  	 /*
      IF p_product_categories IS NOT NULL THEN
          
          SET v_string_product_categories = " AND pcm.product_category_id IN (";
          SET v_string_product_categories = CONCAT(v_string_product_categories, p_product_categories,")");
      END IF;
      */
      IF p_product_code IS NOT NULL THEN
          
          SET v_string_product_code = " AND ph.product_code LIKE '";
          SET v_string_product_code = CONCAT(v_string_product_code  , p_product_code,"%'");
      END IF;
      
      IF p_product_name IS NOT NULL THEN
          
          SET v_string_product_name = " AND ph.product_name LIKE '";
          SET v_string_product_name = CONCAT(v_string_product_name, p_product_name,"%'");
      END IF;
	  
      IF p_status_code IS NOT NULL THEN
          
          SET v_string_status_code = " AND ph.is_active = '";
          SET v_string_status_code = CONCAT(v_string_status_code, p_status_code,"'");
      END IF;
      
      IF p_product_description IS NOT NULL THEN
          
          SET v_string_product_description = " AND ph.product_description LIKE '";
          SET v_string_product_description = CONCAT(v_string_product_description, p_product_description,"%'");
      END IF;
      
      IF p_sku_code IS NOT NULL THEN
          
          SET v_string_sku_code = " AND psh.sku_code LIKE '";
          SET v_string_sku_code = CONCAT(v_string_sku_code, p_sku_code,"%'");
      END IF;
      
      IF p_sku_description IS NOT NULL THEN
          
          SET v_string_sku_description = " AND psh.sku_description LIKE '";
          SET v_string_sku_description = CONCAT(v_string_sku_description, p_sku_description,"%'");
      END IF;
      
      IF p_product_hierarchies_id IS NOT NULL THEN
      
          CALL sp_common_parsing_logic(p_product_hierarchies_id);
          INSERT INTO temp_hierarchies
          (hierarchy_id)
          SELECT parse_code FROM temp_parse_data;
          
          	INSERT INTO temp_hierarchy_products          
          	SELECT 	pshdm.product_id  
		 	FROM 	product_hierarchy_mapping phm,
		         	product_sku_header_detail_mapping pshdm,
		         	temp_hierarchies th
			WHERE 	th.hierarchy_id = phm.product_hierarchy_id
		  	AND     pshdm.table_name = 'product_hierarchy_mapping'
		  	AND     pshdm.details_id = phm.product_hierarchy_mapping_id
		  	AND 	pshdm.product_sku_id IS NULL;
  	
      END IF;
      
      IF p_product_categories IS NOT NULL THEN
      
          CALL sp_common_parsing_logic(p_product_categories);
          INSERT INTO temp_categories
          (category_id)
          SELECT parse_code FROM temp_parse_data;
          
          SELECT 	COUNT(1) INTO v_counter 
	      FROM 		temp_categories;
	      
	      INSERT INTO temp_hierarchies
	      (			hierarchy_id
	      )                  
	      SELECT 	phcm.product_hierarchy_id 
	      FROM 		product_hierarchy_category_mapping phcm, 
	      			temp_categories tc,
	      			product_category_master pcm
	      WHERE 	phcm.product_category_id = tc.category_id
	      AND 		pcm.product_category_id = phcm.product_category_id 
		  GROUP BY 	phcm.product_hierarchy_id
		  HAVING 	COUNT(pcm.product_category_id) = v_counter;
		  
		  INSERT INTO temp_hierarchy_products          
          SELECT 	pshdm.product_id  
		  FROM 		product_hierarchy_mapping phm,
		         	product_sku_header_detail_mapping pshdm,
		         	temp_hierarchies th
		  WHERE 	th.hierarchy_id = phm.product_hierarchy_id
		  AND     pshdm.table_name = 'product_hierarchy_mapping'
		  AND     pshdm.details_id = phm.product_hierarchy_mapping_id
		  AND 	pshdm.product_sku_id IS NULL;
      END IF;
            	  
      IF (p_product_hierarchies_id IS NOT NULL OR p_product_categories IS NOT NULL) AND (p_sku_code IS NOT NULL OR p_sku_description IS NOT NULL)      
      AND p_product_code IS NOT NULL OR p_product_name IS NOT NULL OR p_product_description IS NOT NULL THEN
      	
      	SET @v_sql_statement = CONCAT(
        " SELECT  DISTINCT ph.product_id, ph.product_code,ph.product_name, ph.product_description, psh.product_sku_id, psh.sku_code, psh.sku_description, ph.status_code, ph.is_active, fn_core_get_status_name(ph.status_code) AS status_name,
				  fn_product_get_product_sku_image(ph.product_id,psh.product_sku_id) AS image_url   
          FROM    product_header ph,
				  temp_product_search_product_ids tpspi,       
				  product_sku_header psh,
				  temp_hierarchy_products thm
                  -- product_hierarchy_mapping phm,
                  -- temp_hierarchies th
          WHERE   -- ph.product_id = phm.product_id AND	  
				  ph.product_id = psh.product_id
		  AND     ph.product_id = tpspi.product_id
		  AND     thm.product_id = ph.product_id 
          -- AND     th.hierarchy_id = phm.product_hierarchy_id  
		  ", v_string_sku_code, v_string_sku_description, -- v_string_product_categories, 
          v_string_product_code, v_string_product_name, v_string_product_description, v_string_status_code);
      
      	  PREPARE stmnt3 FROM  @v_sql_statement;
          EXECUTE stmnt3;                    
          
      ELSEIF (p_product_hierarchies_id IS NOT NULL OR p_product_categories IS NOT NULL) AND (p_sku_code IS NOT NULL OR p_sku_description IS NOT NULL) THEN
      	
      	SET @v_sql_statement = CONCAT(
        " SELECT  DISTINCT ph.product_id, ph.product_code,ph.product_name, ph.product_description, psh.product_sku_id, psh.sku_code, psh.sku_description, ph.status_code, ph.is_active, fn_core_get_status_name(ph.status_code) AS status_name,
				  fn_product_get_product_sku_image(ph.product_id,psh.product_sku_id) AS image_url  
          FROM    product_header ph,
				  product_sku_header psh,
				  temp_hierarchy_products thm
                  -- product_hierarchy_mapping phm,                  
                  -- temp_hierarchies th
          WHERE   -- ph.product_id = phm.product_id	  AND	  
				  ph.product_id = psh.product_id
		  AND 	  thm.product_id = ph.product_id  
          -- AND     th.hierarchy_id = phm.product_hierarchy_id  
		  ", v_string_sku_code, v_string_sku_description, -- v_string_product_categories, 
          v_string_product_code, v_string_product_name, v_string_product_description, v_string_status_code);
          
          PREPARE stmnt3 FROM  @v_sql_statement;
          EXECUTE stmnt3;    
          
      ELSEIF p_product_hierarchies_id IS NOT NULL  OR p_product_categories IS NOT NULL THEN
      
        SET @v_sql_statement = CONCAT(
        " SELECT  DISTINCT ph.product_id, ph.product_code,ph.product_name, ph.product_description, psh.product_sku_id, psh.sku_code, psh.sku_description, ph.status_code , ph.is_active, fn_core_get_status_name(ph.status_code) AS status_name,
				  fn_product_get_product_sku_image(ph.product_id,psh.product_sku_id) AS image_url	 
          FROM    product_header ph
				  LEFT JOIN product_sku_header psh
				  ON (ph.product_id = psh.product_id),
				  temp_hierarchy_products thm				  
                  -- product_hierarchy_mapping phm,
                  -- temp_hierarchies th				  
          WHERE   -- ph.product_id = phm.product_id
				  ph.product_id = thm.product_id 
          -- AND     th.hierarchy_id = phm.product_hierarchy_id  
		  ", -- v_string_product_categories, 
          v_string_product_code, v_string_product_name, v_string_product_description, v_string_status_code);
          
          PREPARE stmnt3 FROM  @v_sql_statement;
          EXECUTE stmnt3;                
          
      ELSE
      
      	SET @v_sql_statement = CONCAT(
        "SELECT ph.product_id, ph.product_code,ph.product_name, ph.product_description, psh.product_sku_id, psh.sku_code, psh.sku_description, ph.status_code, ph.is_active, fn_core_get_status_name(ph.status_code) AS status_name,
				psh.default_thumbnail_image_url AS thumbnail_image_url, psh.default_zoom_image_url AS zoom_image_url 
        FROM   	product_header ph,
				product_sku_header psh
        WHERE   psh.product_id = ph.product_id
		AND		psh.default_sku = 1 ", v_string_product_code, v_string_product_name, v_string_product_description, v_string_status_code);
          
        PREPARE stmnt3 FROM  @v_sql_statement;
        EXECUTE stmnt3;
        
      END IF;         
      
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

