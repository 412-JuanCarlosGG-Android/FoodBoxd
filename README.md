# FoodBoxd - App de Reseñas de Restaurantes

FoodBoxd es una aplicación móvil para reseñas de restaurantes inspirada en el formato de red social de cine (como Letterboxd), pero adaptada al mundo gastronómico. Este proyecto está diseñado para la materia de **Desarrollo Móvil Android** y se compone de un frontend móvil en Android (Jetpack Compose) y una API backend en Node.js conectada a MongoDB Atlas.

---

## 📂 Estructura del Monorepo

Para facilitar el desarrollo simultáneo y unificar el historial de contribuciones del equipo, el proyecto está organizado como un **Monorepo**:

*   **`/android-app`**: Contiene la aplicación nativa de Android desarrollada en Kotlin utilizando Jetpack Compose, Arquitectura Limpia (Clean Architecture), patrón MVVM e inyección de dependencias manual.
*   **`/backend-api`**: Contiene la API REST desarrollada en Node.js, Express y Mongoose para gestionar los datos y la lógica del negocio.

---

## 👥 Distribución del Trabajo (5 Integrantes)

El equipo está dividido en 3 áreas técnicas clave para asegurar la participación equitativa de todos los integrantes mediante commits individuales:

### 🎨 Área 1: Frontend - Android & Jetpack Compose (2 Integrantes)
Se encargan del diseño visual responsivo de la interfaz, el manejo del estado local de la UI y la integración con la API REST.

*   **Integrante A (UI Principal y Navegación)**
    *   *Responsabilidad*: Estructura de navegación inferior (`FoodBoxdNavGraph`), pantalla de **Inicio** (`HomeScreen`), **Búsqueda** (`SearchScreen`) y **Perfil** (`ProfileScreen`).
    *   *Foco técnico*: Ciclo de vida de composables, layouts responsivos, buscador reactivo y carga de imágenes eficientes (Coil).
*   **Integrante B (UI Detalle e Interacción de Datos)**
    *   *Responsabilidad*: Pantallas de **Detalle de Restaurante** (`DetailScreen`), **Mejores Rankeados / Ranking** (`RankingScreen`) y **Favoritos** (`FavoritesScreen`).
    *   *Foco técnico*: Formularios reactivos (caja de reseñas, calificación con estrellas), sincronización de estados y ViewModels con flujos reactivos (Kotlin Flow).

### ⚙️ Área 2: Backend - Node.js API (2 Integrantes)
Se encargan de la lógica del servidor, la seguridad y de exponer los endpoints que consumirá el frontend.

*   **Integrante C (Servidor y Rutas de Restaurantes)**
    *   *Responsabilidad*: Creación del servidor Express, arquitectura de carpetas backend, endpoints de consulta de restaurantes (`/api/restaurants`), búsqueda por texto y ordenamientos.
*   **Integrante D (Autenticación y Reseñas)**
    *   *Responsabilidad*: Registro e inicio de sesión de usuarios (cifrado con bcrypt y tokens JWT), endpoints para escribir y consultar reseñas (`/api/reviews`) y middleware de seguridad.

### 💾 Área 3: Base de Datos & DevOps - MongoDB Atlas (1 Integrante)
Se encarga de modelar los datos y asegurar la disponibilidad del backend en la nube.

*   **Integrante E (Administrador de Base de Datos - DBA)**
    *   *Responsabilidad*: Configurar el clúster en la nube en **MongoDB Atlas**, definir los esquemas de Mongoose (`User`, `Restaurant`, `Review`), escribir scripts de carga inicial de datos de prueba (*seeding*) y asegurar la consistencia relacional.

---

## 🛠️ Instrucciones de Ejecución

### 📱 1. Frontend (Android)
1. Abre **Android Studio**.
2. Selecciona **Open** y abre **únicamente** la carpeta `android-app/` de este repositorio.
3. Haz clic en **Sync Project with Gradle Files** para descargar las dependencias de navegación y Coil.
4. Ejecuta el proyecto en tu emulador o dispositivo real.
*Nota: Actualmente el frontend está configurado con repositorios de prueba (`FakeRepository`) para funcionar de forma 100% interactiva sin conexión a internet.*

### ⚙️ 2. Backend (Node.js)
1. Entra a la carpeta del backend en tu terminal:
   ```bash
   cd backend-api
   ```
2. Instala las dependencias necesarias:
   ```bash
   npm install
   ```
3. Crea un archivo `.env` en la raíz de `backend-api` basándote en `.env.example` y añade tus credenciales de MongoDB Atlas:
   ```env
   PORT=3000
   MONGODB_URI=mongodb+srv://<usuario>:<password>@<cluster>.mongodb.net/foodboxd
   JWT_SECRET=tu_clave_secreta_jwt
   ```
4. Inicia el servidor en modo desarrollo:
   ```bash
   npm run dev
   ```

---

## 🔀 Flujo de Trabajo en Git y Pull Requests

Para mantener el repositorio libre de conflictos y registrar los commits individuales:

1.  **Nunca trabajes en `main`**: Todos los desarrollos nuevos deben crearse en ramas auxiliares.
    *   Crea tu rama desde la última versión de `main`:
        ```bash
        git checkout main
        git pull
        git checkout -b feature/mi-pantalla-o-endpoint
        ```
2.  **Sube tu rama y crea un Pull Request (PR)**:
    *   Sube tus cambios: `git push origin feature/mi-pantalla-o-endpoint`
    *   Ve a GitHub y abre un Pull Request hacia la rama `main`.
3.  **Revisión Obligatoria**:
    *   Al menos un integrante del equipo debe revisar el código en GitHub y validar que no rompa la compilación antes de autorizar el *Merge*.
