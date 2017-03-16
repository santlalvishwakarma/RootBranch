update core_parameter_master
set value_text = 'Quotation Initiated'
where parameter_id = 4;

update core_parameter_master
set value_text = 'Quotation Cancelled'
where parameter_id = 5;

update core_parameter_master
set value_text = 'Quotation Confirmed'
where parameter_id = 8;


update core_parameter_master
set value_text = 'Quotation Fulfilled'
where parameter_id = 10;
