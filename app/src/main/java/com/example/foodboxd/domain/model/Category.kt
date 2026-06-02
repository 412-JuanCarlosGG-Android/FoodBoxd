package com.example.foodboxd.domain.model

data class Category(
    val id: String,
    val name: String,
    val iconUrl: String, // Can be an image URL or local vector icon identifier
    val drawableResId: Int? = null // Optional fallback for local drawables
)
