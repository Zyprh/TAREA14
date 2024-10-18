CREATE DATABASE gestion_empleados;

USE gestion_empleados;

CREATE TABLE empleados (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50),
    apellido VARCHAR(50),
    tipo_documento VARCHAR(20),
    numero_documento VARCHAR(20),
    direccion VARCHAR(100),
    telefono VARCHAR(20),
    correo_electronico VARCHAR(50),
    fecha_nacimiento DATE,
    estado_civil VARCHAR(20),
    sexo VARCHAR(20),
    cargo VARCHAR(50),
    departamento VARCHAR(50),
    salario DECIMAL(10,2),
    fecha_ingreso DATE,
    estado BOOLEAN,
    tipo_pago varchar(50),
    anosExperiencia int(11) DEFAULT NULL
);
select * from empleados;