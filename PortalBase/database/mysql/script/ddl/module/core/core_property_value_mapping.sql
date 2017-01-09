CREATE TABLE core_property_value_mapping (
  property_value_mapping_id int(10) unsigned NOT NULL AUTO_INCREMENT,
  size_id INT(10) UNSIGNED NOT NULL,
  property_value VARCHAR(60) DEFAULT NULL,
  unit_id INT(10) UNSIGNED DEFAULT NULL,
  is_active TINYINT(1) DEFAULT NULL,
  created_by varchar(50) NOT NULL,
  created_date datetime NOT NULL,
  modified_by varchar(50) NOT NULL,
  modified_date datetime NOT NULL,
  PRIMARY KEY (property_value_mapping_id),
  KEY FK_core_property_value_mapping_1 (size_id),
  KEY FK_core_property_value_mapping_2 (unit_id),
  CONSTRAINT FK_core_property_value_mapping_1 FOREIGN KEY (size_id) REFERENCES core_size_master (size_id),
  CONSTRAINT FK_core_property_value_mapping_2 FOREIGN KEY (unit_id) REFERENCES core_unit_master (unit_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



