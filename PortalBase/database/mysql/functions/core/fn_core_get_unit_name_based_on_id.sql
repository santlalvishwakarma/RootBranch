
DROP FUNCTION IF EXISTS fn_core_get_unit_name_based_on_id;

DELIMITER $$

CREATE  FUNCTION fn_core_get_unit_name_based_on_id(p_unit_id INT(10)) RETURNS VARCHAR(60) 
    READS SQL DATA
BEGIN
	DECLARE v_unit_name VARCHAR(60);

        SELECT  ccm.unit_name
        INTO    v_unit_name
        FROM    core_unit_master ccm
        WHERE   ccm.unit_id = p_unit_id LIMIT 1;

	RETURN(v_unit_name);
END $$

DELIMITER ;
