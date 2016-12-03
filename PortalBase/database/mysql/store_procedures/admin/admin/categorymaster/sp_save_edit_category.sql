DELIMITER $$
DROP PROCEDURE IF EXISTS sp_save_edit_category $$
CREATE PROCEDURE sp_save_edit_category(
IN p_product_category_id INT(10), 
IN p_product_category_code VARCHAR(25),
IN p_product_category_name VARCHAR(60),
IN p_product_category_description VARCHAR(100),
IN p_seo_title VARCHAR(100),
IN p_seo_keyword VARCHAR(100),
IN p_seo_description TEXT,
IN p_image_url VARCHAR(4000),
IN p_is_active TINYINT(1),
IN p_user_login VARCHAR(50),
OUT p_error_code VARCHAR(50),
OUT p_error_message VARCHAR(500)
)

MODIFIES SQL DATA
DETERMINISTIC

BEGIN

	DECLARE v_last_inserted_id INT(10);

	IF p_product_category_id IS NOT NULL THEN
		UPDATE	category_master
		SET		category_code = p_product_category_code,
				category_name = p_product_category_name,
				category_description = p_product_category_description,
				seo_title = p_seo_title,
				seo_keyword = p_seo_keyword,
				seo_description = p_seo_description,
				image_url = p_image_url,
				is_active = p_is_active,
				modified_by = p_user_login,
				modified_date = NOW()
		WHERE	category_id = p_product_category_id;

		SET v_last_inserted_id = p_product_category_id;

	ELSE

		INSERT INTO category_master(category_code, category_name, category_description, seo_title, seo_keyword, seo_description, image_url,
									is_active, created_by, modified_by, created_date, modified_date)
							 VALUES(p_product_category_code, p_product_category_name, p_product_category_description, p_seo_title,
							 		p_seo_keyword, p_seo_description, p_image_url, p_user_login, p_user_login, NOW(), NOW());
		
		SELECT LAST_INSERT_ID() INTO v_last_inserted_id;

	END IF;

	SELECT v_last_inserted_id AS category_id;
END $$
DELIMITER ;
