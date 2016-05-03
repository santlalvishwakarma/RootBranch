CREATE TABLE core_status_master (
  status_code varchar(25) NOT NULL,
  status_name varchar(60) NOT NULL,
  status_description varchar(255) DEFAULT NULL,
  is_active TINYINT(1) NOT NULL DEFAULT 1,
  created_by varchar(50) NOT NULL,
  created_date datetime NOT NULL,
  modified_by varchar(50) NOT NULL,
  modified_date datetime NOT NULL,
  PRIMARY KEY (status_code),
  UNIQUE KEY UK_core_status_master_1 (status_code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



