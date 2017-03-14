DELIMITER $$

DROP PROCEDURE IF EXISTS sp_sku_save_material_property_mapping $$
CREATE  PROCEDURE sp_sku_save_material_property_mapping(
IN p_product_id INT(10),
IN p_product_sku_id INT(10),
IN p_user_login VARCHAR(50),
IN p_material_parse_srting TEXT,
OUT p_error_code VARCHAR(50),
OUT p_error_message VARCHAR(500)
)

MODIFIES SQL DATA
DETERMINISTIC
BEGIN
	
	
DECLARE v_current_node                  TEXT;
DECLARE v_current_index                 INT(4) DEFAULT 1;
DECLARE v_previous_index                INT(4) DEFAULT 1;
DECLARE v_start_point                   INT(4);
DECLARE v_end_point                     INT(4);
DECLARE v_inner_current_node            TEXT;
DECLARE v_inner_current_index           INT(4) DEFAULT 1;
DECLARE v_inner_previous_index          INT(4) DEFAULT 1;
DECLARE v_inner_start_point             INT(4);
DECLARE v_inner_end_point               INT(4);
DECLARE v_inner_current_node1           TEXT;
DECLARE v_inner_current_index1          INT(4) DEFAULT 1;
DECLARE v_inner_previous_index1         INT(4) DEFAULT 1;
DECLARE v_inner_start_point1            INT(4);
DECLARE v_inner_end_point1              INT(4);
DECLARE	v_row_id                        INT(4);
DECLARE v_property_row_VALUES           TEXT;
DECLARE v_column_sequence_number        INT(10);
                            
DECLARE v_product_sku_material_Mapping_id  INT(10);
DECLARE v_material_id						INT(10);
DECLARE v_deleteYN						TINYINT(1);

DECLARE v_modified_date 				DATETIME ;
DECLARE v_counter						INT;
DECLARE v_user_login					VARCHAR(50);
DECLARE v_last_inserted_id              INT(10);




