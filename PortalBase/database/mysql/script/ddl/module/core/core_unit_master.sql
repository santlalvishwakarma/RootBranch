CREATE TABLE core_unit_master(
	unit_id INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
	unit_code VARCHAR(25),
    unit_name VARCHAR(60),
    unit_description varchar(255),
    display_name varchar(25),
	is_active TINYINT(1) NOT NULL DEFAULT 0,
	created_by VARCHAR(50) NOT NULL,
	created_date DATETIME NOT NULL,
	modified_by VARCHAR(50) NOT NULL,
	modified_date DATETIME NOT NULL,
	PRIMARY KEY (unit_id)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

