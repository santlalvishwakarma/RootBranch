
DROP FUNCTION IF EXISTS fn_get_hierarchy_name_based_on_id;

DELIMITER $$

CREATE  FUNCTION fn_get_hierarchy_name_based_on_id(p_hierarchy_id INT(10)) RETURNS VARCHAR(50) 
    READS SQL DATA
BEGIN
	DECLARE v_hierarchy_name VARCHAR(50);

        SELECT  hierarchy_name
        INTO    v_hierarchy_name
        FROM    hierarchy_master
        WHERE   hierarchy_id = p_hierarchy_id LIMIT 1;

	RETURN(v_hierarchy_name);
END $$

DELIMITER ;
