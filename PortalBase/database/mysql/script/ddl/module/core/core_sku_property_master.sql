CREATE TABLE core_sku_property_master(
									  sku_property_id INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
									  sku_property_code VARCHAR(25) DEFAULT NULL,
									  sku_property_name VARCHAR(25) DEFAULT NULL,
									  sku_property_description VARCHAR(25) DEFAULT NULL,
									  sku_property_type VARCHAR(25) DEFAULT NULL,
									  sku_property_price INT(10) UNSIGNED DEFAULT NULL,
									  is_active TINYINT(1) NOT NULL DEFAULT '0',
									  created_by VARCHAR(50) NOT NULL,
									  created_date DATETIME NOT NULL,
									  modified_by VARCHAR(50) NOT NULL,
									  modified_date DATETIME NOT NULL,
									  PRIMARY KEY (sku_property_id))
									  ENGINE=INNODB DEFAULT CHARSET=utf8;