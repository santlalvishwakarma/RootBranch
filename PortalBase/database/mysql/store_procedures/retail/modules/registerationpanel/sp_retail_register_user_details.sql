DELIMITER $$

DROP PROCEDURE IF EXISTS sp_retail_register_user_details $$

CREATE PROCEDURE sp_retail_register_user_details(
IN p_login_name VARCHAR(100),
IN p_password VARCHAR(50),
IN p_billing_first_name VARCHAR(50),
IN p_billing_last_name VARCHAR(50),
IN p_primary_email_id VARCHAR(100),
IN p_billing_phone VARCHAR(15),
IN p_permanent_address_line1 VARCHAR(100),
IN p_permanent_address_line2 VARCHAR(100),
IN p_permanent_address_city VARCHAR(50),
IN p_permanent_address_zip_code VARCHAR(15),
IN p_permanent_address_country VARCHAR(25),
IN p_permanent_address_state VARCHAR(25),
IN p_shipping_address_line1 VARCHAR(100),
IN p_shipping_address_line2 VARCHAR(100),
IN p_shipping_address_city VARCHAR(50),
IN p_shipping_address_zip_code VARCHAR(15),
IN p_shipping_address_country VARCHAR(25),
IN p_conditions_accepted TINYINT(1),
IN P_shipping_address_state VARCHAR(25),
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
  	
	INSERT INTO core_user_master(
		user_login,
		login_password,
		first_name,
		last_name,
		primary_email_id,
		primary_phone_number,
		permanent_address_country,
		condition_accepted,
		newsletter_subscription,
		sms_alert_subscription,
		is_admin,
		is_active,
		created_by,
		created_date,
		modified_by,
		modified_date,
		permanent_address_line1,
		permanent_address_line2,
		permanent_address_city,
		permanent_address_zip_code,
		permanent_address_state,
		shipping_address_line1,
		shipping_address_line2,
		shipping_address_city,
		shipping_address_zip_code,
		shipping_address_state,
		shipping_address_country)
	VALUES(
		p_login_name,
		MD5(p_password),
		p_billing_first_name,
		p_billing_last_name,
		p_primary_email_id,
		p_billing_phone,
		p_permanent_address_country,
		p_conditions_accepted,
		0,
		0,
		0,
		1,
		'system',
		NOW(),
		'system',
		NOW(),
		p_permanent_address_line1,
		p_permanent_address_line2,
		p_permanent_address_city,
		p_permanent_address_zip_code,
		p_permanent_address_state,
		p_shipping_address_line1,
		p_shipping_address_line2,
		p_shipping_address_city,
		p_shipping_address_zip_code,
		P_shipping_address_state,
		p_shipping_address_country);		
	
	SELECT last_insert_id() INTO v_user_id;
    
    IF v_user_id IS NOT NULL THEN
	 INSERT INTO core_users_roles_mapping (
	  users_roles_mapping_id,
	  user_id,
	  role_id,
	  created_by,
	  created_date,
	  modified_by,
	  modified_date) VALUES(
	  NULL,
	  v_user_id,
	  (SELECT role_id from core_role_master where role_code = 'RETAIL_USER'),
	  p_login_name,
	  NOW(),
	  p_login_name,
	  NOW()
	  );
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

