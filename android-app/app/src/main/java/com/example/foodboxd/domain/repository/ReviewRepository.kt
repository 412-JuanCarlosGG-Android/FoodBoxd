package com.example.foodboxd.domain.repository

import com.example.foodboxd.domain.model.Review
import kotlinx.coroutines.flow.Flow

interface ReviewRepository {
    fun getReviewsForRestaurant(restaurantId: String): Flow<List<Review>>
    suspend fun addReview(restaurantId: String, userId: String, rating: Double, comment: String): Review
}
