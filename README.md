# 🚲 Sistema de Alquiler de Bicicletas

API REST desarrollada con **Java y Spring Boot** para la gestión de bicicletas y alquileres, implementando **Arquitectura Hexagonal (Ports & Adapters)**.

El sistema permite registrar bicicletas, iniciar alquileres, finalizar alquileres y calcular automáticamente los costos de acuerdo con el tipo de bicicleta y el tiempo real de uso, incluyendo multas por devolución tardía.

---

## 📑 Índice

- [Descripción del proyecto](#-descripción-del-proyecto)
- [Objetivos](#-objetivos)
- [Arquitectura](#-arquitectura)
- [Principios y patrones aplicados](#-principios-y-patrones-aplicados)
- [Tecnologías utilizadas](#-tecnologías-utilizadas)
- [Base de datos](#-base-de-datos)
- [Modelo de datos](#-modelo-de-datos)
- [Funcionalidades implementadas](#-funcionalidades-implementadas)
  - [HU-01 — Crear una bicicleta](#hu-01--crear-una-bicicleta)
  - [HU-02 — Iniciar un alquiler](#hu-02--iniciar-un-alquiler)
  - [HU-03 — Finalizar un alquiler](#hu-03--finalizar-un-alquiler)
- [Reglas de negocio](#-reglas-de-negocio)
- [Endpoints](#-endpoints)
- [Validaciones](#-validaciones)
- [Manejo de excepciones](#-manejo-de-excepciones)
- [Flujo de un alquiler](#-flujo-de-un-alquiler)
- [Estructura del proyecto](#-estructura-del-proyecto)
- [Configuración](#-configuración)
- [Ejecución](#-ejecución)
- [Pruebas funcionales](#-pruebas-funcionales)
- [Pruebas automatizadas](#-pruebas-automatizadas)
- [Estado actual](#-estado-actual)
- [Próximas funcionalidades](#-próximas-funcionalidades)
- [Autor](#-autor)

---

# 📌 Descripción del proyecto

Este proyecto consiste en una **API REST para la gestión de alquiler de bicicletas**.

La aplicación permite administrar el ciclo de vida de un alquiler:

```text
Registrar bicicleta
       ↓
Iniciar alquiler
       ↓
Bicicleta → ALQUILADA
       ↓
Finalizar alquiler
       ↓
Calcular tiempo real y costos
       ↓
Aplicar multa si corresponde
       ↓
Bicicleta → DISPONIBLE
```

La solución está construida siguiendo **Arquitectura Hexagonal**, separando la lógica de negocio de los detalles de infraestructura.

---

# 🎯 Objetivos

- Registrar y administrar bicicletas.
- Permitir iniciar alquileres.
- Permitir finalizar alquileres.
- Calcular automáticamente el costo del alquiler.
- Aplicar multas por devolución tardía.
- Mantener separadas las reglas de negocio de Spring, JPA y PostgreSQL.
- Facilitar pruebas y mantenimiento.
- Aplicar principios SOLID y Clean Code.

---

# 🏗️ Arquitectura

El proyecto utiliza **Arquitectura Hexagonal (Ports & Adapters)**.

```text
                    ┌─────────────────────┐
                    │      REST API       │
                    │     Controllers     │
                    └──────────┬──────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │  Puertos de entrada │
                    └──────────┬──────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │     Application     │
                    │      Services       │
                    └──────────┬──────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │       Domain        │
                    │ Model + Rules       │
                    └──────────┬──────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │  Puertos de salida  │
                    └──────────┬──────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │   Infrastructure   │
                    │   JPA / PostgreSQL │
                    └─────────────────────┘
```

## Domain

Contiene:

- Modelos de dominio.
- Excepciones de negocio.
- Puertos de entrada.
- Puertos de salida.
- Enums del negocio.

Principales modelos:

```text
Bicicleta
Alquiler
EstadoBicicleta
TipoBicicleta
```

El dominio no depende directamente de:

```text
Spring
JPA
Hibernate
PostgreSQL
Controllers
DTOs
```

## Application

Contiene los casos de uso y la lógica de aplicación.

Componentes principales:

```text
BicicletaValidador
BicicletaComandoServicio
BicicletaCodigoGenerador
AlquilerComandoServicio
AlquilerFinalizacionServicio
CalculadorCostoAlquiler
```

Responsabilidades:

- Coordinar casos de uso.
- Aplicar reglas de negocio.
- Utilizar puertos de entrada y salida.
- Mantener la lógica independiente de JPA y HTTP.

## Infrastructure

Contiene los detalles técnicos:

- Controllers REST.
- DTOs.
- Mapeadores.
- Entidades JPA.
- Repositorios Spring Data.
- Adaptadores.
- Configuración de Beans.
- Handlers de excepciones.

---

# 🧩 Principios y patrones aplicados

## SOLID

### Single Responsibility Principle (SRP)

Cada componente tiene una responsabilidad específica.

```text
Controller
→ HTTP

Mapper
→ transformación de objetos

Repository
→ persistencia

Servicio
→ caso de uso

CalculadorCostoAlquiler
→ cálculo de costos
```

### Dependency Inversion Principle (DIP)

Application depende de abstracciones (puertos) y no de implementaciones concretas de infraestructura.

---

## CQRS

Se separan operaciones de comando y consulta:

```text
AlquilerRepositorioComandoPuerto
AlquilerRepositorioConsultaPuerto

BicicletaRepositorioComandoPuerto
BicicletaRepositorioConsultaPuerto
```

---

## Inyección de dependencias

Las dependencias se administran mediante Spring y la configuración de Beans.

Ejemplo:

```java
@Bean
public AlquilerFinalizacionPuerto alquilerFinalizacionPuerto(...) {
    return new AlquilerFinalizacionServicio(...);
}
```

---

# 🛠️ Tecnologías utilizadas

| Tecnología | Uso |
|---|---|
| Java | Lenguaje principal |
| Spring Boot | Desarrollo de la API REST |
| Spring Web | Endpoints HTTP |
| Spring Data JPA | Persistencia |
| Hibernate | ORM |
| PostgreSQL | Base de datos |
| Maven | Gestión de dependencias y construcción |
| Jakarta Validation | Validación de datos |
| JUnit 5 | Pruebas automatizadas |
| Mockito | Mocking |
| Postman | Pruebas funcionales |
| Swagger / OpenAPI | Documentación de la API |

> Las versiones concretas utilizadas deben mantenerse sincronizadas con el `pom.xml`.

---

# 🗄️ Base de datos

El proyecto utiliza **PostgreSQL**.

Base de datos:

```text
alquilerbicicletas
```

## Tabla `registro_bicicletas`

Almacena:

```text
codigo
estado
tipo
```

## Tabla `alquiler_bicicletas`

Almacena:

```text
id
bicicleta_codigo
nombre_cliente
hora_inicio
hora_fin
duracion_estimada
tiempo_real_uso
costo_base
multa
costo_total
```

---

# 📊 Modelo de datos

Relación:

```text
Bicicleta
    1
    │
    │
    N
Alquiler
```

Una bicicleta puede tener múltiples alquileres históricos.

Mientras exista un alquiler activo para una bicicleta, esta permanece en estado:

```text
ALQUILADA
```

Al finalizar correctamente:

```text
ALQUILADA → DISPONIBLE
```

---

# 🚲 Funcionalidades implementadas

## HU-01 — Crear una bicicleta

Permite registrar una bicicleta.

Ejemplo:

```http
POST /api/bicicletas
```

Request:

```json
{
  "tipo": "URBANA"
}
```

Tipos disponibles:

```text
URBANA
MONTAÑA
ELECTRICA
```

Al registrarse:

```text
Código → generado automáticamente
Estado → DISPONIBLE
```

---

## HU-02 — Iniciar un alquiler

Permite iniciar un alquiler para una bicicleta disponible.

### Endpoint

```http
POST /api/alquileres
```

### Request

```json
{
  "codigoBicicleta": "BIC-001",
  "nombreCliente": "Juan García",
  "duracionEstimada": 2
}
```

### Flujo

```text
Bicicleta DISPONIBLE
        ↓
Bicicleta ALQUILADA
        ↓
Registrar horaInicio
        ↓
Crear alquiler activo
```

### Validaciones

- La bicicleta debe existir.
- La bicicleta debe estar disponible.
- El nombre debe ser válido.
- La duración debe ser mayor o igual a 1.
- El código de la bicicleta debe ser válido.

---

## HU-03 — Finalizar un alquiler

Permite finalizar un alquiler mediante el código de la bicicleta.

### Endpoint

```http
POST /api/alquileres/{codigoBicicleta}/finalizar
```

Ejemplo:

```http
POST /api/alquileres/BIC-001/finalizar
```

### Flujo

```text
Buscar alquiler activo
       ↓
Registrar horaFin
       ↓
Calcular tiempoRealUso
       ↓
Redondear horas cobrables
       ↓
Obtener tarifa
       ↓
Calcular costoBase
       ↓
Calcular horas de retraso
       ↓
Calcular multa
       ↓
Calcular costoTotal
       ↓
Bicicleta → DISPONIBLE
       ↓
Actualizar alquiler
```

---

# 💰 Tarifas

| Tipo | Tarifa/hora |
|---|---:|
| URBANA | $3.500 |
| MONTAÑA | $5.000 |
| ELECTRICA | $7.500 |

---

# ⏱️ Cálculo del tiempo real

El sistema calcula:

```text
horaFin - horaInicio
```

El resultado se utiliza para determinar las horas cobrables.

Ejemplos:

```text
1h 10min → 2 horas
2h exactas → 2 horas
3h 20min → 4 horas
```

---

# 💵 Cálculo del costo base

```text
costoBase = horasCobrables × tarifaHora
```

Ejemplo:

```text
4 × $5.000 = $20.000
```

---

# ⚠️ Multa por retraso

Si:

```text
tiempo real > duración estimada
```

se calcula el retraso.

La multa corresponde al:

```text
50% de la tarifa/hora
```

por cada hora de retraso, también redondeada al alza.

Ejemplo:

```text
Tipo: MONTAÑA
Tarifa: $5.000
Duración estimada: 2h
Tiempo real: 3h20min
```

Resultado:

```text
Horas cobradas: 4
Costo base: $20.000

Retraso: 1h20min
Horas de retraso: 2

Multa:
2 × ($5.000 × 50%)
= $5.000

Costo total:
$20.000 + $5.000
= $25.000
```

---

# 📦 Respuesta de finalización

Ejemplo:

```json
{
  "codigoBicicleta": "BIC-003",
  "nombreCliente": "Juan García",
  "duracionEstimada": 1,
  "tiempoRealUso": 1,
  "horaInicio": "2026-07-31T14:57:43",
  "horaFin": "2026-07-31T15:00:36",
  "costoBase": 3500,
  "multa": 0,
  "costoTotal": 3500
}
```

---

# 📋 Reglas de negocio

## RN-01 — Tarifas por tipo de bicicleta

```text
URBANA    → $3.500/h
MONTAÑA   → $5.000/h
ELECTRICA → $7.500/h
```

## RN-02 — Cálculo del costo base

El tiempo real se redondea al alza a la siguiente hora completa cuando existe una fracción.

## RN-03 — Multa por devolución tardía

50% de la tarifa por hora por cada hora de retraso redondeada al alza.

## RN-04 — No se puede alquilar una bicicleta no disponible

Solo una bicicleta en estado:

```text
DISPONIBLE
```

puede ser alquilada.

## RN-05 — No se puede finalizar un alquiler inexistente o terminado

Una solicitud de finalización sin alquiler activo debe generar un error descriptivo.

---

# 🌐 Endpoints

## Bicicletas

### Crear

```http
POST /api/bicicletas
```

### Consultar

```http
GET /api/bicicletas/{codigo}
```

### Actualizar

```http
PUT /api/bicicletas/{codigo}
```

### Eliminar

```http
DELETE /api/bicicletas/{codigo}
```

## Alquileres

### Iniciar

```http
POST /api/alquileres
```

### Finalizar

```http
POST /api/alquileres/{codigoBicicleta}/finalizar
```

---

# ✅ Validaciones

Se utilizan validaciones de Jakarta Bean Validation:

```java
@NotBlank
@NotNull
@Min(1)
@Pattern(...)
```

Ejemplos:

```text
Código obligatorio
Nombre obligatorio
Duración inválida
Código inválido
```

Códigos HTTP utilizados:

```text
400 → Datos inválidos / reglas de negocio
404 → Recurso no encontrado
500 → Error inesperado
```

---

# ⚠️ Manejo de excepciones

La API utiliza handlers globales para generar respuestas descriptivas.

Ejemplo:

```json
{
  "error": "La bicicleta BIC-001 no está disponible para alquiler"
}
```

En lugar de exponer detalles internos como stack traces.

Principales excepciones:

```text
DataNotFoundException
BicicletaNoDisponibleException
MethodArgumentNotValidException
```

---

# 🔄 Flujo completo del alquiler

```text
               Crear bicicleta
                      │
                      ▼
                 DISPONIBLE
                      │
                Iniciar alquiler
                      │
                      ▼
                  ALQUILADA
                      │
               Finalizar alquiler
                      │
                      ▼
              Registrar horaFin
                      │
                      ▼
              Calcular tiempo real
                      │
                      ▼
              Calcular costo base
                      │
                      ▼
               Calcular retraso
                      │
                      ▼
                Calcular multa
                      │
                      ▼
              Calcular costo total
                      │
                      ▼
                 DISPONIBLE
```

---

# 📁 Estructura del proyecto

```text
src
└── main
    └── java
        └── co.com.ceiba.alquilerbicicletas
            │
            ├── application
            │   └── service
            │       ├── AlquilerComandoServicio
            │       ├── AlquilerFinalizacionServicio
            │       ├── BicicletaCodigoGenerador
            │       ├── BicicletaComandoServicio
            │       ├── BicicletaValidador
            │       └── CalculadorCostoAlquiler
            │
            ├── domain
            │   ├── exception
            │   ├── model
            │   │   ├── Alquiler
            │   │   ├── Bicicleta
            │   │   ├── EstadoBicicleta
            │   │   └── TipoBicicleta
            │   │
            │   └── ports
            │       ├── in
            │       └── out
            │
            └── infrastructure
                ├── adapter
                │   ├── in
                │   │   └── rest
                │   └── out
                │       └── persistence
                │
                ├── config
                └── exception
```

---

# ⚙️ Configuración

Archivo:

```text
src/main/resources/application.properties
```

Ejemplo:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/alquilerbicicletas
spring.datasource.username=postgres
spring.datasource.password=TU_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

> No publicar contraseñas reales en el repositorio. Para ambientes compartidos se recomienda utilizar variables de entorno.

---

# ▶️ Ejecución

## Clonar

```bash
git clone <URL_DEL_REPOSITORIO>
```

## Entrar

```bash
cd alquilerbicicletas
```

## Crear base de datos

```sql
CREATE DATABASE alquilerbicicletas;
```

## Configurar credenciales

Actualizar:

```text
src/main/resources/application.properties
```

## Ejecutar Maven

```bash
mvn clean install
```

```bash
mvn spring-boot:run
```

La API estará disponible en:

```text
http://localhost:8080
```

---

# 🧪 Pruebas funcionales

Las pruebas funcionales se realizaron con **Postman**.

## HU-01

- Crear bicicleta.
- Validar generación del código.
- Validar estado inicial.
- Validar tipo.

## HU-02

- Iniciar alquiler correctamente.
- Bicicleta inexistente.
- Bicicleta no disponible.
- Nombre inválido.
- Duración inválida.
- Verificar cambio a `ALQUILADA`.

## HU-03

- Finalización correcta.
- Registro de `horaFin`.
- Cálculo de `tiempoRealUso`.
- Redondeo al alza.
- Cálculo de tarifa.
- Cálculo de costo base.
- Cálculo de multa.
- Cálculo de costo total.
- Cambio a `DISPONIBLE`.
- Alquiler inexistente.
- Alquiler ya finalizado.
- Posibilidad de volver a alquilar la bicicleta.

---

# 🧪 Pruebas automatizadas

El proyecto contempla pruebas mediante:

```text
JUnit 5
Mockito
Maven
```

Se consideran escenarios:

- Casos exitosos.
- Validaciones.
- Excepciones.
- Reglas de negocio.
- Casos límite.
- Servicios de aplicación.

Las pruebas automatizadas se consolidarán para todas las Historias de Usuario al finalizar la implementación funcional.

---

# 📊 Estado actual

| Historia de Usuario | Estado |
|---|---|
| HU-01 — Crear bicicleta | ✅ Completada |
| HU-02 — Iniciar alquiler | ✅ Completada |
| HU-03 — Finalizar alquiler | ✅ Completada |
| HU-04 — Consultar disponibilidad | ⏳ Pendiente |
| Pruebas automatizadas finales | ⏳ Pendiente |

---

# 🚧 Próxima funcionalidad

La siguiente Historia de Usuario será:

```text
HU-04 — Consultar disponibilidad de bicicletas
```

Endpoint previsto:

```http
GET /api/bicicletas/disponibles
```

La consulta debe devolver únicamente bicicletas en estado:

```text
DISPONIBLE
```

---

# 📚 Documentación de Historias de Usuario

Cada Historia de Usuario se documenta en archivos Markdown con:

- Requerimientos funcionales.
- Reglas de negocio.
- Diseño.
- Plan de implementación.
- Validaciones.
- Casos de prueba.
- Definición de terminado.

---

# 👨‍💻 Autor

**Juan García**

Proyecto desarrollado como parte de una prueba técnica para **CEIBA — Soluciones Tecnológicas**.

---

# 🏢 CEIBA

Proyecto desarrollado tomando como referencia los requerimientos de la prueba técnica de **CEIBA**.

> Proyecto con fines académicos y de evaluación técnica.