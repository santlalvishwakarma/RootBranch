CREATE TABLE product_header(
	product_id INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
	product_code VARCHAR(25),
    product_name VARCHAR(60),
    product_description VARCHAR(255),
    status_code varchar(25) DEFAULT NULL,
	is_active TINYINT(1) NOT NULL DEFAULT 0,
	created_by VARCHAR(50) NOT NULL,
	created_date DATETIME NOT NULL,
	modified_by VARCHAR(50) NOT NULL,
	modified_date DATETIME NOT NULL,
	PRIMARY KEY (product_id)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

