CREATE TABLE core_store_location_master (
  store_location_id int(10) unsigned NOT NULL AUTO_INCREMENT,
  store_location_code varchar(25) NOT NULL,
  store_location_name varchar(60) NOT NULL,
  store_location_description varchar(255) DEFAULT NULL,
  store_address_line1 varchar(100) NOT NULL,
  store_address_line2 varchar(100) DEFAULT NULL,
  store_address_line3 varchar(100) DEFAULT NULL,
  store_address_city varchar(50) NOT NULL,
  store_address_zip_code varchar(15) NOT NULL,
  store_address_state varchar(100) NOT NULL,
  store_address_country varchar(100) NOT NULL,
  store_image_url varchar(255) DEFAULT NULL,
  store_phone_number varchar(15) DEFAULT NULL,
  store_fax_number varchar(15) DEFAULT NULL,
  store_email_id varchar(60) DEFAULT NULL,
  store_area varchar(50) NOT NULL,
  latitude varchar(30) DEFAULT NULL,
  longitude varchar(30) DEFAULT NULL,
  store_time datetime DEFAULT NULL,
  created_by varchar(50) NOT NULL,
  created_date datetime NOT NULL,
  modified_by varchar(50) NOT NULL,
  modified_date datetime NOT NULL,
  PRIMARY KEY (store_location_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


