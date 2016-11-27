CREATE TABLE category_master(
	category_id INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
	category_code VARCHAR(25),
    category_name VARCHAR(60),
    category_description VARCHAR(100), /* used for onHover similar to alt tag*/
    publish_to_home_page tinyint(1) default 0,
    publish_position int,
    seo_title varchar(100),
    seo_keyword varchar(100),
    seo_description TEXT,
    image_url VARCHAR(4000),
	is_active TINYINT(1) NOT NULL DEFAULT 0,
	created_by VARCHAR(50) NOT NULL,
	created_date DATETIME NOT NULL,
	modified_by VARCHAR(50) NOT NULL,
	modified_date DATETIME NOT NULL,
	PRIMARY KEY (category_id)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

