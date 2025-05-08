--CREATE DATABASE srm;
--
--CREATE TABLE moeda (
--id INT PRIMARY KEY AUTO_INCREMENT,
--nome VARCHAR(20)
--);
--
--CREATE TABLE reino (
--id INT PRIMARY KEY AUTO_INCREMENT,
--nome VARCHAR(30),
--taxa DOUBLE
--);
--
--CREATE TABLE taxa_cambio (
--id INT PRIMARY KEY AUTO_INCREMENT,
--taxa DOUBLE,
--data DATE
--);
--
--CREATE TABLE produto (
--id INT PRIMARY KEY AUTO_INCREMENT,
--nome VARCHAR(30),
--taxa DOUBLE
--);
--
--CREATE TABLE transacao (
--id INT PRIMARY KEY AUTO_INCREMENT,
--data_hora DATETIME,
--categoria VARCHAR(15),
--valor_final DOUBLE,
--reino_id INT,
--FOREIGN KEY (reino_id) REFERENCES reino(id),
--moeda_origem INT,
--FOREIGN KEY (moeda_origem) REFERENCES moeda(id),
--moeda_destino INT,
--FOREIGN KEY (moeda_destino) REFERENCES moeda(id)
--);
--
--CREATE TABLE produto_transacao (
--id INT PRIMARY KEY AUTO_INCREMENT,
--transacao_id INT,
--FOREIGN KEY (transacao_id) REFERENCES transacao(id),
--produto_id INT,
--FOREIGN KEY (produto_id) REFERENCES produto(id)
--) AUTO_INCREMENT = 1000;

-- INSERT
INSERT INTO moeda (nome, simbolo) VALUES
	('Ouro Real', 'OR'),
	('Tibar', 'TB');

INSERT INTO reino(nome) VALUES
    ('Wefin'),
    ('Montanhas Distantes');

INSERT INTO produto(nome, descricao, reino_id) VALUES
	('Pele', 'Usada para fazer roupas', 1),
	('Madeira', 'Usada para construir casas', 1),
	('Hidromel', 'Bebida sagrada para conquistas', 1),
	('Pele', 'Usada para fazer cobertas', 2),
	('Madeira', 'Usada para construir armas', 2),
	('Hidromel', 'Bebida para a batalha', 2);

INSERT INTO taxa_cambio (taxa, data_hora, moeda_origem_id, moeda_destino_id, produto_id) VALUES
    (2.5, '2025-05-07 09:00:00', 1, 2, null),
    (2.2, '2025-05-07 09:00:00', 1, 2, 1),
    (2.0, '2025-05-07 09:00:00', 1, 2, 2),
    (2.7, '2025-05-07 09:00:00', 1, 2, 3),
    (0.45, '2025-05-07 09:00:00', 2, 1, 4),
    (0.5, '2025-05-07 09:00:00', 2, 1, 5),
    (0.37, '2025-05-07 09:00:00', 2, 1, 6);