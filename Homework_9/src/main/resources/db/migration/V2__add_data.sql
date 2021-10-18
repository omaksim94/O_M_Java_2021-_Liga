INSERT INTO school (school_uid, school_name, address) VALUES(uuid_generate_v1(), 'School_1', 'Saint-Petersburg');
INSERT INTO school (school_uid, school_name, address) VALUES(uuid_generate_v1(), 'School_2', 'Vladivostok');
INSERT INTO school (school_uid, school_name, address) VALUES(uuid_generate_v1(), 'School_3', 'Moscow');

INSERT INTO person (person_uid, first_name, last_name, age, gender, username, password,
isAccountNonExpired, isAccountNonLocked, isCredentialsNonExpired, isEnabled) VALUES(uuid_generate_v1(), 'Ann', 'Ivanova', 21, 'female',
'ann', '$2a$10$N/f2lPebtsT1EHLps0FkruAzjD4jLVD6tOXiYf3gRqtr8/tUOqut2', true, true, true, true);
INSERT INTO person (person_uid, first_name, last_name, age, gender, username, password,
isAccountNonExpired, isAccountNonLocked, isCredentialsNonExpired, isEnabled) VALUES(uuid_generate_v1(), 'John', 'Doe', 30, 'male',
'john', '$2a$10$N/f2lPebtsT1EHLps0FkruAzjD4jLVD6tOXiYf3gRqtr8/tUOqut2', true, true, true, true);
INSERT INTO person (person_uid, first_name, last_name, age, gender, username, password,
isAccountNonExpired, isAccountNonLocked, isCredentialsNonExpired, isEnabled) VALUES(uuid_generate_v1(), 'Mary', 'Johnson', 26, 'female',
'mary', '$2a$10$N/f2lPebtsT1EHLps0FkruAzjD4jLVD6tOXiYf3gRqtr8/tUOqut2', true, true, true, true);
INSERT INTO person (person_uid, first_name, last_name, age, gender, username, password,
isAccountNonExpired, isAccountNonLocked, isCredentialsNonExpired, isEnabled) VALUES(uuid_generate_v1(), 'Anthony', 'Robertson', 17, 'male',
'anthony', '$2a$10$N/f2lPebtsT1EHLps0FkruAzjD4jLVD6tOXiYf3gRqtr8/tUOqut2', true, true, true, true);
INSERT INTO person (person_uid, first_name, last_name, age, gender, username, password,
isAccountNonExpired, isAccountNonLocked, isCredentialsNonExpired, isEnabled) VALUES(uuid_generate_v1(), 'Michael', 'Santana', 24, 'male',
'admin', '$2a$10$N/f2lPebtsT1EHLps0FkruAzjD4jLVD6tOXiYf3gRqtr8/tUOqut2', true, true, true, true);


CREATE TABLE IF NOT EXISTS account_authority (
account_authority_id BIGSERIAL PRIMARY KEY,
person_id UUID NOT NULL REFERENCES person (person_uid),
authority_id BIGINT NOT NULL REFERENCES authority (authority_uid)
);
