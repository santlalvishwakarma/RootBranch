
DROP FUNCTION IF EXISTS fn_get_hierarchy_id_based_on_name;

DELIMITER $$

CREATE  FUNCTION fn_get_hierarchy_id_based_on_name(p_hierarchy_name VARCHAR(500)) RETURNS INT(10) 
    READS SQL DATA
BEGIN
	DECLARE v_hierarchy_id INT(10);

        SELECT  hierarchy_id
        INTO    v_hierarchy_id
        FROM    hierarchy_master
        WHERE   hierarchy_name = p_hierarchy_name LIMIT 1;

	RETURN(v_hierarchy_id);
END $$

DELIMITER ;
