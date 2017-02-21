
DROP FUNCTION IF EXISTS fn_get_sku_code;

DELIMITER $$

CREATE  FUNCTION fn_get_sku_code(p_sku_id INT(10)) RETURNS VARCHAR(25) 
    READS SQL DATA
BEGIN

	DECLARE v_sku_code VARCHAR(25);

	SELECT  psh.sku_code
	INTO    v_sku_code
	FROM    product_sku_header psh
	WHERE   psh.product_sku_id = p_sku_id LIMIT 1;

	RETURN(v_sku_code);
END $$

DELIMITER ;
