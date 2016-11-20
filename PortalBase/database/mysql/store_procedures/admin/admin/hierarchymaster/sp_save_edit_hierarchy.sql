DELIMITER $$

DROP PROCEDURE IF EXISTS sp_save_edit_hierarchy $$
CREATE PROCEDURE sp_save_edit_hierarchy (
IN p_product_hierarchy_id INT(10), 
IN p_product_hierarchy_code VARCHAR(25),
IN p_product_hierarchy_name VARCHAR(60),
IN p_product_hierarchy_description VARCHAR(100),
IN p_product_hierarchy_sequence VARCHAR(100),
IN p_is_active TINYINT(1),
IN p_user_login VARCHAR(50),
OUT p_error_code VARCHAR(50),
OUT p_error_message VARCHAR(500)
)

MODIFIES SQL DATA
DETERMINISTIC

BEGIN

	DECLARE v_last_inserted_id INT(10);

	IF p_product_hierarchy_id IS NOT NULL THEN
		UPDATE	hierarchy_master
		SET		hierarchy_code = p_product_hierarchy_code,
				hierarchy_name = p_product_hierarchy_name,
				hierarchy_description = p_product_hierarchy_description,
				hierarchy_sequence = p_product_hierarchy_sequence, 
				is_active = p_is_active,
				modified_by = p_user_login,
				modified_date = NOW()
		WHERE	hierarchy_id = p_product_hierarchy_id;

		SET v_last_inserted_id = p_product_hierarchy_id;

	ELSE

		INSERT INTO hierarchy_master(hierarchy_code, hierarchy_name, hierarchy_description,
									 hierarchy_sequence, is_active, created_by, modified_by, created_date, modified_date)
							  VALUES(p_product_hierarchy_code, p_product_hierarchy_name, p_product_hierarchy_description,
							  		 p_product_hierarchy_sequence, p_is_active, p_user_login, p_user_login, NOW(), NOW());
		
		SELECT LAST_INSERT_ID() INTO v_last_inserted_id;

	END IF;

	SELECT v_last_inserted_id AS hierarchy_id;

END $$

DELIMITER;


