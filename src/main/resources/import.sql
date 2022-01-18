INSERT INTO sensorsus.estado (nome) VALUES	 ('PERNAMBUCO'),   ('PARAIBA');


INSERT INTO sensorsus.cidade(nome, estado_id) VALUES ('Recife', 1);
INSERT INTO sensorsus.cidade(nome, estado_id) VALUES ('João Pessoa',2);
INSERT INTO sensorsus.cidade(nome, estado_id) VALUES ('Campina Grande',2);
INSERT INTO sensorsus.cidade(nome, estado_id) VALUES ('Jaboatão',1);
INSERT INTO sensorsus.cidade(nome, estado_id) VALUES ('Paulista',1);
INSERT INTO sensorsus.cidade(nome, estado_id) VALUES ('Camaragibe',1);
INSERT INTO sensorsus.cidade(nome, estado_id) VALUES  ('Olinda',1);
INSERT INTO sensorsus.cidade(nome, estado_id) VALUES ('Cabo', 1);


INSERT INTO sensorsus.estabelecimento (cod_cnes,count,descricao,natureza_juridica,nome,orgao_gestor,score) VALUES  (3323,4,' Descrição de qualquer informação relevante','Administracão pública','Hospital Springfield','Municipal',3.25);
INSERT INTO sensorsus.estabelecimento (cod_cnes,count,descricao,natureza_juridica,nome,orgao_gestor,score) VALUES  (55555,3,' Descrição sobre o hospital e como ele funciona','Administracão pública','Hospital Wakanda ','Estadual',3.33);
INSERT INTO sensorsus.estabelecimento (cod_cnes,count,descricao,natureza_juridica,nome,orgao_gestor,score) VALUES  (8888,4,' Horario de funcionamento de 8h até 17h','Filantropico','HCB Trata Coracao','Municipal',3.5);
INSERT INTO sensorsus.estabelecimento (cod_cnes,count,descricao,natureza_juridica,nome,orgao_gestor,score) VALUES  (59911,1,' funciona 24 horas','Administracão pública','UPA do alto do morro ','Federal',2.0);
INSERT INTO sensorsus.estabelecimento (cod_cnes,count,descricao,natureza_juridica,nome,orgao_gestor,score) VALUES (54445,2,' Descriçao sobre este hospital','Administracão pública','Hospital de Fratura','Federal',3.0);
INSERT INTO sensorsus.estabelecimento (cod_cnes,count,descricao,natureza_juridica,nome,orgao_gestor,score) VALUES  (44443,NULL,' Descriçao sobre este hospital6','Administracão pública','HGR hospital Geral dos Restaurados','Federal',NULL);
INSERT INTO sensorsus.estabelecimento (cod_cnes,count,descricao,natureza_juridica,nome,orgao_gestor,score) VALUES  (60020,2,' Descriçao sobre este hospital7','Administracão pública','UPA baixa rio lago','Federal',2.5);
INSERT INTO sensorsus.estabelecimento (cod_cnes,count,descricao,natureza_juridica,nome,orgao_gestor,score) VALUES (7777,NULL,' Descriçao sobre este hospital8','Administracão pública','UPA Morro baixo','Federal',NULL);
INSERT INTO sensorsus.estabelecimento (cod_cnes,count,descricao,natureza_juridica,nome,orgao_gestor,score) VALUES (66666,NULL,' Descriçao sobre este hospital9','Filantropico','HO hospital da Vida','Municipal',NULL);
INSERT INTO sensorsus.estabelecimento (cod_cnes,count,descricao,natureza_juridica,nome,orgao_gestor,score) VALUES  (3333,1,' Descriçao sobre este hospital10','Administracão pública','HCP ','Federal',1.0);
INSERT INTO sensorsus.estabelecimento (cod_cnes,count,descricao,natureza_juridica,nome,orgao_gestor,score) VALUES  (35445,NULL,' Descriçao sobre este hospital11','Administracão pública','HB2','Federal',NULL);
INSERT INTO sensorsus.estabelecimento (cod_cnes,count,descricao,natureza_juridica,nome,orgao_gestor,score) VALUES (76444,NULL,' Descriçao sobre este hospital12','Administracão pública','Hospital de Camaragib','Federal',NULL);
INSERT INTO sensorsus.estabelecimento (cod_cnes,count,descricao,natureza_juridica,nome,orgao_gestor,score) VALUES (600,4,' Descriçao sobre este hospital13','Administracão pública','UPA pdsw','Federal',3.5);
INSERT INTO sensorsus.estabelecimento (cod_cnes,count,descricao,natureza_juridica,nome,orgao_gestor,score) VALUES   (27554,NULL,' Descriçao sobre este hospital14','Administracão pública','Pronto Socorro Villa Grande ','Federal',NULL);	
	

