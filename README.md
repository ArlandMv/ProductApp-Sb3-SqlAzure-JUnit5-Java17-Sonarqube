# ProductApp-Sb3-SqlAzure-JUnit5-Java17-Sonarqube

Proyecto Backend de "ProductApp" pensado para demostrar habilidades fundamentales para un desarrollo  eficiente, pruebas unitarias y otras buenas practicas.

**Enlace al Repositorio del Frontend:** [ProductApp Frontend](https://github.com/ArlandMv/ProductAppV1-ngModules-Services-Bootstrap-Jasmine)

## Table of Contents
1. [Resumen](#1-resumen)
2. [Tecnologías Utilizadas](#2-tecnologías-utilizadas)
3. [Empezar](#3-empezar)
   - [3.1 Requisitos](#31-requisitos)
   - [3.2 Configuración](#32-configuración)
   - [3.3 Instalación](#33-instalación)
   - [3.4 Tests](#34-tests)
   - [3.5 Primeras interacciones con la API](#35-primeras-interacciones-con-la-api)
4. [Documentación](#4-documentación)
   - [4.1 Product Model v1](#41-product-model-v1)
   - [4.2 Puntos de Acceso (Endpoints)](#42-puntos-de-acceso-endpoints)
   - [4.3 Documentos Postman](#43-documentos-postman)
5. [Contribuciones](#5-contribuciones)
6. [Licencia](#6-licencia)

---

## 1. Resumen

Esta es una API RESTful simple para la gestión de productos. Proporciona operaciones CRUD básicas (Crear, LeerTodos, LeerPorId, Actualizar, Eliminar) para entidades de productos.

## 2. Tecnologías Utilizadas

- Spring Boot 3
- Maven
- Java 17
- SQL Azure
- Postman
- JUnit 5 
- Mockito
- SonarQube

## 3. Empezar

### 3.1 Requisitos

- Git
- Java 17 por Sonarque 
- Maven
- Mysql

### 3.2 Configuracion
Ejemplo de credenciales mysql
- Usuario: root 
- Contraseña: mypsw


1. Crear DATABASE mediante consola:   
Ingresar a mysql con clave mypsw despues de ejecutar `mysql -u root -p`

Usar script para crear y verificar base de datos:
```
CREATE DATABASE products_db;
show databases;
use products_db;
```

2. Modificar application-dev.properties:
- **spring.datasource.url**= jdbc:mysql://localhost:3306/[DB]?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC
- **spring.datasource.username**=[USER]
- **spring.datasource.password**=[PASSWORD]

```
spring.datasource.url=jdbc:mysql://localhost:3306/products_db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=mypsw
```

### 3.3 Instalación

1. Clonar el repositorio: 
```
git clone https://github.com/ArlandMv/ProductApp-Sb3-SqlAzure-JUnit5-Java17-Sonarqube
```
3. Navegar al directorio del proyecto: `cd ProductApp-Sb3..`
4. Construir el proyecto: `mvn clean install`
5. Ejecutar la aplicación: `java -jar target/api-gestion-productos.jar`

### 3.4 Tests

Ejecutar todas las pruebas unitarias:
```
mvn test
```

Ejecutar una prueba unitaria específica por su nombre de clase:
```
mvn -Dtest=NombreDeLaClaseTest test
```

### 3.5 Primeras interacciones con la API

```
curl -X POST http://localhost:8080/api/v1/products
```

## 4. Documentación

## 4.1 Product Model v1:
| Parámetro    | Tipo     | Descripción           |
| :----------- | :------- | :-------------------- |
| `id`         | `Long`   |  GeneratedValue       |
| `name`       | `String` | Nombre del producto   |
| `precio`     | `Double` | Precio del producto   |
| `lastModified`| `Date`   | Ultima vez Modificado |

## 4.2 Puntos de Acceso (Endpoints)

- `/api/v1/productos`
- `/api/v1/productos//{id}` 

## 4.3 Documentos Postman 

Consulte el archivo [Postman](https://github.com/ArlandMv/ProductApp-Sb3-SqlAzure-JUnit5-Java17-Sonarqube/blob/main/Postman) para obtener más detalles.

## 5. Contribuciones

Siéntase libre de contribuir abriendo problemas o enviando solicitudes de extracción.

## 6. Licencia

Este proyecto está licenciado bajo la Licencia [MIT](https://choosealicense.com/licenses/mit/). Consulte el archivo [LICENSE](https://github.com/ArlandMv/ProductApp-Sb3-SqlAzure-JUnit5-Java17-Sonarqube/blob/main/LICENSE) para obtener más detalles.
