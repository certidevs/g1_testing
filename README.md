# 🎬 Only Film

> Aplicación web de gestión de cine desarrollada con **Spring Boot**, **Thymeleaf** y enfoque profesional en **testing automatizado**, **calidad software** e **integración continua**.

<p align="center">
  <img src="https://img.shields.io/badge/Java-25-orange?logo=openjdk&logoColor=white" alt="Java 25">
  <img src="https://img.shields.io/badge/Spring%20Boot-4.0.5-6DB33F?logo=springboot&logoColor=white" alt="Spring Boot">
  <img src="https://img.shields.io/badge/Thymeleaf-server--side-005F0F?logo=thymeleaf&logoColor=white" alt="Thymeleaf">
  <img src="https://img.shields.io/badge/H2-in--memory-1F6FEB" alt="H2">
  <img src="https://img.shields.io/badge/Testing-JUnit%20%7C%20Mockito%20%7C%20Selenium-red" alt="Testing">
  <img src="https://img.shields.io/badge/CI-GitHub%20Actions-2088FF?logo=githubactions&logoColor=white" alt="GitHub Actions">
</p>

---

# ✨ ¿Qué es?

**Only Film** es una aplicación web completa de gestión de cine desarrollada con Spring Boot y Thymeleaf, donde:

* 👀 Los visitantes pueden consultar películas, sesiones y disponibilidad.
* 🔐 Los usuarios registrados pueden comprar entradas y acceder a sus tickets QR.
* 🛠️ Los administradores gestionan películas, salas, sesiones y usuarios desde un panel administrativo.

El proyecto pone especial énfasis en:

* arquitectura MVC
* testing automatizado
* calidad software
* integración continua
* buenas prácticas de desarrollo

---

# 🚀 Funcionalidades principales

## 👤 Usuario anónimo

* Consultar cartelera
* Ver detalles de películas
* Ver sesiones disponibles
* Registro e inicio de sesión

## 👥 Usuario autenticado

* Compra de entradas
* Selección de butacas
* Checkout de tickets
* Tickets QR
* Historial de entradas

## 🛠️ Administrador

* CRUD de películas
* CRUD de salas
* CRUD de sesiones
* Gestión de usuarios
* Activación/desactivación lógica de elementos

---

# 🧱 Modelo de datos

| Entidad     | Descripción                                    |
| ----------- | ---------------------------------------------- |
| **Movie**   | Películas disponibles en cartelera             |
| **Room**    | Salas de proyección                            |
| **Session** | Sesiones asociadas a película y sala           |
| **Ticket**  | Entradas asociadas a usuario y sesión          |
| **Review**  | Valoraciones y comentarios                     |
| **User**    | Usuarios con roles (`ROLE_USER`, `ROLE_ADMIN`) |

---

# 🛠️ Stack tecnológico

## Backend

* Java 25
* Spring Boot 4
* Spring MVC
* Spring Data JPA
* Hibernate

## Frontend

* Thymeleaf
* Bootstrap 5
* HTML5
* CSS3

## Base de datos

* H2 Database

## Testing

* JUnit 5
* Mockito
* MockMvc
* Selenium WebDriver

## DevOps & CI/CD

* Git
* GitHub
* GitHub Actions

---

# 🧠 Detalles técnicos destacables

* Arquitectura MVC desacoplada mediante:

    * Controller
    * Service
    * Repository

* Testing multinivel:

    * Repository Tests
    * Service Tests
    * Controller Tests
    * Selenium UI Tests

* Seguridad con Spring Security y roles:

    * `ROLE_USER`
    * `ROLE_ADMIN`

* Generación dinámica de tickets QR.

* Gestión de estados de tickets:

    * `LIBRE`
    * `PAGADO`
    * `CANCELADO`

* Borrado lógico mediante atributo `active`.

* Integración continua mediante GitHub Actions.

* Base de datos H2 en memoria para testing y desarrollo rápido.

---

# 🧪 Testing y calidad software

El proyecto incorpora diferentes niveles de testing automatizado.

## Backend Testing

### Repository Tests

Validación de consultas JPA y persistencia.

### Service Tests

Testing de lógica de negocio utilizando Mockito y mocks.

### Controller Tests

Validación de endpoints MVC mediante MockMvc.

---

## UI Testing con Selenium

Automatización del flujo completo de usuario:

* Login
* Navegación
* Selección de butaca
* Checkout
* Generación de ticket QR

---

## CI/CD

GitHub Actions automatiza:

* compilación
* ejecución de tests
* validación de integración

> ℹ️ Los workflows se ejecutan manualmente mediante `workflow_dispatch`
> para optimizar el consumo de minutos disponibles en el entorno académico compartido.

---

# 🔒 Permisos por rol

| Acción                   | Visitante | Usuario | Admin |
| ------------------------ | :-------: | :-----: | :---: |
| Ver películas y sesiones |     ✅     |    ✅    |   ✅   |
| Comprar tickets          |     ❌     |    ✅    |   ✅   |
| Acceder a tickets QR     |     ❌     |    ✅    |   ✅   |
| Gestionar películas      |     ❌     |    ❌    |   ✅   |
| Gestionar sesiones       |     ❌     |    ❌    |   ✅   |
| Gestionar usuarios       |     ❌     |    ❌    |   ✅   |

---

# ▶️ Cómo arrancar el proyecto

## Requisitos

* Java 25
* Maven
* Git

---

## Clonar repositorio

```bash
git clone https://github.com/certidevs/g1_testing.git
cd g1_testing
```

---

## Ejecutar aplicación

```bash
mvn spring-boot:run
```

Abrir en navegador:

```text
http://localhost:8080
```

---

# 🔑 Cuentas demo

| Usuario | Contraseña | Rol           |
| ------- | ---------- | ------------- |
| admin   | admin      | Administrador |
| user    | user       | Usuario       |

---

# 🗄️ Consola H2

URL:

```text
http://localhost:8080/h2-console
```

JDBC URL:

```text
jdbc:h2:mem:testdb
```

Usuario:

```text
sa
```

Contraseña:

```text
(vacía)
```

---

# 🗂️ Estructura del proyecto

```text
src/main/java/com/demo
├── config/        # Seguridad y configuración
├── controller/    # Controladores MVC
├── model/         # Entidades JPA y enums
├── repository/    # Repositorios Spring Data JPA
├── service/       # Lógica de negocio
└── dto/           # DTOs y formularios

src/test/java/com/demo
├── controller/    # Controller Tests
├── repository/    # Repository Tests
├── service/       # Service Tests
└── ui/            # Selenium Tests
```

---

# 📸 Capturas

## 🎬 Cartelera

![Cartelera](docs/screenshots/cartelera.png)

## 💳 Checkout

![Checkout](docs/screenshots/checkout.png)

## 🎟️ Ticket QR

![Ticket QR](docs/screenshots/ticket.png)

## 🛠️ Panel Admin

![Admin](docs/screenshots/admin.png)

---

# 👥 Equipo de desarrollo

* Fran Ramírez Martín
* Adrián López de Haro
* Barbara Urbano
* Andrés Soto

---

# 🚀 Posibles mejoras futuras

* API REST
* Dockerización
* PostgreSQL/MySQL
* SonarQube
* Despliegue cloud
* Pasarela de pago
* JWT/API móvil
* Cobertura avanzada de tests

---

# 🔗 Repositorio

https://github.com/certidevs/g1_testing

---

# 📄 Licencia

Proyecto académico desarrollado con fines educativos.

---

<p align="center">
  Hecho con ☕ Java + 🍃 Spring Boot + 🎬 pasión por el testing
</p>