INSERT INTO sensorsus.endereco (estabelecimento_id,bairro,cep,complemento,logradouro,numero,cidade_id) VALUES	 (1,'Bairro','70999000','Proximo a Rodovia','Avenida Principal',1000,4);
INSERT INTO sensorsus.endereco (estabelecimento_id,bairro,cep,complemento,logradouro,numero,cidade_id) VALUES	 (2,'Cidade Universitaria','70999000','Proximo a UFPE','Rodovia BR 101',4450,1);
INSERT INTO sensorsus.endereco (estabelecimento_id,bairro,cep,complemento,logradouro,numero,cidade_id) VALUES	 (3,'Bairro algum','50500500','Próximo a algum lugar que não sei','Avenida Agamenom Magalhaes',0,3);
INSERT INTO sensorsus.endereco (estabelecimento_id,bairro,cep,complemento,logradouro,numero,cidade_id) VALUES	 (4,'Bairro Velho','77999555','Referencia qualquer','Avenida Teste ',300,2);
INSERT INTO sensorsus.endereco (estabelecimento_id,bairro,cep,complemento,logradouro,numero,cidade_id) VALUES	 (5,'Bairro Feliz','77999555','Em algum lugar perto de alguma coisa','Avenida Test22 ',201,2);
INSERT INTO sensorsus.endereco (estabelecimento_id,bairro,cep,complemento,logradouro,numero,cidade_id) VALUES	 (6,'Bairro Jovem','77999555','Em algum lugar perto de alguma coisa','Avenida Caxanga ',506,2);
INSERT INTO sensorsus.endereco (estabelecimento_id,bairro,cep,complemento,logradouro,numero,cidade_id) VALUES	 (7,'Sitio Verde','77999555','Em algum lugar perto de alguma coisa','Avenida Abidias de Carvalho ',77,1);
INSERT INTO sensorsus.endereco (estabelecimento_id,bairro,cep,complemento,logradouro,numero,cidade_id) VALUES	 (8,'Prado','77999555','Em algum lugar perto de alguma coisa','Avenida Boa viagem ',4088,1);
INSERT INTO sensorsus.endereco (estabelecimento_id,bairro,cep,complemento,logradouro,numero,cidade_id) VALUES	 (9,'Madalena','77999555','Em algum lugar perto de alguma coisa','Rua General Carlos Viana',999,3);
INSERT INTO sensorsus.endereco (estabelecimento_id,bairro,cep,complemento,logradouro,numero,cidade_id) VALUES	 (10,'Dois Irmão','77999555','Em algum lugar perto de alguma coisa','Rua do Principe ',1221,1);
INSERT INTO sensorsus.endereco (estabelecimento_id,bairro,cep,complemento,logradouro,numero,cidade_id) VALUES	 (11,'Macaxeira','77999555','Em algum lugar perto de alguma coisa','Rua da Concordia ',232,4);
INSERT INTO sensorsus.endereco (estabelecimento_id,bairro,cep,complemento,logradouro,numero,cidade_id) VALUES	 (12,'Mustarda','77999555','Em algum lugar perto de alguma coisa','Avenida Romero Marques',0,1);
INSERT INTO sensorsus.endereco (estabelecimento_id,bairro,cep,complemento,logradouro,numero,cidade_id) VALUES	 (13,'Recife','77999555','Em algum lugar perto de alguma coisa','Av. Porta larga ',4554,1);
INSERT INTO sensorsus.endereco (estabelecimento_id,bairro,cep,complemento,logradouro,numero,cidade_id) VALUES	 (14,'Alto do centro','77999555','Em algum lugar perto de alguma coisa','Avenida Teste ',488,3);



