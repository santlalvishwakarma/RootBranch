
DROP FUNCTION IF EXISTS fn_get_parameter_value_text;
DELIMITER $$

CREATE  FUNCTION fn_get_parameter_value_text(p_parameter_id INT(10)) RETURNS VARCHAR(255) 
    READS SQL DATA
BEGIN
		DECLARE v_value_text VARCHAR(255);

        SELECT  value_text
        INTO    v_value_text
        FROM    core_parameter_master
        WHERE   parameter_id = p_parameter_id LIMIT 1;

		RETURN v_value_text;
END $$
DELIMITER ;

