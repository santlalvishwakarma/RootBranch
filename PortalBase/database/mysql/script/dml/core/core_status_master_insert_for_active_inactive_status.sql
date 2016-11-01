SELECT 'parameter master insert for emi scheme cheque Status code setup ......... starts ';

SET @v_insert_id = NULL;
SET @v_datetime_now = NOW();
SET @v_effective_date_from = '1900-01-01';
SET @v_effective_date_to = '2999-12-31';
SET @v_system_user = 'system';

/* for cheque status code */

INSERT INTO core_status_master
		(status_code, status_name, status_description, created_by, created_date, modified_by, modified_date)
VALUES('Active', 'Active', 'Active', @v_system_user, @v_datetime_now, @v_system_user, @v_datetime_now);

INSERT INTO core_status_master
		(status_code, status_name, status_description, created_by, created_date, modified_by, modified_date)
VALUES('Inactive', 'Inactive', 'Inactive', @v_system_user, @v_datetime_now, @v_system_user, @v_datetime_now);
