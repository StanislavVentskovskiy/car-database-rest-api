CREATE SCHEMA IF NOT EXISTS cardatabase;

DROP TABLE IF EXISTS cardatabase.types
    CASCADE;
DROP TABLE IF EXISTS cardatabase.models
    CASCADE;
DROP TABLE IF EXISTS cardatabase.makes
    CASCADE;
DROP TABLE IF EXISTS cardatabase.cars
    CASCADE;

CREATE TABLE IF NOT EXISTS cardatabase.makes
(
    id BIGSERIAL NOT NULL,
    name character varying,
    CONSTRAINT makes_pkey PRIMARY KEY (id)
)

    TABLESPACE pg_default;

CREATE TABLE IF NOT EXISTS cardatabase.models
(
    id BIGSERIAL NOT NULL,
    name character varying,
    CONSTRAINT models_pkey PRIMARY KEY (id)
)

    TABLESPACE pg_default;

CREATE TABLE IF NOT EXISTS cardatabase.types
(
    id BIGSERIAL NOT NULL,
    name character varying,
    CONSTRAINT types_pkey PRIMARY KEY (id)
)

    TABLESPACE pg_default;

CREATE TABLE IF NOT EXISTS  cardatabase.cars
(
    id character varying NOT NULL,
    year integer,
    make_id integer,
    model_id integer,
    type_id integer,
    CONSTRAINT cars_pkey PRIMARY KEY (id),
    CONSTRAINT makes FOREIGN KEY (make_id)
        REFERENCES cardatabase.makes (id) MATCH SIMPLE
        ON UPDATE  NO ACTION
        ON DELETE CASCADE,
    CONSTRAINT models FOREIGN KEY (model_id)
        REFERENCES cardatabase.models (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE,
    CONSTRAINT types FOREIGN KEY (type_id)
        REFERENCES cardatabase.types (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON  DELETE CASCADE
)

    TABLESPACE pg_default;

create table if not exists roles
(
    id   serial
        primary key,
    name varchar(255)
);

alter table roles
    owner to postgres;
