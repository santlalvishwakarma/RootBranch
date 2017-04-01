DELIMITER $$

DROP PROCEDURE IF EXISTS sp_save_category_size_mapping $$
CREATE PROCEDURE sp_save_category_size_mapping
(
	IN p_category_id INT(10),
	IN p_category_size_mapping_id INT(10),
	IN p_size_value_1 FLOAT(15,3),
	IN p_size_value_2 FLOAT(15,3),
	IN p_is_active TINYINT(1),
	IN p_user_login VARCHAR(50),
	IN p_modified_date VARCHAR(25),
	IN p_unit_id INT(10),
	OUT p_error_code VARCHAR(50),
	OUT p_error_message VARCHAR(500)
)

MODIFIES SQL DATA
DETERMINISTIC

BEGIN
	
   DECLARE v_modified_date DATETIME ;
   DECLARE v_user_login VARCHAR(50);
   DECLARE v_counter INT;
   DECLARE v_is_active TINYINT(1);
   DECLARE v_last_inserted_id INT(10);
   
   
    IF p_category_size_mapping_id IS NULL THEN
    
    	INSERT INTO category_size_mapping(category_id, size_value_1, size_value_2, unit_id, is_active, created_by, created_date, modified_by, modified_date) 
				VALUES( p_category_id, p_size_value_1, p_size_value_2, p_unit_id, p_is_active, p_user_login, NOW(), p_user_login, NOW());
   
    ELSE
	     
        SET v_modified_date = DATE_FORMAT(p_modified_date, '%Y-%m-%d %H:%i:%s');   
		  
        SELECT  COUNT(1) INTO v_counter
        FROM    category_size_mapping
        WHERE  	category_size_mapping_id = p_category_size_mapping_id 
	    AND 	modified_date = v_modified_date;         
        
        IF v_counter > 0 THEN
        
        
		          UPDATE  category_size_mapping
			      SET     size_value_1 = size_value_1,
			      		  size_value_2 = size_value_2,
			      		  unit_id = p_unit_id,
			              is_active = p_is_active,
			              modified_by = p_user_login,
			              modified_date = NOW()
			      WHERE   category_size_mapping_id = p_category_size_mapping_id;
			      
			      SET v_last_inserted_id = p_category_size_mapping_id;
				  
      ELSE
  		   		SELECT  modified_by INTO v_user_login 
  		    	FROM    category_size_mapping
  		    	WHERE   category_size_mapping_id = p_category_size_mapping_id;
  		            
  		    	SET p_error_code = 'no_data_update_db_excep_msg';
  		    	SET p_error_message = CONCAT( 'Category Size Mapping data has been updated by (',v_user_login,'). Please search for the updated record to view or make changes.');    
  	  	   END IF;
     END IF; 
     
	 SELECT  modified_date INTO v_modified_date
     FROM    category_size_mapping
     WHERE   category_size_mapping_id = v_last_inserted_id;
    							
	 SELECT v_modified_date AS modified_date, p_error_code, p_error_message, v_last_inserted_id AS category_size_mapping_id;
	
	
	
END$$

DELIMITER ;


