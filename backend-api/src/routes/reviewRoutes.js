const express = require('express');
const router = express.Router();
const reviewController = require('../controllers/reviewController');

// Rutas de reseñas
router.get('/restaurant/:restaurantId', reviewController.getReviewsByRestaurant);
router.post('/restaurant/:restaurantId', reviewController.createReview);

module.exports = router;
