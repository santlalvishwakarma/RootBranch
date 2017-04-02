CREATE TABLE core_guest_master (
  guest_id int(10) unsigned NOT NULL AUTO_INCREMENT,
  name varchar(60) NOT NULL,
  email_id varchar(50) NOT NULL,
  phone_number varchar(15) NOT NULL,
  is_active TINYINT(1) DEFAULT NULL,
  created_by varchar(50) NOT NULL,
  created_date datetime NOT NULL,
  modified_by varchar(50) NOT NULL,
  modified_date datetime NOT NULL,
  PRIMARY KEY (guest_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



