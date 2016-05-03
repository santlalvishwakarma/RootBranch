DELIMITER $$

DROP PROCEDURE IF EXISTS sp_retail_subscribe_to_news_letter $$

CREATE PROCEDURE sp_retail_subscribe_to_news_letter(
IN p_email_id VARCHAR(25),
IN p_user_login VARCHAR(50),
OUT p_error_code VARCHAR(25),
OUT p_error_message VARCHAR(255)
)
BEGIN
	
	DECLARE v_counter INT;
	
	SELECT COUNT(1) INTO v_counter
	FROM core_newsletter_subscription
	WHERE email_address = p_email_id;
	
	IF v_counter > 0 THEN
	
		SET p_error_code = "user_already_exist";
		SET p_error_message = CONCAT("email address ", p_email_id, " already subscribe for news letter");
	
	END IF;
	
	IF p_error_code IS NULL THEN
	
		INSERT INTO core_newsletter_subscription(email_address, user_login, created_by, created_date, modified_by, modified_date) 
		VALUES(p_email_id,p_user_login,'system',NOW(),'system',NOW());
	
	END IF;

	SELECT p_error_code, p_error_message;
	
	
END $$

DELIMITER ;