INSERT INTO sensorsus.servico (nome,estabelecimento_id) VALUES	 ('Emergência adulta',1);
INSERT INTO sensorsus.servico (nome,estabelecimento_id) VALUES	 ('Hemodiálise',1);
INSERT INTO sensorsus.servico (nome,estabelecimento_id) VALUES	 ('Emergencia pediátrica',2);
INSERT INTO sensorsus.servico (nome,estabelecimento_id) VALUES	 ('UTI neonatal',2);
INSERT INTO sensorsus.servico (nome,estabelecimento_id) VALUES	 ('Emergencia pediátrica',3);
INSERT INTO sensorsus.servico (nome,estabelecimento_id) VALUES	 ('Odontologia',3);
INSERT INTO sensorsus.servico (nome,estabelecimento_id) VALUES	 ('Pronto atendimento',3);
INSERT INTO sensorsus.servico (nome,estabelecimento_id) VALUES	 ('Oncologia',4);


INSERT INTO sensorsus.usuario (email,nickname,nome_completo,senha) VALUES     ('test1@test.com','test1','usuario teste um','$2a$10$jKN4yEcYmO/U2/gIjZTXXeb7O2Ebh5PvFGijCVb4.xgMaL6HJ1CgW');
INSERT INTO sensorsus.usuario (email,nickname,nome_completo,senha) VALUES	 ('test2@test.com','test2','usuario teste dois','$2a$10$AqgMKTOHM9IF28MFb/CuFe8nIbsljRDJSsMJgm5VUU/W0v/OZpHhC');
INSERT INTO sensorsus.usuario (email,nickname,nome_completo,senha) VALUES	 ('test3test@test.com','Lews','Lewis Vangon','$2a$10$q8T.HNiBmriIJd7eCyyqoeWOrIL.qBCUzl6RwnDdz5pn.ersmxP6e');
INSERT INTO sensorsus.usuario (email,nickname,nome_completo,senha) VALUES	 ('test4test@test.com','Dali4','Salvador Dali','$2a$10$O4terZUAOEhwUJzjSv4lOeM1FOW2NsApcQ9CpvtmhdqXzyIVvUF0q');
INSERT INTO sensorsus.usuario (email,nickname,nome_completo,senha) VALUES	 ('test5test@test.com','Justino','Vinaya Justino','$2a$10$Trl1llACQL5LkGb5vD93Q.KZEZ26BcvpPQC.QVXi7zOXnwg1BC.LC');
INSERT INTO sensorsus.usuario (email,nickname,nome_completo,senha) VALUES	 ('test6test@test.com','Muriel','Caetano Muriel','$2a$10$4bLGpffURqusvGni0tyAduxwbQsJy3vrCAgNcq9Zan3WJLZEvYZaq');
INSERT INTO sensorsus.usuario (email,nickname,nome_completo,senha) VALUES	 ('test7test@test.com','Johannessen','Chipison Johannessen','$2a$10$vLwq1ZQHi3OctJN6AfSYde.V3PK.bXbjdbL9PI236KI17U8tOaiDu');
INSERT INTO sensorsus.usuario (email,nickname,nome_completo,senha) VALUES	 ('test8test@test.com','Gansa','Alex Gansa','$2a$10$scHQ4wXOWmUzRXvMNO6e6eWi5gIjKc7YJ9gael7PQvBoTJSWAZv3e');
INSERT INTO sensorsus.usuario (email,nickname,nome_completo,senha) VALUES	 ('test9test@test.com','Gordon','Howard Gordon','$2a$10$Rt4pwSVNMbtkMDBz8I59ouzvvwNpIJz4GqD/1mPwtATwbBqwBMhRm');
INSERT INTO sensorsus.usuario (email,nickname,nome_completo,senha) VALUES	 ('aaa@aaa.com','Bob','Bob esponja','$2a$10$7Nl4JWCk8Y4/ffULC0wYXOxLBaUI0IGvafSKsoe6K3oNmshjBqk2u');


INSERT INTO sensorsus.perfis (usuario_id,perfis) VALUES	 (1,2);
INSERT INTO sensorsus.perfis (usuario_id,perfis) VALUES	 (2,2);
INSERT INTO sensorsus.perfis (usuario_id,perfis) VALUES	 (3,2);
INSERT INTO sensorsus.perfis (usuario_id,perfis) VALUES	 (4,2);
INSERT INTO sensorsus.perfis (usuario_id,perfis) VALUES	 (5,2);
INSERT INTO sensorsus.perfis (usuario_id,perfis) VALUES	 (6,2);
INSERT INTO sensorsus.perfis (usuario_id,perfis) VALUES	 (7,2);
INSERT INTO sensorsus.perfis (usuario_id,perfis) VALUES	 (8,2);
INSERT INTO sensorsus.perfis (usuario_id,perfis) VALUES	 (9,1);
INSERT INTO sensorsus.perfis (usuario_id,perfis) VALUES	 (9,2);
INSERT INTO sensorsus.perfis (usuario_id,perfis) VALUES	 (10,2);