outer_parse: WHILE v_current_index > 0
		DO
		  
			SET v_previous_index = v_current_index;
			SELECT LOCATE(';', p_material_parse_srting, v_current_index) INTO v_current_index;
			IF v_current_index > 0
			THEN
				SET v_start_point = v_previous_index;
				SET v_end_point = v_current_index - v_start_point;
				SET v_current_index = v_current_index + 1;	
			ELSE
				SET v_start_point = v_previous_index;
				SET v_end_point = LENGTH(p_material_parse_srting);
			END IF;
	
			SET v_current_node = SUBSTR(p_material_parse_srting,v_start_point,v_end_point);
	     SET v_row_id = v_current_index ;
	     SET  v_property_row_VALUES = v_current_node;
	     
	        inner_parse: WHILE v_inner_current_index > 0
	        DO
	          SET v_inner_previous_index = v_inner_current_index;
	          SELECT LOCATE(';', v_current_node, v_inner_current_index) INTO v_inner_current_index;
	          -- select v_current_node;
	          IF v_inner_current_index > 0
	          THEN
	            SET v_inner_start_point = v_inner_previous_index;
	            SET v_inner_end_point = v_inner_current_index - v_inner_start_point;
	            SET v_inner_current_index = v_inner_current_index + 1;
	          ELSE
	            SET v_inner_start_point = v_inner_previous_index;
	            SET v_inner_end_point = LENGTH(v_current_node);
	          END IF;
	      
	          SET v_inner_current_node = SUBSTR(v_current_node,v_inner_start_point,v_inner_end_point);
	          
	            SET  v_property_row_VALUES = v_inner_current_node;
	          IF v_inner_current_node = '' OR v_inner_current_node = 'null' THEN
	            SET v_inner_current_node = NULL;
	          END IF;
	          
	          SET v_column_sequence_number = 1;
	          
	            inner_parse1: WHILE v_inner_current_index1> 0
	                    DO
	                      SET v_inner_previous_index1 = v_inner_current_index1;
	                      SELECT LOCATE('~', v_inner_current_node, v_inner_current_index1) INTO v_inner_current_index1;
	                      IF v_inner_current_index1 > 0
	                      
	                      THEN
	                        SET v_inner_start_point1 = v_inner_previous_index1;
	                        SET v_inner_end_point1 = v_inner_current_index1 - v_inner_start_point1;
	                        SET v_inner_current_index1 = v_inner_current_index1 + 1;
	                      ELSE
	                        SET v_inner_start_point1 = v_inner_previous_index1;
	                        SET v_inner_end_point1 = LENGTH(v_inner_current_node);
	                      END IF;
	                    
	                      SET v_inner_current_node1 = SUBSTR(v_inner_current_node,v_inner_start_point1,v_inner_end_point1);
	                      IF v_inner_current_node1 = '' OR v_inner_current_node1 = 'null' THEN
	                        SET v_inner_current_node1 = NULL;
	                      END IF;
	                      
	                       		 IF v_column_sequence_number = 1 THEN 
	                        	     SET v_product_sku_material_Mapping_id = v_inner_current_node1;	                        	     	
	                      		 ELSEIF v_column_sequence_number = 2 THEN 
	                        	     SET v_material_id = v_inner_current_node1;
	                        	 ELSEIF v_column_sequence_number = 3 THEN 
	                        	     SET v_modified_date = v_inner_current_node1;
	                        	 ELSEIF v_column_sequence_number = 4 THEN
	                        	 	 SET v_deleteYN = v_inner_current_node1;
	                     		 END IF;   
	                     	                     
	                      	SET v_column_sequence_number = v_column_sequence_number +1;   
	                       	END WHILE inner_parse1;
	                       
	                        SET v_inner_current_index1 = 1;
	                        
	                        IF v_product_sku_material_Mapping_id IS NOT NULL THEN 
	                        
		                        SELECT  COUNT(1) INTO v_counter
								FROM  	product_sku_material_mapping 
								WHERE   product_sku_material_mapping_id = v_product_sku_material_Mapping_id
								AND     modified_date  = v_modified_date; 
								
								IF v_counter > 0 THEN
			                        	
		                        	IF v_deleteYN = 1 THEN 
		                        		
			                        	DELETE  FROM product_sku_material_mapping
			                        	WHERE 	product_sku_material_mapping_id = v_product_sku_material_Mapping_id;
			                        	
		                        	ELSE
		                        	
		                        		UPDATE	product_sku_material_mapping
				                        SET 	material_id = v_material_id,
				                        		is_active = 1,
				                        		modified_by = p_user_login,
				                        		modified_date = NOW()
				                        WHERE 	product_sku_material_mapping_id = v_product_sku_material_Mapping_id;
		                        	
		                        	END IF;
		                        	
		                        	
		                          ELSE
		                          
			                          SELECT  modified_by INTO v_user_login 
									  FROM    product_sku_material_mapping
									  WHERE   product_sku_material_mapping_id = v_product_sku_material_Mapping_id;
									            
									  SET p_error_code = 'no_data_update_db_excep_msg';
									  SET p_error_message = CONCAT( 'Sku material property has been updated by (',v_user_login,'). Please search for the updated record to view or make changes.');
		                        	
		                          END IF;
	                          
	                        ELSE
	                        
	                        	IF v_deleteYN = 0 THEN 
	                        
		                        	INSERT INTO product_sku_material_mapping
		                        	(product_id, product_sku_id, material_id, is_active, created_by, created_date, modified_by, modified_date)
		                        	values(p_product_id, p_product_sku_id, v_material_id, 1, p_user_login, NOW(), p_user_login, NOW());
	                        	END IF;
	                    	END IF;	     
	                    	
	                    	SET v_product_sku_material_Mapping_id = NULL;
	                        SET v_material_id = NULL;
	                        SET v_deleteYN = NULL;
	                        SET v_modified_date = NULL;
	                        SET v_counter = NULL;
	                      
	                    END WHILE inner_parse;
	                      
	       SET v_inner_current_index = 1;              
	                                          
	  	END WHILE outer_parse;

SELECT p_error_code, p_error_message;
	        
END $$


/*
call sp_product_save_product_hierarchies_mapping(
1, -- p_product_id INT(10),
'3~5~1;~6~1', --  p_material_parse_srting TEXT,
'TSD', -- p_user_login VARCHAR(50),
'2012-07-16 16:30:56', -- IN p_modified_date VARCHAR(25),
@p_error_code,
@p_error_message
);

*/
DELIMITER ;

