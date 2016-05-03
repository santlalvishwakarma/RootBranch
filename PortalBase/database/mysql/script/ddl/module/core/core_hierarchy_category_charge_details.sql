CREATE TABLE core_hierarchy_category_charge_details (
  hierarchy_category_charge_details_id int(10) unsigned NOT NULL AUTO_INCREMENT,
  hierarchy_id int(10) unsigned NOT NULL,
  category_level_1_id int(10) unsigned DEFAULT NULL,
  category_level_2_id int(10) unsigned DEFAULT NULL,
  category_level_3_id int(10) unsigned DEFAULT NULL,
  category_level_4_id int(10) unsigned DEFAULT NULL,
  created_by varchar(100) NOT NULL,
  created_date datetime NOT NULL,
  modified_by varchar(100) NOT NULL,
  modified_date datetime NOT NULL,
  PRIMARY KEY (hierarchy_category_charge_details_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


