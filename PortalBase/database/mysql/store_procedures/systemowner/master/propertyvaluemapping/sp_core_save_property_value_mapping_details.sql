DELIMITER $$

DROP PROCEDURE IF EXISTS sp_core_save_property_value_mapping_details $$
CREATE PROCEDURE sp_core_save_property_value_mapping_details
(
	IN p_property_value_mapping_id INT(10),
	IN p_size_id INT(10),
	IN p_property_value VARCHAR(60),
	IN p_unit_id INT(10),
	IN p_is_active TINYINT(1),
	IN p_user_login VARCHAR(50),
	IN p_modified_date VARCHAR(25),
	OUT p_error_code VARCHAR(50),
	OUT p_error_message VARCHAR(500)
)

MODIFIES SQL DATA
DETERMINISTIC

BEGIN
   DECLARE v_modified_date DATETIME ;
   DECLARE v_property_value_mapping_counter INT;
   DECLARE v_user_login VARCHAR(50);
   DECLARE v_counter INT;
   DECLARE v_is_active TINYINT(1);
   DECLARE v_last_inserted_id INT(10);
   
   IF p_property_value_mapping_id IS NULL THEN
   
 	  SELECT  COUNT(1) INTO v_property_value_mapping_counter
      FROM    core_property_value_mapping
      WHERE   size_id = p_size_id
      AND	  property_value = p_property_value
      AND	  unit_id = p_unit_id;              
               
      IF v_property_value_mapping_counter = 0 THEN
         
               INSERT INTO core_property_value_mapping(size_id, property_value, unit_id, is_active, created_by, created_date, modified_by, modified_date) 
				VALUES( p_size_id, p_property_value, p_unit_id, p_is_active, p_user_login, NOW(), p_user_login, NOW());
				
				SELECT LAST_INSERT_ID() INTO v_last_inserted_id;
					   		
  	  ELSE 
  		    SET p_error_code = 'combination_already_exist';
          SET p_error_message = CONCAT( 'Combination of property value and unit is already exist. Please choose different value');  
      END IF;	
   ELSE
	     
        SET v_modified_date = DATE_FORMAT(p_modified_date, '%Y-%m-%d %H:%i:%s');   
		  
        SELECT  COUNT(1) INTO v_counter
        FROM    core_property_value_mapping
        WHERE  	property_value_mapping_id = p_property_value_mapping_id 
	    AND 	modified_date = v_modified_date;         
        
        IF v_counter > 0 THEN
        
        
        		SELECT  COUNT(1) INTO v_property_value_mapping_counter
    		    FROM    core_property_value_mapping
    		    WHERE   size_id = p_size_id
      			AND	  property_value = p_property_value
      			AND	  unit_id = p_unit_id
      			AND	  property_value_mapping_id != p_property_value_mapping_id;  
    		    
    		    IF v_property_value_mapping_counter = 0 THEN
        
	              		UPDATE  core_property_value_mapping
					      SET     size_id = p_size_id,
					              property_value = p_property_value,
					              unit_id = p_unit_id,
					              is_active = p_is_active,
					              modified_by = p_user_login,
					              modified_date = NOW()
					      WHERE   property_value_mapping_id = p_property_value_mapping_id;
					      
					      SET v_last_inserted_id = p_property_value_mapping_id;
					      
				     
				  ELSE
				  
				  	SET p_error_code = 'combination_already_exist';
	  				SET p_error_message = CONCAT('Combination of property value and unit is already exist. Please choose different value');
	  			
	  			END IF;
				  
      ELSE
  		   		SELECT  modified_by INTO v_user_login 
  		    	FROM    core_property_value_mapping
  		    	WHERE   property_value_mapping_id = p_property_value_mapping_id;
  		            
  		    	SET p_error_code = 'no_data_update_db_excep_msg';
  		    	SET p_error_message = CONCAT( 'Property Value Mapping data has been updated by (',v_user_login,'). Please search for the updated record to view or make changes.');    
  	  	   END IF;
     END IF; 
	
	 SELECT  modified_date INTO v_modified_date
     FROM    core_property_value_mapping
     WHERE   property_value_mapping_id = v_last_inserted_id;
    							
	   SELECT v_modified_date AS modified_date, p_error_code, p_error_message, v_last_inserted_id AS property_value_mapping_id;
     	 
END$$

DELIMITER ;
