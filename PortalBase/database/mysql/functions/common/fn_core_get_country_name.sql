
DROP FUNCTION IF EXISTS fn_core_get_country_name;

DELIMITER $$

CREATE  FUNCTION fn_core_get_country_name(p_country_code VARCHAR(50)) RETURNS VARCHAR(100) 
    READS SQL DATA
BEGIN
	DECLARE v_country_name VARCHAR(60);

        SELECT  ccm.country_name
        INTO    v_country_name
        FROM    core_country_master ccm
        WHERE   ccm.country_code = p_country_code LIMIT 1;

	RETURN(v_country_name);
END $$

DELIMITER ;