INSERT INTO sensorsus.avaliacao_estabelecimento (classificacao,data_criacao,descricao,estabelecimento_id,usuario_id) VALUES	 (4.0,'2021-09-30 10:31:00','Hospital Muito Bom atendimento',1,2);
INSERT INTO sensorsus.avaliacao_estabelecimento (classificacao,data_criacao,descricao,estabelecimento_id,usuario_id) VALUES	 (2.0,'2021-10-30 10:33:00','Hospital Atendimento Ruim',4,1);
INSERT INTO sensorsus.avaliacao_estabelecimento (classificacao,data_criacao,descricao,estabelecimento_id,usuario_id) VALUES	 (2.0,'2021-10-25 10:35:00','Hospital teve um atendimento mais ou menos',1,3);
INSERT INTO sensorsus.avaliacao_estabelecimento (classificacao,data_criacao,descricao,estabelecimento_id,usuario_id) VALUES	 (3.0,'2021-11-23 11:10:00','Foi bom o atendimento, mas poderia ser melhor',2,4);
INSERT INTO sensorsus.avaliacao_estabelecimento (classificacao,data_criacao,descricao,estabelecimento_id,usuario_id) VALUES	 (3.0,'2021-11-11 11:10:00','Hospital Atendimento TOP',1,4);
INSERT INTO sensorsus.avaliacao_estabelecimento (classificacao,data_criacao,descricao,estabelecimento_id,usuario_id) VALUES	 (4.0,'2021-11-12 11:10:00','Hospital muito bom',3,4);
INSERT INTO sensorsus.avaliacao_estabelecimento (classificacao,data_criacao,descricao,estabelecimento_id,usuario_id) VALUES	 (1.0,'2021-11-13 11:10:00','Hospital ja foi melhor 7',3,3);
INSERT INTO sensorsus.avaliacao_estabelecimento (classificacao,data_criacao,descricao,estabelecimento_id,usuario_id) VALUES	 (5.0,'2021-11-14 11:10:00','Hospital não sei',3,5);
INSERT INTO sensorsus.avaliacao_estabelecimento (classificacao,data_criacao,descricao,estabelecimento_id,usuario_id) VALUES	 (4.0,'2021-11-15 11:10:00','Hospital muito bom 9',3,7);
INSERT INTO sensorsus.avaliacao_estabelecimento (classificacao,data_criacao,descricao,estabelecimento_id,usuario_id) VALUES	 (2.0,'2021-11-16 11:10:00','Hospital muito bom10 ',2,6);
INSERT INTO sensorsus.avaliacao_estabelecimento (classificacao,data_criacao,descricao,estabelecimento_id,usuario_id) VALUES	 (5.0,'2021-11-17 11:10:00','Hospital muito bom 11',2,8);
INSERT INTO sensorsus.avaliacao_estabelecimento (classificacao,data_criacao,descricao,estabelecimento_id,usuario_id) VALUES	 (4.0,'2021-11-18 11:10:00','Atendimento Fast',1,9);
INSERT INTO sensorsus.avaliacao_estabelecimento (classificacao,data_criacao,descricao,estabelecimento_id,usuario_id) VALUES	 (5.0,'2021-11-19 11:10:00','Hospital tem um atendimento rapido',5,4);
INSERT INTO sensorsus.avaliacao_estabelecimento (classificacao,data_criacao,descricao,estabelecimento_id,usuario_id) VALUES	 (1.0,'2021-11-11 11:10:00','atendimento fraco demais',5,1);
INSERT INTO sensorsus.avaliacao_estabelecimento (classificacao,data_criacao,descricao,estabelecimento_id,usuario_id) VALUES	 (1.0,'2021-12-01 11:10:00','Atendimento demorado',10,1);
INSERT INTO sensorsus.avaliacao_estabelecimento (classificacao,data_criacao,descricao,estabelecimento_id,usuario_id) VALUES	 (3.0,'2021-11-29 12:00:00','aaaaaaaaaaaaaaaaaaaaaaaa aaaaaaaaaaaaaaaaaaaaa',13,9);
INSERT INTO sensorsus.avaliacao_estabelecimento (classificacao,data_criacao,descricao,estabelecimento_id,usuario_id) VALUES	 (4.0,'2021-11-23 11:10:00','bbbbbbbbb bbbbbbbbbbbbbb bbbbbbbbbbbbbbbbbbbbbb',13,2);
INSERT INTO sensorsus.avaliacao_estabelecimento (classificacao,data_criacao,descricao,estabelecimento_id,usuario_id) VALUES	 (5.0,'2021-11-25 11:10:00','',13,6);
INSERT INTO sensorsus.avaliacao_estabelecimento (classificacao,data_criacao,descricao,estabelecimento_id,usuario_id) VALUES	 (1.0,'2021-11-29 11:10:00','Avaliação é o substantivo feminino que significa ato de avaliar, ou remete para o efeito essa avaliação. Pode ser sinônimo de estimativa ou apreciação. Uma avaliação pode ser a estimativa do valor de alguma coisa ou de algum trabalho.',7,4);
INSERT INTO sensorsus.avaliacao_estabelecimento (classificacao,data_criacao,descricao,estabelecimento_id,usuario_id) VALUES	 (4.0,'2021-02-21 11:10:00','cccccccccc ccccccccccccccccccc ccccccccccccccccccccccc cccccccccc',7,8);
INSERT INTO sensorsus.avaliacao_estabelecimento (classificacao,data_criacao,descricao,estabelecimento_id,usuario_id) VALUES	 (2.0,'2021-11-29 09:00:00','ddddddddddddddddddddd ddddddddddddddddddddddddddddddd ddddddddddddddddd',13,3);


