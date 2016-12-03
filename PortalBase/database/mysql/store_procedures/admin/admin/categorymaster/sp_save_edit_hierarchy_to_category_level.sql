DELIMITER $$
DROP PROCEDURE IF EXISTS sp_save_edit_hierarchy_to_category_level $$
CREATE PROCEDURE sp_save_edit_hierarchy_to_category_level (
IN p_hierarchy_category_mapping_id INT(10), 
IN p_hierarchy_id INT(10),
IN p_category_level_1 INT(10),
IN p_category_level_2 INT(10),
IN p_category_level_3 INT(10),
IN p_category_level_4 INT(10),
IN p_category_level_5 INT(10),
IN p_is_active TINYINT(1),
IN p_user_login VARCHAR(50),
OUT p_error_code VARCHAR(50),
OUT p_error_message VARCHAR(500)
)

MODIFIES SQL DATA
DETERMINISTIC

BEGIN

	DECLARE v_last_inserted_id INT(10);
	DECLARE v_select_clause VARCHAR(1024);
	DECLARE v_where_clause VARCHAR(1024);
	DECLARE v_duplicate_mapping_count TINYINT(1);

	SET	v_select_clause = " SELECT COUNT(hierarchy_category_mapping_id) INTO @duplicate_mapping_count FROM hierarchy_category_mapping WHERE ";
	SET v_where_clause = CONCAT(" hierarchy_id = ", p_hierarchy_id);
	

	IF p_category_level_1 IS NOT NULL THEN
		SET v_where_clause = CONCAT(v_where_clause, " AND category_level_1 = ", p_category_level_1);
	END IF;

	IF p_category_level_2 IS NOT NULL THEN
		SET v_where_clause = CONCAT(v_where_clause, " AND category_level_2 = ", p_category_level_2);
	END IF;

	IF p_category_level_3 IS NOT NULL THEN
		SET v_where_clause = CONCAT(v_where_clause, " AND category_level_3 = ", p_category_level_3);
	END IF;

	IF p_category_level_4 IS NOT NULL THEN
		SET v_where_clause = CONCAT(v_where_clause, " AND category_level_4 = ", p_category_level_4);
	END IF;

	SET @v_sql_statement = CONCAT(v_select_clause, v_where_clause);

	PREPARE stmnt3 FROM  @v_sql_statement;
	EXECUTE stmnt3;
	DEALLOCATE PREPARE stmnt3;

	SELECT @duplicate_mapping_count INTO v_duplicate_mapping_count;

	IF v_duplicate_mapping_count > 0 THEN

		SET p_error_code = 'no_data_insert_db_excep_msg';
		SET p_error_message = CONCAT('Mapping already exist in database.');

	END IF;

	IF p_error_code IS NULL THEN
		IF p_hierarchy_category_mapping_id IS NOT NULL THEN
			UPDATE	hierarchy_category_mapping
			SET		hierarchy_id = p_hierarchy_id,
					category_level_1 = p_category_level_1,
					category_level_2 = p_category_level_2,
					category_level_3 = p_category_level_3,
					category_level_4 = p_category_level_4,
					category_level_5 = p_category_level_5,
					is_active = p_is_active,
					modified_by = p_user_login,
					modified_date = NOW()
			WHERE	hierarchy_category_mapping_id = p_hierarchy_category_mapping_id;
	
			SET v_last_inserted_id = p_hierarchy_category_mapping_id;
	
		ELSE
	
			INSERT INTO hierarchy_category_mapping(hierarchy_id, category_level_1, category_level_2, category_level_3, category_level_4,
												   category_level_5, is_active, created_by, created_date, modified_by, modified_date)
								 			VALUES(p_hierarchy_id, p_category_level_1, p_category_level_2, p_category_level_3, p_category_level_4,
								 				   p_category_level_5, p_is_active, p_user_login, NOW(), p_user_login, NOW());
	
			SELECT LAST_INSERT_ID() INTO v_last_inserted_id;
	
		END IF;
	END IF;

	SELECT v_last_inserted_id AS hierarchy_category_mapping_id, p_error_code, p_error_message;

END $$
DELIMITER ;
