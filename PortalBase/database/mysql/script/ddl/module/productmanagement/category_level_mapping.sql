CREATE TABLE category_level_mapping(
	category_level_mapping_id INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
	category_id INT(10) UNSIGNED NOT NULL,
	level_no INT(10) NOT NULL,
	is_active TINYINT(1) NOT NULL DEFAULT 0,
	created_by VARCHAR(50) NOT NULL,
	created_date DATETIME NOT NULL,
	modified_by VARCHAR(50) NOT NULL,
	modified_date DATETIME NOT NULL,
	PRIMARY KEY (category_level_mapping_id),
	KEY FK_category_level_mapping_1 (category_id),
  	CONSTRAINT FK_category_level_mapping_1 FOREIGN KEY (category_id) REFERENCES category_master (category_id)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

