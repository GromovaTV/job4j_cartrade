create table if not exists brands(
  id serial primary key,
  name varchar(255)
);

create table if not exists bodies(
  id serial primary key,
  name varchar(255)
);

create table if not exists cars(
  id serial primary key,
  brand_id int not null references brands(id),
  body_id int not null references bodies(id)
);

create table if not exists photos(
  id serial primary key,
  path text
);

create table if not exists users (
  id serial primary key,
  name varchar(200),
  email varchar(200) UNIQUE,
  password TEXT
);

create table if not exists ads(
  id serial primary key,
  description text,
  sold boolean,
  price integer,
  car_id int not null references cars(id),
  user_id int not null references users(id)
);

create table if not exists ads_photos(
  id serial primary key,
  ads_id int not null references ads(id),
  photo_id int references photos(id)
);
