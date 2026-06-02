const express = require('express');
const cors = require('cors');
const dotenv = require('dotenv');
const connectDB = require('./src/config/db');

// Cargar variables de entorno
dotenv.config();

// Inicializar Express
const app = express();

// Middlewares
app.use(cors());
app.use(express.json());

// Conectar a la Base de Datos en la nube (MongoDB Atlas)
connectDB();

// Rutas de la API
app.use('/api/restaurants', require('./src/routes/restaurantRoutes'));
app.use('/api/reviews', require('./src/routes/reviewRoutes'));
app.use('/api/users', require('./src/routes/userRoutes'));

// Ruta de prueba inicial
app.get('/', (req, res) => {
  res.json({ message: 'Bienvenido al backend de FoodBoxd API' });
});

// Middleware para manejar rutas inexistentes (404)
app.use((req, res, next) => {
  res.status(404).json({ error: 'Ruta no encontrada' });
});

// Middleware de manejo de errores global
app.use((err, req, res, next) => {
  console.error(err.stack);
  res.status(500).json({ error: 'Error interno del servidor' });
});

// Levantar el servidor
const PORT = process.env.PORT || 3000;
app.listen(PORT, () => {
  console.log(`Servidor de FoodBoxd corriendo en el puerto ${PORT}`);
});
