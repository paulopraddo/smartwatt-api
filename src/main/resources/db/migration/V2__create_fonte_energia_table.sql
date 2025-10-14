CREATE TABLE fonte_energia (
    id_fonte            NUMBER(19) NOT NULL PRIMARY KEY,
    tipo_fonte          VARCHAR2(50) NOT NULL,
    capacidade_kw       FLOAT,
    id_residencia       NUMBER NOT NULL
);

