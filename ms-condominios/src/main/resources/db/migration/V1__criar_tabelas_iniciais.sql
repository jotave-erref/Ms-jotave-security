CREATE TABLE apartamento (
    id BIGSERIAL PRIMARY KEY,
    numero VARCHAR(255) NOT NULL,
    bloco VARCHAR(255)
);

CREATE TABLE morador (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    apartamento_id BIGINT,
    CONSTRAINT fk_morador_apartamento FOREIGN KEY (apartamento_id) REFERENCES apartamento(id)
);