CREATE TABLE product_sku_header(
	product_sku_id INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
	product_id INT(10) UNSIGNED NOT NULL,
	sku_code VARCHAR(25),
    sku_name VARCHAR(60),
    sku_description VARCHAR(255),
    
    seo_title varchar(100),
    seo_keyword varchar(100),
    seo_description TEXT,
    
    default_thumbnail_image_url VARCHAR(4000),
    default_zoom_image_url VARCHAR(4000),
    
    base_price FLOAT(15,3),
    discount_amount FLOAT(15,3),
    discount_percent FLOAT(15,3),
	final_base_price FLOAT(15,3),
	
    is_active TINYINT(1) NOT NULL DEFAULT 0,
	created_by VARCHAR(50) NOT NULL,
	created_date DATETIME NOT NULL,
	modified_by VARCHAR(50) NOT NULL,
	modified_date DATETIME NOT NULL,
	PRIMARY KEY (product_sku_id),
	KEY FK_product_sku_header_1 (product_id),
	CONSTRAINT FK_product_sku_header_1 FOREIGN KEY (product_id) REFERENCES product_header (product_id)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

