CREATE TABLE fonte_energia (
    id_fonte            VARCHAR(255) PRIMARY KEY,
    tipo_fonte          VARCHAR2(50) NOT NULL,
    capacidade_kw       NUMBER,
    id_residencia       NUMBER NOT NULL
);

