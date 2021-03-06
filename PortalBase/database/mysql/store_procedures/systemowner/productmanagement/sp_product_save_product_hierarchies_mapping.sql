DELIMITER $$

DROP PROCEDURE IF EXISTS sp_product_save_product_hierarchies_mapping $$
CREATE  PROCEDURE sp_product_save_product_hierarchies_mapping(
IN p_product_id INT(10),
IN p_hierarchies_parse_srting TEXT,
IN p_user_login VARCHAR(50),
IN p_modified_date VARCHAR(25),
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
                            
DECLARE v_product_hierarchy_mapping_id  INT(10);
DECLARE v_hierarchy_id  				INT(10);
DECLARE v_category_level_1_id  			INT(10);
DECLARE v_category_level_2_id  			INT(10);
DECLARE v_category_level_3_id  			INT(10);
DECLARE v_category_level_4_id  			INT(10);
DECLARE v_deleteYN						TINYINT(1);

DECLARE v_modified_date 				DATETIME ;
DECLARE v_counter						INT;
DECLARE v_user_login					VARCHAR(50);
DECLARE v_last_inserted_id              INT(10);

SET v_modified_date = DATE_FORMAT(p_modified_date, '%Y-%m-%d %H:%i:%s');

SELECT  COUNT(1) INTO v_counter
FROM  	product_header 
WHERE   product_id = p_product_id
AND     modified_date  = v_modified_date;            
          
IF v_counter > 0 THEN        	      
    
	IF p_product_id IS NOT NULL THEN
		
		outer_parse: WHILE v_current_index > 0
		DO
		  
			SET v_previous_index = v_current_index;
			SELECT LOCATE(';', p_hierarchies_parse_srting, v_current_index) INTO v_current_index;
			IF v_current_index > 0
			THEN
				SET v_start_point = v_previous_index;
				SET v_end_point = v_current_index - v_start_point;
				SET v_current_index = v_current_index + 1;	
			ELSE
				SET v_start_point = v_previous_index;
				SET v_end_point = LENGTH(p_hierarchies_parse_srting);
			END IF;
	
			SET v_current_node = SUBSTR(p_hierarchies_parse_srting,v_start_point,v_end_point);
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
	                        	     SET v_product_hierarchy_mapping_id = v_inner_current_node1;	                        	     	
	                      		 ELSEIF v_column_sequence_number = 2 THEN 
	                        	     SET v_hierarchy_id = v_inner_current_node1;
	                        	 ELSEIF v_column_sequence_number = 3 THEN
	                        	 	 SET v_category_level_1_id = v_inner_current_node1;
	                        	 ELSEIF v_column_sequence_number = 4 THEN
	                        	 	 SET v_category_level_2_id = v_inner_current_node1;
	                        	 ELSEIF v_column_sequence_number = 5 THEN
	                        	 	 SET v_category_level_3_id = v_inner_current_node1;
	                        	 ELSEIF v_column_sequence_number = 6 THEN
	                        	 	 SET v_category_level_4_id = v_inner_current_node1;
	                        	 ELSEIF v_column_sequence_number = 7 THEN
	                        	 	 SET v_deleteYN = v_inner_current_node1;
	                     		 END IF;   
	                     	                     
	                      	SET v_column_sequence_number = v_column_sequence_number +1;   
	                       	END WHILE inner_parse1;
	                       
	                        SET v_inner_current_index1 = 1;
	                        	                        
	                        IF v_product_hierarchy_mapping_id IS NOT NULL THEN 	                        		                        	
		                        	
	                        	IF v_deleteYN = 1 THEN 
	                        		
		                        	DELETE 	FROM product_hierarchy_category_mapping
		                        	WHERE 	product_hierarchy_category_mapping_id = v_product_hierarchy_mapping_id;
		                        	
	                        	ELSE
	                        		
	                        		UPDATE	product_hierarchy_category_mapping
			                        SET 	product_id = p_product_id,
			                        		hierarchy_id = v_hierarchy_id,
			                        		category_level_1 = v_category_level_1_id,
			                        		category_level_2 = v_category_level_2_id,
			                        		category_level_3 = v_category_level_3_id,
			                        		category_level_4 = v_category_level_4_id,
			                        		modified_by = p_user_login,
			                        		modified_date = NOW()
			                        WHERE 	product_hierarchy_category_mapping_id = v_product_hierarchy_mapping_id;
	                        	
	                        	END IF;
	                        ELSE
	                        
		                        INSERT INTO product_hierarchy_category_mapping   
		                        (   product_id, 
		                        	hierarchy_id, 
		                        	category_level_1, 
		                        	category_level_2, 
		                        	category_level_3, 
		                        	category_level_4, 
		                        	created_by,
		                        	created_date,
		                        	modified_by,
		                        	modified_date    
		                        )
		                        VALUES
		                        (   p_product_id,
		                            v_hierarchy_id,
		                            v_category_level_1_id,
		                            v_category_level_2_id,
		                            v_category_level_3_id,
		                            v_category_level_4_id,
		                            p_user_login,
		                            NOW(),
		                            p_user_login,
		                            NOW()
		                        );                         	
								
		                        SELECT LAST_INSERT_ID() INTO v_last_inserted_id;
		                        		                        
	                    	END IF;	                    		                    	
						    
	                    	SET v_last_inserted_id = NULL;
	                    	SET v_product_hierarchy_mapping_id = NULL;
	                        SET v_hierarchy_id = NULL;
	                        SET v_category_level_1_id = NULL;
	                        SET v_category_level_2_id = NULL;
	                        SET v_category_level_3_id = NULL;
	                        SET v_category_level_4_id = NULL;
	                        SET v_deleteYN = NULL;
	                      
	                    END WHILE inner_parse;
	                      
	       SET v_inner_current_index = 1;              
	                                          
	  	END WHILE outer_parse;
		
		UPDATE 	product_header
		SET 	modified_by = p_user_login,
				modified_date = NOW()
		WHERE   product_id = p_product_id;
	
	END IF; 
	
ELSE
        
	SELECT  modified_by INTO v_user_login 
	FROM    product_header
	WHERE   product_id = p_product_id;
	            
	SET p_error_code = 'no_data_update_db_excep_msg';
	SET p_error_message = CONCAT( 'Product has been updated by (',v_user_login,'). Please search for the updated product to view or make changes.');
END IF;             
	   
SELECT  modified_date INTO v_modified_date
FROM    product_header
WHERE   product_id = p_product_id;
    
SELECT p_product_id, v_modified_date AS modified_date, p_error_code, p_error_message;
	        
END $$


/*
call sp_product_save_product_hierarchies_mapping(
1, -- p_product_id INT(10),
'3~5~1;~6~1', --  p_hierarchies_parse_srting TEXT,
'TSD', -- p_user_login VARCHAR(50),
'2012-07-16 16:30:56', -- IN p_modified_date VARCHAR(25),
@p_error_code,
@p_error_message
);

*/
DELIMITER ;

