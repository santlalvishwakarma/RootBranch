CREATE TABLE core_courier_master (
  courier_id int(10) unsigned NOT NULL AUTO_INCREMENT,
  courier_code varchar(25) NOT NULL,
  courier_name varchar(60) NOT NULL,
  courier_description varchar(255) DEFAULT NULL,
  created_by varchar(100) NOT NULL,
  created_date datetime NOT NULL,
  modified_by varchar(100) NOT NULL,
  modified_date datetime NOT NULL,
  PRIMARY KEY (courier_id),
  UNIQUE KEY UK_core_courier_master_1 (courier_code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


