CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS school (
school_uid UUID NOT NULL PRIMARY KEY,
school_name VARCHAR(255) NOT NULL,
address VARCHAR(255) NOT NULL,
CONSTRAINT unique_address UNIQUE (address)
);

CREATE TABLE IF NOT EXISTS person (
person_uid UUID NOT NULL PRIMARY KEY,
first_name VARCHAR(100) NOT NULL,
last_name VARCHAR(100) NOT NULL,
age INT NOT NULL,
gender VARCHAR(6) NOT NULL,
school_uid UUID REFERENCES school (school_uid)
);

CREATE TABLE IF NOT EXISTS post (
post_uid UUID NOT NULL PRIMARY KEY,
description VARCHAR(1000) NOT NULL,
person_uid UUID REFERENCES person (person_uid)
);

CREATE TABLE IF NOT EXISTS friendlist (
requester_uid UUID NOT NULL REFERENCES person (person_uid),
addressee_uid UUID NOT NULL REFERENCES person (person_uid),
CONSTRAINT friendlist_pk PRIMARY KEY (requester_uid, addressee_uid)
);