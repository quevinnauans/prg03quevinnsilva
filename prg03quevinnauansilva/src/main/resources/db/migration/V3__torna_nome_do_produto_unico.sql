-- V3: não permite dois produtos com nomes iguais

ALTER TABLE produto ADD CONSTRAINT uk_produto_nome UNIQUE (nome);