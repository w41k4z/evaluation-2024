-- AUTH --

-- Ex: 'USER', 'ADMIN'
CREATE TABLE groups (
	id SERIAL PRIMARY KEY,
	group_name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE users (
	id SERIAL PRIMARY KEY,
	username VARCHAR(50) UNIQUE NOT NULL,
	password TEXT NOT NULL,
	group_id BIGINT REFERENCES groups(id) NOT NULL
);

-- CREATE TABLE group_authorities (
-- 	group_id bigint not null,
-- 	authority varchar(50) not null,
-- 	constraint fk_group_authorities_group foreign key(group_id) references groups(id)
-- );

-- create table group_members (
-- 	id SERIAL PRIMARY KEY,
-- 	users_id BIGINT REFERENCES users(id) NOT NULL,
-- 	group_id BIGINT REFERENCES groups(id) NOT NULL
-- );


-- MAIN TABLES --