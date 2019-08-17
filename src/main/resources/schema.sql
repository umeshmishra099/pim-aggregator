
create table IF NOT EXISTS product
(
  name varchar(255) not null,
  description varchar(255) not null,
  provider varchar(255) not null,
  available boolean not null,
  measurementUnits varchar(255) not null,
  createdDate DATE not null,
  updatedDate DATE null,
   primary key(name)
);