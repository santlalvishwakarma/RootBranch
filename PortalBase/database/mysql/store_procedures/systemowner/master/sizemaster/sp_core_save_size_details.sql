DELIMITER $$

DROP PROCEDURE IF EXISTS sp_core_save_size_details $$
CREATE PROCEDURE sp_core_save_size_details
(
	IN p_size_code VARCHAR(25),
	IN p_size_name VARCHAR(60),
	IN p_size_description VARCHAR(255),
	IN p_is_active TINYINT(1),
	IN p_user_login VARCHAR(50),
	IN p_modified_date VARCHAR(25),
	IN p_size_id INT(10),
	OUT p_error_code VARCHAR(50),
	OUT p_error_message VARCHAR(500)
)

MODIFIES SQL DATA
DETERMINISTIC

BEGIN
   DECLARE v_modified_date DATETIME ;
   DECLARE v_size_code_counter INT;
   DECLARE v_size_name_counter INT;
   DECLARE v_user_login VARCHAR(50);
   DECLARE v_counter INT;
   DECLARE v_is_active TINYINT(1);
   DECLARE v_last_inserted_id INT(10);
   
   IF p_size_id IS NULL THEN
   
 	  SELECT  COUNT(1) INTO v_size_code_counter
      FROM    core_size_master
      WHERE   size_code = p_size_code;              
               
      IF v_size_code_counter = 0 THEN
         
         	SELECT  COUNT(1) INTO v_size_name_counter
        	FROM    core_size_master
       	 	WHERE   size_name = p_size_name;
                
        	IF v_size_name_counter = 0 THEN
         	    
               INSERT INTO core_size_master(size_code, size_name, size_description, is_active, created_by, created_date, modified_by, modified_date) 
				VALUES( p_size_code,p_size_name,p_size_description, 1,p_user_login,NOW(),p_user_login,NOW());
				
				SELECT LAST_INSERT_ID() INTO v_last_inserted_id;
					   		
    		ELSE 
    			  SET p_error_code = 'uk_core_size_name_cd';
    			  SET p_error_message = CONCAT( 'Size name  (',p_size_name,') is already exist please choose different name ');  
    		END IF;
    		
  	  ELSE 
  		    SET p_error_code = 'uk_core_size_code_cd';
          SET p_error_message = CONCAT( 'Size code  (',p_size_code,') is already exist please choose different code ');  
      END IF;	
   ELSE
	     
        SET v_modified_date = DATE_FORMAT(p_modified_date, '%Y-%m-%d %H:%i:%s');   
		  
        SELECT  COUNT(1) INTO v_counter
        FROM    core_size_master
        WHERE  	size_id = p_size_id 
	    AND 	modified_date = v_modified_date;         
        
        IF v_counter > 0 THEN
        
        
        		SELECT  COUNT(1) INTO v_size_code_counter
    		    FROM    core_size_master
    		    WHERE   size_code = p_size_code
    		    AND 	size_id != p_size_id
    		    AND 	is_active = 1;
    		    
    		    IF v_size_code_counter = 0 THEN
        
	           		SELECT  COUNT(1) INTO v_size_name_counter
	    		    FROM    core_size_master
	    		    WHERE   size_name = p_size_name
	    		    AND 	size_id != p_size_id
	    		    AND 	is_active = 1;
	    		    
	    		    
			        
	    		    IF v_size_name_counter = 0 THEN
	            	   
	              		UPDATE  core_size_master
					      SET     size_name = p_size_name,
					              size_description = p_size_description,
					              is_active = p_is_active,
					              modified_by = p_user_login,
					              size_code = p_size_code,
					              modified_date = NOW()
					      WHERE   size_id = p_size_id;
					      
					      SET v_last_inserted_id = p_size_id;
					      
				     ELSE 
	  				      
	                SET p_error_code = 'uk_core_size_name_cd';
	  				      SET p_error_message = CONCAT( 'Size name  (',p_size_name,') is already exist please choose different name ');
	                  
				     END IF;
				     
				  ELSE
				  
				  	SET p_error_code = 'uk_core_size_code_cd';
	  				SET p_error_message = CONCAT( 'Size code  (',p_size_code,') is already exist please choose different code ');
	  			
	  			END IF;
				  
      ELSE
  		   		SELECT  modified_by INTO v_user_login 
  		    	FROM    core_size_master
  		    	WHERE   size_id = p_size_id;
  		            
  		    	SET p_error_code = 'no_data_update_db_excep_msg';
  		    	SET p_error_message = CONCAT( 'Size data has been updated by (',v_user_login,'). Please search for the updated Size record to view or make changes.');    
  	  	   END IF;
     END IF; 
	
	 SELECT  modified_date INTO v_modified_date
     FROM    core_size_master
     WHERE   size_id = v_last_inserted_id;
    							
	   SELECT v_modified_date AS modified_date, p_error_code, p_error_message, v_last_inserted_id AS size_id;
     	 
END$$

DELIMITER ;
