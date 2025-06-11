# Sistema de Gestión de Proyectos Académicos

## Autores:
- Javier Alexis Cerón Anacona
- Brayan Steven Gomes Lasso
- Jhonatan Palacios Gómez
- Juan David Perdomo Ramos
- Rubeiro Romero

Este repositorio contiene un conjunto de microservicios desarrollados con el objetivo de gestionar proyectos académicos en un entorno educativo. El sistema está orientado a permitir la administración de usuarios, proyectos, entregas, calificaciones y roles académicos de manera distribuida, utilizando una arquitectura basada en microservicios.

---

## Arquitectura del Sistema

El sistema está dividido en múltiples microservicios independientes que se comunican entre sí. Cada servicio encapsula una funcionalidad específica del dominio:

- **users-service**: Manejo de usuarios (estudiantes, docentes, administradores).
- **project-service**: Gestión de proyectos académicos.
- **qualification-service**: Administración de calificaciones y evaluaciones.
- **delivery-service**: Control de entregas y revisiones de proyectos.
- **gateway-service**: API Gateway para unificar el acceso a los servicios.
- **config-service**: Servicio de configuración centralizada.
- **eureka-server**: Descubrimiento de servicios (Service Registry).
- **admin-service**: Servicio administrativo y monitoreo.
- **security-service**: Autenticación y autorización.

Cada servicio sigue el principio de responsabilidad única y puede desplegarse de forma independiente.

---

## Tecnologías Utilizadas

- **Java 11** y **Spring Boot**
- **Spring Cloud** (Eureka, Config Server, Gateway)
- **Docker** para contenerización
- **Maven** como gestor de dependencias
- **JWT** para autenticación
- **MySQL/PostgreSQL** (dependiendo del servicio)
- **Lombok** para simplificación del código

---

## Cómo Ejecutar el Proyecto

### Prerrequisitos

- Java 11
- Maven 3.8+
- Docker y Docker Compose (opcional pero recomendado)

### Clonación del Repositorio

```bash
git clone https://github.com/bratev-dev/Microservices-Academic-Projects.git
cd Microservices-Academic-Projects
