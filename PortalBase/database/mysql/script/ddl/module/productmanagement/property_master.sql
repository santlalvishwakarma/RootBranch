CREATE TABLE property_master(
	property_id INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
	property_code VARCHAR(25),
    property_name VARCHAR(60),
	is_active TINYINT(1) NOT NULL DEFAULT 0,
	created_by VARCHAR(50) NOT NULL,
	created_date DATETIME NOT NULL,
	modified_by VARCHAR(50) NOT NULL,
	modified_date DATETIME NOT NULL,
	PRIMARY KEY (property_id)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

