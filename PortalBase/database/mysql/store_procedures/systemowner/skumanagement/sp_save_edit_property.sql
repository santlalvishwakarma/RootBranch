DELIMITER $$
DROP PROCEDURE IF EXISTS sp_save_edit_property $$
CREATE PROCEDURE sp_save_edit_property (
IN p_sku_property_id INT(10),
IN p_sku_property_code VARCHAR(25),
IN p_sku_property_name VARCHAR(60),
IN p_sku_property_description VARCHAR(100),
IN p_is_active TINYINT(1),
IN p_sku_property_price INT(10),
IN p_sku_property_type INT(10),
IN p_user_login VARCHAR(50),
OUT p_error_code VARCHAR(50),
OUT p_error_message VARCHAR(500)
)

MODIFIES SQL DATA
DETERMINISTIC

BEGIN

	DECLARE v_last_inserted_id INT(10);

	IF p_sku_property_id IS NOT NULL THEN
		UPDATE	core_sku_property_master
		SET		sku_property_code = p_sku_property_code,
				sku_property_name = p_sku_property_name,
				sku_property_description = p_sku_property_description,
				sku_property_type = p_sku_property_type,
				sku_property_price = p_sku_property_price,
				is_active = p_is_active,
				modified_by = p_user_login,
				modified_date = NOW()
		WHERE	sku_property_id = p_sku_property_id;

		SET v_last_inserted_id = p_sku_property_id;

	ELSE

		INSERT INTO core_sku_property_master(sku_property_id, sku_property_code, sku_property_name,  sku_property_description, sku_property_type, sku_property_price,
									is_active, created_by, modified_by, created_date, modified_date)
							 VALUES(p_sku_property_id, p_sku_property_code, p_sku_property_name, p_sku_property_description, p_sku_property_type, p_sku_property_price,
							 		p_is_active, p_user_login, p_user_login, NOW(), NOW());
		
		SELECT LAST_INSERT_ID() INTO v_last_inserted_id;

	END IF;

	SELECT v_last_inserted_id AS sku_property_id;

END $$
DELIMITER ;
