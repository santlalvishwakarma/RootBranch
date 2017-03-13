alter table product_sku_size_mapping
drop foreign key FK_product_sku_size_mapping_3,
drop column property_value_mapping_id;

alter table product_sku_size_mapping
ADD COLUMN  size_id INT(10) UNSIGNED NOT NULL AFTER product_sku_id,
ADD COLUMN  property_value VARCHAR(60) AFTER size_id,
ADD COLUMN  unit_id INT(10) UNSIGNED AFTER property_value,
ADD KEY FK_product_sku_size_mapping_3 (size_id),
ADD KEY FK_product_sku_size_mapping_4 (unit_id),
ADD CONSTRAINT FK_product_sku_size_mapping_3 FOREIGN KEY (size_id) REFERENCES core_size_master (size_id),
ADD CONSTRAINT FK_product_sku_size_mapping_4 FOREIGN KEY (unit_id) REFERENCES core_unit_master (unit_id);