INSERT INTO sensorsus.avaliacao_servico (classificacao,data_criacao,descricao,servico_id,usuario_id) VALUES	 (5.0,'2021-09-30 10:31:00','serviço Muito Bom',8,1);
INSERT INTO sensorsus.avaliacao_servico (classificacao,data_criacao,descricao,servico_id,usuario_id) VALUES	 (1.5,'2021-09-30 10:31:00','E um serviço Ruim',2,1);
INSERT INTO sensorsus.avaliacao_servico (classificacao,data_criacao,descricao,servico_id,usuario_id) VALUES	 (3.0,'2021-09-30 10:31:00','Esse serviço Não é muito Legal',5,2);
INSERT INTO sensorsus.avaliacao_servico (classificacao,data_criacao,descricao,servico_id,usuario_id) VALUES	 (0.0,'2021-09-30 10:31:00','serviço Não sei responder',6,3);
INSERT INTO sensorsus.avaliacao_servico (classificacao,data_criacao,descricao,servico_id,usuario_id) VALUES	 (3.0,'2021-09-30 10:31:00','serviço Atendimento demorado',7,4);
INSERT INTO sensorsus.avaliacao_servico (classificacao,data_criacao,descricao,servico_id,usuario_id) VALUES	 (4.9,'2021-09-28 10:31:00','serviço Full HD',7,4);
INSERT INTO sensorsus.avaliacao_servico (classificacao,data_criacao,descricao,servico_id,usuario_id) VALUES	 (4.5,'2021-09-29 10:31:00','serviçoatendimento ótimo',1,5);
INSERT INTO sensorsus.avaliacao_servico (classificacao,data_criacao,descricao,servico_id,usuario_id) VALUES	 (4.5,'2021-09-29 10:31:00','serviçoatendimento usuario 1 avaliando',1,1);
INSERT INTO sensorsus.avaliacao_servico (classificacao,data_criacao,descricao,servico_id,usuario_id) VALUES	 (4.5,'2021-09-29 10:31:00','serviçoatendimento ótimo ótimo ótimo ótimo ótimo',1,4);
INSERT INTO sensorsus.avaliacao_servico (classificacao,data_criacao,descricao,servico_id,usuario_id) VALUES	 (4.5,'2021-09-29 10:31:00','serviçoatendimento 99999999999999999 9999999999999 99999999999999',1,6);
INSERT INTO sensorsus.avaliacao_servico (classificacao,data_criacao,descricao,servico_id,usuario_id) VALUES	 (4.5,'2021-09-29 10:31:00','serviçoatendimento wwwwwww  wwwwww wwwww wwwwwwwww  wwwwwwwwww',1,3);


	
