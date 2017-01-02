CREATE TABLE core_color_master(
	color_id INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
	color_code VARCHAR(25),
    color_name VARCHAR(60),
    color_description varchar(255),
	is_active TINYINT(1) NOT NULL DEFAULT 0,
	created_by VARCHAR(50) NOT NULL,
	created_date DATETIME NOT NULL,
	modified_by VARCHAR(50) NOT NULL,
	modified_date DATETIME NOT NULL,
	PRIMARY KEY (color_id)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

