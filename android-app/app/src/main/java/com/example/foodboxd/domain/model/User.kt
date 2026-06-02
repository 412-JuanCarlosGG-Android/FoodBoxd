package com.example.foodboxd.domain.model

data class User(
    val id: String,
    val name: String,
    val email: String,
    val avatarUrl: String,
    val bio: String = "",
    val reviewCount: Int = 0,
    val favoriteCount: Int = 0
)
