CREATE TABLE product_sku_image_mapping(
	product_sku_image_mapping_id INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
	product_sku_id INT(10) UNSIGNED NOT NULL,
	image_type INT(10) DEFAULT NULL, /* Values: Default, Alternate (parameter_id) */
    thumbnail_image_url VARCHAR(4000),
    zoom_image_url VARCHAR(4000),
    sequence_no INT(10) DEFAULT NULL,
	is_active TINYINT(1) NOT NULL DEFAULT 0,
	created_by VARCHAR(50) NOT NULL,
	created_date DATETIME NOT NULL,
	modified_by VARCHAR(50) NOT NULL,
	modified_date DATETIME NOT NULL,
	PRIMARY KEY (product_sku_image_mapping_id),
	KEY FK_product_sku_image_mapping_1 (product_sku_id),
	CONSTRAINT FK_product_sku_image_mapping_1 FOREIGN KEY (product_sku_id) REFERENCES product_sku_header (product_sku_id)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

