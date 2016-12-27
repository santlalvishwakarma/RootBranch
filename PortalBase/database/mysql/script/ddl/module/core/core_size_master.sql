CREATE TABLE core_size_master(
	size_id INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
	size_code VARCHAR(25),
    size_name VARCHAR(60),
	is_active TINYINT(1) NOT NULL DEFAULT 0,
	created_by VARCHAR(50) NOT NULL,
	created_date DATETIME NOT NULL,
	modified_by VARCHAR(50) NOT NULL,
	modified_date DATETIME NOT NULL,
	PRIMARY KEY (size_id)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

