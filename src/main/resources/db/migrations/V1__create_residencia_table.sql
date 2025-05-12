CREATE TABLE residencia (
    id_residencia       VARCHAR(255) PRIMARY KEY,
    endereco            VARCHAR2(255) NOT NULL,
    tipo_residencia     VARCHAR2(50),
    area_m2             NUMBER,
    numero_moradores    NUMBER
);

