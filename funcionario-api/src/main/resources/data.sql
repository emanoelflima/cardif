DROP SCHEMA employees IF EXISTS;
CREATE SCHEMA employees;
USE employees;

DROP TABLE IF EXISTS cargo;

CREATE TABLE cargo (
    cargo_id int NOT NULL,
    cargo_name varchar(50) NOT NULL,
    CONSTRAINT pk_cargo_id PRIMARY KEY (cargo_id)
);

DROP TABLE IF EXISTS funcionario;

CREATE TABLE funcionario (
    funcionario_id int IDENTITY NOT NULL,
    funcionario_name varchar(50) NOT NULL,
    funcionario_birthday date NOT NULL,
    funcionario_document varchar(50) NOT NULL,
    cargo_id int NOT NULL,
    cd_ativo bit NOT NULL DEFAULT 1,
    CONSTRAINT pk_funcionario_id PRIMARY KEY (funcionario_id),
    CONSTRAINT fk_funcionario_cargo FOREIGN KEY (cargo_id) REFERENCES cargo (cargo_id),
    CONSTRAINT un_funcionario_doc UNIQUE (funcionario_document)
);

DROP TABLE IF EXISTS departamento;

CREATE TABLE departamento (
    departamento_id int NOT NULL,
    departamento_name varchar(50) NOT NULL,
    funcionario_id int NULL,
    CONSTRAINT pk_departamento_id PRIMARY KEY (departamento_id),
    CONSTRAINT fk_dpto_funcionario_id FOREIGN KEY (funcionario_id) REFERENCES funcionario (funcionario_id)
);

DROP TABLE IF EXISTS funcionario_departamento;

CREATE TABLE funcionario_departamento (
    departamento_id int NOT NULL,
    funcionario_id int NOT NULL,
    data_admissao date NOT NULL,
    data_saida date NULL,
    CONSTRAINT pk_func_dpto_id PRIMARY KEY (departamento_id, funcionario_id),
    CONSTRAINT fk_func_dpto_funcionario FOREIGN KEY (funcionario_id) REFERENCES funcionario(funcionario_id),
    CONSTRAINT fk_func_dpto_departamento FOREIGN KEY (departamento_id) REFERENCES departamento(departamento_id)
);

INSERT INTO departamento(departamento_id, departamento_name) VALUES (1, 'RH');
INSERT INTO departamento(departamento_id, departamento_name) VALUES (2, 'DP');
INSERT INTO departamento(departamento_id, departamento_name) VALUES (3, 'Financeiro');
INSERT INTO departamento(departamento_id, departamento_name) VALUES (4, 'TI');
INSERT INTO departamento(departamento_id, departamento_name) VALUES (5, 'Manutenção');
INSERT INTO departamento(departamento_id, departamento_name) VALUES (6, 'Marketing');

INSERT INTO CARGO(cargo_id, cargo_name) VALUES (1, 'Assistente');
INSERT INTO CARGO(cargo_id, cargo_name) VALUES (2, 'Analista');
INSERT INTO CARGO(cargo_id, cargo_name) VALUES (3, 'Gerente');
INSERT INTO CARGO(cargo_id, cargo_name) VALUES (4, 'Diretor');
INSERT INTO CARGO(cargo_id, cargo_name) VALUES (5, 'Estagiario');