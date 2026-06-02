const mongoose = require('mongoose');

const MenuItemSchema = new mongoose.Schema({
  name: { type: String, required: true },
  price: { type: Number, required: true },
  description: { type: String, default: '' },
  imageUrl: { type: String, default: '' }
});

const RestaurantSchema = new mongoose.Schema({
  name: { type: String, required: true, trim: true },
  imageUrl: { type: String, required: true },
  rating: { type: Number, default: 0.0 },
  reviewCount: { type: Number, default: 0 },
  category: { type: String, required: true },
  description: { type: String, default: '' },
  menuItems: [MenuItemSchema]
}, {
  timestamps: true
});

module.exports = mongoose.model('Restaurant', RestaurantSchema);
