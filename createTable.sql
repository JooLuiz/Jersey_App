CREATE TABLE crud.usuario (
    id int NOT NULL AUTO_INCREMENT,
    nome varchar(255) NOT NULL,
    sobrenome varchar(255),
    idade int,
    PRIMARY KEY (id)
);