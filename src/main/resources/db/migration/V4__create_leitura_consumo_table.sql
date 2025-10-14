CREATE TABLE leitura_consumo (
    id_leitura          NUMBER(19) NOT NULL PRIMARY KEY,
    id_residencia       NUMBER NOT NULL,
    data_leitura        DATE DEFAULT SYSDATE,
    consumo_total_kw    FLOAT NOT NULL,
    fonte_principal_id  NUMBER
);