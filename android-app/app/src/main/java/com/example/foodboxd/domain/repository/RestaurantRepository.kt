package com.example.foodboxd.domain.repository

import com.example.foodboxd.domain.model.Category
import com.example.foodboxd.domain.model.Restaurant
import kotlinx.coroutines.flow.Flow

interface RestaurantRepository {
    fun getCategories(): Flow<List<Category>>
    fun getFeaturedRestaurants(): Flow<List<Restaurant>>
    fun getRecommendedRestaurants(): Flow<List<Restaurant>>
    fun getPromotions(): Flow<List<Restaurant>>
    fun getTopRankedRestaurants(): Flow<List<Restaurant>>
    fun searchRestaurants(query: String): Flow<List<Restaurant>>
    fun getFavorites(): Flow<List<Restaurant>>
    fun getRestaurantById(id: String): Flow<Restaurant?>
    suspend fun toggleFavorite(id: String, isFavorite: Boolean)
}
