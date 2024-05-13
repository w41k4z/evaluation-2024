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

-- MAIN TABLES --
-- Ex: m²
CREATE TABLE units (
	id SERIAL PRIMARY KEY,
	name VARCHAR(50) UNIQUE NOT NULL
);

-- Ex: Villa, ecole...
CREATE TABLE house_types (
	id SERIAL PRIMARY KEY,
	name VARCHAR(50) UNIQUE NOT NULL,
	duration DOUBLE PRECISION NOT NULL -- construction duration (day) (maybe a denormalized column)
);

-- Ex: Standard, Gold, VIP... 
CREATE TABLE finition_types (
	id SERIAL PRIMARY KEY,
	name VARCHAR(50) UNIQUE NOT NULL,
	majoration DOUBLE PRECISION NOT NULL DEFAULT 0
);

-- Ex: Travaux préparatoires, travaux de terrassement...
CREATE TABLE works (
	id SERIAL PRIMARY KEY,
	name VARCHAR(50) UNIQUE NOT NULL
);

-- Ex: Mur de soutenant, decapage des terrains meuble
CREATE TABLE work_details (
	id SERIAL PRIMARY KEY,
	works_id BIGINT REFERENCES works(id),
	designation VARCHAR(50) UNIQUE NOT NULL,
	units_id BIGINT REFERENCES units(id),
	price DOUBLE PRECISION NOT NULL -- denormalization (maybe)
);

-- Ex: Maison villa -> mur soutenant 10m²
CREATE TABLE house_construction_details (
	id SERIAL PRIMARY KEY,
	house_types_id BIGINT REFERENCES house_types(id) NOT NULL,
	work_details_id BIGINT REFERENCES work_details(id) NOT NULL,
	default_quantity DOUBLE PRECISION NOT NULL,
	CHECK(default_quantity >= 0)
);

-- Devis client
CREATE TABLE quotes (
	id SERIAL PRIMARY KEY,
	"date" TIMESTAMP NOT NULL,
	users_id BIGINT REFERENCES users(id) NOT NULL,
	house_types_id BIGINT REFERENCES house_types(id) NOT NULL,
	finition_types_id BIGINT REFERENCES finition_types(id) NOT NULL,
	finition_type_majoration DOUBLE PRECISION, -- denormalization
	construction_start_date TIMESTAMP NOT NULL,
	construction_end_date TIMESTAMP NOT NULL, -- change according to the quote details,
	total_price DOUBLE PRECISION, -- denormalization (total)
	CHECK(construction_end_date > construction_start_date)
);

CREATE TABLE quote_details (
	id SERIAL PRIMARY KEY,
	quotes_id BIGINT REFERENCES quotes(id) NOT NULL,
	works_id BIGINT REFERENCES works(id) NOT NULL, -- denormalizing the work_id instead of using the house_construction_details_id
	work_details_id BIGINT REFERENCES work_details(id) NOT NULL, -- denormalizing the work_details_id instead of using the house_construction_details_id
	quantity DOUBLE PRECISION NOT NULL, -- quantity denormalization
	unit_price DOUBLE PRECISION NOT NULL,
	CHECK(unit_price > 0)
);

CREATE TABLE payment (
	id SERIAL PRIMARY KEY,
	"date" TIMESTAMP NOT NULL,
	quotes_id BIGINT REFERENCES quotes(id) NOT NULL,
	amount DOUBLE PRECISION NOT NULL,
	CHECK(amount > 0)
);