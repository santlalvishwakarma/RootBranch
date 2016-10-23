DELIMITER $$

DROP PROCEDURE IF EXISTS sp_product_save_product_details $$
CREATE PROCEDURE sp_product_save_product_details(
IN p_product_id INT(10), 
IN p_product_code VARCHAR(25),
IN p_product_name VARCHAR(60),
IN p_product_description VARCHAR(255),
IN p_is_active TINYINT(1),
IN p_user_login VARCHAR(50),
IN p_modified_date VARCHAR(25),
OUT p_last_inserted_id INT(10),
OUT p_error_code VARCHAR(50),
OUT p_error_message VARCHAR(500)
)

MODIFIES SQL DATA
DETERMINISTIC

BEGIN
    
    DECLARE v_last_inserted_id INT(10);       
    DECLARE v_is_active TINYINT(1);
    DECLARE v_modified_date DATETIME ;
    DECLARE v_counter INT;
    DECLARE v_product_code_counter INT;
    DECLARE v_product_name_counter INT;
    DECLARE v_user_login VARCHAR(50);
    DECLARE v_status_code VARCHAR(25);
    
    SET v_modified_date = DATE_FORMAT(p_modified_date, '%Y-%m-%d %H:%i:%s');
    
	IF p_product_id IS NOT NULL THEN
	    	
	    	SELECT  COUNT(1) INTO v_counter
		    FROM 	product_header
		    WHERE   product_id = p_product_id
		    AND     modified_date  = v_modified_date;          
		      
		    IF v_counter > 0 THEN  
		    
			        SELECT  COUNT(1) INTO v_product_name_counter
			        FROM    product_header
			        WHERE   product_name = p_product_name
			        AND     product_id != p_product_id;
		        	
			        IF v_product_name_counter = 0 THEN
		        	    
			            UPDATE  product_header
			            SET     product_name = p_product_name, 
			            		product_description = p_product_description, 
			            		is_active = p_is_active, 
			                    modified_by = p_user_login,
			                    modified_date = NOW()
			            WHERE   product_id = p_product_id;
			            
				ELSE
		    		SET p_error_code = 'uk_prod_name_cd';
	            	SET p_error_message = NULL; 
	            	-- CONCAT('Entered product name already exist. Please check.');
	            
		    	END IF;       			               
			ELSE
	        
				SELECT  modified_by INTO v_user_login 
				FROM    product_header
			    WHERE   product_id = p_product_id;
			            
			    SET p_error_code = 'no_data_update_db_excep_msg';
			    SET p_error_message = CONCAT( 'Product has been updated by (',v_user_login,'). Please search for the updated product to view or make changes.');
			         
			END IF;
	
	    ELSE
	        
	        SELECT  COUNT(1) INTO v_product_name_counter
	        FROM    product_header
	        WHERE   product_name = p_product_name;
	        -- AND 	product_version = 1
	        -- AND		status_code != v_inactive_status_code;
        	
	        IF v_product_name_counter = 0 THEN 
		        
	            INSERT INTO product_header
	            (   product_code, product_name, product_description, is_active,created_by, created_date, modified_by, modified_date
	            )  
	            VALUES
	            (   p_product_code, p_product_name, p_product_description, p_is_active, 
	                p_user_login, NOW(), p_user_login, NOW()
	            );
	            
	            SELECT LAST_INSERT_ID() INTO v_last_inserted_id;
	            SET p_last_inserted_id = v_last_inserted_id;
	
	    	ELSE
	    		SET p_error_code = 'uk_prod_name_cd';
            	SET p_error_message = NULL; 
            	-- CONCAT('Entered product name already exist. Please check.');
            
	    	END IF;
	    END IF; 	
	
    SELECT  modified_date INTO v_modified_date
    FROM    product_header
    WHERE   product_id = IFNULL(p_last_inserted_id,p_product_id);
        
    SELECT IFNULL(p_last_inserted_id,p_product_id) AS product_id, v_modified_date AS modified_date, p_error_code, p_error_message;
END $$

/*
CALL sp_product_save_product_details(
6, -- IN p_product_id INT(10),
'product_ani', -- IN p_product_code VARCHAR(25),
'product_ani', -- IN p_product_name VARCHAR(60),
'product_ani_niraj', -- IN p_product_description VARCHAR(255),
'GMS', -- IN p_uom_code VARCHAR(25),
NULL, -- IN p_uom_category_code VARCHAR(25),
21, -- IN p_allocation_based INT(10),
1, -- IN p_is_active TINYINT(1),
1, -- IN p_is_auto_replenish TINYINT(1),
25, -- IN p_pricing_model VARCHAR(255),
'Code~GMS~KGS',
'tsd',
'2012-07-23 21:16:02',
@p_last_inserted_id,
@p_error_code,
@p_error_message);
*/

DELIMITER ;

