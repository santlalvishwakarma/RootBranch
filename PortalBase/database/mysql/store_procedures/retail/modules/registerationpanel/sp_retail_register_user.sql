DELIMITER $$

DROP PROCEDURE IF EXISTS sp_retail_register_user $$

CREATE PROCEDURE sp_retail_register_user(
IN p_login_name VARCHAR(100),
IN p_first_name VARCHAR(50),
IN p_last_name VARCHAR(50),
IN p_primary_email_id VARCHAR(100),
IN p_conditions_accepted TINYINT(1),
IN p_password VARCHAR(50),
IN p_primary_phone_no VARCHAR(50),
OUT p_error_code VARCHAR(25),
OUT p_error_message VARCHAR(255)
)
MODIFIES SQL DATA
DETERMINISTIC 
BEGIN
	DECLARE v_success_flag VARCHAR(1) ;
	DECLARE v_error_message VARCHAR(100);
	DECLARE v_row_count INT(4) DEFAULT 0;
	DECLARE v_parameter_yes_id INT;
	DECLARE v_user_id INT;
	DECLARE v_billing_id INT;
	DECLARE v_shipping_id INT;
  	
	INSERT INTO core_user_master(user_login, login_password, first_name, last_name, primary_email_id, primary_phone_number,
								 condition_accepted, newsletter_subscription, sms_alert_subscription,
								 is_admin, is_active, created_by, created_date, modified_by, modified_date)
						  VALUES(p_login_name, MD5(p_password), p_first_name, p_last_name, p_primary_email_id, p_primary_phone_no,
								 p_conditions_accepted, 0, 0, 0, 1, 'system', NOW(), 'system', NOW());		

	SELECT last_insert_id() INTO v_user_id;

	IF v_user_id IS NOT NULL THEN
		INSERT INTO core_users_roles_mapping(users_roles_mapping_id, user_id, role_id, created_by, created_date, modified_by, modified_date)
									  VALUES(NULL, v_user_id, (SELECT role_id from core_role_master where role_code = 'RETAIL_USER'),
	  										 p_login_name, NOW(), p_login_name, NOW());

		INSERT INTO core_billing_address(created_by, created_date, modified_by, modified_date)
								  VALUES(p_login_name, NOW(), p_login_name, NOW());

		SELECT last_insert_id() INTO v_billing_id;

		INSERT INTO core_shipping_address(created_by, created_date, modified_by, modified_date)
								  VALUES(p_login_name, NOW(), p_login_name, NOW());

		SELECT last_insert_id() INTO v_shipping_id;

		UPDATE	core_user_master
		SET		billing_address_id = v_billing_id,
				shipping_address_id = v_shipping_id
		WHERE	user_id = v_user_id;

	END IF;
	
	SELECT ROW_COUNT() INTO v_row_count;
	
	IF v_user_id IS NOT NULL AND v_row_count != 0 THEN
		SET p_error_code = NULL;
	ELSE
		SET p_error_code = 'User Registration Failed at insert';
		SET p_error_message = 'Not able to create user';
	END IF;	
	
	select v_user_id, p_error_code, p_error_message;	

END $$
DELIMITER ;

