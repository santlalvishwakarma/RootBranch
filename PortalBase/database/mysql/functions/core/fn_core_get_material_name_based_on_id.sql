
DROP FUNCTION IF EXISTS fn_core_get_material_name_based_on_id;

DELIMITER $$

CREATE  FUNCTION fn_core_get_material_name_based_on_id(p_material_id INT(10)) RETURNS VARCHAR(60) 
    READS SQL DATA
BEGIN
	DECLARE v_material_name VARCHAR(60);

        SELECT  cmm.material_name
        INTO    v_material_name
        FROM    core_material_master cmm
        WHERE   cmm.material_id = p_material_id LIMIT 1;

	RETURN(v_material_name);
END $$

DELIMITER ;
