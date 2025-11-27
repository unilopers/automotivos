CREATE TABLE Fornecedor (
    IDfornecedor INT AUTO_INCREMENT PRIMARY KEY,
    nomeFornecedor VARCHAR(255) NOT NULL
);

CREATE TABLE Funcionario (
    IDfuncionario INT AUTO_INCREMENT PRIMARY KEY,
    nomeFuncionario VARCHAR(255),
    cargoFuncionario VARCHAR(255),
    salarioFuncionario FLOAT
);

CREATE TABLE Cliente (
    IDcliente INT AUTO_INCREMENT PRIMARY KEY,
    Nome VARCHAR(255),
    Telefone VARCHAR(20), -- Mudado para VARCHAR para aceitar DDD e traços
    Endereco VARCHAR(255),
    Anotacao VARCHAR(255)
);

CREATE TABLE Produto (
    SKUproduto VARCHAR(20) PRIMARY KEY, -- SKU é código único (Ex: OLEO-5W30)
    IDfornecedor INT,
    nomeProduto VARCHAR(255),
    codigoBarra VARCHAR(50), -- Mudado para VARCHAR (alguns códigos começam com 0)
    precoProduto FLOAT,
    FOREIGN KEY (IDfornecedor) REFERENCES Fornecedor(IDfornecedor)
);

-- 2. ENTIDADES COMPOSTAS (ONDE ESTÁ SUA NOTA)

-- A. PEDIDO (O Cabeçalho da Venda)
CREATE TABLE Pedido (
    IDpedido INT AUTO_INCREMENT PRIMARY KEY,
    IDcliente INT,
    IDfuncionario INT,
    DataPedido TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    StatusPedido VARCHAR(50), -- Ex: 'ABERTO', 'PAGO', 'CANCELADO'
    ValorTotal FLOAT, -- Soma dos Itens
    FOREIGN KEY (IDcliente) REFERENCES Cliente(IDcliente),
    FOREIGN KEY (IDfuncionario) REFERENCES Funcionario(IDfuncionario)
);

-- B. ITEM PEDIDO (A Tabela que corrige o erro de "1 produto por pedido")
CREATE TABLE ItemPedido (
    IDitem INT AUTO_INCREMENT PRIMARY KEY,
    IDpedido INT,
    SKUproduto VARCHAR(20),
    Quantidade INT,
    PrecoUnitario FLOAT, -- Salva o preço do dia da venda
    Subtotal FLOAT,      -- Quantidade * PrecoUnitario
    FOREIGN KEY (IDpedido) REFERENCES Pedido(IDpedido),
    FOREIGN KEY (SKUproduto) REFERENCES Produto(SKUproduto)
);

-- C. NOTA FISCAL (A 3ª Entidade Composta Obrigatória)
CREATE TABLE NotaFiscal (
    IDnota INT AUTO_INCREMENT PRIMARY KEY,
    IDpedido INT UNIQUE, -- Garante que 1 Pedido só tem 1 Nota
    NumeroNota VARCHAR(50),
    DataEmissao TIMESTAMP,
    ValorImpostos FLOAT,
    ChaveAcesso VARCHAR(100),
    FOREIGN KEY (IDpedido) REFERENCES Pedido(IDpedido)
);