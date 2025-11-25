# Sistema de Login y Registro

Sistema de gestión de usuarios con login, registro y dashboard.

## Requisitos

- Java 8 
- MySQL Connector

## Configuración de Base de Datos

```sql
CREATE DATABASE IF NOT EXISTS sistema_login;
USE sistema_login;

CREATE TABLE IF NOT EXISTS usuarios (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    usuario VARCHAR(50) UNIQUE NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    telefono VARCHAR(20) NOT NULL,
    email VARCHAR(100) NOT NULL,
    contraseña VARCHAR(255) NOT NULL,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    estado VARCHAR(20) DEFAULT 'activo'
);
```

## Instalación

1. Descarga MySQL Connector/J desde [Maven Repository](https://mvnrepository.com/artifact/com.mysql/mysql-connector-j)
2. Crea una carpeta `libs/` en el directorio del proyecto
3. Coloca el JAR descargado en `libs/`


## Ejecutar

```bash
java -cp ".:libs/mysql-connector-j-9.5.0.jar" LoginForm
```

## Funcionalidades

- Login con validación de campos
- Registro de usuarios con todos los campos obligatorios
- Confirmación de contraseña
- Tabla editable de usuarios

## Validaciones

- Campos vacíos en login muestran mensaje específico
- Validación individual de campos en registro
- Verificación de coincidencia de contraseñas
