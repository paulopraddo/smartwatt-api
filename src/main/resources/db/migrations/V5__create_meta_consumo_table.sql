CREATE TABLE meta_consumo (
    id_meta             VARCHAR(255) PRIMARY KEY,
    id_residencia       NUMBER NOT NULL,
    mes_referencia      VARCHAR2(7) NOT NULL,
    limite_kw           NUMBER NOT NULL,
    status_meta         VARCHAR2(20)
);
