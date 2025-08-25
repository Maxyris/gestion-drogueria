CREATE TABLE medicamentos (
    id_medicamento NUMBER PRIMARY KEY,
    nombre VARCHAR2(100) NOT NULL,
    laboratorio VARCHAR2(100) NOT NULL,
    fecha_fabricacion DATE NOT NULL,
    fecha_vencimiento DATE NOT NULL,
    cantidad_stock NUMBER(10,2) DEFAULT 0,
    valor_unitario NUMBER(10,2) NOT NULL,
    fecha_creacion DATE DEFAULT SYSDATE,
    fecha_actualizacion DATE DEFAULT SYSDATE,
    estado VARCHAR2(1) DEFAULT 1 NOT NULL
);

-- Secuencia para ID de medicamentos
CREATE SEQUENCE seq_medicamentos
    START WITH 1
    INCREMENT BY 1
    NOCACHE;

-- Trigger para auto-incrementar ID y actualizar fecha_actualizacion
CREATE OR REPLACE TRIGGER tr_medicamentos_bi
    BEFORE INSERT ON medicamentos
    FOR EACH ROW
BEGIN
    IF :NEW.id_medicamento IS NULL THEN
        :NEW.id_medicamento := seq_medicamentos.NEXTVAL;
    END IF;
    :NEW.fecha_creacion := SYSDATE;
    :NEW.fecha_actualizacion := SYSDATE;
END;
/

CREATE TABLE ventas (
    id_venta NUMBER PRIMARY KEY,
    fecha_hora TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    id_medicamento NUMBER NOT NULL,
    cantidad NUMBER(10,2) NOT NULL,
    valor_unitario NUMBER(10,2) NOT NULL,
    valor_total NUMBER(10,2) NOT NULL,
    
    -- Llave foránea
    CONSTRAINT fk_ventas_medicamento 
        FOREIGN KEY (id_medicamento) 
        REFERENCES medicamentos(id_medicamento)
        ON DELETE CASCADE
);

-- Secuencia para ID de ventas
CREATE SEQUENCE seq_ventas
    START WITH 1
    INCREMENT BY 1
    NOCACHE;

-- Trigger para auto-incrementar ID de ventas
CREATE OR REPLACE TRIGGER tr_ventas_bi
    BEFORE INSERT ON ventas
    FOR EACH ROW
BEGIN
    IF :NEW.id_venta IS NULL THEN
        :NEW.id_venta := seq_ventas.NEXTVAL;
    END IF;
END;
/