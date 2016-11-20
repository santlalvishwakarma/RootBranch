
DROP FUNCTION IF EXISTS fn_get_hierarchy_code_based_on_id;

DELIMITER $$

CREATE  FUNCTION fn_get_hierarchy_code_based_on_id(p_hierarchy_id INT(10)) RETURNS VARCHAR(25) 
    READS SQL DATA
BEGIN
	DECLARE v_hierarchy_code VARCHAR(25);

        SELECT  hierarchy_code
        INTO    v_hierarchy_code
        FROM    hierarchy_master
        WHERE   hierarchy_id = p_hierarchy_id LIMIT 1;

	RETURN(v_hierarchy_code);
END $$

DELIMITER ;
