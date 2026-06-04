const express = require('express');
const router = express.Router();
const reviewController = require('../controllers/reviewController');
const authMiddleware = require('../middleware/authMiddleware');

// Rutas públicas
router.get('/restaurant/:restaurantId', reviewController.getReviewsByRestaurant);
router.get('/user/:userId', reviewController.getReviewsByUser);

// Rutas protegidas
router.post('/restaurant/:restaurantId', authMiddleware, reviewController.createReview);
router.put('/:reviewId', authMiddleware, reviewController.updateReview);
router.delete('/:reviewId', authMiddleware, reviewController.deleteReview);

module.exports = router;