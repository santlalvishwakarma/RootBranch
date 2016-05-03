
DROP FUNCTION IF EXISTS fn_get_category_id_based_on_code;

DELIMITER $$

CREATE  FUNCTION fn_get_category_id_based_on_code(p_category_code VARCHAR(500)) RETURNS INT 
    READS SQL DATA
BEGIN
	DECLARE v_category_id INT(10);

        SELECT  category_id
        INTO    v_category_id
        FROM    category_master
        WHERE   category_code = p_category_code LIMIT 1;

	RETURN(v_category_id);
END $$

DELIMITER ;


