create table cars(
  id serial primary key,
  brand_id int not null references brands(id),
  body_id int not null references bodies(id)
);

create table brands(
  id serial primary key,
  name varchar(255)
);

create table bodies(
  id serial primary key,
  name varchar(255)
);

create table photos(
  id serial primary key,
  path text
);

create table users (
  id serial primary key,
  name varchar(2000),
  email TEXT UNIQUE,
  password TEXT
);

create table ads(
  id serial primary key,
  description text,
  sold boolean,
  car_id int not null references cars(id),
  user_id int not null references users(id)
);