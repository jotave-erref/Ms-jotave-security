CREATE TABLE alertas (
    id BIGSERIAL PRIMARY KEY,
    sensor_id VARCHAR(255),
    apartamento VARCHAR(255),
    bloco VARCHAR(255),
    status VARCHAR(50) NOT NULL,
    data_hora_ocorrencia TIMESTAMP,
    data_hora_processamento TIMESTAMP
);