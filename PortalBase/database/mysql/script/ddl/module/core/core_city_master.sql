CREATE TABLE core_city_master (
  city_code varchar(25) NOT NULL,
  city_name varchar(60) NOT NULL,
  city_description varchar(255) DEFAULT NULL,
  is_active tinyint(1) NOT NULL DEFAULT 1,
  state_code varchar(25) NOT NULL,
  created_by varchar(50) NOT NULL,
  created_date datetime NOT NULL,
  modified_by varchar(50) NOT NULL,
  modified_date datetime NOT NULL,
  PRIMARY KEY (city_code),
  UNIQUE KEY UK_core_city_master_1 (city_code),
  KEY FK_core_city_master_1 (state_code),
  CONSTRAINT FK_core_city_master_1 FOREIGN KEY (state_code) REFERENCES core_state_master (state_code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


