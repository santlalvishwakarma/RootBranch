
DROP FUNCTION IF EXISTS fn_get_parameter_sequence;
DELIMITER $$

CREATE  FUNCTION fn_get_parameter_sequence(p_parameter_id INT(10)) RETURNS INT(4) 
    READS SQL DATA
BEGIN
		DECLARE v_sequence_number INT(4);

        SELECT  sequence_number
        INTO    v_sequence_number
        FROM    core_parameter_master
        WHERE   parameter_id = p_parameter_id LIMIT 1;

		RETURN v_sequence_number;

END $$
DELIMITER ;
