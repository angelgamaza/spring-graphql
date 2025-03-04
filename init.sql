----------------------------------------------------
-- Extensions
----------------------------------------------------

CREATE
EXTENSION IF NOT EXISTS "uuid-ossp";

----------------------------------------------------
-- Deletions
----------------------------------------------------

-- Tables
DROP TABLE IF EXISTS
    director,
    film
    CASCADE;

----------------------------------------------------
-- Tables
----------------------------------------------------

-- Table: Director
CREATE TABLE director
(
    id        UUID DEFAULT uuid_generate_v4(),
    firstname VARCHAR(255) NOT NULL,
    lastname  VARCHAR(255) NOT NULL,
    CONSTRAINT pk_director PRIMARY KEY (id)
);

-- Table: Film
CREATE TABLE film
(
    id       UUID DEFAULT uuid_generate_v4(),
    name     VARCHAR(255) NOT NULL,
    country  VARCHAR(255) NOT NULL,
    director UUID         NOT NULL,
    CONSTRAINT pk_film PRIMARY KEY (id),
    CONSTRAINT uk_film_name UNIQUE (name),
    CONSTRAINT fk_film_director FOREIGN KEY (director) REFERENCES director (id)
        MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

-- Indexes: Film
CREATE UNIQUE INDEX ui_film_name ON film USING btree (name);

----------------------------------------------------
-- Inserts
----------------------------------------------------

-- Table: Director
INSERT INTO director (firstname, lastname)
VALUES ('Steven', 'Spielberg'),
       ('George', 'Lucas'),
       ('James', 'Cameron'),
       ('Quentin', 'Tarantino'),
       ('Christopher', 'Nolan');

-- Table: Film
INSERT INTO film (name, country, director)
VALUES ('Jurassic Park', 'USA', (SELECT id FROM director WHERE firstname = 'Steven' AND lastname = 'Spielberg')),
       ('Jaws', 'USA', (SELECT id FROM director WHERE firstname = 'Steven' AND lastname = 'Spielberg')),
       ('Indiana Jones', 'USA', (SELECT id FROM director WHERE firstname = 'Steven' AND lastname = 'Spielberg')),
       ('E.T.', 'USA', (SELECT id FROM director WHERE firstname = 'Steven' AND lastname = 'Spielberg')),
       ('Star Wars', 'USA', (SELECT id FROM director WHERE firstname = 'George' AND lastname = 'Lucas')),
       ('The Empire Strikes Back', 'USA', (SELECT id FROM director WHERE firstname = 'George' AND lastname = 'Lucas')),
       ('Return of the Jedi', 'USA', (SELECT id FROM director WHERE firstname = 'George' AND lastname = 'Lucas')),
       ('The Terminator', 'USA', (SELECT id FROM director WHERE firstname = 'James' AND lastname = 'Cameron')),
       ('Aliens', 'USA', (SELECT id FROM director WHERE firstname = 'James' AND lastname = 'Cameron')),
       ('Titanic', 'USA', (SELECT id FROM director WHERE firstname = 'James' AND lastname = 'Cameron')),
       ('Pulp Fiction', 'USA', (SELECT id FROM director WHERE firstname = 'Quentin' AND lastname = 'Tarantino')),
       ('Kill Bill', 'USA', (SELECT id FROM director WHERE firstname = 'Quentin' AND lastname = 'Tarantino')),
       ('Django Unchained', 'USA', (SELECT id FROM director WHERE firstname = 'Quentin' AND lastname = 'Tarantino')),
       ('Inception', 'USA', (SELECT id FROM director WHERE firstname = 'Christopher' AND lastname = 'Nolan')),
       ('Interstellar', 'USA', (SELECT id FROM director WHERE firstname = 'Christopher' AND lastname = 'Nolan'));