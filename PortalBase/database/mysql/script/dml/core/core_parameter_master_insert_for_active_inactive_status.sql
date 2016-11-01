SELECT 'Retail Data Setup ......... starts ';
SET @v_insert_id = NULL;
SET @v_datetime_now = NOW();
SET @v_effective_date_from = '1900-01-01';
SET @v_effective_date_to = '2999-12-31';
SET @v_system_user = 'system';



INSERT INTO core_parameter_master 
(		parameter_id,param_code,sequence_number,param_description,value_data_type,value_text,value_numeric, value_date,
		record_deleted,effective_from,effective_to,created_by,created_date,modified_by,modified_date)
VALUES(	@v_insert_id, 'ACTIVE_INACTIVE_STATUS', 1, 'Active', 'S', 'Active',NULL , NULL, 0, 
		@v_effective_date_from, @v_effective_date_to, @v_system_user, @v_datetime_now, @v_system_user, @v_datetime_now);
		

		
INSERT INTO core_parameter_master 
(		parameter_id,param_code,sequence_number,param_description,value_data_type,value_text,value_numeric, value_date,
		record_deleted,effective_from,effective_to,created_by,created_date,modified_by,modified_date)
VALUES(	@v_insert_id, 'ACTIVE_INACTIVE_STATUS', 2, 'Inactive', 'S', 'Inactive',NULL , NULL, 0, 
		@v_effective_date_from, @v_effective_date_to, @v_system_user, @v_datetime_now, @v_system_user, @v_datetime_now);
		
		
		
		