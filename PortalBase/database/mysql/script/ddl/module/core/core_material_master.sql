CREATE TABLE core_material_master(
	material_id INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
	material_code VARCHAR(25),
    material_name VARCHAR(60),
    material_description varchar(255),
	is_active TINYINT(1) NOT NULL DEFAULT 0,
	created_by VARCHAR(50) NOT NULL,
	created_date DATETIME NOT NULL,
	modified_by VARCHAR(50) NOT NULL,
	modified_date DATETIME NOT NULL,
	PRIMARY KEY (material_id)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

