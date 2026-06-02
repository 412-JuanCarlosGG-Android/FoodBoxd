package com.example.foodboxd.domain.model

data class Restaurant(
    val id: String,
    val name: String,
    val imageUrl: String,
    val rating: Double,
    val reviewCount: Int,
    val category: String,
    val description: String,
    val isFavorite: Boolean,
    val menuItems: List<MenuItem> = emptyList()
)

data class MenuItem(
    val id: String,
    val name: String,
    val price: Double,
    val description: String,
    val imageUrl: String
)
