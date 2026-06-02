package com.example.foodboxd.domain.model

data class Review(
    val id: String,
    val restaurantId: String,
    val userId: String,
    val userName: String,
    val userAvatarUrl: String,
    val rating: Double,
    val comment: String,
    val date: String // e.g. "2026-06-02" or friendly date
)
