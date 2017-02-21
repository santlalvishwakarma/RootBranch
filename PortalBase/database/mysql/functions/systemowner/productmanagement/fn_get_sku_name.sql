
DROP FUNCTION IF EXISTS fn_get_sku_name;

DELIMITER $$

CREATE  FUNCTION fn_get_sku_name(p_sku_id INT(10)) RETURNS VARCHAR(25) 
    READS SQL DATA
BEGIN

	DECLARE v_sku_name VARCHAR(25);

	SELECT  psh.sku_name
	INTO    v_sku_name
	FROM    product_sku_header psh
	WHERE   psh.product_sku_id = p_sku_id LIMIT 1;

	RETURN(v_sku_name);
END $$

DELIMITER ;
