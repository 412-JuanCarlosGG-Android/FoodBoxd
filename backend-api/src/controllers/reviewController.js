const Review = require('../models/Review');
const Restaurant = require('../models/Restaurant');

exports.getReviewsByRestaurant = async (req, res) => {
  try {
    const reviews = await Review.find({ restaurantId: req.params.restaurantId }).sort({ createdAt: -1 });
    res.json(reviews);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

exports.createReview = async (req, res) => {
  const { rating, comment, userName, userAvatarUrl, userId } = req.body;
  const { restaurantId } = req.params;

  try {
    const review = new Review({
      restaurantId,
      userId,
      userName,
      userAvatarUrl,
      rating,
      comment
    });

    await review.save();

    // Actualizar la calificación promedio y el conteo de reseñas del restaurante de forma reactiva
    const reviews = await Review.find({ restaurantId });
    const reviewCount = reviews.length;
    const avgRating = reviews.reduce((acc, curr) => acc + curr.rating, 0) / reviewCount;

    await Restaurant.findByIdAndUpdate(restaurantId, {
      rating: parseFloat(avgRating.toFixed(1)),
      reviewCount
    });

    res.status(201).json(review);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};
