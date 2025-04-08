-- V2__insert_clientes_enderecos.sql

-- Inserindo clientes
INSERT INTO clientes (id, nome, cpf, data_nascimento) VALUES
    ('a1b2c3d4-e5f6-7890-abcd-1234567890aa', 'João Silva', '123.456.789-00', '1990-05-15'),
    ('b2c3d4e5-f6a7-8901-bcde-2345678901bb', 'Maria Oliveira', '987.654.321-00', '1985-09-23'),
    ('c3d4e5f6-a7b8-9012-cdef-3456789012cc', 'Carlos Souza', '456.789.123-00', '1992-12-01');

-- Inserindo endereços para os clientes
INSERT INTO enderecos (id, cliente_id, rua, numero, cep, cidade, estado, complemento) VALUES
    (uuid_generate_v4(), 'a1b2c3d4-e5f6-7890-abcd-1234567890aa', 'Rua das Palmeiras', '123', '01001-000', 'São Paulo', 'SP', 'Apto 12'),
    (uuid_generate_v4(), 'b2c3d4e5-f6a7-8901-bcde-2345678901bb', 'Avenida Brasil', '456', '20031-000', 'Rio de Janeiro', 'RJ', null),
    (uuid_generate_v4(), 'c3d4e5f6-a7b8-9012-cdef-3456789012cc', 'Rua das Flores', '789', '30130-010', 'Belo Horizonte', 'MG', 'Casa');
