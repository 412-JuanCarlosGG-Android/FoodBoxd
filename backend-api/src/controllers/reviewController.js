const Review = require('../models/Review');
const Restaurant = require('../models/Restaurant');

// GET reviews de un restaurante
exports.getReviewsByRestaurant = async (req, res) => {
  try {
    const reviews = await Review.find({ restaurantId: req.params.restaurantId }).sort({ createdAt: -1 });
    res.json(reviews);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

// GET reviews de un usuario
exports.getReviewsByUser = async (req, res) => {
  try {
    const reviews = await Review.find({ userId: req.params.userId }).sort({ createdAt: -1 });
    res.json(reviews);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

// POST crear review
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

    // Actualizar rating promedio del restaurante
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

// PUT editar review propia
exports.updateReview = async (req, res) => {
  const { rating, comment } = req.body;
  try {
    const review = await Review.findById(req.params.reviewId);
    if (!review) {
      return res.status(404).json({ error: 'Reseña no encontrada' });
    }

    review.rating = rating || review.rating;
    review.comment = comment || review.comment;
    await review.save();

    // Recalcular rating del restaurante
    const reviews = await Review.find({ restaurantId: review.restaurantId });
    const reviewCount = reviews.length;
    const avgRating = reviews.reduce((acc, curr) => acc + curr.rating, 0) / reviewCount;

    await Restaurant.findByIdAndUpdate(review.restaurantId, {
      rating: parseFloat(avgRating.toFixed(1)),
      reviewCount
    });

    res.json(review);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

// DELETE eliminar review propia
exports.deleteReview = async (req, res) => {
  try {
    const review = await Review.findById(req.params.reviewId);
    if (!review) {
      return res.status(404).json({ error: 'Reseña no encontrada' });
    }

    const restaurantId = review.restaurantId;
    await review.deleteOne();

    // Recalcular rating del restaurante tras eliminar
    const reviews = await Review.find({ restaurantId });
    const reviewCount = reviews.length;
    const avgRating = reviewCount > 0
      ? reviews.reduce((acc, curr) => acc + curr.rating, 0) / reviewCount
      : 0;

    await Restaurant.findByIdAndUpdate(restaurantId, {
      rating: parseFloat(avgRating.toFixed(1)),
      reviewCount
    });

    res.json({ message: 'Reseña eliminada correctamente' });
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};