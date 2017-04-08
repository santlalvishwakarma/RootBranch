DELIMITER $$

DROP PROCEDURE IF EXISTS sp_core_save_guest_user_master_details $$
CREATE PROCEDURE sp_core_save_guest_user_master_details
(
	IN p_guest_user_id INT(10),
	IN p_name VARCHAR(60),
	IN p_emailId VARCHAR(50),
	IN p_phone VARCHAR(15),
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
   DECLARE v_guest_user_counter INT;
   DECLARE v_user_login VARCHAR(50);
   DECLARE v_counter INT;
   DECLARE v_is_active TINYINT(1);
   DECLARE v_last_inserted_id INT(10);
   
   IF p_guest_user_id IS NULL THEN
   
 	  SELECT  COUNT(1) INTO v_guest_user_counter
      FROM    core_guest_master
      WHERE   name = p_name
      AND	  email_id = p_emailId
      AND	  phone_number = p_phone;              
               
      IF v_guest_user_counter = 0 THEN
         
               INSERT INTO core_guest_master(name, email_id, phone_number, is_active, created_by, created_date, modified_by, modified_date) 
				VALUES( p_name, p_emailId, p_phone, p_is_active, p_user_login, NOW(), p_user_login, NOW());
				
				SELECT LAST_INSERT_ID() INTO v_last_inserted_id;
					   		
  	  ELSE 
  		    SET p_error_code = 'combination_already_exist';
          SET p_error_message = CONCAT( 'This user already exist. Please choose different value');  
      END IF;	
   ELSE
	     
        SET v_modified_date = DATE_FORMAT(p_modified_date, '%Y-%m-%d %H:%i:%s');   
		  
        SELECT  COUNT(1) INTO v_counter
        FROM    core_guest_master
        WHERE  	guest_id = p_guest_user_id 
	    AND 	modified_date = v_modified_date;         
        
        IF v_counter > 0 THEN
        
        
      		UPDATE  core_guest_master
		      SET     name = p_name,
		      		  email_id = p_emailId,
		      		  phone_number = p_phone,
		              is_active = p_is_active,
		              modified_by = p_user_login,
		              modified_date = NOW()
		      WHERE   guest_id = p_guest_user_id;
		      
		      SET v_last_inserted_id = p_guest_user_id;
					      
				  
      ELSE
  		   		SELECT  modified_by INTO v_user_login 
  		    	FROM    core_guest_master
  		    	WHERE   guest_id = p_guest_user_id;
  		            
  		    	SET p_error_code = 'no_data_update_db_excep_msg';
  		    	SET p_error_message = CONCAT( 'Guest User data has been updated by (',v_user_login,'). Please search for the updated record to view or make changes.');    
  	  	   END IF;
     END IF; 
	
	 SELECT  modified_date INTO v_modified_date
     FROM    core_guest_master
     WHERE   guest_id = v_last_inserted_id;
    							
	   SELECT v_modified_date AS modified_date, p_error_code, p_error_message, v_last_inserted_id AS guest_id;
     	 
END$$

DELIMITER ;
