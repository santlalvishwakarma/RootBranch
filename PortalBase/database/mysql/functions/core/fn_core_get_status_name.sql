DELIMITER $$

DROP FUNCTION IF EXISTS fn_core_get_status_name $$
CREATE FUNCTION fn_core_get_status_name(p_stauts_code VARCHAR(60)) RETURNS VARCHAR(60)
    READS SQL DATA
BEGIN
DECLARE output_text VARCHAR(60);
       
        SELECT  status_name INTO output_text
        FROM    core_status_master cum
        WHERE   cum.status_code = p_stauts_code LIMIT 1;       
        
RETURN(output_text);
END $$

DELIMITER ;