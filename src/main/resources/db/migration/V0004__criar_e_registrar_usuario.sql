CREATE TABLE usuario (
	codigo BIGINT(20) PRIMARY KEY,
	nome VARCHAR(50) NOT NULL,
	email VARCHAR(50) NOT NULL,
	senha VARCHAR(61) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE permissao (
	codigo BIGINT(20) PRIMARY KEY,
	descricao VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE usuario_permissao (
	codigo_usuario BIGINT(20) NOT NULL,
	codigo_permissao BIGINT(20) NOT NULL,
	PRIMARY KEY (codigo_usuario, codigo_permissao),
	FOREIGN KEY (codigo_usuario) REFERENCES usuario(codigo),
	FOREIGN KEY (codigo_permissao) REFERENCES permissao(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO usuario (codigo, nome, email, senha) values (1, 'Administrador', 'admin@controlacademyapi.com', '$2a$10$QPdfktjH8CAyCzY3Jhq7F..x4NGMx6I41eByywrc1/ijM9O9wBtke');
INSERT INTO usuario (codigo, nome, email, senha) values (2, 'Paulo', 'paulo@controlacademyapi.com', '$2a$10$QPdfktjH8CAyCzY3Jhq7F..x4NGMx6I41eByywrc1/ijM9O9wBtke');

INSERT INTO permissao(codigo,descricao) values (1, 'ROLE_CADASTRAR_PESSOA');
INSERT INTO permissao(codigo,descricao) values (2, 'ROLE_CONSULTAR_PESSOA');
INSERT INTO permissao(codigo,descricao) values (3, 'ROLE_EDITAR_PESSOA');

INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1,1);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1,2);
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (1,3);

INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) values (2,2);
