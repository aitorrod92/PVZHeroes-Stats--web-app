CREATE USER 'webuser'@'localhost' IDENTIFIED BY 'password';

GRANT ALL PRIVILEGES ON * . * TO 'webuser'@'localhost';

CREATE SCHEMA `pvzheroes` ;

CREATE TABLE `pvzheroes`.`cartas` (
  `Nombre` VARCHAR(30) NOT NULL,
  `Ataque` INT NULL,
  `Defensa` INT NULL,
  `Coste` INT NULL,
  `Clase` TINYTEXT NULL,
  `Tribus` TINYTEXT NULL,
  `Atributos` TINYTEXT NULL,
  `Habilidades` TINYTEXT NULL,
  `Rareza` TINYTEXT NULL,
  `Mazo` TINYTEXT NULL,
  `Tipo` TINYTEXT NULL,
  `URL` TEXT NULL,
  `NumeroAtributos` INT NULL,
  `Imagen` TEXT NULL,
  PRIMARY KEY (`Nombre`));