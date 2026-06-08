const Review = require('../models/Review');
const Restaurant = require('../models/Restaurant');
const User = require('../models/User');

const updateRestaurantRating = async (restaurantId) => {
  const reviews = await Review.find({ restaurantId });
  const reviewCount = reviews.length;
  const avgRating = reviewCount > 0
    ? reviews.reduce((acc, curr) => acc + curr.rating, 0) / reviewCount
    : 0;

  await Restaurant.findByIdAndUpdate(restaurantId, {
    rating: parseFloat(avgRating.toFixed(1)),
    reviewCount
  });
};

exports.getReviewsByRestaurant = async (req, res) => {
  try {
    const reviews = await Review.find({ restaurantId: req.params.restaurantId }).sort({ createdAt: -1 });
    res.json(reviews);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

exports.getReviewsByUser = async (req, res) => {
  try {
    const reviews = await Review.find({ userId: req.params.userId }).sort({ createdAt: -1 });
    res.json(reviews);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

exports.createReview = async (req, res) => {
  const { rating, comment } = req.body;
  const { restaurantId } = req.params;

  try {
    const user = await User.findById(req.user.id);
    if (!user) {
      return res.status(404).json({ error: 'Usuario no encontrado' });
    }

    const review = new Review({
      restaurantId,
      userId: user._id,
      userName: user.name,
      userAvatarUrl: user.avatarUrl,
      rating,
      comment
    });

    await review.save();
    await updateRestaurantRating(restaurantId);

    res.status(201).json(review);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

exports.updateReview = async (req, res) => {
  const { rating, comment } = req.body;

  try {
    const review = await Review.findById(req.params.reviewId);
    if (!review) {
      return res.status(404).json({ error: 'Resena no encontrada' });
    }

    if (review.userId.toString() !== req.user.id) {
      return res.status(403).json({ error: 'No tienes permiso para editar esta resena' });
    }

    review.rating = rating !== undefined ? rating : review.rating;
    review.comment = comment !== undefined ? comment : review.comment;
    await review.save();
    await updateRestaurantRating(review.restaurantId);

    res.json(review);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

exports.deleteReview = async (req, res) => {
  try {
    const review = await Review.findById(req.params.reviewId);
    if (!review) {
      return res.status(404).json({ error: 'Resena no encontrada' });
    }

    if (review.userId.toString() !== req.user.id) {
      return res.status(403).json({ error: 'No tienes permiso para eliminar esta resena' });
    }

    const restaurantId = review.restaurantId;
    await review.deleteOne();
    await updateRestaurantRating(restaurantId);

    res.json({ message: 'Resena eliminada correctamente' });
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};
