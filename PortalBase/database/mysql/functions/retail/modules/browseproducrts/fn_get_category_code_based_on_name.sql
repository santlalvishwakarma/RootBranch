
DROP FUNCTION IF EXISTS fn_get_category_code_based_on_name;

DELIMITER $$

CREATE  FUNCTION fn_get_category_code_based_on_name(p_category_name VARCHAR(500)) RETURNS VARCHAR(500) 
    READS SQL DATA
BEGIN
	DECLARE v_category_code VARCHAR(60);

        SELECT  category_code
        INTO    v_category_code
        FROM    category_master
        WHERE   category_name = p_category_name LIMIT 1;

	RETURN(v_category_code);
END $$

DELIMITER ;


