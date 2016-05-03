CREATE TABLE hierarchy_category_mapping (
  hierarchy_category_mapping_id int(10) unsigned NOT NULL AUTO_INCREMENT,
  hierarchy_id int(10) unsigned NOT NULL,
  category_level_1 int(10) unsigned NOT NULL,
  category_level_2 int(10) unsigned DEFAULT NULL,
  category_level_3 int(10) unsigned DEFAULT NULL,
  category_level_4 int(10) unsigned DEFAULT NULL,
  category_level_5 int(10) unsigned DEFAULT NULL,
  is_active TINYINT(1) NOT NULL DEFAULT 0,
  created_by varchar(50) NOT NULL,
  created_date datetime NOT NULL,
  modified_by varchar(50) NOT NULL,
  modified_date datetime NOT NULL,
  PRIMARY KEY (hierarchy_category_mapping_id),
  KEY FK_hierarchy_category_mapping_1 (hierarchy_id),
  KEY FK_hierarchy_category_mapping_2 (category_level_1),
  KEY FK_hierarchy_category_mapping_3 (category_level_2),
  KEY FK_hierarchy_category_mapping_4 (category_level_3),
  KEY FK_hierarchy_category_mapping_5 (category_level_4),
  KEY FK_hierarchy_category_mapping_6 (category_level_5),
  CONSTRAINT FK_hierarchy_category_mapping_1 FOREIGN KEY (hierarchy_id) REFERENCES hierarchy_master (hierarchy_id),
  CONSTRAINT FK_hierarchy_category_mapping_2 FOREIGN KEY (category_level_1) REFERENCES category_master (category_id),
  CONSTRAINT FK_hierarchy_category_mapping_3 FOREIGN KEY (category_level_2) REFERENCES category_master (category_id),
  CONSTRAINT FK_hierarchy_category_mapping_4 FOREIGN KEY (category_level_3) REFERENCES category_master (category_id),
  CONSTRAINT FK_hierarchy_category_mapping_5 FOREIGN KEY (category_level_4) REFERENCES category_master (category_id),
  CONSTRAINT FK_hierarchy_category_mapping_6 FOREIGN KEY (category_level_5) REFERENCES category_master (category_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


