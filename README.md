# Desafio-SRM
Aplicação Java de conversão de moedas para mercado do mundo de Senhor dos Anéis


## Script SQL

CREATE TABLE moeda (
id INT AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(45),
simbolo CHAR(2)
);

CREATE TABLE reino (
id INT AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(45),
moeda_id INT,
FOREIGN KEY (moeda_id) REFERENCES moeda(id)
);

CREATE TABLE produto (
id INT AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(45),
descricao VARCHAR(45),
reino_id INT,
preco_base DECIMAL(10,2),
FOREIGN KEY (reino_id) REFERENCES reino(id)
);

CREATE TABLE taxa_cambio (
id INT AUTO_INCREMENT PRIMARY KEY,
data_hora DATETIME,
taxa DECIMAL(10,2),
moeda_origem_id INT,
moeda_destino_id INT,
produto_id INT,
FOREIGN KEY (moeda_origem_id) REFERENCES moeda(id),
FOREIGN KEY (moeda_destino_id) REFERENCES moeda(id),
FOREIGN KEY (produto_id) REFERENCES produto(id)
);

CREATE TABLE transacao (
id INT AUTO_INCREMENT PRIMARY KEY,
data_hora DATETIME,
valor_final DECIMAL(10,2),
moeda_origem_id INT,
moeda_destino_id INT,
reino_id INT,
taxa_cambio_id INT,
FOREIGN KEY (moeda_origem_id) REFERENCES moeda(id),
FOREIGN KEY (moeda_destino_id) REFERENCES moeda(id),
FOREIGN KEY (reino_id) REFERENCES reino(id),
FOREIGN KEY (taxa_cambio_id) REFERENCES taxa_cambio(id)
);

CREATE TABLE item_transacao (
produto_id INT,
transacao_id INT,
quantidade INT,
valor_origem DECIMAL(10,2),
valor_destino DECIMAL(10,2),
PRIMARY KEY (produto_id, transacao_id),
FOREIGN KEY (produto_id) REFERENCES produto(id),
FOREIGN KEY (transacao_id) REFERENCES transacao(id)
);

### Inserts

INSERT INTO moeda (nome, simbolo) VALUES
('Ouro Real', 'OR'),
('Tibar', 'TB');

INSERT INTO reino (nome, moeda_id) VALUES
('Wefin', 1),
('Montanhas Distantes', 2);

INSERT INTO produto (nome, descricao, reino_id, preco_base) VALUES
('Pele', 'Usada para fazer roupas', 1, 30.0),
('Madeira', 'Usada para construir casas', 1, 15.0),
('Hidromel', 'Bebida sagrada para conquistas', 1, 60.0),
('Pele', 'Usada para fazer cobertas', 2, 30.0),
('Madeira', 'Usada para construir armas', 2, 15.0),
('Hidromel', 'Bebida para a batalha', 2, 60.0);

INSERT INTO taxa_cambio (taxa, data_hora, moeda_origem_id, moeda_destino_id, produto_id) VALUES
(2.5, '2025-05-07 09:00:00', 1, 2, NULL),
(2.2, '2025-05-07 09:00:00', 1, 2, 1),
(2.0, '2025-05-07 09:00:00', 1, 2, 2),
(2.7, '2025-05-07 09:00:00', 1, 2, 3),
(0.45, '2025-05-07 09:00:00', 2, 1, 4),
(0.5, '2025-05-07 09:00:00', 2, 1, 5),
(0.37, '2025-05-07 09:00:00', 2, 1, 6);