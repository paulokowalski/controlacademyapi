ALTER TABLE pessoa ADD cpf VARCHAR(11) NOT NULL;
ALTER TABLE pessoa ADD email VARCHAR(50) NOT NULL;
ALTER TABLE pessoa ADD ativo BOOLEAN NOT NULL;

ALTER TABLE pessoa ADD logradouro VARCHAR(50);
ALTER TABLE pessoa ADD numero VARCHAR(50);
ALTER TABLE pessoa ADD complemento VARCHAR(50);
ALTER TABLE pessoa ADD bairro VARCHAR(50);
ALTER TABLE pessoa ADD cep VARCHAR(50);
ALTER TABLE pessoa ADD cidade VARCHAR(50);
ALTER TABLE pessoa ADD estado VARCHAR(50);
