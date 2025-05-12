CREATE TABLE leitura_consumo (
    id_leitura          VARCHAR(255) PRIMARY KEY,
    id_residencia       NUMBER NOT NULL,
    data_leitura        DATE DEFAULT SYSDATE,
    consumo_total_kw    NUMBER NOT NULL,
    fonte_principal_id  NUMBER
);