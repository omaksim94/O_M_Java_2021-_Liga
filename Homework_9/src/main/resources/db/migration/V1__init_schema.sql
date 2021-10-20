CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS school (
school_uid UUID NOT NULL PRIMARY KEY,
school_name VARCHAR(255) NOT NULL,
address VARCHAR(1024) NOT NULL,
CONSTRAINT unique_address UNIQUE (address)
);

CREATE TABLE IF NOT EXISTS person (
person_uid UUID NOT NULL PRIMARY KEY,
first_name VARCHAR(100) NOT NULL,
last_name VARCHAR(100) NOT NULL,
age INT NOT NULL,
gender VARCHAR(6) NOT NULL,
school_uid UUID REFERENCES school (school_uid),
username VARCHAR(100) NOT NULL,
password VARCHAR(200) NOT NULL,
isAccountNonExpired boolean NOT NULL,
isAccountNonLocked boolean NOT NULL,
isCredentialsNonExpired boolean NOT NULL,
isEnabled  boolean NOT NULL
);

CREATE TABLE IF NOT EXISTS post (
post_uid UUID NOT NULL PRIMARY KEY,
description VARCHAR(1024) NOT NULL,
person_uid UUID REFERENCES person (person_uid)
);

CREATE TABLE IF NOT EXISTS friendlist (
friendlink_id VARCHAR(73) NOT NULL PRIMARY KEY,
person_uid UUID NOT NULL REFERENCES person (person_uid),
friend_uid UUID NOT NULL REFERENCES person (person_uid)
);

CREATE SEQUENCE IF NOT EXISTS authority_sequence;

CREATE TABLE IF NOT EXISTS authority (
authority_uid BIGSERIAL PRIMARY KEY,
authority VARCHAR(100) NOT NULL
);

INSERT INTO authority (authority) VALUES('USER');
INSERT INTO authority (authority) VALUES('ADMIN');

CREATE TABLE IF NOT EXISTS dialogue (
id VARCHAR(73) PRIMARY KEY,
sender_uid UUID NOT NULL REFERENCES person (person_uid),
receiver_uid UUID NOT NULL REFERENCES person (person_uid)
);

CREATE TABLE IF NOT EXISTS message (
id UUID PRIMARY KEY,
date_time TIMESTAMP NOT NULL,
text VARCHAR(500) NOT NULL,
sender_uid UUID NOT NULL REFERENCES person (person_uid),
receiver_uid UUID NOT NULL REFERENCES person (person_uid),
dialogue_id VARCHAR(73) NOT NULL REFERENCES dialogue (id)
);