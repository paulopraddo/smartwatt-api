CREATE TABLE meta_consumo (
    id_meta             NUMBER(19) NOT NULL PRIMARY KEY,
    id_residencia       NUMBER NOT NULL,
    mes_referencia      VARCHAR2(7) NOT NULL,
    limite_kw           FLOAT NOT NULL,
    status_meta         VARCHAR2(20)
);
