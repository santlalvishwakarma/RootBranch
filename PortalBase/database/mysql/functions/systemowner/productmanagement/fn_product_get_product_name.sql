
DROP FUNCTION IF EXISTS fn_product_get_product_name;

DELIMITER $$

CREATE  FUNCTION fn_product_get_product_name(p_product_id INT(10)) RETURNS VARCHAR(60) 
    READS SQL DATA
BEGIN
	DECLARE v_product_name VARCHAR(60);

        SELECT  ph.product_name
        INTO    v_product_name
        FROM    product_header ph
        WHERE   ph.product_id = p_product_id LIMIT 1;

	RETURN(v_product_name);
END $$

DELIMITER ;
