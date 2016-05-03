CREATE TABLE core_shipping_address (
  shipping_address_id int(10) unsigned NOT NULL AUTO_INCREMENT,
  address_line1 varchar(100) DEFAULT NULL,
  address_line2 varchar(100) DEFAULT NULL,
  address_line3 varchar(100) DEFAULT NULL,
  country_code varchar(25) DEFAULT NULL,
  state_code varchar(25) DEFAULT NULL,
  city_code varchar(25) DEFAULT NULL,
  zip_code varchar(25) DEFAULT NULL,
  email_1 varchar(50) DEFAULT NULL,
  email_2 varchar(50) DEFAULT NULL,
  contact_person_1 varchar(400) DEFAULT NULL,
  contact_person_2 varchar(400) DEFAULT NULL,
  landmark varchar(50) DEFAULT NULL,
  created_by varchar(50) NOT NULL,
  created_date datetime NOT NULL,
  modified_by varchar(50) NOT NULL,
  modified_date datetime NOT NULL,
  PRIMARY KEY (shipping_address_id),
  KEY FK_core_shipping_address_1 (country_code),
  KEY FK_core_shipping_address_2 (state_code),
  KEY FK_core_shipping_address_3 (city_code),
  CONSTRAINT FK_core_shipping_address_1 FOREIGN KEY (country_code) REFERENCES core_country_master (country_code),
  CONSTRAINT FK_core_shipping_address_2 FOREIGN KEY (state_code) REFERENCES core_state_master (state_code),
  CONSTRAINT FK_core_shipping_address_3 FOREIGN KEY (city_code) REFERENCES core_city_master (city_code)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
  
  
  
  