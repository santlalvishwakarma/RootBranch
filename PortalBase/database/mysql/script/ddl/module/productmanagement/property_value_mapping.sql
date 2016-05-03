CREATE TABLE property_value_mapping (
  property_value_mapping_id int(10) unsigned NOT NULL AUTO_INCREMENT,
  property_id int(10) unsigned NOT NULL,
  property_value varchar(100) NOT NULL,
  created_by varchar(50) NOT NULL,
  created_date datetime NOT NULL,
  modified_by varchar(50) NOT NULL,
  modified_date datetime NOT NULL,
  PRIMARY KEY (property_value_mapping_id),
  KEY FK_property_value_mapping_1 (property_id),
  CONSTRAINT FK_property_value_mapping_1 FOREIGN KEY (property_id) REFERENCES property_master (property_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


