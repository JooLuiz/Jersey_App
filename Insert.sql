insert into crud.usuario(nome, sobrenome, idade,usuario, senha)
values ('Usuário01', '0001', 0, 'User01', '123');

insert into crud.materia(descricao)
values ('História');

insert into crud.materia_usuario(id_usuario, id_materia, ano)
values (1,1,'2019');

insert into crud.aula(id_materia, descricao)
values (1,'Revolução Francesa');