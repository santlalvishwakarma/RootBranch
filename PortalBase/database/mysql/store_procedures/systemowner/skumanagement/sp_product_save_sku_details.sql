DELIMITER $$

DROP PROCEDURE IF EXISTS sp_product_save_sku_details $$
CREATE PROCEDURE sp_product_save_sku_details(
IN p_product_sku_id INT(10),
IN p_product_id INT(10), 
IN p_sku_name VARCHAR(60),
IN p_is_active TINYINT(1),
IN p_default_sku TINYINT(1),
IN p_user_login VARCHAR(50),
IN p_modified_date VARCHAR(25),
IN p_sku_description VARCHAR(4000),
IN p_base_price FLOAT(15,3),
IN p_discount_amount FLOAT(15,3),
IN p_discount_percent FLOAT(15,3),
IN p_final_base_price FLOAT(15,3),
IN p_sku_code VARCHAR(25),
IN p_sku_seo_title VARCHAR(4000),
IN p_sku_seo_keyword VARCHAR(4000),
IN p_sku_seo_description VARCHAR(4000),
OUT p_last_inserted_id INT(10),
OUT p_error_code VARCHAR(50),
OUT p_error_message VARCHAR(500)
)

MODIFIES SQL DATA
DETERMINISTIC

BEGIN
    
    DECLARE v_last_inserted_id INT(10);       
    DECLARE v_is_active TINYINT(1);
    DECLARE v_product_sku_activation_history_id INT(10); 
    DECLARE v_modified_date DATETIME ;
    DECLARE v_counter INT;
    DECLARE v_sku_code_counter INT;
    DECLARE v_sku_name_counter INT;
    DECLARE v_user_login VARCHAR(50);
    DECLARE v_product_code VARCHAR(25);
    DECLARE v_sku_code VARCHAR(30);
    DECLARE v_transaction_code VARCHAR(25) DEFAULT 'SKU_CREATE';
    DECLARE v_transaction_code_sku_inactive VARCHAR(25) DEFAULT 'SKU_INACTIVE';
    DECLARE v_inactive_status_code VARCHAR(25);
    DECLARE v_status_code VARCHAR(25);
    
    DECLARE v_sku_sequence_number INT(10);
	DECLARE v_sku_sequence_number_1 VARCHAR(5);
	
	DECLARE v_sku_seq_no VARCHAR(5);
	DECLARE v_properties_combination_description VARCHAR(4000);
	DECLARE v_avai_master_sku INT;
	DECLARE v_sku_description VARCHAR(4000);
	
	DECLARE v_product_is_active TINYINT(1);
	
	DECLARE v_sku_id INT(10);
	
    SET v_modified_date = DATE_FORMAT(p_modified_date, '%Y-%m-%d %H:%i:%s');                      	    
	
	IF p_product_sku_id IS NOT NULL THEN	    				

	    	SELECT  COUNT(1), is_active INTO v_counter, v_is_active 
		    FROM 	product_sku_header
		    WHERE   product_sku_id = p_product_sku_id
		    AND     modified_date  = v_modified_date;    
		    
		    IF v_counter > 0 THEN
		    
		    	SELECT  COUNT(1) INTO v_sku_name_counter
		        FROM    product_sku_header
		        WHERE   sku_name = p_sku_name
		        AND     product_sku_id != p_product_sku_id
		        AND     product_id = p_product_id
		        AND		is_active = 1;
		        
		         IF v_sku_name_counter = 0 THEN
		         
		         	IF p_default_sku = 1 THEN
							
						SELECT	COUNT(1), product_sku_id INTO v_avai_master_sku, v_sku_id
						FROM	product_sku_header
						WHERE 	product_id = p_product_id
						AND 	default_sku = 1
						AND 	product_sku_id != p_product_sku_id
						AND		is_active = 1;
						
					ELSE
					
						SET v_avai_master_sku = 0;
						
					END IF;
					
					IF v_avai_master_sku > 0 THEN
								
						UPDATE  product_sku_header
						SET		default_sku = 0
						WHERE	product_id = p_product_id
						AND 	product_sku_id = v_sku_id
						AND		is_active = 1;
					
					ELSE
					
						UPDATE  product_sku_header
			            SET     sku_name = p_sku_name,
			            		sku_code = p_sku_code, 			            
			            		is_active = p_is_active, 
			            		default_sku = p_default_sku,
			            		sku_description = p_sku_description,
			            		seo_title = p_sku_seo_title,
			            		seo_keyword = p_sku_seo_title,
			            		seo_description = p_sku_seo_description,
			            		product_id = p_product_id,
			            		base_price = p_base_price,
			            		discount_amount = p_discount_amount,
			            		discount_percent = p_discount_percent,
			            		final_base_price = p_final_base_price,
			                    modified_by = p_user_login,
			                    modified_date = NOW()
			            WHERE   product_sku_id = p_product_sku_id;
					
					
					END IF;
		         
		         ELSE
		         
		         	SET p_error_code = 'uk_sku_name_cd';
	            	SET p_error_message = NULL; 
		         
		         END IF;
		         
		    ELSE
		    
		    SELECT  modified_by INTO v_user_login 
			FROM    product_sku_header
		    WHERE   product_sku_id = p_product_sku_id;
		            
		    SET p_error_code = 'no_data_update_db_excep_msg';
		    SET p_error_message = CONCAT( 'SKU has been updated by (',v_user_login,'). Please search for the updated SKU to view or make changes.');
		    
		    END IF;
	
	    ELSE

	    	SELECT  COUNT(1) INTO v_sku_code_counter
	        FROM    product_sku_header
	        WHERE   sku_code = v_sku_code
	        AND 	is_active = 1
	        AND     product_id = p_product_id;
	        
	        IF v_sku_code_counter = 0 THEN
	        	
	        	SELECT  COUNT(1) INTO v_sku_name_counter
		        FROM    product_sku_header
		        WHERE   sku_name = p_sku_name
		        AND 	is_active = 1
		        AND     product_id = p_product_id;
		        
		         IF v_sku_name_counter = 0 THEN 
		         
		         	INSERT INTO product_sku_header
		            (  product_id, sku_code, sku_name, sku_description, sku_property_text, seo_title, seo_keyword, seo_description, 
		            	base_price, discount_amount, discount_percent, final_base_price, default_sku, is_active, created_by, created_date, modified_by, modified_date
		            )  
		            VALUES
		            (   p_product_id, p_sku_code, p_sku_name, p_sku_description, NULL, p_sku_seo_title, p_sku_seo_keyword, p_sku_seo_description, p_base_price, p_discount_amount, p_discount_percent,
		            	p_final_base_price, p_default_sku, p_is_active, p_user_login, NOW(), p_user_login, NOW()
		            );
		            
		       		SELECT LAST_INSERT_ID() INTO v_last_inserted_id;
		            SET p_last_inserted_id = v_last_inserted_id;
		         	
		         ELSE
		         	
		         	SET p_error_code = 'uk_sku_name_cd';
	            	SET p_error_message = NULL; 
		         
		         END IF;
		         
	        
	        ELSE
	        
	         SET p_error_code = 'uk_sku_code_cd';
	         SET p_error_message = NULL; 
	        
	        END IF;
	    	 
	    END IF; 	
	
    SELECT  modified_date INTO v_modified_date
    FROM    product_sku_header
    WHERE   product_sku_id = IFNULL(p_last_inserted_id,p_product_sku_id);
        
    SELECT IFNULL(p_last_inserted_id,p_product_sku_id) AS product_sku_id, v_modified_date AS modified_date, p_error_code, p_error_message;
END $$

/*
CALL sp_product_save_sku_details(
null, --  p_product_sku_id INT(10),
1, --  p_product_id INT(10), 
'sku name', -- p_sku_name VARCHAR(60),
1, --  p_is_active TINYINT(1),
1, --  p_default_sku TINYINT(1),
'tsd', --  p_user_login VARCHAR(50),
now(), --  p_modified_date VARCHAR(25),
'COMMENTS', -- p_sku_comments VARCHAR(4000),
@p_last_inserted_id,
@p_error_code,
@p_error_message
);
*/

DELIMITER ;

