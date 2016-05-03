CREATE TABLE core_website (
  core_website_id int(10) unsigned NOT NULL AUTO_INCREMENT,
  website_name varchar(25) NOT NULL,
  website_url varchar(255) NOT NULL,
  created_by varchar(100) NOT NULL,
  created_date datetime NOT NULL,
  modified_by varchar(100) NOT NULL,
  modified_date datetime NOT NULL,
  PRIMARY KEY (core_website_id),
  UNIQUE KEY idx_core_website_website_url_uk (website_url)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


