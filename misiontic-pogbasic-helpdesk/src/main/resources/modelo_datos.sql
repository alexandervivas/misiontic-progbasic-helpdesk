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
    id_usuario MEDIUMINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    cedula VARCHAR(12),
    nombre VARCHAR(100) NOT NULL,
    correo VARCHAR(150)
);

CREATE TABLE empleados (
    id_empleado MEDIUMINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    cedula VARCHAR(12),
    nombre VARCHAR(100) NOT NULL,
    correo VARCHAR(150),
    rol VARCHAR(15)
);

CREATE TABLE solicitudes (
    id_solicitud MEDIUMINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    descripcion TEXT NOT NULL,
    correo VARCHAR(150),
    usuario_creador MEDIUMINT NOT NULL,
    FOREIGN KEY (usuario_creador) REFERENCES usuarios(id_usuario)
);

CREATE TABLE actividades (
    id_actividad INT NOT NULL,
    id_empleado MEDIUMINT NOT NULL,
    id_solicitud MEDIUMINT NOT NULL,
    descripcion TEXT NOT NULL,
    fecha DATE NOT NULL,
    PRIMARY KEY(id_actividad, id_empleado, id_solicitud),
    FOREIGN KEY (id_empleado) REFERENCES empleados(id_empleado),
    FOREIGN KEY (id_solicitud) REFERENCES solicitudes(id_solicitud)
);
