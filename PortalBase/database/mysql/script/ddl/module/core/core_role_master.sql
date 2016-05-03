CREATE TABLE core_role_master (
  role_id int(10) unsigned NOT NULL AUTO_INCREMENT,
  role_code varchar(25) NOT NULL,
  role_name varchar(50) NOT NULL,
  role_description varchar(255) DEFAULT NULL,
  created_by varchar(50) NOT NULL,
  created_date datetime NOT NULL,
  modified_by varchar(50) NOT NULL,
  modified_date datetime NOT NULL,
  PRIMARY KEY (role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


