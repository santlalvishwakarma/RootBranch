CREATE TABLE product_sku_stock_level(
	product_sku_stock_level_id INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
	product_id INT(10) UNSIGNED NOT NULL,
	product_sku_id INT(10) UNSIGNED NOT NULL,
	size_id INT(10) UNSIGNED DEFAULT NULL,
	reorder_level FLOAT(15,3),
	available_quantity FLOAT(15,3),
	blocked_quantity FLOAT(15,3),
	is_active TINYINT(1) NOT NULL DEFAULT 0,
	created_by VARCHAR(50) NOT NULL,
	created_date DATETIME NOT NULL,
	modified_by VARCHAR(50) NOT NULL,
	modified_date DATETIME NOT NULL,
	PRIMARY KEY (product_sku_stock_level_id),
	KEY FK_product_stock_level_1 (product_id),
	KEY FK_product_stock_level_2 (product_sku_id),
	KEY FK_product_stock_level_3 (size_id),
  	CONSTRAINT FK_product_stock_level_1 FOREIGN KEY (product_id) REFERENCES product_header (product_id),
  	CONSTRAINT FK_product_stock_level_2 FOREIGN KEY (product_sku_id) REFERENCES product_sku_header (product_sku_id),
  	CONSTRAINT FK_product_stock_level_13 FOREIGN KEY (size_id) REFERENCES core_size_master (size_id)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

