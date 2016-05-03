CREATE TABLE core_user_master (
  user_id int(10) unsigned NOT NULL AUTO_INCREMENT,
  user_login varchar(50) NOT NULL,
  login_password varchar(50) DEFAULT NULL,
  first_name varchar(60) NOT NULL,
  middle_name varchar(60) DEFAULT NULL,
  last_name varchar(60) NOT NULL,
  billing_address_id int(10) unsigned DEFAULT NULL,
  shipping_address_id int(10) unsigned DEFAULT NULL,
  primary_email_id varchar(50) DEFAULT NULL,
  alternate_email_id varchar(50) DEFAULT NULL,
  primary_phone_number varchar(15) DEFAULT NULL,
  alternate_phone_number varchar(15) DEFAULT NULL,
  birth_date date DEFAULT NULL,
  anniversary_date date DEFAULT NULL,
  marital_status int(10) unsigned DEFAULT NULL,
  is_admin TINYINT(1) DEFAULT NULL,
  is_active TINYINT(1) DEFAULT NULL,
  condition_accepted TINYINT(1) DEFAULT NULL,
  newsletter_subscription tinyint(1) DEFAULT NULL,
  sms_alert_subscription tinyint(1) DEFAULT NULL,
  created_by varchar(50) NOT NULL,
  created_date datetime NOT NULL,
  modified_by varchar(50) NOT NULL,
  modified_date datetime NOT NULL,
  PRIMARY KEY (user_id),
  KEY FK_core_user_master_1 (billing_address_id),
  KEY FK_core_user_master_2 (shipping_address_id),
  CONSTRAINT FK_core_user_master_1 FOREIGN KEY (billing_address_id) REFERENCES core_billing_address (billing_address_id),
  CONSTRAINT FK_core_user_master_2 FOREIGN KEY (shipping_address_id) REFERENCES core_shipping_address (shipping_address_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



