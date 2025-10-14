CREATE TABLE dispositivo (
    id_dispositivo      NUMBER(19) NOT NULL PRIMARY KEY,
    nome_dispositivo    VARCHAR2(100) NOT NULL,
    tipo                VARCHAR2(50),
    consumo_medio_kw    FLOAT,
    id_residencia       NUMBER NOT NULL
);

