CREATE TABLE category_property_mapping (
  category_property_mapping_id int(10) unsigned NOT NULL AUTO_INCREMENT,
  category_id int(10) unsigned NOT NULL,
  property_id int(10) unsigned NOT NULL,
  property_sequence int(10) DEFAULT NULL,
  is_active TINYINT(1) NOT NULL DEFAULT 0,
  created_by varchar(50) NOT NULL,
  created_date datetime NOT NULL,
  modified_by varchar(50) NOT NULL,
  modified_date datetime NOT NULL,
  PRIMARY KEY (category_property_mapping_id),
  KEY FK_category_property_mapping_1 (category_id),
  KEY FK_category_property_mapping_2 (property_id),
  CONSTRAINT FK_category_property_mapping_1 FOREIGN KEY (category_id) REFERENCES category_master (category_id),
  CONSTRAINT FK_category_property_mapping_2 FOREIGN KEY (property_id) REFERENCES property_master (property_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


