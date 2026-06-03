const express = require('express');
const router = express.Router();
const reviewController = require('../controllers/reviewController');
const authMiddleware = require('../middleware/authMiddleware');

// Ruta pública — cualquiera puede ver las reseñas
router.get('/restaurant/:restaurantId', reviewController.getReviewsByRestaurant);

// Ruta protegida — solo usuarios autenticados pueden crear reseñas
router.post('/restaurant/:restaurantId', authMiddleware, reviewController.createReview);

module.exports = router;