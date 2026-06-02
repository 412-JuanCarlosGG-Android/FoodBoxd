package com.example.foodboxd.data.remote.dto

/**
 * Representa la estructura JSON que el backend en Node.js enviará para los restaurantes.
 * 
 * JSON Ejemplo:
 * {
 *   "id": "60d0fe4f5311236168a109ca",
 *   "name": "Pizzería Bella Italia",
 *   "imageUrl": "https://...",
 *   "rating": 4.8,
 *   "reviewCount": 142,
 *   "category": "Italiana",
 *   "description": "Auténtica pizza napolitana...",
 *   "isFavorite": false,
 *   "menuItems": [
 *     {
 *       "id": "menu_123",
 *       "name": "Pizza Margarita",
 *       "price": 12.50,
 *       "description": "Salsa de tomate casera...",
 *       "imageUrl": "https://..."
 *     }
 *   ]
 * }
 */
data class RestaurantDto(
    val id: String,
    val name: String,
    val imageUrl: String,
    val rating: Double,
    val reviewCount: Int,
    val category: String,
    val description: String,
    val menuItems: List<MenuItemDto> = emptyList()
)

data class MenuItemDto(
    val id: String,
    val name: String,
    val price: Double,
    val description: String,
    val imageUrl: String
)
