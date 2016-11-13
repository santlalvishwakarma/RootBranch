DELIMITER $$

DROP PROCEDURE IF EXISTS sp_product_save_product_sku_image_mapping $$
CREATE PROCEDURE sp_product_save_product_sku_image_mapping(
IN p_product_sku_id INT(10),
IN p_image_parse_string TEXT,
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
     
DECLARE v_user_login                    VARCHAR(50);
DECLARE v_modified_date                 DATETIME;
DECLARE v_counter                       INT;
                  
DECLARE v_product_image_mapping_id		INT(10);
DECLARE v_image_url	          			VARCHAR(4000);
DECLARE v_thumbnail_url					VARCHAR(4000);
DECLARE v_zoom_url 						VARCHAR(4000);
DECLARE v_report_url 				    VARCHAR(4000);
DECLARE v_sequence_number 				INT(10);
DECLARE v_deleteYN						TINYINT(1);
DECLARE v_last_inserted_id 				INT(10);

DECLARE v_is_modify_images 				TINYINT(1);
DECLARE v_is_modify_images_counter  	INT;


SET v_modified_date = DATE_FORMAT(p_modified_date, '%Y-%m-%d %H:%i:%s');

IF p_product_sku_id IS NOT NULL THEN

	SELECT  COUNT(1)  INTO v_counter
	FROM  	product_sku_header 
    WHERE   product_sku_id = p_product_sku_id
    AND     modified_date  = v_modified_date; 
    
    IF v_counter > 0 THEN    
		
    	UPDATE 	product_sku_header
		SET 	modified_by = p_user_login,
	    		modified_date = NOW()
		WHERE   product_sku_id = p_product_sku_id; 
						    
		SELECT  modified_date INTO v_modified_date
		FROM    product_sku_header
		WHERE   product_sku_id = p_product_sku_id;																 	    
		
    ELSE
        
	  	SELECT  modified_by INTO v_user_login 
	    FROM    product_sku_header
	    WHERE   product_sku_id = p_product_sku_id;
	            
	    SET p_error_code = 'no_data_update_db_excep_msg';
	    SET p_error_message = CONCAT( 'SKU has been updated by (',v_user_login,'). Please search for the updated SKU to view or make changes.');
        	    
	END IF;    

END IF; 

IF p_error_code IS NULL AND p_image_parse_string IS NOT NULL THEN     
                        
	outer_parse: WHILE v_current_index > 0
	DO
	  
		SET v_previous_index = v_current_index;
		SELECT LOCATE(';', p_image_parse_string, v_current_index) INTO v_current_index;
		IF v_current_index > 0
		THEN
			SET v_start_point = v_previous_index;
			SET v_end_point = v_current_index - v_start_point;
			SET v_current_index = v_current_index + 1;	
		ELSE
			SET v_start_point = v_previous_index;
			SET v_end_point = LENGTH(p_image_parse_string);
		END IF;

		SET v_current_node = SUBSTR(p_image_parse_string,v_start_point,v_end_point);
    SET v_row_id = v_current_index ;
    SET  v_property_row_VALUES = v_current_node;
     
        inner_parse: WHILE v_inner_current_index > 0
        DO
          SET v_inner_previous_index = v_inner_current_index;
          SELECT LOCATE(';', v_current_node, v_inner_current_index) INTO v_inner_current_index;

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
                       		 	SET v_product_image_mapping_id = v_inner_current_node1;	  
                      		 ELSEIF v_column_sequence_number = 2 THEN 
                        	     SET v_image_url = v_inner_current_node1;
                        	 ELSEIF v_column_sequence_number = 3 THEN 
                        	     SET v_thumbnail_url = v_inner_current_node1;
                        	 ELSEIF v_column_sequence_number = 4 THEN 
                        	     SET v_zoom_url = v_inner_current_node1;  
                        	 ELSEIF v_column_sequence_number = 5 THEN 
                        	     SET v_sequence_number = v_inner_current_node1;
                        	 ELSEIF v_column_sequence_number = 6 THEN 
                        	     SET v_deleteYN = v_inner_current_node1;
                     		 END IF;   
                     	                     
                      SET v_column_sequence_number = v_column_sequence_number +1;   
                       END WHILE inner_parse1;
                       
                        SET v_inner_current_index1 = 1;
                                                
	                  IF p_product_sku_id IS NOT NULL THEN
							
	                  		IF v_product_image_mapping_id IS NOT NULL AND v_deleteYN = 0 THEN
	                  	
								IF v_image_url IS NOT NULL AND v_thumbnail_url IS NOT NULL THEN
							
		                  			UPDATE 	product_sku_image_mapping
									SET 	product_sku_id = p_product_sku_id,
											image_url = v_image_url,
											thumbnail_image_url = v_thumbnail_url,
											zoom_image_url = v_zoom_url, 
											sequence_number = v_sequence_number,
			                            	modified_by = p_user_login,
			                            	modified_date = NOW()		                            
									WHERE 	product_sku_image_mapping_id = v_product_image_mapping_id;
									
									IF v_sequence_number = 0 THEN
									
										UPDATE product_sku_header
										SET		default_thumbnail_image_url = v_thumbnail_url,
												default_image_url = v_image_url,
												default_zoom_image_url = v_zoom_url
										WHERE	product_sku_id = p_product_sku_id;
									
									END IF;
									
								
								END IF;
                        	ELSEIF v_product_image_mapping_id IS NULL THEN
                        	
		                      	INSERT INTO product_sku_image_mapping
		                        (	product_sku_id, 
		                        	image_url, 
		                        	thumbnail_image_url, 
		                        	zoom_image_url, 
		                        	sequence_number, 
		                        	is_active,
		                        	created_by, 
		                        	created_date, 
		                        	modified_by, 
		                        	modified_date
		                     	)
		                        VALUES
		                        (	p_product_sku_id,
		                          	v_image_url, 
		                        	v_thumbnail_url, 
		                        	v_zoom_url, 
		                        	v_sequence_number,
		                        	1,
		                           	p_user_login,
		                            NOW(),
		                            p_user_login,
		                            NOW()
		                       	);	
		                       	
		                       	IF v_sequence_number = 0 THEN
									
										UPDATE product_sku_header
										SET		default_thumbnail_image_url = v_thumbnail_url,
												default_image_url = v_image_url,
												default_zoom_image_url = v_zoom_url
										WHERE	product_sku_id = p_product_sku_id;
									
									END IF;
								
		                       	SELECT LAST_INSERT_ID() INTO v_last_inserted_id;
		                       	
							ELSEIF v_product_image_mapping_id IS NOT NULL AND v_deleteYN = 1 THEN
							
								DELETE 	FROM product_sku_image_mapping
								WHERE 	product_sku_id = p_product_sku_id
								AND 	product_sku_image_mapping_id = v_product_image_mapping_id
								AND		product_sku_id = p_product_sku_id;
								
							END IF;	                  			                       							 
						    
					  END IF;
					  
					  SET v_last_inserted_id = NULL;
                      SET v_product_image_mapping_id = NULL;
	                  SET v_image_url = NULL; 
		              SET v_thumbnail_url = NULL; 
		              SET v_zoom_url = NULL; 
		              SET v_report_url = NULL;
		              SET v_sequence_number = NULL;
	                  SET v_deleteYN = NULL;
                                              
                END WHILE inner_parse;
                      
        SET v_inner_current_index = 1;              
                                          
      END WHILE outer_parse;
          
	END IF;
	
		IF p_error_code IS NULL AND p_product_sku_id IS NOT NULL THEN	  
                    		 	 
	   		IF v_is_modify_images_counter > 0 THEN
	   		
				UPDATE 	product_sku_header
				SET 	modified_by = p_user_login,
						modified_date = NOW()
				WHERE 	product_sku_id = p_product_sku_id;
				
				SELECT  modified_date INTO v_modified_date
				FROM    product_sku_header
				WHERE   product_sku_id = p_product_sku_id;	
	
			END IF;		
		END IF;
		
SELECT p_product_sku_id AS product_sku_id, v_modified_date AS modified_date, p_error_code, p_error_message;
  
END $$

/*
CALL sp_product_save_product_sku_image_mapping(
78,
98,
'~/product/prd_r.jpg~/product/prd_r.jpg~product/prd_t.jpg~product/prd_z.jpg~0~0;~/product/prd1_r.jpg~product/prd1_t.jpg~product/prd1_z.jpg~0~0;~/product/prd2_r.jpg~product/prd2_t.jpg~product/prd2_z.jpg~0~0',
0,
'tsd',
'2012-08-07 14:38:53',
@p_error_code,
@p_error_message
);

CALL sp_product_save_product_sku_image_mapping(
79,
98,
'1~/product/prd_r.jpg~/product/prd_r.jpg~product/prd_t.jpg~product/prd_z.jpg~0~0;2~/product/prd1_r.jpg~product/prd1_t.jpg~product/prd1_z.jpg~1~0;3~/product/prd2_r.jpg~product/prd2_t.jpg~product/prd2_z.jpg~2~0',
1,
'tsd',
'2012-08-09 15:30:05',
@p_error_code,
@p_error_message
);

CALL sp_product_save_product_sku_image_mapping(
78,
98,
'~/product/prd_r.jpg~/product/prd_r.jpg~product/prd_t.jpg~product/prd_z.jpg~0~0;~/product/prd1_r.jpg~product/prd1_t.jpg~product/prd1_z.jpg~0~0;~/product/prd2_r.jpg~product/prd2_t.jpg~product/prd2_z.jpg~0~0',
0,
'tsd',
'2012-08-09 15:38:33',
@p_error_code,
@p_error_message
);

CALL sp_product_save_product_sku_image_mapping(
79,
98,
'10~/product/prd_r.jpg~/product/prd_r.jpg~product/prd_t.jpg~product/prd_z.jpg~0~0;11~/product/prd1_r.jpg~product/prd1_t.jpg~product/prd1_z.jpg~1~0;12~/product/prd2_r.jpg~product/prd2_t.jpg~product/prd2_z.jpg~2~1',
1,
'tsd',
'2012-08-09 15:42:48',
@p_error_code,
@p_error_message
);

*/

DELIMITER ;

