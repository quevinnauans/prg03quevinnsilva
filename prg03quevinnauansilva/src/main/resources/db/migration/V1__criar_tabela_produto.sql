
-- V1: cria a tabela produto

CREATE TABLE produto (
    id BIGSERIAL,
    codigo INTEGER NOT NULL,
    nome VARCHAR(100) NOT NULL,
    preco DOUBLE PRECISION NOT NULL,

    CONSTRAINT pk_produto PRIMARY KEY (id),
    CONSTRAINT uk_produto_codigo UNIQUE (codigo)
);
