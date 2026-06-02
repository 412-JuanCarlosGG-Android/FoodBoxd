package com.example.foodboxd.data.fake

import com.example.foodboxd.domain.model.User
import com.example.foodboxd.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

class FakeUserRepository : UserRepository {

    private val userState = MutableStateFlow(
        User(
            id = "u1",
            name = "Diego Gallego",
            email = "diego.gallego@foodboxd.com",
            avatarUrl = "https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?w=150&auto=format&fit=crop&q=60",
            bio = "Foodie profesional y amante de la pizza al horno de leña. Buscando siempre la mejor experiencia gastronómica.",
            reviewCount = 14,
            favoriteCount = 5
        )
    )

    override fun getCurrentUser(): Flow<User?> = userState

    override suspend fun updateProfile(name: String, bio: String): User {
        val currentUser = userState.value
        val updatedUser = currentUser.copy(name = name, bio = bio)
        userState.value = updatedUser
        return updatedUser
    }
}
