
DROP FUNCTION IF EXISTS fn_get_category_code_based_on_id;

DELIMITER $$

CREATE  FUNCTION fn_get_category_code_based_on_id(p_category_id INT(10)) RETURNS VARCHAR(50) 
    READS SQL DATA
BEGIN
	DECLARE v_category_code VARCHAR(50);

        SELECT  category_code
        INTO    v_category_code
        FROM    category_master
        WHERE   category_id = p_category_id LIMIT 1;

	RETURN(v_category_code);
END $$

DELIMITER ;


