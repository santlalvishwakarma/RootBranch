CREATE TABLE core_newsletter_subscription (
  newsletter_subscription_id int(10) unsigned NOT NULL AUTO_INCREMENT,
  email_address varchar(255) NOT NULL,
  user_login varchar(100) DEFAULT NULL,
  created_by varchar(50) NOT NULL,
  created_date datetime NOT NULL,
  modified_by varchar(50) NOT NULL,
  modified_date datetime NOT NULL,
  PRIMARY KEY (newsletter_subscription_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;





