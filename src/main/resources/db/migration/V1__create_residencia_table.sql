CREATE TABLE residencia (
    id_residencia       NUMBER(19) NOT NULL PRIMARY KEY,
    endereco            VARCHAR2(255) NOT NULL,
    tipo_residencia     VARCHAR2(50),
    aream2              FLOAT,
    numero_moradores    NUMBER
);

