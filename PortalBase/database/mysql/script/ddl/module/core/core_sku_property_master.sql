CREATE TABLE core_sku_property_master (sku_property_id int(10) unsigned NOT NULL AUTO_INCREMENT,
									   sku_property_code varchar(25) DEFAULT NULL,
									   sku_property_name varchar(25) DEFAULT NULL,
									   sku_property_description varchar(25) DEFAULT NULL,
									   sku_property_type int(10) unsigned DEFAULT NULL,
									   sku_property_price int(10) unsigned DEFAULT NULL,
									   is_active tinyint(1) NOT NULL DEFAULT '0',
									   created_by varchar(50) NOT NULL,
									   created_date datetime NOT NULL,
									   modified_by varchar(50) NOT NULL,
									   modified_date datetime NOT NULL,
									   PRIMARY KEY (sku_property_id),
									   KEY fk_sku_property_type_parameter (sku_property_type),
									   CONSTRAINT fk_sku_property_type_parameter FOREIGN KEY (sku_property_type) REFERENCES core_parameter_master (parameter_id))
									   ENGINE=InnoDB DEFAULT CHARSET=utf8;