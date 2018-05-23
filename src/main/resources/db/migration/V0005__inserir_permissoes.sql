INSERT INTO permissao(codigo,descricao) values (4, 'ROLE_REMOVER_PESSOA');

INSERT INTO permissao(codigo,descricao) values (5, 'ROLE_CONSULTAR_MENSALIDADE');
INSERT INTO permissao(codigo,descricao) values (6, 'ROLE_PAGAR_MENSALIDADE');
INSERT INTO permissao(codigo,descricao) values (7, 'ROLE_CADASTRAR_MENSALIDADE');
INSERT INTO permissao(codigo,descricao) values (8, 'ROLE_REMOVER_MENSALIDADE');

INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1,4);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1,5);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1,6);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1,7);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1,8);

INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (2,5);