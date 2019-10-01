CREATE TABLE crud.usuario (
    id int NOT NULL AUTO_INCREMENT,
    nome varchar(255) NOT NULL,
    sobrenome varchar(255),
    idade int,
	usuario varchar(255) NOT NULL,
	senha varchar(255) NOT NULL,
    PRIMARY KEY (id)
);