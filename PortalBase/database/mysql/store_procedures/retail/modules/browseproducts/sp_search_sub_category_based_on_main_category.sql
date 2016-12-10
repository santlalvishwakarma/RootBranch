DELIMITER $$
DROP PROCEDURE IF EXISTS sp_search_sub_category_based_on_main_category $$
CREATE PROCEDURE sp_search_sub_category_based_on_main_category(
 IN p_hierarchy_name VARCHAR(500),
 IN p_level_1_category_code VARCHAR(500),
 IN p_level_2_category_code VARCHAR(500),
 IN p_level_3_category_code VARCHAR(500),
 IN p_level_4_category_code VARCHAR(500)
)
    MODIFIES SQL DATA
    DETERMINISTIC
BEGIN
DECLARE v_hierarchy_id INT(10);
DECLARE v_level_1_category_id INT(10);
DECLARE v_level_2_category_id INT(10);
DECLARE v_level_3_category_id INT(10);
DECLARE v_level_4_category_id INT(10);


SET v_hierarchy_id = fn_get_hierarchy_id_based_on_name(p_hierarchy_name);

IF p_level_4_category_code IS NOT NULL THEN

	SET v_level_4_category_id = fn_get_category_id_based_on_code(p_level_4_category_code);

END IF;

IF p_level_3_category_code IS NOT NULL THEN

	SET v_level_3_category_id = fn_get_category_id_based_on_code(p_level_3_category_code);

END IF;

IF p_level_2_category_code IS NOT NULL THEN

	SET v_level_2_category_id = fn_get_category_id_based_on_code(p_level_2_category_code);

END IF;

IF p_level_1_category_code IS NOT NULL THEN

	SET v_level_1_category_id = fn_get_category_id_based_on_code(p_level_1_category_code);

END IF;

	
IF p_level_3_category_code IS NOT NULL AND p_level_4_category_code IS NULL THEN

	SELECT  DISTINCT cm.category_id, cm.category_code, cm.category_name, cm.category_description, cm.seo_title, cm.seo_keyword, cm.seo_description, cm.image_url, cm.is_active
	FROM 	hierarchy_category_mapping hcm, category_master cm
	WHERE	hcm.hierarchy_id = v_hierarchy_id
	AND		hcm.category_level_1 = v_level_1_category_id
	AND		hcm.category_level_2 = v_level_2_category_id
	AND		hcm.category_level_3 = v_level_3_category_id
	AND		hcm.category_level_4 = cm.category_id
	AND		hcm.is_active = 1
	AND 	cm.is_active = 1;
	

ELSEIF p_level_2_category_code IS NOT NULL AND p_level_3_category_code IS NULL AND p_level_4_category_code IS NULL THEN

	SELECT  DISTINCT cm.category_id, cm.category_code, cm.category_name, cm.category_description, cm.seo_title, cm.seo_keyword, cm.seo_description, cm.image_url, cm.is_active
	FROM 	hierarchy_category_mapping hcm, category_master cm
	WHERE	hcm.hierarchy_id = v_hierarchy_id
	AND		hcm.category_level_1 = v_level_1_category_id
	AND		hcm.category_level_2 = v_level_2_category_id
	AND		hcm.category_level_3 = cm.category_id
	AND		hcm.is_active = 1
	AND 	cm.is_active = 1;	
	
ELSEIF p_level_1_category_code IS NOT NULL AND p_level_2_category_code IS NULL AND p_level_3_category_code IS NULL AND p_level_4_category_code IS NULL THEN

	SELECT  DISTINCT cm.category_id, cm.category_code, cm.category_name, cm.category_description, cm.seo_title, cm.seo_keyword, cm.seo_description, cm.image_url, cm.is_active
	FROM 	hierarchy_category_mapping hcm, category_master cm
	WHERE	hcm.hierarchy_id = v_hierarchy_id
	AND		hcm.category_level_1 = v_level_1_category_id
	AND		hcm.category_level_2 = cm.category_id
	AND		hcm.is_active = 1
	AND 	cm.is_active = 1;	

ELSEIF p_hierarchy_name IS NOT NULL AND p_level_1_category_code IS NULL AND p_level_2_category_code IS NULL AND p_level_3_category_code IS NULL AND p_level_4_category_code IS NULL THEN

	SELECT  DISTINCT cm.category_id, cm.category_code, cm.category_name, cm.category_description, cm.seo_title, cm.seo_keyword, cm.seo_description, cm.image_url, cm.is_active
	FROM 	hierarchy_category_mapping hcm, category_master cm
	WHERE	hcm.hierarchy_id = v_hierarchy_id
	AND		hcm.category_level_1 = cm.category_id
	AND		hcm.is_active = 1
	AND 	cm.is_active = 1;	

END IF;





END $$
DELIMITER ;

