CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS roles (
id UUID PRIMARY KEY,
role VARCHAR(100) NOT NULL
);

INSERT INTO roles (id, role) VALUES('43e3e306-fe87-4187-9788-b08db3ab8d65', 'USER');
INSERT INTO roles (id, role) VALUES('5ba3e5c4-3439-11ec-8d3d-0242ac130003', 'ADMIN');

create table users (
   id UUID PRIMARY KEY,
   first_name VARCHAR(100) NOT NULL,
   last_name VARCHAR(100) NOT NULL,
   phone_number VARCHAR(50) NOT NULL,
   email VARCHAR(50) NOT NULL,
   password VARCHAR(255) NOT NULL,
   is_account_non_expired boolean NOT NULL,
   is_account_non_locked boolean NOT NULL,
   is_credentials_non_expired boolean NOT NULL,
   is_enabled boolean NOT NULL,
   role UUID NOT NULL REFERENCES roles (id)
);

insert into users (id, first_name, last_name, phone_number, email, password, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled, role) values ('091c3206-d0a1-4f55-b06d-a68d185ee786', 'Ade', 'Jenkison', '+86 459 277 9295', 'ajenkison0@craigslist.org', '$2a$10$u2vVoSsMLJmZ9fXrnGbmVu/6150jqYPkDjv/O.kfRq7JbRoCEIcLK', true, true, true, true, '5ba3e5c4-3439-11ec-8d3d-0242ac130003');
insert into users (id, first_name, last_name, phone_number, email, password, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled, role) values ('636a8bd0-a13e-4043-91fb-0d1f3e8b79b9', 'Marcellina', 'Divisek', '+33 514 326 3664', 'mdivisek1@sfgate.com', '$2a$10$u2vVoSsMLJmZ9fXrnGbmVu/6150jqYPkDjv/O.kfRq7JbRoCEIcLK', true, true, true, true, '43e3e306-fe87-4187-9788-b08db3ab8d65');
insert into users (id, first_name, last_name, phone_number, email, password, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled, role) values ('553e3428-261d-4b6f-bf95-b6bc2c3d4ee5', 'Wenona', 'Chessill', '+48 989 914 7685', 'wchessill2@gnu.org', '$2a$10$u2vVoSsMLJmZ9fXrnGbmVu/6150jqYPkDjv/O.kfRq7JbRoCEIcLK', true, true, true, true, '43e3e306-fe87-4187-9788-b08db3ab8d65');
insert into users (id, first_name, last_name, phone_number, email, password, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled, role) values ('6f3aa80b-1e74-4f73-9991-555f9bdf2c26', 'Killian', 'Aynscombe', '+7 796 171 3838', 'kaynscombe3@nytimes.com', '$2a$10$u2vVoSsMLJmZ9fXrnGbmVu/6150jqYPkDjv/O.kfRq7JbRoCEIcLK', true, true, true, true, '43e3e306-fe87-4187-9788-b08db3ab8d65');
insert into users (id, first_name, last_name, phone_number, email, password, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled, role) values ('7945ceff-04c6-4dac-88bc-5f375162cb42', 'Elise', 'Dowty', '+44 923 997 9470', 'edowty4@etsy.com', '$2a$10$u2vVoSsMLJmZ9fXrnGbmVu/6150jqYPkDjv/O.kfRq7JbRoCEIcLK', true, true, true, true, '43e3e306-fe87-4187-9788-b08db3ab8d65');
insert into users (id, first_name, last_name, phone_number, email, password, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled, role) values ('b7ad8091-ad08-436f-b4ea-5fa819fbc5fd', 'Tami', 'McKinley', '+351 760 490 6845', 'tmckinley5@github.com', '$2a$10$u2vVoSsMLJmZ9fXrnGbmVu/6150jqYPkDjv/O.kfRq7JbRoCEIcLK', true, true, true, true, '43e3e306-fe87-4187-9788-b08db3ab8d65');
insert into users (id, first_name, last_name, phone_number, email, password, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled, role) values ('4278c833-32b3-46d9-9320-66c1a6f023bf', 'Suki', 'Klasen', '+385 950 295 8382', 'sklasen6@imdb.com', '$2a$10$u2vVoSsMLJmZ9fXrnGbmVu/6150jqYPkDjv/O.kfRq7JbRoCEIcLK', true, true, true, true, '43e3e306-fe87-4187-9788-b08db3ab8d65');
insert into users (id, first_name, last_name, phone_number, email, password, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled, role) values ('3d394817-fd41-40a6-8528-f384d7f7c8fe', 'Megan', 'Gallahue', '+251 683 380 6695', 'mgallahue7@gravatar.com', '$2a$10$u2vVoSsMLJmZ9fXrnGbmVu/6150jqYPkDjv/O.kfRq7JbRoCEIcLK', true, true, true, true, '43e3e306-fe87-4187-9788-b08db3ab8d65');
insert into users (id, first_name, last_name, phone_number, email, password, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled, role) values ('31badef4-85fc-4893-b2ed-47604fae27e5', 'Brendon', 'Lealle', '+46 734 238 0034', 'blealle8@reverbnation.com', '$2a$10$u2vVoSsMLJmZ9fXrnGbmVu/6150jqYPkDjv/O.kfRq7JbRoCEIcLK', true, true, true, true, '43e3e306-fe87-4187-9788-b08db3ab8d65');
insert into users (id, first_name, last_name, phone_number, email, password, is_account_non_expired, is_account_non_locked, is_credentials_non_expired, is_enabled, role) values ('e26e12fd-23ec-4f2b-8774-8d36341cf276', 'Inga', 'Hindshaw', '+63 266 907 7114', 'ihindshaw9@amazonaws.com', '$2a$10$u2vVoSsMLJmZ9fXrnGbmVu/6150jqYPkDjv/O.kfRq7JbRoCEIcLK', true, true, true, true, '43e3e306-fe87-4187-9788-b08db3ab8d65');

CREATE TABLE IF NOT EXISTS bookings (
id UUID PRIMARY KEY,
date_time TIMESTAMP NOT NULL,
booking_created_at TIMESTAMP NOT NULL,
status VARCHAR(100) NOT NULL,
person UUID NOT NULL REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS booking_status (
id UUID PRIMARY KEY,
name VARCHAR(100) NOT NULL
);

