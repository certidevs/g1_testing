# 🎬 Only Film

Sistema de gestión de cine desarrollado con Spring Boot y Thymeleaf, orientado a la gestión de películas, sesiones, salas, entradas y usuarios.

El proyecto ha sido desarrollado siguiendo un enfoque académico-profesional, poniendo especial atención en:

* Arquitectura MVC
* Testing automatizado
* Calidad software
* Integración continua
* Trabajo colaborativo con GitHub
* Buenas prácticas de desarrollo

---

# 📌 Descripción del proyecto

Only Film es una aplicación web que simula el funcionamiento básico de un cine moderno.

La plataforma permite:

* Gestionar películas en cartelera
* Administrar salas de proyección
* Configurar sesiones y horarios
* Gestionar tickets y reservas
* Gestionar usuarios y autenticación
* Visualizar información mediante templates Thymeleaf

Además, el proyecto incorpora testing automatizado y pipelines CI/CD para aproximarse a un entorno de desarrollo profesional real.

---

# 🧱 Funcionalidades principales

## 🎞️ Gestión de películas

* Alta y visualización de películas
* Género, duración y dirección
* Asociación con sesiones

## 🏛️ Gestión de salas

* Capacidad
* Tipo de pantalla
* Gestión de disponibilidad

## 🕒 Gestión de sesiones

* Asociación película/sala
* Horarios
* Precio
* Idioma de proyección

## 🎟️ Gestión de tickets

* Compra de entradas
* Gestión de asientos
* Estado de tickets
* Asociación con usuarios

## ⭐ Reviews

* Valoraciones de películas
* Comentarios
* Sistema de puntuación

---

# 🛠️ Stack tecnológico

## Backend

* Java 25
* Spring Boot
* Spring MVC
* Spring Data JPA
* Hibernate

## Frontend

* Thymeleaf
* HTML5
* CSS3
* Bootstrap

## Base de datos

* H2 Database

## Build & Dependency Management

* Maven

## Testing

* JUnit 5
* Mockito
* Spring Boot Test
* MockMvc
* Selenium WebDriver

## DevOps & CI/CD

* Git
* GitHub
* GitHub Actions

---

# 🧪 Testing

El proyecto incorpora diferentes niveles de testing automatizado.

## Backend Testing

### Controller Tests

Validación de endpoints MVC utilizando MockMvc.

### Repository Tests

Verificación de consultas JPA y persistencia.

### Service Tests

Testing de lógica de negocio mediante mocks y aislamiento de dependencias.

## UI Testing con Selenium

Se realizan pruebas E2E sobre:

* Navegación
* Formularios
* Login
* Flujos de reserva
* Validaciones visuales
* Interacción con templates Thymeleaf

---

# ⚙️ Integración continua (CI/CD)

El proyecto utiliza GitHub Actions para automatizar:

* Build del proyecto
* Ejecución de tests
* Verificación de integración
* Validación básica de calidad

## Nota sobre ejecución del pipeline

Debido a las limitaciones de minutos mensuales disponibles en GitHub Actions dentro del entorno académico compartido, los workflows CI/CD se ejecutan manualmente mediante `workflow_dispatch` en lugar de hacerlo automáticamente en cada push.

Esto permite optimizar recursos sin renunciar a la validación continua del proyecto.

---

# 📂 Estructura del proyecto

```text
src/
 ├── main/
 │    ├── java/
 │    │     ├── controller/
 │    │     ├── model/
 │    │     ├── repository/
 │    │     ├── service/
 │    │     └── config/
 │    │
 │    └── resources/
 │          ├── templates/
 │          ├── static/
 │          └── application.properties
 │
 └── test/
      ├── java/
      │     ├── controller/
      │     ├── repository/
      │     ├── service/
      │     └── selenium/
```

---

# 🚀 Instalación y ejecución

## Requisitos

* Java 25
* Maven
* Git

---

## Clonar el repositorio

```bash
git clone https://github.com/certidevs/g1_testing.git
```

---

## Ejecutar la aplicación

```bash
mvn spring-boot:run
```

La aplicación estará disponible en:

```text
http://localhost:8080
```

---

# 🧪 Ejecutar tests

## Tests backend

```bash
mvn test
```

## Tests completos incluyendo Selenium

```bash
mvn verify
```

---

# 👥 Equipo de desarrollo

* Andrés Soto
* Adrián López de Haro
* Barbara Urbano
* Fran Ramírez Martín


---

# 📈 Objetivos académicos y técnicos

Este proyecto tiene como objetivos:

* Aplicar arquitectura MVC con Spring Boot
* Trabajar con entidades relacionales y JPA
* Implementar testing automatizado multinivel
* Utilizar integración continua
* Simular flujos de trabajo colaborativos reales
* Mejorar la calidad y mantenibilidad del software

---

# 🔮 Mejoras futuras

* Sistema avanzado de reservas
* Pasarela de pago
* API REST
* Dockerización
* Persistencia en MySQL/PostgreSQL
* Despliegue cloud
* Cobertura de tests avanzada
* SonarQube
* Roles y permisos avanzados

---

# 🔗 Repositorio

[Repositorio GitHub - Only Film](https://github.com/certidevs/g1_testing?utm_source=chatgpt.com)

---

# 📄 Licencia

Proyecto académico desarrollado con fines educativos.

