DELIMITER $$

DROP PROCEDURE IF EXISTS sp_sku_save_property_mapping $$
CREATE  PROCEDURE sp_sku_save_property_mapping(
IN p_product_id INT(10),
IN p_product_sku_id INT(10),
IN p_user_login VARCHAR(50),
IN p_modified_date VARCHAR(25),
IN p_size_parse_srting TEXT,
IN p_color_parse_srting TEXT,
IN p_material_parse_srting TEXT,
OUT p_error_code VARCHAR(50),
OUT p_error_message VARCHAR(500)
)

MODIFIES SQL DATA
DETERMINISTIC
BEGIN
	

DECLARE v_modified_date 				DATETIME ;
DECLARE v_counter						INT;
DECLARE v_user_login					VARCHAR(50);
DECLARE v_last_inserted_id              INT(10);
DECLARE v_now 							DATE;

SET v_now = NOW();

IF p_product_sku_id IS NOT NULL THEN

	SELECT  COUNT(1)  INTO v_counter
	FROM  	product_sku_header 
    WHERE   product_sku_id = p_product_sku_id
    AND     modified_date  = p_modified_date; 
    
    IF v_counter > 0 THEN    
		
    	UPDATE 	product_sku_header
		SET 	modified_by = p_user_login,
	    		modified_date = v_now
		WHERE   product_sku_id = p_product_sku_id; 
						    
    ELSE
        
	  	SELECT  modified_by INTO v_user_login 
	    FROM    product_sku_header
	    WHERE   product_sku_id = p_product_sku_id;
	            
	    SET p_error_code = 'no_data_update_db_excep_msg';
	    SET p_error_message = CONCAT( 'SKU has been updated by (',v_user_login,'). Please search for the updated SKU to view or make changes.');
        	    
	END IF;    

END IF; 


IF p_error_code IS NULL THEN 

	IF p_size_parse_srting IS NOT NULL THEN
	
		CALL sp_sku_save_size_property_mapping(p_product_id, p_product_sku_id, p_user_login, p_size_parse_srting, p_error_code, p_error_message);
	
	END IF;
	
	IF p_color_parse_srting IS NOT NULL THEN
	
		CALL sp_sku_save_color_property_mapping(p_product_id, p_product_sku_id, p_user_login, p_color_parse_srting, p_error_code, p_error_message);
	
	END IF;
	
	IF p_material_parse_srting IS NOT NULL THEN
	
		CALL sp_sku_save_material_property_mapping(p_product_id, p_product_sku_id, p_user_login, p_material_parse_srting, p_error_code, p_error_message);
	
	END IF;
	  	
END IF;


SELECT v_now AS modified_date, p_error_code, p_error_message;
  
END $$


DELIMITER ;


	
