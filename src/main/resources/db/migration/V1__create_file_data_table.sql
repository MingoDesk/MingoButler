create table file_data (
  file_id varchar (36) unique not null,
  ticked_id varchar (40) not null,
  message_id varchar (40) not null,
  file_name varchar(40) not null,
  url varchar (256) not null,
  created_date timestamp not null default current_timestamp
);