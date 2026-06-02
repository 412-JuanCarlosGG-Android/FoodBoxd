const express = require('express');
const router = express.Router();
const userController = require('../controllers/userController');

// Registro e Inicio de sesión
router.post('/register', userController.register);
router.post('/login', userController.login);

// Gestión de perfil
router.get('/profile/:id', userController.getProfile);
router.put('/profile/:id', userController.updateProfile);
router.put('/profile/:id/favorite', userController.toggleFavorite);

module.exports = router;
