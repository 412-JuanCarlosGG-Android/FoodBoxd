package com.example.foodboxd.data.remote.dto

/**
 * Representa la estructura JSON que el backend en Node.js enviará para las reseñas.
 * 
 * JSON Ejemplo:
 * {
 *   "id": "60d0fe4f5311236168a109cb",
 *   "restaurantId": "60d0fe4f5311236168a109ca",
 *   "userId": "60d0fe4f5311236168a109cc",
 *   "userName": "Sofía Martínez",
 *   "userAvatarUrl": "https://...",
 *   "rating": 5.0,
 *   "comment": "La mejor pizza margarita...",
 *   "createdAt": "2026-05-28T14:30:00Z"
 * }
 */
data class ReviewDto(
    val id: String,
    val restaurantId: String,
    val userId: String,
    val userName: String,
    val userAvatarUrl: String,
    val rating: Double,
    val comment: String,
    val createdAt: String
)
