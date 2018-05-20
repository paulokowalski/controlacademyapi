CREATE TABLE pessoa (
codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
nome   VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO pessoa (nome) values ('Paulo');
INSERT INTO pessoa (nome) values ('Sabrine');
INSERT INTO pessoa (nome) values ('Maria');
INSERT INTO pessoa (nome) values ('João');
