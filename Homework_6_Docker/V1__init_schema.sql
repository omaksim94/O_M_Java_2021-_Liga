CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS school (
school_uid UUID NOT NULL PRIMARY KEY,
name VARCHAR(255) NOT NULL,
address VARCHAR(255) NOT NULL,
CONSTRAINT unique_address UNIQUE (address)
);

CREATE TABLE IF NOT EXISTS teacher (
teacher_uid UUID NOT NULL PRIMARY KEY,
first_name VARCHAR(100) NOT NULL,
last_name VARCHAR(100) NOT NULL,
age INT NOT NULL,
gender VARCHAR(6) NOT NULL,
school_uid UUID REFERENCES school (school_uid)
);

CREATE TABLE IF NOT EXISTS subject (
subject_uid UUID NOT NULL PRIMARY KEY,
name VARCHAR(200) NOT NULL
);

CREATE TABLE IF NOT EXISTS student (
student_uid UUID NOT NULL PRIMARY KEY,
first_name VARCHAR(100) NOT NULL,
last_name VARCHAR(100) NOT NULL,
age INT NOT NULL,
gender VARCHAR(6) NOT NULL,
school_uid UUID REFERENCES school (school_uid)
);

CREATE TABLE IF NOT EXISTS teacher_subject (
teacher_uid UUID REFERENCES teacher (teacher_uid),
subject_uid UUID REFERENCES subject (subject_uid),
PRIMARY KEY (teacher_uid, subject_uid)
);

CREATE TABLE IF NOT EXISTS student_subject (
student_uid UUID REFERENCES student (student_uid),
subject_uid UUID REFERENCES subject (subject_uid),
PRIMARY KEY (student_uid, subject_uid)
);
