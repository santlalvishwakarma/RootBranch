
DROP FUNCTION IF EXISTS fn_product_get_product_id_based_on_code;

DELIMITER $$

CREATE  FUNCTION fn_product_get_product_id_based_on_code(p_product_code VARCHAR(25)) RETURNS INT(10) 
    READS SQL DATA
BEGIN
	DECLARE v_product_id INT(10);

        SELECT  ph.product_id
        INTO    v_product_id
        FROM    product_header ph
        WHERE   ph.product_code = p_product_code LIMIT 1;

	RETURN(v_product_id);
END $$

DELIMITER ;
