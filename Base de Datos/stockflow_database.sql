/* CREACION DE LA BASE DE DATOS*/
CREATE DATABASE IF NOT EXISTS stockflow_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE stockflow_db;

/* TABLA ROLES (Normalización para control de accesos) */
CREATE TABLE IF NOT EXISTS roles (
    id_rol INT AUTO_INCREMENT PRIMARY KEY,
    nombre_rol VARCHAR(50) NOT NULL UNIQUE
) ENGINE=InnoDB;

-- 2. Tabla de Usuarios (Relacionada con la tabla roles mediante llave foránea)
CREATE TABLE IF NOT EXISTS usuarios (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    id_rol INT NOT NULL,
    CONSTRAINT fk_usuario_rol FOREIGN KEY (id_rol) REFERENCES roles(id_rol)
    ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB;

-- 3. Tabla de Categorías (Agrupación de productos)
CREATE TABLE IF NOT EXISTS categorias (
    id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nombre_categoria VARCHAR(100) NOT NULL
) ENGINE=InnoDB;

-- 4. Tabla de Productos (Inventario central)
CREATE TABLE IF NOT EXISTS productos (
    id_producto INT AUTO_INCREMENT PRIMARY KEY,
    codigo VARCHAR(50) NOT NULL UNIQUE,
    nombre VARCHAR(150) NOT NULL,
    precio DECIMAL(10, 2) NOT NULL,
    stock INT NOT NULL,
    id_categoria INT,
    CONSTRAINT fk_categoria FOREIGN KEY (id_categoria) REFERENCES categorias(id_categoria)
    ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB;


-- ==========================================
-- Inserción de Datos Iniciales (Seeders)
-- ==========================================

-- Roles base
INSERT INTO roles (nombre_rol) VALUES 
('Administrador'), 
('Cajero');

-- Usuarios iniciales (Asumiendo id_rol 1 para Admin y 2 para Cajero)
INSERT INTO usuarios (nombre, username, password, id_rol) VALUES 
('Administrador General', 'admin', '1234', 1),
('Cajero de Turno', 'cajero1', '1234', 2);

-- Categorías iniciales de prueba
INSERT INTO categorias (nombre_categoria) VALUES 
('General'),
('Electrónica'),
('Bebidas');

--INSERCION DE PRODUCTOS
INSERT INTO productos (codigo,nombre,precio,stock,id_categoria) VALUES
();