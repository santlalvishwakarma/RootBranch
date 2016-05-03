CREATE TABLE core_country_master (
  country_code varchar(25) NOT NULL,
  country_name varchar(60) NOT NULL,
  country_description varchar(255) DEFAULT NULL,
  is_active TINYINT(1) NOT NULL DEFAULT 1,
  country_code_iso3 varchar(100) DEFAULT NULL,
  created_by varchar(50) NOT NULL,
  created_date datetime NOT NULL,
  modified_by varchar(50) NOT NULL,
  modified_date datetime NOT NULL,
  PRIMARY KEY (country_code),
   UNIQUE KEY UK_core_country_master_1 (country_code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



