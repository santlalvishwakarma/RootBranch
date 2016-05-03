
DROP FUNCTION IF EXISTS fn_core_get_city_name;

DELIMITER $$

CREATE  FUNCTION fn_core_get_city_name(p_city_code VARCHAR(50)) RETURNS VARCHAR(100) 
    READS SQL DATA
BEGIN
	DECLARE v_city_name VARCHAR(60);

        SELECT  ccm.city_name
        INTO    v_city_name
        FROM    core_city_master ccm
        WHERE   ccm.city_code = p_city_code LIMIT 1;

	RETURN(v_city_name);
END $$

DELIMITER ;
