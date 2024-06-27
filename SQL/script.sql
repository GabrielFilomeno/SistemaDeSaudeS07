CREATE TABLE Funcionario (
    ID_FUNCIONARIO SERIAL PRIMARY KEY,
    MATRICULA VARCHAR(50),
    TEMPO_EXPERIENCIA INT,
    ID_ENDERECO INTEGER,
    FOREIGN KEY (ID_ENDERECO) REFERENCES Endereco(ID_ENDERECO)
);

CREATE TABLE Nutricionista (
	ID_NUTRICIONISTA SERIAL PRIMARY KEY,
	CRN VARCHAR(50),
	ESPECIALIDADE VARCHAR(50)
) INHERITS (Funcionario);

CREATE TABLE Paciente (
	ID_PACIENTE SERIAL PRIMARY KEY,
	NOME VARCHAR(50),
	DATA_NASCIMENTO DATE,
	CPF VARCHAR(50),
	TELEFONE VARCHAR(50),
	EMAIL VARCHAR(50),
	ID_ENDERECO INTEGER,
    FOREIGN KEY (ID_ENDERECO) REFERENCES Endereco(ID_ENDERECO)
);

CREATE TABLE Consulta (
    ID_CONSULTA SERIAL PRIMARY KEY,
    ID_NUTRICIONISTA INTEGER REFERENCES Nutricionista(ID_NUTRICIONISTA),
    ID_PACIENTE INTEGER REFERENCES Paciente(ID_PACIENTE),
	DATA_CONSULTA DATE,
	OBSERVACOES VARCHAR(200),
    UNIQUE(ID_NUTRICIONISTA, ID_PACIENTE)
);

CREATE TABLE Endereco (
	ID_ENDERECO SERIAL PRIMARY KEY,
	LOGRADOURO VARCHAR(200),
	ESTADO VARCHAR(100),
	CIDADE VARCHAR(100),
	NUMERO VARCHAR(50),
	CEP VARCHAR(50)
);

INSERT INTO Funcionario (MATRICULA, TEMPO_EXPERIENCIA, ID_ENDERECO)
	VALUES
    ('Matricula 1', 5, 1),
    ('Matricula 2', 3, 2),
    ('Matricula 3', 7, 3);

INSERT INTO Nutricionista (CRN, ESPECIALIDADE, MATRICULA, TEMPO_EXPERIENCIA, ID_ENDERECO)
	VALUES
	('CRN1', 'Esporte', 'Matricula 5', 6, 4),
	('CRN2', 'Crianças', 'Matricula 6', 8, 5),
	('CRN3', 'Idosos', 'Matricula 7', 2, 6);

INSERT INTO Paciente (NOME, DATA_NASCIMENTO, CPF, TELEFONE, EMAIL, ID_ENDERECO)
	VALUES
	('Teste Silva', '01/01/1999', '111.111.111-11', '(48) 1 1111-1111', 'UM@EXEMPLO.COM', 7),
	('Testando Machado', '02/02/1999', '222.222.222-22', '(48) 2 2222-2222', 'DOIS@EXEMPLO.COM', 8),
	('Testado Santos', '03/03/1999', '333.333.333-33', '(48) 3 3333-3333', 'TRES@EXEMPLO.COM', 9);

INSERT INTO Consulta (ID_NUTRICIONISTA, ID_PACIENTE, DATA_CONSULTA, OBSERVACOES)
	VALUES
	(1, 1, '01/01/2024', 'Melhor rendimento no esporte.'),
	(2, 2, '02/02/2024', 'Paciente apresentou melhoras.'),
	(3, 3, '03/03/2024', 'Paciente se sente mais disposto.');

INSERT INTO Endereco (LOGRADOURO, ESTADO, CIDADE, NUMERO, CEP)
	VALUES
	('Rua 1', 'Estado 1', 'Cidade 1', 'Numero 1', 'Cep 1'),
	('Rua 2', 'Estado 2', 'Cidade 2', 'Numero 2', 'Cep 2'),
	('Rua 3', 'Estado 3', 'Cidade 3', 'Numero 3', 'Cep 3'),
	('Rua 4', 'Estado 4', 'Cidade 4', 'Numero 4', 'Cep 4'),
	('Rua 5', 'Estado 5', 'Cidade 5', 'Numero 5', 'Cep 5'),
	('Rua 6', 'Estado 6', 'Cidade 6', 'Numero 6', 'Cep 6'),
	('Rua 7', 'Estado 7', 'Cidade 7', 'Numero 7', 'Cep 7'),
	('Rua 8', 'Estado 8', 'Cidade 8', 'Numero 8', 'Cep 8'),
	('Rua 9', 'Estado 9', 'Cidade 9', 'Numero 9', 'Cep 9');

UPDATE Paciente SET TELEFONE = '(48) 4 4444-4444' WHERE ID_PACIENTE = 1;

DELETE FROM Consulta WHERE ID_CONSULTA = 2;