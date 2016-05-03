CREATE TABLE core_secondry_addresses (
  secondry_addresses_id int(10) unsigned NOT NULL AUTO_INCREMENT,
  secondry_address_name varchar(50) NOT NULL,
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
  PRIMARY KEY (secondry_addresses_id),
  KEY FK_core_secondry_addresses_1 (country_code),
  KEY FK_core_secondry_addresses_2 (state_code),
  KEY FK_core_secondry_addresses_3 (city_code),
  CONSTRAINT FK_core_secondry_addresses_1 FOREIGN KEY (country_code) REFERENCES core_country_master (country_code),
  CONSTRAINT FK_core_secondry_addresses_2 FOREIGN KEY (state_code) REFERENCES core_state_master (country_code),
  CONSTRAINT FK_core_secondry_addresses_3 FOREIGN KEY (city_code) REFERENCES core_city_master (city_code)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
  
  
  
  