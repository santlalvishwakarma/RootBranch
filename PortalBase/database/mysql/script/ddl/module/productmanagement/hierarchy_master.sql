CREATE TABLE hierarchy_master(
	hierarchy_id INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
	hierarchy_code VARCHAR(25),
    hierarchy_name VARCHAR(60),
    hierarchy_description Varchar(255),
    hierarchy_sequence INT(10) NOT NULL,
	is_active TINYINT(1) NOT NULL DEFAULT 0,
	created_by VARCHAR(50) NOT NULL,
	created_date DATETIME NOT NULL,
	modified_by VARCHAR(50) NOT NULL,
	modified_date DATETIME NOT NULL,
	PRIMARY KEY (hierarchy_id)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

