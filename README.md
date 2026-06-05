# FoodBoxd — API Backend

API REST para FoodBoxd, una app de reseñas de restaurantes inspirada en Letterboxd. Desarrollada con Node.js, Express y MongoDB Atlas.

---

## Tecnologías

- **Node.js** + **Express**
- **Mongoose** / **MongoDB Atlas**
- **bcrypt** para cifrado de contraseñas
- **JWT** para autenticación

---

## Estructura del proyecto

```
backend-api/
├── app.js              # Entrada, middlewares, montaje de rutas
└── src/
    ├── config/
    │   └── db.js       # Conexión a MongoDB Atlas
    ├── controllers/    # Lógica de cada endpoint
    ├── models/         # Esquemas Mongoose (User, Restaurant, Review)
    └── routes/         # Definición de rutas
```

---

## Endpoints disponibles

| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | `/api/restaurants` | Lista todos los restaurantes |
| GET | `/api/restaurants/featured` | Restaurantes destacados |
| GET | `/api/restaurants/recommended` | Restaurantes recomendados |
| GET | `/api/restaurants/promotions` | Restaurantes en promoción |
| GET | `/api/restaurants/ranking` | Ranking por calificación |
| GET | `/api/restaurants/search?q=` | Búsqueda por texto |
| GET | `/api/restaurants/:id` | Detalle de un restaurante |
| GET | `/api/reviews/restaurant/:restaurantId` | Reseñas de un restaurante |
| POST | `/api/reviews/restaurant/:restaurantId` | Crear reseña |
| POST | `/api/users/register` | Registro de usuario |
| POST | `/api/users/login` | Inicio de sesión (devuelve JWT) |
| GET | `/api/users/profile/:id` | Perfil de usuario |
| PUT | `/api/users/profile/:id` | Actualizar perfil |
| PUT | `/api/users/profile/:id/favorite` | Agregar/quitar favorito |

---

## Instalación y ejecución

```bash
cd backend-api
npm install
```

Crea un archivo `.env` basándote en `.env.example`:

```env
PORT=3000
MONGODB_URI=mongodb+srv://<usuario>:<password>@<cluster>.mongodb.net/foodboxd
JWT_SECRET=tu_clave_secreta_jwt
```

```bash
npm run dev    # desarrollo con hot reload (nodemon)
npm start      # producción
```

---

## Flujo de trabajo Git

1. Nunca trabajes directo en `main`. Crea tu rama desde la última versión:
   ```bash
   git checkout main && git pull
   git checkout -b feature/nombre-del-cambio
   ```
2. Sube tu rama y abre un **Pull Request** hacia `main`.
3. Al menos un integrante debe revisar el PR antes de hacer merge.
