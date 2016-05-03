CREATE TABLE order_payment_mode_detail (
  order_payment_mode_detail_id int(10) unsigned NOT NULL AUTO_INCREMENT,
  order_header_id int(10) unsigned DEFAULT NULL,
  payment_type int(10) DEFAULT NULL,
  amount float(15,3) DEFAULT NULL,
  transaction_no varchar(100) DEFAULT NULL,
  bank_code varchar(25) DEFAULT NULL,
  is_confirm tinyint(1) DEFAULT NULL,
  PRIMARY KEY (order_payment_mode_detail_id),
  KEY FK_order_payment_mode_detail_1 (order_header_id),
  CONSTRAINT FK_order_payment_mode_detail_1 FOREIGN KEY (order_header_id) REFERENCES order_header (order_header_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


