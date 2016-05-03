CREATE TABLE core_roles (
  core_roles_id int(10) unsigned NOT NULL AUTO_INCREMENT,
  role_code varchar(25) NOT NULL,
  role_name varchar(25) NOT NULL,
  role_description varchar(255) DEFAULT NULL,
  created_by varchar(100) NOT NULL,
  created_date datetime NOT NULL,
  modified_by varchar(100) NOT NULL,
  modified_date datetime NOT NULL,
  PRIMARY KEY (core_roles_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


