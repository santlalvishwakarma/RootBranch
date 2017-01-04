DELIMITER $$

DROP PROCEDURE IF EXISTS sp_core_save_unit_details $$
CREATE PROCEDURE sp_core_save_unit_details
(
	IN p_unit_code VARCHAR(25),
	IN p_unit_name VARCHAR(60),
	IN p_unit_description VARCHAR(255),
	IN p_is_active TINYINT(1),
	IN p_user_login VARCHAR(50),
	IN p_modified_date VARCHAR(25),
	IN p_unit_id INT(10),
	IN p_display_name VARCHAR(25),
	OUT p_error_code VARCHAR(50),
	OUT p_error_message VARCHAR(500)
)

MODIFIES SQL DATA
DETERMINISTIC

BEGIN
   DECLARE v_modified_date DATETIME ;
   DECLARE v_unit_code_counter INT;
   DECLARE v_unit_name_counter INT;
   DECLARE v_user_login VARCHAR(50);
   DECLARE v_counter INT;
   DECLARE v_is_active TINYINT(1);
   DECLARE v_last_inserted_id INT(10);
   
   IF p_unit_id IS NULL THEN
   
 	  SELECT  COUNT(1) INTO v_unit_code_counter
      FROM    core_unit_master
      WHERE   unit_code = p_unit_code;              
               
      IF v_unit_code_counter = 0 THEN
         
         	SELECT  COUNT(1) INTO v_unit_name_counter
        	FROM    core_unit_master
       	 	WHERE   unit_name = p_unit_name;
                
        	IF v_unit_name_counter = 0 THEN
         	    
               INSERT INTO core_unit_master(unit_code, unit_name, unit_description, display_name, is_active, created_by, created_date, modified_by, modified_date) 
				VALUES( p_unit_code,p_unit_name,p_unit_description,p_display_name, 1,p_user_login,NOW(),p_user_login,NOW());
				
				SELECT LAST_INSERT_ID() INTO v_last_inserted_id;
					   		
    		ELSE 
    			  SET p_error_code = 'uk_core_unit_name_cd';
    			  SET p_error_message = CONCAT( 'Unit name  (',p_unit_name,') is already exist please choose different name ');  
    		END IF;
    		
  	  ELSE 
  		    SET p_error_code = 'uk_core_unit_code_cd';
          SET p_error_message = CONCAT( 'Unit code  (',p_unit_code,') is already exist please choose different code ');  
      END IF;	
   ELSE
	     
        SET v_modified_date = DATE_FORMAT(p_modified_date, '%Y-%m-%d %H:%i:%s');   
		  
        SELECT  COUNT(1) INTO v_counter
        FROM    core_unit_master
        WHERE  	unit_id = p_unit_id 
	    AND 	modified_date = v_modified_date;         
        
        IF v_counter > 0 THEN
        
        
        		SELECT  COUNT(1) INTO v_unit_code_counter
    		    FROM    core_unit_master
    		    WHERE   unit_code = p_unit_code
    		    AND 	unit_id != p_unit_id
    		    AND 	is_active = 1;
    		    
    		    IF v_unit_code_counter = 0 THEN
        
	           		SELECT  COUNT(1) INTO v_unit_name_counter
	    		    FROM    core_unit_master
	    		    WHERE   unit_name = p_unit_name
	    		    AND 	unit_id != p_unit_id
	    		    AND 	is_active = 1;
	    		    
	    		    
			        
	    		    IF v_unit_name_counter = 0 THEN
	            	   
	              		UPDATE  core_unit_master
					      SET     unit_name = p_unit_name,
					              unit_description = p_unit_description,
					              is_active = p_is_active,
					              modified_by = p_user_login,
					              unit_code = p_unit_code,
					              display_name = p_display_name,
					              modified_date = NOW()
					      WHERE   unit_id = p_unit_id;
					      
					      SET v_last_inserted_id = p_unit_id;
					      
				     ELSE 
	  				      
	                SET p_error_code = 'uk_core_unit_name_cd';
	  				      SET p_error_message = CONCAT( 'Unit name  (',p_unit_name,') is already exist please choose different name ');
	                  
				     END IF;
				     
				  ELSE
				  
				  	SET p_error_code = 'uk_core_unit_code_cd';
	  				SET p_error_message = CONCAT( 'Unit code  (',p_unit_code,') is already exist please choose different code ');
	  			
	  			END IF;
				  
      ELSE
  		   		SELECT  modified_by INTO v_user_login 
  		    	FROM    core_unit_master
  		    	WHERE   unit_id = p_unit_id;
  		            
  		    	SET p_error_code = 'no_data_update_db_excep_msg';
  		    	SET p_error_message = CONCAT( 'Unit data has been updated by (',v_user_login,'). Please search for the updated unit record to view or make changes.');    
  	  	   END IF;
     END IF; 
	
	 SELECT  modified_date INTO v_modified_date
     FROM    core_unit_master
     WHERE   unit_id = v_last_inserted_id;
    							
	   SELECT v_modified_date AS modified_date, p_error_code, p_error_message, v_last_inserted_id AS unit_id;
     	 
END$$

DELIMITER ;
