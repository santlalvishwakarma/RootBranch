CREATE TABLE product_hierarchy_category_mapping (
  product_hierarchy_category_mapping_id int(10) unsigned NOT NULL AUTO_INCREMENT,
  product_id int(10) unsigned DEFAULT NULL,
  hierarchy_id int(10)  DEFAULT NULL,
  category_level_1 int(10)  DEFAULT NULL,
  category_level_2 int(10)  DEFAULT NULL,
  category_level_3 int(10)  DEFAULT NULL,
  category_level_4 int(10)  DEFAULT NULL,
  category_level_5 int(10)  DEFAULT NULL,
  is_default TINYINT(1) NOT NULL DEFAULT 0,
  is_active TINYINT(1) NOT NULL DEFAULT 0,
  created_by varchar(50) NOT NULL,
  created_date datetime NOT NULL,
  modified_by varchar(50) NOT NULL,
  modified_date datetime NOT NULL,
  PRIMARY KEY (product_hierarchy_category_mapping_id),
  KEY FK_product_hierarchy_category_mapping_1 (product_id),
  CONSTRAINT FK_product_hierarchy_category_mapping_1 FOREIGN KEY (product_id) REFERENCES product_header (product_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


