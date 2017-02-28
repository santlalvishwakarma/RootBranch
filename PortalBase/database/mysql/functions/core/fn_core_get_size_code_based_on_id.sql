
DROP FUNCTION IF EXISTS fn_core_get_size_code_based_on_id;

DELIMITER $$

CREATE  FUNCTION fn_core_get_size_code_based_on_id(p_size_id INT(10)) RETURNS VARCHAR(60) 
    READS SQL DATA
BEGIN
	DECLARE v_size_code VARCHAR(60);

        SELECT  csm.size_code
        INTO    v_size_code
        FROM    core_size_master csm
        WHERE   csm.size_id = p_size_id LIMIT 1;

	RETURN(v_size_code);
END $$

DELIMITER ;
