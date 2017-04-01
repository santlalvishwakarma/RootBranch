CREATE TABLE category_size_mapping(
	category_size_mapping_id INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
	category_id INT(10) UNSIGNED NOT NULL,
	size_value_1 FLOAT(15,3) NOT NULL,
	size_value_2 FLOAT(15,3) NOT NULL,
	unit_id INT(10) UNSIGNED NOT NULL,
	is_active TINYINT(1) NOT NULL DEFAULT 0,
	created_by VARCHAR(50) NOT NULL,
	created_date DATETIME NOT NULL,
	modified_by VARCHAR(50) NOT NULL,
	modified_date DATETIME NOT NULL,
	PRIMARY KEY (category_size_mapping_id),
	KEY FK_core_category_size_mapping_1 (category_id),
	KEY FK_core_category_size_mapping_2 (unit_id),
	CONSTRAINT FK_core_category_size_mapping_1 FOREIGN KEY (category_id) REFERENCES category_master (category_id),
	CONSTRAINT FK_core_category_size_mapping_2 FOREIGN KEY (unit_id) REFERENCES core_unit_master (unit_id)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

