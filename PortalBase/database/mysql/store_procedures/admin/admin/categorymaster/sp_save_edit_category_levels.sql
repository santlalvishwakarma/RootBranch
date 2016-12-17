DELIMITER $$
DROP PROCEDURE IF EXISTS sp_save_edit_category_levels $$
CREATE PROCEDURE sp_save_edit_category_levels(
IN p_category_level_mapping_id INT(10), 
IN p_category_id INT(10),
IN p_level_no INT(10),
IN p_is_active TINYINT(1),
IN p_is_delete TINYINT(1),
IN p_user_login VARCHAR(50),
OUT p_error_code VARCHAR(50),
OUT p_error_message VARCHAR(500)
)

MODIFIES SQL DATA
DETERMINISTIC

BEGIN

	DECLARE v_last_inserted_id INT(10);

	IF p_category_level_mapping_id IS NOT NULL THEN

		IF p_is_delete = 1 THEN

			DELETE FROM category_level_mapping
			WHERE  category_level_mapping_id = p_category_level_mapping_id;

		ELSE

			UPDATE	category_level_mapping
			SET		category_id = p_category_id,
					level_no = p_level_no,
					is_active = p_is_active,
					modified_by = p_user_login,
					modified_date = NOW()
			WHERE	category_level_mapping_id = p_category_level_mapping_id;

		END IF;

		SET v_last_inserted_id = p_category_level_mapping_id;

	ELSE

		INSERT INTO category_level_mapping(category_id, level_no, is_active, created_by, modified_by, created_date, modified_date)
							 		VALUES(p_category_id, p_level_no, p_is_active, p_user_login, p_user_login, NOW(), NOW());
		
		SELECT LAST_INSERT_ID() INTO v_last_inserted_id;

	END IF;

	SELECT v_last_inserted_id AS category_level_mapping_id;
END $$
DELIMITER ;
