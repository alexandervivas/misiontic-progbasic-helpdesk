/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  alexandervivas
 * Created: Aug 18, 2021
 */

/*
 * String: VARCHAR(longitud), text en caso de las descripciones
 * int: INT
 * DateTime: DATE
 */
CREATE TABLE usuarios (
    id_usuario MEDIUMINT NOT NULL AUTO_INCREMENT,
    cedula VARCHAR(12),
    nombre VARCHAR(100) NOT NULL,
    correo VARCHAR(150),
    CONSTRAINT pk_usuarios 
        PRIMARY KEY(id_usuario)
);

CREATE TABLE empleados (
    id_empleado MEDIUMINT NOT NULL AUTO_INCREMENT,
    cedula VARCHAR(12),
    nombre VARCHAR(100) NOT NULL,
    correo VARCHAR(150),
    rol VARCHAR(15),
    CONSTRAINT pk_empleados 
        PRIMARY KEY(id_empleado)
);

CREATE TABLE solicitudes (
    id_solicitud MEDIUMINT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(100) NOT NULL,
    descripcion TEXT NOT NULL,
    estado VARCHAR(150),
    usuario_creador MEDIUMINT NOT NULL,
    CONSTRAINT pk_solicitudes 
        PRIMARY KEY(id_solicitud),
    CONSTRAINT fk_solicitudes_usuarios 
        FOREIGN KEY (usuario_creador) 
        REFERENCES usuarios(id_usuario)
);

CREATE TABLE actividades (
    id_actividad INT NOT NULL,
    id_empleado MEDIUMINT NOT NULL,
    id_solicitud MEDIUMINT NOT NULL,
    descripcion TEXT NOT NULL,
    fecha DATE NOT NULL,
    CONSTRAINT pk_actividades 
        PRIMARY KEY(id_actividad, id_empleado, id_solicitud),
    CONSTRAINT fk_actividades_empleados 
        FOREIGN KEY (id_empleado) 
        REFERENCES empleados(id_empleado),
    CONSTRAINT fk_actividades_solicitudes 
        FOREIGN KEY (id_solicitud) 
        REFERENCES solicitudes(id_solicitud)
);

INSERT INTO usuarios (cedula, nombre, correo) VALUES ('CC1', 'Alexander', 'alex@mail.com');
