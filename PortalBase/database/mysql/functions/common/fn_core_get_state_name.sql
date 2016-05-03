
DROP FUNCTION IF EXISTS fn_core_get_state_name;

DELIMITER $$

CREATE  FUNCTION fn_core_get_state_name(p_state_code VARCHAR(50)) RETURNS VARCHAR(100) 
    READS SQL DATA
BEGIN
	DECLARE v_state_name VARCHAR(60);

        SELECT  csm.state_name
        INTO    v_state_name
        FROM    core_state_master csm
        WHERE   csm.state_code = p_state_code LIMIT 1;

	RETURN(v_state_name);
END $$

DELIMITER ;
