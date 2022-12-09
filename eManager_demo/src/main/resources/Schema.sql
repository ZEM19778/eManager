DROP SCHEMA IF EXISTS emanagerdb;
CREATE SCHEMA emanagerdb;
USE emanagerdb;

#User-Tabelle
DROP TABLE IF EXISTS user;
CREATE TABLE user (
                      id int PRIMARY KEY NOT NULL,
                      username VARCHAR(30) NOT NULL,
                      password VARCHAR(40) NOT NULL,
                      nachname VARCHAR(40) NOT NULL
);

#Dienste-Tabelle
CREATE TABLE dienste(
                        id int PRIMARY KEY NOT NULL,
                        datumvon DATE NOT NULL,
                        datumbis DATE NOT NULL,
                        addresse VARCHAR(50) NOT NULL,
                        zeit_von TIME,
                        zeit_bis TIME
)