DELIMITER $$

DROP PROCEDURE IF EXISTS sp_core_get_shipping_charges_yes $$

CREATE PROCEDURE sp_core_get_shipping_charges_yes(
IN p_country_code VARCHAR(25),
IN p_hierarchy_id INT,
IN p_level1_category_id INT,
IN p_level2_category_id INT,
IN p_level3_category_id INT,
IN p_level4_category_id INT
)
BEGIN
	DECLARE v_selected_hierarchy_category_charge_id INT(10);
	DECLARE v_count_country INT;
	DECLARE v_default_country VARCHAR(25);
	DECLARE v_delivery_charge FLOAT(15,3);
	DECLARE v_processing_charge FLOAT(15,3);
	DECLARE v_duties_charge FLOAT(15,3);
	DECLARE v_express_charge FLOAT(15,3);
    -- following code is for all level4 categories
    
	SELECT  hierarchy_category_charge_details_id 
	INTO	v_selected_hierarchy_category_charge_id
	FROM	core_hierarchy_category_charge_details
	WHERE	hierarchy_id = p_hierarchy_id
	AND		category_level_1_id = p_level1_category_id
	AND		category_level_2_id = p_level2_category_id
	AND		category_level_3_id = p_level3_category_id
	AND		category_level_4_id = p_level4_category_id;
	
	SELECT country_code INTO v_default_country FROM core_country_master WHERE country_name = 'Default';
	
	IF v_selected_hierarchy_category_charge_id IS NOT NULL THEN
	
		SELECT COUNT(*) INTO  v_count_country 
		FROM core_hierarchy_category_country_charge_mapping 
		WHERE country_code = p_country_code AND hierarchy_category_charge_details_id = v_selected_hierarchy_category_charge_id;
	
		IF v_count_country = 1 THEN
			SELECT delivery_charge, processing_charge, duties_charge, express_charge 
			INTO v_delivery_charge, v_processing_charge, v_duties_charge,v_express_charge
			FROM core_hierarchy_category_country_charge_mapping
			WHERE country_code = p_country_code 
			AND hierarchy_category_charge_details_id = v_selected_hierarchy_category_charge_id;
		END IF;	
		
		IF v_count_country = 0 THEN
			
			SELECT delivery_charge, processing_charge, duties_charge, express_charge 
			INTO v_delivery_charge, v_processing_charge, v_duties_charge,v_express_charge
			FROM core_hierarchy_category_country_charge_mapping 
			WHERE country_code = v_default_country 
			AND hierarchy_category_charge_details_id = v_selected_hierarchy_category_charge_id;
								
		END IF;
	
	END IF;
	
	-- following code is for all level3 categories when v_delivery_charge
	IF v_delivery_charge IS NULL THEN
		
		
		 SET v_selected_hierarchy_category_charge_id = NULL;
		
		SELECT hierarchy_category_charge_details_id INTO v_selected_hierarchy_category_charge_id
		FROM core_hierarchy_category_charge_details 
		WHERE	hierarchy_id = p_hierarchy_id
		AND		category_level_1_id = p_level1_category_id
		AND		category_level_2_id = p_level2_category_id
		AND		category_level_3_id = p_level3_category_id
		AND		category_level_4_id = NULL;
		
		IF v_selected_hierarchy_category_charge_id IS NOT NULL THEN
	
			SELECT COUNT(*) INTO  v_count_country 
			FROM core_hierarchy_category_country_charge_mapping 
			WHERE country_code = p_country_code AND hierarchy_category_charge_details_id = v_selected_hierarchy_category_charge_id;
	
			IF v_count_country = 1 THEN
				SELECT delivery_charge, processing_charge, duties_charge, express_charge 
				INTO v_delivery_charge, v_processing_charge, v_duties_charge,v_express_charge
				FROM core_hierarchy_category_country_charge_mapping
				WHERE country_code = p_country_code 
				AND hierarchy_category_charge_details_id = v_selected_hierarchy_category_charge_id;
			END IF;	
			
			IF v_count_country = 0 THEN
				
				SELECT delivery_charge, processing_charge, duties_charge, express_charge 
				INTO v_delivery_charge, v_processing_charge, v_duties_charge,v_express_charge
				FROM core_hierarchy_category_country_charge_mapping 
				WHERE country_code = v_default_country 
				AND hierarchy_category_charge_details_id = v_selected_hierarchy_category_charge_id;
									
			END IF;
	
		END IF;
		
	END IF;
	
	-- following code is for all level2 categories when v_delivery_charge
	IF v_delivery_charge IS NULL THEN
		
		 SET v_selected_hierarchy_category_charge_id = NULL;
		
		SELECT hierarchy_category_charge_details_id INTO v_selected_hierarchy_category_charge_id
		FROM core_hierarchy_category_charge_details 
		WHERE	hierarchy_id = p_hierarchy_id
		AND		category_level_1_id = p_level1_category_id
		AND		category_level_2_id = p_level2_category_id
		AND		category_level_3_id = NULL
		AND		category_level_4_id = NULL;
		
		IF v_selected_hierarchy_category_charge_id IS NOT NULL THEN
	
			SELECT COUNT(*) INTO  v_count_country 
			FROM core_hierarchy_category_country_charge_mapping 
			WHERE country_code = p_country_code AND hierarchy_category_charge_details_id = v_selected_hierarchy_category_charge_id;
	
			IF v_count_country = 1 THEN
				SELECT delivery_charge, processing_charge, duties_charge, express_charge 
				INTO v_delivery_charge, v_processing_charge, v_duties_charge,v_express_charge
				FROM core_hierarchy_category_country_charge_mapping
				WHERE country_code = p_country_code 
				AND hierarchy_category_charge_details_id = v_selected_hierarchy_category_charge_id;
			END IF;	
			
			IF v_count_country = 0 THEN
				
				SELECT delivery_charge, processing_charge, duties_charge, express_charge 
				INTO v_delivery_charge, v_processing_charge, v_duties_charge,v_express_charge
				FROM core_hierarchy_category_country_charge_mapping 
				WHERE country_code = v_default_country 
				AND hierarchy_category_charge_details_id = v_selected_hierarchy_category_charge_id;
									
			END IF;
	
		END IF;
		
	END IF;
	
	
	-- following code is for all level1 categories when v_delivery_charge
	IF v_delivery_charge IS NULL THEN
		
		 SET v_selected_hierarchy_category_charge_id = NULL;
		
		SELECT hierarchy_category_charge_details_id INTO v_selected_hierarchy_category_charge_id
		FROM core_hierarchy_category_charge_details 
		WHERE	hierarchy_id = p_hierarchy_id
		AND		category_level_1_id = p_level1_category_id
		AND		category_level_2_id = NULL
		AND		category_level_3_id = NULL
		AND		category_level_4_id = NULL;
		
		IF v_selected_hierarchy_category_charge_id IS NOT NULL THEN
	
			SELECT COUNT(*) INTO  v_count_country 
			FROM core_hierarchy_category_country_charge_mapping 
			WHERE country_code = p_country_code AND hierarchy_category_charge_details_id = v_selected_hierarchy_category_charge_id;
	
			IF v_count_country = 1 THEN
				SELECT delivery_charge, processing_charge, duties_charge, express_charge 
				INTO v_delivery_charge, v_processing_charge, v_duties_charge,v_express_charge
				FROM core_hierarchy_category_country_charge_mapping
				WHERE country_code = p_country_code 
				AND hierarchy_category_charge_details_id = v_selected_hierarchy_category_charge_id;
			END IF;	
			
			IF v_count_country = 0 THEN
				
				SELECT delivery_charge, processing_charge, duties_charge, express_charge 
				INTO v_delivery_charge, v_processing_charge, v_duties_charge,v_express_charge
				FROM core_hierarchy_category_country_charge_mapping 
				WHERE country_code = v_default_country 
				AND hierarchy_category_charge_details_id = v_selected_hierarchy_category_charge_id;
									
			END IF;
	
		END IF;
		
	END IF;
	
	
	
	 SELECT v_selected_hierarchy_category_charge_id, v_delivery_charge, v_processing_charge, v_duties_charge, v_express_charge;
	
END $$

DELIMITER ;
