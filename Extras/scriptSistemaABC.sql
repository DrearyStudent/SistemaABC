-- Database: abcsystem

-- DROP DATABASE abcsystem;

CREATE DATABASE abcsystem
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Spanish_Mexico.1252'
    LC_CTYPE = 'Spanish_Mexico.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

DROP TABLE IF EXISTS "estudiante";
CREATE TABLE "estudiante" (
	"idEstudiante" SERIAL,
	"apellidoPaterno" varchar(50) NOT NULL,
	"apellidoMaterno" varchar(50) NOT NULL,
	"primerNombre" varchar(50) NOT NULL,
	"segundoNombre" varchar(50) NOT NULL,
	"estado" BIT NOT NULL,
	PRIMARY KEY ("idEstudiante")
);
