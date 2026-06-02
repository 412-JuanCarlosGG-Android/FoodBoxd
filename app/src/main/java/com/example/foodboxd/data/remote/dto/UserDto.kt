package com.example.foodboxd.data.remote.dto

/**
 * Representa la estructura JSON que el backend en Node.js enviará para el perfil de usuario.
 * 
 * JSON Ejemplo:
 * {
 *   "id": "60d0fe4f5311236168a109cc",
 *   "name": "Diego Gallego",
 *   "email": "diego.gallego@foodboxd.com",
 *   "avatarUrl": "https://...",
 *   "bio": "Foodie profesional...",
 *   "reviewCount": 14,
 *   "favoriteCount": 5
 * }
 */
data class UserDto(
    val id: String,
    val name: String,
    val email: String,
    val avatarUrl: String,
    val bio: String,
    val reviewCount: Int,
    val favoriteCount: Int
)
