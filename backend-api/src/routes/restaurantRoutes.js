const express = require('express');
const router = express.Router();
const restaurantController = require('../controllers/restaurantController');

// Rutas públicas
router.get('/', restaurantController.getAllRestaurants);
router.get('/featured', restaurantController.getFeaturedRestaurants);
router.get('/recommended', restaurantController.getRecommendedRestaurants);
router.get('/promotions', restaurantController.getPromotions);
router.get('/ranking', restaurantController.getTopRankedRestaurants);
router.get('/search', restaurantController.searchRestaurants);
router.get('/categories', restaurantController.getCategories);
router.get('/:id', restaurantController.getRestaurantById);

module.exports = router;