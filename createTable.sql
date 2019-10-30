CREATE SCHEMA `crud` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE crud.usuario (
    id int NOT NULL AUTO_INCREMENT,
    nome varchar(255) NOT NULL,
    sobrenome varchar(255),
    idade int,
	usuario varchar(255) NOT NULL,
	senha varchar(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE crud.materia (
    id int NOT NULL AUTO_INCREMENT,
    descricao varchar(255) NOT NULL,
    ativa varchar(1) NOT NULL DEFAULT 'S',
    PRIMARY KEY (id)
);

CREATE TABLE crud.materia_usuario (
	id_materia_usuario int NOT NULL AUTO_INCREMENT,
    id_usuario int NOT NULL,
    id_materia int NOT NULL,
    ano varchar(4) NOT NULL,
    situacao varchar(1) NOT NULL DEFAULT 'A',
    PRIMARY KEY (id_materia_usuario)
);

ALTER TABLE crud.materia_usuario ADD CONSTRAINT fk_usuario FOREIGN KEY ( id_usuario ) REFERENCES crud.usuario ( id );
ALTER TABLE crud.materia_usuario ADD CONSTRAINT fk_materia FOREIGN KEY ( id_materia ) REFERENCES crud.materia ( id );
	
CREATE TABLE crud.aula (
    id int NOT NULL AUTO_INCREMENT,
    id_materia int NOT NULL,
    descricao varchar(255) NOT NULL,
    situacao varchar(1) NOT NULL DEFAULT 'P',
    PRIMARY KEY (id)
);

ALTER TABLE crud.aula ADD CONSTRAINT fk_materia_aula FOREIGN KEY ( id_materia ) REFERENCES crud.materia ( id );

CREATE TABLE crud.conteudo (
    id int NOT NULL AUTO_INCREMENT,
    id_aula int NOT NULL,
    conteudo varchar(4000) NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE crud.conteudo ADD CONSTRAINT fk_aula_conteudo FOREIGN KEY ( id_aula ) REFERENCES crud.aula ( id );

CREATE TABLE crud.exercicio (
    id int NOT NULL AUTO_INCREMENT,
    id_conteudo int NOT NULL,
    pergunta varchar(4000) NOT NULL,
    dificuldade varchar(1) DEFAULT 'F',
    PRIMARY KEY (id)
);

ALTER TABLE crud.exercicio ADD CONSTRAINT fk_conteudo_exercicio FOREIGN KEY ( id_conteudo ) REFERENCES crud.conteudo ( id );

CREATE TABLE crud.resposta (
    id int NOT NULL AUTO_INCREMENT,
    id_exercicio int NOT NULL,
    descricao varchar(4000) NOT NULL,
    correta varchar(1) NOT NULL DEFAULT 'N',
    PRIMARY KEY (id)
);

ALTER TABLE crud.resposta ADD CONSTRAINT fk_exercicio_resposta FOREIGN KEY ( id_exercicio ) REFERENCES crud.exercicio ( id );
