CREATE TABLE core_users_roles_mapping (
  users_roles_mapping_id int(10) unsigned NOT NULL AUTO_INCREMENT,
  user_id int(10) unsigned NOT NULL,
  role_id int(10) unsigned NOT NULL,
  created_by varchar(50) NOT NULL,
  created_date datetime NOT NULL,
  modified_by varchar(50) NOT NULL,
  modified_date datetime NOT NULL,
  PRIMARY KEY (users_roles_mapping_id),
  KEY fk_core_users_roles_mapping_1 (role_id),
  KEY fk_core_users_roles_mapping_2 (user_id),
  CONSTRAINT fk_core_users_roles_mapping_1 FOREIGN KEY (role_id) REFERENCES core_role_master (role_id),
  CONSTRAINT fk_core_users_roles_mapping_2 FOREIGN KEY (user_id) REFERENCES core_user_master (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


