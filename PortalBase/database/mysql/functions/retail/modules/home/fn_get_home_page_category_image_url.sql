DELIMITER $$

DROP FUNCTION IF EXISTS  fn_get_home_page_category_image_url  $$
CREATE FUNCTION fn_get_home_page_category_image_url(
p_category_level_1 int(10),
p_category_level_2 int(10),
p_category_level_3 int(10),
p_category_level_4 int(10)
) RETURNS  varchar(5000) 
 READS SQL DATA
BEGIN

DECLARE v_output_value varchar(5000);

IF p_category_level_4 IS NOT NULL THEN

	SELECT image_url INTO v_output_value
	FROM category_master
	WHERE category_id = p_category_level_4;

ELSEIF p_category_level_3 IS NOT NULL THEN

	SELECT image_url INTO v_output_value
	FROM category_master
	WHERE category_id = p_category_level_3;

ELSEIF p_category_level_2 IS NOT NULL THEN

	SELECT image_url INTO v_output_value
	FROM category_master
	WHERE category_id = p_category_level_2;
	
ELSEIF p_category_level_1 IS NOT NULL THEN

	SELECT image_url INTO v_output_value
	FROM category_master
	WHERE category_id = p_category_level_1;

	
END IF;    

RETURN(v_output_value);


END $$

DELIMITER ;

