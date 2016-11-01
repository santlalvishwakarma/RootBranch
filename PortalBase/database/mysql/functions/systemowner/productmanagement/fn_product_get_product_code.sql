
DROP FUNCTION IF EXISTS fn_product_get_product_code;

DELIMITER $$

CREATE  FUNCTION fn_product_get_product_code(p_product_id INT(10)) RETURNS VARCHAR(25) 
    READS SQL DATA
BEGIN
	DECLARE v_product_code VARCHAR(25);

        SELECT  ph.product_code
        INTO    v_product_code
        FROM    product_header ph
        WHERE   ph.product_id = p_product_id LIMIT 1;

	RETURN(v_product_code);
END $$

DELIMITER ;
