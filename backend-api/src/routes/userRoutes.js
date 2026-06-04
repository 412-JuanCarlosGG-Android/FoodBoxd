const express = require('express');
const router = express.Router();
const userController = require('../controllers/userController');
const authMiddleware = require('../middleware/authMiddleware');

// Registro e Inicio de sesión — rutas públicas
router.post('/register', userController.register);
router.post('/login', userController.login);

// Gestión de perfil — rutas protegidas
router.get('/profile/:id', authMiddleware, userController.getProfile);
router.put('/profile/:id', authMiddleware, userController.updateProfile);
router.put('/profile/:id/favorite', authMiddleware, userController.toggleFavorite);
router.get('/profile/:id/favorites', authMiddleware, userController.getFavorites);

module.exports = router;