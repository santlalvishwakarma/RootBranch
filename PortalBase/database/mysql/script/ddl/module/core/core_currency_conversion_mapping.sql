CREATE TABLE core_currency_conversion_mapping (
  currency_conversion_mapping_id int(10) unsigned NOT NULL AUTO_INCREMENT,
  parent_currency_code varchar(25) NOT NULL,
  mapped_currency_code varchar(25) NOT NULL,
  conversion_multiplier float(15,3) NOT NULL,
  created_by varchar(50) NOT NULL,
  created_date datetime NOT NULL,
  modified_by varchar(50) NOT NULL,
  modified_date datetime NOT NULL,
  PRIMARY KEY (currency_conversion_mapping_id),
  KEY FK_core_currency_conversion_mapping_1 (parent_currency_code),
  KEY FK_core_currency_conversion_mapping_2 (mapped_currency_code),
  CONSTRAINT FK_core_currency_conversion_mapping_1 FOREIGN KEY (parent_currency_code) REFERENCES core_currency_master (currency_code),
  CONSTRAINT FK_core_currency_conversion_mapping_2 FOREIGN KEY (mapped_currency_code) REFERENCES core_currency_master (currency_code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



