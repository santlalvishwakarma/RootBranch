alter table core_property_value_mapping
add column size_code varchar(25) after size_id,
add column size_name varchar(60) after size_code,
add column unit_code varchar(25) after unit_id,
add column unit_name varchar(60) after unit_code;
