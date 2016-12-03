DELIMITER $$
DROP PROCEDURE IF EXISTS sp_hierarchy_category_level_mapped $$
CREATE PROCEDURE sp_hierarchy_category_level_mapped (
IN p_category_id INT(10),
IN p_level_no INT(10),
IN p_query VARCHAR(512),
OUT p_error_code VARCHAR(50),
OUT p_error_message VARCHAR(500)
)

MODIFIES SQL DATA
DETERMINISTIC

BEGIN

	DECLARE v_select_clause_1 VARCHAR(4096);
	DECLARE v_select_clause_2 VARCHAR(4096);
	DECLARE v_where_clause VARCHAR(4096);

	SET	v_select_clause_1	=	"SELECT hcm.hierarchy_category_mapping_id, hm.hierarchy_id, hm.hierarchy_code, hm.hierarchy_name,
							hcm.category_level_1 category_level_1_id,
							fn_get_category_code_based_on_id(hcm.category_level_1) category_level_1_code,
							fn_get_category_name_based_on_id(hcm.category_level_1) category_level_1_name,
							hcm.category_level_2 category_level_2_id,
							fn_get_category_code_based_on_id(hcm.category_level_2) category_level_2_code,
							fn_get_category_name_based_on_id(hcm.category_level_2) category_level_2_name,
							hcm.category_level_3 category_level_3_id,
							fn_get_category_code_based_on_id(hcm.category_level_3) category_level_3_code,
							fn_get_category_name_based_on_id(hcm.category_level_3) category_level_3_name,
							hcm.category_level_4 category_level_4_id,
							fn_get_category_code_based_on_id(hcm.category_level_4) category_level_4_code,
							fn_get_category_name_based_on_id(hcm.category_level_4) category_level_4_name
							FROM	hierarchy_master hm, hierarchy_category_mapping hcm
							WHERE	hm.hierarchy_id = hcm.hierarchy_id ";

	SET	v_select_clause_2	=	"SELECT hm1.hierarchy_id, hm1.hierarchy_code, hm1.hierarchy_name FROM hierarchy_master hm1 ";

	IF p_level_no = 1 THEN

		SET v_where_clause = CONCAT(" WHERE hm1.hierarchy_code LIKE '%", p_query, "%' ");

		SET @v_sql_statement = CONCAT(v_select_clause_2, v_where_clause);

		PREPARE stmnt3 FROM  @v_sql_statement;
		EXECUTE stmnt3;

	ELSEIF p_level_no = 2 THEN

		SET v_where_clause = CONCAT("AND hcm.category_level_1 IS NOT NULL AND hcm.category_level_2 IS NULL AND hcm.category_level_3 IS NULL
									 AND hcm.category_level_4 IS NULL AND hm.hierarchy_code LIKE '%", p_query ,"%'");

		SET @v_sql_statement = CONCAT(v_select_clause_1, v_where_clause);

		PREPARE stmnt3 FROM  @v_sql_statement;
		EXECUTE stmnt3;

	ELSEIF p_level_no = 3 THEN

		SET v_where_clause = CONCAT("AND hcm.category_level_1 IS NOT NULL AND hcm.category_level_2 IS NOT NULL
									 AND hcm.category_level_3 IS NULL AND hcm.category_level_4 IS NULL
							  		 AND hm.hierarchy_code LIKE '%", p_query ,"%'");

		SET @v_sql_statement = CONCAT(v_select_clause_1, v_where_clause);

		PREPARE stmnt3 FROM  @v_sql_statement;
		EXECUTE stmnt3;

	ELSEIF p_level_no = 4 THEN

		SET v_where_clause = CONCAT("AND hcm.category_level_1 IS NOT NULL AND hcm.category_level_2 IS NOT NULL 
							  		 AND hcm.category_level_3 IS NOT NULL AND hcm.category_level_4 IS NULL
							  		 AND hm.hierarchy_code LIKE '%", p_query ,"%'");

		SET @v_sql_statement = CONCAT(v_select_clause_1, v_where_clause);

		PREPARE stmnt3 FROM  @v_sql_statement;
		EXECUTE stmnt3;

	ELSEIF p_category_id IS NOT NULL THEN

		SET v_where_clause = CONCAT(" AND (hcm.category_level_1 = ", p_category_id, " OR hcm.category_level_2 = ", p_category_id,
									" OR hcm.category_level_3 = ", p_category_id, " OR hcm.category_level_4 = ", p_category_id,")");
		SET @v_sql_statement = CONCAT(v_select_clause_1, v_where_clause);

		PREPARE stmnt3 FROM  @v_sql_statement;
		EXECUTE stmnt3;

	END IF;

END $$
DELIMITER ;
