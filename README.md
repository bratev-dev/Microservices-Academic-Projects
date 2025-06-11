# Sistema de Gestión de Proyectos Académicos

Este repositorio contiene un conjunto de microservicios desarrollados con el objetivo de gestionar proyectos académicos en un entorno educativo. El sistema está orientado a permitir la administración de usuarios, proyectos, entregas, y roles académicos de manera distribuida, utilizando una arquitectura basada en microservicios.

---

## Arquitectura del Sistema

El sistema está dividido en múltiples microservicios independientes que se comunican entre sí. Cada servicio encapsula una funcionalidad específica del dominio:

- **Gateway-service**: Autenticación y Autorización de usuarios (estudiantes, docentes, administradores).
- **Company-service**: Gestión de Empresas y sus proyectos académicos postulados.
- **Coordinator-service**: Gestion de postulaciones y evaluaciones.
- **Student-service**: Gestión de postulaciones y revisiones de proyectos.

---

## Tecnologías Utilizadas

- **Java 11** y **Spring Boot**
- **Spring Cloud** (Eureka, Config Server, Gateway)
- **Docker** para contenerización
- **Maven** como gestor de dependencias
- **JWT** para autenticación
- **Lombok** para simplificación del código

---

## Cómo Ejecutar el Proyecto

### Prerrequisitos

- Java 11
- Maven 3.8+
- Docker y Docker Compose

### Clonación del Repositorio

```bash
git clone https://github.com/bratev-dev/Microservices-Academic-Projects.git
cd Microservices-Academic-Projects
```

---

## Autores:
- Javier Alexis Cerón Anacona
- Brayan Steven Gomes Lasso
- Jhonatan Palacios Gómez
- Juan David Perdomo Ramos
- Rubeiro Romero

