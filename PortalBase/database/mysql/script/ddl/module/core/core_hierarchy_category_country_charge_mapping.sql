CREATE TABLE core_hierarchy_category_country_charge_mapping (
  hierarchy_category_country_charge_mapping_id int(10) unsigned NOT NULL AUTO_INCREMENT,
  country_code VARCHAR(25) DEFAULT NULL,
  currency_id int(10) DEFAULT NULL,
  hierarchy_category_charge_details_id int(10) unsigned NOT NULL,
  delivery_charge float(15,3) DEFAULT NULL,
  processing_charge float(15,3) DEFAULT NULL,
  duties_charge float(15,3) DEFAULT NULL,
  express_charge float(15,3) DEFAULT NULL,
  created_by varchar(50) NOT NULL,
  created_date datetime NOT NULL,
  modified_by varchar(50) NOT NULL,
  modified_date datetime NOT NULL,
  PRIMARY KEY (hierarchy_category_country_charge_mapping_id),
  KEY FK_core_hierarchy_category_country_charge_mapping_1 (country_code),
  KEY FK_core_hierarchy_category_country_charge_mapping_2 (hierarchy_category_charge_details_id),
  CONSTRAINT FK_core_hierarchy_category_country_charge_mapping_1 FOREIGN KEY (country_code) REFERENCES core_country_master (country_code),
  CONSTRAINT FK_core_hierarchy_category_country_charge_mapping_2 FOREIGN KEY (hierarchy_category_charge_details_id) REFERENCES core_hierarchy_category_charge_details (hierarchy_category_charge_details_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


