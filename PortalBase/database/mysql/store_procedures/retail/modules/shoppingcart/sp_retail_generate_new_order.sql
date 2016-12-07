DELIMITER $$

DROP PROCEDURE IF EXISTS sp_retail_generate_new_order $$

CREATE  PROCEDURE sp_retail_generate_new_order(
IN p_user_login VARCHAR(100),
IN p_total_quantity INT,  
IN p_total_amount FLOAT,
IN p_lead_time INT,  
IN p_purchased_delivery_charges FLOAT,
IN p_customer_billing_fname VARCHAR(45),  
IN p_customer_billing_sname VARCHAR(45),  
IN p_customer_billing_address_line1 VARCHAR(100),  
IN p_customer_billing_address_line2 VARCHAR(100),  
IN p_customer_billing_address_line3 VARCHAR(100),  
IN p_customer_billing_address_city VARCHAR(45),  
IN p_customer_billing_address_zip_code VARCHAR(25),  
IN p_customer_billing_address_state VARCHAR(25),  
IN p_customer_billing_address_country VARCHAR(25),  
IN p_customer_shipping_fname VARCHAR(45),  
IN p_customer_shipping_sname VARCHAR(45),  
IN p_customer_shipping_address_line1 VARCHAR(100),  
IN p_customer_shipping_address_line2 VARCHAR(100),  
IN p_customer_shipping_address_line3 VARCHAR(100),  
IN p_customer_shipping_address_city VARCHAR(25),  
IN p_customer_shipping_address_zip_code VARCHAR(25),  
IN p_customer_shipping_address_state VARCHAR(25),  
IN p_customer_shipping_address_country VARCHAR(25),  
IN p_customer_billing_primary_phone_number VARCHAR(45),  
IN p_customer_billing_alternate_phone_number VARCHAR(45),  
IN p_customer_shipping_primary_phone_number VARCHAR(45),
IN p_customer_shipping_alternate_phone_number VARCHAR(45),
IN p_customer_billing_email_id VARCHAR(255),
IN p_customer_shipping_email_id VARCHAR(255),
IN p_order_details_string VARCHAR(4000),
IN p_promo_code VARCHAR(45),
IN p_voucher_discount FLOAT,  
IN p_voucher_id INT,
IN p_gift_wrapping_required TINYINT(1),
IN p_domain_name VARCHAR(45),
IN p_currency_conversion_rate FLOAT,
IN p_converted_currency_symbol VARCHAR(45),
IN p_original_total_amount FLOAT,
IN p_currency_symbol_flag BOOLEAN,
IN p_original_purchased_delivery_charges FLOAT
)
BEGIN
	
	
	DECLARE v_login_name VARCHAR(50);
	DECLARE v_lead_time INT;
	DECLARE v_order_header_id INT;
	DECLARE v_order_number VARCHAR(45);
	DECLARE v_tmp_counter INT;
	DECLARE v_order_date DATE;
    DECLARE v_delivery_date DATE;
	
	/* variable for parse string */
	
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
	
	DECLARE v_product_id INT(10);
	DECLARE v_product_sku_id INT(10);
	DECLARE v_base_price FLOAT;
	DECLARE v_discount_amount FLOAT;
	DECLARE v_quantity INT;
	DECLARE v_sub_total FLOAT;
	DECLARE v_comments TEXT;
	DECLARE v_original_base_price FLOAT;
	DECLARE v_original_discount_price FLOAT;
	DECLARE v_original_sub_total FLOAT;
	DECLARE v_price_per_piece FLOAT;
	DECLARE v_original_price_per_piece FLOAT;
	DECLARE v_order_details_id INT;
	DECLARE v_current_stock_product_sku_id INT;
	DECLARE v_final_base_price FLOAT;
	DECLARE v_discount_percent FLOAT;
	
	
    DROP TEMPORARY TABLE IF EXISTS product_stock_levels_tmp;
	CREATE TEMPORARY TABLE IF NOT EXISTS product_stock_levels_tmp  
	(	product_id INT(10),
		product_sku_id INT(10),
		product_code VARCHAR(250),
		product_name VARCHAR(100),
		available_quantity INT(4),
		blocked_quantity INT(4),
		reorder_level INT(4)
	);
	
	
	 IF p_user_login IS NULL THEN
	    SET v_login_name = 'guest';
	ELSE
	    SET v_login_name = p_user_login;    
	END IF;
	
	SET v_lead_time = IFNULL(p_lead_time,0);
	
	INSERT INTO order_header (user_id, user_login, order_no, total_quantity, lead_time, total_amount, original_total_amount, express_delivery_charge,
	original_express_delivery_charge, currency_conversion_rate, currency_code, currency_symbol_flag, payment_status, order_status, billing_first_name, 
	billing_last_name, billing_email_address_1, billing_email_address_2, billing_mobile_1, billing_mobile_2, billing_address_line_1, billing_address_line_2, billing_address_line_3,
	billing_city, billing_zip_code, billing_state, billing_country, order_tracking_number, order_date, delivery_date, courier_id, voucher_code,
	discount_value, gift_wrapping_required, email_regenerated_by, created_by, created_date, modified_by, modified_date) 
	VALUES ((SELECT user_id from core_user_master where user_login = v_login_name) , v_login_name, UUID() ,p_total_quantity, v_lead_time, p_total_amount ,p_original_total_amount, 
	IFNULL(p_purchased_delivery_charges,0) ,IFNULL(p_original_purchased_delivery_charges,0) ,
	p_currency_conversion_rate ,p_converted_currency_symbol ,p_currency_symbol_flag , (SELECT parameter_id from core_parameter_master where param_code='PAYMENT_STATUS' and sequence_number=1), 
	(SELECT parameter_id from core_parameter_master where param_code='ORDER_STATUS' and sequence_number=1), p_customer_billing_fname , p_customer_billing_sname, 
	p_customer_billing_email_id, NULL, p_customer_billing_primary_phone_number, p_customer_billing_alternate_phone_number, p_customer_billing_address_line1, p_customer_billing_address_line2,
	p_customer_shipping_address_line3, p_customer_billing_address_city, p_customer_billing_address_zip_code, p_customer_billing_address_state, p_customer_billing_address_country, NULL, 
	NOW(), DATE_ADD(NOW(), INTERVAL v_lead_time day), NULL, p_promo_code, p_voucher_discount, p_gift_wrapping_required, NULL, p_user_login, NOW(), p_user_login, NOW());
	
	
	SELECT last_insert_id() INTO v_order_header_id;
	
	SET v_order_number = CONCAT('PORTAL-',v_order_header_id);
	
	UPDATE order_header SET order_no = v_order_number WHERE order_header_id = v_order_header_id;
	
	SELECT order_date, delivery_date 
	INTO v_order_date, v_delivery_date
	FROM order_header
	WHERE order_header_id = v_order_header_id;
	
	
	IF p_order_details_string IS NOT NULL THEN     
                        
		outer_parse: WHILE v_current_index > 0
		DO
		  
			SET v_previous_index = v_current_index;
			SELECT LOCATE(';', p_order_details_string, v_current_index) INTO v_current_index;
			IF v_current_index > 0
			THEN
				SET v_start_point = v_previous_index;
				SET v_end_point = v_current_index - v_start_point;
				SET v_current_index = v_current_index + 1;	
			ELSE
				SET v_start_point = v_previous_index;
				SET v_end_point = LENGTH(p_order_details_string);
			END IF;
	
			SET v_current_node = SUBSTR(p_order_details_string,v_start_point,v_end_point);
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
		                         	 SET v_product_id = v_inner_current_node1;	  
		                          ELSEIF v_column_sequence_number = 2 THEN 
		                          	 SET v_product_sku_id = v_inner_current_node1;
		                          ELSEIF v_column_sequence_number = 3 THEN 
		                          	 SET v_base_price = v_inner_current_node1;	 
							      ELSEIF v_column_sequence_number = 4 THEN 
							         SET v_discount_amount = v_inner_current_node1;
							      ELSEIF v_column_sequence_number = 5 THEN
							         SET v_quantity = v_inner_current_node1;
							      ELSEIF v_column_sequence_number = 6 THEN
							         SET v_sub_total = v_inner_current_node1;
							      ELSEIF v_column_sequence_number = 7 THEN
							         SET v_comments = v_inner_current_node1;
							      ELSEIF v_column_sequence_number = 8 THEN
							         SET v_original_base_price = v_inner_current_node1;
							      ELSEIF v_column_sequence_number = 9 THEN
							         SET v_original_discount_price = v_inner_current_node1;	 
							      ELSEIF v_column_sequence_number = 10 THEN
							         SET v_original_sub_total = v_inner_current_node1;
							      ELSEIF v_column_sequence_number = 11 THEN
							         SET v_final_base_price = v_inner_current_node1;
							      ELSEIF v_column_sequence_number = 12 THEN
							         SET v_discount_percent = v_inner_current_node1;
							      END IF;   
							      
							      SET v_column_sequence_number = v_column_sequence_number +1;   
							      END WHILE inner_parse1;
							                       
							      SET v_inner_current_index1 = 1;
							      
							      SET v_price_per_piece = v_final_base_price;
							      
							      SET v_original_price_per_piece = v_final_base_price;
							      
							      SET v_original_sub_total = v_sub_total;
							      
							      	/*IF v_discount_price IS NOT NULL THEN
							      	
							      		SET v_price_per_piece = v_discount_price;
							      	ELSE
							      	
							      		SET v_price_per_piece = v_base_price;
							      		
							      	END IF;
							      	
							      	IF v_original_discount_price IS NOT NULL THEN
							      	
							      		SET v_original_price_per_piece = v_original_discount_price;
							      	ELSE
							      	
							      		SET v_original_price_per_piece = v_original_base_price;
							      		
							      	END IF;*/
							      
							      
							      	INSERT INTO order_detail
							      	(order_header_id, product_id, product_sku_id, price_per_piece, original_price_per_piece, total_price, original_total_price, quantity)
									VALUES(v_order_header_id, v_product_id, v_product_sku_id, v_price_per_piece, v_original_price_per_piece, v_sub_total, v_original_sub_total, v_quantity);
									
									SELECT last_insert_id() INTO v_order_details_id;
									
									
								/*	INSERT INTO order_shipping_mapping(order_header_id, order_detail_id, shipping_first_name, shipping_last_name, shipping_email_address_1, 
									shipping_mobile_1, shipping_mobile_2, shipping_address_line_1, shipping_address_line_2, shipping_address_line_3, shipping_city, shipping_zip_code, 
									shipping_state, shipping_country, created_by, created_date, modified_by, modified_date)
									VALUES(v_order_header_id, v_order_details_id, p_customer_shipping_fname, p_customer_shipping_sname, p_customer_shipping_email_id, p_customer_shipping_primary_phone_number,
									p_customer_shipping_alternate_phone_number, p_customer_shipping_address_line1, p_customer_shipping_address_line2, p_customer_shipping_address_line3, p_customer_shipping_address_city,
									p_customer_shipping_address_zip_code, p_customer_shipping_address_state, p_customer_shipping_address_country, p_user_login, NOW(), p_user_login, NOW());
								*/
									
									
								/* UPDATE 	 product_sku_stock_level
									SET		 available_quantity = IFNULL(available_quantity,0) - v_quantity,
              								 blocked_quantity = IFNULL(blocked_quantity,0) + v_quantity
									WHERE    product_id = v_product_id
									AND      product_sku_id = v_product_sku_id;
								*/
									
									/* SELECT product_sku_id
						             INTO v_current_stock_product_sku_id
						             FROM product_stock_levels_tmp
						             WHERE product_id = v_product_id and product_sku_id = v_product_sku_id;
						           */
						             
									
									/* IF v_current_stock_product_sku_id IS NOT NULL THEN
									 
						              UPDATE product_stock_levels_tmp a, product_sku_stock_level b
						              SET a.available_quantity = b.available_quantity,
						              a.blocked_quantity = b.blocked_quantity,
						              -- a.shipped_quantity = b.shipped_quantity,
						              a.reorder_level = b.reorder_level
						              WHERE a.product_sku_id = b.product_sku_id
						              AND a.product_id = b.product_id
						              AND b.product_id = v_product_id
						              AND b.product_sku_id = v_product_sku_id;
						              
						             ELSE
						             
						              INSERT INTO product_stock_levels_tmp(product_id, product_sku_id, product_code, product_name, available_quantity,blocked_quantity,reorder_level)
						              SELECT v_product_id, v_product_sku_id, psh.sku_code, psh.sku_name, cpsl.available_quantity, cpsl.blocked_quantity, cpsl.reorder_level
						              FROM product_sku_stock_level cpsl, product_header ph, product_sku_header psh
						              WHERE cpsl.product_id = v_product_id
						              AND cpsl.product_sku_id = v_product_sku_id
						              AND cpsl.product_id = ph.product_id
						              AND cpsl.product_id = psh.product_id;
						              
						             END IF;  
									*/
							      
							      
							    SET v_product_id = NULL;
							    SET v_product_sku_id = NULL;
							    SET v_base_price = NULL;
		                        SET v_discount_amount = NULL;
								SET v_quantity = NULL;
								SET v_sub_total = NULL;
								SET v_comments = NULL;
								SET v_original_base_price = NULL;  
								SET v_original_discount_price = NULL;
								SET v_original_sub_total = NULL;
								SET v_final_base_price = NULL;
								SET v_discount_percent = NULL;
															                                                      
	                END WHILE inner_parse;
	                      
	        SET v_inner_current_index = 1;              
	                                          
	      END WHILE outer_parse;
      
	END IF;
	
  /*SELECT COUNT(*)
   INTO  v_tmp_counter
   FROM product_stock_levels_tmp;
   */
   
   
   select v_order_header_id , v_order_number, v_order_date, v_delivery_date;
   
  /* IF  v_tmp_counter IS NULL OR  v_tmp_counter = 0 THEN
   select v_order_header_id , v_order_number, v_order_date, v_delivery_date;
   ELSE
   select v_order_header_id , v_order_number, v_order_date, v_delivery_date,
    product_id, product_sku_id, product_code, product_name, available_quantity, blocked_quantity, reorder_level
   FROM product_stock_levels_tmp;
   END IF;
   */
	
   
END $$

DELIMITER ;

