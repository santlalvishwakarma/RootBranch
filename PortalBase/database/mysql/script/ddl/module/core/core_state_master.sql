CREATE TABLE core_state_master (
  state_code varchar(25) NOT NULL,
  state_name varchar(60) NOT NULL,
  state_description varchar(255) DEFAULT NULL,
  is_active tinyint(1) NOT NULL DEFAULT 1,
  country_code varchar(25) NOT NULL,
  created_by varchar(50) NOT NULL,
  created_date datetime NOT NULL,
  modified_by varchar(50) NOT NULL,
  modified_date datetime NOT NULL,
  PRIMARY KEY (state_code),
  UNIQUE KEY UK_core_state_master_1 (state_code),
  KEY FK_core_state_master_1 (country_code),
  CONSTRAINT FK_core_state_master_1 FOREIGN KEY (country_code) REFERENCES core_country_master (country_code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



