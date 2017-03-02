CREATE TABLE product_sku_material_mapping(
	product_sku_material_mapping_id INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
	product_id INT(10) UNSIGNED NOT NULL,
	product_sku_id INT(10) UNSIGNED NOT NULL,
	material_id INT(10) UNSIGNED NOT NULL,
	is_active TINYINT(1) NOT NULL DEFAULT 0,
	created_by VARCHAR(50) NOT NULL,
	created_date DATETIME NOT NULL,
	modified_by VARCHAR(50) NOT NULL,
	modified_date DATETIME NOT NULL,
	PRIMARY KEY (product_sku_material_mapping_id),
	KEY FK_product_sku_material_mapping_1 (product_id),
	KEY FK_product_sku_material_mapping_2 (product_sku_id),
	KEY FK_product_sku_material_mapping_3 (material_id),
  	CONSTRAINT FK_product_sku_material_mapping_1 FOREIGN KEY (product_id) REFERENCES product_header (product_id),
  	CONSTRAINT FK_product_sku_material_mapping_2 FOREIGN KEY (product_sku_id) REFERENCES product_sku_header (product_sku_id),
  	CONSTRAINT FK_product_sku_material_mapping_3 FOREIGN KEY (material_id) REFERENCES core_material_master (material_id)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

