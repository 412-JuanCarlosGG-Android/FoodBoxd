package com.example.foodboxd.domain.repository

import com.example.foodboxd.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getCurrentUser(): Flow<User?>
    suspend fun updateProfile(name: String, bio: String): User
}
