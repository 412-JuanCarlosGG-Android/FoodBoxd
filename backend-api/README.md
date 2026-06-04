# FoodBoxd Backend API

API REST para la app de reseñas de restaurantes FoodBoxd.

## Instalación

```bash
npm install
npm run dev
```

## Variables de entorno

Copia `.env.example` a `.env` y llena tus credenciales.

## Endpoints

### Restaurantes
| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | /api/restaurants | Listar todos (paginación: ?page=1&limit=10) |
| GET | /api/restaurants?category=Sushi | Filtrar por categoría |
| GET | /api/restaurants/featured | Restaurantes destacados |
| GET | /api/restaurants/recommended | Restaurantes recomendados |
| GET | /api/restaurants/promotions | Promociones |
| GET | /api/restaurants/ranking | Top restaurantes |
| GET | /api/restaurants/search?q=nombre | Buscar por nombre |
| GET | /api/restaurants/categories | Listar categorías |
| GET | /api/restaurants/:id | Detalle de un restaurante |

### Reseñas
| Método | Ruta | Descripción | Auth |
|--------|------|-------------|------|
| GET | /api/reviews/restaurant/:restaurantId | Reviews de un restaurante | No |
| GET | /api/reviews/user/:userId | Reviews de un usuario | No |
| POST | /api/reviews/restaurant/:restaurantId | Crear review | Si |
| PUT | /api/reviews/:reviewId | Editar review | Si |
| DELETE | /api/reviews/:reviewId | Eliminar review | Si |

### Usuarios
| Método | Ruta | Descripción | Auth |
|--------|------|-------------|------|
| POST | /api/users/register | Registrar usuario | No |
| POST | /api/users/login | Iniciar sesión | No |
| GET | /api/users/profile/:id | Ver perfil | Si |
| PUT | /api/users/profile/:id | Editar perfil | Si |
| PUT | /api/users/profile/:id/favorite | Agregar/quitar favorito | Si |
| GET | /api/users/profile/:id/favorites | Ver favoritos | Si |

## Autenticación

Las rutas con Si requieren token JWT en el header:
```
Authorization: Bearer <token>
```