SET Foreign_key_checks = 0;

truncate customer;
truncate orders;
truncate order_item;
truncate address;

SET Foreign_key_checks = 1;

alter table customer add unique (email);
