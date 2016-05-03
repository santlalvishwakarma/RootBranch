CREATE TABLE core_currency_master (
  currency_code varchar(25) NOT NULL,
  currency_name varchar(60) NOT NULL,
  currency_symbol varchar(5) NOT NULL,
  country_code varchar(25) NOT NULL,
  created_by varchar(50) NOT NULL,
  created_date datetime NOT NULL,
  modified_by varchar(50) NOT NULL,
  modified_date datetime NOT NULL,
  PRIMARY KEY (currency_code),
  UNIQUE KEY UK_core_currency_master_1 (currency_code),
  KEY FK_core_currency_master_1 (country_code),
  CONSTRAINT FK_core_currency_master_1 FOREIGN KEY (country_code) REFERENCES core_country_master (country_code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



