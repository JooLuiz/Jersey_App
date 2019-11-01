insert into crud.usuario(nome, sobrenome, idade,usuario, senha)
values ('Usuário01', '0001', 0, 'User01', '123');

insert into crud.materia(descricao)
values ('História');

insert into crud.materia_usuario(id_usuario, id_materia, ano)
values (1,1,'2019');

insert into crud.aula(id_materia, descricao)
values (1,'Revolução Francesa');

insert into crud.conteudo(id_aula, conteudo)
values (1,'A revolução Francesa aconteçeu no país europeu conhecido por seus brioches e sua cultura de mímicos. Nesta cidade está uma das maiores construções arquitetónicas do planteta');

insert into crud.exercicio(id_conteudo, pergunta)
values (1,'Quais eram os dois lados politicos que participaram da revolução francesa?');

insert into crud.resposta(id_exercicio, descricao)
values(1, "Lero Lero")