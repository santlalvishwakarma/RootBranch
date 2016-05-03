CREATE TABLE core_user_secondry_address_mapping (
user_id int(10) unsigned NOT NULL,
secondry_addresses_id int(10) unsigned NOT NULL,
secondry_address_name varchar(50) NOT NULL,
PRIMARY KEY (user_id, secondry_addresses_id),
 KEY FK_core_user_secondry_address_mapping_1 (user_id),
 KEY FK_core_user_secondry_address_mapping_2 (secondry_addresses_id),
 CONSTRAINT FK_core_user_secondry_address_mapping_1 FOREIGN KEY (user_id) REFERENCES core_user_master (user_id),
 CONSTRAINT FK_core_user_secondry_address_mapping_2 FOREIGN KEY (secondry_addresses_id) REFERENCES core_secondry_addresses (secondry_addresses_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;



