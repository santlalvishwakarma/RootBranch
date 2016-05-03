CREATE TABLE core_charges_apply (
  core_charges_apply_id int(10) unsigned NOT NULL AUTO_INCREMENT,
  charges_mode varchar(45) DEFAULT NULL,
  is_active tinyint(1) DEFAULT NULL,
  mode_desciption varchar(500) DEFAULT NULL,
  created_by varchar(100) NOT NULL,
  created_date datetime NOT NULL,
  modified_by varchar(100) NOT NULL,
  modified_date datetime NOT NULL,
  PRIMARY KEY (core_charges_apply_id)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;


