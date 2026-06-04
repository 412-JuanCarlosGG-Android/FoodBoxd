const Restaurant = require('../models/Restaurant');

// Mock fallback data to prevent crashing and allow API tests without DB connection
const mockRestaurants = [
  {
    _id: "1",
    name: "Pizzería Bella Italia",
    imageUrl: "https://images.unsplash.com/photo-1513104890138-7c749659a591",
    rating: 4.8,
    reviewCount: 142,
    category: "Italiana",
    description: "Auténtica pizza napolitana al horno de leña, pastas frescas...",
    menuItems: [
      { name: "Pizza Margarita", price: 12.50, description: "Salsa de tomate casera...", imageUrl: "" }
    ]
  },
  {
    _id: "2",
    name: "Sushi Roll & Sake",
    imageUrl: "https://images.unsplash.com/photo-1579871494447-9811cf80d66c",
    rating: 4.5,
    reviewCount: 98,
    category: "Sushi",
    description: "Sushi premium elaborado por itamaes expertos...",
    menuItems: []
  }
];

exports.getAllRestaurants = async (req, res) => {
  try {
    const page = parseInt(req.query.page) || 1;
    const limit = parseInt(req.query.limit) || 10;
    const skip = (page - 1) * limit;

    const dbRestaurants = await Restaurant.find().skip(skip).limit(limit);
    const total = await Restaurant.countDocuments();

    if (dbRestaurants.length === 0 && page === 1) {
      return res.json({
        data: mockRestaurants,
        total: mockRestaurants.length,
        page: 1,
        totalPages: 1
      });
    }

    res.json({
      data: dbRestaurants,
      total,
      page,
      totalPages: Math.ceil(total / limit)
    });
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

exports.getFeaturedRestaurants = async (req, res) => {
  try {
    const featured = await Restaurant.find({ rating: { $gte: 4.7 } }).limit(3);
    if (featured.length === 0) {
      return res.json(mockRestaurants.filter(r => r.rating >= 4.7));
    }
    res.json(featured);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

exports.getRecommendedRestaurants = async (req, res) => {
  try {
    const recommended = await Restaurant.find().limit(3); // Simple logic
    if (recommended.length === 0) {
      return res.json(mockRestaurants);
    }
    res.json(recommended);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

exports.getPromotions = async (req, res) => {
  try {
    const promotions = await Restaurant.find().limit(2); // Simple promo mock
    res.json(promotions.length > 0 ? promotions : mockRestaurants.slice(0, 1));
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

exports.getTopRankedRestaurants = async (req, res) => {
  try {
    const topRanked = await Restaurant.find().sort({ rating: -1 });
    if (topRanked.length === 0) {
      return res.json(mockRestaurants.sort((a, b) => b.rating - a.rating));
    }
    res.json(topRanked);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

exports.searchRestaurants = async (req, res) => {
  const { q } = req.query;
  try {
    if (!q) {
      return res.json(await Restaurant.find() || mockRestaurants);
    }
    const queryRegex = new RegExp(q, 'i');
    const results = await Restaurant.find({
      $or: [
        { name: queryRegex },
        { category: queryRegex },
        { description: queryRegex }
      ]
    });
    
    if (results.length === 0) {
      const mockResults = mockRestaurants.filter(r => 
        r.name.match(queryRegex) || r.category.match(queryRegex) || r.description.match(queryRegex)
      );
      return res.json(mockResults);
    }
    res.json(results);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

exports.getRestaurantById = async (req, res) => {
  try {
    const restaurant = await Restaurant.findById(req.params.id);
    if (!restaurant) {
      // Intentar buscar en mock local
      const mockR = mockRestaurants.find(r => r._id === req.params.id);
      if (!mockR) {
        return res.status(404).json({ error: 'Restaurante no encontrado' });
      }
      return res.json(mockR);
    }
    res.json(restaurant);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};
