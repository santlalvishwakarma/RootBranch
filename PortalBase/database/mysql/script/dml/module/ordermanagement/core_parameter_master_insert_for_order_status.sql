SELECT 'Order Management parameter master Status code setup ......... starts ';

SET @v_insert_id = NULL;
SET @v_datetime_now = NOW();
SET @v_effective_from = '1900-01-01';
SET @v_effective_to = '2999-12-31';
SET @v_system_user = 'system';

/* for receipt status code */


INSERT INTO core_parameter_master 
(		param_code,sequence_number,param_description,value_data_type,value_text,value_numeric, value_date,
		record_deleted,effective_from,effective_to,created_by,created_date,modified_by,modified_date)
VALUES(	'ORDER_STATUS', 1, 'Status for current order transaction', 'S', 'Order Initiated',NULL , NULL, 
		0, @v_effective_from, @v_effective_to, @v_system_user, @v_datetime_now, @v_system_user, @v_datetime_now);


INSERT INTO core_parameter_master 
(		param_code,sequence_number,param_description,value_data_type,value_text,value_numeric, value_date,
		record_deleted,effective_from,effective_to,created_by,created_date,modified_by,modified_date)
VALUES(	'ORDER_STATUS', 2, 'Status for current order transaction', 'S', 'Order Cancelled',NULL , NULL, 
		0, @v_effective_from, @v_effective_to, @v_system_user, @v_datetime_now, @v_system_user, @v_datetime_now);
		

INSERT INTO core_parameter_master 
(		param_code,sequence_number,param_description,value_data_type,value_text,value_numeric, value_date,
		record_deleted,effective_from,effective_to,created_by,created_date,modified_by,modified_date)
VALUES(	'ORDER_STATUS', 3, 'Status for current order transaction', 'S', 'Saved to wishlist',NULL , NULL, 
		0, @v_effective_from, @v_effective_to, @v_system_user, @v_datetime_now, @v_system_user, @v_datetime_now);
		

INSERT INTO core_parameter_master 
(		param_code,sequence_number,param_description,value_data_type,value_text,value_numeric, value_date,
		record_deleted,effective_from,effective_to,created_by,created_date,modified_by,modified_date)
VALUES(	'ORDER_STATUS', 4, 'Status for current order transaction', 'S', 'Payment Initiated',NULL , NULL, 
		0, @v_effective_from, @v_effective_to, @v_system_user, @v_datetime_now, @v_system_user, @v_datetime_now);
		
		
INSERT INTO core_parameter_master 
(		param_code,sequence_number,param_description,value_data_type,value_text,value_numeric, value_date,
		record_deleted,effective_from,effective_to,created_by,created_date,modified_by,modified_date)
VALUES(	'ORDER_STATUS', 5, 'Status for current order transaction', 'S', 'Order Confirmed',NULL , NULL, 
		0, @v_effective_from, @v_effective_to, @v_system_user, @v_datetime_now, @v_system_user, @v_datetime_now);
		
		
INSERT INTO core_parameter_master 
(		param_code,sequence_number,param_description,value_data_type,value_text,value_numeric, value_date,
		record_deleted,effective_from,effective_to,created_by,created_date,modified_by,modified_date)
VALUES(	'ORDER_STATUS', 6, 'Status for current order transaction', 'S', 'Shipped',NULL , NULL, 
		0, @v_effective_from, @v_effective_to, @v_system_user, @v_datetime_now, @v_system_user, @v_datetime_now);
		
		
INSERT INTO core_parameter_master 
(		param_code,sequence_number,param_description,value_data_type,value_text,value_numeric, value_date,
		record_deleted,effective_from,effective_to,created_by,created_date,modified_by,modified_date)
VALUES(	'ORDER_STATUS', 7, 'Status for current order transaction', 'S', 'Delivered',NULL , NULL, 
		0, @v_effective_from, @v_effective_to, @v_system_user, @v_datetime_now, @v_system_user, @v_datetime_now);
		
		
INSERT INTO core_parameter_master 
(		param_code,sequence_number,param_description,value_data_type,value_text,value_numeric, value_date,
		record_deleted,effective_from,effective_to,created_by,created_date,modified_by,modified_date)
VALUES(	'ORDER_STATUS', 8, 'Status for current order transaction', 'S', 'Order Offline',NULL , NULL, 
		0, @v_effective_from, @v_effective_to, @v_system_user, @v_datetime_now, @v_system_user, @v_datetime_now);
		
		
		
		
		
		

