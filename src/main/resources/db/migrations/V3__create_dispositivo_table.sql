CREATE TABLE dispositivo (
    id_dispositivo      VARCHAR(255) PRIMARY KEY,
    nome_dispositivo    VARCHAR2(100) NOT NULL,
    tipo                VARCHAR2(50),
    consumo_medio_kw    NUMBER,
    id_residencia       NUMBER NOT NULL
);

